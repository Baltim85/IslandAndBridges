package Controller;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import UI.Bridges;
import UI.ErrorIsland;
import UI.ExitDialog;
import UI.FinishedGame;
import UI.NewPuzzle;
import UI.NoGame;
import modells.GameData.GameData;
import modells.save.SaveGame;

/**
 * Die "DialogController"-Klasse ist verantwortlich für die Verwaltung und Steuerung verschiedener Dialogfenster und 
 * Benachrichtigungen innerhalb der Anwendung. Sie ermöglicht die Interaktion des Benutzers mit dem Spiel und bietet Dialoge 
 * für Aktionen wie das Beenden des Spiels, das Erstellen neuer Rätsel, das Anzeigen von Fehlermeldungen und das Benachrichtigen 
 * des Benutzers über den erfolgreichen Abschluss des Spiels.
 *
 * Konstruktor:
 * - DialogController(GameData data): Dieser Konstruktor erstellt eine Instanz der "DialogController"-Klasse und initialisiert 
 * sie mit den bereitgestellten Spielinformationen (GameData), auf denen die Instanz arbeiten wird.

 * Methoden:
 *
 * - DisposeView(ActionController controller, Bridges bridges): Überprüft und zeigt ein Dialogfenster an, falls das Spiel 
 * noch keine Inseln enthält. Dies dient dazu, den Benutzer daran zu erinnern, Inseln auf dem Spielfeld zu platzieren.
 *
 * - ExitView(): Öffnet den Exit-Dialog, der es dem Benutzer ermöglicht, das Spiel zu beenden oder fortzusetzen. 
 * Dieses Dialogfenster ist nützlich, um den Spielstand zu speichern oder das Spiel zu verlassen.
 *
 * - PuzzleView(): Öffnet das Puzzle-Erstellungsfenster, das es dem Benutzer ermöglicht, neue Rätsel zu erstellen. 
 * Es initialisiert auch den CreatePuzzleController zur Steuerung der Rätselerstellung.
 *
 * - checkOption(int returnValue): Überprüft, ob der Benutzer eine Option ausgewählt oder den Vorgang abgebrochen hat, 
 * basierend auf dem Rückgabewert aus einem Dateiauswahldialog.
 *
 * - cancelSave(SaveGame save): Zeigt eine Fehlermeldung an und markiert den Speichervorgang als abgebrochen. Dies kann 
 * auftreten, wenn der Benutzer den Speichervorgang abbricht.
 *
 * - errorWhileSaving(SaveGame save): Zeigt eine Fehlermeldung an und markiert den Speichervorgang als abgebrochen aufgrund 
 * eines Fehlers während des Speicherns.
 *
 * - errorLoading(int returnValue): Überprüft, ob der Benutzer eine Option ausgewählt oder den Vorgang abgebrochen hat, 
 * basierend auf dem Rückgabewert aus einem Dateiauswahldialog. Dies kann bei fehlerhaften Ladevorgängen auftreten.
 *
 * - errorIslands(): Zeigt eine Fehlermeldung an, um den Benutzer über unzulässige Zustände im Spiel hinzuweisen, z.B., 
 * wenn keine Inseln platziert wurden.
 *
 * - gameCompleted(): Zeigt eine Benachrichtigung über den erfolgreichen Abschluss des Spiels an. Dies kann den Benutzer 
 * darüber informieren, dass das Rätsel erfolgreich gelöst wurde.
 *
 * - getCreatePuzzleController(): Gibt den CreatePuzzleController zurück, der für die Erstellung von Puzzles verantwortlich ist.
 *
 * - setCreatePuzzleController(CreatePuzzleController createPuzzleController): Legt den CreatePuzzleController fest, der für die 
 * Erstellung von Puzzles verwendet wird.
 *
 * - getSaveInfo(): Gibt Informationen über das nicht gespeicherte Spiel zurück, z.B., wenn keine Inseln auf dem Spielfeld platziert 
 * wurden.
 *
 * - setSaveInfo(NoGame saveInfo): Legt Informationen über das nicht gespeicherte Spiel fest.
 *
 * - getPuzzle(): Gibt das NewPuzzle-Objekt zurück, das für die Erstellung neuer Rätsel verwendet wird.
 *
 * - setPuzzle(NewPuzzle puzzle): Legt das NewPuzzle-Objekt fest, das für die Erstellung neuer Rätsel verwendet wird.
 *
 * - getExitGame(): Gibt das ExitDialog-Objekt zurück, das für den Exit-Dialog im Spiel verwendet wird.
 *
 * - setExitGame(ExitDialog exitGame): Legt das ExitDialog-Objekt fest, das für den Exit-Dialog im Spiel verwendet wird.
 *
 * - getBridges(): Gibt die Bridges-Instanz zurück, die das Spiel verwaltet.
 *
 * - setBridges(Bridges bridges): Legt die Bridges-Instanz fest, die das Spiel verwaltet.
 *
 * Die "DialogController"-Klasse spielt eine zentrale Rolle in der Interaktion des Benutzers mit der Anwendung und sorgt für eine benutzerfreundliche Erfahrung bei der Erstellung, dem Lösen und dem Verwalten von Rätseln. Sie ermöglicht die Verwaltung von Dialogen und Benachrichtigungen, um den Benutzer bei verschiedenen Aktionen und Entscheidungen zu unterstützen.
 */
