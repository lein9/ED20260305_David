package jcolonia.daw2025.equipos;

import java.util.List;
import java.util.ArrayList;

import jcolonia.daw2025.equipos.excepciones.LigaSinEquiposException;
import jcolonia.daw2025.equipos.excepciones.LíneaCorruptaException;

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
	 * @param nombre Nombre de la liga. 
	 * @param equipos Cadenas de texto con equipos de la liga.
	 */
	public Liga(String nombre, List <String> equipos) {
		this.liga = nombre;
		
		this.equipos = new ArrayList<Equipo>(20);
		
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
	 * Devuelve el equipo de la {@link Liga} con el índice pasado. 
	 * @param i Índice que ocupa en la {@link Liga} el equipo a devolver. 
	 * @return Equipo con el índice indicado. 
	 * @throws LigaSinEquiposException Lanzada cuando sin haber equipos, se intenta operar sobre uno. 
	 */
	public Equipo getEquipo (int i) throws LigaSinEquiposException {
		if (equipos.isEmpty()) {
			throw new LigaSinEquiposException("La liga no tiene equipos");
		}
		
		return equipos.get(i);
	}
	
	/**
	 * Exporta una lista de cadenas de texto, donde cada línea contiene los datos de un {@link Equipo}. 
	 * @return Lista de cadenas de texto donde cada cadena contiene los datos de un {@link Equipo}. 
	 * @throws LigaSinEquiposException Lanzada cuando sin haber equipos, se intenta operar sobre uno. 
	 */
	public List<String> toListaExportar() throws LigaSinEquiposException {
		if (equipos.isEmpty()) {
			throw new LigaSinEquiposException("La liga no tiene equipos");
		}
		
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
	 * @throws LigaSinEquiposException Lanzada cuando sin haber equipos, se intenta operar sobre uno. 
	 */
	public List<String> toListaPantalla() throws LigaSinEquiposException {
		if (equipos.isEmpty()) {
			throw new LigaSinEquiposException("La liga no tiene equipos");
		}
		
		List <String> líneaAExportar;
		líneaAExportar = new ArrayList <String> (20);
		
		for (Equipo e : equipos) {
			líneaAExportar.add(e.toLíneaExportar());
		}
		
		return líneaAExportar;
	}
}
