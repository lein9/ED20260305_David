package jcolonia.daw2025.equipos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jcolonia.daw2025.comun.VistaGeneral;
import jcolonia.daw2025.comun.VistaMenú;
import jcolonia.daw2025.equipos.excepciones.CancelarException;
import jcolonia.daw2025.equipos.excepciones.LigaSinEquiposException;
import jcolonia.daw2025.equipos.excepciones.LíneaCorruptaException;

public class ControlLiga {
	/** Formato tipo «printf» para el nombre del archivo de exportación. */
	public static final String FORMATO_RUTA_ARCHIVO_EXPORTACIÓN = "%s.txt";	// nombre de la liga
	
	/** Array de opciones para el menú */
	public static final String[] OPCIONES_MENÚ_PRINCIPAL = {"Mostrar liga", "Cambiar de liga", 
			"Crear liga", "Consultar equipo de la liga", "Dar de alta a equipo", "Exportar", 
			"Importar"};
	
	/** Ligas existentes */
	private List <Liga> ligas;
	/** Liga activa. */
	private Liga liga;
	
	/**
	 * Inicia el programa
	 */
	public ControlLiga() {
		ligas = new ArrayList <Liga>();
		init();
	}
	
	/**
	* 
	*/
	public void init(){
		VistaGeneral.mostrarTítulo("Equipos de la liga");
		cambiarDeLiga();
	}
	
	/**
	* Gestión del menú principal. Desde este menú se ejecutan las opciones disponibles 
	* a elección del usuario.
	* A la salida del menú se finaliza el programa.
	*/
	public void buclePrincipal(){
		VistaMenú menú;
		int opción;
		String textoLigaActual;
		boolean seSale;
		
		menú = new VistaMenú("Equipos de la liga", List.of(OPCIONES_MENÚ_PRINCIPAL));
		
		do{
			// pausa de confirmación y muestra el número para la tabla actual
			VistaGeneral.pausa("Pulsa una tecla para continuar...");
			textoLigaActual = String.format("Estamos en la liga: %s", liga.getNombre());
			VistaGeneral.mostrarTexto(textoLigaActual);
			
			// Muestra el menú y pide una opción
			VistaGeneral.mostrarTítulo2("Menú principal");
			menú.mostrarOpciones();
			opción = menú.pedirOpción();
			
			switch(opción){
			case 1:
				mostrarLiga();
				break;
			case 2:
				cambiarDeLiga();
				break;
			case 3:
				try {
					crearLiga();
				} catch (CancelarException e) {
					VistaGeneral.mostrarAviso(e.getLocalizedMessage());
				}
				break;
			case 4:
				consultarEquipo();
				break;
			case 5: 
				alta();
				break;
			case 6: 
				exportar();
				break;
			case 7: 
				try {
					importar();
				} catch (IOException e) {
					VistaGeneral.mostrarAviso(e.getLocalizedMessage());
				}				
				break;
			case 0: // Salir
				seSale = VistaGeneral.pedirConfirmación("¿Quieres salir del programa? → s - sí | n - no ");
				if (!seSale) {
					opción = 1;	// cambiamos el valor de 'opción' para que no salga del bucle
				}
				break;
			default: // Opciones no implementadas
				opciónNoDisponible();
				break;
			}
		} while (opción!=0);
		
		VistaGeneral.mostrarAviso("FIN");
	}
	
	private void mostrarLiga() {
		if (liga.numEquipos() == 0) {
			String mensaje;
			mensaje = String.format("La liga \"%s\" no tiene equipos registrados", liga.getNombre());
			VistaGeneral.mostrarAviso(mensaje);
		} else {
			List <String> listaEquipos;
			String nombreEquipo;
			VistaMenú menú;
			
			listaEquipos = new ArrayList <String>();
			nombreEquipo = null;
			
			// TÍTULO
			VistaGeneral.mostrarTítulo2(liga.getNombre());

			// LISTA
			for (int i = 0; i < liga.numEquipos(); i++) {
				try {
					nombreEquipo = liga.getEquipo(i).getNombre();
				} catch (LigaSinEquiposException e) { VistaGeneral.mostrarAviso(e.getLocalizedMessage()); }
				listaEquipos.add(nombreEquipo);
			}
			
			menú = new VistaMenú(liga.getNombre(), listaEquipos);
			menú.mostrarOpciones();
		}
	}
	
