package jcolonia.daw2025.tablasmvc;
import java.util.List;
import java.util.Scanner;

/**
 * Posee todas las funciones de impresión
 */
public class VistaGeneral {
	private final static String FORMATO_PRINTF_MOSTRARTEXTO;
	private final static String FORMATO_PRINTF_MOSTRARAVISO;
	private static Scanner scEntrada;
	
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
		System.out.printf();
	}

	public static void mostrarTítulo(String texto) {
		System.out.printf();
	}

	public static void mostrarTítulo2(String texto) {
		System.out.printf();
	}
	
	public static int pedirNúmero(String texto) {
		System.out.printf("Elija opción: ");
	}

	public static void pausa(String texto) {
		System.out.printf();
	}

	public static boolean pedirConfirmación(String texto) {
		System.out.printf("¿Quieres salir del programa?");	// s - sí, n - no
	}
	
	public void mostrarLista(List<String>) {
		for (String elemento : ) {
			
		}
	}
}
