package ar.edu.unq.po2.tpFinal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.tpFinal.Enumerativos.tipoDeOrganizacion;
import ar.edu.unq.po2.tpFinal.Observer.IFuncionalidadExterna;
import ar.edu.unq.po2.tpFinal.Observer.Organizacion;
import ar.edu.unq.po2.tpFinal.Ubicaciones.Ubicacion;
import ar.edu.unq.po2.tpFinal.Ubicaciones.ZonaDeCobertura;

class OrganizacionTestCase {

	Organizacion organizacion;
	ZonaDeCobertura zona;
	Ubicacion ubicacion;
	IFuncionalidadExterna nuevaMuestra;
	IFuncionalidadExterna verificacionMuestra;
	Muestra muestra;

	@BeforeEach
	void setUp() throws Exception {
		muestra = mock(Muestra.class);
		ubicacion = mock(Ubicacion.class);
		zona = mock(ZonaDeCobertura.class);
		nuevaMuestra = mock(IFuncionalidadExterna.class);
		verificacionMuestra = mock(IFuncionalidadExterna.class);
		organizacion = new Organizacion(ubicacion, tipoDeOrganizacion.SALUD, 5, nuevaMuestra, verificacionMuestra);
	}

	@Test
	void testConstructorOrganizacion() {
		assertEquals(organizacion.getCantidadDePersonas(), 5);
		assertEquals(organizacion.getTipo(), tipoDeOrganizacion.SALUD);
		assertEquals(organizacion.getUbicacion(), ubicacion);
		assertEquals(organizacion.getNuevaMuestra(), nuevaMuestra);
		assertEquals(organizacion.getVerificacionMuestra(), verificacionMuestra);
	}

	@Test
	void testOrganizacionNotificadaConUnaNuevaMuestra() {

		organizacion.nuevaMuestra(zona, muestra);

		verify(nuevaMuestra, times(1)).nuevoEvento(organizacion, zona, muestra);
	}

	@Test
	void testOrganizacionNotificadaConNuevaVerificacion() {

		organizacion.nuevaVerificacion(zona, muestra);

		verify(verificacionMuestra, times(1)).nuevoEvento(organizacion, zona, muestra);
	}

	@Test
	void testSeRegistraUnaOrganizacionEnUnaZonaDeCobertura() {
		ZonaDeCobertura zonaDeCobertura = mock(ZonaDeCobertura.class);

		organizacion.registrarseAZonaDeCobertura(zonaDeCobertura);

		verify(zonaDeCobertura).registrarseAZonaDeCobertura(organizacion);
	}

	@Test
	void testUnaOrganizacionDejaUnaZonaDeCobertura() {
		ZonaDeCobertura zonaDeCobertura = mock(ZonaDeCobertura.class);

		organizacion.dejarZonaDeCobertura(zonaDeCobertura);

		verify(zonaDeCobertura).dejarZonaDeCobertura(organizacion);
	}

}
