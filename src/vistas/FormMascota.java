package vistas;

import controlador.MascotaControlador;
import DTO.MascotaDTO;
import excepciones.DatoInvalidoException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class FormMascota extends JFrame {

    // === Componentes de la interfaz ===
    private JTextField txtNombre, txtEspecie, txtEdad; // Campos de texto para nombre, especie y edad
    private JPasswordField txtClave; // Campo para la clave
    private MascotaControlador controlador; // Controlador encargado de manejar la lógica
    private JTable tablaMascotas; // Tabla que muestra las mascotas registradas
    private DefaultTableModel modeloTabla; // Modelo para la tabla
    private String claveOriginalSeleccionada = null;

    // === Constructor de la ventana principal ===
    public FormMascota() {
        controlador = new MascotaControlador(); // Inicializa el controlador

        // Configura la ventana
        setTitle("🐾 Gestión de Mascotas - Clínica Veterinaria");
        setSize(700, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // === Panel principal con layout vertical ===
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));

        // === Panel de entrada con formulario para datos ===
        JPanel panelEntrada = new JPanel(new GridBagLayout());
        panelEntrada.setBorder(BorderFactory.createTitledBorder("Datos de la mascota"));
        panelEntrada.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0; gbc.gridy = 0;

        // Campo: Nombre
        panelEntrada.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1;
        txtNombre = new JTextField(15);
        panelEntrada.add(txtNombre, gbc);

        // Campo: Especie
        gbc.gridx = 0; gbc.gridy++;
        panelEntrada.add(new JLabel("Especie:"), gbc);
        gbc.gridx = 1;
        txtEspecie = new JTextField(15);
        panelEntrada.add(txtEspecie, gbc);

        // Campo: Edad
        gbc.gridx = 0; gbc.gridy++;
        panelEntrada.add(new JLabel("Edad:"), gbc);
        gbc.gridx = 1;
        txtEdad = new JTextField(15);
        panelEntrada.add(txtEdad, gbc);

        // Campo: Clave
        gbc.gridx = 0; gbc.gridy++;
        panelEntrada.add(new JLabel("Clave:"), gbc);
        gbc.gridx = 1;
        txtClave = new JPasswordField(15);
        panelEntrada.add(txtClave, gbc);

        // Añade el panel de entrada al principal
        panelPrincipal.add(panelEntrada);

        // === Panel de botones con acciones ===
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelBotones.setBackground(new Color(240, 248, 255));

        // Botón: Guardar
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> guardar());
        panelBotones.add(btnGuardar);

        // Botón: Actualizar
        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.addActionListener(e -> actualizar());
        panelBotones.add(btnActualizar);

        // Botón: Eliminar
        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(e -> eliminar());
        panelBotones.add(btnEliminar);

        // Añade el panel de botones al principal
        panelPrincipal.add(panelBotones);

        // === Tabla de mascotas registradas ===
        String[] columnas = {"#", "Nombre", "Especie", "Edad"}; // Encabezados de tabla
        modeloTabla = new DefaultTableModel(columnas, 0); // Modelo vacío inicialmente
        tablaMascotas = new JTable(modeloTabla); // ✅ Inicializas la tabla aquí
        tablaMascotas.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                cargarMascotaSeleccionada();
            }
        });
        JScrollPane scrollTabla = new JScrollPane(tablaMascotas); // Scroll para tabla
        scrollTabla.setBorder(BorderFactory.createTitledBorder("Mascotas registradas")); // Borde con título
        panelPrincipal.add(scrollTabla); // Agrega la tabla al panel
        add(panelPrincipal); // Agrega todo al JFrame
        actualizarTabla(); // Carga los datos al iniciar
    }

    // === Método para guardar una nueva mascota ===
    private void guardar() {
        try {
            MascotaDTO dto = capturarDatos(); // Captura los datos del formulario
            String mensaje = controlador.agregarMascota(dto); // Llama al controlador para agregar
            mensaje(mensaje); // Muestra mensaje al usuario
            limpiar(); // Limpia los campos
            actualizarTabla(); // Refresca la tabla
        } catch (DatoInvalidoException ex) {
            mostrarError(ex.getMessage()); // Muestra error de validación
        } catch (Exception ex) {
            mostrarError("Error al guardar: " + ex.getMessage()); // Error inesperado
        }
    }

    // === Método para actualizar una mascota seleccionada en la tabla ===
    private void actualizar() {
        try {
            if (claveOriginalSeleccionada == null) {
                mostrarError("Selecciona una mascota en la tabla para actualizar.");
                return;
            }

            MascotaDTO dto = capturarDatos();
            String mensaje = controlador.actualizarPorClave(claveOriginalSeleccionada, dto); // Usar clave
            mensaje(mensaje);
            limpiar();
            actualizarTabla();
            claveOriginalSeleccionada = null;

        } catch (DatoInvalidoException ex) {
            mostrarError(ex.getMessage());
        }
    }

    // === Método para eliminar una mascota seleccionada en la tabla ===
    private void eliminar() {
        int filaSeleccionada = tablaMascotas.getSelectedRow();
        if (filaSeleccionada == -1) {
            mostrarError("Selecciona una mascota en la tabla para eliminar.");
            return;
        }

        String mensaje = controlador.eliminarEntidad(filaSeleccionada); // Elimina en el controlador
        mensaje(mensaje);
        limpiar();
        actualizarTabla();
    }

    // === Método para actualizar el contenido de la tabla ===
    private void actualizarTabla() {
        modeloTabla.setRowCount(0); // Limpia las filas existentes
        List<MascotaDTO> lista = controlador.obtenerEntidades(); // Obtiene lista actualizada
        int i = 0;
        for (MascotaDTO m : lista) {
            // Añade cada mascota a la tabla
            modeloTabla.addRow(new Object[]{i++, m.getNombre(), m.getEspecie(), m.getEdad()});
        }
    }

    // === Método que captura y valida los datos del formulario ===
    private MascotaDTO capturarDatos() throws DatoInvalidoException {
        String nombre = txtNombre.getText().trim();
        String especie = txtEspecie.getText().trim();
        String edadStr = txtEdad.getText().trim();
        String clave = txtClave.getText().trim();

        // Verifica que ningún campo esté vacío (excepto clave si quieres hacerlo opcional)
        if (nombre.isEmpty() || especie.isEmpty() || edadStr.isEmpty()) {
            throw new DatoInvalidoException("Todos los campos son obligatorios");
        }

        int edad;
        try {
            edad = Integer.parseInt(edadStr); // Convierte la edad a entero
        } catch (NumberFormatException e) {
            throw new DatoInvalidoException("La edad debe ser un número válido");
        }

        if (edad < 0) {
            throw new DatoInvalidoException("La edad no puede ser negativa");
        }

        // Crea y retorna un nuevo DTO con los datos capturados
        return new MascotaDTO(nombre, especie, edad, clave);
    }

    // === Muestra mensaje informativo ===
    private void mensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    // === Muestra mensaje de error ===
    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    // === Limpia los campos del formulario ===
    private void limpiar() {
        txtNombre.setText("");
        txtEspecie.setText("");
        txtEdad.setText("");
        txtClave.setText("");
    }

    // Carga los datos de la mascota seleccionada en los campos del formulario
    private void cargarMascotaSeleccionada() {
        int fila = tablaMascotas.getSelectedRow();
        if (fila != -1) {
            // Obtenemos la lista REAL con claves verdaderas
            List<MascotaDTO> listaReal = controlador.obtenerEntidadesConClaveReal();
            if (fila < listaReal.size()) {
                MascotaDTO m = listaReal.get(fila);

                txtNombre.setText(m.getNombre());
                txtEspecie.setText(m.getEspecie());
                txtEdad.setText(String.valueOf(m.getEdad()));
                txtClave.setText(m.getClave());
                claveOriginalSeleccionada = m.getClave();  //Guardamos clave real
            }
        }
    }

    // === Método principal para lanzar la ventana ===
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FormMascota().setVisible(true));
    }
}
