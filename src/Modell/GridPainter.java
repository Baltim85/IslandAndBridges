package Modell;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.ArrayList;



import javax.swing.JPanel;

import modells.Painter.Bridges.DrawBridges;
import modells.Painter.Grid.PaintGrid;
import modells.Painter.Islands.DrawGreenIslands;
import modells.Painter.Islands.DrawIslands;
import modells.Painter.Islands.DrawRedIslands;

/**
 * Die Klasse "GridPainter" ist für das Zeichnen des Spielfelds, der Inseln und der Brücken in einem Brückenrätsel verantwortlich.
 * Sie erweitert die JPanel-Klasse und stellt die Darstellung des Spiels im GUI bereit.
 *
 * Attribute:
 * - private DrawIslands islandDrawer: Eine Referenz auf das Objekt zum Zeichnen von Inseln im Spiel.
 * - private DrawGreenIslands greenIslandDrawer: Eine Referenz auf das Objekt zum Zeichnen von grünen Inseln im Spiel.
 * - private DrawRedIslands redIslandDrawer: Eine Referenz auf das Objekt zum Zeichnen von roten Inseln im Spiel.
 * - private DrawBridges drawBridge: Eine Referenz auf das Objekt zum Zeichnen von Brücken im Spiel.
 * - private int rows: Die Anzahl der Zeilen im Raster des Spielfelds.
 * - private int cols: Die Anzahl der Spalten im Raster des Spielfelds.
 * - private int x: Die Breite jeder Zelle im Raster.
 * - private int y: Die Höhe jeder Zelle im Raster.
 * - private JPanel[][] islandHolder: Ein zweidimensionales Array von JPanels, das die Inseln enthält.
 * - private int[] islandsA: Ein Array von Informationen über die Inseln.
 * - private boolean[][] arrayIsland: Ein zweidimensionales Array zur Verfolgung der Positionen der Inseln im Raster.
 * - private ArrayList<Island> islandList: Eine Liste von Insel-Objekten, die im Spiel platziert werden sollen.
 * - private ArrayList<Island> drawGreenBridges: Eine Liste von Inseln, die mit grünen Brücken gezeichnet werden sollen.
 * - private ArrayList<Island> drawRedBridges: Eine Liste von Inseln, die mit roten Brücken gezeichnet werden sollen.
 * - private ArrayList<Island> drawNormalIslands: Eine Liste von Inseln, die normal gezeichnet werden sollen.
 * - private ArrayList<CreateBridges> bridges: Eine Liste von Brücken, die gezeichnet werden sollen.
 * - private PaintGrid paintGrid: Eine Referenz auf das PaintGrid-Objekt.
 *
 * Methoden:
 *
 * - public GridPainter(int cols, int rows, int x, int y, ArrayList<Island> islandList): Ein Konstruktor für die 
 * "GridPainter"-Klasse,
 *   der das Spielfeld initialisiert. Er akzeptiert die Anzahl der Spalten und Zeilen im Raster sowie die Breite und Höhe jeder 
 *   Zelle.
 *   Zusätzlich nimmt er eine Liste von Insel-Objekten entgegen, die im Spiel platziert werden sollen.
 *
 * - @Override protected void paintComponent(Graphics g): Eine Methode zur Darstellung des Spielfelds und der Inseln auf dieser
 *   Komponente. Sie wird überschrieben, um das Spielfeld zu zeichnen und die Inseln korrekt anzuzeigen.
 *
 * - public int[] getIslandsA(): Gibt ein Array mit Informationen zu den Inseln zurück.
 *
 * - public void setIslandsA(int[] islandsA): Setzt ein Array mit Informationen zu den Inseln.
 *
 * - public boolean hasIsland(JPanel panel): Überprüft, ob ein JPanel-Element eine Insel enthält.
 *
 * - public JPanel[][] getIslandHolder(): Gibt das Array von JPanels zurück, das die Inseln hält.
 *
 * - public void setIslandHolder(JPanel[][] islandHolder): Setzt das Array von JPanels, das die Inseln hält.
 *
 * - public ArrayList<Island> getDrawNormalIslands(): Gibt eine Liste der Inseln zurück, die normal gezeichnet werden sollen.
 *
 * - public void setDrawNormalIslands(ArrayList<Island> drawNormalIslands): Legt die Liste der normal zu zeichnenden Inseln fest.
 *
 * - public ArrayList<Island> getDrawRedBridges(): Gibt eine Liste der Inseln zurück, die mit roten Brücken gezeichnet werden sollen.
 *
 * - public void setDrawRedBridges(ArrayList<Island> drawRedBridges): Legt die Liste der Inseln mit roten Brücken fest.
 *
 * - public ArrayList<Island> getDrawGreenBridges(): Gibt eine Liste der Inseln zurück, die mit grünen Brücken gezeichnet 
 * werden sollen.
 *
 * - public void setDrawGreenBridges(ArrayList<Island> drawGreenBridges): Legt die Liste der Inseln mit grünen Brücken fest.
 *
 * - public ArrayList<CreateBridges> getBridges(): Gibt die Liste der Brücken zurück, die gezeichnet werden sollen.
 *
 * - public void setBridges(ArrayList<CreateBridges> bridges): Setzt die Liste der Brücken auf die angegebene Liste.
 */
