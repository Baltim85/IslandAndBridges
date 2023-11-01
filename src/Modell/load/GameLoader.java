package Modell.load;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import modells.GameData.GameData;




/**
 * Die Klasse "GameLoader" ist verantwortlich für das Laden von Spielinformationen aus einer Datei, deren Analyse und die 
 * Erstellung eines GameData-Objekts, das die geladenen Daten enthält. Sie stellt Methoden zur Verfügung, um das Laden von 
 * Spielständen zu ermöglichen, die aus Spielfelddaten wie Breite, Höhe, Anzahl der Inseln, Brücken und Koordinaten der Inseln 
 * bestehen. Diese Klasse sorgt dafür, dass die geladenen Daten gültig und vollständig sind.

 * Attribute:
 * - width: Die Breite des Spielfelds.
 * - height: Die Höhe des Spielfelds.
 * - islands: Die Anzahl der Inseln im Spiel.
 * - bridgeList: Eine Liste von Brücken im Spielfeld, wobei jede Brücke durch ihre Start- und Endkoordinaten repräsentiert wird.
 * - islandsList: Eine Liste von Koordinaten (Spalten und Zeilen) der Inseln im Spielfeld.

 * Methoden:
 * - loadGame(): Diese Methode lädt Spielinformationen aus einer Datei, analysiert sie und gibt ein GameData-Objekt zurück, 
 * das die geladenen Daten enthält. Sie verwendet dazu einen FileLoader, um die Dateiinhalte zu laden, und einen GameDataParser, 
 * um die Dateiinhalte zu analysieren. Wenn die geladenen Daten gültig sind, wird ein GameData-Objekt erstellt und zurückgegeben.

 * - isValid(GameData gameData): Diese private Methode überprüft die Gültigkeit der übergebenen Spielfelddaten. Sie stellt sicher,
 *  dass die Daten gültig sind und ein GameData-Objekt nicht fehlerhaft ist. Wenn die Daten ungültig sind, wird eine Fehlermeldung 
 *  angezeigt, und die Methode gibt "false" zurück.

 * - setData(GameData gameData): Diese private Methode setzt die Spielfelddaten der aktuellen Klasse auf Basis der übergebenen 
 * Spielfelddaten. Sie extrahiert die Spielfeldgröße, die Brücken und die Inseln und aktualisiert die Attribute der Klasse.

 * - getIslandsList(): Diese Methode gibt die Liste der Inseln im Spielfeld zurück.

 * - setIslandsList(List<int[]> islandsList): Diese Methode legt die Liste der Inseln im Spielfeld fest.

 * - getWidth(): Diese Methode gibt die Breite des Spielfelds zurück.

 * - setWidth(int width): Diese Methode legt die Breite des Spielfelds fest.

 * - getHeight(): Diese Methode gibt die Höhe des Spielfelds zurück.

 * - setHeight(int height): Diese Methode legt die Höhe des Spielfelds fest.

 * - getIslands(): Diese Methode gibt die Anzahl der Inseln auf dem Spielfeld zurück.

 * - setIslands(int islands): Diese Methode legt die Anzahl der Inseln auf dem Spielfeld fest.

 * - getBridgeList(): Diese Methode gibt die Liste der Brücken im Spielfeld zurück.

 * - setBridgeList(List<int[]> bridgeList): Diese Methode legt die Liste der Brücken im Spielfeld fest.

 * Die Klasse "GameLoader" ermöglicht das Laden von Spielständen aus Dateien und stellt sicher, dass die geladenen Daten 
 * konsistent und gültig sind. Sie erleichtert die Wiederaufnahme von gespeicherten Spielen und unterstützt die 
 * Funktionalität des Spiels.
 */
public class GameLoader {

	// Die Breite des Spielfelds
	private int width;

	// Die Höhe des Spielfelds
	private int height;

	// Die Anzahl der Inseln im Spiel
	private int islands;

	// Eine Liste von Brücken im Spielfeld, wobei jede Brücke durch ihre Start- und Endkoordinaten repräsentiert wird
	private List<int[]> bridgeList = new ArrayList<>();

	// Eine Liste von Koordinaten (Spalten und Zeilen) der Inseln im Spielfeld
	private List<int[]> islandsList = new ArrayList<>();


