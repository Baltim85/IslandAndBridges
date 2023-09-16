package UI;

import java.awt.BorderLayout;


import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

/**
 * Ein benutzerdefinierter Dialog für die Bestätigung des Spielendes.
 */
public class ExitDialog extends JDialog{


	// Eindeutige Serial-Version-ID zur Serialisierung der Klasse.
	private static final long serialVersionUID = 1L;

	// Das Haupt-JPanel, das alle UI-Komponenten enthält.
	private final JPanel contentPanel = new JPanel();

	// Schaltflächen zum Abbrechen und Beenden des Dialogs.
	private JButton btnNo, btnExit;

	// Beschriftungen für Aufmerksamkeit und Anfrage.
	private JLabel lblAttention, lblRequest;

	
	
    /**
     * Erstellt einen neuen ExitDialog.
     */
    public ExitDialog() {
        // Dialog-Größe und Layout festlegen
        setBounds(100, 100, 358, 170);
        getContentPane().setLayout(new BorderLayout());
        
        // Panel für den Inhalt erstellen und hinzufügen
        JPanel contentPanel = new JPanel();
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        
        // "Nein"-Button erstellen und positionieren
        btnNo = new JButton("Nein");
        btnNo.setBounds(10, 93, 144, 23);
        
        // "Beenden"-Button erstellen und positionieren
        btnExit = new JButton("Beenden");
        btnExit.setBounds(188, 93, 144, 23);
        
        // Hinweislabel erstellen und positionieren
        lblAttention = new JLabel("Achtung! Nicht gespeicherter Fortschritt geht verloren!");
        lblAttention.setBounds(10, 23, 332, 23);
        
        // Anforderungslabel erstellen und positionieren
        lblRequest = new JLabel("Möchten Sie das Spiel wirklich Beenden?");
        lblRequest.setBounds(10, 59, 332, 23);
        
        // Komponenten zum Panel hinzufügen
        contentPanel.add(lblAttention);
        contentPanel.add(lblRequest);
        contentPanel.add(btnNo);
        contentPanel.add(btnExit);

        // Dialog modal machen (blockiert andere Interaktionen)
        setModal(true);

        // Standard-Aktion beim Schließen des Dialogs festlegen
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }



	/**
	 * Gibt den "Nein"-Button zurück.
	 * 
	 * @return der "Nein"-Button
	 */
	public JButton getBtnNo() {
	    return btnNo;
	}

	/**
	 * Setzt den "Nein"-Button.
	 * 
	 * @param btnNo der zu setzende "Nein"-Button
	 */
	public void setBtnNo(JButton btnNo) {
	    this.btnNo = btnNo;
	}

	/**
	 * Gibt den "Exit"-Button zurück.
	 * 
	 * @return der "Exit"-Button
	 */
	public JButton getBtnExit() {
	    return btnExit;
	}

	/**
	 * Setzt den "Exit"-Button.
	 * 
	 * @param btnExit der zu setzende "Exit"-Button
	 */
	public void setBtnExit(JButton btnExit) {
	    this.btnExit = btnExit;
	}

	/**
	 * Gibt die Serial Version UID zurück.
	 * 
	 * @return die Serial Version UID
	 */
	public static long getSerialversionuid() {
	    return serialVersionUID;
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
