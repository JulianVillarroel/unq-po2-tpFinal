package ar.edu.unq.po2.tpFinal.EstadoDeUsuario;

import ar.edu.unq.po2.tpFinal.Muestra;
import ar.edu.unq.po2.tpFinal.Opinion;

public interface EstadoDeUsuario {
	public void opinarSobreMuestra(Muestra muestra,Opinion opinion, Usuario usuario);
	public boolean esUsuarioBasico();
	public void actualizarCategoria(Usuario usuario);
	public void agregaroppinionAMuestraMasVotadaPorExperto(Usuario usuario, Muestra muestra, Opinion opinion);
}
