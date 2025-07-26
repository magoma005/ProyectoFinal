package vistas;

import controlador.PersonaControlador;
import excepciones.TelefonoInvalidoException;
import modelo.Persona;
import modelo.Propietario;
import modelo.Veterinario;
import DAO.MascotaDAO;
import modelo.Mascota;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class FormPersona extends JFrame {
    private final PersonaControlador controlador = new PersonaControlador(); // Controlador principal
    private final DefaultListModel<String> listModel = new DefaultListModel<>(); // Modelo para la lista de personas

    private JComboBox<String> tipoCombo;
    private JTextField nombreField, idField, campoExtraField;
    private JLabel campoExtraLabel;
    private JList<String> listaPersonas;
    private JComboBox<String> comboMascotas;
    private final MascotaDAO mascotaDAO = new MascotaDAO();

    // Variable auxiliar para guardar el ID original al seleccionar una persona (necesario para actualizar correctamente)
    private String idOriginalSeleccionado = null;

    public FormPersona() {
        setTitle("👥 Gestión de Personas");
        setSize(520, 520);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        add(crearFormulario(), BorderLayout.NORTH);
        add(crearLista(), BorderLayout.CENTER);
        add(crearBotonera(), BorderLayout.SOUTH);

        actualizarLista();

        // Escucha cambios en la selección de la lista
        listaPersonas.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                cargarPersonaSeleccionada();
            }
        });
    }

    private JPanel crearFormulario() {
        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10)); // Aumenta a 6 filas
        panel.setBorder(BorderFactory.createTitledBorder("📋 Datos de Persona"));

        tipoCombo = new JComboBox<>(new String[]{"Propietario", "Veterinario"});
        nombreField = new JTextField();
        idField = new JTextField();
        campoExtraField = new JTextField();
        campoExtraLabel = new JLabel("Teléfono");

        // Combo de mascotas disponibles
        comboMascotas = new JComboBox<>();
        llenarComboMascotas(); // Método para llenar las mascotas disponibles

        tipoCombo.addActionListener(e -> actualizarEtiquetaCampoExtra());

        panel.add(new JLabel("Tipo:"));
        panel.add(tipoCombo);
        panel.add(new JLabel("Nombre:"));
        panel.add(nombreField);
        panel.add(new JLabel("Identificación:"));
        panel.add(idField);
        panel.add(campoExtraLabel);
        panel.add(campoExtraField);
        panel.add(new JLabel("A cargo de:"));
        panel.add(comboMascotas);

        return panel;
    }

    private JScrollPane crearLista() {
        listaPersonas = new JList<>(listModel);
        listaPersonas.setBorder(BorderFactory.createTitledBorder("📃 Personas registradas"));
        return new JScrollPane(listaPersonas);
    }

    private JPanel crearBotonera() {
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> guardarPersona());

        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.addActionListener(e -> actualizarPersona());

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(e -> eliminarSeleccionada());

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panel.add(btnGuardar);
        panel.add(btnActualizar);
        panel.add(btnEliminar);
        return panel;
    }

    // Cambia la etiqueta del campo extra según el tipo seleccionado
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
            // Mascota a cargo
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
        String nuevoId = idField.getText().trim(); // ID actual en el campo
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
            // Mascota seleccionada
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

                // 👉 Mostrar la mascota a cargo en el combo
                Mascota mascota = p.getMascotaACargo();
                if (mascota != null) {
                    comboMascotas.setSelectedItem(mascota.getNombre());
                } else {
                    comboMascotas.setSelectedIndex(-1); // Ninguna seleccionada
                }

                actualizarEtiquetaCampoExtra();
            }
        }
    }


    private void actualizarLista() {
        listModel.clear();
        List<Persona> personas = controlador.listar();
        for (Persona p : personas) {
            listModel.addElement(p.toString());
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
            comboMascotas.addItem(m.getNombre()); // O puedes usar un toString más descriptivo
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FormPersona().setVisible(true));
    }
}
