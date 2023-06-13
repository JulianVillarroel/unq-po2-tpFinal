package ar.edu.unq.po2.tpFinal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.awt.image.BufferedImage;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.tpFinal.Enumerativos.Calificacion;
import ar.edu.unq.po2.tpFinal.EstadoDeUsuario.EstadoDeUsuario;
import ar.edu.unq.po2.tpFinal.EstadoDeUsuario.Usuario;
import ar.edu.unq.po2.tpFinal.Ubicaciones.Ubicacion;

class UsuarioTestCase {

	Usuario usuarioBasico; // sut
	Usuario usuarioExperto; // sut
	Usuario usuarioExpertoJuli;
	Usuario usuarioEspecialista; // sut
	EstadoDeUsuario estadoExperto;

	AplicacionWeb app;// doc
	Muestra muestra, muestra2, muestra3, muestra4, muestra5, muestra6, muestra7, muestra8, muestra9, muestra10;
	Ubicacion ubicacion1;
	BufferedImage imagen1;
	Opinion opinion, opinion1;
	List<Opinion> opiniones;
	Set<Muestra> muestrasEnviadas;

	@BeforeEach
	void setUp() throws Exception {
		estadoExperto = mock(EstadoDeUsuario.class);
		when(estadoExperto.esUsuarioExperto()).thenReturn(true);
		app = mock(AplicacionWeb.class);
		usuarioBasico = new Usuario("Nahu");
		usuarioExperto = new Usuario("Jony");
		usuarioExpertoJuli = new Usuario("Fede");
		usuarioEspecialista = new Usuario("Villa");
		opinion = mock(Opinion.class);

		when(opinion.getCalificacion()).thenReturn(Calificacion.GUASAYANA);
		when(opinion.getFechaDeEmision()).thenReturn(LocalDate.now());

		muestra = mock(Muestra.class);
		muestra2 = mock(Muestra.class);
		muestra3 = mock(Muestra.class);
		muestra4 = mock(Muestra.class);
		muestra5 = mock(Muestra.class);
		muestra6 = mock(Muestra.class);
		muestra7 = mock(Muestra.class);
		muestra8 = mock(Muestra.class);
		muestra9 = mock(Muestra.class);
		muestra10 = mock(Muestra.class);

		when(muestra.getFechaDeCreacion()).thenReturn(LocalDate.now());
		when(muestra2.getFechaDeCreacion()).thenReturn(LocalDate.now());
		when(muestra3.getFechaDeCreacion()).thenReturn(LocalDate.now());
		when(muestra4.getFechaDeCreacion()).thenReturn(LocalDate.now());
		when(muestra5.getFechaDeCreacion()).thenReturn(LocalDate.now());
		when(muestra6.getFechaDeCreacion()).thenReturn(LocalDate.now());
		when(muestra7.getFechaDeCreacion()).thenReturn(LocalDate.now());
		when(muestra8.getFechaDeCreacion()).thenReturn(LocalDate.now());
		when(muestra9.getFechaDeCreacion()).thenReturn(LocalDate.now());
		when(muestra10.getFechaDeCreacion()).thenReturn(LocalDate.now());

		muestrasEnviadas = new HashSet<Muestra>();
		muestrasEnviadas.add(muestra);
		muestrasEnviadas.add(muestra2);
		muestrasEnviadas.add(muestra3);
		muestrasEnviadas.add(muestra4);
		muestrasEnviadas.add(muestra5);
		muestrasEnviadas.add(muestra6);
		muestrasEnviadas.add(muestra7);
		muestrasEnviadas.add(muestra8);
		muestrasEnviadas.add(muestra9);
		muestrasEnviadas.add(muestra10);

		// PARA TESTEAR LAS OPINIONES ENVIADAS EN LOS ULTIMOS 30 DIAS

		opiniones = Arrays.asList(opinion, opinion, opinion, opinion, opinion, opinion, opinion, opinion, opinion,
				opinion, opinion, opinion, opinion, opinion, opinion, opinion, opinion, opinion, opinion, opinion,
				opinion);
	}
	// TESTS DEL USUARIO BASICO

	@Test
	void testConoceSusDatos() {
		assertEquals(usuarioBasico.getEnvios(), 0);
		assertEquals(usuarioBasico.getRevisiones(), 0);
		assertEquals(usuarioBasico.getIdentificacion(), "Nahu");
	}

	@Test
	void testEsUsuarioBasico() {
		assertTrue(usuarioBasico.esUsuarioBasico());
	}

	@Test
	void testNoEsUsuarioExperto() {
		assertFalse(usuarioBasico.esUsuarioExperto());
	}

	@Test
	void testlasOpinionesA30DiasDeLaFechaActual() {
		usuarioBasico.setOpinionesEnviadas(opiniones);
		assertEquals(usuarioBasico.cantidadDeOpinionesEnLosUltimos30Dias(), 21);
	}

	@Test
	void testLasMuestrasEstanDentroDe30DiasDeLaFechaActual() {
		usuarioBasico.setMuestras(muestrasEnviadas);
		assertEquals(usuarioBasico.cantidadDeEnviosEnLosUltimos30Dias(), 10);
	}

	@Test
	void testUsuarioCambiaSuCategoriaAExperto() {
		usuarioBasico.setMuestras(muestrasEnviadas);
		usuarioBasico.setOpinionesEnviadas(opiniones);
		usuarioBasico.actualizarCategoria();
		assertTrue(usuarioBasico.esUsuarioExperto());
	}

