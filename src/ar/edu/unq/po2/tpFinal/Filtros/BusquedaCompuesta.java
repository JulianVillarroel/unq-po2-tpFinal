package ar.edu.unq.po2.tpFinal.Filtros;

import java.util.ArrayList;

import ar.edu.unq.po2.tpFinal.Muestra;

public abstract class BusquedaCompuesta implements BusquedaDeMuestra {

	protected ArrayList<BusquedaDeMuestra> listaBusquedas;

	public BusquedaCompuesta() {
		
		this.listaBusquedas = new ArrayList<BusquedaDeMuestra>();
		
	}
	
	public ArrayList<Muestra> buscar(ArrayList<Muestra> muestras){
		ArrayList<Muestra> resultado = muestras;
		for(BusquedaDeMuestra busqueda: this.listaBusquedas) {
			resultado = this.formatoBusqueda(resultado,busqueda.buscar(muestras));
		}		
		return resultado;
	}
	
	public void addBusqueda(BusquedaDeMuestra busqueda) {

		this.listaBusquedas.add(busqueda);
		
	}
	
	public void removeBusqueda(BusquedaDeMuestra busqueda) {
		
		this.listaBusquedas.remove(busqueda);
		
	}
	
	private ArrayList<Muestra> formatoBusqueda(ArrayList<Muestra> listaUno, ArrayList<Muestra> listaDos) {
		ArrayList<Muestra> resultado = new ArrayList<Muestra>();
		for(Muestra muestra:listaUno) {
			if(this.condicionFormato(listaDos,muestra)) {
				resultado.add(muestra);
			}
		}		
		return resultado;
	}

	protected abstract boolean condicionFormato(ArrayList<Muestra> lista, Muestra elemento);	
	
}
