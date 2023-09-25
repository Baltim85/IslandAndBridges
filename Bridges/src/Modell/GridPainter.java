package Modell;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.ArrayList;



import javax.swing.JPanel;

/**
 * Die GridPainter-Klasse stellt ein Raster dar, auf dem Inseln gezeichnet werden können.
 */
public class GridPainter extends JPanel{




	/**
	 * Die serialVersionUID wird für die Serialisierung von Objekten verwendet,
	 * um die Kompatibilität zwischen serialisierten Objekten und Klassen sicherzustellen.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Anzahl der Zeilen und Spalten im Raster.
	 */
	private int rows, cols;

	/**
	 * X-Koordinate des Ursprungs des Rasters.
	 */
	private int originX = 0;

	/**
	 * Y-Koordinate des Ursprungs des Rasters.
	 */
	private int originY = 0;

	/**
	 * Breite und Höhe jeder Zelle im Raster.
	 */
	private int x, y;

	/**
	 * Ein Raster zur Speicherung von Insel-Panels.
	 */
	private JPanel islandHolder[][];

	/**
	 * Ein Array zur Speicherung von Informationen über Inseln.
	 */
	private int islandsA[] = new int[2];

	/**
	 * Ein zweidimensionales Array zur Verfolgung von Inseln.
	 */
	private boolean arrayIsland[][];

	/**
	 * Eine Liste zur Speicherung von Insel-Objekten.
	 */
	private ArrayList<Island> islandList = new ArrayList<Island>();

	/**
	 * Eine Liste von Inseln, die mit grünen Brücken gezeichnet werden sollen.
	 */
	private ArrayList<Island> drawGreenBridges = new ArrayList<Island>();

	/**
	 * Eine Liste von Inseln, die mit roten Brücken gezeichnet werden sollen.
	 */
	private ArrayList<Island> drawRedBridges = new ArrayList<Island>();

	/**
	 * Eine Liste von Inseln, die normal gezeichnet werden sollen.
	 */
	private ArrayList<Island> drawNormalIslands = new ArrayList<Island>();

