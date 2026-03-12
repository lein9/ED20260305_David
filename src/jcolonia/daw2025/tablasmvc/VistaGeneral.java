package jcolonia.daw2025.tablasmvc;
import java.util.List;
import java.util.Scanner;

import jcolonia.daw2025.ut5.menu.excepciones.NoEstáEntreOpcionesException;

/**
 * Posee todas las funciones de impresión
 */
public class VistaGeneral {
	private final static String FORMATO_PRINTF_MOSTRARTEXTO;
	private final static String FORMATO_PRINTF_MOSTRARAVISO;
	private static Scanner scEntrada;
	
//	{
//		FORMATO_PRINTF_MOSTRARTEXTO = "";
//		FORMATO_PRINTF_MOSTRARAVISO = "";
//	}
	
	public static Scanner getScEntrada() {	// método getInstance()
		if (scEntrada == null) {
			scEntrada = new Scanner(System.in); 
		}
		
		return scEntrada;
	}
	
	public static void mostrarTexto(String texto) {
		System.out.println(texto);
	}
	
	public static void mostrarAviso(String texto) {
		System.out.println(texto);
	}

	/**
	 * Imprime el título del menú con un subrayado para separarlo de las opciones.
	 * Sobre el formato: antes del título hay un espacio y el subrayado será con '=' y sobresaldrá un 
	 * caracter por ambos lados:
	 * <div><pre>
	 * Título 
	 *========
	 * </pre>
	 * </div>
	 * 
	 * @param texto título a mostrar
	 */
	public static void mostrarTítulo(String texto) {
		StringBuffer base;
		base = new StringBuffer();
		base.append("==");	// que sobresalga un carácter por ambos lados
		
		// Ponemos tantas veces el carácter báse, '=', como longitud tiene el título 
		for (int i=0; i < texto.length(); i++) {
			base.append("=");
		}
		
		System.out.printf(" %s %n", texto);
		System.out.printf("%s%n", base);
	}

	/**
	 * Imprime un título encima de las opciones del menú. 
	 * Sobre el formato: antes del título hay un espacio y el subrayado será con '=' y sobresaldrá un 
	 * caracter por ambos lados:
	 * <div><pre>
	 * Título 
	 *========
	 * </pre>
	 * </div>
	 * 
	 * @param texto título que se pone encima de las opciones. 
	 */
	public static void mostrarTítulo2(String texto) {
		/* este método tal vez sea para mostrar un título nuevo en caso de cambio de este */
		StringBuffer base;
		base = new StringBuffer();
		base.append("==");	// que sobresalga un carácter por ambos lados
		
		// Ponemos tantas veces el carácter báse, '=', como longitud tiene el título 
		for (int i=0; i < texto.length(); i++) {
			base.append("=");
		}
		
		System.out.printf(" %s %n", texto);
		System.out.printf("%s%n", base);
	}
	
	public static int pedirNúmero(String texto) {
		
	}

	/**
	 * Imprime un mensaje 'de pausa', hasta que se presione una tecla.  
	 * 
	 * @param texto texto que se mostrará en el mensaje
	 */
	public static void pausa(String texto) {
		System.out.println(texto);	// será tipo: "Pulsa una tecla para continuar..."
		scEntrada.nextLine();	// cuando se presione se pasará a la función que cierra el escaner
		scEntrada.close();
	}

	public static boolean pedirConfirmación(String texto) {
		boolean presionado;
		final String MENSAJE_CONFIRMACIÓN;
		MENSAJE_CONFIRMACIÓN = "¿Quieres salir del programa?  →  s - sí | n - no ";
		System.out.println(MENSAJE_CONFIRMACIÓN);
		
		switch(texto) {
			case "s":
				presionado = true;
				break;
			case "S":
				presionado = true;
				break;
			case "n":
				presionado = false;
				break;
			case "N":
				presionado = false;
				break;
			default: 
				System.out.println("Por favor introduce 's' o 'n'.");
				System.out.println(MENSAJE_CONFIRMACIÓN);
				throw new IllegalArgumentException("");
				break;
		}
		
		return presionado;
			
	}
	
	public void mostrarLista(List<String>) {
		for (String elemento : ) {
			
		}
	}
}
