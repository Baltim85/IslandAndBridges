package Modell;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;


public class Island {

	private int x, y, id, bridgeCount;
	private int index = 1;
	boolean north, east, south, west;

	private int islandX, islandY;
	private boolean isOk = false;
	private int totalIslands;
	ArrayList<Island> listofIslands;
	private HashMap<Integer, Island> islandMap = new HashMap<Integer, Island>();
	ArrayList<Island> possibleIslands = new ArrayList<Island>();
	ArrayList<CreateBridges> listOfBridges = new ArrayList<CreateBridges>();
	ArrayList<Integer> fromTo = new ArrayList<Integer>();
	private static final Random random = new Random();
	private int centerX, centerY;


	/**
	 * Dieser Konstruktor erstellt ein Objekt der Klasse Island mit den angegebenen Koordinaten, einer ID,
	 * einer Liste von Inseln, einer Brückenzahl und den Richtungen, in denen Brücken zu benachbarten Inseln führen.
	 *
	 * @param x             Die x-Koordinate der Insel.
	 * @param y             Die y-Koordinate der Insel.
	 * @param id            Die eindeutige ID der Insel.
	 * @param listofIslands Eine Liste von Inseln, zu der diese Insel gehört.
	 * @param bridgeCount   Die Anzahl der Brücken, die mit dieser Insel verbunden sind.
	 * @param north         True, wenn eine Brücke in nördlicher Richtung führt, andernfalls False.
	 * @param east          True, wenn eine Brücke in östlicher Richtung führt, andernfalls False.
	 * @param south         True, wenn eine Brücke in südlicher Richtung führt, andernfalls False.
	 * @param west          True, wenn eine Brücke in westlicher Richtung führt, andernfalls False.
	 */
	public Island(int x, int y, int id, ArrayList<Island> listofIslands, int bridgeCount, boolean north, boolean east,
	              boolean south, boolean west, int centerX, int centerY) {
	    this.x = x;
	    this.y = y;
	    this.id = id;
	    this.bridgeCount = bridgeCount;
	    this.north = north;
	    this.east = east;
	    this.south = south;
	    this.west = west;
	    this.listofIslands = listofIslands;
	    this.centerX = centerX;
	    this.centerY = centerY;

	    
	    /*if (id == -1) {
	        this.setMaxX(x);
	        this.setMaxY(y);
	        System.out.println("neu: " + x + " y: " + y);
	        setFlagNewGame(false);
	        
	    }*/
	}
	
	



	/**
	 * Dieser Konstruktor erstellt ein Objekt der Klasse Island mit den angegebenen Koordinaten, einer ID und einer
	 * Anzahl von Brücken.
	 *
	 * @param x           Die x-Koordinate der Insel.
	 * @param y           Die y-Koordinate der Insel.
	 * @param id          Die eindeutige ID der Insel.
	 * @param bridgeCount Die Anzahl der Brücken, die mit dieser Insel verbunden sind.
	 */
	public Island(int x, int y, int id, int bridgeCount) {
	    this.x = x;
	    this.y = y;
	    this.id = id;
	    this.bridgeCount = bridgeCount;
	}

	/**
	 * Diese Methode erstellt die erste Insel im Rätsel. Sie setzt den Index auf 1, initialisiert verschiedene Listen und
	 * Karten, und ruft dann die Methode "initFirstIsland" auf, um die erste Insel zu erstellen. Anschließend werden
	 * weitere Inseln erstellt, bis die Anzahl der erstellten Inseln der Gesamtanzahl entspricht.
	 *
	 * @param totalIslands Die Gesamtanzahl der Inseln, die erstellt werden sollen.
	 */
	public void createFirstIsland(int totalIslands) {
	    // Setze den Index auf 1 und initialisiere Listen und Karten
	    setIndex(1);
	    this.totalIslands = totalIslands;
	    listofIslands.clear();
	    possibleIslands.clear();
	    listOfBridges.clear();
	    islandMap.clear();

	    // Initialisiere die erste Insel
	    initFirstIsland();

	    // Erstelle weitere Inseln, bis die gewünschte Gesamtanzahl erreicht ist
	    createNextIsland();

	    // Überprüfe, ob die Anzahl der erstellten Inseln der Gesamtanzahl entspricht
	    if (listofIslands.size() == totalIslands) {
	        setOk(true);
	    } else {
	        setOk(false);
	    }
	}
	
	/**
	 * Diese Methode initialisiert die erste Insel im Rätsel. Sie wählt zufällige Koordinaten innerhalb des gegebenen
	 * Bereichs aus und erstellt die erste Insel mit diesen Koordinaten. Die Insel wird dann den Listen "listofIslands",
	 * "possibleIslands" und der Karte "islandMap" hinzugefügt.
	 */
	private void initFirstIsland() {
	    // Wähle zufällige Koordinaten für die erste Insel
	    islandX = 1 + random.nextInt(x);
	    islandY = 1 + random.nextInt(y);
	    setIslandX(islandX);
	    setIslandY(islandY);

	    // Erstelle die erste Insel und füge sie den Listen und der Karte hinzu
	    Island firstIsland = new Island(islandX, islandY, 0, listofIslands, 0, isBridgeNorthPossible(),
	            isBridgeEastPossible(), isBridgeSouthPossible(), isBridgeWestPossible(),0,0);
	    listofIslands.add(firstIsland);
	    islandMap.put(0, firstIsland);
	    possibleIslands.add(firstIsland);
	}

