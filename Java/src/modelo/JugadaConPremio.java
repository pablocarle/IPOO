package modelo;

import java.util.List;

public class JugadaConPremio extends Jugada {

	private Premio premio;
	
	public JugadaConPremio(List<Fruta> combinacion) {
		super(combinacion);
	}

	@Override
	public boolean tienePremio() {
		return true;
	}

}
