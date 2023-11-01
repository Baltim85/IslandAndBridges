package modells.Support.Island;

import java.util.Random;

import Modell.Island;
import modells.GameData.GameData;
import modells.Support.Directions;


/**
 * Die Klasse "PossibleValueForNextIsland" bietet Funktionen zur Berechnung möglicher Positionen für neue Inseln in Bezug 
 * auf die ausgewählte Insel. Sie wird im Kontext eines Spiels verwendet, um die Positionen neuer Inseln im Südosten (SE) 
 * und Nordwesten (NW) der ausgewählten Insel zu ermitteln.
 *
 * Konstruktor:
 * - PossibleValueForNextIsland(GameData data): Dieser Konstruktor initialisiert eine Instanz von "PossibleValueForNextIsland" 
 * und benötigt Spiel-Daten, um die Berechnungen durchzuführen.
 *
 * Methoden:
 * - possibleValuesSE(Island chosenIsland, int xy, int islandXY, Directions direction): Diese Methode berechnet die möglichen 
 * Werte für die Position einer Insel im Südosten (SE) der ausgewählten Insel. Dabei werden die aktuellen Positionen in X- oder 
 * Y-Richtung sowie die Richtung berücksichtigt. Die Methode gibt einen möglichen Wert für die Position im Südosten zurück.
 *
 * - possibleValuesNW(Island chosenIsland, int xy, int islandXY, Directions direction): Diese Methode berechnet die möglichen 
 * Werte für die Position einer Insel im Nordwesten (NW) der ausgewählten Insel. Sie berücksichtigt die aktuellen Positionen 
 * in X- oder Y-Richtung sowie die Richtung. Die Methode gibt einen möglichen Wert für die Position im Nordwesten zurück.
 *
 * Diese Klasse ist nützlich, um im Spiel die Platzierung neuer Inseln zu steuern und sicherzustellen, dass sie den Regeln und
Bedingungen des Spiels entsprechen. Sie kapselt die Logik zur Berechnung dieser Positionen und trägt zur Erweiterung des Spielfelds bei.
 */
public class PossibleValueForNextIsland {


	// Ein Random-Objekt zur Generierung von Zufallszahlen
	private static final Random random = new Random();
	private GameData data;

	/**
	 * Erstellt eine Instanz der Klasse "PossibleValueForNextIsland".
	 *
	 * @param data Die Spiel-Daten, die für die Berechnung der möglichen Werte benötigt werden.
	 */
	public PossibleValueForNextIsland(GameData data) {
		this.data = data;
	}

	/**
	 * Berechnet die möglichen Werte für die Position einer Insel im Südosten (SE) der ausgewählten Insel.
	 *
	 * @param chosenIsland Die ausgewählte Insel, von der aus die Position berechnet wird.
	 * @param xy Die aktuelle Position in X- oder Y-Richtung, abhängig von der Richtung.
	 * @param islandXY Die Position der ausgewählten Insel in X- oder Y-Richtung, abhängig von der Richtung.
	 * @param direction Die Richtung, in der die Position berechnet wird (z.B. Directions.SOUTH oder Directions.EAST).
	 * @return Ein möglicher Wert für die Position im Südosten der ausgewählten Insel.
	 */
	public int possibleValuesSE(Island chosenIsland, int xy, int islandXY, Directions direction) {
		int possibleValues = xy - (islandXY + 1);
		if (possibleValues <= 0) {
			return 0;
		} else {
			possibleValues = (random.nextInt(possibleValues));
			if (possibleValues == 0) {
				if (direction == Directions.SOUTH && (2 + islandXY) > data.getHeight()) {
					return possibleValues = 0;
				}
				if (direction == Directions.EAST && (2 + islandXY) > data.getWidth()) {
					return possibleValues = 0;
				} else {
					return possibleValues = 2 + islandXY;
				}
			}
		}
		return 2 + islandXY;
	}



	/**
	 * Berechnet die möglichen Werte für die Position einer Insel im Nordwesten (NW) der ausgewählten Insel.
	 *
	 * @param chosenIsland Die ausgewählte Insel, von der aus die Position berechnet wird.
	 * @param xy Die aktuelle Position in X- oder Y-Richtung, abhängig von der Richtung.
	 * @param islandXY Die Position der ausgewählten Insel in X- oder Y-Richtung, abhängig von der Richtung.
	 * @param direction Die Richtung, in der die Position berechnet wird (z.B. Directions.NORTH oder Directions.WEST).
	 * @return Ein möglicher Wert für die Position im Nordwesten der ausgewählten Insel.
	 */
	public int possibleValuesNW(Island chosenIsland, int xy, int islandXY, Directions direction) {
		int possibleValues = 0;
		if (direction == Directions.NORTH) {
			possibleValues = xy + (islandXY - data.getHeight() - 2);
		} else {
			possibleValues = xy + (islandXY - data.getWidth() - 2);
		}
		if (possibleValues <= 0) {
			return 0;
		} else {
			possibleValues = (random.nextInt(possibleValues));
			if (possibleValues == 0) {
				if ((islandXY - 2) <= 0)
					return possibleValues = 0;
				else
					return possibleValues = islandXY - 2;
			}
		}
		return possibleValues;
	}
	
	/**
	 * Sucht eine neue Zufällige Insel aus der Anzahl der möglichen Inseln.
	 * 
	 * @param possibleIslands die Menge der möglichen Inseln
	 * @param islandM Eine HashMap mit allen möglichen Inseln
	 * @return liefert eine zufällige Insel zurück
	 */
	public Island randomIsland(GameData data) {		
		Island item = data.getPossibleIslands().get(random.nextInt(data.getPossibleIslands().size()));
		return data.getIslandMap().get(item.getId());

	}

}
