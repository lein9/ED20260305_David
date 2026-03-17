package jcolonia.daw2025.tablasmvc;

import java.util.ArrayList;
import java.util.List;

/**
 * Representación de un menú con un título y una lista de opciones. 
 * Posee una función que muestra la lista de opciones, asociadas a enteros consecutivos, y una función que permite elegir 
 * opción, vía el entero que se corresponde con la opción. 
 * 
 * @author dani <GitHub: lein9>
 * @version 3.0 20260317
 */
public class VistaMenú {
	/** título que aparecerá en el menú */
	private String título;
	/** almacen con las posibles elecciones */
	private List<String> opciones;	
	
	/**
	 * Generador de Menús con título y opciones. También recibe el objeto Scanner de {@link VistaGeneral}. 
	 * @param título encabezado que llevará el menú
	 * @param opciones lista de elecciones que aparecerán en el menú
	 */
	public VistaMenú (String título, List<String> opciones) {
		this.título = título;
		this.opciones = new ArrayList<String>(opciones);
	}
	
	/**
	 * Imprime las opciones del menú. 
	 * El formato será:
	 * <div><pre>
	 *  1) &lt;opción&gt;
	 *  2) &lt;opción&gt;
	 *  3) &lt;opción&gt;
	 *  .  .  .
	 *  0) Salir
	 * </pre>
	 * </div>
	 */
	public void mostrarOpciones() {
		for (int i=0; i<opciones.size(); i++) {
			System.out.printf("  %d) %s%n", i+1, opciones.get(i));
		}
		System.out.printf("  %d) %s%n", 0, "Salir");
		
		System.out.println();
	}
	
	/**
	 * Saca un texto para pedir que se introduzca un número para elegir opción. 
	 * @return número de opción elegido
	 */
	public int pedirOpción() {
		int num;	// entero a devolver, que representa la opción
		boolean introducido;
		String mensajeAMostrar;
		
		num = 0;
		introducido = false;
		mensajeAMostrar = String.format("Elija opción entre el 0 y %d", opciones.size());
		
		do {
			try {
				num = VistaGeneral.pedirNúmero(mensajeAMostrar);
				introducido = true;
			} catch(NumberFormatException e) {
				System.out.println("Por favor, introduzca un entero que corresponda a las opciones");
			}
		} while (!introducido);
		
		return num;
	}
}
