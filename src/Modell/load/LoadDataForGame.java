package Modell.load;


import java.util.ArrayList;


import Modell.CalculateGrid;
import Modell.CreateBridges;
import Modell.GridPainter;
import Modell.Island;

import modells.CreateGame.AddMouseListener;
import modells.CreateGame.ClearGrid;
import modells.GameData.GameData;
import modells.Support.CalculateDelta;

import modells.Support.UpdateGameData;


/**
 * Die "LoadDataForGame" Klasse ist verantwortlich für das Laden eines zuvor gespeicherten Spiels und die Wiederherstellung 
 * dieses Spiels in der Anwendung. Sie führt eine Reihe von Schritten aus, um sicherzustellen, dass das geladene Spiel 
 * vollständig und korrekt in den aktuellen Spielzustand integriert wird. Dies beinhaltet das Laden von Informationen 
 * zur Spielfeldgröße, den Inseln, den Brücken und deren Positionen, um das Spiel in einem früheren Zustand wiederherzustellen.

 * Attribute:
 * - gameLoader: Ein Objekt zum Laden von Spielinformationen.
 * - gridValues: Eine Klasse zur Berechnung von Gitterwerten für das Spielfeld.
 * - island: Eine einzelne Insel im Puzzle.
 * - grid: Ein Objekt zur Darstellung des Spielfelds.
 * - insertIslands: Ein Objekt zum Einfügen von Inseln in Listen.
 * - insertBridges: Ein Objekt zum Einfügen von Brücken in Listen.
 * - data: Ein Objekt der Klasse "GameData" für die Verwaltung der Spielinformationen und -zustände.
 * - updateData: Ein Objekt zur Aktualisierung der Spielinformationen in "GameData".
 * - calculateDelta: Ein Objekt zur Berechnung des Werts "delta".
 * - deepCopy: Eine ArrayList, die als Deep Copy für andere Listen verwendet werden kann.

 * Konstruktor:
 * - Der Konstruktor initialisiert verschiedene Objekte und Vorbereitungen für das Laden und Verarbeiten von Spielinformationen. 
 * Er akzeptiert ein Objekt der Klasse "GameData" für die Verwaltung der Spielinformationen und -zustände.

 * Methoden:
 * - loadGame(): Diese Methode ermöglicht das Laden eines zuvor gespeicherten Spiels und stellt sicher, dass das geladene Spiel 
 * korrekt in der Anwendung wiederhergestellt wird. Sie führt Schritte wie das Laden der Spielfeldgröße, der Inseln, der Brücken 
 * und deren Positionen durch. Falls das geladene Spiel nicht existiert oder einige notwendige Daten fehlen, wird eine 
 * NullPointerException ausgelöst.

 * Die "LoadDataForGame" Klasse spielt eine entscheidende Rolle bei der Wiederherstellung gespeicherter Spiele und sorgt dafür, 
 * dass der Benutzer nahtlos zu einem zuvor gespeicherten Spiel zurückkehren kann. Sie koordiniert das Laden und die Verarbeitung 
 * von Spielinformationen und gewährleistet die Konsistenz des Spielzustands.
 */
public class LoadDataForGame {

	/**
	 * Ein Objekt zum Laden von Spielinformationen aus externen Quellen.
	 */
	private GameLoader gameLoader;

	/**
	 * Eine Klasse zur Berechnung von Gitterwerten, beispielsweise Abständen zwischen den Gitterlinien.
	 */
	private CalculateGrid gridValues;

	/**
	 * Eine Instanz, die eine einzelne Insel im Puzzle repräsentiert.
	 */
	private Island island;

	/**
	 * Ein Objekt zur Darstellung des Spielfelds, verwendet für die grafische Darstellung des Spiels.
	 */
	private GridPainter grid;

	/**
	 * Ein Objekt zum Einfügen von Inseln in Listen oder Datenstrukturen.
	 */
	private InsertIslands insertIslands;

	/**
	 * Ein Objekt zum Einfügen von Brücken in Listen oder Datenstrukturen.
	 */
	private InsertBridges insertBridges;

	/**
	 * Repräsentiert die Spielinformationen und den aktuellen Zustand des Spiels.
	 */
	private GameData data;

	/**
	 * Wird verwendet, um Spielinformationen zu aktualisieren.
	 */
	private UpdateGameData updateData;

