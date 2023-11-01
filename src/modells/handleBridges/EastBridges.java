package modells.handleBridges;

import Modell.CreateBridges;
import Modell.Island;
import modells.GameData.GameData;
import modells.Support.Directions;

/**
 * Die Klasse `EastBridges` ist ein Teil des Bridge-Bauprozesses im Brückenrätselspiel und ist zuständig für das Erstellen von Brücken in östlicher Richtung von einer ausgewählten Insel. Hier werden die Spiellogik und die Berechnungen für die Brückenerstellung und -überprüfung durchgeführt.
 *
 * Attribute:
 * - `data`: Die Spieldaten, die in diesem Controller verwendet werden.
 * - `selectedID`: Die ID der ausgewählten Insel.
 * - `updateBridgeCounter`: Ein Objekt zur Aktualisierung des Brückenzählers im Spiel.
 * - `lookForAnotherBridge`: Ein Objekt zum Suchen nach anderen Brücken.
 * - `drawLineX` und `drawLineX2`: Die X-Koordinaten für das Zeichnen der Brücke.
 * - `id`: Die ID einer Insel.
 * - `selectedX` und `selectedY`: Die Koordinaten der ausgewählten Insel.
 * - `islandX` und `islandY`: Die Koordinaten einer anderen Insel.
 * - `centerX` und `centerY`: Die Koordinaten des Zentrums.
 *
 * Konstruktor:
 * - `EastBridges(GameData data, LookForAnotherBridge lookForAnotherBridge, UpdateBridgeCounter updateBridgeCounter)`: Erzeugt einen `EastBridges`-Controller mit den benötigten Spiel- und Brückendaten.
 *
 * Methoden:
 * - `createEastBridge(boolean leftButton, int selectedID)`: Erstellt eine Brücke in östlicher Richtung von der ausgewählten Insel, basierend auf Benutzereingaben und Spielregeln.
 * - `anotherBridge(Directions direction, int x, int y, int position)`: Überprüft, ob eine andere Brücke in der angegebenen Richtung und Position vorhanden ist.
 * - `handleBridgeCreation(boolean leftButton)`: Behandelt die Erstellung einer Brücke basierend auf den bereitgestellten Eingaben.
 * - `addNewBridge()`: Fügt eine neue Brücke hinzu und aktualisiert die Spiel- und Brückendaten.
 * - `delta(int x1, int x2)`: Aktualisiert die Zeichnungskoordinaten für die Linie basierend auf der ausgewählten Richtung und den x-Koordinaten.
 * - Weitere Getter und Setter-Methoden für die Attribute der Klasse.
 */
public class EastBridges implements CreateNewBridges{
	
	private GameData data;                 // Die Spieldaten, die in diesem Controller verwendet werden.

	private int selectedID;                // Die ID der ausgewählten Insel.


	private int drawLineX, drawLineX2;      // Die X-Koordinaten für das Zeichnen der Brücke.

	private int id;                         // Die ID einer Insel.
	private int selectedX, selectedY;       // Die Koordinaten der ausgewählten Insel.
	private int islandX, islandY;           // Die Koordinaten einer anderen Insel.
	private int centerX, centerY;           // Die Koordinaten des Zentrums.

	/**
	 * Initialisiert eine Instanz der EastBridges-Klasse mit den angegebenen Spieldaten.
	 *
	 * @param data Die Spieldaten, in denen die Brückeninformationen verwaltet werden.
	 */
	public EastBridges(GameData data) {
		this.data = data;
	}
	
