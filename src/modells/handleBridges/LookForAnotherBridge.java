package modells.handleBridges;

import Modell.CreateBridges;
import modells.GameData.GameData;
import modells.Support.Directions;

/**
 * Die Klasse "LookForAnotherBridge" ist ein Hilfsdienst, der speziell darauf ausgerichtet ist, nach vorhandenen Brücken in den Spielerdaten zu suchen. 
 * Ihr Zweck besteht darin, sicherzustellen, dass bei der Erstellung von Brücken im Spiel keine überlappenden Brücken entstehen.
 *
 * Diese Klasse prüft die Spielerdaten auf das Vorhandensein von Brücken und ermöglicht es, festzustellen, ob eine geplante Brücke 
 * eine bereits existierende Brücke in einer bestimmten Richtung kreuzen würde. Sie dient dazu, mögliche Konflikte bei der 
 * Brückenerstellung zu verhindern und sicherzustellen, dass die Spielregeln für die Verbindung von Inseln und Brücken eingehalten werden.
 *
 * Die Hauptfunktion der Klasse ist die Methode "checkForAnotherBridge", die in einer gegebenen Richtung prüft, ob es bereits 
 * eine Brücke gibt, die die ausgewählte Position kreuzt. Wenn eine kreuzende Brücke gefunden wird, wird dies gemeldet, um 
 * sicherzustellen, dass keine ungültigen Brücken erstellt werden.
 *
 * "LookForAnotherBridge" ist ein wichtiger Bestandteil der Brückenverwaltung im Spiel und trägt dazu bei, ein kohärentes 
 * und regelkonformes Spielerlebnis sicherzustellen.
 */
public class LookForAnotherBridge {

	
	private GameData data;
	
	
	/**
	 * Ein Hilfsdienst, der darauf spezialisiert ist, nach vorhandenen Brücken in den Spielerdaten zu suchen.
	 * Er wird verwendet, um sicherzustellen, dass keine überlappenden Brücken erstellt werden.
	 *
	 * @param data Die Spielerdaten, die verwendet werden, um nach vorhandenen Brücken zu suchen.
	 */
	public LookForAnotherBridge(GameData data) {
		this.data = data;
	}
	
	/**
	 * Überprüft, ob es eine andere Brücke in der angegebenen Richtung gibt, die die ausgewählte Position kreuzt.
	 *
	 * @param direction   Die Richtung, in der überprüft werden soll (WEST, OST, NORD oder SÜD).
	 * @param selectedY   Die ausgewählte Y-Koordinate.
	 * @param selectedX   Die ausgewählte X-Koordinate.
	 * @param i           Der Index, der überprüft wird.
	 * @return            True, wenn eine andere Brücke die ausgewählte Position kreuzt, andernfalls False.
	 */
	public boolean checkForAnotherBridge(Directions direction, int selectedX, int selectedY, int i) {
		for (CreateBridges bridge : data.getBridgesList()) {
			int firstX = bridge.getFirstIslandX();
			int firstY = bridge.getFirstIslandY();
			int secondY = bridge.getSecondIslandY();
			int secondX = bridge.getSecondIslandX();

			if (direction == Directions.WEST || direction == Directions.EAST) {
				// Überprüft, ob die Brücke in WEST oder OST verläuft
				if (firstX == i && ((firstY < selectedY && secondY > selectedY) || (secondY < selectedY && firstY > selectedY))) {
					System.out.println("Error: Eine Brücke kreuzt diese Position.");
					return true;
				}
			}

			if (direction == Directions.NORTH || direction == Directions.SOUTH) {
				// Überprüft, ob die Brücke in NORD oder SÜD verläuft
				if (firstY == i && ((firstX < selectedX && secondX > selectedX) || (secondX < selectedX && firstX > selectedX))) {
					System.out.println("Error: Eine Brücke kreuzt diese Position.");
					return true;
				}
			}
		} 

		// Es wurde keine kreuzende Brücke gefunden.
		return false;
	}
}
