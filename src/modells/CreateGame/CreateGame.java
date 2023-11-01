package modells.CreateGame;

import java.util.ArrayList;

import Controller.IslandController;
import Modell.CalculateGrid;
import Modell.GridPainter;
import Modell.Island;
import modells.GameData.GameData;
import modells.Support.CalculateDelta;
import modells.Support.UpdateGameData;


/**
 * Die Klasse "CreateGame" verwaltet die Erstellung eines neuen Brückenrätselspiels. Sie ist verantwortlich für das Erstellen des Spielfelds,
 * die Inseln und die Initialisierung des Spiels. Die Klasse interagiert eng mit den Spieldaten und anderen Controllern, um ein vollständiges
 * Spiel zu erstellen.
 *
 * Attribute:
 * - private GameData data: Ein Objekt, das die Spieldaten enthält, einschließlich des Spielzustands und der Einstellungen.
 * - private UpdateGameData updateData: Eine Instanz zum Aktualisieren der Spieldaten.
 * - private GridPainter grid: Das Spielfeld, auf dem das Puzzle gezeichnet wird.
 * - private CalculateGrid gridValues: Eine Klasse zur Berechnung von Gitterwerten für das Spielfeld.
 * - private IslandController islandController: Ein Controller zur Verwaltung der Erstellung von Inseln im Spiel.
 * - private CalculateDelta calculateDelta: Eine Klasse zur Berechnung des Delta-Werts für die Brückenberechnung.
 * - private ArrayList<Island> deepCopy: Eine Liste, die als Deep Copy für andere Listen von Inseln verwendet wird.
 *
 * Methoden:
 *
 * - public CreateGame(GameData data): Ein Konstruktor für die "CreateGame"-Klasse. Er akzeptiert ein "GameData"-Objekt und verwendet
 *   es als Grundlage, um den Spielzustand zu verwalten und die Erstellung des Spiels zu steuern.
 *
 * - public void createBoard(int islands): Diese Methode erstellt ein neues Spielfeld mit den angegebenen Abmessungen und einer
 *   bestimmten Anzahl von Inseln. Sie löscht zunächst die Liste der vorhandenen Inseln, aktualisiert die Spielfelddaten und ruft
 *   dann die Methode "createIslands()" auf, um die Inseln für das Spiel zu generieren. Schließlich wird das Spielfeld erstellt
 *   und der Delta-Wert für die Brückenberechnung berechnet.
 *
 * - public void createIslands(): Diese Methode erstellt Inseln für das Spiel und aktualisiert die Kopie der Inseln in den
 *   Spieldaten. Sie beginnt mit der Erstellung der ersten Insel und erstellt anschließend weitere Inseln, bis die gewünschte
 *   Anzahl erreicht ist. Es wird sichergestellt, dass die erstellten Inseln korrekt sind.
 *
 * - public void createPainter(): Diese Methode erstellt ein neues Spielfeld (GridPainter) und aktualisiert das Spielfeld in den
 *   Spieldaten. Sie verwendet die Spielfeldabmessungen und die Gitterwerte, um ein neues Spielfeld zu erstellen und sicherzustellen,
 *   dass das Spielfeld zuerst gelöscht und dann aktualisiert wird, um das Spiel vorzubereiten.
 *
 */
public class CreateGame {

	/**
	 * Die Klasse ActionController verwaltet verschiedene Controller und Objekte im Spiel.
	 */
	private GameData data;         // Die Daten des Spiels, die den Zustand und die Einstellungen speichern.
	private UpdateGameData updateData;  // Die Instanz zum Aktualisieren der Spieldaten.


	// Felder zur Darstellung des Spielfelds und der Inseln
	private GridPainter grid;       // Das Spielfeld, auf dem das Puzzle gezeichnet wird
	//private Island island;           // Eine einzelne Insel im Puzzle
	private CalculateGrid gridValues; // Eine Klasse zur Berechnung von Gitterwerten
	private IslandController islandController;

	private CalculateDelta calculateDelta;


	// Hier wird eine ArrayList erstellt, die als Deep Copy für andere Listen verwendet werden kann.
	private ArrayList<Island> deepCopy = new ArrayList<Island>(); 



	/**
	 * Erstellt eine Instanz der CreateGame-Klasse mit den bereitgestellten Spieldaten.
	 *
	 * @param data Die Spieldaten, auf denen die Instanz arbeiten wird.
	 */
	public CreateGame(GameData data) {
		this.data = data;
		updateData = new UpdateGameData(data);
		islandController = new IslandController(data);

	}




