package modells.Painter.Bridges;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import Modell.CreateBridges;


/**
 * Die Klasse "DrawBridges" stellt Methoden zum Zeichnen von Brücken auf einer grafischen Oberfläche zur Verfügung. 
 * Sie wird in der Benutzeroberfläche verwendet, um die Brücken im Spiel darzustellen.
 *
 * Methoden:
 *
 * - paintBridges(Graphics g, ArrayList<CreateBridges> bridges): Diese Methode zeichnet die Brücken auf der grafischen Oberfläche.
 *   Sie akzeptiert ein "Graphics"-Objekt, mit dem die Zeichenoperationen durchgeführt werden, und eine Liste von 
 *   "CreateBridges"-Objekten, die Informationen über die zu zeichnenden Brücken enthalten.
 *   Die Methode iteriert durch die Liste der Brücken und zeichnet Linien zwischen den Inseln, um die Brücken visuell darzustellen.
 */
public class DrawBridges {

	
	 /**
     * Zeichnet die Brücken auf der grafischen Oberfläche.
     *
     * @param g        Das Graphics-Objekt, mit dem die Brücken gezeichnet werden.
     * @param bridges  Eine Liste von "CreateBridges"-Objekten, die die Informationen über die zu zeichnenden Brücken enthalten.
     */
    public void paintBridges(Graphics g, ArrayList<CreateBridges> bridges) {
        // Setzt die Farbe für das Zeichnen der Brücken auf Schwarz
        g.setColor(Color.BLACK);

        // Iteriert durch die Liste von Brücken
        for (CreateBridges bridge : bridges) {
            int x1 = bridge.getFirstIslandX();
            int y1 = bridge.getFirstIslandY();
            int x2 = bridge.getSecondIslandX();
            int y2 = bridge.getSecondIslandY();

            // Zeichnet eine Linie zwischen den beiden Inseln, um die Brücke darzustellen
            g.drawLine(x1, y1, x2, y2);
        }
    }
}
