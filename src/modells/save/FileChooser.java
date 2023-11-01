package modells.save;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.util.ArrayList;

import Modell.Island;

/**
 * Die Klasse "FileChooser" ermöglicht es dem Benutzer, eine Datei auszuwählen, in die der Spielstand gespeichert werden soll. 
 * Sie stellt Methoden zur Verfügung, um einen Dateiauswahldialog anzuzeigen, einen Standarddateinamen basierend auf den 
 * Spielfeldparametern vorzuschlagen und einen Dateifilter für .bgs-Dateien festzulegen.

 * Methoden:
 * - getFileToSave(int width, int height, int islands, ArrayList<Island> listOfIslands): Diese Methode öffnet einen 
 * Dateiauswahldialog, in dem der Benutzer eine Datei auswählen kann, um den Spielstand zu speichern. Die Methode 
 * akzeptiert die Breite des Spielfeldes, die Höhe des Spielfeldes, die Anzahl der Inseln auf dem Spielfeld und eine 
 * Liste von Informationen zu den Inseln. Sie gibt die ausgewählte Datei zurück oder null, wenn keine ausgewählt wurde.

 * - setFileChooserFilter(JFileChooser fileChooser): Diese private Methode legt einen Dateifilter für den Dateiauswahldialog 
 * fest. Es werden nur .bgs-Dateien angezeigt, um die Auswahl auf speicherbare Spieldateien zu beschränken.

 * - createDefaultFile(int width, int height, int islands, ArrayList<Island> listOfIslands): Diese private Methode erstellt 
 * einen vorgeschlagenen Standarddateinamen basierend auf den Spielfeldparametern. Sie akzeptiert die Breite des Spielfeldes, 
 * die Höhe des Spielfeldes, die Anzahl der Inseln auf dem Spielfeld und eine Liste von Informationen zu den Inseln. 
 * Die Methode generiert einen Standarddateinamen und erstellt eine Datei mit diesem Namen.

 * Die Klasse "FileChooser" erleichtert dem Benutzer die Speicherung des Spielstands, indem sie einen benutzerfreundlichen 
 * Dateiauswahldialog und sinnvolle Standarddateinamen bereitstellt. Der Dateifilter stellt sicher, dass nur gültige 
 * Spieldateien ausgewählt werden können, um die Konsistenz der gespeicherten Daten sicherzustellen.
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
