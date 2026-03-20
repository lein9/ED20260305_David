package jcolonia.daw2025.tablasmvc;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Proporciona funciones para indicar la ruta de exportación del archivo y para guardar los datos. 
 * 
 * @author dani - GitHub: lein9
 * @version 2.0 20260312
 */
public final class ExportaciónArchivo {
	/** ruta/nombre del archivo que se creará */
	private static Path refArchivo;
	
	// private ExportaciónArchivo() {}
	
	/**
	 * Indica dónde y con qué nombre guardar el archivo. 
	 * @param rutaArchivo ruta/nombre del archivo.  
	 */
	public static void exportaciónArchivo(String rutaArchivo) {
		refArchivo = Path.of(rutaArchivo);
	}
	
	/**
	 * Graba los datos en el archivo que se ha indicado mediante {@link ExportaciónArchivo#exportaciónArchivo(String)} 
	 * 
	 * @param contenidos líneas a guardar en el archivo a exportar
	 */
	public static void guardar(List <String> contenidos) {
		if (refArchivo == null) {
			throw new IllegalArgumentException("La ruta no puede ser nula");
		}
		
		try {
			Files.deleteIfExists(refArchivo);
			Path archivo = Files.createFile(refArchivo);
			
			BufferedWriter escribe;
			escribe = Files.newBufferedWriter(archivo, Charset.forName("windows-1252"));
			PrintWriter salida = new PrintWriter(escribe);

			for (String s : contenidos) {
				salida.println(s);
			}

			salida.close();
		} catch (IOException e) {
			System.err.printf("Error de escritura: %s", e.getLocalizedMessage());
		}
	}
}
