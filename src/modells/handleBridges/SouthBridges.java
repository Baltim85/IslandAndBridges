package modells.handleBridges;

import Modell.CreateBridges;
import Modell.Island;
import modells.GameData.GameData;
import modells.Support.Directions;


/**
 * Die Klasse `SouthBridges` ist ein Teil des Bridge-Bauprozesses im Brückenrätselspiel und ist zuständig für das Erstellen
 *  von Brücken in südlicher Richtung von einer ausgewählten Insel. Hier werden die Spiellogik und die Berechnungen für die 
 *  Brückenerstellung und -überprüfung durchgeführt.
 *
 * Attribute:
 * - `data`: Die Spieldaten, die in diesem Controller verwendet werden.
 * - `selectedID`: Die ID der ausgewählten Insel.
 * - `updateBridgeCounter`: Ein Objekt zur Aktualisierung des Brückenzählers im Spiel.
 * - `lookForAnotherBridge`: Ein Objekt zum Suchen nach anderen Brücken.
 * - `drawLineY` und `drawLineY2`: Die Y-Koordinaten für das Zeichnen der Brücke.
 * - `id`: Die ID einer Insel.
 * - `selectedX` und `selectedY`: Die Koordinaten der ausgewählten Insel.
 * - `islandX` und `islandY`: Die Koordinaten einer anderen Insel.
 * - `centerX` und `centerY`: Die Koordinaten des Zentrums.
 *
 * Konstruktor:
 * - `SouthBridges(GameData data, LookForAnotherBridge lookForAnotherBridge, UpdateBridgeCounter updateBridgeCounter)`: 
 * Erzeugt einen `SouthBridges`-Controller mit den benötigten Spiel- und Brückendaten.
 *
 * Methoden:
 * - `createSouthBridge(boolean leftButton, int selectedID)`: Erstellt eine Brücke in südlicher Richtung von der ausgewählten Insel, 
 * basierend auf Benutzereingaben und Spielregeln.
 * - `anotherBridge(Directions direction, int x, int y, int position)`: Überprüft, ob eine andere Brücke in der angegebenen 
 * Richtung und Position vorhanden ist.
 * - `handleBridgeCreation(boolean leftButton)`: Behandelt die Erstellung einer Brücke basierend auf den bereitgestellten Eingaben.
 * - `addNewBridge()`: Fügt eine neue Brücke hinzu und aktualisiert die Spiel- und Brückendaten.
 * - `delta(int y1, int y2)`: Aktualisiert die Zeichnungskoordinaten für die Linie basierend auf der ausgewählten Richtung und 
 * den y-Koordinaten.
 * - Weitere Getter und Setter-Methoden für die Attribute der Klasse.
 */
public class SouthBridges implements CreateNewBridges {
	
	
	private GameData data;                 // Die Spieldaten, die in diesem Controller verwendet werden.

	private int selectedID;                // Die ID der ausgewählten Insel.

	private int drawLineY, drawLineY2;      // Die Y-Koordinaten für das Zeichnen der Brücke.

	private int id;                         // Die ID einer Insel.
	private int selectedX, selectedY;       // Die Koordinaten der ausgewählten Insel.
	private int islandX, islandY;           // Die Koordinaten einer anderen Insel.
	private int centerX, centerY;           // Die Koordinaten des Zentrums.

	
	
	/**
	 * Initialisiert eine Instanz der SouthBridges-Klasse mit den angegebenen Spieldaten.
	 *
	 * @param data Die Spieldaten, in denen die Brückeninformationen verwaltet werden.
	 */
	public SouthBridges(GameData data) {
		this.data = data;
	}

