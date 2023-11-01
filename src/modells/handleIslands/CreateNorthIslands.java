package modells.handleIslands;

import java.util.Random;

import Modell.CreateBridges;
import Modell.Island;
import modells.GameData.GameData;
import modells.Support.Directions;
import modells.Support.Island.UpdateIslandInformation;
import modells.Support.Island.PossibleValueForNextIsland;


/**
 * Die Klasse "CreateNorthIslands" ist verantwortlich für die Erstellung neuer Inseln im Norden bereits vorhandener Inseln in 
 * einem Spielkontext. Sie ermöglicht die Verbindung dieser neuen Inseln mit vorhandenen Brücken 
 * und sorgt für die Aktualisierung der Spieldaten.
 *
 * Konstruktor:
 * - CreateNorthIslands(GameData data): Dieser Konstruktor initialisiert eine Instanz von "CreateNorthIslands" mit den 
 * benötigten Spieldaten.
 *
 * Methoden:
 * - createNorthIsland(Island chosenIsland): Diese Methode erstellt eine neue Insel im Norden der ausgewählten Insel. 
 * Dabei wird eine zufällige Anzahl von Brücken zwischen 1 und 2 generiert.
 *  Es wird überprüft, ob die neue Insel mit vorhandenen Inseln oder Brücken kollidiert. 
 *  Falls keine Kollision auftritt, erfolgt die Verbindung mit den vorhandenen Brücken und die Aktualisierung der Spieldaten.
 *
 * - connectToExistingBridges(Island chosenIsland, int newY, int bridgeCount): Diese Methode verbindet die ausgewählte Insel im 
 * Norden mit vorhandenen Brücken und erstellt gegebenenfalls eine neue Insel. 
 * Sie überprüft, ob Kollisionen mit benachbarten Inseln oder Brücken auftreten und passt die Verbindungen entsprechend an.
 *
 * - newNorthIsland(Island chosenIsland, int bridgeCount, int newY): Diese Methode erstellt eine neue Insel im Norden der 
 * ausgewählten Insel und fügt die entsprechende Brücke hinzu. Sie aktualisiert den Brückenzähler und die Richtungen der 
 * beteiligten Inseln sowie die Spieldaten.
 *
 * Diese Klasse ist integraler Bestandteil eines Spiels, das Inseln und Brücken verwaltet, 
 * und ermöglicht die dynamische Erweiterung des Spielfelds durch das Erstellen neuer Inseln im Norden bestehender Inseln.
 */
public class CreateNorthIslands implements ConnectToExistingBridges {

	// Ein Random-Objekt zur Generierung von Zufallszahlen
	private static final Random random = new Random();

	private GameData data;                // Die Spieldaten
	private LookForAnotherIsland anotherIsland;   // Sucht nach weiteren Inseln
	private LookForDirectedNeighbours lookForNeighbours;  // Überprüft benachbarte Inseln
	private PossibleValueForNextIsland posibleValues;   // Ermittelt mögliche Werte für die nächste Insel
	private UpdateIslandInformation createNextIsland;    // Erstellt die nächste Insel

	/**
	 * Initialisiert eine Instanz von CreateNorthIslands.
	 *
	 * @param data Die Spieldaten, die für die Erstellung von Inseln und Brücken verwendet werden.
	 */
	public CreateNorthIslands(GameData data) {
		this.data = data;
		anotherIsland = new LookForAnotherIsland(data);
		lookForNeighbours = new LookForDirectedNeighbours(data);
		posibleValues = new PossibleValueForNextIsland(data);
		createNextIsland = new UpdateIslandInformation();
	}

	/**
	 * Erstellt eine neue Insel im Norden der ausgewählten Insel und verbindet sie gegebenenfalls mit vorhandenen Brücken.
	 *
	 * @param chosenIsland Die ausgewählte Insel, von der aus die Verbindung erstellt wird.
	 */
	public void createNorthIsland(Island chosenIsland) {
		// Zufällige Anzahl von Brücken zwischen 1 und 2 erstellen
		int bridgeCount = 1 + (random.nextInt(2));

		// Bestimmen der neuen Y-Position für die Insel im Norden
		int newPosY = posibleValues.possibleValuesNW(chosenIsland, data.getHeight(), chosenIsland.getY(), Directions.NORTH);

		// Überprüfen, ob der Bau der Brücke nicht möglich ist
		if (newPosY <= 1 || !anotherIsland.checkForAnotherIsland(chosenIsland, newPosY, Directions.NORTH, data) 
				|| !lookForNeighbours.checkDirectedNeighboursWE(chosenIsland, newPosY, Directions.NORTH)) {
			// Setze die Richtung "Nord" auf false und beende die Methode
			chosenIsland.setNorth(false);
			return;
		} else {


			// Wenn die Liste der Brücken nicht leer ist
			if (!data.getListOfBridges().isEmpty()) {
				// Schleife über die Y-Koordinaten von der aktuellen Insel bis zur neuen Position
				if(!connectToExistingBridges(chosenIsland, newPosY, bridgeCount))
					newNorthIsland(chosenIsland, bridgeCount, newPosY);
				return;
			}


			newNorthIsland(chosenIsland, bridgeCount, newPosY);

		}
		return;
	}


