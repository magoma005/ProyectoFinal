package vistas;

import DTO.MascotaDTO;
import controlador.ConsultaControlador;
import DTO.ConsultaDTO;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import DAO.MascotaDAO;
import excepciones.DatoInvalidoException;
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
        btnAgendar.addActionListener(e -> agendarConsulta());
        panelFormulario.add(btnAgendar, gbc);

        // === Botón: Eliminar Consulta ===
        gbc.gridy++;
        JButton btnEliminar = new JButton("Eliminar Consulta");
        btnEliminar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnEliminar.setBackground(new Color(255, 100, 100));
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.addActionListener(e -> eliminarConsulta());
        panelFormulario.add(btnEliminar, gbc);

        // === Botón: Editar Consulta ===
        gbc.gridy++;
        JButton btnActualizar = new JButton("Actualizar Consulta");
        btnActualizar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnActualizar.setBackground(new Color(4, 76, 21));
        btnActualizar.setForeground(Color.WHITE);
        btnActualizar.addActionListener(e -> actualizarConsulta());
        panelFormulario.add(btnActualizar, gbc);



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
                    c.getServicio(),
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

    // Elimina la consulta seleccionada de la tabla
    private void eliminarConsulta() {
        int filaSeleccionada = tablaConsultas.getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Por favor selecciona una consulta para eliminar.",
                    "Sin selección", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(this,
                "¿Estás seguro de eliminar esta consulta?", "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            String resultado = controlador.eliminarConsulta(filaSeleccionada);
            JOptionPane.showMessageDialog(this, resultado);
            cargarConsultas(); // Recargar la tabla
        }
    }

    private void actualizarConsulta() {
        int filaSeleccionada = tablaConsultas.getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona una consulta en la tabla para actualizar.",
                    "Sin selección", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            // Captura los nuevos datos desde el formulario
            String fecha = txtFecha.getText().trim();
            String mascota = (String) comboMascotas.getSelectedItem();
            String servicio = (String) comboServicio.getSelectedItem();
            String diagnostico = txtDiagnostico.getText().trim();

            // Validación
            if (fecha.isEmpty() || mascota == null || servicio == null || diagnostico.isEmpty()) {
                mostrarError("Todos los campos deben estar completos para actualizar.");
                return;
            }

            // Crea un nuevo objeto con los datos actualizados
            ConsultaDTO dtoActualizada = new ConsultaDTO(fecha, mascota, servicio, diagnostico);

            // Llama al controlador para actualizar la consulta
            String mensaje = controlador.actualizarConsulta(filaSeleccionada, dtoActualizada);

            mensaje(mensaje);
            limpiarFormulario();
            cargarConsultas();

        } catch (Exception e) {
            mostrarError("Error inesperado: " + e.getMessage());
        }
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void mensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
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