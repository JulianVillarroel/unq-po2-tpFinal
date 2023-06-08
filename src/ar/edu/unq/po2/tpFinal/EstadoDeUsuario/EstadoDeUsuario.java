package ar.edu.unq.po2.tpFinal.EstadoDeUsuario;

import ar.edu.unq.po2.tpFinal.Muestra;
import ar.edu.unq.po2.tpFinal.Opinion;

public abstract class EstadoDeUsuario {

	public abstract void opinarSobreMuestra(Muestra muestra, Opinion opinion, Usuario usuario) throws Exception;

	public Boolean esUsuarioBasico() {
		return false;
	}

	public Boolean esUsuarioExperto() {
		return false;
	}

	public abstract void actualizarCategoria(Usuario usuario);

	public abstract void agregarOpinionAMuestraVotadaPorExperto(Usuario usuario, Muestra muestra,
			Opinion opinionAAgregar) throws Exception;
}