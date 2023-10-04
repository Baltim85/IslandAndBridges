package Modell.load;

import java.util.List;

import javax.swing.JOptionPane;



public class GameData {

	/**
	 * Die Breite des Spielfelds, die die Anzahl der Spalten auf dem Spielfeld repräsentiert.
	 */
	private int width;

	/**
	 * Die Höhe des Spielfelds, die die Anzahl der Zeilen auf dem Spielfeld repräsentiert.
	 */
	private int height;

	/**
	 * Die Anzahl der Inseln im Spiel, die auf dem Spielfeld vorhanden sind.
	 */
	private int islands;

	/**
	 * Eine Liste von Koordinaten (Spalten und Zeilen) der Inseln auf dem Spielfeld.
	 * Jedes Element in der Liste ist ein int-Array, das die Koordinaten der Insel repräsentiert.
	 */
	private List<int[]> islandsList;

	/**
	 * Eine Liste von Brücken auf dem Spielfeld, wobei jede Brücke durch ihre Start- und Endkoordinaten repräsentiert wird.
	 * Jedes Element in der Liste ist ein int-Array, das die Start- und Endkoordinaten der Brücke enthält.
	 */
	private List<int[]> bridgeList;




	/**
	 * Überprüft die Gültigkeit der geladenen Spielfelddaten.
	 *
	 * @return true, wenn die Spielfelddaten gültig sind, andernfalls false.
	 */
	public boolean isValid() {
		int maxWidth = 25;   // Maximale Breite des Spielfelds
		int maxHeight = 25;  // Maximale Höhe des Spielfelds

		// Überprüfe die Spielfeldgröße und die Anzahl der Inseln
		if ((getWidth() < 4 || getWidth() > maxWidth || getHeight() < 4 || getHeight() > maxHeight)
				|| (getIslands() < 2)) {
			// Wenn die Spielfeldgröße ungültig ist oder die Anzahl der Inseln zu gering ist, wird eine Fehlermeldung angezeigt
			JOptionPane.showMessageDialog(null, "Ungültige Spielfeldinformation!\n Ladevorgang abgebrochen", "Laden abgebrochen", JOptionPane.ERROR_MESSAGE);

			return false; // Die Spielfelddaten sind ungültig
		}

		// Überprüfe die Gültigkeit der Inselinformationen
		if (!isIslandValid()) {
			// Wenn die Inselinformationen ungültig sind, wird eine Fehlermeldung angezeigt
			JOptionPane.showMessageDialog(null, "Ungültige Inselinformationen!", "Ladevorgang abgebrochen", JOptionPane.ERROR_MESSAGE);
			return false; // Die Spielfelddaten sind ungültig
		}

		// Überprüfe die Gültigkeit der Brückeninformationen
		if (!isBridgeValid()) {
			// Wenn die Brückeninformationen ungültig sind, wird eine Fehlermeldung angezeigt
			JOptionPane.showMessageDialog(null, "Ungültige Brückeninformationen!", "Ladevorgang abgebrochen", JOptionPane.ERROR_MESSAGE);
			return false; // Die Spielfelddaten sind ungültig
		}

		// Alle Überprüfungen wurden bestanden, die Spielfelddaten sind gültig
		return true;
	}



	/**
	 * Überprüft, ob die Informationen über die Inseln auf dem Spielfeld gültig sind.
	 *
	 * @return true, wenn die Inselinformationen gültig sind, andernfalls false.
	 */
	private boolean isIslandValid() {
		// Iteriere durch die Liste der Inselinformationen
		for (int[] islandInfo : getIslandsList()) {
			int x = islandInfo[0];      // X-Koordinate der Insel
			int y = islandInfo[1];      // Y-Koordinate der Insel
			int bridge = islandInfo[2]; // Anzahl der Brücken, die mit der Insel verbunden sind

			// Überprüfe, ob die Inselkoordinaten innerhalb des Spielfelds liegen
			if (x > getWidth() || y > getHeight() || bridge > 8 || x < 0 || y < 0) {
				// Die Inselinformationen sind ungültig, daher wird false zurückgegeben
				return false;
			}
		}
		// Alle Inselinformationen sind gültig
		return true;
	}