	/**
	 * Eine Liste von Brücken, die gezeichnet werden sollen.
	 */
	private ArrayList<CreateBridges> bridges = new ArrayList<CreateBridges>();


	
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
	    arrayIsland = new boolean[cols + 1][rows + 1];
	    this.islandList = islandList;
	    for (int i = 0; i < cols; i++) {
	        for (int j = 0; j < rows; j++) {
	            arrayIsland[i][j] = false;
	        }
	    }
	}
	




	/**
	 * Zeichnet das Gitter und die Inseln auf dieser Komponente.
	 *
	 * @param g Das Graphics-Objekt, mit dem gezeichnet wird.
	 */
	@Override
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);

	    // Zeichne das Gitter
	    for (int i = 0; i < rows; i++) {
	        // Horizontale Linien zeichnen
	        g.drawLine(originX, originY + i * y, originX + cols * x, originY + i * y);
	    }
	    for (int j = 0; j < cols; j++) {
	        // Vertikale Linien zeichnen
	        g.drawLine(originX + j * x, originY, originX + j * x, originY + rows * y);
	    }

	    // Zeichne die Inseln
	    createIslands(4, g); // Methode zur Erstellung der Inseln aufrufen
	    paintBridges(g); // Methode zum Zeichnen von Brücken aufrufen
	    drawGreenIsland(g); // Methode zum Zeichnen grüner Inseln aufrufen
	    drawRedIsland(g); // Methode zum Zeichnen roter Inseln aufrufen
	}

	
	/**
	 * Zeichnet die Brücken auf der grafischen Oberfläche.
	 *
	 * @param g Das Graphics-Objekt, mit dem gezeichnet wird.
	 */
	public void paintBridges(Graphics g) {
	    for(CreateBridges bridge : bridges) {
	        int x1 = bridge.getFirstIslandX();
	        int y1 = bridge.getFirstIslandY();
	        int x2 = bridge.getSecondIslandX();
	        int y2 = bridge.getSecondIslandY();

	        // Zeichne eine Linie zwischen den beiden Inseln
	        g.drawLine(x1, y1, x2, y2);
	    }
	}



	/**
	 * Zeichnet die Inseln auf das Grafikobjekt g.
	 * 
	 * @param islands die Anzahl der Inseln
	 * @param g das Grafikobjekt, auf das gezeichnet werden soll
	 */
	public void createIslands(int islands, Graphics g) {
	    for (Island island : islandList) {
	        drawIsland(g, island);	    
	    }	    
	}
	
	

	/**
	 * Zeichnet eine einzelne Insel auf das gegebene Grafikobjekt g.
	 *
	 * @param g Das Grafikobjekt, auf das gezeichnet werden soll.
	 * @param island Die Insel, die gezeichnet werden soll.
	 */
	private void drawIsland(Graphics g, Island island) {
	    int islandX = island.getX();
	    int islandY = island.getY();
	    int id = island.getId();

	    int halfX = x / 2;
	    int halfY = y / 2;

	    // Bestimme, ob die Breite (x) kleiner ist als die Höhe (y)
	    if (x < y) {
	        // Zeichne die Inselnummer und eine ovalförmige Insel
	        g.drawString(Integer.toString(id), x * islandX - halfX, y * islandY - halfY);
	        g.drawOval(x * islandX - x, y * islandY - y + (int) ((y - x) / 2), x, x);
	    } else {
	        // Zeichne die Inselnummer und eine ovalförmige Insel
	        g.drawString(Integer.toString(id), x * islandX - (x / 2), y * islandY - (y / 2));
	        g.drawOval(x * islandX - (int) (x / 2) - (int) (y / 2), y * islandY - y, y, y);
	    }
	}


	/**
	 * Zeichnet eine einzelne grüne Insel auf das gegebene Grafikobjekt g.
	 *
	 * @param g Das Grafikobjekt, auf das gezeichnet werden soll.
	 */
	private void drawGreenIsland(Graphics g) {
	    // Schleife über alle grünen Inseln in der Liste
	    for (Island island : getDrawGreenBridges()) {
	        int islandX = island.getX();
	        int islandY = island.getY();
	        int bridgeCount = island.getBridgeCount();
	        
	        // Setze die Farbe auf grün
	        g.setColor(Color.GREEN);
	        fillIslands(islandX, islandY, bridgeCount, g);

	    }
	}

	
	/**
	 * Zeichnet eine einzelne gefüllte Insel auf das gegebene Grafikobjekt g.
	 *
	 * @param islandX     Die X-Koordinate der Insel.
	 * @param islandY     Die Y-Koordinate der Insel.
	 * @param bridgeCount Die Anzahl der Brücken, die mit der Insel verbunden sind.
	 * @param g           Das Grafikobjekt, auf das gezeichnet werden soll.
	 */
	private void fillIslands(int islandX, int islandY, int bridgeCount, Graphics g) {
	    int halfX = x / 2;
	    int halfY = y / 2;
	    
	    // Bestimme, ob die Breite (x) kleiner ist als die Höhe (y)
	    if (x < y) {
	        // Zeichne eine gefüllte ovale Insel
	        g.fillOval(x * islandX - x, y * islandY - y + (int) ((y - x) / 2), x, x);
	        // Setze die Farbe auf schwarz und zeichne die Brückenzahl
	        g.setColor(Color.BLACK);
	        g.drawString(Integer.toString(bridgeCount), x * islandX - halfX, y * islandY - halfY);

	    } else {
	        // Zeichne eine gefüllte ovale Insel
	        g.fillOval(x * islandX - (int) (x / 2) - (int) (y / 2), y * islandY - y, y, y);
	        // Setze die Farbe auf schwarz und zeichne die Brückenzahl
	        g.setColor(Color.BLACK);
	        g.drawString(Integer.toString(bridgeCount), x * islandX - (x / 2), y * islandY - (y / 2));
	    }
	}

	/**
	 * Zeichnet eine einzelne Insel auf das Grafikobjekt g.
	 * 
	 * @param g das Grafikobjekt, auf das gezeichnet werden soll
	 * @param island die Insel, die gezeichnet werden soll
	 */
	public void drawRedIsland(Graphics g) {
		for(Island island: getDrawRedBridges()) {
			  int islandX = island.getX();
			    int islandY = island.getY();
			    int bridgeCount = island.getBridgeCount();
			    g.setColor(Color.RED);
			    fillIslands(islandX, islandY, bridgeCount, g);
		}
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
