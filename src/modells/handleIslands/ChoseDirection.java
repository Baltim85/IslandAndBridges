package modells.handleIslands;


import Modell.Island;
import modells.GameData.GameData;

public class ChoseDirection {
	
	private CreateNorthIslands northIsland;
	private CreateSouthIslands southIsland;
	
	private CreateEastIslands eastIsland;
	private CreateWestIslands westIsland;
	private GameData data;
	
	public ChoseDirection(GameData data) {
		this.data = data;
		northIsland = new CreateNorthIslands(data);
		southIsland = new CreateSouthIslands(data);
		eastIsland = new CreateEastIslands(data);
		westIsland = new CreateWestIslands(data);
	}
	
	public void checkDirection(Island chooseIsland) {
	    if (!chooseIsland.isNorth() && !chooseIsland.isEast() && !chooseIsland.isSouth() && !chooseIsland.isWest()) {
	        handleIsolatedIsland(chooseIsland);
	        return;
	    } else if (chooseIsland.isNorth() && chooseIsland.isEast()) {
	        handleNorthEast(chooseIsland);
	        return;
	    } else if (chooseIsland.isNorth() && chooseIsland.isSouth()) {
	        handleNorthSouth(chooseIsland);
	        return;
	    } else if (chooseIsland.isNorth() && chooseIsland.isWest()) {
	        handleNorthWest(chooseIsland);
	        return;
	    } else if (chooseIsland.isEast() && chooseIsland.isWest()) {
	        handleEastWest(chooseIsland);
	        return;
	    } else if (chooseIsland.isEast() && chooseIsland.isSouth()) {
	        handleEastSouth(chooseIsland);
	        return;
	    }else if (chooseIsland.isSouth() && chooseIsland.isWest()) {
	        handleSouthWest(chooseIsland);
	        return;
	    } else if (chooseIsland.isNorth() && chooseIsland.isEast() && chooseIsland.isSouth()) {
	        handleNorthEastSouth(chooseIsland);
	        return;
	    } else if (chooseIsland.isNorth() && chooseIsland.isEast() && chooseIsland.isWest()) {
	        handleNorthEastWest(chooseIsland);
	        return;
	    } else if (chooseIsland.isNorth() && chooseIsland.isSouth() && chooseIsland.isWest()) {
	        handleNorthSouthWest(chooseIsland);
	        return;
	    } else if (chooseIsland.isEast() && chooseIsland.isSouth() && chooseIsland.isWest()) {
	        handleEastSouthWest(chooseIsland);
	        return;
	    } else if (chooseIsland.isNorth() && chooseIsland.isEast() && chooseIsland.isSouth() && chooseIsland.isWest()) {
	        handleNorthEastSouthWest(chooseIsland);
	        return;
	    } 
	    else if (chooseIsland.isNorth()) {
	        handleNorth(chooseIsland);
	        return;
	    }
	    else if (chooseIsland.isEast()) {
	        handleEast(chooseIsland);
	        return;
	    }
	    else if (chooseIsland.isSouth()) {
	        handleSouth(chooseIsland);
	        return;
	    }
	    else if (chooseIsland.isWest()) {
	        handleWest(chooseIsland);
	        return;
	    }
	}

	// Separated functions for handling specific cases

	private void handleIsolatedIsland(Island chooseIsland) {
	    for (int i = 0; i < data.getPossibleIslands().size(); i++) {
	        if (data.getPossibleIslands().get(i).getId() == chooseIsland.getId()) {
	            data.getPossibleIslands().remove(i);
	            return;
	        }
	    }
	}

	
	private void handleNorth(Island chooseIsland) {
	    northIsland.createNorthIsland(chooseIsland);
	}
	
	private void handleEast(Island chooseIsland) {
	    eastIsland.createEastIsland(chooseIsland);
	}
	
	private void handleSouth(Island chooseIsland) {
	    southIsland.createSouthIsland(chooseIsland);
	}
	private void handleWest(Island chooseIsland) {
	    westIsland.createWestIsland(chooseIsland);
	}
	
