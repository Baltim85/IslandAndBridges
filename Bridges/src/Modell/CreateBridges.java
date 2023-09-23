package Modell;

import static Modell.Directions.EAST;
import static Modell.Directions.NORTH;
import static Modell.Directions.SOUTH;
import static Modell.Directions.WEST;

import java.util.ArrayList;
import java.util.Random;

public class CreateBridges {

	
	/**
	 * Eine Liste von verbundenen Inseln.
	 */
	private ArrayList<Island> connectedIslands = new ArrayList<Island>();

	/**
	 * Eine Liste von isolierten Inseln.
	 */
	private ArrayList<Island> isolatedIslands;

	/**
	 * Eine Liste von Inseln, die nicht bewegt werden können.
	 */
	private ArrayList<Island> notMoveableList = new ArrayList<Island>();

	/**
	 * Eine Liste zur Speicherung von Verbindungen zwischen Inseln.
	 */
	private ArrayList<Integer> connectionFromTo = new ArrayList<Integer>();

	/**
	 * Die Breite des Spielfelds.
	 */
	private int width;

	/**
	 * Die Höhe des Spielfelds.
	 */
	private int height;

	/**
	 * Eine Flagge für eine Brücke nach Norden.
	 */
	private boolean northBridge;

	/**
	 * Eine Flagge für eine Brücke nach Osten.
	 */
	private boolean eastBridge;

	/**
	 * Eine Flagge für eine Brücke nach Süden.
	 */
	private boolean southBridge;

	/**
	 * Eine Flagge für eine Brücke nach Westen.
	 */
	private boolean westBridge;

	/**
	 * Die X-Koordinate der aktuellen Insel.
	 */
	private int islandX;

	/**
	 * Die Y-Koordinate der aktuellen Insel.
	 */
	private int islandY;

	/**
	 * Die erste Insel im Spiel.
	 */
	private Island firstIsland = null;

	/**
	 * Die Gesamtanzahl der Inseln im Spiel.
	 */
	private int totalIslands;

	/**
	 * Eine Liste von Inseln im Spiel.
	 */
	private ArrayList<Island> listOfIslands;

	/**
	 * Eine Liste von Brücken im Spiel.
	 */
	private ArrayList<Bridges> listOfBridges = new ArrayList<Bridges>();

	/**
	 * Die maximale Anzahl der Versuche für die Brückenkonstruktion.
	 */
	private final int maxTry = 50;

	/**
	 * Ein Zähler für die Anzahl der Versuche.
	 */
	private int counter = 0;

	/**
	 * Eine Flagge, die angibt, ob das Spiel korrekt gelöst wurde.
	 */
	private boolean isOk = false;

	/**
	 * Die ID der ersten Insel in einer Brückenverbindung.
	 */
	private int firstIslandID;

	/**
	 * Die ID der zweiten Insel in einer Brückenverbindung.
	 */
	private int secondIslandID;

	/**
	 * Die X-Koordinate der ersten Insel in einer Brückenverbindung.
	 */
	private int firstIslandX;

	/**
	 * Die X-Koordinate der zweiten Insel in einer Brückenverbindung.
	 */
	private int secondIslandX;

	/**
	 * Die Y-Koordinate der ersten Insel in einer Brückenverbindung.
	 */
	private int firstIslandY;

	/**
	 * Die Y-Koordinate der zweiten Insel in einer Brückenverbindung.
	 */
	private int secondIslandY;

	/**
	 * Die Anzahl der Brücken zwischen den Inseln in einer Brückenverbindung.
	 */
	private int numberOfBridges;

	//private ArrayList<Integer> connectionFromTo;
	
	
	public CreateBridges(int firstIslandID, int firstIslandX, int firstIslandY, int secondIslandID, 
			int secondIslandX, int secondIslandY, int numberOfBridges, ArrayList<Integer> connectionFromTo) {
		this.firstIslandID = firstIslandID;
		this.firstIslandX = firstIslandX;
		this.firstIslandY = firstIslandY;
		
		this.secondIslandID = secondIslandID;
		this.secondIslandX = secondIslandX;
		this.secondIslandY = secondIslandY;
		this.numberOfBridges = numberOfBridges;
		this.connectionFromTo = connectionFromTo;
		
		
	}
	
	public CreateBridges(ArrayList<Island> listOfIslands, int width, int height) {
		isolatedIslands = new ArrayList<Island>();
		this.listOfIslands =listOfIslands;
		this.setWidth(width);
		this.setHeight(height);
		this.setTotalIslands(listOfIslands.size());
		//System.out.println("Size of possible Islands: " + listOfIslands.size());
		counter = 0;
		setOk(false);
		firstIslandConnection();
	
	}
	
	
	public CreateBridges(int firstIslandID, int firstIslandX, int firstIslandY,
			int secondIslandID, int secondIslandX, int secondIslandY) {
		this.firstIslandID = firstIslandID;
		this.firstIslandX = firstIslandX;
		this.firstIslandY = firstIslandY;
		
		this.secondIslandID = secondIslandID;
		this.secondIslandX = secondIslandX;
		this.secondIslandY = secondIslandY;
	}

