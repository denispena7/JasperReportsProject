package es.studium.practicaWindowBuilder;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

public class CrudArticulos extends JFrame {

	private static final long serialVersionUID = 1L;
	JPanel contentPane;
	JTextField txtDescripcion;
	JTextField txtPrecio;
	JTextField txtStock;
	JTable tblConsultaArticulos;
	
	JButton btnAgregar;
	JButton btnActualizar;
	JButton btnSeleccionar;
	JButton btnEliminar;
	
	Modelo datos = new Modelo();
	
	/**
	 * Create the frame.
	 */
	public CrudArticulos() {
		setTitle("CRUD ARTÍCULOS");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 449, 585);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(241, 237, 220));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pnlFormulario = new JPanel();
		pnlFormulario.setBackground(new Color(241, 237, 220));
		pnlFormulario.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "DATOS", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 64, 128)));
		pnlFormulario.setBounds(20, 11, 392, 253);
		contentPane.add(pnlFormulario);
		pnlFormulario.setLayout(null);
		
		JLabel lblDescripcion = new JLabel("DESCRIPCIÓN");
		lblDescripcion.setForeground(new Color(0, 64, 128));
		lblDescripcion.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		lblDescripcion.setBounds(23, 36, 124, 28);
		pnlFormulario.add(lblDescripcion);
		
		JLabel lblPrecio = new JLabel("PRECIO");
		lblPrecio.setForeground(new Color(0, 64, 128));
		lblPrecio.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		lblPrecio.setBounds(23, 86, 80, 19);
		pnlFormulario.add(lblPrecio);
		
		JLabel lblStock = new JLabel("STOCK");
		lblStock.setForeground(new Color(0, 64, 128));
		lblStock.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		lblStock.setBounds(23, 133, 61, 18);
		pnlFormulario.add(lblStock);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		txtDescripcion.setBounds(168, 38, 201, 28);
		pnlFormulario.add(txtDescripcion);
		txtDescripcion.setColumns(10);
		
		txtPrecio = new JTextField();
		txtPrecio.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		txtPrecio.setBounds(168, 83, 201, 28);
		pnlFormulario.add(txtPrecio);
		txtPrecio.setColumns(10);
		
		txtStock = new JTextField();
		txtStock.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		txtStock.setBounds(168, 130, 201, 28);
		pnlFormulario.add(txtStock);
		txtStock.setColumns(10);
		
		btnAgregar = new JButton("AGREGAR");
		btnAgregar.setForeground(new Color(232, 232, 232));
		btnAgregar.setBackground(new Color(68, 119, 206));
		btnAgregar.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		btnAgregar.setBounds(23, 181, 166, 41);
		pnlFormulario.add(btnAgregar);
		
		btnActualizar = new JButton("ACTUALIZAR");
		btnActualizar.setForeground(new Color(232, 232, 232));
		btnActualizar.setBackground(new Color(68, 119, 206));
		btnActualizar.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		btnActualizar.setBounds(203, 181, 166, 41);
		pnlFormulario.add(btnActualizar);
		
		JPanel panel = new JPanel();
		panel.setForeground(new Color(0, 0, 0));
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "CONSULTA", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 64, 128)));
		panel.setBackground(new Color(241, 237, 220));
		panel.setBounds(20, 277, 392, 258);
		contentPane.add(panel);
		panel.setLayout(null);
		
		
		tblConsultaArticulos = new JTable();
		tblConsultaArticulos.setRowHeight(22);
		tblConsultaArticulos.setAutoCreateRowSorter(true);
		tblConsultaArticulos.setBackground(new Color(240, 240, 240));
		tblConsultaArticulos.setBorder(new LineBorder(new Color(0, 0, 0)));
		tblConsultaArticulos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblConsultaArticulos.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		tblConsultaArticulos.setBounds(26, 37, 339, 132);
		
		datos.conectar();
		datos.rellenarTablaArticulos(tblConsultaArticulos);
		datos.desconectar();
		
		ajustarAnchoColumnas(tblConsultaArticulos);
		
		centrarContenidoTabla(tblConsultaArticulos);
		
	    // Envolver la tabla en un JScrollPane para que sea desplazable
	    JScrollPane scrollPane = new JScrollPane(tblConsultaArticulos);
	    scrollPane.setBounds(26, 37, 339, 132); // Ajusta el tamaño del área visible según tu diseño
	    scrollPane.setBorder(new LineBorder(new Color(0, 0, 0)));

	    // Agregar el JScrollPane a tu panel o contenedor
	    panel.add(scrollPane);
		
		btnSeleccionar = new JButton("SELECCIONAR");
		btnSeleccionar.setForeground(new Color(241, 237, 242));
		btnSeleccionar.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		btnSeleccionar.setBackground(new Color(68, 119, 206));
		btnSeleccionar.setBounds(23, 194, 166, 41);
		panel.add(btnSeleccionar);
		
		btnEliminar = new JButton("ELIMINAR");
		btnEliminar.setForeground(new Color(241, 237, 242));
		btnEliminar.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		btnEliminar.setBackground(new Color(68, 119, 206));
		btnEliminar.setBounds(199, 194, 166, 41);
		panel.add(btnEliminar);
	}
	
	public void ajustarAnchoColumnas(JTable tabla) {
	    // Obtener el modelo de las columnas
	    TableColumnModel columnModel = tabla.getColumnModel();

	    for (int col = 0; col < tabla.getColumnCount(); col++) {
	        int maxWidth = 0; // Ancho máximo para la columna

	        // Iterar sobre todas las filas para encontrar la más ancha
	        for (int row = 0; row < tabla.getRowCount(); row++) {
	            TableCellRenderer renderer = tabla.getCellRenderer(row, col);
	            Component comp = tabla.prepareRenderer(renderer, row, col);
	            maxWidth = Math.max(comp.getPreferredSize().width, maxWidth);
	        }

	        // Incluir el ancho del encabezado de la columna
	        TableCellRenderer headerRenderer = tabla.getTableHeader().getDefaultRenderer();
	        Component headerComp = headerRenderer.getTableCellRendererComponent(
	                tabla, tabla.getColumnName(col), false, false, 0, col);
	        maxWidth = Math.max(headerComp.getPreferredSize().width, maxWidth);

	        // Establecer el ancho preferido para la columna
	        columnModel.getColumn(col).setPreferredWidth(maxWidth + 10); // +10 para espacio adicional
	    }
	}
	
	public void centrarContenidoTabla(JTable tabla)
	{
		DefaultTableCellRenderer centrador = new DefaultTableCellRenderer();
		centrador.setHorizontalAlignment(SwingConstants.CENTER);
		
		// Asignar el renderizador a todas las columnas de la tabla
		TableColumnModel columnModel = tabla.getColumnModel();
		for (int i = 0; i < columnModel.getColumnCount(); i++)
		{
			columnModel.getColumn(i).setCellRenderer(centrador);
		}
	}
	
	public void limpiar()
	{
		txtDescripcion.setText("");
		txtPrecio.setText("");
		txtStock.setText("");
	}
}
