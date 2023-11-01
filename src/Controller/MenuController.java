package Controller;

import javax.swing.JMenuItem;
import Modell.Island;
import Modell.load.LoadDataForGame;
import UI.Bridges;
import modells.CreateGame.RestartGame;
import modells.GameData.GameData;
import modells.save.MainSaveFile;
import modells.save.SaveGame;


/**
 * Die Klasse MenuController ist verantwortlich für die Steuerung und Verwaltung der Menüfunktionen des Bridges-Spiels. 
 * Sie ermöglicht
 * Benutzerinteraktionen im Spielmenü, wie das Erstellen eines neuen Puzzles, das Speichern und Laden von Spielen sowie das 
 * Beenden des Spiels.
 * Der MenuController dient als Vermittler zwischen den Menüoptionen und den verschiedenen Controllern und Objekten, die das Spiel
 * verwalten, wie ActionController, SaveGame und LoadDataForGame.
 *
 * Methoden:
 *
 * - MenuController(ActionController controller, Bridges bridges, DialogController dialog, BridgeController bridgeController, 
 * LoadDataForGame loadGame):
 *   Der Konstruktor initialisiert die Klasse und legt die Schnittstellen zu den zentralen Spielobjekten und -steuerungen fest. 
 *   Er ermöglicht
 *   Interaktionen mit dem Benutzer über das Menü, einschließlich des Erstellens eines neuen Rätsels, des Speicherns und Ladens 
 *   von Spielen
 *   sowie des Beendens des Spiels.
 *
 * - getLoadData(): Gibt das Objekt zurück, das für das Laden von Spielinformationen verantwortlich ist.
 *
 * - getIsland(): Gibt die aktuelle Insel im Spiel zurück.
 *
 * - getBridgeController(): Gibt den Controller für Brücken im Spiel zurück.
 *
 * - getDialogController(): Gibt den Controller für Dialoge und Benachrichtigungen zurück.
 *
 * - getBridges(): Gibt die Hauptklasse des Spiels zurück, die die Benutzeroberfläche verwaltet.
 *
 * - getSave(): Gibt das Objekt zurück, das für das Speichern von Spielen verantwortlich ist.
 *
 * - getController(): Gibt den Controller für die Spiellogik und -steuerung zurück.
 *
 * - setLoadData(LoadDataForGame loadData): Legt das Objekt fest, das für das Laden von Spielinformationen verantwortlich ist.
 *
 * - setIsland(Island island): Legt die aktuelle Insel im Spiel fest.
 *
 * - setBridgeController(BridgeController bridgeController): Legt den Controller für Brücken im Spiel fest.
 *
 * - setDialogController(DialogController dialogController): Legt den Controller für Dialoge und Benachrichtigungen fest.
 *
 * - setBridges(Bridges bridges): Legt die Hauptklasse des Spiels fest, die die Benutzeroberfläche verwaltet.
 *
 * - setSave(SaveGame save): Legt das Objekt fest, das für das Speichern von Spielen verantwortlich ist.
 *
 * - setController(ActionController controller): Legt den Controller für die Spiellogik und -steuerung fest.
 */
public class MenuController {


	/**
	 * Der Controller für Dialoge und Benachrichtigungen im Spiel.
	 */
	private DialogController dialogController;

	/**
	 * Das Objekt zum Speichern von Spielen.
	 */
	private SaveGame save;

	/**
	 * Die Hauptklasse des Spiels, die die Benutzeroberfläche verwaltet.
	 */
	private Bridges bridges;

	/**
	 * Eine einzelne Insel im Spiel.
	 */
	private Island island;

	/**
	 * Das Objekt zum Laden von Spielinformationen.
	 */
	private LoadDataForGame loadData;

	/**
	 * Das Objekt zum Neustart des Spiels.
	 */
	private RestartGame restartGame;

	/**
	 * Die Hauptdatenklasse des Spiels, die Spielinformationen und Zustände verwaltet.
	 */
	private GameData data;

	/**
	 * Die Hauptklasse zum Speichern von Spielfiles.
	 */
	private MainSaveFile mainSave;


    /**
     * Der Konstruktor der Klasse "MenuController" akzeptiert die benötigten Abhängigkeiten über Konstruktorinjektion.
     *
     * @param bridges           Die "Bridges"-Instanz, die das gesamte Spiel verwaltet.
     * @param dialogController  Der "DialogController" für die Verwaltung von Dialogfeldern und Benachrichtigungen.
     * @param saveGame          Das "SaveGame"-Objekt für das Speichern von Spielen.
     * @param loadDataForGame  Das "LoadDataForGame"-Objekt für das Laden von Spielinformationen.
     * @param actionController  Der "ActionController" für die Spiellogik und -steuerung.
     * @param bridgeController  Der "BridgeController" zur Verwaltung der Brücken im Spiel.
     */
    public MenuController(GameData data) {
        this.bridges = data.getBridges();
        this.dialogController = data.getDialogController();
        mainSave = new MainSaveFile();
        this.loadData = data.getLoadData();
        this.data = data;
        restartGame = new RestartGame(data);

        initializeMenuItems(); 
    }

