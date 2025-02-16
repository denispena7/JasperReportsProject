package es.studium.practicaWindowBuilder;

/**
 * Clase principal que inicia la aplicaci�n.
 * 
 * Se encarga de crear las instancias del modelo y la vista, y luego inicializa
 * el controlador para gestionar la l�gica del programa.
 * 
 * @author Denis Pe�a
 * @version 1.0
 * @since 2025-02-12
 */
public class Principal {
	/**
	 * M�todo principal que inicia la aplicaci�n.
	 * 
	 * @param args Argumentos de l�nea de comandos (no utilizados en esta
	 *             aplicaci�n).
	 */
	public static void main(String[] args) {
		Modelo modelo = new Modelo();
		MenuPrincipal menu = new MenuPrincipal();

		new Controlador(modelo, menu);
	}
}