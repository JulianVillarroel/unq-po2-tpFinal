package ar.edu.unq.po2.tpFinal.Filtros;

import java.util.ArrayList;

import ar.edu.unq.po2.tpFinal.Muestra;

public abstract class BusquedaCompuesta extends BusquedaDeMuestra {

	protected ArrayList<BusquedaDeMuestra> listaBusquedas;

	public BusquedaCompuesta() {
		
		this.listaBusquedas = new ArrayList<BusquedaDeMuestra>();
		
	}
	
	@Override
	public ArrayList<Muestra> buscar(ArrayList<Muestra> muestras){
		ArrayList<Muestra> resultado = this.casoBase(muestras);
		for(BusquedaDeMuestra busqueda: this.listaBusquedas) {
			resultado = this.formatoBusqueda(resultado,busqueda.buscar(muestras));
		}		
		return resultado;
	}
	
	protected abstract ArrayList<Muestra> casoBase(ArrayList<Muestra> muestras);

	public void addBusqueda(BusquedaDeMuestra busqueda) {

		this.listaBusquedas.add(busqueda);
		
	}
	
	public void removeBusqueda(BusquedaDeMuestra busqueda) {
		
		this.listaBusquedas.remove(busqueda);
		
	}
	
	protected abstract ArrayList<Muestra> formatoBusqueda(ArrayList<Muestra> listaUno, ArrayList<Muestra> listaDos);

	protected abstract boolean condicionFormato(ArrayList<Muestra> lista, Muestra elemento);
	
}
