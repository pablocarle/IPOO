package modelo;

import java.util.List;

public abstract class Jugada {
	
	List<Fruta> combinacion;

	public Jugada(List<Fruta> combinacion) {
		this.combinacion =  combinacion;
	}
	
	public abstract boolean tienePremio();

}
