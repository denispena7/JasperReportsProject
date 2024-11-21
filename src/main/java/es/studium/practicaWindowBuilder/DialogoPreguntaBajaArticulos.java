package es.studium.practicaWindowBuilder;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;

public class DialogoPreguntaBajaArticulos extends JDialog {

	private static final long serialVersionUID = 1L;
	JButton btnSi;
	JButton btnNo;

	/**
	 * Create the dialog.
	 */
	public DialogoPreguntaBajaArticulos() {
		setTitle("PELIGRO");
		setBounds(100, 100, 420, 144);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel pnlPrincipal = new JPanel();
			pnlPrincipal.setBackground(new Color(241, 237, 220));
			getContentPane().add(pnlPrincipal, BorderLayout.CENTER);
			pnlPrincipal.setLayout(null);
			{
				JLabel lblPregunta = new JLabel("¿ESTÁS SEGURO DE BORRAR ESTE ARTÍCULO?");
				lblPregunta.setForeground(new Color(0, 64, 128));
				lblPregunta.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
				lblPregunta.setBounds(30, 11, 346, 39);
				pnlPrincipal.add(lblPregunta);
			}
			{
				btnSi = new JButton("SI");
				btnSi.setBackground(new Color(68, 119, 206));
				btnSi.setForeground(new Color(232, 232, 232));
				btnSi.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
				btnSi.setBounds(95, 61, 89, 23);
				pnlPrincipal.add(btnSi);
			}
			{
				btnNo = new JButton("NO");
				btnNo.setBackground(new Color(68, 119, 206));
				btnNo.setForeground(new Color(232, 232, 232));
				btnNo.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
				btnNo.setBounds(211, 61, 89, 23);
				pnlPrincipal.add(btnNo);
			}
		}
	}

}
