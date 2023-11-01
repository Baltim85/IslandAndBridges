package modells.handleBridges;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import Modell.CreateBridges;
import Modell.GridPainter;
import modells.GameData.GameData;
import modells.Support.Directions;
import modells.handleIslands.RemoveIslands;


/**
 * Die "DrawBridges" Klasse bietet Methoden zum Zeichnen von Brücken auf einer Zeichenfläche in einem Rätselspiel wie Bridges.
 * 
 * Methoden:
 * - DrawBridges(GameData data): Konstruktor, der die erforderlichen Daten für das Zeichnen von Brücken initialisiert.
 * - drawBridge(int x, int y, int x2, int y2, int firstID, int secondID): Zeichnet eine Brücke zwischen zwei Punkten auf der 
 * Zeichenfläche.
 * - drawDoubleBridge(int x, int y, int x2, int y2, Directions direction, int firstID, int secondID): Zeichnet eine Doppelbrücke 
 * zwischen zwei Inseln in der angegebenen Richtung.
 * 
 * Die "DrawBridges" Klasse ist ein wichtiger Bestandteil von Anwendungen, die Rätselspiele wie Bridges implementieren und 
 * ermöglicht das Zeichnen von Brücken zwischen Inseln auf einer Zeichenfläche.
 */
public class DrawBridges {


    private GameData data;       // Das Objekt, das die Spielinformationen enthält.
    private JPanel drawPanel;    // Das Zeichenfeld, auf dem die Brücken gezeichnet werden.
    private RemoveIslands removeIslands;  // Eine Instanz zum Entfernen von Inseln und Brücken.
    
    private static final int LAMBDA = 3;  // Eine Konstante mit dem Wert 3.

    /**
     * Konstruktor für die `DrawBridges`-Klasse.
     *
     * @param data Das `GameData`-Objekt, das die Spielinformationen und das Zeichenfeld enthält.
     */
    public DrawBridges(GameData data) {
        this.data = data;
        drawPanel = data.getBridges().getDraw();  // Das Zeichenfeld aus dem `GameData`-Objekt abrufen.
        removeIslands = new RemoveIslands();      // Eine Instanz von `RemoveIslands` erstellen.
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
		
		GridPainter painter = data.getGrid();
	    // Holen Sie sich die Grafik-Objekt zum Zeichnen auf der Zeichenfläche.
	    Graphics g = drawPanel.getGraphics();
	    
	    // Zeichnen Sie eine Linie zwischen den angegebenen Koordinaten.
	    g.drawLine(x, y, x2, y2);
	    
	    // Aktualisieren Sie die Zeichenfläche, um die gezeichnete Brücke anzuzeigen.
	    drawPanel.repaint();
	    
	    // Fügen Sie die Informationen zur gezeichneten Brücke in die Liste der Brücken ein.
	    painter.getBridges().add(new CreateBridges(firstID, x, y, secondID, x2, y2));
	}
	
	
	/**
	 * Zeichnet eine Doppelbrücke zwischen zwei Inseln in der angegebenen Richtung.
	 *
	 * Diese Methode ermöglicht das Zeichnen einer Doppelbrücke zwischen zwei Inseln im Spielfeld. Die Doppelbrücke besteht
	 * aus zwei parallelen Linien in der angegebenen Richtung. Die Methode berücksichtigt die Koordinaten der Brückenendpunkte,
	 * die Richtung der Brücke sowie die IDs der ersten und zweiten Insel.
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
	    Graphics g = drawPanel.getGraphics();
	    
	    // Entferne die Inseln zwischen denen die Doppelbrücke gezeichnet wird.
	    removeIslands.removeIsland(firstID, secondID, data);

	    g.setColor(Color.WHITE);
	    g.drawLine(x, y, x2, y2);
	    drawPanel.repaint();

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

	    // Erstelle neue Brückenobjekte und füge sie der Liste der Brücken hinzu.
	    data.getGrid().getBridges().add(new CreateBridges(firstID, x + xOffset, y + yOffset, secondID, x2 + xOffset, y2 + yOffset));
	    data.getGrid().getBridges().add(new CreateBridges(firstID, x - xOffset, y - yOffset, secondID, x2 - xOffset, y2 - yOffset));
	    drawPanel.repaint();
	}

}
