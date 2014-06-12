package modelo;

import java.util.List;

import vista.JugadaView;

public class JugadaConPremio extends Jugada {

	private Premio premio;
	
	public JugadaConPremio(List<Fruta> combinacion) {
		super(combinacion);
	}

	@Override
	public boolean tienePremio() {
		return true;
	}
	public float getPremioValor(){
		return premio.getValorPremio();
	}
	
	public JugadaView getView(){
		return new JugadaView(this.getCombinacion(),this.tienePremio(),this.getPremioValor());
	}

}
