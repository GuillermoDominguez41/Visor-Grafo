
import com.formdev.flatlaf.intellijthemes.FlatGradiantoDeepOceanIJTheme;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.util.logging.Level;
import java.util.logging.Logger;

import bd.AdmBaseDatos;
import coordinador.Coordinador;
import modelo.Logica;
import vista.VentanaPrincipal;

public class Principal {

    private static final Logger logger = Logger.getLogger(Principal.class.getName());

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatGradiantoDeepOceanIJTheme());
            Principal miPrincipal = new Principal();
            miPrincipal.iniciar();
        } catch (UnsupportedLookAndFeelException e) {
            logger.log(Level.SEVERE, "Error al importar libreria de temas", e);
        }
    }

    private void iniciar() {
        /* Se instancian las clases y se relacionan con el coordinador. */
        Coordinador coordinador = new Coordinador();

        AdmBaseDatos admBD = new AdmBaseDatos();
        coordinador.setAdministradorBD(admBD);

        Logica logica = new Logica(coordinador);
        coordinador.setLogica(logica);

        VentanaPrincipal vPrincipal = new VentanaPrincipal(coordinador);
        coordinador.setVentanaPrincipal(vPrincipal);

        /* Iniciamos la interfaz principal de la app */
        vPrincipal.mostrarVentana();
    }

}
