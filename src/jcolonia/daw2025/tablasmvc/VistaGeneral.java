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
		System.out.printf();
	}
	
	public static void mostrarAviso(String texto) {
		System.out.printf("Estamos en la tabla del %s", scEntrada.nextLine());
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
		/* tal vez sea para mostrar un título nuevo en caso de cambio de este */
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
	 * Muestra las opciones del menú, que se pasa como parámetro, y saca un texto pidiendo un número, 
	 * que corresponderá a una de las opciones del menú. 
	 * El formato será:
	 * <div><pre>
	 *  1) &lt;opción&gt;
	 *  2) &lt;opción&gt;
	 *  3) &lt;opción&gt;
	 *  .  .  .
	 *  0) Salir
	 *  
	 * Introduzca número de opción (0 para salir):
	 * </pre>
	 * </div>
	 * 
	 * @param texto cadena de texto con las opciones para el menú separadas por ','. 
	 * @return entero que indica la opción elegida. 
	 */
	public static int pedirNúmero(String texto) {
		String[] opciones;	// para almacenar las opciones del menú
		int elección;
		boolean salir;	/* para salir del bucle do-while */
		
		opciones = new String[texto.split(",").length];
		// considero que el texto es un CSV
		for (int i=0; i<texto.split(",").length; i++) {
			opciones[i] = texto.split(",")[i];
		}
		
		/* hay que darle un valor, porque en el do-while puede considerar que no se le asigna un valor */
		elección = -1;
		salir = false;
		
		// IMPRIMO LAS OPCIONES
		for (int i=0; i<opciones.length; i++) {
			System.out.printf("  %d) %s%n", i+1, opciones[i]);
		}
		System.out.printf("  %d) %s%n", 0, "Salir");
		
		System.out.println();
		

		// SOLICITO NÚMERO Y OPERACIONES PARA CAPTURARLO
		System.out.printf("Introduzca número de opción (0 para salir): ");
		
		do {
			try {
				elección = Integer.parseInt( scEntrada.nextLine() );
				if (elección == 0) {
					System.exit(0);	// Salida sin anomalía
				} else if (elección < 1 || elección > opciones.length) {
					// se usaría NumberFormatException, pero cree una
					throw new NumberFormatException("Entrada inválida");
				}
				salir = true;
			} catch (NumberFormatException e) {
				System.out.printf("Por favor introduce un número válido%n");
			}
		} while (!salir);
		
		scEntrada.close();
		return elección;
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
		System.out.printf("¿Quieres salir del programa?  →  s - sí | n - no");
	}
	
	public void mostrarLista(List<String>) {
		for (String elemento : ) {
			
		}
	}
}
