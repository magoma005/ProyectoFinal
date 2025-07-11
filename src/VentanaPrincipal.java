import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {
    public VentanaPrincipal() {
        setTitle("Sistema de Gestión Clínica Veterinaria");
        setSize(800, 600);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        //Se le agrega el JDesktopPane en el centro.
        JDesktopPane escritorio = new JDesktopPane();
        add(escritorio, BorderLayout.CENTER);
        // Crear el JMenuBar
        JMenuBar barraMenu = new JMenuBar();

        // Creacion Menu Archivo
        JMenu menuArchivo = new JMenu("Archivo");

        // Opciones del menú
        JMenuItem itemNuevoRegistro = new JMenuItem("Nuevo registro");
        JMenuItem itemSalir = new JMenuItem("Salir");

        // Acción salir
        itemSalir.addActionListener(e -> System.exit(0));

        // Agregar opciones a Archivo
        menuArchivo.add(itemNuevoRegistro);
        menuArchivo.addSeparator();
        menuArchivo.add(itemSalir);

    }
}
