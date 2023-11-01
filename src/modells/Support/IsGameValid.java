package modells.Support;

import java.util.Random;

import javax.swing.JOptionPane;

import UI.NewPuzzle;
import modells.GameData.GameData;

/**
 * Die Klasse "IsGameValid" ist verantwortlich für die Validierung und Überprüfung der Benutzereingaben beim Konfigurieren 
 * eines neuen Rätsels im Bridges-Spiel.
 * Sie stellt sicher, dass die eingegebenen Werte für die Breite, Höhe und die Anzahl der Inseln den Anforderungen des Spiels 
 * entsprechen.
 * Wenn die Eingaben gültig sind, ermöglicht sie die Erstellung eines neuen Rätsels, andernfalls zeigt sie Fehlermeldungen an.
 *
 * Methoden:
 *
 * - checkInput(NewPuzzle puzzle, GameData data): Diese Methode überprüft die Benutzereingaben für die Konfiguration eines neuen 
 * Rätsels im Spiel.
 *   Sie validiert die eingegebenen Werte für die Breite, Höhe und die Anzahl der Inseln. Wenn die Eingaben gültig sind, wird ein 
 *   neues Rätsel mit den übergebenen Parametern erstellt.
 *   Sie gibt "true" zurück, wenn die Benutzereingaben gültig sind und ein neues Rätsel erstellt wurde, andernfalls "false".
 *
 * - checkInputValidity(int width, int height): Diese private Methode prüft die Gültigkeit der Benutzereingaben für Breite und Höhe.
 *   Sie überprüft, ob die eingegebene Breite und Höhe innerhalb der vorgegebenen Grenzen liegen und gibt "true" zurück, wenn die 
 *   Eingaben gültig sind, andernfalls "false".
 *
 * - checkIslandsValidity(int width, int height, int islands): Diese private Methode prüft die Gültigkeit der Benutzereingaben für 
 * die Anzahl der Inseln.
 *   Sie überprüft, ob die eingegebene Anzahl der Inseln innerhalb der vorgegebenen Grenzen liegt und gibt "true" zurück, wenn die 
 *   Eingaben gültig sind, andernfalls "false".
 */
public class IsGameValid {

	// Zufallszahlengenerator für die Erstellung von zufälligen Puzzle-Größen
	private Random randomSize = new Random();
	

	/**
	 * Die Methode "checkInput" überprüft die Benutzereingaben für die Konfiguration eines neuen Rätsels im Spiel. Sie validiert
	 * die eingegebenen Werte für die Breite, Höhe und die Anzahl der Inseln, stellt sicher, dass die Werte den Anforderungen
	 * des Spiels entsprechen, und erstellt ein neues Rätsel mit den übergebenen Parametern, wenn die Eingaben gültig sind.
	 *
	 * @param puzzle Das "NewPuzzle"-Fenster, in dem die Benutzereingaben erfolgen.
	 * @param data Ein "GameData"-Objekt, in dem die Informationen für das neue Rätsel gespeichert werden.
	 * @return true, wenn die Benutzereingaben gültig sind und ein neues Rätsel erstellt wurde, andernfalls false.
	 */
	public boolean checkInput(NewPuzzle puzzle, GameData data) {
	    int width, height, islands;

	    try {
	        // Benutzereingaben für Höhe und Breite abrufen
	        height = Integer.parseInt(puzzle.getTfHeight().getText());
	        width = Integer.parseInt(puzzle.getTfWidth().getText());

	        // Benutzereingaben auf Gültigkeit prüfen 
	        if (!checkInputValidity(width, height)) {
	            return false;
	        }

	        // Wenn die Inselanzahl festgelegt ist
	        if (puzzle.getCbDefineIslands().isSelected()) {
	            islands = Integer.parseInt(puzzle.getTfIslands().getText());

	            // Prüfen, ob die Anzahl der Inseln gültig ist
	            if (!checkIslandsValidity(width, height, islands)) {
	                return false;
	            }
	        } else {
	            // Zufällige Anzahl von Inseln zwischen 2 und einem berechneten Maximum generieren
	            int maxRandomIslands = (int) (0.2 * width * height);
	            int minRandomIslands = 2;
	            islands = randomSize.nextInt((maxRandomIslands - minRandomIslands) + 1) + minRandomIslands;
	        }

	        // Dialog schließen und neues Rätsel erstellen
	        puzzle.dispose();
	        data.setWidth(width);
	        data.setHeight(height);
	        data.setIslands(islands);
	        //createBoard(width, height, islands);  // Hier könnte die Logik zur Rätselerstellung stehen
	        return true;
	    } catch (NumberFormatException nfe) {
	        // Fehlermeldung bei ungültigen Eingaben anzeigen
	        JOptionPane.showMessageDialog(null, "Ungültige Eingabe. Bitte geben Sie gültige Zahlen ein.", "Fehlerhafte Eingabe", JOptionPane.ERROR_MESSAGE);
	    }
	    return true;
	}

	
	/**
	 * Prüft die Gültigkeit der Benutzereingaben für Breite und Höhe.
	 *
	 * @param width  Die eingegebene Breite.
	 * @param height Die eingegebene Höhe.
	 * @return true, wenn die Eingaben gültig sind, ansonsten false.
	 */
	private boolean checkInputValidity(int width, int height) {
		int maxWidth = 25;
		int maxHeight = 25;
	    if(width < 4 || width > maxWidth || height < 4 || height > maxHeight) {
	        JOptionPane.showMessageDialog(null, "Breite und Höhe muss zwischem 4 und 25 liegen.", "Ungültige Eingabe", JOptionPane.ERROR_MESSAGE);
	        return false;
	    }
	    return true;
	}

	/**
	 * Prüft die Gültigkeit der Benutzereingaben für die Anzahl der Inseln.
	 *
	 * @param width   Die Breite des Spielfelds.
	 * @param height  Die Höhe des Spielfelds.
	 * @param islands Die eingegebene Anzahl der Inseln.
	 * @return true, wenn die Eingaben gültig sind, ansonsten false.
	 */
	private boolean checkIslandsValidity(int width, int height, int islands) {
	    int maxIslands = (int) (0.2 * width * height);
	    if (islands < 2 || islands > maxIslands) {
	        JOptionPane.showMessageDialog(null, "Die Anzahl der Inseln muss zwischen 2 und " + maxIslands + " liegen.", "Ungültige Eingabe", JOptionPane.ERROR_MESSAGE);
	        return false;
	    }
	    return true;
	}
}