	// TESTS DEL USUARIO EXPERTO
	@Test
	void testUsuarioAgregarOpinionAMuestraVotadaPorExperto() throws Exception {
		muestra.verificarMuestra();
		usuarioExperto.agregarOpinionEnviada(opinion);
		muestra.verificarMuestra();

		assertThrows(Exception.class, () -> usuarioBasico.agregarOpinionAMuestraVotadaPorExperto(muestra, opinion));
	}

	@Test
	void testEsUsuarioExperto() {
		usuarioExperto.setEstadoDeUsuario(estadoExperto);
		assertTrue(usuarioExperto.esUsuarioExperto());
	}

	@Test
	void testNoEsUsuarioBasico() {
		usuarioExperto.setEstadoDeUsuario(estadoExperto);
		assertFalse(usuarioExperto.esUsuarioBasico());
	}

	@Test
		void testLasOpinionesNoEstanDentroDe30DiasDeLaFecha() {
			when(opinion.getFechaDeEmision()).thenReturn(LocalDate.of(2020, 5, 5));
			opiniones = Arrays.asList(opinion);
			usuarioExperto.setOpinionesEnviadas(opiniones);
			assertEquals(usuarioExperto.cantidadDeOpinionesEnLosUltimos30Dias(), 0);
		}

	@Test
		void testLasMuestrasNoEstanDentroDe30DiasDeLaFecha() {
			when(muestra.getFechaDeCreacion()).thenReturn(LocalDate.of(2020, 5, 5));
			muestrasEnviadas = new HashSet<Muestra>();
			muestrasEnviadas.add(muestra);

			usuarioExperto.setMuestras(muestrasEnviadas);
			assertEquals(usuarioExperto.cantidadDeEnviosEnLosUltimos30Dias(), 0);

		}

	@Test
	void testUsuarioBasicoQueTieneCategoriaExpertoBajaACategoriaBasicoPorNoCumplirLosRequisitos() {
		usuarioBasico.actualizarCategoria();
		usuarioBasico.setMuestras(muestrasEnviadas);
		usuarioBasico.setOpinionesEnviadas(opiniones);
		usuarioBasico.actualizarCategoria();
		Boolean usuarioConEstadoAnteriorExperto = usuarioBasico.esUsuarioExperto();

		when(muestra.getFechaDeCreacion()).thenReturn(LocalDate.of(2020, 5, 5));
		muestrasEnviadas = new HashSet<Muestra>();
		muestrasEnviadas.add(muestra);

		usuarioBasico.setMuestras(muestrasEnviadas);
		usuarioBasico.actualizarCategoria();

		Boolean usuarioConEstadoNuevoBasico = usuarioBasico.esUsuarioBasico();
		assertTrue(usuarioConEstadoAnteriorExperto);
		assertTrue(usuarioConEstadoNuevoBasico);

	}

	@Test
	void testUsuarioConEstadoExperto()
			// Un usuario con estado de usuario experto opina sobre una muestra en
			// EstadoVotadaPorExperto Y le envia el mensaje a VerificarMuestra
			throws Exception {
		usuarioExperto.setMuestras(muestrasEnviadas);
		usuarioExperto.setOpinionesEnviadas(opiniones);
		usuarioExperto.actualizarCategoria();
		usuarioExperto.agregarOpinionAMuestraVotadaPorExperto(muestra, opinion);
		verify(muestra).agregarLaOpinionDelUsuario(opinion, usuarioExperto);
	}

	@Test
	void testUsuarioConEstadoDeUsuarioExpertoOpinaSobreUnaMuestra() throws Exception {
		usuarioExperto.setMuestras(muestrasEnviadas);
		usuarioExperto.setOpinionesEnviadas(opiniones);
		usuarioExperto.actualizarCategoria();
		usuarioExperto.opinarSobreMuestra(muestra, opinion);
		verify(muestra).verificarMuestraConOpinionDeUsuario(opinion, usuarioExperto);
	}

	// TEST USUARIO ESPECIALISTA
	@Test
	void testUsuarioEspecialistaSiempreTieneEstadoExperto() {
		usuarioEspecialista.cambiarAUsuarioEspecialista();

		assertFalse(usuarioEspecialista.esUsuarioBasico());
		assertTrue(usuarioEspecialista.esUsuarioExperto());
	}

	@Test
	void testUsuarioEspecialistaQuiereCambiarDeEstado() {
		// Un usuario especialista quiere cambiar de estado pero no puede ya que no
		// puede cambiar de estado ya que siempre es experto
		usuarioEspecialista.cambiarAUsuarioEspecialista();

		usuarioEspecialista.actualizarCategoria();
		assertTrue(usuarioEspecialista.esUsuarioExperto());

	}

	@Test
	void testUsuarioEnviaMuestra() {
		// Un usuario envia una Muestra envia mensaje a la aplicion web para registrarla

		usuarioBasico.enviarMuestra(muestra, app);
		verify(app).registrarMuestra(muestra);
	}

	@Test
	void testUsuarioEmiteUnaOpinionYLeEnviaElMensajeDeAgregarOpinionALaMuestra() throws Exception {
		usuarioBasico.opinarSobreMuestra(muestra, opinion);
		verify(muestra).agregarLaOpinionDelUsuario(opinion, usuarioBasico);
	}
}