public class DialogController {

	private NoGame saveInfo;                  // Ein Dialogfenster für fehlende Spielinformationen
	private ExitDialog exitGame;              // Ein Dialogfenster zum Beenden des Spiels
	private Bridges bridges;                  // Die Hauptanwendungsinstanz
	private NewPuzzle puzzle;                // Ein Fenster zur Erstellung neuer Rätsel
	private CreatePuzzleController createPuzzleController; // Controller zur Steuerung des Rätselerstellungsprozesses


	/**
	 * Enthält referenzierte Objekte für Spielinformationen, Fehler- und Abschlussmeldungen.
	 */
	private GameData data;
	private ErrorIsland errorInfo;
	private FinishedGame gameCompleted;

	/**
	 * Konstruktor für die "DialogController"-Klasse. Initialisiert Dialogfenster und Controller für die Benutzeroberfläche.
	 *
	 * @param bridges     Die Hauptanwendungsinstanz, die die Benutzeroberfläche verwaltet.
	 * @param controller  Ein Controller zur Steuerung der Spiellogik.
	 */
	public DialogController( GameData data) {
		setBridges(data.getBridges());
		exitGame = new ExitDialog();
		puzzle = new NewPuzzle();
		createPuzzleController = new CreatePuzzleController(puzzle, data); 
		errorInfo = new ErrorIsland();
		gameCompleted = new FinishedGame();

		this.data = data;
	}



	/**
	 * Schließt das sichtbare Dialogfenster, falls eines davon sichtbar ist.
	 * Überprüft, ob das Exit-Dialogfenster, das Puzzle-Dialogfenster oder das Spielinfo-Dialogfenster sichtbar ist,
	 * und schließt es entsprechend.
	 */
	public boolean DisposeView(Bridges bridges) {
		saveInfo = new NoGame();
		if(data.getDeepCopy().isEmpty()) {
			// Wenn keine Inseln vorhanden sind, wird ein Fehlerdialog angezeigt.
			saveInfo.setLocationRelativeTo(bridges.getDraw());
			saveInfo.setVisible(true);
			return true;
		}
		return false;

	}


	/**
	 * Öffnet die Ansicht für den Exit-Dialog und positioniert sie relativ zum Zeichenfeld der Bridges-Anwendung.
	 * Der Exit-Dialog wird sichtbar gemacht und ermöglicht dem Benutzer, das Spiel zu beenden oder fortzusetzen.
	 */
	public void ExitView() {
		// Setze die Position des Exit-Dialogs relativ zum Zeichenfeld von Bridges
		exitGame.setLocationRelativeTo(getBridges().getDraw());

		// Mache den Exit-Dialog sichtbar
		exitGame.setVisible(true);
	}

