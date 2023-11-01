package modells.CreateGame;

import java.awt.BorderLayout;

import modells.GameData.GameData;

/**
 * Die Klasse "ClearGrid" ist verantwortlich für das Entfernen aller Komponenten aus der Zeichenfläche und das Hinzufügen des Gitters. 
 * Nachdem alle Komponenten aus der Zeichenfläche entfernt wurden, wird das Gitter zur Zeichenfläche in der Mitte 
 * (BorderLayout.CENTER) hinzugefügt. Diese Klasse dient dazu, die Zeichenfläche zu löschen und das Gitter zu initialisieren, 
 * um Änderungen am Spielfeld anzuzeigen.
 *
 * Konstruktor:
 * - ClearGrid(GameData data): Dieser Konstruktor erstellt eine Instanz der "ClearGrid" Klasse und initialisiert sie mit den 
 * bereitgestellten Spieldaten. Beim Erstellen der Instanz wird auch die Methode "clearAndAddGrid()" aufgerufen, um das Gitter 
 * zur Zeichenfläche hinzuzufügen.
 *
 * Methode:
 *
 * - clearAndAddGrid(): Diese Methode entfernt alle vorhandenen Komponenten aus der Zeichenfläche und fügt das Gitter zur 
 * Zeichenfläche hinzu. Anschließend wird die Anzeige aktualisiert, um die vorgenommenen Änderungen sichtbar zu machen.
 */
public class ClearGrid {

    private GameData data;

    /**
     * Erstellt eine Instanz der "ClearGrid" Klasse und initialisiert sie mit den bereitgestellten Spieldaten.
     *
     * @param data Die Spieldaten, auf denen die Instanz arbeiten wird.
     */
    public ClearGrid(GameData data) {
        this.data = data;
        clearAndAddGrid();
    }

    /**
     * Entfernt alle Komponenten aus der Zeichenfläche und fügt das Gitter hinzu. Aktualisiert dann die Anzeige, 
     * um die Änderungen sichtbar zu machen.
     */
    public void clearAndAddGrid() {
        // Entferne alle vorhandenen Komponenten aus der Zeichenfläche
        data.getBridges().getDraw().removeAll();

        // Füge das Gitter zur Zeichenfläche in der Mitte (BorderLayout.CENTER) hinzu
        data.getBridges().getDraw().add(data.getGrid(), BorderLayout.CENTER);

        // Aktualisiere die Anzeige, um die Änderungen sichtbar zu machen
        data.getBridges().getDraw().revalidate();
    }
}

