package ar.edu.unq.po2.tpFinal.Filtros;

import java.util.ArrayList;

import ar.edu.unq.po2.tpFinal.Muestra;

public class BusquedaNivelDeVerificacion implements BusquedaDeMuestra {

	private String nivelVerificacion;

	public BusquedaNivelDeVerificacion(String nivelVerificacion) {
		
		this.nivelVerificacion = nivelVerificacion;
		
	}
	
	public ArrayList<Muestra> buscar(ArrayList<Muestra> muestras) {
		ArrayList<Muestra> resultado = new ArrayList<Muestra>();
		for(Muestra muestra:muestras) {
			if(muestra.getNivelDeVerificacion().equals(this.nivelVerificacion)) {
				resultado.add(muestra);
			}
		}		
		return resultado;
	}

}
