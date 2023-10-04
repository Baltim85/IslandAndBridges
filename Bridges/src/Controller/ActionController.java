package Controller;

import java.util.ArrayList;
import java.util.Random;


import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import UI.Bridges;
import UI.CancelSave;
import UI.ErrorIsland;
import UI.ExitDialog;
import UI.FinishedGame;
import UI.NewPuzzle;
import UI.NoGame;
import UI.SaveGameSuccessfully;
import Modell.CalculateGrid;

import Modell.CreateBridges;
import Modell.GridPainter;
import Modell.Island;
import Modell.load.GameLoader;
import Modell.save.SaveGame;
import SaveLoad.*;

/**
 * Die Klasse GameController ist verantwortlich für die Steuerung der Anwendung.
 * Sie verwaltet die Hauptkomponenten der Anwendung, darunter das Hauptfenster, Dialogfenster für verschiedene Aktionen
 * wie Beenden, Puzzle-Erstellung, Speichern und Laden von Spielständen sowie Fehlermeldungen und Erfolgsmeldungen.
 */
public class ActionController {
    private Bridges bridges;             // Das Hauptfenster der Anwendung
    private ExitDialog exitGame;         // Ein Dialogfenster zum Beenden des Spiels
    private NewPuzzle puzzle;            // Ein Dialogfenster zur Puzzle-Erstellung
    private NoGame saveInfo;             // Ein Dialogfenster für fehlende Spielinformationen
    private SaveGame save;               // Eine Klasse zur Speicherung von Spielständen
    private LoadGame load;               // Eine Klasse zur Verwaltung des Ladens von Spielständen
    private ErrorIsland errorInfo;       // Ein Dialogfenster für Fehlermeldungen im Zusammenhang mit Inseln
    private FinishedGame completeGame;   // Ein Dialogfenster zur Anzeige des Spielabschlusses
    //private SaveGameSuccessfully success; // Ein Dialogfenster zur Anzeige des erfolgreichen Speicherns eines Spielstands
    //private CancelSave cancelSaveOption; // Ein Dialogfenster zur Bestätigung der Abbruchoption beim Speichern


    private GameLoader loadGame;
    
    
	// Felder zur Verwaltung von Spielinformationen und Zuständen
	private int height;              // Die Höhe des Spielfelds
	private int width;               // Die Breite des Spielfelds
	private int islands;             // Die Anzahl der Inseln auf dem Spielfeld
	//private CheckInput checkInput;   // Ein Objekt zur Überprüfung von Benutzereingaben

	// Felder zur Darstellung des Spielfelds und der Inseln
	private GridPainter grid;       // Das Spielfeld, auf dem das Puzzle gezeichnet wird
	private Island island;           // Eine einzelne Insel im Puzzle
	private CalculateGrid gridValues; // Eine Klasse zur Berechnung von Gitterwerten

	// Konstanten für die minimale und maximale Spielfeldgröße
	//private final int minValue = 4;
	//private final int maxValue = 25;

	// Zufallszahlengenerator für die Erstellung von zufälligen Puzzle-Größen
	private Random randomSize = new Random();



	// Eine Liste von Inseln im aktuellen Puzzle
	private ArrayList<Island> listofIslands = new ArrayList<Island>();

	// Der BridgeController zur Verwaltung der Brücken
	private BridgeController bridgeC;

	// Ein Flag, das angibt, ob der BridgeController bereits erstellt wurde
	private boolean controllerExist = false;
	
	// Hier wird eine ArrayList erstellt, die als Deep Copy für andere Listen verwendet werden kann.
	private ArrayList<Island> deepCopy = new ArrayList<Island>(); 

	// Hier wird eine Konstante für den Standardwert des X-Zentrums definiert.
	private static final int DEFAULT_CENTER_X = 0; 
	
	
	//Eine Konstante, die den Standardwert für das Y-Zentrum definiert.
	private static final int DEFAULT_CENTER_Y = 0;

	
	// Eine Flagge, die angibt, ob ein Spiel existiert oder nicht.
	private boolean gameExist = false;

    
    
