package vistas;// [IMPORTS]
import modelo.Consulta;
import modelo.Mascota;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.util.ArrayList;

public class VentanaPrincipal extends JFrame {

    private JDesktopPane escritorio;
    private ArrayList<Mascota> listaPacientes = new ArrayList<>();
    private ArrayList<Consulta> listaConsultas = new ArrayList<>();

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

        // PANEL DE BIENVENIDA
        JPanel panelBienvenida = new JPanel();
        panelBienvenida.setBackground(Color.WHITE);
        panelBienvenida.setLayout(new BoxLayout(panelBienvenida, BoxLayout.Y_AXIS));

        // Logo centrado
        ImageIcon iconoLogo = new ImageIcon(getClass().getResource("/imagenes/logo2.jpg"));
        Image imagenLogo = iconoLogo.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        JLabel lblLogo = new JLabel(new ImageIcon(imagenLogo));
        lblLogo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelBienvenida.add(Box.createVerticalStrut(20)); // Espacio superior
        panelBienvenida.add(lblLogo);

        // Texto de bienvenida
        JLabel lblBienvenida = new JLabel("Bienvenido a PetControl");
        lblBienvenida.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblBienvenida.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelBienvenida.add(Box.createVerticalStrut(20));
        panelBienvenida.add(lblBienvenida);

        // Mensaje guía
        JLabel lblMensaje = new JLabel("Selecciona una opción en el menú superior para comenzar.");
        lblMensaje.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblMensaje.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelBienvenida.add(Box.createVerticalStrut(10));
        panelBienvenida.add(lblMensaje);

        JButton btnAccesoPacientes = new JButton("Ver pacientes");
        btnAccesoPacientes.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnAccesoPacientes.addActionListener(e -> new FormMascota().setVisible(true));
        panelBienvenida.add(Box.createVerticalStrut(20));
        panelBienvenida.add(btnAccesoPacientes);

        JButton btnAccesoConsulta = new JButton("Agendar consulta");
        btnAccesoConsulta.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnAccesoConsulta.addActionListener(e -> agendarConsulta());
        panelBienvenida.add(Box.createVerticalStrut(10));
        panelBienvenida.add(btnAccesoConsulta);
        panelBienvenida.add(Box.createVerticalGlue()); // Empuja contenido hacia arriba

        //Panel de bienvenida al escritorio
        panelBienvenida.setBounds(200, 50, 400, 400);
        escritorio.add(panelBienvenida);


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
        JMenuItem itemAgendarConsulta = new JMenuItem("Agendar Consulta");
        itemAgendarConsulta.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        itemAgendarConsulta.addActionListener(e -> agendarConsulta());
        menuArchivo.add(itemAgendarConsulta);
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
        itemConsultas.addActionListener(e -> new FormConsulta().setVisible(true));

         // Acción pacientes
        itemPacientes.addActionListener(e -> new FormMascota().setVisible(true));

        // Acción consultas
        itemConsultas.addActionListener(e -> new FormConsulta().setVisible(true));

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

    // FORMULARIO DE REGISTRO
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
        gbc.gridx = 0;
        gbc.gridy = 0;
        form.add(lblNombre, gbc);
        gbc.gridx = 1;
        form.add(txtNombre, gbc);

        // Clave historial
        JLabel lblClave = new JLabel("Clave historial:");
        lblClave.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        JPasswordField txtClave = new JPasswordField();
        txtClave.setPreferredSize(new Dimension(150, 25));
        gbc.gridx = 0;
        gbc.gridy++;
        form.add(lblClave, gbc);
        gbc.gridx = 1;
        form.add(txtClave, gbc);

        // Especie
        JLabel lblEspecie = new JLabel("Especie:");
        lblEspecie.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        String[] especies = {"Perro", "Gato", "Conejo", "Ave", "Oso", "Oso de Anteojos"};
        JComboBox<String> comboEspecie = new JComboBox<>(especies);
        comboEspecie.setPreferredSize(new Dimension(150, 25));
        gbc.gridx = 0;
        gbc.gridy++;
        form.add(lblEspecie, gbc);
        gbc.gridx = 1;
        form.add(comboEspecie, gbc);

        // Edad
        JLabel lblEdad = new JLabel("Edad:");
        lblEdad.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        JSpinner spinnerEdad = new JSpinner(new SpinnerNumberModel(1, 0, 500, 1));
        ((JSpinner.DefaultEditor) spinnerEdad.getEditor()).getTextField().setPreferredSize(new Dimension(50, 25));
        gbc.gridx = 0;
        gbc.gridy++;
        form.add(lblEdad, gbc);
        gbc.gridx = 1;
        form.add(spinnerEdad, gbc);

