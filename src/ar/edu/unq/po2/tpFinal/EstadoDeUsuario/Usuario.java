package ar.edu.unq.po2.tpFinal.EstadoDeUsuario;

import java.util.List;
import java.util.Set;

import ar.edu.unq.po2.tpFinal.AplicacionWeb;
import ar.edu.unq.po2.tpFinal.Muestra;
import ar.edu.unq.po2.tpFinal.Opinion;

public class Usuario {
	private String identificacion;
	private Set<Muestra> muestras;
	private List<Opinion> opinionesEnviadas;
	private AplicacionWeb aplicacionDeMuestra;
	private EstadoDeUsuario estadoDeUsuario;
	
	public void newUsuario(){}
	
	public EstadoDeUsuario getEstadoDelUsuario() {
		return estadoDeUsuario;
	}
	
	public void opinarSobreMuestra(Muestra muestra, Opinion opinion) {
		
	}
	
	public String getIdentificacion() {
		return identificacion;
	}

	public void agregarOpinionEnviada(Opinion opinion) {
		// TODO Auto-generated method stub
		
	}
	
	
}


