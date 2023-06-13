package ar.edu.unq.po2.tpFinal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.awt.image.BufferedImage;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.tpFinal.Enumerativos.Calificacion;
import ar.edu.unq.po2.tpFinal.EstadoDeUsuario.Usuario;
import ar.edu.unq.po2.tpFinal.Ubicaciones.Ubicacion;
import ar.edu.unq.po2.tpFinal.Ubicaciones.ZonaDeCobertura;

class MuestraTestCase {

	Muestra muestra;
	Usuario usuarioBasico;
	Usuario usuarioExperto;
	Usuario jonyExperto;
	Usuario julianBasico;
	Usuario villaBasico;
	Opinion opinionChincheFoliada;
	Opinion opinionChincheFoliada2;
	Opinion opinionGuasayana;
	Opinion opinionGuasayana2;
	Ubicacion ubicacion;
	Ubicacion ubicacionEpicentro;
	BufferedImage fotoVinchuca;
	ZonaDeCobertura zona1;

	@BeforeEach
	void SetUp() throws Exception {

		usuarioBasico = mock(Usuario.class);
		usuarioExperto = mock(Usuario.class);
		jonyExperto = mock(Usuario.class);
		julianBasico = mock(Usuario.class);
		villaBasico = mock(Usuario.class);
		opinionChincheFoliada = mock(Opinion.class);
		opinionChincheFoliada2 = mock(Opinion.class);
		opinionGuasayana = mock(Opinion.class);
		opinionGuasayana2 = mock(Opinion.class);
		zona1 = mock(ZonaDeCobertura.class);
		ubicacion = mock(Ubicacion.class);
		fotoVinchuca = mock(BufferedImage.class);

		muestra = new Muestra(fotoVinchuca, ubicacion, julianBasico, opinionGuasayana, LocalDate.of(2022, 5, 13));

		when(usuarioBasico.esUsuarioBasico()).thenReturn(true);
		when(usuarioExperto.esUsuarioExperto()).thenReturn(true);
		when(jonyExperto.esUsuarioExperto()).thenReturn(true);
		when(julianBasico.esUsuarioBasico()).thenReturn(true);

		when(opinionGuasayana.getCalificacion()).thenReturn(Calificacion.GUASAYANA);
		when(opinionGuasayana.getFechaDeEmision()).thenReturn(LocalDate.now());
		when(opinionGuasayana2.getCalificacion()).thenReturn(Calificacion.GUASAYANA);
		when(opinionGuasayana2.getFechaDeEmision()).thenReturn(LocalDate.now());
		when(opinionChincheFoliada.getCalificacion()).thenReturn(Calificacion.CHINCHE_FOLIADA);
		when(opinionChincheFoliada.getFechaDeEmision()).thenReturn(LocalDate.now());
		when(opinionChincheFoliada2.getCalificacion()).thenReturn(Calificacion.CHINCHE_FOLIADA);
		when(opinionChincheFoliada2.getFechaDeEmision()).thenReturn(LocalDate.now());
	}

	@Test
	void testConstructor() {
		assertEquals(fotoVinchuca, muestra.getFotoVinchuca());
		assertEquals(ubicacion, muestra.getUbicacion());
		when(muestra.getIdentificacionPropietarioDeLaMuestra()).thenReturn("Sofia");
		assertEquals("Sofia", muestra.getIdentificacionPropietarioDeLaMuestra());
		assertEquals(LocalDate.of(2022, 5, 13), muestra.getFechaDeCreacion());
		assertEquals(1, muestra.getHistorialDeOpiniones().size());
	}

	@Test
	void testMuestraConUnaSolaOpinion() {
		muestra.agregarLaOpinionDelUsuario(opinionGuasayana, usuarioBasico);
		assertEquals(Calificacion.GUASAYANA, muestra.getResultadoActual());
	}

	@Test
	void testEstadoDeLaMuestraConVerificacionParcial() {
		muestra.agregarLaOpinionDelUsuario(opinionGuasayana, usuarioBasico);
		muestra.agregarLaOpinionDelUsuario(opinionChincheFoliada, julianBasico);
		muestra.agregarLaOpinionDelUsuario(opinionChincheFoliada, usuarioExperto);

		assertEquals(Calificacion.CHINCHE_FOLIADA, muestra.getResultadoActual());
		assertThrows(Exception.class, () -> muestra.agregarLaOpinion(opinionGuasayana, villaBasico));

	}

	@Test
	void testSeAgregaUnaOpinionALaMuestraYSeActualizaLaUltimaFechaDeVotacion() {
		assertEquals(LocalDate.of(2022, 5, 13), muestra.getFechaUltimaVotacion());
		muestra.agregarLaOpinionDelUsuario(opinionGuasayana, julianBasico);

		assertEquals(LocalDate.now(), muestra.getFechaUltimaVotacion());

	}

	@Test
	void testUnaMuestraNoPuedeOpinarCreador() throws Exception {
		assertThrows(Exception.class, () -> muestra.agregarLaOpinion(opinionGuasayana, julianBasico));
	}

	@Test
	void testUnaMuestraConUnUsuarioQueNoOpino() {
		assertFalse(muestra.contieneAlUsuario(usuarioExperto));
	}

	@Test
	void testSeAgregaUnaOpinionYSeLeAgregaAsuHistorialDeOpinionesaMuestra() throws Exception {
		assertFalse(muestra.contieneLaOpinion(opinionChincheFoliada));
		muestra.agregarLaOpinionDelUsuario(opinionChincheFoliada, jonyExperto);

		assertTrue(muestra.contieneLaOpinion(opinionChincheFoliada));
	}

