package Modell;

import java.util.ArrayList;


/**
 * Die Klasse "Island" repräsentiert eine Insel im Spiel. Sie speichert Informationen über die Position der Insel,
 * ihre eindeutige ID, die Anzahl der mit ihr verbundenen Brücken und die Richtungen, in denen Brücken zu benachbarten
 * Inseln führen können. Diese Klasse ermöglicht die Erstellung und Verwaltung von Inseln im Spiel.
 */
public class Island {

	// X- und Y-Koordinaten der aktuellen Insel
	private int x, y;

	// Eindeutige ID für die Insel
	private int id;

	// Zähler für die Anzahl der Brücken, die mit dieser Insel verbunden sind
	private int bridgeCount;

	// Richtungen, in denen Brücken von dieser Insel aus gebaut werden können
	private boolean north, east, south, west;

	// X- und Y-Koordinaten der Insel (könnte redundant sein)
	private int islandX, islandY;

	// Ein Flag, das angibt, ob die Insel korrekt erstellt wurde
	private boolean isOk = false;

	// Liste der Inseln im Spiel
	private ArrayList<Island> listofIslands;


	// X- und Y-Koordinaten des Zentrums (könnte redundant sein)
	private int centerX, centerY;


	public Island() {


	}

	/**
	 * Dieser Konstruktor erstellt ein Objekt der Klasse Island mit den angegebenen Koordinaten, einer ID,
	 * einer Liste von Inseln, einer Brückenzahl und den Richtungen, in denen Brücken zu benachbarten Inseln führen.
	 *
	 * @param x             Die x-Koordinate der Insel.
	 * @param y             Die y-Koordinate der Insel.
	 * @param id            Die eindeutige ID der Insel.
	 * @param listofIslands Eine Liste von Inseln, zu der diese Insel gehört.
	 * @param bridgeCount   Die Anzahl der Brücken, die mit dieser Insel verbunden sind.
	 * @param north         True, wenn eine Brücke in nördlicher Richtung führt, andernfalls False.
	 * @param east          True, wenn eine Brücke in östlicher Richtung führt, andernfalls False.
	 * @param south         True, wenn eine Brücke in südlicher Richtung führt, andernfalls False.
	 * @param west          True, wenn eine Brücke in westlicher Richtung führt, andernfalls False.
	 */
	public Island(int x, int y, int id, ArrayList<Island> listofIslands, int bridgeCount, boolean north, boolean east,
			boolean south, boolean west, int centerX, int centerY) {
		this.x = x;
		this.y = y;
		this.id = id;
		this.bridgeCount = bridgeCount;
		this.north = north;
		this.east = east;
		this.south = south;
		this.west = west;
		this.listofIslands = listofIslands;
		this.centerX = centerX;
		this.centerY = centerY;

	}


	/**
	 * Dieser Konstruktor erstellt ein Objekt der Klasse Island mit den angegebenen Koordinaten, einer ID und einer
	 * Anzahl von Brücken.
	 *
	 * @param x           Die x-Koordinate der Insel.
	 * @param y           Die y-Koordinate der Insel.
	 * @param id          Die eindeutige ID der Insel.
	 * @param bridgeCount Die Anzahl der Brücken, die mit dieser Insel verbunden sind.
	 */
	public Island(int x, int y, int id, int bridgeCount) {
		this.x = x;
		this.y = y;
		this.id = id;
		this.bridgeCount = bridgeCount;


	}

	



	/*****************************************************************************************************************************/
	/*****************************************************GETTER******************************************************************/
	/*****************************************************************************************************************************/

	/**
	 * @return the islandX
	 */
	public int getIslandX() {
		return islandX;
	}


	/**
	 * @param islandX the islandX to set
	 */
	public void setIslandX(int islandX) {
		this.islandX = islandX;
	}




	/**
	 * @return the islandY
	 */
	public int getIslandY() {
		return islandY;
	}




	/**
	 * Setzt den Y-Wert der Insel.
	 *
	 * @param islandY der Y-Wert, der gesetzt werden soll
	 */
	public void setIslandY(int islandY) {
		this.islandY = islandY;
	}



	/**
	 * Gibt eine Zeichenfolge zurück, die die X-Koordinate der Insel darstellt.
	 *
	 * @return eine Zeichenfolge mit der X-Koordinate der Insel
	 */
	public String toString() {
		String text = "X: " + x;
		return text;
	}

	/**
	 * @return die Anzahl der Brücken, die mit dieser Insel verbunden sind
	 */
	public int getBridgeCount() {
		return bridgeCount;
	}




	/**
	 * Setzt die Anzahl der Brücken, die mit dieser Insel verbunden sind.
	 *
	 * @param bridgeCount die Anzahl der Brücken, die gesetzt werden soll
	 */
	public void setBridgeCount(int bridgeCount) {
		this.bridgeCount = bridgeCount;
	}

