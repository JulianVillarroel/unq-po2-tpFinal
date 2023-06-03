package ar.edu.unq.po2.tpFinal.Filtros;

import java.time.LocalDate;

import ar.edu.unq.po2.tpFinal.Muestra;

public class BusquedaFechaCreacion extends BusquedaDeMuestra {

	private LocalDate fechaFiltro;

	public BusquedaFechaCreacion(LocalDate fechaFiltro) {
		
		super();
		this.fechaFiltro = fechaFiltro;
		
	}

	@Override
	protected boolean condicionBusqueda(Muestra muestra) {
		return muestra.getFechaDeCreacion().equals(fechaFiltro);
	}

}
