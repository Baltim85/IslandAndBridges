package Modell.load;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * Die FileLoader-Klasse ist verantwortlich für das Laden von Spielfelddaten aus einer Datei.
 * Sie verwendet einen Dateiauswahldialog, um den Benutzer aufzufordern, eine Datei auszuwählen, und liest dann die Inhalte dieser Datei.
 */
public class FileLoader {

    /**
     * Lädt die Inhalte einer Datei aus einer vom Benutzer ausgewählten Datei.
     *
     * @return Eine Liste von Zeichenketten, die die Zeilen aus der Datei darstellen, oder null, wenn der Ladevorgang abgebrochen wurde oder ein Fehler auftrat.
     */
    public List<String> loadFile() {
        // Einen Dateiauswahldialog erstellen
        JFileChooser fileChooser = new JFileChooser();

        // Den Benutzer auffordern, eine Datei auszuwählen
        int returnValue = fileChooser.showOpenDialog(null);

        // Überprüfen, ob der Benutzer eine Datei ausgewählt hat
        if (returnValue != JFileChooser.APPROVE_OPTION) {
            // Wenn der Benutzer den Vorgang abgebrochen hat, wird eine Nachricht ausgegeben, und null wird zurückgegeben
        	JOptionPane.showMessageDialog(null, "Ladevorgang abgebrochen!", "Laden abgebrochen", JOptionPane.ERROR_MESSAGE);
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
