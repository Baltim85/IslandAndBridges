package modells.handleIslands;


import Modell.Island;
import modells.GameData.GameData;



/**
 * Die "RedIslands" Klasse bietet eine Methode, um eine Insel im Spiel rot zu markieren und überprüft anschließend das Spiel auf mögliche Regelverletzungen.
 * 
 * Methoden:
 * - fillIslandRed(Island island, GameData data): Markiert eine Insel als rot und überprüft das Spiel auf mögliche Fehler.
 * 
 * Die "RedIslands" Klasse ist ein wesentlicher Bestandteil von Anwendungen, die Rätselspiele wie Bridges implementieren. Sie ermöglicht die Kennzeichnung von Inseln als potenzielle Regelverletzungen und die Aktualisierung des Spielstatus, wenn Fehler im Spiel auftreten.
 */
public class RedIslands {
	
	/**
	 * Färbt eine Insel rot und überprüft das Spiel auf Fehler.
	 *
	 * Diese Methode färbt eine gegebene Insel im Spiel rot und überprüft dann, ob das Spiel Fehler enthält. 
	 * Die Methode wird verwendet, um eine potenzielle Spielregelverletzung darzustellen und die Spielstatuskontrolle zu aktualisieren.
	 *
	 * @param island Die Insel, die rot gefärbt werden soll.
	 * @param data   Das GameData-Objekt, das die Spielinformationen enthält.
	 */
	public void fillIslandRed(Island island, GameData data) {
	    // Überprüfen, ob die Insel bereits als grüne Brücke gezeichnet wurde, falls ja, entfernen.
	    if (data.getGrid().getDrawGreenBridges().contains(island))
	        data.getGrid().getDrawGreenBridges().remove(island);

	    // Überprüfen, ob die Insel bereits als rote Brücke gezeichnet wurde
	    if (data.getGrid().getDrawRedBridges().contains(island)) {
	        data.getBridges().getDraw().repaint();
	        data.getBridges().getLblInfo().setText("Das Spiel enthält Fehler");
	    } else {
	        data.getGrid().getDrawRedBridges().add(island);
	        data.getBridges().getLblInfo().setText("Das Spiel enthält Fehler");

	        // Wenn Fehlermeldungen bisher nicht sichtbar waren, wird eine Fehlermeldung angezeigt
	        if (!data.isErrorVisible()) {
	            data.getDialogController().errorIslands();
	        }
	    }

	    // Die Zeichenfläche aktualisieren
	    data.getBridges().getDraw().repaint();
	}

}
