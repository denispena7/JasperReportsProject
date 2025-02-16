package es.studium.practicaWindowBuilder;

import java.awt.Color;
import java.awt.Font;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

/**
 * Esta clase representa la vista del CRUD de los tickets.
 * 
 * @author Denis Peña
 * @version 1.0
 * @since 2025-02-12
 */
public class CrudTickets extends JFrame {
	/**
	 * @serial
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JTextField txtPrecio;
	JTextField txtCantidad;
	JTextField txtImporte;
	JTable tblDelTicket;
	JTextField txtTotal;

	JLabel lblFechaTicket;
	JPanel panel;
	JLabel lblArticulo;
	JLabel lblPrecio;
	JLabel lblCantidad;
	JLabel lblImporte;
	JScrollPane scrollPane;
	JLabel lblTotal;

	JPanel panelTabla;

	JButton btnAgregarArticulo;
	JButton btnFinalizar;

	JComboBox<String> comboBox;

	/**
	 * Instancia del Modelo del programa.
	 */
	Modelo datos = new Modelo();

	/**
	 * Constructor vacío de la clase.
	 */
	public CrudTickets() {
		setTitle("TICKET Nº 1");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 549, 632);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(241, 237, 220));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		LocalDate fechaHoy = LocalDate.now();
		String fechaActual = datos.formatearFecha(fechaHoy);

		lblFechaTicket = new JLabel("FECHA: " + fechaActual);
		lblFechaTicket.setForeground(new Color(0, 64, 128));
		lblFechaTicket.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		lblFechaTicket.setBounds(357, 11, 152, 21);
		contentPane.add(lblFechaTicket);

		panel = new JPanel();
		panel.setBackground(new Color(241, 237, 220));
		panel.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "DATOS",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 64, 128)));
		panel.setBounds(20, 48, 489, 240);
		contentPane.add(panel);
		panel.setLayout(null);

		lblArticulo = new JLabel("ARTÍCULO");
		lblArticulo.setForeground(new Color(0, 64, 128));
		lblArticulo.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		lblArticulo.setBounds(37, 31, 93, 32);
		panel.add(lblArticulo);

		lblPrecio = new JLabel("PRECIO");
		lblPrecio.setForeground(new Color(0, 64, 128));
		lblPrecio.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		lblPrecio.setBounds(37, 74, 74, 32);
		panel.add(lblPrecio);

		lblCantidad = new JLabel("CANTIDAD");
		lblCantidad.setForeground(new Color(0, 64, 128));
		lblCantidad.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		lblCantidad.setBounds(37, 119, 93, 32);
		panel.add(lblCantidad);

		lblImporte = new JLabel("IMPORTE");
		lblImporte.setForeground(new Color(0, 64, 128));
		lblImporte.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		lblImporte.setBounds(278, 119, 86, 32);
		panel.add(lblImporte);

		txtPrecio = new JTextField();
		txtPrecio.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		txtPrecio.setEnabled(false);
		txtPrecio.setBounds(160, 83, 300, 25);
		panel.add(txtPrecio);
		txtPrecio.setColumns(10);

		txtCantidad = new JTextField();
		txtCantidad.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		txtCantidad.setBounds(160, 126, 86, 25);
		panel.add(txtCantidad);
		txtCantidad.setColumns(10);

		txtImporte = new JTextField();
		txtImporte.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		txtImporte.setEnabled(false);
		txtImporte.setBounds(374, 126, 86, 25);
		panel.add(txtImporte);
		txtImporte.setColumns(10);

		datos.conectar();
		String[] elementos = datos.rellenarListaArticulos();

		comboBox = new JComboBox<String>(elementos);
		datos.desconectar();

		comboBox.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		comboBox.setMaximumRowCount(4);

		comboBox.setBounds(160, 39, 300, 25);
		panel.add(comboBox);

		btnAgregarArticulo = new JButton("AGREGAR ARTÍCULO");
		btnAgregarArticulo.setForeground(new Color(232, 232, 232));
		btnAgregarArticulo.setBackground(new Color(68, 119, 206));
		btnAgregarArticulo.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		btnAgregarArticulo.setBounds(37, 175, 423, 41);
		panel.add(btnAgregarArticulo);

		panelTabla = new JPanel();
		panelTabla.setBackground(new Color(241, 237, 220));
		panelTabla.setBorder(new TitledBorder(null, "DETALLES", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 64, 128)));
		panelTabla.setBounds(20, 314, 489, 254);
		contentPane.add(panelTabla);
		panelTabla.setLayout(null);

		tblDelTicket = new JTable();
		tblDelTicket.setRowHeight(22);
		tblDelTicket.setAutoCreateRowSorter(true);
		tblDelTicket.setBackground(new Color(240, 240, 240));
		tblDelTicket.setBorder(new LineBorder(new Color(0, 0, 0)));
		tblDelTicket.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblDelTicket.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		tblDelTicket.setBounds(33, 39, 416, 88);

		// Envolver la tabla en un JScrollPane para que sea desplazable
		scrollPane = new JScrollPane(tblDelTicket);
		scrollPane.setBounds(33, 39, 416, 88);
		scrollPane.setBorder(new LineBorder(new Color(0, 0, 0)));

		panelTabla.add(scrollPane);

		lblTotal = new JLabel("TOTAL");
		lblTotal.setForeground(new Color(0, 64, 128));
		lblTotal.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		lblTotal.setBounds(295, 154, 64, 26);
		panelTabla.add(lblTotal);

		txtTotal = new JTextField();
		txtTotal.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		txtTotal.setEnabled(false);
		txtTotal.setBounds(368, 155, 86, 25);
		panelTabla.add(txtTotal);
		txtTotal.setColumns(10);

		btnFinalizar = new JButton("FINALIZAR COMPRA");
		btnFinalizar.setForeground(new Color(232, 232, 232));
		btnFinalizar.setBackground(new Color(68, 119, 206));
		btnFinalizar.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		btnFinalizar.setBounds(33, 191, 423, 41);
		panelTabla.add(btnFinalizar);
	}

	/**
	 * Método que limpia la tabla.
	 * 
	 * @param tabla Tabla a limpiar.
	 */
	public void limpiar(JTable tabla) {
		comboBox.setSelectedIndex(0);
		txtCantidad.setText("");
		txtPrecio.setText("");
		txtImporte.setText("");
		txtTotal.setText("");

		// Crear un modelo vacío con los encabezados originales
		DefaultTableModel modeloVacio = new DefaultTableModel(
				new String[] { "DESCRIPCIÓN", "PRECIO", "CANTIDAD", "IMPORTE" }, 0 // Sin filas
		);

		// Asignar el modelo vacío a la tabla
		tabla.setModel(modeloVacio);

	}
}
