package ar.edu.unq.po2.tpFinal;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.tpFinal.Enumerativos.Calificacion;

class OpinionTestCase {

	Opinion opinion;
	Calificacion calificacionDeOpinion;

	@BeforeEach
	void setUp() throws Exception {
		calificacionDeOpinion = Calificacion.CHINCHE_FOLIADA;
		opinion = new Opinion(calificacionDeOpinion);

	}

	@Test
	void testOpinionCalificacionDeChincheFoliada() {
		assertEquals(opinion.getCalificacion(), calificacionDeOpinion);
	}

	@Test
	void testOpinionCalificacionImagenPocoClara() {
		calificacionDeOpinion = Calificacion.IMAGEN_POCO_CLARA;
		opinion.setCalificacion(calificacionDeOpinion);
		assertEquals(opinion.getCalificacion(), calificacionDeOpinion);
	}

	@Test
	void testFechaEnQueSeEmiteLaOpinion() {
		LocalDate fechaEmision = LocalDate.now();
		assertEquals(opinion.getFechaDeEmision(), fechaEmision);
	}

}
