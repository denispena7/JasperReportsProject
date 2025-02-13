package es.studium.practicaWindowBuilder;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;

public class DialogoArticuloAltaConfirmada extends JDialog 
{
	private static final long serialVersionUID = 1L;
	JPanel pnlPrincipal;
	JLabel lblMensaje;
	JButton btnOK;

	public DialogoArticuloAltaConfirmada() 
	{
		setTitle("ALTA ARTÍCULO");
		setBounds(100, 100, 330, 127);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		{
			pnlPrincipal = new JPanel();
			pnlPrincipal.setBackground(new Color(241, 237, 220));
			getContentPane().add(pnlPrincipal, BorderLayout.CENTER);
			pnlPrincipal.setLayout(null);
			{
				lblMensaje = new JLabel("ALTA CORRECTA");
				lblMensaje.setForeground(new Color(0, 64, 128));
				lblMensaje.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
				lblMensaje.setBounds(87, 11, 132, 34);
				pnlPrincipal.add(lblMensaje);
			}
			{
				btnOK = new JButton("OK");
				btnOK.setForeground(new Color(232, 232, 232));
				btnOK.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
				btnOK.setBackground(new Color(68, 119, 206));
				btnOK.setBounds(109, 54, 89, 23);
				pnlPrincipal.add(btnOK);
			}
		}
	}
}