package ni.edu.uam.registropulperia;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PulperiaApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(
                PulperiaApplication.class.getResource("inventario-view.fxml")
        );
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root, 620, 700);
        stage.setTitle("Pulpería - Registro de Productos");
        stage.setResizable(true);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
