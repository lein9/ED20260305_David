package jcolonia.daw2025.equipos;

import jcolonia.daw2025.equipos.excepciones.LíneaCorruptaException;

/**
 * Representación de un equipo de fútbol. 
 * Tiene los siguientes datos: nombre, estadio, presidente, año de fundación y tipo de propiedad. 
 * Contiene getters y setters para dichos datos y una función para exportarlos. 
 * 
 * @author dani - GitHub: lein9
 * @version 1.0 20260319
 */
public class Equipo {
	/** 
	 * Tipos de propiedad que puede tener un equipo de fútbol:
	 * CLUB_DE_SOCIOS - <em>Club de fútbol</em>, pertenece a los aficionados-socios del club. 
	 * SAD - Sociedad Anónima Deportiva, pertenece a los accionistas. 
	 */
	public enum TipoPropiedad { CLUB_DE_SOCIOS, SAD };
	// En 1ª división, que sean club de socios solo existen: RM, FBC, OSA y ATH.
	
	/** Nombre del equipo */
	private String nombre;
	/** Nombre del estadio */
	private String estadio;
	/** Nombre del presidente actual */
	private String presidente;
	/** Año de fundación */
	private int fundación;
	/** Indica el tipo de propiedad: club de socios o S.A.D */
	private TipoPropiedad propiedad;
	
	/**
	 * Crea un equipo de fútbol con datos sobre él. 
	 * @param nombre Cadena de texto con el nombre del equipo.
	 * @param estadio Cadena de texto con el nombre del estadio.
	 * @param presidente Cadena de texto con el nombre del presidente. 
	 * @param fundación Entero con el año de fundación del equipo. 
	 * @param propiedad Enumerado que indica el tipo de propiedad del equipo: 
	 * <span style="color: cadetblue">CLUB_DE_SOCIOS</span>, <span style="color: cadetblue">SAD</span>
	 */
	public Equipo(String nombre, String estadio, String presidente, int fundación, TipoPropiedad propiedad) {
		this.nombre = nombre;
		this.estadio = estadio;
		this.presidente = presidente;
		this.fundación = fundación;
		this.propiedad = propiedad;
	}
	
	/**
	 * Crea un equipo de fútbol, con sus datos, a partir de una cadena de texto que se le pasa 
	 * como parámetro. Línea que contiene los datos del equipo, que serán:
	 * Tres cadenas de texto: nombre (del equipo), nombre del estadio y nombre del presidente. 
	 * Un entero: el año de la fundación.
	 * Un dato enumerado: CLUB_DE_SOCIOS o SAD.
	 * 
	 * @param líneaImportada Línea recibida con datos del club. 
	 * @return El nuevo equipo creado. 
	 * @throws LíneaCorruptaException Lanzada cuando la línea pasada como parámetro no sigue la estructura esperada.
	 */
	public static Equipo of(String líneaImportada) throws LíneaCorruptaException {
		if (líneaImportada == null) { throw new LíneaCorruptaException("La línea pasada está vacía."); }

		Equipo equipoNuevo;
		String textoTipo;
		
		// datos que almacenará el nuevo equipo
		String nombreNuevo;
		String estadioNuevo;
		String presidenteNuevo;
		int fundaciónNuevo;
		TipoPropiedad propiedadNuevo;
		
		// Dividimos el String del parámetro y metemos las divisiones en un array
		String[] datosEquipo;
		datosEquipo = new String[5];
		datosEquipo = líneaImportada.split("#");
		
		if (datosEquipo.length != 5) { throw new LíneaCorruptaException("Número de datos no esperado (5)."); }
						
		nombreNuevo = datosEquipo[0];
		estadioNuevo = datosEquipo[1];
		presidenteNuevo = datosEquipo[2];

		try {
			fundaciónNuevo = Integer.parseInt(datosEquipo[3]);
			if (fundaciónNuevo < 1857) {
				// El 1er club de fútbol (Sheffield F.C) fue fundado en 1857
				throw new LíneaCorruptaException("El año debe ser igual o posterior a 1857");
			}
		} catch(NumberFormatException e) {
			throw new LíneaCorruptaException("El año de fundación debería ser un entero.");
		}

		try {
			/* tuve problemas al intentar comparar con datosEquipo[4], así que le dí 
			 * vueltas hasta que funcionó. */
			textoTipo = TipoPropiedad.valueOf(datosEquipo[4].trim()).toString();
		} catch (IllegalArgumentException e) {
			throw new LíneaCorruptaException("El dato de tipo de propiedad es incorrecto. Válido: CLUB_DE_SOCIOS o SAD");
		}
		
		if ( "CLUB_DE_SOCIOS".equals(textoTipo) ) {
			propiedadNuevo = TipoPropiedad.CLUB_DE_SOCIOS;
		} else {
			propiedadNuevo = TipoPropiedad.SAD;
		}
		
		equipoNuevo = new Equipo(nombreNuevo, estadioNuevo, presidenteNuevo, fundaciónNuevo, propiedadNuevo);

		return equipoNuevo;
	}

