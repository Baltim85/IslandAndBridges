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

	private int rows, cols; // Anzahl der Zeilen und Spalten im Raster

	private int originX = 0; // X-Koordinate des Ursprungs des Rasters
	private int originY = 0; // Y-Koordinate des Ursprungs des Rasters

	private int x, y; // Breite und Höhe jeder Zelle im Raster

	private JPanel islandHolder[][]; // Ein Raster zur Speicherung von Insel-Panels
	private int islandsA[] = new int[2]; // Ein Array zur Speicherung von Informationen über Inseln
	private boolean arrayIsland[][]; // Ein zweidimensionales Array zur Verfolgung von Inseln
	private ArrayList<Island> islandList = new ArrayList<Island>(); // Eine Liste zur Speicherung von Insel-Objekten
	private ArrayList<Island> drawGreenBridges = new ArrayList<Island>();
	private ArrayList<Island> drawRedBridges = new ArrayList<Island>();
	
	public ArrayList<Island> getDrawRedBridges() {
		return drawRedBridges;
	}


	public void setDrawRedBridges(ArrayList<Island> drawRedBridges) {
		this.drawRedBridges = drawRedBridges;
	}


	public ArrayList<Island> getDrawGreenBridges() {
		return drawGreenBridges;
	}


	public void setDrawGreenBridges(ArrayList<Island> drawGreenBridges) {
		this.drawGreenBridges = drawGreenBridges;
	}

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
	

	public ArrayList<CreateBridges> getBridges() {
		return bridges;
	}


	public void setBridges(ArrayList<CreateBridges> bridges) {
		this.bridges = bridges;
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
	        g.drawLine(originX, originY + i * y, originX + cols * x, originY + i * y);
	    }
	    for (int j = 0; j < cols; j++) {
	        g.drawLine(originX + j * x, originY, originX + j * x, originY + rows * y);
	    }

	    // Zeichne die Inseln
	    createIslands(4,g);
	    paintBridges(g);
	    drawGreenIsland(g);
	    drawRedIsland(g);
	}
	

	public void paintBridges(Graphics g) {
		for(CreateBridges bridges : bridges) {
			int x = bridges.getFirstIslandX();
			int y = bridges.getFirstIslandY();
			int x2 = bridges.getSecondIslandX();
			int y2 = bridges.getSecondIslandY();
			g.drawLine(x, y, x2, y2);
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
	 * Zeichnet eine einzelne Insel auf das Grafikobjekt g.
	 * 
	 * @param g das Grafikobjekt, auf das gezeichnet werden soll
	 * @param island die Insel, die gezeichnet werden soll
	 */
	private void drawIsland(Graphics g, Island island) {
	    int islandX = island.getX();
	    int islandY = island.getY();
	    int id = island.getId();
	    int bridgeCount = island.getBridgeCount();
	    
	    int halfX = x / 2;
	    int halfY = y / 2;
	    
	    if (x < y) {
	    	//g.drawString(Integer.toString(id), x * islandX - halfX, y * islandY - halfY);
	        g.drawString(Integer.toString(bridgeCount), x * islandX - halfX, y * islandY - halfY);
	        g.drawOval(x * islandX - x, y * islandY - y + (int) ((y - x) / 2), x, x);
	    } else {
	    	//g.drawString(Integer.toString(id), x * islandX - (x / 2), y * islandY - (y / 2));
	    	g.drawString(Integer.toString(bridgeCount), x * islandX - (x / 2), y * islandY - (y / 2));
	        g.drawOval(x * islandX - (int) (x / 2) - (int) (y / 2), y * islandY - y, y, y);
	    }
	}

	/**
	 * Zeichnet eine einzelne Insel auf das Grafikobjekt g.
	 * 
	 * @param g das Grafikobjekt, auf das gezeichnet werden soll
	 * @param island die Insel, die gezeichnet werden soll
	 */
	private void drawGreenIsland(Graphics g) {
		for(Island island: getDrawGreenBridges()) {
			  int islandX = island.getX();
			    int islandY = island.getY();
			    int id = island.getId();
			    int bridgeCount = island.getBridgeCount();
			    
			    int halfX = x / 2;
			    int halfY = y / 2;
			    g.setColor(Color.GREEN);
			    if (x < y) {
			    	//g.drawString(Integer.toString(id), x * islandX - halfX, y * islandY - halfY);
			    	g.fillOval(x * islandX - x, y * islandY - y + (int) ((y - x) / 2), x, x);
			    	g.setColor(Color.BLACK);
			        g.drawString(Integer.toString(bridgeCount), x * islandX - halfX, y * islandY - halfY);
			        
			    } else {
			    	//g.drawString(Integer.toString(id), x * islandX - (x / 2), y * islandY - (y / 2));
			    	g.fillOval(x * islandX - (int) (x / 2) - (int) (y / 2), y * islandY - y, y, y);
			    	g.setColor(Color.BLACK);
			    	g.drawString(Integer.toString(bridgeCount), x * islandX - (x / 2), y * islandY - (y / 2));
//			        g.fillOval(x * islandX - (int) (x / 2) - (int) (y / 2), y * islandY - y, y, y);
			    }
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
			    int id = island.getId();
			    int bridgeCount = island.getBridgeCount();
			    
			    int halfX = x / 2;
			    int halfY = y / 2;
			    g.setColor(Color.RED);
			    if (x < y) {
			    	//g.drawString(Integer.toString(id), x * islandX - halfX, y * islandY - halfY);
			    	g.fillOval(x * islandX - x, y * islandY - y + (int) ((y - x) / 2), x, x);
			    	g.setColor(Color.BLACK);
			        g.drawString(Integer.toString(bridgeCount), x * islandX - halfX, y * islandY - halfY);
			        
			    } else {
			    	//g.drawString(Integer.toString(id), x * islandX - (x / 2), y * islandY - (y / 2));
			    	g.fillOval(x * islandX - (int) (x / 2) - (int) (y / 2), y * islandY - y, y, y);
			    	g.setColor(Color.BLACK);
			    	g.drawString(Integer.toString(bridgeCount), x * islandX - (x / 2), y * islandY - (y / 2));
//			        g.fillOval(x * islandX - (int) (x / 2) - (int) (y / 2), y * islandY - y, y, y);
			    }
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


}
