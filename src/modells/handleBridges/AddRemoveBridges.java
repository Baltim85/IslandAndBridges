package modells.handleBridges;

import Modell.CreateBridges;
import modells.GameData.GameData;
import modells.Support.Directions;

/**
 * Die Klasse `AddRemoveBridges` ist verantwortlich für das Hinzufügen, Entfernen und Verwalten von Brücken in einem 
 * Brückenrätselspiel.
 * Sie ermöglicht die Interaktion mit Brücken und Inseln im Spiel durch verschiedene Methoden.
 *
 * Attribute:
 * - `data`: Ein Objekt vom Typ `GameData`, das Spielinformationen speichert und für Brückenoperationen verwendet wird.
 * - `selectedID`: Eine Integer-Variable, die die aktuell ausgewählte Insel oder Brücke im Spiel repräsentiert.
 *
 * Konstruktor:
 * - `AddRemoveBridges(GameData data)`: Dieser Konstruktor erstellt eine Instanz der `AddRemoveBridges`-Klasse und 
 * initialisiert sie mit
 *   den erforderlichen Spielerdaten, um Brückenaktionen im Spiel durchzuführen.
 *
 * Methoden:
 * - `removeAndAddDoubleBridges(int id, boolean leftButton, int x1, int y1, int x2, int y2, Directions direction, int selectedID)`: 
 *   Diese Methode verwaltet das Hinzufügen, Entfernen oder Aktualisieren von Brücken im Spiel. Sie durchläuft die Liste der 
 *   vorhandenen
 *   Brücken, vergleicht die IDs der beteiligten Inseln mit den gegebenen IDs und führt je nach Bedingungen und Brückenzustand 
 *   verschiedene Aktionen aus.
 *   Sie gibt `true` zurück, wenn die Brückenverwaltung erfolgreich war, andernfalls `false`.
 *
 * - `createDoubleBridges(int id, int x1, int y1, int x2, y2, Directions direction, CreateBridges bridge)`: Diese Methode erstellt 
 * eine
 *   doppelte Brücke zwischen zwei Inseln. Sie erhöht den Brückenzähler für die ausgewählte Brücke, verringert den Brückenzähler 
 *   für die betroffenen Inseln
 *   und zeichnet die doppelte Brücke auf der Benutzeroberfläche.
 *
 * - `removeSingleBridge(int id, int x1, int y1, int x2, y2, Directions direction, CreateBridges bridge)`: Diese Methode entfernt 
 * eine einzelne
 *   Brücke zwischen zwei Inseln. Sie löscht die Brücke aus der Liste der Brücken, entfernt die visuelle Darstellung der 
 *   Brücke und aktualisiert
 *   den Brückenzähler und die Farben der betroffenen Inseln.
 *
 * - `removeDoubleBrigde(int id, int x1, int y1, int x2, int y2, Directions direction, CreateBridges bridge)`: Diese Methode
entfernt eine doppelte
 *   Brücke zwischen zwei Inseln. Sie entfernt die visuelle Darstellung der doppelten Brücke, aktualisiert den Brückenzähler 
 *   und die Farben der betroffenen
 *   Inseln.
 *
 * - `getSelectedID()`: Diese Methode gibt die aktuell ausgewählte ID einer Insel oder Brücke zurück, die normalerweise für 
 * verschiedene Aktionen
 *   im Spiel verwendet wird.
 *
 * - `setSelectedID(int selectedID)`: Diese Methode setzt die aktuell ausgewählte ID für Inseln oder Brücken im Spiel. Sie 
 * wird verwendet, um die ausgewählte
 *   Insel zu aktualisieren.
 */


public class AddRemoveBridges {
	
	// Das GameData-Objekt speichert die Spielinformationen und wird für Brückenoperationen verwendet.
	private GameData data;

	// Die ausgewählte ID repräsentiert die aktuell ausgewählte Insel oder Brücke im Spiel.
	private int selectedID;
	
	/**
	 * Dieser Konstruktor erstellt eine Instanz der Klasse "AddRemoveBridges". Diese Klasse ist dafür verantwortlich,
	 * Brücken in einem Spiel hinzuzufügen oder zu entfernen. Der Konstruktor initialisiert eine Instanz der Klasse mit
	 * den erforderlichen Spielerdaten, um Brückenaktionen im Spiel durchzuführen.
	 *
	 * @param data Die Spielerdaten, die alle erforderlichen Informationen über das Spiel enthalten, einschließlich der Inseln,
	 *             Brücken und Brückenzähler.
	 */
	public AddRemoveBridges(GameData data) {
		this.data = data;
		
	}
	
