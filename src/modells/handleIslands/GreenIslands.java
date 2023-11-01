package modells.handleIslands;

import Modell.GridPainter;
import Modell.Island;
import UI.Bridges;
import modells.GameData.GameData;
import modells.GameStatus.CheckCompletedStatus;

/**
 * Die "GreenIslands" Klasse bietet die Methode "fillIslandGreen", die verwendet wird, um ein Inselobjekt zur Liste der grünen Inseln hinzuzufügen und es aus der Liste der roten Inseln zu entfernen. Diese Klasse überwacht auch den Spielstatus, zeigt Fehlermeldungen an und prüft, ob das Rätsel abgeschlossen wurde.
 * 
 * Methoden:
 * - fillIslandGreen(Island island, GameData data): Fügt das übergebene Inselobjekt zur Liste der grünen Inseln hinzu und entfernt es aus der Liste der roten Inseln. Es überprüft, ob das Spiel Fehler enthält, aktualisiert die Anzeige entsprechend und prüft, ob das Rätsel abgeschlossen wurde.
 * 
 * Die "GreenIslands" Klasse ist Teil von Anwendungen, die Bridges-Rätsel oder ähnliche Rätselspiele implementieren und bietet die Funktionalität zur Verwaltung der Zustände von Inseln, zur Überprüfung von Spielfehlern und zur Überwachung des Spielstatus.
 */
public class GreenIslands {
	
	// Eine Instanz der Klasse CheckCompletedStatus zur Überprüfung des Spielstatus
	private CheckCompletedStatus isGameCompleted;
	
	private ControllGameStatus gameStatus;
	
   
	/**
	 * Die Methode `fillIslandGreen` fügt eine Insel zur Liste der grünen Inseln hinzu und entfernt sie aus der Liste der roten Inseln.
	 * Sie überprüft, ob das Spiel Fehler enthält, aktualisiert die Anzeige entsprechend und prüft, ob das Rätsel abgeschlossen wurde.
	 * 
	 * @param island Das hinzuzufügende Inselobjekt.
	 * @param data Das Spiel-Datenobjekt, in dem die Listen der Inseln und Brücken verwaltet werden.
	 */
	public void fillIslandGreen(Island island, GameData data) {
	    GridPainter painter = data.getGrid();
	    Bridges bridges = data.getBridges();
	    isGameCompleted = new CheckCompletedStatus();
	    gameStatus = new ControllGameStatus();
	    
	    // Füge die Insel zur Liste der grünen Inseln hinzu.
	    painter.getDrawGreenBridges().add(island); 
	    
	    // Entferne die Insel aus der Liste der roten Inseln.       
	    painter.getDrawRedBridges().remove(island);      
	    gameStatus.gameStatus(painter, bridges);

	    // Überprüfe, ob die Liste der roten Brücken leer ist und setze die Sichtbarkeit des Fehlersymbols entsprechend.
	    if (painter.getDrawRedBridges().isEmpty()) {
	        data.setErrorVisible(false);
	    }

	    // Aktualisiere die Anzeige der Brücken.
	    if (!data.getBridgesList().isEmpty()) {
	        isGameCompleted.checkCompleteStatus(data);
	    }
	    bridges.getDraw().repaint();                         
	}
}
