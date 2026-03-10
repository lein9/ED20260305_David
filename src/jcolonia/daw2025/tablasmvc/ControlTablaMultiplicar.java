package jcolonia.daw2025.tablasmvc;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * 
 */
public class ControlTablaMultiplicar {
	/** Formato tipo «printf» para el nombre del archivo de exportación. */
	public static final String FORMATO_RUTA_ARCHIVO_EXPORTACIÓN = "tabla del %02d.txt";
	
	/** Tabla de multiplicar activa. */
	private TablaMultiplicar tabla;
	
	/**
	 * 
	 */
	public ControlTablaMultiplicar() {
		init();
	}
	
	/* en realidad usarémos VistaMenú, que hereda de VistaGeneral (por ahora no la ponemos) */
	//private VistaGeneral vista;
	
	/**
	 * Pide al usuario un número y prepara la primera tabla activa. 
	 */
	public void init() {
		cambiarTabla();
	}
	
	/**
	 * Gestión del menú principal. Desde este menú se ejecutan las opciones disponibles a elección del
	 * usuario. 
	 * A la sadila del menú se finaliza el programa. 
	 */
	public void buclePrincipal() {
		VistaMenú menú;
		int opción;
		
		// título, array de opciones
		menú = new VistaMenú("Tablas de multiplicar", OPCIONES_MENÚ_PRINCIPAL);
		
		do {
			menú.mostrarOpciones();
			opción = menú.pedirNúmero(); // tal vez .pedirOpción() del menú original
			
			switch (opción) {
			case 1:	// Mostrar tabla
				mostrarTabla();
				break;
			case 2: // Exportar tabla
				exportarTabla();
				break;
			case 3: // Cambiar tabla
				cambiarTabla();
				break;
			case 0:	// Salir

				break;
			default:	// Otras opciones no implementadas -opciónNoDisponible()-
				opciónNoDisponible();
				break;
			}
		} while (opción != 0);
		
		// mostrado cuando el usuario introduce 0
		VistaGeneral.mostrarAviso("FIN");
	}
	
	/**
	 * Muestra por pantalla -envía a la salida estándar- los productos correspondientes a la tabla activa. 
	 */
	private void mostrarTabla() {
		tabla.toString();
	}
	
	/**
	 * Envía a un archivo los productos correspondientes a la tabla activa. 
	 */
	private void exportarTabla() {
		Path refArchivo = Path.of( String.format(FORMATO_RUTA_ARCHIVO_EXPORTACIÓN) );
		try {
			Files.deleteIfExists(refArchivo);
			Path archivo = Files.createFile(refArchivo);
			
			BufferedWriter escribe;
			escribe = Files.newBufferedWriter(archivo, Charset.forName("windows-1252"));	// tiene el charset
			PrintWriter salida = new PrintWriter(escribe);

			tabla.toString();

			salida.close();
		} catch (IOException e) {
			System.err.printf("Error de escritura: %s", e.getLocalizedMessage());
		}
	}
	
	/**
	 * Cambia la tabla activa por otra elegida por el usuario. 
	 */
	private void cambiarTabla() {
		int n;
		
		n = VistaGeneral.pedirNúmero("Introduzca el número de la tabla a la que cambiar: ");
		// falta lo del Scanner
		tabla = new TablaMultiplicar(n);
		tabla.generarTabla();
	}
	
	/**
	 * Muestra un mensaje de aviso indicando que la opción elegida no está disponible. 
	 */
	private void opciónNoDisponible() {
		
	}
}
