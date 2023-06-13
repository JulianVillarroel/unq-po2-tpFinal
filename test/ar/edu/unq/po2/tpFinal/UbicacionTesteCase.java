package ar.edu.unq.po2.tpFinal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.tpFinal.Ubicaciones.Ubicacion;
import ar.edu.unq.po2.tpFinal.Ubicaciones.ZonaDeCobertura;

class UbicacionTesteCase {

	 Ubicacion ubicacion1, ubicacion2, ubicacion3, ubicacion4, ubicacion5;
	    List<Ubicacion> ubicaciones, resultado;
	    Muestra muestra, otraMuestra, otraMuestraMuyLejos;
	    ZonaDeCobertura zona, zona2;

	    @BeforeEach
	    void setUp() throws Exception {
	        ubicacion2 = new Ubicacion(40d, 50d);
	        zona2 = new ZonaDeCobertura("Wilde", ubicacion2, 30);
	        ubicacion1 = new Ubicacion(50d, 40d);
	        zona = new ZonaDeCobertura("Bernal", ubicacion1, 30);

			muestra = mock(Muestra.class);
	        otraMuestra = mock(Muestra.class);
	        otraMuestraMuyLejos = mock(Muestra.class);

	        ubicacion3 = new Ubicacion(10d, 20d);
	        ubicacion5 = new Ubicacion(60d, 60d);
	        when(otraMuestra.getUbicacion()).thenReturn(ubicacion1); // ubicacion 50 40
	        when(muestra.getUbicacion()).thenReturn(ubicacion2); // ubicacion 40 50
	        when(otraMuestraMuyLejos.getUbicacion()).thenReturn(ubicacion5);
	        zona.agregarMuestra(otraMuestra);
	        zona.agregarMuestra(otraMuestraMuyLejos);

	        ubicacion4 = new Ubicacion();
	        ubicaciones = new ArrayList<Ubicacion>();
	        ubicaciones.add(ubicacion2);
	        ubicaciones.add(ubicacion3);
	        resultado = new ArrayList<Ubicacion>();
	        resultado.add(ubicacion2);

	    }

	    @Test
	    void testUbicacionEnCero() {
	        assertEquals(0d, ubicacion4.getLatitud());
	        assertEquals(0d, ubicacion4.getLongitud());
	    }

	    @Test
	    void testUbicacionLatitudYLongitud() {
	        assertEquals(50d, ubicacion1.getLatitud());
	        assertEquals(40d, ubicacion1.getLongitud());
	    }

	    @Test
	    public void testDistanciaEntreDosUbicaciones() {
	        assertEquals(1359.2545257553352, ubicacion1.distanciaHasta(ubicacion2)); // el resultado 1359 son km's
	    }

	    @Test
	    public void testUbicacionesA1500km() {
	        assertEquals(resultado, ubicacion1.getUbicacionesAMenosDe(1500d, ubicaciones));
	    }



}