	/**
	 * Diese Methode erstellt die nächsten Inseln im Rätsel. Sie verwendet eine Schleife, um Inseln zu erstellen, bis die
	 * gewünschte Anzahl erreicht ist. Sie wählt eine Insel aus der Liste "possibleIslands" aus, überprüft mögliche
	 * Richtungen für Brücken und erstellt die nächste Insel entsprechend. Dies wird wiederholt, bis die Anzahl der
	 * erstellten Inseln der Gesamtanzahl entspricht oder keine weiteren Inseln erstellt werden können.
	 */
	private void createNextIsland() {
	    while (getIndex() < totalIslands) {
	        if (possibleIslands.isEmpty() || listofIslands.size() > totalIslands) {
	            setOk(false);
	            return;
	        }
	        // Wähle eine Insel aus der Liste "possibleIslands" aus
	        Island chooseIsland = randomIsland(possibleIslands, islandMap);
	        // Überprüfe mögliche Richtungen für Brücken und erstelle die nächste Insel
	        checkDirection(chooseIsland);
	    }
	    setOk(true);
	}
	
	
	


	private void createNorthIsland(Island chosenIsland) {
		int bridgeCount = 1+(random.nextInt(2));

		int newPosY = possibleValuesNW(chosenIsland, y, chosenIsland.getY());

		if(newPosY <= 1 || !checkForAnotherIsland(chosenIsland, newPosY, Directions.NORTH) ||
				!checkDirectedNeighboursWE(chosenIsland, newPosY, Directions.NORTH)) {
			chosenIsland.setNorth(false);
		} else {
			int chosenX = chosenIsland.getX();
			int chosenY = chosenIsland.getY();
			if(!listOfBridges.isEmpty()) {
				for(int i = chosenIsland.getY(); i > newPosY-1; i-- ) {
					
					for (CreateBridges bridge : listOfBridges) {
						int firstX = bridge.getFirstIslandX();
						int secondX = bridge.getSecondIslandX();
						int y = bridge.getFirstIslandY();
						if(y == chosenIsland.getY()-1 &&(firstX < chosenX && secondX > chosenX)) {
							chosenIsland.setNorth(false);
							return;
						}
						if(y < chosenY-1 && i == y && (firstX < chosenX-1 && secondX > chosenX+1)) {
							for (Island island : listofIslands) {
								int islandX = island.getX();
								int islandY = island.getY();
								if(islandX == chosenX && (islandY == i-1 || islandY == i || islandX == chosenX+1 || islandX == chosenX-1 || islandY == i+1)) {
									chosenIsland.setNorth(false);
									return;
								}
								
							}
						
							Island nextIsland = new Island(chosenIsland.getX(), i, getIndex(), listofIslands, bridgeCount, isBridgeNorthPossible(),false, 
									false, false,0,0);
							
							islandMap.put(getIndex(), nextIsland);
							setIndex(getIndex()+1);
							chosenIsland.setNorth(false);
							
							nextIsland.setBridgeCount(nextIsland.getBridgeCount() + 2*bridge.getNumberOfBridges());
							listOfBridges.add(new CreateBridges(nextIsland.getId(), nextIsland.getX(), nextIsland.getY(), 
									chosenIsland.getId(), chosenIsland.getX(), chosenIsland.getY(), bridgeCount, null));
							listOfBridges.add(new CreateBridges(bridge.getFirstIslandID(), firstX, y, 
									nextIsland.getId(), nextIsland.getX(), nextIsland.getY(), bridge.getNumberOfBridges(), null));
							listOfBridges.add(new CreateBridges(nextIsland.getId(), nextIsland.getX(), nextIsland.getY(), 
									bridge.getSecondIslandID(), secondX, y, bridge.getNumberOfBridges(), null));
							listOfBridges.remove(bridge);
							
							listofIslands.add(nextIsland);
							possibleIslands.add(nextIsland);
							System.out.println(chosenIsland.getId() + "id1: "+ bridge.getFirstIslandID() + "id2: " + bridge.getSecondIslandID());
							System.out.println("Norht New ID: " + nextIsland.getId());
							return;
							
						}
	
					}
					
				}
					
				
			}
			
			Island nextIsland = createNextIsland(chosenIsland.getX(), newPosY, bridgeCount);
			/*Island nextIsland = new Island(chosenIsland.getX(), newPosY, getIndex(), listofIslands, bridgeCount, isBridgeNorthPossible(), isBridgeEastPossible(), 
					isBridgeSouthPossible(), isBridgeWestPossible());
			
			islandMap.put(getIndex(), nextIsland);
			setIndex(getIndex()+1);*/
		
			listOfBridges.add(new CreateBridges(nextIsland.getId(), nextIsland.getX(), nextIsland.getY(), 
					chosenIsland.getId(), chosenIsland.getX(), chosenIsland.getY(), bridgeCount, null));
			System.out.println("North FirstID: " + nextIsland.getId() + "Second ID: "+ chosenIsland.getId() );
			System.out.println(newPosY);
			chosenIsland.setBridgeCount(chosenIsland.getBridgeCount()+ bridgeCount);
			chosenIsland.setNorth(false);
			nextIsland.setSouth(false);
			
			listofIslands.add(nextIsland);
			possibleIslands.add(nextIsland);

		}	
		return;			
	}


	

	
	private void createSouthIsland(Island chosenIsland) {
		int bridgeCount = 1+(random.nextInt(2));
		
		int newPosY = possibleValuesSE(chosenIsland, y, chosenIsland.getY(), Directions.SOUTH);

		if(newPosY <= 1 || !checkForAnotherIsland(chosenIsland, newPosY, Directions.SOUTH) || 
				!checkDirectedNeighboursWE(chosenIsland, newPosY, Directions.SOUTH)) {
			chosenIsland.setSouth(false);
		} else {
			if(!listOfBridges.isEmpty()) {
				int chosenX = chosenIsland.getX();
				int chosenY = chosenIsland.getY();
				for(int i = chosenIsland.getY(); i < newPosY+1; i++ ) {
					
					for (CreateBridges bridge : listOfBridges) {
						int firstX = bridge.getFirstIslandX();
						int secondX = bridge.getSecondIslandX();
						int y = bridge.getFirstIslandY();
						if(y == chosenIsland.getY()+1 &&(firstX < chosenX && secondX > chosenX)) {
							chosenIsland.setSouth(false);
							return;
						}
						if(y > chosenY+1 && i == y && (firstX < chosenX-1 && secondX > chosenX+1)) {
							for (Island island : listofIslands) {
								int islandX = island.getX();
								int islandY = island.getY();
								if(islandX == chosenX && (islandY == i+1 || islandY == i || islandX == chosenX+1 || islandX == chosenX-1 || islandY == i-1)) {
									chosenIsland.setSouth(false);
									return;
								}
								
							}
						
							Island nextIsland = new Island(chosenIsland.getX(), i, getIndex(), listofIslands, bridgeCount, false,false, 
									isBridgeSouthPossible(), false,0,0);
							
							islandMap.put(getIndex(), nextIsland);
							setIndex(getIndex()+1);
							chosenIsland.setSouth(false);
							
							nextIsland.setBridgeCount(nextIsland.getBridgeCount() + 2*bridge.getNumberOfBridges());
							listOfBridges.add(new CreateBridges( chosenIsland.getId(), chosenIsland.getX(), chosenIsland.getY(), 
									nextIsland.getId(), nextIsland.getX(), nextIsland.getY(),bridgeCount, null));
							listOfBridges.add(new CreateBridges(bridge.getFirstIslandID(), firstX, y, 
									nextIsland.getId(), nextIsland.getX(), nextIsland.getY(), bridge.getNumberOfBridges(), null));
							listOfBridges.add(new CreateBridges(nextIsland.getId(), nextIsland.getX(), nextIsland.getY(), 
									bridge.getSecondIslandID(), secondX, y, bridge.getNumberOfBridges(), null));
							listOfBridges.remove(bridge);
							
							listofIslands.add(nextIsland);
							possibleIslands.add(nextIsland);
							
							System.out.println(chosenIsland.getId() + "id1: "+ bridge.getFirstIslandID() + "id2: " + bridge.getSecondIslandID());
							System.out.println("South New ID: " + nextIsland.getId());
							return;
							
						}
	
					}
					
				}
					
				
			}
			
			Island nextIsland = createNextIsland(chosenIsland.getX(), newPosY, bridgeCount);
			
			/*Island nextIsland = new Island(chosenIsland.getX(), newPosY, getIndex(), listofIslands, bridgeCount, isBridgeNorthPossible(), 
					isBridgeEastPossible(), isBridgeSouthPossible(), isBridgeWestPossible());
			
			islandMap.put(getIndex(), nextIsland);
			setIndex(getIndex()+1);*/
			
			nextIsland.setNorth(false);
			chosenIsland.setSouth(false);
			chosenIsland.setBridgeCount(chosenIsland.getBridgeCount()+ bridgeCount);
			listOfBridges.add(new CreateBridges(chosenIsland.getId(), chosenIsland.getX(), chosenIsland.getY(),
					nextIsland.getId(), nextIsland.getX(), nextIsland.getY(), bridgeCount, null));
			System.out.println("South FirstID: " + chosenIsland.getId() + "Second ID: "+ nextIsland.getId() );
			listofIslands.add(nextIsland);
			
			possibleIslands.add(nextIsland);


		}
		return;
	}



