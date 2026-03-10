package jcolonia.daw2025.tablasmvc;
import java.util.ArrayList;
import java.util.List;

/**
 * Representación de una tabla de multiplicar. Con funciones para crear la tabla, para definir el formato 
 * de exportación, de impresión y para sacar una representación de texto de la misma. 
 * @author dani <GitHub: lein9>
 * @version 2.0 20260309
 */
public class TablaMultiplicar {
	private int número;
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
	 * Devuelve una lista de cadenas de texto que tienen, cada una, una línea de la {@link TablaMultiplicar}. 
	 * Las líneas están separadas por una ','. 
	 * 
	 * @return una lista de cadenas de texto con las líneas de la {@link TablaMultiplicar}, con un separador ',' por línea. 
	 */
	public List<String> toListaExportación() {
		// Hay que ponerle un separador, para cada campo (tipo CSV)
		List <String> aDevolver;
		aDevolver = new ArrayList <String>(11);
		for (int i=0; i<listaTextos.size(); i++) {
			aDevolver.add( String.format("%s,", listaTextos.get(i)) );	// separador ','
		}
		return aDevolver;
	}
	
	/**
	 * Devuelve una lista de cadenas de texto que tienen, cada una, una línea de la {@link TablaMultiplicar}. 
	 * Cada línea tiene un salto de línea al final.
	 * 
	 * @return una lista de cadenas de texto con las líneas de la {@link TablaMultiplicar}, con un salto de línea al final de cada una. 
	 */
	public List<String> toListaPantalla() {
		// Con formato, para la impresión
		List <String> aDevolver;
		aDevolver = new ArrayList <String>(11);
		for (int i=0; i<listaTextos.size(); i++) {
			aDevolver.add( String.format("%s%n", listaTextos.get(i)) );	// añado '%n'
		}
		return aDevolver;
	}
	
	/**
	 * Devuelve una cadena de texto con todas las líneas de la instancia de {@link TablaMultiplicar} con la 
	 * siguiente estructura (el ×, no es el carácter ×):<div>
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
		/* probablemente lo suyo fuese haber usado toListaPantalla(), en vez de todo esto */
		String devolución;
		String[] temp;
		devolución = null;
		temp = new String[10];
		
		for(int i=0; i<listaTextos.size(); i++) {
			temp[i] = String.format("%s%n", listaTextos.get(i));
		}
		for (int i=0; i<temp.length; i++) {
			devolución = "" + temp[i];
		}
		return devolución;
	}
}
