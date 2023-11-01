package Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import Modell.Island;
import modells.GameData.GameData;
import modells.Support.PuzzleGenerationCompleted;
import modells.handleIslands.ChoseDirection;
import modells.handleIslands.FirstIsland;

/**
 * Die Klasse "IslandController" verwaltet die Erstellung von Inseln in einem Puzzle-Rätsel. Sie ist verantwortlich für das 
 * Erstellen
 * der Inseln und die Überwachung des Erstellungsprozesses.
 *
 * Attribute:
 * - private ChoseDirection choseDirection: Ein Objekt zur Auswahl der Richtung für Brücken von einer Insel.
 * - private GameData data: Ein Objekt, das die Spieldaten enthält.
 * - private static final Random random: Ein statisches Random-Objekt zur Generierung von Zufallszahlen für die Erstellung von Inseln.
 * - private PuzzleGenerationCompleted isCompleted: Ein Objekt zur Überprüfung, ob das Rätsel vollständig generiert wurde.
 * - private boolean isOk: Ein Flag, das anzeigt, ob die Insel korrekt erstellt wurde.
 *
 * Methoden:
 *
 * - public boolean isOk(): Diese Methode gibt zurück, ob die Insel korrekt erstellt wurde.
 *
 * - public void setOk(boolean isOk): Diese Methode legt den Wert des "isOk"-Flags fest, um anzuzeigen, ob die Insel korrekt 
 * erstellt wurde.
 *
 * - public IslandController(GameData data): Ein Konstruktor für die "IslandController"-Klasse. Er akzeptiert ein "GameData"-Objekt,
 *   das die Spieldaten enthält, und verwendet es, um den Zustand des Spiels zu verwalten.
 *
 * - public void createFirstIsland(int totalIslands, GameData data): Diese Methode erstellt die erste Insel im Rätsel. 
 * Sie setzt den Index
 *   auf 1, initialisiert verschiedene Listen und Karten und ruft dann die Methode "initFirstIsland" auf, um die erste 
 *   Insel zu erstellen.
 *   Anschließend werden weitere Inseln erstellt, bis die Anzahl der erstellten Inseln der Gesamtanzahl entspricht.
 *
 * - private void createNextIsland(): Diese Methode erstellt die nächsten Inseln im Rätsel. Sie verwendet eine Schleife, um 
 * Inseln zu erstellen,
 *   bis die gewünschte Anzahl erreicht ist. Sie wählt eine Insel aus der Liste "possibleIslands" aus, überprüft mögliche 
 *   Richtungen für Brücken
 *   und erstellt die nächste Insel entsprechend. Dies wird wiederholt, bis die Anzahl der erstellten Inseln der Gesamtanzahl 
 *   entspricht oder keine
 *   weiteren Inseln erstellt werden können.
 *
 * - private Island randomIsland(ArrayList<Island> possibleIslands, HashMap<Integer, Island> islandM): Diese Methode sucht eine 
 * neue zufällige Insel
 *   aus der Anzahl der möglichen Inseln.
 *
 */
public class IslandController {

	private ChoseDirection choseDirection;
	private GameData data;


	// Ein Random-Objekt zur Generierung von Zufallszahlen
	private static final Random random = new Random();

	private PuzzleGenerationCompleted isCompleted = new PuzzleGenerationCompleted();
	// Ein Flag, das angibt, ob die Insel korrekt erstellt wurde
	private boolean isOk = false;



	/**
	 * Erstellt einen neuen IslandController und initialisiert ihn mit den übergebenen Spielinformationen (GameData).
	 *
	 * @param data Die Spielinformationen, die zur Steuerung der Inseln und des Spiels verwendet werden.
	 */
	public IslandController(GameData data) {
		this.data = data;
	}


	/**
	 * Diese Methode erstellt die erste Insel im Rätsel. Sie setzt den Index auf 1, initialisiert verschiedene Listen und
	 * Karten, und ruft dann die Methode "initFirstIsland" auf, um die erste Insel zu erstellen. Anschließend werden
	 * weitere Inseln erstellt, bis die Anzahl der erstellten Inseln der Gesamtanzahl entspricht.
	 *
	 * @param totalIslands Die Gesamtanzahl der Inseln, die erstellt werden sollen.
	 */
	public void createFirstIsland(int totalIslands, GameData data) {
		this.data = data;
		// Setze den Index auf 1 und initialisiere Listen und Karten
		data.setIndex(1);
		data.getListofIslands().clear();

		data.getPossibleIslands().clear();
		data.getIslandMap().clear();
		data.getListOfBridges().clear();

		FirstIsland firstIsland = new FirstIsland();
		firstIsland.initFirstIsland(data);
		choseDirection = new ChoseDirection(data);
		// Erstelle weitere Inseln, bis die gewünschte Gesamtanzahl erreicht ist
		createNextIsland();

		if(isCompleted.isCompleted(data))
			setOk(true);
		else
			setOk(false);


	}

	/**
	 * Diese Methode erstellt die nächsten Inseln im Rätsel. Sie verwendet eine Schleife, um Inseln zu erstellen, bis die
	 * gewünschte Anzahl erreicht ist. Sie wählt eine Insel aus der Liste "possibleIslands" aus, überprüft mögliche
	 * Richtungen für Brücken und erstellt die nächste Insel entsprechend. Dies wird wiederholt, bis die Anzahl der
	 * erstellten Inseln der Gesamtanzahl entspricht oder keine weiteren Inseln erstellt werden können.
	 */
	private void createNextIsland() {
		while (data.getIndex() < data.getIslands()) {
			if (data.getPossibleIslands().isEmpty() || data.getListofIslands().size() > data.getIslands()) {
				setOk(false);
				return;
			}
			// Wähle eine Insel aus der Liste "possibleIslands" aus
			Island chooseIsland = randomIsland(data.getPossibleIslands(), data.getIslandMap());
			// Überprüfe mögliche Richtungen für Brücken und erstelle die nächste Insel
			choseDirection.checkDirection(chooseIsland);
		}
		setOk(true);
	}

	/**
	 * Sucht eine neue Zufällige Insel aus der Anzahl der möglichen Inseln.
	 * 
	 * @param possibleIslands die Menge der möglichen Inseln
	 * @param islandM Eine HashMap mit allen möglichen Inseln
	 * @return liefert eine zufällige Insel zurück
	 */
	private Island randomIsland(ArrayList<Island>possibleIslands, HashMap<Integer, Island> islandM) {		
		Island item = possibleIslands.get(random.nextInt(possibleIslands.size()));
		return islandM.get(item.getId());

	}

	/**
	 * Überprüft den aktuellen Status der "isOk"-Eigenschaft.
	 *
	 * @return true, wenn der Status "isOk" in Ordnung ist, andernfalls false.
	 */
	public boolean isOk() {
		return isOk;
	}

	/**
	 * Legt den Status der "isOk"-Eigenschaft fest, um anzugeben, ob etwas in Ordnung ist oder nicht.
	 *
	 * @param isOk true, wenn etwas in Ordnung ist; andernfalls false, um anzuzeigen, dass etwas nicht in Ordnung ist.
	 */
	public void setOk(boolean isOk) {
		this.isOk = isOk;
	}

}
