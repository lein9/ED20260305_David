package jcolonia.daw2025.tablasmvc;

import java.util.Arrays;
import java.util.List;

/**
* Núcleo de aplicación de consola de texto con menús. Aplicación
* de texto usando tablas de multiplicar infantiles clásicas. 
* @author dani - GitHub: lein9 _ sobre el código base de dhmartin
* @version 3.0 20260314
*/
public class ControlTablaMultiplicar {
	/** Formato tipo «printf» para el nombre del archivo de exportación. */
	public static final String FORMATO_RUTA_ARCHIVO_EXPORTACIÓN = "tabla del %02d.txt";
	
	/** Array de opciones para el menú */
	public static final String[] OPCIONES_MENÚ_PRINCIPAL = {"Mostrar tabla", "Cambiar de tabla", "Exportar tabla"};
	
	/** Tabla de multiplicar activa. */
	private TablaMultiplicar tabla;

	/**
	 * Inicia el programa
	 */
	public ControlTablaMultiplicar(){
		init();
	}

	/**
	* Pide al usuario un número y prepara la primera tabla activa.
	*/
	public void init(){
		VistaGeneral.mostrarTítulo("Tablas de multiplicar");
		cambiarTabla();
	}

	/**
	* Gestión del menú principal. Desde este menú se ejecutan las opciones disponibles 
	* a elección del usuario.
	* A la salida del menú se finaliza el programa.
	*/
	public void buclePrincipal(){
		VistaMenú menú;
		int opción;
		String textoTablaActual;
		
		menú = new VistaMenú("Tablas de multiplicar", List.of(OPCIONES_MENÚ_PRINCIPAL));
		
		do{
			// pausa de confirmación y muestra el número para la tabla actual
			VistaGeneral.pausa("Pulsa una tecla para continuar...");
			textoTablaActual = String.format("Estamos en la tabla del %d", tabla.getNúmero());
			VistaGeneral.mostrarTexto(textoTablaActual);
						
			// Muestra el menú y pide una opción
			VistaGeneral.mostrarTítulo2("Menú principal");
			menú.mostrarOpciones();
			opción = menú.pedirOpción();
			
			switch(opción){
			case 1: // Mostrar tabla
				mostrarTabla();
				break;
			case 2: //Cambiar tabla
				cambiarTabla();
				break;
			case 3: // Exportar tabla
				exportarTabla();
				break;
			case 0: // Salir
				break;
			default: // Opciones no implementadas
				opciónNoDisponible();
				break;
			}
			
		} while (opción!=0);
		
		VistaGeneral.mostrarAviso("FIN");
		
	}
	
	/**
	* Muestra por pantalla -envía a la salida estándar- los productos correspondientes a la tabla activa.
	*/
	private void mostrarTabla(){
		VistaGeneral.mostrarLista(tabla.toListaPantalla());
	}
	
	/**
	* Cambia la tabla activa por otra elegida por el usuario.
	*/
	private void cambiarTabla(){
		int n;
		
		n = VistaGeneral.pedirNúmero("Introduzca el número para la tabla");
		
		tabla = new TablaMultiplicar(n);
		tabla.generarTabla();
	}

	/**
	* Envía a un archivo los productos correspondientes a la tabla activa.
	*/
	private void exportarTabla(){
		ExportaciónArchivo exp;
		exp = new ExportaciónArchivo();
		
		exp.exportaciónArchivo(FORMATO_RUTA_ARCHIVO_EXPORTACIÓN);
		exp.guardar(Arrays.asList(OPCIONES_MENÚ_PRINCIPAL));
	}
	
	/**
	 * Muestra un mensaje de aviso indicando que 
	 * la opción elegida no está disponible.
	*/
	private void opciónNoDisponible(){
		VistaMenú.mostrarAviso("La opción elegida no está disponible");	// RELLENAR EL AVISO CON ALGO (el método es de VistaGeneral)
	}

}