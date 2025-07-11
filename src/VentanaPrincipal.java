import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.util.ArrayList;

public class VentanaPrincipal extends JFrame {

    private JDesktopPane escritorio;
    private ArrayList<Mascota> listaPacientes = new ArrayList<>();

    public VentanaPrincipal() {
        //CONFIGURACIÓN DE LA VENTANA PRINCIPAL
        setTitle("PetControl - Sistema de Gestión Clínica Veterinaria");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        //Colores
        Color colorFondo = new Color(240, 248, 255);
        getContentPane().setBackground(colorFondo);

        //PANEL IZQUIERDO: JTree de servicios
        DefaultMutableTreeNode raiz = new DefaultMutableTreeNode("Servicios");
        raiz.add(new DefaultMutableTreeNode("Medicina general"));
        raiz.add(new DefaultMutableTreeNode("Cirugía"));
        raiz.add(new DefaultMutableTreeNode("Vacunación"));
        raiz.add(new DefaultMutableTreeNode("Peluquería"));
        raiz.add(new DefaultMutableTreeNode("Urgencias"));

        JTree arbolServicios = new JTree(raiz);
        JScrollPane scrollArbol = new JScrollPane(arbolServicios);
        add(scrollArbol, BorderLayout.WEST);

        //PANEL CENTRAL CON ESCRITORIO
        escritorio = new JDesktopPane();
        escritorio.setBackground(Color.WHITE);
        add(escritorio, BorderLayout.CENTER);

        //PIE DE PÁGINA CON INFORMACIÓN
        JLabel piePagina = new JLabel("© 2025 PetControl. Todos los derechos reservados.", SwingConstants.CENTER);
        piePagina.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        add(piePagina, BorderLayout.SOUTH);

        //BARRA DE MENÚ
        JMenuBar barraMenu = new JMenuBar();
        JMenu menuArchivo = new JMenu("Archivo");
        menuArchivo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JMenuItem itemNuevoRegistro = new JMenuItem("Nuevo registro");
        itemNuevoRegistro.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        JMenuItem itemSalir = new JMenuItem("Salir");
        itemSalir.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        // Acción salir
        itemSalir.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "👋 Gracias por usar PetControl. ¡Hasta pronto!");
            System.exit(0);
        });
        // Acción nuevo registro
        itemNuevoRegistro.addActionListener(e -> crearFormularioIngreso());
        menuArchivo.add(itemNuevoRegistro);
        menuArchivo.addSeparator();
        menuArchivo.add(itemSalir);
        JMenu menuVista = new JMenu("Vista");
        menuVista.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JMenuItem itemPacientes = new JMenuItem("Pacientes");
        itemPacientes.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        JMenuItem itemConsultas = new JMenuItem("Consultas");
        itemConsultas.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        // Acción pacientes
        itemPacientes.addActionListener(e -> mostrarTablaPacientes());
        menuVista.add(itemPacientes);
        menuVista.add(itemConsultas);
        barraMenu.add(menuArchivo);
        barraMenu.add(menuVista);
        setJMenuBar(barraMenu);
        setVisible(true);
    }

    // Creacion de un Splash Screen con su logo y carga
    public static void mostrarSplashScreen() {
        JWindow splash = new JWindow();
        // Panel con BorderLayout para texto arriba y logo abajo
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        // Texto de carga
        JLabel lblTexto = new JLabel("Cargando PetControl...", SwingConstants.CENTER);
        lblTexto.setFont(new Font("Segoe UI", Font.BOLD, 18));
        panel.add(lblTexto, BorderLayout.NORTH);
        ImageIcon icono = new ImageIcon(VentanaPrincipal.class.getResource("/imagenes/logo.jpg"));
        JLabel lblImagen = new JLabel(icono, SwingConstants.CENTER);
        panel.add(lblImagen, BorderLayout.CENTER);
        splash.getContentPane().add(panel);
        splash.setSize(400, 400);
        splash.setLocationRelativeTo(null);
        splash.setVisible(true);
        try {
            Thread.sleep(2500); // duración del splash
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        splash.setVisible(false);
        splash.dispose();
    }

    //FORMULARIO DE REGISTRO
    private void crearFormularioIngreso() {
        JInternalFrame form = new JInternalFrame("Formulario de paciente", true, true, true, true);
        form.setSize(500, 300);
        form.setLayout(new GridBagLayout());
        form.getContentPane().setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Campo nombre
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        JTextField txtNombre = new JTextField();
        txtNombre.setPreferredSize(new Dimension(150, 25));

        gbc.gridx = 0; gbc.gridy = 0;
        form.add(lblNombre, gbc);
        gbc.gridx = 1;
        form.add(txtNombre, gbc);

        // Clave historial
        JLabel lblClave = new JLabel("Clave historial:");
        lblClave.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        JPasswordField txtClave = new JPasswordField();
        txtClave.setPreferredSize(new Dimension(150, 25));
        // Posiciona el próximo componente en la columna 0 (izquierda)
        // Incrementa la fila en 1 para ubicar los siguientes componentes en una nueva línea del formulario
        gbc.gridx = 0; gbc.gridy++;
        // Agrega la etiqueta lblClave (etiqueta "Clave historial") al formulario en la posición definida
        // Cambia la posición horizontal a la columna 1 (derecha) para ubicar el campo de texto junto a la etiqueta
        form.add(lblClave, gbc);
        gbc.gridx = 1;
        // Agrega el campo de texto txtClave (campo para ingresar la clave) en la misma fila pero en la columna 1
        form.add(txtClave, gbc);

        // Especie
        JLabel lblEspecie = new JLabel("Especie:");
        lblEspecie.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        String[] especies = {"Perro", "Gato", "Conejo", "Ave", "Oso", "Oso de Anteojos"};
        JComboBox<String> comboEspecie = new JComboBox<>(especies);
        comboEspecie.setPreferredSize(new Dimension(150, 25));

        gbc.gridx = 0; gbc.gridy++;
        form.add(lblEspecie, gbc);
        gbc.gridx = 1;
        form.add(comboEspecie, gbc);

        // Edad
        JLabel lblEdad = new JLabel("Edad:");
        lblEdad.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        JSpinner spinnerEdad = new JSpinner(new SpinnerNumberModel(1, 0, 500, 1));
        ((JSpinner.DefaultEditor) spinnerEdad.getEditor()).getTextField().setPreferredSize(new Dimension(50, 25));

        gbc.gridx = 0; gbc.gridy++;
        form.add(lblEdad, gbc);
        gbc.gridx = 1;
        form.add(spinnerEdad, gbc);

        // Botón registrar
        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnRegistrar.setPreferredSize(new Dimension(100, 30));

        btnRegistrar.addActionListener(e -> {
            String nombre = txtNombre.getText();
            String clave = new String(txtClave.getPassword());
            String especie = (String) comboEspecie.getSelectedItem();
            int edad = (int) spinnerEdad.getValue();

            Mascota m = new Mascota(nombre, especie, edad);
            listaPacientes.add(m);

            JOptionPane.showMessageDialog(form,
                    "🐾 Mascota registrada:\n" +
                            "Nombre: " + nombre +
                            "\nEspecie: " + especie +
                            "\nEdad: " + edad + " años",
                    "Registro exitoso",
                    JOptionPane.INFORMATION_MESSAGE);
        });

        gbc.gridx = 0; gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        form.add(btnRegistrar, gbc);

        escritorio.add(form);
        form.setVisible(true);
    }

    //TABLA DE PACIENTES CON PROGRESS BAR
    private void mostrarTablaPacientes() {
        JInternalFrame frameTabla = new JInternalFrame("Lista de pacientes", true, true, true, true);
        frameTabla.setSize(600, 350);
        frameTabla.setLayout(new BorderLayout());
        JPanel panelCarga = new JPanel(new BorderLayout());
        JProgressBar barraProgreso = new JProgressBar(0, 100);
        barraProgreso.setStringPainted(true);
        panelCarga.add(barraProgreso, BorderLayout.NORTH);
        frameTabla.add(panelCarga, BorderLayout.CENTER);
        Timer timer = new Timer(50, null);
        timer.addActionListener(e -> {
            int valor = barraProgreso.getValue();
            if (valor < 100) {
                barraProgreso.setValue(valor + 5);
            } else {
                timer.stop();
                String[] columnas = {"Nombre", "Especie", "Edad"};
                DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
                for (Mascota m : listaPacientes) {
                    Object[] fila = {m.getNombre(), m.getEspecie(), m.getEdad()};
                    modelo.addRow(fila);
                }
                JTable tabla = new JTable(modelo);
                tabla.getTableHeader().setBackground(new Color(173, 216, 230));
                tabla.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
                JScrollPane scrollTabla = new JScrollPane(tabla);
                frameTabla.getContentPane().removeAll();
                frameTabla.add(scrollTabla, BorderLayout.CENTER);
                frameTabla.revalidate();
                frameTabla.repaint();
            }
        });
        timer.start();
        escritorio.add(frameTabla);
        frameTabla.setVisible(true);
    }

    public static void main(String[] args) {
        // Mostrar splash antes de iniciar app
        mostrarSplashScreen();

        // Lanzar ventana principal
        SwingUtilities.invokeLater(() -> new VentanaPrincipal());
    }
}
