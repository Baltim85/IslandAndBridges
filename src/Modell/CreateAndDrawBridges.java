package Modell;


import java.util.ArrayList;

import modells.GameData.GameData;
import modells.handleIslands.CalculateCenter;



/**
 * Die Klasse "CreateAndDrawBridges" ist verantwortlich für die Erstellung und Zeichnung von Brücken zwischen Inseln im Spiel.
 * Sie verwaltet die Liste der Inseln und die Liste der erstellten Brücken. Außerdem steuert sie die Erstellung von Brücken
 * in verschiedenen Richtungen von der ausgewählten Insel.
 */
public class CreateAndDrawBridges {




	// Die X-Koordinate des Zentrums.
	private int centerX;

	// Die Y-Koordinate des Zentrums.
	private int centerY;


	// Berechnung des Zentrums.
	private CalculateCenter calculateCenter;

	
	/**
	 * Diese Klasse verwaltet die Erstellung und das Zeichnen von Brücken im Spiel.
	 * Sie initialisiert auch andere Hilfsklassen und Controller, die für das Brücken-Handling benötigt werden.
	 *
	 * @param islandList Die Liste der Inseln im Spiel.
	 * @param data       Die Spieldaten, die von der Anwendung verwendet werden.
	 */
	public CreateAndDrawBridges(ArrayList<Island> islandList, GameData data) {

		// Initialisieren der Klasse mit den übergebenen Inseln und den Spieldaten.
	    initClass(islandList, data);
	}

	
	/**
	 * Initialisiert die Klasse mit den erforderlichen Daten und Werten, um Brücken zu erstellen und zu zeichnen.
	 *
	 * @param islandList Die Liste der Inseln im Spiel.
	 * @param data       Die Spieldaten, die von der Anwendung verwendet werden.
	 */
	public void initClass(ArrayList<Island> islandList, GameData data) {
	    // Holen der Gitterwerte und des Zeichen-Panels aus den Spieldaten
	    CalculateGrid gridValues = data.getGridValues();

	    // Holen der Listen der Brücken aus den Spieldaten
	    ArrayList<CreateBridges> listOfB = data.getBridgesList();



	    // Initialisieren von Werten wie Zentrums-Koordinaten und Abstand (delta)
	    this.centerX = gridValues.getXDistance();
	    this.centerY = gridValues.getYDistance();


	    // Wenn vorhanden, die Brücken aus den Spieldaten übernehmen
	    if (listOfB != null) {
	        //listOfBridge = listOfB;
	    	//data.getBridgesList().clear();
	        //data.setBridgesList(listOfB);
	    } else 
	    	data.setBridgesList(new ArrayList<CreateBridges>());

	    
	    // Initialisieren der Klasse zum Berechnen der Zentren der Inseln
	    calculateCenter = new CalculateCenter();

	    // Berechnen der Zentren für jede Insel
	    islandList = calculateCenter.calculateCenterForEachIsland(islandList, centerX, centerY);
	    data.setDeepCopy(islandList);
	}




	/**
	 * Gibt die X-Koordinate des Zentrums zurück.
	 *
	 * @return Die X-Koordinate des Zentrums.
	 */
	public int getCenterX() {
		return centerX;
	}

	/**
	 * Setzt die X-Koordinate des Zentrums.
	 *
	 * @param centerX Die X-Koordinate des Zentrums, die gesetzt werden soll.
	 */
	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	/**
	 * Gibt die Y-Koordinate des Zentrums zurück.
	 *
	 * @return Die Y-Koordinate des Zentrums.
	 */
	public int getCenterY() {
		return centerY;
	}

	/**
	 * Setzt die Y-Koordinate des Zentrums.
	 *
	 * @param centerY Die Y-Koordinate des Zentrums, die gesetzt werden soll.
	 */
	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}




}
