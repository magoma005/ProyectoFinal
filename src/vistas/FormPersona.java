package vistas;

import controlador.PersonaControlador;
import excepciones.TelefonoInvalidoException;
import modelo.Persona;
import modelo.Propietario;
import modelo.Veterinario;
import DAO.MascotaDAO;
import modelo.Mascota;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class FormPersona extends JFrame {

    private final PersonaControlador controlador = new PersonaControlador();
    private final DefaultListModel<String> listModel = new DefaultListModel<>();

    private JComboBox<String> tipoCombo;
    private JTextField nombreField, idField, campoExtraField;
    private JLabel campoExtraLabel;
    private JList<String> listaPersonas; // Este componente se necesita para selección y eliminación por ID
    private JComboBox<String> comboMascotas;
    private JTable tablaPersonas;
    private DefaultTableModel tableModel;

    private final MascotaDAO mascotaDAO = new MascotaDAO();
    private String idOriginalSeleccionado = null;

    public FormPersona() {
        setTitle("👥 Gestión de Personas");
        setSize(700, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.decode("#F0F2F5")); // fondo suave general

        listaPersonas = new JList<>(listModel);
        listaPersonas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaPersonas.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        listaPersonas.setFixedCellHeight(28);
        listaPersonas.setBorder(BorderFactory.createLineBorder(new Color(0xCCCCCC)));
        listaPersonas.setSelectionBackground(new Color(0xD6EAF8));

        listaPersonas.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) cargarPersonaSeleccionada();
        });

        add(crearFormulario(), BorderLayout.NORTH);
        add(crearLista(), BorderLayout.CENTER);
        add(crearBotonera(), BorderLayout.SOUTH);

        actualizarLista();
    }

    private JPanel crearFormulario() {
        JPanel panel = new JPanel(new GridLayout(6, 2, 12, 12));
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY),
                "Datos de Persona ", 0, 0, new Font("Segoe UI", Font.BOLD, 16), Color.DARK_GRAY));
        panel.setBackground(Color.WHITE);
        panel.setPreferredSize(new Dimension(700, 200));

        Font labelFont = new Font("Segoe UI", Font.PLAIN, 14);

        tipoCombo = new JComboBox<>(new String[]{"Propietario", "Veterinario"});
        tipoCombo.setFont(labelFont);

        nombreField = new JTextField(); nombreField.setFont(labelFont);
        idField = new JTextField(); idField.setFont(labelFont);
        campoExtraField = new JTextField(); campoExtraField.setFont(labelFont);

        campoExtraLabel = new JLabel("Teléfono", SwingConstants.RIGHT);
        campoExtraLabel.setFont(labelFont);

        comboMascotas = new JComboBox<>();
        comboMascotas.setFont(labelFont);
        llenarComboMascotas();

        tipoCombo.addActionListener(e -> actualizarEtiquetaCampoExtra());

        panel.add(new JLabel("Tipo:", SwingConstants.RIGHT)).setFont(labelFont);
        panel.add(tipoCombo);
        panel.add(new JLabel("Nombre:", SwingConstants.RIGHT)).setFont(labelFont);
        panel.add(nombreField);
        panel.add(new JLabel("Identificación:", SwingConstants.RIGHT)).setFont(labelFont);
        panel.add(idField);
        panel.add(campoExtraLabel);
        panel.add(campoExtraField);
        panel.add(new JLabel("A cargo de:", SwingConstants.RIGHT)).setFont(labelFont);
        panel.add(comboMascotas);

        return panel;
    }


    private JScrollPane crearLista() {
        String[] columnas = {"Tipo", "Nombre", "ID", "Extra", "Mascota a Cargo"};
        tableModel = new DefaultTableModel(columnas, 0) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        };

        tablaPersonas = new JTable(tableModel);
        tablaPersonas.setRowHeight(28);
        tablaPersonas.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tablaPersonas.setSelectionBackground(new Color(0xD1F2EB));
        tablaPersonas.setSelectionForeground(Color.BLACK);
        tablaPersonas.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        tablaPersonas.getTableHeader().setBackground(new Color(0xAED6F1));
        tablaPersonas.setGridColor(new Color(220, 220, 220));

        tablaPersonas.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) cargarPersonaSeleccionadaDesdeTabla();
        });

        JScrollPane scroll = new JScrollPane(tablaPersonas);
        scroll.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY),
                "Personas Registradas", 0, 0, new Font("Segoe UI", Font.BOLD, 15), Color.DARK_GRAY));
        scroll.getViewport().setBackground(Color.WHITE);
        return scroll;
    }


    private JPanel crearBotonera() {
        JButton btnGuardar = crearBoton("Guardar", new Color(47, 57, 15));
        btnGuardar.addActionListener(e -> guardarPersona());

        JButton btnActualizar = crearBoton("Actualizar", new Color(221, 75, 212));
        btnActualizar.addActionListener(e -> actualizarPersona());

        JButton btnEliminar = crearBoton("Eliminar", new Color(204, 0, 0));
        btnEliminar.addActionListener(e -> eliminarSeleccionada());

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 15));
        panel.setBackground(Color.decode("#F0F2F5"));
        panel.add(btnGuardar);
        panel.add(btnActualizar);
        panel.add(btnEliminar);

        return panel;
    }

    private JButton crearBoton(String texto, Color colorFondo) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setForeground(Color.WHITE);
        btn.setBackground(colorFondo);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
        return btn;
    }


    private void actualizarEtiquetaCampoExtra() {
        String tipo = (String) tipoCombo.getSelectedItem();
        campoExtraLabel.setText(tipo.equals("Propietario") ? "Teléfono" : "Especialidad");
    }

    private void guardarPersona() {
        String tipo = (String) tipoCombo.getSelectedItem();
        String nombre = nombreField.getText().trim();
        String id = idField.getText().trim();
        String extra = campoExtraField.getText().trim();

        if (nombre.isEmpty() || id.isEmpty() || extra.isEmpty()) {
            JOptionPane.showMessageDialog(this, "❌ Todos los campos son obligatorios");
            return;
        }

        if (controlador.buscarPorIdentificacion(id) != null) {
            JOptionPane.showMessageDialog(this, "⚠️ Ya existe una persona con esta identificación.");
            return;
        }

        try {
            Persona persona;
            if (tipo.equals("Propietario")) {
                persona = new Propietario(nombre, id, extra);
            } else {
                persona = new Veterinario(nombre, id, extra);
            }

            String nombreMascota = (String) comboMascotas.getSelectedItem();
            if (nombreMascota != null) {
                Mascota mascota = mascotaDAO.buscarPorNombre(nombreMascota);
                persona.setMascotaACargo(mascota);
            }

            controlador.agregar(persona);
            limpiarCampos();
            actualizarLista();

        } catch (TelefonoInvalidoException e) {
            JOptionPane.showMessageDialog(this, "❌ " + e.getMessage(), "Error de Teléfono", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "⚠️ Error inesperado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarPersona() {
        String tipo = (String) tipoCombo.getSelectedItem();
        String nombre = nombreField.getText().trim();
        String nuevoId = idField.getText().trim();
        String extra = campoExtraField.getText().trim();

        if (nombre.isEmpty() || nuevoId.isEmpty() || extra.isEmpty()) {
            JOptionPane.showMessageDialog(this, "❌ Todos los campos son obligatorios");
            return;
        }

        if (idOriginalSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "⚠️ Debes seleccionar una persona de la lista para actualizarla.");
            return;
        }

        if (!nuevoId.equals(idOriginalSeleccionado) && controlador.buscarPorIdentificacion(nuevoId) != null) {
            JOptionPane.showMessageDialog(this, "⚠️ El nuevo ID ya está en uso por otra persona.");
            return;
        }

        try {
            Persona persona;
            if (tipo.equals("Propietario")) {
                persona = new Propietario(nombre, nuevoId, extra);
            } else {
                persona = new Veterinario(nombre, nuevoId, extra);
            }

            String nombreMascota = (String) comboMascotas.getSelectedItem();
            if (nombreMascota != null) {
                Mascota mascota = mascotaDAO.buscarPorNombre(nombreMascota);
                persona.setMascotaACargo(mascota);
            }

            boolean actualizado = controlador.actualizarPersona(idOriginalSeleccionado, persona);
            if (actualizado) {
                JOptionPane.showMessageDialog(this, "✅ Persona actualizada");
                actualizarLista();
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "❌ No se pudo actualizar la persona");
            }

        } catch (TelefonoInvalidoException e) {
            JOptionPane.showMessageDialog(this, "❌ " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "⚠️ Error inesperado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarSeleccionada() {
        int index = listaPersonas.getSelectedIndex();
        if (index != -1) {
            String seleccion = listModel.getElementAt(index);
            String id = seleccion.substring(seleccion.indexOf('(') + 1, seleccion.indexOf(')'));
            controlador.eliminarPorIdentificacion(id);
            actualizarLista();
            limpiarCampos();
        }
    }

    private void cargarPersonaSeleccionada() {
        int index = listaPersonas.getSelectedIndex();
        if (index != -1) {
            String seleccion = listModel.getElementAt(index);
            String id = seleccion.substring(seleccion.indexOf('(') + 1, seleccion.indexOf(')'));
            Persona p = controlador.buscarPorIdentificacion(id);
            if (p != null) {
                cargarDatosPersona(p);
            }
        }
    }

    private void cargarPersonaSeleccionadaDesdeTabla() {
        int fila = tablaPersonas.getSelectedRow();
        if (fila != -1) {
            String id = (String) tableModel.getValueAt(fila, 2);
            Persona p = controlador.buscarPorIdentificacion(id);
            if (p != null) {
                cargarDatosPersona(p);
            }
        }
    }

    private void cargarDatosPersona(Persona p) {
        nombreField.setText(p.getNombre());
        idField.setText(p.getIdentificacion());
        idField.setEditable(true);
        idOriginalSeleccionado = p.getIdentificacion();

        if (p instanceof Propietario) {
            tipoCombo.setSelectedItem("Propietario");
            campoExtraField.setText(((Propietario) p).getTelefono());
        } else if (p instanceof Veterinario) {
            tipoCombo.setSelectedItem("Veterinario");
            campoExtraField.setText(((Veterinario) p).getEspecialidad());
        }

        Mascota mascota = p.getMascotaACargo();
        if (mascota != null) {
            comboMascotas.setSelectedItem(mascota.getNombre());
        } else {
            comboMascotas.setSelectedIndex(-1);
        }

        actualizarEtiquetaCampoExtra();
    }

    private void actualizarLista() {
        tableModel.setRowCount(0);
        List<Persona> personas = controlador.listar();
        for (Persona p : personas) {
            String tipo = (p instanceof Propietario) ? "Propietario" : "Veterinario";
            String extra = (p instanceof Propietario) ? ((Propietario) p).getTelefono() : ((Veterinario) p).getEspecialidad();
            String mascota = (p.getMascotaACargo() != null) ? p.getMascotaACargo().getNombre() : "Ninguna";
            tableModel.addRow(new Object[]{tipo, p.getNombre(), p.getIdentificacion(), extra, mascota});
        }
    }

    private void limpiarCampos() {
        nombreField.setText("");
        idField.setText("");
        idField.setEditable(true);
        campoExtraField.setText("");
        idOriginalSeleccionado = null;
    }

    private void llenarComboMascotas() {
        comboMascotas.removeAllItems();
        List<Mascota> mascotas = mascotaDAO.listar();
        for (Mascota m : mascotas) {
            comboMascotas.addItem(m.getNombre());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FormPersona().setVisible(true));
    }
}
