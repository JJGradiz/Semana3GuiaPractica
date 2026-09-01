package ni.edu.uam.registropulperia.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ni.edu.uam.registropulperia.modelos.Venta;

public class pulperiaController {
    @FXML
    private TextField txtCantidad;

    @FXML
    private TextField txtPrecio;

    @FXML
    private Label lblTotal;

    @FXML
    private void calcularVenta(ActionEvent event) {
        try {
            int cantidad = Integer.parseInt(txtCantidad.getText());
            double precio = Double.parseDouble(txtPrecio.getText());

            Venta venta = new Venta(cantidad, precio);

            lblTotal.setText(String.format("Total: C$ %.2f", venta.getTotal()));

        } catch (NumberFormatException e) {
            lblTotal.setText("Ingrese datos numéricos válidos.");
        }
    }

}