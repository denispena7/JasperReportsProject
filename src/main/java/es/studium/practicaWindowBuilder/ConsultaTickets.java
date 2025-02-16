package es.studium.practicaWindowBuilder;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;

/**
 * Esta clase representa la vista de la consulta de los tickets.
 * 
 * @author Denis Peña
 * @version 1.0
 * @since 2025-02-12
 */
public class ConsultaTickets extends JFrame {
	/**
	 * @serial
	 */
	private static final long serialVersionUID = 1L;
	private JPanel pnlTicket;
	JPanel pnlInformacion;
	JLabel lblDireccion;
	JLabel lblCp;
	JLabel lblTelefono;
	JLabel lblCaja;
	JPanel pnlConsultaTicket;
	JLabel lblCajero;
	JLabel lblGracias;

	JTable tblConsultaTicket;
	JTextField txtTotal;
	JLabel lblFecha;
	JButton btnImprimirTicket;

	/**
	 * Constructor vacío de la clase.
	 */
	public ConsultaTickets() {
		setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 534);
		setLocationRelativeTo(null);
		pnlTicket = new JPanel();
		pnlTicket.setBackground(new Color(241, 237, 220));
		pnlTicket.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(pnlTicket);
		pnlTicket.setLayout(null);

		pnlInformacion = new JPanel();
		pnlInformacion.setBackground(new Color(241, 237, 220));
		pnlInformacion.setBounds(10, 11, 414, 115);
		pnlTicket.add(pnlInformacion);
		pnlInformacion.setLayout(null);

		lblDireccion = new JLabel("ALBERCHE 7");
		lblDireccion.setForeground(new Color(0, 64, 128));
		lblDireccion.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		lblDireccion.setBounds(10, 11, 99, 24);
		pnlInformacion.add(lblDireccion);

		lblCp = new JLabel("41005 SEVILLA");
		lblCp.setForeground(new Color(0, 64, 128));
		lblCp.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		lblCp.setBounds(10, 46, 120, 24);
		pnlInformacion.add(lblCp);

		lblTelefono = new JLabel("TELÉFONO: 697152347");
		lblTelefono.setForeground(new Color(0, 64, 128));
		lblTelefono.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		lblTelefono.setBounds(10, 81, 179, 23);
		pnlInformacion.add(lblTelefono);

		lblFecha = new JLabel();
		lblFecha.setForeground(new Color(0, 64, 128));
		lblFecha.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		lblFecha.setBounds(254, 11, 150, 21);
		pnlInformacion.add(lblFecha);

		lblCaja = new JLabel("CAJA: 01");
		lblCaja.setForeground(new Color(0, 64, 128));
		lblCaja.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		lblCaja.setBounds(327, 48, 77, 21);
		pnlInformacion.add(lblCaja);

		JSeparator sep1 = new JSeparator();
		sep1.setForeground(new Color(0, 64, 128));
		sep1.setBounds(20, 137, 393, 2);
		pnlTicket.add(sep1);

		pnlConsultaTicket = new JPanel();
		pnlConsultaTicket.setBackground(new Color(241, 237, 220));
		pnlConsultaTicket.setBounds(10, 150, 414, 236);
		pnlTicket.add(pnlConsultaTicket);
		pnlConsultaTicket.setLayout(null);

		tblConsultaTicket = new JTable();
		tblConsultaTicket.setRowHeight(22);
		tblConsultaTicket.setAutoCreateRowSorter(true);
		tblConsultaTicket.setBackground(new Color(232, 232, 232));
		tblConsultaTicket.setBorder(new LineBorder(new Color(0, 0, 0)));
		tblConsultaTicket.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblConsultaTicket.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		tblConsultaTicket.setBounds(22, 11, 369, 132);

		// Envolver la tabla en un JScrollPane para que sea desplazable
		JScrollPane scrollPane = new JScrollPane(tblConsultaTicket);
		scrollPane.setBounds(22, 11, 369, 132);
		scrollPane.setBorder(new LineBorder(new Color(0, 0, 0)));

		pnlConsultaTicket.add(scrollPane);

		JLabel lblTotal = new JLabel("TOTAL:");
		lblTotal.setForeground(new Color(0, 64, 128));
		lblTotal.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		lblTotal.setBounds(229, 200, 62, 19);
		pnlConsultaTicket.add(lblTotal);

		txtTotal = new JTextField();
		txtTotal.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		txtTotal.setEnabled(false);
		txtTotal.setBounds(301, 197, 86, 25);
		pnlConsultaTicket.add(txtTotal);
		txtTotal.setColumns(10);

		JSeparator sep2 = new JSeparator();
		sep2.setForeground(new Color(0, 64, 128));
		sep2.setBounds(20, 397, 393, 2);
		pnlTicket.add(sep2);

		lblCajero = new JLabel("LE ATENDIÓ: DENIS PEÑA");
		lblCajero.setForeground(new Color(0, 64, 128));
		lblCajero.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		lblCajero.setBounds(20, 415, 193, 29);
		pnlTicket.add(lblCajero);

		lblGracias = new JLabel("GRACIAS POR SU VISITA");
		lblGracias.setForeground(new Color(0, 64, 128));
		lblGracias.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		lblGracias.setBounds(20, 455, 183, 20);
		pnlTicket.add(lblGracias);

		btnImprimirTicket = new JButton("IMPRIMIR TICKET");
		btnImprimirTicket.setBackground(new Color(68, 119, 206));
		btnImprimirTicket.setForeground(new Color(232, 232, 232));
		btnImprimirTicket.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		btnImprimirTicket.setBounds(251, 426, 162, 41);
		pnlTicket.add(btnImprimirTicket);
	}
}
