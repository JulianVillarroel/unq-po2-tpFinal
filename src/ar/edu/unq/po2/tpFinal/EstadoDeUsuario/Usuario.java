package ar.edu.unq.po2.tpFinal.EstadoDeUsuario;

import java.util.List;

import ar.edu.unq.po2.tpFinal.AplicacionWeb;
import ar.edu.unq.po2.tpFinal.Muestra;
import ar.edu.unq.po2.tpFinal.Opinion;

public class Usuario {
	private String identificacion;
	private List<Muestra> muestras;
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
	
	
}


