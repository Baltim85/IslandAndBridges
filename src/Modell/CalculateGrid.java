package Modell;


/**
 * Die Klasse CalculateGrid ermöglicht die Berechnung eines Rasters basierend auf den übergebenen
 * Breiten- und Höhenwerten, der Anzahl der Spalten und Zeilen. Sie dient der Platzierung von
 * Elementen in einem gleichmäßigen Raster innerhalb eines Panels.
 *
 * Die Klasse enthält Felder für die Rastergröße, Panelgröße, Nullpositionen und Abstände zwischen
 * den Rasterlinien in horizontaler und vertikaler Richtung. Außerdem bietet sie Methoden zur Berechnung
 * der Nullpositionen, um sicherzustellen, dass das Raster gleichmäßig platziert wird.
 *
 * Diese Klasse erleichtert die Erstellung von Rastern für Anwendungen, bei denen eine geordnete Anordnung
 * von Elementen erforderlich ist.
 */
public class CalculateGrid {
	// Die Höhe des Rasters (Anzahl der Zeilen).
	private int gridHeight;

	// Die Breite des Rasters (Anzahl der Spalten).
	private int gridWidth;

	// Die Breite des Panels, das das Raster enthält.
	private int panelWidth;

	// Die Höhe des Panels, das das Raster enthält.
	private int panelHeight;

	// Die x-Koordinate als Referenzpunkt für das Raster.
	private int zeroX;

	// Die y-Koordinate als Referenzpunkt für das Raster.
	private int zeroY;

	// Der horizontale Abstand zwischen den Rasterlinien.
	private int xDistance;

	// Der vertikale Abstand zwischen den Rasterlinien.
	private int yDistance;

	
	/**
	 * Dieser Konstruktor erstellt ein Objekt für die Berechnung eines Rasters basierend auf den übergebenen
	 * Breiten- und Höhenwerten, der Anzahl der Spalten und Zeilen. Er initialisiert die Rasterberechnung, indem er
	 * die Höhe und Breite des Panels, die Anzahl der Spalten und Zeilen, die x- und y-Abstände zwischen den Rasterlinien
	 * sowie die Nullpositionen für x und y festlegt. Anschließend werden die Methoden zur Berechnung der Nullpositionen
	 * aufgerufen, um sicherzustellen, dass das Raster gleichmäßig platziert wird.
	 *
	 * @param width  Die Breite des Panels.
	 * @param height Die Höhe des Panels.
	 * @param col    Die Anzahl der Spalten im Raster.
	 * @param rows   Die Anzahl der Zeilen im Raster.
	 */
	public CalculateGrid(int width, int height, int col, int rows) {
	    setPanelHeight(height);
	    setPanelWidth(width);
	    setGridHeight(rows);
	    setGridWidth(col);
	    setXDistance(width / col);
	    setYDistance(height / rows);
	    calculateLeftZero();
	    calculateUpperZero();
	}

	
	/**
	 * Diese Methode berechnet die linke Nullposition für die x-Koordinate im Raster.
	 * Wenn die Anzahl der vertikalen Linien im Raster ungerade ist, wird die linke Nullposition
	 * für die x-Koordinate berechnet. Andernfalls wird sie für den Fall einer geraden Anzahl
	 * von vertikalen Linien berechnet. Die Methode passt die Nullposition an, um ein gleichmäßiges
	 * Raster zu erstellen.
	 */
	private void calculateLeftZero() {
	    if (getGridHeight() % 2 != 0) {
	        // Die Anzahl der vertikalen Linien ist ungerade
	        int gridZeroLeftOdd = getCenterY();
	        
	        for (int i = 0; i < getGridHeight() - 1; i++) {
	            if (i % 2 == 0) {
	                // Die Nullposition für gerade Linien wird nach links angepasst
	                gridZeroLeftOdd -= getXDistance();
	            }
	        }
	        // Die berechnete Nullposition für x setzen
	        setZeroX(gridZeroLeftOdd);
	    } else if (getGridHeight() % 2 == 0) {
	        // Die Anzahl der vertikalen Linien ist gerade
	        int gridZeroLeftEven = getCenterY() - (getXDistance() / 2);
	        for (int i = 0; i < getGridHeight() - 2; i++) {
	            if (i % 2 == 0) {
	                // Die Nullposition für gerade Linien wird nach links angepasst
	                gridZeroLeftEven -= getXDistance();
	            }
	        }
	        // Die berechnete Nullposition für x setzen
	        setZeroX(gridZeroLeftEven);
	    }
	}

	
	/**
	 * Diese Methode berechnet die obere Nullposition für die y-Koordinate im Raster.
	 * Wenn die Anzahl der horizontalen Linien im Raster ungerade ist, wird die obere Nullposition
	 * für die y-Koordinate berechnet. Andernfalls wird sie für den Fall einer geraden Anzahl
	 * von horizontalen Linien berechnet. Die Methode passt die Nullposition an, um ein gleichmäßiges
	 * Raster zu erstellen.
	 */
	private void calculateUpperZero() {
	    if (getGridWidth() % 2 != 0) {
	        // Die Anzahl der horizontalen Linien ist ungerade
	        int gridWidthDown = getCenterX();
	        for (int i = 0; i < getGridWidth() - 1; i++) {
	            if (i % 2 == 0) {
	                // Die Nullposition für gerade Linien wird nach oben angepasst
	                gridWidthDown -= getYDistance();
	            }
	        }
	        // Die berechnete Nullposition für y setzen
	        setZeroY(gridWidthDown);
	    } else if (getGridWidth() % 2 == 0) {
	        // Die Anzahl der horizontalen Linien ist gerade
	        int gridWidthDownPos = getCenterX() - (getYDistance() / 2);
	        for (int i = 0; i < getGridWidth() - 2; i++) {
	            if (i % 2 == 0) {
	                // Die Nullposition für gerade Linien wird nach oben angepasst
	                gridWidthDownPos -= getYDistance();
	            }
	        }
	        // Die berechnete Nullposition für y setzen
	        setZeroY(gridWidthDownPos);
	    }
	}

