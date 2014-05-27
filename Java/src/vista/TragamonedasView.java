package vista;

import java.util.List;

import modelo.Casilla;
import modelo.Jugada;
import modelo.Premio;

public class TragamonedasView {

	private int codigoTragamoneda;
	private float recaudacion;
	private float recaudacionMin;
	private float precioJugada;
	private float credito;

	private List<Premio> premios;
	private List<Casilla> casillas;
	private List<Jugada> jugadas;
	
			
	public TragamonedasView(int codigoTragamoneda, float recaudacion, float recaudacionMin, float precioJugada, float credito, List<Premio> premios, List<Casilla> casillas, List<Jugada> jugadas) {
		
		this.codigoTragamoneda=codigoTragamoneda;
		this.recaudacion=recaudacion;
		this.recaudacionMin=recaudacionMin;
		this.precioJugada=precioJugada;
		this.credito=credito;
		this.premios=premios;
		this.casillas=casillas;
		this.jugadas=jugadas;
				
	}


	public int getCodigoTragamoneda() {
		return codigoTragamoneda;
	}


	public float getRecaudacion() {
		return recaudacion;
	}


	public float getRecaudacionMin() {
		return recaudacionMin;
	}


	public float getPrecioJugada() {
		return precioJugada;
	}


	public float getCredito() {
		return credito;
	}


	public List<Premio> getPremios() {
		return premios;
	}


	public List<Casilla> getCasillas() {
		return casillas;
	}


	public List<Jugada> getJugadas() {
		return jugadas;
	}



}
