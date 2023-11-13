package modelo.grafo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class ConjuntoDominanteMinimo {

    public static Set<Integer> buscarConjuntoDominanteMinimo(Grafo grafo) {
        Set<Integer> conjuntoDominante = new HashSet<>();
        List<Integer> vertices = new ArrayList<>(grafo.obtenerClaveVertices());

        while (!vertices.isEmpty()) {
            int mejorVertice = buscarMejorVertice(vertices, grafo);
            if (mejorVertice != -1) {
                conjuntoDominante.add(mejorVertice);
                eliminarVerticesAdyacentes(mejorVertice, vertices, grafo);
            }
        }

        return conjuntoDominante;
    }

    private static int buscarMejorVertice(List<Integer> verticesRestantes, Grafo grafo) {
        int mejorVertice = -1;
        int mejorResultado = -1;

        for (int vertice : verticesRestantes) {
            Set<Integer> vecinos = grafo.obtenerVecinos(vertice);
            vecinos.retainAll(verticesRestantes); // Sólo se tienen en cuenta los vecinos que aún no están en el conjunto dominante
            int resultado = vecinos.size();

            if (resultado > mejorResultado) {
                mejorResultado = resultado;
                mejorVertice = vertice;
            }
        }

        return mejorVertice;
    }

    private static void eliminarVerticesAdyacentes(int vertice, List<Integer> verticesRestantes, Grafo grafo) {
        Set<Integer> vecinos = grafo.obtenerVecinos(vertice);
        vecinos.retainAll(verticesRestantes);

        verticesRestantes.remove((Integer) vertice);
        verticesRestantes.removeAll(vecinos);
    }

}