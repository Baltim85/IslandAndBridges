package UI;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JLabel;



import java.awt.Font;

import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.BevelBorder;


import Controller.MainController;

/**
 * Die Bridges-Klasse ist die Hauptklasse der Anwendung und dient zur Erstellung
 * und Steuerung des Bridges-Rätselspiels. Sie enthält die Benutzeroberfläche,
 * Menüoptionen, Schaltflächen und Zeichenflächen, die für die Interaktion mit
 * dem Benutzer und die Anzeige des Rätsels verwendet werden. Darüber hinaus
 * startet sie die Anwendung und initialisiert einen ActionController, der die
 * Spiellogik steuert.
 */
public class Bridges {

	// Hauptfenster für die Anwendung
	private JFrame frmBridges;

	// Menüelemente
	private JMenuItem miQuit;             // Menüpunkt zum Beenden der Anwendung
	private JMenuItem miNewPuzzle;        // Menüpunkt zum Erstellen eines neuen Rätsels
	private JMenuItem miRestartPuzzle;    // Menüpunkt zum Neustarten des aktuellen Rätsels
	private JMenuItem miSavePuzzle;       // Menüpunkt zum Speichern des aktuellen Rätsels
	private JMenuItem miSavePuzzleAs;     // Menüpunkt zum Speichern des aktuellen Rätsels unter einem anderen Namen
	private JMenuItem miLoadPuzzle;       // Menüpunkt zum Laden eines gespeicherten Rätsels

	// Ein Kontrollkästchen (Checkbox)
	private JCheckBox chckbxNewCheckBox;

	// Schaltflächen (Buttons)
	private JButton btnAutoSolve;          // Schaltfläche zum automatischen Lösen des Rätsels
	private JButton btnNextBridge;         // Schaltfläche zum Hinzufügen einer weiteren Brücke

	// Eine Beschriftung (Label)
	private JLabel lblInfo;               // Ein Label zur Anzeige von Informationen

	// Ein Zeichenbereich oder eine Zeichenfläche
	private JPanel draw;                   // Ein Panel zum Zeichnen des Rätsels oder der Brücken



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Bridges window = new Bridges();
					window.frmBridges.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Konstruktor für die Bridges-Anwendung.
	 * Hier wird die Initialisierung der Anwendung durchgeführt und ein ActionController erstellt.
	 */
	public Bridges() {
		initialize();          // Initialisieren der Anwendung
		new MainController(this);  // Erstellen eines ActionController-Objekts, das die Anwendung steuert
	}

	/**
	 * Initialisiert das Hauptfenster der Anwendung und seine Komponenten.
	 */
	private void initialize() {
		frmBridges = new JFrame();
		frmBridges.setResizable(false);
		frmBridges.setTitle("Bridges");
		frmBridges.setBounds(100, 100, 1680, 1024);
		frmBridges.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Menüleiste
		JMenuBar menuBar = new JMenuBar();
		frmBridges.setJMenuBar(menuBar);

		// Menü "Datei"
		JMenu menuFile = new JMenu("Datei");
		menuBar.add(menuFile);

		// Menüelemente für "Datei"
		miNewPuzzle = new JMenuItem("Neues Rätsel");
		miRestartPuzzle = new JMenuItem("Rätsel neu Starten");
		miSavePuzzle = new JMenuItem("Rätsel Speichern");
		miSavePuzzleAs = new JMenuItem("Rätsel Speichern Unter");
		miLoadPuzzle = new JMenuItem("Rätsel Laden");
		miQuit = new JMenuItem("Beenden");

		// Füge Menüelemente zu "Datei" hinzu
		menuFile.add(miNewPuzzle);
		menuFile.add(miRestartPuzzle);
		menuFile.addSeparator();
		menuFile.add(miSavePuzzle);
		menuFile.add(miSavePuzzleAs);
		menuFile.addSeparator();
		menuFile.add(miLoadPuzzle);
		menuFile.addSeparator();
		menuFile.add(miQuit);

		// Haupt-Panel
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		frmBridges.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		// Unteres Panel (South)
		JPanel pSouth = new JPanel();
		panel.add(pSouth, BorderLayout.SOUTH);
		pSouth.setLayout(new GridLayout(3, 1, 3, 5));

		// Checkbox
		chckbxNewCheckBox = new JCheckBox("Anzahl fehlender Brücken anzeigen");
		chckbxNewCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pSouth.add(chckbxNewCheckBox);

		// Button-Panel
		JPanel pButtons = new JPanel();
		pSouth.add(pButtons);
		pButtons.setLayout(new GridLayout(1, 2, 100, 0));

		// "Automatisch Lösen" Button
		btnAutoSolve = new JButton("Automatisch Lösen");
		btnAutoSolve.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pButtons.add(btnAutoSolve);

		// "Nächste Brücke" Button
		btnNextBridge = new JButton("Nächste Brücke");
		btnNextBridge.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pButtons.add(btnNextBridge);

		// Informationslabel
		lblInfo = new JLabel("Das Rätsel ist noch nicht gelöst!");
		lblInfo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pSouth.add(lblInfo);

		// Zeichenflächen-Panel
		draw = new JPanel();
		draw.setBorder(new BevelBorder(BevelBorder.LOWERED));
		draw.setBackground(Color.WHITE);
		panel.add(draw, BorderLayout.CENTER);
		draw.setLayout(new GridLayout(1, 0));
	}



