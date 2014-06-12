package vista;

import java.util.List;
import modelo.Fruta;

public class JugadaView {
	
	private List<Fruta> combinacion;
	private boolean tienePremio;
	private float premioValor;
	
	public JugadaView(List<Fruta> combinacion, boolean tienePremio, float premioValor){
		this.combinacion= combinacion;
		this.tienePremio= tienePremio;
		this.premioValor= premioValor;
		
	}
	
	public List<Fruta> getCombinacion(){
		return combinacion;		
	}
	public boolean getTienePremio(){
		return tienePremio;		
	}
	public float getPremioValor(){
		return premioValor;
	}

}
