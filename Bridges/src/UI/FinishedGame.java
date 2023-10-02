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
 * Ein benutzerdefinierter Dialog für die Bestätigung des Spielendes.
 * Diese Klasse erweitert JDialog und stellt einen Dialog dar, der den Benutzer über einen Fehler informiert.
 * Der Dialog enthält eine Meldung und einen OK-Button zum Schließen des Dialogs.
 */
public class FinishedGame extends JDialog{


	// Eindeutige Serial-Version-ID zur Serialisierung der Klasse.
	private static final long serialVersionUID = 1L;

	// Das Haupt-JPanel, das alle UI-Komponenten enthält.
	private final JPanel contentPanel = new JPanel();

	// Schaltflächen zum Abbrechen und Beenden des Dialogs.
	private JButton btnNo;

	// Beschriftungen für Aufmerksamkeit und Anfrage.
	private JLabel lblAttention, lblRequest;



	/**
	 * Erstellt einen neuen ErrorIsland-Dialog.
	 * Dieser Dialog wird verwendet, um den Benutzer über einen Fehler zu informieren.
	 * Der Dialog enthält eine Meldung und einen OK-Button zum Schließen des Dialogs.
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

		// "Nein"-Button erstellen und positionieren
		btnNo = new JButton("Ok");
		btnNo.setBounds(112, 93, 111, 23);

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
		contentPanel.add(btnNo);

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
