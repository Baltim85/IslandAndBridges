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
 * Die Klasse ErrorIsland repräsentiert ein Dialogfenster, das angezeigt wird, wenn ein Fehler im Spiel auftritt.
 * Es informiert den Benutzer über den Fehler und bietet eine Schaltfläche zum Schließen des Dialogs.
 */
public class ErrorIsland extends JDialog{


	// Eindeutige Serial-Version-ID zur Serialisierung der Klasse.
	private static final long serialVersionUID = 1L;

	// Das Haupt-JPanel, das alle UI-Komponenten enthält.
	private final JPanel contentPanel = new JPanel();

	// Schaltflächen zum Abbrechen und Beenden des Dialogs.
	private JButton btnOk;

	// Beschriftungen für Aufmerksamkeit und Anfrage.
	private JLabel lblAttention, lblRequest;



	/**
	 * Erstellt einen neuen ErrorIsland-Dialog.
	 * Dieser Dialog wird verwendet, um den Benutzer über einen Fehler zu informieren.
	 * Der Dialog enthält eine Meldung und einen OK-Button zum Schließen des Dialogs.
	 */
	public ErrorIsland() {
		// Dialog-Größe und Layout festlegen
		setBounds(100, 100, 358, 170);
		getContentPane().setLayout(new BorderLayout());

		// Panel für den Inhalt erstellen und hinzufügen
		JPanel contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		// "Ok"-Button erstellen und positionieren
		btnOk = new JButton("OK");
		btnOk.setBounds(96, 93, 144, 23);

		// Hinweislabel erstellen und positionieren
		lblAttention = new JLabel("Achtung!");
		lblAttention.setForeground(new Color(255, 0, 0));
		lblAttention.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAttention.setHorizontalAlignment(SwingConstants.CENTER);
		lblAttention.setBounds(10, 23, 332, 23);

		// Anforderungslabel erstellen und positionieren
		lblRequest = new JLabel("Eine oder mehrere Inseln enthalten zuviele Brücken!");
		lblRequest.setFont(new Font("Tahoma", Font.PLAIN, 14));
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
