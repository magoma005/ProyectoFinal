package vistas;

import controlador.ConsultaControlador;
import DTO.ConsultaDTO;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import DAO.MascotaDAO;
import modelo.Mascota;


public class FormConsulta extends JFrame {

    // Controlador que maneja la lógica de consultas
    private ConsultaControlador controlador;

    // Componentes visuales principales
    private JTable tablaConsultas;
    private DefaultTableModel modeloTabla;
    private JComboBox<String> comboMascotas;
    private JTextField txtFecha, txtDiagnostico;
    private JComboBox<String> comboServicio;

    // Constructor principal del formulario
    public FormConsulta() {
        controlador = new ConsultaControlador();

        setTitle("🐾 Agenda y Consultas - PetControl");
        setSize(800, 600);
        setLocationRelativeTo(null); // Centra la ventana
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE); // Fondo blanco general

        // Panel contenedor principal
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelPrincipal.setBackground(Color.WHITE);
        add(panelPrincipal, BorderLayout.CENTER);

        // Título principal
        JLabel titulo = new JLabel("Gestión de Consultas", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titulo.setBorder(BorderFactory.createEmptyBorder(15, 10, 10, 10));
        panelPrincipal.add(titulo);

        // Panel del formulario para agendar una consulta
        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBackground(new Color(240, 248, 255));
        panelFormulario.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(180, 220, 255)),
                "Agendar nueva consulta",
                TitledBorder.LEFT, TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 14), new Color(50, 100, 160)));

        GridBagConstraints gbc = new GridBagConstraints(); // Para ordenar los componentes
        gbc.insets = new Insets(8, 8, 8, 8); // Espaciado entre componentes
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // === Campo: Mascota ===
        gbc.gridx = 0; gbc.gridy = 0;
        panelFormulario.add(new JLabel("Mascota:"), gbc);
        gbc.gridx = 1;
        comboMascotas = new JComboBox<>(obtenerNombresMascotas()); // Llena con mascotas disponibles
        panelFormulario.add(comboMascotas, gbc);

        // === Campo: Fecha ===
        gbc.gridx = 0; gbc.gridy++;
        panelFormulario.add(new JLabel("Fecha (AAAA/MM/DD):"), gbc);
        gbc.gridx = 1;
        txtFecha = new JTextField();
        panelFormulario.add(txtFecha, gbc);

        // === Campo: Servicio ===
        gbc.gridx = 0; gbc.gridy++;
        panelFormulario.add(new JLabel("Servicio:"), gbc);
        gbc.gridx = 1;
        String[] servicios = {"Vacunación", "Peluquería", "Cirugía", "Urgencias", "Medicina General"};
        comboServicio = new JComboBox<>(servicios);
        panelFormulario.add(comboServicio, gbc);

        // === Campo: Diagnóstico ===
        gbc.gridx = 0; gbc.gridy++;
        panelFormulario.add(new JLabel("Diagnóstico:"), gbc);
        gbc.gridx = 1;
        txtDiagnostico = new JTextField();
        panelFormulario.add(txtDiagnostico, gbc);

        // === Botón: Agendar Consulta ===
        gbc.gridx = 0; gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton btnAgendar = new JButton("Agendar Consulta");
        btnAgendar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnAgendar.setBackground(new Color(100, 180, 240));
        btnAgendar.setForeground(Color.WHITE);
        btnAgendar.addActionListener(e -> agendarConsulta()); // Acción al presionar
        panelFormulario.add(btnAgendar, gbc);

        // Agrega el formulario al panel principal
        panelPrincipal.add(panelFormulario);

        // === Tabla de Consultas ===
        modeloTabla = new DefaultTableModel(
                new String[]{"Fecha", "Mascota", "Servicio", "Diagnóstico"}, 0); // <-- columnas actualizadas
        tablaConsultas = new JTable(modeloTabla);
        tablaConsultas.setFillsViewportHeight(true); // Ajuste automático
        tablaConsultas.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        tablaConsultas.getTableHeader().setBackground(new Color(200, 230, 255));

        JScrollPane scrollTabla = new JScrollPane(tablaConsultas);
        scrollTabla.setBorder(BorderFactory.createTitledBorder("Consultas agendadas"));
        scrollTabla.setPreferredSize(new Dimension(700, 250));

        panelPrincipal.add(Box.createVerticalStrut(15)); // Espacio vertical
        panelPrincipal.add(scrollTabla);

        // === Botón Cerrar ===
        JPanel panelCerrar = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelCerrar.setBackground(Color.WHITE);
        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> dispose()); // Cierra la ventana
        panelCerrar.add(btnCerrar);
        add(panelCerrar, BorderLayout.SOUTH);

        // Carga las consultas existentes al iniciar
        cargarConsultas();
    }

    // Método que agenda una nueva consulta usando los datos del formulario
    private void agendarConsulta() {
        String mascota = (String) comboMascotas.getSelectedItem();
        String fecha = txtFecha.getText().trim();
        String servicio = (String) comboServicio.getSelectedItem();
        String diagnostico = txtDiagnostico.getText().trim();

        // Validación de campos obligatorios
        if (mascota == null || fecha.isEmpty() || diagnostico.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, completa todos los campos.",
                    "Campos incompletos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            // Crea una nueva consulta con los datos
            ConsultaDTO consulta = new ConsultaDTO(fecha, mascota, servicio, diagnostico);
            controlador.agendarConsulta(consulta); // Guarda en el sistema

            JOptionPane.showMessageDialog(this, "✅ Consulta agendada correctamente.");
            limpiarFormulario(); // Limpia los campos
            cargarConsultas();   // Recarga la tabla
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, "❌ Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Carga las consultas desde el controlador y las muestra en la tabla
    private void cargarConsultas() {
        modeloTabla.setRowCount(0); // Limpia la tabla
        List<ConsultaDTO> consultas = controlador.obtenerConsultas();

        for (ConsultaDTO c : consultas) {
            modeloTabla.addRow(new Object[]{
                    c.getFecha(),
                    c.getMascota(),
                    c.getServicio(),      // <--- Agregado
                    c.getDiagnostico()
            });
        }
    }

    // Limpia todos los campos del formulario para registrar una nueva consulta
    private void limpiarFormulario() {
        txtFecha.setText("");
        txtDiagnostico.setText("");
        comboServicio.setSelectedIndex(0);
        comboMascotas.setSelectedIndex(0);
    }

    // Obtiene los nombres de las mascotas registradas para mostrarlas en el JComboBox
    private String[] obtenerNombresMascotas() {
        MascotaDAO mascotaDAO = new MascotaDAO();
        List<Mascota> mascotas = mascotaDAO.listar();

        if (mascotas.isEmpty()) {
            JOptionPane.showMessageDialog(this, "⚠ No hay mascotas registradas aún.",
                    "Sin datos", JOptionPane.WARNING_MESSAGE);
            return new String[0];
        }

        // Devuelve un arreglo con los nombres de las mascotas
        return mascotas.stream()
                .map(Mascota::getNombre)
                .toArray(String[]::new);
    }

    // Método main para ejecutar el formulario de forma independiente
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FormConsulta().setVisible(true));
    }
}