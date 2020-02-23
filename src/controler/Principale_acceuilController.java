package controler;

import static Autre_.Cls_connexion.conne;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Akim
 */
public class Principale_acceuilController implements Initializable {

    @FXML
    private AnchorPane principale_;
    @FXML
    private Label perimer;
    ResultSet rs;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @FXML
    private Label produitNondispo;
    @FXML
    private Label presExpire;
    @FXML
    private Label clicIci;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Backup();
    }

    public String recuper(String rqte) {
        String a = "";
        try {
            rs = conne().createStatement().executeQuery(rqte);
            while (rs.next()) {
                a = rs.getString(1);
            }
        } catch (SQLException ex) {
            
            Logger.getLogger(Principale_acceuilController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return a;
    }

    void afficher() {
        perimer.setText(recuper("SELECT COUNT_BIG(CODE) FROM List_produitExpire"));
        presExpire.setText(recuper("presExpiration"));
        produitNondispo.setText(recuper("SELECT COUNT_BIG(designation_pro) FROM requisition_"));
    }

    @FXML
    private void ActionExpiration(MouseEvent event) {
    }

    public void Backup() {
        Timeline to = new Timeline(new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                afficher();
            }

        }));
        to.play();
    }

}
