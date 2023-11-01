package modells.handleIslands;

import java.util.Random;

import Modell.CreateBridges;
import Modell.Island;
import modells.GameData.GameData;
import modells.Support.Directions;
import modells.Support.Island.UpdateIslandInformation;
import modells.Support.Island.PossibleValueForNextIsland;

/**
 * Die Klasse "CreateEastIslands" ist verantwortlich für die Erstellung neuer Inseln im Osten bereits vorhandener Inseln in einem Spielkontext. Sie ermöglicht die Verbindung dieser neuen Inseln mit vorhandenen Brücken und sorgt für die Aktualisierung der Spieldaten.
 *
 * Konstruktor:
 * - CreateEastIslands(GameData data): Dieser Konstruktor initialisiert eine Instanz von "CreateEastIslands" mit den benötigten Spieldaten.
 *
 * Methoden:
 * - createEastIsland(Island chosenIsland): Diese Methode erstellt eine neue Insel im Osten der ausgewählten Insel. Dabei wird eine zufällige Anzahl von Brücken zwischen 1 und 2 generiert. Es wird überprüft, ob die neue Insel mit vorhandenen Inseln oder Brücken kollidiert. Falls keine Kollision auftritt, erfolgt die Verbindung mit den vorhandenen Brücken und die Aktualisierung der Spieldaten.
 *
 * - connectToExistingBridges(Island chosenIsland, int newX, int bridgeCount): Diese Methode verbindet die ausgewählte Insel im Osten mit vorhandenen Brücken und erstellt gegebenenfalls eine neue Insel. Sie überprüft, ob Kollisionen mit benachbarten Inseln oder Brücken auftreten und passt die Verbindungen entsprechend an.
 *
 * - newEastIsland(Island chosenIsland, int bridgeCount, int newX): Diese Methode erstellt eine neue Insel im Osten der ausgewählten Insel und fügt die entsprechende Brücke hinzu. Sie aktualisiert den Brückenzähler und die Richtungen der beteiligten Inseln sowie die Spieldaten.
 *
 * Diese Klasse ist integraler Bestandteil eines Spiels, das Inseln und Brücken verwaltet, und ermöglicht die dynamische Erweiterung des Spielfelds durch das Erstellen neuer Inseln im Osten bestehender Inseln.
 */
public class CreateEastIslands implements ConnectToExistingBridges{

	// Ein Random-Objekt zur Generierung von Zufallszahlen
	private static final Random random = new Random();

	private GameData data;                // Die Spieldaten
	private LookForAnotherIsland anotherIsland;   // Sucht nach weiteren Inseln
	private LookForDirectedNeighbours lookForNeighbours;  // Überprüft benachbarte Inseln
	private PossibleValueForNextIsland posibleValues;   // Ermittelt mögliche Werte für die nächste Insel
	private UpdateIslandInformation createNextIsland;    // Erstellt die nächste Insel


	/**
	 * Initialisiert eine Instanz von CreateEastIslands.
	 *
	 * @param data Die Spieldaten, die für die Erstellung von Inseln und Brücken verwendet werden.
	 */
	public CreateEastIslands(GameData data) {
		this.data = data;
		anotherIsland = new LookForAnotherIsland(data);
		lookForNeighbours = new LookForDirectedNeighbours(data);
		posibleValues = new PossibleValueForNextIsland(data);
		createNextIsland = new UpdateIslandInformation();
	}



	/**
	 * Erstellt eine neue Insel im Osten der ausgewählten Insel und verbindet sie gegebenenfalls mit vorhandenen Brücken.
	 *
	 * @param chosenIsland Die ausgewählte Insel, von der aus die Verbindung erstellt wird.
	 */
	public void createEastIsland(Island chosenIsland) {
		// Zufällige Anzahl von Brücken zwischen 1 und 2 erstellen
		int bridgeCount = 1 + (random.nextInt(2));

		// Bestimmen der neuen X-Position für die Insel im Osten
		int newPosX = posibleValues.possibleValuesSE(chosenIsland, data.getWidth(), chosenIsland.getX(), Directions.EAST);

		// Überprüfen, ob der Bau der Brücke nicht möglich ist
		if (newPosX <= 1 || !anotherIsland.checkForAnotherIsland(chosenIsland, newPosX, Directions.EAST, data) 
				|| !lookForNeighbours.checkDirectedNeighboursWE(chosenIsland, newPosX, Directions.EAST)) {
			// Setze die Richtung "Ost" auf false und beende die Methode
			chosenIsland.setEast(false);
			return;
		} else {
			if (newPosX < chosenIsland.getX())
				newPosX += chosenIsland.getX();

			// Wenn die Liste der Brücken nicht leer ist
			if (!data.getListOfBridges().isEmpty()) {
				// Schleife über die X-Koordinaten von der aktuellen Insel bis zur neuen Position
				if(!connectToExistingBridges(chosenIsland, newPosX, bridgeCount))
					newEastIsland(chosenIsland, bridgeCount, newPosX);
				return;
			}

			newEastIsland(chosenIsland, bridgeCount, newPosX);
		}
		return;
	}




