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

    private ConsultaControlador controlador;
    private JTable tablaConsultas;
    private DefaultTableModel modeloTabla;
    private JComboBox<String> comboMascotas;
    private JTextField txtFecha, txtDiagnostico;
    private JComboBox<String> comboServicio;



    public FormConsulta() {
        controlador = new ConsultaControlador();

        setTitle("🐾 Agenda y Consultas - PetControl");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelPrincipal.setBackground(Color.WHITE);
        add(panelPrincipal, BorderLayout.CENTER);

        JLabel titulo = new JLabel("Gestión de Consultas", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titulo.setBorder(BorderFactory.createEmptyBorder(15, 10, 10, 10));
        panelPrincipal.add(titulo);

        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBackground(new Color(240, 248, 255));
        panelFormulario.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(180, 220, 255)),
                "Agendar nueva consulta",
                TitledBorder.LEFT, TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 14), new Color(50, 100, 160)));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Mascota
        gbc.gridx = 0; gbc.gridy = 0;
        panelFormulario.add(new JLabel("Mascota:"), gbc);
        gbc.gridx = 1;
        comboMascotas = new JComboBox<>(obtenerNombresMascotas());
        panelFormulario.add(comboMascotas, gbc);

        // Fecha
        gbc.gridx = 0; gbc.gridy++;
        panelFormulario.add(new JLabel("Fecha (AAAA/MM/DD):"), gbc);
        gbc.gridx = 1;
        txtFecha = new JTextField();
        panelFormulario.add(txtFecha, gbc);

        // Servicio
        gbc.gridx = 0; gbc.gridy++;
        panelFormulario.add(new JLabel("Servicio:"), gbc);
        gbc.gridx = 1;
        String[] servicios = {"Vacunación", "Peluquería", "Cirugía", "Urgencias", "Medicina General"};
        comboServicio = new JComboBox<>(servicios);
        panelFormulario.add(comboServicio, gbc);

        // Diagnóstico
        gbc.gridx = 0; gbc.gridy++;
        panelFormulario.add(new JLabel("Diagnóstico:"), gbc);
        gbc.gridx = 1;
        txtDiagnostico = new JTextField();
        panelFormulario.add(txtDiagnostico, gbc);

        // Botón agendar
        gbc.gridx = 0; gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton btnAgendar = new JButton("Agendar Consulta");
        btnAgendar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnAgendar.setBackground(new Color(100, 180, 240));
        btnAgendar.setForeground(Color.WHITE);
        btnAgendar.addActionListener(e -> agendarConsulta());
        panelFormulario.add(btnAgendar, gbc);

        panelPrincipal.add(panelFormulario);

        // Tabla
        modeloTabla = new DefaultTableModel(
                new String[]{"Fecha", "Mascota", "Motivo", "Diagnóstico", "Tratamiento"}, 0);
        tablaConsultas = new JTable(modeloTabla);
        tablaConsultas.setFillsViewportHeight(true);
        tablaConsultas.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        tablaConsultas.getTableHeader().setBackground(new Color(200, 230, 255));

        JScrollPane scrollTabla = new JScrollPane(tablaConsultas);
        scrollTabla.setBorder(BorderFactory.createTitledBorder("Consultas agendadas"));
        scrollTabla.setPreferredSize(new Dimension(700, 250));
        panelPrincipal.add(Box.createVerticalStrut(15));
        panelPrincipal.add(scrollTabla);

        // Botón cerrar
        JPanel panelCerrar = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelCerrar.setBackground(Color.WHITE);
        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> dispose());
        panelCerrar.add(btnCerrar);
        add(panelCerrar, BorderLayout.SOUTH);

        cargarConsultas();
    }

    private void agendarConsulta() {
        String mascota = (String) comboMascotas.getSelectedItem();
        String fecha = txtFecha.getText().trim();
        String servicio = (String) comboServicio.getSelectedItem();
        String diagnostico = txtDiagnostico.getText().trim();

        if (mascota == null || fecha.isEmpty() || diagnostico.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, completa todos los campos.",
                    "Campos incompletos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            ConsultaDTO consulta = new ConsultaDTO(fecha, mascota, servicio, diagnostico);
            controlador.agendarConsulta(consulta);
            JOptionPane.showMessageDialog(this, "✅ Consulta agendada correctamente.");
            limpiarFormulario();
            cargarConsultas();
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, "❌ Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarConsultas() {
        modeloTabla.setRowCount(0);
        List<ConsultaDTO> consultas = controlador.obtenerConsultas();
        for (ConsultaDTO c : consultas) {
            modeloTabla.addRow(new Object[]{
                    c.getFecha(), c.getMascota(), c.getMotivo(), c.getDiagnostico(), c.getTratamiento()
            });
        }
    }

    private void limpiarFormulario() {
        txtFecha.setText("");
        txtDiagnostico.setText("");
        comboServicio.setSelectedIndex(0);
        comboMascotas.setSelectedIndex(0);
    }

    private String[] obtenerNombresMascotas() {
        MascotaDAO mascotaDAO = new MascotaDAO();
        List<Mascota> mascotas = mascotaDAO.listar();

        if (mascotas.isEmpty()) {
            JOptionPane.showMessageDialog(this, "⚠ No hay mascotas registradas aún.", "Sin datos", JOptionPane.WARNING_MESSAGE);
            return new String[0];
        }

        return mascotas.stream()
                .map(Mascota::getNombre)
                .toArray(String[]::new);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FormConsulta().setVisible(true));
    }
}
