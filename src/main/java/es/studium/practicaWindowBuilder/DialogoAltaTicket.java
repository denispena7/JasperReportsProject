package es.studium.practicaWindowBuilder;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;

/**
 * Esta clase representa la vista del diálogo que pregunta si se quiere crear un
 * nuevo ticket.
 * 
 * @author Denis Peña
 * @version 1.0
 * @since 2025-02-12
 */
public class DialogoAltaTicket extends JDialog {
	/**
	 * @serial
	 */
	private static final long serialVersionUID = 1L;

	JPanel pnlPrincipal;
	JLabel lblMensaje;

	JButton btnSiguiente;
	JButton btnVolver;

	/**
	 * Constructor vacío de la clase.
	 */
	public DialogoAltaTicket() {
		setTitle("ALTA TICKET");
		setBounds(100, 100, 404, 210);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		{
			pnlPrincipal = new JPanel();
			pnlPrincipal.setBackground(new Color(241, 237, 220));
			getContentPane().add(pnlPrincipal, BorderLayout.CENTER);
			pnlPrincipal.setLayout(null);
			{
				lblMensaje = new JLabel("VAS A CREAR UN NUEVO TICKET");
				lblMensaje.setForeground(new Color(0, 64, 128));
				lblMensaje.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
				lblMensaje.setBounds(60, 38, 276, 35);
				pnlPrincipal.add(lblMensaje);
			}
			{
				btnSiguiente = new JButton("SIGUIENTE");
				btnSiguiente.setBackground(new Color(68, 119, 206));
				btnSiguiente.setForeground(new Color(232, 232, 232));
				btnSiguiente.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
				btnSiguiente.setBounds(67, 96, 121, 41);
				pnlPrincipal.add(btnSiguiente);
			}
			{
				btnVolver = new JButton("VOLVER");
				btnVolver.setForeground(new Color(232, 232, 232));
				btnVolver.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
				btnVolver.setBackground(new Color(68, 119, 206));
				btnVolver.setBounds(198, 96, 121, 41);
				pnlPrincipal.add(btnVolver);
			}
		}
	}
}