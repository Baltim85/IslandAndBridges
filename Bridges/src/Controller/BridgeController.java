package Controller;

import java.awt.event.MouseEvent;

import java.awt.event.MouseListener;
import java.util.ArrayList;

import UI.Bridges;
import Modell.CreateBridges;
import Modell.Directions;
import Modell.Island;

public class BridgeController implements MouseListener {

	private Bridges bridges;
	private CreateBridges cBridges;
	
	private ArrayList<Island> islandList = new ArrayList<Island>();
	private ArrayList<CreateBridges> listOfBridge = new ArrayList<CreateBridges>();
	private int centerX, centerY, delta, width, height;
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	private int selectedID;
	
	
	
	
    public int getSelectedID() {
		return selectedID;
	}

	public void setSelectedID(int selectedID) {
		this.selectedID = selectedID;
	}

	public BridgeController() {
        /*this.islandList = islandList;
        this.bridges = bridges;
        for(Island island: islandList) {
        	System.out.println("Island: " + island.getId() + " counter " + island.getBridgeCount());
        	
        }
        this.centerX = centerX;
        this.centerY = centerY;
        calculateCenterForEachIsland(islandList, centerX, centerY);*/
        
       
    }
	
    public void initController(ArrayList<Island> islandList, Bridges bridges, int centerX, int centerY, int delta, int width, int height) {
    	this.islandList = islandList;
        this.bridges = bridges;
        this.width = width;
        this.height = height;
        for(Island island: islandList) {
        	System.out.println("Island: " + island.getId() + " counter " + island.getBridgeCount());
        	
        }
        this.centerX = centerX;
        this.centerY = centerY;
        this.delta = delta;
        listOfBridge.clear();
        calculateCenterForEachIsland(islandList, centerX, centerY);
    }
    
    private void calculateCenterForEachIsland(ArrayList<Island> islandList, int x, int y) {
    	
    	for(Island island : islandList) {
    		int islandX = island.getX();
    		int islandY = island.getY();
    		island.setCenterX(x * islandX - x + ((x/2)+2));
    		island.setCenterY(y * islandY - y + ((y/2)+2));
    		//System.out.println("Island X: " + islandX + " island y: " + islandY + " X: " + x + " y: " + y  + " CenterX" + (x * islandX - x + ((x/2)+2)) );
        	//System.out.println("Island X: " + islandX + " island y: " + islandY + " X: " + x + " y: " + y  + " CenterY" + (y * islandY - y + ((y/2)+2)) );
    		
    	}
    }
    
    
    public void handleMouseClick(int x, int y) {
        System.out.println("Maus Klicked: " + x + " y: " + y);
        Directions direction = checkDirection(x, y, islandList, getDelta());
        if(direction == Directions.EAST){
        	System.out.println("Osten");
        	createEastBridge(0, 0);
        }
        if(direction == Directions.WEST){
        	System.out.println("Westen");
        	createWestBridge(0, 0);
        }
        if(direction == Directions.SOUTH){
        	System.out.println("Süden");
        	createSouthBridge(0, 0);
        }
        if(direction == Directions.NORTH){
        	createNorthBridge(0, 0);
        	System.out.println("Norden");
        }
    }

