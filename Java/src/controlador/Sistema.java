package controlador;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import modelo.Fruta;
import modelo.Premio;
import modelo.Tragamonedas;
import vista.JugadaView;
import vista.TragamonedasView;
import vista.UserMessageView;
import controlador.exceptions.CombinacionExistenteException;
import controlador.exceptions.MaquinaNoEncontradaException;
import controlador.exceptions.PremioException;
import controlador.exceptions.PremioNoEncontradoException;
import controlador.exceptions.TragamonedasCreacionException;
import controlador.exceptions.TragamonedasException;

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
	
	public boolean agregarFruta(String nombre, String url) {
		Fruta fruta = new Fruta(nombre, url);
		
		if (!frutasDisponibles.contains(fruta)) {
			frutasDisponibles.add(fruta);
			return true;
		}
		
		return false;
	}
	
	public List<Fruta> obtenerListaFrutas() {
		return new Vector<Fruta>(frutasDisponibles);
	}

	/**
	 *  
	 * @param precioJugada
	 * @param recaudacionInicial
	 * @param recaudacionMinima
	 * @param cantCasillas
	 */
	public TragamonedasView crearTragamonedas(float precioJugada, float recaudacionInicial, float recaudacionMinima, int cantCasillas, boolean cargaDefault) throws TragamonedasCreacionException {

		Tragamonedas tragamonedasNuevo = new Tragamonedas(tragamonedas.size() + 1, precioJugada, recaudacionInicial, recaudacionMinima, cantCasillas);
		tragamonedas.add(tragamonedasNuevo);
		for (int i = 0; i < cantCasillas; i++) {
			tragamonedasNuevo.agregarCasilla(i, frutasDisponibles);
		}
		return tragamonedasNuevo.getView();

	}
	
	public boolean eliminarTragamonedas(int nroTragamonedas) {
		
		try {
			Tragamonedas maquina = this.buscarTragamonedas(nroTragamonedas);
			tragamonedas.remove(maquina);
			return true;
		} catch (MaquinaNoEncontradaException e) {
			return false; 
		}
	}


	/**
	 * TODO Esto deberia devolver una vista
	 * @throws  
	 */
	public UserMessageView altaPremio(int nroMaquina, List<String> combinacionInput, float valorPremio) {

		List<Fruta> combinacionPremio = new Vector<Fruta>();

		//		Genera la combinacion del premio con la coleccion de Strings
		combinacionPremio = this.generarCombinacion(combinacionInput);
		try {
			Tragamonedas maquina = this.buscarTragamonedas(nroMaquina);

			if (maquina != null){
				if (valorPremio <= 0){
					return new UserMessageView("Importe de premio debe ser mayor a 0");
				}else {
					maquina.crearPremio(combinacionPremio, valorPremio);
				}
			}
		} catch (MaquinaNoEncontradaException e) {
			return new UserMessageView("Maquina No Encontrada");		
		} catch (PremioException e) {
			if (e instanceof CombinacionExistenteException)
				return new UserMessageView("La combinacion ingresada ya Existe");
			else
				return new UserMessageView(e.getMessage());
		}
		
		return new UserMessageView("Premio creado exitosamente");
	}

	/**
	 * TODO Esto deberia devolver una vista
	 */
	public JugadaView jugarConMaquina(int nroMaquina) throws TragamonedasException {

		try {
			Tragamonedas maquina = this.buscarTragamonedas(nroMaquina);

			JugadaView jugada = maquina.jugarConMaquina();
			
			return jugada;


		} catch (MaquinaNoEncontradaException e) {
			throw new TragamonedasException(e.getMessage());
		} catch (Exception e) {
			throw new TragamonedasException(e.getMessage());
		}	
	}
	
	/*
	 * Metodo para validar el estado actual de un tragamonedas
	 */
	public UserMessageView validarEstadoMaquina(int nroMaquina){
		UserMessageView mensaje=null;
		
		try {
			Tragamonedas maquina = this.buscarTragamonedas(nroMaquina);

			mensaje = maquina.validarEstadoMaquina();
			
		} catch (MaquinaNoEncontradaException e) {
			mensaje= new UserMessageView(e.getMessage());
		} catch (Exception e) {
			mensaje= new UserMessageView(e.getMessage());
		}	
		
		return mensaje;
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

	public UserMessageView bajaPremio(int nroMaquina, List<String> combinacionPremioAEliminar) {
		
		UserMessageView mensajeRetorno = new UserMessageView("Premio dado de baja correctamente");
		
		try {
			Tragamonedas maquina = this.buscarTragamonedas(nroMaquina);
			maquina.bajaPremio(this.generarCombinacion(combinacionPremioAEliminar));
			
		} catch (MaquinaNoEncontradaException e) {
			mensajeRetorno.setMensaje("No se encontro la maquina solicitada");
		} catch (PremioException e) {
			if (e instanceof PremioNoEncontradoException)
				mensajeRetorno.setMensaje("No existe el premio solicitado");
		}
		
		return mensajeRetorno;
	}

	public UserMessageView cargarCredito(Integer nbrMaquina, Float creditoAdicional) {

		try {
			Tragamonedas maquina = this.buscarTragamonedas(nbrMaquina);
			maquina.incrementarCredito(creditoAdicional);
			return new UserMessageView("Nuevo credito para maquina " + nbrMaquina + " es " + maquina.getCredito());
		} catch (MaquinaNoEncontradaException e) {
			return new UserMessageView("No se encontro la maquina solicitada");
		}
	}
	
	/**
	 * Convertir lista 
	 * 
	 * @param frutas
	 * @return
	 */
	private List<Fruta> generarCombinacion(List<String> frutas) {
		
		List<Fruta> listaRetorno = new ArrayList<Fruta>();
		for (int i=0; i<frutas.size(); i++){
			Fruta comb = buscarFruta(frutas.get(i));
			listaRetorno.add(comb);
		}
		
		return listaRetorno;
	}

	private Fruta buscarFruta(String nombreFruta) {
		for (Fruta fruta : frutasDisponibles) {
			
			if (fruta.getNombre().equalsIgnoreCase(nombreFruta))
				return fruta;
			else
				continue;
		}
		return null;
	}
	
	public UserMessageView cobrarCredito(Integer nbrMaquina) {

		try {
			Tragamonedas maquina = this.buscarTragamonedas(nbrMaquina);
			String texto ="El 'Credito' cobrado de la maquina " + nbrMaquina + " es " + maquina.getCredito();
			maquina.cobrarCredito();
			return new UserMessageView(texto);
		} catch (MaquinaNoEncontradaException e) {
			return new UserMessageView("No se encontro la maquina solicitada");
		}
	}
	
	public Float obtenerCredito(Integer nbrMaquina) throws MaquinaNoEncontradaException{

		Tragamonedas maquina = this.buscarTragamonedas(nbrMaquina);
		if (maquina!=null){	
		   return maquina.getCredito();
		}
		
		throw new MaquinaNoEncontradaException("No se encontro el tragamonedas solicitado");
	} 
	
	public Float obtenerPrecioJugada(Integer nbrMaquina) throws MaquinaNoEncontradaException{

		Tragamonedas maquina = this.buscarTragamonedas(nbrMaquina);
		if (maquina!=null){	
		   return maquina.getPrecioJugada();
		}
		
		throw new MaquinaNoEncontradaException("No se encontro el tragamonedas solicitado");
		
	}
	
	public boolean iniciarCasillas(int nroTragamoneda, List<List<Fruta>> casillas ) {
		
		try {
			Tragamonedas maquina = buscarTragamonedas(nroTragamoneda);
			for (List<Fruta> casilla : casillas) {
				maquina.cargarCasilla(casillas.indexOf(casilla), casilla);
			}
			return true;
		} catch (MaquinaNoEncontradaException e) {
			return false;
		}
	}
	public List<String> consultarPremios(Integer nbrMaquina) {
        List<Premio> Premios;
        List<Fruta>  Combinacion= new Vector<Fruta>();
        List<String> listadoPremios=new Vector<String>();
        String texto = new String();
        
		try {
			Tragamonedas maquina = this.buscarTragamonedas(nbrMaquina);
			Premios=maquina.getPremios();
			for(int i=0;i<Premios.size();i++){
				texto="";
				Combinacion.addAll(Premios.get(i).getCombinacion());
				for(int j=0;j<Combinacion.size();j++){
					texto+= Combinacion.get(j).getNombre() + "    ";
				}
				Combinacion.clear();
//				String valorPremio = String.valueOf(Premios.get(i).getValorPremio());
//				texto += valorPremio;
				listadoPremios.add(texto);
			}
			
			return listadoPremios;
		} catch (MaquinaNoEncontradaException e) {
			return null;
		}
	}

	
	public List<String> obtenerURLFrutas(Integer nbrMaquina, JugadaView jugada) throws MaquinaNoEncontradaException{
		List<String> URLImagenes = new Vector<String>();
		Tragamonedas maquina = this.buscarTragamonedas(nbrMaquina);
		List<Fruta> combinacion= maquina.getCombinacionJugada(jugada);
		int i;
		
		for (i=0; i<combinacion.size();i++){
			String urlFruta = combinacion.get(i).getUrlImagen();
			URLImagenes.add(urlFruta);
		}
			
	
		return URLImagenes;
		
	}
	

	
}
