package modells.Support;

import java.util.Random;

import modells.CreateGame.CreateGame;
import modells.CreateGame.PuzzleCreator;
import modells.GameData.GameData;

/**
 * Die Klasse "CreateRandomPuzzle" implementiert das "PuzzleCreator"-Interface und bietet eine Methode zum Generieren eines 
 * zufälligen Puzzles. 
 * Sie erzeugt zufällige Werte für die Breite, Höhe und die Anzahl der Inseln innerhalb vordefinierter Mindest- und Höchstgrenzen 
 * und erstellt ein neues Rätsel basierend auf diesen Parametern.
 * Diese Klasse ermöglicht die Erzeugung von zufälligen Puzzle-Szenarien für das Spiel.
 *
 * Implementiertes Interface:
 * - PuzzleCreator: Dieses Interface definiert die Methode "createPuzzle", die zum Erstellen von Puzzles verwendet wird.
 *
 * Methoden:
 *
 * - createPuzzle(GameData data): Diese Methode implementiert das "createPuzzle"-Interface und erstellt ein neues Rätsel mit 
 * zufällig generierten Parametern. 
 *   Sie legt die Breite, Höhe und die Anzahl der Inseln basierend auf vordefinierten Mindest- und Höchstgrenzen fest und 
 *   verwendet diese Werte zur Erstellung des Puzzles.
 *   Das generierte Puzzle wird im übergebenen "GameData"-Objekt gespeichert.
 */
public class CreateRandomPuzzle implements PuzzleCreator{

	// Zufallszahlengenerator für die Erstellung von zufälligen Puzzle-Größen
	private Random randomSize = new Random();


	/**
	 * Die Methode "createPuzzle" erstellt ein neues Puzzle mit zufällig generierten Parametern. Dies umfasst die Festlegung von
	 * Breite, Höhe und der Anzahl der Inseln basierend auf vordefinierten Mindest- und Höchstgrenzen. Nach der Generierung der
	 * Parameter wird ein neues Rätsel erstellt, indem diese Werte verwendet werden.
	 *
	 * @param data Das GameData-Objekt, in dem die generierten Puzzle-Parameter gespeichert werden.
	 */
	@Override
	public void createPuzzle(GameData data) {
	    // Mindest- und Höchstgrößen für das Rätsel und die Mindestanzahl der Inseln festlegen
	    int minSize = 4;
	    int maxSize = 25;
	    int minIslands = 2;
	    int width, height, islands;

	    // Zufällige Werte für Breite, Höhe und Inselanzahl generieren
	    height = randomSize.nextInt((maxSize - minSize) + 1) + minSize;
	    width = randomSize.nextInt((maxSize - minSize) + 1) + minSize;

	    // Die maximale Anzahl von Inseln basierend auf der Größe des Spielfelds berechnen
	    int maxIslands = (int) (0.2 * width * height);

	    // Zufällige Anzahl von Inseln zwischen minIslands und maxIslands generieren
	    islands = randomSize.nextInt((maxIslands - minIslands) + 1) + minIslands;

	    // Puzzle-Parameter setzen und ein neues Rätsel erstellen
	    data.setWidth(width);
	    data.setHeight(height);
	    data.setIslands(islands);

	    // Ein neues CreateGame-Objekt erstellen und das Spielbrett generieren
	    CreateGame createGame = new CreateGame(data);
	    createGame.createBoard(islands);
	}


}
