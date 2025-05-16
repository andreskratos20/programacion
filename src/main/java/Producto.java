package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

 class GestionTienda extends JFrame {
    private JTextField txtNombre, txtPrecio, txtCantidad;
    private JTable tablaProductos;
    private DefaultTableModel modeloTabla;

    public GestionTienda() {
        setTitle("Gestión de Tienda");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centra la ventana
        setResizable(false);

        // Panel de entrada de datos (Norte)
        JPanel panelFormulario = new JPanel(new GridLayout(4, 2, 5, 5));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Nuevo Producto"));

        panelFormulario.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panelFormulario.add(txtNombre);

        panelFormulario.add(new JLabel("Precio:"));
        txtPrecio = new JTextField();
        panelFormulario.add(txtPrecio);

        panelFormulario.add(new JLabel("Cantidad:"));
        txtCantidad = new JTextField();
        panelFormulario.add(txtCantidad);

        JButton btnGuardar = new JButton("Guardar Producto");
        panelFormulario.add(btnGuardar);

        JButton btnEliminar = new JButton("Eliminar Producto");
        panelFormulario.add(btnEliminar);

        add(panelFormulario, BorderLayout.NORTH);

        // Tabla de productos (Centro)
        modeloTabla = new DefaultTableModel(new Object[]{"Nombre", "Precio", "Cantidad"}, 0);
        tablaProductos = new JTable(modeloTabla);
        add(new JScrollPane(tablaProductos), BorderLayout.CENTER);

        // Acción botón Guardar
        btnGuardar.addActionListener(e -> {
            String nombre = txtNombre.getText().trim();
            String precioStr = txtPrecio.getText().trim();
            String cantidadStr = txtCantidad.getText().trim();

            if (nombre.isEmpty() || precioStr.isEmpty() || cantidadStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                double precio = Double.parseDouble(precioStr);
                int cantidad = Integer.parseInt(cantidadStr);

                if (precio < 0 || cantidad < 0) {
                    throw new NumberFormatException();
                }

                modeloTabla.addRow(new Object[]{nombre, precio, cantidad});
                System.out.println("Producto guardado: " + nombre + ", Precio: " + precio + ", Cantidad: " + cantidad);

                // Limpiar campos
                txtNombre.setText("");
                txtPrecio.setText("");
                txtCantidad.setText("");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Precio y cantidad deben ser números positivos.", "Error de validación", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Acción botón Eliminar
        btnEliminar.addActionListener(e -> {
            int filaSeleccionada = tablaProductos.getSelectedRow();
            if (filaSeleccionada != -1) {
                modeloTabla.removeRow(filaSeleccionada);
                System.out.println("Producto eliminado.");
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un producto para eliminar.", "Error", JOptionPane.WARNING_MESSAGE);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GestionTienda().setVisible(true));
    }
}
// codigo terminado