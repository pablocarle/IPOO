package modelo;

import java.util.List;

import vista.JugadaView;

public class JugadaSinPremio extends Jugada {

	public JugadaSinPremio(List<Fruta> combinacion) {
		super(combinacion);
	}

	@Override
	public boolean tienePremio() {
		return false;
	}
	
	public float getPremioValor(){
		return 0;
	}
	
	public JugadaView getView(){
		return new JugadaView(this.getCombinacion(),this.tienePremio(),this.getPremioValor());
	}


}
