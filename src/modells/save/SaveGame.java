package modells.save;

import Modell.CreateBridges;
import Modell.Island;

import java.io.File;
import java.util.ArrayList;


import Controller.DialogController;


/**
 * Die "SaveGame" Klasse ist verantwortlich für das Speichern des aktuellen Spielstands in einer Datei. 
 * Sie ermöglicht es dem Benutzer, einen Speicherort auszuwählen und den Spielstand, einschließlich der Spielfeldgröße, 
 * der Inselinformationen und der Brückeninformationen, in einer Datei zu sichern.
 * 
 * Attribute:
 * - listOfBridges (ArrayList<CreateBridges>): Eine Liste von Objekten des Typs "CreateBridges", die dazu verwendet wird, 
 * erstellte Brückenobjekte zu speichern.
 * - fileChooser (FileChooser): Eine Instanz der "FileChooser"-Klasse zur Auswahl der Speicherdatei.
 * - fileHandler (SaveFileHandler): Eine Instanz der "SaveFileHandler"-Klasse zur Verarbeitung des Speichervorgangs.
 * - cancelSave (boolean): Ein Flag, das anzeigt, ob der Speichervorgang abgebrochen wurde.
 * 
 * Konstruktor:
 * - SaveGame(): Dieser Konstruktor initialisiert die "fileChooser" und "fileHandler" Instanzen, die für die Dateiauswahl 
 * und das Speichern der Datei verwendet werden.
 * 
 * Methoden:
 * - saveGame(int width, int height, int islands, ArrayList<Island> listOfIslands, ArrayList<CreateBridges> listOfBridges, 
 * DialogController dialogController): Diese Methode fordert den Benutzer auf, eine Datei zum Speichern auszuwählen, 
 * und speichert den aktuellen Spielstand in der ausgewählten Datei. Sie akzeptiert Informationen zur Spielfeldgröße, 
 * den Inseln und den Brücken sowie eine Instanz des "DialogController" zur Handhabung von Fehler- und Abbruchmeldungen.
 * - getListOfBridges(): Diese Methode gibt die ArrayList "listOfBridges" zurück, die Brückenobjekte enthält.
 * - setListOfBridges(ArrayList<CreateBridges> listOfBridges): Diese Methode legt die ArrayList "listOfBridges" fest, 
 * um Brückenobjekte zu speichern.
 * - isCancelSave(): Diese Methode gibt an, ob der Speichervorgang abgebrochen wurde, und gibt "true" zurück, wenn der 
 * Vorgang abgebrochen wurde, andernfalls "false".
 * - setCancelSave(boolean cancelSave): Diese Methode legt fest, ob der Speichervorgang abgebrochen wurde, basierend auf 
 * dem übergebenen Boolean-Wert.
 * 
 * Die "SaveGame" Klasse ermöglicht die Sicherung des aktuellen Spielstands in einer Datei und spielt eine wichtige
 * Rolle bei der Verwaltung von Spielständen und dem Fortschritt in der Anwendung oder dem Spiel.
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
	public void saveGame(int width, int height, int islands, ArrayList<Island> listOfIslands, ArrayList<CreateBridges> listOfBridges, DialogController dialogController) {
		// Fordert den Benutzer auf, eine Datei zum Speichern auszuwählen.
		File selectedFile = fileChooser.getFileToSave(width, height, islands, listOfIslands);

		// Überprüft, ob eine Datei ausgewählt wurde.
		if (selectedFile != null) {
			// Versucht, den Spielstand in die ausgewählte Datei zu speichern.
			if (!fileHandler.saveToFile(selectedFile, width, height, islands, listOfIslands, listOfBridges)) {
				// Wenn das Speichern fehlschlägt, wird eine Fehlermeldung ausgegeben.
				dialogController.errorWhileSaving(this);
				return;
			}
		} else {
			// Wenn keine Datei ausgewählt wurde, wird eine Meldung ausgegeben.
			dialogController.cancelSave(this);
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