	/**
	 * Verbindet die ausgewählte Insel im Osten mit vorhandenen Brücken und erstellt gegebenenfalls eine neue Insel.
	 *
	 * @param chosenIsland Die ausgewählte Insel, von der aus die Verbindung erstellt wird.
	 * @param newX Die X-Koordinate der neuen Insel.
	 * @param bridgeCount Die Anzahl der Brücken, die erstellt werden.
	 */
	private boolean connectToExistingBridges(Island chosenIsland, int newX, int bridgeCount) {
		int chosenX = chosenIsland.getX();
		int chosenY = chosenIsland.getY();
		for (int i = chosenIsland.getX(); i < newX + 1; i++) {
			// Schleife über vorhandene Brücken
			for (CreateBridges bridge : data.getListOfBridges()) {
				int firstY = bridge.getFirstIslandY();
				int secondY = bridge.getSecondIslandY();
				int x = bridge.getFirstIslandX();

				// Überprüfen, ob eine Brücke über der aktuellen Insel existiert
				if (x == chosenX + 1 && (firstY < chosenY && secondY > chosenY)) {
					// Setze die Richtung "Ost" auf false und beende die Methode
					chosenIsland.setEast(false);
					return true;
				}

				// Überprüfen, ob eine Brücke über der geplanten Position der neuen Insel existiert
				if (x > chosenX + 1 && i == x && (firstY < chosenY - 1 && secondY > chosenX + 1)) {
					// Überprüfen, ob die neue Insel mit benachbarten Inseln kollidiert
					if (lookForNeighbours.checkDirectedNeighboursWE(chosenIsland, i, Directions.EAST)) {
						chosenIsland.setEast(false);
						return true;
					}
					// Verbinde die aktuelle Insel mit der neuen Insel im Osten
					Island nextIsland = new Island(newX, chosenIsland.getY(), data.getIndex(), data.getListofIslands(), bridgeCount, false, true, false, false, 0, 0);
					ConnectToExistingBridges.connectNorthIsland(chosenIsland, nextIsland, bridge, bridgeCount, Directions.EAST, data, createNextIsland);
					chosenIsland.setEast(false);
					return true;
				}
			}
		}
		return false;
	}



	/**
	 * Erstellt eine neue Insel im Osten der ausgewählten Insel und fügt die entsprechende Brücke hinzu.
	 *
	 * @param chosenIsland Die ausgewählte Insel, von der aus die Verbindung erstellt wird.
	 * @param bridgeCount  Die Anzahl der Brücken, die erstellt werden.
	 * @param newX Die X-Koordinate der neuen Insel.
	 */
	private void newEastIsland(Island chosenIsland, int bridgeCount, int newX) {
		// Erzeugt die nächste Insel
		Island nextIsland = createNextIsland.updateIslandInformation(newX, chosenIsland.getY(), bridgeCount, data);

		// Fügt die Brücke hinzu
		data.getListOfBridges().add(new CreateBridges(chosenIsland.getId(), chosenIsland.getX(), chosenIsland.getY(),
				nextIsland.getId(), nextIsland.getX(), nextIsland.getY(),
				bridgeCount));

		// Aktualisiert den Brückenzähler und die Richtungen der Inseln
		chosenIsland.setBridgeCount(chosenIsland.getBridgeCount() + bridgeCount);
		chosenIsland.setEast(false);
		nextIsland.setWest(false);

		// Fügt die neue Insel zur Liste hinzu
		data.getListofIslands().add(nextIsland);
		data.getPossibleIslands().add(nextIsland);
		System.out.println("East FirstID: " + chosenIsland.getId() + " Second ID: " + nextIsland.getId());
		System.out.println(newX);
	}


}
