package jcolonia.daw2025.equipos.excepciones;

import jcolonia.daw2025.equipos.Liga;

/**
 * Excepción lanzada cuando se intenta operar sobre equipos en una liga sin equipos. 
 * Utilizada por ejemplo en {@link Liga#getEquipo}. 
 * 
 * @author dani - GitHub: lein9
 * @version 1.0 20260322
 */
public class LigaSinEquiposException extends Exception {
	/** Identificador de versión. */
	private static final long serialVersionUID = 20260322001L;

	/**
	 * Crea una excepción sin ninguna descripción.
	 */
	public LigaSinEquiposException() {
		super();
	}

	/**
	 * Crea una excepción con descripción.
	 * 
	 * @param descripción El texto que describe la excepción ocurrida.
	 */
	public LigaSinEquiposException(String descripción) {
		super(descripción);
	}
}
