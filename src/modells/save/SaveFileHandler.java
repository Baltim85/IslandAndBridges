package modells.save;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import Modell.CreateBridges;
import Modell.Island;

/**
 * Die "SaveFileHandler" Klasse ist verantwortlich für das Schreiben des aktuellen Spielstands in eine Datei.
 * Sie enthält Methoden zum Erstellen des Dateiinhalts und zum Schreiben der Datei. Dies ermöglicht es, den 
 * Spielstand, einschließlich Spielfeldgröße, Inselinformationen und Brückeninformationen, in einer speicherbaren 
 * Datei zu speichern.
 * 
 * Attribute:
 * - HEADER (String): Ein Konstantenattribut, das den Header-Text für die Datei speichert.
 * - ISLAND_HEADER (String): Ein Konstantenattribut, das den Header-Text für die Inselinformationen speichert.
 * - BRIDGES_HEADER (String): Ein Konstantenattribut, das den Header-Text für die Brückeninformationen speichert.
 * 
 * Konstruktor:
 * - SaveFileHandler(): Dieser Konstruktor initialisiert die Konstantenattribute für die Header-Texte.
 * 
 * Methoden:
 * - saveToFile(File selectedFile, int width, int height, int islands, ArrayList<Island> listOfIslands, 
 *  ArrayList<CreateBridges> listOfBridges): Diese Methode schreibt den aktuellen Spielstand in die ausgewählte Datei.
 *  Sie akzeptiert Informationen zur Spielfeldgröße, den Inseln und den Brücken sowie die ausgewählte Datei. 
 *  Die Methode gibt "true" zurück, wenn das Schreiben erfolgreich war, andernfalls "false".
 * - appendHeader(StringBuilder content): Private Methode, die den Header-Text zum übergebenen StringBuilder 'content' hinzufügt.
 * - appendBridgesInfo(StringBuilder content, CreateBridges bridge): Private Methode, die Informationen zu einer 
 * Brücke zum übergebenen StringBuilder 'content' hinzufügt.
 * - appendInfoAndIslandHeader(StringBuilder content, int width, int height, int islands): Private Methode, 
 * die Informationen zur Spielfeldgröße und zur Anzahl der Inseln zum übergebenen StringBuilder 'content' hinzufügt.
 * - appendIslandInfo(StringBuilder content, Island island): Private Methode, die Informationen zu einer einzelnen 
 * Insel zum übergebenen StringBuilder 'content' hinzufügt.
 * - writeToFile(File selectedFile, String fileContent): Private Methode, die den übergebenen Dateiinhalt in die 
 * ausgewählte Datei schreibt. Sie kann eine IOException werfen.
 * 
 * Die "SaveFileHandler" Klasse spielt eine Schlüsselrolle beim Speichern des Spielstands in einer Datei und ist dafür 
 * verantwortlich, die richtigen Informationen in das Dateiformat zu konvertieren und sie in die ausgewählte Datei zu schreiben.
 */
class SaveFileHandler {
	// Konstanten für die Header-Texte
	private static final String HEADER = "FIELD\n# Width x Height | Number of islands\n";
	private static final String ISLAND_HEADER = "ISLANDS\n + # { ( Column, Row | Number of bridges ) } \n# Columns and rows are 0 indexed!\n";
	private static final String BRIDGES_HEADER = "\nBRIDGES\n# { ( Start Index, End Index | Double Bridge ) } \n";

	// Konstante für die Anzahl der Brücken, die als doppelte Brücken betrachtet werden


