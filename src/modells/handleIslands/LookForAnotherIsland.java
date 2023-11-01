package modells.handleIslands;

import java.util.HashSet;
import java.util.Set;

import Modell.Island;
import modells.GameData.GameData;
import modells.Support.Directions;

public class LookForAnotherIsland {
	
	private GameData data;
	
	public LookForAnotherIsland(GameData data) {
		this.data = data;
	}
	
	
	/**
	 * Überprüft, ob auf der angegebenen Position in einer bestimmten Richtung bereits eine Insel existiert.
	 *
	 * @param choosenIsland Die aktuelle Insel, von der aus die Überprüfung durchgeführt wird.
	 * @param newPosition Die neue Position, die überprüft werden soll.
	 * @param direction Die Richtung, in der die Überprüfung erfolgen soll (NORD, OST, SÜD oder WEST).
	 * @return true, wenn die Position frei ist und keine andere Insel vorhanden ist, sonst false.
	 */
	public boolean checkForAnotherIsland(Island choosenIsland, int newPosition, Directions direction, GameData data) {
	    // Eine Menge, um die bereits besetzten Positionen zu verfolgen
	    Set<Integer> occupiedPositions = new HashSet<Integer>();

	    // Abhängig von der Richtung, in der überprüft wird
	    if (direction == Directions.WEST) {
	        // Durchlaufe alle Inseln in der Liste der Inseln
	        for (Island island : data.getListofIslands()) {
	            // Wenn die Insel auf derselben Y-Koordinate wie die aktuelle Insel liegt
	            if (island.getY() == choosenIsland.getY()) {
	                // Füge die X-Koordinate der besetzten Positionen hinzu
	                occupiedPositions.add(island.getX());
	            }
	        }
	        // Überprüfe, ob die gewünschte Position bereits besetzt ist
	        for (int i = choosenIsland.getX() - 1; i > newPosition - 1; i--) {
	            if (occupiedPositions.contains(i)) {
	                return false; // Die Position ist bereits besetzt
	            }
	        }
	    }  
	    
	    // Abhängig von der Richtung, in der überprüft wird
	    else if (direction == Directions.EAST) {
	        // Durchlaufe alle Inseln in der Liste der Inseln
	        for (Island island : data.getListofIslands()) {
	            // Wenn die Insel auf derselben Y-Koordinate wie die aktuelle Insel liegt
	            if (island.getY() == choosenIsland.getY()) {
	                // Füge die X-Koordinate der besetzten Positionen hinzu
	            	occupiedPositions.add(island.getX());
	            }
	        }
	        // Überprüfe, ob die gewünschte Position bereits besetzt ist
	        for (int i = choosenIsland.getX() + 1; i < newPosition + 1; i++) {
	            if (occupiedPositions .contains(i)) {
	                return false; // Die Position ist bereits besetzt
	            }
	        }
	    } 
	    
	    // Abhängig von der Richtung, in der überprüft wird
	    else if (direction == Directions.SOUTH) {
	        // Durchlaufe alle Inseln in der Liste der Inseln
	        for (Island island : data.getListofIslands()) {
	            // Wenn die Insel auf derselben X-Koordinate wie die aktuelle Insel liegt
	            if (island.getX() == choosenIsland.getX()) {
	                // Füge die Y-Koordinate der besetzten Positionen hinzu
	            	occupiedPositions.add(island.getY());
	            }
	        }
	        // Überprüfe, ob die gewünschte Position bereits besetzt ist
	        for (int i = choosenIsland.getY() + 1; i < newPosition + 1; i++) {
	            if (occupiedPositions.contains(i)) {
	                return false; // Die Position ist bereits besetzt
	            }
	        }
	    } 

	    // Abhängig von der Richtung, in der überprüft wird
	    else if (direction == Directions.NORTH) {
	        // Durchlaufe alle Inseln in der Liste der Inseln
	        for (Island island : data.getListofIslands()) {
	            // Wenn die Insel auf derselben X-Koordinate wie die aktuelle Insel liegt
	            if (island.getX() == choosenIsland.getX()) {
	                // Füge die Y-Koordinate der besetzten Positionen hinzu
	            	occupiedPositions.add(island.getY());
	            }
	        }
	        // Überprüfe, ob die gewünschte Position bereits besetzt ist
	        for (int i = choosenIsland.getY() - 1; i > newPosition - 1; i--) {
	            if (occupiedPositions .contains(i)) {
	                return false; // Die Position ist bereits besetzt
	            }
	        }
	    }

	    return true;
	}

}
