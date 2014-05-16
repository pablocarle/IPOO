package modelo;

import java.util.List;
import java.util.Random;

public class Casilla {

	private List<Fruta> frutas;
	
	public Casilla(List<Fruta> frutas) {
		super();
		this.frutas = frutas;
	}


	/**
	 * "Gira" el rodillo y devuelve una fruta aleatoriamente (de entre las cargadas en la casilla)
	 * 
	 * @return
	 */
	public Fruta girar() {
		
		return frutas.get(new Random().nextInt(frutas.size()));
		
	}
}
