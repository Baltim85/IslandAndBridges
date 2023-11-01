package Modell.load;

import java.util.ArrayList;
import java.util.List;

import Modell.Island;

/**
 * Die Klasse InsertIslands ist verantwortlich für das Einfügen von Inseln basierend auf den geladenen Informationen
 * in eine Liste von Inseln. Sie bietet eine Methode zum Verarbeiten einer Liste von Integer-Arrays, wobei jedes Integer-Array
 * Informationen über eine Insel enthält, und erstellt eine ArrayList von Inseln aus diesen Informationen.
 *
 * Methoden:
 *
 * - insertIslandsIntoList(List<int[]> listOfIslands): Diese Methode nimmt eine Liste von Integer-Arrays entgegen, wobei jedes
 *   Integer-Array Informationen über eine Insel enthält. Sie erstellt eine ArrayList von Inseln basierend auf den gelieferten
 *   Informationen und gibt diese ArrayList zurück. Jedes Integer-Array sollte die folgenden Informationen enthalten:
 *   [x-Koordinate, y-Koordinate, Brückenanzahl].
 *
 *   Diese Methode iteriert durch die Liste der Integer-Arrays, extrahiert die Informationen für jede Insel und erstellt eine
 *   entsprechende Instanz der Klasse "Island". Diese Inseln werden der erstellten ArrayList hinzugefügt und am Ende wird die
 *   ArrayList von Inseln zurückgegeben.
 */
public class InsertIslands {
	
	
	/**
	 * Diese Methode nimmt eine Liste von Integer-Arrays entgegen, wobei jedes Integer-Array Informationen über eine Insel enthält.
	 * Sie erstellt eine ArrayList von Inseln basierend auf den gelieferten Informationen und gibt diese ArrayList zurück.
	 *
	 * @param listOfIslands Die Liste von Integer-Arrays, die Informationen über Inseln enthält. Jedes Array enthält [x-Koordinate, y-Koordinate, Brückenanzahl].
	 * @return Eine ArrayList von Inseln, die aus den gelieferten Informationen erstellt wurde.
	 */
	public ArrayList<Island> insertIslandsIntoList(List<int[]> listOfIslands) {
	    // Erstelle eine leere ArrayList, um die Inseln aufzunehmen
	    ArrayList<Island> islandsA = new ArrayList<Island>();

	    // Iteriere durch die Integer-Arrays in listOfIslands
	    for (int[] islandInfo : listOfIslands) {
	        // Extrahiere die Informationen aus dem aktuellen Integer-Array
	        int x = islandInfo[0];          // x-Koordinate der Insel
	        int y = islandInfo[1];          // y-Koordinate der Insel
	        int bridge = islandInfo[2];     // Brückenanzahl der Insel

	        // Eine neue Insel erstellen, indem die Informationen verwendet werden,
	        // und zur Liste der Inseln hinzufügen
	        Island island = new Island(x, y, islandsA.size(), bridge);
	        islandsA.add(island);
	    }

	    // Gib die ArrayList von Inseln zurück, die aus den gelieferten Informationen erstellt wurde
	    return islandsA;
	}
	

}
