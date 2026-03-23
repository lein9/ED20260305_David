package jcolonia.daw2025.equipos;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import jcolonia.daw2025.comun.ExportaciónArchivo;

public class AccesoArchivo {
	
	public static void escribir(String rutaExportación, List <String> contenidos) throws IOException {
		if (rutaExportación == null || rutaExportación.isEmpty() || contenidos == null) {
			throw new IOException("La ruta de exportación está vacía o no hay donde leer datos.");
		}
		
		ExportaciónArchivo.exportaciónArchivo(rutaExportación);
		ExportaciónArchivo.guardar(contenidos);
	}
	
	public static List <String> leer(String rutaExportación) throws IOException {
		if (rutaExportación == null || rutaExportación.isEmpty()) {
			throw new IOException("La ruta de exportación está vacía.");
		}
		
		Path refArchivo;
		List <String> textoTabla;
		String texto;
		boolean salir;
		
		refArchivo = Path.of(rutaExportación);
		textoTabla = new ArrayList <String> ();
		salir = false;
		
		try {
			BufferedReader in = Files.newBufferedReader(refArchivo, Charset.forName("UTF-8"));
			do {
				texto = in.readLine();
				if (texto == null) {
					salir = true;
				} else {
					textoTabla.add(String.format("%s",texto));
				}
			} while (!salir);
			in.close();
		} catch (IOException e) {
			throw new IOException("Error de lectura: " + e.getLocalizedMessage());
		}
		
		return textoTabla;
	}
}
