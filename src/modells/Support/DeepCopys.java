package modells.Support;

import java.util.ArrayList;

import Modell.Island;

/**
 * Die Klasse "DeepCopys" bietet eine Methode zum Erstellen tiefer Kopien einer Liste von Inseln. Diese Methode ermöglicht
 * es, eine völlig unabhängige Kopie der Inseln zu erstellen, die separat bearbeitet werden kann, ohne die Originaldaten 
 * zu beeinflussen.
 *
 * Methoden:
 *
 * - createDeepCopy(ArrayList<Island> listToCopy, ArrayList<Island> listToCopyIn): Diese Methode erstellt eine tiefe Kopie der 
 * gegebenen Liste von Inseln.
 *   Alle Elemente in der Ziel-Liste (listToCopyIn) werden zuerst gelöscht, und dann werden Kopien der Inseln aus der Quell-Liste 
 *   (listToCopy) erstellt und zur Ziel-Liste hinzugefügt.
 *   Die erstellten Kopien sind völlig unabhängig von den Original-Inseln und können separat bearbeitet werden, ohne die 
 *   Originaldaten zu beeinflussen.
 *   Die Methode gibt die Ziel-Liste mit den tiefen Kopien der Inseln zurück.
 */
public class DeepCopys {

	
    /**
     * Die Methode "createDeepCopy" erstellt eine tiefe Kopie der gegebenen Liste von Inseln. Alle Elemente in der
     * Ziel-Liste (listToCopyIn) werden zuerst gelöscht, und dann werden Kopien der Inseln aus der Quell-Liste (listToCopy)
     * erstellt und zur Ziel-Liste hinzugefügt. Die erstellten Kopien sind völlig unabhängig von den Original-Inseln und
     * können separat bearbeitet werden, ohne die Originaldaten zu beeinflussen.
     *
     * @param listToCopy    Die Quellliste von Inseln, die kopiert werden soll.
     * @param listToCopyIn  Die Ziel-Liste, in die die tiefen Kopien der Inseln eingefügt werden.
     * @return Die Ziel-Liste mit den tiefen Kopien der Inseln.
     */
    public ArrayList<Island> createDeepCopy(ArrayList<Island> listToCopy, ArrayList<Island> listToCopyIn) {
        // Vorhandene Elemente in der 'listToCopyIn'-Liste löschen
        listToCopyIn.clear();

        // Durchlaufe alle Inseln in der 'listToCopy'-Liste
        for (Island island : listToCopy) {
            // Erstelle eine Kopie der Insel mit denselben Eigenschaften
            Island islandCopy = new Island(island.getX(), island.getY(), island.getId(), null, island.getBridgeCount(),
                                           false, false, false, false, island.getCenterX(), island.getCenterY());

            // Füge die Inselkopie zur 'listToCopyIn'-Liste hinzu
            listToCopyIn.add(islandCopy);
        }
        return listToCopyIn;
    }
}