	/**
	 * Erstellt ein neues Spielfeld mit den angegebenen Abmessungen und Inselanzahl.
	 *
	 * @param width   Die Breite des Spielfelds.
	 * @param height  Die Höhe des Spielfelds.
	 * @param islands Die Anzahl der Inseln auf dem Spielfeld.
	 */
	public void createBoard(int islands) {
		// Die Liste der Inseln löschen, um Platz für die neuen Inseln zu schaffen
		data.getListofIslands().clear();
		int panelWidth = data.getBridges().getDraw().getWidth();
		int panelHeight = data.getBridges().getDraw().getHeight();
		int width = data.getWidth();
		int height = data.getHeight();

		if(data.getBridgesList() != null)
			data.getBridgesList().clear();
		data.setIslands(islands);

		//calculateGrid();
		gridValues = new CalculateGrid(panelWidth, panelHeight, width, height);
		updateData.updateGridValues(gridValues);
		createIslands();

		createPainter();
		calculateDelta = new CalculateDelta();
		int delta = calculateDelta.calculateDelta(data.getGridValues());
		data.setDelta(delta);		
		data.getBridgeC().initController(data.getDeepCopy(),   data);

		if (!data.isControllerExist()) {
			AddMouseListener addListener = new AddMouseListener(data);
			addListener.registerMouseListener();
		}

		updateData.updateGameExist();
	}




	/**
	 * Erstellt Inseln für das Spiel und aktualisiert die Kopie der Inseln in den Spieldaten.
	 * 
	 * Die Methode erstellt eine bestimmte Anzahl von Inseln für das Spiel basierend auf den angegebenen
	 * Spielfeldabmessungen. Zunächst wird eine Instanz der `Island`-Klasse erstellt. Diese Instanz wird
	 * verwendet, um die Inseln zu generieren. Die Methode beginnt mit der Erstellung der ersten Insel,
	 * und solange diese noch nicht korrekt ist, werden weitere Inseln erstellt. Die erstellten Inseln
	 * werden in der Liste `listOfIslands` gespeichert.
	 * 
	 * @see Island
	 */
	public void createIslands() {
		ArrayList<Island> listOfIslands = data.getListofIslands();
		int islands = data.getIslands();

		// Die erste Insel erstellen
		islandController.createFirstIsland(islands, data);

		// Solange die Insel noch nicht korrekt ist, weitere Inseln erstellen
		do {
			System.out.println("Creating new island...\n");

			islandController.createFirstIsland(islands, data);
		} while(!islandController.isOk());
		System.out.println(islands);

		// Aktualisiert die Kopie der Inseln in den Spieldaten.
		updateData.updateDeepCopy(listOfIslands, deepCopy);
		deepCopy = data.getDeepCopy();
	}




	/**
	 * Erstellt ein neues Spielfeld (GridPainter) und aktualisiert das Spielfeld in den Spieldaten.
	 * 
	 * Die Methode verwendet die Spielfeldabmessungen sowie die Gitterwerte aus den Spieldaten, um eine
	 * neue Instanz von `GridPainter` zu erstellen. Dieses Spielfeld dient zur Darstellung des
	 * Brückenrätsels. Anschließend wird das Spielfeld in den Spieldaten aktualisiert. Um sicherzustellen,
	 * dass das Spielfeld zuerst gelöscht und dann aktualisiert wird, wird die Methode `clearAndAddGrid()`
	 * aus der `ClearGrid`-Klasse verwendet.
	 * 
	 * @see GridPainter
	 * @see ClearGrid
	 */
	public void createPainter() {
		int width = data.getWidth();
		int height = data.getHeight();
		CalculateGrid gridValues = data.getGridValues();

		// Erstelle eine neue Instanz von GridPainter für das Spielfeld
		grid = new GridPainter(width, height, gridValues.getXDistance(), gridValues.getYDistance(), deepCopy);

		// Aktualisiere das Spielfeld in den Spieldaten
		updateData.updateGrid(grid);

		// Lösche und aktualisiere das Spielfeld, bevor das neue Spielfeld erstellt wird
		ClearGrid clearGrid = new ClearGrid(data);
		clearGrid.clearAndAddGrid();
	}



}
