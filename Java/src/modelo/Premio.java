package modelo;

import java.util.List;

public class Premio {
	
	private float valorPremio;
	private List<Fruta> combinacion;

	public Premio(List<Fruta> combinacion, float valorPremio) {
		this.valorPremio = valorPremio;
		this.combinacion = combinacion;
	}

	/**
	 * @return the valorPremio
	 */
	public float getValorPremio() {
		return valorPremio;
	}

	/**
	 * @return the combinacion
	 */
	public List<Fruta> getCombinacion() {
		return combinacion;
	}
}
