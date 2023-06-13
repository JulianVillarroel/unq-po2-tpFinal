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

	@Override
	protected ArrayList<Muestra> casoBase(ArrayList<Muestra> muestras) {
		return muestras;
	}

	@Override
	protected ArrayList<Muestra> formatoBusqueda(ArrayList<Muestra> listaUno, ArrayList<Muestra> listaDos) {
		ArrayList<Muestra> resultado = new ArrayList<Muestra>();
		for(Muestra muestra:listaDos) {
			if(this.condicionFormato(listaUno,muestra)) {
				resultado.add(muestra);
			}
		}		
		return resultado;
	}

}
