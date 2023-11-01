package Controller;

import UI.NewPuzzle;
import modells.CreateGame.IsInputValid;
import modells.GameData.GameData;
import modells.uiSupport.ManualSetIslands;
import modells.uiSupport.ManualSizeIslandToggle;

/**
 * Die "CreatePuzzleController" Klasse ist für die Steuerung der Benutzeroberfläche zur Erstellung von Puzzles verantwortlich. 
 * Sie initialisiert UI-Elemente und Event-Listener, um auf Benutzerinteraktionen zu reagieren und Einstellungen für die 
 * Puzzle-Erstellung zu ermöglichen.

 * Attribute:
 * - puzzle: Die Benutzeroberfläche "NewPuzzle" für die Puzzle-Erstellung.
 * - isValid: Ein Objekt zur Überprüfung der Benutzereingaben auf Gültigkeit.
 * - data: Ein "GameData"-Objekt zur Verwaltung von Spieldaten.
 * - autoSize: Ein Toggle-Objekt für die automatische Größenanpassung der Inseln.
 * - manualSize: Ein Toggle-Objekt für die manuelle Größeneinstellung der Inseln.
 * - manualIsland: Ein Objekt zur manuellen Einstellung der Inselanzahl.

 * Konstruktor:
 * - CreatePuzzleController(NewPuzzle puzzle, GameData data): Erstellt eine neue Instanz der "CreatePuzzleController"-Klasse 
 * und initialisiert die UI-Elemente und Event-Listener für die Puzzle-Erstellung.

 * Methoden:
 * - addListener(): Fügt Event-Listener zu verschiedenen UI-Elementen der Benutzeroberfläche hinzu, um auf Benutzerinteraktionen 
 * zu reagieren.
 * - DisposeView(): Schließt das sichtbare Dialogfenster, falls eines davon sichtbar ist.
 * - getPuzzle(): Gibt die Benutzeroberfläche "NewPuzzle" für die Puzzle-Erstellung zurück.
 * - setPuzzle(NewPuzzle puzzle): Setzt die Benutzeroberfläche "NewPuzzle" für die Puzzle-Erstellung.
 * - getData(): Gibt das "GameData"-Objekt zurück, das für die Verwaltung von Spieldaten verwendet wird.
 * - setData(GameData data): Setzt das "GameData"-Objekt zur Verwaltung von Spieldaten.

 * Die "CreatePuzzleController" Klasse erleichtert die Steuerung und Interaktion bei der Erstellung von Puzzles in der 
 * Benutzeroberfläche der Anwendung.
 */
public class CreatePuzzleController {

	private NewPuzzle puzzle;

	private IsInputValid isValid;
	private GameData data;
	private AutoSizeIslandToggle autoSize;
	private ManualSizeIslandToggle manualSize;
	private ManualSetIslands manualIsland;



	/**
	 * Konstruktor für die "CreatePuzzleController"-Klasse.
	 *
	 * @param puzzle     Die Benutzeroberfläche "NewPuzzle" für die Puzzle-Erstellung.
	 * @param controller Der ActionController, der für die Spielsteuerung verantwortlich ist.
	 */
	public CreatePuzzleController(NewPuzzle puzzle, GameData data) {
		autoSize = new AutoSizeIslandToggle(puzzle);
		manualSize = new ManualSizeIslandToggle(puzzle);
		manualIsland = new ManualSetIslands(puzzle);
		setPuzzle(puzzle);
		this.setData(data);
		isValid = new IsInputValid(data);
		addListener();
	}


	/**
	 * Fügt Event-Listener zu verschiedenen UI-Elementen der Benutzeroberfläche hinzu, um auf Benutzerinteraktionen zu reagieren.
	 * Die hinzugefügten Listener aktivieren oder deaktivieren bestimmte Einstellungen und Aktionen basierend auf den vom Benutzer
	 * getroffenen Auswahlmöglichkeiten.
	 */
	private void addListener() {
		// Listener für automatische Größenanpassung der Inseln
		getPuzzle().getRbnAutoSizeIsland().addActionListener(e -> autoSize.toggleElement());

		// Listener für manuelle Größeneinstellung der Inseln
		getPuzzle().getRbnSizeIsland().addActionListener(e -> manualSize.toggleElement());

		// Listener für die Einstellung der Inselanzahl
		getPuzzle().getCbDefineIslands().addActionListener(e -> manualIsland.toggleElement());
		// Listener für die "OK"-Schaltfläche zur Überprüfung der Benutzereingabe
		getPuzzle().getBtnOk().addActionListener(e -> isValid.checkInput(getPuzzle()));


		// Listener für die "Abbrechen"-Schaltfläche zum Schließen des Einstellungsfensters
		puzzle.getBtnAbord().addActionListener(e -> DisposeView());
	}

	/**
	 * Schließt das sichtbare Dialogfenster, falls eines davon sichtbar ist.
	 * Überprüft, ob das Exit-Dialogfenster, das Puzzle-Dialogfenster oder das Spielinfo-Dialogfenster sichtbar ist,
	 * und schließt es entsprechend.
	 */
	private void DisposeView() {
		System.out.println("Click");
		if (getPuzzle().isVisible()) {

			getPuzzle().dispose(); // Schließt das Puzzle-Dialogfenster
		}
	}



	/**
	 * Gibt die Benutzeroberfläche "NewPuzzle" zurück, die für die Puzzle-Erstellung verwendet wird.
	 *
	 * @return Die Benutzeroberfläche "NewPuzzle" für die Puzzle-Erstellung.
	 */
	public NewPuzzle getPuzzle() {
		return puzzle;
	}

	/**
	 * Setzt die Benutzeroberfläche "NewPuzzle" für die Puzzle-Erstellung.
	 *
	 * @param puzzle Die Benutzeroberfläche "NewPuzzle" für die Puzzle-Erstellung.
	 */
	public void setPuzzle(NewPuzzle puzzle) {
		this.puzzle = puzzle;
	}


	public GameData getData() {
		return data;
	}


	public void setData(GameData data) {
		this.data = data;
	}





}