	/**
	 * Überprüft, ob eine Brücke im Norden dieser Insel existiert.
	 *
	 * @return true, wenn eine Brücke im Norden existiert, sonst false
	 */
	public boolean isNorth() {
		return north;
	}

	/**
	 * Setzt den Wert, ob eine Brücke im Norden dieser Insel existiert.
	 *
	 * @param north true, wenn eine Brücke im Norden existiert, sonst false
	 */
	public void setNorth(boolean north) {
		this.north = north;
	}

	/**
	 * Überprüft, ob eine Brücke im Osten dieser Insel existiert.
	 *
	 * @return true, wenn eine Brücke im Osten existiert, sonst false
	 */
	public boolean isEast() {
		return east;
	}

	/**
	 * Setzt den Wert, ob eine Brücke im Osten dieser Insel existiert.
	 *
	 * @param east true, wenn eine Brücke im Osten existiert, sonst false
	 */
	public void setEast(boolean east) {
		this.east = east;
	}

	/**
	 * Überprüft, ob eine Brücke im Süden dieser Insel existiert.
	 *
	 * @return true, wenn eine Brücke im Süden existiert, sonst false
	 */
	public boolean isSouth() {
		return south;
	}

	/**
	 * Setzt den Wert, ob eine Brücke im Süden dieser Insel existiert.
	 *
	 * @param south true, wenn eine Brücke im Süden existiert, sonst false
	 */
	public void setSouth(boolean south) {
		this.south = south;
	}

	/**
	 * Überprüft, ob eine Brücke im Westen dieser Insel existiert.
	 *
	 * @return true, wenn eine Brücke im Westen existiert, sonst false
	 */
	public boolean isWest() {
		return west;
	}




	/**
	 * Setzt den Wert, ob eine Brücke im Westen dieser Insel existiert.
	 *
	 * @param west true, wenn eine Brücke im Westen existiert, sonst false
	 */
	public void setWest(boolean west) {
		this.west = west;
	}

	/**
	 * Gibt die Liste der Inseln zurück.
	 *
	 * @return die Liste der Inseln
	 */
	public ArrayList<Island> getListofIslands() {
		return listofIslands;
	}

	/**
	 * Setzt die Liste der Inseln.
	 *
	 * @param listofIslands die Liste der Inseln, die gesetzt werden soll
	 */
	public void setListofIslands(ArrayList<Island> listofIslands) {
		this.listofIslands = listofIslands;
	}

	/**
	 * Gibt die X-Koordinate dieser Insel zurück.
	 *
	 * @return die X-Koordinate der Insel
	 */
	public int getX() {
		return x;
	}

	/**
	 * Setzt die X-Koordinate dieser Insel.
	 *
	 * @param x die X-Koordinate, die gesetzt werden soll
	 */
	public void setX(int x) {
		this.x = x;
	}


	/**
	 * Gibt die Y-Koordinate dieser Insel zurück.
	 *
	 * @return die Y-Koordinate der Insel
	 */
	public int getY() {
		return y;
	}

	/**
	 * Setzt die Y-Koordinate dieser Insel.
	 *
	 * @param y die Y-Koordinate, die gesetzt werden soll
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Gibt die ID dieser Insel zurück.
	 *
	 * @return die ID der Insel
	 */
	public int getId() {
		return id;
	}

	/**
	 * Setzt die ID dieser Insel.
	 *
	 * @param id die ID, die gesetzt werden soll
	 */
	public void setId(int id) {
		this.id = id;
	}




	/**
	 * Überprüft, ob diese Insel korrekt platziert ist.
	 *
	 * @return true, wenn die Insel korrekt platziert ist, andernfalls false
	 */
	public boolean isOk() {
		return isOk;
	}

	/**
	 * Setzt den Zustand, ob diese Insel korrekt platziert ist oder nicht.
	 *
	 * @param isOk true, wenn die Insel korrekt platziert ist, andernfalls false
	 */
	public void setOk(boolean isOk) {
		this.isOk = isOk;
	}

	/**
	 * Gibt die X-Koordinate des Zentrums dieser Insel zurück.
	 *
	 * @return die X-Koordinate des Zentrums der Insel
	 */
	public int getCenterX() {
		return centerX;
	}

	/**
	 * Setzt die X-Koordinate des Zentrums dieser Insel.
	 *
	 * @param centerX die X-Koordinate des Zentrums der Insel
	 */
	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	/**
	 * Gibt die Y-Koordinate des Zentrums dieser Insel zurück.
	 *
	 * @return die Y-Koordinate des Zentrums der Insel
	 */
	public int getCenterY() {
		return centerY;
	}

	/**
	 * Setzt die Y-Koordinate des Zentrums dieser Insel.
	 *
	 * @param centerY die Y-Koordinate des Zentrums der Insel
	 */
	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}



}