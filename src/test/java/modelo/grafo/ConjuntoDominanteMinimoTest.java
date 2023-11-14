package modelo.grafo;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class ConjuntoDominanteMinimoTest {
   private Grafo grafo;
   private Vertice vertice;
    @Before
    public void configuracion() {
        grafo = new Grafo();
        vertice = new Vertice(1, 10, 20, "Vertice");
    }

    @Test
    public void conjuntoEsperadoOK() {
        grafo.agregarVertice(vertice);
        Set<Integer> conjuntoDominanteMinimo = grafo.obtenerConjuntoDominanteMinimo();

        Set<Integer> conjuntoEsperado = new HashSet<>();
        conjuntoEsperado.add(vertice.id());

        assertEquals(conjuntoEsperado, conjuntoDominanteMinimo);
    }
}
