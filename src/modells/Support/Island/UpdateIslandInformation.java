package modells.Support.Island;

import Modell.CreateBridges;
import Modell.Island;
import modells.GameData.GameData;


/**
 * Die Klasse "UpdateIslandInformation" bietet Funktionen zur Aktualisierung von Informationen für neue Inseln 
 * im Kontext eines Spiels. Sie dient dazu, die Details einer neu erstellten Insel, wie ihre Koordinaten, 
 * Brückenzahl und Brückenrichtungen, zu verwalten und diese Insel in die Spielspielerdaten einzufügen.
 *
 * Konstruktor:
 * - UpdateIslandInformation: Dieser Konstruktor initialisiert eine Instanz von "UpdateIslandInformation" 
 * und benötigt keine speziellen Parameter.
 *
 * Methoden:
 * - updateIslandInformation(int x, int y, int bridgeCount, GameData data): Diese Methode aktualisiert die 
 * Informationen für eine neue Insel mit den angegebenen Koordinaten, Brückenzählern und möglichen Brückenrichtungen. 
 * Sie fügt die neu erstellte Insel zur Inselkarte hinzu und gibt sie zurück.
 *
 * - updateIslands(Island chosenIsland, Island nextIsland, CreateBridges bridge, int bridgeCount, GameData data):
 *	Diese Methode aktualisiert die Spielspielerdaten nach dem Erstellen einer neuen Insel und einer Brücke.
 *  Sie aktualisiert den Index, die Brückenzähler und fügt die neue Insel zur Liste der Inseln und möglichen Inseln hinzu. 
 *  Gleichzeitig wird die ursprüngliche Brücke aus der Liste entfernt.
 *
 * - updateIndex(GameData data, Island nextIsland): Diese Methode aktualisiert den Index in den Spielspielerdaten und fügt die neue Insel in die Index-basierte Karte ein.
 *
 * Diese Klasse ist entscheidend für die Verwaltung von Inseln und Brücken im Spiel, indem sie sicherstellt, dass alle Änderungen korrekt in den Spielspielerdaten berücksichtigt werden. Sie erleichtert die Erweiterung des Spiels, indem sie die erforderlichen Aktualisierungen an den Daten durchführt.
 */
public class UpdateIslandInformation {


	/**
	 * Aktualisiert die Informationen für eine neue Insel und fügt sie zur Inselkarte hinzu.
	 *
	 * @param x Die X-Koordinate der neuen Insel.
	 * @param y Die Y-Koordinate der neuen Insel.
	 * @param bridgeCount Die Anzahl der Brücken, die von der neuen Insel ausgehen.
	 * @param data Die Spielspielerdaten, in denen die Aktualisierung durchgeführt wird.
	 * @return Die neu erstellte Insel mit den angegebenen Informationen.
	 */
	public Island updateIslandInformation(int x, int y, int bridgeCount, GameData data) {
		// Erstelle eine neue Insel mit den angegebenen Koordinaten, Brückenzählern und möglichen Brückenrichtungen
		Island nextIsland = new Island(x, y, data.getIndex(), data.getListofIslands(), bridgeCount, true,
				true, true, true, 0, 0);

		// Füge die neue Insel zur Inselkarte hinzu und erhöhe den Index
		updateIndex(data, nextIsland);

		// Gib die neu erstellte Insel zurück
		return nextIsland;
	}


	/**
	 * Aktualisiert die Spielspielerdaten nach dem Erstellen einer neuen Insel und einer Brücke.
	 *
	 * @param chosenIsland Die ausgewählte Insel, von der aus die Brücke erstellt wird.
	 * @param nextIsland Die neu erstellte Insel, die mit der Brücke verbunden ist.
	 * @param bridge Die Brücke, die entfernt wird, da sie durch die neue Insel ersetzt wird.
	 * @param bridgeCount Die Anzahl der Brücken, die zur neuen Insel führen.
	 * @param data Die Spielspielerdaten, die aktualisiert werden.
	 */
	public void updateIslands(Island chosenIsland, Island nextIsland, CreateBridges bridge, int bridgeCount, GameData data) {
		// Aktualisiere den Index und füge die neue Insel zur Inselkarte hinzu
		updateIndex(data, nextIsland);

		// Aktualisiere die Brückenzähler für die ausgewählte und die neue Insel
		chosenIsland.setBridgeCount(chosenIsland.getBridgeCount() + bridgeCount);
		nextIsland.setBridgeCount(nextIsland.getBridgeCount() + 2 * bridge.getNumberOfBridges());

		// Entferne die ursprüngliche Brücke aus der Liste
		data.getListOfBridges().remove(bridge);

		// Füge die neue Insel zur Liste der Inseln und der möglichen Inseln hinzu
		data.getListofIslands().add(nextIsland);
		data.getPossibleIslands().add(nextIsland);
	}


	/**
	 * Aktualisiert den Index in den Spieldaten und fügt die neue Insel in die Index-basierte Karte ein.
	 *
	 * @param data Die Spielspielerdaten, die den Index und die Inselkarte verwalten.
	 * @param nextIsland Die neu erstellte Insel, die in die Karte aufgenommen wird.
	 */
	private void updateIndex(GameData data, Island nextIsland) {
		// Fügt die neue Insel zur Index-basierten Karte hinzu.
		data.getIslandMap().put(data.getIndex(), nextIsland);

		// Aktualisiert den Index für zukünftige Verwendungszwecke.
		data.setIndex(data.getIndex() + 1);
	}


}
