package UI;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JCheckBox;


/**
 * Ein benutzerdefinierter Dialog für die Bestätigung des Spielendes.
 */
public class NewPuzzle extends JDialog {

	// Eindeutige Serial-Version-ID zur Serialisierung der Klasse.
	private static final long serialVersionUID = 1L;
	

	// Das Haupt-JPanel, das alle UI-Komponenten enthält.
	private final JPanel contentPanel = new JPanel();
	

	// Textfelder zur Eingabe von Breite, Höhe und Inselanzahl.
	private JTextField tfWidth, tfHeight, tfIslands;

	// Beschriftungen für die Textfelder und Optionen.
	private JLabel lbNewPuzzle, lblWidth, lbHeight, lbIsland;

	// Radio-Buttons zur Auswahl zwischen automatischer und manueller Größeneinstellung.
	private JRadioButton rbnAutoSizeIsland, rbnSizeIsland;
	
	// Checkbox zur Festlegung der Inselanzahl.
	private JCheckBox cbDefineIslands;
	
	// Schaltflächen zum Abbrechen und Bestätigen der Eingabe.
	private JButton btnAbord, btnOk;

	// Standardwerte für Breite und Inselanzahl, die in den Textfeldern angezeigt werden.
	private String defaultValueSize = "20";
	private String defaultIslands = "70";

