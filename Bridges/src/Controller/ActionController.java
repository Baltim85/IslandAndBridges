package Controller;




import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import UI.Bridges;
import UI.ErrorIsland;
import UI.ExitDialog;
import UI.NewPuzzle;
import UI.NoGame;
import Modell.CalculateGrid;
import Modell.CheckInput;

import Modell.GridPainter;
import Modell.Island;

import SaveLoad.*;

/**
 * Die `ActionController`-Klasse ist die Hauptklasse für die Steuerung der Anwendung,
 * die die Benutzeroberfläche und Spiellogik verknüpft.
 */
public class ActionController {
	// Felder zur Speicherung der Hauptkomponenten der Anwendung
	private Bridges bridges;         // Das Hauptfenster der Anwendung
	private ExitDialog exitGame;     // Ein Dialogfenster zum Beenden des Spiels
	private NewPuzzle puzzle;        // Ein Dialogfenster zur Puzzle-Erstellung
	private NoGame saveInfo;         // Ein Dialogfenster für fehlende Spielinformationen
	private SaveGame save;           // Eine Klasse zur Speicherung von Spielständen
	private LoadGame load;           // Eine Klasse zur Verwaltung des Ladens von Spielständen
	
	private ErrorIsland errorInfo;

	public ErrorIsland getErrorInfo() {
		return errorInfo;
	}



	public void setErrorInfo(ErrorIsland errorInfo) {
		this.errorInfo = errorInfo;
	}

	// Felder zur Verwaltung von Spielinformationen und Zuständen
	private int height;              // Die Höhe des Spielfelds
	private int width;               // Die Breite des Spielfelds
	private int islands;             // Die Anzahl der Inseln auf dem Spielfeld
	private CheckInput checkInput;   // Ein Objekt zur Überprüfung von Benutzereingaben

	// Felder zur Darstellung des Spielfelds und der Inseln
	private GridPainter grid;       // Das Spielfeld, auf dem das Puzzle gezeichnet wird
	private Island island;           // Eine einzelne Insel im Puzzle
	private CalculateGrid gridValues; // Eine Klasse zur Berechnung von Gitterwerten

	// Konstanten für die minimale und maximale Spielfeldgröße
	private final int minValue = 4;
	private final int maxValue = 25;

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
	
	// Hier wird eine Konstante für den Standardwert des Y-Zentrums definiert.
	private static final int DEFAULT_CENTER_Y = 0; 

    
    
    /**
     * Konstruktor für den ActionController. Initialisiert die Dialogfenster und fügt Listener hinzu.
     *
     * @param bridges Die Hauptanwendung (Bridges), zu der dieser Controller gehört.
     */
    public ActionController(Bridges bridges) {
        this.bridges = bridges;
        exitGame = new ExitDialog();
        errorInfo = new ErrorIsland();
        puzzle = new NewPuzzle();
        checkInput = new CheckInput();
        bridgeC = new BridgeController();
        save = new SaveGame();
        saveInfo = new NoGame();
        addListener();
    }
	
	
	
	/**
	 * Fügt ActionListeners zu verschiedenen UI-Elementen hinzu.
	 */
	public void addListener() {
	    // Menüaktionen
	    bridges.getMiNewPuzzle().addActionListener(e -> PuzzleView()); // Öffnet ein neues Rätselansichtsfenster
	    bridges.getMiQuit().addActionListener(e -> ExitView()); // Öffnet ein Bestätigungsdialogfenster zum Beenden
	    bridges.getMiSavePuzzle().addActionListener(e -> SaveGame()); // Speichert das aktuelle Spiel
	    bridges.getMiLoadPuzzle().addActionListener(e -> loadGame()); // Lädt ein gespeichertes Spiel
	    bridges.getMiRestartPuzzle().addActionListener(e -> restartPuzzle());

	    // Dialog für Spielinformationen
	    saveInfo.getBtnOK().addActionListener(e -> DisposeView()); // Schließt das Dialogfenster für Spielinformationen

	    // Dialog für Spiel beenden
	    exitGame.getBtnExit().addActionListener(e -> ExitGame()); // Beendet das Spiel
	    exitGame.getBtnNo().addActionListener(e -> DisposeView()); // Schließt das Bestätigungsdialogfenster

	    errorInfo.getBtnNo().addActionListener(e -> ErrorMessage());
	    
	    
	    // Puzzle-Einstellungen
	    puzzle.getRbnAutoSizeIsland().addActionListener(e -> Toggle()); // Aktiviert die automatische Größenanpassung
	    puzzle.getRbnSizeIsland().addActionListener(e -> Toggle()); // Aktiviert die manuelle Größeneinstellung
	    puzzle.getCbDefineIslands().addActionListener(e -> ToggleIslands()); // Aktiviert die Einstellung der Inselanzahl
	    puzzle.getBtnOk().addActionListener(e -> checkInput()); // Überprüft die Benutzereingabe
	    puzzle.getBtnAbord().addActionListener(e -> DisposeView()); // Schließt das Einstellungsfenster
	}
	
	private void restartPuzzle() {
		System.out.println("Restat");
		
		if(!isGameExist()) {
			return;
		} else {
			bridgeC.clearLists();
			createDeepCopy();
			   
			   for (Island island : island.getListofIslands()) {
				    Island islandCopy = new Island(island.getX(), island.getY(), island.getId(),null, island.getBridgeCount(), false, false, false,false,  island.getCenterX(), island.getCenterY());
				    deepCopy.add(islandCopy);
				}
			grid = new GridPainter(width, height, gridValues.getXDistance(), gridValues.getYDistance(), deepCopy);
		    clearAndAddGrid();
	
		    int delta = 0;
		    if(gridValues.getXDistance() < gridValues.getYDistance())
		    	delta = gridValues.getXDistance();
		    else
		    	delta = gridValues.getYDistance();
		    
		    bridgeC.initController(deepCopy, bridges, gridValues.getXDistance(), gridValues.getYDistance(), delta, width, height, grid);
			//return null;
		}
	}