    /**
     * Die Methode "initializeMenuItems" ist verantwortlich für die Initialisierung der Menüelemente in der Benutzeroberfläche.
     * Sie verknüpft die Menüelemente mit den entsprechenden Aktionen, die bei Auswahl des Menüs ausgeführt werden sollen.
     * Dies umfasst die Verknüpfung der Menüelemente "Neues Rätsel", "Beenden", "Rätsel speichern" und "Rätsel laden" mit den
     * entsprechenden Aktionen, die die Anzeige von Dialogfenstern oder das Ausführen von Spieloperationen auslösen.
     *
     * Diese Methode wird normalerweise einmalig beim Start der Anwendung aufgerufen, um die Menüaktionen festzulegen.
     */
    private void initializeMenuItems() {
        // Menüelemente abrufen
        JMenuItem miNewPuzzle = bridges.getMiNewPuzzle();
        JMenuItem miQuit = bridges.getMiQuit();
        JMenuItem miSavePuzzle = bridges.getMiSavePuzzle();
        JMenuItem miLoadPuzzle = bridges.getMiLoadPuzzle();

        // Aktionen für die Menüelemente festlegen
        miNewPuzzle.addActionListener(e -> dialogController.PuzzleView());   // Öffnet das Dialogfenster zur Erstellung eines neuen Rätsels
        miQuit.addActionListener(e -> dialogController.ExitView());         // Öffnet das Dialogfenster zum Beenden des Spiels
        miSavePuzzle.addActionListener(e -> mainSave.SaveGame(data));   
        miLoadPuzzle.addActionListener(e -> loadData.loadGame()); // Lädt ein gespeichertes Spiel
        data.getBridges().getMiRestartPuzzle().addActionListener(e -> restartGame.restartPuzzle());
    }



	/**
	 * Gibt das Objekt zurück, das für das Laden von Spielinformationen verantwortlich ist.
	 *
	 * @return Das "LoadDataForGame"-Objekt.
	 */
	public LoadDataForGame getLoadData() {
		return loadData;
	}

	/**
	 * Legt das Objekt fest, das für das Laden von Spielinformationen verantwortlich ist.
	 *
	 * @param loadData Das "LoadDataForGame"-Objekt.
	 */
	public void setLoadData(LoadDataForGame loadData) {
		this.loadData = loadData;
	}

	/**
	 * Gibt die aktuelle Insel im Spiel zurück.
	 *
	 * @return Die aktuelle Insel als "Island"-Objekt.
	 */
	public Island getIsland() {
		return island;
	}

	/**
	 * Legt die aktuelle Insel im Spiel fest.
	 *
	 * @param island Die aktuelle Insel als "Island"-Objekt.
	 */
	public void setIsland(Island island) {
		this.island = island;
	}



	/**
	 * Gibt den Controller für Dialoge und Benachrichtigungen zurück.
	 *
	 * @return Der "DialogController".
	 */
	public DialogController getDialogController() {
		return dialogController;
	}

	/**
	 * Legt den Controller für Dialoge und Benachrichtigungen fest.
	 *
	 * @param dialogController Der "DialogController".
	 */
	public void setDialogController(DialogController dialogController) {
		this.dialogController = dialogController;
	}

	/**
	 * Gibt die Hauptklasse des Spiels zurück, die die Benutzeroberfläche verwaltet.
	 *
	 * @return Die "Bridges"-Hauptklasse.
	 */
	public Bridges getBridges() {
		return bridges;
	}

	/**
	 * Legt die Hauptklasse des Spiels fest, die die Benutzeroberfläche verwaltet.
	 *
	 * @param bridges Die "Bridges"-Hauptklasse.
	 */
	public void setBridges(Bridges bridges) {
		this.bridges = bridges;
	}

	/**
	 * Gibt das Objekt zurück, das für das Speichern von Spielen verantwortlich ist.
	 *
	 * @return Das "SaveGame"-Objekt.
	 */
	public SaveGame getSave() {
		return save;
	}

	/**
	 * Legt das Objekt fest, das für das Speichern von Spielen verantwortlich ist.
	 *
	 * @param save Das "SaveGame"-Objekt.
	 */
	public void setSave(SaveGame save) {
		this.save = save;
	}

}
