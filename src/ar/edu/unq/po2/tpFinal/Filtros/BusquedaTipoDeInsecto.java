package ar.edu.unq.po2.tpFinal.Filtros;

import ar.edu.unq.po2.tpFinal.Muestra;
import ar.edu.unq.po2.tpFinal.Enumerativos.Calificacion;

public class BusquedaTipoDeInsecto extends BusquedaDeMuestra {

	private Calificacion tipoDeInsecto;

	public BusquedaTipoDeInsecto(Calificacion tipoDeInsecto) {
		
		super();
		this.tipoDeInsecto = tipoDeInsecto;		
		
	}

	@Override
	protected boolean condicionBusqueda(Muestra muestra) {
		return muestra.getEspecieDeVinchuca().equals(tipoDeInsecto);
	}
	
}