	/**
	 * Erstellt eine Brücke in östlicher Richtung von der ausgewählten Insel.
	 *
	 * @param leftButton  Gibt an, ob die linke Maustaste gedrückt wurde.
	 * @param selectedID  Die ID der ausgewählten Insel.
	 */
	@Override
	public void createEastBridge(boolean leftButton, int selectedID) {
	    // Die ausgewählte Insel abrufen
	    setSelectedID(selectedID);

	    // Die Koordinaten der ausgewählten Insel abrufen
	    setSelectedX(CreateNewBridges.getSelectedX(selectedID, data));
	    setSelectedY(CreateNewBridges.getSelectedY(selectedID, data));
	    setCenterX(CreateNewBridges.getCenterX(selectedID, data));
	    setCenterY(CreateNewBridges.getCenterY(selectedID, data));

	    // Schleife zur Suche nach potenziell verbundenen Inseln in östlicher Richtung
	    for (int i = selectedX + 1; i < data.getWidth() + 1; i++) {
	        for (Island island : data.getDeepCopy()) {
	            setIslandX(island.getX());
	            setIslandY(island.getY());
	            setId(island.getId());
	            int secondCenterX = island.getCenterX();

	            // Den Abstand zwischen den Zentren der Inseln berechnen
	            delta(getCenterX(), secondCenterX);

	            // Überprüfen, ob bereits Brücken vorhanden sind
	            if (CreateNewBridges.anotherBridge(Directions.EAST, 0, getSelectedY(), i, data))
	                return;

	            // Überprüfen, ob die aktuelle Insel mit der ausgewählten Insel verbunden werden kann
	            if (getIslandY() == getSelectedY() && getId() != getSelectedID() && getIslandX() == i) {
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
		CreateBridges bridge = new CreateBridges(getSelectedID(), getDrawLineX(), getCenterY(), getId(), getDrawLineX2(), getCenterY());

		data.getUpdateBridgeCounter().decrementBridgeCounter(getId(), getSelectedID(), data.getDeepCopy());

		data.getGrid().getBridges().add(bridge);
		
	}

	/**
	 * Aktualisiert die Zeichnungskoordinaten für die Linie basierend auf der ausgewählten Richtung und den x-Koordinaten.
	 *
	 * @param x1        Die x-Koordinate des ersten Endpunkts der Linie.
	 * @param x2        Die x-Koordinate des zweiten Endpunkts der Linie.
	 */
	@Override
	public void delta(int x1, int x2) {
		int delta = data.getDelta();
		setDrawLineX(x1 + (delta / 2) + 3);
		setDrawLineX2(x2 - (delta / 2) - 3);
		
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
	        
	        if (data.getAddAndRemoveBridges().removeAndAddDoubleBridges(getId(), leftButton, getDrawLineX(), getCenterY(), getDrawLineX2(), getCenterY(), Directions.EAST, getSelectedID())) {
	            // Füge eine neue Brücke hinzu, wenn das Hinzufügen möglich ist.
	        	return;
	        } 
	        if(leftButton)
	        	addNewBridge();
	    }
		
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
	 * Setzt die x-Koordinate des Zentrums.
	 *
	 * @param centerX Die zu setzende x-Koordinate des Zentrums.
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
	 * Setzt die y-Koordinate des Zentrums.
	 *
	 * @param centerY Die zu setzende y-Koordinate des Zentrums.
	 */
	public void setCenterY(int centerY) {
	    this.centerY = centerY;
	}

	/**
	 * Gibt die ID des Objekts zurück.
	 *
	 * @return Die ID des Objekts.
	 */
	public int getId() {
	    return id;
	}

	/**
	 * Setzt die ID des Objekts.
	 *
	 * @param id Die zu setzende ID des Objekts.
	 */
	public void setId(int id) {
	    this.id = id;
	}

	
	/**
	 * Gibt die ausgewählte x-Koordinate zurück.
	 *
	 * @return Die ausgewählte x-Koordinate.
	 */
	public int getSelectedX() {
	    return selectedX;
	}

	/**
	 * Setzt die ausgewählte x-Koordinate.
	 *
	 * @param selectedX Die zu setzende ausgewählte x-Koordinate.
	 */
	public void setSelectedX(int selectedX) {
	    this.selectedX = selectedX;
	}

	/**
	 * Gibt die ausgewählte y-Koordinate zurück.
	 *
	 * @return Die ausgewählte y-Koordinate.
	 */
	public int getSelectedY() {
	    return selectedY;
	}

	/**
	 * Setzt die ausgewählte y-Koordinate.
	 *
	 * @param selectedY Die zu setzende ausgewählte y-Koordinate.
	 */
	public void setSelectedY(int selectedY) {
	    this.selectedY = selectedY;
	}

	/**
	 * Gibt die x-Koordinate der Insel zurück.
	 *
	 * @return Die x-Koordinate der Insel.
	 */
	public int getIslandX() {
	    return islandX;
	}

	/**
	 * Setzt die x-Koordinate der Insel.
	 *
	 * @param islandX Die zu setzende x-Koordinate der Insel.
	 */
	public void setIslandX(int islandX) {
	    this.islandX = islandX;
	}

	/**
	 * Gibt die y-Koordinate der Insel zurück.
	 *
	 * @return Die y-Koordinate der Insel.
	 */
	public int getIslandY() {
	    return islandY;
	}

	/**
	 * Setzt die y-Koordinate der Insel.
	 *
	 * @param islandY Die zu setzende y-Koordinate der Insel.
	 */
	public void setIslandY(int islandY) {
	    this.islandY = islandY;
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
	 * Setzt die ausgewählte ID.
	 *
	 * @param selectedID Die zu setzende ausgewählte ID.
	 */
	public void setSelectedID(int selectedID) {
	    this.selectedID = selectedID;
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
	 * Setzt die Spieldaten.
	 *
	 * @param data Die zu setzenden Spieldaten.
	 */
	public void setData(GameData data) {
	    this.data = data;
	}


	/**
	 * Gibt die x-Koordinate für das Zeichnen einer Linie zurück.
	 *
	 * @return Die x-Koordinate für das Zeichnen einer Linie.
	 */
	public int getDrawLineX() {
	    return drawLineX;
	}

	/**
	 * Setzt die x-Koordinate für das Zeichnen einer Linie.
	 *
	 * @param drawLineX Die zu setzende x-Koordinate für das Zeichnen einer Linie.
	 */
	public void setDrawLineX(int drawLineX) {
	    this.drawLineX = drawLineX;
	}

	/**
	 * Gibt die zweite x-Koordinate für das Zeichnen einer Linie zurück.
	 *
	 * @return Die zweite x-Koordinate für das Zeichnen einer Linie.
	 */
	public int getDrawLineX2() {
	    return drawLineX2;
	}

	/**
	 * Setzt die zweite x-Koordinate für das Zeichnen einer Linie.
	 *
	 * @param drawLineX2 Die zu setzende zweite x-Koordinate für das Zeichnen einer Linie.
	 */
	public void setDrawLineX2(int drawLineX2) {
	    this.drawLineX2 = drawLineX2;
	}

	@Override
	public void createNorthBridge(boolean leftButton, int selectedID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createSouthBridge(boolean leftButton, int selectedID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createWestBridge(boolean leftButton, int selectedID) {
		// TODO Auto-generated method stub
		
	}



}
