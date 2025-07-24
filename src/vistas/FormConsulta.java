package vistas;

import controlador.ConsultaControlador;
import DTO.ConsultaDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;


public class FormConsulta extends JFrame {

    private ConsultaControlador controlador;

    public FormConsulta() {
        controlador = new ConsultaControlador();

        setTitle("Consultas agendadas");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // === Columnas de la tabla ===
        String[] columnas = {"Fecha", "Mascota", "Motivo", "Diagnóstico", "Tratamiento"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

        // === Obtener datos del controlador ===
        List<ConsultaDTO> listaConsultas = controlador.obtenerConsultas();

        // === Agregar filas con los datos de cada consulta ===
        for (ConsultaDTO c : listaConsultas) {
            Object[] fila = {
                    c.getFecha(),
                    c.getMascota(),
                    c.getMotivo(),
                    c.getDiagnostico(),
                    c.getTratamiento()
            };
            modelo.addRow(fila);
        }

        JTable tabla = new JTable(modelo);
        tabla.getTableHeader().setBackground(new Color(173, 216, 230));
        tabla.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));

        JScrollPane scrollTabla = new JScrollPane(tabla);
        add(scrollTabla, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FormConsulta().setVisible(true));
    }
}
