import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class VentanaPrincipal extends JFrame {

    private JDesktopPane escritorio;
    private ArrayList<Mascota> listaPacientes = new ArrayList<>();

    public VentanaPrincipal() {
        setTitle("Sistema de Gestión Clínica Veterinaria");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        escritorio = new JDesktopPane();
        add(escritorio, BorderLayout.CENTER);

        JMenuBar barraMenu = new JMenuBar();

        JMenu menuArchivo = new JMenu("Archivo");
        JMenuItem itemNuevoRegistro = new JMenuItem("Nuevo registro");
        JMenuItem itemSalir = new JMenuItem("Salir");

        // Acción Salir
        itemSalir.addActionListener(e -> System.exit(0));

        // Acción Nuevo registro
        itemNuevoRegistro.addActionListener(e -> {
            crearFormularioIngreso();
        });

        menuArchivo.add(itemNuevoRegistro);
        menuArchivo.addSeparator();
        menuArchivo.add(itemSalir);

        JMenu menuVista = new JMenu("Vista");
        JMenuItem itemPacientes = new JMenuItem("Pacientes");
        JMenuItem itemConsultas = new JMenuItem("Consultas");

        // Acción Pacientes
        itemPacientes.addActionListener(e -> {
            mostrarTablaPacientes();
        });

        menuVista.add(itemPacientes);
        menuVista.add(itemConsultas);

        barraMenu.add(menuArchivo);
        barraMenu.add(menuVista);

        setJMenuBar(barraMenu);

        setVisible(true);
    }

    // Crear formulario de ingreso
    private void crearFormularioIngreso() {
        JInternalFrame form = new JInternalFrame("Formulario de paciente", true, true, true, true);
        form.setSize(500, 300);
        form.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // === Campos del formulario ===

        // Nombre
        JLabel lblNombre = new JLabel("Nombre:");
        JTextField txtNombre = new JTextField();
        txtNombre.setPreferredSize(new Dimension(150, 25));

        gbc.gridx = 0; gbc.gridy = 0;
        form.add(lblNombre, gbc);
        gbc.gridx = 1;
        form.add(txtNombre, gbc);

        // Clave historial
        JLabel lblClave = new JLabel("Clave historial:");
        JPasswordField txtClave = new JPasswordField();
        txtClave.setPreferredSize(new Dimension(150, 25));

        gbc.gridx = 0; gbc.gridy++;
        form.add(lblClave, gbc);
        gbc.gridx = 1;
        form.add(txtClave, gbc);

        // Especie
        JLabel lblEspecie = new JLabel("Especie:");
        String[] especies = {"Perro", "Gato", "Conejo", "Ave", "Oso", "Oso de Anteojos"};
        JComboBox<String> comboEspecie = new JComboBox<>(especies);
        comboEspecie.setPreferredSize(new Dimension(150, 25));

        gbc.gridx = 0; gbc.gridy++;
        form.add(lblEspecie, gbc);
        gbc.gridx = 1;
        form.add(comboEspecie, gbc);

        // Edad
        JLabel lblEdad = new JLabel("Edad:");
        JSpinner spinnerEdad = new JSpinner(new SpinnerNumberModel(1, 0, 500, 1));
        ((JSpinner.DefaultEditor) spinnerEdad.getEditor()).getTextField().setPreferredSize(new Dimension(50, 25));

        gbc.gridx = 0; gbc.gridy++;
        form.add(lblEdad, gbc);
        gbc.gridx = 1;
        form.add(spinnerEdad, gbc);

        // Botón registrar
        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.setPreferredSize(new Dimension(100, 30));

        // Acción del botón
        btnRegistrar.addActionListener(e -> {
            String nombre = txtNombre.getText();
            String clave = new String(txtClave.getPassword());
            String especie = (String) comboEspecie.getSelectedItem();
            int edad = (int) spinnerEdad.getValue();

            // Crear mascota y agregarla a la lista
            Mascota m = new Mascota(nombre, especie, edad);
            listaPacientes.add(m);

            JOptionPane.showMessageDialog(form,
                    "Mascota registrada:\n" +
                            "Nombre: " + nombre +
                            "\nEspecie: " + especie +
                            "\nEdad: " + edad + " años",
                    "Registro exitoso",
                    JOptionPane.INFORMATION_MESSAGE);
        });

        // Espacio para botón
        gbc.gridx = 0; gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        form.add(btnRegistrar, gbc);

        // Agregar formulario al escritorio y mostrarlo
        escritorio.add(form);
        form.setVisible(true);
    }

    // Mostrar tabla de pacientes con barra de progreso
    private void mostrarTablaPacientes() {
        // Crear JInternalFrame
        JInternalFrame frameTabla = new JInternalFrame("Lista de pacientes", true, true, true, true);
        frameTabla.setSize(600, 350);
        frameTabla.setLayout(new BorderLayout());

        // Crear panel con ProgressBar arriba
        JPanel panelCarga = new JPanel(new BorderLayout());
        JProgressBar barraProgreso = new JProgressBar(0, 100);
        barraProgreso.setStringPainted(true);
        panelCarga.add(barraProgreso, BorderLayout.NORTH);

        frameTabla.add(panelCarga, BorderLayout.CENTER);

        // Timer para simular carga
        Timer timer = new Timer(50, null);
        timer.addActionListener(e -> {
            int valor = barraProgreso.getValue();
            if (valor < 100) {
                barraProgreso.setValue(valor + 5);
            } else {
                timer.stop();

                // Cuando la barra llegue a 100, mostrar la tabla
                String[] columnas = {"Nombre", "Especie", "Edad"};
                DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

                for (Mascota m : listaPacientes) {
                    Object[] fila = {m.getNombre(), m.getEspecie(), m.getEdad()};
                    modelo.addRow(fila);
                }

                JTable tabla = new JTable(modelo);
                JScrollPane scrollTabla = new JScrollPane(tabla);

                // Reemplazar barra por la tabla
                frameTabla.getContentPane().removeAll();
                frameTabla.add(scrollTabla, BorderLayout.CENTER);
                frameTabla.revalidate();
                frameTabla.repaint();
            }
        });
        timer.start();

        // Agregar frame al escritorio y mostrarlo
        escritorio.add(frameTabla);
        frameTabla.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaPrincipal());
    }
}

