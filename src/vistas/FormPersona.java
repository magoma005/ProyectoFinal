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

    // Controlador principal que maneja las operaciones sobre personas
    private final PersonaControlador controlador = new PersonaControlador();

    // Modelo de lista que se usa para llenar la JList de personas
    private final DefaultListModel<String> listModel = new DefaultListModel<>();

    // Componentes del formulario
    private JComboBox<String> tipoCombo;
    private JTextField nombreField, idField, campoExtraField;
    private JLabel campoExtraLabel;
    private JList<String> listaPersonas;
    private JComboBox<String> comboMascotas;

    // DAO para acceder a la información de mascotas
    private final MascotaDAO mascotaDAO = new MascotaDAO();

    // Variable que guarda el ID original de una persona seleccionada para actualizarla correctamente
    private String idOriginalSeleccionado = null;

    // Constructor principal del formulario
    public FormPersona() {
        setTitle("👥 Gestión de Personas");
        setSize(520, 520);
        setLocationRelativeTo(null); // Centra la ventana
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Cierra solo esta ventana
        setLayout(new BorderLayout());

        // Agrega los paneles al formulario
        add(crearFormulario(), BorderLayout.NORTH);
        add(crearLista(), BorderLayout.CENTER);
        add(crearBotonera(), BorderLayout.SOUTH);

        // Llena la lista con personas existentes
        actualizarLista();

        // Agrega un listener para detectar cuando se selecciona una persona de la lista
        listaPersonas.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                cargarPersonaSeleccionada();
            }
        });
    }

    // Crea el panel del formulario de datos personales
    private JPanel crearFormulario() {
        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10)); // 6 filas, 2 columnas
        panel.setBorder(BorderFactory.createTitledBorder(" Datos de Persona"));

        tipoCombo = new JComboBox<>(new String[]{"Propietario", "Veterinario"});
        nombreField = new JTextField();
        idField = new JTextField();
        campoExtraField = new JTextField();
        campoExtraLabel = new JLabel("Teléfono"); // Por defecto, para propietarios

        // Combo que muestra las mascotas disponibles
        comboMascotas = new JComboBox<>();
        llenarComboMascotas(); // Carga las mascotas en el combo

        // Listener que cambia la etiqueta del campo extra según el tipo de persona
        tipoCombo.addActionListener(e -> actualizarEtiquetaCampoExtra());

        // Agrega los componentes al panel
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

    // Crea el panel que contiene la lista de personas registradas
    private JScrollPane crearLista() {
        listaPersonas = new JList<>(listModel);
        listaPersonas.setBorder(BorderFactory.createTitledBorder("📃 Personas registradas"));
        return new JScrollPane(listaPersonas);
    }

    // Crea el panel con los botones de acción: Guardar, Actualizar, Eliminar
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

    // Cambia la etiqueta del campo extra dependiendo si es Propietario o Veterinario
    private void actualizarEtiquetaCampoExtra() {
        String tipo = (String) tipoCombo.getSelectedItem();
        campoExtraLabel.setText(tipo.equals("Propietario") ? "Teléfono" : "Especialidad");
    }

    // Lógica para guardar una nueva persona
    private void guardarPersona() {
        String tipo = (String) tipoCombo.getSelectedItem();
        String nombre = nombreField.getText().trim();
        String id = idField.getText().trim();
        String extra = campoExtraField.getText().trim();

        // Validación de campos vacíos
        if (nombre.isEmpty() || id.isEmpty() || extra.isEmpty()) {
            JOptionPane.showMessageDialog(this, "❌ Todos los campos son obligatorios");
            return;
        }

        // Verifica si ya existe una persona con el mismo ID
        if (controlador.buscarPorIdentificacion(id) != null) {
            JOptionPane.showMessageDialog(this, "⚠️ Ya existe una persona con esta identificación.");
            return;
        }

        try {
            // Crea un objeto Persona según el tipo
            Persona persona;
            if (tipo.equals("Propietario")) {
                persona = new Propietario(nombre, id, extra);
            } else {
                persona = new Veterinario(nombre, id, extra);
            }

            // Asigna la mascota seleccionada si hay alguna
            String nombreMascota = (String) comboMascotas.getSelectedItem();
            if (nombreMascota != null) {
                Mascota mascota = mascotaDAO.buscarPorNombre(nombreMascota);
                persona.setMascotaACargo(mascota);
            }

            // Agrega la persona al sistema
            controlador.agregar(persona);
            limpiarCampos();
            actualizarLista();

        } catch (TelefonoInvalidoException e) {
            JOptionPane.showMessageDialog(this, "❌ " + e.getMessage(), "Error de Teléfono", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "⚠️ Error inesperado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Lógica para actualizar una persona ya existente
    private void actualizarPersona() {
        String tipo = (String) tipoCombo.getSelectedItem();
        String nombre = nombreField.getText().trim();
        String nuevoId = idField.getText().trim();
        String extra = campoExtraField.getText().trim();

        // Validación de campos vacíos
        if (nombre.isEmpty() || nuevoId.isEmpty() || extra.isEmpty()) {
            JOptionPane.showMessageDialog(this, "❌ Todos los campos son obligatorios");
            return;
        }

        // Verifica si se seleccionó una persona antes
        if (idOriginalSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "⚠️ Debes seleccionar una persona de la lista para actualizarla.");
            return;
        }

        // Verifica si el nuevo ID ya está siendo usado por otra persona
        if (!nuevoId.equals(idOriginalSeleccionado) && controlador.buscarPorIdentificacion(nuevoId) != null) {
            JOptionPane.showMessageDialog(this, "⚠️ El nuevo ID ya está en uso por otra persona.");
            return;
        }

        try {
            // Crea la nueva persona actualizada
            Persona persona;
            if (tipo.equals("Propietario")) {
                persona = new Propietario(nombre, nuevoId, extra);
            } else {
                persona = new Veterinario(nombre, nuevoId, extra);
            }

            // Asigna mascota si hay seleccionada
            String nombreMascota = (String) comboMascotas.getSelectedItem();
            if (nombreMascota != null) {
                Mascota mascota = mascotaDAO.buscarPorNombre(nombreMascota);
                persona.setMascotaACargo(mascota);
            }

            // Actualiza la persona en el sistema
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

    // Elimina la persona seleccionada en la lista
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

    // Carga los datos de la persona seleccionada en el formulario
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

                // Muestra la mascota a cargo en el combo
                Mascota mascota = p.getMascotaACargo();
                if (mascota != null) {
                    comboMascotas.setSelectedItem(mascota.getNombre());
                } else {
                    comboMascotas.setSelectedIndex(-1);
                }

                actualizarEtiquetaCampoExtra();
            }
        }
    }

    // Actualiza la lista visual de personas
    private void actualizarLista() {
        listModel.clear();
        List<Persona> personas = controlador.listar();
        for (Persona p : personas) {
            listModel.addElement(p.toString()); // Usa el toString de la persona
        }
    }

    // Limpia todos los campos del formulario
    private void limpiarCampos() {
        nombreField.setText("");
        idField.setText("");
        idField.setEditable(true);
        campoExtraField.setText("");
        idOriginalSeleccionado = null;
    }

    // Llena el combo con los nombres de mascotas existentes
    private void llenarComboMascotas() {
        comboMascotas.removeAllItems();
        List<Mascota> mascotas = mascotaDAO.listar();
        for (Mascota m : mascotas) {
            comboMascotas.addItem(m.getNombre());
        }
    }

    // Método principal para ejecutar la ventana
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FormPersona().setVisible(true));
    }
}
