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
 * Die Klasse SaveGameSuccessfully repräsentiert ein Dialogfenster, das dem Benutzer
 * anzeigt, dass das Spiel erfolgreich gespeichert wurde. Der Dialog enthält eine Meldung
 * und einen OK-Button zum Schließen.
 */
public class SaveGameSuccessfully extends JDialog{


	// Eindeutige Serial-Version-ID zur Serialisierung der Klasse.
	private static final long serialVersionUID = 1L;

	// Das Haupt-JPanel, das alle UI-Komponenten enthält.
	private final JPanel contentPanel = new JPanel();

	// Schaltflächen zum Abbrechen und Beenden des Dialogs.
	private JButton btnOk;

	// Beschriftungen für Aufmerksamkeit und Anfrage.
	private JLabel lblSuccess;




	/**
	 * Dies ist ein Dialogfenster, das dem Benutzer anzeigt, dass das Spiel erfolgreich gespeichert wurde.
	 * Der Dialog enthält eine Meldung und einen OK-Button zum Schließen.
	 */
	public SaveGameSuccessfully() {
		// Festlegen der Größe und des Layouts des Dialogs
		setBounds(100, 100, 358, 170); // Festlegen der Position und Größe des Dialogs
		getContentPane().setLayout(new BorderLayout()); // Verwendung des BorderLayouts für die Anordnung von Komponenten

		// Erstellen und Konfigurieren des Panels für den Dialoginhalt
		JPanel contentPanel = new JPanel(); // Ein Panel, um die Inhaltskomponenten zu gruppieren
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5)); // Einfügen eines leeren Rahmens um das Panel
		getContentPane().add(contentPanel, BorderLayout.CENTER); // Hinzufügen des Panels zum Dialog und Zentrieren im BorderLayout
		contentPanel.setLayout(null); // Verwenden eines Nulllayouts für manuelle Positionierung

		// Erstellen und Konfigurieren des "OK"-Buttons
		btnOk = new JButton("Ok"); // Ein Button mit der Beschriftung "Ok"
		btnOk.setBounds(112, 93, 111, 23); // Festlegen der Position und Größe des Buttons im Panel

		// Erstellen und Konfigurieren des Hinweislabels
		lblSuccess = new JLabel("Das Spiel wurde gespeichert"); // Ein Label mit der Meldung
		lblSuccess.setVerticalAlignment(SwingConstants.TOP); // Vertikale Ausrichtung oben
		lblSuccess.setHorizontalAlignment(SwingConstants.CENTER); // Horizontale Ausrichtung in der Mitte
		lblSuccess.setForeground(new Color(0, 0, 0)); // Farbe des Texts auf Schwarz setzen
		lblSuccess.setFont(new Font("Tahoma", Font.PLAIN, 22)); // Schriftart, Stil und Größe festlegen
		lblSuccess.setBounds(10, 23, 332, 32); // Festlegen der Position und Größe des Labels im Panel

		// Hinzufügen der Komponenten zum Panel
		contentPanel.add(lblSuccess); // Hinzufügen des Hinweislabels zum Panel
		contentPanel.add(btnOk); // Hinzufügen des "OK"-Buttons zum Panel

		// Dialog als modal festlegen (blockiert andere Interaktionen)
		setModal(true);

		// Standardaktion beim Schließen des Dialogs festlegen (Dialog wird geschlossen)
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
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
