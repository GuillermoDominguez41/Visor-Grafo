package main.java.modelo;

import java.util.ArrayList;
import java.util.Set;

import main.java.coordinador.Coordinador;
import main.java.modelo.grafo.Grafo;
import main.java.modelo.grafo.Vertice;

public class Logica {

    private final Coordinador coordinador;
    private final Grafo grafo;

    public Logica(Coordinador coordinador) {
        this.coordinador = coordinador;
        this.grafo = new Grafo();
        actualizarGrafoConBD();
    }

    private void actualizarGrafoConBD() {
        coordinador.obtenerVerticesDesdeBD().forEach(grafo::agregarVertice);
    }

    public Set<Integer> obtenerConjuntoDominanteMinimo() {
        return grafo.obtenerConjuntoDominanteMinimo();
    }

    public Vertice agregarVertice(int x, int y, String nombre) {
        return grafo.agregarVertice(x, y, nombre);
    }

    public ArrayList<Vertice> obtenerVertices() {
        return grafo.obtenerListaVertices();
    }

    public ArrayList<Integer> obtenerIdVertices(){
        return new ArrayList<>(grafo.obtenerClaveVertices());
    }

    public void actualizarPosicionVertice(int id, int posicionX, int posicionY) {
        grafo.actualizarPosicionVertice(id, posicionX, posicionY);
    }

    public Vertice obtenerVertice(int id) {
        return grafo.obtenerVertice(id);
    }

    public void eliminarVertice(int id) {
        grafo.eliminarVertice(id);
    }

    public void generarArista(int idVerticeOrigen, int idVerticeDestino) {
        grafo.agregarArista(idVerticeOrigen, idVerticeDestino);
    }

    public void actualizarVertice(Vertice vertice) {
        grafo.actualizarVertice(vertice);
    }

}