	/**
	 * Gibt das Hauptfenster der Anwendung zurück.
	 *
	 * @return Das Hauptfenster der Anwendung.
	 */
	public JFrame getFrmBridges() {
		return frmBridges;
	}

	/**
	 * Legt das Hauptfenster der Anwendung fest.
	 *
	 * @param frmBridges Das Hauptfenster der Anwendung, das festgelegt werden soll.
	 */
	public void setFrmBridges(JFrame frmBridges) {
		this.frmBridges = frmBridges;
	}

	/**
	 * Gibt das Menüelement zum Beenden der Anwendung zurück.
	 *
	 * @return Das Menüelement zum Beenden der Anwendung.
	 */
	public JMenuItem getMiQuit() {
		return miQuit;
	}


	/**
	 * Legt das Menüelement zum Beenden der Anwendung fest.
	 *
	 * @param miQuit Das Menüelement zum Beenden der Anwendung, das festgelegt werden soll.
	 */
	public void setMiQuit(JMenuItem miQuit) {
		this.miQuit = miQuit;
	}

	/**
	 * Gibt das Menüelement für ein neues Puzzle zurück.
	 *
	 * @return Das Menüelement für ein neues Puzzle.
	 */
	public JMenuItem getMiNewPuzzle() {
		return miNewPuzzle;
	}

	/**
	 * Legt das Menüelement für ein neues Puzzle fest.
	 *
	 * @param miNewPuzzle Das Menüelement für ein neues Puzzle, das festgelegt werden soll.
	 */
	public void setMiNewPuzzle(JMenuItem miNewPuzzle) {
		this.miNewPuzzle = miNewPuzzle;
	}



	/**
	 * Gibt das Menüelement für das Neustarten eines Puzzles zurück.
	 *
	 * @return Das Menüelement für das Neustarten eines Puzzles.
	 */
	public JMenuItem getMiRestartPuzzle() {
		return miRestartPuzzle;
	}

	/**
	 * Legt das Menüelement für das Neustarten eines Puzzles fest.
	 *
	 * @param miRestartPuzzle Das Menüelement für das Neustarten eines Puzzles, das festgelegt werden soll.
	 */
	public void setMiRestartPuzzle(JMenuItem miRestartPuzzle) {
		this.miRestartPuzzle = miRestartPuzzle;
	}

	/**
	 * Gibt das Menüelement für das Speichern eines Puzzles zurück.
	 *
	 * @return Das Menüelement für das Speichern eines Puzzles.
	 */
	public JMenuItem getMiSavePuzzle() {
		return miSavePuzzle;
	}

	/**
	 * Legt das Menüelement für das Speichern eines Puzzles fest.
	 *
	 * @param miSavePuzzle Das Menüelement für das Speichern eines Puzzles, das festgelegt werden soll.
	 */
	public void setMiSavePuzzle(JMenuItem miSavePuzzle) {
		this.miSavePuzzle = miSavePuzzle;
	}


