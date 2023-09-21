package Controller;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import UI.Bridges;
import Modell.CreateAndDrawBridges;
import Modell.CreateBridges;
import Modell.Directions;
import Modell.GridPainter;
import Modell.Island;

public class BridgeController implements MouseListener {

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
	
	
	private GridPainter painter;
	
	private CreateAndDrawBridges createBridges;
	
	/**
	 * Konstruktor für die BridgeController-Klasse.
	 * Dieser Konstruktor wird verwendet, um eine Instanz der Klasse zu erstellen.
	 */
	public BridgeController() {
	    // Hier können Initialisierungen oder Setup-Code platziert werden.
	}

	
	/**
	 * Initialisiert den BridgeController mit den angegebenen Parametern und setzt die zugehörigen Felder.
	 *
	 * @param islandList Eine Liste von Inseln, die von diesem Controller verwaltet werden.
	 * @param bridges Die Bridges, die in diesem Controller verwaltet werden.
	 * @param centerX Die X-Koordinate des Zentrums.
	 * @param centerY Die Y-Koordinate des Zentrums.
	 * @param delta Der Abstand (delta) zwischen den Elementen.
	 * @param width Die Breite des Objekts.
	 * @param height Die Höhe des Objekts.
	 */
	public void initController(ArrayList<Island> islandList, Bridges bridges, int centerX, int centerY
			, int delta, int width, int height, GridPainter painter) {
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
	    this.painter = painter;
	    createBridges = new CreateAndDrawBridges(islandList, bridges, centerX, centerY, delta, width, height, this);
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
	 * Diese Methode zeichnet eine Brücke zwischen zwei Punkten auf der Zeichenfläche.
	 *
	 * @param x        Die x-Koordinate des Startpunkts der Brücke.
	 * @param y        Die y-Koordinate des Startpunkts der Brücke.
	 * @param x2       Die x-Koordinate des Endpunkts der Brücke.
	 * @param y2       Die y-Koordinate des Endpunkts der Brücke.
	 * @param firstID  Die ID der ersten Insel, die miteinander verbunden ist.
	 * @param secondID Die ID der zweiten Insel, die miteinander verbunden ist.
	 */
	public void drawBridge(int x, int y, int x2, int y2, int firstID, int secondID) {
	    // Holen Sie sich die Grafik-Objekt zum Zeichnen auf der Zeichenfläche.
	    Graphics g = bridges.getDraw().getGraphics();
	    
	    // Zeichnen Sie eine Linie zwischen den angegebenen Koordinaten.
	    g.drawLine(x, y, x2, y2);
	    
	    // Aktualisieren Sie die Zeichenfläche, um die gezeichnete Brücke anzuzeigen.
	    bridges.getDraw().repaint();
	    
	    // Fügen Sie die Informationen zur gezeichneten Brücke in die Liste der Brücken ein.
	    painter.getBridges().add(new CreateBridges(firstID, x, y, secondID, x2, y2));
	}
	
    
	/**
	 * Diese Methode entfernt eine Brücke zwischen zwei Inseln aus der Liste der gezeichneten Brücken.
	 *
	 * @param firstID  Die ID der ersten Insel, die miteinander verbunden ist.
	 * @param secondID Die ID der zweiten Insel, die miteinander verbunden ist.
	 */
	public void removeIsland(int firstID, int secondID) {
	    if (painter.getBridges().isEmpty()) {
	        // Wenn die Liste der gezeichneten Brücken leer ist, gibt es nichts zu entfernen.
	        return;
	    }
	    
	    // Durchlaufen Sie die Liste der gezeichneten Brücken.
	    for (CreateBridges drawBridges : painter.getBridges()) {
	        int first_ID = drawBridges.getFirstIslandID();
	        int second_ID = drawBridges.getSecondIslandID();
	        
	        // Überprüfen Sie, ob die Brücke zwischen den angegebenen Inseln existiert.
	        if ((first_ID == firstID && second_ID == secondID) || (first_ID == secondID && second_ID == firstID)) {
	            // Entfernen Sie die Brücke aus der Liste der gezeichneten Brücken.
	            painter.getBridges().remove(drawBridges);
	            return; // Beenden Sie die Schleife, da die Brücke gefunden und entfernt wurde.
	        }
	    }
	}

    
    /*private void drawDoubleBridge(int x, int y, int x2, int y2, Directions direction, int firstID, int secondID) {
    	Graphics g = bridges.getDraw().getGraphics();
    	removeIsland(firstID, secondID);
    	
    	if(direction == Directions.EAST || direction == Directions.WEST) {
    		g.setColor(Color.WHITE);
    		g.drawLine(x, y, x2, y2);
    		g.setColor(Color.BLACK);
    		g.drawLine(x, y+LAMBDA, x2, y2+LAMBDA);
    		g.drawLine(x, y-LAMBDA, x2, y2-LAMBDA);
    		painter.getBridges().add(new CreateBridges(firstID, x, y+LAMBDA, secondID, x2, y2+LAMBDA));
    		painter.getBridges().add(new CreateBridges(firstID, x, y-LAMBDA, secondID, x2, y2-LAMBDA));		
    	} 
    	
    	if(direction == Directions.NORTH || direction == Directions.SOUTH) {
    		g.setColor(Color.WHITE);
    		g.drawLine(x, y, x2, y2);
    		g.setColor(Color.BLACK);
    		g.drawLine(x+LAMBDA, y, x2+LAMBDA, y2);
    		g.drawLine(x-LAMBDA, y, x2-LAMBDA, y2);
    		
    		painter.getBridges().add(new CreateBridges(firstID, x+LAMBDA, y, secondID, x2+LAMBDA, y2));
    		painter.getBridges().add(new CreateBridges(firstID, x-LAMBDA, y, secondID, x2-LAMBDA, y2));
    		
    	}
    	bridges.getDraw().repaint();
    	
    }*/

    /**
     * Zeichnet eine Doppelbrücke zwischen zwei Inseln in der angegebenen Richtung.
     *
     * @param x         Die x-Koordinate des ersten Brückenendpunkts.
     * @param y         Die y-Koordinate des ersten Brückenendpunkts.
     * @param x2        Die x-Koordinate des zweiten Brückenendpunkts.
     * @param y2        Die y-Koordinate des zweiten Brückenendpunkts.
     * @param direction Die Richtung der Brücke (Directions.EAST, Directions.WEST, Directions.NORTH oder Directions.SOUTH).
     * @param firstID   Die ID der ersten Insel.
     * @param secondID  Die ID der zweiten Insel.
     */
    public void drawDoubleBridge(int x, int y, int x2, int y2, Directions direction, int firstID, int secondID) {
        Graphics g = bridges.getDraw().getGraphics();
        removeIsland(firstID, secondID);

        g.setColor(Color.WHITE);
        g.drawLine(x, y, x2, y2);
        bridges.getDraw().repaint();

        // Abhängig von der Richtung der Brücke werden die Verschiebungen in x und y berechnet.
        int xOffset = 0;
        int yOffset = 0;

        if (direction == Directions.EAST || direction == Directions.WEST) {
            yOffset = LAMBDA;
        } else if (direction == Directions.NORTH || direction == Directions.SOUTH) {
            xOffset = LAMBDA;
        }

        g.setColor(Color.BLACK);
        g.drawLine(x + xOffset, y + yOffset, x2 + xOffset, y2 + yOffset);
        g.drawLine(x - xOffset, y - yOffset, x2 - xOffset, y2 - yOffset);

        painter.getBridges().add(new CreateBridges(firstID, x + xOffset, y + yOffset, secondID, x2 + xOffset, y2 + yOffset));
        painter.getBridges().add(new CreateBridges(firstID, x - xOffset, y - yOffset, secondID, x2 - xOffset, y2 - yOffset));
        bridges.getDraw().repaint();
    }
    
    
    /**
     * Entfernt eine einzelne Brücke zwischen zwei Inseln, indem sie in Weiß gezeichnet wird, um sie unsichtbar zu machen.
     *
     * @param x        Die x-Koordinate des ersten Brückenendpunkts.
     * @param y        Die y-Koordinate des ersten Brückenendpunkts.
     * @param x2       Die x-Koordinate des zweiten Brückenendpunkts.
     * @param y2       Die y-Koordinate des zweiten Brückenendpunkts.
     * @param firstID  Die ID der ersten Insel.
     * @param secondID Die ID der zweiten Insel.
     */
    public void removeSingleBridge(int x, int y, int x2, int y2, int firstID, int secondID) {
        Graphics g = bridges.getDraw().getGraphics();
        
        // Entfernen Sie die Verbindung zwischen den Inseln aus der Liste.
        removeIsland(firstID, secondID);
        
        // Zeichnen Sie die Brücke in Weiß, um sie unsichtbar zu machen.
        g.setColor(Color.WHITE);
        g.drawLine(x, y, x2, y2);
        
        // Aktualisieren Sie die Anzeige.
        bridges.getDraw().repaint();
    }
    
    /*private void removeDoubleBridge(int x, int y, int x2, int y2, int firstID, int secondID, Directions direction) {
    	Graphics g = bridges.getDraw().getGraphics();
    	if(direction == Directions.EAST || direction == Directions.WEST) {
    		removeIsland(firstID, secondID);
    		removeIsland(firstID, secondID);
    		g.setColor(Color.WHITE);
    		g.drawLine(x, y+LAMBDA, x2, y2+LAMBDA);
    		g.drawLine(x, y-LAMBDA, x2, y2-LAMBDA);
    		//g.setColor(Color.black);
    		drawBridge(x, y, x2, y2, firstID, secondID);
    		
    	} 
    	
    	if(direction == Directions.NORTH || direction == Directions.SOUTH) {
    		removeIsland(firstID, secondID);
    		removeIsland(firstID, secondID);
    		g.setColor(Color.WHITE);
    		g.drawLine(x+LAMBDA, y, x2+LAMBDA, y2);
    		g.drawLine(x-LAMBDA, y, x2-LAMBDA, y2);
    		//g.setColor(Color.black);
    		drawBridge(x, y, x2, y2, firstID, secondID);
    		
    	} 
    }*/
    
    /**
     * Entfernt eine doppelte Brücke zwischen zwei Inseln, indem sie zuerst die Verbindungen löscht und dann die Brücke neu zeichnet.
     *
     * @param x         Die x-Koordinate des ersten Brückenendpunkts.
     * @param y         Die y-Koordinate des ersten Brückenendpunkts.
     * @param x2        Die x-Koordinate des zweiten Brückenendpunkts.
     * @param y2        Die y-Koordinate des zweiten Brückenendpunkts.
     * @param firstID   Die ID der ersten Insel.
     * @param secondID  Die ID der zweiten Insel.
     * @param direction Die Richtung der Brücke (EAST, WEST, NORTH oder SOUTH).
     */
    public void removeDoubleBridge(int x, int y, int x2, int y2, int firstID, int secondID, Directions direction) {
        Graphics g = bridges.getDraw().getGraphics();
        
        // Entfernen Sie die Verbindung zwischen den Inseln zweimal (für beide Enden der Brücke).
        removeIsland(firstID, secondID);
        removeIsland(firstID, secondID);

        // Zeichnen Sie die Brücke in Weiß, um sie unsichtbar zu machen.
        g.setColor(Color.WHITE);
        
        if (direction == Directions.EAST || direction == Directions.WEST) {
            g.drawLine(x, y + LAMBDA, x2, y2 + LAMBDA);
            g.drawLine(x, y - LAMBDA, x2, y2 - LAMBDA);
        } else if (direction == Directions.NORTH || direction == Directions.SOUTH) {
            g.drawLine(x + LAMBDA, y, x2 + LAMBDA, y2);
            g.drawLine(x - LAMBDA, y, x2 - LAMBDA, y2);
        }

        // Zeichnen Sie die Brücke erneut, um sie anzuzeigen.
        drawBridge(x, y, x2, y2, firstID, secondID);
    }
    
    /**
     * Verarbeitet Mausklick-Ereignisse für das Erstellen von Brücken auf der Inselkarte.
     *
     * @param x Die x-Koordinate des Mausklicks.
     * @param y Die y-Koordinate des Mausklicks.
     * @param e Das MouseEvent-Objekt, das Informationen über das Mausklick-Ereignis enthält.
     */
    public void handleMouseClick(int x, int y, MouseEvent e) {
    	// Methode zur Verarbeitung von Mausklick-Ereignissen
        System.out.println("Maus Klicked: " + x + " y: " + y);
        Directions direction = checkDirection(x, y, islandList, getDelta());
        boolean leftMouse = true;
        if (direction == Directions.EAST) {
            System.out.println("Osten");
            if (e.getButton() == MouseEvent.BUTTON1) { 
                // Linker Mausklick in Richtung Osten
            	createBridges.createEastBridge(leftMouse, getSelectedID());
            } else if (e.getButton() == MouseEvent.BUTTON3) {
                // Rechter Mausklick in Richtung Osten
                System.out.println("Rechts");
                createBridges.createEastBridge(false, getSelectedID());
            }
        }
        if (direction == Directions.WEST) {
            System.out.println("Westen");
            if (e.getButton() == MouseEvent.BUTTON1) { 
                // Linker Mausklick in Richtung Westen
            	createBridges.createWestBridge(leftMouse, getSelectedID());
            } else if (e.getButton() == MouseEvent.BUTTON3) {
                // Rechter Mausklick in Richtung Westen
                System.out.println("Rechts");
                createBridges.createWestBridge(false, getSelectedID());
            }
        }
        if (direction == Directions.SOUTH) {
            System.out.println("Süden");
            if (e.getButton() == MouseEvent.BUTTON1) { 
                // Linker Mausklick in Richtung Süden
            	createBridges.createSouthBridge(leftMouse, getSelectedID());
            } else if (e.getButton() == MouseEvent.BUTTON3) {
                // Rechter Mausklick in Richtung Süden
                System.out.println("Rechts");
                createBridges.createSouthBridge(false, getSelectedID());
            }
        }
        if (direction == Directions.NORTH) {
            System.out.println("Norden");
            if (e.getButton() == MouseEvent.BUTTON1) { 
                // Linker Mausklick in Richtung Norden
            	createBridges.createNorthBridge(leftMouse, getSelectedID());
            } else if (e.getButton() == MouseEvent.BUTTON3) {
                // Rechter Mausklick in Richtung Norden
            	createBridges.createNorthBridge(false, getSelectedID());
                System.out.println("Rechts");
            }
        }
    }


    
    /**
     * Überprüft die Richtung, in die sich der Mauszeiger in Bezug auf die Inseln befindet, und setzt die ausgewählte Insel-ID.
     *
     * @param x         Die X-Koordinate des Mauszeigers.
     * @param y         Die Y-Koordinate des Mauszeigers.
     * @param islandList Eine Liste von Inseln, auf denen geprüft wird.
     * @param delta     Der Wert, der zur Berechnung des Bereichs um die Inseln verwendet wird.
     * @return Die Richtung, in die der Mauszeiger zeigt (EAST, WEST, SOUTH, NORTH) oder null, wenn keine Insel getroffen wurde.
     */
    private Directions checkDirection(int x, int y, ArrayList<Island> islandList, int delta) {
        for (Island island : islandList) {
            int islandX = island.getCenterX();
            int islandY = island.getCenterY();
            int d = delta / 2;
            int xDiff = Math.abs(x - islandX);
            int yDiff = Math.abs(y - islandY);

            if (xDiff < d && yDiff < d) {
                System.out.println("Insel ID: " + island.getId());
                setSelectedID(island.getId());

                if (xDiff > yDiff) {
                    return (x > islandX) ? Directions.EAST : Directions.WEST;
                } else {
                    return (y > islandY) ? Directions.SOUTH : Directions.NORTH;
                }
            }
        }

        // Wenn keine Insel getroffen wurde, gibt null zurück
        return null;
    }
    
    
    /**
     * Erzeugt eine Brücke in westlicher Richtung von der ausgewählten Insel.
     *
     * @param leftButton Ein boolescher Wert, der angibt, ob die linke Maustaste gedrückt wurde.
     */
    public void createWestBridge(boolean leftButton) {
        // Die ausgewählte Insel abrufen
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
                        drawBridge(drawLineX, centerY, drawLineX2, centerY, getSelectedID(), id);

                        return;
                    } else {
                        // Schleife zur Überprüfung vorhandener Brücken
                        for (CreateBridges bridge : listOfBridge) {
                            int firstID = bridge.getFirstIslandID();
                            int secondID = bridge.getSecondIslandID();
                            if ((firstID == id && secondID == getSelectedID()) || (firstID == getSelectedID() && secondID == id)) {
                                if (!leftButton && bridge.getNumberOfBridges() == 2) {
                                    // Doppelte Brücke entfernen und Zähler aktualisieren
                                    removeDoubleBridge(drawLineX, centerY, drawLineX2, centerY, getSelectedID(), id, Directions.WEST);
                                    updateRemoveCounter(bridge, getSelectedID(), id, islandList);
                                    return;
                                } else if (!leftButton && bridge.getNumberOfBridges() == 1) {
                                    // Einzelne Brücke entfernen und Zähler aktualisieren
                                    removeSingleBridge(drawLineX, centerY, drawLineX2, centerY, getSelectedID(), id);
                                    updateRemoveCounter(bridge, getSelectedID(), id, islandList);
                                    listOfBridge.remove(bridge);
                                    return;
                                } else {
                                    // Brückenzähler aktualisieren und doppelte Brücke zeichnen
                                    updateBridgeCounter(bridge);
                                    drawDoubleBridge(drawLineX, centerY, drawLineX2, centerY, Directions.WEST, getSelectedID(), id);
                                    decrementBridgeCounter(id, getSelectedID(), islandList);
                                    return;
                                }
                            }
                        }

                        if (leftButton) {
                            // Neue Brücke erstellen und zur Liste hinzufügen
                            listOfBridge.add(new CreateBridges(id, islandX, islandY, getSelectedID(), selectedX, selectedY, 1, null));
                            drawBridge(drawLineX, centerY, drawLineX2, centerY, getSelectedID(), id);
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
    public void createNorthBridge( boolean leftButton) {
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
                        drawBridge(centerX, drawLineY, centerX, drawLineY2, getSelectedID(), id);
                    } else {
                        // Schleife zur Überprüfung vorhandener Brücken
                        for (CreateBridges bridge : listOfBridge) {
                            int firstID = bridge.getFirstIslandID();
                            int secondID = bridge.getSecondIslandID();
                            if ((firstID == id && secondID == getSelectedID()) || (firstID == getSelectedID() && secondID == id)) {
                                if (!leftButton && bridge.getNumberOfBridges() == 2) {
                                    // Doppelte Brücke entfernen und Zähler aktualisieren
                                    removeDoubleBridge(centerX, drawLineY, centerX, drawLineY2, getSelectedID(), id, Directions.NORTH);
                                    updateRemoveCounter(bridge, getSelectedID(), id, islandList);
                                    return;
                                } else if (!leftButton && bridge.getNumberOfBridges() == 1) {
                                    // Einzelne Brücke entfernen und Zähler aktualisieren
                                    removeSingleBridge(centerX, drawLineY, centerX, drawLineY2, getSelectedID(), id);
                                    updateRemoveCounter(bridge, getSelectedID(), id, islandList);
                                    listOfBridge.remove(bridge);
                                    return;
                                } else {
                                    // Brückenzähler aktualisieren und doppelte Brücke zeichnen
                                    updateBridgeCounter(bridge);
                                    decrementBridgeCounter(id, getSelectedID(), islandList);
                                    drawDoubleBridge(centerX, drawLineY, centerX, drawLineY2, Directions.NORTH, getSelectedID(), id);
                                    return;
                                }
                            }
                        }

                        if (leftButton) {
                            // Neue Brücke erstellen und zur Liste hinzufügen
                            listOfBridge.add(new CreateBridges(id, islandX, islandY, getSelectedID(), selectedX, selectedY, 1, null));
                            decrementBridgeCounter(id, getSelectedID(), islandList);
                            // Brücke zeichnen
                            drawBridge(centerX, drawLineY, centerX, drawLineY2, getSelectedID(), id);
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
    public void createEastBridge(boolean leftButton) {
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
                        drawBridge(drawLineX, centerY, drawLineX2, centerY, getSelectedID(), id);
                    } else {
                        // Schleife zur Überprüfung vorhandener Brücken
                        for (CreateBridges bridge : listOfBridge) {
                            int firstID = bridge.getFirstIslandID();
                            int secondID = bridge.getSecondIslandID();
                            if ((firstID == id && secondID == getSelectedID()) || (firstID == getSelectedID() && secondID == id)) {
                                if (!leftButton && bridge.getNumberOfBridges() == 2) {
                                    // Doppelte Brücke entfernen und Zähler aktualisieren
                                    removeDoubleBridge(drawLineX, centerY, drawLineX2, centerY, getSelectedID(), id, Directions.EAST);
                                    updateRemoveCounter(bridge, getSelectedID(), id, islandList);
                                    return;
                                } else if (!leftButton && bridge.getNumberOfBridges() == 1) {
                                    // Einzelne Brücke entfernen und Zähler aktualisieren
                                    removeSingleBridge(drawLineX, centerY, drawLineX2, centerY, getSelectedID(), id);
                                    updateRemoveCounter(bridge, getSelectedID(), id, islandList);
                                    listOfBridge.remove(bridge);
                                    return;
                                } else {
                                    // Brückenzähler aktualisieren und doppelte Brücke zeichnen
                                    updateBridgeCounter(bridge);
                                    decrementBridgeCounter(id, getSelectedID(), islandList);
                                    drawDoubleBridge(drawLineX, centerY, drawLineX2, centerY, Directions.EAST, getSelectedID(), id);
                                    return;
                                }
                            }
                        }

                        if (leftButton) {
                            // Neue Brücke erstellen und zur Liste hinzufügen
                            listOfBridge.add(new CreateBridges(getSelectedID(), selectedX, selectedY, id, islandX, islandY, 1, null));
                            decrementBridgeCounter(id, getSelectedID(), islandList);

                            // Brücke zeichnen
                            drawBridge(drawLineX, centerY, drawLineX2, centerY, getSelectedID(), id);
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
    public void createSouthBridge(boolean leftButton) {
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
                        drawBridge(centerX, drawLineY, centerX, drawLineY2, getSelectedID(), id);
                    } else {
                        // Durchlaufen Sie die Liste der Brücken und überprüfen Sie, ob bereits eine Brücke zwischen diesen Inseln existiert.
                        for (CreateBridges bridge : listOfBridge) {
                            int firstID = bridge.getFirstIslandID();
                            int secondID = bridge.getSecondIslandID();

                            // Überprüfen Sie, ob die Brücke zwischen den aktuellen Inseln existiert.
                            if (firstID == id && secondID == getSelectedID() || firstID == getSelectedID() && secondID == id) {
                                if (!leftButton && bridge.getNumberOfBridges() == 2) {
                                    // Entfernen Sie die doppelte Brücke, aktualisieren Sie den Brückenzähler und kehren Sie zurück.
                                    removeDoubleBridge(centerX, drawLineY, centerX, drawLineY2, getSelectedID(), id, Directions.SOUTH);
                                    updateRemoveCounter(bridge, getSelectedID(), id, islandList);
                                    return;
                                }
                                if (!leftButton && bridge.getNumberOfBridges() == 1) {
                                    // Entfernen Sie die einzelne Brücke, aktualisieren Sie den Brückenzähler und entfernen Sie die Brücke aus der Liste.
                                    removeSingleBridge(centerX, drawLineY, centerX, drawLineY2, getSelectedID(), id);
                                    updateRemoveCounter(bridge, getSelectedID(), id, islandList);
                                    listOfBridge.remove(bridge);
                                    return;
                                } else {
                                    // Aktualisieren Sie den Brückenzähler, verringern Sie den Insel-Brückenzähler, und zeichnen Sie eine doppelte Brücke.
                                    updateBridgeCounter(bridge);
                                    decrementBridgeCounter(id, getSelectedID(), islandList);
                                    drawDoubleBridge(centerX, drawLineY, centerX, drawLineY2, Directions.SOUTH, getSelectedID(), id);
                                    return;
                                }
                            }
                        }

                        // Wenn die Schleife bis hierhin gelangt, bedeutet dies, dass keine Brücke zwischen diesen Inseln existiert.
                        if (leftButton) {
                            // Fügen Sie eine neue Brücke hinzu, verringern Sie den Insel-Brückenzähler, und zeichnen Sie die Brücke.
                            listOfBridge.add(new CreateBridges(getSelectedID(), selectedX, selectedY, id, islandX, islandY, 1, null));
                            decrementBridgeCounter(id, getSelectedID(), islandList);
                            drawBridge(centerX, drawLineY, centerX, drawLineY2, getSelectedID(), id);
                            return;
                        }
                    }
                    return;
                }
            }
        }
    }

    public void fillIslandGreen(Island island) {


    	
    	painter.getDrawGreenBridges().add(island);
    	bridges.getDraw().repaint();
    	
    }
    
	public void fillIslandRed(Island island) {
		painter.getDrawGreenBridges().remove(island);
		painter.getDrawRedBridges().add(island);
    	bridges.getDraw().repaint();
		
	}

    
    /**
     * Aktualisiert den Brückenzähler für die beiden Inseln und die übergebene Brücke.
     *
     * @param bridge      Die Brücke, deren Zähler aktualisiert werden soll.
     * @param firstID     Die ID der ersten Insel.
     * @param secondID    Die ID der zweiten Insel.
     * @param islandList  Die Liste der Inseln, die aktualisiert werden sollen.
     */
    private void updateRemoveCounter(CreateBridges bridge, int  firstID, int secondID, ArrayList<Island> islandList) {
    	// Die Brückenzähler für die beiden Inseln erhöhen
    	incrementBridgeCounter(firstID, secondID, islandList);
      
    	// Holen Sie sich den aktuellen Brückenzähler für die Brücke.
        int bridgeCounter = bridge.getNumberOfBridges();

        // Überprüfen, ob der Brückenzähler bereits 2 erreicht hat.
        if (bridgeCounter > 0) {      	
            bridge.setNumberOfBridges(bridgeCounter-1); // Setze den aktualisierten Zähler zurück.
            return;
        } 
        // Wenn der Brückenzähler kleiner als 2 ist, erhöhe ihn um 1.
        else {
            System.out.println("Nichts zu entfernen");
            return;
        }
    }
    
    
    /**
     * Dekrementiert die Brückenzähler für zwei Inseln und aktualisiert die islandList.
     *
     * @param firstID   Die ID der ersten Insel.
     * @param secondID  Die ID der zweiten Insel.
     * @param islandList Die Liste der Inseln, die aktualisiert werden sollen.
     */
    private void decrementBridgeCounter(int firstID, int secondID, ArrayList<Island> islandList) {
        // Die Anzahl der Brücken für die erste Insel abrufen
        int firstIslandBridgeCount = islandList.get(firstID).getBridgeCount();
        
        // Die Anzahl der Brücken für die zweite Insel abrufen
        int secondIslandBridgeCount = islandList.get(secondID).getBridgeCount();
        
        // Brückenzähler für die erste Insel dekrementieren
        islandList.get(firstID).setBridgeCount(firstIslandBridgeCount - 1);
        
        // Brückenzähler für die zweite Insel dekrementieren
        islandList.get(secondID).setBridgeCount(secondIslandBridgeCount - 1);
    }

    
    /**
     * Dekrementiert die Brückenzähler für zwei Inseln und aktualisiert die islandList.
     *
     * @param firstID   Die ID der ersten Insel.
     * @param secondID  Die ID der zweiten Insel.
     * @param islandList Die Liste der Inseln, die aktualisiert werden sollen.
     */
    private void incrementBridgeCounter(int firstID, int secondID, ArrayList<Island> islandList) {
        // Die Anzahl der Brücken für die erste Insel abrufen
        int firstIslandBridgeCount = islandList.get(firstID).getBridgeCount();
        
        // Die Anzahl der Brücken für die zweite Insel abrufen
        int secondIslandBridgeCount = islandList.get(secondID).getBridgeCount();
        
        // Brückenzähler für die erste Insel dekrementieren
        islandList.get(firstID).setBridgeCount(firstIslandBridgeCount + 1);
        
        // Brückenzähler für die zweite Insel dekrementieren
        islandList.get(secondID).setBridgeCount(secondIslandBridgeCount + 1);
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
