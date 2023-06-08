package ar.edu.unq.po2.tpFinal.EstadoDeUsuario;

import ar.edu.unq.po2.tpFinal.Muestra;
import ar.edu.unq.po2.tpFinal.Opinion;

public class EstadoDeUsuarioBasico extends EstadoDeUsuario {

	@Override
	public void opinarSobreMuestra(Muestra muestra, Opinion opinion, Usuario usuario) throws Exception {
		muestra.agregarLaOpinionDelUsuario(opinion, usuario);
	}

	@Override
	public Boolean esUsuarioBasico() {
		return true;
	}

	@Override
	public void actualizarCategoria(Usuario usuario) {
		if (usuario.cumpleConRevisionesNecesarias() & usuario.cumpleConEnviosNecesarios()) {
			usuario.setEstadoDeUsuario(new EstadoDeUsuarioExperto());
		}
	}

	@Override
	public void agregarOpinionAMuestraVotadaPorExperto(Usuario usuario, Muestra muestra, Opinion opinionAAgregar)
			throws Exception {
		throw new Exception("La muestra solo puede ser votada por usuarios expertos");
	}

}