	private void resetValues(ArrayList<Island> isolatedIslands) {
		for(Island islands : isolatedIslands) {
			setIslandX(islands.getX());
			setIslandY(islands.getY());
			islands.setNorth(isNorthBridge());
			islands.setEast(isEastBridge());
			islands.setSouth(isSouthBridge());
			islands.setWest(isWestBridge());
			islands.setBridgeCount(0);			
		}
	}
	
	
	private void firstIslandConnection() {
		for(int i =0; i < listOfIslands.size(); i++) {
			isolatedIslands.add(listOfIslands.get(i));
		}
		resetValues(isolatedIslands);
		Island firstIsland;
		if(!isolatedIslands.isEmpty()) {
			firstIsland = isolatedIslands.get(new Random().nextInt(isolatedIslands.size()));
		} else {
			return;
		}
		
		setFirstIsland(firstIsland);
		for(int i = 0; i < isolatedIslands.size(); i++) {
			if(isolatedIslands.get(i).getId() == firstIsland.getId()){
				isolatedIslands.remove(i);
				break;
			}
		}
		
		createNextBridges();
		if(isOk() == false) {
			counter++;
			//System.out.println("Not connectable");
			return;
			//if(counter == 15) {
			//	System.out.println("Not connectable");
			//	return;
			//}
			//firstIslandConnection();
		}
		
	}
	
	private void createNextBridges() {
		while(!isolatedIslands.isEmpty()) {
			if(connectedIslands.isEmpty()) {
				checkDirection(getFirstIsland());
			} else {
				Island nextIsland = connectedIslands.get(new Random().nextInt(connectedIslands.size()));
				
				checkDirection(nextIsland);
			} if(isolatedIslands.size()+notMoveableList.size() == getTotalIslands()) {
				connectedIslands.clear();
				isolatedIslands.clear();
				notMoveableList.clear();
				listOfBridges.clear();
				break;
				//System.out.println("Error");
				//counter++;
				//if(counter == 500) {
					
				//	setOk(false);
				//	break;
				//}
				//System.out.println(counter);
				//firstIslandConnection();
				
				//return;
			}
		}
		if(!notMoveableList.isEmpty()) {
			for(int i =0; i < notMoveableList.size(); i++) {
				connectedIslands.add(notMoveableList.get(i));
			}
		} 
		if(listOfBridges.isEmpty()) {
			setOk(false);
			return;
		} else {
			setOk(true);
			return;
		}
	}
	
	
	

	/**
	 * createWestBridge will create another bridge in the West direction, if there is another island
	 * If there is no other island the West direction for this island will be locked. 
	 * First the function will see if there is already a bridge in the North To South direction. If true
	 * the direction will be locked else, the function looks for an isolated island in the direction, if this is false
	 * the function looks if there is a connected Island in this direction. 
	 * 
	 * If any check is true a bridge will be created in this direction else the direction West will be locked.
	 * 
	 * @param choosenIsland the island that wants a connection in the west direction
	 */
	public boolean createWestBridge(Island choosenIsland) {	
		//setListOfIslands(listofIslands);
		int bridges = 1+ new Random().nextInt(2);
		connectionFromTo = new ArrayList<Integer>();
		for(int i = choosenIsland.getX()-1; i > 0; i-- ) {
			// Look for a bridge from North to South
			if(isNorthSouthBridge(choosenIsland, i)) {
				choosenIsland.setWest(false);
				return true;
			}
			// search for an isolated island in the West direction at the position i
			//if(findIsolatedIslandWE(choosenIsland, i, WEST, bridges)) {
			//	return true;
			//}
			// look for a connected Island in the West direction at the position i
			//if(findConnectedIslandEW(choosenIsland, i, WEST, bridges)) {
			//	return true;
			//}
		}
		choosenIsland.setWest(false);
		return false;
	}

