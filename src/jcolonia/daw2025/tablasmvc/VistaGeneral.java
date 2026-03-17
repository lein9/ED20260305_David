package jcolonia.daw2025.tablasmvc;
import java.util.List;
import java.util.Scanner;

/**
 * Posee todas las funciones de impresión
 * 
 * @author dani <GitHub: lein9>
 * @version 5.0 20260317
 */
public abstract class VistaGeneral {
	/** Formato para el texto mostrado por consola */
	private final static String FORMATO_PRINTF_MOSTRARTEXTO = "%s%n";
	/** Formato para el texto de aviso -saldrá en verde, salvo consolas sin opción de color- */
	private final static String FORMATO_PRINTF_MOSTRARAVISO = "\033[32m *** %s *** \033[m%n";
	/** entrada para todo menú que se cree */
	private static Scanner scEntrada;

	/**
	 * Crea un objeto de {@link Scanner} o devuelve el existente. 
	 * @return Objeto de {@link Scanner}
	 */
	public static Scanner getScEntrada() { // método getInstance()
		if (scEntrada == null) {
			scEntrada = new Scanner(System.in);
		}

		return scEntrada;
	}
	
	/**
	 * Muestra el texto pasado por parámetro.
	 * Siguiendo el formato indicado por la constante de clase FORMATO_PRINTF_MOSTRARTEXTO
	 * @param texto Cadena de texto pasada como parámetro
	 */
	public static void mostrarTexto(String texto) {
		System.out.printf(FORMATO_PRINTF_MOSTRARTEXTO, texto);
	}

	/**
	 * Muestra el aviso pasado por parámetro.
	 * Siguiendo el formato indicado por la constante de clase FORMATO_PRINTF_MOSTRARAVISO
	 * @param texto Aviso pasado como parámetro
	 */
	public static void mostrarAviso(String texto) {
		System.out.printf(FORMATO_PRINTF_MOSTRARAVISO, texto);
	}

	/**
	 * Imprime el título del menú con un subrayado para separarlo de las opciones.
	 * Sobre el formato: antes del título hay un espacio y el subrayado será con '='
	 * y sobresaldrá un caracter por ambos lados: <div>
	 * 
	 * <pre>
	 * Título 
	 *========
	 * </pre>
	 * 
	 * </div>
	 * 
	 * @param texto título a mostrar
	 */
	public static void mostrarTítulo(String texto) {
		StringBuffer base;
		base = new StringBuffer();
		base.append("=="); // que sobresalga un carácter por ambos lados

		// Ponemos tantas veces el carácter báse, '=', como longitud tiene el título
		for (int i = 0; i < texto.length(); i++) {
			base.append("=");
		}

		System.out.printf(" %s %n", texto);
		System.out.printf(FORMATO_PRINTF_MOSTRARTEXTO, base);
	}

	/**
	 * Imprime un título encima de las opciones del menú. Sobre el formato: antes
	 * del título hay un espacio y el subrayado será con '=' y sobresaldrá un
	 * caracter por ambos lados: <div>
	 * 
	 * <pre>
	 * Título 
	 *========
	 * </pre>
	 * 
	 * </div>
	 * 
	 * @param texto título que se pone encima de las opciones.
	 */
	public static void mostrarTítulo2(String texto) {
		/*
		 * este método tal vez sea para mostrar un título nuevo en caso de cambio de
		 * este
		 */
		StringBuffer base;
		base = new StringBuffer();
		base.append("=="); // que sobresalga un carácter por ambos lados

		// Ponemos tantas veces el carácter báse, '=', como longitud tiene el título
		for (int i = 0; i < texto.length(); i++) {
			base.append("=");
		}

		System.out.printf(" %s %n", texto);
		System.out.printf(FORMATO_PRINTF_MOSTRARTEXTO, base);
	}

	/**
	 * Pide un entero, sacando por pantalla el mensaje pasado.  
	 * @param texto cadena de texto a sacar por pantalla para pedir un nº. al usuario. 
	 * @return entero introducido por el usuario. 
	 */
	public static int pedirNúmero(String texto) {
		boolean introducido;
		int num = 0;
		
		introducido = false;
		getScEntrada();
		
		System.out.printf("%s%n", texto);	// Tipo "Introduzca el número para la tabla"
		
		do {
			try {
				num = Integer.parseInt( scEntrada.nextLine() );
				introducido = true;
			} catch (NumberFormatException e) {
				System.out.println("Por favor introduzca un entero");
			}
		} while(!introducido);
		
		return num;
	}

	/**
	 * Imprime un mensaje 'de pausa', hasta que se presione una tecla.
	 * 
	 * @param texto texto que se mostrará en el mensaje
	 */
	public static void pausa(String texto) {
		System.out.printf(FORMATO_PRINTF_MOSTRARTEXTO, texto); // será tipo: "Pulsa una tecla para continuar..."
		scEntrada.nextLine();
	}

	/**
	 * Pide la confirmación al usuario (usando {@link Scanner}) con respecto a un mensaje que se le pasa como parámetro. 
	 * 
	 * @param texto mensaje a mostrar para solicitar confirmación 
	 * @return si se confirma o no
	 */
	public static boolean pedirConfirmación(String texto) {
		// Mensaje que espere como respuesta 's' o 'n'. Por ej: "¿Quieres salir del programa? → s - sí | n - no "
		boolean confirmado = false;
		String introducido;

		System.out.printf(FORMATO_PRINTF_MOSTRARTEXTO, texto);
		introducido = scEntrada.nextLine();

		switch (introducido) {
		case "s":
			confirmado = true;
			break;
		case "S":
			confirmado = true;
			break;
		case "n":
			confirmado = false;
			break;
		case "N":
			confirmado = false;
			break;
		default:
			System.out.printf(FORMATO_PRINTF_MOSTRARTEXTO, "Solo son válidos 's' o 'n'.");
			// Recursivo. Con el 'return' nos aseguramos que la confirmación (la respuesta)
			// llegue al método original
			return pedirConfirmación(texto);
		}

		return confirmado;
	}

	/** 
	 * Saca por pantalla las cadenas de texto que se le pase como parámetro. 
	 * @param lista cadenas de texto que se imprimirán
	 */
	public static void mostrarLista(List<String> lista) {
		for (int i=0; i<lista.size(); i++) {
			System.out.println(lista.get(i));
		}
	}
}
