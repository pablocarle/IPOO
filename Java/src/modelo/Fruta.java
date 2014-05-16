package modelo;

/**
 * Las disponibles estan creadas en un array en la inicializacion del controlador
 * 
 * @author Grupo IPOO
 *
 */
public class Fruta {

	private String nombre;
	private String urlImagen;
	
	
	/**
	 * @param nombre
	 * @param urlImagen
	 */
	public Fruta(String nombre, String urlImagen) {
		super();
		this.nombre = nombre;
		this.urlImagen = urlImagen;
	}


	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}


	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	/**
	 * @return the urlImagen
	 */
	public String getUrlImagen() {
		return urlImagen;
	}


	/**
	 * @param urlImagen the urlImagen to set
	 */
	public void setUrlImagen(String urlImagen) {
		this.urlImagen = urlImagen;
	}
}
