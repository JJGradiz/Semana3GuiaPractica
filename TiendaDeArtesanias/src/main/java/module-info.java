module ni.edu.uam.tiendadeartesanias {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens ni.edu.uam.tiendadeartesanias to javafx.fxml;
    exports ni.edu.uam.tiendadeartesanias;
}