	/**
	 * Verbindet die ausgewählte Insel im Norden mit vorhandenen Brücken und erstellt gegebenenfalls eine neue Insel.
	 *
	 * @param chosenIsland Die ausgewählte Insel, von der aus die Verbindung erstellt wird.
	 * @param newY Die Y-Koordinate der neuen Insel.
	 * @param bridgeCount Die Anzahl der Brücken, die erstellt werden.
	 */
	private boolean connectToExistingBridges(Island chosenIsland, int newY, int bridgeCount) {
		int chosenX = chosenIsland.getX();
		int chosenY = chosenIsland.getY();
		for (int i = chosenY; i > newY - 1; i--) {
			// Schleife über vorhandene Brücken
			for (CreateBridges bridge : data.getListOfBridges()) {
				int firstX = bridge.getFirstIslandX();
				int secondX = bridge.getSecondIslandX();
				int y = bridge.getFirstIslandY();

				// Überprüfen, ob eine Brücke über der aktuellen Insel existiert
				if (y == chosenY - 1 && (firstX < chosenX && secondX > chosenX)) {
					// Setze die Richtung "Nord" auf false und beende die Methode
					chosenIsland.setNorth(false);
					return true;
				}

				// Überprüfen, ob eine Brücke über der geplanten Position der neuen Insel existiert
				if (y < chosenY - 1 && i == y && (firstX < chosenX - 1 && secondX > chosenX + 1)) {
					// Überprüfen, ob die neue Insel mit benachbarten Inseln kollidiert
					if(lookForNeighbours.checkDirectedNeighboursWE(chosenIsland, i, Directions.NORTH)) {
						chosenIsland.setNorth(false);
						return true;
					}
					// Verbinde die aktuelle Insel mit der neuen Insel im Norden
					Island nextIsland = new Island(chosenIsland.getX(), newY, data.getIndex(), data.getListofIslands(), bridgeCount, true, false, false, false, 0, 0);
					ConnectToExistingBridges.connectNorthIsland(nextIsland, chosenIsland, bridge, bridgeCount, Directions.NORTH, data, createNextIsland);
					chosenIsland.setNorth(false);
					return true;
				}
			}
		}
		return false;
	}





	/**
	 * Erstellt eine neue Insel im Norden der ausgewählten Insel und fügt die entsprechende Brücke hinzu.
	 *
	 * @param chosenIsland Die ausgewählte Insel, von der aus die Verbindung erstellt wird.
	 * @param bridgeCount  Die Anzahl der Brücken, die erstellt werden.
	 * @param newY Die Y-Koordinate der neuen Insel.
	 */
	private void newNorthIsland(Island chosenIsland, int bridgeCount, int newY) {
		Island nextIsland = createNextIsland.updateIslandInformation(chosenIsland.getX(), newY, bridgeCount, data);

		// Füge die Brücke hinzu
		data.getListOfBridges().add(new CreateBridges(nextIsland.getId(), nextIsland.getX(), nextIsland.getY(), chosenIsland.getId(), chosenIsland.getX(), chosenIsland.getY(), bridgeCount));

		// Aktualisiere Brückenzähler und Richtungen der Inseln
		chosenIsland.setBridgeCount(chosenIsland.getBridgeCount() + bridgeCount);
		chosenIsland.setNorth(false);
		nextIsland.setSouth(false);

		// Füge die neue Insel zur Liste hinzu
		data.getListofIslands().add(nextIsland); 
		data.getPossibleIslands().add(nextIsland);
		System.out.println("North FirstID: " + nextIsland.getId() + "Second ID: " + chosenIsland.getId());
		System.out.println(newY);
	}
}
