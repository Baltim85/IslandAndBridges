package Modell;


import java.util.ArrayList;

import Controller.BridgeController;
import UI.Bridges;

/**
 * Die Klasse "CreateAndDrawBridges" ist verantwortlich für die Erstellung und Zeichnung von Brücken zwischen Inseln im Spiel.
 * Sie verwaltet die Liste der Inseln und die Liste der erstellten Brücken. Außerdem steuert sie die Erstellung von Brücken
 * in verschiedenen Richtungen von der ausgewählten Insel.
 */
public class CreateAndDrawBridges {

	/**
	 * Die Bridges, die in diesem Controller verwaltet werden.
	 */
	private Bridges bridges;
	
	private boolean loaded = false;


	public boolean isLoaded() {
		return loaded;
	}

	public void setLoaded(boolean loaded) {
		this.loaded = loaded;
	}

	/**
	 * Die Liste der Inseln, die von diesem Controller verwaltet werden.
	 */
	private ArrayList<Island> islandList = new ArrayList<Island>();

	/**
	 * Die Liste der erstellten Brücken.
	 */
	private ArrayList<CreateBridges> listOfBridge = new ArrayList<CreateBridges>();

	/**
	 * Die X-Koordinate des Zentrums.
	 */
	private int centerX;

	/**
	 * Die Y-Koordinate des Zentrums.
	 */
	private int centerY;

	/**
	 * Der Abstand (delta) zwischen den Elementen.
	 */
	private int delta;

	/**
	 * Die Breite des Objekts.
	 */
	private int width;

	/**
	 * Die Höhe des Objekts.
	 */
	private int height;
	
	/**
	 * Die ausgewählte ID, die auf eine bestimmte Insel in der Liste von Inseln verweist.
	 * Diese ID wird für verschiedene Aktionen und Berechnungen verwendet.
	 */
	private int selectedID;
	
	private BridgeController controller;
	
	/**
	 * Konstruktor für die Erstellung und Zeichnung von Brücken.
	 *
	 * @param islandList   Die Liste der Inseln.
	 * @param bridges      Die Brücken.
	 * @param centerX      Die x-Koordinate des Zentrums.
	 * @param centerY      Die y-Koordinate des Zentrums.
	 * @param delta        Der Delta-Wert.
	 * @param width        Die Breite.
	 * @param height       Die Höhe.
	 * @param controller   Der BridgeController.
	 */
	public CreateAndDrawBridges(ArrayList<Island> islandList, Bridges bridges, int centerX, int centerY,
	                            int delta, int width, int height, BridgeController controller) {
	    this.islandList = islandList;
	    this.bridges = bridges;
	    this.width = width;
	    this.height = height;
	    this.centerX = centerX;
	    this.centerY = centerY;
	    this.delta = delta;
	    listOfBridge.clear();
	    
	    this.controller = controller;
	    calculateCenterForEachIsland(islandList, centerX, centerY);
	}
	
	/**
	 * Konstruktor für die Erstellung und Zeichnung von Brücken.
	 *
	 * @param islandList   Die Liste der Inseln.
	 * @param bridges      Die Brücken.
	 * @param centerX      Die x-Koordinate des Zentrums.
	 * @param centerY      Die y-Koordinate des Zentrums.
	 * @param delta        Der Delta-Wert.
	 * @param width        Die Breite.
	 * @param height       Die Höhe.
	 * @param controller   Der BridgeController.
	 */
	public CreateAndDrawBridges(ArrayList<Island> islandList, ArrayList<CreateBridges> listOfB, Bridges bridges, int centerX, int centerY,
	                            int delta, int width, int height, BridgeController controller) {
	    this.islandList = islandList;
	    this.bridges = bridges;
	    this.width = width;
	    this.height = height;
	    this.centerX = centerX;
	    this.centerY = centerY;
	    this.delta = delta;
	    listOfBridge.clear();
	    listOfBridge = listOfB;
	    this.controller = controller;
	    calculateCenterForEachIsland(islandList, centerX, centerY);
	    
	    
	}