public class GridPainter extends JPanel{

	// Referenz auf das Objekt zum Zeichnen von Inseln.
	private DrawIslands islandDrawer;

	// Referenz auf das Objekt zum Zeichnen von grünen Inseln.
	private DrawGreenIslands greenIslandDrawer;

	// Referenz auf das Objekt zum Zeichnen von roten Inseln.
	private DrawRedIslands redIslandDrawer;

	// Referenz auf das Objekt zum Zeichnen von Brücken.
	private DrawBridges drawBridge;

	
	// Die serialVersionUID wird für die Serialisierung von Objekten verwendet,
	// um die Kompatibilität zwischen serialisierten Objekten und Klassen sicherzustellen.
	private static final long serialVersionUID = 1L;

	// Anzahl der Zeilen und Spalten im Raster.
	private int rows, cols;

	// Breite und Höhe jeder Zelle im Raster.
	private int x, y;

	// Ein Raster zur Speicherung von Insel-Panels.
	private JPanel islandHolder[][];

	// Ein Array zur Speicherung von Informationen über Inseln.
	private int islandsA[] = new int[2];

	// Ein zweidimensionales Array zur Verfolgung von Inseln.
	private boolean arrayIsland[][];

	// Eine Liste zur Speicherung von Insel-Objekten.
	private ArrayList<Island> islandList = new ArrayList<Island>();

	// Eine Liste von Inseln, die mit grünen Brücken gezeichnet werden sollen.
	private ArrayList<Island> drawGreenBridges = new ArrayList<Island>();

	// Eine Liste von Inseln, die mit roten Brücken gezeichnet werden sollen.
	private ArrayList<Island> drawRedBridges = new ArrayList<Island>();

	// Eine Liste von Inseln, die normal gezeichnet werden sollen.
	private ArrayList<Island> drawNormalIslands = new ArrayList<Island>();

	// Eine Liste von Brücken, die gezeichnet werden sollen.
	private ArrayList<CreateBridges> bridges = new ArrayList<CreateBridges>();

	// Referenz auf das PaintGrid-Objekt.
	private PaintGrid paintGrid;

	
	/**
	 * Konstruktor für die GridPainter-Klasse.
	 *
	 * @param cols       Die Anzahl der Spalten im Raster.
	 * @param rows       Die Anzahl der Zeilen im Raster.
	 * @param x          Die Breite jeder Zelle im Raster.
	 * @param y          Die Höhe jeder Zelle im Raster.
	 * @param islandList Eine Liste von Inseln, die auf dem Raster platziert werden sollen.
	 */
	public GridPainter(int cols, int rows, int x, int y, ArrayList<Island> islandList) {
	    this.rows = rows;
	    this.cols = cols;
	    this.x = x;
	    this.y = y;
	    islandHolder = new JPanel[cols][rows];
	    this.setBackground(Color.white);
	    this.setLayout(new GridLayout(cols, rows, 1, 1));
	    arrayIsland = new boolean[cols][rows];
	    this.islandList = islandList;
	    for (int i = 0; i < cols; i++) {
	        for (int j = 0; j < rows; j++) {
	            arrayIsland[i][j] = false;
	        }
	    }
	    paintGrid = new PaintGrid();
	    islandDrawer = new DrawIslands(x, y);
	    greenIslandDrawer = new DrawGreenIslands(x, y);
	    redIslandDrawer = new DrawRedIslands(x, y);

	    drawBridge = new DrawBridges();		
	    		
	}
	




