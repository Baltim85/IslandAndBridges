package modells.Painter.Grid;

import java.awt.Graphics;


/**
 * Die "PaintGrid" Klasse ist dafür verantwortlich, ein Gitter auf einer Grafikfläche zu zeichnen. 
 * Dieses Gitter wird durch die Angabe der Anzahl der Zeilen, der Anzahl der Spalten sowie der Breite und Höhe einer 
 * Zelle definiert. Sie bietet eine Methode, die ein grafisches Objekt (in der Regel ein JPanel oder eine Zeichenfläche) verwendet, 
 * um das Gitter zu zeichnen.

 * Methoden:
 * - drawGridOnPanel(Graphics g, int rows, int cols, int x, int y): Zeichnet ein Gitter auf die angegebene Grafikfläche mit den 
 * spezifizierten Parametern für Zeilen, Spalten und Zellengröße.

 * Die "PaintGrid" Klasse ist nützlich, um ein visuelles Gitter für Anwendungen zu erstellen, bei denen ein gerastertes Layout 
 * erforderlich ist, wie z. B. bei Zeichenanwendungen, Rätselspielen oder ähnlichen Szenarien.
 */
public class PaintGrid {
	
	/**
	 * Zeichnet ein Gitter auf eine Grafikfläche mit den angegebenen Parametern.
	 *
	 * @param g    Die Grafik-Objekt, auf dem das Gitter gezeichnet wird.
	 * @param rows Die Anzahl der Zeilen im Gitter.
	 * @param cols Die Anzahl der Spalten im Gitter.
	 * @param x    Die horizontale Breite einer Zelle im Gitter.
	 * @param y    Die vertikale Höhe einer Zelle im Gitter.
	 */
	public void drawGridOnPanel(Graphics g, int rows, int cols, int x, int y) {
	    int originX = 0;
	    int originY = 0;

	    // Zeichne das Gitter
	    for (int i = 0; i < rows; i++) {
	        // Horizontale Linien zeichnen
	        g.drawLine(originX, originY + i * y, originX + cols * x, originY + i * y);
	    }
	    for (int j = 0; j < cols; j++) {
	        // Vertikale Linien zeichnen
	        g.drawLine(originX + j * x, originY, originX + j * x, originY + rows * y);
	    }

	}

}
