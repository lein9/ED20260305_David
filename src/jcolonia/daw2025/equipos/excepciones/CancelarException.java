package jcolonia.daw2025.equipos.excepciones;

import jcolonia.daw2025.equipos.VistaAltaEquipo;

/**
 * Excepción lanzada cuando se cancela la operación de alta de un equipo. 
 * Utilizada por ejemplo en {@link VistaAltaEquipo#elecciónTipoPropiedad}. 
 * 
 * @author dani - GitHub: lein9
 * @version 1.0 20260322
 */
public class CancelarException extends Exception {
	/** Identificador de versión. */
	private static final long serialVersionUID = 20260322000L;

	/**
	 * Crea una excepción sin ninguna descripción.
	 */
	public CancelarException() {
		super();
	}

	/**
	 * Crea una excepción con descripción.
	 * 
	 * @param descripción El texto que describe la excepción ocurrida.
	 */
	public CancelarException(String descripción) {
		super(descripción);
	}
}
