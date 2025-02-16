package es.studium.practicaWindowBuilder;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

/**
 * Esta clase representa el modelo de la aplicación diseñada en MVC. Contiene la
 * lógica del programa.
 * 
 * @author Denis Peña
 * @version 1.0
 * @since 2025-02-12
 */
public class Modelo {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/tiendecitaDPM";
	String login = "adminTiendecita";
	String password = "studium";

	/**
	 * Conexión a la base de datos.
	 */
	Connection connection = null;
	/**
	 * Ejecutor de sentencias SQL.
	 */
	Statement statement = null;
	/**
	 * Objeto que guarda el resultado de SELECTS.
	 */
	ResultSet rs = null;

	/**
	 * Constructor vacío de la clase.
	 */
	public Modelo() {
	}

	/**
	 * Método para conectar el programa con la base de datos.
	 * 
	 * @return Devuelve true si la conexión se ha realizado con éxito, false en caso
	 *         contrario.
	 */
	public boolean conectar() {
		boolean conexionCorrecta = true;

		try {
			Class.forName(driver);
		} catch (ClassNotFoundException cnfe) {
			System.out.println("Error al cargar el driver.");
			conexionCorrecta = false;
		}

		try {
			connection = DriverManager.getConnection(url, login, password);
		} catch (SQLException sqle) {
			System.out.println("Error al tratar de conectar con la base de datos.");
			conexionCorrecta = false;
		}

		return conexionCorrecta;
	}

	/**
	 * Método para cerrar la conexión con la base de datos.
	 * 
	 */
	public void desconectar() {
		try {
			statement.close();
			connection.close();
		} catch (SQLException sqle) {
			System.out.println("Error al cerrar " + sqle.toString());
		}
	}

