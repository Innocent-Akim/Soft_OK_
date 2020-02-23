package GUI;

import static Autre_.Cls_connexion.testConnection;
import Autre_.Cls_preference;
import static Autre_.Cls_preference.RedKeyExterne;
import Autre_.Cls_alerte;
import static Autre_.Cls_connexion.conne;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import controler.Interface_loginController;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.input.MouseEvent;
import static champs.champs_vide.isFieldsempty;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Akim
 */
public class Interface_configurationController implements Initializable {

    @FXML
    private JFXButton testConnection;
    @FXML
    private JFXTextField server_;
    @FXML
    private JFXTextField BD;
    @FXML
    private JFXTextField login;
    @FXML
    private JFXButton save_configuration;
    @FXML
    private JFXPasswordField pwd;
    @FXML
    private FontAwesomeIconView exit1;
    @FXML
    private FontAwesomeIconView retour;
    @FXML
    private Label testConnection_;
    Interface_loginController log;
    @FXML
    private ProgressIndicator progresseBar;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void closeAPK(MouseEvent event) {
    }

    @FXML
    private void back_up(MouseEvent event) throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource("/GUI/interface_login.fxml"));
//        soft_ok.Soft_OK.stage.getScene().setRoot(root);
        //stage.getIcons().add(new javafx.scene.image.Image("/image/Pill_52px.png"));
    }

    @FXML
    private void ConnecterFa(ActionEvent event) {
        if (event.getSource() == save_configuration) {
            if (isFieldsempty(server_, login, BD, pwd)) {
                Cls_alerte.alerteErreur("Champ()s", "Pas autorises le vide !!!");
            } else {
                if (Cls_preference.RedKey(server_.getText(), login.getText(), BD.getText(), pwd.getText()) == true) {
                    
                    RedKeyExterne("DB", BD.getText());
                    if (conne() != null) {
                        Cls_alerte.alerteInformation("Information", "Configuration reussi !!!");
                        CallWindow.Callwindow.Methode.call("/GUI/interface_login.fxml", "Login", 2, "/image/Pill_52px.png");
                        ((Stage) Interface_loginController.Principale.getScene().getWindow()).close();
                    } else {
                        Cls_alerte.alerteErreur("ERROR", "Configuration echoue !!!");
                    }
                }
            }
        }
    }

    @FXML
    private void ConnecterFa1(ActionEvent event) {
        progresse();
    }

    private boolean progresse() {
        boolean bool = true;
        @SuppressWarnings("unchecked")
        Task<Integer> task = secode(1000);
        progresseBar.progressProperty().unbind();
        progresseBar.progressProperty().bind(task.progressProperty());
        task.setOnSucceeded(e -> {
            try {
                if (conne() == null) {
                    testConnection_.setText("Connection echouer !!!");

                } else {
                    testConnection_.setText("Connection stable avec succes !!!");
                    //test();
                }
            } catch (Exception ex) {
                Cls_alerte.alerteErreur("ERROR", ex.getMessage());
            }
        });
        Thread th = new Thread(task);
        th.start();
        return bool;
    }

    private Task secode(int seconde) {
        return new Task<Integer>() {
            @Override
            protected Integer call() throws Exception {
                int i;
                for (i = 0; i <= seconde; i++) {
                    updateProgress(i, 1000);
                    Thread.sleep(10);
                }
                return i;
            }
        };
    }

}
