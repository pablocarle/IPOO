package controlador;

import java.util.List;
import java.util.Vector;

import modelo.Fruta;
import modelo.Jugada;
import modelo.Tragamonedas;
import vista.TragamonedasView;
//import jdk.nashorn.internal.runtime.arrays.ArrayLikeIterator;
import vista.UserMessageView;
import controlador.exceptions.MaquinaNoEncontradaException;
import controlador.exceptions.PremioException;
import controlador.exceptions.PremioNoEncontradoException;
import controlador.exceptions.TragamonedasCreacionException;

public class Sistema {
	
	private List<Fruta> frutasDisponibles;
	private List<Tragamonedas> tragamonedas;
	
	public Sistema() {
	
		frutasDisponibles = new Vector<Fruta>();
		frutasDisponibles.add(new Fruta("Frutilla", "frutilla.jpg"));
		frutasDisponibles.add(new Fruta("Sandia", "sandia.jpg"));
		frutasDisponibles.add(new Fruta("Banana", "banana.jpg"));
		frutasDisponibles.add(new Fruta("Manzana", "manzana.jpg"));
		frutasDisponibles.add(new Fruta("Pera", "pera.jpg"));
		
		tragamonedas = new Vector<Tragamonedas>();
	}
	
	public boolean validarPremio() {
		
		return false;
		
	}
	
	/**
	 * TODO Esto deberia devolver una vista
	 * 
	 * @param precioJugada
	 * @param recaudacionInicial
	 * @param recaudacionMinima
	 * @param cantCasillas
	 */
	public TragamonedasView crearTragamonedas(float precioJugada, float recaudacionInicial, float recaudacionMinima, int cantCasillas) throws TragamonedasCreacionException {
		
		Tragamonedas tragamonedasNuevo = new Tragamonedas(tragamonedas.size() + 1, precioJugada, recaudacionInicial, recaudacionMinima, cantCasillas);
		tragamonedas.add(tragamonedasNuevo);
		return tragamonedasNuevo.getView();
	
	}
	
	
	/**
	 * TODO Esto deberia devolver una vista
	 */
	public UserMessageView altaPremio(int nroMaquina, List<String> combinacionInput, float valorPremio) {
		
		try {
			 List<Fruta> combinacionPremio = new Vector<Fruta>();
			
			for (int i=0;i<combinacionInput.size();i++){
				Fruta comb = new Fruta(combinacionInput.get(i)," ");
				combinacionPremio.add(comb);
			}
			
			Tragamonedas maquina = this.buscarTragamonedas(nroMaquina);
			
			if (maquina != null){
			   
			   if (valorPremio < 0 ){
				   throw new PremioException("Importe de premio debe ser mayor a 0");
			   }
				   if (!maquina.crearPremio(combinacionPremio, valorPremio)){
				   throw new PremioNoEncontradoException("La combinacion ingresada ya Existe");
			   }
			   
			}
				
		} catch (MaquinaNoEncontradaException e) {
//			FIXME
			return null;	
		} catch (PremioException e){
			return null;
		} catch (PremioNoEncontradoException e){
			return null;
		}
//		FIXME
		return null;
	}
	
	/**
	 * TODO Esto deberia devolver una vista
	 */
	public void jugarConMaquina(int nroMaquina) {
		
		try {
			Tragamonedas maquina = this.buscarTragamonedas(nroMaquina);
			
			Jugada jugada = maquina.jugar();
			
			if (jugada.tienePremio()) {
//				TODO Hacer algo si la jugada tuvo premio
			} else {
//				TODO Hacer algo si la jugada no tuvo premio
			}
			
		} catch (MaquinaNoEncontradaException e) {
//			TODO Devolver vista con error al usuario
		} catch (Exception e) {
//			Error no esperado
		}	
	}

	/**
	 * Busca el tragamonedas por codigo asignado en la coleccion de tragamonedas conocida por Sistema
	 * 
	 * @param nroMaquina El numero de maquina a buscar
	 * @return Instancia de tragamonedas
	 * @throws MaquinaNoEncontradaException Si no se encontro la maquina
	 */
	public Tragamonedas buscarTragamonedas(int nroMaquina) throws MaquinaNoEncontradaException {
		
		for (int i = 0; i < tragamonedas.size(); i++) {
			if (tragamonedas.get(i).getCodigoTragamoneda() == nroMaquina)
				return tragamonedas.get(i);
		}
		
		throw new MaquinaNoEncontradaException("No se encontro el tragamonedas solicitado");
	}
}
