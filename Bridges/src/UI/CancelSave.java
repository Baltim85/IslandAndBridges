package UI;

import java.awt.BorderLayout;


import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

/**
 * Die Klasse CancelSave repräsentiert ein Dialogfenster, das dem Benutzer
 * anzeigt, dass der Speichervorgang abgebrochen wurde. Der Dialog enthält eine Meldung
 * und einen OK-Button zum Schließen.
 */
public class CancelSave extends JDialog{


	// Eindeutige Serial-Version-ID zur Serialisierung der Klasse.
	private static final long serialVersionUID = 1L;

	// Das Haupt-JPanel, das alle UI-Komponenten enthält.
	private final JPanel contentPanel = new JPanel();

	// Schaltflächen zum Abbrechen und Beenden des Dialogs.
	private JButton btnOk;

	// Beschriftungen für Aufmerksamkeit und Anfrage.
	private JLabel lblabord;



	/**
	 * Konstruktor für die CancelSave-Klasse. Erzeugt ein Dialogfenster, das dem
	 * Benutzer anzeigt, dass der Speichervorgang abgebrochen wurde.
	 */
	public CancelSave() {
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
		btnOk.addActionListener(e -> closeView()); 
        

		// Hinweislabel erstellen und positionieren
		lblabord = new JLabel("Speichern wurde Abgebrochen");
		lblabord.setVerticalAlignment(SwingConstants.TOP);
		lblabord.setHorizontalAlignment(SwingConstants.CENTER);
		lblabord.setForeground(new Color(0, 0, 0));
		lblabord.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblabord.setBounds(10, 23, 332, 32);

		// Komponenten zum Panel hinzufügen
		contentPanel.add(lblabord);
		contentPanel.add(btnOk);

		// Dialog modal machen (blockiert andere Interaktionen)
		setModal(true);

		// Standard-Aktion beim Schließen des Dialogs festlegen
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}


	public void closeView() {
		this.dispose();
	}


	/**
	 * Gibt den "OK"-Button des Dialogs zurück.
	 *
	 * @return Der "OK"-Button des Dialogs.
	 */
	public JButton getBtnOk() {
		return btnOk;
	}

	/**
	 * Legt den "OK"-Button des Dialogs fest.
	 *
	 * @param btnOk Der "OK"-Button, der festgelegt werden soll.
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
