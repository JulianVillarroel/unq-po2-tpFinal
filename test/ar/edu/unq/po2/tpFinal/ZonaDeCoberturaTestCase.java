package ar.edu.unq.po2.tpFinal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.tpFinal.Observer.IOrganizacionObserver;
import ar.edu.unq.po2.tpFinal.Observer.Organizacion;
import ar.edu.unq.po2.tpFinal.Ubicaciones.Ubicacion;
import ar.edu.unq.po2.tpFinal.Ubicaciones.ZonaDeCobertura;

class ZonaDeCoberturaTestCase {

	ZonaDeCobertura zonaDeCobertura, otraZonaDeCobertura, otraZonaDeCoberturaMas;
	Organizacion organizacion;
	IOrganizacionObserver suscriptor1;
	IOrganizacionObserver suscriptor2;
	Ubicacion ubicacion1, ubicacion2, ubicacion3, ubicacion4, ubicacion5;
	Muestra muestra, otraMuestra;

	@BeforeEach
	void setUp() throws Exception {
		muestra = mock(Muestra.class);
		otraMuestra = mock(Muestra.class);
		when(muestra.getNivelDeVerificacion()).thenReturn("verificada");
		ubicacion1 = mock(Ubicacion.class);
		ubicacion2 = mock(Ubicacion.class);

		ubicacion3 = mock(Ubicacion.class);
		organizacion = mock(Organizacion.class);

		suscriptor1 = mock(Organizacion.class);
		suscriptor2 = mock(Organizacion.class);

		zonaDeCobertura = new ZonaDeCobertura("Berazategui", ubicacion1, 500);
		otraZonaDeCobertura = new ZonaDeCobertura("Wilde", ubicacion1, 400);

		when(muestra.getUbicacion()).thenReturn(ubicacion1);

	}

	@Test
	void testSuscribirObserverAZonaDeCobertura() {
		zonaDeCobertura.registrarseAZonaDeCobertura(this.suscriptor1);
		assertEquals(1, zonaDeCobertura.getObservers().size());
	}

	@Test
	void testDesuscribirObserverAZonaDeCobertura() {
		zonaDeCobertura.registrarseAZonaDeCobertura(this.suscriptor1);
		zonaDeCobertura.dejarZonaDeCobertura(this.suscriptor1);
		assertEquals(0, zonaDeCobertura.getObservers().size());
	}

	@Test
	void testNotificarSuscriptoresNuevaMuestra() {
		zonaDeCobertura.registrarseAZonaDeCobertura(suscriptor1);
		zonaDeCobertura.registrarseAZonaDeCobertura(suscriptor2);

		zonaDeCobertura.agregarMuestra(muestra);

		verify(suscriptor1, times(1)).nuevaMuestra(zonaDeCobertura, muestra);
		verify(suscriptor2, times(1)).nuevaMuestra(zonaDeCobertura, muestra);
	}

	@Test
	void testNotificarSuscriptoresNuevaVerificacion() {
		zonaDeCobertura.registrarseAZonaDeCobertura(suscriptor1);
		zonaDeCobertura.registrarseAZonaDeCobertura(suscriptor2);

		zonaDeCobertura.agregarMuestra(muestra);

		zonaDeCobertura.notificarNuevaVerificacion(muestra);

		verify(suscriptor1, times(1)).nuevaVerificacion(zonaDeCobertura, muestra);
		verify(suscriptor2, times(1)).nuevaVerificacion(zonaDeCobertura, muestra);
	}

	@Test
	void testUnaZonaSeNoSolapaConOtraZona() {
		when(ubicacion1.getLatitud()).thenReturn(50d);
		when(ubicacion1.getLongitud()).thenReturn(40d);
		when(ubicacion2.getLatitud()).thenReturn(40d);
		when(ubicacion2.getLongitud()).thenReturn(50d);
		when(ubicacion1.distanciaHasta(ubicacion2)).thenReturn(1360.56d);

		zonaDeCobertura = new ZonaDeCobertura("Berazategui", ubicacion1, 500);
		otraZonaDeCobertura = new ZonaDeCobertura("Ezpeleta", ubicacion2, 200);

		assertFalse(zonaDeCobertura.seSolapaCon(otraZonaDeCobertura));
	}

	@Test
	void testUnaZonaSeSolapaConOtraZona() {
		when(ubicacion1.getLatitud()).thenReturn(300d);
		when(ubicacion1.getLongitud()).thenReturn(300d);
		when(ubicacion2.getLatitud()).thenReturn(299d);
		when(ubicacion2.getLongitud()).thenReturn(299d);
		when(ubicacion1.distanciaHasta(ubicacion2)).thenReturn(124.06d);

		zonaDeCobertura = new ZonaDeCobertura("Berazategui", ubicacion1, 500);
		otraZonaDeCobertura = new ZonaDeCobertura("Ezpeleta", ubicacion2, 600);

		assertTrue(zonaDeCobertura.seSolapaCon(otraZonaDeCobertura));
	}

	@Test
	void testAgregarZonasQueSeSolapan() {

		when(ubicacion1.getLatitud()).thenReturn(300d);
		when(ubicacion1.getLongitud()).thenReturn(300d);
		when(ubicacion2.getLatitud()).thenReturn(299d);
		when(ubicacion2.getLongitud()).thenReturn(299d);
		when(ubicacion3.getLatitud()).thenReturn(1d);
		when(ubicacion3.getLongitud()).thenReturn(1d);
		when(ubicacion1.distanciaHasta(ubicacion2)).thenReturn(124.06d);
		when(ubicacion1.distanciaHasta(ubicacion3)).thenReturn(5855.18d);

		zonaDeCobertura = new ZonaDeCobertura("Berazategui", ubicacion1, 500);
		otraZonaDeCobertura = new ZonaDeCobertura("Ezpeleta", ubicacion2, 600);
		otraZonaDeCoberturaMas = new ZonaDeCobertura("Quilmes", ubicacion3, 500);

		Set<ZonaDeCobertura> zonas = new HashSet<ZonaDeCobertura>();

		zonas.add(zonaDeCobertura);
		zonas.add(otraZonaDeCobertura);
		zonas.add(otraZonaDeCoberturaMas);

		assertEquals(zonaDeCobertura.zonasQueSolapan(zonas).size(), 2);
	}

	@Test
	void testUnaZonaNotificaNuevaVerificacion() {
		zonaDeCobertura = new ZonaDeCobertura("Berazategui", ubicacion1, 500);

		zonaDeCobertura.registrarseAZonaDeCobertura(organizacion);
		zonaDeCobertura.muestraVerificada(muestra);

		verify(organizacion).nuevaVerificacion(zonaDeCobertura, muestra);
	}

	@Test
	void testMuestrasAMenosDe1359KmEnLaZonaDeCobertura() {

		ubicacion1 = new Ubicacion(50d, 40d);
		ubicacion2 = new Ubicacion(123150d, 1241240d);

		when(muestra.getUbicacion()).thenReturn(ubicacion1);
		zonaDeCobertura = new ZonaDeCobertura("Berazategui", ubicacion1, 2000);

		when(otraMuestra.getUbicacion()).thenReturn(ubicacion2);

		zonaDeCobertura.agregarMuestra(muestra);
		zonaDeCobertura.agregarMuestra(otraMuestra);

		assertFalse(zonaDeCobertura.muestrasCercanas(muestra, 1359.2545257553352).contains(otraMuestra));

	}
}