	/**
	 * Función que formatea las fechas al formato europeo.
	 * 
	 * @param fecha Fecha recibida de tipo LocalDate.
	 * @return Fecha formateada.
	 */
	public String formatearFecha(LocalDate fecha) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return fecha.format(formatter);
	}

	/**
	 * Método para dar formato MySQL a las fechas pasadas como parámetros y ejecutar
	 * sentencias SQL.
	 * 
	 * @param fecha Fecha recibida para dar formato.
	 * @return Fecha con formato MySQL.
	 */
	public String formatoMySQL(String fecha) {
		String[] fechaCambiada = fecha.split("/");
		String fechaFormateada = fechaCambiada[2] + "-" + fechaCambiada[1] + "-" + fechaCambiada[0];
		return fechaFormateada;
	}

	/**
	 * Función que valida las fechas.
	 * 
	 * @param fecha Fecha que será validada.
	 * @return Devuelve true si la fecha es correcta, false en caso contrario.
	 */
	public boolean validarFecha(String fecha) {
		try {
			SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
			formatoFecha.setLenient(false);
			formatoFecha.parse(fecha);

		} catch (java.text.ParseException e) {
			return false;
		}
		return true;
	}

	/**
	 * Método para rellenar la consulta de artículos (rellena una tabla con la
	 * info).
	 * 
	 * @param tablaArticulos La tabla a rellenar en la vista.
	 */
	public void rellenarTablaArticulos(JTable tablaArticulos) {
		String sentencia = "SELECT * FROM articulos;";

		// Modelo inicial con encabezados
		DefaultTableModel datosTabla = new DefaultTableModel(new String[] { " ID", "DESCRIPCIÓN", "PRECIO", "STOCK" },
				0 // Inicialmente sin filas
		);

		try {
			statement = connection.createStatement();
			rs = statement.executeQuery(sentencia);

			// Agregar filas al modelo dinámicamente
			while (rs.next()) {
				datosTabla.addRow(new Object[] { rs.getInt("idArticulo"), rs.getString("descripcionArticulo"),
						rs.getDouble("precioArticulo") + " €", rs.getInt("stockArticulo"), });
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al cargar los datos: " + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		tablaArticulos.setModel(datosTabla); // Asignar el modelo actualizado a la tabla
	}

	/**
	 * Método para rellenar la consulta de tickets (rellena una tabla con la info).
	 * 
	 * @param tablaDetallesTicket La tabla a rellenar en la vista.
	 * @param idTicketFK Id del ticket del que se quiere generar la consulta.
	 */
	public void rellenarTablaTickets(JTable tablaDetallesTicket, int idTicketFK) {
		String sentencia = "SELECT descripcionArticulo, precioArticulo, cantidadArticulo "
				+ "FROM detalles_tickets JOIN articulos ON detalles_tickets.idArticuloFK = articulos.idArticulo "
				+ "WHERE idTicketFK = " + idTicketFK + ";";

		// Modelo inicial con encabezados
		DefaultTableModel datosTabla = new DefaultTableModel(
				new String[] { "DESCRIPCIÓN", "CANTIDAD", "PRECIO", "IMPORTE" }, 0 // Inicialmente sin filas
		);

		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = statement.executeQuery(sentencia);

			// Agregar filas al modelo dinámicamente
			while (rs.next()) {
				int cantidad = rs.getInt("cantidadArticulo");
				Double precio = rs.getDouble("precioArticulo");

				Double importe = cantidad * precio;

				datosTabla.addRow(new Object[] { rs.getString("descripcionArticulo"), rs.getInt("cantidadArticulo"),
						rs.getDouble("precioArticulo") + " €", importe + " €" });
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al cargar los datos: " + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}

		// Asignar el modelo actualizado a la tabla
		tablaDetallesTicket.setModel(datosTabla);
	}

	/**
	 * Función que calcula el total de la compra realizada en el ticket.
	 * 
	 * @param idTicketFK Id de la compra que se quiere calcular el total.
	 * @return Importe total correspondiente al ticket.
	 */
	public String calcularTotalCompra(int idTicketFK) {
		Double resultado = 0.0;
		double redondeado = 0.0;
		String sentencia = "SELECT descripcionArticulo, precioArticulo, cantidadArticulo "
				+ "FROM detalles_tickets JOIN articulos ON detalles_tickets.idArticuloFK = articulos.idArticulo "
				+ "WHERE idTicketFK = " + idTicketFK + ";";

		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = statement.executeQuery(sentencia);

			while (rs.next()) {
				int cantidad = rs.getInt("cantidadArticulo");
				Double precio = rs.getDouble("precioArticulo");
				Double total = cantidad * precio;
				resultado += total;
			}

			BigDecimal bigDecimal = new BigDecimal(resultado);
			bigDecimal = bigDecimal.setScale(2, RoundingMode.HALF_UP); // Redondear a 2 decimales
			redondeado = bigDecimal.doubleValue();
		} catch (SQLException e) {
			System.out.println("Error en la sentencia SQL " + e.getMessage());
		}
		return redondeado + " €";
	}

	/**
	 * Función para dar de alta artículos.
	 * 
	 * @param desc   Descripción del artículo.
	 * @param precio Precio del artículo.
	 * @param stock  Cantidad del artículo.
	 * @return True si el alta se realizó correctamente, false en caso contrario.
	 */
	public boolean darAltaArticulo(String desc, float precio, int stock) {
		boolean altaCorrecta = true;
		String sentencia = "INSERT INTO articulos VALUES (NULL, '" + desc + "', " + precio + ", " + stock + ");";

		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			statement.executeUpdate(sentencia);
		} catch (SQLException e) {
			System.out.println("Error al realizar el alta.");
			altaCorrecta = false;
		}

		return altaCorrecta;
	}

	/**
	 * Función para actualzar artículos.
	 * 
	 * @param id     Id del artículo a actualizar.
	 * @param desc   Nueva descripción del artículo.
	 * @param precio Nuevo precio del artículo.
	 * @param stock  Nuevo stock del artículo.
	 * @return True si la actualización fue realizada correctamente.
	 */
	public boolean actualizarArticulo(int id, String desc, float precio, int stock) {
		boolean actualizacionCorrecta = true;
		String sentencia = "UPDATE articulos SET descripcionArticulo = '" + desc + "', precioArticulo = " + precio
				+ ", " + "stockArticulo = " + stock + " WHERE idArticulo = " + id + ";";

		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			statement.executeUpdate(sentencia);
		} catch (SQLException sqle) {
			System.out.println("Error al realizar el alta.");
			actualizacionCorrecta = false;
		}

		return actualizacionCorrecta;
	}

	/**
	 * Función para dar de baja artículos.
	 * 
	 * @param id Id del artículo a dar de baja.
	 * @return True si la baja se ha realizado correctamente, false en caso
	 *         contrario.
	 */
	public boolean bajaArticulo(int id) {
		boolean bajaCorrecta = true;
		String sentencia = "DELETE FROM articulos WHERE idArticulo = " + id + ";";

		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			statement.executeUpdate(sentencia);
		} catch (SQLException sqle) {
			System.out.println("Error al realizar el alta.");
			bajaCorrecta = false;
		}

		return bajaCorrecta;
	}

	/**
	 * Función que comprueba la existencia de un artículo.
	 * 
	 * @param descripcion Nombre del artículo a buscar.
	 * @return True si la búsqueda ha devuelto algo, false en caso contrario.
	 */
	public boolean comprobarExistencia(String descripcion) {
		String sentencia = "SELECT * FROM articulos WHERE descripcionArticulo = '" + descripcion + "';";
		boolean articuloExiste = false;

		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = statement.executeQuery(sentencia);

			if (rs.next()) {
				articuloExiste = true;
			}
		} catch (SQLException sqle) {
			System.out.println("Error en la sentencia SQL.");
		}

		return articuloExiste;
	}

	/**
	 * Función que rellena desplegables con artículos
	 * 
	 * @return Lista de artículos.
	 */
	public String[] rellenarListaArticulos() {
		String elementos = "Elige un artículo...*";
		String sentencia = "SELECT * FROM articulos;";

		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = statement.executeQuery(sentencia);

			while (rs.next()) {
				elementos += rs.getString("descripcionArticulo") + "*";
			}
		} catch (SQLException sqle) {
			System.out.println("Error en la sentencia SQL." + sqle.toString());
		}

		return elementos.split("\\*");
	}

	/**
	 * Función que rellena desplegables con tickets.
	 * 
	 * @return Lista de tickets.
	 */
	public String[] rellenarListaTickets() {
		String elementos = "Elige un ticket...*";
		String sentencia = "SELECT * FROM tickets;";

		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = statement.executeQuery(sentencia);

			while (rs.next()) {
				elementos += rs.getInt("idTicket") + "*";
			}
		} catch (SQLException sqle) {
			System.out.println("Error en la sentencia SQL." + sqle.toString());
		}

		return elementos.split("\\*");
	}

	/**
	 * Función para encontrar el precio de un artículo dada su descripción.
	 * 
	 * @param descripcion Nombre del artículo a buscar.
	 * @return Precio del artículo.
	 */
	public float encontrarPrecio(String descripcion) {
		float precio = 0.0f;
		String sentencia = "SELECT precioArticulo FROM articulos WHERE descripcionArticulo = '" + descripcion + "';";

		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = statement.executeQuery(sentencia);

			if (rs.next()) {
				precio = rs.getFloat("precioArticulo");
			} else {
				System.out.println("No se encontró el precio del artículo " + descripcion);
			}
		} catch (SQLException sqle) {
			System.out.println("Error en la sentencia SQL: " + sqle.toString());
		}

		return precio;
	}

	/**
	 * Método para crear nuevos tickets.
	 * 
	 * @return True si el alta del ticket se ha realizado correctamente, false en
	 *         caso contrario.
	 */
	public boolean darAltaTicket() {
		boolean altaCorrecta = true;
		LocalDate fechaHoy = LocalDate.now();
		String fechaActual = formatearFecha(fechaHoy);

		String sentencia = "INSERT INTO tickets VALUES (NULL, '" + formatoMySQL(fechaActual) + "')";

		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			statement.executeUpdate(sentencia);
		} catch (SQLException sqle) {
			System.out.println("Error en la sentencia SQL: " + sqle.toString());
			altaCorrecta = false;
		}

		return altaCorrecta;
	}

	/**
	 * Función para dar de alta registros en la tabla detalles_tickets. Actualiza si
	 * un artículo ya existe.
	 * 
	 * @param idTicket   Id del ticket.
	 * @param idArticulo Id del artículo.
	 * @param cantidad   Cantidad del artículo.
	 * @return True si el alta ha sido realizada correctamente, false en caso
	 *         contrario.
	 */
	public boolean darAltaDetallesTicket(int idTicket, int idArticulo, int cantidad) {
		boolean altaCorrecta = true;
		String sentenciaInsert = "INSERT INTO detalles_tickets VALUES (" + idTicket + ", " + idArticulo + ", "
				+ cantidad + ");";
		String sentenciaSelect = "SELECT cantidadArticulo FROM detalles_tickets WHERE idTicketFK = " + idTicket + " "
				+ "AND idArticuloFK = " + idArticulo + ";";
		String sentenciaUpdate = "";

		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

			if (comprobarArticuloComprado(idTicket, idArticulo) == false) {
				statement.executeUpdate(sentenciaInsert);
			} else {
				rs = statement.executeQuery(sentenciaSelect);
				int cantidadAntes = 0;

				while (rs.next()) {
					cantidadAntes = rs.getInt("cantidadArticulo");
				}

				int cantidadDespues = cantidadAntes + cantidad;

				sentenciaUpdate = "UPDATE detalles_tickets SET cantidadArticulo = " + cantidadDespues
						+ " WHERE idTicketFK = " + idTicket + " " + "AND idArticuloFK = " + idArticulo + ";";
				statement.executeUpdate(sentenciaUpdate);
			}
		} catch (SQLException sqle) {
			System.out.println("Error en la sentencia SQL: " + sqle.toString());
		}

		return altaCorrecta;
	}

	/**
	 * Función que comprueba si un artículo ya está asociado a un ticket.
	 * 
	 * @param idTicket   Id del ticket.
	 * @param idArticulo Id del artículo.
	 * @return True si el ticket ya ha registrado determinado artículo, false en
	 *         caso contrario.
	 */
	public boolean comprobarArticuloComprado(int idTicket, int idArticulo) {
		boolean siExiste = false;
		String sentencia = "SELECT * FROM detalles_tickets WHERE idTicketFK = " + idTicket + " AND idArticuloFK = "
				+ idArticulo + ";";

		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = statement.executeQuery(sentencia);

			if (rs.next()) {
				siExiste = true;
				System.out.println("El artículo ya ha sido añadido, se procederá a aumentar la cantidad del mismo.");
			}
		} catch (SQLException sqle) {
			System.out.println("Error en la sentencia SQL: " + sqle.toString());
		}

		return siExiste;
	}

	/**
	 * Función que devuelve el último ticket creado.
	 * 
	 * @return Último ticket creado.
	 */
	public int idTicket() {
		int idTicket = 0;
		String sentencia = "SELECT MAX(idTicket) AS maxId FROM tickets;";

		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = statement.executeQuery(sentencia);

			if (rs.next()) {
				idTicket = rs.getInt("maxId");
			} else {
				System.out.println("No se encontró el id del ticket.");
			}
		} catch (SQLException sqle) {
			System.out.println("Error en la sentencia SQL: " + sqle.toString());
		}

		return idTicket;
	}

	/**
	 * Función que devuelve el id de artículo.
	 * 
	 * @param articulo Nombre del artículo.
	 * @return Id del artículo.
	 */
	public int idArticuloFK(String articulo) {
		int id = 0;
		String sentencia = "SELECT idArticulo FROM articulos WHERE descripcionArticulo = '" + articulo + "';";

		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = statement.executeQuery(sentencia);

			if (rs.next()) {
				id = rs.getInt("idArticulo");
			} else {
				System.out.println("El id del artículo " + articulo + " no ha sido encontrado.");
			}
		} catch (SQLException sqle) {
			System.out.println("Error en la sentencia SQL: " + sqle.toString());
		}

		return id;
	}

	/**
	 * Función que devuelve la fecha de creación de un ticket.
	 * 
	 * @param idTicket Id del ticket.
	 * @return Fecha del ticket.
	 */
	public String obtenerFechaTicket(int idTicket) {
		String fecha = "";
		String sentencia = "SELECT fechaTicket FROM tickets WHERE idTicket = " + idTicket + ";";

		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = statement.executeQuery(sentencia);

			while (rs.next()) {
				fecha = rs.getString("fechaTicket");
			}
		} catch (SQLException sqle) {
			System.out.println("Error en la sentencia SQL: " + sqle.toString());
		}

		return fecha;
	}

	/**
	 * Función que devuelve la lista de los artículos comprados por un ticket.
	 * 
	 * @param idTicket Id del ticket.
	 * @return Lista de artículos del ticket.
	 */
	public String obtenerArticulosComprados(int idTicket) {
		String datos = "";
		String sentencia = "SELECT descripcionArticulo, precioArticulo, cantidadArticulo"
				+ "	FROM detalles_tickets JOIN articulos ON detalles_tickets.idArticuloFK = articulos.idArticulo"
				+ " WHERE idTicketFK = " + idTicket + ";";

		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = statement.executeQuery(sentencia);

			while (rs.next()) {
				int cantidad = rs.getInt("cantidadArticulo");
				Double precio = rs.getDouble("precioArticulo");

				Double importe = cantidad * precio;

				datos += rs.getString("descripcionArticulo") + "-" + rs.getInt("cantidadArticulo") + "-"
						+ rs.getDouble("precioArticulo") + " €" + "-" + importe + " €" + "\n";
			}
		} catch (SQLException e) {
			System.out.println("Error al cargar los datos en la tabla.");
		}

		return datos;
	}

	/**
	 * Método para añadir contenido a una tabla de un fichero PDF.
	 * 
	 * @param tabla      Tabla a rellenar.
	 * @param linea      Línea de datos a insertar en la tabla.
	 * @param fuente     Fuente a utilizar en dependencia del tipo de línea.
	 * @param esCabecera Tipo de línea. True si es cabecera, false en caso
	 *                   contrario.
	 */
	public void process(Table tabla, String linea, PdfFont fuente, boolean esCabecera) {
		StringTokenizer tokenizer = new StringTokenizer(linea, "-");
		while (tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken();
			Cell cell = new Cell().add(new Paragraph(token).setFont(fuente));
			if (esCabecera) {
				tabla.addHeaderCell(cell); // Añadir celda como encabezado
			} else {
				tabla.addCell(cell); // Añadir celda normal
			}
		}
	}
}