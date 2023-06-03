package ar.edu.unq.po2.tpFinal;

import java.time.LocalDate;

import ar.edu.unq.po2.tpFinal.Enumerativos.Calificacion;

public class Opinion {

	private Calificacion calificacion;
	private LocalDate fechaDeEmision;

	public Calificacion getCalificacion() {
		return calificacion;
	}

	public LocalDate getFechaDeEmision() {
		return fechaDeEmision;
	}

	public Opinion(Calificacion calificacion) {
		fechaDeEmision = LocalDate.now();
		this.calificacion = calificacion;

	}

	public void setCalificacion(Calificacion calificacionDeOpinion) {
		this.calificacion = calificacionDeOpinion;

	}

}
