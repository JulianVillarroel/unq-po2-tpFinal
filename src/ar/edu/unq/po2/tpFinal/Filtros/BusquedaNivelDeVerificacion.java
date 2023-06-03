package ar.edu.unq.po2.tpFinal.Filtros;

import ar.edu.unq.po2.tpFinal.Muestra;

public class BusquedaNivelDeVerificacion extends BusquedaDeMuestra {

	private String nivelVerificacion;

	public BusquedaNivelDeVerificacion(String nivelVerificacion) {
		
		super();
		this.nivelVerificacion = nivelVerificacion;
		
	}

	@Override
	protected boolean condicionBusqueda(Muestra muestra) {
		return muestra.getNivelDeVerificacion().equals(nivelVerificacion);
	}
	
}