	/**
	 * Der ActionController ist verantwortlich für die Steuerung der Anwendung und die Initialisierung der Hauptkomponenten.
	 * Er erstellt verschiedene Dialogfenster und den BridgeController, weist ihnen die erforderlichen Instanzen zu und fügt
	 * Event-Listener hinzu, um die Benutzerinteraktionen zu behandeln.
	 *
	 * @param bridges Die Hauptanwendungsklasse, die die Benutzeroberfläche darstellt.
	 */
	public ActionController(Bridges bridges) {
	    this.bridges = bridges;
	    exitGame = new ExitDialog();             // Initialisieren des Dialogfensters zum Beenden des Spiels
	    errorInfo = new ErrorIsland();           // Initialisieren des Dialogfensters für Fehlermeldungen im Zusammenhang mit Inseln
	    puzzle = new NewPuzzle();                // Initialisieren des Dialogfensters zur Puzzle-Erstellung
	    bridgeC = new BridgeController();        // Initialisieren des BridgeControllers
	    save = new SaveGame();                   // Initialisieren der Klasse zur Speicherung von Spielständen
	    saveInfo = new NoGame();                 // Initialisieren des Dialogfensters für fehlende Spielinformationen
	    completeGame = new FinishedGame();       // Initialisieren des Dialogfensters zur Anzeige des Spielabschlusses
	    //success = new SaveGameSuccessfully();    // Initialisieren des Dialogfensters zur Anzeige des erfolgreichen Speicherns eines Spielstands
	    //cancelSaveOption = new CancelSave();     // Initialisieren des Dialogfensters zur Bestätigung der Abbruchoption beim Speichern
	    addListener();                           // Hinzufügen von Event-Listenern für die Benutzerinteraktionen
	}

	
	
	
	
	/**
	 * Fügt Event-Listener zu den verschiedenen UI-Komponenten hinzu, um auf Benutzerinteraktionen zu reagieren.
	 * Die Aktionen sind nach Menüaktionen, Dialogfenstern und Puzzle-Einstellungen sortiert.
	 */
	public void addListener() {
	    // Menüaktionen
	    bridges.getMiNewPuzzle().addActionListener(e -> PuzzleView());        // Öffnet ein neues Rätselansichtsfenster
	    bridges.getMiQuit().addActionListener(e -> ExitView());               // Öffnet ein Bestätigungsdialogfenster zum Beenden
	    bridges.getMiSavePuzzle().addActionListener(e -> SaveGame());          // Speichert das aktuelle Spiel
	    bridges.getMiLoadPuzzle().addActionListener(e -> loadGame());          // Lädt ein gespeichertes Spiel
	    bridges.getMiRestartPuzzle().addActionListener(e -> restartPuzzle());  // Startet das aktuelle Puzzle neu

	    // Dialog für Spielinformationen
	    saveInfo.getBtnOK().addActionListener(e -> DisposeView());             // Schließt das Dialogfenster für Spielinformationen

	    // Dialog für Spiel beenden
	    //exitGame.getBtnExit().addActionListener(e -> ExitGame());              // Beendet das Spiel
	    //exitGame.getBtnNo().addActionListener(e -> DisposeView());             // Schließt das Bestätigungsdialogfenster

	    // Dialog für Fehler bei Inseln
	    //errorInfo.getBtnOk().addActionListener(e -> ErrorMessage());           // Schließt das Dialogfenster für Fehlermeldungen

	    // Dialog für abgeschlossenes Spiel
	    //completeGame.getBtnOk().addActionListener(e -> completedGame());       // Schließt das Dialogfenster für abgeschlossene Spiele

	    // Dialog für erfolgreiches Speichern
	    //success.getBtnOk().addActionListener(e -> closeMessage());             // Schließt das Dialogfenster für die Erfolgsmeldung

	    // Dialog für Bestätigung des Abbruchs beim Speichern
	    //cancelSaveOption.getBtnOk().addActionListener(e -> closeCancelWindow());// Schließt das Dialogfenster zur Bestätigung des Abbruchs

	    // Puzzle-Einstellungen
	    puzzle.getRbnAutoSizeIsland().addActionListener(e -> Toggle());         // Aktiviert die automatische Größenanpassung
	    puzzle.getRbnSizeIsland().addActionListener(e -> Toggle());             // Aktiviert die manuelle Größeneinstellung
	    puzzle.getCbDefineIslands().addActionListener(e -> ToggleIslands());    // Aktiviert die Einstellung der Inselanzahl
	    puzzle.getBtnOk().addActionListener(e -> checkInput());                // Überprüft die Benutzereingabe
	    puzzle.getBtnAbord().addActionListener(e -> DisposeView());             // Schließt das Einstellungsfenster
	}
	
	
	/**
	 * Startet das Puzzle neu, indem es den aktuellen Zustand löscht und zur ursprünglichen Konfiguration zurückkehrt.
	 */
	private void restartPuzzle() {
	    // Überprüfen, ob ein Spiel existiert
	    if (!isGameExist()) {
	        return; // Kein Neustart, wenn kein Spiel existiert
	    } else {
	        // Listen in der BridgeController-Klasse leeren
	        bridgeC.clearLists();
	        
	        // Erstellt eine tiefe Kopie des Spielfelds
	        createDeepCopy();

	        // Erstellt ein neues GridPainter-Objekt und fügt es hinzu
	        grid = new GridPainter(width, height, gridValues.getXDistance(), gridValues.getYDistance(), deepCopy);
	        clearAndAddGrid();

	        // Berechnet den Delta-Wert für die Brückenberechnung
	        int delta = 0;
	        if (gridValues.getXDistance() < gridValues.getYDistance()) {
	            delta = gridValues.getXDistance();
	        } else {
	            delta = gridValues.getYDistance();
	        }

	        // Initialisiert den BridgeController mit der tiefen Kopie und anderen Parametern
	        bridgeC.initController(deepCopy, bridges, gridValues.getXDistance(), gridValues.getYDistance(), delta, width, height, grid);
	    }
	}


