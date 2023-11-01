package modells.CreateGame;

import modells.GameData.GameData;

/**
 * Das "PuzzleCreator"-Interface definiert eine Methode zum Erstellen eines neuen Puzzle-Spiels. Klassen, die dieses Interface
 * implementieren, müssen die Methode "createPuzzle" implementieren, um ein neues Puzzle mit den gegebenen Parametern zu erstellen.
 *
 * Das Interface ermöglicht die Abstraktion der Erstellung von Puzzle-Spielen und bietet eine gemeinsame Schnittstelle
 * für verschiedene Implementierungen, die unterschiedliche Wege zur Generierung von Puzzles verwenden können.
 */
public interface PuzzleCreator {

    /**
     * Erstellt ein neues Puzzle-Spiel mit den gegebenen Parametern und speichert die Ergebnisse im übergebenen "GameData"-Objekt.
     *
     * @param data Das "GameData"-Objekt, in dem die generierten Puzzle-Parameter und das erstellte Puzzle gespeichert werden.
     */
    void createPuzzle(GameData data);
}
