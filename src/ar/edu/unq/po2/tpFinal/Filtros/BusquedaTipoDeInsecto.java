package ar.edu.unq.po2.tpFinal.Filtros;

import java.util.ArrayList;

import ar.edu.unq.po2.tpFinal.Muestra;
import ar.edu.unq.po2.tpFinal.Enumerativos.Calificacion;

public class BusquedaTipoDeInsecto implements BusquedaDeMuestra {

	private Calificacion tipoDeInsecto;

	public BusquedaTipoDeInsecto(Calificacion tipoDeInsecto) {
		
		this.tipoDeInsecto = tipoDeInsecto;		
		
	}
	
	public ArrayList<Muestra> buscar(ArrayList<Muestra> muestras) {
		ArrayList<Muestra> resultado = new ArrayList<Muestra>();
		for(Muestra muestra:muestras) {
			if(muestra.getResultadoActual().equals(this.tipoDeInsecto)) {
				resultado.add(muestra);
			}
		}		
		return resultado;
	}

}
