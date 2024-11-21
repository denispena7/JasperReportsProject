package es.studium.practicaWindowBuilder;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;

public class DialogoArticuloActConfirmada extends JDialog {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the dialog.
	 */
	public DialogoArticuloActConfirmada() {
		setTitle("ACTUALIZACIÓN ARTÍCULOS");
		setBounds(100, 100, 312, 131);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel pnlPrincipal = new JPanel();
			pnlPrincipal.setBackground(new Color(241, 237, 220));
			getContentPane().add(pnlPrincipal, BorderLayout.CENTER);
			pnlPrincipal.setLayout(null);
			{
				JLabel lblMensaje = new JLabel("ACTUALIZACIÓN CORRECTA");
				lblMensaje.setForeground(new Color(0, 64, 128));
				lblMensaje.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
				lblMensaje.setBounds(37, 11, 218, 25);
				pnlPrincipal.add(lblMensaje);
			}
			{
				JButton btnOK = new JButton("OK");
				btnOK.setForeground(new Color(232, 232, 232));
				btnOK.setBackground(new Color(68, 119, 206));
				btnOK.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
				btnOK.setBounds(100, 47, 89, 23);
				pnlPrincipal.add(btnOK);
			}
		}
	}

}
