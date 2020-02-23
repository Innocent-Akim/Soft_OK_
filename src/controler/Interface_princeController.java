/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import Autre_.Cls_alerte;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Akm
 */
public class Interface_princeController implements Initializable {
    
    @FXML
    private AnchorPane principale_;
    @FXML
    private Pane ecran_1;
    @FXML
    private FontAwesomeIconView _close;
    double x = 0, y = 0;
    @FXML
    private Label vente_btn;
    @FXML
    private Label produit_btn;
    @FXML
    private Label nom_concter_;
    @FXML
    private Label parametreBtn_pr;
    @FXML
    private Label heure_du_jour;
    @FXML
    private Label date_du_jour;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ecran_remove("interface_acceuil.fxml");
        date_du_jour.setText(LocalDate.now().toString());
        heure_du_jour.setText(LocalTime.now().toString());
        
    }
    
    @FXML
    private void action_produit(MouseEvent event) {
        if (event.getSource() == vente_btn) {
            ecran_remove("interface_vente.fxml");
        } else if (event.getSource() == produit_btn) {
            ecran_remove("interface_produit.fxml");
        } else if (event.getSource() == parametreBtn_pr) {
            ecran_remove("interface_client_agent.fxml");
        }
        
    }
    
    public void ecran_remove(String ecran) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(ecran));
            ecran_1.getChildren().removeAll();
            ecran_1.getChildren().setAll(root);
        } catch (IOException ex) {
            Cls_alerte.alerteErreur("error", ex.getMessage());
            
        }
    }
    
    @FXML
    private void exit_appli(MouseEvent event) {
        System.exit(0);
    }
    
    @FXML
    private void dragged(MouseEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setX(event.getScreenX() - x);
        stage.setY(event.getScreenY() - y);
    }
    
    @FXML
    private void pressed(MouseEvent event) {
        x = event.getSceneX();
        y = event.getSceneY();
    }
    
    public void ecran_clean(String ecran) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(ecran));
            ecran_1.getChildren().clear();
            ecran_1.getChildren().setAll(root);
        } catch (IOException ex) {
            ex.getMessage();
        }
    }
    
}
