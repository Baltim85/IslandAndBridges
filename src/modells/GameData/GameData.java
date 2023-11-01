package modells.GameData;

import java.util.ArrayList;
import java.util.HashMap;

import Controller.BridgeController;
import Controller.DialogController;
import Controller.MenuController;
import Modell.CalculateGrid;
import Modell.CreateAndDrawBridges;
import Modell.CreateBridges;
import Modell.GridPainter;
import Modell.Island;
import Modell.load.LoadDataForGame;
import UI.Bridges;
import modells.Support.CreateRandomPuzzle;
import modells.Support.DeepCopys;
import modells.Support.IsGameValid;
import modells.handleBridges.AddRemoveBridges;
import modells.handleBridges.DrawBridges;
import modells.handleBridges.EastBridges;
import modells.handleBridges.LookForAnotherBridge;
import modells.handleBridges.NorthBridges;
import modells.handleBridges.RemoveBridges;
import modells.handleBridges.SouthBridges;
import modells.handleBridges.UpdateBridgeCounter;
import modells.handleBridges.WestBridges;



/**
 * Die Klasse GameData beschreibt den Zweck und die Funktionen dieser Klasse.
 * Sie enthält verschiedene Felder, die für die Verwaltung von Daten und den Zustand
 * dieser Klasse verwendet werden.
 *
 * **Felder und Eigenschaften:**
 * - `bridges`: Eine Instanz für Brücken, die Informationen über die im Spiel vorhandenen Brücken enthält.
 * - `copyList`: Eine Instanz zur Erstellung von Kopien von Inseln im Spiel.
 * - `isGameValid`: Eine Instanz zur Überprüfung der Gültigkeit des Spiels.
 * - `randomGame`: Eine Instanz zur Erstellung zufälliger Spiele.
 * - `loadData`: Eine Instanz zur Datenübertragung für den Spielstand.
 * - `dialogController`: Eine Instanz für die Steuerung von Dialogfenstern im Spiel.
 * - `menuController`: Eine Instanz für die Steuerung des Spielmenüs.
 * - `height`: Die Höhe des Spielfelds.
 * - `width`: Die Breite des Spielfelds.
 * - `islands`: Die Anzahl der Inseln im Spiel.
 * - `grid`: Eine Instanz für das Zeichnen des Spielfelds.
 * - `island`: Die aktuell ausgewählte Insel.
 * - `gridValues`: Eine Instanz zur Berechnung von Spielfeldwerten.
 * - `listofIslands`: Eine Liste von Inseln im Spiel.
 * - `bridgesList`: Eine Liste von Brücken im Spiel.
 * - `bridgeC`: Eine Instanz des Brückencontrollers.
 * - `controllerExist`: Gibt an, ob der Controller für das Spiel existiert.
 * - `deepCopy`: Eine Liste von tiefen Kopien der Inseln.
 * - `gameExist`: Gibt an, ob ein Spiel existiert.
 * - `errorVisible`: Gibt an, ob Spiel-Fehler sichtbar sind.
 * - `gameWasLoaded`: Gibt an, ob ein Spiel geladen wurde.
 * - `createBridges`: Eine Instanz für das Erstellen und Zeichnen von Brücken.
 * - `delta`: Der Delta-Wert für die Berechnung von Brückenkoordinaten.
 * - `index`: Der aktuelle Index.
 * - `listOfBridges`: Eine Liste von erstellten Brücken.
 * - `possibleIslands`: Eine Liste von möglichen Inseln.
 * - `islandMap`: Eine Zuordnung von Insel-IDs zu Inseln.
 * - `northBridges`: Eine Instanz für die Verwaltung von Brücken im Norden.
 * - `southBridges`: Eine Instanz für die Verwaltung von Brücken im Süden.
 * - `eastBridges`: Eine Instanz für die Verwaltung von Brücken im Osten.
 * - `westBridges`: Eine Instanz für die Verwaltung von Brücken im Westen.
 * - `lookForAnotherBridge`: Eine Instanz für die Suche nach vorhandenen Brücken.
 * - `addAndRemoveBridges`: Eine Instanz für das Hinzufügen und Entfernen von Brücken.
 * - `removeBridges`: Eine Instanz für das Entfernen von Brücken.
 * - `updateBridgeCounter`: Eine Instanz für die Aktualisierung des Brückenzählers.
 * - `drawBridges`: Eine Instanz für das Zeichnen von Brücken.
 */
