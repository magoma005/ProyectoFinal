package vistas;

import controlador.MascotaControlador;
import DTO.MascotaDTO;
import excepciones.DatoInvalidoException;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class FormMascota extends JFrame {

    private JTextField txtNombre, txtEspecie, txtEdad;
    private JPasswordField txtClave;
    private JTextArea txtListado;
    private MascotaControlador controlador;

    public FormMascota() {
        controlador = new MascotaControlador();

        setTitle("🐾 Gestión de Mascotas - Clínica Veterinaria");
        setSize(700, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // === Layout principal: BoxLayout vertical ===
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));

        // === Panel de entrada ===
        JPanel panelEntrada = new JPanel(new GridBagLayout());
        panelEntrada.setBorder(BorderFactory.createTitledBorder("Datos de la mascota"));
        panelEntrada.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0; gbc.gridy = 0;

        panelEntrada.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1;
        txtNombre = new JTextField(15);
        panelEntrada.add(txtNombre, gbc);

        gbc.gridx = 0; gbc.gridy++;
        panelEntrada.add(new JLabel("Especie:"), gbc);
        gbc.gridx = 1;
        txtEspecie = new JTextField(15);
        panelEntrada.add(txtEspecie, gbc);

        gbc.gridx = 0; gbc.gridy++;
        panelEntrada.add(new JLabel("Edad:"), gbc);
        gbc.gridx = 1;
        txtEdad = new JTextField(15);
        panelEntrada.add(txtEdad, gbc);

        gbc.gridx = 0; gbc.gridy++;
        panelEntrada.add(new JLabel("Clave:"), gbc);
        gbc.gridx = 1;
        txtClave = new JPasswordField(15);
        panelEntrada.add(txtClave, gbc);


        panelPrincipal.add(panelEntrada);

        // === Panel de botones ===
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelBotones.setBackground(new Color(240, 248, 255));

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> guardar());
        panelBotones.add(btnGuardar);

        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.addActionListener(e -> actualizar());
        panelBotones.add(btnActualizar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(e -> eliminar());
        panelBotones.add(btnEliminar);

        JButton btnListar = new JButton("Listar");
        btnListar.addActionListener(e -> listar());
        panelBotones.add(btnListar);

        panelPrincipal.add(panelBotones);

        // === Área de listado ===
        txtListado = new JTextArea(10, 50);
        txtListado.setEditable(false);
        txtListado.setFont(new Font("Consolas", Font.PLAIN, 13));
        txtListado.setBackground(new Color(245, 245, 245));

        JScrollPane scroll = new JScrollPane(txtListado);
        scroll.setBorder(BorderFactory.createTitledBorder("Listado de mascotas"));
        panelPrincipal.add(scroll);

        add(panelPrincipal);
    }

    private void guardar() {
        try {
            MascotaDTO dto = capturarDatos();
            String mensaje = controlador.agregarMascota(dto);
            mensaje(mensaje);
            limpiar();
        } catch (DatoInvalidoException ex) {
            mostrarError(ex.getMessage());
        } catch (Exception ex) {
            mostrarError("Error al guardar: " + ex.getMessage());
        }
    }

    private void actualizar() {
        try {
            int indice = Integer.parseInt(JOptionPane.showInputDialog(this, "Ingrese el índice de la mascota a actualizar:"));
            MascotaDTO dto = capturarDatos();
            String mensaje = controlador.actualizarEntidad(indice, dto);
            mensaje(mensaje);
            limpiar();
        } catch (NumberFormatException ex) {
            mostrarError("❌ Índice inválido.");
        } catch (DatoInvalidoException ex) {
            mostrarError(ex.getMessage());
        }
    }

    private void eliminar() {
        try {
            int indice = Integer.parseInt(JOptionPane.showInputDialog(this, "Ingrese el índice de la mascota a eliminar:"));
            String mensaje = controlador.eliminarEntidad(indice);
            mensaje(mensaje);
            limpiar();
        } catch (NumberFormatException ex) {
            mostrarError("❌ Índice inválido.");
        }
    }

    private void listar() {
        List<MascotaDTO> lista = controlador.obtenerEntidades();
        txtListado.setText("");
        int i = 0;
        for (MascotaDTO m : lista) {
            txtListado.append((i++) + ". " + m.getNombre() + " | " + m.getEspecie() + " | " + m.getEdad() + "\n");
        }
    }

    private MascotaDTO capturarDatos() throws DatoInvalidoException {
        String nombre = txtNombre.getText().trim();
        String especie = txtEspecie.getText().trim();
        String edadStr = txtEdad.getText().trim();
        String clave = txtClave.getText().trim();

        if (nombre.isEmpty() || especie.isEmpty() || edadStr.isEmpty()) {
            throw new DatoInvalidoException("Todos los campos son obligatorios");
        }

        int edad;
        try {
            edad = Integer.parseInt(edadStr);
        } catch (NumberFormatException e) {
            throw new DatoInvalidoException("La edad debe ser un número válido");
        }

        if (edad < 0) {
            throw new DatoInvalidoException("La edad no puede ser negativa");
        }

        return new MascotaDTO(nombre, especie, edad, clave);
    }

    private void mensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void limpiar() {
        txtNombre.setText("");
        txtEspecie.setText("");
        txtEdad.setText("");
        txtClave.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FormMascota().setVisible(true));
    }
}
