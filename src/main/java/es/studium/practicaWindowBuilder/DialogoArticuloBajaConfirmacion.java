package es.studium.practicaWindowBuilder;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;

/**
 * Esta clase representa la vista que confirma la baja de un artículo.
 * 
 * @author Denis Peña
 * @version 1.0
 * @since 2025-02-12
 */
public class DialogoArticuloBajaConfirmacion extends JDialog {
	/**
	 * @serial
	 */
	private static final long serialVersionUID = 1L;
	JPanel pnlPrincipal;
	JLabel lblMensaje;
	JButton btnNewButton;

	/**
	 * Constructor vacío de la clase
	 */
	public DialogoArticuloBajaConfirmacion() {
		setTitle("BAJA ARTÍCULOS");
		setBounds(100, 100, 330, 130);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		{
			pnlPrincipal = new JPanel();
			pnlPrincipal.setBackground(new Color(241, 237, 220));
			getContentPane().add(pnlPrincipal, BorderLayout.CENTER);
			pnlPrincipal.setLayout(null);
			{
				lblMensaje = new JLabel("BAJA CORRECTA");
				lblMensaje.setForeground(new Color(0, 64, 128));
				lblMensaje.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
				lblMensaje.setBounds(88, 11, 135, 23);
				pnlPrincipal.add(lblMensaje);
			}
			{
				JButton btnNewButton = new JButton("OK");
				btnNewButton.setBackground(new Color(68, 119, 206));
				btnNewButton.setForeground(new Color(232, 232, 232));
				btnNewButton.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
				btnNewButton.setBounds(111, 45, 89, 23);
				pnlPrincipal.add(btnNewButton);
			}
		}
	}
}