	/**
	 * Gibt die Höhe des ausgewählten Rasters zurück. Wird für die Berechnung der
	 * y-Koordinate verwendet.
	 *
	 * @return gridHeight die Höhe des aktuellen Rasters
	 */
	public int getGridHeight() {
	    return gridHeight;
	}

	/**
	 * Setzt die Rasterhöhe. Die Anzahl der Zeilen, die der Benutzer ausgewählt hat
	 * oder eine Zufallszahl.
	 *
	 * @param gridHeight Die zu setzende Rasterhöhe als Ganzzahl.
	 */
	public void setGridHeight(int gridHeight) {
	    this.gridHeight = gridHeight;
	}

	/**
	 * Gibt die Breite des ausgewählten Rasters zurück. Wird für die Berechnung der
	 * x-Koordinate verwendet.
	 *
	 * @return gridWidth die Breite des aktuellen Rasters
	 */
	public int getGridWidth() {
	    return gridWidth;
	}

	/**
	 * Setzt die Rasterbreite. Die Anzahl der Spalten, die der Benutzer ausgewählt hat
	 * oder eine Zufallszahl.
	 *
	 * @param gridWidth Die zu setzende Rasterbreite als Ganzzahl.
	 */
	public void setGridWidth(int gridWidth) {
	    this.gridWidth = gridWidth;
	}

	/**
	 * Gibt die Höhe des Panels zurück. Das Panel ist nur das Spielfeld, nicht die
	 * gesamte Ansicht. Die Höhe des Panels ist wichtig für die Berechnung der
	 * x-Koordinate.
	 *
	 * @return panelHeight der Abstand für die x-Koordinate des aktuellen Panels
	 */
	public int getPanelHeight() {
	    return panelHeight;
	}

	/**
	 * Legt die Höhe des aktuellen Spielfelds fest. Wird später für die Berechnung
	 * der x-Koordinate benötigt.
	 *
	 * @param panelHeight die Höhe des Panels
	 */
	public void setPanelHeight(int panelHeight) {
	    this.panelHeight = panelHeight;
	}
	
	/**
	 * Gibt die Breite des Panels zurück. Das Panel ist nur das Spielfeld, nicht die
	 * gesamte Ansicht. Die Breite des Panels ist wichtig für die Berechnung der
	 * y-Koordinate.
	 *
	 * @return panelWidth der Abstand für die y-Koordinate des aktuellen Panels
	 */
	public int getPanelWidth() {
	    return panelWidth;
	}

	/**
	 * Legt die Breite des aktuellen Spielfelds fest. Wird später für die Berechnung
	 * der y-Koordinate benötigt.
	 *
	 * @param panelWidth die Breite des Panels
	 */
	public void setPanelWidth(int panelWidth) {
	    this.panelWidth = panelWidth;
	}

	/**
	 * Gibt den Abstand für die x-Koordinate zurück. Wird für ein sauberes und
	 * gleichmäßiges Raster in x-Richtung verwendet.
	 *
	 * @return xDistance der Abstand für jede einzelne x-Koordinate
	 */
	public int getXDistance() {
	    return xDistance;
	}

