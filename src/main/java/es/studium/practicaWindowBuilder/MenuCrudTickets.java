package es.studium.practicaWindowBuilder;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;

/**
 * Esta clase representa la vista del menú CRUD de los tickets.
 * 
 * @author Denis Peña
 * @version 1.0
 * @since 2025-02-12
 */
public class MenuCrudTickets extends JFrame {
	/**
	 * @serial
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JLabel lbLTitulo;
	JLabel lblConsulta;

	JButton btnAltaTicket;
	JButton btnElegirTicket;
	JButton btnInfomeTickets;

	JComboBox<String> listaTickets;

	/**
	 * Instancia del Modelo del programa.
	 */
	Modelo datos = new Modelo();

	/**
	 * Constructor vacío de la clase.
	 */
	public MenuCrudTickets() {
		setTitle("TICKETS");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 377, 439);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(241, 237, 220));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		lbLTitulo = new JLabel("ALTA");
		lbLTitulo.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		lbLTitulo.setBounds(150, 22, 61, 30);
		contentPane.add(lbLTitulo);

		btnAltaTicket = new JButton("CREAR NUEVO TICKET");
		btnAltaTicket.setForeground(new Color(232, 232, 232));
		btnAltaTicket.setBackground(new Color(68, 119, 206));
		btnAltaTicket.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 18));
		btnAltaTicket.setBounds(51, 63, 257, 43);
		contentPane.add(btnAltaTicket);

		lblConsulta = new JLabel("CONSULTA");
		lblConsulta.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		lblConsulta.setBounds(127, 135, 114, 30);
		contentPane.add(lblConsulta);

		datos.conectar();
		String[] elementos = datos.rellenarListaTickets();
		datos.desconectar();

		listaTickets = new JComboBox<String>(elementos);
		listaTickets.setMaximumRowCount(4);
		listaTickets.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		listaTickets.setBounds(98, 176, 174, 22);
		contentPane.add(listaTickets);

		btnElegirTicket = new JButton("ACEPTAR");
		btnElegirTicket.setBackground(new Color(68, 119, 206));
		btnElegirTicket.setForeground(new Color(241, 237, 242));
		btnElegirTicket.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 18));
		btnElegirTicket.setBounds(51, 220, 257, 43);
		contentPane.add(btnElegirTicket);

		JLabel lblInforme = new JLabel("INFORME");
		lblInforme.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		lblInforme.setBounds(137, 286, 92, 30);
		contentPane.add(lblInforme);

		btnInfomeTickets = new JButton("GENERAR INFORME");
		btnInfomeTickets.setForeground(new Color(241, 237, 242));
		btnInfomeTickets.setBackground(new Color(68, 119, 206));
		btnInfomeTickets.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 18));
		btnInfomeTickets.setBounds(51, 326, 257, 43);
		contentPane.add(btnInfomeTickets);
	}
}