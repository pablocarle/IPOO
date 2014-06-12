package modelo;

import java.util.List;

import java.util.Vector;

import vista.JugadaView;
import vista.TragamonedasView;
import controlador.exceptions.CantidadCasillasIncorrectaException;
import controlador.exceptions.CombinacionExistenteException;
import controlador.exceptions.PremioException;
import controlador.exceptions.PremioNoEncontradoException;
import controlador.exceptions.TragamonedasCreacionException;
import controlador.exceptions.TragamonedasException;

public class Tragamonedas {

	private int codigoTragamoneda;
	private float recaudacion;
	private float recaudacionMin;
	private float precioJugada;
	private float credito;

	private List<Premio> premios;
	private List<Casilla> casillas;
//	private List<Jugada> jugadas;


	public Tragamonedas(int codigoTragamoneda, float precioJugada, float recaudacionInicial, float recaudacionMinima, int cantCasillas) throws TragamonedasCreacionException {

		//-------------------------------------------------------------
		// Validaciones en el alta de tragamonedas y mostrar excepciones
		//-------------------------------------------------------------
		if (precioJugada <= 0 )
		{
			throw new TragamonedasCreacionException("El precio de jugada debe ser mayor que 0.");
		}else if ( cantCasillas <= 0){
			throw new TragamonedasCreacionException("La cantidad de casillas debe ser mayor que 0.");
		}else if( recaudacionMinima > recaudacionInicial){
			throw new TragamonedasCreacionException("La recaudaci�n m�nima debe ser mayor que la recaudaci�n inicial.");							        
		}   
		//-------------------------------------------------------------

		this.codigoTragamoneda = codigoTragamoneda;
		this.precioJugada = precioJugada;
		this.recaudacion = recaudacionInicial;
		this.recaudacionMin = recaudacionMinima;
		this.credito = 0;

		//		Crea un Vector reservando desde el inicio "cantCasillas" de espacios, de este modo se puede acceder a subindices aunque no esten cargados, evitando excepciones
		this.casillas = new Vector<Casilla>(cantCasillas);
		this.premios = new Vector<Premio>();
//		this.jugadas = new Vector<Jugada>();

	}

	public void agregarCasilla(int posicion, List<Fruta> frutas) {
		casillas.add(posicion, new Casilla(frutas));
	}

	public void crearPremio(List<Fruta> combinacion, float valorPremio) throws PremioException {
	
	//		Verificar el valor del premio y la cantidad de casillas
			int cantCasillas = casillas.size();
			
			if (buscarCombinacion(combinacion)){
				throw new CombinacionExistenteException(); 
			} else if (combinacion.size() == cantCasillas) {
				premios.add(new Premio(combinacion, valorPremio));
			} else
				throw new CantidadCasillasIncorrectaException();
		}

	/**
	 * 
	 * 
	 * @param combinacion
	 */
	public void bajaPremio(List<Fruta> combinacion) throws PremioException {
		
		Premio premio = buscarPremio(combinacion);
		
		if (premio != null) {
			premios.remove(premio);
		} else
			throw new PremioNoEncontradoException();
	}

	//View de tragamonedas 
	public TragamonedasView getView() {
		TragamonedasView tragamonedas = new TragamonedasView(codigoTragamoneda, casillas.size(), recaudacion, recaudacionMin, precioJugada, credito );

		return tragamonedas;
	}

	/**
	 * TODO 
	 * 
	 * @return
	 */
	public JugadaView jugarConMaquina() throws TragamonedasException {
		List<Fruta> combinacionDummy=new Vector<Fruta>();
		boolean tienePremioDummy=false;
		float premioValorDummy=0;

		try {
			if (this.sePuedeJugar()){
		        Jugada jugada=this.jugar();
		        JugadaView jugadaVista= jugada.getView();
		        return jugadaVista;
				
			}
		} catch (TragamonedasException e){
			throw new TragamonedasException(e.getMessage());
			
		}

		return new JugadaView(combinacionDummy, tienePremioDummy, premioValorDummy);
	
	}	

	public Jugada jugar() {

		List<Fruta> combinacion = new Vector<Fruta>();

		for (int i = 0; i < casillas.size() ; i++) { //Otra forma seria con el "foreach" de java (for (Casilla c : casillas))

			Fruta fruta = casillas.get(i).girar();

			combinacion.add(fruta);

		}
		this.setRecaudacion(this.getRecaudacion()+this.precioJugada);
		this.setCredito(this.getCredito()-this.getPrecioJugada());

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

	private boolean buscarCombinacion(List<Fruta> combinacion){

		for (int i=0;i<premios.size();i++){
			if (premios.get(i).getCombinacion().equals(combinacion)){
				return true;
			}
		}
		return false;
	}
	
	private Premio buscarPremio(List<Fruta> combinacion){
	
		for (Premio p : premios) {
			if (p.getCombinacion().equals(combinacion))
				return p;
		}
		return null;
	}

	public void incrementarCredito(Float creditoAdicional) {
		credito += creditoAdicional;
	}
	
	public void cobrarCredito(){
		credito = 0;
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
	 * @set the recaudacion
	 */
	private void setRecaudacion(float recaudacionNueva) {
		recaudacion=recaudacionNueva;
	}

	/**
	 * @set the credito
	 */
	private void setCredito(float creditoNuevo) {
		credito=creditoNuevo;
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

	public void cargarCasilla(int posicionCasilla, List<Fruta> frutas) {
		
		Casilla nuevaCasilla = new Casilla(frutas);
		casillas.add(posicionCasilla, nuevaCasilla);
		
	}

	private boolean sePuedeJugar() throws TragamonedasException {
        
		boolean sePuede=true;
		
		if (this.getCredito()>=0.0){
			if (this.getCredito()>=this.getPrecioJugada()){
				if (this.getRecaudacion()>this.getRecaudacionMin()){
					sePuede=true;
				}else{
					sePuede=false;
					throw new TragamonedasException("Se alcanz� la recaudaci�n minima");
				}
				
			}else{
				sePuede=false;
				throw new TragamonedasException("El credito actual de la maquina debe ser mayor o igual al precio de la jugada ");
			}
			
		}else{
			sePuede=false;
			throw new TragamonedasException("El credito actual de la maquina debe ser mayor que 0");
		}
		return sePuede;

	}

	
	
}
