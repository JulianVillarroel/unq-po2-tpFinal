package ar.edu.unq.po2.tpFinal.Filtros;

import java.time.LocalDate;
import java.util.ArrayList;

import ar.edu.unq.po2.tpFinal.Muestra;

public class BusquedaFechaCreacion implements BusquedaDeMuestra {

	private LocalDate fechaFiltro;

	public BusquedaFechaCreacion(LocalDate fechaFiltro) {
		
		this.fechaFiltro = fechaFiltro;
		
	}
	
	public ArrayList<Muestra> buscar(ArrayList<Muestra> muestras) {
		ArrayList<Muestra> resultado = new ArrayList<Muestra>();
		for(Muestra muestra:muestras) {
			if(muestra.getFechaDeCreacion().equals(this.fechaFiltro)) {
				resultado.add(muestra);
			}
		}		
		return resultado;
	}

}
