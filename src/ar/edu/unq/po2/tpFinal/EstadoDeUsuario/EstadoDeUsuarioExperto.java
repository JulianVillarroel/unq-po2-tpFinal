package ar.edu.unq.po2.tpFinal.EstadoDeUsuario;

import ar.edu.unq.po2.tpFinal.Muestra;
import ar.edu.unq.po2.tpFinal.Opinion;

public class EstadoDeUsuarioExperto extends EstadoDeUsuario {
	@Override
	public void opinarSobreMuestra(Muestra muestra, Opinion opinion, Usuario usuario) throws Exception {
		muestra.verificarMuestraConOpinionDeUsuario(opinion, usuario);

	}

	@Override
	public Boolean esUsuarioExperto() {
		return true;
	}

	@Override
	public void actualizarCategoria(Usuario usuario) {
		if (!usuario.cumpleConRevisionesNecesarias() | !usuario.cumpleConEnviosNecesarios()) {
			usuario.setEstadoDeUsuario(new EstadoDeUsuarioBasico());
		}
	}

	@Override
	public void agregarOpinionAMuestraVotadaPorExperto(Usuario usuario, Muestra muestra, Opinion opinion)
			throws Exception {
		muestra.agregarLaOpinionDelUsuario(opinion, usuario);
	}
}
