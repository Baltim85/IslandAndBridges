package modells.Painter.Islands;

import java.awt.Graphics;
import java.util.ArrayList;

import Modell.Island;


/**
 * Die abstrakte Klasse IslandDrawer ist die Basisklasse für spezialisierte Implementierungen,
 * die für das Zeichnen von Inseln auf Grafikobjekten verantwortlich sind. Sie deklariert abstrakte
 * Methoden, die in den abgeleiteten Klassen implementiert werden müssen, um Inseln zu zeichnen und
 * zu füllen.
 *
 * Abstrakte Methoden:
 * - drawColoredIsland(Graphics g, ArrayList<Island> listOfRedIslands): Diese Methode muss implementiert
 *   werden, um eine Liste von Inseln auf das übergebene Grafikobjekt g zu zeichnen. Sie ermöglicht die
 *   kundenspezifische Implementierung für das Zeichnen von Inseln in verschiedenen Farben oder Stilen.
 * - fillIsland(Graphics g, Island island): Diese Methode muss implementiert werden, um eine einzelne Insel
 *   auf das übergebene Grafikobjekt g zu zeichnen. Die konkrete Umsetzung kann je nach den Anforderungen für
 *   das Zeichnen einer Insel variieren.
 * - setColor(Graphics g): Diese Methode muss implementiert werden, um die Zeichenfarbe auf das gewünschte
 *   Farbschema oder den gewünschten Stil festzulegen. Abgeleitete Klassen können diese Methode verwenden, um
 *   die Zeichenfarbe anzupassen.
 *
 * Verwendung:
 * Die abstrakte Klasse IslandDrawer dient als Schnittstelle oder Basis für spezialisierte Zeichenklassen
 * wie DrawRedIslands oder DrawGreenIslands, die bestimmte Inseln in unterschiedlichen Farben zeichnen.
 * Entwickler können von dieser Klasse erben und die abstrakten Methoden implementieren, um maßgeschneiderte
 * Zeichnungen von Inseln in ihren Anwendungen zu erstellen.
 */
public abstract class IslandDrawer {
	/**
	 * Zeichnet die farbigen Inseln auf die gegebene Grafikfläche.
	 *
	 * @param g Die Grafikfläche, auf der die Inseln gezeichnet werden sollen.
	 * @param listOfRedIslands Eine Liste von roten Inselobjekten, die gezeichnet werden sollen.
	 */
	public abstract void drawColoredIsland(Graphics g, ArrayList<Island> listOfRedIslands);

	/**
	 * Füllt eine einzelne Insel auf der gegebenen Grafikfläche.
	 *
	 * @param g Die Grafikfläche, auf der die Insel gefüllt werden soll.
	 * @param island Das Inselobjekt, das gefüllt werden soll.
	 */
	public abstract void fillIsland(Graphics g, Island island);

	/**
	 * Setzt die Farbe für das Zeichnen und Füllen von Inseln auf der Grafikfläche.
	 *
	 * @param g Die Grafikfläche, auf der die Farbe gesetzt werden soll.
	 */
	public abstract void setColor(Graphics g);




}