        // Botón registrar
        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnRegistrar.setPreferredSize(new Dimension(100, 30));
        btnRegistrar.addActionListener(e -> {
            String nombre = txtNombre.getText().trim();
            String clave = new String(txtClave.getPassword()).trim();

            if (nombre.isEmpty()) {
                JOptionPane.showMessageDialog(form, "El nombre no puede estar vacío.");
                return;
            }

            if (clave.isEmpty()) {
                JOptionPane.showMessageDialog(form, "La clave no puede estar vacía.");
                return;
            }

            // Verificar si ya existe ese nombre en listaPacientes
            for (Mascota m : listaPacientes) {
                if (m.getNombre().equalsIgnoreCase(nombre)) {
                    JOptionPane.showMessageDialog(form, "⚠️ Ya existe un paciente con este nombre. Usa otro único.");
                    return;
                }
            }

            String especie = (String) comboEspecie.getSelectedItem();
            int edad = (int) spinnerEdad.getValue();

            // Uso del constructor
            Mascota m = new Mascota(nombre, especie, edad, clave);
            listaPacientes.add(m);

            JOptionPane.showMessageDialog(form,
                    "🐾 Mascota registrada:\n" +
                            "Nombre: " + nombre +
                            "\nEspecie: " + especie +
                            "\nEdad: " + edad + " años",
                    "Registro exitoso",
                    JOptionPane.INFORMATION_MESSAGE);
        });

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        form.add(btnRegistrar, gbc);

        escritorio.add(form);
        form.setVisible(true);
}

    // FORMULARIO PARA AGENDAR CONSULTA
    private void agendarConsulta() {
        JInternalFrame formConsulta = new JInternalFrame("Agendar Consulta", true, true, true, true);
        formConsulta.setSize(450, 350);
        formConsulta.setLayout(new GridBagLayout());
        formConsulta.getContentPane().setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Seleccionar mascota
        JLabel lblMascota = new JLabel("Mascota:");
        lblMascota.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        JComboBox<String> comboMascotas = new JComboBox<>();
        for (Mascota m : listaPacientes) {
            comboMascotas.addItem(m.getNombre());
        }
        gbc.gridx = 0; gbc.gridy = 0;
        formConsulta.add(lblMascota, gbc);
        gbc.gridx = 1;
        formConsulta.add(comboMascotas, gbc);

        // Fecha
        JLabel lblFecha = new JLabel("Fecha (AAAA/MM/DD):");
        lblFecha.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        JTextField txtFecha = new JTextField();
        gbc.gridx = 0; gbc.gridy++;
        formConsulta.add(lblFecha, gbc);
        gbc.gridx = 1;
        formConsulta.add(txtFecha, gbc);

        // Servicio deseado
        JLabel lblServicio = new JLabel("Servicio:");
        lblServicio.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        String[] servicios = {"Vacunación", "Peluquería", "Cirugía", "Urgencias", "Medicina General"};
        JComboBox<String> comboServicio = new JComboBox<>(servicios);
        gbc.gridx = 0; gbc.gridy++;
        formConsulta.add(lblServicio, gbc);
        gbc.gridx = 1;
        formConsulta.add(comboServicio, gbc);

        // Comentario adicional
        JLabel lblComentario = new JLabel("Comentario adicional:");
        lblComentario.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        JTextField txtComentario = new JTextField();
        txtComentario.setPreferredSize(new Dimension(150, 25));
        gbc.gridx = 0; gbc.gridy++;
        formConsulta.add(lblComentario, gbc);
        gbc.gridx = 1;
        formConsulta.add(txtComentario, gbc);


        // Botón confirmar
        JButton btnConfirmar = new JButton("Agendar");
        btnConfirmar.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnConfirmar.addActionListener(e -> {
            String mascota = (String) comboMascotas.getSelectedItem();
            String fecha = txtFecha.getText().trim();
            String comentario = txtComentario.getText().trim();
            String servicio = (String) comboServicio.getSelectedItem();

            if (mascota == null || fecha.isEmpty() || comentario.isEmpty() || servicio == null) {
                JOptionPane.showMessageDialog(formConsulta, "Por favor completa todos los campos.");
                return;
            }

            try {
                // Crear la consulta usando la clase modelo
                Consulta nuevaConsulta = new Consulta(fecha, mascota, servicio, comentario);

                // Guardar en la lista de consultas
                listaConsultas.add(nuevaConsulta);

                JOptionPane.showMessageDialog(formConsulta,
                        "✅ Consulta agendada:\n" +
                                "Código: " + nuevaConsulta.getCodigo() +
                                "\nMascota: " + nuevaConsulta.getMascota() +
                                "\nFecha: " + nuevaConsulta.getFecha() +
                                "\nServicio: " + nuevaConsulta.getServicio() +
                                "\nComentario: " + nuevaConsulta.getComentario(),
                        "Consulta Agendada", JOptionPane.INFORMATION_MESSAGE);
                formConsulta.dispose();
            } catch (IllegalArgumentException ex) {
                // Si hay error en formato de fecha
                JOptionPane.showMessageDialog(formConsulta, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        gbc.gridx = 0; gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        formConsulta.add(btnConfirmar, gbc);

        escritorio.add(formConsulta);
        formConsulta.setVisible(true);
    }

    public static void main(String[] args) {
        // Mostrar splash antes de iniciar app
        mostrarSplashScreen();
        // Lanzar ventana principal
        SwingUtilities.invokeLater(() -> new VentanaPrincipal());
    }
}