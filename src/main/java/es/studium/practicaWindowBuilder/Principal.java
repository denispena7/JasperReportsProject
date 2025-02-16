package es.studium.practicaWindowBuilder;

/**
 * Clase principal que inicia la aplicación.
 * 
 * Se encarga de crear las instancias del modelo y la vista, y luego inicializa
 * el controlador para gestionar la lógica del programa.
 * 
 * @author Denis Peña
 * @version 1.0
 * @since 2025-02-12
 */
public class Principal {
	/**
	 * Método principal que inicia la aplicación.
	 * 
	 * @param args Argumentos de línea de comandos (no utilizados en esta
	 *             aplicación).
	 */
	public static void main(String[] args) {
		Modelo modelo = new Modelo();
		MenuPrincipal menu = new MenuPrincipal();

		new Controlador(modelo, menu);
	}
}