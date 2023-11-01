package modells.handleBridges;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import modells.GameData.GameData;
import modells.Support.Directions;
import modells.handleIslands.RemoveIslands;

/**
 * Die "RemoveBridges" Klasse bietet Methoden zum Entfernen von Brücken im Spiel und ist ein wichtiger Bestandteil der Spiellogik.
 * 
 * Methoden:
 * - removeSingleBridge(int x, int y, int x2, int y2, int firstID, int secondID): Entfernt eine einzelne Brücke zwischen zwei 
 * Inseln, indem sie in Weiß gezeichnet wird, um sie unsichtbar zu machen.
 * - removeDoubleBridge(int x, int y, int x2, int y2, int firstID, int secondID, Directions direction): Entfernt eine doppelte 
 * Brücke zwischen zwei Inseln, indem sie zuerst die Verbindungen löscht und dann die Brücke neu zeichnet.
 * 
 * Die "RemoveBridges" Klasse ist integraler Bestandteil von Anwendungen, die Rätselspiele wie Bridges implementieren,
 *  und ermöglicht das Entfernen von Brücken zwischen Inseln unter Berücksichtigung der Spiellogik.
 */
public class RemoveBridges {
	
	/** Konstante für die LAMBDA-Größe. */
	private static final int LAMBDA = 3;

	/** Spielinformationen. */
	private GameData data;

	/** Instanz zum Entfernen von Inseln. */
	private RemoveIslands removeIsland;

	/** Zeichenbereich in der Benutzeroberfläche. */
	private JPanel drawPanel;

	/** Instanz zum Zeichnen von Brücken. */
	private DrawBridges drawBridges;
	
	/**
	 * Initialisiert eine Instanz von RemoveBridges.
	 *
	 * Diese Klasse wird verwendet, um Brücken im Spiel zu entfernen und ist Teil der Spiellogik.
	 *
	 * @param data Das GameData-Objekt, das die Spielinformationen enthält.
	 */
	public RemoveBridges(GameData data) {
		this.data = data;
		removeIsland = new RemoveIslands();
		drawPanel = data.getBridges().getDraw();
		drawBridges = new DrawBridges(data);
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
    	//JPanel drawPanel = data.getBridges().getDraw();
        Graphics g = drawPanel.getGraphics();
        
        // Entfernen Sie die Verbindung zwischen den Inseln aus der Liste.
        removeIsland.removeIsland(firstID, secondID, data);
        
        // Zeichnen Sie die Brücke in Weiß, um sie unsichtbar zu machen.
        g.setColor(Color.WHITE);
        g.drawLine(x, y, x2, y2);
        
        // Aktualisieren Sie die Anzeige.
        drawPanel.repaint();
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
        Graphics g = drawPanel.getGraphics();
        
        // Entfernen Sie die Verbindung zwischen den Inseln zweimal (für beide Enden der Brücke).
        removeIsland.removeIsland(firstID, secondID, data);
        removeIsland.removeIsland(firstID, secondID, data);

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
        drawBridges.drawBridge(x, y, x2, y2, firstID, secondID);
    }
}
