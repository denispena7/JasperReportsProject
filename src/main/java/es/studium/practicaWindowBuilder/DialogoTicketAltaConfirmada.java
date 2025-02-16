package es.studium.practicaWindowBuilder;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;

/**
 * Esta clase representa la vista que confirma que un ticket ha sido de alta.
 * 
 * @author Denis Peña
 * @version 1.0
 * @since 2025-02-12
 */
public class DialogoTicketAltaConfirmada extends JDialog {
	/**
	 * @serial
	 */
	private static final long serialVersionUID = 1L;
	JPanel pnlPrincipal;
	JLabel lblMensaje;
	JButton btnOK;

	/**
	 * Constructor vacío de la clase.
	 */
	public DialogoTicketAltaConfirmada() {
		setTitle("ALTA CORRECTA TICKET");
		setBounds(100, 100, 330, 129);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		{
			pnlPrincipal = new JPanel();
			pnlPrincipal.setBackground(new Color(241, 237, 220));
			getContentPane().add(pnlPrincipal, BorderLayout.CENTER);
			pnlPrincipal.setLayout(null);
			{
				lblMensaje = new JLabel("EL TICKET HA SIDO REGISTRADO");
				lblMensaje.setForeground(new Color(0, 64, 128));
				lblMensaje.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
				lblMensaje.setBounds(37, 11, 254, 31);
				pnlPrincipal.add(lblMensaje);
			}
			{
				btnOK = new JButton("OK");
				btnOK.setBackground(new Color(68, 119, 206));
				btnOK.setForeground(new Color(232, 232, 232));
				btnOK.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
				btnOK.setBounds(110, 53, 89, 23);
				pnlPrincipal.add(btnOK);
			}
		}
	}
}