	private void cambiarDeLiga() {
		VistaMenú menú;
		List <String> nombresDeLigas;
		int n;
		
		nombresDeLigas = new ArrayList <String>();
		
		// Para crear la primera liga, si no las hay
		if (ligas.isEmpty()) {
			boolean hayLiga;
			int opción;
			hayLiga = false;
					
			do {
				opción = VistaGeneral.pedirNúmero("Introduce 0 para crear liga, 1 para importarla.");
				if (opción == 0 ) {
					try {
						crearLiga();
						hayLiga = true;
					} catch (CancelarException e) {
						VistaGeneral.mostrarAviso("Error inesperado.");
					}
				} else if (opción == 1) {
					try {
						importar();
						hayLiga = true;
					} catch (IOException e) {
						VistaGeneral.mostrarAviso(e.getLocalizedMessage());
					}
				}
			} while (!hayLiga);

		} else {	// si hay ligas
			for (int i=0; i<ligas.size(); i++) {
				nombresDeLigas.add( ligas.get(i).getNombre() );
			}
			
			// Mostramos las opciones a través de un elemento menú
			menú = new VistaMenú("Ligas", nombresDeLigas);
			menú.mostrarOpciones();
			
			n = VistaGeneral.pedirNúmero("Introduce el número de la liga deseada.");
			
			if (n != 0 && n <= ligas.size()) {	// el menú tiene una opción de salir (para no cambiar)
				liga = ligas.get(n-1);	// n-1 porque el menú empieza en 1
			}
		}
	}
	
	private void crearLiga() throws CancelarException {
//		Liga nuevaLiga;
		String nombreLiga;
		nombreLiga = VistaAltaEquipo.lecturaString("el nombre de la liga");
		
		// comprobamos que no exista una liga con igual nombre
		if (ligas != null || !ligas.isEmpty()) {
			for (int i=0; i < ligas.size() ;i++) {
				if (nombreLiga.equals(ligas.get(i).getNombre())) {
					throw new CancelarException("Una liga con este nombre ya existe.");
				}
			}
		}
		
		liga = new Liga(nombreLiga);
		ligas.add(liga);
	}
	
	private void consultarEquipo() {
		if (liga.numEquipos() == 0) {
			String mensaje;
			mensaje = String.format("La liga \"%s\" no tiene equipos registrados", liga.getNombre());
			VistaGeneral.mostrarAviso(mensaje);
		} else {
			String textoDatosEquipo;
			String pregunta;
			int n;
			boolean válido;
			
			n = 0;
			válido = false;
			mostrarLiga();	// La 1ª parte del método coincide, pero no quería repetir el mensaje de salida

			do {
				try {
					pregunta = String.format("Introduzca el índice del equipo", liga.numEquipos());
					n = VistaGeneral.pedirNúmero(pregunta);
					if ( 0 > n || n > liga.numEquipos() ) {
						throw new NumberFormatException("Índice incorrecto.");
					}
					válido = true;
				} catch (NumberFormatException e) {
					VistaGeneral.mostrarAviso(e.getLocalizedMessage());
				}
			} while(!válido);
			
			// si no se ha presionado 0
			if (n != 0) {
				try {
					textoDatosEquipo = liga.toListaExportar().get(n-1);
					VistaAltaEquipo.mostrarDatosEquipo(textoDatosEquipo);
				} catch (LigaSinEquiposException e) { VistaGeneral.mostrarAviso(e.getLocalizedMessage());
				} catch (LíneaCorruptaException e) {
					VistaGeneral.mostrarAviso("Ha habido algún problema al leer los datos del equipo.");
				}
			}
		}
	}
	
	
	/**
	 * Guarda en la liga activa los datos de un equipo. 
	 */
	private void alta() {
		Equipo nuevoEquipo;
		
		try {
			nuevoEquipo = VistaAltaEquipo.cargarEquipo();
			// comprobamos que no exista un equipo con igual nombre
			for (int i=0; i < liga.numEquipos() ;i++) {
				if ( nuevoEquipo.getNombre().equals(liga.getEquipo(i).getNombre()) ) {
					throw new CancelarException("Un equipo con este nombre ya existe en la liga.");
				}
			}
			liga.añadirEquipo(nuevoEquipo);
		} catch (CancelarException e) {
			VistaGeneral.mostrarAviso(e.getLocalizedMessage());
		} catch (LigaSinEquiposException e) { VistaGeneral.mostrarAviso(e.getLocalizedMessage()); }
	}
	
