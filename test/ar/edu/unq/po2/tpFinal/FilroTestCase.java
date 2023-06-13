package ar.edu.unq.po2.tpFinal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.tpFinal.Enumerativos.Calificacion;
import ar.edu.unq.po2.tpFinal.EstadoDeMuestra.EstadoDeMuestra;
import ar.edu.unq.po2.tpFinal.Filtros.*;

class FilroTestCase {

	BusquedaDeMuestra busquedaInsectoInfestans;
	BusquedaDeMuestra busquedaInsectoGuasayana;
	BusquedaDeMuestra busquedaInsectoSordida;
	BusquedaDeMuestra busquedaFecha;
	BusquedaDeMuestra busquedaVotacion;
	BusquedaDeMuestra busquedaNivelVerificacionVotada;
	BusquedaDeMuestra busquedaNivelVerificacionVerificada;
	
	BusquedaCompuesta busquedaCompuestaAnd;
	BusquedaCompuesta busquedaCompuestaAnd2;
	BusquedaCompuesta busquedaCompuestaOR;
	BusquedaCompuesta busquedaCompuestaOR2;
	
	ArrayList<Muestra> muestras;
	
	Muestra muestraInfestans;
	Muestra muestraGuasayana;
	Muestra muestraSordida;
	
	Opinion opinion;
	
	EstadoDeMuestra estadoVotada;
	EstadoDeMuestra estadoVerificada;

	@BeforeEach
	void setUp() throws Exception {
		busquedaInsectoInfestans = new BusquedaTipoDeInsecto(Calificacion.INFESTANS);
		busquedaInsectoGuasayana = new BusquedaTipoDeInsecto(Calificacion.GUASAYANA);
		busquedaInsectoSordida = new BusquedaTipoDeInsecto(Calificacion.SORDIDA);
		busquedaFecha = new BusquedaFechaCreacion(LocalDate.of(2022, 6, 17));
		busquedaVotacion = new BusquedaFechaUltimaVotacion(LocalDate.of(2022, 5, 15));
		busquedaNivelVerificacionVotada = new BusquedaNivelDeVerificacion("votada");
		busquedaNivelVerificacionVerificada = new BusquedaNivelDeVerificacion("verificada");

		muestraInfestans = mock(Muestra.class);
		muestraGuasayana = mock(Muestra.class);
		muestraSordida = mock(Muestra.class);
		estadoVotada = mock(EstadoDeMuestra.class);
		estadoVerificada = mock(EstadoDeMuestra.class);

		when(muestraInfestans.getEspecieDeVinchuca()).thenReturn(Calificacion.INFESTANS);
		when(muestraGuasayana.getEspecieDeVinchuca()).thenReturn(Calificacion.GUASAYANA);
		when(muestraSordida.getEspecieDeVinchuca()).thenReturn(Calificacion.SORDIDA);

		when(muestraInfestans.getEstadoMuestra()).thenReturn(estadoVerificada);
		when(muestraGuasayana.getEstadoMuestra()).thenReturn(estadoVotada);
		when(muestraSordida.getEstadoMuestra()).thenReturn(estadoVerificada);

		when(muestraInfestans.getFechaDeCreacion()).thenReturn(LocalDate.of(2022, 6, 17));
		when(muestraGuasayana.getFechaDeCreacion()).thenReturn(LocalDate.of(2022, 6, 17));
		when(muestraSordida.getFechaDeCreacion()).thenReturn(LocalDate.of(2022, 6, 12));

		when(muestraInfestans.getFechaUltimaVotacion()).thenReturn(LocalDate.of(2022, 2, 12));
		when(muestraGuasayana.getFechaUltimaVotacion()).thenReturn(LocalDate.of(2022, 5, 15));
		when(muestraSordida.getFechaUltimaVotacion()).thenReturn(LocalDate.of(2022, 5, 15));

		when(muestraInfestans.getFechaUltimaVotacion()).thenReturn(LocalDate.of(2022, 2, 12));
		when(muestraGuasayana.getFechaUltimaVotacion()).thenReturn(LocalDate.of(2022, 5, 15));
		when(muestraSordida.getFechaUltimaVotacion()).thenReturn(LocalDate.of(2022, 5, 15));
		
		when(muestraInfestans.getNivelDeVerificacion()).thenReturn("verificada");
		when(muestraGuasayana.getNivelDeVerificacion()).thenReturn("votada");
		when(muestraSordida.getNivelDeVerificacion()).thenReturn("verificada");
		
		muestras = new ArrayList<Muestra>(Arrays.asList(muestraInfestans, muestraGuasayana, muestraSordida));

	}

