package es.studium.practicaWindowBuilder;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;

public class DialogoTicketAltaConfirmada extends JDialog {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the dialog.
	 */
	public DialogoTicketAltaConfirmada() {
		setTitle("ALTA CORRECTA TICKET");
		setBounds(100, 100, 330, 129);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel pnlPrincipal = new JPanel();
			pnlPrincipal.setBackground(new Color(241, 237, 220));
			getContentPane().add(pnlPrincipal, BorderLayout.CENTER);
			pnlPrincipal.setLayout(null);
			{
				JLabel lblMensaje = new JLabel("EL TICKET HA SIDO REGISTRADO");
				lblMensaje.setForeground(new Color(0, 64, 128));
				lblMensaje.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
				lblMensaje.setBounds(37, 11, 254, 31);
				pnlPrincipal.add(lblMensaje);
			}
			{
				JButton btnOK = new JButton("OK");
				btnOK.setBackground(new Color(68, 119, 206));
				btnOK.setForeground(new Color(232, 232, 232));
				btnOK.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
				btnOK.setBounds(110, 53, 89, 23);
				pnlPrincipal.add(btnOK);
			}
		}
	}

}
