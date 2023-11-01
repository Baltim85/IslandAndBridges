package Controller;

import UI.NewPuzzle;
import modells.uiSupport.ToggleButtonsA;

/**
 * Die "AutoSizeIslandToggle" Klasse stellt eine UI-Komponente dar, die die automatische Größenanpassung von Inseln in 
 * einem Puzzle steuert. Sie erweitert die "ToggleButtonsA"-Klasse und ermöglicht es dem Benutzer, zwischen manueller 
 * Größeneinstellung und automatischer Größenanpassung der Inseln zu wählen.

 * Attribute:
 * - puzzle: Ein "NewPuzzle"-Objekt, das die UI-Elemente enthält, die von dieser Klasse gesteuert werden.

 * Konstruktor:
 * - AutoSizeIslandToggle(NewPuzzle puzzle): Erstellt eine neue Instanz der "AutoSizeIslandToggle"-Klasse mit einem 
 * "NewPuzzle"-Objekt als Parameter, das die zugehörigen UI-Elemente enthält.

 * Methoden:
 * - toggleElement(): Schaltet die UI-Elemente für die automatische Größenanpassung der Inseln ein oder aus, abhängig von der 
 * Auswahl des Benutzers.

 * Die "AutoSizeIslandToggle" Klasse bietet eine benutzerfreundliche Möglichkeit, die Größe von Inseln in einem Puzzle zu steuern, 
 * sei es manuell oder automatisch. Sie erleichtert die Interaktion mit der Benutzeroberfläche und bietet Flexibilität 
 * bei der Anpassung der Inselgrößen.
 */
public class AutoSizeIslandToggle extends ToggleButtonsA {

    /**
     * Konstruktor für die AutoSizeIslandToggle-Klasse.
     *
     * @param puzzle Das NewPuzzle-Objekt, das die UI-Elemente enthält, die gesteuert werden sollen.
     */
    public AutoSizeIslandToggle(NewPuzzle puzzle) {
        super(puzzle);
    }

    /**
     * Schaltet die UI-Elemente für die automatische Größenanpassung der Inseln ein oder aus,
     * abhängig von der Auswahl des Benutzers.
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