	private void createEastIsland(Island chosenIsland) {
		int bridgeCount = 1+(random.nextInt(2));

		int newPosX = possibleValuesSE(chosenIsland, x, chosenIsland.getX(), Directions.EAST);
		if(newPosX <= 1 || !checkForAnotherIsland(chosenIsland, newPosX, Directions.EAST) ||
				!checkDirectedNeighboursWE(chosenIsland, newPosX, Directions.EAST)) {
			chosenIsland.setEast(false);

		} else {
			if(!listOfBridges.isEmpty()) {
				int chosenX = chosenIsland.getX();
				int chosenY = chosenIsland.getY();
				for(int i = chosenIsland.getX(); i < newPosX+1; i++ ) {
					
					for (CreateBridges bridge : listOfBridges) {
						int firstY = bridge.getFirstIslandY();
						int secondY = bridge.getSecondIslandY();
						int x = bridge.getFirstIslandX();
						if(x == chosenIsland.getY()+1 &&(firstY < chosenY && secondY > chosenY)) {
							chosenIsland.setEast(false);
							return;
						}
						if(x > chosenX+1 && i == x && (firstY < chosenY-1 && secondY > chosenY+1)) {
							for (Island island : listofIslands) {
								int islandX = island.getX();
								int islandY = island.getY();
								if(islandY == chosenY && (islandX == i+1 || islandX == i || islandY == chosenY-1 || islandY == chosenY+1|| islandX == i-1)) {
									chosenIsland.setEast(false);
									return;
								}
								
							}
						
							Island nextIsland = new Island(i, chosenIsland.getY(), getIndex(), listofIslands, bridgeCount, false,isBridgeEastPossible(), 
									false, false,0,0);
							
							islandMap.put(getIndex(), nextIsland);
							setIndex(getIndex()+1);
							chosenIsland.setEast(false);
							
							nextIsland.setBridgeCount(nextIsland.getBridgeCount() + 2*bridge.getNumberOfBridges());
							
							listOfBridges.add(new CreateBridges(chosenIsland.getId(), chosenIsland.getX(), chosenIsland.getY(), 
									nextIsland.getId(), nextIsland.getX(), nextIsland.getY(),bridgeCount, null));
							listOfBridges.add(new CreateBridges(bridge.getFirstIslandID(), x, firstY, 
									nextIsland.getId(), nextIsland.getX(), nextIsland.getY(), bridge.getNumberOfBridges(), null));
							listOfBridges.add(new CreateBridges(nextIsland.getId(), nextIsland.getX(), nextIsland.getY(), 
									bridge.getSecondIslandID(), x, secondY, bridge.getNumberOfBridges(), null));
							listOfBridges.remove(bridge);
							
							listofIslands.add(nextIsland);
							possibleIslands.add(nextIsland);
							
							System.out.println(chosenIsland.getId() + "id1: "+ bridge.getFirstIslandID() + "id2: " + bridge.getSecondIslandID());
							System.out.println("East New ID: " + nextIsland.getId());
							return;
							
						}
	
					}
					
				}
					
				
			}
			
			
			
			
			
			Island nextIsland = createNextIsland(newPosX, chosenIsland.getY(), bridgeCount);

			/*Island nextIsland = new Island(newPosX, chosenIsland.getY(), getIndex(), listofIslands, bridgeCount, isBridgeNorthPossible(), 
					isBridgeEastPossible(), isBridgeSouthPossible(), isBridgeWestPossible());
			
			islandMap.put(getIndex(), nextIsland);
			setIndex(getIndex()+1);*/
			
			chosenIsland.setEast(false);
			nextIsland.setWest(false);
			chosenIsland.setBridgeCount(chosenIsland.getBridgeCount()+ bridgeCount);
			listOfBridges.add(new CreateBridges(chosenIsland.getId(), chosenIsland.getX(), chosenIsland.getY(),
					nextIsland.getId(), nextIsland.getX(), nextIsland.getY(),
					bridgeCount, null));
			System.out.println("East FirstID: " + chosenIsland.getId() + "Second ID: "+ nextIsland.getId() );
			
			listofIslands.add(nextIsland);
			
			possibleIslands.add(nextIsland);

		}
		return;

	}


	
	private boolean checkForAnotherIsland(Island choosenIsland, int newPosition, Directions direction) {
	    Set<Integer> occupiedPositions  = new HashSet<Integer>();
	 
	    if (direction == Directions.WEST) {
	        for (Island island : listofIslands) {
	            if (island.getY() == choosenIsland.getY()) {
	            	occupiedPositions .add(island.getX());
	            }
	        }
	        for (int i = choosenIsland.getX() - 1; i > newPosition - 1; i--) {
	            if (occupiedPositions .contains(i)) {
	                return false;
	            }
	        }
	    } else if (direction == Directions.EAST) {
	        for (Island island : listofIslands) {
	            if (island.getY() == choosenIsland.getY()) {
	            	occupiedPositions .add(island.getX());
	            }
	        }
	        for (int i = choosenIsland.getX() + 1; i < newPosition + 1; i++) {
	            if (occupiedPositions .contains(i)) {
	                return false;
	            }
	        }
	    } else if (direction == Directions.SOUTH) {
	        for (Island island : listofIslands) {
	            if (island.getX() == choosenIsland.getX()) {
	            	occupiedPositions .add(island.getY());
	            }
	        }
	        for (int i = choosenIsland.getY() + 1; i < newPosition + 1; i++) {
	            if (occupiedPositions .contains(i)) {
	                return false;
	            }
	        }
	    } else if (direction == Directions.NORTH) {
	        for (Island island : listofIslands) {
	            if (island.getX() == choosenIsland.getX()) {
	            	occupiedPositions .add(island.getY());
	            }
	        }
	        for (int i = choosenIsland.getY() - 1; i > newPosition - 1; i--) {
	            if (occupiedPositions .contains(i)) {
	                return false;
	            }
	        }
	    }

	    return true;
	}




