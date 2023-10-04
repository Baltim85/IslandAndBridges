package UI;

import java.awt.BorderLayout;


import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;

/**
 * Die Klasse FinishedGame repräsentiert ein Dialogfenster, das angezeigt wird, wenn das Spiel erfolgreich abgeschlossen wurde.
 * Es zeigt einen Glückwunsch und eine Erfolgsmeldung an und bietet eine Schaltfläche zum Schließen des Dialogs.
 */
public class FinishedGame extends JDialog{


	// Eindeutige Serial-Version-ID zur Serialisierung der Klasse.
	private static final long serialVersionUID = 1L;

	// Das Haupt-JPanel, das alle UI-Komponenten enthält.
	private final JPanel contentPanel = new JPanel();

	// Schaltflächen zum Abbrechen und Beenden des Dialogs.
	private JButton btnOk;

	// Beschriftungen für Aufmerksamkeit und Anfrage.
	private JLabel lblAttention, lblRequest;



	/**
	 * Konstruktor für das Dialogfenster, das nach erfolgreichem Spielabschluss angezeigt wird.
	 */
	public FinishedGame() {
		// Dialog-Größe und Layout festlegen
		setBounds(100, 100, 358, 170);
		getContentPane().setLayout(new BorderLayout());

		// Panel für den Inhalt erstellen und hinzufügen
		JPanel contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		// "Ok"-Button erstellen und positionieren
		btnOk = new JButton("Ok");
		btnOk.setBounds(112, 93, 111, 23);
		btnOk.addActionListener(e -> disposeView());

		// Hinweislabel erstellen und positionieren
		lblAttention = new JLabel("Herzlichen Glückwunsch");
		lblAttention.setForeground(new Color(9, 125, 2));
		lblAttention.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblAttention.setHorizontalAlignment(SwingConstants.CENTER);
		lblAttention.setBounds(10, 23, 332, 23);

		// Anforderungslabel erstellen und positionieren
		lblRequest = new JLabel("Sie haben alle Inseln erfolgreich verbunden");
		lblRequest.setHorizontalAlignment(SwingConstants.CENTER);
		lblRequest.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblRequest.setBounds(10, 59, 332, 23);

		// Komponenten zum Panel hinzufügen
		contentPanel.add(lblAttention);
		contentPanel.add(lblRequest);
		contentPanel.add(btnOk);

		// Dialog modal machen (blockiert andere Interaktionen)
		setModal(true);

		// Standard-Aktion beim Schließen des Dialogs festlegen
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}
	
	/**
	 * Schließt und entsorgt das aktuelle UI-Fenster oder Dialogfeld.
	 * 
	 * Diese Methode wird aufgerufen, um das Fenster zu schließen und die damit
	 * verbundenen Ressourcen freizugeben. Sie wird normalerweise verwendet, wenn der
	 * Benutzer die Schließtaste des Fensters oder einen ähnlichen Schließmechanismus auslöst.
	 */
	private void disposeView() {
	    this.dispose();
	}


	/**
	 * Gibt die Schaltfläche "Ok" zurück.
	 * 
	 * @return die Schaltfläche "Ok"
	 */
	public JButton getBtnOk() {
		return btnOk;
	}

	/**
	 * Setzt die Schaltfläche "Ok".
	 * 
	 * @param btnOk die zu setzende Schaltfläche "Ok"
	 */
	public void setBtnOk(JButton btnOk) {
		this.btnOk = btnOk;
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