public class GameData {
	private Bridges bridges; // Instanz für Brücken
	private DeepCopys copyList; // Instanz für die Kopien von Spieldaten
	private IsGameValid isGameValid; // Instanz zur Überprüfung der Spielgültigkeit
	private CreateRandomPuzzle randomGame; // Instanz zur Erstellung eines zufälligen Spiels
	private LoadDataForGame loadData; // Instanz zum Laden von Spieldaten
	private DialogController dialogController; // Instanz für die Dialogsteuerung
	private MenuController menuController; // Instanz für die Menüsteuerung
	private int height; // Höhe des Spielfelds
	private int width; // Breite des Spielfelds
	private int islands; // Anzahl der Inseln im Spiel
	private GridPainter grid; // Instanz für das Zeichnen des Spielfelds
	private Island island; // Aktuelle Insel
	private CalculateGrid gridValues; // Instanz für die Berechnung von Gitterwerten
	private ArrayList<Island> listofIslands; // Liste der Inseln im Spiel
	private ArrayList<CreateBridges> bridgesList; // Liste der Brücken im Spiel

	private BridgeController bridgeC; // Instanz des Brückencontrollers
	private boolean controllerExist; // Gibt an, ob der Brückencontroller existiert
	private ArrayList<Island> deepCopy; // Kopie der Liste der Inseln
	private boolean gameExist; // Gibt an, ob ein Spiel existiert

	private boolean errorVisible; // Gibt an, ob ein Fehler sichtbar ist
	private boolean gameWasLoaded; // Gibt an, ob ein Spiel geladen wurde
	private CreateAndDrawBridges createBridges; // Instanz zum Erstellen und Zeichnen von Brücken
	private int delta; // Delta-Wert für die Berechnung von Brückenkoordinaten



	private int index; // Der aktuelle Index
	private ArrayList<CreateBridges> listOfBridges; // Eine Liste von erstellten Brücken	
	private ArrayList<Island> possibleIslands; // Eine Liste von möglichen Inseln
	private HashMap<Integer, Island> islandMap; // Eine Zuordnung von Insel-IDs zu Inseln

	private NorthBridges northBridges; // Instanz für die Verwaltung von Brücken im Norden
	private SouthBridges southBridges; // Instanz für die Verwaltung von Brücken im Süden
	private EastBridges eastBridges; // Instanz für die Verwaltung von Brücken im Osten
	private WestBridges westBridges; // Instanz für die Verwaltung von Brücken im Westen

	private LookForAnotherBridge lookForAnotherBridge; // Instanz für die Suche nach vorhandenen Brücken
	private AddRemoveBridges addAndRemoveBridges; // Instanz für das Hinzufügen und Entfernen von Brücken
	private RemoveBridges removeBridges; // Instanz für das Entfernen von Brücken
	private UpdateBridgeCounter updateBridgeCounter; // Instanz für die Aktualisierung des Brückenzählers
	private DrawBridges drawBridges; // Instanz für das Zeichnen von Brücken



