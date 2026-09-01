package ni.edu.uam.registropulperia;



import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class PulperiaApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(
                PulperiaApplication.class.getResource("inventario-view.fxml")
        );
        Scene scene = new Scene(fxmlLoader.load(), 450, 600);
        stage.setTitle("Inventario de Pulpería");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}