	/**
	 * Devuelve el nombre del equipo. 
	 * @return Cadena de texto con el nombre del equipo
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Pone el nombre del equipo. 
	 * @param nombre de texto con el nombre del equipo. 
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * Devuelve el estadio del equipo.
	 * @return Cadena de texto con el nombre del estadio del equipo
	 */
	public String getEstadio() {
		return estadio;
	}

	/**
	 * Pone el estadio del equipo.
	 * @param estadio Cadena de texto con el nombre del estadio del equipo.
	 */
	public void setEstadio(String estadio) {
		this.estadio = estadio;
	}

	/**
	 * Devuelve el nombre del presidente del equipo.
	 * @return Cadena de texto con el nombre del presidente del equipo
	 */
	public String getPresidente() {
		return presidente;
	}

	/**
	 * Pone el nombre del presidente del equipo.
	 * @param presidente Cadena de texto con el nombre del presidente del equipo.
	 */
	public void setPresidente(String presidente) {
		this.presidente = presidente;
	}

	/**
	 * Devuelve el año de fundación del equipo.
	 * @return entero con el año de fundación del equipo
	 */
	public int getFundación() {
		return fundación;
	}

	/**
	 * Pone el año de fundación del equipo.
	 * @param fundación Entero con el año de fundación del equipo.
	 */
	public void setFundación(int fundación) {
		this.fundación = fundación;
	}
	
	/**
	 * Devuelve el tipo de propiedad del equipo.
	 * @return enumerado El tipo de propiedad del equipo
	 */
	public TipoPropiedad getPropiedad() {
		return propiedad;
	}

	/**
	 * Pone el tipo de propiedad del equipo.
	 * @param propiedad Enumerado con el tipo de propiedad del equipo.
	 */
	public void setPropiedad(TipoPropiedad propiedad) {
		this.propiedad = propiedad;
	}
	
	/**
	 * Exporta los datos del equipo como una cadena de texto, donde cada dato 
	 * se separa por el símbolo <span style="color: cadetblue">#</span>: <br>
	 * Real Madrid C.F.<span style="color: cadetblue">#</span>Santiago Bernabéu
	 * <span style="color: cadetblue">#</span>Florentino Pérez
	 * <span style="color: cadetblue">#</span>1902<span style="color: cadetblue">#</span>
	 * CLUB_DE_SOCIOS
	 * 
	 * <p>Pensado para almacenar el texto en un fichero de texto plano de acceso secuencial.</p>
	 * @return Cadena de texto con los datos del equipo separados por <span style="color: cadetblue">#</span>.
	 */
	public String toLíneaExportar() {
		String líneaAExportar;
		líneaAExportar = String.format("%s#%s#%s#%s#%s", 
				nombre, estadio, presidente, fundación, propiedad.toString());
		
		return líneaAExportar;
	}
}
