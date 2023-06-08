package ar.edu.unq.po2.tpFinal.EstadoDeMuestra;

import ar.edu.unq.po2.tpFinal.Muestra;
import ar.edu.unq.po2.tpFinal.Opinion;
import ar.edu.unq.po2.tpFinal.EstadoDeUsuario.Usuario;

public class EstadoDeMuestraVotada implements EstadoDeMuestra {

	@Override
	public String getNivelDeVerificacion(Muestra muestra) {
		return "votada";
	}

	@Override
	public void agregarOpinion(Muestra muestra, Opinion opinion, Usuario usuario) throws Exception {
		if (!muestra.contieneAlUsuario(usuario)) {
			muestra.agregarLaOpinionDelUsuario(opinion, usuario);
		} else {
			throw new Exception("El usuario ya ha opinado sobre la muestra");
		}
	}

	@Override
	public void actualizarEstado(Muestra muestra) throws Exception {
		this.limpiarHistorialDeOpiniones(muestra);
		muestra.setEstadoDeMuestra(new EstadoDeMuestraVotadaPorExperto());

	}

	private void limpiarHistorialDeOpiniones(Muestra muestra) {
		muestra.getHistorialDeOpiniones().clear();
	}
}