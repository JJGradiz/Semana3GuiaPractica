module ni.edu.uam.registropulperia {
    requires javafx.controls;
    requires javafx.fxml;


    opens ni.edu.uam.registropulperia to javafx.fxml;
    exports ni.edu.uam.registropulperia;
}