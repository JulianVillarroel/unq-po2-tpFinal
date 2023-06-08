package ar.edu.unq.po2.tpFinal.EstadoDeUsuario;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ar.edu.unq.po2.tpFinal.AplicacionWeb;
import ar.edu.unq.po2.tpFinal.Muestra;
import ar.edu.unq.po2.tpFinal.Opinion;

public class Usuario {

	private String identificacion;
	private Set<Muestra> muestras;
	private List<Opinion> opinionesEnviadas;
	private EstadoDeUsuario estadoDeUsuario;

	public Usuario(String identificacion) {
		this.identificacion = identificacion;
		this.muestras = new HashSet<Muestra>();
		this.opinionesEnviadas = new ArrayList<Opinion>();
		this.estadoDeUsuario = new EstadoDeUsuarioBasico();
	}

	public String getIdentificacion() {
		return this.identificacion;
	}

	public Integer getRevisiones() {
		return this.opinionesEnviadas.size();
	}

	public void opinarSobreMuestra(Muestra muestra, Opinion opinion) throws Exception {
		this.estadoDeUsuario.opinarSobreMuestra(muestra, opinion, this);
	}

	public void setOpinionesEnviadas(List<Opinion> opinionesEnviadas) {
		this.opinionesEnviadas = opinionesEnviadas;
	}

	public Integer getEnvios() {
		return this.muestras.size();
	}

	public void enviarMuestra(Muestra muestra, AplicacionWeb app) {
		this.muestras.add(muestra);
		app.registrarMuestra(muestra);
	}

	public void setEstadoDeUsuario(EstadoDeUsuario estadoDeUsuario) {
		this.estadoDeUsuario = estadoDeUsuario;
	}

	public void agregarOpinionEnviada(Opinion opinion) {
		this.opinionesEnviadas.add(opinion);
	}

	public List<Opinion> getOpinionesEnviadas() {
		return this.opinionesEnviadas;
	}

	public Set<Muestra> getMuestrasEnviadas() {
		return this.muestras;
	}

	public void setMuestras(Set<Muestra> muestras) {
		this.muestras = muestras;
	}

	public Boolean esUsuarioExperto() {
		return this.estadoDeUsuario.esUsuarioExperto();
	}

	public Boolean esUsuarioBasico() {
		return this.estadoDeUsuario.esUsuarioBasico();
	}

	public Integer cantidadDeOpinionesEnLosUltimos30Dias() {
		return this.opinionesDelUltimoMes().size();
	}

	public List<Opinion> opinionesDelUltimoMes() {
		ArrayList<Opinion> opinionesDelUltimoMes = new ArrayList<Opinion>();
		for (Opinion opinion : this.getOpinionesEnviadas()) {
			if (laOpinionEstaDentroDe30DiasDeLaFecha(opinion)) {
				opinionesDelUltimoMes.add(opinion);
			}
		}
		return opinionesDelUltimoMes;
	}

	public Boolean laOpinionEstaDentroDe30DiasDeLaFecha(Opinion opinion) {
		LocalDate fechaActual = LocalDate.now();
		return ChronoUnit.DAYS.between(opinion.getFechaDeEmision(), fechaActual) <= 30;
		/*
		 * ChronoUnit es una enumeración que implementa la interfaz TemporalUnit , que
		 * proporciona las unidades estándar utilizadas en la API de fecha y hora de
		 * Java .
		 */
	}

	public Integer cantidadDeEnviosEnLosUltimos30Dias() {
		return this.enviosDelUltimoMes().size();
	}

	public List<Muestra> enviosDelUltimoMes() {
		ArrayList<Muestra> enviosDelUltimoMes = new ArrayList<Muestra>();
		for (Muestra muestra : this.getMuestrasEnviadas()) {
			if (laMuestraEstaDentroDe30DiasDeLaFecha(muestra)) {
				enviosDelUltimoMes.add(muestra);
			}
		}
		return enviosDelUltimoMes;
	}

	public Boolean laMuestraEstaDentroDe30DiasDeLaFecha(Muestra muestra) {
		LocalDate fechaActual = LocalDate.now();
		return ChronoUnit.DAYS.between(muestra.getFechaDeCreacion(), fechaActual) <= 30;
	}

	public void agregarOpinionAMuestraVotadaPorExperto(Muestra muestra, Opinion opinion) throws Exception {
		this.estadoDeUsuario.agregarOpinionAMuestraVotadaPorExperto(this, muestra, opinion);
	}

	public void actualizarCategoria() {
		this.estadoDeUsuario.actualizarCategoria(this);
	}

	protected Boolean cumpleConRevisionesNecesarias() {
		return this.cantidadDeOpinionesEnLosUltimos30Dias() >= 20;
	}

	protected Boolean cumpleConEnviosNecesarios() {
		return this.cantidadDeEnviosEnLosUltimos30Dias() >= 10;
	}

	public void cambiarAUsuarioEspecialista() {
		this.estadoDeUsuario = new EstadoDeUsuarioEspecialista();
	}

}