	/**
	 * Lädt ein gespeichertes Spiel und stellt es wieder her.
	 */
	private void loadGame() {
		loadGame = new GameLoader();
		loadGame.loadGame();
		
		if(loadGame == null) {
			return;
		}
		
		gridValues = new CalculateGrid(bridges.getDraw().getWidth(), bridges.getDraw().getHeight(), loadGame.getWidth(), loadGame.getHeight());
		ArrayList<Island> islandsA = new ArrayList<Island>();
	    insertIslandsIntoList(islandsA);
	    
	    // Liste von Brücken erstellen, indem die Informationen aus der Datei verarbeitet werden
	    ArrayList<CreateBridges> bridgesList = new ArrayList<CreateBridges>();
	    insertBridgesIntoList(bridgesList, islandsA);
	    
	    // Raster mit den geladenen Informationen erstellen
	    grid = new GridPainter(loadGame.getWidth(), loadGame.getHeight(), gridValues.getXDistance(), gridValues.getYDistance(),  islandsA);

	    // Inseln in der 'island'-Instanz aktualisieren
	    island.setListofIslands(islandsA);
	    createDeepCopy();
	    
	    int delta = 0;
	    if(gridValues.getXDistance() < gridValues.getYDistance())
	        delta = gridValues.getXDistance();
	    else
	        delta = gridValues.getYDistance();
	    
	    // BridgeController initialisieren
	    bridgeC.initController(deepCopy, bridgesList, bridges, gridValues.getXDistance(), gridValues.getYDistance(), delta, loadGame.getWidth(), loadGame.getHeight(), grid);
	    
	    // Spielfeldbreite, Spielfeldhöhe und Anzahl der Inseln setzen
	    setWidth(loadGame.getWidth());
	    setHeight(loadGame.getHeight());
	    setIslands(loadGame.getIslands());
	    
	    // Wenn der Controller nicht existiert, MouseListener registrieren
	    if (!controllerExist) {
	        registerMouseListener();
	    }
	    
	    // Das Spiel ist jetzt aktiv
	    setGameExist(true);
	    
	    // Alte Zeichenfläche entfernen und neues Raster hinzufügen
	    clearAndAddGrid();
		
		
	    // Instanz der 'LoadGame'-Klasse erstellen
	    /*load = new LoadGame();
	    if(load.isAbordLoad())
	    	return;
	    
	    // Gitterwerte basierend auf den Spielinformationen berechnen
	    gridValues = new CalculateGrid(bridges.getDraw().getWidth(), bridges.getDraw().getHeight(), load.getWidth(), load.getHeight());

	    // Liste von Inseln erstellen, indem die Informationen aus der Datei verarbeitet werden
	    ArrayList<Island> islandsA = new ArrayList<Island>();
	    insertIslandsIntoList(islandsA);

	    // Liste von Brücken erstellen, indem die Informationen aus der Datei verarbeitet werden
	    ArrayList<CreateBridges> bridgesList = new ArrayList<CreateBridges>();
	    insertBridgesIntoList(bridgesList, islandsA);
	    
	    // Raster mit den geladenen Informationen erstellen
	    grid = new GridPainter(load.getWidth(), load.getHeight(), gridValues.getXDistance(), gridValues.getYDistance(),  islandsA);

	    // Inseln in der 'island'-Instanz aktualisieren
	    island.setListofIslands(islandsA);
	    createDeepCopy();
	    
	    int delta = 0;
	    if(gridValues.getXDistance() < gridValues.getYDistance())
	        delta = gridValues.getXDistance();
	    else
	        delta = gridValues.getYDistance();
	    
	    // BridgeController initialisieren
	    bridgeC.initController(deepCopy, bridgesList, bridges, gridValues.getXDistance(), gridValues.getYDistance(), delta, load.getWidth(), load.getHeight(), grid);
	    
	    // Spielfeldbreite, Spielfeldhöhe und Anzahl der Inseln setzen
	    setWidth(load.getWidth());
	    setHeight(load.getHeight());
	    setIslands(load.getIslands());
	    
	    // Wenn der Controller nicht existiert, MouseListener registrieren
	    if (!controllerExist) {
	        registerMouseListener();
	    }
	    
	    // Das Spiel ist jetzt aktiv
	    setGameExist(true);
	    
	    // Alte Zeichenfläche entfernen und neues Raster hinzufügen
	    clearAndAddGrid();  */
	}

	
	/**
	 * Fügt Inseln in eine Liste ein, basierend auf den Informationen aus 'LoadGame'.
	 *
	 * @param islandsA Die Liste, in die die Inseln eingefügt werden.
	 */
	private void insertIslandsIntoList(ArrayList<Island> islandsA) {
	    for (int[] islandInfo : loadGame.getIslandsList()) {
	        int x = islandInfo[0];
	        int y = islandInfo[1];
	        int bridge = islandInfo[2];
	        
	        // Eine neue Insel erstellen und zur Liste hinzufügen
	        island = new Island(x, y, islandsA.size(), bridge);
	        islandsA.add(island);
	    }
	}