	@Test
	void testBusquedaPorTipoDeInsectoConVinchucaInfestans() {
		ArrayList<Muestra> listaFiltrada = busquedaInsectoInfestans.buscar(muestras);

		assertTrue(listaFiltrada.contains(muestraInfestans));
		assertFalse(listaFiltrada.contains(muestraGuasayana));
		assertFalse(listaFiltrada.contains(muestraSordida));
	}

	@Test
	void testBusquedaPorFechaCreacion() {
		ArrayList<Muestra> listaFiltrada = busquedaFecha.buscar(muestras);

		assertTrue(listaFiltrada.contains(muestraInfestans));
		assertTrue(listaFiltrada.contains(muestraGuasayana));
		assertFalse(listaFiltrada.contains(muestraSordida));
	}

	@Test
	void testBusquedaPorFechaDeUltimaVotacion() {
		ArrayList<Muestra> listaFiltrada = busquedaVotacion.buscar(muestras);

		assertFalse(listaFiltrada.contains(muestraInfestans));
		assertTrue(listaFiltrada.contains(muestraGuasayana));
		assertTrue(listaFiltrada.contains(muestraSordida));
	}

	@Test
	void testBusquedaPorNivelDeVerificacionConEstadoVerificadoMuestrasInfestansYSordida() {
		ArrayList<Muestra> listaFiltrada = busquedaNivelVerificacionVerificada.buscar(muestras);

		assertTrue(listaFiltrada.contains(muestraInfestans)); 
		assertTrue(listaFiltrada.contains(muestraSordida));
		assertFalse(listaFiltrada.contains(muestraGuasayana));
	}

	@Test
	void testBusquedaPorNivelDeVerificacionConEstadoVotadaMuestraGuasayana() {
		ArrayList<Muestra> listaFiltrada = busquedaNivelVerificacionVotada.buscar(muestras);

		assertTrue(listaFiltrada.contains(muestraGuasayana));
		assertFalse(listaFiltrada.contains(muestraInfestans));
		assertFalse(listaFiltrada.contains(muestraSordida));
	}

	@Test
	void testBusquedaCompuestaANDConBusquedaTipoDeInsectoYBusquedaFechaCreacion() {
		busquedaCompuestaAnd = new BusquedaCompuestaAnd();
		busquedaCompuestaAnd.addBusqueda(busquedaFecha);
		busquedaCompuestaAnd.addBusqueda(busquedaInsectoInfestans);
		/*
		 * que me busque las muestras que son tipo insecto INFESTANS Y fecha
		 * LocalDate.of(2022, 6, 17) osea la muestraInfestans
		 */

		ArrayList<Muestra> listaFiltrada = busquedaCompuestaAnd.buscar(muestras);

		assertTrue(listaFiltrada.contains(muestraInfestans));
		assertFalse(listaFiltrada.contains(muestraGuasayana));
		assertFalse(listaFiltrada.contains(muestraSordida));
	}

	@Test
	void testBusquedaCompuestaANDConBusquedaTipoDeInsectoBusquedaFechaCreacionYBusquedaNivelVerificacionVerificada () {
		busquedaCompuestaAnd = new BusquedaCompuestaAnd();
		busquedaCompuestaAnd.addBusqueda(busquedaFecha);
		busquedaCompuestaAnd.addBusqueda(busquedaInsectoInfestans);
		busquedaCompuestaAnd.addBusqueda(busquedaNivelVerificacionVerificada);
		/*
		 * que me busque las muestras que son tipo insecto INFESTANS Y fecha
		 * LocalDate.of(2022, 6, 17) osea la muestraInfestans
		 */

		ArrayList<Muestra> listaFiltrada = busquedaCompuestaAnd.buscar(muestras);

		assertTrue(listaFiltrada.contains(muestraInfestans));
		assertFalse(listaFiltrada.contains(muestraGuasayana));
		assertFalse(listaFiltrada.contains(muestraSordida));
	}

