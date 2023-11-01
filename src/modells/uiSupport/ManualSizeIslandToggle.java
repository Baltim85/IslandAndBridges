package modells.uiSupport;

import UI.NewPuzzle;

/**
 * Die Klasse "ManualSizeIslandToggle" erweitert die abstrakte Klasse "ToggleButtonsA" und implementiert spezifische Logik
 * zum Umschalten von Schaltflächen und Optionen in der Benutzeroberfläche in Bezug auf die manuelle Größeneinstellung
 * von Inseln. Sie ist dafür verantwortlich, die entsprechenden UI-Elemente ein- oder auszuschalten, um die Auswahl der
 * manuellen Größeneinstellung zu steuern.
 *
 * Konstruktor:
 * - ManualSizeIslandToggle(NewPuzzle puzzle): Der Konstruktor initialisiert die Klasse mit einem "NewPuzzle"-Fenster, das
 *   die zu steuernden UI-Elemente bereitstellt.
 *
 * Methoden:
 * - toggleElement(): Die Methode implementiert die spezifische Logik zum Umschalten der UI-Elemente in Bezug auf die
 *   manuelle Größeneinstellung der Inseln. Wenn die Option für die manuelle Größeneinstellung ausgewählt ist, werden
 *   bestimmte UI-Elemente aktiviert oder deaktiviert, um die Einstellungen für die automatische Größenanpassung zu deaktivieren
 *   und umgekehrt. Bei der manuellen Größeneinstellung werden auch Optionen für die Definition der Inselanzahl verfügbar.
 *
 * Diese Klasse dient dazu, die Steuerungslogik für die manuelle Größeneinstellung der Inseln zu kapseln und in der
 * abstrakten Klasse "ToggleButtonsA" eine allgemeine Struktur für solche Steuerungselemente bereitzustellen.
 */
public class ManualSizeIslandToggle extends ToggleButtonsA {

    /**
     * Der Konstruktor für die Klasse "ManualSizeIslandToggle" initialisiert die Instanz mit einem "NewPuzzle"-Fenster, das die
     * zu steuernden UI-Elemente für die manuelle Größeneinstellung der Inseln bereitstellt.
     *
     * @param puzzle Das "NewPuzzle"-Fenster, das die Steuerungselemente bereitstellt.
     */
    public ManualSizeIslandToggle(NewPuzzle puzzle) {
        super(puzzle);
    }

    /**
     * Die Methode "toggleElement" implementiert die spezifische Logik zum Umschalten der UI-Elemente in Bezug auf die
     * manuelle Größeneinstellung der Inseln. Wenn die Option für die manuelle Größeneinstellung ausgewählt ist, werden
     * bestimmte UI-Elemente aktiviert oder deaktiviert, um die Einstellungen für die automatische Größenanpassung zu deaktivieren
     * und umgekehrt. Bei der manuellen Größeneinstellung werden auch Optionen für die Definition der Inselanzahl verfügbar.
     */
    @Override
    public void toggleElement() {
        // Ein- oder Ausschalten der UI-Elemente für die manuelle Größeneinstellung der Inseln
        if (puzzle.getRbnSizeIsland().isSelected()) {
            // Manuelle Größe ausgewählt
            puzzle.getRbnAutoSizeIsland().setSelected(false);
            puzzle.getTfHeight().setEnabled(true);
            puzzle.getTfWidth().setEnabled(true);
            puzzle.getCbDefineIslands().setEnabled(true);
            if (puzzle.getCbDefineIslands().isSelected()) {
                puzzle.getTfIslands().setEnabled(true);
            }
        } 

    }
}
