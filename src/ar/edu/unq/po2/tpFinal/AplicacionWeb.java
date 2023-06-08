package ar.edu.unq.po2.tpFinal;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ar.edu.unq.po2.tpFinal.EstadoDeUsuario.Usuario;
import ar.edu.unq.po2.tpFinal.Ubicaciones.ZonaDeCobertura;

public class AplicacionWeb {

	private List<Muestra> muestrasRegistradas;
	private Set<Usuario> usuariosRegistrados;
	private Set<ZonaDeCobertura> zonasDeCobertura;

	public AplicacionWeb() {
		this.muestrasRegistradas = new ArrayList<Muestra>();
		this.usuariosRegistrados = new HashSet<Usuario>();
		this.zonasDeCobertura = new HashSet<ZonaDeCobertura>();
	}

	public List<Muestra> getMuestrasRegistradas() {
		return muestrasRegistradas;
	}

	public Set<Usuario> getUsuariosRegistrados() {
		return usuariosRegistrados;
	}

	public Set<ZonaDeCobertura> getZonasDeCobertura() {
		return zonasDeCobertura;
	}

	public void registrarMuestra(Muestra muestra) {
		muestrasRegistradas.add(muestra);
		this.avisarAZonasNuevaMuestra(muestra);
	}

	private void avisarAZonasNuevaMuestra(Muestra muestra) {
		for (ZonaDeCobertura zona : zonasDeCobertura) {
			zona.agregarMuestra(muestra);
		}
	}

	public void registrarUsuario(Usuario usuario) {
		usuariosRegistrados.add(usuario);

	}

	public void agregarZonaDeCobertura(ZonaDeCobertura zonaDeCobertura) {
		this.zonasDeCobertura.add(zonaDeCobertura);

	}

	public List<Muestra> muestrasCercanas(ZonaDeCobertura zona, Muestra muestra, Double kilometros) {
		return zona.muestrasCercanas(muestra, kilometros);
	}

	//public List<Muestra> filtrarMuestras(IFiltroBusquedaMuestra filtro) {
	//	return filtro.buscarMuestras(muestrasRegistradas);                           acomodar idea
	//}

	public Set<ZonaDeCobertura> zonasQueSeSolapanCon(ZonaDeCobertura zonaDeCobertura) {
		return zonaDeCobertura.zonasQueSolapan(this.listaDeZonasSin(zonaDeCobertura));

	}

	private Set<ZonaDeCobertura> listaDeZonasSin(ZonaDeCobertura zonaARemover) {
		Set<ZonaDeCobertura> listaNueva = new HashSet<ZonaDeCobertura>();
		listaNueva.addAll(getZonasDeCobertura());
		listaNueva.stream().filter(zona -> !zona.equals(zonaARemover));
		return listaNueva;
	}

	//ublic void actualizarCategoriaDeUsuariosALaFecha() {
	//	for (Usuario usuario : this.usuariosRegistrados) {   acomodar idea
	//		usuario.actualizarCategoria();
	//	}
	//}

}

