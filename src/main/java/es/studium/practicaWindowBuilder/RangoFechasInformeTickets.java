package es.studium.practicaWindowBuilder;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;

/**
 * Esta clase representa la vista que pregunta el rango de fechas para generar
 * el informe de tickets.
 * 
 * @author Denis Peña
 * @version 1.0
 * @since 2025-02-12
 */
public class RangoFechasInformeTickets extends JDialog {
	/**
	 * @serial
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	JTextField txtFechaDesde;
	JTextField txtFechaHasta;
	JButton btnGenerarInforme;

	/**
	 * Constructor vacío de la clase.
	 */
	public RangoFechasInformeTickets() {
		setTitle("Consulta Tickets");
		setBounds(100, 100, 357, 236);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(241, 237, 220));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblFechaDesde = new JLabel("FECHA DESDE");
			lblFechaDesde.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
			lblFechaDesde.setBounds(44, 43, 111, 13);
			contentPanel.add(lblFechaDesde);
		}
		{
			JLabel lblFechaHasta = new JLabel("FECHA HASTA");
			lblFechaHasta.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
			lblFechaHasta.setBounds(44, 91, 111, 13);
			contentPanel.add(lblFechaHasta);
		}
		{
			btnGenerarInforme = new JButton("ACEPTAR");
			btnGenerarInforme.setForeground(new Color(232, 232, 232));
			btnGenerarInforme.setBackground(new Color(68, 119, 206));
			btnGenerarInforme.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
			btnGenerarInforme.setBounds(44, 132, 255, 41);
			contentPanel.add(btnGenerarInforme);
		}

		txtFechaDesde = new JTextField();
		txtFechaDesde.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		txtFechaDesde.setBounds(179, 35, 120, 30);
		contentPanel.add(txtFechaDesde);
		txtFechaDesde.setColumns(10);

		txtFechaHasta = new JTextField();
		txtFechaHasta.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		txtFechaHasta.setBounds(179, 85, 120, 30);
		contentPanel.add(txtFechaHasta);
		txtFechaHasta.setColumns(10);
	}
}
