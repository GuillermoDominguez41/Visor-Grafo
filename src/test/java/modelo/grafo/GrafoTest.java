package modelo.grafo;

import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

public class GrafoTest {

    private Grafo grafo;

    @Before
    public void configuracion() {
        grafo = new Grafo();
    }

    @Test
    public void grafoNoNulo() {
        assertNotNull(grafo);
    }

    @Test
    public void agregarVertice() {
        grafo.agregarVertice(0, 0, "Vertice1");
        grafo.agregarVertice(0, 0, "Vertice2");
        grafo.agregarVertice(0, 0, "Vertice3");

        int cantVertices = grafo.obtenerClaveVertices().size();
        assertEquals(3, cantVertices);
    }

    @Test
    public void agregarVerticeNulo() {
        grafo.agregarVertice(null);
        int cantVertices = grafo.obtenerClaveVertices().size();

        assertEquals(0, cantVertices);
    }


    @Test
    public void obtenerVertice() {
        Vertice vertice1 = new Vertice(1, 0, 0, "Vertice1");
        grafo.agregarVertice(vertice1);

        assertEquals(vertice1, grafo.obtenerVertice(vertice1.id()));
    }

    @Test
    public void agregarArista() {
        Vertice vertice1 = grafo.agregarVertice(1, 2, "Vertice1");
        Vertice vertice2 = grafo.agregarVertice(3, 4, "Vertice2");

        grafo.agregarArista(vertice1.id(), vertice2.id());

        Set<Integer> vecinosVertice1 = grafo.obtenerVecinos(vertice1.id());
        Set<Integer> vecinosVertice2 = grafo.obtenerVecinos(vertice2.id());

        assertTrue(vecinosVertice1.contains(vertice2.id()));
        assertTrue(vecinosVertice2.contains(vertice1.id()));
    }

}