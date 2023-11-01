package Modell.load;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;

import modells.GameData.GameData;

/**
 * Die "FileLoader" Klasse ist verantwortlich für das Laden des Inhalts einer ausgewählten Datei und die Rückgabe dieses 
 * Inhalts als Liste von Zeichenfolgen. Sie ermöglicht es dem Benutzer, eine Datei auszuwählen, liest ihren Inhalt zeilenweise 
 * und stellt die Daten zur weiteren Verarbeitung zur Verfügung.

 * Attribute:
 * - Keine.

 * Konstruktor:
 * - Keine.

 * Methoden:
 * - loadFile(GameData data): Diese Methode lädt den Inhalt einer ausgewählten Datei und gibt ihn als Liste von Zeichenfolgen 
 * zurück. Sie akzeptiert ein Objekt der Klasse "GameData" für die Verarbeitung und Prüfung der Datei. Die Methode öffnet einen 
 * Dateiauswahldialog, ermöglicht dem Benutzer die Auswahl einer Datei und liest dann den Inhalt dieser Datei zeilenweise. 
 * Die Liste der Zeichenfolgen wird zurückgegeben. Falls ein Fehler auftritt oder der Benutzer den Ladevorgang abbricht, wird 
 * die Methode null zurückgeben.

 * Die "FileLoader" Klasse spielt eine wichtige Rolle beim Laden von Dateiinhalten und ermöglicht es, externe Daten in einer 
 * Anwendung zu verwenden oder zu verarbeiten. Die Klasse handhabt die Interaktion mit dem Benutzer, um eine Datei auszuwählen, 
 * und stellt den Inhalt der Datei zur weiteren Verwendung zur Verfügung.
 */
public class FileLoader {

    /**
     * Lädt den Inhalt einer ausgewählten Datei und gibt ihn als Liste von Zeichenfolgen zurück.
     *
     * @param data Ein Objekt der Klasse `GameData`, das für die Verarbeitung und Prüfung der Datei geladen wird.
     * @return Eine Liste von Zeichenfolgen, die den Inhalt der geladenen Datei repräsentieren.
     */
    public List<String> loadFile(GameData data) {
        // Einen Dateiauswahldialog erstellen
        JFileChooser fileChooser = new JFileChooser();

        // Den Benutzer auffordern, eine Datei auszuwählen
        int returnValue = fileChooser.showOpenDialog(null);

        // Überprüfen, ob der Benutzer eine Datei ausgewählt hat und ob ein Fehler beim Laden aufgetreten ist
        if (!data.getDialogController().errorLoading(returnValue)) {
            return null;
        }

        // Die ausgewählte Datei abrufen
        File selectedFile = fileChooser.getSelectedFile();

        // Eine Liste für die Dateiinhalte erstellen
        List<String> fileContents = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(selectedFile))) {
            // Zeilenweise die Datei lesen und die Zeilen zur Liste hinzufügen
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                fileContents.add(line);
            }
        } catch (IOException e) {
            // Bei einem Fehler wird die Fehlermeldung auf der Konsole ausgegeben
            e.printStackTrace();
        }

        // Die Liste der Dateiinhalte zurückgeben
        return fileContents;
    }
    

}
