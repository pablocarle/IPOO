package vista;


public class TragamonedasView {

	private int codigoTragamoneda;
	private int cantidadCasillas;
	private float recaudacion;
	private float recaudacionMin;
	private float precioJugada;
	private float credito;


			
	public TragamonedasView(int codigoTragamoneda, int cantidadCasillas, float recaudacion, float recaudacionMin, float precioJugada, float credito) {
		
		this.codigoTragamoneda=codigoTragamoneda;
		this.recaudacion=recaudacion;
		this.recaudacionMin=recaudacionMin;
		this.precioJugada=precioJugada;
		this.credito=credito;
		this.cantidadCasillas = cantidadCasillas;
				
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


	public int getCantidadCasillas() {
		return cantidadCasillas;
	}	
}
