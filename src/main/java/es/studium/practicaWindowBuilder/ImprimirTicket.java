package es.studium.practicaWindowBuilder;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.UnitValue;

/**
 * Esta clase es la que se ocupa de gestionar la lógica que genera un fichero
 * PDF a partir de un ticket.
 * 
 * @author Denis Peña
 * @version 1.0
 * @since 2025-02-12
 */
public class ImprimirTicket {
	/**
	 * Instancia del Modelo de la aplicación.
	 */
	Modelo datos = new Modelo();

	/**
	 * Constructor por parámetros de la clase.
	 * 
	 * @param dest  Fichero a generar.
	 * @param id    Id del ticket.
	 * @param fecha Fecha del ticket.
	 * @param total Total del ticket.
	 * @throws IOException Si hay un error en el flujo de información
	 */
	public ImprimirTicket(String dest, int id, String fecha, double total) {
		try {
			// Inicializar el PdfWriter
			PdfWriter writer = new PdfWriter(dest);
			// Inicializar el documento pdf
			PdfDocument pdf = new PdfDocument(writer);
			// Inicializar documento, como parámetros indicamos el PdfDocument a utilizar y
			// el tamaño de la hoja
			Document documento = new Document(pdf, PageSize.A5);
			// Damos márgenes a la hoja
			documento.setMargins(25, 25, 25, 25);
			// Establecemos una fuente para los registros
			PdfFont fuente = PdfFontFactory.createFont(StandardFonts.HELVETICA);
			// Establecemos una fuente para las cabeceras
			PdfFont fuenteNegrita = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
			// Creamos la tabla y le damos un 'ancho' a cada campo
			Table tabla = new Table(UnitValue.createPercentArray(new float[] { 3, 1, 1, 1 })).useAllAvailableWidth();

			// AÃ±adir informaciÃ³n
			documento.add(new Paragraph("TICKET " + id).setFont(fuenteNegrita));
			documento.add(new Paragraph("ALBERCHE 7"));
			documento.add(new Paragraph("41005 SEVILLA"));
			documento.add(new Paragraph("TELÉFONO 697152347"));
			documento.add(new Paragraph("CAJA: 01"));
			documento.add(new Paragraph("FECHA: " + fecha).setFont(fuenteNegrita));

			// Conexión a la base de datos
			datos.conectar();
			// Crear array del tipo String y partirlo por cada línea de la cadena devuelta
			String[] lineas = datos.obtenerArticulosComprados(id).split("\n");
			// Extraer la posición 0 del array y establecerla como cabecera de la tabla
			datos.process(tabla, "DESCRIPCIÓN-CANTIDAD-PRECIO-IMPORTE", fuenteNegrita, true);

			// Rellenamos la tabla
			for (int i = 0; i < lineas.length; i++) {
				datos.process(tabla, lineas[i], fuente, false);
			}

			// Añadimos la tabla al documento
			documento.add(tabla);
			// Añadimos el total
			documento.add(new Paragraph("TOTAL: " + total + " €").setFont(fuenteNegrita));
			documento.add(new Paragraph("LE ATENDIÓ: DENIS PEÑA"));
			documento.add(new Paragraph("GRACIAS POR SU VISITA"));
			// Nos desconectamos de la base de datos y cerramos el document
			datos.desconectar();
			documento.close();
			// // Abrimos el pdf creado
			Desktop.getDesktop().open(new File(dest));

		} catch (IOException e) {
		}
	}
}
