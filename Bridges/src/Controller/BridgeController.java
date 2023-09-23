package Controller;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import java.awt.event.MouseListener;
import java.lang.ModuleLayer.Controller;
import java.util.ArrayList;

import javax.swing.JLabel;

import UI.Bridges;
import UI.ErrorIsland;
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
	
	/**
	* Referenz auf ein Objekt, das die Darstellung des Rasters und der Brücken verwaltet.
	*/
	private GridPainter painter;
	
	/**
	* Referenz auf ein Objekt oder eine Klasse, die das Erstellen und Zeichnen von Brücken behandelt.
	*/
	
	private CreateAndDrawBridges createBridges;
	/**
	 * Konstruktor für die BridgeController-Klasse.
	 * Dieser Konstruktor wird verwendet, um eine Instanz der Klasse zu erstellen.
	 */
	public BridgeController() {
	    // Hier können Initialisierungen oder Setup-Code platziert werden.
	}

	private JLabel infoText = new JLabel("Das Spiel ist Fehlerhaft!");
	
	
	private ErrorIsland errorInfo;

	public ErrorIsland getErrorInfo() {
		return errorInfo;
	}



	public void setErrorInfo(ErrorIsland errorInfo) {
		this.errorInfo = errorInfo;
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
	    
	    
	    errorInfo = new ErrorIsland();
	    errorInfo.getBtnNo().addActionListener(e -> ErrorMessage());
	    
	    this.centerX = centerX;
	    this.centerY = centerY;
	    this.delta = delta;
	    listOfBridge.clear();
	    this.painter = painter;
	    createBridges = new CreateAndDrawBridges(islandList, bridges, centerX, centerY, delta, width, height, this);
	    calculateCenterForEachIsland(islandList, centerX, centerY);
	}
	
	private void ErrorMessage() {
		errorInfo.dispose();
		
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
    
 // Leert die Insel, indem sie aus der Liste der grünen Inseln entfernt und zur Liste der normalen Inseln hinzugefügt wird.
    public void emptyIsland(Island island) {
    	// Entferne die Insel aus der Liste der grünen Inseln.
    	painter.getDrawGreenBridges().remove(island);      
    	// Füge die Insel zur Liste der normalen Inseln hinzu.
    	painter.getDrawNormalIslands().add(island);   
    	if (!painter.getDrawRedBridges().isEmpty()) {
            bridges.getDraw().repaint();
            bridges.getLblInfo().setText("Das Spiel enthält fehler!");
        } else {
        	bridges.getLblInfo().setText("Das Rätsel ist noch nicht gelöst");
        }
    	if(painter.getDrawRedBridges().isEmpty())
    		setVisible(false);
    	// Aktualisiere die Anzeige der Brücken.
    	bridges.getDraw().repaint();                         
    }

    // Füllt die Insel grün, indem sie zur Liste der grünen Inseln hinzugefügt und aus der Liste der roten Inseln entfernt wird.
    public void fillIslandGreen(Island island) {
    	// Füge die Insel zur Liste der grünen Inseln hinzu.
        painter.getDrawGreenBridges().add(island); 
        // Entferne die Insel aus der Liste der roten Inseln.       
        painter.getDrawRedBridges().remove(island);      
        if (!painter.getDrawRedBridges().isEmpty()) {
            bridges.getDraw().repaint();
            bridges.getLblInfo().setText("Das Spiel enthält fehler!");
            
        } else
        	bridges.getLblInfo().setText("Das Rätsel ist noch nicht gelöst");
    	
        if(painter.getDrawRedBridges().isEmpty())
    		setVisible(false);
        // Aktualisiere die Anzeige der Brücken.
        bridges.getDraw().repaint();                         
    }
    
    private boolean isVisible = false;
    
    public boolean isVisible() {
		return isVisible;
	}



	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}



	/**
     * Färbt die Insel rot oder entfernt sie aus der Liste der roten Brückenzeichnungen,
     * je nachdem, ob die Insel bereits als rote Brücke gezeichnet wurde oder nicht.
     * 
     * @param island Das Island-Objekt, das eingefärbt werden soll.
     */
    public void fillIslandRed(Island island) {
        // Überprüfen, ob die Insel bereits als grüne Brücke gezeichnet wurde
        if (painter.getDrawGreenBridges().contains(island))
            painter.getDrawGreenBridges().remove(island);
        	
        // Überprüfen, ob die Insel bereits als rote Brücke gezeichnet wurde
        if (painter.getDrawRedBridges().contains(island)) {
            bridges.getDraw().repaint();
            bridges.getLblInfo().setText("Das Spiel enthält fehler!");
            
            
            
        }
        else {
            painter.getDrawRedBridges().add(island);
            bridges.getLblInfo().setText("Das Spiel enthält fehler!");
            if(!isVisible()) {
            	errorInfo.setVisible(true);
            	setVisible(true);
        	}
        }
        
        // Die Zeichenfläche aktualisieren
        bridges.getDraw().repaint();
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
