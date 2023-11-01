package modells.handleIslands;

import Modell.GridPainter;
import UI.Bridges;



/**
 * Die "ControllGameStatus" Klasse bietet die Methode "gameStatus", die verwendet wird, um den Status des Spiels basierend auf vorhandenen Fehlern und dem Lösungsstatus zu aktualisieren.
 * 
 * Methoden:
 * - gameStatus(GridPainter painter, Bridges bridges): Aktualisiert den Status des Spiels basierend auf den vorhandenen Fehlern und dem Lösungsstatus.
 * 
 * Die "ControllGameStatus" Klasse ist Teil von Anwendungen, die Bridges-Rätsel oder ähnliche Rätselspiele implementieren und bietet die Funktionalität zur Überwachung und Anzeige des Spielstatus.
 */
public class ControllGameStatus {
	
    /**
     * Aktualisiert den Status des Spiels basierend auf den vorhandenen Fehlern und dem Lösungsstatus.
     *
     * Diese Methode überprüft, ob Fehler im Spiel vorhanden sind, indem sie die Liste der gezeichneten roten Brücken
     * im `painter`-Objekt untersucht. Wenn rote Brücken gefunden werden, wird die Anzeige aktualisiert, um den Benutzer
     * darüber zu informieren, dass das Spiel Fehler enthält. Andernfalls wird eine Meldung angezeigt, dass das Rätsel
     * noch nicht gelöst wurde.
     *
     * @param painter Das `GridPainter`-Objekt, das das Spielfeld und die Brücken darstellt.
     * @param bridges Das `Bridges`-Objekt, das für die Anzeige und Informationen des Spiels verantwortlich ist.
     */
    public void gameStatus(GridPainter painter, Bridges bridges) {
        if (!painter.getDrawRedBridges().isEmpty()) {
            // Wenn die Liste der gezeichneten roten Brücken nicht leer ist, gibt es Fehler im Spiel.
            bridges.getDraw().repaint(); // Aktualisieren Sie die Anzeige, um die Fehler zu zeigen.
            bridges.getLblInfo().setText("Das Spiel enthält Fehler!");
        } else {
            // Wenn keine roten Brücken vorhanden sind, ist das Rätsel noch nicht gelöst.
            bridges.getLblInfo().setText("Das Rätsel ist noch nicht gelöst");
        }
    }
    

    

}
