package es.studium.practicaWindowBuilder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;

import javax.swing.JOptionPane;

public class Controlador implements ActionListener, ItemListener
{
	Modelo modelo;
	// Vistas
	MenuPrincipal menu;
	CrudArticulos articulos = new CrudArticulos();
	MenuCrudTickets menuCrudT = new MenuCrudTickets();
	CrudTickets tickets = new CrudTickets();
	DialogoAltaTicket dlgAltaTicket = new DialogoAltaTicket();
	ConsultaTickets conTickets = new ConsultaTickets();

	// Dialogos de confirmación
	DialogoArticuloAltaConfirmada dlgAltaCorrecta = new DialogoArticuloAltaConfirmada();
	DialogoTicketAltaConfirmada dlgAltaTicketCorrecta = new DialogoTicketAltaConfirmada();
	DialogoArticuloActConfirmada dlgActualizaciónCorrecta= new DialogoArticuloActConfirmada();
	DialogoArticuloBajaConfirmacion dlgBajaCorrecta = new DialogoArticuloBajaConfirmacion();
	DialogoPreguntaBajaArticulos dlgPreguntaBaja = new DialogoPreguntaBajaArticulos();

	public Controlador(Modelo m, MenuPrincipal menuP)
	{
		modelo = m;
		menu = menuP;

		// Botones del menú principal
		menuP.btnArticulos.addActionListener(this);
		menuP.btnTickets.addActionListener(this);

		// Botones de la vista del crud de artículos
		articulos.btnAgregar.addActionListener(this);
		articulos.btnActualizar.addActionListener(this);
		articulos.btnSeleccionar.addActionListener(this);
		articulos.btnEliminar.addActionListener(this);

		// Botones de elección de baja
		dlgPreguntaBaja.btnSi.addActionListener(this);
		dlgPreguntaBaja.btnNo.addActionListener(this);

		// Botones del menu del crud de tickets
		menuCrudT.btnAltaTicket.addActionListener(this);
		menuCrudT.btnElegirTicket.addActionListener(this);

		// Botones de confirmacion para el alta de los tickets
		dlgAltaTicket.btnSiguiente.addActionListener(this);
		dlgAltaTicket.btnVolver.addActionListener(this);

		// Botones de la vista del crud de tickets
		tickets.btnAgregarArticulo.addActionListener(this);
		tickets.btnFinalizar.addActionListener(this);

		// JComboBox de articulos
		tickets.comboBox.addItemListener(this);

		// Botón para imprimir tickets
		conTickets.btnImprimirTicket.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		// Botón que lleva al crud de los artículos
		if(e.getSource().equals(menu.btnArticulos))
		{
			articulos.txtDescripcion.setText("");
			articulos.txtPrecio.setText("");
			articulos.txtStock.setText("");
			articulos.tblConsultaArticulos.clearSelection();
			articulos.setVisible(true);
		}
		// Botón que abre el menú de tickets (altas y consultas como opciones)
		else if(e.getSource().equals(menu.btnTickets))
		{
			menuCrudT.setVisible(true);
		}
		// Botón que abre un cuadro de diálogo que pide la confirmación del alta del ticket
		else if(e.getSource().equals(menuCrudT.btnAltaTicket))
		{
			dlgAltaTicket.setVisible(true);
		}
		// Botón que elige el ticket una vez seleccionado con el desplegable y muestra la consulta 
		else if(e.getSource().equals(menuCrudT.btnElegirTicket))
		{
			if(menuCrudT.listaTickets.getSelectedIndex() != 0)
			{
				int id = Integer.parseInt(menuCrudT.listaTickets.getSelectedItem().toString());
				conTickets.setTitle("TICKET " + id);

				modelo.conectar();

				modelo.rellenarTablaTickets(conTickets.tblConsultaTicket, id);
				articulos.centrarContenidoTabla(conTickets.tblConsultaTicket);
				articulos.ajustarAnchoColumnas(conTickets.tblConsultaTicket);

				String fechaString = modelo.obtenerFechaTicket(id);
				LocalDate fecha = LocalDate.parse(fechaString);
				String fechaTicket = modelo.formatearFecha(fecha);

				conTickets.lblFecha.setText("Fecha: " + fechaTicket);
				conTickets.txtTotal.setText(modelo.calcularTotalCompra(id));
				modelo.desconectar();	

				conTickets.setVisible(true);				
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Selecciona un ticket válido", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		// Botón que REALIZA EL ALTA DEL TICKET y muestra la venta para sumar artículos al ticket
		else if(e.getSource().equals(dlgAltaTicket.btnSiguiente))
		{
			// Dar el alta de TICKET
			modelo.conectar();
			modelo.darAltaTicket();

			// Actualizar lista de tickets
			menuCrudT.listaTickets.removeAllItems();
			String[] elementos = modelo.rellenarListaTickets();

			for(String elemento: elementos)
			{
				menuCrudT.listaTickets.addItem(elemento);				
			}

			dlgAltaTicket.setVisible(false);

			tickets.setTitle("TICKET " + modelo.idTicket());
			tickets.limpiar(tickets.tblDelTicket);
			tickets.setVisible(true);

			modelo.desconectar();
		}
		// Botón para volver al menú del crud de los tickets
		else if(e.getSource().equals(dlgAltaTicket.btnVolver))
		{
			dlgAltaTicket.setVisible(false);
		}
		// Botón que selecciona una fila de la tabla
		else if(e.getSource().equals(articulos.btnSeleccionar))
		{			
			int filaSeleccionada = articulos.tblConsultaArticulos.getSelectedRow(); // Obtiene la fila seleccionada

			if (filaSeleccionada >= 0)
			{ 
				// Verifica que una fila esté seleccionada
				String descripcion = articulos.tblConsultaArticulos.getValueAt(filaSeleccionada, 1) + "".trim();
				String precio = articulos.tblConsultaArticulos.getValueAt(filaSeleccionada, 2) + "".trim();
				String stock = articulos.tblConsultaArticulos.getValueAt(filaSeleccionada, 3) + "".trim();

				articulos.txtDescripcion.setText(descripcion);
				articulos.txtPrecio.setText(precio);
				articulos.txtStock.setText(stock);
			} 
			else 
			{
				JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna fila.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		// Botón que da de alta artículos
		else if(e.getSource().equals(articulos.btnAgregar))
		{
			try
			{
				String descripcion = articulos.txtDescripcion.getText();

				if(articulos.txtDescripcion.getText().equals("") || articulos.txtPrecio.getText().equals("")
						|| articulos.txtStock.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Tienes que rellenar todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					modelo.conectar();
					if(modelo.comprobarExistencia(descripcion))
					{
						JOptionPane.showMessageDialog(null, "El artículo ya existe.", "Error", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						float precio = Float.parseFloat(articulos.txtPrecio.getText().split(" ")[0]);
						int stock = Integer.parseInt(articulos.txtStock.getText());

						modelo.darAltaArticulo(descripcion, precio, stock);
						modelo.rellenarTablaArticulos(articulos.tblConsultaArticulos);
						articulos.centrarContenidoTabla(articulos.tblConsultaArticulos);
						articulos.ajustarAnchoColumnas(articulos.tblConsultaArticulos);
						articulos.limpiar();						
					}
					modelo.desconectar();

					// Mostrar dlg de confirmación
					dlgAltaCorrecta.setVisible(true);
				}
			}
			catch(NumberFormatException nfe)
			{
				JOptionPane.showMessageDialog(null, "Formato de entrada no válido.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		// Botón que actualiza un artículo
		else if(e.getSource().equals(articulos.btnActualizar))
		{
			try
			{
				String descripcion = articulos.txtDescripcion.getText();

				if(articulos.txtDescripcion.getText().equals("") || articulos.txtPrecio.getText().equals("")
						|| articulos.txtStock.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Los campos deben corresponder a un registro de artículo.", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					int id = Integer.parseInt(articulos.tblConsultaArticulos.getValueAt(articulos.tblConsultaArticulos.getSelectedRow(), 0) + "".trim());
					float precio = Float.parseFloat(articulos.txtPrecio.getText().split(" ")[0]);
					int stock = Integer.parseInt(articulos.txtStock.getText());

					modelo.conectar();
					modelo.actualizarArticulo(id, descripcion, precio, stock);
					modelo.rellenarTablaArticulos(articulos.tblConsultaArticulos);
					articulos.centrarContenidoTabla(articulos.tblConsultaArticulos);
					articulos.ajustarAnchoColumnas(articulos.tblConsultaArticulos);
					modelo.desconectar();

					articulos.limpiar();

					// Mostrar dlg de confirmación
					dlgActualizaciónCorrecta.setVisible(true);
				}
			}
			catch(NumberFormatException nfe)
			{
				JOptionPane.showMessageDialog(null, "Formato de entrada no válido.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		// Botón para eliminar artículos
		else if(e.getSource().equals(articulos.btnEliminar))
		{
			try
			{
				if(articulos.txtDescripcion.getText().equals("") && articulos.txtPrecio.getText().equals("")
						&& articulos.txtStock.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Los campos deben corresponder a un registro de artículo.", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					dlgPreguntaBaja.setVisible(true);
				}
			}
			catch(NumberFormatException nfe)
			{
				JOptionPane.showMessageDialog(null, "El id especificado no es válido.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		// Botón que ELIMINA UN TICKET
		else if(e.getSource().equals(dlgPreguntaBaja.btnSi))
		{
			int id = Integer.parseInt(articulos.tblConsultaArticulos.getValueAt(articulos.tblConsultaArticulos.getSelectedRow(), 0) + "".trim());

			modelo.conectar();
			modelo.bajaArticulo(id);
			modelo.rellenarTablaArticulos(articulos.tblConsultaArticulos);
			articulos.centrarContenidoTabla(articulos.tblConsultaArticulos);
			articulos.ajustarAnchoColumnas(articulos.tblConsultaArticulos);
			modelo.desconectar();

			articulos.limpiar();

			// Mostrar dlg de confirmación
			dlgPreguntaBaja.setVisible(false);
			dlgBajaCorrecta.setVisible(true);

		}
		// Botón que cancela la baja del artículo
		else if(e.getSource().equals(dlgPreguntaBaja.btnNo))
		{
			dlgPreguntaBaja.setVisible(false);
		}
		// Botón que agrega un artículo al ticket
		else if(e.getSource().equals(tickets.btnAgregarArticulo))
		{
			// Dar el alta de Detalles_Tickets
			try
			{
				String articulo = tickets.comboBox.getSelectedItem().toString();

				if(tickets.comboBox.getSelectedIndex() == 0 || tickets.txtPrecio.getText().equals("") || tickets.txtCantidad.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Tienes que rellenar todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					modelo.conectar();

					float precio = Float.parseFloat(tickets.txtPrecio.getText().split(" ")[0]);
					int cantidad = Integer.parseInt(tickets.txtCantidad.getText());

					modelo.darAltaDetallesTicket(modelo.idTicket(), modelo.idArticuloFK(articulo), cantidad);
					modelo.rellenarTablaTickets(tickets.tblDelTicket, modelo.idTicket());
					articulos.centrarContenidoTabla(tickets.tblDelTicket);
					articulos.ajustarAnchoColumnas(tickets.tblDelTicket);
					tickets.txtImporte.setText(precio * cantidad + " €");
					tickets.txtTotal.setText(modelo.calcularTotalCompra(modelo.idTicket()));
					modelo.desconectar();			

				}
			}
			catch(NumberFormatException nfe)
			{
				JOptionPane.showMessageDialog(null, "Formato de entrada no válido.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		// Botón para finalizar la compra
		else if(e.getSource().equals(tickets.btnFinalizar))
		{
			// Mostra dlg de confirmación
			dlgAltaTicketCorrecta.setVisible(true);
			tickets.limpiar(tickets.tblDelTicket);
			tickets.setVisible(false);
		}
		// Botón para imprimir el ticket
		else if(e.getSource().equals(conTickets.btnImprimirTicket))
		{
			// Imprimir el ticket
			int id = Integer.parseInt(menuCrudT.listaTickets.getSelectedItem().toString());
			Double total = Double.parseDouble(conTickets.txtTotal.getText().split(" ")[0]);

			modelo.conectar();

			String fechaString = modelo.obtenerFechaTicket(id);
			LocalDate fecha = LocalDate.parse(fechaString);
			String fechaTicket = modelo.formatearFecha(fecha);

			new ImprimirTicket("Ticket " + id + ".pdf", id, fechaTicket, total);
			modelo.desconectar();
			System.out.println("Fichero creado.");
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) 
	{
		// Si se selecciona un artículo, se colocará el precio respectivo en un txt
		if(e.getStateChange() == ItemEvent.SELECTED)
		{
			modelo.conectar();
			String articulo = e.getItem().toString();
			tickets.txtPrecio.setText(modelo.encontrarPrecio(articulo) + " €");
			modelo.desconectar();
		}
	}
}