	public void updateLoadedGame() {
		for(CreateBridges b : listOfBridge) {
			int firstID = b.getFirstIslandID();
			int secondID = b.getSecondIslandID();
			
			int numberOfConnections = b.getNumberOfBridges();
			
			Island firstIsland = islandList.get(firstID);
			Island secondIsland = islandList.get(secondID);
			
			
			
			int centerX = firstIsland.getCenterX();
			int centerY = firstIsland.getCenterY();
			int secondCenterX = secondIsland.getCenterX();
			int secondCenterY = secondIsland.getCenterY();
			int drawLineX = 0;
			int drawLineX2 = 0;
			int drawLineY = 0;
			int drawLineY2 = 0;
			
			//East
			if(centerX < secondCenterX) {
				drawLineX = centerX + (delta / 2) + 3;
                drawLineX2 = secondCenterX - (delta / 2) - 3;
                if(numberOfConnections == 2)
                	controller.drawDoubleBridge(drawLineX, centerY, drawLineX2, centerY, Directions.EAST, firstID, secondID);
                else
                	controller.drawBridge(drawLineX, centerY, drawLineX2, centerY, firstID, secondID);
                
			} 
			//West
			else if(centerX > secondCenterX) {
				drawLineX = centerX - (delta / 2) - 3;
	            drawLineX2 = secondCenterX + (delta / 2) + 3;
	            if(numberOfConnections == 2)
	            	controller.drawDoubleBridge(drawLineX, centerY, drawLineX2, centerY, Directions.WEST, secondID, firstID);
	            else
	            	controller.drawBridge(drawLineX, centerY, drawLineX2, centerY, secondID, firstID);
			}
			
			//North
			else if(centerY > secondCenterY) {
				drawLineY = centerY - (delta / 2) - 3;
                drawLineY2 = secondCenterY + (delta / 2) + 3;	
                if(numberOfConnections == 2)
                	controller.drawDoubleBridge(centerX, drawLineY, centerX, drawLineY2, Directions.NORTH, secondID, firstID);
                else
                	controller.drawBridge(centerX, drawLineY, centerX, drawLineY2, secondID, firstID);
			}
			
			//South
			else if(centerY < secondCenterY) {
				drawLineY = centerY + (delta / 2) + 3;
                drawLineY2 = secondCenterY - (delta / 2) - 3;		
                if(numberOfConnections == 2)
                	controller.drawDoubleBridge(centerX, drawLineY, centerX, drawLineY2, Directions.SOUTH, firstID, secondID);
                else
                	controller.drawBridge(centerX, drawLineY, centerX, drawLineY2, firstID, secondID);
			}
			
			
			firstIsland.setBridgeCount(firstIsland.getBridgeCount() - numberOfConnections);
			secondIsland.setBridgeCount(secondIsland.getBridgeCount() - numberOfConnections);
			setLoaded(true);
			decrementBridgeCounter(firstID, secondID, islandList);
			setLoaded(false);
		}
	}
	
	
    
	/**
	 * Berechnet das Zentrum (centerX und centerY) für jede Insel in der angegebenen Liste von Inseln.
	 * Das Zentrum wird anhand der übergebenen x- und y-Koordinaten berechnet.
	 *
	 * @param islandList Eine Liste von Inseln, für die das Zentrum berechnet werden soll.
	 * @param x Die X-Koordinate des Zentrums.
	 * @param y Die Y-Koordinate des Zentrums.
	 */
	private void calculateCenterForEachIsland(ArrayList<Island> islandList, int x, int y) {
	    for (Island island : islandList) {
	        int islandX = island.getX();
	        int islandY = island.getY();
	        island.setCenterX(x * islandX - x + ((x / 2) + 2));
	        island.setCenterY(y * islandY - y + ((y / 2) + 2));
	    }
	}


