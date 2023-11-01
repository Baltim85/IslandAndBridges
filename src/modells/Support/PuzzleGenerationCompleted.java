package modells.Support;

import modells.GameData.GameData;


/**
 * Die Klasse "PuzzleGenerationCompleted" bietet eine Methode zur Überprüfung, ob das Spiel abgeschlossen ist. Dies wird
 * erreicht, indem die Anzahl der erstellten Inseln mit der Gesamtanzahl der erforderlichen Inseln verglichen wird. Sie ist
 * verantwortlich für die Überwachung des Spielzustands und das Erkennen, ob das generierte Puzzle fertiggestellt wurde.

 * Methoden:
 * - isCompleted(GameData data): Diese Methode überprüft, ob das Spiel abgeschlossen ist, indem sie die Anzahl der in der
 *   Spielerdaten-Instanz erstellten Inseln mit der Gesamtanzahl der erforderlichen Inseln vergleicht. Wenn die Anzahl
 *   übereinstimmt, wird das Spiel als abgeschlossen betrachtet, andernfalls als noch nicht abgeschlossen. Wenn das Spiel
 *   abgeschlossen ist, werden zusätzliche Aktualisierungen am Spielerdatenobjekt vorgenommen, einschließlich der
 *   Neubewertung der Insel-IDs mithilfe der Klasse "ReindexID".

 * Diese Klasse dient dazu, den Spielzustand zu überwachen und festzustellen, wann das generierte Puzzle vollständig ist.
 * Sie kann in Inselrätselspielen oder ähnlichen Anwendungen verwendet werden, um sicherzustellen, dass die Spielziele
 * erreicht wurden und das Rätsel erfolgreich gelöst wurde.
 */
public class PuzzleGenerationCompleted {
	
	/**
	 * Überprüft, ob das Spiel abgeschlossen ist, indem es die Anzahl der erstellten Inseln mit der Gesamtanzahl vergleicht.
	 *
	 * @param data Die Spielerdaten, die verwendet werden, um den Spielzustand zu überprüfen.
	 * @return True, wenn das Spiel abgeschlossen ist, andernfalls false.
	 */
	public boolean isCompleted(GameData data) {
	    // Überprüfe, ob die Anzahl der erstellten Inseln der Gesamtanzahl entspricht
	    if (data.getListofIslands().size() == data.getIslands()) {
	        // Aktualisiere die Inseln im Spielerdatenobjekt
	        data.setListofIslands(data.getListofIslands());

	        // Reindiziere die IDs der Inseln
	        ReindexID reindex = new ReindexID();
	        //reindex.reindexID(data);

	        // Das Spiel ist abgeschlossen
	        return true;
	    } else {
	        // Das Spiel ist noch nicht abgeschlossen
	        return false;
	    }
	}


}
