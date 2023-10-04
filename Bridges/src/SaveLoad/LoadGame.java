package SaveLoad;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;


/**
 * Die LoadGame-Klasse ermöglicht das Laden eines Spielstands aus einer Datei.
 * Sie extrahiert Informationen über das Spielfeld, die Inseln und die Brücken aus der Datei.
 * Diese Informationen werden dann in entsprechenden Listen gespeichert.
 */
public class LoadGame {


	// Die Breite des Spielfelds
	private int width;

	// Die Höhe des Spielfelds
	private int height;

	// Die Anzahl der Inseln im Spiel
	private int islands;

	// Ein Flag, das anzeigt, ob gerade das Spielfeld eingelesen wird
	private boolean isReading = true;

	// Eine Liste von Koordinaten (Spalten und Zeilen) der Inseln im Spielfeld
	private List<int[]> islandsList = new ArrayList<>();

	// Eine Liste von Brücken im Spielfeld, wobei jede Brücke durch ihre Start- und Endkoordinaten repräsentiert wird
	private List<int[]> bridgeList = new ArrayList<>();

	// Eine Konstante, die die Information über die Spielfeldgröße und die Anzahl der Inseln enthält
	private static final String FIELD_INFO = "# Width x Height | Number of islands";
	
	
	// Eine Flagge, die angibt, ob der Ladevorgang abgebrochen werden soll.
	private boolean abordLoad = false;


