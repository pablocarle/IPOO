package modelo;

import java.util.List;

public class JugadaConPremio extends Jugada {

	public JugadaConPremio(List<Fruta> combinacion) {
		super(combinacion);
	}

	@Override
	public boolean tienePremio() {
		// TODO Auto-generated method stub
		return false;
	}

}