	/**
	 * Fügt Brücken in eine Liste ein, basierend auf den Informationen aus 'LoadGame'.
	 *
	 * @param bridgesList Die Liste, in die die Brücken eingefügt werden.
	 * @param islandsA    Die Liste von Inseln, auf die sich die Brücken beziehen.
	 */
	private void insertBridgesIntoList(ArrayList<CreateBridges> bridgesList, ArrayList<Island> islandsA) {
	    for (int[] bridgeInfo : loadGame.getBridgeList()) {
	        int firstID = bridgeInfo[0];
	        int secondID = bridgeInfo[1];
	        int numberBridges = bridgeInfo[2];
	        
	        // Die Koordinaten der ersten Insel und der zweiten Insel erhalten
	        int firstX = islandsA.get(firstID).getX();
	        int firstY = islandsA.get(firstID).getY();
	        int secondX = islandsA.get(secondID).getX();
	        int secondY = islandsA.get(secondID).getY();
	        
	        // Eine neue Brücke erstellen und zur Liste hinzufügen
	        bridgesList.add(new CreateBridges(firstID, firstX, firstY, secondID, secondX, secondY, numberBridges, null));
	    }		
	}

	

	/**
	 * Speichert das aktuelle Spiel.
	 */
	private void SaveGame() {
	    if (deepCopy.isEmpty()) {
	        // Wenn keine Inseln vorhanden sind, wird ein Fehlerdialog angezeigt.
	        saveInfo.setLocationRelativeTo(bridges.getDraw());
	        saveInfo.setVisible(true);
	    } else {
	        // Speichert das Spiel mit den aktuellen Parametern und der Liste der Inseln.
	        save.saveGame(getWidth(), getHeight(), getIslands(), island.getListofIslands(), bridgeC.getListOfBridge());
	        
	        // Debug-Ausgabe: Anzahl der Brücken
	        for(CreateBridges bridges : bridgeC.getListOfBridge()) {
	            System.out.println(bridges.getNumberOfBridges());
	        }
	        if(save.isCancelSave()) 	
	        	return;
	        
	        // Zeigt ein Dialogfenster an, um den Erfolg des Speicherns zu bestätigen.
	        JOptionPane.showMessageDialog(bridges.getFrmBridges(), "Die Daten wurden erfolgreich\n gespeichert!", "Speichern Erfolgreich", JOptionPane.INFORMATION_MESSAGE);
	        //success.setLocationRelativeTo(bridges.getDraw());
	        //success.setVisible(true);
	    }
	}

	




