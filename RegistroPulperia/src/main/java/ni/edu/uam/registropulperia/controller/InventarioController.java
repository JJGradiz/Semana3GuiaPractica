package ni.edu.uam.registropulperia.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import ni.edu.uam.registropulperia.modelos.Producto;

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
    private TableView<Producto> tablaProductos;

    @FXML
    private TableColumn<Producto, String> colCodigo;

    @FXML
    private TableColumn<Producto, String> colNombre;

    @FXML
    private TableColumn<Producto, String> colCategoria;

    @FXML
    private TableColumn<Producto, Double> colPrecio;

    @FXML
    private TableColumn<Producto, Integer> colCantidad;

    private final ObservableList<Producto> inventario = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));

        tablaProductos.setItems(inventario);
    }

    @FXML
    private void guardarProducto(ActionEvent event) {
        String codigo = txtCodigo.getText() == null ? "" : txtCodigo.getText().trim();
        String nombre = txtNombre.getText() == null ? "" : txtNombre.getText().trim();
        String categoria = txtCategoria.getText() == null ? "" : txtCategoria.getText().trim();
        String precioTexto = txtPrecio.getText() == null ? "" : txtPrecio.getText().trim();
        String cantidadTexto = txtCantidad.getText() == null ? "" : txtCantidad.getText().trim();

        if (codigo.isEmpty() || nombre.isEmpty() || categoria.isEmpty() || precioTexto.isEmpty() || cantidadTexto.isEmpty()) {
            mostrarAlerta("Campos obligatorios", "Debes completar todos los campos antes de guardar el producto.");
            return;
        }

        try {
            double precio = Double.parseDouble(precioTexto);
            int cantidad = Integer.parseInt(cantidadTexto);

            if (precio <= 0 || cantidad <= 0) {
                mostrarAlerta("Datos inválidos", "El precio y la cantidad deben ser valores mayores a cero.");
                return;
            }

            Producto producto = new Producto(codigo, nombre, categoria, precio, cantidad);
            inventario.add(producto);
            tablaProductos.setItems(inventario);

            lblResultado.setText("✓ Producto guardado exitosamente");
            lblResultado.setStyle("-fx-text-fill: green; -fx-font-weight: bold;");
            lblInfoProducto.setText(producto.toString());

            limpiarCampos();

        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "Ingrese valores numéricos válidos para precio y cantidad.");
        }
    }

    @FXML
    private void buscarProducto(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            String codigoBuscar = txtBuscar.getText() == null ? "" : txtBuscar.getText().trim();

            if (codigoBuscar.isEmpty()) {
                lblResultado.setText("⚠ Ingrese un código para buscar");
                lblResultado.setStyle("-fx-text-fill: orange;");
                lblInfoProducto.setText("");
                return;
            }

            Producto encontrado = null;
            for (Producto producto : inventario) {
                if (producto.getCodigo().equalsIgnoreCase(codigoBuscar)) {
                    encontrado = producto;
                    break;
                }
            }

            if (encontrado != null) {
                lblResultado.setText("✓ Producto encontrado");
                lblResultado.setStyle("-fx-text-fill: green; -fx-font-weight: bold;");
                lblInfoProducto.setText(encontrado.toString());
            } else {
                lblResultado.setText("✗ Producto no encontrado");
                lblResultado.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
                lblInfoProducto.setText("");
            }
        }
    }

    @FXML
    private void limpiarFormulario(ActionEvent event) {
        limpiarCampos();
        lblResultado.setText("Formulario limpiado");
        lblResultado.setStyle("-fx-text-fill: #555;");
        lblInfoProducto.setText("");
    }

    private void limpiarCampos() {
        txtCodigo.clear();
        txtNombre.clear();
        txtCategoria.clear();
        txtPrecio.clear();
        txtCantidad.clear();
        txtBuscar.clear();
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}