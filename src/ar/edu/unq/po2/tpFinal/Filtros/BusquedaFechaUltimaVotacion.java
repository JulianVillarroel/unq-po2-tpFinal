package ar.edu.unq.po2.tpFinal.Filtros;

import java.time.LocalDate;
import java.util.ArrayList;

import ar.edu.unq.po2.tpFinal.Muestra;

public class BusquedaFechaUltimaVotacion implements BusquedaDeMuestra {

	private LocalDate ultimaFecha;

	public BusquedaFechaUltimaVotacion(LocalDate ultimaFecha) {
		
		this.ultimaFecha = ultimaFecha;
		
	}
	
	public ArrayList<Muestra> buscar(ArrayList<Muestra> muestras) {
		ArrayList<Muestra> resultado = new ArrayList<Muestra>();
		for(Muestra muestra:muestras) {
			if(muestra.getFechaUltimaVotacion().equals(this.ultimaFecha)) {
				resultado.add(muestra);
			}
		}		
		return resultado;
	}

}
