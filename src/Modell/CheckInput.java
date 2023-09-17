package Modell;

import javax.swing.JOptionPane;

/**
 * Die Klasse CheckInput bietet Methoden zum Überprüfen der Benutzereingaben für die
 * Breite und Höhe eines Rasters sowie die Anzahl der Inseln. Sie stellt sicher, dass die
 * Eingaben innerhalb bestimmter Grenzen liegen und den Anforderungen entsprechen.
 */
public class CheckInput {
    // Mindest- und Höchstwerte für die Breite und Höhe des Rasters
    private final int minValue = 4;
    private final int maxValue = 25;

    // Fehlermeldungen
    private String message = "Die Höhe und / oder Breite des Rätsel muss zwischen " + minValue + " und " + maxValue + " liegen!\n"
            + "Bitte korrigieren Sie die Eingabe.";
    //private String nan = "Sie müssen eine Zahl zwischen: " + minValue  + " und " + maxValue + " eingeben!";
    private String maxIslandCount = "Die Anzahl der Inseln muss zwischen 2 und ";
    private String end = " liegen!";

    /**
     * Überprüft die Benutzereingaben für Breite und Höhe des Rasters.
     *
     * @param width  Die Breite des Rasters.
     * @param height Die Höhe des Rasters.
     * @return True, wenn die Eingaben gültig sind, andernfalls False.
     */
    public boolean checkUserWH(int width, int height) {
        if (((height < 4 || width < 4) || (height > 25 || width > 25) || (width == 0 || height == 0))) {
            JOptionPane.showMessageDialog(null, message, "Fehlerhafte Eingabe", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            return true;
        }
    }

    /**
     * Überprüft die Benutzereingabe für die Anzahl der Inseln.
     *
     * @param width   Die Breite des Rasters.
     * @param height  Die Höhe des Rasters.
     * @param islands Die Anzahl der Inseln.
     * @return True, wenn die Eingabe gültig ist, andernfalls False.
     */
    public boolean checkUserIslands(int width, int height, int islands) {
        if (islands < 2 || islands > (0.2 * width * height)) {
            int maxIslands = (int)(0.2 * width * height);
            JOptionPane.showMessageDialog(null, maxIslandCount + maxIslands + end, "Fehlerhafte Eingabe", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            return true;
        }
    }
}