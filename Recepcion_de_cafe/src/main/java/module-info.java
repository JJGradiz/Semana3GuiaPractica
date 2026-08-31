module ni.edu.uam.recepcion_de_cafe {
    requires javafx.controls;
    requires javafx.fxml;


    opens ni.edu.uam.recepcion_de_cafe to javafx.fxml;
    exports ni.edu.uam.recepcion_de_cafe;
}