	/**
	 * Schließt das sichtbare Dialogfenster, falls eines davon sichtbar ist.
	 * Überprüft, ob das Exit-Dialogfenster, das Puzzle-Dialogfenster oder das Spielinfo-Dialogfenster sichtbar ist,
	 * und schließt es entsprechend.
	 */
	private void DisposeView() {
	    /*if (exitGame.isVisible()) {
	        exitGame.dispose(); // Schließt das Exit-Dialogfenster
	    }*/
	    if (puzzle.isVisible()) {
	        puzzle.dispose(); // Schließt das Puzzle-Dialogfenster
	    }
	    /*if (saveInfo.isVisible()) {
	        saveInfo.dispose(); // Schließt das Spielinfo-Dialogfenster
	    }*/
	}
	
	/**
	 * Aktiviert/Deaktiviert die Eingabefelder und Optionen basierend auf der ausgewählten Größenoption.
	 */
	private void Toggle() {
	    if (puzzle.getRbnAutoSizeIsland().isSelected()) {
	        // Automatische Größe ausgewählt
	        puzzle.getRbnSizeIsland().setSelected(false);
	        puzzle.getRbnSizeIsland().setEnabled(true);
	        puzzle.getTfHeight().setEnabled(false);
	        puzzle.getTfWidth().setEnabled(false);
	        puzzle.getCbDefineIslands().setEnabled(false);
	        puzzle.getTfIslands().setEnabled(false);
	    } 
	    if (puzzle.getRbnSizeIsland().isSelected()) {
	        // Manuelle Größe ausgewählt
	        puzzle.getRbnAutoSizeIsland().setSelected(false);
	        puzzle.getTfHeight().setEnabled(true);
	        puzzle.getTfWidth().setEnabled(true);
	        puzzle.getCbDefineIslands().setEnabled(true);
	        if (puzzle.getCbDefineIslands().isSelected()) {
	            puzzle.getTfIslands().setEnabled(true);
	        }
	    }
	}

	/**
	 * Aktiviert/Deaktiviert das Eingabefeld für die Anzahl der Inseln basierend auf der Auswahl.
	 */
	private void ToggleIslands() {
	    if (puzzle.getCbDefineIslands().isSelected()) {
	        // Wenn "Inselzahl festlegen" ausgewählt ist
	        puzzle.getTfIslands().setEnabled(true);
	    } else {
	        // Wenn "Inselzahl festlegen" nicht ausgewählt ist
	        puzzle.getTfIslands().setEnabled(false);
	    }
	}
	
