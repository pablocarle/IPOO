package modelo;

import java.util.List;

public class JugadaSinPremio extends Jugada {

	public JugadaSinPremio(List<Fruta> combinacion) {
		super(combinacion);
	}

	@Override
	public boolean tienePremio() {
		// TODO Auto-generated method stub
		return false;
	}

}
