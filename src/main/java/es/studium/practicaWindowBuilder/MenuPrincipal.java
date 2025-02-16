package es.studium.practicaWindowBuilder;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 * Esta clase representa la vista principal de la aplicación.
 * 
 * @author Denis Peña
 * @version 1.0
 * @since 2025-02-12
 */
public class MenuPrincipal extends JFrame {
	/**
	 * @serial
	 */
	private static final long serialVersionUID = 1L;
	JPanel contentPane;
	JButton btnArticulos;
	JButton btnTickets;
	JLabel lblInicio;

	/**
	 * Constructor vacío de la clase.
	 */
	public MenuPrincipal() {
		setBackground(new Color(247, 246, 242));
		setTitle("WindowBuilder");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 356, 219);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(241, 237, 220));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblInicio = new JLabel("PULSA UN BOTÓN");
		lblInicio.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		lblInicio.setBounds(86, 28, 178, 24);
		contentPane.add(lblInicio);

		btnArticulos = new JButton("ARTÍCULOS");
		btnArticulos.setBackground(new Color(88, 135, 211));
		btnArticulos.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 18));
		btnArticulos.setForeground(new Color(241, 237, 220));
		btnArticulos.setBorder(new LineBorder(Color.BLACK, 1));
		btnArticulos.setBounds(39, 63, 257, 43);
		contentPane.add(btnArticulos);

		btnTickets = new JButton("TICKETS");
		btnTickets.setForeground(new Color(241, 237, 220));
		btnTickets.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 18));
		btnTickets.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnTickets.setBackground(new Color(88, 135, 211));
		btnTickets.setBounds(39, 117, 257, 43);
		contentPane.add(btnTickets);

		setVisible(true);
	}
}