	/**
	 * Diese Methode wird aufgerufen, um die Verwaltung von Brücken in einem Spiel durchzuführen. Je nach den angegebenen Parametern
	 * kann sie eine Brücke hinzufügen, entfernen oder aktualisieren. Die Methode durchläuft die Liste der vorhandenen Brücken und
	 * vergleicht die IDs der beteiligten Inseln mit den gegebenen IDs. Basierend auf den Bedingungen und dem Zustand der Brücke
	 * werden verschiedene Aktionen ausgeführt, wie das Entfernen einer Brücke, das Aktualisieren des Brückenzählers und das Zeichnen
	 * einer doppelten Brücke auf der Benutzeroberfläche.
	 *
	 * @param id           Die eindeutige ID der Brücke.
	 * @param leftButton   Ein boolescher Wert, der angibt, ob die Aktion mit der linken Maustaste ausgeführt wird.
	 * @param x1           Die X-Koordinate der ersten Insel.
	 * @param y1           Die Y-Koordinate der ersten Insel.
	 * @param x2           Die X-Koordinate der zweiten Insel.
	 * @param y2           Die Y-Koordinate der zweiten Insel.
	 * @param direction    Die Richtung der Brücke (WEST, OST, NORD oder SÜD).
	 * @param selectedID   Die eindeutige ID der ausgewählten Insel.
	 * @return             True, wenn die Brückenverwaltung erfolgreich abgeschlossen wurde; andernfalls False.
	 */
	public boolean removeAndAddDoubleBridges(int id, boolean leftButton, int x1, int y1, int x2, int y2, Directions direction, int selectedID) {
	    // Durchlaufe die Liste der vorhandenen Brücken.
	    for (CreateBridges bridge : data.getBridgesList()) {
	        int firstID = bridge.getFirstIslandID();
	        int secondID = bridge.getSecondIslandID();

	        // Setze die ausgewählte ID.
	        setSelectedID(selectedID);
	        
	        // Überprüfe, ob die beteiligten Insel-IDs mit den gegebenen IDs übereinstimmen.
	        if ((firstID == id && secondID == getSelectedID()) || (firstID == getSelectedID() && secondID == id)) {
	            // Überprüfe, ob die Aktion mit der linken Maustaste ausgeführt wird.
	            if (!leftButton && bridge.getNumberOfBridges() == 2) {
	                // Doppelte Brücke entfernen, Brückenzähler aktualisieren und True zurückgeben.
	                removeDoubleBrigde(id, x1, y1, x2, y2, direction, bridge);
	                return true;
	            } else if (!leftButton && bridge.getNumberOfBridges() == 1) {
	                // Einzelne Brücke entfernen, Brückenzähler aktualisieren und Brücke aus der Liste entfernen.
	                removeSingleBridge(id, x1, y1, x2, y2, direction, bridge);
	                return true;
	            } else {
	                // Brückenzähler aktualisieren, Insel-Brückenzähler verringern und doppelte Brücke zeichnen.
	                if (bridge.getNumberOfBridges() == 2) {
	                    System.out.println("Zu viele Brücken");
	                    return true;
	                }
	                createDoubleBridges(id, x1, y1, x2, y2, direction, bridge);            
	                return true;
	            }
	        }
	    }
	    // Keine passende Brücke in der Liste gefunden, daher wird False zurückgegeben.
	    return false;
	}

	
	/**
	 * Diese Methode wird aufgerufen, um eine doppelte Brücke zwischen zwei Inseln zu erstellen. Sie erhöht den Brückenzähler für die
	 * ausgewählte Brücke und verringert den Brückenzähler für die betroffenen Inseln. Dann wird die Methode "drawDoubleBridge" aus
	 * dem "DrawBridges"-Objekt aufgerufen, um die visuelle Darstellung der Brücke auf der Benutzeroberfläche zu erstellen.
	 *
	 * @param id        Die eindeutige ID der Brücke.
	 * @param x1        Die X-Koordinate der ersten Insel.
	 * @param y1        Die Y-Koordinate der ersten Insel.
	 * @param x2        Die X-Koordinate der zweiten Insel.
	 * @param y2        Die Y-Koordinate der zweiten Insel.
	 * @param direction Die Richtung der Brücke (WEST, OST, NORD oder SÜD).
	 * @param bridge    Das "CreateBridges"-Objekt, das die erstellte Brücke repräsentiert.
	 */
	private void createDoubleBridges(int id, int x1, int y1, int x2, int y2, Directions direction, CreateBridges bridge) {
	    // Erhöhe den Brückenzähler für die ausgewählte Brücke.
		data.getUpdateBridgeCounter().updateBridgeCounter(bridge);

	    // Verringere den Brückenzähler für die betroffenen Inseln.
		data.getUpdateBridgeCounter().decrementBridgeCounter(id, getSelectedID(), data.getDeepCopy());

	    // Erstelle die visuelle Darstellung der doppelten Brücke auf der Benutzeroberfläche.
	    data.getDrawBridges().drawDoubleBridge(x1, y1, x2, y2, direction, getSelectedID(), id);
	}
	