	/**
	 * Erstellt ein neues GameData-Objekt mit den angegebenen Anfangsdaten.
	 *
	 * @param listofIslands Eine Liste von Inseln, die im Spiel vorhanden sind.
	 * @param controllerExist Gibt an, ob der Controller für das Spiel existiert.
	 * @param deepCopy Eine Liste von tiefen Kopien der Inseln.
	 * @param gameExist Gibt an, ob das Spiel existiert oder aktiv ist.
	 */
	public GameData(ArrayList<Island> listofIslands, boolean controllerExist, ArrayList<Island> deepCopy, boolean gameExist) {
		// Initialisiert das GameData-Objekt mit den übergebenen Werten.
		this.listofIslands = listofIslands;
		this.controllerExist = controllerExist;
		this.deepCopy = deepCopy;
		this.gameExist = gameExist;
		setPossibleIslands(new ArrayList<>());
		setIslandMap(new HashMap<Integer, Island>());
		setListOfBridges(new ArrayList<CreateBridges>());
	}


	/**
	 * Gibt die Instanz der Klasse "Bridges" zurück, die Informationen über die im Spiel vorhandenen Brücken enthält.
	 *
	 * @return Eine Instanz der Klasse "Bridges".
	 */
	public Bridges getBridges() {
		return bridges;
	}

	/**
	 * Legt die Instanz der Klasse "Bridges" fest, um Informationen über die im Spiel vorhandenen Brücken zu speichern.
	 *
	 * @param bridges Die Instanz der Klasse "Bridges".
	 */
	public void setBridges(Bridges bridges) {
		this.bridges = bridges;
	}

	/**
	 * Gibt das Objekt zur Erstellung von Kopien von Inseln zurück.
	 *
	 * @return Ein "DeepCopys"-Objekt zur Erstellung von Kopien von Inseln.
	 */
	public DeepCopys getCopyList() {
		return copyList;
	}

	/**
	 * Legt das Objekt zur Erstellung von Kopien von Inseln fest.
	 *
	 * @param copyList Ein "DeepCopys"-Objekt zur Erstellung von Kopien von Inseln.
	 */
	public void setCopyList(DeepCopys copyList) {
		this.copyList = copyList;
	}

	/**
	 * Gibt das Objekt zur Validierung des Spiels zurück.
	 *
	 * @return Ein "IsGameValid"-Objekt zur Validierung des Spiels.
	 */
	public IsGameValid getIsGameValid() {
		return isGameValid;
	}

	/**
	 * Legt das Objekt zur Validierung des Spiels fest.
	 *
	 * @param isGameValid Ein "IsGameValid"-Objekt zur Validierung des Spiels.
	 */
	public void setIsGameValid(IsGameValid isGameValid) {
		this.isGameValid = isGameValid;
	}

	/**
	 * Gibt das Objekt zur Erstellung zufälliger Spiele zurück.
	 *
	 * @return Ein "CreateRandomPuzzle"-Objekt zur Erstellung zufälliger Spiele.
	 */
	public CreateRandomPuzzle getRandomGame() {
		return randomGame;
	}

	/**
	 * Legt das Objekt zur Erstellung zufälliger Spiele fest.
	 *
	 * @param randomGame Ein "CreateRandomPuzzle"-Objekt zur Erstellung zufälliger Spiele.
	 */
	public void setRandomGame(CreateRandomPuzzle randomGame) {
		this.randomGame = randomGame;
	}


	/**
	 * Gibt das Objekt zur Datenübertragung für den Spielstand zurück.
	 *
	 * @return Ein "LoadDataForGame"-Objekt zur Datenübertragung für den Spielstand.
	 */
	public LoadDataForGame getLoadData() {
		return loadData;
	}

	/**
	 * Legt das Objekt zur Datenübertragung für den Spielstand fest.
	 *
	 * @param loadData Ein "LoadDataForGame"-Objekt zur Datenübertragung für den Spielstand.
	 */
	public void setLoadData(LoadDataForGame loadData) {
		this.loadData = loadData;
	}

	/**
	 * Gibt den Controller für Dialogfenster im Spiel zurück.
	 *
	 * @return Ein "DialogController"-Objekt für Dialogfenster im Spiel.
	 */
	public DialogController getDialogController() {
		return dialogController;
	}

	/**
	 * Legt den Controller für Dialogfenster im Spiel fest.
	 *
	 * @param dialogController Ein "DialogController"-Objekt für Dialogfenster im Spiel.
	 */
	public void setDialogController(DialogController dialogController) {
		this.dialogController = dialogController;
	}

