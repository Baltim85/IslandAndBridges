package Modell.load;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Die Klasse "GameDataParser" ist für das Analysieren von Spielinformationen aus einer Liste von Zeichenketten verantwortlich, 
 * die aus einer geladenen Datei stammen. Sie extrahiert relevante Informationen wie Spielfeldgröße, Inseln und Brücken aus den 
 * Zeichenketten und erstellt ein GameData-Objekt, das die geladenen Spielfelddaten enthält.

 * Attribute:
 * - islandsList: Eine Liste von Koordinaten (Spalten und Zeilen) der Inseln im Spielfeld.
 * - bridgeList: Eine Liste von Brücken im Spielfeld, wobei jede Brücke durch ihre Start- und Endkoordinaten repräsentiert wird.
 * - FIELD_INFO: Eine Konstante, die die Information über die Spielfeldgröße und die Anzahl der Inseln enthält.
 * - width: Die Breite des Spielfelds.
 * - height: Die Höhe des Spielfelds.
 * - islands: Die Anzahl der Inseln im Spiel.
 * - abordLoad: Eine Flagge, die angibt, ob der Ladevorgang abgebrochen werden soll.

 * Methoden:
 * - parseGameData(List<String> fileContents): Diese Methode analysiert die geladenen Spielfelddaten aus der übergebenen Liste 
 * von Zeichenketten und erstellt ein GameData-Objekt, das die extrahierten Informationen enthält. Sie analysiert die Zeichenketten, 
 * um die Spielfeldgröße, Inseln und Brücken zu extrahieren, und aktualisiert die Attribute der Klasse entsprechend.

 * - setData(GameData gameData): Diese private Methode aktualisiert die Spielfelddaten im übergebenen `GameData`-Objekt 
 * mit den aktuellen Werten dieses Objekts. Sie extrahiert die Spielfeldgröße, die Brücken und die Inseln und aktualisiert 
 * die Attribute des GameData-Objekts.

 * - readFieldInformation(String line): Diese private Methode extrahiert die Spielfeldinformationen aus der gegebenen 
 * Zeichenkette und aktualisiert die entsprechenden Attribute in der Klasse. Sie sucht nach einem bestimmten Format in der 
 * Zeichenkette, um Breite, Höhe und die Anzahl der Inseln zu extrahieren.

 * - parseIslandInfo(String line): Diese Methode extrahiert Informationen über eine Insel aus der gegebenen Zeichenkette und 
 * gibt sie als int-Array zurück. Sie sucht nach einem bestimmten Format in der Zeichenkette, um die x-Koordinate, y-Koordinate 
 * und die Anzahl der Brücken der Insel zu extrahieren.

 * - parseBridgeInfo(String line): Diese statische Methode extrahiert Informationen über eine Brücke aus der gegebenen 
 * Zeichenkette und gibt sie als int-Array zurück. Sie sucht nach einem bestimmten Format in der Zeichenkette, um die 
 * Start-Insel-ID, die End-Insel-ID und die Art der Brücke (einfach oder doppelt) zu extrahieren.

 * - getIslandsList(): Diese Methode gibt die Liste der Koordinaten (Spalten und Zeilen) der Inseln im Spielfeld zurück.

 * - setIslandsList(List<int[]> islandsList): Diese Methode legt die Liste der Koordinaten (Spalten und Zeilen) der Inseln 
 * im Spielfeld fest.

 * - getBridgeList(): Diese Methode gibt die Liste der Koordinaten (Start- und Endkoordinaten) der Brücken im Spielfeld zurück.

 * - setBridgeList(List<int[]> bridgeList): Diese Methode legt die Liste der Koordinaten (Start- und Endkoordinaten) 
 * der Brücken im Spielfeld fest.

 * - isAbordLoad(): Diese Methode gibt an, ob der Ladevorgang abgebrochen wurde.

 * - setAbordLoad(boolean abordLoad): Diese Methode legt fest, ob der Ladevorgang abgebrochen wurde.

 * - getWidth(): Diese Methode gibt die Breite des Spielfelds zurück.

 * - setWidth(int width): Diese Methode legt die Breite des Spielfelds fest.

 * - getHeight(): Diese Methode gibt die Höhe des Spielfelds zurück.

 * - setHeight(int height): Diese Methode legt die Höhe des Spielfelds fest.

 * - getIslands(): Diese Methode gibt die Anzahl der Inseln auf dem Spielfeld zurück.

 * - setIslands(int islands): Diese Methode legt die Anzahl der Inseln auf dem Spielfeld fest.

 * - getFieldInfo(): Diese statische Methode gibt eine Zeichenfolge mit statischer Information über das Spielfeld zurück.

 * Die Klasse "GameDataParser" ist von entscheidender Bedeutung, um geladene Spielfelddaten aus Dateien zu verarbeiten und sie in 
 * ein für das Spiel verständliches Format umzuwandeln. Sie ist verantwortlich für die Analyse und das Parsen der Daten, um 
 * sicherzustellen, dass das Spiel korrekt wiederhergestellt werden kann.
 */
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
	 * Analysiert die geladenen Spielfelddaten aus der übergebenen Liste von Zeichenketten und erstellt ein GameData-Objekt, 
	 * das die extrahierten Informationen enthält.
	 *
	 * @param fileContents Die Liste der Zeichenketten, die die Spielfelddaten darstellen.
	 * @return Ein GameData-Objekt, das die extrahierten Spielfelddaten enthält, oder null, wenn ein Fehler auftritt.
	 */
	public GameDataForLoad parseGameData(List<String> fileContents) {


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
					getIslandsList().add(islandInfo);
				}
			} else if (isParsingBridges) {
				// Extrahiere Brückeninformationen 
				int[] bridgeInfo = parseBridgeInfo(line);
				if (bridgeInfo != null) {
					getBridgeList().add(bridgeInfo);
				}
			}

			// Überprüfe, ob der Ladevorgang abgebrochen werden soll
			if (isAbordLoad()) {
				return null;
			}
		}

		// Erstelle ein GameData-Objekt mit den extrahierten Daten und gib es zurück
		GameDataForLoad gameData = new GameDataForLoad();
		setData(gameData);

		return gameData;
	}

	/**
	 * Aktualisiert die Spielfelddaten im übergebenen `GameData`-Objekt mit den aktuellen Werten dieses Objekts.
	 *
	 * @param gameData Das `GameData`-Objekt, das aktualisiert werden soll.
	 */
	private void setData(GameDataForLoad gameData) {
		gameData.setWidth(getWidth());
		gameData.setHeight(getHeight());
		gameData.setIslands(getIslands());
		gameData.setIslandsList(getIslandsList());
		gameData.setBridgeList(getBridgeList());
	}


	/**
	 * Extrahiert die Spielfeldinformationen aus der gegebenen Zeichenkette und aktualisiert die entsprechenden Attribute in 
	 * der Klasse.
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
	 * @return Ein int-Array mit den extrahierten Inselinformationen [x-Koordinate, y-Koordinate, Anzahl der Brücken] 
	 * oder null, wenn keine gültigen Informationen gefunden wurden.
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
	 * @return Ein int-Array mit den extrahierten Brückeninformationen [Start-Insel-ID, End-Insel-ID, Anzahl der Brücken] 
	 * oder null, wenn keine gültigen Informationen gefunden wurden.
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