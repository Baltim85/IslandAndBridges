package modells.uiSupport;

import UI.NewPuzzle;

/**
 * Die abstrakte Klasse "ToggleButtonsA" stellt eine Grundstruktur für Klassen bereit, die die Schaltflächen und Optionen
 * in der Benutzeroberfläche einer Anwendung steuern. Sie dient als allgemeines Framework für die Implementierung von
 * Schaltflächen-Steuerungen und ermöglicht es, verschiedene Steuerungslogiken in abgeleiteten Klassen zu definieren.
 *
 * Attribute:
 * - puzzle (NewPuzzle): Eine Instanz des "NewPuzzle"-Fensters, das die Schaltflächen und Optionen enthält, die gesteuert werden.
 *
 * Konstruktor:
 * - ToggleButtonsA(NewPuzzle puzzle): Der Konstruktor initialisiert die Klasse mit einer Instanz von "NewPuzzle", die die
 *   Steuerungselemente bereitstellt.
 *
 * Methoden:
 * - toggleElement(): Eine abstrakte Methode, die in abgeleiteten Klassen implementiert werden muss. Sie definiert die Logik
 *   zum Umschalten von Steuerelementen in der Benutzeroberfläche.
 * 
 * Diese abstrakte Klasse dient als Grundlage für die Implementierung spezifischer Steuerungslogiken, die in den abgeleiteten
 * Klassen definiert werden. Die Methode "toggleElement" sollte in den abgeleiteten Klassen die spezifische Logik für das
 * Umschalten von Schaltflächen und Optionen enthalten.
 */
public abstract class ToggleButtonsA {

    protected NewPuzzle puzzle;

    /**
     * Der Konstruktor für die abstrakte Klasse "ToggleButtonsA" initialisiert die Instanz mit einem "NewPuzzle"-Fenster,
     * das die zu steuernden Schaltflächen und Optionen enthält.
     *
     * @param puzzle Das "NewPuzzle"-Fenster, das die Steuerungselemente bereitstellt.
     */
    public ToggleButtonsA(NewPuzzle puzzle) {
        this.puzzle = puzzle;
    }

    /**
     * Die abstrakte Methode "toggleElement" muss in den abgeleiteten Klassen implementiert werden. Sie definiert die spezifische
     * Logik zum Umschalten von Steuerelementen in der Benutzeroberfläche.
     */
    public abstract void toggleElement();
}

