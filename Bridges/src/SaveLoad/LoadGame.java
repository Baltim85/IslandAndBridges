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

import Modell.Bridges;

/**
 * Die LoadGame-Klasse ermöglicht das Laden von Spielinformationen aus einer Datei.
 * Sie extrahiert die Spielfeldgröße und die Inselinformationen aus der Datei und
 * stellt Methoden bereit, um auf diese Informationen zuzugreifen.
 */
public class LoadGame {
	
	
	private int width, height, islands; // Breite, Höhe und Anzahl der Inseln im Spiel
	private boolean isReading = true; // Eine Flagge, um anzuzeigen, ob der Ladevorgang aktiv ist
	private List<int[]> islandsList = new ArrayList<>(); // Eine Liste zur Speicherung der Inselinformationen
	private static final String FIELD_INFO ="# Width x Height | Number of islands";

	private List<int[]> bridgeList = new ArrayList<>();
	
	public List<int[]> getBridgeList() {
		return bridgeList;
	}


	public void setBridgeList(List<int[]> bridgeList) {
		this.bridgeList = bridgeList;
	}

	private ArrayList<Bridges> bridgesList = new ArrayList<>();
	
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
	            
	            // Wenn die Zeile den Beginn des Inselabschnitts anzeigt
	            if (line.contains("BRIDGES")) {
	                islandsSection = true;
	                continue;
	            }

	            // Wenn sich das Programm im Inselabschnitt befindet und die Zeile mit "(" beginnt
	            if (islandsSection && line.trim().startsWith("(")) {
	                // Die Inselinformationen verarbeiten
	                readBridgesInformation(line, islandsSection);
	            }
	        }

	        // Den Lesevorgang beenden
	        setReading(false);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	
    /**
     * Extrahiert und verarbeitet Informationen zu den Inseln aus einer Zeile der Datei und speichert sie in der 'islandsList'.
     *
     * @param line           Die Zeile aus der Datei, die Informationen zu den Inseln enthält.
     * @param islandsSection Ein boolescher Wert, der angibt, ob sich der Parser im Abschnitt der Inselinformationen befindet.
     */
    private void readBridgesInformation(String line, boolean islandsSection) {
        // Überprüfen, ob sich der Parser im Abschnitt der Inselinformationen befindet und ob die Zeile mit '(' beginnt
        if (islandsSection && line.trim().startsWith("(")) {
        	
            // Ein regulärer Ausdruck, um Informationen aus der Zeile zu extrahieren
        	Pattern pattern = Pattern.compile("\\(\\s*(\\d+),\\s*(\\d+)\\s*\\|\\s*(True|False)\\s*\\)");
            Matcher matcher = pattern.matcher(line);

            // Solange Übereinstimmungen in der Zeile gefunden werden
            while (matcher.find()) {
                // Extrahiere die X-Koordinate der Insel
                int firstID = Integer.parseInt(matcher.group(1));

                // Extrahiere die Y-Koordinate der Insel
                int secondID = Integer.parseInt(matcher.group(2));

                // Extrahiere die Anzahl der Brücken, die mit der Insel verbunden sind
                boolean doubleBridge = Boolean.parseBoolean(matcher.group(3));
                int bridgeNumbers = 1;
                if(doubleBridge)
                	bridgeNumbers = 2;
                
                // Erstelle ein Array mit den extrahierten Informationen (X, Y, Anzahl der Brücken)
                //int[] islandInfo = new int[]{x + 1, y + 1, bridge};
                int[] bridgeInfo = new int[]{firstID, secondID, bridgeNumbers};
                
                // Füge das Array zur Liste von Inselinformationen hinzu
                bridgeList.add(bridgeInfo);
            }
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
            }
        }
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

                // Erstelle ein Array mit den extrahierten Informationen (X, Y, Anzahl der Brücken)
                int[] islandInfo = new int[]{x + 1, y + 1, bridge};

                // Füge das Array zur Liste von Inselinformationen hinzu
                islandsList.add(islandInfo);
                
            }
        }
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
}

