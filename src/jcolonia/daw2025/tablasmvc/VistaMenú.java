package jcolonia.daw2025.tablasmvc;

import java.util.Scanner;

/**
 * Generador de menús que muestra un título, y unas opciones, elegibles al introducir por consola 
 * el número correspondiente. La última de las opciones, activable con el 0, cerrará el menú. 
 * 
 * @author dani
 * @version 1.0 (202602)
 */
public class VistaMenú extends VistaGeneral{
	/** título que aparecerá en el menú */
	private String título;
	public final String[] OPCIONES_MENÚ_PRINCIPAL;
	/** entrada para todo menú que se cree */
	private static Scanner entrada;
	
	/** Inicializador del {@link Scanner} */
	static {
		if(entrada == null) {	// esto lo usarémos en un getInstance() estático
			entrada = new Scanner(System.in);
		}
	}
	
	/**
	 * Crea un menú con un título y opciones de las que escoger.
	 * 
	 * @param título encabezado que llevará el menú
	 * @param arrayDeOpciones lista de elecciones que aparecerán en el menú
	 */
	public VistaMenú(String título, String[] arrayDeOpciones) {
		this.título = título;
		
		OPCIONES_MENÚ_PRINCIPAL = new String[arrayDeOpciones.length];
		for (int i=0; i<arrayDeOpciones.length; i++) {
			OPCIONES_MENÚ_PRINCIPAL[i] = arrayDeOpciones[i];
		}
		
		/* aún no sé por qué puso esto:
		 * this.OPCIONES_MENÚ_PRINCIPAL = arrayDeOpciones;  */
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
	 */
	public void mostrarTítulo() {
		StringBuffer base;
		base = new StringBuffer();
		base.append("==");	// que sobresalga un carácter por ambos lados
		
		// Ponemos tantas veces el carácter báse, '=', como longitud tiene el título 
		for (int i=0; i < título.length(); i++) {
			base.append("=");
		}
		
		System.out.printf(" %s %n", título);
		System.out.printf("%s%n", base);
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
		for (int i=0; i<OPCIONES_MENÚ_PRINCIPAL.length; i++) {
			System.out.printf("  %d) %s%n", i+1, OPCIONES_MENÚ_PRINCIPAL[i]);
		}
		System.out.printf("  %d) %s%n", 0, "Salir");
		
		System.out.println();
	}
	
	/**
	 * Saca un texto para pedir que se introduzca un número para elegir opción. 
	 * Si se presiona 0 cierra el menú.
	 * @return número de opción elegido
	 */
	public int pedirOpción() {
		int elección;
		boolean salir;
		
		/* creo que tengo que darle un valor, porque en el do-while puede considerar que no se le asigna un valor */
		elección = -1;
		
		salir = false;

		System.out.println("Introduce un nº (escribe 0 para salir)");
		
		do {
			try {
				elección = Integer.parseInt( entrada.nextLine() );
				if (elección == 0) {
					System.exit(0);	// Salida sin anomalía
				} else if (elección < 1 || elección > OPCIONES_MENÚ_PRINCIPAL.length) {
					// se usaría NumberFormatException, pero cree una
					throw new NumberFormatException("Entrada inválida");
				}
				salir = true;
			} catch (NumberFormatException e) {
				System.out.printf("Por favor introduce un número válido%n");
			}
		} while (!salir);
		
		entrada.close();
		return elección;
	}
	
	/**
	 * Muestra la opción elegida, tras el texto: "<em>Se ha elegido </em>".
	 * @param texto la cadena de texto de la opción elegida
	 */
	public static void mostrarTexto(String texto) {
		int elección=-1;
		try {
			elección = Integer.parseInt(texto);
		} catch (NumberFormatException e) {
			System.err.println("No se ha recibido un número.");
		}
		
		System.out.printf("Se ha elegido %d", elección);
	}
}
