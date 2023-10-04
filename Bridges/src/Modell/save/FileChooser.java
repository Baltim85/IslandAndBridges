package Modell.save;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.util.ArrayList;

import Modell.Island;

/**
 * Die Klasse `FileChooser` ermöglicht die Auswahl einer Datei, in die der Spielstand gespeichert werden soll.
 * Sie bietet Methoden zum Erstellen eines Dateifilters, zur Erzeugung eines Standarddateinamens und zur Auswahl der Datei.
 */
public class FileChooser {

	/**
	 * Lässt den Benutzer eine Datei auswählen, in die der Spielstand gespeichert werden soll.
	 *
	 * @param width         Die Breite des Spielfeldes.
	 * @param height        Die Höhe des Spielfeldes.
	 * @param islands       Die Anzahl der Inseln auf dem Spielfeld.
	 * @param listOfIslands Eine Liste mit Informationen zu den Inseln auf dem Spielfeld.
	 * @return Die ausgewählte Datei oder null, wenn keine ausgewählt wurde.
	 */
	public File getFileToSave(int width, int height, int islands, ArrayList<Island> listOfIslands) {
		// Erstellt einen Dateiauswahldialog.
		JFileChooser fileChooser = new JFileChooser();
		// Setzt den Dateifilter für .bgs-Dateien.
		setFileChooserFilter(fileChooser);

		// Erzeugt einen Standarddateinamen basierend auf Spielfeldgröße und Anzahl der Inseln.
		File selectedFile = createDefaultFile(width, height, islands, listOfIslands);
		// Setzt die vorgeschlagene Datei im Dialog.
		fileChooser.setSelectedFile(selectedFile);

		// Zeigt den Dialog und wartet auf die Benutzeraktion.
		int returnValue = fileChooser.showSaveDialog(null);

		// Wenn die Benutzeraktion "Speichern" ist, wird die ausgewählte Datei zurückgegeben.
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			return fileChooser.getSelectedFile();
		}

		// Andernfalls wird null zurückgegeben, um anzuzeigen, dass keine Datei ausgewählt wurde.
		return null;
	}

	/**
	 * Setzt den Dateifilter für den Dateiauswahldialog.
	 * Es werden nur .bgs-Dateien angezeigt.
	 * 
	 * @param fileChooser Der Dateiauswahldialog, für den der Filter festgelegt wird.
	 */
	private void setFileChooserFilter(JFileChooser fileChooser) {
		FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("BGS-Dateien (*.bgs)", "bgs");
		fileChooser.setFileFilter(fileFilter);
	}

	/**
	 * Erzeugt einen Standarddateinamen basierend auf Spielfeldgröße und Anzahl der Inseln.
	 * 
	 * @param width         Die Breite des Spielfeldes.
	 * @param height        Die Höhe des Spielfeldes.
	 * @param islands       Die Anzahl der Inseln auf dem Spielfeld.
	 * @param listOfIslands Eine Liste mit Informationen zu den Inseln auf dem Spielfeld.
	 * @return Eine Standarddatei mit einem vorgeschlagenen Dateinamen.
	 */
	private File createDefaultFile(int width, int height, int islands, ArrayList<Island> listOfIslands) {
		// Erstellt einen vorgeschlagenen Dateinamen basierend auf Spielfeldgröße und Anzahl der Inseln.
		String defaultFileName = "Islands " + width + " x " + height + " with " + listOfIslands.size() + " Islands";
		// Erzeugt eine neue Datei mit dem vorgeschlagenen Dateinamen.
		return new File(defaultFileName);
	}
}