	private void createWestIsland(Island chosenIsland) {
		int bridgeCount = 1+(random.nextInt(2));
		int newPosX = possibleValuesNW(chosenIsland, x, chosenIsland.getX());

		
		if(newPosX <= 1 || !checkForAnotherIsland(chosenIsland, newPosX, Directions.WEST) ||
				!checkDirectedNeighboursWE(chosenIsland, newPosX, Directions.WEST)) {
			chosenIsland.setWest(false);
		} else {
			
			if(!listOfBridges.isEmpty()) {
				int chosenX = chosenIsland.getX();
				int chosenY = chosenIsland.getY();
				for(int i = chosenIsland.getX(); i > newPosX-1; i-- ) {
					
					for (CreateBridges bridge : listOfBridges) {
						int firstY = bridge.getFirstIslandY();
						int secondY = bridge.getSecondIslandY();
						int x = bridge.getFirstIslandX();
						if(x == chosenIsland.getY()-1 &&(firstY < chosenY && secondY > chosenY)) {
							chosenIsland.setWest(false);
							return;
						}
						if(x < chosenX-1 && i == x && (firstY < chosenY-1 && secondY > chosenY+1)) {
							for (Island island : listofIslands) {
								int islandX = island.getX();
								int islandY = island.getY();
								if(islandY == chosenY && (islandX == i-1 || islandX == i || islandY == chosenY+1 || islandY == chosenY-1 || islandX == i+1)) {
									chosenIsland.setWest(false);
									return;
								}
								
							}
						
							Island nextIsland = new Island(i, chosenIsland.getY(), getIndex(), listofIslands, bridgeCount, false,false, 
									false, isBridgeWestPossible(),0,0);
							
							islandMap.put(getIndex(), nextIsland);
							setIndex(getIndex()+1);
							chosenIsland.setWest(false);
							
							nextIsland.setBridgeCount(nextIsland.getBridgeCount() + 2*bridge.getNumberOfBridges());
							
							listOfBridges.add(new CreateBridges(nextIsland.getId(), nextIsland.getX(), nextIsland.getY(),
									chosenIsland.getId(), chosenIsland.getX(), chosenIsland.getY() ,bridgeCount, null));
							
							listOfBridges.add(new CreateBridges(bridge.getFirstIslandID(), x, firstY, 
									nextIsland.getId(), nextIsland.getX(), nextIsland.getY(), bridge.getNumberOfBridges(), null));
							
							listOfBridges.add(new CreateBridges(nextIsland.getId(), nextIsland.getX(), nextIsland.getY(), 
									bridge.getSecondIslandID(), x, secondY, bridge.getNumberOfBridges(), null));
							listOfBridges.remove(bridge);
							
							listofIslands.add(nextIsland);
							possibleIslands.add(nextIsland);
							
							System.out.println(chosenIsland.getId() + "id1: "+ bridge.getFirstIslandID() + "id2: " + bridge.getSecondIslandID());
							System.out.println("West New ID: " + nextIsland.getId());
							return;
							
						}
	
					}
					
				}
					
				
			}
			
			
			
			
			Island nextIsland = createNextIsland(newPosX, chosenIsland.getY(), bridgeCount);
					/*new Island(newPosX, chosenIsland.getY(), getIndex(), listofIslands, bridgeCount, isBridgeNorthPossible(), 
					isBridgeEastPossible(), isBridgeSouthPossible(), isBridgeWestPossible());
			
			islandMap.put(getIndex(), nextIsland);
			setIndex(getIndex()+1);*/
					
			chosenIsland.setWest(false);
			nextIsland.setEast(false);
			
			listOfBridges.add(new CreateBridges(nextIsland.getId(), nextIsland.getX(), nextIsland.getY(),
					chosenIsland.getId(), chosenIsland.getX(), chosenIsland.getY(),
					bridgeCount, null));
			chosenIsland.setBridgeCount(chosenIsland.getBridgeCount()+ bridgeCount);
			System.out.println("West FirstID: " + nextIsland.getId() + "Second ID: "+ chosenIsland.getId() );
			listofIslands.add(nextIsland);
			
			possibleIslands.add(nextIsland);

		}
		return;		
		

	}
	