	/**
	 * Überprüft die Benutzereingaben und erstellt ein neues Rätsel basierend auf den Eingaben.
	 */
	private void checkInput() {
	    try {
	        if (puzzle.getRbnAutoSizeIsland().isSelected()) {
	            // Benutzer hat die Option "Automatische Größe" ausgewählt
	            randomPuzzle();
	            return;
	        }

	        // Benutzereingaben für Höhe und Breite abrufen
	        height = Integer.parseInt(puzzle.getTfHeight().getText());
	        width = Integer.parseInt(puzzle.getTfWidth().getText());

	        // Benutzereingaben auf Gültigkeit prüfen
	        if (!checkInputValidity(width, height)) {
	            return;
	        }

	        // Wenn die Inselanzahl festgelegt ist
	        if (puzzle.getCbDefineIslands().isSelected()) {
	            islands = Integer.parseInt(puzzle.getTfIslands().getText());

	            // Prüfen, ob die Anzahl der Inseln gültig ist
	            if (!checkIslandsValidity(width, height, islands)) {
	                return;
	            }
	        } else {
	            // Zufällige Anzahl von Inseln zwischen 2 und einem berechneten Maximum generieren
	            int maxRandomIslands = (int) (0.2 * width * height);
	            int minRandomIslands = 2;
	            islands = randomSize.nextInt((maxRandomIslands - minRandomIslands) + 1) + minRandomIslands;
	        }

	        // Dialog schließen und neues Rätsel erstellen
	        puzzle.dispose();
	        setWidth(width);
	        setHeight(height);
	        setIslands(islands);
	        createBoard(width, height, islands);
	    } catch (NumberFormatException nfe) {
	        // Fehlermeldung bei ungültigen Eingaben anzeigen
	        JOptionPane.showMessageDialog(null, "Ungültige Eingabe. Bitte geben Sie gültige Zahlen ein.", "Fehlerhafte Eingabe", JOptionPane.ERROR_MESSAGE);
	    }
	}

	/**
	 * Prüft die Gültigkeit der Benutzereingaben für Breite und Höhe.
	 *
	 * @param width  Die eingegebene Breite.
	 * @param height Die eingegebene Höhe.
	 * @return true, wenn die Eingaben gültig sind, ansonsten false.
	 */
	private boolean checkInputValidity(int width, int height) {
		int maxWidth = 25;
		int maxHeight = 25;
	    if(width < 4 || width > maxWidth || height < 4 || height > maxHeight) {
	        JOptionPane.showMessageDialog(null, "Breite und Höhe muss zwischem 4 und 25 liegen.", "Ungültige Eingabe", JOptionPane.ERROR_MESSAGE);
	        return false;
	    }
	    return true;
	}

	/**
	 * Prüft die Gültigkeit der Benutzereingaben für die Anzahl der Inseln.
	 *
	 * @param width   Die Breite des Spielfelds.
	 * @param height  Die Höhe des Spielfelds.
	 * @param islands Die eingegebene Anzahl der Inseln.
	 * @return true, wenn die Eingaben gültig sind, ansonsten false.
	 */
	private boolean checkIslandsValidity(int width, int height, int islands) {
	    int maxIslands = (int) (0.2 * width * height);
	    if (islands < 2 || islands > maxIslands) {
	        JOptionPane.showMessageDialog(null, "Die Anzahl der Inseln muss zwischen 2 und " + maxIslands + " liegen.", "Ungültige Eingabe", JOptionPane.ERROR_MESSAGE);
	        return false;
	    }
	    return true;
	}

	
	
	/**
	 * Erstellt ein zufälliges Rätsel basierend auf zufällig generierten Werten für Breite, Höhe und Inselanzahl.
	 */
	private void randomPuzzle() {
	    // Mindest- und Höchstgrößen für das Rätsel und die Mindestanzahl der Inseln festlegen
	    int minSize = 4;
	    int maxSize = 25;
	    int minIslands = 2;

	    // Zufällige Werte für Breite, Höhe und Inselanzahl generieren
	    height = randomSize.nextInt((maxSize - minSize) + 1) + minSize;
	    width = randomSize.nextInt((maxSize - minSize) + 1) + minSize;
	    
	    // Die maximale Anzahl von Inseln basierend auf der Größe des Spielfelds berechnen
	    int maxIslands = (int) (0.2 * width * height);
	    
	    // Zufällige Anzahl von Inseln zwischen minIslands und maxIslands generieren
	    islands = randomSize.nextInt((maxIslands - minIslands) + 1) + minIslands;

	    // Werte setzen und ein neues Rätsel erstellen
	    setWidth(width);
	    setHeight(height);
	    setIslands(islands);

	    createBoard(width, height, islands);
	    return;
	}
	
