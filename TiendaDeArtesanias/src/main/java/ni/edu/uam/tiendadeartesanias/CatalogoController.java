package ni.edu.uam.tiendadeartesanias;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.InputStream;

public class CatalogoController {

    @FXML private TableView<Productos> tablaProductos;
    @FXML private TableColumn<Productos, String> colImagen;
    @FXML private TableColumn<Productos, String> colId;
    @FXML private TableColumn<Productos, String> colNombre;
    @FXML private TableColumn<Productos, String> colCategoria;
    @FXML private TableColumn<Productos, Double> colPrecio;
    @FXML private TextField txtBuscar;

    private final ObservableList<Productos> listaProductos = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Mapeo de columnas
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));

        // Renderizado de la imagen en la celda de la tabla
        colImagen.setCellValueFactory(new PropertyValueFactory<>("rutaImagen"));
        colImagen.setCellFactory(col -> new TableCell<Productos, String>() {
            private final ImageView imageView = new ImageView();

            @Override
            protected void updateItem(String rutaImagen, boolean empty) {
                super.updateItem(rutaImagen, empty);
                if (empty || rutaImagen == null) {
                    setGraphic(null);
                } else {
                    InputStream stream = getClass().getResourceAsStream(rutaImagen);
                    if (stream != null) {
                        Image img = new Image(stream, 40, 40, true, true);
                        imageView.setImage(img);
                        setGraphic(imageView);
                    } else {
                        setGraphic(null);
                    }
                }
            }
        });

        cargarDatosDemo();
        tablaProductos.setItems(listaProductos);
    }

    private void cargarDatosDemo() {
        // Productos existentes
        listaProductos.add(new Productos("ART-001", "Hamaca Masaya", "Textil", 1200.0, "/imagenes/hamacas.jpg"));
        listaProductos.add(new Productos("ART-002", "Vasija de Barro", "Cerámica", 450.0, "/imagenes/vasijas.jpg"));

        listaProductos.add(new Productos("ART-003", "Marimba de Arco", "Instrumentos", 2500.0, "/imagenes/marimba.jpeg"));
        listaProductos.add(new Productos("ART-004", "Guayabera Bordada", "Textil", 850.0, "/imagenes/guayabera.jpg"));
    }

    // --- ACCIONES DE TOOLBAR Y MENÚ ---

    @FXML
    private void onNuevo() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Nuevo Producto");
        alert.setHeaderText(null);
        alert.setContentText("Formulario para registrar una nueva artesanía.");
        alert.showAndWait();
    }

    @FXML
    private void onGuardar() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Guardar Cambios");
        alert.setHeaderText(null);
        alert.setContentText("Los cambios del catálogo han sido guardados con éxito.");
        alert.showAndWait();
    }

    @FXML
    private void onBuscar() {
        String filtro = txtBuscar.getText().toLowerCase().trim();
        if (filtro.isEmpty()) {
            tablaProductos.setItems(listaProductos);
        } else {
            ObservableList<Productos> filtrados = FXCollections.observableArrayList();
            for (Productos p : listaProductos) {
                if (p.getNombre().toLowerCase().contains(filtro) || p.getCategoria().toLowerCase().contains(filtro)) {
                    filtrados.add(p);
                }
            }
            tablaProductos.setItems(filtrados);
        }
    }
}