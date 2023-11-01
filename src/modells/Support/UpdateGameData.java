package modells.Support;

import java.util.ArrayList;

import Modell.CalculateGrid;
import Modell.CreateBridges;
import Modell.GridPainter;
import Modell.Island;
import modells.GameData.GameData;


/**
 * Die "UpdateGameData" Klasse ist verantwortlich für die Aktualisierung der Daten im `GameData`-Objekt, das in der 
 * Anwendung verwendet wird. Diese Klasse enthält Methoden zum Aktualisieren verschiedener Datenkomponenten innerhalb des 
 * `GameData`-Objekts, um sicherzustellen, dass die Spielinformationen und Zustände korrekt und konsistent bleiben.

 * Attribute:
 * - data: Ein Verweis auf das `GameData`-Objekt, in dem die Daten aktualisiert werden.

 * Konstruktor:
 * - UpdateGameData(GameData data): Erstellt eine neue Instanz der "UpdateGameData"-Klasse mit dem gegebenen `GameData`-Objekt.

 * Methoden:
 * - updateDeepCopy(ArrayList<Island> listOfIslands, ArrayList<Island> deepCopy): Diese Methode aktualisiert die Liste der 
 * tiefen Kopien der Inseln im `GameData`-Objekt, um sicherzustellen, dass Änderungen an den Inseln nachvollzogen werden können.

 * - updateGrid(GridPainter grid): Diese Methode aktualisiert das Gitter im `GameData`-Objekt, um sicherzustellen, dass die 
 * Rasterdarstellung des Spielfelds korrekt ist.

 * - updateGridValues(CalculateGrid gridValues): Diese Methode aktualisiert die Gitterwerte im `GameData`-Objekt, um die 
 * aktuellen Berechnungen für das Spielfeld zu berücksichtigen.

 * - updateGameExist(): Diese Methode setzt den Status, ob ein Spiel existiert, auf "true" im `GameData`-Objekt.

 * - updateBridgeList(ArrayList<CreateBridges> bridgesList): Diese Methode aktualisiert die Liste der Brücken-Objekte im 
 * `GameData`-Objekt.

 * - updateWidth(int width): Diese Methode aktualisiert die Breite (width) im `GameData`-Objekt, um Änderungen in den 
 * Spielfelddimensionen widerzuspiegeln.

 * - updateHeight(int height): Diese Methode aktualisiert die Höhe (height) im `GameData`-Objekt, um Änderungen in den 
 * Spielfelddimensionen widerzuspiegeln.

 * - updateIslands(int islands): Diese Methode aktualisiert die Anzahl der Inseln (islands) im `GameData`-Objekt, um die 
 * Anzahl der Inseln auf dem Spielfeld zu berücksichtigen.

 * Die "UpdateGameData" Klasse spielt eine Schlüsselrolle bei der Verwaltung und Aktualisierung der Spieldaten und stellt 
 * sicher, dass die Informationen konsistent und korrekt bleiben, um ein reibungsloses Spielerlebnis zu gewährleisten.
 */
public class UpdateGameData {
    private GameData data;

    /**
     * Erstellt eine neue Instanz der `UpdateGameData`-Klasse mit dem gegebenen `GameData`-Objekt.
     *
     * @param data Das `GameData`-Objekt, in dem die Daten aktualisiert werden.
     */
    public UpdateGameData(GameData data) {
        this.data = data;
    }

    /**
     * Aktualisiert die Liste der tiefen Kopien der Inseln im `GameData`-Objekt.
     *
     * @param listOfIslands Die Liste der Inseln, deren Kopien erstellt werden sollen.
     * @param deepCopy Die Liste, in die die tiefen Kopien der Inseln geschrieben werden.
     */
    public void updateDeepCopy(ArrayList<Island> listOfIslands, ArrayList<Island> deepCopy) {
        data.setDeepCopy(data.getCopyList().createDeepCopy(listOfIslands, deepCopy));
    }

    /**
     * Aktualisiert das Gitter im `GameData`-Objekt.
     *
     * @param grid Das `GridPainter`-Objekt, das das Gitter repräsentiert.
     */
    public void updateGrid(GridPainter grid) {
        data.setGrid(grid);
    }

    /**
     * Aktualisiert die Gitterwerte im `GameData`-Objekt.
     *
     * @param gridValues Das `CalculateGrid`-Objekt, das die Gitterwerte enthält.
     */
    public void updateGridValues(CalculateGrid gridValues) {
        data.setGridValues(gridValues);
    }

    /**
     * Aktualisiert den Status, ob ein Spiel existiert, im `GameData`-Objekt auf `true`.
     */
    public void updateGameExist() {
        data.setGameExist(true);
    }
    
    /**
     * Die Methode `updateBridgeList` dient dazu, die Liste der Brücken-Objekte in der GameData-Klasse zu aktualisieren.
     *
     * @param bridgesList Die zu setzende Liste von Brücken-Objekten.
     */
    public void updateBridgeList(ArrayList<CreateBridges> bridgesList) {
        data.setBridgesList(bridgesList);
    }
    
    /**
     * Aktualisiert die Breite (width) im GameData-Objekt.
     *
     * @param width Die neue Breite des Spielfelds.
     */
    public void updateWidth(int width) {
        data.setWidth(width);
    }

    /**
     * Aktualisiert die Höhe (height) im GameData-Objekt.
     *
     * @param height Die neue Höhe des Spielfelds.
     */
    public void updateHeight(int height) {
        data.setHeight(height);
    }

    /**
     * Aktualisiert die Anzahl der Inseln (islands) im GameData-Objekt.
     *
     * @param islands Die neue Anzahl der Inseln auf dem Spielfeld.
     */
    public void updateIslands(int islands) {
        data.setIslands(islands);
    }
}

