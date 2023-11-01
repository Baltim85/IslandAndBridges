package modells.handleIslands;

import Modell.GridPainter;
import Modell.Island;
import UI.Bridges;
import modells.GameData.GameData;


/**
 * Die "DrawEmptyIsland" Klasse bietet die Methode "emptyIsland", die verwendet wird, um eine Insel aus der Liste der grünen Inseln zu entfernen und zur Liste der normalen Inseln hinzuzufügen. Außerdem wird überprüft, ob das Spiel Fehler enthält, und die Anzeige entsprechend aktualisiert.
 * 
 * Methoden:
 * - emptyIsland(Island island, GameData data): Entfernt eine Insel aus der Liste der grünen Inseln und fügt sie zur Liste der normalen Inseln hinzu. Aktualisiert den Spielstatus basierend auf vorhandenen Fehlern und Lösungsstatus.
 * - gameStatus(GridPainter painter, Bridges bridges): Aktualisiert den Status des Spiels basierend auf den vorhandenen Fehlern und dem Lösungsstatus.
 * 
 * Die "DrawEmptyIsland" Klasse ist Teil von Anwendungen, die Bridges-Rätsel oder ähnliche Rätselspiele implementieren und bietet die Funktionalität zur Behandlung von Inseln und zur Aktualisierung des Spielstatus.
 */
public class DrawEmptyIsland {

	private ControllGameStatus gameStatus;
	
	/**
	 * Die Methode `emptyIsland` wird verwendet, um eine Insel aus der Liste der grünen Inseln zu entfernen und zur Liste der normalen Inseln hinzuzufügen.
	 * Außerdem wird überprüft, ob das Spiel Fehler enthält, und die Anzeige entsprechend aktualisiert.
	 * 
	 * @param island Das zu entfernende Inselobjekt.
	 * @param data Das Spiel-Datenobjekt, in dem die Listen der Inseln und Brücken verwaltet werden.
	 */
    public void emptyIsland(Island island, GameData data) {
    	GridPainter painter = data.getGrid();
    	Bridges bridges = data.getBridges();
    	// Entferne die Insel aus der Liste der grünen Inseln.
    	painter.getDrawGreenBridges().remove(island);      
    	// Füge die Insel zur Liste der normalen Inseln hinzu.
    	painter.getDrawNormalIslands().add(island);   
    	gameStatus = new ControllGameStatus();
    	gameStatus.gameStatus(painter, bridges);
    	//gameStatus(painter, bridges);
    	if(painter.getDrawRedBridges().isEmpty())
    		data.setErrorVisible(false);
    		
    	// Aktualisiere die Anzeige der Brücken.
    	bridges.getDraw().repaint();                         
    }

}
