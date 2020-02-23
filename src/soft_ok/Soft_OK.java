package soft_ok;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Akm
 */
public class Soft_OK extends Application {

    public static Stage stage = null;

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/interface_progressBar.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.getIcons().add(new javafx.scene.image.Image("/image/Pill_52px.png"));
        Soft_OK.stage = stage;
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);

    }

}
