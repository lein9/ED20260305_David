package jcolonia.daw2025.tablasmvc;

import java.util.Arrays;

/**
* Núcleo de aplicación de consola de texto con menús. Aplicación
* de texto usando tablas de multiplicar infantiles clásicas. 
*/
public class ControlTablaMultiplicar {
	/** Formato tipo «printf» para el nombre del archivo de exportación.
	*/
	public static final String FORMATO_RUTA_ARCHIVO_EXPORTACIÓN="tabla del %02d.txt";
	
	// Creo que el profesor quiere que lo obtengamos como array y lo cambiemos a lista
		/** Array de opciones para el menú */
		public static final String[] OPCIONES_MENÚ_PRINCIPAL;
	
	/** Tabla de multiplicar activa. */
	private TablaMultiplicar tabla;
	
	{	// ESTA ES LA V2
		OPCIONES_MENÚ_PRINCIPAL
	}

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
		
		menú=new VistaMenú("Tablas de multiplicar", Arrays.asList(OPCIONES_MENÚ_PRINCIPAL));
		
		do{
			menú.mostrarOpciones();
			opción=menú.pedirOpción();
			
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
	private void mostrarTabla(){}
	
	/**
	* Cambia la tabla activa por otra elegida por el usuario.
	*/
	private void cambiarTabla(){
		int n;
		
		VistaGeneral.pedirNúmero("Introduzca el número para la tabla");
		
		tabla=new TablaMultiplicar(n);
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
		VistaMenú.mostrarAviso();	// RELLENAR EL AVISO CON ALGO (el método es de VistaGeneral)
	}




}