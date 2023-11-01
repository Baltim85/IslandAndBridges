package modells.handleIslands;

import java.util.ArrayList;

import Modell.Island;
import modells.GameData.GameData;

public class UpdateIslandColors {
	
	// Behandlung roter Inseln.
	private RedIslands redIslands;

	// Behandlung grüner Inseln.
	private GreenIslands greenIslands;

	// Zeichnen leerer Inseln.
	private DrawEmptyIsland emptyIslands;
	
	private GameData data;
	
	public UpdateIslandColors(GameData data) {
	    // Initialisieren der Hilfsklassen für das Zeichnen von roten und grünen Inseln.
	    redIslands = new RedIslands();
	    greenIslands = new GreenIslands();

	    // Initialisieren der Klasse zum Zeichnen leerer Inseln.
	    emptyIslands = new DrawEmptyIsland();
	    this.data = data;
	}
	
	/**
	 * Aktualisiert den Brückenzähler und die Farbe einer Insel basierend auf dem Brückenzählerwert.
	 *
	 * @param islandList     Die Liste der Inseln.
	 * @param islandID       Die eindeutige ID der Insel.
	 */
	public void updateIslandColors(ArrayList<Island> islandList, int islandID) {
		Island island = islandList.get(islandID);
		int bridgeCount = island.getBridgeCount();

		if (bridgeCount == 0) {
			greenIslands.fillIslandGreen(island, data);
		} else if (bridgeCount < 0) {
			redIslands.fillIslandRed(island, data);
		} else {
			emptyIslands.emptyIsland(island, data);
		}
	}

}
