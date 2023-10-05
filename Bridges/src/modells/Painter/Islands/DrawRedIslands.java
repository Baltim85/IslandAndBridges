package modells.Painter.Islands;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import Modell.Island;


/**
 * Die Klasse DrawRedIslands ist eine spezialisierte Implementierung der abstrakten Klasse IslandDrawer
 * und ist verantwortlich für das Zeichnen von roten Inseln auf ein Grafikobjekt.
 * Sie verwendet die IslandFiller-Klasse, um die roten Inseln zu füllen und zu zeichnen.
 * Jede Instanz dieser Klasse repräsentiert das Zeichnen von roten Inseln an einer bestimmten Position
 * auf einem Grafikobjekt. Eine rote Insel wird definiert als eine Insel, deren Anzahl zulässiger Brücken
 * überschritten wurde.
 *
 * Attribute:
 * - x (int): Die X-Koordinate, an der die roten Inseln gezeichnet werden sollen.
 * - y (int): Die Y-Koordinate, an der die roten Inseln gezeichnet werden sollen.
 * - islandFiller (IslandFiller): Ein Objekt der Klasse IslandFiller, das für das Füllen der Inseln verwendet wird.
 * - RED_COLOR (Color): Eine Konstante, die die Farbe Rot repräsentiert und für das Zeichnen der roten Inseln verwendet wird.
 *
 * Konstruktor:
 * - DrawRedIslands(int x, int y): Ein Konstruktor, der die X- und Y-Koordinaten für das Zeichnen der roten Inseln
 *   akzeptiert und eine Instanz der DrawRedIslands-Klasse erstellt.
 *
 * Methoden:
 * - drawColoredIsland(Graphics g, ArrayList<Island> listOfRedIslands): Eine Methode, die eine Liste von roten Inseln auf
 *   das übergebene Grafikobjekt g zeichnet, indem sie die setColor und fillIsland-Methoden aufruft.
 * - fillIsland(Graphics g, Island island): Eine Methode, die eine einzelne rote Insel auf das übergebene Grafikobjekt g zeichnet,
 *   indem sie die Methode "fillIslands" der IslandFiller-Klasse aufruft.
 * - setColor(Graphics g): Eine Methode, die die Zeichenfarbe auf Rot setzt.
 * - getX(): Eine Getter-Methode für die X-Koordinate.
 * - setX(int x): Eine Setter-Methode für die X-Koordinate.
 * - getY(): Eine Getter-Methode für die Y-Koordinate.
 * - setY(int y): Eine Setter-Methode für die Y-Koordinate.
 */
public class DrawRedIslands extends IslandDrawer{


	// Private Attribute zur Speicherung der X- und Y-Koordinaten
	private int x, y;

	// Ein Objekt der Klasse IslandFiller für das Füllen der Inseln
	private IslandFiller islandFiller;// = new IslandFiller();

	// Konstante für die Farbe Rot definieren
	private static final Color RED_COLOR = Color.RED;

	// Flag zum angeben das die Insel gefüllt werden soll
	private final boolean FILL_OVAL = true;



	/**
	 * Konstruktor für die DrawRedIslands-Klasse.
	 * 
	 * @param x Die X-Koordinate für das Zeichnen der roten Inseln.
	 * @param y Die Y-Koordinate für das Zeichnen der roten Inseln.
	 */
	public DrawRedIslands(int x, int y) {
		setX(x);
		setY(y);
		islandFiller = new IslandFiller();
	}


	/**
	 * Zeichnet eine Liste von roten Inseln auf das übergebene Grafikobjekt g. Die Methode
	 * setzt zunächst die Farbe auf Rot und durchläuft dann die Liste der roten Inseln, um
	 * jede einzelne Insel zu zeichnen.
	 *
	 * @param g                  Das Grafikobjekt, auf das gezeichnet werden soll.
	 * @param listOfRedIslands   Die Liste der roten Inseln, die gezeichnet werden sollen.
	 */
	@Override
	public void drawColoredIsland(Graphics g, ArrayList<Island> listOfRedIslands) {
		setColor(g); // Setzt die Zeichenfarbe auf Rot
		for (Island island : listOfRedIslands) {
			fillIsland(g, island); // Zeichnet die rote Insel
		}
	}

	/**
	 * Zeichnet eine einzelne rote Insel auf das übergebene Grafikobjekt g. Die Methode
	 * ruft die Methode "fillIslands" der IslandFiller-Klasse auf, um die Insel basierend auf
	 * ihren Koordinaten und der Anzahl der Brücken zu füllen.
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
		g.setColor(RED_COLOR); // Setzt die Zeichenfarbe auf Rot
	}

	/**
	 * Getter-Methode für die X-Koordinate.
	 * 
	 * @return Die X-Koordinate für das Zeichnen der roten Inseln.
	 */
	public int getX() {
		return x;
	}

	/**
	 * Setter-Methode für die X-Koordinate.
	 * 
	 * @param x Die X-Koordinate für das Zeichnen der roten Inseln.
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Getter-Methode für die Y-Koordinate.
	 * 
	 * @return Die Y-Koordinate für das Zeichnen der roten Inseln.
	 */
	public int getY() {
		return y;
	}

	/**
	 * Setter-Methode für die Y-Koordinate.
	 * 
	 * @param y Die Y-Koordinate für das Zeichnen der roten Inseln.
	 */
	public void setY(int y) {
		this.y = y;
	}
}
