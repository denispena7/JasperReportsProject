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

public class InformeArticulosJasperSoft 
{
	public InformeArticulosJasperSoft()
	{
		try
		{
			// Compilar el informe generando fichero jasper
			JasperCompileManager.compileReportToFile("./src/main/resources/InformeArticulos.jrxml");
			System.out.println("Fichero InformeArticulos.jasper generado CORRECTAMENTE!");

			// Objeto para guardar parámetros necesarios para el informe
			HashMap<String,Object> parametros = new HashMap<String,Object>();
			
			// Cargar el informe compilado
			JasperReport report = (JasperReport) 
					JRLoader.loadObjectFromFile("./src/main/resources/InformeArticulos.jasper");
			
			// Conectar a la base de datos para sacar la información
			Class.forName("com.mysql.cj.jdbc.Driver");
			String servidor = "jdbc:mysql://localhost:3306/tiendecitaDPM?useSSL=false";
			String usuarioDB = "root";
			String passwordDB = "Studium2024;";
			Connection conexion = DriverManager.getConnection(servidor, usuarioDB, passwordDB);
			
			// Completar el informe con los datos de la base de datos
			JasperPrint print = JasperFillManager.fillReport(report, parametros, conexion);

			// Para exportarlo a pdf
			JasperExportManager.exportReportToPdfFile(print, "./src/main/resources/Articulos.pdf");
			
			// Abrir el fichero PDF generado
			File path = new File ("./src/main/resources/Articulos.pdf");
			Desktop.getDesktop().open(path);
		}
		catch (Exception e)
		{
			System.out.println("Error: " + e.toString());
		}
	}
}


