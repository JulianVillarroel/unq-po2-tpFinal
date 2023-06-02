package ar.edu.unq.po2.tpFinal.EstadoDeMuestra;

import ar.edu.unq.po2.tpFinal.Muestra;
import ar.edu.unq.po2.tpFinal.Opinion;
import ar.edu.unq.po2.tpFinal.EstadoDeUsuario.Usuario;

public interface EstadoDeMuestra {

	String getNivelDeVerificacion(Muestra muestra);

	void agregarOpinion(Muestra muestra, Opinion opinion, Usuario usuario);

	void actualizarEstado(Muestra muestra);
	
}
