package vista.visorGrafo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

import vista.VentanaPrincipal;

public class FormaArista extends JComponent {
	private final Color colorDeLinea;
    private final int origenPosX;
    private final int origenPosY;
    private final int destinoPosX;
    private final int destinoPosY;
    private final int verticeSize;
    
    public FormaArista(FormaVertice vertice, FormaVertice vecino) {
    	this.colorDeLinea = Color.decode("#7094FF");
    	this.origenPosX = vertice.posX();
    	this.origenPosY = vertice.posY();
    	this.destinoPosX = vecino.posX();
    	this.destinoPosY = vecino.posY();
    	this.verticeSize = FormaVertice.tananio();
    	
        setName("Arista");
        setBounds(0, 0, VentanaPrincipal.altoVentana(), VentanaPrincipal.anchoVentana());
        setOpaque(false);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setColor(colorDeLinea);
		g2d.setStroke(new BasicStroke(4));

		int[] inicio = new int[2];
		int[] fin = new int[2];
		encuentraXY(inicio, fin);

		g2d.drawLine(inicio[0], inicio[1], fin[0], fin[1]);
		g2d.dispose();
	}
	
    private void encuentraXY(int[] inicio, int[] fin) {
        inicio[0] = origenPosX;
        inicio[1] = origenPosY;
        fin[0] = destinoPosX;
        fin[1] = destinoPosY;

        int radio = verticeSize / 2;
        int deltaX = fin[0] - inicio[0];
        int deltaY = fin[1] - inicio[1];
        double distancia = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
        int offsetX = (int) (radio * deltaX / distancia);
        int offsetY = (int) (radio * deltaY / distancia);
        inicio[0] += offsetX;
        inicio[1] += offsetY;
        fin[0] -= offsetX;
        fin[1] -= offsetY;
    }
    
    @Override
    public boolean equals(Object o) { return false;}
}