	/**
	 * Gibt den Controller für das Spielmenü zurück.
	 *
	 * @return Ein "MenuController"-Objekt für das Spielmenü.
	 */
	public MenuController getMenuController() {
		return menuController;
	}

	/**
	 * Legt den Controller für das Spielmenü fest.
	 *
	 * @param menuController Ein "MenuController"-Objekt für das Spielmenü.
	 */
	public void setMenuController(MenuController menuController) {
		this.menuController = menuController;
	}

	/**
	 * Gibt die Höhe des Spielfelds zurück.
	 *
	 * @return Die Höhe des Spielfelds.
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Legt die Höhe des Spielfelds fest.
	 *
	 * @param height Die Höhe des Spielfelds.
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * Gibt die Breite des Spielfelds zurück.
	 *
	 * @return Die Breite des Spielfelds.
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Legt die Breite des Spielfelds fest.
	 *
	 * @param width Die Breite des Spielfelds.
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * Gibt die Anzahl der Inseln im aktuellen Spiel zurück.
	 *
	 * @return Die Anzahl der Inseln im aktuellen Spiel.
	 */
	public int getIslands() {
		return islands;
	}

	/**
	 * Legt die Anzahl der Inseln im aktuellen Spiel fest.
	 *
	 * @param islands Die Anzahl der Inseln im aktuellen Spiel.
	 */
	public void setIslands(int islands) {
		this.islands = islands;
	}

	/**
	 * Gibt das Objekt zur Darstellung des Spielfelds zurück.
	 *
	 * @return Ein "GridPainter"-Objekt zur Darstellung des Spielfelds.
	 */
	public GridPainter getGrid() {
		return grid;
	}

	/**
	 * Legt das Objekt zur Darstellung des Spielfelds fest.
	 *
	 * @param grid Ein "GridPainter"-Objekt zur Darstellung des Spielfelds.
	 */
	public void setGrid(GridPainter grid) {
		this.grid = grid;
	}

	/**
	 * Gibt das aktuell ausgewählte Inselobjekt zurück.
	 *
	 * @return Das aktuell ausgewählte "Island"-Objekt.
	 */
	public Island getIsland() {
		return island;
	}

	/**
	 * Legt das aktuell ausgewählte Inselobjekt fest.
	 *
	 * @param island Das aktuell ausgewählte "Island"-Objekt.
	 */
	public void setIsland(Island island) {
		this.island = island;
	}

	/**
	 * Gibt das Objekt zur Berechnung der Spielfeldwerte zurück.
	 *
	 * @return Ein "CalculateGrid"-Objekt zur Berechnung der Spielfeldwerte.
	 */
	public CalculateGrid getGridValues() {
		return gridValues;
	}

	/**
	 * Legt das Objekt zur Berechnung der Spielfeldwerte fest.
	 *
	 * @param gridValues Ein "CalculateGrid"-Objekt zur Berechnung der Spielfeldwerte.
	 */
	public void setGridValues(CalculateGrid gridValues) {
		this.gridValues = gridValues;
	}

	/**
	 * Gibt eine Liste von Inseln im aktuellen Spiel zurück.
	 *
	 * @return Eine Liste von "Island"-Objekten im aktuellen Spiel.
	 */
	public ArrayList<Island> getListofIslands() {
		return listofIslands;
	}

	/**
	 * Legt die Liste von Inseln im aktuellen Spiel fest.
	 *
	 * @param listofIslands Eine Liste von "Island"-Objekten im aktuellen Spiel.
	 */
	public void setListofIslands(ArrayList<Island> listofIslands) {
		this.listofIslands = listofIslands;
	}

	/**
	 * Gibt den BridgeController für das Spiel zurück.
	 *
	 * @return Der BridgeController für das Spiel.
	 */
	public BridgeController getBridgeC() {
		return bridgeC;
	}

