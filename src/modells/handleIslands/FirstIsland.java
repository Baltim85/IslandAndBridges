package modells.handleIslands;

import java.util.Random;

import Modell.Island;
import modells.GameData.GameData;

public class FirstIsland {

	// Ein Random-Objekt zur Generierung von Zufallszahlen
		private static final Random random = new Random();
	
	/**
	 * Diese Methode initialisiert die erste Insel im Rätsel. Sie wählt zufällige Koordinaten innerhalb des gegebenen
	 * Bereichs aus und erstellt die erste Insel mit diesen Koordinaten. Die Insel wird dann den Listen "listofIslands",
	 * "possibleIslands" und der Karte "islandMap" hinzugefügt.
	 */
	public void initFirstIsland(GameData data) {
	   
		// Wähle zufällige Koordinaten für die erste Insel
	    int islandX = 1 + random.nextInt(data.getWidth());
	    int islandY = 1 + random.nextInt(data.getHeight());
	

	    // Erstelle die erste Insel und füge sie den Listen und der Karte hinzu
	    Island firstIsland = new Island(islandX, islandY, 0, data.getListofIslands(), 0, true,
	            true, true, true,0,0);
	    data.getListofIslands().add(firstIsland);
	    data.getIslandMap().put(0, firstIsland);
	    data.getPossibleIslands().add(firstIsland);
	}
}