	private void handleNorthSouth(Island chooseIsland) {
		int randomDirection = (int) (Math.random() * 2);
	    if (randomDirection == 0) {
	        southIsland.createSouthIsland(chooseIsland);
	    } else {
	        northIsland.createNorthIsland(chooseIsland);
	    }
	    
	}
	
	private void handleNorthEast(Island chooseIsland) {
		int randomDirection = (int) (Math.random() * 2);
	    if (randomDirection == 0) {
	        eastIsland.createEastIsland(chooseIsland);
	    } else {
	        northIsland.createNorthIsland(chooseIsland);
	    }
	}
	
	private void handleNorthWest(Island chooseIsland) {
		int randomDirection = (int) (Math.random() * 2);
	    if (randomDirection == 0) {
	        westIsland.createWestIsland(chooseIsland);
	    } else {
	        northIsland.createNorthIsland(chooseIsland);
	    }
	}
	
	private void handleEastWest(Island chooseIsland) {
		int randomDirection = (int) (Math.random() * 2);
	    if (randomDirection == 0) {
	        westIsland.createWestIsland(chooseIsland);
	    } else {
	        eastIsland.createEastIsland(chooseIsland);
	    }
	}
	
	private void handleEastSouth(Island chooseIsland) {
		int randomDirection = (int) (Math.random() * 2);
	    if (randomDirection == 0) {
	        southIsland.createSouthIsland(chooseIsland);
	    } else {
	        eastIsland.createEastIsland(chooseIsland);
	    }
	}
	
	private void handleSouthWest(Island chooseIsland) {
		int randomDirection = (int) (Math.random() * 2);
	    if (randomDirection == 0) {
	        westIsland.createWestIsland(chooseIsland);
	    } else {
	        southIsland.createSouthIsland(chooseIsland);
	    }
	}
	
	private void handleNorthEastSouth(Island chooseIsland) {
		int randomDirection = (int) (Math.random() * 3);
	    if (randomDirection == 0) {
	        northIsland.createNorthIsland(chooseIsland);
	    } else if(randomDirection == 1){
	        eastIsland.createEastIsland(chooseIsland);
	    } else
	    	southIsland.createSouthIsland(chooseIsland);
	}
	
	private void handleNorthEastWest(Island chooseIsland) {
		int randomDirection = (int) (Math.random() * 3);
	    if (randomDirection == 0) {
	        northIsland.createNorthIsland(chooseIsland);
	    } else if(randomDirection == 1){
	        eastIsland.createEastIsland(chooseIsland);
	    } else
	    	westIsland.createWestIsland(chooseIsland);
	}
	
	private void handleNorthSouthWest(Island chooseIsland) {
		int randomDirection = (int) (Math.random() * 3);
	    if (randomDirection == 0) {
	        northIsland.createNorthIsland(chooseIsland);
	    } else if(randomDirection == 1){
	        southIsland.createSouthIsland(chooseIsland);
	    } else
	    	westIsland.createWestIsland(chooseIsland);
	}
	
	private void handleEastSouthWest(Island chooseIsland) {
		int randomDirection = (int) (Math.random() * 3);
	    if (randomDirection == 0) {
	        eastIsland.createEastIsland(chooseIsland);
	    } else if(randomDirection == 1){
	        southIsland.createSouthIsland(chooseIsland);
	    } else
	    	westIsland.createWestIsland(chooseIsland);
	}
	
	private void handleNorthEastSouthWest(Island chooseIsland) {
		int randomDirection = (int) (Math.random() * 4);
	    if (randomDirection == 0) {
	        northIsland.createNorthIsland(chooseIsland);
	    } else if(randomDirection == 1){
	        eastIsland.createEastIsland(chooseIsland);
	    } else if(randomDirection == 2) {
	    	southIsland.createSouthIsland(chooseIsland);
	    } else {
	    	westIsland.createWestIsland(chooseIsland);
	    }
	}
	

	


}
