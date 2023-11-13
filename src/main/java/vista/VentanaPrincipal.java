package vista;

import java.awt.Color;
import java.util.Objects;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JPanel;
import javax.swing.JTextField;

import coordinador.Coordinador;
import vista.visorGrafo.VisorGrafo;

import java.awt.GridLayout;

public class VentanaPrincipal extends JFrame {

    private final Coordinador coordinador;
    private final LineBorder lineBorder;
    private final Color colorTextFont;
    private final VisorGrafo visorGrafo;
    private JTextField txt_avisos;
    private static final int altoVentana = 720;
    private static final int anchoVentana = 1280;

    public VentanaPrincipal(Coordinador coordinador) {
        this.visorGrafo = new VisorGrafo(coordinador);
        this.coordinador = coordinador;
        this.colorTextFont = new Color(111, 145, 173);
        this.lineBorder = new LineBorder(new Color(39, 57, 88), 1, true);

        setSize(1280, 720);
        setTitle("Conjunto Dominante Minimo");
/*        setIconImage(new ImageIcon(Objects.requireNonNull(
                VentanaPrincipal.class.getClassLoader().getResource("img/appIcon.png"))).getImage());*/
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        crearBarraHerramientas();
        agregarVisorGrafo();
        crearPanelAvisos();
    }

    private void agregarVisorGrafo() {
        JPanel panelVisorGrafo = new JPanel();
        panelVisorGrafo.setBorder(
                new TitledBorder(lineBorder, "Visor Grafo", TitledBorder.LEFT, TitledBorder.TOP, null, colorTextFont));
        panelVisorGrafo.setBounds(10, 93, 1246, 522);
        panelVisorGrafo.add(visorGrafo.obtenerPanelPrincipal());
        panelVisorGrafo.setLayout(new GridLayout(1, 0, 0, 0));
        getContentPane().add(panelVisorGrafo);
    }

    private void crearBarraHerramientas() {
        JToolBar toolBar = new JToolBar();
        toolBar.setBorder(new TitledBorder(lineBorder, "Barra de herramientas", TitledBorder.LEFT, TitledBorder.TOP,
                null, colorTextFont));
        toolBar.setBounds(10, 10, 1246, 72);
        getContentPane().add(toolBar);
        toolBar.addSeparator();

/*        JButton btn_GuardarCambios = new JButton(new ImageIcon(VARIABLES.iconoGuardarCambios));*/
        JButton btn_GuardarCambios = new JButton();
        btn_GuardarCambios.setToolTipText("Guardar Cambios");
        btn_GuardarCambios.addActionListener((evento) -> coordinador.guardarVertices());
        toolBar.add(btn_GuardarCambios);
        toolBar.addSeparator();

/*        JButton btn_ActualizarPantalla = new JButton(new ImageIcon(VARIABLES.iconoDeshacerCambios));*/
        JButton btn_ActualizarPantalla = new JButton();
        btn_ActualizarPantalla.setToolTipText("Actualizar Pantalla");
        btn_ActualizarPantalla.addActionListener((evento) -> actualizarVerticesDesdeBase());
        toolBar.add(btn_ActualizarPantalla);
        toolBar.addSeparator();

/*        JButton btn_CDM = new JButton("Conjunto Dominante Minimo", new ImageIcon(VARIABLES.iconoConjuntoDominanteMinimo));*/
        JButton btn_CDM = new JButton();
        btn_CDM.addActionListener((evento) -> {
            Set<Integer> CDM = coordinador.obtenerConjuntoDominanteMinimo();
            mostrarAviso("Conjunto Dominante Minimo: " + CDM.toString());
            visorGrafo.resaltarVertices(CDM);
        });
        toolBar.add(btn_CDM);
        toolBar.addSeparator();
    }

    private void crearPanelAvisos() {
        JPanel panelAvisos = new JPanel(new GridLayout(1, 0, 0, 0));
        panelAvisos.setBorder(
                new TitledBorder(lineBorder, "Avisos", TitledBorder.LEFT, TitledBorder.TOP, null, colorTextFont));
        panelAvisos.setBounds(10, 625, 1246, 48);
        getContentPane().add(panelAvisos);

        txt_avisos = new JTextField(
                "Puede agregar un nuevo vertice pulsando sobre alguna seccion vacia del visor grafo o interactuar con los vertices precargados pulsando sobre los mismos.");
        txt_avisos.setForeground(new Color(255, 255, 255));
        txt_avisos.setEditable(false);
        txt_avisos.setHorizontalAlignment(SwingConstants.CENTER);
        panelAvisos.add(txt_avisos);
    }

    public void mostrarVentana() {
        setVisible(true);
    }

    public void actualizarVerticesDesdeBase() {
        visorGrafo.actualizarDesdeBase();
    }

    public void mostrarAviso(String aviso) {
        txt_avisos.setText(aviso);
    }

    public static int anchoVentana() {
        return anchoVentana;
    }

    public static int altoVentana() {
        return altoVentana;
    }

}