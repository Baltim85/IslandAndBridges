package modells.handleBridges;

import Modell.Island;
import modells.GameData.GameData;
import modells.Support.Directions;


/**
 * Das Interface `CreateNewBridges` dient als Schnittstelle für die Erstellung und Verwaltung von Brücken in einem Spiel.
 * Implementierende Klassen sind verantwortlich für die Implementierung der Methoden, um Brücken in verschiedenen Richtungen 
 * zu erstellen und zu handhaben.
 *
 * Attribute:
 * - Keine, da es sich um ein Interface handelt.
 *
 * Methoden:
 * - `addNewBridge()`: Diese abstrakte Methode ist verantwortlich für das Hinzufügen einer neuen Brücke zwischen zwei Punkten.
 * - `delta(int x1, int x2)`: Diese abstrakte Methode aktualisiert das Brückendelta zwischen zwei Punkten.
 * - `createNorthBridge(boolean leftButton, int selectedID)`: Diese Methode erstellt eine Brücke in nördlicher Richtung von 
 * einer ausgewählten Insel, basierend auf Benutzereingaben und Spielregeln.
 * - `createEastBridge(boolean leftButton, int selectedID)`: Diese Methode erstellt eine Brücke in östlicher Richtung von einer 
 * ausgewählten Insel, basierend auf Benutzereingaben und Spielregeln.
 * - `createSouthBridge(boolean leftButton, int selectedID)`: Diese Methode erstellt eine Brücke in südlicher Richtung von einer 
 * ausgewählten Insel, basierend auf Benutzereingaben und Spielregeln.
 * - `createWestBridge(boolean leftButton, int selectedID)`: Diese Methode erstellt eine Brücke in westlicher Richtung von einer 
 * ausgewählten Insel, basierend auf Benutzereingaben und Spielregeln.
 * - `handleBridgeCreation(boolean leftButton)`: Diese Methode ist abstrakt und dient der Handhabung der Erstellung einer Brücke 
 * in einer bestimmten Richtung, abhängig von den bereitgestellten Eingaben.
 *
 * Zusätzlich bietet das Interface statische Hilfsmethoden zur Abfrage von Inselkoordinaten und zur Überprüfung vorhandener Brücken 
 * in den Spielerdaten.
 * Diese Methoden sind wie folgt:
 *
 * - `getSelectedX(int selectedID, GameData data)`: Gibt die x-Koordinate der ausgewählten Insel basierend auf ihrer ID zurück.
 * - `getSelectedY(int selectedID, GameData data)`: Gibt die y-Koordinate der ausgewählten Insel basierend auf ihrer ID zurück.
 * - `getCenterX(int selectedID, GameData data)`: Gibt die x-Koordinate des Zentrums der ausgewählten Insel basierend auf ihrer 
 * ID zurück.
 * - `getCenterY(int selectedID, GameData data)`: Gibt die y-Koordinate des Zentrums der ausgewählten Insel basierend auf ihrer 
 * ID zurück.
 * - `anotherBridge(Directions direction, int x, int y, int position, GameData data)`: Überprüft, ob es bereits vorhandene Brücken 
 * in den Spielerdaten gibt, die sich an einer bestimmten Position und Richtung befinden.
 */

public interface CreateNewBridges {

	/**
	 * Gibt die x-Koordinate der ausgewählten Insel basierend auf ihrer ID zurück.
	 *
	 * @param selectedID Die ID der ausgewählten Insel.
	 * @return Die x-Koordinate der ausgewählten Insel.
	 */
	static int getSelectedX(int selectedID, GameData data) {
	    Island selectedIsland = data.getDeepCopy().get(selectedID);
	    return selectedIsland.getX();
	}
	
	/**
	 * Gibt die y-Koordinate der ausgewählten Insel basierend auf ihrer ID zurück.
	 *
	 * @param selectedID Die ID der ausgewählten Insel.
	 * @return Die y-Koordinate der ausgewählten Insel.
	 */
	static int getSelectedY(int selectedID, GameData data) {
	    Island selectedIsland = data.getDeepCopy().get(selectedID);
	    return selectedIsland.getY();
	}
	