	/**
	 * Dient zur Berechnung des Wertes "delta" für Platzierungen oder Größenverhältnisse im Spiel.
	 */
	private CalculateDelta calculateDelta;

    // Hier wird eine ArrayList erstellt, die als Deep Copy für andere Listen verwendet werden kann.
    private ArrayList<Island> deepCopy = new ArrayList<Island>();

    /**
     * Der Konstruktor für die "LoadDataForGame"-Klasse initialisiert verschiedene Objekte und Vorbereitungen
     * für das Laden und Verarbeiten von Spielinformationen.
     */
    public LoadDataForGame(GameData data) {
        gameLoader = new GameLoader();      // Initialisiere das Objekt zum Laden von Spielinformationen
        
        insertIslands = new InsertIslands(); // Initialisiere das Objekt zum Einfügen von Inseln in Listen
        insertBridges = new InsertBridges(); // Initialisiere das Objekt zum Einfügen von Brücken in Listen
        island = new Island();
        this.data = data;
        updateData = new UpdateGameData(data);         
    }
    
    
    /**
     * Diese Methode ermöglicht das Laden eines zuvor gespeicherten Spiels und stellt sicher, dass das geladene Spiel 
     * korrekt in der Anwendung wiederhergestellt wird. Sie führt eine Reihe von Schritten aus, um sicherzustellen, 
     * dass das geladene Spiel vollständig und korrekt in den aktuellen Spielzustand integriert wird.
     *
     * @throws NullPointerException Wenn das geladene Spiel `null` ist oder einige notwendige Daten nicht vorhanden sind.
     */
	public void loadGame() {
	    // Versuche, das gespeicherte Spiel zu laden
		
 	    if(gameLoader.loadGame(data) == null)
 	    	return;
	    int width = gameLoader.getWidth();
	    int height = gameLoader.getHeight();
	    int islands = gameLoader.getIslands();

	    // Wenn kein Spiel geladen wurde, beende die Methode
	    if (gameLoader == null) {
	        return;
	    }
	    
	    updateData.updateWidth(width);
	    updateData.updateHeight(height);
	    updateData.updateIslands(islands);
	    
	    int panelWidth = data.getBridges().getDraw().getWidth();
	    int panelHeight = data.getBridges().getDraw().getHeight();
	    // Berechne die Informationen für das Raster (gridValues)
	    gridValues = new CalculateGrid(panelWidth, panelHeight, width, height);
	    updateData.updateGridValues(gridValues);

	    // Erstelle eine Liste von Inseln anhand der geladenen Informationen
	    ArrayList<Island> islandsList = insertIslands.insertIslandsIntoList(gameLoader.getIslandsList());

	    // Erstelle eine Liste von Brücken anhand der geladenen Informationen
	    ArrayList<CreateBridges> bridgesList = insertBridges.insertBridgesIntoList(islandsList, gameLoader.getBridgeList());

	    // Erstelle ein Raster (grid) basierend auf den geladenen Informationen
	    grid = new GridPainter(width, height, gridValues.getXDistance(), gridValues.getYDistance(), islandsList);
	    updateData.updateGrid(grid);
	    
	    
	    // Aktualisiere die Inseln in der 'island'-Instanz
	    island.setListofIslands(islandsList);
	    data.setListofIslands(islandsList);
	    
	    updateData.updateDeepCopy(data.getListofIslands(), deepCopy);
	    //deepCopy = data.getDeepCopy();


	    // Berechne den Wert "delta"
	    calculateDelta = new CalculateDelta();
	    int delta = calculateDelta.calculateDelta(gridValues);
	    data.setDelta(delta);
	    
	    if (!data.isControllerExist()) {
	        AddMouseListener addListener = new AddMouseListener(data);
	        addListener.registerMouseListener();
	    }
	    
	    updateData.updateBridgeList(bridgesList);
	    data.setGameWasLoaded(true);
	    
	    data.getBridgeC().initController(data.getDeepCopy(), data);
	    //data.getBridgeC().initController(data.getDeepCopy(), data.getBridgesList(),   data);

	    ClearGrid clearGrid = new ClearGrid(data);
	    clearGrid.clearAndAddGrid();
	    
	    updateData.updateGameExist();

	}

}
