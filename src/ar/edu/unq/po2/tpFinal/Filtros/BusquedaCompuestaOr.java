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

	@Override
	protected ArrayList<Muestra> casoBase(ArrayList<Muestra> muestras) {
		return new ArrayList<Muestra>();
	}

	@Override
	protected ArrayList<Muestra> formatoBusqueda(ArrayList<Muestra> listaUno, ArrayList<Muestra> listaDos) {
		ArrayList<Muestra> resultado = listaUno;
		for(Muestra muestra:listaDos) {
			if(this.condicionFormato(listaUno,muestra)) {
				resultado.add(muestra);
			}
		}		
		return resultado;
	}

}