	/**
	 * Zeichnet das Gitter und die Inseln auf dieser Komponente.
	 *
	 * @param g Das Graphics-Objekt, mit dem gezeichnet wird.
	 */
	@Override
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);

	    paintGrid.drawGridOnPanel(g, rows, cols, x, y);
	    
	    // Zeichne die Inseln
	    islandDrawer.drawColoredIsland(g, islandList);

	    //paintBridges(g); // Methode zum Zeichnen von Brücken aufrufen
	    
	    greenIslandDrawer.drawColoredIsland(g, getDrawGreenBridges());
	    redIslandDrawer.drawColoredIsland(g, getDrawRedBridges());
	    drawBridge.paintBridges(g, bridges);
	}



	
	/**
	 * Gibt ein Array mit den Informationen zu den Inseln zurück.
	 *
	 * @return Ein Array mit Informationen zu den Inseln.
	 */
	public int[] getIslandsA() {
	    return islandsA;
	}

	/**
	 * Setzt ein Array mit Informationen zu den Inseln.
	 *
	 * @param islandsA Das Array mit Informationen zu den Inseln.
	 */
	public void setIslandsA(int[] islandsA) {
	    this.islandsA = islandsA;
	}
	

	/**
	 * Überprüft, ob ein JPanel-Element eine Insel enthält.
	 *
	 * @param panel Das JPanel-Element, das überprüft werden soll.
	 * @return true, wenn das JPanel eine Insel enthält, ansonsten false.
	 */
	public boolean hasIsland(JPanel panel) {
	    return true; // Hier sollte die eigentliche Implementierung stehen, um zu überprüfen, ob das JPanel eine Insel enthält.
	}

	/**
	 * Gibt das Array von JPanels zurück, das die Inseln hält.
	 *
	 * @return Ein zweidimensionales Array von JPanels, das die Inseln hält.
	 */
	public JPanel[][] getIslandHolder() {
	    return islandHolder;
	}

	/**
	 * Setzt das Array von JPanels, das die Inseln hält.
	 *
	 * @param islandHolder Das zu setzende zweidimensionale Array von JPanels, das die Inseln hält.
	 */
	public void setIslandHolder(JPanel[][] islandHolder) {
	    this.islandHolder = islandHolder;
	}

	
	/**
	 * Gibt eine Liste der Inseln zurück, die normal gezeichnet werden sollen.
	 *
	 * @return Eine Liste der normal zu zeichnenden Inseln.
	 */
	public ArrayList<Island> getDrawNormalIslands() {
	    return drawNormalIslands;
	}

	/**
	 * Legt die Liste der normal zu zeichnenden Inseln fest.
	 *
	 * @param drawNormalIslands Die Liste der normal zu zeichnenden Inseln.
	 */
	public void setDrawNormalIslands(ArrayList<Island> drawNormalIslands) {
	    this.drawNormalIslands = drawNormalIslands;
	}

	/**
	 * Gibt eine Liste der Inseln zurück, die mit roten Brücken gezeichnet werden sollen.
	 *
	 * @return Eine Liste der Inseln mit roten Brücken.
	 */
	public ArrayList<Island> getDrawRedBridges() {
	    return drawRedBridges;
	}

	/**
	 * Legt die Liste der Inseln mit roten Brücken fest.
	 *
	 * @param drawRedBridges Die Liste der Inseln mit roten Brücken.
	 */
	public void setDrawRedBridges(ArrayList<Island> drawRedBridges) {
	    this.drawRedBridges = drawRedBridges;
	}

	/**
	 * Gibt eine Liste der Inseln zurück, die mit grünen Brücken gezeichnet werden sollen.
	 *
	 * @return Eine Liste der Inseln mit grünen Brücken.
	 */
	public ArrayList<Island> getDrawGreenBridges() {
	    return drawGreenBridges;
	}

	/**
	 * Legt die Liste der Inseln mit grünen Brücken fest.
	 *
	 * @param drawGreenBridges Die Liste der Inseln mit grünen Brücken.
	 */
	public void setDrawGreenBridges(ArrayList<Island> drawGreenBridges) {
	    this.drawGreenBridges = drawGreenBridges;
	}
	
	/**
	 * Gibt die Liste der Brücken zurück.
	 *
	 * @return Die Liste der Brücken.
	 */
	public ArrayList<CreateBridges> getBridges() {
	    return bridges;
	}

	/**
	 * Setzt die Liste der Brücken auf die angegebene Liste.
	 *
	 * @param bridges Die Liste der Brücken, die gesetzt werden soll.
	 */
	public void setBridges(ArrayList<CreateBridges> bridges) {
	    this.bridges = bridges;
	}

	
}
