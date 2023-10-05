package modells.Painter.Islands;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import Modell.Island;


/**
 * Die Klasse DrawIslands ist eine spezialisierte Implementierung der abstrakten Klasse IslandDrawer und
 * ist verantwortlich für das Zeichnen von Inseln in einem grafischen Kontext. Sie verwendet die Klasse
 * IslandFiller, um die Inseln zu füllen und zu zeichnen. Jede Instanz dieser Klasse repräsentiert das
 * Zeichnen von Inseln mit einer bestimmten Breite (X) und Höhe (Y) auf einem Grafikobjekt.
 *
 * Attribute:
 * - x (int): Die Breite (X) der zu zeichnenden Inseln.
 * - y (int): Die Höhe (Y) der zu zeichnenden Inseln.
 * - islandFiller (IslandFiller): Ein Objekt der Klasse IslandFiller, das für das Füllen der Inseln mit Farbe verwendet wird.
 * - COLOR_WHITE (Color): Eine Konstante, die die Farbe Weiß repräsentiert und für das Zeichnen der Inseln verwendet wird.
 * - FILL_OVAL (boolean): Ein Konstantenflag, das angibt, ob die Inseln als gefüllte Ovale gezeichnet werden sollen.
 *
 * Konstruktor:
 * - DrawIslands(int x, int y): Dieser Konstruktor akzeptiert die Breite (X) und Höhe (Y) als Parameter und erstellt eine Instanz
 *   der DrawIslands-Klasse mit den angegebenen Abmessungen. Dabei wird auch ein IslandFiller-Objekt initialisiert.
 *
 * Methoden:
 * - drawColoredIsland(Graphics g, ArrayList<Island> listOfIslands): Diese Methode zeichnet eine Liste 
 * 	 von Inseln in einem grafischen Kontext g. 
 * 	 Sie setzt die Zeichenfarbe auf Weiß und ruft dann die Methode fillIsland für jede Insel 
 *   in der übergebenen Liste auf, um sie zu zeichnen.
 * - fillIsland(Graphics g, Island island): Diese Methode zeichnet eine einzelne Insel in einem grafischen Kontext g, indem sie die Methode
 *   fillIslands der IslandFiller-Klasse aufruft und die erforderlichen Koordinaten, ID und Grafikgrößen übergibt. Das FILL_OVAL-Flag
 *   steuert, ob die Inseln als gefüllte Ovale gezeichnet werden.
 * - setColor(Graphics g): Diese Methode setzt die Zeichenfarbe auf Weiß (oder eine vordefinierte Farbe), um Inseln zu zeichnen.
 * - getX(): Diese Methode ist ein Getter für die Breite (X) der Inseln.
 * - setX(int x): Diese Methode ist ein Setter für die Breite (X) der Inseln.
 * - getY(): Diese Methode ist ein Getter für die Höhe (Y) der Inseln.
 * - setY(int y): Diese Methode ist ein Setter für die Höhe (Y) der Inseln.
 */
public class DrawIslands extends IslandDrawer {

	// Private Attribute "x" und "y" zur Speicherung von Koordinaten
	private int x, y;

	// Ein Objekt der Klasse IslandFiller für das Füllen der Inseln
	private IslandFiller islandFiller;// = new IslandFiller();

	// Konstante für die Farbe Rot definieren
	private static final Color COLOR_WHITE = Color.WHITE;

	// Flag zum angeben das die Insel gefüllt werden soll
	private final boolean FILL_OVAL = false;



	/**
	 * Konstruktor für die "DrawIslands"-Klasse.
	 *
	 * @param x Die Breite (X) der Inseln.
	 * @param y Die Höhe (Y) der Inseln.
	 */
	public DrawIslands(int x, int y) {
		// Die Breite (X) und Höhe (Y) der Inseln setzen
		setX(x);
		setY(y);

		// Eine Instanz von "IslandFiller" erstellen, um Inseln zu zeichnen
		islandFiller = new IslandFiller();
	}

	/**
	 * Die Methode "drawColoredIsland" dient dazu, rote Inseln in einem grafischen Kontext zu zeichnen. 
	 *
	 * @param g                Das Graphics-Objekt, auf dem die Inseln gezeichnet werden.
	 * @param listOfIslands Eine Liste von Inseln (Island-Objekten), die in Rot gezeichnet werden sollen.
	 */
	@Override
	public void drawColoredIsland(Graphics g, ArrayList<Island> listOfIslands) {
		// Setzt die Zeichenfarbe auf Rot, um rote Inseln zu zeichnen
		setColor(g);

		// Schleife durch die Liste der roten Inseln und zeichnet jede einzelne
		for (Island island : listOfIslands) {
			// Ruft die Methode "fillIsland" auf, um die rote Insel zu zeichnen
			fillIsland(g, island);
		}
	}


	/**
	 * Die Methode "fillIsland" dient dazu, eine Insel in einem grafischen Kontext zu zeichnen.
	 *
	 * @param g      Das Graphics-Objekt, auf dem die Insel gezeichnet wird.
	 * @param island Die Insel, die gezeichnet werden soll (Island-Objekt).
	 */
	@Override
	public void fillIsland(Graphics g, Island island) {
		// Die X- und Y-Koordinaten sowie die Brückenanzahl der Insel abrufen
		int islandX = island.getX();
		int islandY = island.getY();
		int id = island.getId();

		// Aufruf der Methode "fillIslands" aus "islandFiller" zum Zeichnen der Insel
		// Die Methode erwartet die Inselkoordinaten, Brückenanzahl sowie die Grafik- und Rastergröße
		islandFiller.fillIslands(islandX, islandY, id, g, getX(), getY(), FILL_OVAL);
	}


	/**
	 * Die Methode "setColor" dient dazu, die Zeichenfarbe auf Weiß (oder eine vordefinierte Farbe) zu setzen.
	 *
	 * @param g Das Graphics-Objekt, auf dem die Zeichenfarbe gesetzt wird.
	 */
	@Override
	public void setColor(Graphics g) {
		// Setze die Zeichenfarbe auf Weiß (oder eine vordefinierte Farbe)
		g.setColor(COLOR_WHITE);
	}

	/**
	 * Getter-Methode für die Breite (X) der Inseln.
	 *
	 * @return Die Breite (X) der Inseln.
	 */
	public int getX() {
		return x;
	}

	/**
	 * Setter-Methode für die Breite (X) der Inseln.
	 *
	 * @param x Die zu setzende Breite (X) der Inseln.
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Getter-Methode für die Höhe (Y) der Inseln.
	 *
	 * @return Die Höhe (Y) der Inseln.
	 */
	public int getY() {
		return y;
	}

	/**
	 * Setter-Methode für die Höhe (Y) der Inseln.
	 *
	 * @param y Die zu setzende Höhe (Y) der Inseln.
	 */
	public void setY(int y) {
		this.y = y;
	}

}