	/**
	 * Gibt die x-Koordinate des Zentrums der ausgewählten Insel basierend auf ihrer ID zurück.
	 *
	 * @param selectedID Die ID der ausgewählten Insel.
	 * @return Die x-Koordinate des Zentrums der ausgewählten Insel.
	 */
	static int getCenterX(int selectedID, GameData data) {
	    Island selectedIsland = data.getDeepCopy().get(selectedID);
	    return selectedIsland.getCenterX();
	}

	/**
	 * Gibt die y-Koordinate des Zentrums der ausgewählten Insel basierend auf ihrer ID zurück.
	 *
	 * @param selectedID Die ID der ausgewählten Insel.
	 * @return Die y-Koordinate des Zentrums der ausgewählten Insel.
	 */
	static int getCenterY(int selectedID, GameData data) {
	    Island selectedIsland = data.getDeepCopy().get(selectedID);
	    return selectedIsland.getCenterY();
	}
	
	
	/**
	 * Überprüft, ob es bereits vorhandene Brücken in den Spielerdaten gibt, die sich an einer bestimmten Position und Richtung befinden.
	 * 
	 * @param direction Die Richtung, in der nach vorhandenen Brücken gesucht wird.
	 * @param x Die x-Koordinate des Prüfpunkts.
	 * @param y Die y-Koordinate des Prüfpunkts.
	 * @param position Die Position, an der nach Brücken gesucht wird.
	 * @param data Die Spielerdaten, die die Informationen zu den Brücken enthalten.
	 * @return {@code true}, wenn bereits Brücken vorhanden sind, die die Position und Richtung abdecken, andernfalls {@code false}.
	 */
	static boolean anotherBridge(Directions direction, int x, int y, int position, GameData data) {
		if (!data.getBridgesList().isEmpty()) {
	        if (data.getLookForAnotherBridge().checkForAnotherBridge(direction, x, y, position))
	            return true;
	    }
	    return false;
	}
	
    /**
     * Abstrakte Methode zum Hinzufügen einer neuen Brücke.
     */
    abstract void addNewBridge();

    /**
     * Abstrakte Methode zur Aktualisierung des Brückendeltas zwischen zwei Punkten.
     *
     * @param x1 Die x-Koordinate des ersten Punkts.
     * @param x2 Die x-Koordinate des zweiten Punkts.
     */
    abstract void delta(int x1, int x2);

    /**
     * Erstellt eine Brücke in nördlicher Richtung von einer ausgewählten Insel.
     *
     * @param leftButton Ein boolescher Wert, der angibt, ob die linke Maustaste gedrückt ist.
     * @param selectedID Die ID der ausgewählten Insel.
     */
    public void createNorthBridge(boolean leftButton, int selectedID);

    /**
     * Erstellt eine Brücke in östlicher Richtung von einer ausgewählten Insel.
     *
     * @param leftButton Ein boolescher Wert, der angibt, ob die linke Maustaste gedrückt ist.
     * @param selectedID Die ID der ausgewählten Insel.
     */
    public void createEastBridge(boolean leftButton, int selectedID);

    /**
     * Erstellt eine Brücke in südlicher Richtung von einer ausgewählten Insel.
     *
     * @param leftButton Ein boolescher Wert, der angibt, ob die linke Maustaste gedrückt ist.
     * @param selectedID Die ID der ausgewählten Insel.
     */
    public void createSouthBridge(boolean leftButton, int selectedID);

    /**
     * Erstellt eine Brücke in westlicher Richtung von einer ausgewählten Insel.
     *
     * @param leftButton Ein boolescher Wert, der angibt, ob die linke Maustaste gedrückt ist.
     * @param selectedID Die ID der ausgewählten Insel.
     */
    public void createWestBridge(boolean leftButton, int selectedID);

    /**
     * Abstrakte Methode zur Handhabung der Erstellung einer Brücke in einer bestimmten Richtung.
     *
     * @param leftButton Ein boolescher Wert, der angibt, ob die linke Maustaste gedrückt ist.
     */
    abstract void handleBridgeCreation(boolean leftButton);


	


}
