package ar.edu.unq.po2.tpFinal.Observer;

import ar.edu.unq.po2.tpFinal.Muestra;

public interface IListenerZonaCobertura {
	
	public void registrarseAZonaDeCobertura(IOrganizacionObserver observer);
	public void dejarZonaDeCobertura(IOrganizacionObserver observer);
	public void notificarNuevaMuestra(Muestra muestra);
	public void notificarNuevaVerificacion(Muestra muestra);
}
