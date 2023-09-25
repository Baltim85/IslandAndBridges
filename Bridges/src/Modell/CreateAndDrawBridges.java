package Modell;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import Controller.BridgeController;
import UI.Bridges;
import Modell.CreateBridges;
import Modell.Directions;
import Modell.GridPainter;
import Modell.Island;

public class CreateAndDrawBridges {

	/**
	 * Die Bridges, die in diesem Controller verwaltet werden.
	 */
	private Bridges bridges;

	private static final int LAMBDA = 3;

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

	    // Ausgabe von Informationen zu den Inseln und ihren Brückenzählern
	    for (Island island : islandList) {
	        System.out.println("Island: " + island.getId() + " counter " + island.getBridgeCount());
	    }

	    this.centerX = centerX;
	    this.centerY = centerY;
	    this.delta = delta;
	    listOfBridge.clear();

	    this.controller = controller;
	    calculateCenterForEachIsland(islandList, centerX, centerY);
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
                        decrementBridgeCounter(id, getSelectedID(), islandList);

                        // Brücke zeichnen
                        controller.drawBridge(drawLineX, centerY, drawLineX2, centerY, getSelectedID(), id);

                        return;
                    } else {
                        // Schleife zur Überprüfung vorhandener Brücken
                        for (CreateBridges bridge : listOfBridge) {
                            int firstID = bridge.getFirstIslandID();
                            int secondID = bridge.getSecondIslandID();
                            if ((firstID == id && secondID == getSelectedID()) || (firstID == getSelectedID() && secondID == id)) {
                                if (!leftButton && bridge.getNumberOfBridges() == 2) {
                                    // Doppelte Brücke entfernen und Zähler aktualisieren
                                	controller.removeDoubleBridge(drawLineX, centerY, drawLineX2, centerY, getSelectedID(), id, Directions.WEST);
                                    updateRemoveCounter(bridge, getSelectedID(), id, islandList);
                                    return;
                                } else if (!leftButton && bridge.getNumberOfBridges() == 1) {
                                    // Einzelne Brücke entfernen und Zähler aktualisieren
                                	controller.removeSingleBridge(drawLineX, centerY, drawLineX2, centerY, getSelectedID(), id);
                                    updateRemoveCounter(bridge, getSelectedID(), id, islandList);
                                    listOfBridge.remove(bridge);
                                    return;
                                } else {
                                	if (bridge.getNumberOfBridges() == 2) {
                                		System.out.println("Zu viele Brücken");
                                		return;
                                	}
                                    // Brückenzähler aktualisieren und doppelte Brücke zeichnen
                                    updateBridgeCounter(bridge);
                                    controller.drawDoubleBridge(drawLineX, centerY, drawLineX2, centerY, Directions.WEST, getSelectedID(), id);
                                    decrementBridgeCounter(id, getSelectedID(), islandList);
                                    return;
                                }
                            }
                        }

                        if (leftButton) {
                            // Neue Brücke erstellen und zur Liste hinzufügen
                            listOfBridge.add(new CreateBridges(id, islandX, islandY, getSelectedID(), selectedX, selectedY, 1, null));
                            controller.drawBridge(drawLineX, centerY, drawLineX2, centerY, getSelectedID(), id);
                            decrementBridgeCounter(id, getSelectedID(), islandList);
                            return;
                        }
                    }
                    return;
                }
            }
        }
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
                        decrementBridgeCounter(id, getSelectedID(), islandList);

                        // Brücke zeichnen
                        controller.drawBridge(centerX, drawLineY, centerX, drawLineY2, getSelectedID(), id);
                    } else {
                        // Schleife zur Überprüfung vorhandener Brücken
                        for (CreateBridges bridge : listOfBridge) {
                            int firstID = bridge.getFirstIslandID();
                            int secondID = bridge.getSecondIslandID();
                            if ((firstID == id && secondID == getSelectedID()) || (firstID == getSelectedID() && secondID == id)) {
                                if (!leftButton && bridge.getNumberOfBridges() == 2) {
                                    // Doppelte Brücke entfernen und Zähler aktualisieren
                                	controller.removeDoubleBridge(centerX, drawLineY, centerX, drawLineY2, getSelectedID(), id, Directions.NORTH);
                                    updateRemoveCounter(bridge, getSelectedID(), id, islandList);
                                    return;
                                } else if (!leftButton && bridge.getNumberOfBridges() == 1) {
                                    // Einzelne Brücke entfernen und Zähler aktualisieren
                                	controller.removeSingleBridge(centerX, drawLineY, centerX, drawLineY2, getSelectedID(), id);
                                    updateRemoveCounter(bridge, getSelectedID(), id, islandList);
                                    listOfBridge.remove(bridge);
                                    return;
                                } else {
                                	if (bridge.getNumberOfBridges() == 2) {
                                		System.out.println("Zu viele Brücken");
                                		return;
                                	}
                                    // Brückenzähler aktualisieren und doppelte Brücke zeichnen
                                    updateBridgeCounter(bridge);
                                    decrementBridgeCounter(id, getSelectedID(), islandList);
                                    controller.drawDoubleBridge(centerX, drawLineY, centerX, drawLineY2, Directions.NORTH, getSelectedID(), id);
                                    return;
                                }
                            }
                        }

                        if (leftButton) {
                            // Neue Brücke erstellen und zur Liste hinzufügen
                            listOfBridge.add(new CreateBridges(id, islandX, islandY, getSelectedID(), selectedX, selectedY, 1, null));
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
                        decrementBridgeCounter(id, getSelectedID(), islandList);

                        // Brücke zeichnen
                        controller.drawBridge(drawLineX, centerY, drawLineX2, centerY, getSelectedID(), id);
                    } else {
                        // Schleife zur Überprüfung vorhandener Brücken
                        for (CreateBridges bridge : listOfBridge) {
                            int firstID = bridge.getFirstIslandID();
                            int secondID = bridge.getSecondIslandID();
                            if ((firstID == id && secondID == getSelectedID()) || (firstID == getSelectedID() && secondID == id)) {
                                if (!leftButton && bridge.getNumberOfBridges() == 2) {
                                    // Doppelte Brücke entfernen und Zähler aktualisieren
                                	controller.removeDoubleBridge(drawLineX, centerY, drawLineX2, centerY, getSelectedID(), id, Directions.EAST);
                                    updateRemoveCounter(bridge, getSelectedID(), id, islandList);
                                    return;
                                } else if (!leftButton && bridge.getNumberOfBridges() == 1) {
                                    // Einzelne Brücke entfernen und Zähler aktualisieren
                                	controller.removeSingleBridge(drawLineX, centerY, drawLineX2, centerY, getSelectedID(), id);
                                    updateRemoveCounter(bridge, getSelectedID(), id, islandList);
                                    listOfBridge.remove(bridge);
                                    return;
                                } else {
                                    // Brückenzähler aktualisieren und doppelte Brücke zeichnen
                                	if (bridge.getNumberOfBridges() == 2) {
                                		System.out.println("Zu viele Brücken");
                                		return;
                                	}
                                    updateBridgeCounter(bridge);
                                    decrementBridgeCounter(id, getSelectedID(), islandList);
                                    controller. drawDoubleBridge(drawLineX, centerY, drawLineX2, centerY, Directions.EAST, getSelectedID(), id);
                                    return;
                                }
                            }
                        }

                        if (leftButton) {
                            // Neue Brücke erstellen und zur Liste hinzufügen
                            listOfBridge.add(new CreateBridges(getSelectedID(), selectedX, selectedY, id, islandX, islandY, 1, null));
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
                        decrementBridgeCounter(id, getSelectedID(), islandList);
                        controller.drawBridge(centerX, drawLineY, centerX, drawLineY2, getSelectedID(), id);
                    } else {
                        // Durchlaufen Sie die Liste der Brücken und überprüfen Sie, ob bereits eine Brücke zwischen diesen Inseln existiert.
                        for (CreateBridges bridge : listOfBridge) {
                            int firstID = bridge.getFirstIslandID();
                            int secondID = bridge.getSecondIslandID();

                            // Überprüfen Sie, ob die Brücke zwischen den aktuellen Inseln existiert.
                            if (firstID == id && secondID == getSelectedID() || firstID == getSelectedID() && secondID == id) {
                                if (!leftButton && bridge.getNumberOfBridges() == 2) {
                                    // Entfernen Sie die doppelte Brücke, aktualisieren Sie den Brückenzähler und kehren Sie zurück.
                                	controller.removeDoubleBridge(centerX, drawLineY, centerX, drawLineY2, getSelectedID(), id, Directions.SOUTH);
                                    updateRemoveCounter(bridge, getSelectedID(), id, islandList);
                                    return;
                                }
                                if (!leftButton && bridge.getNumberOfBridges() == 1) {
                                    // Entfernen Sie die einzelne Brücke, aktualisieren Sie den Brückenzähler und entfernen Sie die Brücke aus der Liste.
                                	controller.removeSingleBridge(centerX, drawLineY, centerX, drawLineY2, getSelectedID(), id);
                                    updateRemoveCounter(bridge, getSelectedID(), id, islandList);
                                    listOfBridge.remove(bridge);
                                    return;
                                } else {
                                    // Aktualisieren Sie den Brückenzähler, verringern Sie den Insel-Brückenzähler, und zeichnen Sie eine doppelte Brücke.
                                	if (bridge.getNumberOfBridges() == 2) {
                                		System.out.println("Zu viele Brücken");
                                		return;
                                	}
                                	updateBridgeCounter(bridge);
                                    decrementBridgeCounter(id, getSelectedID(), islandList);
                                    controller.drawDoubleBridge(centerX, drawLineY, centerX, drawLineY2, Directions.SOUTH, getSelectedID(), id);
                                    return;
                                }
                            }
                        }

                        // Wenn die Schleife bis hierhin gelangt, bedeutet dies, dass keine Brücke zwischen diesen Inseln existiert.
                        if (leftButton) {
                            // Fügen Sie eine neue Brücke hinzu, verringern Sie den Insel-Brückenzähler, und zeichnen Sie die Brücke.
                            listOfBridge.add(new CreateBridges(getSelectedID(), selectedX, selectedY, id, islandX, islandY, 1, null));
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
        
        // Verringern der Brückenzähler für beide Inseln um 1
        islandList.get(firstID).setBridgeCount(firstIslandBridgeCount - 1);
        islandList.get(secondID).setBridgeCount(secondIslandBridgeCount - 1);
        
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
    
    
    public ArrayList<CreateBridges> getListOfBridge() {
		return listOfBridge;
	}



	public void setListOfBridge(ArrayList<CreateBridges> listOfBridge) {
		this.listOfBridge = listOfBridge;
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


}
