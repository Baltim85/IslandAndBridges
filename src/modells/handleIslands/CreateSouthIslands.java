package modells.handleIslands;

import java.util.Random;

import Modell.CreateBridges;
import Modell.Island;
import modells.GameData.GameData;
import modells.Support.Directions;
import modells.Support.Island.UpdateIslandInformation;
import modells.Support.Island.PossibleValueForNextIsland;



/**
 * Die Klasse "CreateNorthIslands" ist verantwortlich für die Erstellung neuer Inseln im Süden bereits vorhandener Inseln in 
 * einem Spielkontext. Sie ermöglicht die Verbindung dieser neuen Inseln mit vorhandenen Brücken 
 * und sorgt für die Aktualisierung der Spieldaten.
 *
 * Konstruktor:
 * - CreateSouthIslands(GameData data): Dieser Konstruktor initialisiert eine Instanz von "CreateSouthIslands" mit den 
 * benötigten Spieldaten.
 *
 * Methoden:
 * - createSouthIsland(Island chosenIsland): Diese Methode erstellt eine neue Insel im Süden der ausgewählten Insel. 
 * Dabei wird eine zufällige Anzahl von Brücken zwischen 1 und 2 generiert.
 *  Es wird überprüft, ob die neue Insel mit vorhandenen Inseln oder Brücken kollidiert. 
 *  Falls keine Kollision auftritt, erfolgt die Verbindung mit den vorhandenen Brücken und die Aktualisierung der Spieldaten.
 *
 * - connectToExistingBridges(Island chosenIsland, int newY, int bridgeCount): Diese Methode verbindet die ausgewählte Insel im 
 * Süden mit vorhandenen Brücken und erstellt gegebenenfalls eine neue Insel. 
 * Sie überprüft, ob Kollisionen mit benachbarten Inseln oder Brücken auftreten und passt die Verbindungen entsprechend an.
 *
 * - newSouthIsland(Island chosenIsland, int bridgeCount, int newY): Diese Methode erstellt eine neue Insel im Süden der 
 * ausgewählten Insel und fügt die entsprechende Brücke hinzu. Sie aktualisiert den Brückenzähler und die Richtungen der 
 * beteiligten Inseln sowie die Spieldaten.
 *
 * Diese Klasse ist integraler Bestandteil eines Spiels, das Inseln und Brücken verwaltet, 
 * und ermöglicht die dynamische Erweiterung des Spielfelds durch das Erstellen neuer Inseln im Süden bestehender Inseln.
 */
public class CreateSouthIslands implements ConnectToExistingBridges {


	// Ein Random-Objekt zur Generierung von Zufallszahlen
	private static final Random random = new Random();

	private GameData data;                // Die Spieldaten
	private LookForAnotherIsland anotherIsland;   // Sucht nach weiteren Inseln
	private LookForDirectedNeighbours lookForNeighbours;  // Überprüft benachbarte Inseln
	private PossibleValueForNextIsland posibleValues;   // Ermittelt mögliche Werte für die nächste Insel
	private UpdateIslandInformation createNextIsland;    // Erstellt die nächste Insel

	/**
	 * Initialisiert eine Instanz von CreateSouthIslands.
	 *
	 * @param data Die Spieldaten, die für die Erstellung von Inseln und Brücken verwendet werden.
	 */
	public CreateSouthIslands(GameData data) {
		this.data = data;
		anotherIsland = new LookForAnotherIsland(data);
		lookForNeighbours = new LookForDirectedNeighbours(data);
		posibleValues = new PossibleValueForNextIsland(data);
		createNextIsland = new UpdateIslandInformation();
	} 

	/**
	 * Erstellt eine neue Insel im Süden der ausgewählten Insel und verbindet sie gegebenenfalls mit vorhandenen Brücken.
	 *
	 * @param chosenIsland Die ausgewählte Insel, von der aus die Verbindung erstellt wird.
	 */
	public void createSouthIsland(Island chosenIsland) {
		// Zufällige Anzahl von Brücken zwischen 1 und 2 erstellen
		int bridgeCount = 1 + (random.nextInt(2));

		// Bestimmen der neuen Y-Position für die Insel im Norden
		int newPosY = posibleValues.possibleValuesSE(chosenIsland, data.getHeight(), chosenIsland.getY(), Directions.SOUTH);

		// Überprüfen, ob der Bau der Brücke nicht möglich ist
		if (newPosY <= 1 || !anotherIsland.checkForAnotherIsland(chosenIsland, newPosY, Directions.SOUTH, data) 
				|| !lookForNeighbours.checkDirectedNeighboursWE(chosenIsland, newPosY, Directions.SOUTH)) {
			// Setze die Richtung "Süden" auf false und beende die Methode
			chosenIsland.setSouth(false);
			return;
		} else {
			if (newPosY < chosenIsland.getY())
				newPosY += chosenIsland.getY();

			// Wenn die Liste der Brücken nicht leer ist
			if (!data.getListOfBridges().isEmpty()) {
				// Schleife über die Y-Koordinaten von der aktuellen Insel bis zur neuen Position
				if(!connectToExistingBridges(chosenIsland, newPosY, bridgeCount))
					newSouthIsland(chosenIsland, bridgeCount, newPosY);
				return;
			}


			newSouthIsland(chosenIsland, bridgeCount, newPosY);

		}
		return;
	}


