package main.java.modelo.grafo;

import java.util.HashSet;
import java.util.Set;

import com.google.gson.annotations.SerializedName;

public class Vertice {

    @SerializedName("Id")
    private final int id;
    @SerializedName("Nombre")
    private String nombre;
    @SerializedName("Vecinos")
    private final Set<Integer> vecinos;
    @SerializedName("Posicion X")
    private int posX;
    @SerializedName("Posicion Y")
    private int posY;

    public Vertice(int id, int posicionX, int posicionY, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.posX = posicionX;
        this.posY = posicionY;
        this.vecinos = new HashSet<>();
    }

    public int id() {
        return this.id;
    }

    public String nombre() {
        return this.nombre;
    }

    public int posX() {
        return this.posX;
    }

    public int posY() {
        return this.posY;
    }

    public Set<Integer> vecinos() {
        return this.vecinos;
    }

    public void agregarVecino(int verticeVecino) {
        this.vecinos.add(verticeVecino);
    }

    public void actualizarPosicion(int posicionX, int posicionY) {
        this.posX = posicionX;
        this.posY = posicionY;
    }

    @Override
    public String toString() {
        return "{Id: " + id + ", Nombre: " + nombre + " - Vecinos: " + vecinos + "}";
    }

    public void actualizarNombre(String nuevoNombre) {
        this.nombre = nuevoNombre;
    }

}