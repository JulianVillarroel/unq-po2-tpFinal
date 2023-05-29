package ar.edu.unq.po2.tpFinal;

import java.awt.image.BufferedImage;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;

import ar.edu.unq.po2.tpFinal.Enumerativos.Calificacion;
import ar.edu.unq.po2.tpFinal.EstadoDeMuestra.EstadoDeMuestra;
import ar.edu.unq.po2.tpFinal.EstadoDeMuestra.EstadoDeMuestraVotada;
import ar.edu.unq.po2.tpFinal.EstadoDeUsuario.Usuario;
import ar.edu.unq.po2.tpFinal.Ubicaciones.Ubicacion;

public class Muestra {
	
	BufferedImage fotoVinchuca;
	Calificacion especie;
	Usuario usuario;
	Ubicacion ubicacion;

	EstadoDeMuestra estadoActual;
	LocalDate fechaDeCreacion;
	LocalDate fechaDeUltimaVotacion;
	Map<Usuario, Opinion> historialDeOpiniones;

	public Muestra(BufferedImage fotoVinchuca, Ubicacion ubicacion, Usuario usuario, Opinion opinion,
			LocalDate fechaDeCreacion) throws Exception {

		this.fotoVinchuca = fotoVinchuca;
		this.ubicacion = ubicacion;
		this.usuario = usuario;
		this.estadoActual = new EstadoDeMuestraVotada();
		this.historialDeOpiniones = new HashMap<Usuario, Opinion>();
		this.agregarLaOpinionDelUsuario(opinion, usuario);
		this.fechaDeCreacion = fechaDeCreacion;
		this.fechaDeUltimaVotacion = fechaDeCreacion;
	}

	public LocalDate getFechaDeCreacion() {
		return this.fechaDeCreacion;
	}

	public Map<Usuario, Opinion> getHistorialDeOpiniones() {
		return this.historialDeOpiniones;
	}

	public LocalDate getFechaUltimaVotacion() {
		return this.fechaDeUltimaVotacion;
	}

	public Calificacion getEspecieDeVinchuca() {
		return especie;
	}

	public void setEspecie(Calificacion especie) {
		this.especie = especie;
	}

	public BufferedImage getFotoVinchuca() {
		return this.fotoVinchuca;
	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public String getIdentificacionPropietarioDeLaMuestra() {
		return this.usuario.getIdentificacion();
	}

	public EstadoDeMuestra getEstadoMuestra() {
		return estadoActual;
	}

	public void setEstadoDeMuestra(EstadoDeMuestra estado) {
		this.estadoActual = estado;
	}

	public void verificarMuestra() throws Exception {
		this.estadoActual.actualizarEstado(this);
	}

	public String getNivelDeVerificacion() {
		return this.getEstadoMuestra().getNivelDeVerificacion(this);
	}

	public boolean contieneAlUsuario(Usuario usuario) {
		return this.historialDeOpiniones.containsKey(usuario);
	}

	public boolean contieneLaOpinion(Opinion opinion) {
		return this.historialDeOpiniones.containsValue(opinion);
	}

	public void agregarLaOpinion(Opinion opinion, Usuario usuario) throws Exception {
		this.estadoActual.agregarOpinion(this, opinion, usuario);
	}

	public void agregarLaOpinionDelUsuario(Opinion opinion, Usuario usuario) {
		this.historialDeOpiniones.put(usuario, opinion);
		this.actualizarFechaUltimaVotacion(opinion);
		usuario.agregarOpinionEnviada(opinion);
	}

	private void actualizarFechaUltimaVotacion(Opinion opinion) {
		this.fechaDeUltimaVotacion = opinion.getFechaDeEmision();
	}

	public List<Calificacion> getCalificacionDeOpiniones() {
		ArrayList<Calificacion> opiniones = new ArrayList<Calificacion>();
		for (HashMap.Entry<Usuario, Opinion> opinionDeUsuario : this.getHistorialDeOpiniones().entrySet()) {
			opiniones.add((opinionDeUsuario.getValue()).getCalificacion());
		}
		return opiniones;
	}

	public boolean coincidenDosExpertosEnSuCalificacionDeOpinion() {
		final List<Calificacion> calificacionDeOpinionesEncontradas = new ArrayList<Calificacion>();
		for (Calificacion calif : this.getCalificacionDeOpiniones()) {
			if (calif.equals(this.getEspecieDeVinchuca())) {
				calificacionDeOpinionesEncontradas.add(calif);
			}
		}
		return calificacionDeOpinionesEncontradas.size() >= 2;
	}

	public Calificacion getResultadoActual() {
		Calificacion opinionMasVotada = getCalificacionDeOpiniones().stream()
				.reduce(BinaryOperator.maxBy((o1, o2) -> Collections.frequency(getCalificacionDeOpiniones(), o1)
						- Collections.frequency(getCalificacionDeOpiniones(), o2)))
				.orElse(Calificacion.NO_DEFINIDO);
		return (opinionMasVotada);
	}

	public void verificarMuestraConOpinionDeUsuario(Opinion opinion, Usuario usuario) throws Exception {
		this.verificarMuestra();
		this.agregarLaOpinionDelUsuario(opinion, usuario);

	}
}
