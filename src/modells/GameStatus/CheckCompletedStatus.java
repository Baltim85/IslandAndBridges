package modells.GameStatus;

import java.util.ArrayList;
import java.util.Iterator;

import modells.GameData.GameData;


/**
 * Die "CheckCompletedStatus" Klasse bietet eine Methode zur Überprüfung des Spielstatus, um festzustellen, 
 * ob das Spiel abgeschlossen ist. Diese Klasse ist ein wichtiger Bestandteil der Spiellogik und ermöglicht es, den 
 * Abschluss des Spiels zu ermitteln.
 * 
 * Methoden:
 * - checkCompleteStatus(GameData data): Überprüft den Status des Spiels, um festzustellen, ob es abgeschlossen ist, 
 * indem geprüft wird, ob alle Inseln miteinander verbunden sind.
 * 
 * Die "CheckCompletedStatus" Klasse ist von zentraler Bedeutung für Anwendungen, die Rätselspiele wie Bridges 
 * implementieren. Sie ermöglicht die Feststellung, ob das Spiel erfolgreich abgeschlossen wurde, indem sie die Verbindungen 
 * zwischen den Inseln analysiert und den Spielstatus entsprechend aktualisiert.
 */
public class CheckCompletedStatus {
	
	
	/**
	 * Überprüft den Status des Spiels, um festzustellen, ob es abgeschlossen ist.
	 *
	 * @param data Die Spielinformationen.
	 */
	public void checkCompleteStatus(GameData data) {
	    // Erstelle eine Liste für die IDs der Inseln
	    ArrayList<Integer> islands = new ArrayList<Integer>();
	    
	    // Holen Sie sich den Zeichenbereich und die Brückeninstanz aus den Spielinformationen

	    // Füge die IDs der ersten Inseln aus den vorhandenen Brücken hinzu
	    if (!data.getBridgesList().isEmpty()) {
	        islands.add(data.getBridgesList().get(0).getFirstIslandID());
	        islands.add(data.getBridgesList().get(0).getSecondIslandID());
	    }
	    
	    // Iterator für die Inseln
	    Iterator<Integer> iterator = islands.iterator();
	    
	    // Überprüfe, ob die Anzahl der grün gezeichneten Brücken gleich der Gesamtanzahl der Inseln ist
	    if (data.getGrid().getDrawGreenBridges().size() == data.getListofIslands().size()) {
	        // Iteriere über die IDs der Inseln
	        while(iterator.hasNext()) {
	            int islandID = iterator.next();
	            for (int i = 1; i < data.getBridgesList().size(); i++) {
	                int firstID = data.getBridgesList().get(i).getFirstIslandID();
	                int secondID = data.getBridgesList().get(i).getSecondIslandID();

	                // Überprüfe, ob die aktuellen IDs in der Brücke vorhanden sind
	                if (firstID == islandID || secondID == islandID) {
	                    // Füge die IDs der verbundenen Inseln zur islands-Liste hinzu, wenn sie noch nicht enthalten sind
	                    if (!islands.contains(firstID)) {
	                        islands.add(firstID);
	                        iterator = islands.iterator();
	                    }
	                    
	                    if (!islands.contains(secondID)) {
	                        islands.add(secondID);
	                        iterator = islands.iterator();
	                    }
	                }
	            }
	        }
	        
	        // Wenn die Anzahl der IDs in der Liste gleich der Gesamtanzahl der Inseln ist, ist das Spiel abgeschlossen
	        if(islands.size() == data.getListofIslands().size())
	            data.getDialogController().gameCompleted();
	    }
	}

	
}
