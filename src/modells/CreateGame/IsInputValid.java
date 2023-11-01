package modells.CreateGame;

import UI.NewPuzzle;
import modells.GameData.GameData;
import modells.Support.CreateRandomPuzzle;
import modells.Support.IsGameValid;


/**
 * Die Klasse "IsInputValid" bietet Methoden zur Überprüfung der Benutzereingaben, um sicherzustellen, dass sie gültig sind, 
 * und zur Erstellung des Spiels entsprechend den Eingaben. Sie verwendet andere Klassen und Objekte, um diese Aufgaben auszuführen.
 *
 * Konstruktor:
 * - IsInputValid(GameData data): Dieser Konstruktor erstellt eine Instanz der "IsInputValid" Klasse und initialisiert sie mit 
 * den erforderlichen Objekten und Daten, einschließlich des "GameData"-Objekts, das Spielinformationen enthält.
 *
 * Methoden:
 *
 * - checkInput(NewPuzzle puzzle): Diese Methode überprüft die Benutzereingaben, um sicherzustellen, dass sie gültig sind, und 
 * erstellt das Spiel entsprechend den Eingaben. Sie akzeptiert ein "NewPuzzle"-Objekt, das die Benutzereingaben enthält. 
 * Die Methode überprüft, ob der Benutzer die Option "Automatische Größe" ausgewählt hat. In diesem Fall wird ein zufälliges 
 * Spiel erstellt und das Einstellungsfenster geschlossen. Andernfalls überprüft die Methode die Eingaben auf Gültigkeit und 
 * erstellt das Spiel gemäß den Eingaben, bevor sie das Einstellungsfenster schließt.
 */
public class IsInputValid {

	private CreateRandomPuzzle randomGame; // Eine Instanz der CreateRandomPuzzle-Klasse, die für die Erstellung zufälliger Puzzles verwendet wird.
	private CreateGame createGame; // Eine Instanz der CreateGame-Klasse, die für die Erstellung von Spielen verwendet wird.

	private IsGameValid isGameValid; // Eine Instanz der IsGameValid-Klasse, die für die Überprüfung der Gültigkeit von Spielen verwendet wird.

	private GameData data; // Eine Instanz der GameData-Klasse, die Daten für das Spiel enthält.


	/**
	 * Erstellt eine Instanz der "IsInputValid" Klasse und initialisiert sie mit den erforderlichen Objekten und Daten.
	 *
	 * @param data Das "GameData"-Objekt, das Spielinformationen enthält.
	 */
	public IsInputValid(GameData data) {
		// Initialisierung der notwendigen Instanzen und Daten
		randomGame = new CreateRandomPuzzle();
		isGameValid = new IsGameValid();
		this.data = data;
		createGame = new CreateGame(data);
	}

	/**
	 * Überprüft die Benutzereingaben, um sicherzustellen, dass sie gültig sind, und erstellt das Spiel entsprechend den Eingaben.
	 *
	 * @param puzzle Das "NewPuzzle"-Objekt, das die Benutzereingaben enthält.
	 */
	public void checkInput(NewPuzzle puzzle) {
		// Initialisierung der notwendigen Instanzen und Objekte
		randomGame = new CreateRandomPuzzle();
		isGameValid = new IsGameValid();
		createGame = new CreateGame(data);

		if (puzzle.getRbnAutoSizeIsland().isSelected()) {
			// Der Benutzer hat die Option "Automatische Größe" ausgewählt
			// Zufälliges Spiel erstellen und das Einstellungsfenster schließen
			randomGame.createPuzzle(data);
			puzzle.dispose();
			return;
		}
		if (isGameValid.checkInput(puzzle, data)) {

			createGame.createBoard(data.getIslands());
			puzzle.dispose();
		}
	}

}
