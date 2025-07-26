package vistas;

import controlador.PersonaControlador;
import modelo.Persona;
import modelo.Propietario;
import modelo.Veterinario;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class FormPersona extends JFrame {
    private final PersonaControlador controlador = new PersonaControlador();
    private final DefaultListModel<String> listModel = new DefaultListModel<>();

    private JComboBox<String> tipoCombo;
    private JTextField nombreField, idField, campoExtraField;
    private JLabel campoExtraLabel;
    private JList<String> listaPersonas;

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

        listaPersonas.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                cargarPersonaSeleccionada();
            }
        });
    }

    private JPanel crearFormulario() {
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("📋 Datos de Persona"));

        tipoCombo = new JComboBox<>(new String[]{"Propietario", "Veterinario"});
        nombreField = new JTextField();
        idField = new JTextField();
        campoExtraField = new JTextField();
        campoExtraLabel = new JLabel("Teléfono");

        tipoCombo.addActionListener(e -> actualizarEtiquetaCampoExtra());

        panel.add(new JLabel("Tipo:"));
        panel.add(tipoCombo);
        panel.add(new JLabel("Nombre:"));
        panel.add(nombreField);
        panel.add(new JLabel("Identificación:"));
        panel.add(idField);
        panel.add(campoExtraLabel);
        panel.add(campoExtraField);

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

        Persona persona;
        if (tipo.equals("Propietario")) {
            persona = new Propietario(nombre, id, extra);
        } else {
            persona = new Veterinario(nombre, id, extra);
        }

        controlador.agregar(persona);
        limpiarCampos();
        actualizarLista();
    }

    private void actualizarPersona() {
        String tipo = (String) tipoCombo.getSelectedItem();
        String nombre = nombreField.getText().trim();
        String id = idField.getText().trim();
        String extra = campoExtraField.getText().trim();

        if (nombre.isEmpty() || id.isEmpty() || extra.isEmpty()) {
            JOptionPane.showMessageDialog(this, "❌ Todos los campos son obligatorios");
            return;
        }

        Persona persona;
        if (tipo.equals("Propietario")) {
            persona = new Propietario(nombre, id, extra);
        } else {
            persona = new Veterinario(nombre, id, extra);
        }

        boolean actualizado = controlador.actualizarPersona(id, persona);
        if (actualizado) {
            JOptionPane.showMessageDialog(this, "✅ Persona actualizada");
            actualizarLista();
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(this, "❌ No se encontró una persona con ese ID");
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
                idField.setEditable(false); // Para que no se edite la ID
                if (p instanceof Propietario) {
                    tipoCombo.setSelectedItem("Propietario");
                    campoExtraField.setText(((Propietario) p).getTelefono());
                } else if (p instanceof Veterinario) {
                    tipoCombo.setSelectedItem("Veterinario");
                    campoExtraField.setText(((Veterinario) p).getEspecialidad());
                }
                actualizarEtiquetaCampoExtra();
            }
        }
    }

    private void actualizarLista() {
        listModel.clear();
        List<Persona> personas = controlador.listar();
        for (Persona p : personas) {
            listModel.addElement(p.toString()); // Ya debería mostrar con nombre e ID
        }
    }

    private void limpiarCampos() {
        nombreField.setText("");
        idField.setText("");
        idField.setEditable(true);
        campoExtraField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FormPersona().setVisible(true));
    }
}
