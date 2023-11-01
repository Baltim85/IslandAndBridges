package modells.CreateGame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import modells.GameData.GameData;

/**
 * Die Klasse "AddMouseListener" ermöglicht das Hinzufügen eines MouseListeners zur Zeichenfläche, um Mausklicks zu verarbeiten. 
 * Sie bietet eine Methode, um einen MouseListener zu registrieren und Mausklicks auf der Zeichenfläche zu verarbeiten. 

 * Konstruktor:
 * - AddMouseListener(GameData data): Dieser Konstruktor erstellt eine Instanz der "AddMouseListener"-Klasse und initialisiert 
 * sie mit den bereitgestellten Spieldaten.

 * Methoden:
 * - registerMouseListener(): Diese Methode registriert einen MouseListener für die Zeichenfläche, um Mausklicks zu verarbeiten. 
 * Sie fügt einen MouseAdapter zur Zeichenfläche hinzu, der Mausklicks verarbeitet und die Koordinaten des Mausklicks an 
 * den BridgeController weitergibt, um entsprechende Aktionen auszuführen.
 */
public class AddMouseListener {
    private GameData data;

    /**
     * Erstellt eine Instanz von `AddMouseListener` mit den bereitgestellten Spieldaten.
     *
     * @param data Die Spieldaten, auf denen die Instanz arbeiten wird.
     */
    public AddMouseListener(GameData data) {
        this.data = data;
    }

    /**
     * Registriert einen MouseListener für die Zeichenfläche, um Mausklicks zu verarbeiten.
     */
    public void registerMouseListener() {
        data.setControllerExist(true);

        // Fügen Sie einen MouseAdapter zur Zeichenfläche hinzu, um Mausklicks zu verarbeiten
        data.getBridges().getDraw().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Koordinaten des Mausklicks abrufen
                int x = e.getX();
                int y = e.getY();

                // Den BridgeController aufrufen und die Mausklick-Koordinaten übergeben
                data.getBridgeC().handleMouseClick(x, y, e);
            }
        });
    }
}

