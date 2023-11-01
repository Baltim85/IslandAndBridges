package modells.Support;

import java.util.Collections;
import java.util.Comparator;

import Modell.Island;
import modells.GameData.GameData;


/**
 * Die Klasse "ReindexID" ist für die Neubewertung (Neuindizierung) der IDs von Inseln in der "listofIslands"-Liste
 * verantwortlich. Die Neuindizierung basiert auf der geordneten Reihenfolge der Inseln, zuerst nach ihrer X-Koordinate
 * und dann nach ihrer Y-Koordinate. Diese Klasse stellt sicher, dass die IDs der Inseln gemäß ihrer geografischen
 * Position in der Inselkarte aktualisiert werden.

 * Methoden:
 * - reindexID(GameData data): Diese Methode sortiert die "listofIslands"-Liste zuerst nach der X-Koordinate und dann
 * nach der Y-Koordinate der Inseln. Anschließend weist sie den Inseln neue IDs basierend auf ihrer Reihenfolge in der
 * sortierten Liste zu. Die neu indizierten IDs werden von null aufsteigend vergeben und die Inseln behalten ihre relative
 * Reihenfolge bei.

 * Diese Klasse ist nützlich, um sicherzustellen, dass die IDs der Inseln aktuell und in Übereinstimmung mit ihrer
 * geografischen Position sind. Sie kann in Szenarien verwendet werden, in denen die Reihenfolge und ID der Inseln in der
 * Liste wichtig sind, beispielsweise bei der Erstellung von Rätseln oder der Berechnung von Lösungen in einem
 * Inselrätselspiel.
 */
public class ReindexID {
	
	
	
	/**
	 * Diese Methode indiziert die IDs der Inseln in der listofIslands-Liste neu,
	 * basierend auf ihrer Position in der sortierten Reihenfolge (erst nach X, dann nach Y).
	 */
	public void reindexID(GameData data) {
	    // Sortiere die listofIslands zuerst nach der X-Koordinate und dann nach der Y-Koordinate.
	    Collections.sort(data.getListofIslands(), new Comparator<Island>() {
	        @Override
	        public int compare(Island island1, Island island2) {
	            // Vergleiche die X-Koordinaten der Inseln.
	            int compareX = Integer.compare(island1.getX(), island2.getX());
	            if (compareX == 0) {
	                // Wenn die X-Koordinaten gleich sind, vergleiche nach Y-Koordinaten.
	                return Integer.compare(island1.getY(), island2.getY());
	            }
	            return compareX;
	        }
	    });

	    // Setze einen Zähler für die neuen IDs.
	    int newID = 0;

	    // Durchlaufe die sortierte Liste und weise den Inseln neue IDs zu.
	    for (Island island : data.getListofIslands()) {
	        island.setId(newID);
	        newID++;
	    }
	    // Die IDs wurden erfolgreich neu indiziert.
	}

}
