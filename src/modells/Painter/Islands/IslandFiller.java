package modells.Painter.Islands;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Die Klasse IslandFiller ist verantwortlich für das Zeichnen von gefüllten Inseln in einem grafischen Kontext.
 * Gefüllte Inseln repräsentieren Inseln in einem Rätsel, bei denen die Anzahl der Brücken visuell dargestellt wird.
 * Diese Klasse bietet eine Methode an, um solche Inseln zu zeichnen.
 *
 * Methoden:
 *
 * - fillIslands(int islandX, int islandY, int bridgeCount, Graphics g, int x, int y):
 *
 *   Diese Methode zeichnet eine gefüllte Insel in einem grafischen Kontext. Sie akzeptiert mehrere Parameter:
 *   
 *   - islandX (int): Die X-Koordinate der Insel im Raster.
 *   - islandY (int): Die Y-Koordinate der Insel im Raster.
 *   - bridgeCount (int): Die Anzahl der Brücken, die von der Insel ausgehen.
 *   - g (Graphics): Der grafische Kontext, auf dem gezeichnet wird.
 *   - x (int): Die Breite einer Rasterzelle.
 *   - y (int): Die Höhe einer Rasterzelle.
 *   
 *   Die Methode berechnet die Position und Größe eines Ovals, das die gefüllte Insel darstellt, und zeichnet es 
 *   in grüner oder roter Farbe.
 *   Zusätzlich wird die Anzahl der Brücken in schwarzer Farbe in der mitte des Ovals angezeigt.
 *   Dies ermöglicht die visuelle Darstellung einer gefüllten Insel im Kontext eines Rätsels.
 *   
 * Hinweis: Diese Methode geht davon aus, dass die Rasterzellen quadratisch sind, aber sie ist flexibel genug, 
 * um sowohl schmale als auch breite Zellen zu unterstützen.
 * Wenn die Breite (x) kleiner ist als die Höhe (y), wird die gefüllte Insel in einer schmalen Zelle gezeichnet, 
 * andernfalls in einer breiten Zelle.
 */
public class IslandFiller {

    /**
     * Diese Methode zeichnet eine gefüllte Insel mit einer bestimmten Anzahl von Brücken in einem grafischen Kontext.
     *
     * @param islandX      Die X-Koordinate der Insel im Raster.
     * @param islandY      Die Y-Koordinate der Insel im Raster.
     * @param bridgeCount  Die Anzahl der Brücken, die von der Insel ausgehen.
     * @param g            Der grafische Kontext, in dem gezeichnet wird.
     * @param x            Die Breite einer Rasterzelle.
     * @param y            Die Höhe einer Rasterzelle.
     */
    public void fillIslands(int islandX, int islandY, int bridgeCount, Graphics g, int x, int y, boolean fillOval) {
        // Die Hälfte der Breite (x) und Höhe (y) der Insel-Zelle berechnen
        int halfX = x / 2;
        int halfY = y / 2;

        // Die obere linke Ecke des Insel-Ovals berechnen
        int upperXCorner = x * islandX - halfX;
        int upperYCorner = y * islandY - halfY;

        // Variablen zur Speicherung der Position und Größe des Ovals
        int ovalX, ovalY, ovalWidth, ovalHeight;

        // Bestimmen, ob die Breite (x) kleiner ist als die Höhe (y)
        if (x < y) {
            // Die Position und Größe des Ovals für schmale Zellen berechnen
            ovalX = x * islandX - x;
            ovalY = y * islandY - y + (int) ((y - x) / 2);
            ovalWidth = x;
            ovalHeight = x;
        } else {
            // Die Position und Größe des Ovals für breite Zellen berechnen
            ovalX = x * islandX - (halfX) - (halfY);
            ovalY = y * islandY - y;
            ovalWidth = y;
            ovalHeight = y;
        }
        
        // Prüft ob die Insel gefüllt werden soll oder nur eine Leere Insel erstellt werden soll
        if(fillOval)
        	g.fillOval(ovalX, ovalY, ovalWidth, ovalHeight);
        else
        	g.drawOval(ovalX, ovalY, ovalWidth, ovalHeight);

        // Die Farbe auf Schwarz setzen und die Brückenzahl/ID in der Mitte des Ovals zeichnen
        g.setColor(Color.BLACK);
        g.drawString(Integer.toString(bridgeCount), upperXCorner, upperYCorner);
    }
    
    
}
