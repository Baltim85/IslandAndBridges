package Controller;

import java.util.ArrayList;


import UI.Bridges;
import modells.GameData.GameData;
import modells.Support.CreateRandomPuzzle;
import modells.Support.DeepCopys;
import modells.handleBridges.AddRemoveBridges;
import modells.handleBridges.DrawBridges;
import modells.handleBridges.EastBridges;
import modells.handleBridges.LookForAnotherBridge;
import modells.handleBridges.NorthBridges;
import modells.handleBridges.RemoveBridges;
import modells.handleBridges.SouthBridges;
import modells.handleBridges.UpdateBridgeCounter;
import modells.handleBridges.WestBridges;
import Modell.Island;

import Modell.load.LoadDataForGame;

/**
 * Die "ActionController" Klasse ist verantwortlich für die Steuerung der Anwendung und die Initialisierung der Hauptkomponenten.
 *  Sie spielt eine zentrale Rolle in der Anwendungslogik, indem sie verschiedene Dialogfenster, den BridgeController 
 *  und andere erforderliche Instanzen erstellt, diesen Instanzen die erforderlichen Daten zuweist und Event-Listener hinzufügt, 
 *  um Benutzerinteraktionen zu behandeln.

 * Attribute:
 * - data: Ein `GameData`-Objekt, das zentrale Daten und Zustände der Anwendung verwaltet.

 * Konstruktor:
 * - ActionController(Bridges bridges): Erstellt eine neue Instanz des "ActionController" mit der Hauptanwendungsklasse 
 * "Bridges" als Parameter.

 * Methoden:
 * - Keine.

 * Die "ActionController" Klasse ist eine wichtige Schnittstelle zwischen den Hauptkomponenten der Anwendung und ermöglicht 
 * die Verwaltung von Spieldaten, die Steuerung von Dialogfenstern und die Handhabung von Benutzerinteraktionen. Sie erleichtert 
 * die Initialisierung und den reibungslosen Ablauf der Anwendung.
 */
public class MainController {



	private GameData data = new GameData(new ArrayList<Island>(), false, new ArrayList<Island>(), false);

	/**
	 * Der ActionController ist verantwortlich für die Steuerung der Anwendung und die Initialisierung der Hauptkomponenten.
	 * Er erstellt verschiedene Dialogfenster und den BridgeController, weist ihnen die erforderlichen Instanzen zu und fügt
	 * Event-Listener hinzu, um die Benutzerinteraktionen zu behandeln.
	 *
	 * @param bridges Die Hauptanwendungsklasse, die die Benutzeroberfläche darstellt.
	 */
	public MainController(Bridges bridges) {
	    // Initialisiere die Bridges-Instanz in den Spieldaten
	    this.data.setBridges(bridges);

	    // Initialisiere den BridgeController in den Spieldaten
	    data.setBridgeC(new BridgeController());

	    // Initialisiere die DeepCopys-Instanz in den Spieldaten
	    data.setCopyList(new DeepCopys());

	    // Initialisiere die CreateRandomPuzzle-Instanz in den Spieldaten
	    data.setRandomGame(new CreateRandomPuzzle());

	    // Initialisiere das LoadDataForGame-Objekt in den Spieldaten
	    data.setLoadData(new LoadDataForGame(data));

	    // Initialisiere den DialogController mit den Spieldaten
	    data.setDialogController(new DialogController(data));

	    // Initialisiere den MenuController mit den Spieldaten
	    data.setMenuController(new MenuController(data));
	    
	    
	    // Initialisieren der Klasse zum Entfernen von Brücken.
	    //removeBridges = new RemoveBridges(data);
	    data.setRemoveBridges(new RemoveBridges(data));

	    // Initialisieren der Klasse zum Zeichnen von Brücken.
	    //drawBridges = new DrawBridges(data);
	    data.setDrawBridges(new DrawBridges(data));
	    
	    //updateBridgeCounter = new UpdateBridgeCounter(data);
	    data.setUpdateBridgeCounter(new UpdateBridgeCounter(data));
	    
	    data.setAddAndRemoveBridges(new AddRemoveBridges(data));
	    
	    data.setLookForAnotherBridge(new LookForAnotherBridge(data));
	    
	    data.setNorthBridges(new NorthBridges(data));
	    data.setSouthBridges(new SouthBridges(data));
	    data.setEastBridges(new EastBridges(data));
	    data.setWestBridges(new WestBridges(data));

	}
}
