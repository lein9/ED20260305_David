package jcolonia.daw2025.equipos;

import java.util.List;
import java.util.ArrayList;

/**
 * Representación de una liga. Esta consta de nombre, una lista de equipos y funciones para consultar; 
 * exportar, añadir equipos y crear ligas.  
 * 
 * @author dani - GitHub: lein9
 * @version 1.0 20260319
 */
public class Liga {
	/** Nombre de la {@link Liga}. */
	private String liga;
	/** Almacén de {@link Equipo equipos} de la {@link Liga}. */
	private List <Equipo> equipos;
	
	/**
	 * Crea una {@link Liga} que tendrá equipos de fútbol.
	 */
	public Liga(String nombre) {
		this.liga = nombre;
		equipos = new ArrayList <Equipo>(20);
	}
	
	/**
	 * Crea una Liga que tendrá {@link Liga#equipos equipos} de fútbol. 
	 * @param 
	 */
	public Liga(String nombre, List <String> equipos) {
		this.liga = nombre;
		
		for(int i=0; i<equipos.size(); i++) {
			try {
				this.equipos.add( Equipo.of(equipos.get(i)) );
			} catch (LíneaCorruptaException e) {
				System.out.printf("\033[35mLa línea %d tiene un problema: %s\033[m%n", i, e.getMessage());
			}
		}
	}
	
	/**
	 * Devuelve el nombre de la {@link Liga#liga liga}. 
	 * @return Nombre de la {@link Liga#liga liga}
	 */
	public String getNombre() {
		return liga;
	}
	
	/**
	 * Devuelve el número de {@link Liga#equipos equipos} de la {@link Liga}. 
	 * @return Número de {@link Liga#equipos equipos} de la {@link Liga}.
	 */
	public int numEquipos() {
		return equipos.size();
	}
	
	/**
	 * Añade un equipo a la lista de {@link Liga#equipos equipos}. 
	 * @param equipo {@link Equipo} que se añadirá a la {@link Liga}
	 */
	public void añadirEquipo(Equipo equipo) {
		equipos.add(equipo);
	}
	
	/**
	 * Exporta una lista de cadenas de texto, donde cada línea contiene los datos de un {@link Equipo}. 
	 * @return Lista de cadenas de texto donde cada cadena contiene los datos de un {@link Equipo}. 
	 */
	public List<String> toListaExportar() {
		List <String> líneaAExportar;
		líneaAExportar = new ArrayList <String> (20);
		
		for (Equipo e : equipos) {
			líneaAExportar.add(e.toLíneaExportar());
		}
		
		return líneaAExportar;
	}
	
	/**
	 * Devuelve una lista de cadenas de texto donde cada línea se corresponde con la información de un 
	 * equipo de la {@link Liga}. 
	 * 
	 * @return Lista de cadenas de texto con los datos de los equipos de la {@link Liga}
	 */
	public List<String> toListaPantalla() {
		List <String> líneaAExportar;
		líneaAExportar = new ArrayList <String> (20);
		
		for (Equipo e : equipos) {
			líneaAExportar.add(e.toLíneaExportar());
		}
		
		return líneaAExportar;
	}
}
