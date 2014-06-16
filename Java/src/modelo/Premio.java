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
	
	public boolean tienePremio(List<Fruta> combinacionAEvaluar) {
		boolean validacion=true;

		for (int i = 0; i < combinacionAEvaluar.size(); i++){

			Fruta frutaEvaluar = combinacionAEvaluar.get(i);
			Fruta frutaCombinacion = this.combinacion.get(i);

			if (frutaCombinacion.esFrutaIgual(frutaEvaluar))
				continue;
			else
				return false;

		}

		return validacion;
	}
	
}