	/**
	 * Verbindet die ausgewählte Insel im Süden mit vorhandenen Brücken und erstellt gegebenenfalls eine neue Insel.
	 *
	 * @param chosenIsland Die ausgewählte Insel, von der aus die Verbindung erstellt wird.
	 * @param newY Die Y-Koordinate der neuen Insel.
	 * @param bridgeCount Die Anzahl der Brücken, die erstellt werden.
	 */
	private boolean connectToExistingBridges(Island chosenIsland, int newY, int bridgeCount) {
		int chosenX = chosenIsland.getX();
		int chosenY = chosenIsland.getY();
		for (int i = chosenIsland.getY(); i < newY + 1; i++) {
			// Schleife über vorhandene Brücken
			for (CreateBridges bridge : data.getListOfBridges()) {
				int firstX = bridge.getFirstIslandX();
				int secondX = bridge.getSecondIslandX();
				int y = bridge.getFirstIslandY();

				// Überprüfen, ob eine Brücke über der aktuellen Insel existiert
				// Überprüfen, ob eine Brücke über der aktuellen Insel existiert
				if (y == chosenIsland.getY() + 1 && (firstX < chosenX && secondX > chosenX)) {
					// Setze die Richtung "Süden" auf false und beende die Methode
					chosenIsland.setSouth(false);
					return true;
				}

				// Überprüfen, ob eine Brücke über der geplanten Position der neuen Insel existiert
				if (y > chosenY + 1 && i == y && (firstX < chosenX - 1 && secondX > chosenX + 1)) {
					// Überprüfen, ob die neue Insel mit benachbarten Inseln kollidiert
					if(lookForNeighbours.checkDirectedNeighboursWE(chosenIsland, i, Directions.SOUTH)) {
						chosenIsland.setSouth(false);
						return true;
					}
					System.out.println("Neue Y Süden: " + newY + " i: " + i);
					// Verbinde die aktuelle Insel mit der neuen Insel im Norden
					Island nextIsland = new Island(chosenIsland.getX(), newY, data.getIndex(), data.getListofIslands(), bridgeCount, false, false, true, false, 0, 0);
					ConnectToExistingBridges.connectNorthIsland(chosenIsland, nextIsland, bridge, bridgeCount, Directions.SOUTH, data, createNextIsland);
					chosenIsland.setSouth(false);
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Erstellt eine neue Insel im Süden der ausgewählten Insel und fügt die entsprechende Brücke hinzu.
	 *
	 * @param chosenIsland Die ausgewählte Insel, von der aus die Verbindung erstellt wird.
	 * @param bridgeCount  Die Anzahl der Brücken, die erstellt werden.
	 * @param newY Die Y-Koordinate der neuen Insel.
	 */
	private void newSouthIsland(Island chosenIsland, int bridgeCount, int newY) {
		Island nextIsland = createNextIsland.updateIslandInformation(chosenIsland.getX(), newY, bridgeCount, data);

		// Füge die Brücke hinzu
		data.getListOfBridges().add(new CreateBridges(chosenIsland.getId(), chosenIsland.getX(), chosenIsland.getY(),
				nextIsland.getId(), nextIsland.getX(), nextIsland.getY(), bridgeCount));

		// Aktualisiere Brückenzähler und Richtungen der Inseln
		chosenIsland.setBridgeCount(chosenIsland.getBridgeCount() + bridgeCount);
		nextIsland.setNorth(false);
		chosenIsland.setSouth(false);

		// Füge die neue Insel zur Liste hinzu
		data.getListofIslands().add(nextIsland); 
		data.getPossibleIslands().add(nextIsland);
		System.out.println("South FirstID: " + chosenIsland.getId() + "Second ID: " + nextIsland.getId());
		System.out.println(newY);
	}



}
