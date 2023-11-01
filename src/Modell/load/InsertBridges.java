package Modell.load;

import java.util.ArrayList;
import java.util.List;

import Modell.CreateBridges;
import Modell.Island;


/**
 * Die Klasse InsertBridges ist verantwortlich für das Einfügen von Brücken zwischen Inseln basierend auf den geladenen Informationen
 * in eine Liste von CreateBridges-Objekten. Sie bietet eine Methode zum Verarbeiten einer Liste von Integer-Arrays, wobei jedes 
 * Integer-Array
 * Informationen über eine Brücke zwischen zwei Inseln enthält, und erstellt eine ArrayList von CreateBridges-Objekten aus diesen 
 * Informationen.
 *
 * Methoden:
 *
 * - insertBridgesIntoList(ArrayList<Island> islandsA, List<int[]> listOfBridges): Diese Methode nimmt eine ArrayList von Inseln 
 * und eine
 *   Liste von Integer-Arrays entgegen. Die ArrayList von Inseln enthält Informationen über die Inseln im Spiel, und jedes 
 *   Integer-Array in
 *   der Liste listOfBridges enthält Informationen über Brücken zwischen Inseln. Jedes Integer-Array sollte die folgenden 
 *   Informationen
 *   enthalten: [erste Insel-ID, zweite Insel-ID, Anzahl der Brücken].
 *
 *   Diese Methode iteriert durch die Liste der Integer-Arrays, extrahiert die Informationen für jede Brücke und erstellt eine
 *   entsprechende Instanz der Klasse "CreateBridges". Diese Brücken werden der erstellten ArrayList von CreateBridges-Objekten 
 *   hinzugefügt
 *   und am Ende wird die ArrayList von Brücken zurückgegeben. Für jede Brücke werden die Koordinaten der beiden verbundenen 
 *   Inseln ermittelt
 *   und in der CreateBridges-Instanz gespeichert.
 */
public class InsertBridges {

	
	/**
	 * Diese Methode nimmt eine Liste von Integer-Arrays entgegen, wobei jedes Integer-Array Informationen über eine Brücke 
	 * zwischen zwei Inseln enthält.
	 * Sie erstellt eine ArrayList von CreateBridges-Objekten, die die Brücken repräsentieren, und gibt diese ArrayList zurück.
	 *
	 * @param islandsA     Eine ArrayList von Inseln, die Informationen über die Inseln im Spiel enthält.
	 * @param listOfBridges Die Liste von Integer-Arrays, die Informationen über Brücken zwischen Inseln enthält. Jedes Array 
	 * enthält [erste Insel-ID, zweite Insel-ID, Anzahl der Brücken].
	 * @return Eine ArrayList von CreateBridges-Objekten, die die Brücken im Spiel repräsentieren.
	 */
	public ArrayList<CreateBridges> insertBridgesIntoList(ArrayList<Island> islandsA, List<int[]> listOfBridges) {
	    // Erstelle eine leere ArrayList, um die Brücken aufzunehmen
	    ArrayList<CreateBridges> bridgesList = new ArrayList<CreateBridges>();

	    // Iteriere durch die Integer-Arrays in listOfBridges
	    for (int[] bridgeInfo : listOfBridges) {
	        // Extrahiere die Informationen aus dem aktuellen Integer-Array
	        int firstID = bridgeInfo[0];            // ID der ersten Insel
	        int secondID = bridgeInfo[1];           // ID der zweiten Insel
	        int numberBridges = bridgeInfo[2];      // Anzahl der Brücken

	        // Erhalte die Koordinaten der ersten Insel und der zweiten Insel aus der Insel-ArrayList
	        int firstX = islandsA.get(firstID).getX();
	        int firstY = islandsA.get(firstID).getY();
	        int secondX = islandsA.get(secondID).getX();
	        int secondY = islandsA.get(secondID).getY();

	        // Erstelle eine neue Brücke, indem du die Informationen verwendest, und füge sie zur Liste der Brücken hinzu
	        bridgesList.add(new CreateBridges(firstID, firstX, firstY, secondID, secondX, secondY, numberBridges));
	    }

	    // Gib die ArrayList von CreateBridges-Objekten zurück, die die Brücken im Spiel repräsentiert
	    return bridgesList;
	}

}