	/**
	 * Erzeugt eine Brücke in westlicher Richtung von der ausgewählten Insel.
	 *
	 * @param leftButton Ein boolescher Wert, der angibt, ob die linke Maustaste gedrückt wurde.
	 * @param selectedID Die ID der ausgewählten Insel.
	 */
	public void createWestBridge(boolean leftButton, int selectedID) {
	    // Die ausgewählte Insel abrufen
	    setSelectedID(selectedID);
	    Island selectedIsland = islandList.get(getSelectedID());

	    // Die Koordinaten der ausgewählten Insel abrufen
	    int selectedX = selectedIsland.getX();
	    int selectedY = selectedIsland.getY();
	    int centerX = selectedIsland.getCenterX();
	    int centerY = selectedIsland.getCenterY();

	    // Schleife zur Suche nach potenziell verbundenen Inseln in westlicher Richtung
	    for (int i = selectedX - 1; i >= 0; i--) {
	        for (Island island : islandList) {
	            int islandX = island.getX();
	            int islandY = island.getY();
	            int id = island.getId();

	            int secondCenterX = island.getCenterX();
	            int drawLineX = centerX - (delta / 2) - 3;
	            int drawLineX2 = secondCenterX + (delta / 2) + 3;

	            // Überprüfen, ob bereits Brücken vorhanden sind
	            if (!listOfBridge.isEmpty()) {
	                if (checkForAnotherBridge(Directions.WEST, selectedY, 0, i))
	                    return;
	            }

	            // Überprüfen, ob die aktuelle Insel mit der ausgewählten Insel verbunden werden kann
	            if (islandY == selectedY && id != getSelectedID() && islandX == i) {
	                System.out.println("Connected ID: " + id);

	                // Fall: Keine Brücken vorhanden, linke Maustaste wurde gedrückt
	                if (listOfBridge.isEmpty() && leftButton) {
	                    // Neue Brücke erstellen und zur Liste hinzufügen
	                    listOfBridge.add(new CreateBridges(id, islandX, islandY, getSelectedID(), selectedX, selectedY, 1, null));
	                    
	                    updateBridges();
	                    
	                    decrementBridgeCounter(id, getSelectedID(), islandList);

	                    // Brücke zeichnen
	                    controller.drawBridge(drawLineX, centerY, drawLineX2, centerY, getSelectedID(), id);

	                    return;
	                } else {
	                    // Überprüfen, ob bereits eine Brücke zwischen den Inseln existiert
	                    if (connectEastOrWestIsland(id, drawLineX, drawLineX2, leftButton, centerY, Directions.WEST))
	                        return;

	                    if (leftButton) {
	                        // Neue Brücke erstellen und zur Liste hinzufügen
	                        listOfBridge.add(new CreateBridges(id, islandX, islandY, getSelectedID(), selectedX, selectedY, 1, null));
	                        controller.drawBridge(drawLineX, centerY, drawLineX2, centerY, getSelectedID(), id);
	                        decrementBridgeCounter(id, getSelectedID(), islandList);
	                        
	                        updateBridges();
	                        
	                        return;
	                    }
	                }
	                return;
	            }
	        }
	    }
	}

	private void updateBridges() {
		controller.setListOfBridge(listOfBridge);
	}

