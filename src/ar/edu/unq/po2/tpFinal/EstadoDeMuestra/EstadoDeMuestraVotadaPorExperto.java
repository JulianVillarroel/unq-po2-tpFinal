package ar.edu.unq.po2.tpFinal.EstadoDeMuestra;

import ar.edu.unq.po2.tpFinal.Muestra;
import ar.edu.unq.po2.tpFinal.Opinion;
import ar.edu.unq.po2.tpFinal.EstadoDeUsuario.Usuario;

public class EstadoDeMuestraVotadaPorExperto implements EstadoDeMuestra {

	@Override
	public void agregarOpinion(Muestra muestra, Opinion opinion, Usuario usuario) throws Exception {
		if (!muestra.contieneAlUsuario(usuario)) {
			usuario.agregarOpinionAMuestraVotadaPorExperto(muestra, opinion);
		} else {
			throw new Exception("El usuario ya ha opinado sobre la muestra");
		}
	}

	@Override
	public String getNivelDeVerificacion(Muestra muestra) {
		return "votada";
	}

	@Override
	public void actualizarEstado(Muestra muestra) {
		if (muestra.coincidenDosExpertosEnSuCalificacionDeOpinion()) {
			muestra.setEstadoDeMuestra(new EstadoDeMuestraVerificada());
		}

	}

}
