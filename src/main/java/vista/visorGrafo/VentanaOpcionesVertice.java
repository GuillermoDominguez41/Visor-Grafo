package main.java.vista.visorGrafo;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.GridLayout;

import main.java.coordinador.Coordinador;
import main.java.modelo.grafo.Vertice;
import main.resources.VARIABLES;

import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JTextField;

public class VentanaOpcionesVertice extends JFrame {

	private final Coordinador coordinador;
	private final Vertice vertice;
	private JTextField txt_Nombre;

	public VentanaOpcionesVertice(Coordinador coordinador, int id) {
		this.coordinador = coordinador;
		this.vertice = this.coordinador.obtenerVertice(id);

		setSize(400, 300);
		setResizable(false);
		setLocationRelativeTo(null);
		setUndecorated(true);
		getContentPane().setLayout(new GridLayout(1, 0, 0, 0));

		crearPanelPrincipal();
	}
	
	private void crearPanelPrincipal() {
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new LineBorder(new Color(239, 239, 239), 2));
		panelPrincipal.setLayout(null);
		getContentPane().add(panelPrincipal);
		
		JButton btn_Cerrar = new JButton(new ImageIcon(VARIABLES.iconoCerrar));
		btn_Cerrar.setToolTipText("Cerrar ventana");
		btn_Cerrar.setBackground(null);
		btn_Cerrar.setBorder(null);
		btn_Cerrar.setBounds(367, 11, 23, 21);
		btn_Cerrar.addActionListener((evento) -> cerrarVentana());
		panelPrincipal.add(btn_Cerrar);
		
		JLabel lbl_Titulo = new JLabel("Editar Vertice");
		lbl_Titulo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_Titulo.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_Titulo.setBounds(10, 11, 334, 21);
		panelPrincipal.add(lbl_Titulo);
		
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setBounds(10, 40, 380, 207);
		tabbedPane.addTab("Detalle", panelDetalle());
		tabbedPane.addTab("Actualizar", panelActualizar());
		tabbedPane.addTab("Generar Arista", panelGenerarArista());
		tabbedPane.addTab("Eliminar", panelEliminar());

		panelPrincipal.add(tabbedPane);
	}
	
	private JPanel panelDetalle() {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		JLabel lbl_Id = new JLabel("Id:");
		lbl_Id.setLocation(10, 10);
		panel.add(lbl_Id);
		
		JTextField txt_Id = new JTextField( String.valueOf(vertice.id()) );
		txt_Id.setLocation(80, 10);
		panel.add(txt_Id);
		
		JLabel lbl_Nombre = new JLabel("Nombre:");
		lbl_Nombre.setLocation(10, 40);
		panel.add(lbl_Nombre);
		
		JTextField txt_Nombre = new JTextField( vertice.nombre() );
		txt_Nombre.setLocation(80, 40);
		panel.add(txt_Nombre);
		
		JLabel lbl_Vecinos = new JLabel("Vecinos:");
		lbl_Vecinos.setLocation(10, 70);
		panel.add(lbl_Vecinos);
		
		JTextField txt_Vecinos = new JTextField( vertice.vecinos().toString() );
		txt_Vecinos.setLocation(80, 70);
		panel.add(txt_Vecinos);
		
		JLabel lbl_PosicionX = new JLabel("Posicion X:");
		lbl_PosicionX.setLocation(10, 100);
		panel.add(lbl_PosicionX);
		
		JTextField txt_PosicionX = new JTextField( String.valueOf(vertice.posX()) );
		txt_PosicionX.setLocation(80, 100);
		panel.add(txt_PosicionX);
		
		JLabel lbl_PosicionY = new JLabel("Posicion Y:");
		lbl_PosicionY.setLocation(10, 130);
		panel.add(lbl_PosicionY);
		
		JTextField txt_PosicionY = new JTextField( String.valueOf(vertice.posY()) );
		txt_PosicionY.setLocation(80, 130);
		panel.add(txt_PosicionY);
		
		// Asignamos los atributos generales/compartidos a los componentes
		for(Component c : panel.getComponents()) {
			if(c instanceof JLabel ) {
				((JLabel) c).setHorizontalAlignment(SwingConstants.RIGHT);
				c.setFont(new Font("Tahoma", Font.BOLD, 12));
				c.setSize(71, 25);
			}
			if(c instanceof JTextField) {
				((JTextField) c).setEditable(false);
				c.setSize(286, 25);
			}
		}	
		
		return panel;
	}

	private JPanel panelActualizar() {

		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		JLabel lbl_Nombre = new JLabel("Nombre:");
		lbl_Nombre.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_Nombre.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbl_Nombre.setBounds(10, 6, 72, 25);
		panel.add(lbl_Nombre);
		
		txt_Nombre = new JTextField( vertice.nombre() );
		txt_Nombre.setColumns(10);
		txt_Nombre.setBounds(82, 5, 283, 25);
		panel.add(txt_Nombre);
		
		JButton btn_GuardarCambios = new JButton("Guardar Cambios");
		btn_GuardarCambios.setBounds(116, 40, 127, 30);
		btn_GuardarCambios.addActionListener((evento) -> {
			vertice.actualizarNombre(txt_Nombre.getText());
			coordinador.actualizarVertice(vertice);
		});
		panel.add(btn_GuardarCambios);
		
		return panel;
	}

	private JPanel panelGenerarArista() {
		JPanel panel = new JPanel();
		panel.setLayout(null);
			
		JComboBox<Integer> listaVertices = new JComboBox<>();
		listaVertices.setFont(new Font("Tahoma", Font.PLAIN, 11));
		listaVertices.setBounds(177, 9, 130, 21);
		panel.add(listaVertices);
		
		JLabel lbl_ListaVertice = new JLabel("Vertice a conectar:");
		lbl_ListaVertice.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_ListaVertice.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_ListaVertice.setBounds(20, 13, 147, 13);
		panel.add(lbl_ListaVertice);
		
		JButton btn_GenerarArista = new JButton("Generar Arista");
		btn_GenerarArista.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btn_GenerarArista.setBounds(112, 41, 147, 28);
		btn_GenerarArista.addActionListener((evento) -> {
			if(listaVertices.getSelectedItem() != null){
				int idSeleccionado = (int) listaVertices.getSelectedItem();
				coordinador.generarArista( vertice.id(), idSeleccionado );
				coordinador.actualizarVerticesEnVentanaPrincipal();
				cerrarVentana();
			}
		});
		
		panel.add(btn_GenerarArista);
		
		coordinador.obtenerIdVertices().forEach( id ->{
			if(id != vertice.id())
				listaVertices.addItem(id);
		});
		
		return panel;
	}

	private JPanel panelEliminar() {
		JPanel panel = new JPanel();

		JButton btn_Eliminar = new JButton("Eliminar vertice");
		btn_Eliminar.setBounds(101, 10, 168, 26);
		btn_Eliminar.addActionListener((evento) -> {
			coordinador.eliminarVertice( vertice.id() );
			coordinador.mostrarAvisoEnVentanaPrincipal("Vertice " + vertice.id() + " eliminado");
			coordinador.actualizarVerticesEnVentanaPrincipal();
			cerrarVentana();
		});
		panel.setLayout(null);
		panel.add(btn_Eliminar);

		return panel;
	}

	public void mostrarVentana() {
		setVisible(true);
	}

	public void cerrarVentana() {
		setVisible(false);
	}
}