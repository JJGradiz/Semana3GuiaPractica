package ni.edu.uam.registropulperia.controller;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import ni.edu.uam.registropulperia.modelos.Producto;

import java.util.ArrayList;
import java.util.List;

public class InventarioController {

    @FXML
    private TextField txtCodigo;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtCategoria;

    @FXML
    private TextField txtPrecio;

    @FXML
    private TextField txtCantidad;

    @FXML
    private TextField txtBuscar;

    @FXML
    private Label lblResultado;

    @FXML
    private Label lblInfoProducto;

    @FXML
    private Label lblTotalProductos;

    @FXML
    private Label lblValorInventario;

    private List<Producto> inventario = new ArrayList<>();

    @FXML
    private void guardarProducto(ActionEvent event) {
        try {
            if (camposVacios()) {
                mostrarAlerta("Error", "Todos los campos son obligatorios.");
                return;
            }

            String codigo = txtCodigo.getText().trim();
            String nombre = txtNombre.getText().trim();
            String categoria = txtCategoria.getText().trim();
            double precio = Double.parseDouble(txtPrecio.getText());
            int cantidad = Integer.parseInt(txtCantidad.getText());

            Producto producto = new Producto(codigo, nombre, categoria, precio, cantidad);

            if (!producto.hayStock()) {
                mostrarAlerta("Advertencia", "Producto registrado sin stock.");
            }

            inventario.add(producto);
            actualizarEstadisticas();

            lblResultado.setText("✓ Producto guardado exitosamente");
            lblResultado.setStyle("-fx-text-fill: green; -fx-font-weight: bold;");
            lblInfoProducto.setText(producto.toString());

            limpiarCampos();

        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "Ingrese valores numéricos válidos para precio y cantidad.");
        } catch (IllegalArgumentException e) {
            mostrarAlerta("Error", e.getMessage());
        }
    }

    @FXML
    private void buscarProducto(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            String codigoBuscar = txtBuscar.getText().trim();

            if (codigoBuscar.isEmpty()) {
                lblResultado.setText(" Ingrese un código para buscar");
                lblResultado.setStyle("-fx-text-fill: orange;");
                lblInfoProducto.setText("");
                return;
            }

            for (Producto producto : inventario) {
                if (producto.getCodigo().equalsIgnoreCase(codigoBuscar)) {
                    lblResultado.setText("✓ Producto encontrado");
                    lblResultado.setStyle("-fx-text-fill: green; -fx-font-weight: bold;");
                    lblInfoProducto.setText(producto.toString());
                    return;
                }
            }

            lblResultado.setText("✗ Producto no encontrado");
            lblResultado.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
            lblInfoProducto.setText("");
        }
    }

    @FXML
    private void limpiarCampos(ActionEvent event) {
        txtCodigo.clear();
        txtNombre.clear();
        txtCategoria.clear();
        txtPrecio.clear();
        txtCantidad.clear();
        txtBuscar.clear();
        lblResultado.setText("");
        lblInfoProducto.setText("");
    }

    @FXML
    private void mostrarSinStock(ActionEvent event) {
        StringBuilder sb = new StringBuilder("PRODUCTOS SIN STOCK:\n\n");
        int count = 0;

        for (Producto p : inventario) {
            if (!p.hayStock()) {
                sb.append("- ").append(p.getNombre()).append(" (").append(p.getCodigo()).append(")\n");
                count++;
            }
        }

        if (count == 0) {
            lblResultado.setText("Todos los productos tienen stock disponible");
            lblResultado.setStyle("-fx-text-fill: green;");
        } else {
            lblResultado.setText(sb.toString());
            lblResultado.setStyle("-fx-text-fill: red;");
        }
    }

    private void actualizarEstadisticas() {
        int totalProductos = inventario.size();
        double valorTotal = 0;

        for (Producto p : inventario) {
            valorTotal += p.getPrecio() * p.getCantidad();
        }

        lblTotalProductos.setText("Total productos: " + totalProductos);
        lblValorInventario.setText("Valor inventario: C$ " + String.format("%.2f", valorTotal));
    }

    private boolean camposVacios() {
        return txtCodigo.getText().isEmpty() ||
                txtNombre.getText().isEmpty() ||
                txtCategoria.getText().isEmpty() ||
                txtPrecio.getText().isEmpty() ||
                txtCantidad.getText().isEmpty();
    }

    private void limpiarCampos() {
        txtCodigo.clear();
        txtNombre.clear();
        txtCategoria.clear();
        txtPrecio.clear();
        txtCantidad.clear();
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}