	/**
	 * Lee los datos de un archivo externo. 
	 */
	private void importar() throws IOException {
		String ruta;
		String mensajeDeSalida;
		String nombreLiga;
		List <String> recibido;	// lo recibirémos de AccesoArchivo
		List <Equipo> equipos; // no se creará la liga hasta que todos los datos leídos sean correctos
		Equipo equipo;
		boolean fallo;	// solo añadiremos una liga cuando no halla fallo de importación
		
		fallo = false;
		recibido = new ArrayList <String> (20);
		equipos = new ArrayList <Equipo> (20);
		
		// Intentamos leer de la ruta
		ruta = VistaAltaEquipo.lecturaString("ruta de liga a importar");
		
		try {
			recibido = AccesoArchivo.leer(ruta);
			if (recibido.isEmpty()) { throw new IOException("No se ha leído nada"); }
		} catch (IOException e) {
			throw new IOException(e.getLocalizedMessage());
		}
		
		// Si se consigue leer algo se intenta crear un equipo
		for (String r : recibido) {
			try {
				equipo = Equipo.of(r);
				equipos.add(equipo);
			} catch(LíneaCorruptaException e) {
				fallo = true;
				VistaGeneral.mostrarAviso( e.getLocalizedMessage() );
				break;
			}
		}
		
		if (!fallo) {
			nombreLiga = VistaAltaEquipo.lecturaString("Introduzca nombre de liga: ");
			liga = new Liga(nombreLiga);
			
			for (int i=0; i<equipos.size() ;i++) {
				liga.añadirEquipo(equipos.get(i));
			}
			ligas.add(liga);
			mensajeDeSalida = String.format("Liga importada de la ruta: %n\"%s\"", ruta);
			VistaGeneral.mostrarTexto(mensajeDeSalida);
		} else {
			VistaGeneral.mostrarAviso("Fallo al importar.");
		}
	}

	
	/**
	 * Envía a un archivo los datos de los equipos de la liga. 
	 */
	private void exportar() {
		String notificaciónDeExportación;
		String ruta;
		List <String> datosEquipos;
		
		ruta = String.format(FORMATO_RUTA_ARCHIVO_EXPORTACIÓN, liga.getNombre());
		
		datosEquipos = new ArrayList <String>();

		try {
			datosEquipos = liga.toListaExportar();
		} catch (LigaSinEquiposException e) { VistaGeneral.mostrarAviso(e.getLocalizedMessage()); }
		
		try {
			AccesoArchivo.escribir(ruta, datosEquipos);
		} catch (IOException e) { VistaGeneral.mostrarAviso(e.getLocalizedMessage()); }
		
		// Se notifica que se ha exportado
		notificaciónDeExportación = String.format("Exportado a la ruta: %n\"%s\"", ruta);
		VistaGeneral.mostrarTexto(notificaciónDeExportación);
	}
	
	/**
	* Muestra un mensaje de aviso indicando que 
	* la opción elegida no está disponible.
	*/
	private void opciónNoDisponible(){
		VistaGeneral.mostrarAviso("La opción elegida no está disponible");
	}
}
