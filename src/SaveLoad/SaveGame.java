package SaveLoad;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import Modell.Island;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;


/**
 * Die SaveGame-Klasse ermöglicht das Speichern des aktuellen Spielstands in einer Datei im BGS-Format.
 * Dabei werden Informationen zur Breite und Höhe des Spielfelds, zur Anzahl der Inseln und den Brücken
 * zwischen den Inseln gespeichert. Die gespeicherte Datei kann später geladen werden, um das Spiel
 * fortzusetzen.
 */
public class SaveGame {
	
	private String header ="FIELD\n# Width x Height | Number of islands\n";
	private String islandHeader = "ISLANDS\n + # { ( Column, Row | Number of bridges ) } \n# Columns and rows are 0 indexed!\n";
	private String bridgesHeader = "\nBRIDGES\n# { ( Start Index, End Index | Double Bridge ) } \n";
	
	/**
	 * Speichert den aktuellen Spielstand in einer Datei.
	 * 
	 * @param width die Breite des Spielfeldes
	 * @param height die Höhe des Spielfeldes
	 * @param islands die Anzahl der Inseln auf dem Spielfeld
	 * @param listofIslands eine ArrayList mit den Informationen zu jeder Insel auf dem Spielfeld
	 */
	public void saveGame(int width, int height, int islands, ArrayList<Island> listofIslands) {
	    // Erzeugen Sie einen Dateiauswahldialog
	    JFileChooser fileChooser = new JFileChooser();

	    // Legen Sie einen Dateifilter für das ".bgs"-Format fest
	    FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("BGS-Dateien (*.bgs)", "bgs");
	    fileChooser.setFileFilter(fileFilter);

	    boolean overwrite = true;

	    while (overwrite) {
	        // Holen Sie die ausgewählte Datei zum Speichern
	        File selectedFile = getFileToSave(fileChooser, width, height, islands, listofIslands);

	        // Überprüfen, ob der Vorgang abgebrochen wurde
	        if (selectedFile == null) {
	            System.out.println("Speichern abgebrochen.");
	            return;
	        }

	        // Überprüfen, ob die ausgewählte Datei bereits existiert
	        if (selectedFile.exists()) {
	            int option = askUserToReplaceFile(selectedFile);

	            if (option == JOptionPane.NO_OPTION) {
	                System.out.println("Speichern abgebrochen.");
	                continue;
	            }
	        }

	        // Versuchen, die Daten in die Datei zu schreiben
	        if (writeToFile(selectedFile, width, height, islands, listofIslands)) {
	            System.out.println("Datei erfolgreich gespeichert: " + selectedFile.getAbsolutePath());
	            overwrite = false;
	        } else {
	            System.err.println("Fehler beim Speichern der Datei.");
	        }
	    }
	}

	/**
	 * Gibt die Datei zurück, in die die Informationen zum Spielstand geschrieben werden sollen.
	 * 
	 * @param fileChooser der Dateiauswahldialog
	 * @param width die Breite des Spielfeldes
	 * @param height die Höhe des Spielfeldes
	 * @param islands die Anzahl der Inseln auf dem Spielfeld
	 * @param listofIslands eine ArrayList mit den Informationen zu jeder Insel auf dem Spielfeld
	 * @return die ausgewählte Datei zum Speichern
	 */
	private File getFileToSave(JFileChooser fileChooser, int width, int height, int islands, ArrayList<Island> listofIslands) {
	    String defaultFileName = "Islands " + width + " x " + height + " with " + listofIslands.size() + " Islands";
	    File defaultFile = new File(defaultFileName);
	    fileChooser.setSelectedFile(defaultFile);

	    int returnValue = fileChooser.showSaveDialog(null);

	    if (returnValue == JFileChooser.APPROVE_OPTION) {
	        return fileChooser.getSelectedFile();
	    }

	    return null;
	}

