package ar.edu.unq.po2.tpFinal.EstadoDeUsuario;

public class EstadoDeUsuarioEspecialista extends EstadoDeUsuarioExperto {

	@Override
	public Boolean esUsuarioExperto() {
		return true;
	}

	@Override
	public void actualizarCategoria(Usuario usuario) {
	}
}