	public List<String> loadFile(){
		// Einen Dateiauswahldialog erstellen
		JFileChooser fileChooser = new JFileChooser();

		// Dateiauswahldialog anzeigen und auf die Benutzerwahl warten
		int returnValue = fileChooser.showOpenDialog(null);

		// Überprüfen, ob der Benutzer "Öffnen" ausgewählt hat
		if (returnValue != JFileChooser.APPROVE_OPTION) {
			System.out.println("Ladevorgang abgebrochen.");
			return null; // Ladevorgang abbrechen, wenn der Benutzer "Abbrechen" gewählt hat
		}
		// Die ausgewählte Datei abrufen
		File selectedFile = fileChooser.getSelectedFile();

		List<String> fileContents = new ArrayList<>();
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(selectedFile))) {
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				fileContents.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return fileContents;
	}


	/**
	 * Konstruktor für die LoadGame-Klasse, der den Ladevorgang für ein Spiel ausführt.
	 */
	public LoadGame() {
		// Einen Dateiauswahldialog erstellen
		JFileChooser fileChooser = new JFileChooser();

		// Dateiauswahldialog anzeigen und auf die Benutzerwahl warten
		int returnValue = fileChooser.showOpenDialog(null);

		// Eine Flagge, um den Abschnitt mit den Inselinformationen zu erkennen
		boolean islandsSection = false;
		setAbordLoad(false);

		// Überprüfen, ob der Benutzer "Öffnen" ausgewählt hat
		if (returnValue != JFileChooser.APPROVE_OPTION) {
			System.out.println("Ladevorgang abgebrochen.");
			return; // Ladevorgang abbrechen, wenn der Benutzer "Abbrechen" gewählt hat
		}

		// Die ausgewählte Datei abrufen
		File selectedFile = fileChooser.getSelectedFile();

		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(selectedFile))) {
			String line;

			// Zeilen in der Datei nacheinander lesen
			while ((line = bufferedReader.readLine()) != null) {
				// Wenn die Zeile Informationen zum Spielfeld enthält
				if (line.contains(FIELD_INFO)) {
					line = bufferedReader.readLine();
					// Die Spielfeldinformationen verarbeiten
					readFieldInformation(line, bufferedReader);
				}
				// Wenn die Zeile "FIELD" enthält
				else if (line.contains("FIELD")) {
					// Zeile mit Spielfeldgröße und Anzahl der Inseln gefunden
					line = bufferedReader.readLine();
					// Überprüfen, ob die Zeile auch die Spielfeldinformationen enthält
					if (line.contains(FIELD_INFO)) {
						line = bufferedReader.readLine();
						// Die Spielfeldinformationen verarbeiten
						readFieldInformation(line, bufferedReader);
					} else {
						// Die Spielfeldinformationen verarbeiten
						readFieldInformation(line, bufferedReader);
					}
				}
				

				// Wenn die Zeile den Beginn des Inselabschnitts anzeigt
				if (line.contains("ISLANDS")) {
					islandsSection = true;
					continue;
				}

				// Wenn sich das Programm im Inselabschnitt befindet und die Zeile mit "(" beginnt
				if (islandsSection && line.trim().startsWith("(")) {
					// Die Inselinformationen verarbeiten
					readIslandInformation(line, islandsSection);
				}
				
				if(isAbordLoad())
					return;

				// Wenn die Zeile den Beginn des Brückenabschnitts anzeigt
				if (line.contains("BRIDGES")) {
					islandsSection = true;
					continue;
				}
				

				// Wenn sich das Programm im Brückenabschnitt befindet und die Zeile mit "(" beginnt
				if (islandsSection && line.trim().startsWith("(")) {
					// Die Brückeninformation verarbeiten
					readBridgesInformation(line, islandsSection);
				}
				if(isAbordLoad())
					return;
			}

			// Den Lesevorgang beenden
			setReading(false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}



	/**
	 * Extrahiert und verarbeitet die Spielfeldinformationen aus einer Zeile der Datei.
	 *
	 * @param line           Die Zeile aus der Datei, die die Spielfeldinformationen enthält.
	 * @param bufferedReader Der BufferedReader, der für das Lesen der Datei verwendet wird.
	 */
	private void readFieldInformation(String line, BufferedReader bufferedReader) {
		// Die Zeile in Teile aufteilen, die durch das Trennzeichen '|' getrennt sind
		String[] parts = line.split("\\|");
		
	

		// Überprüfen, ob ausreichend Teile vorhanden sind
		if (parts.length >= 2) {
			// Die erste Komponente enthält die Spielfeldabmessungen im Format "Breite x Höhe"
			String dimensions = parts[0].trim();

			// Die zweite Komponente enthält die Anzahl der Inseln
			String islandsStr = parts[1].trim();

			// Die Spielfeldabmessungen in Breite und Höhe aufteilen
			String[] dimensionParts = dimensions.split("x");

			// Überprüfen, ob ausreichend Teile für Breite und Höhe vorhanden sind
			if (dimensionParts.length >= 2) {
				
				// Die Breite und Höhe des Spielfelds festlegen, indem die Teile in Integer umgewandelt werden
				setWidth(Integer.parseInt(dimensionParts[0].trim()));
				setHeight(Integer.parseInt(dimensionParts[1].trim()));
				
				// Die Anzahl der Inseln festlegen, indem der String in Integer umgewandelt wird
				setIslands(Integer.parseInt(islandsStr));
				if(!isFieldHeaderValid())
					return;
				
			}
		}
	}
	
	/**
	 * Überprüft die Gültigkeit der Spielfeldinformationen, einschließlich Breite, Höhe und Anzahl der Inseln.
	 * Zeigt Fehlermeldungen an, wenn die Spielfeldinformationen ungültig sind.
	 *
	 * @return true, wenn die Spielfeldinformationen gültig sind, andernfalls false.
	 */
	private boolean isFieldHeaderValid() {
	    int maxWidth = 25;
	    int maxHeight = 25;

	    // Überprüfen, ob die Breite und Höhe innerhalb akzeptabler Grenzen liegen
	    if (getWidth() < 4 || getWidth() > maxWidth || getHeight() < 4 || getHeight() > maxHeight) {
	        JOptionPane.showMessageDialog(null, "Ungültige Spielfeldinformation!\n Ladevorgang abgebrochen", "Laden abgebrochen", JOptionPane.ERROR_MESSAGE);
	        setAbordLoad(true);
	        return false;
	    }

	    // Die maximale Anzahl der Inseln berechnen (20% des Spielfelds)
	    int maxIslands = (int) (0.2 * getWidth() * getHeight());

	    // Überprüfen, ob die Anzahl der Inseln innerhalb akzeptabler Grenzen liegt
	    if (getIslands() < 2 || getIslands() > maxIslands) {
	        JOptionPane.showMessageDialog(null, "Ungültige Spielfeldinformation!\n Inseln: " + getIslands() + " Maximale Inseln: " + maxIslands, "Ladevorgang abgebrochen", JOptionPane.ERROR_MESSAGE);
	        setAbordLoad(true);
	        return false;
	    }

	    // Die Spielfeldinformationen sind gültig
	    return true;
	}

	/**
	 * Extrahiert und verarbeitet Informationen zu den Inseln aus einer Zeile der Datei und speichert sie in der 'islandsList'.
	 *
	 * @param line           Die Zeile aus der Datei, die Informationen zu den Inseln enthält.
	 * @param islandsSection Ein boolescher Wert, der angibt, ob sich der Parser im Abschnitt der Inselinformationen befindet.
	 */
	private void readIslandInformation(String line, boolean islandsSection) {
		
		// Überprüfen, ob sich der Parser im Abschnitt der Inselinformationen befindet und ob die Zeile mit '(' beginnt
		if (islandsSection && line.trim().startsWith("(")) {
			// Ein regulärer Ausdruck, um Informationen aus der Zeile zu extrahieren
			Pattern pattern = Pattern.compile("\\(\\s*(\\d+),\\s*(\\d+)\\s*\\|\\s*(\\d+)\\s*\\)");
			Matcher matcher = pattern.matcher(line);

			// Solange Übereinstimmungen in der Zeile gefunden werden
			while (matcher.find()) {
				// Extrahiere die X-Koordinate der Insel
				int x = Integer.parseInt(matcher.group(1));

				// Extrahiere die Y-Koordinate der Insel
				int y = Integer.parseInt(matcher.group(2));

				// Extrahiere die Anzahl der Brücken, die mit der Insel verbunden sind
				int bridge = Integer.parseInt(matcher.group(3));
				
				if(!isIslandValid(x, y, bridge))
					return;

				// Erstelle ein Array mit den extrahierten Informationen (X, Y, Anzahl der Brücken)
				int[] islandInfo = new int[]{x + 1, y + 1, bridge};

				// Füge das Array zur Liste von Inselinformationen hinzu
				islandsList.add(islandInfo);

			}
		}
	}
	
	/**
	 * Überprüft die Gültigkeit der Inselinformationen, einschließlich der X- und Y-Koordinaten sowie der Anzahl der Brücken.
	 * Zeigt Fehlermeldungen an, wenn die Inselinformationen ungültig sind.
	 *
	 * @param x      Die X-Koordinate der Insel.
	 * @param y      Die Y-Koordinate der Insel.
	 * @param bridge Die Anzahl der Brücken, die mit der Insel verbunden sind.
	 * @return true, wenn die Inselinformationen gültig sind, andernfalls false.
	 */
	private boolean isIslandValid(int x, int y, int bridge) {
	    // Überprüfen, ob die X- und Y-Koordinaten und die Anzahl der Brücken innerhalb akzeptabler Grenzen liegen
	    if (x >= getWidth() || y >= getHeight() || bridge > 8 || x < 0 || y < 0) {
	        JOptionPane.showMessageDialog(null, "Ungültige Inselinformationen! \nX: " + x + " Y: " + y + " Brücken: " + bridge, "Ladevorgang abgebrochen", JOptionPane.ERROR_MESSAGE);
	        setAbordLoad(true);
	        return false;
	    }

	    // Die Inselinformationen sind gültig
	    return true;
	}


	/**
	 * Diese Methode liest Informationen über Brücken aus einer Zeile des Spielfelds ein.
	 *
	 * @param line            Die Zeile aus der Datei, die die Brückeninformationen enthält.
	 * @param islandsSection  Ein Flag, das anzeigt, ob sich die Zeile im Abschnitt der Inselinformationen befindet.
	 */
	private void readBridgesInformation(String line, boolean islandsSection) {
		// Überprüfen, ob die Zeile im Abschnitt der Inselinformationen ist und mit "(" beginnt
		if (islandsSection && line.trim().startsWith("(")) {
			// Ein regulärer Ausdruck, um die Brückeninformationen aus der Zeile zu extrahieren
			Pattern pattern = Pattern.compile("\\(\\s*(\\d+),\\s*(\\d+)\\s*\\|\\s*(True|False)\\s*\\)");
			Matcher matcher = pattern.matcher(line);

			// Iteriere durch die Übereinstimmungen im Text
			while (matcher.find()) {
				// Extrahiere die Start- und End-IDs der Brücke aus den Übereinstimmungen
				int firstID = Integer.parseInt(matcher.group(1));
				int secondID = Integer.parseInt(matcher.group(2));

				// Extrahiere, ob es sich um eine Doppelbrücke handelt (True oder False)
				boolean doubleBridge = Boolean.parseBoolean(matcher.group(3));

				// Bestimme die Anzahl der Brücken (1 für einfache Brücke, 2 für Doppelbrücke)
				int bridgeNumbers = 1;
				if (doubleBridge) {
					bridgeNumbers = 2;
				}
				if(!isBridgeValid(firstID, secondID, bridgeNumbers))
					return;

				// Erstelle ein Array mit Informationen über die Brücke (Start-ID, End-ID, Anzahl der Brücken)
				int[] bridgeInfo = new int[]{firstID, secondID, bridgeNumbers};

				// Füge die Brückeninformationen zur Liste der Brücken hinzu
				bridgeList.add(bridgeInfo);
			}
		}
	}
	
	/**
	 * Überprüft die Gültigkeit von Brückeninformationen, einschließlich der IDs der Start- und Endinseln sowie der Anzahl der Brücken.
	 * Zeigt Fehlermeldungen an, wenn die Brückeninformationen ungültig sind.
	 *
	 * @param firstID     Die ID der Startinsel.
	 * @param secondID    Die ID der Endinsel.
	 * @param bridgeNumber Die Anzahl der Brücken zwischen den Inseln.
	 * @return true, wenn die Brückeninformationen gültig sind, andernfalls false.
	 */
	private boolean isBridgeValid(int firstID, int secondID, int bridgeNumber) {
	    // Überprüfen, ob die IDs der Start- und Endinseln innerhalb der zulässigen Bereich (0 bis Anzahl der Inseln) liegen
	    if (firstID < 0 || secondID < 0 || firstID >= getIslands() || secondID >= getIslands()) {
	        JOptionPane.showMessageDialog(null, "Ungültige Brückeninformationen!", "Ladevorgang abgebrochen", JOptionPane.ERROR_MESSAGE);
	        setAbordLoad(true);
	        return false;
	    }

	    // Die Brückeninformationen sind gültig
	    return true;
	}


	/**
	 * Gibt die Liste der Inseln als Arrays zurück.
	 * @return Die Liste der Inseln als Arrays.
	 */
	public List<int[]> getIslandsList() {
		return islandsList;
	}

	/**
	 * Legt die Liste der Inseln als Arrays fest.
	 * @param islandsList Die Liste der Inseln als Arrays.
	 */
	public void setIslandsList(List<int[]> islandsList) {
		this.islandsList = islandsList;
	}

	/**
	 * Gibt die Breite des Spielfelds zurück.
	 * @return Die Breite des Spielfelds.
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Legt die Breite des Spielfelds fest.
	 * @param width Die Breite des Spielfelds.
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * Gibt die Höhe des Spielfelds zurück.
	 * @return Die Höhe des Spielfelds.
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Legt die Höhe des Spielfelds fest.
	 * @param height Die Höhe des Spielfelds.
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * Gibt die Anzahl der Inseln auf dem Spielfeld zurück.
	 * @return Die Anzahl der Inseln auf dem Spielfeld.
	 */
	public int getIslands() {
		return islands;
	}

	/**
	 * Legt die Anzahl der Inseln auf dem Spielfeld fest.
	 * @param islands Die Anzahl der Inseln auf dem Spielfeld.
	 */
	public void setIslands(int islands) {
		this.islands = islands;
	}

	/**
	 * Überprüft, ob das Spiel im Lesezustand ist.
	 * @return true, wenn das Spiel im Lesezustand ist, ansonsten false.
	 */
	public boolean isReading() {
		return isReading;
	}

	/**
	 * Legt fest, ob das Spiel im Lesezustand ist.
	 * @param isReading true, wenn das Spiel im Lesezustand ist, ansonsten false.
	 */
	public void setReading(boolean isReading) {
		this.isReading = isReading;
	}

	/**
	 * Gibt die Liste der Brücken im Spielfeld zurück.
	 *
	 * @return Die Liste der Brücken.
	 */
	public List<int[]> getBridgeList() {
		return bridgeList;
	}

	/**
	 * Setzt die Liste der Brücken im Spielfeld.
	 *
	 * @param bridgeList Die zu setzende Liste der Brücken.
	 */
	public void setBridgeList(List<int[]> bridgeList) {
		this.bridgeList = bridgeList;
	}
	
	/**
	 * Überprüft, ob der Ladevorgang abgebrochen werden soll.
	 *
	 * @return true, wenn der Ladevorgang abgebrochen werden soll, andernfalls false.
	 */
	public boolean isAbordLoad() {
		return abordLoad;
	}


	/**
	 * Legt fest, ob der Ladevorgang abgebrochen werden soll.
	 *
	 * @param abortLoad true, wenn der Ladevorgang abgebrochen werden soll, andernfalls false.
	 */
	public void setAbordLoad(boolean abordLoad) {
		this.abordLoad = abordLoad;
	}

}


