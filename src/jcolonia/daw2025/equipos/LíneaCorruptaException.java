package jcolonia.daw2025.equipos;

/**
 * Excepción lanzada cuando la línea con la que se va a trabajar no tiene los datos esperados. 
 * Utilizada por ejemplo en {@link Equipo}. 
 * 
 * @author dani - GitHub: lein9
 * @version 1.0 20260318
 */
public class LíneaCorruptaException extends Exception {
	/** Identificador de versión. */
	private static final long serialVersionUID = 20260318000L;

	/**
	 * Crea una excepción sin ninguna descripción.
	 */
	public LíneaCorruptaException() {
		super();
	}

	/**
	 * Crea una excepción con descripción.
	 * 
	 * @param descripción El texto que describe la excepción ocurrida.
	 */
	public LíneaCorruptaException(String descripción) {
		super(descripción);
	}
}
