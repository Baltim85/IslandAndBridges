package Modell.load;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;


/**
 * Die GameLoader-Klasse ist verantwortlich für das Laden von Spielfelddaten aus einer Datei, deren Analyse und die Bereitstellung dieser Daten als GameData-Objekt.
 * Diese Klasse enthält auch Methoden zum Verwalten der Spielfeldinformationen, wie Breite, Höhe, Anzahl der Inseln, Brücken und Inselnliste.
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
	 * Diese Methode lädt Spielinformationen aus einer Datei, analysiert sie und gibt ein GameData-Objekt zurück, das die geladenen Daten enthält.
	 *
	 * @return Ein GameData-Objekt, das die geladenen Spielfelddaten enthält, oder null, wenn ein Fehler auftritt.
	 */
	public GameData loadGame() {
		// Ein Objekt der FileLoader-Klasse erstellen, um die Datei zu laden
		FileLoader fileLoader = new FileLoader();

		// Die Dateiinhalte aus der Datei laden
		List<String> fileContents = fileLoader.loadFile();

		// Überprüfen, ob die Datei erfolgreich geladen wurde
		if (fileContents == null) {
			return null;
		}

		// Debug-Ausgabe der geladenen Dateiinhalte (kann für Tests verwendet werden)
		System.out.println(fileContents);

		// Ein Objekt der GameDataParser-Klasse erstellen, um die Dateiinhalte zu analysieren
		GameDataParser dataParser = new GameDataParser();

		// Die Dateiinhalte analysieren und ein GameData-Objekt erstellen
		GameData gameData = dataParser.parseGameData(fileContents);

		// Überprüfen, ob die geladenen Spielfelddaten gültig sind
		if (gameData != null && !gameData.isValid()) {
			// Wenn die Daten ungültig sind, wird eine Fehlermeldung angezeigt und null zurückgegeben
			JOptionPane.showMessageDialog(null, "Ungültige Spielfelddaten!", "Ladevorgang abgebrochen", JOptionPane.ERROR_MESSAGE);
			return null;
		}

		// Die Spielfeldgröße und Informationen in der aktuellen Klasse aktualisieren
		setWidth(gameData.getWidth());
		setHeight(gameData.getHeight());
		setIslands(gameData.getIslands());
		setIslandsList(gameData.getIslandsList());
		setBridgeList(gameData.getBridgeList());

		// Das GameData-Objekt mit den geladenen Daten zurückgeben
		return gameData;
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