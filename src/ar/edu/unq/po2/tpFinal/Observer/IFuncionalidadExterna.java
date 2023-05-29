package ar.edu.unq.po2.tpFinal.Observer;

import ar.edu.unq.po2.tpFinal.Muestra;
import ar.edu.unq.po2.tpFinal.Ubicaciones.ZonaDeCobertura;

public interface IFuncionalidadExterna {
	
	public void nuevoEvento(Organizacion organizacion, ZonaDeCobertura zona, Muestra muestra);
}
