package ar.edu.unq.po2.tpFinal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

import ar.edu.unq.po2.tpFinal.EstadoDeUsuario.Usuario;
import ar.edu.unq.po2.tpFinal.Filtros.BusquedaDeMuestra;
import ar.edu.unq.po2.tpFinal.Ubicaciones.ZonaDeCobertura;
import ar.edu.unq.po2.tpFinal.Ubicaciones.Ubicacion;

class AplicacionWebTestCase {

	AplicacionWeb aplicacionWeb;
	Muestra muestra;
	Muestra muestra2;
	Muestra muestra3;
	ZonaDeCobertura zonaDeCobertura;
	ZonaDeCobertura zonaDeCobertura2;
	ZonaDeCobertura zonaDeCobertura3;

	@BeforeEach
	void setUp() {
		aplicacionWeb = new AplicacionWeb();
		muestra = mock(Muestra.class);
		muestra2 = mock(Muestra.class);
		muestra3 = mock(Muestra.class);
		zonaDeCobertura = mock(ZonaDeCobertura.class);
		zonaDeCobertura2 = mock(ZonaDeCobertura.class);
		zonaDeCobertura3 = mock(ZonaDeCobertura.class);
	}

	@Test
	void testNoSeRegistranMuestrasEnLaAplicacionWeb() {
		assertEquals(0, aplicacionWeb.getMuestrasRegistradas().size());
	}

	@Test
	void testSeRegistraUnaMuestraYHaySoloUnaMuestraEnPaginaWeb() {
		aplicacionWeb.registrarMuestra(muestra);

		assertEquals(1, aplicacionWeb.getMuestrasRegistradas().size());
	}

	@Test
	void testNoSeRegistranUsuariosEnLaAplicacionWeb() {
		assertEquals(0, aplicacionWeb.getUsuariosRegistrados().size());
	}

	@Test
	void testSeRegistraUnUsuarioYHaySoloUnUsuarioEnPaginaWeb() {
		Usuario usuario = mock(Usuario.class);
		aplicacionWeb.registrarUsuario(usuario);

		assertEquals(1, aplicacionWeb.getUsuariosRegistrados().size());
	}

	@Test
	void testUnaAplicacionWebNoTieneZonasDeCoberturaRegistradas() {
		assertEquals(0, aplicacionWeb.getZonasDeCobertura().size());
	}

	@Test
	void testSeRegistraUnaCoberturaYHaySoloUnaCoberturaEnPaginaWeb() {
		aplicacionWeb.agregarZonaDeCobertura(zonaDeCobertura);

		assertEquals(1, aplicacionWeb.getZonasDeCobertura().size());
	}

	@Test
	void testAplicacionWebActualizaLaCategoriaDeTodosLosUsuarios() {
		Usuario Jony = mock(Usuario.class);
		Usuario julian = mock(Usuario.class);

		aplicacionWeb.registrarUsuario(Jony);
		aplicacionWeb.registrarUsuario(julian);
		aplicacionWeb.actualizarCategoriaDeUsuariosALaFecha();

		verify(Jony).actualizarCategoria();
		verify(julian).actualizarCategoria();
	}

	@Test
	void testAplicacionWebRegistraUnaNuevaMuestraYLeAvisaALasZonasDeCoberturaRegistradas() {
		aplicacionWeb.agregarZonaDeCobertura(zonaDeCobertura);
		aplicacionWeb.agregarZonaDeCobertura(zonaDeCobertura2);
		aplicacionWeb.registrarMuestra(muestra);

		verify(zonaDeCobertura).agregarMuestra(muestra);
		verify(zonaDeCobertura2).agregarMuestra(muestra);
	}

	@Test
	void testAplicacionWebFiltraSuListraDeMuestra() {
		ArrayList<Muestra> listaDeMuestras = new ArrayList<Muestra>();
		BusquedaDeMuestra filtro = mock(BusquedaDeMuestra.class);

		listaDeMuestras.add(muestra);
		listaDeMuestras.add(muestra2);
		listaDeMuestras.add(muestra3);
		aplicacionWeb.registrarMuestra(muestra);
		aplicacionWeb.registrarMuestra(muestra2);
		aplicacionWeb.registrarMuestra(muestra3);
		aplicacionWeb.filtrarMuestras(filtro);

		verify(filtro).buscar(listaDeMuestras);
	}

	@Test
	void testAplicacionWebFiltraSusMuestrasYElFiltroLeDevuelveLasMismasMuestras() {
		ArrayList<Muestra> listaDeMuestras = new ArrayList<Muestra>();
		BusquedaDeMuestra filtro = mock(BusquedaDeMuestra.class);

		listaDeMuestras.add(muestra);
		listaDeMuestras.add(muestra2);
		listaDeMuestras.add(muestra3);
		aplicacionWeb.registrarMuestra(muestra);
		aplicacionWeb.registrarMuestra(muestra2);
		aplicacionWeb.registrarMuestra(muestra3);
		when(filtro.buscar(listaDeMuestras)).thenReturn(listaDeMuestras);

		assertEquals(listaDeMuestras, aplicacionWeb.filtrarMuestras(filtro));
	}

	@Test
	void testAplicacionWebQuiereSaberCualesSonLasZonasQueSeSolapanConUnaZonaDeCobertura() {
		Set<ZonaDeCobertura> zonasDeCobertura = new HashSet<ZonaDeCobertura>();

		zonasDeCobertura.add(zonaDeCobertura2);
		zonasDeCobertura.add(zonaDeCobertura3);
		aplicacionWeb.agregarZonaDeCobertura(zonaDeCobertura2);
		aplicacionWeb.agregarZonaDeCobertura(zonaDeCobertura3);
		aplicacionWeb.zonasQueSeSolapanCon(zonaDeCobertura);

		verify(zonaDeCobertura).zonasQueSolapan(zonasDeCobertura);
	}

	@Test
	void testAplicacionConoceLasMuestrasCercanasDeUnaZona() {
		Ubicacion ubicacion = new Ubicacion(40d, 50d);
		Ubicacion ubicacion2 = new Ubicacion(50d, 40d);
		Ubicacion ubicacion3 = new Ubicacion(500d, 400d);

		when(zonaDeCobertura.getEpicentro()).thenReturn(ubicacion);
		when(zonaDeCobertura2.getEpicentro()).thenReturn(ubicacion2);
		when(zonaDeCobertura3.getEpicentro()).thenReturn(ubicacion3);
		when(muestra.getUbicacion()).thenReturn(ubicacion);
		when(muestra2.getUbicacion()).thenReturn(ubicacion2);
		when(muestra3.getUbicacion()).thenReturn(ubicacion3);
		aplicacionWeb.registrarMuestra(muestra);
		aplicacionWeb.registrarMuestra(muestra2);
		aplicacionWeb.registrarMuestra(muestra3);
		aplicacionWeb.muestrasCercanas(zonaDeCobertura, muestra, 1359.2545257553352);

		verify(zonaDeCobertura).muestrasCercanas(muestra, 1359.2545257553352);
	}

}