	/**
	 * Gibt das Menüelement für das Speichern eines Puzzles unter einem anderen Namen zurück.
	 *
	 * @return Das Menüelement für das Speichern eines Puzzles unter einem anderen Namen.
	 */
	public JMenuItem getMiSavePuzzleAs() {
		return miSavePuzzleAs;
	}

	/**
	 * Legt das Menüelement für das Speichern eines Puzzles unter einem anderen Namen fest.
	 *
	 * @param miSavePuzzleAs Das Menüelement für das Speichern eines Puzzles unter einem anderen Namen, das festgelegt werden soll.
	 */
	public void setMiSavePuzzleAs(JMenuItem miSavePuzzleAs) {
		this.miSavePuzzleAs = miSavePuzzleAs;
	}

	/**
	 * Gibt das Menüelement für das Laden eines Puzzles zurück.
	 *
	 * @return Das Menüelement für das Laden eines Puzzles.
	 */
	public JMenuItem getMiLoadPuzzle() {
		return miLoadPuzzle;
	}

	/**
	 * Legt das Menüelement für das Laden eines Puzzles fest.
	 *
	 * @param miLoadPuzzle Das Menüelement für das Laden eines Puzzles, das festgelegt werden soll.
	 */
	public void setMiLoadPuzzle(JMenuItem miLoadPuzzle) {
		this.miLoadPuzzle = miLoadPuzzle;
	}

	/**
	 * Gibt das Kontrollkästchen für eine neue Checkbox zurück.
	 *
	 * @return Das Kontrollkästchen für eine neue Checkbox.
	 */
	public JCheckBox getChckbxNewCheckBox() {
		return chckbxNewCheckBox;
	}

	/**
	 * Legt das Kontrollkästchen für eine neue Checkbox fest.
	 *
	 * @param chckbxNewCheckBox Das Kontrollkästchen für eine neue Checkbox, das festgelegt werden soll.
	 */
	public void setChckbxNewCheckBox(JCheckBox chckbxNewCheckBox) {
		this.chckbxNewCheckBox = chckbxNewCheckBox;
	}

	/**
	 * Gibt die Schaltfläche "Auto Solve" zurück.
	 *
	 * @return Die Schaltfläche "Auto Solve".
	 */
	public JButton getBtnAutoSolve() {
		return btnAutoSolve;
	}


	/**
	 * Legt die Schaltfläche "Auto Solve" fest.
	 *
	 * @param btnAutoSolve Die Schaltfläche "Auto Solve", die festgelegt werden soll.
	 */
	public void setBtnAutoSolve(JButton btnAutoSolve) {
		this.btnAutoSolve = btnAutoSolve;
	}

	/**
	 * Gibt die Schaltfläche "Next Bridge" zurück.
	 *
	 * @return Die Schaltfläche "Next Bridge".
	 */
	public JButton getBtnNextBridge() {
		return btnNextBridge;
	}

	/**
	 * Legt die Schaltfläche "Next Bridge" fest.
	 *
	 * @param btnNextBridge Die Schaltfläche "Next Bridge", die festgelegt werden soll.
	 */
	public void setBtnNextBridge(JButton btnNextBridge) {
		this.btnNextBridge = btnNextBridge;
	}

	/**
	 * Gibt das Label für Informationen zurück.
	 *
	 * @return Das Label für Informationen.
	 */
	public JLabel getLblInfo() {
		return lblInfo;
	}

	/**
	 * Legt das Label für Informationen fest.
	 *
	 * @param lblInfo Das Label für Informationen, das festgelegt werden soll.
	 */
	public void setLblInfo(JLabel lblInfo) {
		this.lblInfo = lblInfo;
	}

	/**
	 * Gibt das Zeichenflächen-Panel zurück, auf dem das Spielbrett gezeichnet wird.
	 *
	 * @return Das Zeichenflächen-Panel.
	 */
	public JPanel getDraw() {
		return draw;
	}

	/**
	 * Legt das Zeichenflächen-Panel fest, auf dem das Spielbrett gezeichnet wird.
	 *
	 * @param draw Das Zeichenflächen-Panel, das festgelegt werden soll.
	 */
	public void setDraw(JPanel draw) {
		this.draw = draw;
	}



}
