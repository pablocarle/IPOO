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


			
	public TragamonedasView(int codigoTragamoneda, float recaudacion, float recaudacionMin, float precioJugada, float credito) {
		
		this.codigoTragamoneda=codigoTragamoneda;
		this.recaudacion=recaudacion;
		this.recaudacionMin=recaudacionMin;
		this.precioJugada=precioJugada;
		this.credito=credito;

				
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


}
