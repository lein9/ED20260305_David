package jcolonia.daw2025.equipos;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import jcolonia.daw2025.comun.VistaGeneral;
import jcolonia.daw2025.comun.VistaMenú;
import jcolonia.daw2025.equipos.Equipo.TipoPropiedad;
import jcolonia.daw2025.equipos.excepciones.CancelarException;
import jcolonia.daw2025.equipos.excepciones.LíneaCorruptaException;

public abstract class VistaAltaEquipo {
	
	public static Equipo cargarEquipo() throws CancelarException {
		Equipo nuevoEquipo;
		boolean confirmación;
		String txInfoEquipo;
		
		// Atributos del nuevo equipo
		String nombreEquipo, estadio, presidente;
		int fundación;
		TipoPropiedad tPropiedad;

		nombreEquipo = lecturaString("nombre");
		estadio = lecturaString("estadio");
		presidente = lecturaString("presidente");
		fundación = lecturaEntero();
		
		try {
			tPropiedad = elecciónTipoPropiedad();
		} catch (CancelarException e) {
			throw new CancelarException(e.getLocalizedMessage());
		}
		
		txInfoEquipo = String.format("%s#%s#%s#%s#%s", nombreEquipo, estadio, presidente, fundación, tPropiedad);
		try {
			mostrarDatosEquipo(txInfoEquipo);
		} catch (LíneaCorruptaException e) {
			VistaGeneral.mostrarAviso("Ha habido algún problema al leer los datos: )" + e.getLocalizedMessage());
		}
		
		confirmación = VistaGeneral.pedirConfirmación("¿Confirmas los datos? → s - sí | n - no ");
		if (!confirmación) {
			throw new CancelarException("Operación cancelada");
		}

		nuevoEquipo = new Equipo(nombreEquipo, estadio, presidente, fundación, tPropiedad);
		
		return nuevoEquipo;
	}
	
	public static String lecturaString(String aIntroducir) {
		String aDevolver;
		Scanner scEntrada;
		boolean introducido;
		
		scEntrada = VistaGeneral.getScEntrada();
		if (scEntrada == null) { throw new NullPointerException("El objeto de Scanner no fue inicializado."); }
		
		aDevolver = null;
		introducido = false;
		
		System.out.printf("Introduzca dato \"%s\": ", aIntroducir);
		
		do {
			try {
				if (aDevolver == null) {
					aDevolver = scEntrada.nextLine();
					// <string>.trim() remueve los espacios, y similares, de la cadena de texto
					if (aDevolver.trim() == "") { throw new NoSuchElementException("Texto vacío."); }
				}
				introducido = true;
			} catch (NoSuchElementException e) {
				VistaGeneral.mostrarAviso("No se ha podido leer nada. " + e.getLocalizedMessage());
				aDevolver = lecturaString(aIntroducir);	// vuelve a pedir que se introduzca el dato. 
			}
		} while(!introducido);
		
		return aDevolver;
	}
	
	private static int lecturaEntero() {
		int num;	// entero a devolver
		boolean válido;
		
		num = 0;
		válido = false;
		
		do {
			try {
				num = VistaGeneral.pedirNúmero("Introduzca año de fundación (igual o posterior a 1857): ");
				if (num < 1857) {
					throw new NumberFormatException("El año de fundación debe ser 1857 o posterior");
				}
				válido = true;
			} catch (NumberFormatException e) {
				System.out.println(e.getLocalizedMessage());
			}
		} while (!válido);

		return num;
	}
	
	private static TipoPropiedad elecciónTipoPropiedad() throws CancelarException {
		TipoPropiedad[] tiposDePropiedad;
		List<String> txTiposDePropiedad;
		VistaMenú menú;
		int opciónEnum;

		tiposDePropiedad = TipoPropiedad.values();	// array con los valores del enumerado
		txTiposDePropiedad = new ArrayList<String>();

		for (TipoPropiedad t : tiposDePropiedad) {
			txTiposDePropiedad.add(t.toString());	// el menú funciona con una lista de String
		}

		menú = new VistaMenú("Tipo de propiedad", txTiposDePropiedad);
		VistaGeneral.mostrarTexto("Elige tipo de propiedad (con 0 se cancela todo el proceso)");
		menú.mostrarOpciones();	// muestra los enumerados y su correspondencia con un entero.
		opciónEnum = menú.pedirOpción();
		if (opciónEnum == 0) {
			throw new CancelarException("Operación cancelada");
		}
		
		return tiposDePropiedad[opciónEnum-1];
	}

	
	
	public static void mostrarDatosEquipo (String equipo) throws LíneaCorruptaException {
		String[] datosEquipo;
		datosEquipo = equipo.split("#");
		
		if (datosEquipo.length != 5) { throw new LíneaCorruptaException("Número de datos no esperado (5)."); }
		
		
		VistaGeneral.mostrarTítulo2(datosEquipo[0].toUpperCase());
		
		VistaGeneral.mostrarTexto("Nombre: " + datosEquipo[0]);
		VistaGeneral.mostrarTexto("Estadio: " + datosEquipo[1]);
		VistaGeneral.mostrarTexto("Presidente: " + datosEquipo[2]);
		VistaGeneral.mostrarTexto("Fundación: " + datosEquipo[3]);
		VistaGeneral.mostrarTexto("Tipo de propiedad: " + datosEquipo[4]);
	}
}
