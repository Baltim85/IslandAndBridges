package modells.Support;

import Modell.GridPainter;
import modells.GameData.GameData;

/**
 * Die "ClearLists" Klasse ist für das Zurücksetzen von verschiedenen Listen in einem Spiel-Datenobjekt verantwortlich. 
 * Sie bietet eine Methode, um spezifische Listen in einem "GameData"-Objekt zu löschen, was dazu dient, 
 * den aktuellen Spielzustand zu bereinigen.
 * 
 * Methoden:
 * - clearLists(GameData data): Löscht bestimmte Listen im übergebenen "GameData"-Objekt, um sie zurückzusetzen und den 
 * Spielzustand zu bereinigen.
 * 
 * Die "ClearLists" Klasse kann in Anwendungen verwendet werden, um die Listen von Brücken, Inseln und anderen 
 * Spielfeldkomponenten zurückzusetzen, wenn ein Spiel neu gestartet oder zurückgesetzt wird.
 */
public class ClearLists {

	   /**
     * Löscht verschiedene Listen in einem Spiel-Datenobjekt, um sie zurückzusetzen.
     * 
     * @param data Das Spiel-Datenobjekt, in dem die Listen zurückgesetzt werden sollen.
     */
	public void clearLists(GameData data) {
		GridPainter gridPainter = data.getGrid();
		gridPainter.getDrawGreenBridges().clear();
		gridPainter.getDrawNormalIslands().clear();
		gridPainter.getDrawRedBridges().clear();
		if(data.getBridgesList() == null)
			return;
		data.getBridgesList().clear();
		
		//islandList.clear();
		//listOfBridge.clear();
		//createBridges.getIslandList().clear();
		//createBridges.getListOfBridge().clear();
	}
}