	/**
	 * Erstellt eine Brücke in südlicher Richtung von der ausgewählten Insel.
	 *
	 * @param leftButton  Gibt an, ob die linke Maustaste gedrückt wurde.
	 * @param selectedID  Die ID der ausgewählten Insel.
	 */
	@Override
	public void createSouthBridge(boolean leftButton, int selectedID) {
		// Die ausgewählte Insel abrufen
		setSelectedID(selectedID);

		// Die Koordinaten der ausgewählten Insel abrufen
		setSelectedX(CreateNewBridges.getSelectedX(selectedID, data));
		setSelectedY(CreateNewBridges.getSelectedY(selectedID, data));
		setCenterX(CreateNewBridges.getCenterX(selectedID, data));
		setCenterY(CreateNewBridges.getCenterY(selectedID, data));
		
		 // Schleife zur Suche nach potenziell verbundenen Inseln in östlicher Richtung
		for (int i = selectedY + 1; i < data.getHeight() + 1; i++) {
			for (Island island : data.getDeepCopy()) {
				setIslandX(island.getX());
				setIslandY(island.getY());
				setId(island.getId());
				int secondCenterY = island.getCenterY();
				
				// Den Abstand zwischen den Zentren der Inseln berechnen
				delta(getCenterY(), secondCenterY);

				// Überprüfen, ob bereits Brücken vorhanden sind				
				if(CreateNewBridges.anotherBridge(Directions.SOUTH, getSelectedX(), 0, i, data))
					return;
				// Überprüfen, ob die aktuelle Insel mit der ausgewählten Insel verbunden werden kann				
				if (getIslandX() == getSelectedX() && getId() != getSelectedID() && getIslandY() == i) {
	                // Versuch, eine Brücke zu erstellen
					handleBridgeCreation(leftButton);
					return;
				}
			}

		}
		return;
	}		

	/**
	 * Fügt eine neue Brücke hinzu und aktualisiert die entsprechenden Daten.
	 */
	@Override
	public void addNewBridge() {
		data.getBridgesList().add(new CreateBridges(getSelectedID(), getSelectedX(), getSelectedY(), getId(), getIslandX(), getIslandY(), 1));
		
		CreateBridges bridge = new CreateBridges(getSelectedID(), getCenterX(), getDrawLineY(), getId(), getCenterX(), getDrawLineY2());
		data.getUpdateBridgeCounter().decrementBridgeCounter(getId(), getSelectedID(), data.getDeepCopy());
		data.getGrid().getBridges().add(bridge);
		
	}

	/**
	 * Aktualisiert die Zeichnungskoordinaten für die Linie basierend auf der ausgewählten Richtung und den y-Koordinaten.
	 *
	 * @param y1        Die y-Koordinate des ersten Endpunkts der Linie.
	 * @param y2        Die y-Koordinate des zweiten Endpunkts der Linie.
	 */
	@Override
	public void delta(int y1, int y2) {
		int delta = data.getDelta();
		setDrawLineY(y1 + (delta / 2) + 3);
		setDrawLineY2(y2 - (delta / 2) - 3);	

	}


	/**
	 * Behandelt die Erstellung von Brücken basierend auf den bereitgestellten Eingaben.
	 *
	 * @param leftButton Gibt an, ob die linke Maustaste gedrückt wurde.
	 */
	@Override
	public void handleBridgeCreation(boolean leftButton) {
	    // Überprüfen, ob die Liste der Brücken leer ist und ob die linke Maustaste gedrückt wurde.
	    if (data.getBridgesList().isEmpty() && leftButton) {
	        // Füge eine neue Brücke hinzu, da keine Brücken vorhanden sind.
	        addNewBridge();
	        return;
	    } else {

	        // Überprüfe, ob das Hinzufügen einer Brücke möglich ist.
	        
	        if (data.getAddAndRemoveBridges().removeAndAddDoubleBridges(getId(), leftButton, getCenterX(), getDrawLineY(), getCenterX(), getDrawLineY2(), Directions.SOUTH, getSelectedID())) {
	            // Füge eine neue Brücke hinzu, wenn das Hinzufügen möglich ist.
	        	return;
	        } 
	        if(leftButton)
	        	addNewBridge();
	    }
	}
	
	
	/**
	 * Legt die ausgewählte ID fest.
	 *
	 * @param selectedID Die ausgewählte ID.
	 */
	public void setSelectedID(int selectedID) {
	    this.selectedID = selectedID;
	}

	/**
	 * Gibt die y-Koordinate der ersten Brückenposition zurück.
	 *
	 * @return Die y-Koordinate der ersten Brückenposition.
	 */
	public int getDrawLineY() {
	    return drawLineY;
	}

	/**
	 * Legt die y-Koordinate der ersten Brückenposition fest.
	 *
	 * @param drawLineY Die y-Koordinate der ersten Brückenposition.
	 */
	public void setDrawLineY(int drawLineY) {
	    this.drawLineY = drawLineY;
	}