    private Directions checkDirection(int x, int y, ArrayList<Island> islandList, int delta) {
    	for(Island island : islandList) {
    		int islandX = island.getCenterX();
    		int islandY = island.getCenterY();
    		int d = delta/2;
    		if((islandX < x && x < islandX + d) 
    			&& (islandY - d < y && y < islandY + d) && 
    			(Math.abs(y-islandY) < Math.abs(x - islandX))) {
    			System.out.println("Island ID: " + island.getId());
    			setSelectedID(island.getId());
    			return Directions.EAST;
    		}
    		if( (islandX > x && x > islandX - d) && 
    			(islandY - d < y && y < islandY + d) &&
    			(Math.abs(y-islandY) < Math.abs(x - islandX))) {
    			System.out.println("Island ID: " + island.getId());
    			setSelectedID(island.getId());
    			return Directions.WEST;
    			
    		}
    		
    		if((islandY < y && y < islandY + d) 
        			&& (islandX - d < x && x < islandX + d) && 
        			(Math.abs(x-islandX) < Math.abs(y - islandY))) {
        			System.out.println("Island ID: " + island.getId());
        			setSelectedID(island.getId());
        			return Directions.SOUTH;
        		}
        		if( (islandY > y && y > islandY - d) && 
        			(islandX - d < x && x < islandX + d) &&
        			(Math.abs(x-islandX) < Math.abs(y - islandY))) {
        			System.out.println("Island ID: " + island.getId());
        			setSelectedID(island.getId());
        			return Directions.NORTH;
        			
        		}
    				
    				
    		
    	}
    	return null;
    }
    
    
    private void createWestBridge(int x, int y) {
    	int selectedX = islandList.get(getSelectedID()).getX();
    	int selectedY = islandList.get(getSelectedID()).getY();

    	//System.out.println(selectedX);
    	for(int i = selectedX-1; i > -1;i-- ) {
    		for(Island island: islandList) {
        		int islandX = island.getX();
        		int islandY = island.getY();
        		int id = island.getId();
        		
        		if(!listOfBridge.isEmpty()) {
        			if(checkForAnotherBridge(Directions.WEST, selectedY, 0, i)) 
        				return;
        			/*for(CreateBridges bridge : listOfBridge) {
        				int firstX = bridge.getFirstIslandX();
        				int firstY = bridge.getFirstIslandY();
        				int secondY = bridge.getSecondIslandY();
        				if(firstX == i && ((firstY < selectedY && secondY > selectedY) || (secondY < selectedY && firstY > selectedY))) {
        					System.out.println("Error");
        					return;
        				}
        			}*/
        		}
        		
        		if(islandY == selectedY && id != getSelectedID() && islandX == i) {
        			System.out.println("Connected ID: " + id);
        			if(listOfBridge.isEmpty())
        				
        				listOfBridge.add(new CreateBridges( id, islandX, islandY,getSelectedID(), selectedX, selectedY, 1, null));
        			else {
        				for(CreateBridges bridge : listOfBridge) {
        					int firstID = bridge.getFirstIslandID();
        					int secondID = bridge.getSecondIslandID();
        					if(firstID == id && secondID == getSelectedID()  || firstID == getSelectedID() && secondID == id) {
        						int bridgeCounter = bridge.getNumberOfBridges();
        						if(bridgeCounter == 2) {
        							System.out.println("Zu viele Inseln");
        							return;
        						} 
        						else {
        							bridgeCounter += 1;
        							bridge.setNumberOfBridges(bridgeCounter);
        							return;
        						}
        							
        					}
        				}
        				listOfBridge.add(new CreateBridges( id, islandX, islandY,getSelectedID(), selectedX, selectedY, 1, null));
        				
        			}
        			return;
        		}
        	}
    	}
    	
    }
    

    

    
    private void createNorthBridge(int x, int y) {
    	int selectedX = islandList.get(getSelectedID()).getX();
    	int selectedY = islandList.get(getSelectedID()).getY();

    	//System.out.println(selectedX);
    	for(int i = selectedY-1; i > -1;i-- ) {
    		for(Island island: islandList) {
        		int islandX = island.getX();
        		int islandY = island.getY();
        		int id = island.getId();
        		
        		if(!listOfBridge.isEmpty()) {
        			if(checkForAnotherBridge(Directions.NORTH, 0, selectedX, i))
        				return;
        			/*for(CreateBridges bridge : listOfBridge) {
        				int firstY = bridge.getFirstIslandY();
        				int firstX = bridge.getFirstIslandX();
        				int secondX = bridge.getSecondIslandX();
        				if(firstY == i && ((firstX < selectedX && secondX > selectedX) || (secondX < selectedX && firstX > selectedX))) {
        					System.out.println("Error");
        					return;
        				}
        			}*/
        		}
        		
        		if(islandX == selectedX && id != getSelectedID() && islandY == i) {
        			System.out.println("Connected ID: " + id);
        			if(listOfBridge.isEmpty())
        				
        				listOfBridge.add(new CreateBridges(id, islandX, islandY,getSelectedID(), selectedX, selectedY, 1, null));
        			else {
        				for(CreateBridges bridge : listOfBridge) {
        					int firstID = bridge.getFirstIslandID();
        					int secondID = bridge.getSecondIslandID();
        					if(firstID == id && secondID == getSelectedID()  || firstID == getSelectedID() && secondID == id) {
        						int bridgeCounter = bridge.getNumberOfBridges();
        						if(bridgeCounter == 2) {
        							System.out.println("Zu viele Inseln");
        							return;
        						} 
        						else {
        							bridgeCounter += 1;
        							bridge.setNumberOfBridges(bridgeCounter);
        							return;
        						}
        							
        					}
        				}
        				listOfBridge.add(new CreateBridges(id, islandX, islandY,getSelectedID(), selectedX, selectedY, 1, null));
        			}
        			return;
        		}
        	}
    	}
    	
    }
    
