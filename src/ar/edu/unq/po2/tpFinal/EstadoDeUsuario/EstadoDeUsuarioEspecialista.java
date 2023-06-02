package ar.edu.unq.po2.tpFinal.EstadoDeUsuario;

import ar.edu.unq.po2.tpFinal.Muestra;
import ar.edu.unq.po2.tpFinal.Opinion;

public class EstadoDeUsuarioEspecialista extends EstadoDeUsuarioExperto {
	

	@Override
	public void opinarSobreMuestra(Muestra muestra, Opinion opinion, Usuario usuario) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean esUsuarioBasico() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void actualizarCategoria(Usuario usuario) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void agregaroppinionAMuestraMasVotadaPorExperto(Usuario usuario, Muestra muestra, Opinion opinion) {
		// TODO Auto-generated method stub
	}	

}