	/**
	 * Es existiert bereits eine Datei mit dem gleichen Namen. Diese Methode dient zur Bestätigung, ob die Datei wirklich ersetzt werden soll.
	 * 
	 * @param selectedFile die Datei, die eine bereits existierende Datei überschreiben soll
	 * @return ein Dialogfenster zur Bestätigung der Ersetzung
	 */
	private int askUserToReplaceFile(File selectedFile) {
	    return JOptionPane.showConfirmDialog(
	            null,
	            "Die Datei existiert bereits. Möchten Sie sie ersetzen?",
	            "Datei existiert",
	            JOptionPane.YES_NO_OPTION
	    );
	}

	/**
	 * Schreibt die Informationen in die gerade geöffnete Datei.
	 * 
	 * @param selectedFile die Datei, in die geschrieben werden soll
	 * @param width die Breite des Spielfeldes
	 * @param height die Höhe des Spielfeldes
	 * @param islands die Anzahl der Inseln auf dem Spielfeld
	 * @param listofIslands eine ArrayList mit den Informationen aller Inseln auf dem Spielfeld
	 * @return true, wenn die Daten erfolgreich in die Datei geschrieben wurden, ansonsten false
	 */
	private boolean writeToFile(File selectedFile, int width, int height, int islands, ArrayList<Island> listofIslands) {
	    try {
	        // Ihre Methode fillFileWithInfo() hier aufrufen
	        fillFileWithInfo(width, height, islands, listofIslands, selectedFile);
	        return true;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}
    
    
    
    /**
     * Schreibt die Informationen in die ausgewählte Datei zum Speichern.
     * 
     * @param width          die Breite des Spielfeldes
     * @param height         die Höhe des Spielfeldes
     * @param islands        die Anzahl der Inseln auf dem Spielfeld
     * @param listofIslands  eine Liste aller Inseln auf dem Spielfeld
     * @param selectedFile   die Datei, in die die Informationen geschrieben werden sollen.
     */
    private void fillFileWithInfo(int width, int height, int islands, ArrayList<Island> listofIslands, File selectedFile) {
        try (FileOutputStream fos = new FileOutputStream(selectedFile);
             BufferedOutputStream bos = new BufferedOutputStream(fos)) {

            StringBuilder fileContent = new StringBuilder();

            // Füge den Header hinzu
            appendHeader(fileContent);

            // Füge den Info-Text und den Island Header hinzu
            appendInfoAndIslandHeader(fileContent, width, height, islands);

            // Iteriere über die Inseln und füge sie dem fileContent hinzu
            for (Island island : listofIslands) {
                appendIslandInfo(fileContent, island);
            }

            // Füge den bridgesHeader hinzu
            fileContent.append(bridgesHeader);

            // Schreibe alles in die Datei
            bos.write(fileContent.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Fügt den Header zum StringBuilder 'content' hinzu.
     * 
     * @param content der StringBuilder, um den Header zu speichern
     */
    private void appendHeader(StringBuilder content) {
        // Hier den Header hinzufügen
        content.append(header);
    }

    /**
     * Fügt Informationen zu Höhe, Breite des Spielfeldes und der Anzahl der Inseln
     * dem StringBuilder 'content' hinzu.
     * 
     * @param content der StringBuilder, um die Informationen zu speichern
     * @param width   die Breite des Spielfeldes
     * @param height  die Höhe des Spielfeldes
     * @param islands die Anzahl der Inseln auf dem Spielfeld
     */
    private void appendInfoAndIslandHeader(StringBuilder content, int width, int height, int islands) {
        String infoText = width + " x " + height + " | " + islands + "\n\n";
        content.append(infoText);
        content.append(islandHeader);
    }

    /**
     * Fügt Informationen zu einer Insel dem StringBuilder 'content' hinzu.
     * 
     * @param content der StringBuilder, um die Informationen zu speichern
     * @param island  die Insel, die dem StringBuilder hinzugefügt werden soll.
     */
    private void appendIslandInfo(StringBuilder content, Island island) {
        int x = island.getX()-1;
        int y = island.getY()-1;
        int bridges = island.getBridgeCount();
        String islandInfo = "( " + x + ", " + y + " | " + bridges + " )\n";
        content.append(islandInfo);
    }
}