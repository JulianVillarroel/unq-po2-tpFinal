package ar.edu.unq.po2.tpFinal.Observer;

import ar.edu.unq.po2.tpFinal.Muestra;
import ar.edu.unq.po2.tpFinal.Ubicaciones.ZonaDeCobertura;

public interface IOrganizacionObserver {
	
	public void nuevaMuestra(ZonaDeCobertura zona, Muestra muestra);

	public void nuevaVerificacion(ZonaDeCobertura zonaDeCobertura, Muestra muestra);
}