    private void createEastBridge(int x, int y) {
    	int selectedX = islandList.get(getSelectedID()).getX();
    	int selectedY = islandList.get(getSelectedID()).getY();

    	//System.out.println(selectedX);
    	for(int i = selectedX+1; i < getWidth()+1;i++ ) {
    		for(Island island: islandList) {
        		int islandX = island.getX();
        		int islandY = island.getY();
        		int id = island.getId();
        		
        		if(!listOfBridge.isEmpty()) {
        			if(checkForAnotherBridge(Directions.EAST, selectedY, 0, i))
        				return;
        			
        			/*for(CreateBridges bridge : listOfBridge) {
        				int firstX = bridge.getFirstIslandX();
        				int firstY = bridge.getFirstIslandY();
        				int secondY = bridge.getSecondIslandY();
        				if(firstX == i && ((firstY < selectedY && secondY > selectedY) || (secondY < selectedY && firstY > selectedY))) {
        					System.out.println("Error");
        					return;
        				}
        			}*/
        		}
        		
        		if(islandY == selectedY && id != getSelectedID() && islandX == i) {
        			System.out.println("Connected ID: " + id);
        			if(listOfBridge.isEmpty())
        				
        				listOfBridge.add(new CreateBridges(getSelectedID(), selectedX, selectedY, id, islandX, islandY, 1, null));
        			else {
        				for(CreateBridges bridge : listOfBridge) {
        					
        					
        					int firstID = bridge.getFirstIslandID();
        					int secondID = bridge.getSecondIslandID();
        					if(firstID == id && secondID == getSelectedID()  || firstID == getSelectedID() && secondID == id)  {
        						int bridgeCounter = bridge.getNumberOfBridges();
        						if(bridgeCounter == 2) {
        							System.out.println("Zu viele Inseln");
        							return;
        						} 
        						else {
        							bridgeCounter += 1;
        							bridge.setNumberOfBridges(bridgeCounter);
        							return;
        						}
        							
        					}
        				}
        				listOfBridge.add(new CreateBridges(getSelectedID(), selectedX, selectedY, id, islandX, islandY, 1, null));
        			}
        			return;
        		}
        	}
    	}
    	
    }
    