	/**
	 * Diese Methode lädt Spielinformationen aus einer Datei, analysiert sie und gibt ein GameData-Objekt zurück, das die 
	 * geladenen Daten enthält.
	 * @param data 
	 *
	 * @return Ein GameData-Objekt, das die geladenen Spielfelddaten enthält, oder null, wenn ein Fehler auftritt.
	 */
	public GameDataForLoad loadGame(GameData data) {
		// Ein Objekt der FileLoader-Klasse erstellen, um die Datei zu laden
		FileLoader fileLoader = new FileLoader();

		// Die Dateiinhalte aus der Datei laden
		List<String> fileContents = fileLoader.loadFile(data);

		// Überprüfen, ob die Datei erfolgreich geladen wurde
		if (fileContents == null) {
			return null;
		}

		// Debug-Ausgabe der geladenen Dateiinhalte (kann für Tests verwendet werden)
		System.out.println(fileContents);

		// Ein Objekt der GameDataParser-Klasse erstellen, um die Dateiinhalte zu analysieren
		GameDataParser dataParser = new GameDataParser();

		// Die Dateiinhalte analysieren und ein GameData-Objekt erstellen
		GameDataForLoad gameData = dataParser.parseGameData(fileContents);

		if(!isValid(gameData)) {
			return null;
		}

		// Die Spielfeldgröße und Informationen in der aktuellen Klasse aktualisieren
		setData(gameData);


		// Das GameData-Objekt mit den geladenen Daten zurückgeben
		return gameData;
	}

	/**
	 * Überprüft die Gültigkeit der übergebenen Spielfelddaten.
	 *
	 * @param gameData Die zu überprüfenden Spielfelddaten.
	 * @return true, wenn die Daten gültig sind, andernfalls false.
	 */
	private boolean isValid(GameDataForLoad gameData) {
		if (gameData != null && !gameData.isValid()) {
			// Wenn die Daten ungültig sind, wird eine Fehlermeldung angezeigt und null zurückgegeben
			JOptionPane.showMessageDialog(null, "Ungültige Spielfelddaten!", "Ladevorgang abgebrochen", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	/**
	 * Setzt die Spielfelddaten auf Basis der übergebenen Spielfelddaten.
	 *
	 * @param gameData Die Spielfelddaten, die zur Aktualisierung verwendet werden.
	 */
	private void setData(GameDataForLoad gameData) {
		setWidth(gameData.getWidth());
		setHeight(gameData.getHeight());
		setIslands(gameData.getIslands());
		setIslandsList(gameData.getIslandsList());
		setBridgeList(gameData.getBridgeList());
	}


	/**
	 * Gibt die Liste der Inseln im Spielfeld zurück.
	 *
	 * @return Die Liste der Inseln als Arrays.
	 */
	public List<int[]> getIslandsList() {
		return islandsList;
	}

	/**
	 * Legt die Liste der Inseln im Spielfeld fest.
	 *
	 * @param islandsList Die Liste der Inseln als Arrays.
	 */
	public void setIslandsList(List<int[]> islandsList) {
		this.islandsList = islandsList;
	}

	/**
	 * Gibt die Breite des Spielfelds zurück.
	 *
	 * @return Die Breite des Spielfelds.
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Legt die Breite des Spielfelds fest.
	 *
	 * @param width Die Breite des Spielfelds.
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * Gibt die Höhe des Spielfelds zurück.
	 *
	 * @return Die Höhe des Spielfelds.
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Legt die Höhe des Spielfelds fest.
	 *
	 * @param height Die Höhe des Spielfelds.
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * Gibt die Anzahl der Inseln auf dem Spielfeld zurück.
	 *
	 * @return Die Anzahl der Inseln auf dem Spielfeld.
	 */
	public int getIslands() {
		return islands;
	}

	/**
	 * Legt die Anzahl der Inseln auf dem Spielfeld fest.
	 *
	 * @param islands Die Anzahl der Inseln auf dem Spielfeld.
	 */
	public void setIslands(int islands) {
		this.islands = islands;
	}

	/**
	 * Gibt die Liste der Brücken im Spielfeld zurück.
	 *
	 * @return Die Liste der Brücken als Arrays.
	 */
	public List<int[]> getBridgeList() {
		return bridgeList;
	}

	/**
	 * Legt die Liste der Brücken im Spielfeld fest.
	 *
	 * @param bridgeList Die Liste der Brücken als Arrays.
	 */
	public void setBridgeList(List<int[]> bridgeList) {
		this.bridgeList = bridgeList;
	}


}