	/**
	 * Speichert den aktuellen Spielstand in eine Datei.
	 *
	 * @param selectedFile  Die ausgewählte Datei, in die der Spielstand gespeichert wird.
	 * @param width         Die Breite des Spielfeldes.
	 * @param height        Die Höhe des Spielfeldes.
	 * @param islands       Die Anzahl der Inseln auf dem Spielfeld.
	 * @param listOfIslands Eine Liste mit Informationen zu den Inseln auf dem Spielfeld.
	 * @param listOfBridges Eine Liste mit Informationen zu den Brücken auf dem Spielfeld.
	 * @return true, wenn das Speichern erfolgreich war, andernfalls false.
	 */
	public boolean saveToFile(File selectedFile, int width, int height, int islands, ArrayList<Island> listOfIslands, ArrayList<CreateBridges> listOfBridges) {
		try {
			// Ein StringBuilder zum Zusammenstellen des Dateiinhalts
			StringBuilder fileContent = new StringBuilder();

			// Füge den Header hinzu
			appendHeader(fileContent);

			// Füge Informationen über das Spielfeld und die Inseln hinzu
			appendInfoAndIslandHeader(fileContent, width, height, islands);

			int i = 0;
			// Iteriere über die Inseln und füge sie dem Dateiinhalt hinzu
			for (Island island : listOfIslands) {
				appendIslandInfo(fileContent, island);
				island.setId(i);
				i++;
			}

			// Füge den Brücken-Header hinzu
			fileContent.append(BRIDGES_HEADER);

			// Iteriere über die Brücken und füge sie dem Dateiinhalt hinzu
			if(listOfBridges != null) {
				for (CreateBridges bridges : listOfBridges) {
					appendBridgesInfo(fileContent, bridges);
				}
			}

			// Schreibe den Dateiinhalt in die ausgewählte Datei
			writeToFile(selectedFile, fileContent.toString());
			return true;
		} catch (IOException e) {
			// Behandle eine mögliche IOException, falls das Schreiben in die Datei fehlschlägt
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Fügt den Header zur Dateiinformation dem StringBuilder 'content' hinzu.
	 * 
	 * @param content Der StringBuilder, in den der Header eingefügt wird.
	 */
	private void appendHeader(StringBuilder content) {
		// Füge den vordefinierten Header (HEADER) dem 'content'-StringBuilder hinzu.
		content.append(HEADER);
	}

	/**
	 * Fügt Informationen zu einer Brücke dem StringBuilder 'content' hinzu.
	 * 
	 * @param content Der StringBuilder, in den die Brückeninformation eingefügt wird.
	 * @param bridge  Die Brücke, deren Informationen eingefügt werden sollen.
	 */
	private void appendBridgesInfo(StringBuilder content, CreateBridges bridge) {
		// Initialisiere Variablen für die IDs der beiden beteiligten Inseln und die Anzahl der Brücken.
		int firstID = 0;
		int secondID = 0;
		int doubleBridge = 2;
		int bridgesNumber = bridge.getNumberOfBridges();
		String bridgeInfo = "";

		// Bestimme die Reihenfolge der Inseln basierend auf ihren IDs, um die Ausgabe einheitlich zu gestalten.
		if (bridge.getFirstIslandID() < bridge.getSecondIslandID()) {
			firstID = bridge.getFirstIslandID();
			secondID = bridge.getSecondIslandID();
		} else {
			firstID = bridge.getSecondIslandID();
			secondID = bridge.getFirstIslandID();
		}

		// Bestimme den Text für die Brückeninformation (True für doppelte Brücke, sonst False).
		if (bridgesNumber == doubleBridge)
			bridgeInfo = "True";
		else
			bridgeInfo = "False";

		// Erstelle den Text für die Brückeninformation und füge ihn zum 'content'-StringBuilder hinzu.
		String bridgeInfoText = "( " + firstID + ", " + secondID + " | " + bridgeInfo + " )\n";
		content.append(bridgeInfoText);
	}

	/**
	 * Fügt Informationen zur Spielfeldgröße und zur Anzahl der Inseln dem StringBuilder 'content' hinzu.
	 * 
	 * @param content Der StringBuilder, in den die Informationen eingefügt werden.
	 * @param width   Die Breite des Spielfeldes.
	 * @param height  Die Höhe des Spielfeldes.
	 * @param islands Die Anzahl der Inseln auf dem Spielfeld.
	 */
	private void appendInfoAndIslandHeader(StringBuilder content, int width, int height, int islands) {
		// Erstelle eine Zeichenfolge, die die Spielfeldgröße und die Anzahl der Inseln enthält.
		String infoText = width + " x " + height + " | " + islands + "\n\n";

		// Füge die Zeichenfolge zum 'content'-StringBuilder hinzu.
		content.append(infoText);

		// Füge die Überschrift für den Inselabschnitt hinzu.
		content.append(ISLAND_HEADER);
	}

	/**
	 * Fügt Informationen zu einer einzelnen Insel dem StringBuilder 'content' hinzu.
	 * 
	 * @param content Der StringBuilder, in den die Informationen eingefügt werden.
	 * @param island  Die Insel, deren Informationen eingefügt werden sollen.
	 */
	private void appendIslandInfo(StringBuilder content, Island island) {
		// Extrahiere die x- und y-Koordinaten der Insel.
		int x = island.getX() - 1;
		int y = island.getY() - 1;

		// Ermittle die Anzahl der Brücken, die mit dieser Insel verbunden sind.
		int bridges = island.getBridgeCount();

		// Erstelle eine Zeichenfolge, die die Inselkoordinaten und die Brückenanzahl enthält.
		String islandInfo = "( " + x + ", " + y + " | " + bridges + " )\n";

		// Füge die Zeichenfolge zum 'content'-StringBuilder hinzu.
		content.append(islandInfo);
	}

	/**
	 * Schreibt den übergebenen Dateiinhalt in die angegebene Datei.
	 * 
	 * @param selectedFile Die Zieldatei, in die der Inhalt geschrieben wird.
	 * @param fileContent Der zu schreibende Inhalt als Zeichenfolge.
	 * @throws IOException Wenn ein Fehler beim Schreiben in die Datei auftritt.
	 */
	private void writeToFile(File selectedFile, String fileContent) throws IOException {
		// Erzeugt ein FileOutputStream, um die Datei zu schreiben.
		// Ein FileOutputStream ist für binäre Daten geeignet.
		// 'fos' wird automatisch geschlossen, wenn der try-Block verlassen wird.
		try (FileOutputStream fos = new FileOutputStream(selectedFile);
				// Erzeugt ein BufferedOutputStream, um Pufferung für effizientes Schreiben hinzuzufügen.
				// 'bos' wird ebenfalls automatisch geschlossen.
				BufferedOutputStream bos = new BufferedOutputStream(fos)) {
			// Konvertiert die Zeichenfolge 'fileContent' in ein Byte-Array und schreibt es in die Datei.
			bos.write(fileContent.getBytes());
		}
	}
}
