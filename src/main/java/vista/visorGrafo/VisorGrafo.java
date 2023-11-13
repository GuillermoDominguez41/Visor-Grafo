package main.java.vista.visorGrafo;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import main.java.coordinador.Coordinador;
import main.java.modelo.grafo.Vertice;

public class VisorGrafo {

	private final Coordinador coordinador;
	private final JPanel panelPrincipal;
	private final Color fondoPanel;
	private final Map<Integer, FormaVertice> vertices;

	public VisorGrafo(Coordinador coordinador) {
		this.coordinador = coordinador;
		this.panelPrincipal = new JPanel();
		this.fondoPanel = new Color(41, 58, 86);
		this.vertices = new HashMap<>();
		
		crearPanelPrincipal();
		actualizarDesdeBase();
	}

	private void crearPanelPrincipal() {
		panelPrincipal.setLayout(null);
		panelPrincipal.setBackground(fondoPanel);
		panelPrincipal.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				agregarVertice(e);
			}
		});
	}

	private void agregarVertice(MouseEvent e) {
		String nombre = solicitarEntradaUsuario();
		
		if (nombre != null &&  !nombre.isEmpty()) {
			Vertice vertice =  coordinador.agregarVertice(e.getX(), e.getY(), nombre);
			if(vertice != null) {
				agregarVertice(vertice);
			} else {
				JOptionPane.showMessageDialog(panelPrincipal, "No se pudo generar el vertice");
			}
		}
	}
	
	private String solicitarEntradaUsuario() {
		String input = "";
		input = (String) JOptionPane.showInputDialog(panelPrincipal, "Ingrese el nombre:", "Nombre del vértice", JOptionPane.PLAIN_MESSAGE, null, null, input);
		return input;
	}

	public void actualizarDesdeBase() {
		ArrayList<Vertice> listaVertice = coordinador.obtenerVerticesDesdeGrafo();
		
		eliminarTodoslosElementos();
		actualizarVerticesDesdeBD(listaVertice);
		actualizarAristas();
	}
	
	private void eliminarTodoslosElementos() {
		vertices.clear();
		panelPrincipal.removeAll();
	}

	public void actualizarVerticesDesdeBD(ArrayList<Vertice> listaVertice) {
		listaVertice.forEach(this::agregarVertice);
	}
	
	public void actualizarAristas() {
		vertices.values().forEach( vertice -> vertice.vecinos().forEach(idVecino -> {
			FormaVertice vecino = vertices.get(idVecino);
			agregarArista(vertice, vecino);
		}));
	}
	
	private void agregarArista(FormaVertice vertice, FormaVertice vecino) {
		FormaArista arista = new FormaArista(vertice, vecino);
		panelPrincipal.add(arista);
		panelPrincipal.repaint();
	}

	private void agregarVertice(Vertice vertice) {
		FormaVertice formaVertice = new FormaVertice(
				coordinador,
				vertice.id(),
				vertice.posX(),
				vertice.posY(),
				vertice.nombre(),
				vertice.vecinos()
		);
		
		vertices.put(formaVertice.id(), formaVertice);
		
		panelPrincipal.add(formaVertice);
		panelPrincipal.setComponentZOrder(formaVertice, 0);
		panelPrincipal.revalidate();
		panelPrincipal.repaint();
	}

	public JPanel obtenerPanelPrincipal() {
		return panelPrincipal;
	}

	public void resaltarVertices(Set<Integer> conjuntoDominanteMinimo) {
		conjuntoDominanteMinimo.forEach( id -> vertices.get(id).cambiarColorVertice());
		panelPrincipal.repaint();
	}
	
}