	/**
	 * Öffnet die Ansicht für das Puzzle-Erstellungsfenster und positioniert es relativ zum Zeichenfeld der Bridges-Anwendung.
	 * Das Puzzle-Erstellungsfenster wird sichtbar gemacht und ermöglicht dem Benutzer, neue Rätsel zu erstellen.
	 * Es wird auch ein CreatePuzzleController initialisiert, um die Steuerung der Rätselerstellung zu ermöglichen.
	 */
	public void PuzzleView() {
		// Setze die Position des Puzzle-Erstellungsfensters relativ zum Zeichenfeld von Bridges
		puzzle.setLocationRelativeTo(getBridges().getDraw());

		// Mache das Puzzle-Erstellungsfenster sichtbar
		puzzle.setVisible(true);

	}

	/**
	 * Überprüft, ob der Benutzer eine Option ausgewählt hat oder den Vorgang abgebrochen hat.
	 *
	 * @param returnValue Der Rückgabewert aus dem Dateiauswahldialog.
	 * @return true, wenn der Benutzer eine Option ausgewählt hat; false, wenn der Vorgang abgebrochen wurde.
	 */
	public boolean checkOption(int returnValue) {
		if (returnValue != JFileChooser.APPROVE_OPTION) {
			// Wenn der Benutzer den Vorgang abgebrochen hat, wird eine Fehlermeldung angezeigt und false zurückgegeben
			JOptionPane.showMessageDialog(null, "Vorgang abgebrochen!", "Abgebrochen", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	/**
	 * Zeigt eine Fehlermeldung an und markiert den Speichervorgang als abgebrochen.
	 *
	 * @param save Das SaveGame-Objekt, das den Speichervorgang steuert.
	 */
	public void cancelSave(SaveGame save) {
		JOptionPane.showMessageDialog(null, "Speichervorgang wurde abgebrochen!", "Speichern abgebrochen", JOptionPane.ERROR_MESSAGE);
		save.setCancelSave(true);
	}

	/**
	 * Zeigt eine Fehlermeldung an und markiert den Speichervorgang als abgebrochen aufgrund eines Fehlers.
	 *
	 * @param save Das SaveGame-Objekt, das den Speichervorgang steuert.
	 */
	public void errorWhileSaving(SaveGame save) {
		JOptionPane.showMessageDialog(null, "Fehler während des Speichervorgangs!", "Speichern abgebrochen", JOptionPane.ERROR_MESSAGE);
		save.setCancelSave(true);
	}

	/**
	 * Überprüft, ob der Benutzer eine Option ausgewählt hat oder den Vorgang abgebrochen hat.
	 *
	 * @param returnValue Der Rückgabewert aus dem Dateiauswahldialog.
	 * @return true, wenn der Benutzer eine Option ausgewählt hat; false, wenn der Vorgang abgebrochen wurde.
	 */
	public boolean errorLoading(int returnValue) {
		if (returnValue != JFileChooser.APPROVE_OPTION) {
			// Wenn der Benutzer den Vorgang abgebrochen hat, wird eine Fehlermeldung angezeigt und false zurückgegeben
			JOptionPane.showMessageDialog(null, "Ladevorgang abgebrochen!", "Laden abgebrochen", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	
	


	/**
	 * Zeigt eine Fehlermeldung an, um den Benutzer auf fehlerhafte Bedingungen im Spiel hinzuweisen.
	 * Diese Methode öffnet ein Fehlerinformationsfenster, das den Benutzer über unzulässige Zustände im Spiel informiert.
	 */
	public void errorIslands() {
		// Platziert das Fehlerinformationsfenster relativ zur Zeichenfläche des Spiels
		errorInfo.setLocationRelativeTo(data.getBridges().getDraw());
		// Macht das Fehlerinformationsfenster sichtbar
		errorInfo.setVisible(true);
		// Markiert, dass ein Fehler sichtbar ist
		data.setErrorVisible(true);
	}

	/**
	 * Zeigt eine Meldung an, um den Benutzer über den erfolgreichen Abschluss des Spiels zu informieren.
	 * Diese Methode öffnet ein Benachrichtigungsfenster, um den Benutzer darüber zu benachrichtigen, dass das Rätsel erfolgreich gelöst wurde.
	 */
	public void gameCompleted() {
		// Platziert das Benachrichtigungsfenster relativ zur Zeichenfläche des Spiels
		gameCompleted.setLocationRelativeTo(bridges.getDraw());
		// Macht das Benachrichtigungsfenster sichtbar
		gameCompleted.setVisible(true);
	}
	
	/**
	 * Zeigt eine Bestätigungsnachricht an, um den erfolgreichen Abschluss des Speichervorgangs zu melden.
	 */
	public void saveSuccessfully() {
		JOptionPane.showMessageDialog(getBridges().getFrmBridges(), "Die Daten wurden erfolgreich\n gespeichert!", "Speichern Erfolgreich", JOptionPane.INFORMATION_MESSAGE);
	}


	/**
	 * Gibt den CreatePuzzleController zurück, der für die Erstellung von Puzzles verantwortlich ist.
	 *
	 * @return Der CreatePuzzleController.
	 */
	public CreatePuzzleController getCreatePuzzleController() {
		return createPuzzleController;
	}

	/**
	 * Legt den CreatePuzzleController fest, der für die Erstellung von Puzzles verwendet wird.
	 *
	 * @param createPuzzleController Der CreatePuzzleController.
	 */
	public void setCreatePuzzleController(CreatePuzzleController createPuzzleController) {
		this.createPuzzleController = createPuzzleController;
	}



	/**
	 * Gibt Informationen über das nicht gespeicherte Spiel zurück.
	 *
	 * @return Die Informationen über das nicht gespeicherte Spiel (NoGame).
	 */
	public NoGame getSaveInfo() {
		return saveInfo;
	}

	/**
	 * Legt Informationen über das nicht gespeicherte Spiel fest.
	 *
	 * @param saveInfo Die Informationen über das nicht gespeicherte Spiel (NoGame).
	 */
	public void setSaveInfo(NoGame saveInfo) {
		this.saveInfo = saveInfo;
	}

	/**
	 * Gibt das NewPuzzle-Objekt zurück, das für die Erstellung neuer Rätsel verwendet wird.
	 *
	 * @return Das NewPuzzle-Objekt.
	 */
	public NewPuzzle getPuzzle() {
		return puzzle;
	}

	/**
	 * Legt das NewPuzzle-Objekt fest, das für die Erstellung neuer Rätsel verwendet wird.
	 *
	 * @param puzzle Das NewPuzzle-Objekt.
	 */
	public void setPuzzle(NewPuzzle puzzle) {
		this.puzzle = puzzle;
	}

	/**
	 * Gibt das ExitDialog-Objekt zurück, das für den Exit-Dialog im Spiel verwendet wird.
	 *
	 * @return Das ExitDialog-Objekt.
	 */
	public ExitDialog getExitGame() {
		return exitGame;
	}

	/**
	 * Legt das ExitDialog-Objekt fest, das für den Exit-Dialog im Spiel verwendet wird.
	 *
	 * @param exitGame Das ExitDialog-Objekt.
	 */
	public void setExitGame(ExitDialog exitGame) {
		this.exitGame = exitGame;
	}

	/**
	 * Gibt die Bridges-Instanz zurück, die das Spiel verwaltet.
	 *
	 * @return Die Bridges-Instanz.
	 */
	public Bridges getBridges() {
		return bridges;
	}

	/**
	 * Legt die Bridges-Instanz fest, die das Spiel verwaltet.
	 *
	 * @param bridges Die Bridges-Instanz.
	 */
	public void setBridges(Bridges bridges) {
		this.bridges = bridges;
	}

}
