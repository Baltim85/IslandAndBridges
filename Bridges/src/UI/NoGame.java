package UI;

import java.awt.BorderLayout;


import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.Font;


/**
 * Die Klasse "NoGame" repräsentiert ein Dialogfenster, das dem Benutzer angezeigt wird,
 * wenn keine Informationen zum Speichern vorliegen. Dieses Dialogfenster informiert den Benutzer
 * darüber, dass keine Spielinformationen vorhanden sind und bittet darum, ein neues Spiel zu starten.
 * 
 * Das Fenster zeigt zwei Nachrichten an: Die erste Nachricht informiert den Benutzer darüber,
 * dass keine Informationen zum Speichern vorliegen, und die zweite Nachricht fordert den Benutzer auf,
 * ein neues Spiel zu starten. Es enthält auch eine "OK"-Schaltfläche, mit der der Benutzer den Dialog schließen kann.
 * 
 */
public class NoGame extends JDialog {

	// Eindeutige Serial-Version-ID zur Serialisierung der Klasse.
	private static final long serialVersionUID = 1L;

	// Schaltfläche zum Bestätigen (OK) des Dialogs.
	private JButton btnOK;

	/**
	 * Konstruktor für das NoGame-Dialogfenster.
	 * Initialisiert das Fenster, setzt seinen Typ auf POPUP und erstellt die UI-Elemente.
	 */
	public NoGame() {
		// Das Fenster nicht veränderbar machen
		setResizable(false);

		// Den Fenstertyp auf POPUP setzen
		setType(Type.POPUP);

		// Die Fenstergröße und Position festlegen
		setBounds(100, 100, 450, 190);

		// Das Haupt-Panel erstellen und dem Fenster hinzufügen
		JPanel contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));

		// Das "OK"-Button erstellen und dem unteren Bereich des Haupt-Panels hinzufügen
		btnOK = new JButton("OK");
		btnOK.setFont(new Font("Calibri", Font.PLAIN, 18));
		contentPanel.add(btnOK, BorderLayout.SOUTH);

		// Ein Panel für die Nachrichten erstellen und dem mittleren Bereich des Haupt-Panels hinzufügen
		JPanel panel = new JPanel();
		contentPanel.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(2, 1, 0, 0));

		// Erste Nachricht hinzufügen
		JLabel lblNewLabel = new JLabel("Es liegen keine Informationen zum Speichern vor!");
		lblNewLabel.setFont(new Font("Calibri", Font.ITALIC, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel);

		// Zweite Nachricht hinzufügen
		JLabel lblNewLabel_1 = new JLabel("Bitte starten Sie ein neues Spiel!");
		lblNewLabel_1.setFont(new Font("Calibri", Font.ITALIC, 18));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_1);
	}


	/**
	 * Gibt die Schaltfläche "OK" zurück.
	 * 
	 * @return Die Schaltfläche "OK".
	 */
	public JButton getBtnOK() {
		return btnOK;
	}

	/**
	 * Legt die Schaltfläche "OK" fest.
	 * 
	 * @param btnOK Die Schaltfläche "OK", die festgelegt werden soll.
	 */
	public void setBtnOK(JButton btnOK) {
		this.btnOK = btnOK;
	}

}
