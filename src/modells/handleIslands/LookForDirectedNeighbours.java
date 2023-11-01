package modells.handleIslands;

import Modell.Island;
import modells.GameData.GameData;
import modells.Support.Directions;

public class LookForDirectedNeighbours {

	
	private GameData data;
	
	public LookForDirectedNeighbours(GameData data) {
		this.data = data;
	}
	
	/**
	 * Überprüft, ob es benachbarte Inseln in der angegebenen Richtung gibt.
	 *
	 * @param chosenIsland Die ausgewählte Insel, von der aus die Überprüfung erfolgt.
	 * @param newPosition Die neue Position in der angegebenen Richtung.
	 * @param direction Die Richtung (EAST, WEST, NORTH oder SOUTH).
	 * @return true, wenn keine benachbarten Inseln vorhanden sind, sonst false.
	 */
	public boolean checkDirectedNeighboursWE(Island chosenIsland, int newPosition, Directions direction) {
	    // Schleife über alle Inseln in der Liste der Inseln
	    for (Island island : data.getListofIslands()) {
	        int x = island.getX();
	        int y = island.getY();
	        
	        // Überprüfen, ob die Richtung EAST oder WEST ist
	        if ((direction == Directions.EAST || direction == Directions.WEST) &&
	            (x == newPosition && (y == chosenIsland.getY() || y == chosenIsland.getY() + 1 || y == chosenIsland.getY() - 1) ||
	            (y == chosenIsland.getY() && (x == newPosition + 1 || x == newPosition - 1)))) {
	            return false; // Es gibt eine benachbarte Insel in dieser Richtung
	        }
	        
	        // Überprüfen, ob die Richtung NORTH oder SOUTH ist
	        else if ((direction == Directions.NORTH || direction == Directions.SOUTH) &&
	            (y == newPosition && (x == chosenIsland.getX() || x == chosenIsland.getX() + 1 || x == chosenIsland.getX() - 1) ||
	            (x == chosenIsland.getX() && (y == newPosition + 1 || y == newPosition - 1)))) {
	            return false; // Es gibt eine benachbarte Insel in dieser Richtung
	        }
	    }

	    return true; // Keine benachbarten Inseln in der angegebenen Richtung gefunden
	}
}
