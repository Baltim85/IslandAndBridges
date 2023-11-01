package modells.handleIslands;

import Modell.CreateBridges;
import Modell.Island;
import modells.GameData.GameData;
import modells.Support.Directions;
import modells.Support.Island.UpdateIslandInformation;

public interface ConnectToExistingBridges {
	

	
	static void connectNorthIsland(Island firstIsland, Island secondIsland, CreateBridges bridge, int bridgeCount, Directions direction,
			GameData data, UpdateIslandInformation createNextIsland) {
		


		if(direction == Directions.NORTH || direction == Directions.SOUTH) {
			int firstX = bridge.getFirstIslandX();
			int secondX = bridge.getSecondIslandX();
			int oldY = bridge.getFirstIslandY();
			data.getListOfBridges().add(new CreateBridges(firstIsland.getId(), firstIsland.getX(), firstIsland.getY(),
					secondIsland.getId(), secondIsland.getX(), secondIsland.getY(), bridgeCount));

			data.getListOfBridges().add(new CreateBridges(bridge.getFirstIslandID(), firstX, oldY,
					firstIsland.getId(), firstIsland.getX(), firstIsland.getY(), bridge.getNumberOfBridges()));

			data.getListOfBridges().add(new CreateBridges(firstIsland.getId(), firstIsland.getX(), firstIsland.getY(),
					bridge.getSecondIslandID(), secondX, oldY, bridge.getNumberOfBridges()));

		} 
		if(direction == Directions.EAST || direction == Directions.WEST) {
			int firstY = bridge.getFirstIslandY();
			int secondY = bridge.getSecondIslandY();
			int oldX = bridge.getFirstIslandX();
						
			// Füge die Brücke zur Liste der Brücken hinzu
			data.getListOfBridges().add(new CreateBridges(firstIsland.getId(), firstIsland.getX(), firstIsland.getY(),
					secondIsland.getId(), secondIsland.getX(), secondIsland.getY(), bridgeCount));
			
			data.getListOfBridges().add(new CreateBridges(bridge.getFirstIslandID(), oldX, firstY,
					secondIsland.getId(), secondIsland.getX(), secondIsland.getY(), bridge.getNumberOfBridges()));
			data.getListOfBridges().add(new CreateBridges(secondIsland.getId(), secondIsland.getX(), secondIsland.getY(),
					bridge.getSecondIslandID(), oldX, secondY, bridge.getNumberOfBridges()));
		}
		

		if(direction == Directions.NORTH || direction == Directions.WEST)
			createNextIsland.updateIslands(secondIsland, firstIsland, bridge, bridgeCount, data);
		if(direction == Directions.SOUTH || direction == Directions.EAST)
			createNextIsland.updateIslands(firstIsland, secondIsland, bridge, bridgeCount, data);


	}

}