	private Island createNextIsland(int x, int y, int bridgeCount) {
		Island nextIsland = new Island(x, y, getIndex(), listofIslands, bridgeCount, isBridgeNorthPossible(), 
				isBridgeEastPossible(), isBridgeSouthPossible(), isBridgeWestPossible(),0,0);
		
		islandMap.put(getIndex(), nextIsland);
		setIndex(getIndex()+1);
		return nextIsland;
	}
	

	
	/**
	 * Überprüft, ob es benachbarte Inseln in der angegebenen Richtung gibt.
	 *
	 * @param choosenIsland Die ausgewählte Insel.
	 * @param newPosition Die neue Position in der Richtung.
	 * @param direction Die Richtung (EAST, WEST, NORTH oder SOUTH).
	 * @return true, wenn keine benachbarten Inseln vorhanden sind, sonst false.
	 */
	private boolean checkDirectedNeighboursWE(Island chosenIsland, int newPosition, Directions direction) {
		
		for (Island island : listofIslands) {	
				int x = island.getX();
				int y = island.getY();
				
				if((direction == Directions.EAST || direction == Directions.WEST) &&
						(x == newPosition && (y == chosenIsland.getY()|| y == chosenIsland.getY()+1 || y == chosenIsland.getY()-1)||
						(y == chosenIsland.getY() && (x == newPosition+1 || x == newPosition-1)))) 
					return false;
				
				else if((direction == Directions.NORTH || direction == Directions.SOUTH) &&
						(y == newPosition && (x == chosenIsland.getX() || x == chosenIsland.getX()+1 ||x == chosenIsland.getX()-1) ||
						(x == chosenIsland.getX() && (y == newPosition+1 || y == newPosition-1)))) 
					return false;
				
			}
	
		return true;
	}
		