	/**
	 * Legt den BridgeController für das Spiel fest.
	 *
	 * @param bridgeC Der BridgeController für das Spiel.
	 */
	public void setBridgeC(BridgeController bridgeC) {
		this.bridgeC = bridgeC;
	}

	/**
	 * Überprüft, ob der Controller existiert.
	 *
	 * @return True, wenn der Controller existiert, ansonsten False.
	 */
	public boolean isControllerExist() {
		return controllerExist;
	}

	/**
	 * Legt fest, ob der Controller existiert.
	 *
	 * @param controllerExist True, wenn der Controller existiert, ansonsten False.
	 */
	public void setControllerExist(boolean controllerExist) {
		this.controllerExist = controllerExist;
	}

	/**
	 * Gibt die tiefe Kopie der Liste von Inseln zurück.
	 *
	 * @return Eine Liste von "Island"-Objekten, die eine tiefe Kopie der ursprünglichen Liste darstellt.
	 */
	public ArrayList<Island> getDeepCopy() {
		return deepCopy;
	}

	/**
	 * Legt die tiefe Kopie der Liste von Inseln fest.
	 *
	 * @param deepCopy Eine Liste von "Island"-Objekten, die eine tiefe Kopie der ursprünglichen Liste darstellt.
	 */
	public void setDeepCopy(ArrayList<Island> deepCopy) {
		this.deepCopy = deepCopy;
	}

	/**
	 * Überprüft, ob ein Spiel existiert.
	 *
	 * @return True, wenn ein Spiel existiert, ansonsten False.
	 */
	public boolean isGameExist() {
		return gameExist;
	}

	/**
	 * Legt fest, ob ein Spiel existiert.
	 *
	 * @param gameExist True, wenn ein Spiel existiert, ansonsten False.
	 */
	public void setGameExist(boolean gameExist) {
		this.gameExist = gameExist;
	}

	/**
	 * Gibt die Liste der Brücken-Objekte zurück.
	 *
	 * @return Die Liste der Brücken-Objekte.
	 */
	public ArrayList<CreateBridges> getBridgesList() {
		return bridgesList;
	}

	/**
	 * Legt die Liste der Brücken-Objekte fest.
	 *
	 * @param bridgesList Die zu setzende Liste der Brücken-Objekte.
	 */
	public void setBridgesList(ArrayList<CreateBridges> bridgesList) {
		this.bridgesList = bridgesList;
	}


	/**
	 * Diese Methode gibt an, ob ein Spiel geladen wurde.
	 *
	 * @return true, wenn ein Spiel geladen wurde, andernfalls false.
	 */
	public boolean isGameWasLoaded() {
		return gameWasLoaded;
	}

	/**
	 * Setzt den Zustand, ob ein Spiel geladen wurde.
	 *
	 * @param gameWasLoaded true, wenn ein Spiel geladen wurde, andernfalls false.
	 */
	public void setGameWasLoaded(boolean gameWasLoaded) {
		this.gameWasLoaded = gameWasLoaded;
	}

	/**
	 * Diese Methode gibt den berechneten Wert "delta" zurück.
	 *
	 * @return Der berechnete Wert "delta".
	 */
	public int getDelta() {
		return delta;
	}

	/**
	 * Setzt den berechneten Wert "delta".
	 *
	 * @param delta Der zu setzende Wert für "delta".
	 */
	public void setDelta(int delta) {
		this.delta = delta;
	}

	/**
	 * Diese Methode gibt an, ob Spiel-Fehler sichtbar sind.
	 *
	 * @return true, wenn Spiel-Fehler sichtbar sind, andernfalls false.
	 */
	public boolean isErrorVisible() {
		return errorVisible;
	}

	/**
	 * Setzt den Zustand, ob Spiel-Fehler sichtbar sind.
	 *
	 * @param errorVisible true, wenn Spiel-Fehler sichtbar sein sollen, andernfalls false.
	 */
	public void setErrorVisible(boolean errorVisible) {
		this.errorVisible = errorVisible;
	}