	/**
	 * createEastBridge will create another bridge in the East direction, if there is another island
	 * If there is no other island the East direction for this island will be locked. 
	 * First the function will see if there is already a bridge in the North To South direction. If true
	 * the direction will be locked else, the function looks for an isolated island in the direction, if this is false
	 * the function looks if there is a connected Island in this direction. 
	 * 
	 * If any check is true a bridge will be created in this direction else the direction East will be locked.
	 * 
	 * @param choosenIsland the island that wants a connection in the East direction
	 */
	private void createEastBridge(Island choosenIsland) {
		//setListOfIslands(listofIslands);
		int bridges = 1+ new Random().nextInt(2);
		connectionFromTo = new ArrayList<Integer>();
		for(int i = choosenIsland.getX()+1; i < getWidth(); i++ ) {
			// Look for a bridge from North to South
			if(isNorthSouthBridge(choosenIsland, i)) {
				choosenIsland.setEast(false);
				return;
			}
			// search for an isolated island in the East direction at the position i
			if(findIsolatedIslandWE(choosenIsland, i, EAST, bridges)) {
				return;
			}
			// look for a connected Island in the East direction at the position i
			if(findConnectedIslandEW(choosenIsland, i, EAST, bridges)) {
				return;
			}
		}
		choosenIsland.setEast(false);
		return;
	}
	
	
	/**
	 * isNorthSouthBridge will check for the chosen island that will create a West or East bridge, if there are
	 * already two islands connected and will the next bridge cross this existing bridge. If yes the function will return
	 * true and the direction for the chosen island will be locked.
	 * 
	 * 
	 * @param choosenIsland the island that will create another bridge from West to East or East to West
	 * @param position the current y position 
	 * @return true or false
	 */
	private boolean isNorthSouthBridge(Island choosenIsland, int position) {
		if(listOfBridges.isEmpty() || listOfBridges == null) {
			return false;
		}
		for(int i =0; i < listOfBridges.size(); i++) {
			if(listOfBridges.get(i).getyFirst() < choosenIsland.getY() && listOfBridges.get(i).getySecond() > choosenIsland.getY()) {
				for(int j = 0; j < listOfBridges.get(i).getConnection().size(); j++) {
					if(listOfBridges.get(i).getConnection().get(j) == position) {			
						return true;
					}
				}
			}
		}
		return false;
	}
	
	
	/**
	 * isWestEastBridge will check for the chosen island that will create a North or South bridge, if there are
	 * already two islands connected and will the next bridge cross this existing bridge. If yes the function will return
	 * true and the direction for the chosen island will be locked.
	 * 
	 * 
	 * @param choosenIsland the island that will create another bridge from North to SOuth or South to North
	 * @param position the current x position 
	 * @return true or false
	 */
	private boolean isWestEastBridge(Island choosenIsland, int position) {
		if(listOfBridges.isEmpty() || listOfBridges == null) {
			return false;
		}
		for(int i =0; i < listOfBridges.size(); i++) {
			if(listOfBridges.get(i).getxFirst() < choosenIsland.getX() && listOfBridges.get(i).getxSecond() > choosenIsland.getX()) {
				for(int j = 0; j < listOfBridges.get(i).getConnection().size(); j++) {
					if(listOfBridges.get(i).getConnection().get(j) == position) {				
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * findIsolatedIslandsWE returns an isolated island in the West or East direction depending on the direction
	 * the next island will be created. 
	 * If there is no island in this direction false will be return. Else a new bridge to this island 
	 * will be created and is added to the list of connectedIsland 
	 * 
	 * @param westIsland the choosen island. This could be West or East
	 * @param position the next position on the x axis to look for another island
	 * @param direction the direction where the next bridge should be created
	 * @param bridges the number of bridges
	 * @return true if an isolated island was found and connected else false
	 */
	private boolean findIsolatedIslandWE(Island westIsland, int position, Directions direction, int bridges) {
		for(int j =0; j < isolatedIslands.size(); j++) {
			if(isolatedIslands.get(j).getY() == westIsland.getY() && isolatedIslands.get(j).getX() == position) {
				if(connectedIslands.isEmpty()) {
					connectedIslands.add(westIsland);
				}
				if(direction == EAST) {
					//System.out.println("East " + isolatedIslands.get(j).getX() + " y " + isolatedIslands.get(j).getY() + 
					//		" second " + westIsland.getX() + " y " + westIsland.getY() );
					westIsland.setEast(false);
					isolatedIslands.get(j).setWest(false);
					addWEBridgeToList(westIsland, isolatedIslands.get(j), bridges);					
					addIslandUpdateBridges(j, westIsland, bridges);
					return true;
				}
				if(direction == WEST) {
					//System.out.println("West " + westIsland.getX() + " y " + westIsland.getY() + 
					//		" second ost" + isolatedIslands.get(j).getX() + " y " + isolatedIslands.get(j).getY() );
					westIsland.setWest(false);
					isolatedIslands.get(j).setEast(false);
					for(int k = isolatedIslands.get(j).getX()+1; k < westIsland.getX(); k++) {
						connectionFromTo.add(k);
					}
					addWEBridgeToList(isolatedIslands.get(j), westIsland, bridges);
					addIslandUpdateBridges(j, westIsland, bridges);
					return true;
					
				}
			}
		}		
		return false;
	}
	
	
	private boolean findConnectedIslandEW(Island westIsland, int position, Directions direction, int bridges) {
		for(int j = 0; j < connectedIslands.size(); j++) {
			if(westIsland.getId() != connectedIslands.get(j).getId()) {
				if(connectedIslands.get(j).getY() == westIsland.getY() && connectedIslands.get(j).getX() == position) {
					if(direction == EAST) {
						//System.out.println("East Verbundender Nachbar Ost: " + connectedIslands.get(j).getX() + " y " + connectedIslands.get(j).getY() + " second West " + westIsland.getX() + " y " + westIsland.getY() );
						westIsland.setEast(false);						
						connectedIslands.get(j).setWest(false);
						westIsland.setBridgeCount(westIsland.getBridgeCount() + bridges);
						connectedIslands.get(j).setBridgeCount(connectedIslands.get(j).getBridgeCount() + bridges);
						addWEBridgeToList(westIsland, connectedIslands.get(j), bridges);
						return true;
					} 
					if(direction == WEST) {
						//System.out.println("West Verbundender Nachbar: " + westIsland.getX() + " y " + westIsland.getY() + " second ost" + connectedIslands.get(j).getX() + " y " + connectedIslands.get(j).getY() );
						westIsland.setWest(false);
						connectedIslands.get(j).setEast(false);
						westIsland.setBridgeCount(westIsland.getBridgeCount() + bridges);
						connectedIslands.get(j).setBridgeCount(connectedIslands.get(j).getBridgeCount() + bridges);
						addWEBridgeToList(connectedIslands.get(j), westIsland, bridges);
						return true;
					}
				}
			}
		}
		return false;
	}
	
	
	/**
	 * createSouthBridge will create another bridge in the South direction, if there is another island
	 * If there is no other island the South direction for this island will be locked. 
	 * First the function will see if there is already a bridge in the West To East direction. If true
	 * the direction will be locked else, the function looks for an isolated island in the direction, if this is false
	 * the function looks if there is a connected Island in this direction. 
	 * 
	 * If any check is true a bridge will be created in this direction else the direction South will be locked.
	 * 
	 * @param choosenIsland the island that wants a connection in the south direction
	 */
	private void createSouthBridge(Island choosenIsland) {
		//setListOfIslands(listofIslands);
		int bridges = 1+ new Random().nextInt(2);
		for(int i = choosenIsland.getY()+1; i < getHeight(); i++ ) {
			// Look for a bridge from West to East
			if(isWestEastBridge(choosenIsland, i)) {
				choosenIsland.setSouth(false);
				return;
			}
			// search for an isolated island in the South direction at the position i
			if(findIsolatedIslandNS(choosenIsland, i, SOUTH, bridges)) {
				return;
			} 
			// search for a connected island in the South direction at the position i
			if(findConnectedIslandNS(choosenIsland, i, SOUTH, bridges)) {
				return;
			}
		}
		choosenIsland.setSouth(false);
		return;	
	}
	
	/**
	 * createNorthBridge will create another bridge in the North direction, if there is another island
	 * If there is no other island the North direction for this island will be locked. 
	 * First the function will see if there is already a bridge in the West To East direction. If true
	 * the direction will be locked else, the function looks for an isolated island in the direction, if this is false
	 * the function looks if there is a connected Island in this direction. 
	 * 
	 * If any check is true a bridge will be created in this direction else the direction North will be locked.
	 * 
	 * @param choosenIsland the island that wants a connection in the north direction
	 */
	private void createNorthBridge(Island choosenIsland) {	
		//setListOfIslands(listofIslands);
		int bridges = 1+ new Random().nextInt(2);
		for(int i = choosenIsland.getY()-1; i > 0; i-- ) {
			// Look for a bridge from West to East
			if(isWestEastBridge(choosenIsland, i)) {
				choosenIsland.setNorth(false);
				return;
			}
			// search for an isolated island in the North direction at the position i
			if(findIsolatedIslandNS(choosenIsland, i, NORTH, bridges)) {
				return;
			} 
			// search for a connected island in the North direction at the position i
			if(findConnectedIslandNS(choosenIsland, i, NORTH, bridges)){
				return;
			}

		}
		choosenIsland.setNorth(false);
		return;
	}
	
	
	/**
	 * findIsolatedIslandsNS returns an isolated island in the North or South direction depending on the direction
	 * the next island will be created. 
	 * If there is no island in this direction false will be return. Else a new bridge to this island 
	 * will be created and is added to the list of connectedIsland 
	 * 
	 * @param northIsland the chosen island. This could be North or South
	 * @param position the next position on the x axis to look for another island
	 * @param direction the direction where the next bridge should be created
	 * @param bridges the number of bridges
	 * @return true if an isolated island was found and connected else false
	 */
	private boolean findConnectedIslandNS(Island northIsland, int position, Directions direction, int bridges) {
		for(int j = 0; j < connectedIslands.size(); j++) {
			if(northIsland.getId() != connectedIslands.get(j).getId()) {
				if(connectedIslands.get(j).getX() == northIsland.getX() && connectedIslands.get(j).getY() == position) {
					if(direction == SOUTH) {
						//System.out.println("South Verbundender Nachbar: " + northIsland.getX() + " y " + northIsland.getY() + " second süd" + connectedIslands.get(j).getX() + " y " + connectedIslands.get(j).getY() );
						northIsland.setSouth(false);
						connectedIslands.get(j).setNorth(false);
						northIsland.setBridgeCount(northIsland.getBridgeCount() + bridges);
						connectedIslands.get(j).setBridgeCount(connectedIslands.get(j).getBridgeCount() + bridges);
						addNSBridgeToList(northIsland, connectedIslands.get(j), bridges);
						return true;
					} if(direction == NORTH) {
						//System.out.println("North Verbundender Nachbar Nord: " + connectedIslands.get(j).getX() + " y " + connectedIslands.get(j).getY()  + " second Süd" + northIsland.getX() + " y " + northIsland.getY());
						northIsland.setNorth(false);
						connectedIslands.get(j).setSouth(false);
						northIsland.setBridgeCount(northIsland.getBridgeCount() + bridges);
						connectedIslands.get(j).setBridgeCount(connectedIslands.get(j).getBridgeCount() + bridges);
						addNSBridgeToList(connectedIslands.get(j), northIsland, bridges);
						return true;
					}
				}
			}
		}
		return false;
	}
	

	private boolean findIsolatedIslandNS(Island northIsland, int position, Directions direction, int bridges) {
		for(int j =0; j < isolatedIslands.size(); j++) {
			if(isolatedIslands.get(j).getX() == northIsland.getX() && isolatedIslands.get(j).getY() == position) {
				if(connectedIslands.isEmpty()) {
					connectedIslands.add(northIsland);
				}
				if(direction == SOUTH) {
					//System.out.println("South " + northIsland.getX() + " y " + northIsland.getY() + " second Süd" + isolatedIslands.get(j).getX() + " y " + isolatedIslands.get(j).getY() );
					northIsland.setSouth(false);
					isolatedIslands.get(j).setNorth(false);
					addNSBridgeToList(northIsland, isolatedIslands.get(j), bridges);
					addIslandUpdateBridges(j, northIsland, bridges);
					return true;
				}
				if(direction == NORTH) {
					//System.out.println("North " + isolatedIslands.get(j).getX() + " y " + isolatedIslands.get(j).getY() + " second " + northIsland.getX() + " y " + northIsland.getY() );
					northIsland.setNorth(false);
					isolatedIslands.get(j).setSouth(false);
					addNSBridgeToList(isolatedIslands.get(j), northIsland, bridges);
					addIslandUpdateBridges(j, northIsland, bridges);
					return true;
				}
			}
		}
		
		return false;
	}
	
	/**
	 * addNSBridgeToList will add a new Bridge into the list from North to South
	 * 
	 * @param northIsland the upper island and always the first island
	 * @param southIsland the lower island and always the second 
	 * @param bridges the number of bridges that will be created in this direction
	 */
	private void addNSBridgeToList(Island northIsland, Island southIsland, int bridges) {
		connectionFromTo = new ArrayList<Integer>();
		for(int k = northIsland.getY()+1; k < southIsland.getY(); k++) {
			connectionFromTo.add(k);
		}
		listOfBridges.add(new Bridges(northIsland.getId(), northIsland.getX(), northIsland.getY(), 
				southIsland.getId(), southIsland.getX(), southIsland.getY(),bridges, connectionFromTo));	
		return;
	}
	
	/**
	 * addWEBridgeToList will add a new Bridge into the list from West to East
	 * 
	 * @param westIsland the left island in the puzzle
	 * @param eastIsland the right island in the puzzle to set a connection
	 * @param bridges the number of bridges that will be created in this direction
	 */
	private void addWEBridgeToList(Island westIsland, Island eastIsland, int bridges) {
		connectionFromTo = new ArrayList<Integer>();
		for(int k = westIsland.getX()+1; k <eastIsland.getX(); k++) {
			connectionFromTo.add(k);
		}
		listOfBridges.add(new Bridges(westIsland.getId(), westIsland.getX(), westIsland.getY(),
				eastIsland.getId(), eastIsland.getX(), eastIsland.getY(),bridges, connectionFromTo));
		return;
	}
	
	
	/**
	 * addIslandUpdateBridges will add the new isolated island to the list of connected island. This 
	 * element will be removed from the list after adding and setting up the bridge count for it. 
	 * 
	 * 
	 * @param index The index of the found isolated island that will be connected
	 * @param choosenIsland the island that creates the connection to the isolated island
	 * @param bridges the number ob bridges between both islands
	 */
	private void addIslandUpdateBridges(int index, Island choosenIsland, int bridges) {
		connectedIslands.add(isolatedIslands.get(index));
		isolatedIslands.get(index).setBridgeCount(isolatedIslands.get(index).getBridgeCount() + bridges);
		choosenIsland.setBridgeCount(choosenIsland.getBridgeCount() + bridges);
		isolatedIslands.remove(index);	
	}
	
	
	private void checkDirection(Island chooseIsland) {
		int direction = 0;
		// true, false, false, false
		if(chooseIsland.isNorth() && !chooseIsland.isEast() && !chooseIsland.isSouth() && !chooseIsland.isWest()) {
				createNorthBridge(chooseIsland);				
				return;
		}
		// false, true, false, false
		if(!chooseIsland.isNorth() && chooseIsland.isEast() && !chooseIsland.isSouth() && !chooseIsland.isWest()) {
			//System.out.println("East");	
			createEastBridge(chooseIsland);
			return;
		}
		// false, false, true, false
		if(!chooseIsland.isNorth() && !chooseIsland.isEast() && chooseIsland.isSouth() && !chooseIsland.isWest()) {
			//System.out.println("South");
			createSouthBridge(chooseIsland);
			return;
		}
		// false, false, false, true
		if(!chooseIsland.isNorth() && !chooseIsland.isEast() && !chooseIsland.isSouth() && chooseIsland.isWest()) {
			//System.out.println("west");
			createWestBridge(chooseIsland);
			return;
		}
		
		// true, true, false, false
		if(chooseIsland.isNorth() && chooseIsland.isEast() && !chooseIsland.isSouth() && !chooseIsland.isWest()) {
			//System.out.println("North, east");
			direction = 1 + (new Random().nextInt(2));
			if(direction == 1) {
				createEastBridge(chooseIsland);
				return;
			} else {
				createNorthBridge(chooseIsland);
				return;
			}
		}
			
		// true, false, true, false
		if(chooseIsland.isNorth() && !chooseIsland.isEast() && chooseIsland.isSouth() && !chooseIsland.isWest()) {
			//System.out.println("North, south");
			direction = 1 + (new Random().nextInt(2));
			if(direction == 1) {
				createNorthBridge(chooseIsland);
				return;
			} else {
				createSouthBridge(chooseIsland);
				return;
			}
		}
		
		// true, false, false, true
		if(chooseIsland.isNorth() && !chooseIsland.isEast() && !chooseIsland.isSouth() && chooseIsland.isWest()) {
			//System.out.println("north, west");
			direction = 1 + (new Random().nextInt(2));
			if(direction == 1) {
				createNorthBridge(chooseIsland);
				return;
			} else {
				createWestBridge(chooseIsland);
				return;
			}
		}
		

		
		// false, true, false, true
		if(!chooseIsland.isNorth() && chooseIsland.isEast() && !chooseIsland.isSouth() && chooseIsland.isWest()) {
			//System.out.println("East, West");
			direction = 1+ (new Random().nextInt(2));
			if (direction == 1){
				createEastBridge(chooseIsland);
				return;
			} else {
				createWestBridge(chooseIsland);
				return;
			}
		}
		
		// false, false, true, true
		if(!chooseIsland.isNorth() && !chooseIsland.isEast() && chooseIsland.isSouth() && chooseIsland.isWest()) {
			//System.out.println("South, West");
			direction = 1+ (new Random().nextInt(2));
			if(direction == 1) {
				createSouthBridge(chooseIsland);
				return;
			} else {
				createWestBridge(chooseIsland);
				return;
			}
		}
		

		
		// true, false, true, true
		if(chooseIsland.isNorth() && !chooseIsland.isEast() && chooseIsland.isSouth() && chooseIsland.isWest()) {
			//System.out.println("Norht, South, West");
			direction = 1 + (new Random().nextInt(3));
			if(direction == 1) {
				createNorthBridge(chooseIsland);
				return;
			} else if(direction == 2) {
				createSouthBridge(chooseIsland);
				return;
			} else  {
				createWestBridge(chooseIsland);
				return;
			}

		}
		
		// true, true, false, true
		if(chooseIsland.isNorth() && chooseIsland.isEast() && !chooseIsland.isSouth() && chooseIsland.isWest()) {
			//System.out.println("Nort, East, West");
			direction = 1+ (new Random().nextInt(3));
			if(direction == 1) {
				createNorthBridge(chooseIsland);
				return;
			} else if(direction == 2) {
				createEastBridge(chooseIsland);
				return;
			} else  {
				createWestBridge(chooseIsland);
				return;
			}
			
		}
		
		// false, true, true, true
		if(!chooseIsland.isNorth() && chooseIsland.isEast() && chooseIsland.isSouth() && chooseIsland.isWest()) {
			//System.out.println("East, South, West");
			direction = 1+ (new Random().nextInt(3));
			if(direction == 1) {
				createEastBridge(chooseIsland);
				return;
			} else if(direction == 2) {
				createSouthBridge(chooseIsland);
				return;
			} else  {
				createWestBridge(chooseIsland);
				return;
			}
			
		}
		
		// true, true, true, true
		if(chooseIsland.isNorth() && chooseIsland.isEast() && chooseIsland.isSouth() && chooseIsland.isWest()) {
			//System.out.println("North, East, South, West");
			direction = 1+ (new Random().nextInt(4));
			if(direction == 1) {
				createNorthBridge(chooseIsland);
				return;
			} else if(direction == 2) {
				createEastBridge(chooseIsland);
				return;
			} else if(direction == 3) {
				createSouthBridge(chooseIsland);
				return;
			} else  {
				createWestBridge(chooseIsland);
				return;
			}
			
		}
		
		// false, false, false, false
		if(!chooseIsland.isNorth() && !chooseIsland.isEast() && !chooseIsland.isSouth() && !chooseIsland.isWest()) {
			for(int i =0; i < connectedIslands.size(); i++) {
				if(connectedIslands.get(i).getId() == chooseIsland.getId()) {
					notMoveableList.add(connectedIslands.get(i));
					connectedIslands.remove(i);
					
					return;
				}
			}
			return;
		}
		
		// false, true, true, false
		if(!chooseIsland.isNorth() && chooseIsland.isEast() && chooseIsland.isSouth() && !chooseIsland.isWest()) {
			//System.out.println("East, South");
			direction = 1+ (new Random().nextInt(2));
			if(direction == 1) {
				createEastBridge(chooseIsland);
				return;
			} else  {
				createSouthBridge(chooseIsland);
				return;
			}

		}
		// true, true, true, false
		if(chooseIsland.isNorth() && chooseIsland.isEast() && chooseIsland.isSouth() && !chooseIsland.isWest()) {
			//System.out.println("North, East, South");	
			direction = 1+ (new Random().nextInt(3));
			if(direction == 1) {
				createNorthBridge(chooseIsland);
				return;
			} else if(direction == 2) {
				createEastBridge(chooseIsland);
				return;
			} else  {
				createSouthBridge(chooseIsland);
				return;
			}
			
		}
		
	}

	


	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * @return the northBridge
	 */
	public boolean isNorthBridge() {
		if(getIslandY()-1 == 1 || getIslandY() == 1) {
			northBridge = false;
		} else {
			northBridge = true;
		}
		return northBridge;
	}

	/**
	 * @param northBridge the northBridge to set
	 */
	public void setNorthBridge(boolean northBridge) {
		this.northBridge = northBridge;
	}

	/**
	 * @return the eastBridge
	 */
	public boolean isEastBridge() {
		if(getIslandX()+1 == getWidth() || getIslandX() == getWidth()) {
			eastBridge = false;
		} else {
			eastBridge = true;
		}
		return eastBridge;
	}

	/**
	 * @param eastBridge the eastBridge to set
	 */
	public void setEastBridge(boolean eastBridge) {
		this.eastBridge = eastBridge;
	}

	/**
	 * @return the southBridge
	 */
	public boolean isSouthBridge() {
		if(getIslandY()+1 == getHeight() || getIslandY() == getHeight()) {
			southBridge = false;
		} else {
			southBridge = true;
		}
		return southBridge;
	}

	/**
	 * @param southBridge the southBridge to set
	 */
	public void setSouthBridge(boolean southBridge) {
		this.southBridge = southBridge;
	}

	/**
	 * @return the westBridge
	 */
	public boolean isWestBridge() {
		if(getIslandX()-1 == 1 || getIslandX() == 1) {
			westBridge = false;
		} else {
			westBridge = true;
		}
		return westBridge;
	}

	/**
	 * @param westBridge the westBridge to set
	 */
	public void setWestBridge(boolean westBridge) {
		this.westBridge = westBridge;
	}

	/**
	 * @return the islandX
	 */
	public int getIslandX() {
		return islandX;
	}

	/**
	 * @param islandX the islandX to set
	 */
	public void setIslandX(int islandX) {
		this.islandX = islandX;
	}

	/**
	 * @return the islandY
	 */
	public int getIslandY() {
		return islandY;
	}

	/**
	 * @param islandY the islandY to set
	 */
	public void setIslandY(int islandY) {
		this.islandY = islandY;
	}

	/**
	 * @return the firstIsland
	 */
	public Island getFirstIsland() {
		return firstIsland;
	}

	/**
	 * @param firstIsland the firstIsland to set
	 */
	public void setFirstIsland(Island firstIsland) {
		this.firstIsland = firstIsland;
	}

	/**
	 * @return the connectedIslands
	 */
	public ArrayList<Island> getConnectedIslands() {
		return connectedIslands;
	}

	/**
	 * @param connectedIslands the connectedIslands to set
	 */
	public void setConnectedIslands(ArrayList<Island> connectedIslands) {
		this.connectedIslands = connectedIslands;
	}

	/**
	 * @return the isolatedIslands
	 */
	public ArrayList<Island> getIsolatedIslands() {
		return isolatedIslands;
	}

	/**
	 * @param isolatedIslands the isolatedIslands to set
	 */
	public void setIsolatedIslands(ArrayList<Island> isolatedIslands) {
		this.isolatedIslands = isolatedIslands;
	}

	/**
	 * @return the notMoveableList
	 */
	public ArrayList<Island> getNotMoveableList() {
		return notMoveableList;
	}

	/**
	 * @param notMoveableList the notMoveableList to set
	 */
	public void setNotMoveableList(ArrayList<Island> notMoveableList) {
		this.notMoveableList = notMoveableList;
	}

	/**
	 * @return the totalIslands
	 */
	public int getTotalIslands() {
		return totalIslands;
	}

	/**
	 * @param totalIslands the totalIslands to set
	 */
	public void setTotalIslands(int totalIslands) {
		this.totalIslands = totalIslands;
	}

	/**
	 * @return the listOfIslands
	 */
	public ArrayList<Island> getListOfIslands() {
		return listOfIslands;
	}

	/**
	 * @param listOfIslands the listOfIslands to set
	 */
	public void setListOfIslands(ArrayList<Island> listOfIslands) {
		this.listOfIslands = listOfIslands;
	}


	/**
	 * @return the isOk
	 */
	public boolean isOk() {
		return isOk;
	}


	/**
	 * @param isOk the isOk to set
	 */
	public void setOk(boolean isOk) {
		this.isOk = isOk;
	}

	/**
	 * @return the connectionFromTo
	 */
	public ArrayList<Integer> getConnectionFromTo() {
		return connectionFromTo;
	}

	/**
	 * @param connectionFromTo the connectionFromTo to set
	 */
	public void setConnectionFromTo(ArrayList<Integer> connectionFromTo) {
		this.connectionFromTo = connectionFromTo;
	}

	/**
	 * @return the listOfBridges
	 */
	public ArrayList<Bridges> getListOfBridges() {
		return listOfBridges;
	}

	/**
	 * @param listOfBridges the listOfBridges to set
	 */
	public void setListOfBridges(ArrayList<Bridges> listOfBridges) {
		this.listOfBridges = listOfBridges;
	}

	/**
	 * @return the counter
	 */
	public int getCounter() {
		return counter;
	}

	/**
	 * @param counter the counter to set
	 */
	public void setCounter(int counter) {
		this.counter = counter;
	}

	/**
	 * @return the firstIslandID
	 */
	public int getFirstIslandID() {
		return firstIslandID;
	}

	/**
	 * @param firstIslandID the firstIslandID to set
	 */
	public void setFirstIslandID(int firstIslandID) {
		this.firstIslandID = firstIslandID;
	}

	/**
	 * @return the secondIslandID
	 */
	public int getSecondIslandID() {
		return secondIslandID;
	}

	/**
	 * @param secondIslandID the secondIslandID to set
	 */
	public void setSecondIslandID(int secondIslandID) {
		this.secondIslandID = secondIslandID;
	}

	/**
	 * @return the firstIslandX
	 */
	public int getFirstIslandX() {
		return firstIslandX;
	}

	/**
	 * @param firstIslandX the firstIslandX to set
	 */
	public void setFirstIslandX(int firstIslandX) {
		this.firstIslandX = firstIslandX;
	}

	/**
	 * @return the secondIslandX
	 */
	public int getSecondIslandX() {
		return secondIslandX;
	}

	/**
	 * @param secondIslandX the secondIslandX to set
	 */
	public void setSecondIslandX(int secondIslandX) {
		this.secondIslandX = secondIslandX;
	}

	/**
	 * @return the firstIslandY
	 */
	public int getFirstIslandY() {
		return firstIslandY;
	}

	/**
	 * @param firstIslandY the firstIslandY to set
	 */
	public void setFirstIslandY(int firstIslandY) {
		this.firstIslandY = firstIslandY;
	}

	/**
	 * @return the secondIslandY
	 */
	public int getSecondIslandY() {
		return secondIslandY;
	}

	/**
	 * @param secondIslandY the secondIslandY to set
	 */
	public void setSecondIslandY(int secondIslandY) {
		this.secondIslandY = secondIslandY;
	}

	/**
	 * @return the numberOfBridges
	 */
	public int getNumberOfBridges() {
		return numberOfBridges;
	}

	/**
	 * @param numberOfBridges the numberOfBridges to set
	 */
	public void setNumberOfBridges(int numberOfBridges) {
		this.numberOfBridges = numberOfBridges;
	}

	/**
	 * @return the maxTry
	 */
	public int getMaxTry() {
		return maxTry;
	}
}
