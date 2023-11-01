package modells.handleIslands;

import java.util.ArrayList;

import Modell.Island;

/**
 * Die Klasse "CalculateCenter" bietet eine Methode zum Berechnen des Zentrums (centerX und centerY) für jede Insel in der angegebenen Liste von Inseln. Das Zentrum wird anhand der übergebenen x- und y-Koordinaten berechnet.
 *
 * Methoden:
 *
 * - calculateCenterForEachIsland(ArrayList<Island> islandList, int x, int y): Diese Methode berechnet das Zentrum für jede Insel in der übergebenen Liste von Inseln. Das Zentrum wird anhand der angegebenen x- und y-Koordinaten berechnet.
 *   Sie akzeptiert eine Liste von "Island"-Objekten, für die das Zentrum berechnet werden soll, sowie die X- und Y-Koordinaten des Zentrums.
 *   Die Methode iteriert durch die Liste von Inseln, berechnet das Zentrum für jede Insel und aktualisiert die centerX- und centerY-Eigenschaften jeder Insel in der Liste. Schließlich gibt sie die aktualisierte Liste von Inseln zurück.
 */
public class CalculateCenter {

    /**
     * Berechnet das Zentrum (centerX und centerY) für jede Insel in der angegebenen Liste von Inseln.
     * Das Zentrum wird anhand der übergebenen x- und y-Koordinaten berechnet.
     *
     * @param islandList Eine Liste von Inseln, für die das Zentrum berechnet werden soll.
     * @param x Die X-Koordinate des Zentrums.
     * @param y Die Y-Koordinate des Zentrums.
     * @return Die aktualisierte Liste von Inseln mit den berechneten centerX- und centerY-Eigenschaften.
     */
    public ArrayList<Island> calculateCenterForEachIsland(ArrayList<Island> islandList, int x, int y) {
        for (Island island : islandList) {
            int islandX = island.getX();
            int islandY = island.getY();
            island.setCenterX(x * islandX - x + ((x / 2) + 2));
            island.setCenterY(y * islandY - y + ((y / 2) + 2));
        }
        return islandList;
    }
}