	/**
	 * Diese Methode gibt die Instanz für das Erstellen und Zeichnen von Brücken im Spiel zurück.
	 *
	 * @return Die Instanz für das Erstellen und Zeichnen von Brücken.
	 */
	public CreateAndDrawBridges getCreateBridges() {
		return createBridges;
	}

	/**
	 * Setzt die Instanz für das Erstellen und Zeichnen von Brücken im Spiel.
	 *
	 * @param createBridges Die zu setzende Instanz.
	 */
	public void setCreateBridges(CreateAndDrawBridges createBridges) {
		this.createBridges = createBridges;
	}

	/**
	 * Gibt den aktuellen Index zurück.
	 *
	 * @return Der aktuelle Index.
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * Setzt den aktuellen Index.
	 *
	 * @param index Der neue Index-Wert.
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	/**
	 * Gibt die Liste von erstellten Brücken zurück.
	 *
	 * @return Eine Liste von erstellten Brücken.
	 */
	public ArrayList<CreateBridges> getListOfBridges() {
		return listOfBridges;
	}

	/**
	 * Setzt die Liste von erstellten Brücken.
	 *
	 * @param listOfBridges Die neue Liste von erstellten Brücken.
	 */
	public void setListOfBridges(ArrayList<CreateBridges> listOfBridges) {
		this.listOfBridges = listOfBridges;
	}


	/**
	 * Gibt eine Liste von möglichen Inseln zurück.
	 *
	 * @return Eine Liste von möglichen Inseln.
	 */
	public ArrayList<Island> getPossibleIslands() {
		return possibleIslands;
	}

	/**
	 * Setzt die Liste von möglichen Inseln.
	 *
	 * @param possibleIslands Die neue Liste von möglichen Inseln.
	 */
	public void setPossibleIslands(ArrayList<Island> possibleIslands) {
		this.possibleIslands = possibleIslands;
	}

	/**
	 * Gibt eine Zuordnung von Insel-IDs zu Inseln zurück.
	 *
	 * @return Eine Zuordnung von Insel-IDs zu Inseln.
	 */
	public HashMap<Integer, Island> getIslandMap() {
		return islandMap;
	}

	/**
	 * Setzt die Zuordnung von Insel-IDs zu Inseln.
	 *
	 * @param islandMap Die neue Zuordnung von Insel-IDs zu Inseln.
	 */
	public void setIslandMap(HashMap<Integer, Island> islandMap) {
		this.islandMap = islandMap;
	}

	/**
	 * Gibt die Instanz für die Verwaltung von Brücken im Norden zurück.
	 *
	 * @return Die Instanz für die Verwaltung von Brücken im Norden.
	 */
	public NorthBridges getNorthBridges() {
		return northBridges;
	}

	/**
	 * Setzt die Instanz für die Verwaltung von Brücken im Norden.
	 *
	 * @param northBridges Die neue Instanz für die Verwaltung von Brücken im Norden.
	 */
	public void setNorthBridges(NorthBridges northBridges) {
		this.northBridges = northBridges;
	}

	/**
	 * Gibt die Instanz für die Verwaltung von Brücken im Süden zurück.
	 *
	 * @return Die Instanz für die Verwaltung von Brücken im Süden.
	 */
	public SouthBridges getSouthBridges() {
		return southBridges;
	}

	/**
	 * Setzt die Instanz für die Verwaltung von Brücken im Süden.
	 *
	 * @param southBridges Die neue Instanz für die Verwaltung von Brücken im Süden.
	 */
	public void setSouthBridges(SouthBridges southBridges) {
		this.southBridges = southBridges;
	}

	/**
	 * Gibt die Instanz für die Verwaltung von Brücken im Osten zurück.
	 *
	 * @return Die Instanz für die Verwaltung von Brücken im Osten.
	 */
	public EastBridges getEastBridges() {
		return eastBridges;
	}

