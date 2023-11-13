package main.java.modelo.grafo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Grafo {

    private final Map<Integer, Vertice> vertices = new HashMap<>();

    public Vertice agregarVertice(int posicionX, int posicionY, String nombre) {
        Vertice nuevoVertice = new Vertice(generarID(), posicionX, posicionY, nombre);
        vertices.put(nuevoVertice.id(), nuevoVertice);
        return nuevoVertice;
    }

    public void agregarVertice(Vertice nuevoVertice) {
        vertices.put(nuevoVertice.id(), nuevoVertice);
    }

    public int generarID() {
        return vertices.size()+1;
    }

    public void agregarArista(int idVerticeOrigen, int idVerticeDestino) {
        if(existeVertice(idVerticeOrigen) || existeVertice(idVerticeDestino)) {
            vertices.get(idVerticeOrigen).agregarVecino(idVerticeDestino);
            vertices.get(idVerticeDestino).agregarVecino(idVerticeOrigen);
        }
    }

    private boolean existeVertice(int idVertice) {
        return vertices.containsKey(idVertice);
    }

    public Set<Integer> obtenerClaveVertices() {
        return vertices.keySet();
    }

    public ArrayList<Vertice> obtenerListaVertices(){
        return new ArrayList<>(vertices.values());
    }

    public Set<Integer> obtenerVecinos(int idVerticeConsulta) {
        return vertices.get(idVerticeConsulta).vecinos();
    }

    public Set<Integer> obtenerConjuntoDominanteMinimo() {
        return ConjuntoDominanteMinimo.buscarConjuntoDominanteMinimo(this);
    }

    public void actualizarPosicionVertice(int id, int posicionX, int posicionY) {
        if(vertices.containsKey(id)) {
            Vertice verticePorModificar = vertices.get(id);
            verticePorModificar.actualizarPosicion(posicionX, posicionY);
            vertices.put(id, verticePorModificar);
        }
    }

    public Vertice obtenerVertice(int id) {
        return vertices.get(id);
    }

    public void eliminarVertice(int id) {
        removerVerticeVecino(id);
        vertices.remove(id);
    }

    private void removerVerticeVecino(int idEliminar) {
        Vertice verticeEliminar = vertices.get(idEliminar);

        verticeEliminar.vecinos().forEach( idVecino ->{
            Vertice verticeVecino = vertices.get(idVecino);
            verticeVecino.vecinos().remove(idEliminar);
            vertices.put(idVecino, verticeVecino);
        });
    }

    public void actualizarVertice(Vertice vertice) {
        vertices.put(vertice.id(), vertice);
    }

}