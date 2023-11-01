package Modell.load;

import Modell.CreateBridges;
import Modell.GridPainter;
import Modell.Island;
import modells.GameData.GameData;
import modells.Support.Directions;
import modells.handleBridges.DrawBridges;
import modells.handleBridges.UpdateBridgeCounter;

/**
 * Die Klasse "UpdateLoadedGame" ist für die Aktualisierung eines geladenen Brückenrätselspiels verantwortlich. Sie stellt sicher,
 * dass der Spielzustand nach dem Laden korrekt wiederhergestellt wird und führt die notwendigen Schritte zur Aktualisierung des
 * Spiels aus. Dies beinhaltet das Zeichnen der Brücken, die Aktualisierung der Inselzähler und die Verwaltung des Brückenzählers.
 *
 * Attribute:
 * - private GameData data: Die Spielinformationen, die das geladene Spiel repräsentieren und aktualisiert werden sollen.
 * - private int delta: Der Zeitschritt für die Spielzeit.
 * - private DrawBridges drawBridges: Eine Instanz zum Zeichnen von Brücken im Spiel.
 * - private GridPainter painter: Eine Instanz des Malers für das Spielfeld.
 * - private UpdateBridgeCounter updateBridgeCounter: Eine Instanz zur Aktualisierung des Brückenzählers.
 *
 * Methoden:
 *
 * - public UpdateLoadedGame(GameData data): Ein Konstruktor für die "UpdateLoadedGame"-Klasse. Er akzeptiert ein "GameData"-Objekt,
 *   das das geladene Spiel repräsentiert und stellt sicher, dass der Spielzustand nach dem Laden korrekt wiederhergestellt wird.
 *
 * - public void updateLoadedGame(): Diese Methode aktualisiert das geladene Spiel, indem die Brücken zwischen den Inseln gezeichnet
 *   und die Inselzähler entsprechend aktualisiert werden. Sie stellt sicher, dass der Spielzustand vollständig wiederhergestellt 
 *   wird
 *   und das Spiel in einem konsistenten Zustand ist.
 *
 */
public class UpdateLoadedGame {


	private GameData data;             // Die Spielinformationen
	private int delta;                 // Zeitschritt für die Spielzeit

	private DrawBridges drawBridges;   // Instanz zum Zeichnen von Brücken im Spiel
	private GridPainter painter;       // Instanz des Malers für das Spielfeld
	private UpdateBridgeCounter updateBridgeCounter;  // Instanz zur Aktualisierung des Brückenzählers



	/**
	 * Erstellt eine Instanz von "UpdateLoadedGame" und initialisiert sie mit den übergebenen Spielinformationen (GameData).
	 * Diese Klasse ist für die Aktualisierung des geladenen Spiels verantwortlich und führt die notwendigen Schritte aus, um
	 * den Spielzustand nach dem Laden wiederherzustellen und zu aktualisieren.
	 *
	 * @param data Die Spielinformationen, die das geladene Spiel repräsentieren und aktualisiert werden sollen.
	 */
	public UpdateLoadedGame(GameData data) {
		this.data = data;

		// Initialisiere delta-Wert für die Aktualisierung der Spielzeit
		delta = data.getDelta();

		// Erstelle eine Instanz von "DrawBridges", um die Brücken im Spiel zu zeichnen
		drawBridges = new DrawBridges(data);

		// Erstelle eine Instanz des "painter" aus den Spielinformationen, um das Spielfeld zu malen
		painter = data.getGrid();

		// Erstelle eine Instanz von "UpdateBridgeCounter", um den Brückenzähler zu aktualisieren
		updateBridgeCounter = new UpdateBridgeCounter(data);

		// Führt die Methode "updateLoadedGame" aus, um den Spielzustand nach dem Laden zu aktualisieren
		updateLoadedGame();
	}


