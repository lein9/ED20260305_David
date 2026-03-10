package jcolonia.daw2025.tablasmvc;

public class VistaMenú extends VistaGeneral{
	private String título;
	public final String[] OPCIONES_MENÚ_PRINCIPAL;
	
	public VistaMenú(String título, String[] arrayDeOpciones) {
		this.título = título;
		this.OPCIONES_MENÚ_PRINCIPAL = arrayDeOpciones;
	}
}
