package es.studium.practicaWindowBuilder;

import java.awt.Desktop;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * Esta clase se ocupa de generar informe con JasperReports de los tickets
 * registrados.
 * 
 * @author Denis Pe�a
 * @version 1.0
 * @since 2025-02-12
 */
public class InformeTicketsJasperSoft {
	/**
	 * Constructor por par�metros de la clase.
	 * 
	 * @param fDesde Fecha a partir de la cual obtener los tickets.
	 * @param fHasta Fecha hasta la cual obtener los tickets.
	 * @throws Exception Si algo malo ocurre.
	 */
	public InformeTicketsJasperSoft(String fDesde, String fHasta) {
		try {
			// Compilar el informe generando fichero.jasper
			JasperCompileManager.compileReportToFile("./src/main/resources/InformeTickets.jrxml");
			System.out.println("Fichero InformeTickets.jasper generado CORRECTAMENTE!");

			// Objeto para guardar par�metros necesarios para el informe.
			// Como ejemplo usamos dos par�metros autor y titulo
			// D�ndole valores fijos a los par�metros del informe.
			HashMap<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("fechaDesde", fDesde);
			parametros.put("fechaHasta", fHasta);

			// Cargar el informe compilado
			JasperReport report = (JasperReport) JRLoader
					.loadObjectFromFile("./src/main/resources/InformeTickets.jasper");

			// Conectar a la base de datos para sacar la informaci�n
			Class.forName("com.mysql.cj.jdbc.Driver");
			String servidor = "jdbc:mysql://localhost:3306/tiendecitaDPM";
			String usuarioDB = "root";
			String passwordDB = "Studium2024;";
			Connection conexion = DriverManager.getConnection(servidor, usuarioDB, passwordDB);

			// Completar el informe con los datos de la base de datos
			JasperPrint print = JasperFillManager.fillReport(report, parametros, conexion);

			// Para exportarlo a pdf
			JasperExportManager.exportReportToPdfFile(print, "./src/main/resources/InformeTickets.pdf");

			// Abrir el fichero PDF generado
			File path = new File("./src/main/resources/InformeTickets.pdf");

			// Abrir el fichero PDF generado
			Desktop.getDesktop().open(path);
			System.out.println("Fichero InformeTickets.pdf generado CORRECTAMENTE!");
		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
		}
	}
}