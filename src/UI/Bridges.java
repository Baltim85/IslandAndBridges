package UI;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.SwingConstants;
import java.awt.Font;

import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.BevelBorder;


import Controller.ActionController;


public class Bridges {

	private JFrame frmBridges;
	private JMenuItem miQuit;
	private JMenuItem miNewPuzzle;
	private JMenuItem miRestartPuzzle;
	private JMenuItem miSavePuzzle;
	private JMenuItem miSavePuzzleAs;
	private JMenuItem miLoadPuzzle;
	
	private JCheckBox chckbxNewCheckBox;
	
	private JButton btnAutoSolve;
	private JButton btnNextBridge;
	private JLabel lblInfo;
	
	private JPanel draw;
	
	
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
	 * Create the application.
	 */
	public Bridges() {
		initialize();
		new ActionController(this);
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBridges = new JFrame();
		frmBridges.setResizable(false);
		frmBridges.setTitle("Bridges");
		frmBridges.setBounds(100, 100, 520, 570);
		frmBridges.setSize(1680, 1024);
		frmBridges.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmBridges.setJMenuBar(menuBar);
		
		JMenu menuFile = new JMenu("Datei");
		menuBar.add(menuFile);
		
		miNewPuzzle = new JMenuItem("Neues Rätsel");
		menuFile.add(miNewPuzzle);
		
		miRestartPuzzle = new JMenuItem("Rätsel neu Starten");
		menuFile.add(miRestartPuzzle);
		
		JSeparator separator = new JSeparator();
		menuFile.add(separator);
		
		miSavePuzzle = new JMenuItem("Rätsel Speichern");
		menuFile.add(miSavePuzzle);
		
		miSavePuzzleAs = new JMenuItem("Rätsel Speichern Unter");
		menuFile.add(miSavePuzzleAs);
		
		JSeparator separator_1 = new JSeparator();
		menuFile.add(separator_1);
		
		miLoadPuzzle = new JMenuItem("Rätsel Laden");
		menuFile.add(miLoadPuzzle);
		
		JSeparator separator_2 = new JSeparator();
		menuFile.add(separator_2);
		
		miQuit = new JMenuItem("Beenden");
		menuFile.add(miQuit);
		
		frmBridges.getContentPane().setLayout(new BorderLayout(2, 2));
		
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		panel.setBounds(10, 10, 1008, 700);
		frmBridges.getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel pSouth = new JPanel();
		panel.add(pSouth, BorderLayout.SOUTH);
		pSouth.setLayout(new GridLayout(3, 1, 3, 5));
		
		chckbxNewCheckBox = new JCheckBox("Anzahl fehlender Brücken anzeigen");
		chckbxNewCheckBox.setHorizontalAlignment(SwingConstants.LEFT);
		chckbxNewCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pSouth.add(chckbxNewCheckBox);
		
		JPanel pButtons = new JPanel();
		pSouth.add(pButtons);
		pButtons.setLayout(new GridLayout(1, 2, 100, 0));
		
		btnAutoSolve = new JButton("Automatisch Lösen");
		btnAutoSolve.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pButtons.add(btnAutoSolve);
		
		btnNextBridge = new JButton("Nächste Brücke");
		btnNextBridge.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pButtons.add(btnNextBridge);
		
		lblInfo = new JLabel("Das Rätsel ist noch nicht gelöst!");
		lblInfo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pSouth.add(lblInfo);
		
		draw = new JPanel();
		draw.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), new Color(0, 0, 0), new Color(0, 0, 0), new Color(0, 0, 0)));
		draw.setBackground(new Color(255, 255, 255));
		panel.add(draw, BorderLayout.CENTER);
		draw.setLayout(new GridLayout(1, 0, 0, 0));
		
		
	
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
	 * @return the chckbxNewCheckBox
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