	private void createDeepCopy() {
		deepCopy.clear();
		   
		for (Island island : island.getListofIslands()) {
			    Island islandCopy = new Island(island.getX(), island.getY(), island.getId(),null, island.getBridgeCount(), false, false, false,false,  island.getCenterX(), island.getCenterY());
			    deepCopy.add(islandCopy);
		}
	}
	
	private boolean gameExist = false;

	public boolean isGameExist() {
		return gameExist;
	}



	public void setGameExist(boolean gameExist) {
		this.gameExist = gameExist;
	}



	private void ErrorMessage() {
		errorInfo.dispose();
		
	}



	/**
	 * Lädt ein gespeichertes Spiel und initialisiert die erforderlichen Komponenten.
	 */
	private void loadGame() {
	    // Laden der Spielinformationen aus der LoadGame-Klasse
	    load = new LoadGame();
	    
	    // Berechnung der Gitterwerte basierend auf den Spielinformationen
	    gridValues = new CalculateGrid(bridges.getDraw().getWidth(), bridges.getDraw().getHeight(), load.getWidth(), load.getHeight());

	    // Erstellen einer Liste von Inseln durch Verarbeitung der Informationen aus der Datei
	    ArrayList<Island> islandsA = new ArrayList<Island>();
	    for (int[] islandInfo : load.getIslandsList()) {
	        int x = islandInfo[0];
	        int y = islandInfo[1];
	        int bridge = islandInfo[2];
	        
	        // Eine Insel erstellen und zur Liste hinzufügen
	        Island islands = new Island(x, y, islandsA.size() + 1, bridge);
	        islandsA.add(islands);
	    }

	    // Erstellen des Rasters mit den geladenen Informationen
	    grid = new GridPainter(load.getWidth(), load.getHeight(), gridValues.getXDistance(), gridValues.getYDistance(),  islandsA);

	    // Entfernen der alten Zeichenfläche und Hinzufügen des neuen Rasters
	    bridges.getDraw().removeAll();
	    bridges.getDraw().add(grid, BorderLayout.CENTER);

	    // Aktualisieren der Oberfläche
	    bridges.getDraw().revalidate();
	    bridges.getDraw().repaint();
	    grid.revalidate();
	    grid.repaint();
	}
	
	/**
	 * Speichert das aktuelle Spiel, sofern es vorhanden ist, oder zeigt eine Fehlermeldung an.
	 */
	private void SaveGame() {
	    if (island == null) {
	        // Wenn keine Inseln vorhanden sind, wird ein Fehlerdialog angezeigt.
	        saveInfo.setVisible(true);
	    } else {
	        // Speichert das Spiel mit den aktuellen Parametern und der Liste der Inseln.
	        save.saveGame(getWidth(), getHeight(), getIslands(), island.getListofIslands());
	    }
	}

	/**
	 * Zeigt das Exit-Dialogfenster an, um das Spiel zu beenden.
	 */
	private void ExitView() {
	    exitGame.setVisible(true);
	}

	/**
	 * Zeigt das Puzzle-Dialogfenster an, um Einstellungen für ein neues Rätsel vorzunehmen.
	 */
	private void PuzzleView() {
	    puzzle.setVisible(true);
	    //listofIslands.clear(); // Diese Zeile ist auskommentiert. Falls notwendig, kann sie verwendet werden.
	}

	/**
	 * Beendet das Spiel und schließt die Anwendung.
	 */
	private void ExitGame() {
	    System.exit(0);
	}

	/**
	 * Schließt das sichtbare Dialogfenster, falls eines davon sichtbar ist.
	 * Überprüft, ob das Exit-Dialogfenster, das Puzzle-Dialogfenster oder das Spielinfo-Dialogfenster sichtbar ist,
	 * und schließt es entsprechend.
	 */
	private void DisposeView() {
	    if (exitGame.isVisible()) {
	        exitGame.dispose(); // Schließt das Exit-Dialogfenster
	    }
	    if (puzzle.isVisible()) {
	        puzzle.dispose(); // Schließt das Puzzle-Dialogfenster
	    }
	    if (saveInfo.isVisible()) {
	        saveInfo.dispose(); // Schließt das Spielinfo-Dialogfenster
	    }
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
	    if (width <= 0 || height <= 0) {
	        JOptionPane.showMessageDialog(null, "Breite und Höhe müssen größer als 0 sein.", "Ungültige Eingabe", JOptionPane.ERROR_MESSAGE);
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

	    /*deepCopy.clear();
		   
		   for (Island island : island.getListofIslands()) {
			    Island islandCopy = new Island(island.getX(), island.getY(), island.getId(),null, island.getBridgeCount(), false, false, false,false,  island.getCenterX(), island.getCenterY());
			    deepCopy.add(islandCopy);
			}*/
	    createDeepCopy();
	
	    // Ein neues Gitter-Objekt erstellen
	   grid = new GridPainter(width, height, gridValues.getXDistance(), gridValues.getYDistance(), deepCopy);
	   
	   //Collections.copy(island.getListofIslands(), deepCopy);
	    
	   
	   /*deepCopy.clear();
	   
	   for (Island island : island.getListofIslands()) {
		    Island islandCopy = new Island(island.getX(), island.getY(), island.getId(),null, island.getBridgeCount(), false, false, false,false,  island.getCenterX(), island.getCenterY());
		    deepCopy.add(islandCopy);
		}*/

	  
	   
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
}



