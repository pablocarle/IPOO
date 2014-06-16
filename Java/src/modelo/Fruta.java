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
	
	@Override
	public boolean equals(Object obj) {
//		Definir que 2 frutas son iguales cuando nombre y urlImagen son iguales
		if (obj instanceof Fruta) {
			Fruta fruta2 = (Fruta) obj;
			
			return (fruta2.getNombre().equalsIgnoreCase(this.getNombre()));
			
		}
		
//		Si no esta comparando con otra fruta, utilizar el comportamiento default de Object
		return super.equals(obj);
	}
	
	@Override
	public String toString() {
		return nombre;
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
	
	public boolean esFrutaIgual(Fruta frutaEvaluar){
		
		return this.nombre==frutaEvaluar.getNombre();
		
	}
	
}