	/**
	 * Diese Methode aktualisiert das geladene Spiel, indem die Brücken gezeichnet und die Inselzähler aktualisiert werden.
	 */
	public void updateLoadedGame() {
		for (CreateBridges bridge : data.getBridgesList()) {
			// Die IDs der betroffenen Inseln abrufen
			int firstID = bridge.getFirstIslandID();
			int secondID = bridge.getSecondIslandID();

			// Die Anzahl der Brückenverbindungen zwischen den Inseln abrufen
			int numberOfConnections = bridge.getNumberOfBridges();

			// Die betroffenen Inselobjekte aus der islandList abrufen
			Island firstIsland = data.getDeepCopy().get(firstID);
			Island secondIsland = data.getDeepCopy().get(secondID);

			// Die Koordinaten des Zentrums der ersten Insel
			int centerX = firstIsland.getCenterX();
			int centerY = firstIsland.getCenterY();

			// Die Koordinaten des Zentrums der zweiten Insel
			int secondCenterX = secondIsland.getCenterX();
			int secondCenterY = secondIsland.getCenterY();

			// Variablen zur Speicherung der Zeichnungspositionen initialisieren
			int drawLineX, drawLineX2, drawLineY, drawLineY2;

			// Die Brücke je nach Position der Inseln zeichnen
			if (centerX < secondCenterX) {
				// Wenn die erste Insel östlich der zweiten liegt
				drawLineX = centerX + (delta / 2) + 3;
				drawLineX2 = secondCenterX - (delta / 2) - 3;

				// Je nach Anzahl der Brückenverbindungen entweder eine einfache oder doppelte Brücke zeichnen
				if (numberOfConnections == 2)
					drawBridges.drawDoubleBridge(drawLineX, centerY, drawLineX2, centerY, Directions.EAST, firstID, secondID);
				else {
					CreateBridges loadedBridge = new CreateBridges(firstID, drawLineX, centerY, secondID, drawLineX2, centerY);
					painter.getBridges().add(loadedBridge);

				}
				//controller.drawBridge(drawLineX, centerY, drawLineX2, centerY, firstID, secondID);
			} else if (centerX > secondCenterX) {
				// Wenn die erste Insel westlich der zweiten liegt
				drawLineX = centerX - (delta / 2) - 3;
				drawLineX2 = secondCenterX + (delta / 2) + 3;

				// Je nach Anzahl der Brückenverbindungen entweder eine einfache oder doppelte Brücke zeichnen
				if (numberOfConnections == 2)
					drawBridges.drawDoubleBridge(drawLineX, centerY, drawLineX2, centerY, Directions.WEST, secondID, firstID);
				else {
					CreateBridges loadedBridge = new CreateBridges(secondID, drawLineX, centerY, firstID, drawLineX2, centerY);
					painter.getBridges().add(loadedBridge);
				}
				//controller.drawBridge(drawLineX, centerY, drawLineX2, centerY, secondID, firstID);
			} else if (centerY > secondCenterY) {
				// Wenn die erste Insel nördlich der zweiten liegt
				drawLineY = centerY - (delta / 2) - 3;
				drawLineY2 = secondCenterY + (delta / 2) + 3;

				// Je nach Anzahl der Brückenverbindungen entweder eine einfache oder doppelte Brücke zeichnen
				if (numberOfConnections == 2)
					drawBridges.drawDoubleBridge(centerX, drawLineY, centerX, drawLineY2, Directions.NORTH, secondID, firstID);
				else {
					CreateBridges loadedBridge = new CreateBridges(secondID, centerX, drawLineY, firstID, centerX, drawLineY2);
					painter.getBridges().add(loadedBridge);
				}
				//controller.drawBridge(centerX, drawLineY, centerX, drawLineY2, secondID, firstID);
			} else if (centerY < secondCenterY) {
				// Wenn die erste Insel südlich der zweiten liegt
				drawLineY = centerY + (delta / 2) + 3;
				drawLineY2 = secondCenterY - (delta / 2) - 3;

				// Je nach Anzahl der Brückenverbindungen entweder eine einfache oder doppelte Brücke zeichnen
				if (numberOfConnections == 2)
					drawBridges.drawDoubleBridge(centerX, drawLineY, centerX, drawLineY2, Directions.SOUTH, firstID, secondID);
				else {
					CreateBridges loadedBridge = new CreateBridges(firstID, centerX, drawLineY, secondID, centerX, drawLineY2);
					painter.getBridges().add(loadedBridge);
				}
				//controller.drawBridge(centerX, drawLineY, centerX, drawLineY2, firstID, secondID);
			}

			// Die Anzahl der Brücken an den Inseln aktualisieren
			firstIsland.setBridgeCount(firstIsland.getBridgeCount() - numberOfConnections);
			secondIsland.setBridgeCount(secondIsland.getBridgeCount() - numberOfConnections);

			// Brückenrückgängigmachung aktualisieren
			//setLoaded(true);
			data.setGameWasLoaded(true);
			updateBridgeCounter.decrementBridgeCounter(firstID, secondID, data.getDeepCopy());
			data.setGameWasLoaded(false);
			//setLoaded(false);
			painter.repaint();
		}
	}


}
