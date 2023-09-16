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

/**
 * Die LoadGame-Klasse ermöglicht das Laden von Spielinformationen aus einer Datei.
 * Sie extrahiert die Spielfeldgröße und die Inselinformationen aus der Datei und
 * stellt Methoden bereit, um auf diese Informationen zuzugreifen.
 */
public class LoadGame {
	
	
	private int width, height, islands; // Breite, Höhe und Anzahl der Inseln im Spiel
	private boolean isReading = true; // Eine Flagge, um anzuzeigen, ob der Ladevorgang aktiv ist
	private List<int[]> islandsList = new ArrayList<>(); // Eine Liste zur Speicherung der Inselinformationen

	/**
	 * Konstruktor für die LoadGame-Klasse, der den Ladevorgang für ein Spiel ausführt.
	 */
    public LoadGame() {
        JFileChooser fileChooser = new JFileChooser();

        // Öffnet einen Dateiauswahldialog
        int returnValue = fileChooser.showOpenDialog(null);

        boolean islandsSection = false;

        // Überprüft, ob der Benutzer "Öffnen" ausgewählt hat
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(selectedFile))) {
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    // Zeile ausgeben (für Debugging-Zwecke)
                    // System.out.println(line);

                    if (line.contains("# Width x Height | Number of islands")) {
                        // Zeile mit Spielfeldgröße und Anzahl der Inseln gefunden
                        line = bufferedReader.readLine();
                        String[] parts = line.split("\\|");

                        if (parts.length >= 2) {
                            String dimensions = parts[0].trim();
                            String islandsStr = parts[1].trim();

                            String[] dimensionParts = dimensions.split("x");
                            if (dimensionParts.length >= 2) {
                                setWidth(Integer.parseInt(dimensionParts[0].trim()));
                                setHeight(Integer.parseInt(dimensionParts[1].trim()));
                                setIslands(Integer.parseInt(islandsStr));
                            }
                        }
                    }

                    if (line.contains("ISLANDS")) {
                        islandsSection = true;
                        continue;
                    }

                    if (islandsSection && line.trim().startsWith("(")) {
                        // Zeile mit Inselinformationen gefunden
                        Pattern pattern = Pattern.compile("\\(\\s*(\\d+),\\s*(\\d+)\\s*\\|\\s*(\\d+)\\s*\\)");
                        Matcher matcher = pattern.matcher(line);

                        while (matcher.find()) {
                            int x = Integer.parseInt(matcher.group(1));
                            int y = Integer.parseInt(matcher.group(2));
                            int bridge = Integer.parseInt(matcher.group(3));
                            islandsList.add(new int[]{x, y, bridge});
                        }
                    }
                }

                // Jetzt haben Sie die Liste der Inseln, Sie können sie verwenden oder ausdrucken
                /*for (int[] island : islandsList) {
                    int x = island[0];
                    int y = island[1];
                    int bridge = island[2];

                    System.out.println("Island - X: " + x + ", Y: " + y + ", Bridges: " + bridge);
                }*/

                setReading(false);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Ladevorgang abgebrochen.");
        }
    }

	/*public LoadGame() {
		JFileChooser fileChooser = new JFileChooser();

        // Öffnet einen Dateiauswahldialog
        int returnValue = fileChooser.showOpenDialog(null);
        
        boolean islandsSection = false;
        // Überprüft, ob der Benutzer "Öffnen" ausgewählt hat
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            try {
                // Datei einlesen
                FileReader fileReader = new FileReader(selectedFile);
                BufferedReader bufferedReader = new BufferedReader(fileReader);

                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    //System.out.println(line); // Hier kann die Dateiinhalt verarbeitet werden
                	
                    if(line.contains("# Width x Height | Number of islands")) {
                    	System.out.println(line);
                    	line = bufferedReader.readLine();
                        String[] parts = line.split("\\|");
                       
                        if (parts.length >= 2) {
                            String dimensions = parts[0].trim();
                            String islandsStr = parts[1].trim();

                            String[] dimensionParts = dimensions.split("x");
                            if (dimensionParts.length >= 2) {
                                setWidth(Integer.parseInt(dimensionParts[0].trim()));
                                setHeight(Integer.parseInt(dimensionParts[1].trim()));
                                setIslands(Integer.parseInt(islandsStr));

                            }
                        }
                    }
                   
                        if (line.contains("ISLANDS")) {
                            islandsSection = true;
                            continue;
                        }

                        if (islandsSection && line.trim().startsWith("(")) {
                            Pattern pattern = Pattern.compile("\\(\\s*(\\d+),\\s*(\\d+)\\s*\\|\\s*(\\d+)\\s*\\)");
                            Matcher matcher = pattern.matcher(line);
                            while (matcher.find()) {
                                int x = Integer.parseInt(matcher.group(1));
                                int y = Integer.parseInt(matcher.group(2));
                                int bridge = Integer.parseInt(matcher.group(3));
                                islandsList.add(new int[]{x, y, bridge});
                            }
                        }
                    }

                    // Jetzt haben Sie die Liste der Inseln, Sie können sie verwenden oder ausdrucken
                    for (int[] island : islandsList) {
                        int x = island[0];
                        int y = island[1];
                        int bridge = island[2];

                        System.out.println("Island - X: " + x + ", Y: " + y + ", Bridges: " + bridge);
                    }
                    
                
                setReading(false);
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Ladevorgang abgebrochen.");
        }
    }*/

	
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

