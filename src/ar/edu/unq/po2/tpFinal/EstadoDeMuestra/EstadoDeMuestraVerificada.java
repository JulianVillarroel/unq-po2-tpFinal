package ar.edu.unq.po2.tpFinal.EstadoDeMuestra;

import ar.edu.unq.po2.tpFinal.Muestra;
import ar.edu.unq.po2.tpFinal.Opinion;
import ar.edu.unq.po2.tpFinal.EstadoDeUsuario.Usuario;

public class EstadoDeMuestraVerificada implements EstadoDeMuestra {

	@Override
	public void agregarOpinion(Muestra muestra, Opinion opinion, Usuario usuario) throws Exception {
		throw new Exception("Nadie puede opinar sobre muestras verificadas");
	}

	@Override
	public String getNivelDeVerificacion(Muestra muestra) {
		return "verificada";
	}

	@Override
	public void actualizarEstado(Muestra muestra) throws Exception {
		throw new Exception("La muestra ya esta verificada");

	}

}
