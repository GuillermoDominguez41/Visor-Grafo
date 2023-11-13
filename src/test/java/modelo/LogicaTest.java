package modelo;

import coordinador.Coordinador;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class LogicaTest {
    private Logica logica;

    @Before
    public void configuracion() {
        logica = new Logica(new Coordinador());
    }

    @Test
    public void grafoNoNulo() {
        assertNotNull(logica);
    }


}
