package controler;

import Autre_.Cls_autre_;
import Autre_.Cls_alerte;
import static controler.Interface_loginController.makeStage;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Akim
 */
public class Interface_progressBarController implements Initializable {

    @FXML
    private ProgressBar progressebar_;
    @FXML
    private AnchorPane progressBarOk;
    private Label progress_Lbl;

    ;
    @FXML
    private ProgressIndicator progressbar02;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        progressBar();
        makeStage(progressBarOk);
    }
    boolean bool = false;

    private boolean progressBar() {

        @SuppressWarnings("unchecked")
        Task<Integer> task = secode(1000);
        progressebar_.progressProperty().unbind();
        progressebar_.progressProperty().bind(task.progressProperty());
        progressbar02.progressProperty().unbind();
        progressbar02.progressProperty().bind(task.progressProperty());

        task.setOnSucceeded(e -> {
            try {
                Parent d = FXMLLoader.load(getClass().getResource("/GUI/interface_login.fxml"));
                Scene scene = new Scene(d);
                Stage st = new Stage();
                st.setScene(scene);
                ((Stage) progressBarOk.getScene().getWindow()).close();
                st.initStyle(StageStyle.UNDECORATED);
                st.getIcons().add(new javafx.scene.image.Image("/image/Pill_52px.png"));
                st.show();

            } catch (IOException ex) {
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

    public void pregress() {

        Thread ss;
        ss = new Thread() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 1; i++) {
                        sleep(300);
                        progress_Lbl.setText("Running  .");
                        sleep(400);
                        progress_Lbl.setText("Running  ..");
                        sleep(500);
                        progress_Lbl.setText("Running  ...");
                        sleep(600);
                        progress_Lbl.setText("Running  .");
                        sleep(700);
                        progress_Lbl.setText("Running  ..");
                        sleep(500);
                        progress_Lbl.setText("Running  ...");
                        sleep(500);
                        progress_Lbl.setText("Running  .");
                        sleep(600);
                        progress_Lbl.setText("Running  ..");
                        sleep(700);
                        progress_Lbl.setText("Running  ...");
                        progress_Lbl.setText("Running  .");
                        sleep(700);
                        progress_Lbl.setText("Running  ..");
                        sleep(800);
                        progress_Lbl.setText("Running  ...");
                        sleep(800);
                        progress_Lbl.setText("Running  .");
                        sleep(900);
                        progress_Lbl.setText("Running  ..");
                        sleep(900);
                        progress_Lbl.setText("Running  ...");

                    }

                } catch (InterruptedException e) {
                }
            }
        };
        ss.start();
    }

}