	/**
	 * Gibt die y-Koordinate der zweiten Brückenposition zurück.
	 *
	 * @return Die y-Koordinate der zweiten Brückenposition.
	 */
	public int getDrawLineY2() {
	    return drawLineY2;
	}

	/**
	 * Legt die y-Koordinate der zweiten Brückenposition fest.
	 *
	 * @param drawLineY2 Die y-Koordinate der zweiten Brückenposition.
	 */
	public void setDrawLineY2(int drawLineY2) {
	    this.drawLineY2 = drawLineY2;
	}

	/**
	 * Gibt die ID zurück.
	 *
	 * @return Die ID.
	 */
	public int getId() {
	    return id;
	}

	/**
	 * Legt die ID fest.
	 *
	 * @param id Die ID.
	 */
	public void setId(int id) {
	    this.id = id;
	}

	/**
	 * Gibt die x-Koordinate der ausgewählten Insel zurück.
	 *
	 * @return Die x-Koordinate der ausgewählten Insel.
	 */
	public int getSelectedX() {
	    return selectedX;
	}

	/**
	 * Legt die x-Koordinate der ausgewählten Insel fest.
	 *
	 * @param selectedX Die x-Koordinate der ausgewählten Insel.
	 */

	public void setSelectedX(int selectedX) {
	    this.selectedX = selectedX;
	}

	/**
	 * Gibt die y-Koordinate der ausgewählten Insel zurück.
	 *
	 * @return Die y-Koordinate der ausgewählten Insel.
	 */
	public int getSelectedY() {
	    return selectedY;
	}
	

	/**
	 * Gibt die Spieldaten zurück.
	 *
	 * @return Die Spieldaten.
	 */
	public GameData getData() {
	    return data;
	}

	/**
	 * Legt die Spieldaten fest.
	 *
	 * @param data Die Spieldaten.
	 */
	public void setData(GameData data) {
	    this.data = data;
	}

	/**
	 * Gibt die ausgewählte ID zurück.
	 *
	 * @return Die ausgewählte ID.
	 */
	public int getSelectedID() {
	    return selectedID;
	}

	/**
	 * Legt die y-Koordinate der ausgewählten Insel fest.
	 *
	 * @param selectedY Die y-Koordinate der ausgewählten Insel.
	 */
	public void setSelectedY(int selectedY) {
	    this.selectedY = selectedY;
	}

	/**
	 * Gibt die x-Koordinate der aktuellen Insel zurück.
	 *
	 * @return Die x-Koordinate der aktuellen Insel.
	 */
	public int getIslandX() {
	    return islandX;
	}

	/**
	 * Legt die x-Koordinate der aktuellen Insel fest.
	 *
	 * @param islandX Die x-Koordinate der aktuellen Insel.
	 */

	public void setIslandX(int islandX) {
	    this.islandX = islandX;
	}

	/**
	 * Gibt die y-Koordinate der aktuellen Insel zurück.
	 *
	 * @return Die y-Koordinate der aktuellen Insel.
	 */
	public int getIslandY() {
	    return islandY;
	}

	/**
	 * Legt die y-Koordinate der aktuellen Insel fest.
	 *
	 * @param islandY Die y-Koordinate der aktuellen Insel.
	 */

	public void setIslandY(int islandY) {
	    this.islandY = islandY;
	}

	/**
	 * Gibt die x-Koordinate des Zentrums zurück.
	 *
	 * @return Die x-Koordinate des Zentrums.
	 */
	public int getCenterX() {
	    return centerX;
	}

	/**
	 * Legt die x-Koordinate des Zentrums fest.
	 *
	 * @param centerX Die x-Koordinate des Zentrums.
	 */
	public void setCenterX(int centerX) {
	    this.centerX = centerX;
	}

	/**
	 * Gibt die y-Koordinate des Zentrums zurück.
	 *
	 * @return Die y-Koordinate des Zentrums.
	 */
	public int getCenterY() {
	    return centerY;
	}

	/**
	 * Legt die y-Koordinate des Zentrums fest.
	 *
	 * @param centerY Die y-Koordinate des Zentrums.
	 */
	public void setCenterY(int centerY) {
	    this.centerY = centerY;
	}

	@Override
	public void createEastBridge(boolean leftButton, int selectedID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createNorthBridge(boolean leftButton, int selectedID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createWestBridge(boolean leftButton, int selectedID) {
		// TODO Auto-generated method stub
		
	}

}
