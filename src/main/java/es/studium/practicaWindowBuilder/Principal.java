package es.studium.practicaWindowBuilder;

public class Principal 
{
	public static void main(String[] args) 
	{
		Modelo modelo = new Modelo();
		MenuPrincipal menu = new MenuPrincipal();
		
		new Controlador(modelo, menu);
	}
}