	/**
	 * Diese Methode wird aufgerufen, um eine einzelne Brücke zwischen zwei Inseln zu entfernen. Sie entfernt die Brücke aus der
	 * Liste der Brücken, ruft die Methode "removeSingleBridge" aus dem "RemoveBridges"-Objekt auf, um die visuelle Darstellung
	 * der Brücke auf der Benutzeroberfläche zu entfernen, und aktualisiert den Brückenzähler und die Farben der betroffenen Inseln.
	 *
	 * @param id        Die eindeutige ID der Brücke.
	 * @param x1        Die X-Koordinate der ersten Insel.
	 * @param y1        Die Y-Koordinate der ersten Insel.
	 * @param x2        Die X-Koordinate der zweiten Insel.
	 * @param y2        Die Y-Koordinate der zweiten Insel.
	 * @param direction Die Richtung der Brücke (WEST, OST, NORD oder SÜD).
	 * @param bridge    Das "CreateBridges"-Objekt, das die zu entfernende Brücke repräsentiert.
	 */
	private void removeSingleBridge(int id, int x1, int y1, int x2, int y2, Directions direction, CreateBridges bridge) {
	    // Entferne die Brücke aus der Liste der Brücken.
	    data.getBridgesList().remove(bridge);

	    // Entferne die visuelle Darstellung der einzelnen Brücke auf der Benutzeroberfläche.
	    data.getRemoveBridges().removeSingleBridge(x1, y1, x2, y2, getSelectedID(), id);

	    // Aktualisiere den Brückenzähler und die Farben der betroffenen Inseln.
	    data.getUpdateBridgeCounter().updateRemoveCounter(bridge, getSelectedID(), id, data.getDeepCopy());
	}
	
	
	/**
	 * Diese Methode wird aufgerufen, um eine doppelte Brücke zwischen zwei Inseln zu entfernen. Sie ruft die Methode "removeDoubleBridge" aus
	 * dem "RemoveBridges"-Objekt auf, um die visuelle Darstellung der Brücke auf der Benutzeroberfläche zu entfernen. Anschließend
	 * werden der Brückenzähler und die Farben der betroffenen Inseln aktualisiert.
	 *
	 * @param id        Die eindeutige ID der Brücke.
	 * @param x1        Die X-Koordinate der ersten Insel.
	 * @param y1        Die Y-Koordinate der ersten Insel.
	 * @param x2        Die X-Koordinate der zweiten Insel.
	 * @param y2        Die Y-Koordinate der zweiten Insel.
	 * @param direction Die Richtung der Brücke (WEST, OST, NORD oder SÜD).
	 * @param bridge    Das "CreateBridges"-Objekt, das die zu entfernende Brücke repräsentiert.
	 */
	private void removeDoubleBrigde(int id, int x1, int y1, int x2, int y2, Directions direction, CreateBridges bridge) {
	    // Entferne die visuelle Darstellung der doppelten Brücke auf der Benutzeroberfläche.
	    data.getRemoveBridges().removeDoubleBridge(x1, y1, x2, y2, getSelectedID(), id, direction);

	    // Aktualisiere den Brückenzähler und die Farben der betroffenen Inseln.
	    data.getUpdateBridgeCounter().updateRemoveCounter(bridge, getSelectedID(), id, data.getDeepCopy());
	}
	
	/**
	 * Diese Methode gibt die aktuell ausgewählte ID einer Insel zurück. Die ausgewählte ID wird normalerweise für
	 * verschiedene Aktionen im Spiel benötigt, wie das Hinzufügen oder Entfernen von Brücken zwischen Inseln.
	 *
	 * @return Die aktuell ausgewählte ID einer Insel.
	 */
	public int getSelectedID() {
	    return selectedID;
	}

	/**
	 * Diese Methode dient dazu, die aktuell ausgewählte ID einer Insel festzulegen. Sie wird normalerweise verwendet,
	 * um die ausgewählte Insel zu aktualisieren, wenn der Spieler zwischen verschiedenen Inseln wechselt oder eine bestimmte
	 * Insel auswählt, um Aktionen im Spiel auszuführen, wie das Hinzufügen von Brücken.
	 *
	 * @param selectedID Die ID der ausgewählten Insel, die festgelegt werden soll.
	 */
	public void setSelectedID(int selectedID) {
	    this.selectedID = selectedID;
	}

}
