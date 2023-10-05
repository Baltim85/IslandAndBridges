package modells.Painter.Islands;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import Modell.Island;

/**
 * Die Klasse DrawGreenIslands ist eine spezialisierte Implementierung der abstrakten Klasse IslandDrawer
 * und ist verantwortlich für das Zeichnen von grünen Inseln auf ein Grafikobjekt.
 * Sie verwendet die IslandFiller-Klasse, um die grünen Inseln zu füllen und zu zeichnen.
 * Jede Instanz dieser Klasse repräsentiert das Zeichnen von grünen Inseln an einer bestimmten Position
 * auf einem Grafikobjekt. Eine grüne Insel wird definiert als eine Insel, deren Anzahl zulässiger Brücken
 * erreicht wurde und daher visuell hervorgehoben wird.

 * Attribute:
 * - x (int): Die X-Koordinate, an der die grünen Inseln gezeichnet werden sollen.
 * - y (int): Die Y-Koordinate, an der die grünen Inseln gezeichnet werden sollen.
 * - islandFiller (IslandFiller): Ein Objekt der Klasse IslandFiller, das für das Füllen der Inseln mit Farbe verwendet wird.
 * - GREEN_COLOR (Color): Eine Konstante, die die Farbe Grün repräsentiert und für das Zeichnen der grünen Inseln verwendet wird.

 * Konstruktor:
 * - DrawGreenIslands(int x, int y): Dieser Konstruktor akzeptiert die X- und Y-Koordinaten als Parameter und erstellt eine Instanz der DrawGreenIslands-Klasse an der angegebenen Position.

 * Methoden:
 * - drawColoredIsland(Graphics g, ArrayList<Island> listOfGreenIslands): Diese Methode zeichnet eine Liste von grünen Inseln auf das übergebene Grafikobjekt g, indem sie die Zeichenfarbe auf Grün setzt und dann die Methode fillIsland für jede einzelne grüne Insel aufruft.
 * - fillIsland(Graphics g, Island island): Diese Methode zeichnet eine einzelne grüne Insel auf das übergebene Grafikobjekt g, indem sie die Methode "fillIslands" der IslandFiller-Klasse aufruft und die erforderlichen Koordinaten und Brückenzahlen übergibt.
 * - setColor(Graphics g): Diese Methode setzt die Zeichenfarbe auf Grün, um grüne Inseln zu zeichnen.
 * - getX(): Diese Methode ist ein Getter für die X-Koordinate.
 * - setX(int x): Diese Methode ist ein Setter für die X-Koordinate.
 * - getY(): Diese Methode ist ein Getter für die Y-Koordinate.
 * - setY(int y): Diese Methode ist ein Setter für die Y-Koordinate.
 */

public class DrawGreenIslands extends IslandDrawer{

	// Private Attribute zur Speicherung der Koordinaten
	private int x, y;

	// Ein Objekt der Klasse IslandFiller für das Füllen der Inseln
	private IslandFiller islandFiller;

	// Konstante für die Farbe Rot definieren
	private static final Color GREEN_COLOR = Color.GREEN;

	// Flag zum angeben das die Insel gefüllt werden soll
	private final boolean FILL_OVAL = true;


	/**
	 * Konstruktor für die DrawGreenIslands-Klasse.
	 * 
	 * @param x Die X-Koordinate für das Zeichnen der grünen Inseln.
	 * @param y Die Y-Koordinate für das Zeichnen der grünen Inseln.
	 */
	public DrawGreenIslands(int x, int y) {
		setX(x);
		setY(y);
		islandFiller = new IslandFiller();
	}

	/**
	 * Zeichnet eine Liste von roten Inseln auf das übergebene Grafikobjekt g.
	 *
	 * @param g                  Das Grafikobjekt, auf das gezeichnet werden soll.
	 * @param listOfGreenIslands   Die Liste der roten Inseln, die gezeichnet werden sollen.
	 */
	@Override
	public void drawColoredIsland(Graphics g, ArrayList<Island> listOfGreenIslands) {
		setColor(g); // Setzt die Zeichenfarbe auf Rot
		for (Island island : listOfGreenIslands) {
			fillIsland(g, island); // Zeichnet die rote Insel
		}
	}

	/**
	 * Zeichnet eine einzelne rote Insel auf das übergebene Grafikobjekt g.
	 *
	 * @param g       Das Grafikobjekt, auf das gezeichnet werden soll.
	 * @param island  Die rote Insel, die gezeichnet werden soll.
	 */
	@Override
	public void fillIsland(Graphics g, Island island) {
		int islandX = island.getX();
		int islandY = island.getY();
		int bridgeCount = island.getBridgeCount();

		// Setze die Zeichenfarbe auf Rot
		setColor(g);

		// Fülle die Insel mit den ermittelten Koordinaten und der Brückenanzahl
		islandFiller.fillIslands(islandX, islandY, bridgeCount, g, getX(), getY(), FILL_OVAL);
	}

	/**
	 * Setzt die Zeichenfarbe auf Rot.
	 *
	 * @param g Das Grafikobjekt, dessen Farbe auf Rot gesetzt werden soll.
	 */
	@Override
	public void setColor(Graphics g) {
		g.setColor(GREEN_COLOR);
	}


	/**
	 * Getter-Methode für die X-Koordinate.
	 * 
	 * @return Die X-Koordinate für das Zeichnen der grünen Inseln.
	 */
	public int getX() {
		return x;
	}

	/**
	 * Setter-Methode für die X-Koordinate.
	 * 
	 * @param x Die X-Koordinate für das Zeichnen der grünen Inseln.
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Getter-Methode für die Y-Koordinate.
	 * 
	 * @return Die Y-Koordinate für das Zeichnen der grünen Inseln.
	 */
	public int getY() {
		return y;
	}

	/**
	 * Setter-Methode für die Y-Koordinate.
	 * 
	 * @param y Die Y-Koordinate für das Zeichnen der grünen Inseln.
	 */
	public void setY(int y) {
		this.y = y;
	}
}

