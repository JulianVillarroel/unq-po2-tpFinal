package ar.edu.unq.po2.tpFinal.Observer;

import ar.edu.unq.po2.tpFinal.Muestra;
import ar.edu.unq.po2.tpFinal.Enumerativos.tipoDeOrganizacion;
import ar.edu.unq.po2.tpFinal.Ubicaciones.Ubicacion;
import ar.edu.unq.po2.tpFinal.Ubicaciones.ZonaDeCobertura;

public class Organizacion implements IOrganizacionObserver {
	
	private Ubicacion ubicacion;
	private tipoDeOrganizacion tipo;
	private Integer cantidadDePersonas;
	private IFuncionalidadExterna nuevaMuestra;
	private IFuncionalidadExterna verificacionMuestra;

	public Organizacion(Ubicacion ubicacion, tipoDeOrganizacion tipo, Integer cantidadPersonas,
			IFuncionalidadExterna nuevaMuestra, IFuncionalidadExterna verificacionMuestra) {
		this.ubicacion = ubicacion;
		this.tipo = tipo;
		this.cantidadDePersonas = cantidadPersonas;
		this.nuevaMuestra = nuevaMuestra;
		this.verificacionMuestra = verificacionMuestra;
	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public tipoDeOrganizacion getTipo() {
		return tipo;
	}

	public Integer getCantidadDePersonas() {
		return cantidadDePersonas;
	}

	public IFuncionalidadExterna getNuevaMuestra() {
		return nuevaMuestra;
	}

	public IFuncionalidadExterna getVerificacionMuestra() {
		return verificacionMuestra;
	}

	public void nuevaMuestra(ZonaDeCobertura zona, Muestra muestra) {
		this.getNuevaMuestra().nuevoEvento(this, zona, muestra);

	}

	public void nuevaVerificacion(ZonaDeCobertura zonaDeCobertura, Muestra muestra) {
		this.getVerificacionMuestra().nuevoEvento(this, zonaDeCobertura, muestra);
	}

	public void registrarseAZonaDeCobertura(ZonaDeCobertura zonaDeCobertura) {
		zonaDeCobertura.registrarseAZonaDeCobertura(this);

	}

	public void dejarZonaDeCobertura(ZonaDeCobertura zonaDeCobertura) {
		zonaDeCobertura.dejarZonaDeCobertura(this);

	}
}
