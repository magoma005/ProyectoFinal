package vistas;// [IMPORTS]
import modelo.Mascota;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class FormMascota extends JFrame {

    private ArrayList<Mascota> listaPacientes;

    public FormMascota(ArrayList<Mascota> listaPacientes) {
        this.listaPacientes = listaPacientes;

        setTitle("Lista de pacientes");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel superior con progress bar
        JPanel panelCarga = new JPanel(new BorderLayout());
        JProgressBar barraProgreso = new JProgressBar(0, 100);
        barraProgreso.setStringPainted(true);
        panelCarga.add(barraProgreso, BorderLayout.NORTH);
        add(panelCarga, BorderLayout.CENTER);

        Timer timer = new Timer(50, null);
        timer.addActionListener(e -> {
            int valor = barraProgreso.getValue();
            if (valor < 100) {
                barraProgreso.setValue(valor + 5);
            } else {
                timer.stop();
                // Crear tabla
                String[] columnas = {"Nombre", "Especie", "Edad"};
                DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
                for (Mascota m : listaPacientes) {
                    Object[] fila = {m.getNombre(), m.getEspecie(), m.getEdad()};
                    modelo.addRow(fila);
                }
                JTable tabla = new JTable(modelo);
                tabla.getTableHeader().setBackground(new Color(173, 216, 230));
                tabla.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));

                // Botón eliminar
                JButton btnEliminar = new JButton("Eliminar paciente");
                btnEliminar.addActionListener(ev -> eliminarPaciente());

                // Botón actualizar
                JButton btnActualizar = new JButton("Actualizar paciente");
                btnActualizar.addActionListener(ev -> actualizarPaciente());

                // Panel inferior con botones
                JPanel panelBotones = new JPanel();
                panelBotones.add(btnEliminar);
                panelBotones.add(btnActualizar);

                JScrollPane scrollTabla = new JScrollPane(tabla);
                getContentPane().removeAll();
                add(scrollTabla, BorderLayout.CENTER);
                add(panelBotones, BorderLayout.SOUTH);
                revalidate();
                repaint();
            }
        });
        timer.start();
    }

    private void eliminarPaciente() {
        JTextField campoNombre = new JTextField();
        JPasswordField campoClave = new JPasswordField();
        Object[] mensaje = {
                "Nombre del paciente a eliminar:", campoNombre,
                "Clave del paciente:", campoClave
        };
        int opcion = JOptionPane.showConfirmDialog(this, mensaje, "Eliminar paciente", JOptionPane.OK_CANCEL_OPTION);
        if (opcion == JOptionPane.OK_OPTION) {
            String nombreEliminar = campoNombre.getText().trim();
            String claveEliminar = new String(campoClave.getPassword()).trim();
            boolean encontrado = false;
            for (Mascota m : listaPacientes) {
                if (m.getNombre().equalsIgnoreCase(nombreEliminar) && m.getClave().equals(claveEliminar)) {
                    listaPacientes.remove(m);
                    encontrado = true;
                    break;
                }
            }
            if (encontrado) {
                JOptionPane.showMessageDialog(this, "✅ Paciente eliminado exitosamente.");
                recargarVentana();
            } else {
                JOptionPane.showMessageDialog(this, "⚠️ No se encontró un paciente con ese nombre y clave.");
            }
        }
    }

    private void actualizarPaciente() {
        JTextField campoNombre = new JTextField();
        JPasswordField campoClave = new JPasswordField();
        Object[] mensaje = {
                "Nombre del paciente a actualizar:", campoNombre,
                "Clave del paciente:", campoClave
        };

        int opcion = JOptionPane.showConfirmDialog(this, mensaje, "Actualizar paciente", JOptionPane.OK_CANCEL_OPTION);
        if (opcion == JOptionPane.OK_OPTION) {
            String nombreBuscar = campoNombre.getText().trim();
            String claveBuscar = new String(campoClave.getPassword()).trim();

            Mascota mascotaEncontrada = null;
            for (Mascota m : listaPacientes) {
                if (m.getNombre().equalsIgnoreCase(nombreBuscar) && m.getClave().equals(claveBuscar)) {
                    mascotaEncontrada = m;
                    break;
                }
            }

            if (mascotaEncontrada != null) {
                JTextField nuevoNombre = new JTextField(mascotaEncontrada.getNombre());
                JTextField nuevaEspecie = new JTextField(mascotaEncontrada.getEspecie());
                JSpinner nuevaEdad = new JSpinner(new SpinnerNumberModel(mascotaEncontrada.getEdad(), 0, 500, 1));
                JPasswordField nuevaClave = new JPasswordField(mascotaEncontrada.getClave());

                Object[] camposEditar = {
                        "Nuevo nombre:", nuevoNombre,
                        "Nueva especie:", nuevaEspecie,
                        "Nueva edad:", nuevaEdad,
                        "Nueva clave:", nuevaClave
                };

                int editar = JOptionPane.showConfirmDialog(this, camposEditar, "Editar datos paciente", JOptionPane.OK_CANCEL_OPTION);
                if (editar == JOptionPane.OK_OPTION) {
                    mascotaEncontrada.setNombre(nuevoNombre.getText().trim());
                    mascotaEncontrada.setEspecie(nuevaEspecie.getText().trim());
                    mascotaEncontrada.setEdad((int) nuevaEdad.getValue());
                    mascotaEncontrada.setClave(new String(nuevaClave.getPassword()).trim());

                    JOptionPane.showMessageDialog(this, "✅ Datos actualizados exitosamente.");
                    recargarVentana();
                }
            } else {
                JOptionPane.showMessageDialog(this, "⚠️ No se encontró un paciente con ese nombre y clave.");
            }
        }
    }

    private void recargarVentana() {
        dispose();
        new FormMascota(listaPacientes).setVisible(true);
    }

    // Método de prueba para lanzar esta ventana individualmente
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ArrayList<Mascota> lista = new ArrayList<>();
            lista.add(new Mascota("Firulais", "Perro", 5, "1234"));
            lista.add(new Mascota("Michi", "Gato", 3, "abcd"));
            new FormMascota(lista).setVisible(true);
        });
    }
}
