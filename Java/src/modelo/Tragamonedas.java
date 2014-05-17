package modelo;

import java.util.List;
import java.util.Vector;

public class Tragamonedas {

	private int codigoTragamoneda;
	private float recaudacion;
	private float recaudacionMin;
	private float precioJugada;
	private float credito;

	private List<Premio> premios;
	private List<Casilla> casillas;
	
	
	public Tragamonedas(float precioJugada, float recaudacionInicial, float recaudacionMinima, int cantCasillas) {
		
		this.precioJugada = precioJugada;
		this.credito = recaudacionInicial;
		this.recaudacionMin = recaudacionMinima;
		
//		Crea un Vector reservando desde el inicio "cantCasillas" de espacios, de este modo se puede acceder a subindices aunque no esten cargados, evitando excepciones
		casillas = new Vector<Casilla>(cantCasillas);
		premios = new Vector<Premio>();
		
	}
	
	public void agregarCasilla(int posicion, List<Fruta> frutas) {
		casillas.add(posicion, new Casilla(frutas));
	}
	
	
	/**
	 * TODO Revisar retorno del metodo
	 * 
	 * @param combinacion
	 * @param valorPremio
	 */
	public void altaPremio(List<Fruta> combinacion, float valorPremio) {
		
		premios.add(new Premio(combinacion, valorPremio));
		
	}
	
	/**
	 * TODO Revisar retorno del metodo
	 * 
	 * @param combinacion
	 */
	public void bajaPremio(List<Fruta> combinacion) {
		
	}
	
	
	/**
	 * TODO 
	 * 
	 * @return
	 */
	public Jugada jugar() {
		
		List<Fruta> combinacion = new Vector<Fruta>();
		
		for (int i = 0; i < casillas.size() ; i++) { //Otra forma seria con el "foreach" de java (for (Casilla c : casillas))
			
			Fruta fruta = casillas.get(i).girar();
			
			combinacion.add(fruta);
			
		}
		
		if (this.esJugadaConPremio(combinacion))
			return new JugadaConPremio(combinacion);
		
		return new JugadaSinPremio(combinacion);
		
	}
	
	private boolean esJugadaConPremio(List<Fruta> combinacion) {
		
		for (int i = 0; i < premios.size(); i++){
			
			Premio premio = premios.get(i);
			
			List<Fruta> combinacionAEvaluar = premio.getCombinacion();
			
			if (combinacion.equals(combinacionAEvaluar))
				return true;
			else
				continue;
			
		}
	
		return false;
	}

	/*GETTERS Y SETTERS*/

	/**
	 * @return the codigoTragamoneda
	 */
	public int getCodigoTragamoneda() {
		return codigoTragamoneda;
	}

	/**
	 * @return the recaudacion
	 */
	public float getRecaudacion() {
		return recaudacion;
	}

	/**
	 * @return the recaudacionMin
	 */
	public float getRecaudacionMin() {
		return recaudacionMin;
	}

	/**
	 * @return the precioJugada
	 */
	public float getPrecioJugada() {
		return precioJugada;
	}

	/**
	 * @return the credito
	 */
	public float getCredito() {
		return credito;
	}

	/**
	 * @return the premios
	 */
	public List<Premio> getPremios() {
		return premios;
	}

	/**
	 * @return the casillas
	 */
	public List<Casilla> getCasillas() {
		return casillas;
	}
}