package modells.uiSupport;
import UI.NewPuzzle;

/**
 * Die Klasse "AutoSizeIslandToggle" erweitert die abstrakte Klasse "ToggleButtonsA" und implementiert spezifische Logik
 * zum Umschalten von Schaltflächen und Optionen in der Benutzeroberfläche in Bezug auf die automatische Größenanpassung
 * von Inseln. Sie ist dafür verantwortlich, die entsprechenden UI-Elemente ein- oder auszuschalten, um die Auswahl der
 * automatischen Größenanpassung zu steuern.
 *
 * Konstruktor:
 * - AutoSizeIslandToggle(NewPuzzle puzzle): Der Konstruktor initialisiert die Klasse mit einem "NewPuzzle"-Fenster, das
 *   die zu steuernden UI-Elemente bereitstellt.
 *
 * Methoden:
 * - toggleElement(): Die Methode implementiert die spezifische Logik zum Umschalten der UI-Elemente in Bezug auf die
 *   automatische Größenanpassung der Inseln. Wenn die Option für die automatische Größenanpassung ausgewählt ist, werden
 *   bestimmte UI-Elemente aktiviert oder deaktiviert, um die Einstellungen für die manuelle Größeneinstellung zu deaktivieren
 *   und umgekehrt.
 *
 * Diese Klasse dient dazu, die Steuerungslogik für die automatische Größenanpassung der Inseln zu kapseln und in der
 * abstrakten Klasse "ToggleButtonsA" eine allgemeine Struktur für solche Steuerungselemente bereitzustellen.
 */
public class AutoSizeIslandToggle extends ToggleButtonsA {

    /**
     * Der Konstruktor für die Klasse "AutoSizeIslandToggle" initialisiert die Instanz mit einem "NewPuzzle"-Fenster, das die
     * zu steuernden UI-Elemente für die automatische Größenanpassung der Inseln bereitstellt.
     *
     * @param puzzle Das "NewPuzzle"-Fenster, das die Steuerungselemente bereitstellt.
     */
    public AutoSizeIslandToggle(NewPuzzle puzzle) {
        super(puzzle);
    }

    /**
     * Die Methode "toggleElement" implementiert die spezifische Logik zum Umschalten der UI-Elemente in Bezug auf die
     * automatische Größenanpassung der Inseln. Wenn die Option für die automatische Größenanpassung ausgewählt ist, werden
     * bestimmte UI-Elemente aktiviert oder deaktiviert, um die Einstellungen für die manuelle Größeneinstellung zu deaktivieren
     * und umgekehrt.
     */
    @Override
    public void toggleElement() {
        // Ein- oder Ausschalten der UI-Elemente für die automatische Größenanpassung der Inseln
        if (puzzle.getRbnAutoSizeIsland().isSelected()) {
            // Automatische Größe ausgewählt
            puzzle.getRbnSizeIsland().setSelected(false);
            puzzle.getRbnSizeIsland().setEnabled(true);
            puzzle.getTfHeight().setEnabled(false);
            puzzle.getTfWidth().setEnabled(false);
            puzzle.getCbDefineIslands().setEnabled(false);
            puzzle.getTfIslands().setEnabled(false);
        } 

    }
}