    /**
     * Erzeugt eine Brücke in nördlicher Richtung von der ausgewählten Insel.
     *
     * @param leftButton Ein boolescher Wert, der angibt, ob die linke Maustaste gedrückt wurde.
     */
    public void createNorthBridge( boolean leftButton, int selectedID) {
    	setSelectedID(selectedID);
        // Die ausgewählte Insel abrufen
        Island selectedIsland = islandList.get(getSelectedID());

        // Die Koordinaten der ausgewählten Insel abrufen
        int selectedX = selectedIsland.getX();
        int selectedY = selectedIsland.getY();
        int centerX = selectedIsland.getCenterX();
        int centerY = selectedIsland.getCenterY();

        // Schleife zur Suche nach potenziell verbundenen Inseln in nördlicher Richtung
        for (int i = selectedY - 1; i > -1; i--) {
            for (Island island : islandList) {
                int islandX = island.getX();
                int islandY = island.getY();
                int id = island.getId();

                int secondCenterY = island.getCenterY();
                int drawLineY = centerY - (delta / 2) - 3;
                int drawLineY2 = secondCenterY + (delta / 2) + 3;

                // Überprüfen, ob bereits Brücken vorhanden sind und die linke Maustaste gedrückt wurde
                if (!listOfBridge.isEmpty() && leftButton) {
                    if (checkForAnotherBridge(Directions.NORTH, 0, selectedX, i))
                        return;
                }

                // Überprüfen, ob die aktuelle Insel mit der ausgewählten Insel verbunden werden kann
                if (islandX == selectedX && id != getSelectedID() && islandY == i) {
                    System.out.println("Connected ID: " + id);

                    // Fall: Keine Brücken vorhanden, linke Maustaste wurde gedrückt
                    if (listOfBridge.isEmpty() && leftButton) {
                        // Neue Brücke erstellen und zur Liste hinzufügen
                        listOfBridge.add(new CreateBridges(id, islandX, islandY, getSelectedID(), selectedX, selectedY, 1, null));
                       
                        updateBridges();
                        
                        decrementBridgeCounter(id, getSelectedID(), islandList);

                        // Brücke zeichnen
                        controller.drawBridge(centerX, drawLineY, centerX, drawLineY2, getSelectedID(), id);
                    } else {
                    	 // Überprüfen, ob bereits eine Brücke zwischen den Inseln existiert
                    	if(connectNorthOrSouthIsland(id, drawLineY, drawLineY2, centerX, leftButton, Directions.NORTH))
                    		return;

                        if (leftButton) {
                            // Neue Brücke erstellen und zur Liste hinzufügen
                            listOfBridge.add(new CreateBridges(id, islandX, islandY, getSelectedID(), selectedX, selectedY, 1, null));
                           
                            updateBridges();
                            
                            decrementBridgeCounter(id, getSelectedID(), islandList);
                            // Brücke zeichnen
                            controller.drawBridge(centerX, drawLineY, centerX, drawLineY2, getSelectedID(), id);
                        }
                    }
                    return;
                }
            }
        }
    }



    /**
     * Erzeugt eine Brücke in östlicher Richtung von der ausgewählten Insel.
     *
     * @param leftButton Ein boolescher Wert, der angibt, ob die linke Maustaste gedrückt wurde.
     */
    public void createEastBridge(boolean leftButton, int selectedID) {
    	setSelectedID(selectedID);
        // Die ausgewählte Insel abrufen
        Island selectedIsland = islandList.get(getSelectedID());

        // Die Koordinaten der ausgewählten Insel abrufen
        int selectedX = selectedIsland.getX();
        int selectedY = selectedIsland.getY();
        int centerX = selectedIsland.getCenterX();
        int centerY = selectedIsland.getCenterY();

        // Schleife zur Suche nach potenziell verbundenen Inseln in östlicher Richtung
        for (int i = selectedX + 1; i < getWidth() + 1; i++) {
            for (Island island : islandList) {
                int islandX = island.getX();
                int islandY = island.getY();
                int id = island.getId();

                int secondCenterX = island.getCenterX();

                int drawLineX = centerX + (delta / 2) + 3;
                int drawLineX2 = secondCenterX - (delta / 2) - 3;
                
                

                // Überprüfen, ob bereits Brücken vorhanden sind
                if (!listOfBridge.isEmpty()) {
                    if (checkForAnotherBridge(Directions.EAST, selectedY, 0, i))
                        return;
                }

                // Überprüfen, ob die aktuelle Insel mit der ausgewählten Insel verbunden werden kann
                if (islandY == selectedY && id != getSelectedID() && islandX == i) {
                    System.out.println("Connected ID: " + id);

                    // Fall: Keine Brücken vorhanden, linke Maustaste wurde gedrückt
                    if (listOfBridge.isEmpty() && leftButton) {
                        // Neue Brücke erstellen und zur Liste hinzufügen
                        listOfBridge.add(new CreateBridges(getSelectedID(), selectedX, selectedY, id, islandX, islandY, 1, null));
                       
                        updateBridges();
                        
                        decrementBridgeCounter(id, getSelectedID(), islandList);

                        // Brücke zeichnen
                        controller.drawBridge(drawLineX, centerY, drawLineX2, centerY, getSelectedID(), id);
                    } else {
                    	 // Überprüfen, ob bereits eine Brücke zwischen den Inseln existiert
                    	if(connectEastOrWestIsland(id, drawLineX, drawLineX2, leftButton, centerY, Directions.EAST))
                    		return;

                        if (leftButton) {
                            // Neue Brücke erstellen und zur Liste hinzufügen
                            listOfBridge.add(new CreateBridges(getSelectedID(), selectedX, selectedY, id, islandX, islandY, 1, null));
                           
                            updateBridges();
                            
                            decrementBridgeCounter(id, getSelectedID(), islandList);

                            // Brücke zeichnen
                            controller.drawBridge(drawLineX, centerY, drawLineX2, centerY, getSelectedID(), id);
                        }
                    }
                    return;
                }
            }
        }
    }





