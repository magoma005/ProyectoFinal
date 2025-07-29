package vistas;

import modelo.Mascota;
import modelo.Persona;
import DAO.PersonaDAO;
import java.util.List;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.util.ArrayList;

public class VentanaPrincipal extends JFrame {

    private JDesktopPane escritorio;
    private ArrayList<Mascota> listaPacientes = new ArrayList<>();
    private final PersonaDAO personaDAO = new PersonaDAO();
    private List<Persona> listaPersonas;

    public VentanaPrincipal() {
        setTitle("PetControl - Sistema de Gestión Clínica Veterinaria");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        listaPersonas = personaDAO.cargarPersonas();

        Color colorFondo = new Color(245, 251, 255);
        getContentPane().setBackground(colorFondo);
        // === Árbol lateral izquierdo ===
        DefaultMutableTreeNode raiz = new DefaultMutableTreeNode("Servicios");
        raiz.add(new DefaultMutableTreeNode("Medicina general"));
        raiz.add(new DefaultMutableTreeNode("Cirugía"));
        raiz.add(new DefaultMutableTreeNode("Vacunación"));
        raiz.add(new DefaultMutableTreeNode("Peluquería"));
        raiz.add(new DefaultMutableTreeNode("Urgencias"));
        JTree arbolServicios = new JTree(raiz);
        arbolServicios.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JScrollPane scrollArbol = new JScrollPane(arbolServicios);
        scrollArbol.setPreferredSize(new Dimension(180, 0));
        scrollArbol.setBorder(BorderFactory.createTitledBorder("Áreas disponibles"));
        add(scrollArbol, BorderLayout.WEST);

        // === Panel de contenido central ===
        JPanel panelContenido = new JPanel(new BorderLayout());
        panelContenido.setBackground(new Color(255, 248, 240));
        panelContenido.setOpaque(true);
        add(panelContenido, BorderLayout.CENTER);
        escritorio = new JDesktopPane();
        escritorio.setOpaque(false);
        panelContenido.add(escritorio, BorderLayout.CENTER);

        // === Panel de bienvenida personalizado ===
        JPanel panelBienvenida = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(255, 249, 243));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        panelBienvenida.setOpaque(true);
        panelBienvenida.setBackground(new Color(255, 248, 240));
        panelBienvenida.setLayout(new BoxLayout(panelBienvenida, BoxLayout.Y_AXIS));
        panelBienvenida.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        escritorio.add(panelBienvenida);

        // === Contenido visual del panel ===
        ImageIcon iconoLogo = new ImageIcon(getClass().getResource("/imagenes/logo2.jpg"));
        Image imagenLogo = iconoLogo.getImage().getScaledInstance(160, 160, Image.SCALE_SMOOTH);
        JLabel lblLogo = new JLabel(new ImageIcon(imagenLogo));
        lblLogo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelBienvenida.add(Box.createVerticalStrut(20));
        panelBienvenida.add(lblLogo);

        JLabel lblBienvenida = new JLabel("Bienvenido a PetControl");
        lblBienvenida.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblBienvenida.setForeground(new Color(33, 97, 140));
        lblBienvenida.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelBienvenida.add(Box.createVerticalStrut(20));
        panelBienvenida.add(lblBienvenida);

        JLabel lblMensaje = new JLabel("Selecciona una opción en el menú superior para comenzar.");
        lblMensaje.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblMensaje.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelBienvenida.add(Box.createVerticalStrut(10));
        panelBienvenida.add(lblMensaje);
        panelBienvenida.add(Box.createVerticalGlue());

        // === Pie de página ===
        JLabel piePagina = new JLabel("© 2025 PetControl. Todos los derechos reservados.", SwingConstants.CENTER);
        piePagina.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        piePagina.setForeground(new Color(100, 100, 100));
        piePagina.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(piePagina, BorderLayout.SOUTH);

        // === Barra de menú ===
        JMenuBar barraMenu = new JMenuBar();
        barraMenu.setBackground(Color.WHITE);
        barraMenu.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
        barraMenu.add(Box.createHorizontalGlue());

        JMenu menuArchivo = new JMenu("Archivo");
        menuArchivo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JMenuItem itemSalir = new JMenuItem("Salir");
        itemSalir.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        itemSalir.addActionListener(e -> {
            personaDAO.guardarPersonas(listaPersonas);
            JOptionPane.showMessageDialog(this, "👋 Gracias por usar PetControl. ¡Hasta pronto!");
            System.exit(0);
        });
        menuArchivo.add(itemSalir);

        JMenu menuPacientes = new JMenu("Pacientes");
        menuPacientes.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JMenuItem itemPacientes = new JMenuItem("Ver Pacientes");
        itemPacientes.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        itemPacientes.addActionListener(e -> new FormMascota().setVisible(true));
        menuPacientes.add(itemPacientes);

        JMenu menuConsultas = new JMenu("Consultas");
        menuConsultas.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JMenuItem itemConsultas = new JMenuItem("Ver Consultas");
        itemConsultas.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        itemConsultas.addActionListener(e -> new FormConsulta().setVisible(true));
        menuConsultas.add(itemConsultas);

        JMenu menuRegistro = new JMenu("Registro");
        menuRegistro.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JMenuItem itemPersonas = new JMenuItem("Gestionar personas");
        itemPersonas.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        itemPersonas.addActionListener(e -> new FormPersona().setVisible(true));
        menuRegistro.add(itemPersonas);

        barraMenu.add(menuArchivo);
        barraMenu.add(menuPacientes);
        barraMenu.add(menuConsultas);
        barraMenu.add(menuRegistro);
        barraMenu.add(Box.createHorizontalGlue());
        setJMenuBar(barraMenu);

        setVisible(true); // Mostrar ventana primero

        // Forzar ubicación del panel bienvenida
        int ancho = 460;
        int alto = 400;
        int x = (escritorio.getWidth() - ancho) / 2;
        int y = (escritorio.getHeight() - alto) / 2;
        panelBienvenida.setBounds(x, y, ancho, alto);
        panelBienvenida.revalidate();
        panelBienvenida.repaint();

        // Reubicar si se cambia el tamaño de la ventana
        escritorio.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                int ancho = 460;
                int alto = 400;
                int x = (escritorio.getWidth() - ancho) / 2;
                int y = (escritorio.getHeight() - alto) / 2;
                panelBienvenida.setBounds(x, y, ancho, alto);
            }
        });
    }

    public static void mostrarSplashScreen() {
        JWindow splash = new JWindow();
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
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
            Thread.sleep(2500);
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
   /* private void agendarConsulta() {
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
    }*/

    public static void main(String[] args) {
        // Mostrar splash antes de iniciar app
        mostrarSplashScreen();
        // Lanzar ventana principal
        SwingUtilities.invokeLater(() -> new VentanaPrincipal());
    }
}