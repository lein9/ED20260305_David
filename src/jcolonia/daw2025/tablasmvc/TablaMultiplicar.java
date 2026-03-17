package jcolonia.daw2025.tablasmvc;
import java.util.ArrayList;
import java.util.List;

/**
 * Representación de una tabla de multiplicar. Con funciones para crear la tabla, para obtener sus líneas, 
 * y para sacar una representación de texto de la misma. 
 *  
 * @author dani <GitHub: lein9>
 * @version 3.0 20260313
 */
public class TablaMultiplicar {
	/** Número del que se ha hecho la {@link TablaMultiplicar} */
	private int número;
	/** Lista que guardará las líneas de la tabla de multiplicar */
	private List<String> listaTextos;
	
	/**
	 * Genera la tabla de multiplicar del número correspondiente. 
	 * @param número del que se elaborará la tabla de multiplicar. 
	 */
	public TablaMultiplicar(int número) {
		this.número = número;
		listaTextos = new ArrayList <String>(11);
	}
	
	/**
	 * Devuelve el número del que se ha hecho la {@link TablaMultiplicar}. 
	 * @return número del que se ha hecho la {@link TablaMultiplicar}. 
	 */
	public int getNúmero() {
		return número;
	}
	
	/**
	 * Genera la tabla de multiplicar. 
	 * La cual corresponde al número pasado a {@link TablaMultiplicar}
	 */
	public void generarTabla() {
		//genera la tabla (lo que tengo en el constructor)
		for (int i = 0; i <= 10; i++) {
			listaTextos.add(String.format("%d × %2d = %3d", número, i, número * (i)));
		}
	}
	
	/**
	 * Devuelve una lista de cadenas de texto, encerradas entre comillas dobles, donde cada una es una línea 
	 * de {@link TablaMultiplicar}. 
	 * Las comillas dobles se ponen pensando en exportación. Por ejemplo, con la tabla del 7 podríamos tener:<div>
	 * <h2 style="font-size: 1em">CSV (separado por coma)</h2>
	 * "7 ×  0 =   0","7 ×  1 =   7", ... , "7 ×  9 =  63", "7 × 10 =  70"<br>
	 * <br>
	 * <h2 style="font-size: 1em">TSV (separado por tabulador)</h2>
	 * "7 ×  0 =   0" &ensp; "7 ×  1 =   7" &ensp; ...  &ensp; "7 ×  9 =  63" &ensp; "7 × 10 =  70"<br>
	 * <br>
	 * <h2 style="font-size: 1em">JSON</h2><pre>
	 * {
	 *  "clave0": "7 ×  0 =   0",
	 *  "clave1": "7 ×  1 =   7",
	 * 		. . .
	 *  "clave9": "7 ×  9 =  63",
	 *  "clave10": "7 × 10 =  70"
	 * }</pre>
	 * 
	 * A la hora de trabajar con las cadenas de texto retornadas, hay que tratar posibles apariciones de comillas 
	 * dobles dentro del texto. Por ejemplo, en CSV se escapan con dobles comillas <span style="color: cadetblue;">""</span>, en java con un \ delante <span style="color: cadetblue;">\"</span>. 
	 * 
	 * @return Lista de cadenas de texto con las líneas de la {@link TablaMultiplicar} entrecomilladas 
	 */
	public List<String> toListaExportación() {
		List <String> aDevolver;
		aDevolver = new ArrayList <String>(11);
		for (int i=0; i<listaTextos.size(); i++) {
			aDevolver.add( String.format("\"%s\"", listaTextos.get(i)) );
		}
		return aDevolver;
	}
	
	/**
	 * Devuelve una lista de cadenas de texto, cada una, con una línea de la {@link TablaMultiplicar}. 
	 * 
	 * @return una lista de cadenas de texto con las líneas de la {@link TablaMultiplicar}
	 */
	public List<String> toListaPantalla() {
		return listaTextos;
	}
	
	/**
	 * Devuelve una cadena de texto con todas las líneas de la instancia de {@link TablaMultiplicar} con la 
	 * siguiente estructura (el ×, no es el carácter x):<div>
	 * <pre>3 ×  0 =   0
	 *3 ×  1 =   3
	 *3 ×  2 =   6
	 *  .  .  .  
	 *3 ×  9 =  27
	 *3 × 10 =  30</pre>
	 *</div>
	 * 
	 * @return cadena de texto con todas las líneas de la tabla de multiplicar. 
	 */
	@Override
	public String toString() {
		StringBuffer devolución;
		devolución = new StringBuffer();
		for(int i=0; i<listaTextos.size(); i++) {
			devolución.append(String.format( "%s%n", listaTextos.get(i)));
		}
		return devolución.toString();
	}
}