	/**
	 * Konstruktor für das Fenster zur Erstellung eines neuen Rätsels.
	 */
	public NewPuzzle() {
	    // Einstellungen für das Hauptfenster
	    setResizable(false); // Deaktiviert die Größenänderung des Fensters
	    setBounds(100, 100, 354, 303); // Setzt die Fenstergröße und Position
	    getContentPane().setLayout(new BorderLayout()); // Verwendet BorderLayout für die Hauptkomponente
	    contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5)); // Setzt den Innenabstand des Hauptpanels
	    getContentPane().add(contentPanel, BorderLayout.CENTER); // Fügt das Hauptpanel zum BorderLayout hinzu

	    // Überschrift für das neue Rätsel
	    lbNewPuzzle = new JLabel("Neues Rätsel");
	    lbNewPuzzle.setFont(new Font("Times New Roman", Font.BOLD, 16));

	    // Radiobuttons für die Auswahl der Rätselgröße
	    rbnAutoSizeIsland = new JRadioButton("Automatische Größe und Inselzahl");
	    rbnAutoSizeIsland.setSelected(true); // Standardauswahl
	    rbnAutoSizeIsland.setFont(new Font("Tahoma", Font.PLAIN, 14));

	    rbnSizeIsland = new JRadioButton("Größe und/oder Inselzahl selbst festlegen");
	    rbnSizeIsland.setFont(new Font("Tahoma", Font.PLAIN, 14));

	    // Button-Gruppe für die Radiobuttons
	    ButtonGroup bg = new ButtonGroup();
	    bg.add(rbnAutoSizeIsland);
	    bg.add(rbnSizeIsland);

	    // Breite-Eingabe
	    lblWidth = new JLabel("Breite:");
	    lblWidth.setFont(new Font("Tahoma", Font.PLAIN, 14));

	    tfWidth = new JTextField(defaultValueSize, 10); // Textfeld mit Standardwert
	    tfWidth.setEnabled(false); // Deaktiviert das Textfeld

	    // Höhe-Eingabe
	    lbHeight = new JLabel("Höhe:");
	    lbHeight.setFont(new Font("Tahoma", Font.PLAIN, 14));

	    tfHeight = new JTextField(defaultValueSize, 10); // Textfeld mit Standardwert
	    tfHeight.setEnabled(false); // Deaktiviert das Textfeld

	    // Checkbox für die Festlegung der Inselzahl
	    cbDefineIslands = new JCheckBox("Inselzahl festlegen");
	    cbDefineIslands.setEnabled(false); // Deaktiviert die Checkbox
	    cbDefineIslands.setFont(new Font("Tahoma", Font.PLAIN, 14));

	    // Inseln-Eingabe
	    lbIsland = new JLabel("Inseln:");
	    lbIsland.setFont(new Font("Tahoma", Font.PLAIN, 14));

	    tfIslands = new JTextField(defaultIslands, 10); // Textfeld mit Standardwert
	    tfIslands.setEnabled(false); // Deaktiviert das Textfeld

	    // Abbrechen-Button
	    btnAbord = new JButton("Abbrechen");
	    btnAbord.setFont(new Font("Tahoma", Font.PLAIN, 16));

	    // OK-Button
	    btnOk = new JButton("OK");
	    btnOk.setFont(new Font("Tahoma", Font.PLAIN, 16));

	    // Layout-Definition für das Hauptpanel
	    GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
	    initGroupLayout(gl_contentPanel); // Methode zur weiteren Anpassung des Layouts

	    contentPanel.setLayout(gl_contentPanel); // Setzt das GroupLayout für das Hauptpanel
	}
	
	
	/**
	 * Initialisiert das GroupLayout für das ContentPanel des NewPuzzle-Fensters.
	 *
	 * @param gl_contentPanel das GroupLayout, das initialisiert wird
	 */
	private void initGroupLayout(GroupLayout gl_contentPanel) {
		gl_contentPanel.setHorizontalGroup(
				gl_contentPanel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPanel.createSequentialGroup()
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPanel.createSequentialGroup()
								.addContainerGap()
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
									.addComponent(lbNewPuzzle)
									.addComponent(rbnAutoSizeIsland)
									.addComponent(rbnSizeIsland)))
							.addGroup(gl_contentPanel.createSequentialGroup()
								.addGap(45)
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_contentPanel.createSequentialGroup()
										.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
											.addComponent(lblWidth)
											.addComponent(lbHeight, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
											.addComponent(tfHeight, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
											.addComponent(tfWidth, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)))
									.addComponent(cbDefineIslands)
									.addGroup(gl_contentPanel.createSequentialGroup()
										.addComponent(lbIsland)
										.addGap(18)
										.addComponent(tfIslands, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE))))
							.addGroup(gl_contentPanel.createSequentialGroup()
								.addContainerGap()
								.addComponent(btnAbord)
								.addPreferredGap(ComponentPlacement.RELATED, 90, Short.MAX_VALUE)
								.addComponent(btnOk, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap())
			);
			gl_contentPanel.setVerticalGroup(
				gl_contentPanel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPanel.createSequentialGroup()
						.addContainerGap()
						.addComponent(lbNewPuzzle)
						.addGap(18)
						.addComponent(rbnAutoSizeIsland)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(rbnSizeIsland)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblWidth)
							.addComponent(tfWidth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
							.addComponent(tfHeight, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lbHeight, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(cbDefineIslands)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
							.addComponent(lbIsland)
							.addComponent(tfIslands, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnAbord)
							.addComponent(btnOk, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
			);
		
	}

	

	/**
	 * Gibt das Textfeld für die Breite zurück.
	 * 
	 * @return das Textfeld für die Breite
	 */
	public JTextField getTfWidth() {
	    return tfWidth;
	}

	/**
	 * Setzt das Textfeld für die Breite.
	 * 
	 * @param tfWidth das zu setzende Textfeld für die Breite
	 */
	public void setTfWidth(JTextField tfWidth) {
	    this.tfWidth = tfWidth;
	}

	/**
	 * Gibt das Textfeld für die Höhe zurück.
	 * 
	 * @return das Textfeld für die Höhe
	 */
	public JTextField getTfHeight() {
	    return tfHeight;
	}

	/**
	 * Setzt das Textfeld für die Höhe.
	 * 
	 * @param tfHeight das zu setzende Textfeld für die Höhe
	 */
	public void setTfHeight(JTextField tfHeight) {
	    this.tfHeight = tfHeight;
	}

	/**
	 * Gibt das Textfeld für die Anzahl der Inseln zurück.
	 * 
	 * @return das Textfeld für die Anzahl der Inseln
	 */
	public JTextField getTfIslands() {
	    return tfIslands;
	}

	/**
	 * Setzt das Textfeld für die Anzahl der Inseln.
	 * 
	 * @param tfIslands das zu setzende Textfeld für die Anzahl der Inseln
	 */
	public void setTfIslands(JTextField tfIslands) {
	    this.tfIslands = tfIslands;
	}

	/**
	 * Gibt den RadioButton für die automatische Größe der Inseln zurück.
	 * 
	 * @return der RadioButton für die automatische Größe der Inseln
	 */
	public JRadioButton getRbnAutoSizeIsland() {
	    return rbnAutoSizeIsland;
	}

	/**
	 * Setzt den RadioButton für die automatische Größe der Inseln.
	 * 
	 * @param rbnAutoSizeIsland der zu setzende RadioButton für die automatische Größe der Inseln
	 */
	public void setRbnAutoSizeIsland(JRadioButton rbnAutoSizeIsland) {
	    this.rbnAutoSizeIsland = rbnAutoSizeIsland;
	}

	/**
	 * Gibt den RadioButton für die manuelle Größe der Inseln zurück.
	 * 
	 * @return der RadioButton für die manuelle Größe der Inseln
	 */
	public JRadioButton getRbnSizeIsland() {
	    return rbnSizeIsland;
	}

	/**
	 * Setzt den RadioButton für die manuelle Größe der Inseln.
	 * 
	 * @param rbnSizeIsland der zu setzende RadioButton für die manuelle Größe der Inseln
	 */
	public void setRbnSizeIsland(JRadioButton rbnSizeIsland) {
	    this.rbnSizeIsland = rbnSizeIsland;
	}

	/**
	 * Gibt die CheckBox für die Definition der Inseln zurück.
	 * 
	 * @return die CheckBox für die Definition der Inseln
	 */
	public JCheckBox getCbDefineIslands() {
	    return cbDefineIslands;
	}

	/**
	 * Setzt die CheckBox für die Definition der Inseln.
	 * 
	 * @param cbDefineIslands die zu setzende CheckBox für die Definition der Inseln
	 */
	public void setCbDefineIslands(JCheckBox cbDefineIslands) {
	    this.cbDefineIslands = cbDefineIslands;
	}

	/**
	 * Gibt den "Abbrechen"-Button zurück.
	 * 
	 * @return der "Abbrechen"-Button
	 */
	public JButton getBtnAbord() {
	    return btnAbord;
	}

	/**
	 * Setzt den "Abbrechen"-Button.
	 * 
	 * @param btnAbord der zu setzende "Abbrechen"-Button
	 */
	public void setBtnAbord(JButton btnAbord) {
	    this.btnAbord = btnAbord;
	}

	/**
	 * Gibt den "OK"-Button zurück.
	 * 
	 * @return der "OK"-Button
	 */
	public JButton getBtnOk() {
	    return btnOk;
	}

	/**
	 * Setzt den "OK"-Button.
	 * 
	 * @param btnOk der zu setzende "OK"-Button
	 */
	public void setBtnOk(JButton btnOk) {
	    this.btnOk = btnOk;
	}

	/**
	 * Gibt den Standardwert für die Größe zurück.
	 * 
	 * @return der Standardwert für die Größe
	 */
	public String getDefaultValueSize() {
	    return defaultValueSize;
	}

	/**
	 * Setzt den Standardwert für die Größe.
	 * 
	 * @param defaultValueSize der zu setzende Standardwert für die Größe
	 */
	public void setDefaultValueSize(String defaultValueSize) {
	    this.defaultValueSize = defaultValueSize;
	}

	/**
	 * Gibt den Standardwert für die Anzahl der Inseln zurück.
	 * 
	 * @return der Standardwert für die Anzahl der Inseln
	 */
	public String getDefaultIslands() {
	    return defaultIslands;
	}

	/**
	 * Setzt den Standardwert für die Anzahl der Inseln.
	 * 
	 * @param defaultIslands der zu setzende Standardwert für die Anzahl der Inseln
	 */
	public void setDefaultIslands(String defaultIslands) {
	    this.defaultIslands = defaultIslands;
	}

	/**
	 * Gibt das Inhalts-Panel zurück.
	 * 
	 * @return das Inhalts-Panel
	 */
	public JPanel getContentPanel() {
	    return contentPanel;
	}

}
