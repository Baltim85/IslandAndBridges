package modells.save;


import Controller.DialogController;
import Modell.CreateBridges;
import modells.GameData.GameData;


/**
 * Die "MainSaveFile"-Klasse ist für das Speichern des aktuellen Spielstands und der dazugehörigen Parameter verantwortlich. 
 * Sie ermöglicht es, den aktuellen Zustand des Spiels, einschließlich Spielfeldabmessungen, 
 * Anzahl der Inseln und die Platzierung von Inseln und Brücken, zu speichern und später wiederherzustellen. 
 * Dies ist nützlich, um den Fortschritt eines Brückenrätsels zu sichern und zu einem späteren Zeitpunkt fortzusetzen.
 *
 * Methoden:
 *
 * - SaveGame(GameData data): Diese Methode speichert den aktuellen Spielstand und die dazugehörigen Parameter
 *   auf Grundlage der übergebenen Spielinformationsdaten (GameData). Zunächst werden die Spielfeldabmessungen 
 *   (Breite und Höhe) sowie die Anzahl der Inseln extrahiert. Anschließend wird eine Instanz von "SaveGame" erstellt, 
 *   die für das Speichern von Spielständen zuständig ist. Die Methode überprüft, ob das Dialogfeld zum Schließen 
 *   des Spiels bestätigt wurde, und führt das Speichern nur durch, wenn dies nicht der Fall ist. 
 *   Nach erfolgreichem Speichern wird ein Dialogfenster angezeigt, um den Benutzer über den Erfolg des Speicherns zu informieren.

 * Die "MainSaveFile"-Klasse spielt eine entscheidende Rolle bei der Sicherung des Spielstands und der Ermöglichung 
 * des fortgesetzten Spielens. Sie arbeitet eng mit der "SaveGame"-Klasse zusammen, um die Spielinformationen zu speichern, 
 * und nutzt den "DialogController", um den Benutzer über den Fortschritt und Erfolg des Speichervorgangs zu informieren. 
 * Dies ermöglicht eine nahtlose und benutzerfreundliche Spielerfahrung.
 */
public class MainSaveFile {
	

	/**
	 * Speichert den aktuellen Spielstand und die dazugehörigen Parameter.
	 *
	 * @param data Die Spielinformationsdaten, die gespeichert werden sollen.
	 */
	public void SaveGame(GameData data) {
	    // Extrahiert die Spielparameter aus den übergebenen Daten
	    int width = data.getWidth();
	    int height = data.getHeight();
	    int islands = data.getIslands();
	    
	    // Erstellt eine Instanz für das Speichern von Spielständen
	    SaveGame save = new SaveGame();
	    
	    // Ruft die Dialog-Controller-Instanz aus den Spielinformationsdaten ab
	    DialogController dialogController = data.getDialogController();
	    
	    // Überprüft, ob das Dialogfeld zum Schließen bestätigt wurde
	    if (dialogController.DisposeView(data.getBridges()))
	        return;
	    else {
	        // Speichert das Spiel mit den aktuellen Parametern und der Liste der Inseln und Brücken
	        save.saveGame(width, height, islands, data.getListofIslands(), data.getBridgesList(), data.getDialogController());
	        
	        if (data.getBridgesList() != null) {
	            // Debug-Ausgabe: Anzahl der Brücken
	            for (CreateBridges bridges : data.getBridgesList()) {
	                System.out.println(bridges.getNumberOfBridges());
	            }
	        }
	        
	        // Überprüft, ob das Speichern abgebrochen wurde
	        if (save.isCancelSave())
	            return;
	        
	        // Zeigt ein Dialogfenster an, um den Erfolg des Speicherns zu bestätigen
	        dialogController.saveSuccessfully();
	    }
	}


}