	@Test
	void testBusquedaCompuestaANDConBusquedaTipoDeInsectoYOtraBusquedaCompuestaAND() {
		busquedaCompuestaAnd2 = new BusquedaCompuestaAnd();
		busquedaCompuestaAnd2.addBusqueda(busquedaFecha);
		busquedaCompuestaAnd2.addBusqueda(busquedaVotacion);
		
		busquedaCompuestaAnd = new BusquedaCompuestaAnd();
		busquedaCompuestaAnd.addBusqueda(busquedaInsectoGuasayana);
		busquedaCompuestaAnd.addBusqueda(busquedaCompuestaAnd2);
		// guasayana y LocalDate.of(2022, 6, 17) fecha y LocalDate.of(2022, 5, 15)
		// votacion

		ArrayList<Muestra> listaFiltrada = busquedaCompuestaAnd.buscar(muestras);

		assertTrue(listaFiltrada.contains(muestraGuasayana));
		assertFalse(listaFiltrada.contains(muestraInfestans));
		assertFalse(listaFiltrada.contains(muestraSordida));
	}

	@Test
	void testBusquedaCompuestaORConBusquedaTipoDeInsectoOYBusquedaNivelVerificacionVotada() {
		busquedaCompuestaOR = new BusquedaCompuestaOr();
		busquedaCompuestaOR.addBusqueda(busquedaNivelVerificacionVotada);
		busquedaCompuestaOR.addBusqueda(busquedaInsectoInfestans);
		// muestras de nivel "votada" OR infestans
		
		ArrayList<Muestra> listaFiltrada = busquedaCompuestaOR.buscar(muestras);
		
		assertTrue(listaFiltrada.contains(muestraGuasayana));
		assertTrue(listaFiltrada.contains(muestraInfestans));
		assertFalse(listaFiltrada.contains(muestraSordida));
	}

	@Test
	void testBusquedaCompuestaORConBusquedaNivelVerificacionVerificadaYOtraBusquedaCompuestaOR() {
		busquedaCompuestaOR2 = new BusquedaCompuestaOr();
		busquedaCompuestaOR2.addBusqueda(busquedaVotacion);
		busquedaCompuestaOR2.addBusqueda(busquedaInsectoGuasayana);
		
		busquedaCompuestaOR = new BusquedaCompuestaOr();
		busquedaCompuestaOR.addBusqueda(busquedaNivelVerificacionVerificada);
		busquedaCompuestaOR.addBusqueda(busquedaCompuestaOR2);

		ArrayList<Muestra> listaFiltrada = busquedaCompuestaOR.buscar(muestras);

		assertTrue(listaFiltrada.contains(muestraGuasayana));
		assertTrue(listaFiltrada.contains(muestraInfestans));
		assertTrue(listaFiltrada.contains(muestraSordida));
	}

	@Test
	void testBusquedaCompuestaANDCombinadoConBusquedaCompuestaOR() {
		busquedaCompuestaOR = new BusquedaCompuestaOr();
		busquedaCompuestaOR.addBusqueda(busquedaNivelVerificacionVerificada);
		busquedaCompuestaOR.addBusqueda(busquedaVotacion);
		
		busquedaCompuestaAnd = new BusquedaCompuestaAnd();
		busquedaCompuestaAnd.addBusqueda(busquedaInsectoSordida);
		busquedaCompuestaAnd.addBusqueda(busquedaCompuestaOR);

		ArrayList<Muestra> listaFiltrada = busquedaCompuestaAnd.buscar(muestras);

		assertTrue(listaFiltrada.contains(muestraSordida));
		assertFalse(listaFiltrada.contains(muestraGuasayana));
		assertFalse(listaFiltrada.contains(muestraInfestans));
	}


}
