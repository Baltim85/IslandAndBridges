package Modell.save;

import Modell.CreateBridges;
import Modell.Island;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JOptionPane;


/**
 * Die Klasse `SaveGame` stellt eine Schnittstelle zur Verfügung, um Spielstände zu speichern und
 * enthält eine Liste von Brückenobjekten zur Speicherung erstellter Brücken.
 */
public class SaveGame {


	/**
	 * Die ArrayList "listOfBridges" enthält Objekte vom Typ "CreateBridges".
	 * Diese Liste wird verwendet, um erstellte Brückenobjekte zu speichern.
	 */
	private ArrayList<CreateBridges> listOfBridges = new ArrayList<CreateBridges>();

	// FileChooser-Instanz für die Dateiauswahl und SaveFileHandler-Instanz für das Speichern von Dateien
	private final FileChooser fileChooser;
	private final SaveFileHandler fileHandler;

	// Ein Flag das angibt ob der Speichervorgang abgebrochen wurden
	private boolean cancelSave = false;



	/**
	 * Konstruktor für die SaveGame-Klasse. Initialisiert FileChooser und SaveFileHandler.
	 */
	public SaveGame() {
		this.fileChooser = new FileChooser();
		this.fileHandler = new SaveFileHandler();
	}

	/**
	 * Methode zum Speichern des aktuellen Spielstands in einer Datei.
	 *
	 * @param width         Die Breite des Spielfeldes.
	 * @param height        Die Höhe des Spielfeldes.
	 * @param islands       Die Anzahl der Inseln auf dem Spielfeld.
	 * @param listOfIslands Eine Liste mit Informationen zu den Inseln auf dem Spielfeld.
	 * @param listOfBridges Eine Liste mit Informationen zu den Brücken auf dem Spielfeld.
	 */
	public void saveGame(int width, int height, int islands, ArrayList<Island> listOfIslands, ArrayList<CreateBridges> listOfBridges) {
		// Fordert den Benutzer auf, eine Datei zum Speichern auszuwählen.
		File selectedFile = fileChooser.getFileToSave(width, height, islands, listOfIslands);

		// Überprüft, ob eine Datei ausgewählt wurde.
		if (selectedFile != null) {
			// Versucht, den Spielstand in die ausgewählte Datei zu speichern.
			if (!fileHandler.saveToFile(selectedFile, width, height, islands, listOfIslands, listOfBridges)) {
				// Wenn das Speichern fehlschlägt, wird eine Fehlermeldung ausgegeben.
				System.err.println("Fehler beim Speichern der Datei.");
				setCancelSave(true);
				return;
			}
		} else {
			// Wenn keine Datei ausgewählt wurde, wird eine Meldung ausgegeben.
			System.out.println("Speichern abgebrochen.");
			JOptionPane.showMessageDialog(null, "Speichervorgang wurde abgebrochen!", "Speichern abgebrochen", JOptionPane.ERROR_MESSAGE);
			setCancelSave(true);
			return;
		}
		setCancelSave(false);
	}


	/**
	 * Gibt die ArrayList "listOfBridges" zurück, die Brückenobjekte enthält.
	 *
	 * @return Die ArrayList "listOfBridges".
	 */
	public ArrayList<CreateBridges> getListOfBridges() {
		return listOfBridges;
	}

	/**
	 * Legt die ArrayList "listOfBridges" fest, um Brückenobjekte zu speichern.
	 *
	 * @param listOfBridges Die zu setzende ArrayList "listOfBridges".
	 */
	public void setListOfBridges(ArrayList<CreateBridges> listOfBridges) {
		this.listOfBridges = listOfBridges;
	}

	/**
	 * Gibt an, ob der Speichervorgang abgebrochen wurde.
	 *
	 * @return true, wenn der Speichervorgang abgebrochen wurde, andernfalls false.
	 */
	public boolean isCancelSave() {
		return cancelSave;
	}

	/**
	 * Legt fest, ob der Speichervorgang abgebrochen wurde.
	 *
	 * @param cancelSave true, wenn der Speichervorgang abgebrochen wurde, andernfalls false.
	 */
	public void setCancelSave(boolean cancelSave) {
		this.cancelSave = cancelSave;
	}
}