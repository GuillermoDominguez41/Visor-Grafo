package modelo.grafo;

import bd.AdmBaseDatos;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.*;

public class ConjuntoDominanteMinimoTest {
   private Grafo grafo;
   private ArrayList<Vertice> vertices;

    @Before
    public void configuracion() {
        grafo = new Grafo();
        obtenerVerticesDesdeJson();
    }

    @Ignore
    public void obtenerVerticesDesdeJson(){
        Logger logger = Logger.getLogger(AdmBaseDatos.class.getName());
        Gson gson = new Gson();
        InputStream file = getClass().getClassLoader().getResourceAsStream("data/vertice.json");
        try (Reader reader = new InputStreamReader(Objects.requireNonNull(file))){
            Type tipoArraylist = new TypeToken<ArrayList<Vertice>>(){}.getType();
            vertices = gson.fromJson(reader, tipoArraylist);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "No se logro abrir archivo Json", e);
        }
    }

    @Test
    public void grafoVacio() {
        Set<Integer> conjuntoDominante = ConjuntoDominanteMinimo.buscarConjuntoDominanteMinimo(grafo);

        Set<Integer> conjuntoEsperado = new HashSet<>();
        assertEquals(conjuntoEsperado, conjuntoDominante);
    }

    @Test
    public void grafoPequenoConAristas() {
        grafo.agregarVertice(new Vertice(1, 0, 0, "Vertice1"));
        grafo.agregarVertice(new Vertice(2, 0, 0, "Vertice2"));

        grafo.agregarArista(1, 2);

        Set<Integer> conjuntoDominante = ConjuntoDominanteMinimo.buscarConjuntoDominanteMinimo(grafo);

        Set<Integer> conjuntoEsperado = new HashSet<>();
        conjuntoEsperado.add(1);

        assertEquals(conjuntoEsperado, conjuntoDominante);
    }

    @Test
    public void grafoPequenoSinAristas() {
        grafo.agregarVertice(new Vertice(1, 0, 0, "Vertice1"));
        grafo.agregarVertice(new Vertice(2, 0, 0, "Vertice2"));

        Set<Integer> conjuntoDominanteMinimo = grafo.obtenerConjuntoDominanteMinimo();

        Set<Integer> conjuntoEsperado = new HashSet<>();
        conjuntoEsperado.add(1);
        conjuntoEsperado.add(2);

        assertEquals(conjuntoEsperado, conjuntoDominanteMinimo);
    }

    @Test
    public void grafoCompleto() {
        vertices.forEach(vertice -> grafo.agregarVertice(vertice));
        Set<Integer> conjuntoDominanteMinimo = grafo.obtenerConjuntoDominanteMinimo();

        Set<Integer> conjuntoEsperado = new HashSet<>();
        conjuntoEsperado.add(2);
        conjuntoEsperado.add(4);

        assertEquals(conjuntoEsperado, conjuntoDominanteMinimo);
    }

}