	/**
	 * Setzt die Instanz für die Verwaltung von Brücken im Osten.
	 *
	 * @param eastBridges Die neue Instanz für die Verwaltung von Brücken im Osten.
	 */
	public void setEastBridges(EastBridges eastBridges) {
		this.eastBridges = eastBridges;
	}

	/**
	 * Gibt die Instanz für die Verwaltung von Brücken im Westen zurück.
	 *
	 * @return Die Instanz für die Verwaltung von Brücken im Westen.
	 */
	public WestBridges getWestBridges() {
		return westBridges;
	}

	/**
	 * Setzt die Instanz für die Verwaltung von Brücken im Westen.
	 *
	 * @param westBridges Die neue Instanz für die Verwaltung von Brücken im Westen.
	 */
	public void setWestBridges(WestBridges westBridges) {
		this.westBridges = westBridges;
	}

	/**
	 * Gibt die Instanz für die Suche nach vorhandenen Brücken zurück.
	 *
	 * @return Die Instanz für die Suche nach vorhandenen Brücken.
	 */
	public LookForAnotherBridge getLookForAnotherBridge() {
		return lookForAnotherBridge;
	}

	/**
	 * Setzt die Instanz für die Suche nach vorhandenen Brücken.
	 *
	 * @param lookForAnotherBridge Die neue Instanz für die Suche nach vorhandenen Brücken.
	 */
	public void setLookForAnotherBridge(LookForAnotherBridge lookForAnotherBridge) {
		this.lookForAnotherBridge = lookForAnotherBridge;
	}

	/**
	 * Gibt die Instanz für das Hinzufügen und Entfernen von Brücken zurück.
	 *
	 * @return Die Instanz für das Hinzufügen und Entfernen von Brücken.
	 */
	public AddRemoveBridges getAddAndRemoveBridges() {
		return addAndRemoveBridges;
	}

	/**
	 * Setzt die Instanz für das Hinzufügen und Entfernen von Brücken.
	 *
	 * @param addAndRemoveBridges Die neue Instanz für das Hinzufügen und Entfernen von Brücken.
	 */
	public void setAddAndRemoveBridges(AddRemoveBridges addAndRemoveBridges) {
		this.addAndRemoveBridges = addAndRemoveBridges;
	}


	/**
	 * Gibt die Instanz für das Entfernen von Brücken zurück.
	 *
	 * @return Die Instanz für das Entfernen von Brücken.
	 */
	public RemoveBridges getRemoveBridges() {
		return removeBridges;
	}

	/**
	 * Setzt die Instanz für das Entfernen von Brücken.
	 *
	 * @param removeBridges Die neue Instanz für das Entfernen von Brücken.
	 */
	public void setRemoveBridges(RemoveBridges removeBridges) {
		this.removeBridges = removeBridges;
	}

	/**
	 * Gibt die Instanz für die Aktualisierung des Brückenzählers zurück.
	 *
	 * @return Die Instanz für die Aktualisierung des Brückenzählers.
	 */
	public UpdateBridgeCounter getUpdateBridgeCounter() {
		return updateBridgeCounter;
	}

	/**
	 * Setzt die Instanz für die Aktualisierung des Brückenzählers.
	 *
	 * @param updateBridgeCounter Die neue Instanz für die Aktualisierung des Brückenzählers.
	 */
	public void setUpdateBridgeCounter(UpdateBridgeCounter updateBridgeCounter) {
		this.updateBridgeCounter = updateBridgeCounter;
	}

	/**
	 * Gibt die Instanz für das Zeichnen von Brücken zurück.
	 *
	 * @return Die Instanz für das Zeichnen von Brücken.
	 */
	public DrawBridges getDrawBridges() {
		return drawBridges;
	}

	/**
	 * Setzt die Instanz für das Zeichnen von Brücken.
	 *
	 * @param drawBridges Die neue Instanz für das Zeichnen von Brücken.
	 */
	public void setDrawBridges(DrawBridges drawBridges) {
		this.drawBridges = drawBridges;
	}

}