	/**
	 * Überprüft, ob die Informationen über die Brücken auf dem Spielfeld gültig sind.
	 *
	 * @return true, wenn die Brückeninformationen gültig sind, andernfalls false.
	 */
	private boolean isBridgeValid() {
		// Iteriere durch die Liste der Brückeninformationen
		for (int[] bridgeInfo : getBridgeList()) {
			int firstID = bridgeInfo[0];  // ID der ersten mit der Brücke verbundenen Insel
			int secondID = bridgeInfo[1]; // ID der zweiten mit der Brücke verbundenen Insel

			// Überprüfe, ob die IDs der verbundenen Inseln gültig sind
			if (firstID < 0 || secondID < 0 || firstID >= getIslands() || secondID >= getIslands()) {
				// Die Brückeninformationen sind ungültig, daher wird false zurückgegeben
				return false;
			}
		}

		// Alle Brückeninformationen sind gültig
		return true;
	}



	/**
	 * Gibt die Breite des Spielfelds zurück, die die Anzahl der Spalten auf dem Spielfeld repräsentiert.
	 *
	 * @return Die Breite des Spielfelds.
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Legt die Breite des Spielfelds fest, die die Anzahl der Spalten auf dem Spielfeld repräsentiert.
	 *
	 * @param width Die zu setzende Breite des Spielfelds.
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * Gibt die Höhe des Spielfelds zurück, die die Anzahl der Zeilen auf dem Spielfeld repräsentiert.
	 *
	 * @return Die Höhe des Spielfelds.
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Legt die Höhe des Spielfelds fest, die die Anzahl der Zeilen auf dem Spielfeld repräsentiert.
	 *
	 * @param height Die zu setzende Höhe des Spielfelds.
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * Gibt die Anzahl der Inseln im Spiel zurück, die auf dem Spielfeld vorhanden sind.
	 *
	 * @return Die Anzahl der Inseln im Spiel.
	 */
	public int getIslands() {
		return islands;
	}

	/**
	 * Legt die Anzahl der Inseln im Spiel fest, die auf dem Spielfeld vorhanden sind.
	 *
	 * @param islands Die zu setzende Anzahl der Inseln im Spiel.
	 */
	public void setIslands(int islands) {
		this.islands = islands;
	}

	/**
	 * Gibt die Liste von Koordinaten (Spalten und Zeilen) der Inseln auf dem Spielfeld zurück.
	 *
	 * @return Die Liste der Inseln als Arrays von Koordinaten.
	 */
	public List<int[]> getIslandsList() {
		return islandsList;
	}

	/**
	 * Legt die Liste von Koordinaten (Spalten und Zeilen) der Inseln auf dem Spielfeld fest.
	 *
	 * @param islandsList Die zu setzende Liste der Inseln als Arrays von Koordinaten.
	 */
	public void setIslandsList(List<int[]> islandsList) {
		this.islandsList = islandsList;
	}

	/**
	 * Gibt die Liste von Brücken auf dem Spielfeld zurück, wobei jede Brücke durch ihre Start- und Endkoordinaten repräsentiert wird.
	 *
	 * @return Die Liste der Brücken als Arrays von Koordinaten.
	 */
	public List<int[]> getBridgeList() {
		return bridgeList;
	}

	/**
	 * Legt die Liste von Brücken auf dem Spielfeld fest, wobei jede Brücke durch ihre Start- und Endkoordinaten repräsentiert wird.
	 *
	 * @param bridgeList Die zu setzende Liste der Brücken als Arrays von Koordinaten.
	 */
	public void setBridgeList(List<int[]> bridgeList) {
		this.bridgeList = bridgeList;
	}

}