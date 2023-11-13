package main.java.coordinador;

import java.util.ArrayList;
import java.util.Set;

import main.java.bd.AdmBaseDatos;
import main.java.modelo.Logica;
import main.java.modelo.grafo.Vertice;
import main.java.vista.VentanaPrincipal;

public class Coordinador {

    private Logica logica;
    private AdmBaseDatos admBD;
    private VentanaPrincipal vPrincipal;

    public void setLogica(Logica logica) {
        this.logica = logica;
    }

    public void setAdministradorBD(AdmBaseDatos administradorBaseDatos) {
        this.admBD = administradorBaseDatos;
    }

    public void setVentanaPrincipal(VentanaPrincipal ventanaPrincipal) {
        this.vPrincipal = ventanaPrincipal;
    }

    public Set<Integer> obtenerConjuntoDominanteMinimo(){
        return logica.obtenerConjuntoDominanteMinimo();
    }

    public ArrayList<Vertice> obtenerVerticesDesdeBD() {
        return admBD.obtenerVertices();
    }

    public ArrayList<Vertice> obtenerVerticesDesdeGrafo(){
        return logica.obtenerVertices();
    }

    public ArrayList<Integer> obtenerIdVertices(){
        return logica.obtenerIdVertices();
    }

    public Vertice agregarVertice(int x, int y, String nombre) {
        return logica.agregarVertice(x, y, nombre);
    }

    public void actualizarVertice(Vertice vertice) {
        logica.actualizarVertice(vertice);
    }

    public void guardarVertices() {
        admBD.guardarVertices(logica.obtenerVertices());
    }

    public void actualizarPosicionVertice(int id, int posicionX, int posicionY) {
        logica.actualizarPosicionVertice(id, posicionX, posicionY);
    }

    public Vertice obtenerVertice(int id) {
        return logica.obtenerVertice(id);
    }

    public void eliminarVertice(int id) {
        logica.eliminarVertice(id);
        actualizarVerticesEnVentanaPrincipal();
    }

    public void actualizarVerticesEnVentanaPrincipal() {
        vPrincipal.actualizarVerticesDesdeBase();
    }

    public void mostrarAvisoEnVentanaPrincipal(String aviso) {
        vPrincipal.mostrarAviso(aviso);
    }

    public void generarArista(int idVerticeOrigen, int idVerticeDestino) {
        logica.generarArista(idVerticeOrigen, idVerticeDestino);
    }
}
