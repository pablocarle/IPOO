package modelo;

import java.util.List;

import vista.JugadaView;

public abstract class Jugada {
	
	List<Fruta> combinacion;

	public Jugada(List<Fruta> combinacion) {
		this.combinacion =  combinacion;
	}
	
	public List<Fruta> getCombinacion(){
		return combinacion;
	}

	public abstract boolean tienePremio();

	public abstract float getPremioValor();
	
	public abstract JugadaView getView();

}