	/**
	 * Erstellt ein neues Spielbrett mit den angegebenen Dimensionen und Inseln.
	 * @param width Die Breite des Spielbretts.
	 * @param height Die Höhe des Spielbretts.
	 * @param islands Die Anzahl der Inseln auf dem Spielbrett.
	 */
	private void createBoard(int width, int height, int islands) {
	    // Die Liste der Inseln löschen, um Platz für die neuen Inseln zu schaffen
	    listofIslands.clear();
	    
	    // Die Gitterwerte für die Berechnung festlegen
	    gridValues = new CalculateGrid(bridges.getDraw().getWidth(), bridges.getDraw().getHeight(), width, height);

	    // Ein neues Insel-Objekt erstellen
	    island = new Island(width, height, -1, listofIslands, 0, false, false, false, false,DEFAULT_CENTER_X,DEFAULT_CENTER_Y);

	    // Die erste Insel erstellen
	    island.createFirstIsland(islands);

	    // Solange die Insel noch nicht korrekt ist, weiter Inseln erstellen
	    do {
	        System.out.println("Creating new island...\n");
	        //island.testMethod();
	        island.createFirstIsland(islands);
	    } while (!island.isOk());

	    createDeepCopy();
	
	    // Ein neues Gitter-Objekt erstellen
	   grid = new GridPainter(width, height, gridValues.getXDistance(), gridValues.getYDistance(), deepCopy);
	   

	    // Das Puzzle-Fenster schließen
	    puzzle.dispose();

	    
	    clearAndAddGrid();

	    int delta = 0;
	    if(gridValues.getXDistance() < gridValues.getYDistance())
	    	delta = gridValues.getXDistance();
	    else
	    	delta = gridValues.getYDistance();
	    
	    bridgeC.initController(deepCopy, bridges, gridValues.getXDistance(), gridValues.getYDistance(), delta, width, height, grid);
	    if(!controllerExist) {
	    	registerMouseListener();
	    }
	    setGameExist(true);
	    
	    
	}
	
	/**
	 * Entfernt alle Komponenten aus der Zeichenfläche und fügt das Gitter hinzu.
	 * Aktualisiert dann die Anzeige, um die Änderungen sichtbar zu machen.
	 */
	private void clearAndAddGrid() {
	    // Entferne alle vorhandenen Komponenten aus der Zeichenfläche
	    bridges.getDraw().removeAll();

	    // Füge das Gitter zur Zeichenfläche in der Mitte (BorderLayout.CENTER) hinzu
	    bridges.getDraw().add(grid, BorderLayout.CENTER);

	    // Aktualisiere die Anzeige, um die Änderungen sichtbar zu machen
	    bridges.getDraw().revalidate();
	    //bridges.getDraw().repaint();
	}
	
	
	
	/**
	 * Registriert einen MouseListener für die Zeichenfläche, um Mausklicks zu verarbeiten.
	 */
	private void registerMouseListener() {
		controllerExist = true;

	    // Fügen Sie einen MouseAdapter zur Zeichenfläche hinzu, um Mausklicks zu verarbeiten
	    bridges.getDraw().addMouseListener(new MouseAdapter() {
	        @Override
	        public void mouseClicked(MouseEvent e) {
	            // Koordinaten des Mausklicks abrufen
	            int x = e.getX();
	            int y = e.getY();

	            // Den BridgeController aufrufen und die Mausklick-Koordinaten übergeben
	            bridgeC.handleMouseClick(x, y, e);
	        }
	    });
	}
	
	
	