	@Test
	void testSeAgregaUnaOpinionYSeSumaALaLista() throws Exception {
		muestra.agregarLaOpinionDelUsuario(opinionChincheFoliada2, usuarioBasico);

		assertEquals(2, muestra.getHistorialDeOpiniones().size());
	}

	@Test
	void testMuestraEsComentadaPorUnUsuarioExperto() throws Exception {
		muestra.verificarMuestra();
		muestra.agregarLaOpinion(opinionChincheFoliada, usuarioExperto);

		verify(usuarioExperto).agregarOpinionAMuestraVotadaPorExperto(muestra, opinionChincheFoliada);
	}

	@Test
	void testOpinaUnExpertoLasOpinionesQueValenSonSoloLasDeLosExpertos() throws Exception {
		assertTrue(muestra.contieneLaOpinion(opinionGuasayana));

		muestra.verificarMuestra();
		muestra.agregarLaOpinionDelUsuario(opinionChincheFoliada, jonyExperto);

		assertFalse(muestra.contieneLaOpinion(opinionGuasayana));
		assertTrue(muestra.contieneLaOpinion(opinionChincheFoliada));

	}

	@Test
	void testExpertosCoincidenEnSuOpinionVerificandoLaMuestra() throws Exception {
		muestra.setEspecie(Calificacion.GUASAYANA);
		muestra.verificarMuestra();
		muestra.agregarLaOpinionDelUsuario(opinionGuasayana, jonyExperto);
		muestra.agregarLaOpinionDelUsuario(opinionGuasayana, usuarioExperto);
		muestra.verificarMuestra();

		assertTrue(muestra.coincidenDosExpertosEnSuCalificacionDeOpinion());
		assertEquals("verificada", muestra.getNivelDeVerificacion());
		assertThrows(Exception.class, () -> muestra.agregarLaOpinion(opinionGuasayana, julianBasico));

	}

	@Test // (expected =OutOfRangeException.class)
	void testMuestraVerificadaNoSePuedeVolverAVerificar() throws Exception {
		muestra.setEspecie(Calificacion.CHINCHE_FOLIADA);
		when(opinionChincheFoliada.getCalificacion()).thenReturn(Calificacion.CHINCHE_FOLIADA);

		muestra.verificarMuestra();
		muestra.agregarLaOpinionDelUsuario(opinionChincheFoliada, usuarioExperto);
		muestra.agregarLaOpinionDelUsuario(opinionChincheFoliada, jonyExperto);

		muestra.verificarMuestra();

		assertThrows(Exception.class, () -> muestra.verificarMuestra());
	}

	@Test
	void testDosUsuariosExpertosNoCoincidenEnLaOpinionSobreUnaMuestraEstaNoSeVerifica() throws Exception {
		muestra.verificarMuestra();
		muestra.agregarLaOpinionDelUsuario(opinionChincheFoliada, julianBasico);
		muestra.agregarLaOpinionDelUsuario(opinionGuasayana2, usuarioExperto);
		muestra.verificarMuestra();

		assertFalse(muestra.coincidenDosExpertosEnSuCalificacionDeOpinion());
		assertEquals("votada", muestra.getNivelDeVerificacion());
	}

	@Test
	void testCuandoUsuarioExpertoTrataDeVotarUnaMuestraVerificadaSuOpinionNoCuenta() throws Exception {
		muestra.verificarMuestra();
		muestra.agregarLaOpinionDelUsuario(opinionChincheFoliada, usuarioExperto);
		muestra.agregarLaOpinionDelUsuario(opinionChincheFoliada2, jonyExperto);
		muestra.verificarMuestra();

		assertThrows(Exception.class, () -> muestra.agregarLaOpinion(opinionGuasayana2, usuarioExperto));
	}

	@Test
	void testCuandoUsuarioExpertoTrataDeVotarUnaMuestraQueYaVotoNoPuedeVolverAOpinar() throws Exception {
		muestra.verificarMuestra();
		muestra.agregarLaOpinionDelUsuario(opinionChincheFoliada, usuarioExperto);
		muestra.agregarLaOpinionDelUsuario(opinionChincheFoliada2, usuarioExperto);
		muestra.verificarMuestra();

		assertThrows(Exception.class, () -> muestra.agregarLaOpinion(opinionGuasayana2, usuarioExperto));
	}

	@Test
	void testMuestraTieneNivelDeVerificacionVotadaSiNoOpinaNingunExperto() {
		assertEquals("votada", muestra.getNivelDeVerificacion());
	}

	@Test
	void testMuestraTieneUnNivelDeVerificacionVotadaPorExpertoSiOpinoAlMenosUnExperto() throws Exception {
		muestra.agregarLaOpinionDelUsuario(opinionChincheFoliada, usuarioExperto);
		muestra.verificarMuestra();

		assertEquals("votada", muestra.getNivelDeVerificacion());
	}

	@Test
	void testCuandoSeLePideAMuestraSuResultadoActualRetornaLaOpinionConMasVotos() throws Exception {
		muestra.agregarLaOpinionDelUsuario(opinionChincheFoliada, usuarioBasico);
		muestra.agregarLaOpinionDelUsuario(opinionChincheFoliada2, usuarioExperto);
		muestra.agregarLaOpinionDelUsuario(opinionChincheFoliada2, jonyExperto);

		assertEquals(Calificacion.CHINCHE_FOLIADA, muestra.getResultadoActual());
	}

	@Test
	void test() {

	}


}
