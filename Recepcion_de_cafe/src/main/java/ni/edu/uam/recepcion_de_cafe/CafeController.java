package ni.edu.uam.recepcion_de_cafe;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.util.Optional;

public class CafeController {

    @FXML private TextField txtProductor;
    @FXML private TextField txtPeso;
    @FXML private TextField txtVariedad;

    @FXML private TableView<Lote> tablaLotes;
    @FXML private TableColumn<Lote, Integer> colId;
    @FXML private TableColumn<Lote, String> colProductor;
    @FXML private TableColumn<Lote, Double> colPeso;
    @FXML private TableColumn<Lote, String> colVariedad;
    @FXML private Label lblDetalles;

    private final ObservableList<Lote> listaLotes = FXCollections.observableArrayList();
    private int contadorId = 1;

    @FXML
    public void initialize() {

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colProductor.setCellValueFactory(new PropertyValueFactory<>("productor"));
        colPeso.setCellValueFactory(new PropertyValueFactory<>("pesoKg"));
        colVariedad.setCellValueFactory(new PropertyValueFactory<>("variedad"));

        tablaLotes.setItems(listaLotes);

        // Eventos MouseEvent y ContextMenu
        configurarEventosTabla();
    }

    @FXML
    private void onAgregarLoteClick() {
        String productor = txtProductor.getText().trim();
        String pesoText = txtPeso.getText().trim();
        String variedad = txtVariedad.getText().trim();

        if (productor.isEmpty() || pesoText.isEmpty() || variedad.isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Campos vacíos", "Por favor completa todos los campos.");
            return;
        }

        try {
            double peso = Double.parseDouble(pesoText);
            Lote nuevoLote = new Lote(contadorId++, productor, peso, variedad);
            listaLotes.add(nuevoLote);


            txtProductor.clear();
            txtPeso.clear();
            txtVariedad.clear();

            lblDetalles.setText("Lote #" + nuevoLote.getId() + " agregado exitosamente.");
        } catch (NumberFormatException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Formato incorrecto", "El peso debe ser un número válido.");
        }
    }

    private void configurarEventosTabla() {
        ContextMenu contextMenu = new ContextMenu();
        MenuItem itemEditar = new MenuItem("Editar Lote");
        MenuItem itemEliminar = new MenuItem("Eliminar Lote");

        itemEditar.setOnAction(e -> editarLote());
        itemEliminar.setOnAction(e -> confirmarEliminacion());

        contextMenu.getItems().addAll(itemEditar, itemEliminar);

        tablaLotes.setOnMouseClicked((MouseEvent event) -> {
            Lote seleccionado = tablaLotes.getSelectionModel().getSelectedItem();

            if (seleccionado != null) {
                // Clic izquierdo: Ver detalles
                if (event.getButton() == MouseButton.PRIMARY) {
                    lblDetalles.setText("Detalles: Lote #" + seleccionado.getId() +
                            " | Productor: " + seleccionado.getProductor() +
                            " | Peso: " + seleccionado.getPesoKg() + " Kg" +
                            " | Variedad: " + seleccionado.getVariedad());
                }

                // Clic derecho: Mostrar menú contextual
                if (event.getButton() == MouseButton.SECONDARY) {
                    contextMenu.show(tablaLotes, event.getScreenX(), event.getScreenY());
                } else {
                    contextMenu.hide();
                }
            }
        });
    }

    private void editarLote() {
        Lote seleccionado = tablaLotes.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            TextInputDialog dialog = new TextInputDialog(String.valueOf(seleccionado.getPesoKg()));
            dialog.setTitle("Editar Lote");
            dialog.setHeaderText("Editar peso del lote #" + seleccionado.getId());
            dialog.setContentText("Nuevo Peso (Kg):");

            Optional<String> result = dialog.showAndWait();
            result.ifPresent(nuevoPeso -> {
                try {
                    double peso = Double.parseDouble(nuevoPeso);
                    seleccionado.setPesoKg(peso);
                    tablaLotes.refresh();
                    lblDetalles.setText("Lote #" + seleccionado.getId() + " actualizado correctamente.");
                } catch (NumberFormatException ex) {
                    mostrarAlerta(Alert.AlertType.ERROR, "Error", "Por favor ingresa un número válido.");
                }
            });
        }
    }

    private void confirmarEliminacion() {
        Lote seleccionado = tablaLotes.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmar eliminación");
            alert.setHeaderText("¿Estás seguro de eliminar el lote #" + seleccionado.getId() + "?");
            alert.setContentText("Esta acción eliminará la entrega del productor " + seleccionado.getProductor() + ".");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                listaLotes.remove(seleccionado);
                lblDetalles.setText("Lote #" + seleccionado.getId() + " eliminado.");
            }
        }
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}