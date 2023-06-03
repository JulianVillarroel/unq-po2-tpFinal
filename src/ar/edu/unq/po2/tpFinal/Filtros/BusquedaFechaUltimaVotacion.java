package ar.edu.unq.po2.tpFinal.Filtros;

import java.time.LocalDate;

import ar.edu.unq.po2.tpFinal.Muestra;

public class BusquedaFechaUltimaVotacion extends BusquedaDeMuestra {

	private LocalDate ultimaFecha;

	public BusquedaFechaUltimaVotacion(LocalDate ultimaFecha) {
		
		super();
		this.ultimaFecha = ultimaFecha;
		
	}

	@Override
	protected boolean condicionBusqueda(Muestra muestra) {
		return muestra.getFechaUltimaVotacion().equals(ultimaFecha);
	}

}
