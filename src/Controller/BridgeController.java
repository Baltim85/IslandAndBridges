package Controller;

import java.awt.event.MouseEvent;

import java.awt.event.MouseListener;

import java.util.ArrayList;

import modells.GameData.GameData;
import modells.Support.Directions;
import Modell.CreateAndDrawBridges;
import Modell.Island;
import Modell.load.UpdateLoadedGame;


/**
 * Die Klasse BridgeController ist für die Steuerung und Logik des Brückenrätsels verantwortlich. 
 * Sie ermöglicht die Interaktion mit Inseln und das Erstellen von Brücken auf der Inselkarte.
 * Der BridgeController handhabt Mausklick-Ereignisse und die Verwaltung von Brücken im Spiel.
 *
 * Attribute:
 * - islandList: Eine Liste zur Speicherung von Insel-Objekten.
 * - selectedID: Die ausgewählte ID eines Objekts.
 * - createBridges: Eine Instanz der CreateAndDrawBridges-Klasse für das Erstellen und Zeichnen von Brücken.
 * - data: Die GameData-Instanz, die Spielinformationen speichert.
 *
 * Methoden:
 * - initController(ArrayList<Island> islandList, GameData data): Initialisiert den BridgeController und bereitet die 
 * erforderlichen Elemente vor, um die Steuerung und Logik des Brückenrätsels zu ermöglichen.
 * - handleMouseClick(int x, int y, MouseEvent e): Verarbeitet Mausklick-Ereignisse für das Erstellen von Brücken auf der 
 * Inselkarte.
 * - checkDirection(int x, int y, ArrayList<Island> islandList, int delta): Überprüft die Richtung, in die sich der Mauszeiger 
 * in Bezug auf die Inseln befindet, und setzt die ausgewählte Insel-ID.
 * - getIslandList(): Gibt die Liste der Inseln zurück.
 * - setIslandList(ArrayList<Island> islandList): Setzt die Liste der Inseln.
 * - getSelectedID(): Gibt die ausgewählte ID des Objekts zurück.
 * - setSelectedID(int selectedID): Setzt die ausgewählte ID des Objekts auf den angegebenen Wert.
 *
 * @see CreateAndDrawBridges
 * @see GameData
 * @see Island
 */
public class BridgeController implements MouseListener {


	// Eine Liste zur Speicherung von Insel-Objekten
	private ArrayList<Island> islandList = new ArrayList<Island>();

	// Die ausgewählte ID
	private int selectedID;

	// Eine Instanz der CreateAndDrawBridges-Klasse für das Erstellen und Zeichnen von Brücken
	private CreateAndDrawBridges createBridges;

	// Die GameData-Instanz, die Spielinformationen speichert
	private GameData data;


	

	
	
	/**
	 * Initialisiert den Brückenrätsel-Controller und bereitet die erforderlichen Elemente vor, um die Steuerung und 
	 * Logik des Brückenrätsels zu ermöglichen.
	 *
	 * @param islandList Eine Liste von Inseln (Island-Objekten), die die Konfiguration der Inseln im Brückenrätsel darstellt.
	 * @param data Die Spielinformationsdaten (GameData), die wesentliche Informationen und Zustände des Spiels speichern.
	 *
	 */
	public void initController(ArrayList<Island> islandList, GameData data) {
	    this.islandList = islandList;
	    this.data = data;	    
	    
	    createBridges = new CreateAndDrawBridges(islandList, data);
	    data.setCreateBridges(createBridges);
	    if(data.isGameWasLoaded()) {
	    	new UpdateLoadedGame(data);
		}

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
        Directions direction = checkDirection(x, y, islandList, data.getDelta());
        boolean leftMouse = true;
        if (direction == Directions.EAST) {
            if (e.getButton() == MouseEvent.BUTTON1) { 
                // Linker Mausklick in Richtung Osten
            	data.getEastBridges().createEastBridge(leftMouse, getSelectedID());
            } else if (e.getButton() == MouseEvent.BUTTON3) {
                // Rechter Mausklick in Richtung Osten
            	data.getEastBridges().createEastBridge(false, getSelectedID());
            }
        }
        if (direction == Directions.WEST) {
            if (e.getButton() == MouseEvent.BUTTON1) { 
                // Linker Mausklick in Richtung Westen
            	data.getWestBridges().createWestBridge(leftMouse, getSelectedID());
            } else if (e.getButton() == MouseEvent.BUTTON3) {
                // Rechter Mausklick in Richtung Westen
            	data.getWestBridges().createWestBridge(false, getSelectedID());
              
            }
        }
        if (direction == Directions.SOUTH) {
            if (e.getButton() == MouseEvent.BUTTON1) { 
                // Linker Mausklick in Richtung Süden
            	data.getSouthBridges().createSouthBridge(leftMouse, getSelectedID());
            } else if (e.getButton() == MouseEvent.BUTTON3) {
                // Rechter Mausklick in Richtung Süden
            	data.getSouthBridges().createSouthBridge(false, getSelectedID());
            }
        }
        if (direction == Directions.NORTH) {
            if (e.getButton() == MouseEvent.BUTTON1) { 
                // Linker Mausklick in Richtung Norden
            	data.getNorthBridges().createNorthBridge(leftMouse, getSelectedID());
            } else if (e.getButton() == MouseEvent.BUTTON3) {
                // Rechter Mausklick in Richtung Norden
            	data.getNorthBridges().createNorthBridge(false, getSelectedID());;
            }
        }
        data.getBridges().getDraw().repaint();
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