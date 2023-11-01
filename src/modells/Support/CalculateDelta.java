package modells.Support;

import Modell.CalculateGrid;

/**
 * Die "CalculateDelta" Klasse ist für die Berechnung des Werts "delta" verantwortlich, der auf den Distanzen "XDistance" 
 * und "YDistance" basiert. "Delta" entspricht der kleineren der beiden Distanzen und wird in verschiedenen Teilen der 
 * Anwendung verwendet, um die Größe von Spielobjekten und deren Positionen im Raster zu steuern.

 * Attribute:
 * - Keine.

 * Konstruktor:
 * - Keine.

 * Methoden:
 * - calculateDelta(CalculateGrid gridValues): Diese Methode berechnet den Wert "delta" basierend auf den Distanzen "XDistance" 
 * und "YDistance", die aus dem Objekt "gridValues" abgerufen werden. "Delta" entspricht der kleineren der beiden Distanzen. 
 * Sie gibt den berechneten Wert "delta" zurück.

 * Die "CalculateDelta" Klasse spielt eine wichtige Rolle bei der Größenanpassung und Positionierung von Spielobjekten im Raster. 
 * Sie sorgt dafür, dass die Elemente im Spiel in Abhängigkeit von den Rasterdistanzen und den Anforderungen der Anwendung 
 * angepasst werden.
 */
public class CalculateDelta {
	
	/**
	 * Die Methode "calculateDelta" berechnet den Wert "delta" basierend auf den Distanzen "XDistance" und "YDistance", die aus
	 * dem Objekt "gridValues" abgerufen werden. "Delta" entspricht der kleineren der beiden Distanzen.
	 *
	 * @return Der berechnete Wert "delta", der die kleinere der beiden Distanzen darstellt.
	 */
	public int calculateDelta(CalculateGrid gridValues) {
	    int delta = 0;

	    // Vergleiche die Distanzen "XDistance" und "YDistance" aus "gridValues"
	    if (gridValues.getXDistance() < gridValues.getYDistance()) {
	        // Wenn "XDistance" kleiner ist, setze "delta" auf den Wert von "XDistance"
	        delta = gridValues.getXDistance();
	    } else {
	        // Andernfalls, wenn "YDistance" kleiner ist oder gleich, setze "delta" auf den Wert von "YDistance"
	        delta = gridValues.getYDistance();
	    }

	    // Gib den berechneten Wert "delta" zurück
	    return delta;
	}
	


}
