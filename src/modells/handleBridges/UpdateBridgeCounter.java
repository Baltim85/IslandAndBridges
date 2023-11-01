package modells.handleBridges;

import java.util.ArrayList;

import Modell.CreateBridges;
import Modell.Island;
import modells.GameData.GameData;
import modells.handleIslands.UpdateIslandColors;


/**
 * Die Klasse "UpdateBridgeCounter" ist verantwortlich für die Aktualisierung der Brückenzähler und Inselfarben im Spiel. 
 * Sie verwaltet die Anzahl der Brücken, die mit jeder Insel verbunden sind, und passt die Farben der Inseln entsprechend an. 
 * Diese Klasse stellt sicher, dass die Brücken und Inselfarben im Spiel konsistent und korrekt aktualisiert werden.
 *
 * Die Aktualisierung der Brückenzähler erfolgt durch Methoden zum Hinzufügen, Entfernen und Verringern der Anzahl von Brücken. 
 * Bei jedem Aktualisierungsschritt überwacht die Klasse auch die Brückenzähler der betroffenen Inseln und aktualisiert die 
 * Inselfarben basierend auf den Brückenzuständen. Die Klasse arbeitet eng mit den Spielerdaten zusammen und verwendet sie, 
 * um kohärente Aktualisierungen sicherzustellen.
 *
 * Das Ziel der "UpdateBridgeCounter" Klasse ist es, sicherzustellen, dass die Spiellogik in Bezug auf Brücken und Inseln 
 * ordnungsgemäß verwaltet wird, um ein reibungsloses Spielerlebnis zu gewährleisten.
 */
public class UpdateBridgeCounter {
	
	
	private UpdateIslandColors updateColors;
	private GameData data;
	
	
	/**
	 * Der Konstruktor der Klasse "UpdateBridgeCounter" initialisiert eine Instanz dieser Klasse mit den erforderlichen
	 * Daten, um den Brückenzähler und die Inselfarben zu aktualisieren. Diese Klasse ist dafür verantwortlich, die Brückenzähler
	 * für jede Insel in Abhängigkeit von den tatsächlich verknüpften Brücken zu aktualisieren und die Farben der Inseln entsprechend
	 * den Brückenzählern festzulegen.
	 *
	 * @param data Die Spielerdaten, die die erforderlichen Informationen über das Spiel enthalten, einschließlich der Inseln,
	 *             Brücken und Brückenzähler.
	 */
	public UpdateBridgeCounter(GameData data) {
		this.data = data;
		updateColors = new UpdateIslandColors(data);
	}
	
	
	/**
	 * Aktualisiert den Zähler für die Anzahl der Brücken für eine gegebene Brücke.
	 *
	 * @param bridge Die Brücke, für die der Zähler aktualisiert werden soll.
	 */
	public void updateBridgeCounter(CreateBridges bridge) {
		// Holen Sie sich den aktuellen Brückenzähler für die Brücke.
		int bridgeCounter = bridge.getNumberOfBridges();

		// Überprüfen, ob der Brückenzähler bereits 2 erreicht hat.
		if (bridgeCounter == 2) {
			System.out.println("Zu viele Brücken für diese Inseln.");
			return; // Beende die Methode, da die maximale Anzahl erreicht wurde.
		} 
		// Wenn der Brückenzähler kleiner als 2 ist, erhöhe ihn um 1.
		else {
			bridgeCounter += 1;
			bridge.setNumberOfBridges(bridgeCounter); // Setze den aktualisierten Zähler zurück.
			return;
		}
	}
	
	
	/**
	 * Aktualisiert die Brückenzähler und die Farben der betroffenen Inseln nach Entfernen einer Brücke.
	 *
	 * @param bridge         Die Brücke, die entfernt wurde.
	 * @param firstID        Die eindeutige ID der ersten Insel.
	 * @param secondID       Die eindeutige ID der zweiten Insel.
	 * @param islandList     Die Liste der Inseln, die die aktuellen Brückenzähler enthält.
	 */
	public void updateRemoveCounter(CreateBridges bridge, int firstID, int secondID, ArrayList<Island> islandList) {
		// Ermitteln der aktuellen Brückenzähler für beide Inseln
		int firstIslandBridgeCount = islandList.get(firstID).getBridgeCount();
		int secondIslandBridgeCount = islandList.get(secondID).getBridgeCount();

		// Erhöhen der Brückenzähler für beide Inseln um 1
		islandList.get(firstID).setBridgeCount(firstIslandBridgeCount + 1);
		islandList.get(secondID).setBridgeCount(secondIslandBridgeCount + 1);

		// Holen Sie sich den aktuellen Brückenzähler für die Brücke.
		int bridgeCounter = bridge.getNumberOfBridges();

		// Überprüfen, ob der Brückenzähler bereits 2 erreicht hat.
		if (bridgeCounter > 0) {
			bridgeCounter -= 1;
			bridge.setNumberOfBridges(bridgeCounter); // Setze den aktualisierten Zähler zurück.
		} 
		// Wenn der Brückenzähler kleiner als 2 ist, erhöhe ihn um 1.

		// Aktualisiere die Farben der Inseln
		updateColors.updateIslandColors(islandList, firstID);
		updateColors.updateIslandColors(islandList, secondID);

	}
	
	
	/**
	 * Verringert den Brückenzähler für die beiden Inseln und aktualisiert deren Farben entsprechend ihrem Brückenzustand.
	 *
	 * @param firstID      Die eindeutige ID der ersten Insel.
	 * @param secondID     Die eindeutige ID der zweiten Insel.
	 * @param islandList   Die Liste der Inseln, die die aktuellen Brückenzähler enthält.
	 */
	public void decrementBridgeCounter(int firstID, int secondID, ArrayList<Island> islandList) {
		// Ermitteln der aktuellen Brückenzähler für beide Inseln
		int firstIslandBridgeCount = islandList.get(firstID).getBridgeCount();
		int secondIslandBridgeCount = islandList.get(secondID).getBridgeCount();

		if(!data.isGameWasLoaded()) {
			// Verringern der Brückenzähler für beide Inseln um 1
			islandList.get(firstID).setBridgeCount(firstIslandBridgeCount - 1);
			islandList.get(secondID).setBridgeCount(secondIslandBridgeCount - 1);
		}

		updateColors.updateIslandColors(islandList, firstID);
		updateColors.updateIslandColors(islandList, secondID);
	
	}

}
