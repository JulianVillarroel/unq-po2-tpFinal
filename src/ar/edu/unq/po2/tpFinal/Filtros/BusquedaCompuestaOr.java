package ar.edu.unq.po2.tpFinal.Filtros;

import java.util.ArrayList;

import ar.edu.unq.po2.tpFinal.Muestra;

public class BusquedaCompuestaOr extends BusquedaCompuesta {

	public BusquedaCompuestaOr() {
		super();
	}

	@Override
	protected boolean condicionFormato(ArrayList<Muestra> lista, Muestra elemento) {
		return !lista.contains(elemento);
	}

}
