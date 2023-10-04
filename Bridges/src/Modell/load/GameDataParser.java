package Modell.load;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class GameDataParser {

	// Eine Liste von Koordinaten (Spalten und Zeilen) der Inseln im Spielfeld
	private List<int[]> islandsList = new ArrayList<>();

	// Eine Liste von Brücken im Spielfeld, wobei jede Brücke durch ihre Start- und Endkoordinaten repräsentiert wird
	private List<int[]> bridgeList = new ArrayList<>();		

	// Eine Konstante, die die Information über die Spielfeldgröße und die Anzahl der Inseln enthält
	private static final String FIELD_INFO = "# Width x Height | Number of islands";

	// Die Breite des Spielfelds
	private int width;

	// Die Höhe des Spielfelds
	private int height;

	// Die Anzahl der Inseln im Spiel
	private int islands;

	// Eine Flagge, die angibt, ob der Ladevorgang abgebrochen werden soll.
	private boolean abordLoad = false;

	/**
	 * Analysiert die geladenen Spielfelddaten aus der übergebenen Liste von Zeichenketten und erstellt ein GameData-Objekt, das die extrahierten Informationen enthält.
	 *
	 * @param fileContents Die Liste der Zeichenketten, die die Spielfelddaten darstellen.
	 * @return Ein GameData-Objekt, das die extrahierten Spielfelddaten enthält, oder null, wenn ein Fehler auftritt.
	 */
	public GameData parseGameData(List<String> fileContents) {
		List<int[]> islandsList = new ArrayList<>(); // Eine Liste zur Speicherung der extrahierten Inselinformationen
		List<int[]> bridgeList = new ArrayList<>(); // Eine Liste zur Speicherung der extrahierten Brückeninformationen

		// Variablen zur Steuerung des Parsens der verschiedenen Abschnitte der Spielfelddaten
		boolean isParsingField = false; // Wird auf true gesetzt, wenn der "FIELD"-Abschnitt analysiert wird
		boolean isParsingIslands = false; // Wird auf true gesetzt, wenn der "ISLANDS"-Abschnitt analysiert wird
		boolean isParsingBridges = false; // Wird auf true gesetzt, wenn der "BRIDGES"-Abschnitt analysiert wird

		for (String line : fileContents) {
			// Überprüft ob die FIELD_INFO gelesen wird
			if (line.contains(FIELD_INFO)) {
				// Setzt das Parse-Flag auf true
				isParsingField = true;
			}

			// Überprüft ob der Abschnitt FIELD gelesen wird
			if (line.contains("FIELD")) {
				// Setzt das Parse-Flag auf true
				isParsingField = true;
			}

			// Überprüft ob der Abschnitt ISLANDS gelesen wird
			if (line.contains("ISLANDS")) {
				// Setzt das Field-Flag auf false und Island-Flag auf true
				isParsingField = false;
				isParsingIslands = true;
			}

			// Überprüft ob der Abschnitt BRIDGES gelesen wird
			if (line.contains("BRIDGES")) {
				// Setzt beide Flags auf false
				isParsingField = false;
				isParsingIslands = false;
				// Setzt Bridge-Flag auf true
				isParsingBridges = true;
			}

			if (isParsingField) {
				// Extrahiere Feldinformationen 
				readFieldInformation(line);
			} else if (isParsingIslands) {
				// Extrahiere Inselinformationen 
				int[] islandInfo = parseIslandInfo(line);
				if (islandInfo != null) {
					islandsList.add(islandInfo);
				}
			} else if (isParsingBridges) {
				// Extrahiere Brückeninformationen 
				int[] bridgeInfo = parseBridgeInfo(line);
				if (bridgeInfo != null) {
					bridgeList.add(bridgeInfo);
				}
			}

			// Überprüfe, ob der Ladevorgang abgebrochen werden soll
			if (isAbordLoad()) {
				return null;
			}
		}

		// Erstelle ein GameData-Objekt mit den extrahierten Daten und gib es zurück
		GameData gameData = new GameData();
		gameData.setWidth(width);
		gameData.setHeight(height);
		gameData.setIslands(islands);
		gameData.setIslandsList(islandsList);
		gameData.setBridgeList(bridgeList);

		return gameData;
	}





	/**
	 * Extrahiert die Spielfeldinformationen aus der gegebenen Zeichenkette und aktualisiert die entsprechenden Attribute in der Klasse.
	 *
	 * @param line Die Zeichenkette, die die Spielfeldinformationen enthält.
	 */
	private void readFieldInformation(String line) {
		// Definiere ein Regex-Muster, um die Spielfeldinformationen zu extrahieren (z.B., "16 x 24 | 4")
		Pattern pattern = Pattern.compile("(\\d+) x (\\d+) \\| (\\d+)");
		Matcher matcher = pattern.matcher(line);

		// Prüfe, ob das Muster in der Zeichenkette gefunden wird
		if (matcher.find()) {
			// Extrahiere und setze die Breite des Spielfelds (z.B., "16")
			setWidth(Integer.parseInt(matcher.group(1)));

			// Extrahiere und setze die Höhe des Spielfelds (z.B., "24")
			setHeight(Integer.parseInt(matcher.group(2)));

			// Extrahiere und setze die Anzahl der Inseln im Spielfeld (z.B., "4")
			setIslands(Integer.parseInt(matcher.group(3)));
		}
	}





	/**
	 * Extrahiert Informationen über eine Insel aus der gegebenen Zeichenkette und gibt sie als int-Array zurück.
	 *
	 * @param line Die Zeichenkette, die die Inselinformationen enthält.
	 * @return Ein int-Array mit den extrahierten Inselinformationen [x-Koordinate, y-Koordinate, Anzahl der Brücken] oder null, wenn keine gültigen Informationen gefunden wurden.
	 */
	public int[] parseIslandInfo(String line) {
		// Definiere ein Regex-Muster, um die Inselinformationen zu extrahieren (z.B., "(2, 1 | 3)")
		Pattern pattern = Pattern.compile("\\(\\s*(\\d+)\\s*,\\s*(\\d+)\\s*\\|\\s*(\\d+)\\s*\\)");
		Matcher matcher = pattern.matcher(line);

		// Prüfe, ob das Muster in der Zeichenkette gefunden wird
		if (matcher.find()) {
			// Extrahiere und parsen der x-Koordinate der Insel
			int x = Integer.parseInt(matcher.group(1));

			// Extrahiere und parsen der y-Koordinate der Insel
			int y = Integer.parseInt(matcher.group(2));

			// Extrahiere und parsen der Anzahl der Brücken
			int bridges = Integer.parseInt(matcher.group(3));

			// Inkrementiere die Koordinaten um 1, da sie in der Regel von 1 anstatt von 0 beginnen
			return new int[]{x + 1, y + 1, bridges};
		}

		// Falls keine gültigen Informationen gefunden wurden, wird null zurückgegeben
		return null;
	}

	/**
	 * Extrahiert Informationen über eine Brücke aus der gegebenen Zeichenkette und gibt sie als int-Array zurück.
	 *
	 * @param line Die Zeichenkette, die die Brückeninformationen enthält.
	 * @return Ein int-Array mit den extrahierten Brückeninformationen [Start-Insel-ID, End-Insel-ID, Anzahl der Brücken] oder null, wenn keine gültigen Informationen gefunden wurden.
	 */
	public static int[] parseBridgeInfo(String line) {
		// Definiere ein Regex-Muster, um die Brückeninformationen zu extrahieren (z.B., "(2, 1 | True)")
		Pattern pattern = Pattern.compile("\\(\\s*(\\d+),\\s*(\\d+)\\s*\\|\\s*(True|False)\\s*\\)");
		Matcher matcher = pattern.matcher(line);

		// Prüfe, ob das Muster in der Zeichenkette gefunden wird
		if (matcher.find()) {
			// Extrahiere und parsen der Start-Insel-ID
			int startID = Integer.parseInt(matcher.group(1));

			// Extrahiere und parsen der End-Insel-ID
			int endID = Integer.parseInt(matcher.group(2));

			// Extrahiere und parsen, ob es sich um eine doppelte Brücke (True) oder eine einfache Brücke (False) handelt
			boolean isDouble = Boolean.parseBoolean(matcher.group(3));

			// Bestimme die Anzahl der Brücken basierend auf dem Brückentyp (1 für einfache Brücke, 2 für doppelte Brücke)
			int numBridges = isDouble ? 2 : 1;

			// Gib die Brückeninformationen als int-Array zurück
			return new int[]{startID, endID, numBridges};
		}

		// Falls keine gültigen Informationen gefunden wurden, wird null zurückgegeben
		return null;
	}



	/**
	 * Gibt die Liste der Koordinaten (Spalten und Zeilen) der Inseln im Spielfeld zurück.
	 *
	 * @return Die Liste der Inseln als Arrays von int-Werten.
	 */
	public List<int[]> getIslandsList() {
		return islandsList;
	}

	/**
	 * Legt die Liste der Koordinaten (Spalten und Zeilen) der Inseln im Spielfeld fest.
	 *
	 * @param islandsList Die Liste der Inseln als Arrays von int-Werten.
	 */
	public void setIslandsList(List<int[]> islandsList) {
		this.islandsList = islandsList;
	}

	/**
	 * Gibt die Liste der Koordinaten (Start- und Endkoordinaten) der Brücken im Spielfeld zurück.
	 *
	 * @return Die Liste der Brücken als Arrays von int-Werten.
	 */
	public List<int[]> getBridgeList() {
		return bridgeList;
	}

	/**
	 * Legt die Liste der Koordinaten (Start- und Endkoordinaten) der Brücken im Spielfeld fest.
	 *
	 * @param bridgeList Die Liste der Brücken als Arrays von int-Werten.
	 */
	public void setBridgeList(List<int[]> bridgeList) {
		this.bridgeList = bridgeList;
	}


	/**
	 * Gibt an, ob der Ladevorgang abgebrochen wurde.
	 *
	 * @return true, wenn der Ladevorgang abgebrochen wurde, andernfalls false.
	 */
	public boolean isAbordLoad() {
		return abordLoad;
	}

	/**
	 * Legt fest, ob der Ladevorgang abgebrochen wurde.
	 *
	 * @param abordLoad true, wenn der Ladevorgang abgebrochen wurde, andernfalls false.
	 */
	public void setAbordLoad(boolean abordLoad) {
		this.abordLoad = abordLoad;
	}

	/**
	 * Gibt die Breite des Spielfelds zurück.
	 *
	 * @return Die Breite des Spielfelds.
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Legt die Breite des Spielfelds fest.
	 *
	 * @param width Die Breite des Spielfelds.
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * Gibt die Höhe des Spielfelds zurück.
	 *
	 * @return Die Höhe des Spielfelds.
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Legt die Höhe des Spielfelds fest.
	 *
	 * @param height Die Höhe des Spielfelds.
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * Gibt die Anzahl der Inseln auf dem Spielfeld zurück.
	 *
	 * @return Die Anzahl der Inseln auf dem Spielfeld.
	 */
	public int getIslands() {
		return islands;
	}

	/**
	 * Legt die Anzahl der Inseln auf dem Spielfeld fest.
	 *
	 * @param islands Die Anzahl der Inseln auf dem Spielfeld.
	 */
	public void setIslands(int islands) {
		this.islands = islands;
	}

	/**
	 * Gibt eine statische Information über das Spielfeld zurück.
	 *
	 * @return Eine Zeichenfolge mit Informationen über das Spielfeld.
	 */
	public static String getFieldInfo() {
		return FIELD_INFO;
	}
}