	/**
	 * Erstellt eine tiefe Kopie der Inseln und fügt sie zur 'deepCopy'-Liste hinzu.
	 */
	private void createDeepCopy() {
	    // Vorhandene Elemente in der 'deepCopy'-Liste löschen
	    deepCopy.clear();

	    // Durchlaufe alle Inseln in der 'listofIslands'-Liste
	    for (Island island : island.getListofIslands()) {
	        // Erstelle eine Kopie der Insel mit denselben Eigenschaften
	        Island islandCopy = new Island(island.getX(), island.getY(), island.getId(), null, island.getBridgeCount(),
	                                       false, false, false, false, island.getCenterX(), island.getCenterY());
	        
	        // Füge die Inselkopie zur 'deepCopy'-Liste hinzu
	        deepCopy.add(islandCopy);
	    }
	}

	
	
	/**
	 * Beendet das Spiel und schließt die Anwendung.
	 */
	/*private void ExitGame() {
	    System.exit(0);
	}*/
	
	/**
	 * Schließt das Dialogfenster zur Bestätigung des Abbruchs beim Speichern.
	 */
	/*private void closeCancelWindow() {
	    cancelSaveOption.dispose();
	}*/

	/**
	 * Schließt das Dialogfenster für die Erfolgsmeldung nach erfolgreichem Speichern.
	 */
	/*private void closeMessage() {
	    success.dispose();
	}*/

	/**
	 * Behandelt das Abschließen eines Spiels und schließt das entsprechende Dialogfenster.
	 */
	/*private void completedGame() {
	    completeGame.dispose();
	}*/
	
    /**
     * Behandelt die Aktion bei einer Fehlermeldung und schließt das entsprechende Dialogfenster.
     */
    /*private void ErrorMessage() {
        errorInfo.dispose();
    }*/
    
    
	/**
	 * Zeigt das Exit-Dialogfenster an, um das Spiel zu beenden.
	 */
	private void ExitView() {
		exitGame.setLocationRelativeTo(bridges.getDraw());
	    exitGame.setVisible(true);
	}

	/**
	 * Zeigt das Puzzle-Dialogfenster an, um Einstellungen für ein neues Rätsel vorzunehmen.
	 */
	private void PuzzleView() {
		puzzle.setLocationRelativeTo(bridges.getDraw());
	    puzzle.setVisible(true);
	    
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
	 * @param height Die zu setzende Höhe des Spielfelds.
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
	 * @param width Die zu setzende Breite des Spielfelds.
	 */
	public void setWidth(int width) {
	    this.width = width;
	}

	/**
	 * Gibt die Anzahl der Inseln auf dem Spielfeld zurück.
	 *
	 * @return Die Anzahl der Inseln.
	 */
	public int getIslands() {
	    return islands;
	}

	/**
	 * Legt die Anzahl der Inseln auf dem Spielfeld fest.
	 *
	 * @param islands Die zu setzende Anzahl der Inseln.
	 */
	public void setIslands(int islands) {
	    this.islands = islands;
	}
	
    /**
     * Gibt das Dialogfenster für Fehlermeldungen im Zusammenhang mit Inseln zurück.
     *
     * @return Das Dialogfenster für Fehlermeldungen im Zusammenhang mit Inseln.
     */
    public ErrorIsland getErrorInfo() {
        return errorInfo;
    }

    /**
     * Legt das Dialogfenster für Fehlermeldungen im Zusammenhang mit Inseln fest.
     *
     * @param errorInfo Das Dialogfenster für Fehlermeldungen im Zusammenhang mit Inseln.
     */
    public void setErrorInfo(ErrorIsland errorInfo) {
        this.errorInfo = errorInfo;
    }
    
    
    /**
     * Überprüft, ob ein Spiel existiert.
     * 
     * @return true, wenn ein Spiel existiert, andernfalls false.
     */
    public boolean isGameExist() {
        return gameExist;
    }

    /**
     * Setzt den Zustand, ob ein Spiel existiert oder nicht.
     * 
     * @param gameExist true, wenn ein Spiel existiert, andernfalls false.
     */
    public void setGameExist(boolean gameExist) {
        this.gameExist = gameExist;
    }


}



