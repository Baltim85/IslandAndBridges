package modells.handleIslands;

import Modell.CreateBridges;
import Modell.GridPainter;
import modells.GameData.GameData;

/**
 * Die "RemoveIslands" Klasse bietet die Methode "removeIsland", die verwendet wird, um eine Brücke zwischen zwei Inseln anhand ihrer IDs aus dem Spiel zu entfernen.
 * 
 * Methoden:
 * - removeIsland(int firstID, int secondID, GameData data): Sucht in der Liste der gezeichneten Brücken nach einer Brücke, die zwischen den beiden angegebenen Inseln existiert, und entfernt sie aus der Liste, wenn sie gefunden wird.
 * 
 * Die "RemoveIslands" Klasse ist Teil von Anwendungen, die Bridges-Rätsel oder ähnliche Rätselspiele implementieren und bietet die Funktionalität zur Entfernung von Brücken zwischen Inseln aufgrund ihrer IDs.
 */
public class RemoveIslands {


	/**
	 * Entfernt eine Brücke zwischen zwei Inseln anhand ihrer IDs aus dem Spiel.
	 *
	 * Diese Methode sucht in der Liste der gezeichneten Brücken nach einer Brücke, die zwischen den beiden
	 * angegebenen Inseln existiert, und entfernt sie aus der Liste, wenn sie gefunden wird.
	 *
	 * @param firstID Die ID der ersten Insel.
	 * @param secondID Die ID der zweiten Insel.
	 * @param data Das GameData-Objekt, das die Spieldaten enthält.
	 */
	public void removeIsland(int firstID, int secondID, GameData data) {
	    GridPainter painter = data.getGrid();
	    if (painter.getBridges().isEmpty()) {
	        // Wenn die Liste der gezeichneten Brücken leer ist, gibt es nichts zu entfernen.
	        return;
	    }

	    // Durchlaufen Sie die Liste der gezeichneten Brücken.
	    for (CreateBridges drawBridges : painter.getBridges()) {
	        int first_ID = drawBridges.getFirstIslandID();
	        int second_ID = drawBridges.getSecondIslandID();

	        // Überprüfen Sie, ob die Brücke zwischen den angegebenen Inseln existiert.
	        if ((first_ID == firstID && second_ID == secondID) || (first_ID == secondID && second_ID == firstID)) {
	            // Entfernen Sie die Brücke aus der Liste der gezeichneten Brücken.
	            painter.getBridges().remove(drawBridges);
	            return; // Beenden Sie die Schleife, da die Brücke gefunden und entfernt wurde.
	        }
	    }
	}
}
