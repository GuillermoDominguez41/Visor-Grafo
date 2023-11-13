package modelo.grafo;

import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

public class ConjuntoDominanteMinimoTest {
    private Set<Integer> cdm;
    @Before
    public void configuracion() {
        Grafo grafo = new Grafo();
        cdm = grafo.obtenerConjuntoDominanteMinimo();
    }

    @Test
    public void grafoNoNulo() {
        assertNull(cdm);
    }
}
