package modells.CreateGame;

import java.util.ArrayList;

import Modell.GridPainter;
import Modell.Island;
import modells.GameData.GameData;
import modells.Support.ClearLists;

/**
 * Die Klasse "RestartGame" dient zur Wiederaufnahme eines Spiels, indem der Spielzustand und die Komponenten zurückgesetzt werden.
 * Wenn kein Spiel aktiv ist, hat diese Methode keine Auswirkungen.
 *
 * Diese Klasse beinhaltet eine Methode zum Zurücksetzen eines Puzzle-Spiels auf den Ausgangszustand.
 *
 * Attribute:
 * - private GameData data: Ein Objekt, das die Spieldaten enthält.
 * - private ClearLists clearList: Ein Objekt, das zum Löschen von Datenlisten verwendet wird.
 *
 * Methoden:
 *
 * - public void restartPuzzle(): Startet das Puzzle neu, indem der Spielzustand und die Komponenten zurückgesetzt werden.
 *   Wenn kein Spiel aktiv ist, hat diese Methode keine Auswirkungen. Andernfalls führt sie die folgenden Schritte aus:
 *   - Löscht bestehende Datenlisten und erstellt eine tiefe Kopie der Insel-Liste.
 *   - Ruft die Breite, Höhe und Abstände zwischen den Rasterelementen ab.
 *   - Erstellt ein neues GridPainter-Objekt und legt es als das Spielfeld fest.
 *   - Ermittelt den Delta-Wert für die Brückenberechnung basierend auf dem Minimum von X- und Y-Abständen.
 *   - Initialisiert den BridgeController mit der tiefen Kopie der Insel-Liste und anderen erforderlichen Parametern.
 *
 * - public RestartGame(GameData data): Konstruktor für die "RestartGame" Klasse.
 *   Akzeptiert ein "GameData"-Objekt, das die Spieldaten enthält, und verwendet es, um den Zustand des Spiels zu verwalten.
 */
public class RestartGame {

	// Ein Objekt, das die Spieldaten enthält.
	private GameData data; 


	
	private ClearLists clearList;


	/**
	 * Startet das Puzzle neu, indem der Spielzustand und die Komponenten zurückgesetzt werden.
	 * Wenn kein Spiel aktiv ist, hat diese Methode keine Auswirkungen.
	 */
	public void restartPuzzle() {
	    // Überprüft, ob ein Spiel aktiv ist. Falls nicht, wird nichts unternommen.
	    if (!data.isGameExist()) {
	        return;
	    } else {
	        // Bestehende Datenlisten löschen und eine tiefe Kopie der Insel-Liste erstellen.
	        clearList = new ClearLists();
	        clearList.clearLists(data);
	        ArrayList<Island> deepCopy = new ArrayList<Island>();

	        data.setDeepCopy(data.getCopyList().createDeepCopy(data.getListofIslands(), deepCopy));

	        // Die Breite, Höhe und Abstände zwischen den Rasterelementen abrufen.
	        int width = data.getWidth();
	        int height = data.getHeight();
	        int xDistance = data.getGridValues().getXDistance();
	        int yDistance = data.getGridValues().getYDistance();

	        // Ein neues GridPainter-Objekt erstellen und es als das Spielfeld festlegen.
	        data.setGrid(new GridPainter(width, height, xDistance, yDistance, deepCopy));
	        ClearGrid clearGrid = new ClearGrid(data);
	        clearGrid.clearAndAddGrid();

	        // Den Delta-Wert für die Brückenberechnung ermitteln, basierend auf dem Minimum von X- und Y-Abständen.
	        int delta = 0;
	        if (data.getGridValues().getXDistance() < data.getGridValues().getYDistance()) {
	            delta = data.getGridValues().getXDistance();
	        } else {
	            delta = data.getGridValues().getYDistance();
	        }
	        data.setDelta(delta);

	        // Den BridgeController mit der tiefen Kopie der Insel-Liste und anderen erforderlichen Parametern initialisieren.
	        data.getBridgeC().initController(deepCopy, data);
	    }
	}




	/**
	 * Konstruktor für die "RestartGame" Klasse.
	 *
	 * @param data Ein "GameData"-Objekt, das die Spieldaten enthält.
	 */
	public RestartGame(GameData data) {
		this.data = data;
	}
}