    private void createSouthBridge(int x, int y) {
    	int selectedX = islandList.get(getSelectedID()).getX();
    	int selectedY = islandList.get(getSelectedID()).getY();

    	//System.out.println(selectedX);
    	for(int i = selectedY+1; i < getHeight()+1;i++ ) {
    		for(Island island: islandList) {
        		int islandX = island.getX();
        		int islandY = island.getY();
        		int id = island.getId();
        		
        		if(!listOfBridge.isEmpty()) {
        			if(checkForAnotherBridge(Directions.SOUTH, 0, selectedX, i))
        				return;
        			/*for(CreateBridges bridge : listOfBridge) {
        				int firstY = bridge.getFirstIslandY();
        				int firstX = bridge.getFirstIslandX();
        				int secondX = bridge.getSecondIslandX();
        				if(firstY == i && ((firstX < selectedX && secondX > selectedX) || (secondX < selectedX && firstX > selectedX))) {
        					System.out.println("Error");
        					return;
        				}
        			}*/
        		}
        		
        		
        		if(islandX == selectedX && id != getSelectedID() && islandY == i) {
        			System.out.println("Connected ID: " + id);
        			if(listOfBridge.isEmpty())       				
        				listOfBridge.add(new CreateBridges(getSelectedID(), selectedX, selectedY, id, islandX, islandY, 1, null));
        			else {
        				for(CreateBridges bridge : listOfBridge) {
        					int firstX = bridge.getFirstIslandX();
        					int secondX = bridge.getSecondIslandX();
        					int firstY = bridge.getFirstIslandY();
        					int secondY = bridge.getSecondIslandY();
        					int firstID = bridge.getFirstIslandID();
        					int secondID = bridge.getSecondIslandID();
        					
        					if(firstID == id && secondID == getSelectedID()  || firstID == getSelectedID() && secondID == id)  {
        						/*int bridgeCounter = bridge.getNumberOfBridges();
        						if(bridgeCounter == 2) {
        							System.out.println("Zu viele Inseln");
        							return;
        						} 
        						else {
        							bridgeCounter += 1;
        							bridge.setNumberOfBridges(bridgeCounter);
        							return;
        						}*/
        						updateBridgeCounter(bridge);
        						return;
        							
        					}
        				}
        				listOfBridge.add(new CreateBridges(getSelectedID(), selectedX, selectedY, id, islandX, islandY, 1, null));
        			}
        			return;
        		}
        	}
    	}
    	
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
    private boolean checkForAnotherBridge(Directions direction, int selectedY, int selectedX, int i) {
        for (CreateBridges bridge : listOfBridge) {
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
    
    
    /**
     * Aktualisiert den Zähler für die Anzahl der Brücken für eine gegebene Brücke.
     *
     * @param bridge Die Brücke, für die der Zähler aktualisiert werden soll.
     */
    private void updateBridgeCounter(CreateBridges bridge) {
        // Holen Sie sich den aktuellen Brückenzähler für die Brücke.
        int bridgeCounter = bridge.getNumberOfBridges();

        // Überprüfen, ob der Brückenzähler bereits 2 erreicht hat.
        if (bridgeCounter == 2) {
            System.out.println("Zu viele Brücken für diese Inseln.");
            return; // Beende die Methode, da die maximale Anzahl erreicht wurde.
        } 
        // Wenn der Brückenzähler kleiner als 2 ist, erhöhe ihn um 1.
        else {
            bridgeCounter += 1;
            bridge.setNumberOfBridges(bridgeCounter); // Setze den aktualisierten Zähler zurück.
            return;
        }
    }
    
    
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Gibt die Bridges-Instanz zurück.
	 *
	 * @return Die Bridges-Instanz.
	 */
	public Bridges getBridges() {
	    return bridges;
	}

	/**
	 * Setzt die Bridges-Instanz.
	 *
	 * @param bridges Die Bridges-Instanz, die gesetzt werden soll.
	 */
	public void setBridges(Bridges bridges) {
	    this.bridges = bridges;
	}

	/**
	 * Gibt die Liste der Inseln zurück.
	 *
	 * @return Die Liste der Inseln.
	 */
	public ArrayList<Island> getIslandList() {
	    return islandList;
	}

	/**
	 * Setzt die Liste der Inseln.
	 *
	 * @param islandList Die Liste der Inseln, die gesetzt werden soll.
	 */
	public void setIslandList(ArrayList<Island> islandList) {
	    this.islandList = islandList;
	}

	/**
	 * Gibt die X-Koordinate des Zentrums zurück.
	 *
	 * @return Die X-Koordinate des Zentrums.
	 */
	public int getCenterX() {
	    return centerX;
	}

	/**
	 * Setzt die X-Koordinate des Zentrums.
	 *
	 * @param centerX Die X-Koordinate des Zentrums, die gesetzt werden soll.
	 */
	public void setCenterX(int centerX) {
	    this.centerX = centerX;
	}

	/**
	 * Gibt die Y-Koordinate des Zentrums zurück.
	 *
	 * @return Die Y-Koordinate des Zentrums.
	 */
	public int getCenterY() {
	    return centerY;
	}

	/**
	 * Setzt die Y-Koordinate des Zentrums.
	 *
	 * @param centerY Die Y-Koordinate des Zentrums, die gesetzt werden soll.
	 */
	public void setCenterY(int centerY) {
	    this.centerY = centerY;
	}

	/**
	 * Gibt den Delta-Wert zurück.
	 *
	 * @return Der Delta-Wert.
	 */
	public int getDelta() {
	    return delta;
	}

	/**
	 * Setzt den Delta-Wert.
	 *
	 * @param delta Der Delta-Wert, der gesetzt werden soll.
	 */
	public void setDelta(int delta) {
	    this.delta = delta;
	}

}