    /**
     * Diese Methode erstellt eine Brücke in südlicher Richtung von der ausgewählten Insel.
     *
     * @param leftButton Gibt an, ob die linke Maustaste gedrückt ist.
     */
    public void createSouthBridge(boolean leftButton, int selectedID) {
    	setSelectedID(selectedID);
        // Holen Sie sich die ausgewählte Insel aus der Liste der Inseln.
        Island selectedIsland = islandList.get(getSelectedID());

        // Extrahieren Sie die Koordinaten und das Zentrum der ausgewählten Insel.
        int selectedX = selectedIsland.getX();
        int selectedY = selectedIsland.getY();
        int centerX = selectedIsland.getCenterX();
        int centerY = selectedIsland.getCenterY();

        // Schleife über die y-Koordinaten der Inseln unterhalb der ausgewählten Insel.
        for (int i = selectedY + 1; i < getHeight() + 1; i++) {
            // Schleife über alle Inseln in der Insel-Liste.
            for (Island island : islandList) {
                int islandX = island.getX();
                int islandY = island.getY();
                int id = island.getId();

                // Berechnen Sie die y-Koordinaten der Zeichenlinien für die Brücke.
                int secondCenterY = island.getCenterY();
                int drawLineY = centerY + (delta / 2) + 3;
                int drawLineY2 = secondCenterY - (delta / 2) - 3;

                // Überprüfen Sie, ob bereits Brücken vorhanden sind und ob die linke Maustaste gedrückt wurde.
                if (!listOfBridge.isEmpty() && leftButton) {
                    if (checkForAnotherBridge(Directions.SOUTH, 0, selectedX, i))
                        return;
                }

                // Überprüfen Sie, ob die aktuelle Insel auf derselben x-Koordinate wie die ausgewählte Insel liegt
                // und ob sie sich genau unterhalb der ausgewählten Insel befindet.
                if (islandX == selectedX && id != getSelectedID() && islandY == i) {
                    System.out.println("Connected ID: " + id);

                    // Wenn die Liste der Brücken leer ist und die linke Maustaste gedrückt wurde, fügen Sie eine neue Brücke hinzu.
                    if (listOfBridge.isEmpty() && leftButton) {
                        listOfBridge.add(new CreateBridges(getSelectedID(), selectedX, selectedY, id, islandX, islandY, 1, null));
                        
                        updateBridges();
                        
                        decrementBridgeCounter(id, getSelectedID(), islandList);
                        controller.drawBridge(centerX, drawLineY, centerX, drawLineY2, getSelectedID(), id);
                    } else {
                    	 // Überprüfen, ob bereits eine Brücke zwischen den Inseln existiert
                    	if(connectNorthOrSouthIsland(id, drawLineY, drawLineY2, centerX, leftButton, Directions.SOUTH))
                    		return;

                        // Wenn die Schleife bis hierhin gelangt, bedeutet dies, dass keine Brücke zwischen diesen Inseln existiert.
                        if (leftButton) {
                            // Fügen Sie eine neue Brücke hinzu, verringern Sie den Insel-Brückenzähler, und zeichnen Sie die Brücke.
                            listOfBridge.add(new CreateBridges(getSelectedID(), selectedX, selectedY, id, islandX, islandY, 1, null));

                            updateBridges();
                            
                            decrementBridgeCounter(id, getSelectedID(), islandList);
                            controller.drawBridge(centerX, drawLineY, centerX, drawLineY2, getSelectedID(), id);
                            return;
                        }
                    }
                    return;
                }
            }
        }
    }
    
    
    /**
     * Verbindet Inseln in nördlicher oder südlicher Richtung und verwaltet Brücken.
     *
     * @param id Die ID der zu verbindenden Insel.
     * @param drawLineY Die Y-Koordinate für das Zeichnen der Brücke.
     * @param drawLineY2 Die zweite Y-Koordinate für das Zeichnen der Brücke (bei doppelter Brücke).
     * @param centerX Die X-Koordinate der Inseln (für die Brücke).
     * @param leftButton true, wenn der linke Mausknopf gedrückt wurde, sonst false.
     * @param direction Die Richtung der Verbindung (NORTH oder SOUTH).
     * @return true, wenn die Verbindung erfolgreich hergestellt oder entfernt wurde, sonst false.
     */
    private boolean connectNorthOrSouthIsland(int id, int drawLineY, int drawLineY2, int centerX, boolean leftButton, Directions direction) {
        for (CreateBridges bridge : listOfBridge) {
            int firstID = bridge.getFirstIslandID();
            int secondID = bridge.getSecondIslandID();
            
            // Überprüfen, ob die Brücke die zu verbindenden Inseln enthält
            if ((firstID == id && secondID == getSelectedID()) || (firstID == getSelectedID() && secondID == id)) {
                if (!leftButton && bridge.getNumberOfBridges() == 2) {
                    // Doppelte Brücke entfernen, Brückenzähler aktualisieren und zurückkehren
                    controller.removeDoubleBridge(centerX, drawLineY, centerX, drawLineY2, getSelectedID(), id, direction);
                    updateRemoveCounter(bridge, getSelectedID(), id, islandList);
                    return true;
                } else if (!leftButton && bridge.getNumberOfBridges() == 1) {
                    // Einzelne Brücke entfernen, Brückenzähler aktualisieren und Brücke aus der Liste entfernen
                    controller.removeSingleBridge(centerX, drawLineY, centerX, drawLineY2, getSelectedID(), id);
                    updateRemoveCounter(bridge, getSelectedID(), id, islandList);
                    listOfBridge.remove(bridge);
                    
                    updateBridges();
                    
                    return true;
                } else {
                    // Brückenzähler aktualisieren, Insel-Brückenzähler verringern und doppelte Brücke zeichnen
                    if (bridge.getNumberOfBridges() == 2) {
                        System.out.println("Zu viele Brücken");
                        return true;
                    }
                    updateBridgeCounter(bridge);
                    decrementBridgeCounter(id, getSelectedID(), islandList);
                    controller.drawDoubleBridge(centerX, drawLineY, centerX, drawLineY2, direction, getSelectedID(), id);
                    return true;
                }
            }
        }
        return false;
    }

    
    /**
     * Verbindet Inseln in östlicher oder westlicher Richtung und verwaltet Brücken.
     *
     * @param id Die ID der zu verbindenden Insel.
     * @param drawLineX Die X-Koordinate für das Zeichnen der Brücke.
     * @param drawLineX2 Die zweite X-Koordinate für das Zeichnen der Brücke (bei doppelter Brücke).
     * @param leftButton true, wenn der linke Mausknopf gedrückt wurde, sonst false.
     * @param centerY Die Y-Koordinate der Inseln (für die Brücke).
     * @param direction Die Richtung der Verbindung (EAST oder WEST).
     * @return true, wenn die Verbindung erfolgreich hergestellt oder entfernt wurde, sonst false.
     */
    private boolean connectEastOrWestIsland(int id, int drawLineX, int drawLineX2, boolean leftButton, int centerY, Directions direction) {
        for (CreateBridges bridge : listOfBridge) {
            int firstID = bridge.getFirstIslandID();
            int secondID = bridge.getSecondIslandID();
            
            // Überprüfe, ob die Brücke die zu verbindenden Inseln enthält
            if ((firstID == id && secondID == getSelectedID()) || (firstID == getSelectedID() && secondID == id)) {
                if (!leftButton && bridge.getNumberOfBridges() == 2) {
                    // Doppelte Brücke entfernen und Zähler aktualisieren
                    controller.removeDoubleBridge(drawLineX, centerY, drawLineX2, centerY, getSelectedID(), id, direction);
                    updateRemoveCounter(bridge, getSelectedID(), id, islandList);
                    return true;
                } else if (!leftButton && bridge.getNumberOfBridges() == 1) {
                    // Einzelne Brücke entfernen und Zähler aktualisieren
                    controller.removeSingleBridge(drawLineX, centerY, drawLineX2, centerY, getSelectedID(), id);
                    updateRemoveCounter(bridge, getSelectedID(), id, islandList);
                    listOfBridge.remove(bridge);
                    
                    updateBridges();
                    
                    return true;
                } else {
                    // Brückenzähler aktualisieren und doppelte Brücke zeichnen
                    if (bridge.getNumberOfBridges() == 2) {
                        System.out.println("Zu viele Brücken");
                        return true;
                    }
                    updateBridgeCounter(bridge);
                    decrementBridgeCounter(id, getSelectedID(), islandList);
                    controller.drawDoubleBridge(drawLineX, centerY, drawLineX2, centerY, direction, getSelectedID(), id);
                    return true;
                }
            }
        }
        return false;
    }


    

   
    /**
     * Verringert den Brückenzähler für die beiden Inseln und aktualisiert deren Farben entsprechend ihrem Brückenzustand.
     *
     * @param firstID      Die eindeutige ID der ersten Insel.
     * @param secondID     Die eindeutige ID der zweiten Insel.
     * @param islandList   Die Liste der Inseln, die die aktuellen Brückenzähler enthält.
     */
    private void decrementBridgeCounter(int firstID, int secondID, ArrayList<Island> islandList) {
        // Ermitteln der aktuellen Brückenzähler für beide Inseln
        int firstIslandBridgeCount = islandList.get(firstID).getBridgeCount();
        int secondIslandBridgeCount = islandList.get(secondID).getBridgeCount();
        
        if(!loaded) {
        // Verringern der Brückenzähler für beide Inseln um 1
        islandList.get(firstID).setBridgeCount(firstIslandBridgeCount - 1);
        islandList.get(secondID).setBridgeCount(secondIslandBridgeCount - 1);
        }
        
     // Aktualisiere die Farben der Inseln
       //updateIslandColors(islandList, firstID);
        //updateIslandColors(islandList, secondID);
        
        // Überprüfen, ob der Brückenzähler für die erste Insel auf 0 gefallen ist
        if (islandList.get(firstID).getBridgeCount() == 0) {
            // Wenn ja, die Insel grün färben, da alle Brücken entfernt wurden
            controller.fillIslandGreen(islandList.get(firstID));
        }
        
        // Überprüfen, ob der Brückenzähler für die zweite Insel auf 0 gefallen ist
        if (islandList.get(secondID).getBridgeCount() == 0) {
            // Wenn ja, die Insel grün färben, da alle Brücken entfernt wurden
            controller.fillIslandGreen(islandList.get(secondID));
        } 
        
        // Überprüfen, ob der Brückenzähler für die erste Insel negativ geworden ist
        if (islandList.get(firstID).getBridgeCount() < 0) {
            // Wenn ja, die Insel rot färben, da zu viele Brücken entfernt wurden
            controller.fillIslandRed(islandList.get(firstID));
        }
        
        // Überprüfen, ob der Brückenzähler für die zweite Insel negativ geworden ist
        if (islandList.get(secondID).getBridgeCount() < 0) {
            // Wenn ja, die Insel rot färben, da zu viele Brücken entfernt wurden
            controller.fillIslandRed(islandList.get(secondID));
        }  	
    }


    
    /**
     * Aktualisiert die Brückenzähler und die Farben der betroffenen Inseln nach Entfernen einer Brücke.
     *
     * @param bridge         Die Brücke, die entfernt wurde.
     * @param firstID        Die eindeutige ID der ersten Insel.
     * @param secondID       Die eindeutige ID der zweiten Insel.
     * @param islandList     Die Liste der Inseln, die die aktuellen Brückenzähler enthält.
     */
    private void updateRemoveCounter(CreateBridges bridge, int firstID, int secondID, ArrayList<Island> islandList) {
        // Ermitteln der aktuellen Brückenzähler für beide Inseln
        int firstIslandBridgeCount = islandList.get(firstID).getBridgeCount();
        int secondIslandBridgeCount = islandList.get(secondID).getBridgeCount();

        // Erhöhen der Brückenzähler für beide Inseln um 1
        islandList.get(firstID).setBridgeCount(firstIslandBridgeCount + 1);
        islandList.get(secondID).setBridgeCount(secondIslandBridgeCount + 1);

        // Holen Sie sich den aktuellen Brückenzähler für die Brücke.
        int bridgeCounter = bridge.getNumberOfBridges();

        // Überprüfen, ob der Brückenzähler bereits 2 erreicht hat.
        if (bridgeCounter > 0) {
            bridgeCounter -= 1;
            bridge.setNumberOfBridges(bridgeCounter); // Setze den aktualisierten Zähler zurück.
        } 
        // Wenn der Brückenzähler kleiner als 2 ist, erhöhe ihn um 1.

        // Aktualisiere die Farben der Inseln
        updateIslandColors(islandList, firstID);
        updateIslandColors(islandList, secondID);
      
    }
    
    
    /**
     * Aktualisiert den Brückenzähler und die Farbe einer Insel basierend auf dem Brückenzählerwert.
     *
     * @param islandList     Die Liste der Inseln.
     * @param islandID       Die eindeutige ID der Insel.
     */
    private void updateIslandColors(ArrayList<Island> islandList, int islandID) {
        Island island = islandList.get(islandID);
        int bridgeCount = island.getBridgeCount();

        if (bridgeCount == 0) {
            controller.fillIslandGreen(island);
        } else if (bridgeCount < 0) {
            controller.fillIslandRed(island);
        } else {
            controller.emptyIsland(island);
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
	
	/**
	 * Gibt die Breite des Objekts zurück.
	 *
	 * @return Die Breite des Objekts.
	 */
	public int getWidth() {
	    return width;
	}

	/**
	 * Setzt die Breite des Objekts auf den angegebenen Wert.
	 *
	 * @param width Die neue Breite des Objekts.
	 */
	public void setWidth(int width) {
	    this.width = width;
	}

	/**
	 * Gibt die Höhe des Objekts zurück.
	 *
	 * @return Die Höhe des Objekts.
	 */
	public int getHeight() {
	    return height;
	}

	/**
	 * Setzt die Höhe des Objekts auf den angegebenen Wert.
	 *
	 * @param height Die neue Höhe des Objekts.
	 */
	public void setHeight(int height) {
	    this.height = height;
	}

	/**
	 * Gibt die ausgewählte ID des Objekts zurück.
	 *
	 * @return Die ausgewählte ID des Objekts.
	 */
	public int getSelectedID() {
	    return selectedID;
	}

	/**
	 * Setzt die ausgewählte ID des Objekts auf den angegebenen Wert.
	 *
	 * @param selectedID Die neue ausgewählte ID des Objekts.
	 */
	public void setSelectedID(int selectedID) {
	    this.selectedID = selectedID;
	}

    
    /**
     * Gibt die Liste der Brücken zurück.
     *
     * @return Die Liste der Brücken.
     */
    public ArrayList<CreateBridges> getListOfBridge() {
        return listOfBridge;
    }

    /**
     * Setzt die Liste der Brücken auf die angegebene Liste.
     *
     * @param listOfBridge Die Liste der Brücken, die gesetzt werden soll.
     */
    public void setListOfBridge(ArrayList<CreateBridges> listOfBridge) {
        this.listOfBridge = listOfBridge;
    }


}
