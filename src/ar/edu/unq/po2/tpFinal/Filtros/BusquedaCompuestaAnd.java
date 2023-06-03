package ar.edu.unq.po2.tpFinal.Filtros;

import java.util.ArrayList;

import ar.edu.unq.po2.tpFinal.Muestra;

public class BusquedaCompuestaAnd extends BusquedaCompuesta {

	public BusquedaCompuestaAnd() {
		super();
	}

	@Override
	protected boolean condicionFormato(ArrayList<Muestra> lista, Muestra elemento) {
		return lista.contains(elemento);
	}

}