	/**
	 * Legt den Wert für die x-Koordinate fest. Dies ist erforderlich, um den Abstand
	 * zwischen der ersten und der zweiten x-Koordinate zu berechnen. Dieser Abstand
	 * ist für eine bessere Platzierung der Inseln erforderlich.
	 *
	 * @param xCoord der Wert für den Abstand in x-Richtung
	 */
	public void setXDistance(int xCoord) {
	    this.xDistance = xCoord;
	}

	/**
	 * Gibt den Abstand für die y-Koordinate zurück. Wird für ein sauberes und
	 * gleichmäßiges Raster in y-Richtung verwendet.
	 *
	 * @return yDistance der Abstand für jede einzelne y-Koordinate
	 */
	public int getYDistance() {
	    return yDistance;
	}

	/**
	 * Legt den Wert für die y-Koordinate fest. Dies ist erforderlich, um den Abstand
	 * zwischen der ersten und der zweiten y-Koordinate zu berechnen. Dieser Abstand
	 * ist für eine bessere Platzierung der Inseln erforderlich.
	 *
	 * @param yCoord der Wert für den Abstand in y-Richtung
	 */
	public void setYDistance(int yCoord) {
	    this.yDistance = yCoord;
	}

	/**
	 * Gibt das Zentrum des Spielfelds in x-Richtung zurück. Dies wird verwendet, um
	 * die erste Linie im Raster in der Mitte einzurichten. Wenn die Anzahl der
	 * Spalten ungerade ist, wird die erste Linie direkt in der Mitte liegen. Wenn
	 * nicht, wird die x-Distanz durch zwei geteilt. Das Ergebnis wird zu
	 * <code>getCenterX</code> für die erste Linie hinzugefügt und von
	 * <code>getCenterX</code> für die zweite Linie abgezogen.
	 *
	 * @return centerX das Zentrum des Spielfelds in x-Richtung
	 */
	public int getCenterX() {
	    return panelWidth / 2;
	}

	/**
	 * Gibt das Zentrum des Spielfelds in y-Richtung zurück. Dies wird verwendet, um
	 * die erste Linie im Raster in der Mitte einzurichten. Wenn die Anzahl der
	 * Zeilen ungerade ist, wird die erste Linie direkt in der Mitte liegen. Wenn
	 * nicht, wird die y-Distanz durch zwei geteilt. Das Ergebnis wird zu
	 * <code>getCenterY</code> für die erste Linie hinzugefügt und von
	 * <code>getCenterY</code> für die zweite Linie abgezogen.
	 *
	 * @return centerY das Zentrum des Spielfelds in y-Richtung
	 */
	public int getCenterY() {
	    return panelHeight / 2;
	}

	/**
	 * Gibt die absolute x-Koordinate auf der linken Seite des Feldes zurück.
	 *
	 * @return zeroX die neue Null-Koordinate für x
	 */
	public int getZeroX() {
	    return zeroX;
	}

	/**
	 * Setzt die neue Null-Koordinate für die x-Richtung. Dies ist erforderlich,
	 * da ein Objekt aus der Klasse Graphics die Koordinate links unten als Null
	 * verwendet. Daher ist keine spätere Übersetzung für dieses Objekt erforderlich.
	 * Dies vereinfacht das Zeichnen der Inseln. Und es ist auch erforderlich, um
	 * einen kleinen Abstand zwischen der linken Begrenzung des Spiels zu erhalten.
	 * Keine Insel wird außerhalb des Panels erstellt.
	 *
	 * @param zeroX der neue Nullpunkt für die x-Richtung
	 */
	public void setZeroX(int zeroX) {
	    this.zeroX = zeroX;
	}

	/**
	 * Gibt die absolute y-Koordinate auf der linken Seite des Feldes zurück.
	 *
	 * @return zeroY die neue Null-Koordinate für y
	 */
	public int getZeroY() {
	    return zeroY;
	}

	/**
	 * Setzt die neue Null-Koordinate für die y-Richtung. Dies ist erforderlich,
	 * da ein Objekt aus der Klasse Graphics die Koordinate links unten als Null
	 * verwendet. Daher ist keine spätere Übersetzung für dieses Objekt erforderlich.
	 * Dies vereinfacht das Zeichnen der Inseln. Und es ist auch erforderlich, um
	 * einen kleinen Abstand zwischen der oberen Begrenzung des Spiels zu erhalten.
	 * Keine Insel wird außerhalb des Panels erstellt.
	 *
	 * @param zeroY der neue Nullpunkt für die y-Richtung
	 */
	public void setZeroY(int zeroY) {
	    this.zeroY = zeroY;
	}
}