	private void checkDirection(Island chooseIsland) {
		int direction = 0;
		// true, false, false, false
		if(chooseIsland.isNorth() && !chooseIsland.isEast() && !chooseIsland.isSouth() && !chooseIsland.isWest()) {
			createNorthIsland(chooseIsland);				
			return;
		}
		// false, true, false, false
		if(!chooseIsland.isNorth() && chooseIsland.isEast() && !chooseIsland.isSouth() && !chooseIsland.isWest()) {
			//System.out.println("East");	
			createEastIsland(chooseIsland);
			return;
		}
		// false, false, true, false
		if(!chooseIsland.isNorth() && !chooseIsland.isEast() && chooseIsland.isSouth() && !chooseIsland.isWest()) {
			//System.out.println("South");
			createSouthIsland(chooseIsland);
			return;
		}
		// false, false, false, true
		if(!chooseIsland.isNorth() && !chooseIsland.isEast() && !chooseIsland.isSouth() && chooseIsland.isWest()) {
			//System.out.println("west");
			createWestIsland(chooseIsland);
			return;
		}

		// true, true, false, false
		if(chooseIsland.isNorth() && chooseIsland.isEast() && !chooseIsland.isSouth() && !chooseIsland.isWest()) {
			//System.out.println("North, east");
			direction = 1 + (new Random().nextInt(2));
			if(direction == 1) {
				createEastIsland(chooseIsland);
				return;
			} else {
				createNorthIsland(chooseIsland);
				return;
			}
		}

		// true, false, true, false
		if(chooseIsland.isNorth() && !chooseIsland.isEast() && chooseIsland.isSouth() && !chooseIsland.isWest()) {
			//System.out.println("North, south");
			direction = 1 + (new Random().nextInt(2));
			if(direction == 1) {
				createNorthIsland(chooseIsland);
				return;
			} else {
				createSouthIsland(chooseIsland);
				return;
			}
		}

		// true, false, false, true
		if(chooseIsland.isNorth() && !chooseIsland.isEast() && !chooseIsland.isSouth() && chooseIsland.isWest()) {
			//System.out.println("north, west");
			direction = 1 + (new Random().nextInt(2));
			if(direction == 1) {
				createNorthIsland(chooseIsland);
				return;
			} else {
				createWestIsland(chooseIsland);
				return;
			}
		}



		// false, true, false, true
		if(!chooseIsland.isNorth() && chooseIsland.isEast() && !chooseIsland.isSouth() && chooseIsland.isWest()) {
			//System.out.println("East, West");
			direction = 1+ (new Random().nextInt(2));
			if (direction == 1){
				createEastIsland(chooseIsland);
				return;
			} else {
				createWestIsland(chooseIsland);
				return;
			}
		}

		// false, false, true, true
		if(!chooseIsland.isNorth() && !chooseIsland.isEast() && chooseIsland.isSouth() && chooseIsland.isWest()) {
			//System.out.println("South, West");
			direction = 1+ (new Random().nextInt(2));
			if(direction == 1) {
				createSouthIsland(chooseIsland);
				return;
			} else {
				createWestIsland(chooseIsland);
				return;
			}
		}



		// true, false, true, true
		if(chooseIsland.isNorth() && !chooseIsland.isEast() && chooseIsland.isSouth() && chooseIsland.isWest()) {
			//System.out.println("Norht, South, West");
			direction = 1 + (new Random().nextInt(3));
			if(direction == 1) {
				createNorthIsland(chooseIsland);
				return;
			} else if(direction == 2) {
				createSouthIsland(chooseIsland);
				return;
			} else  {
				createWestIsland(chooseIsland);
				return;
			}

		}

		// true, true, false, true
		if(chooseIsland.isNorth() && chooseIsland.isEast() && !chooseIsland.isSouth() && chooseIsland.isWest()) {
			//System.out.println("Nort, East, West");
			direction = 1+ (new Random().nextInt(3));
			if(direction == 1) {
				createNorthIsland(chooseIsland);
				return;
			} else if(direction == 2) {
				createEastIsland(chooseIsland);
				return;
			} else  {
				createWestIsland(chooseIsland);
				return;
			}

		}

		// false, true, true, true
		if(!chooseIsland.isNorth() && chooseIsland.isEast() && chooseIsland.isSouth() && chooseIsland.isWest()) {
			//System.out.println("East, South, West");
			direction = 1+ (new Random().nextInt(3));
			if(direction == 1) {
				createEastIsland(chooseIsland);
				return;
			} else if(direction == 2) {
				createSouthIsland(chooseIsland);
				return;
			} else  {
				createWestIsland(chooseIsland);
				return;
			}

		}

		// true, true, true, true
		if(chooseIsland.isNorth() && chooseIsland.isEast() && chooseIsland.isSouth() && chooseIsland.isWest()) {
			//System.out.println("North, East, South, West");
			direction = 1+ (new Random().nextInt(4));
			if(direction == 1) {
				createNorthIsland(chooseIsland);
				return;
			} else if(direction == 2) {
				createEastIsland(chooseIsland);
				return;
			} else if(direction == 3) {
				createSouthIsland(chooseIsland);
				return;
			} else  {
				createWestIsland(chooseIsland);
				return;
			}

		}

		// false, false, false, false
		if(!chooseIsland.isNorth() && !chooseIsland.isEast() && !chooseIsland.isSouth() && !chooseIsland.isWest()) {
			for(int i =0; i < possibleIslands.size(); i++) {
				if(possibleIslands.get(i).getId() == chooseIsland.getId()) {
					possibleIslands.remove(i);
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
				createEastIsland(chooseIsland);
				return;
			} else  {
				createSouthIsland(chooseIsland);
				return;
			}

		}
		// true, true, true, false
		if(chooseIsland.isNorth() && chooseIsland.isEast() && chooseIsland.isSouth() && !chooseIsland.isWest()) {
			//System.out.println("North, East, South");	
			direction = 1+ (new Random().nextInt(3));
			if(direction == 1) {
				createNorthIsland(chooseIsland);
				return;
			} else if(direction == 2) {
				createEastIsland(chooseIsland);
				return;
			} else  {
				createSouthIsland(chooseIsland);
				return;
			}

		}

	}



	
	/**
	 * Sucht eine neue Zufällige Insel aus der Anzahl der möglichen Inseln.
	 * 
	 * @param possibleIslands die Menge der möglichen Inseln
	 * @param islandM Eine HashMap mit allen möglichen Inseln
	 * @return liefert eine zufällige Insel zurück
	 */
	private Island randomIsland(ArrayList<Island>possibleIslands, HashMap<Integer, Island> islandM) {		
		Island item = possibleIslands.get(random.nextInt(possibleIslands.size()));
		return islandM.get(item.getId());

	}


	/**
	 * Berechnet eine zufällige Anzahl von Brücken in südöstlicher Richtung zur nächsten Insel.
	 * SE steht für Südosten.
	 * Wenn die berechnete Anzahl kleiner oder gleich Null ist, gibt die Methode 0 zurück, was bedeutet, dass keine Brücken erstellt werden können.
	 * Andernfalls gibt die Methode eine zufällige Anzahl von Brücken zurück, die zur Verbindung mit der nächsten Insel verwendet werden sollen.
	 * 
	 * @param chosenIsland Die ausgewählte Insel, von der aus die Brücken gelegt werden sollen.
	 * @param xy Die aktuelle Position auf dem Spielfeld.
	 * @param islandXY Die Position der nächsten Insel auf dem Spielfeld.
	 * @return 0, wenn keine Brücken möglich sind, oder eine zufällige Anzahl von Brücken, die gelegt werden können.
	 */
	private int possibleValuesSE(Island chosenIsland, int xy, int islandXY, Directions direction ) {
	    int possibleValues = xy - (islandXY + 1);
	    if (possibleValues <= 0) {
	        return 0;
	    } else {
	        possibleValues = (random.nextInt(possibleValues));
	        if (possibleValues == 0) {
	        	if(direction == Directions.SOUTH && (2+ islandXY) > getY() )
	        		return possibleValues = 0;
	        	if(direction == Directions.EAST && (2+ islandXY) > getY()) 
	        		return possibleValues = 0;
	        	else
	        		return possibleValues = 2 + islandXY;
	            //return possibleValues;
	        }
	    }
	    possibleValues += 1 + islandXY;
	    return possibleValues;
	}





	/**
	 * possibleValuesNW gibt eine Zufallszahl zurück, abhängig von der Richtung zur nächsten Insel
	 * NW steht für Nordwesten
	 * Wenn der berechnete Wert kleiner oder gleich Null ist, gibt die Methode 0 zurück
	 * Andernfalls gibt die Methode eine Zufallszahl zurück, die angibt, wo die nächste Insel auf dem Spielfeld platziert werden sollte
	 * 
	 * @param chosenIsland 
	 * @param xy
	 * @param inselXY
	 * @return 0 als falsch oder eine Zufallszahl als wahr
	 */
	private int possibleValuesNW(Island chosenIsland, int xy, int islandXY) {
		int possibleValues = xy + (islandXY - x-2);
		if(possibleValues <= 0) {			
			return 0;
		} else {
			possibleValues = (random.nextInt(possibleValues));
			if(possibleValues == 0) {
				if((islandXY - 2) < 0)
					return possibleValues =0;
				else
					return possibleValues = islandXY- 2;
				

				//return possibleValues;
			}
		}
		return possibleValues;	
	}

	
	
	/*****************************************************************************************************************************/
	/*****************************************************GETTER******************************************************************/
	/*****************************************************************************************************************************/

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
	 * Setzt den Y-Wert der Insel.
	 *
	 * @param islandY der Y-Wert, der gesetzt werden soll
	 */
	public void setIslandY(int islandY) {
	    this.islandY = islandY;
	}

	/**
	 * Überprüft, ob eine Brücke im Norden dieser Insel möglich ist.
	 *
	 * @return true, wenn eine Brücke im Norden möglich ist, sonst false
	 */
	public boolean isBridgeNorthPossible() {
	    if (getIslandY() == 1 || getIslandY() - 1 == 1) {
	        return false;
	    }
	    return true;
	}

	/**
	 * Überprüft, ob eine Brücke im Osten dieser Insel möglich ist.
	 *
	 * @return true, wenn eine Brücke im Osten möglich ist, sonst false
	 */
	public boolean isBridgeEastPossible() {
	    if (getIslandX() == getX() || getIslandX() + 1 == getX()) {
	        return false;
	    }
	    return true;
	}

	/**
	 * Überprüft, ob eine Brücke im Süden dieser Insel möglich ist.
	 *
	 * @return true, wenn eine Brücke im Süden möglich ist, sonst false
	 */
	public boolean isBridgeSouthPossible() {
	    if (getIslandY() == getY() || getIslandY() + 1 == getY()) {
	        return false;
	    }
	    return true;
	}

	/**
	 * Überprüft, ob eine Brücke im Westen dieser Insel möglich ist.
	 *
	 * @return true, wenn eine Brücke im Westen möglich ist, sonst false
	 */
	public boolean isBridgeWestPossible() {
	    if (getIslandX() == 1 || getIslandX() - 1 == 1) {
	        return false;
	    }
	    return true;
	}

	/**
	 * Gibt eine Zeichenfolge zurück, die die X-Koordinate der Insel darstellt.
	 *
	 * @return eine Zeichenfolge mit der X-Koordinate der Insel
	 */
	public String toString() {
	    String text = "X: " + x;
	    return text;
	}

	/**
	 * @return die Anzahl der Brücken, die mit dieser Insel verbunden sind
	 */
	public int getBridgeCount() {
	    return bridgeCount;
	}




	/**
	 * Setzt die Anzahl der Brücken, die mit dieser Insel verbunden sind.
	 *
	 * @param bridgeCount die Anzahl der Brücken, die gesetzt werden soll
	 */
	public void setBridgeCount(int bridgeCount) {
	    this.bridgeCount = bridgeCount;
	}

	/**
	 * Überprüft, ob eine Brücke im Norden dieser Insel existiert.
	 *
	 * @return true, wenn eine Brücke im Norden existiert, sonst false
	 */
	public boolean isNorth() {
	    return north;
	}

	/**
	 * Setzt den Wert, ob eine Brücke im Norden dieser Insel existiert.
	 *
	 * @param north true, wenn eine Brücke im Norden existiert, sonst false
	 */
	public void setNorth(boolean north) {
	    this.north = north;
	}

	/**
	 * Überprüft, ob eine Brücke im Osten dieser Insel existiert.
	 *
	 * @return true, wenn eine Brücke im Osten existiert, sonst false
	 */
	public boolean isEast() {
	    return east;
	}

	/**
	 * Setzt den Wert, ob eine Brücke im Osten dieser Insel existiert.
	 *
	 * @param east true, wenn eine Brücke im Osten existiert, sonst false
	 */
	public void setEast(boolean east) {
	    this.east = east;
	}

	/**
	 * Überprüft, ob eine Brücke im Süden dieser Insel existiert.
	 *
	 * @return true, wenn eine Brücke im Süden existiert, sonst false
	 */
	public boolean isSouth() {
	    return south;
	}

	/**
	 * Setzt den Wert, ob eine Brücke im Süden dieser Insel existiert.
	 *
	 * @param south true, wenn eine Brücke im Süden existiert, sonst false
	 */
	public void setSouth(boolean south) {
	    this.south = south;
	}

	/**
	 * Überprüft, ob eine Brücke im Westen dieser Insel existiert.
	 *
	 * @return true, wenn eine Brücke im Westen existiert, sonst false
	 */
	public boolean isWest() {
	    return west;
	}




	/**
	 * Setzt den Wert, ob eine Brücke im Westen dieser Insel existiert.
	 *
	 * @param west true, wenn eine Brücke im Westen existiert, sonst false
	 */
	public void setWest(boolean west) {
	    this.west = west;
	}

	/**
	 * Gibt die Liste der Inseln zurück.
	 *
	 * @return die Liste der Inseln
	 */
	public ArrayList<Island> getListofIslands() {
	    return listofIslands;
	}

	/**
	 * Setzt die Liste der Inseln.
	 *
	 * @param listofIslands die Liste der Inseln, die gesetzt werden soll
	 */
	public void setListofIslands(ArrayList<Island> listofIslands) {
	    this.listofIslands = listofIslands;
	}

	/**
	 * Gibt die X-Koordinate dieser Insel zurück.
	 *
	 * @return die X-Koordinate der Insel
	 */
	public int getX() {
	    return x;
	}

	/**
	 * Setzt die X-Koordinate dieser Insel.
	 *
	 * @param x die X-Koordinate, die gesetzt werden soll
	 */
	public void setX(int x) {
	    this.x = x;
	}


	/**
	 * Gibt die Y-Koordinate dieser Insel zurück.
	 *
	 * @return die Y-Koordinate der Insel
	 */
	public int getY() {
	    return y;
	}

	/**
	 * Setzt die Y-Koordinate dieser Insel.
	 *
	 * @param y die Y-Koordinate, die gesetzt werden soll
	 */
	public void setY(int y) {
	    this.y = y;
	}

	/**
	 * Gibt die ID dieser Insel zurück.
	 *
	 * @return die ID der Insel
	 */
	public int getId() {
	    return id;
	}

	/**
	 * Setzt die ID dieser Insel.
	 *
	 * @param id die ID, die gesetzt werden soll
	 */
	public void setId(int id) {
	    this.id = id;
	}

	/**
	 * Gibt den Index dieser Insel zurück.
	 *
	 * @return der Index der Insel
	 */
	public int getIndex() {
	    return index;
	}

	/**
	 * Setzt den Index dieser Insel.
	 *
	 * @param index der Index, der gesetzt werden soll
	 */
	public void setIndex(int index) {
	    this.index = index;
	}


	/**
	 * Überprüft, ob diese Insel korrekt platziert ist.
	 *
	 * @return true, wenn die Insel korrekt platziert ist, andernfalls false
	 */
	public boolean isOk() {
	    return isOk;
	}

	/**
	 * Setzt den Zustand, ob diese Insel korrekt platziert ist oder nicht.
	 *
	 * @param isOk true, wenn die Insel korrekt platziert ist, andernfalls false
	 */
	public void setOk(boolean isOk) {
	    this.isOk = isOk;
	}

	/**
	 * Gibt die X-Koordinate des Zentrums dieser Insel zurück.
	 *
	 * @return die X-Koordinate des Zentrums der Insel
	 */
	public int getCenterX() {
	    return centerX;
	}

	/**
	 * Setzt die X-Koordinate des Zentrums dieser Insel.
	 *
	 * @param centerX die X-Koordinate des Zentrums der Insel
	 */
	public void setCenterX(int centerX) {
	    this.centerX = centerX;
	}

	/**
	 * Gibt die Y-Koordinate des Zentrums dieser Insel zurück.
	 *
	 * @return die Y-Koordinate des Zentrums der Insel
	 */
	public int getCenterY() {
	    return centerY;
	}

	/**
	 * Setzt die Y-Koordinate des Zentrums dieser Insel.
	 *
	 * @param centerY die Y-Koordinate des Zentrums der Insel
	 */
	public void setCenterY(int centerY) {
	    this.centerY = centerY;
	}



}