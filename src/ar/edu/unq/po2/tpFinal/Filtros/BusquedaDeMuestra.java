package ar.edu.unq.po2.tpFinal.Filtros;

import java.util.ArrayList;

import ar.edu.unq.po2.tpFinal.Muestra;

public abstract class BusquedaDeMuestra {

	public BusquedaDeMuestra() {
	}

	public ArrayList<Muestra> buscar(ArrayList<Muestra> muestras) {
		ArrayList<Muestra> resultado = new ArrayList<Muestra>();
		for (Muestra muestra : muestras) {
			if (this.condicionBusqueda(muestra)) {
				resultado.add(muestra);
			}
		}
		return resultado;
	}

	protected boolean condicionBusqueda(Muestra muestra) {

		return true;

	}

}
