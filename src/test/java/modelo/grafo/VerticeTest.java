package modelo.grafo;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class VerticeTest {
    private Vertice vertice1, vertice2;

    @Before
    public void configuracion() {
        vertice1 = new Vertice(1, 10, 20, "Vertice1");
        vertice2 = new Vertice(2, 20, 10, "Vertice2");
    }

    @Test
    public void noIguales(){assertNotEquals(vertice1, vertice2);}

    @Test
    public void iguales(){assertEquals(vertice1, vertice1);}

    @Test
    public void getId(){assertEquals(1, vertice1.id());}

    @Test
    public void getNombre(){assertEquals("Vertice1", vertice1.nombre());}

    @Test
    public void getPosX(){assertEquals(10, vertice1.posX());}

    @Test
    public void getPosY(){assertEquals(20, vertice1.posY());}

    @Test
    public void agregarVecino(){
        vertice1.agregarVecino(vertice2.id());
        assertTrue(vertice1.vecinos().contains(vertice2.id()));
    }

    @Test
    public void actualizarNombre(){
        String nuevoNombre = "Vertice";
        vertice1.actualizarNombre(nuevoNombre);
        assertEquals(nuevoNombre, vertice1.nombre());
    }

    @Test
    public void actualizarPosicion(){
        vertice1.actualizarPosicion(20, 40);
        assertEquals(20, vertice1.posX());
        assertEquals(40, vertice1.posY());
    }
}
