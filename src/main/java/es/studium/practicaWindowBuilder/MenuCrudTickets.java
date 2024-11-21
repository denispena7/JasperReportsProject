package es.studium.practicaWindowBuilder;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class MenuCrudTickets extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	JButton btnAltaTicket;
	JButton btnElegirTicket;

	JComboBox<String> listaTickets;
	
	Modelo datos = new Modelo();

	/**
	 * Create the frame.
	 */
	public MenuCrudTickets() {
		setTitle("TICKETS");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 377, 340);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(241, 237, 220));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbLTitulo = new JLabel("ALTA");
		lbLTitulo.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		lbLTitulo.setBounds(150, 22, 61, 30);
		contentPane.add(lbLTitulo);
		
		btnAltaTicket = new JButton("CREAR NUEVO TICKET");
		btnAltaTicket.setForeground(new Color(232, 232, 232));
		btnAltaTicket.setBackground(new Color(68, 119, 206));
		btnAltaTicket.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 18));
		btnAltaTicket.setBounds(51, 63, 257, 43);
		contentPane.add(btnAltaTicket);
		
		JLabel lblConsulta = new JLabel("CONSULTA");
		lblConsulta.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		lblConsulta.setBounds(127, 135, 114, 30);
		contentPane.add(lblConsulta);
		
		datos.conectar();
		String[] elementos = datos.rellenarListaTickets();
		datos.desconectar();
		
		listaTickets = new JComboBox<String>(elementos);
		listaTickets.setMaximumRowCount(4);
		listaTickets.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		//listaTickets.setModel(new DefaultComboBoxModel(new String[] {"ELIGE EL TICKET...", "1", "2", "3", "4", "5"}));
		listaTickets.setBounds(98, 176, 174, 22);
		contentPane.add(listaTickets);
		
		btnElegirTicket = new JButton("ACEPTAR");
		btnElegirTicket.setBackground(new Color(68, 119, 206));
		btnElegirTicket.setForeground(new Color(241, 237, 242));
		btnElegirTicket.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 18));
		btnElegirTicket.setBounds(83, 220, 200, 43);
		contentPane.add(btnElegirTicket);
		
//		setVisible(true);
	}
}
