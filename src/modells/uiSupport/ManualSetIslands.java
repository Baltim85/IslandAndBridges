package modells.uiSupport;

import UI.NewPuzzle;

/**
 * Die Klasse "ManualSetIslands" erweitert die abstrakte Klasse "ToggleButtonsA" und implementiert spezifische Logik
 * zum Umschalten von Schaltflächen und Optionen in der Benutzeroberfläche in Bezug auf die manuelle Festlegung der
 * Inselanzahl. Sie ist dafür verantwortlich, das Textfeld zur Eingabe der Inselanzahl zu aktivieren oder zu deaktivieren
 * abhängig davon, ob die Option zur manuellen Festlegung der Inselanzahl ausgewählt ist.
 *
 * Konstruktor:
 * - ManualSetIslands(NewPuzzle puzzle): Der Konstruktor initialisiert die Klasse mit einem "NewPuzzle"-Fenster, das
 *   die zu steuernden UI-Elemente bereitstellt.
 *
 * Methoden:
 * - toggleElement(): Die Methode implementiert die spezifische Logik zum Umschalten des Textfelds für die manuelle
 *   Festlegung der Inselanzahl in Abhängigkeit davon, ob die Option zur manuellen Festlegung der Inselanzahl ausgewählt
 *   ist. Wenn die Option zur manuellen Festlegung der Inselanzahl ausgewählt ist, wird das Textfeld aktiviert, ansonsten
 *   wird es deaktiviert.
 *
 * Diese Klasse dient dazu, die Steuerungslogik für die manuelle Festlegung der Inselanzahl in der Benutzeroberfläche
 * zu kapseln und in der abstrakten Klasse "ToggleButtonsA" eine allgemeine Struktur für solche Steuerungselemente
 * bereitzustellen.
 */
public class ManualSetIslands extends ToggleButtonsA {

    /**
     * Der Konstruktor für die Klasse "ManualSetIslands" initialisiert die Instanz mit einem "NewPuzzle"-Fenster, das
     * die Steuerungselemente für die manuelle Festlegung der Inselanzahl bereitstellt.
     *
     * @param puzzle Das "NewPuzzle"-Fenster, das die Steuerungselemente bereitstellt.
     */
    public ManualSetIslands(NewPuzzle puzzle) {
        super(puzzle);
    }

    /**
     * Die Methode "toggleElement" implementiert die spezifische Logik zum Umschalten des Textfelds für die manuelle
     * Festlegung der Inselanzahl in Abhängigkeit davon, ob die Option zur manuellen Festlegung der Inselanzahl ausgewählt
     * ist. Wenn die Option zur manuellen Festlegung der Inselanzahl ausgewählt ist, wird das Textfeld aktiviert, ansonsten
     * wird es deaktiviert.
     */
    @Override
    public void toggleElement() {
        if (puzzle.getCbDefineIslands().isSelected()) {
            // Wenn "Inselzahl festlegen" ausgewählt ist
            puzzle.getTfIslands().setEnabled(true);
        } else {
            // Wenn "Inselzahl festlegen" nicht ausgewählt ist
            puzzle.getTfIslands().setEnabled(false);
        }
    }
}
