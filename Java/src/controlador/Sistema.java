package controlador;

import java.util.List;
import java.util.Vector;

import modelo.Fruta;
import modelo.Jugada;
import modelo.Tragamonedas;
import controlador.exceptions.MaquinaNoEncontradaException;

public class Sistema {
	
	private List<Fruta> frutasDisponibles;
	private List<Tragamonedas> tragamonedas;
	
	public Sistema() {
	
		frutasDisponibles = new Vector<Fruta>();
		frutasDisponibles.add(new Fruta("Frutilla", "frutilla.png"));
		frutasDisponibles.add(new Fruta("Sandia", "sandia.png"));
		frutasDisponibles.add(new Fruta("Banana", "banana.png"));
		frutasDisponibles.add(new Fruta("Manzana", "manzana.png"));
		frutasDisponibles.add(new Fruta("Pera", "pera.png"));
		
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
	public void crearTragamonedas(float precioJugada, float recaudacionInicial, float recaudacionMinima, int cantCasillas) {
//		TODO Corregir constructor
		tragamonedas.add(new Tragamonedas(precioJugada, recaudacionInicial, recaudacionMinima, cantCasillas));
			
	}
	
	
	/**
	 * TODO Esto deberia devolver una vista
	 */
	public void altaPremio(int nroMaquina, List<Fruta> combinacionPremio, float valorPremio) {
		
		try {
			
			Tragamonedas maquina = this.buscarTragamonedas(nroMaquina);
			
//			TODO agregarPremio aun no implementado
			maquina.altaPremio(combinacionPremio, valorPremio);
			
		} catch (MaquinaNoEncontradaException e) {
			
		} catch (Exception e) {
//			Error no esperado
		}
		
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
	private Tragamonedas buscarTragamonedas(int nroMaquina) throws MaquinaNoEncontradaException {
		
		for (int i = 0; i < tragamonedas.size(); i++) {
			if (tragamonedas.get(i).getCodigoTragamoneda() == nroMaquina)
				return tragamonedas.get(i);
		}
		
		throw new MaquinaNoEncontradaException("No se encontro el tragamonedas solicitado");
	}
}
