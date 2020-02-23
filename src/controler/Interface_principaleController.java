/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import Autre_.Cls_alerte;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Akm
 */
public class Interface_principaleController implements Initializable {

    private StackPane ecran_1;
    @FXML
    private JFXButton acceuilBtn;
    @FXML
    private JFXButton approvi_btn;
    @FXML
    private JFXButton produit_btn;
    @FXML
    private JFXButton vente_btn;
    @FXML
    private JFXButton parametreBtn_pr;
    double x = 0, y = 0;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //  ecran_remove("interface_acceuil.fxml");
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

    private void Actionclic_principal(ActionEvent event) {
        if (event.getSource() == vente_btn) {
            ecran_remove("interface_vente.fxml");
        } else if (event.getSource() == produit_btn) {
            ecran_remove("interface_produit.fxml");
        } else if (event.getSource() == parametreBtn_pr) {
            ecran_remove("interface_client_agent.fxml");
        }
    }

    private void dragged(MouseEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setX(event.getScreenX() - x);
        stage.setY(event.getScreenY() - y);
    }

    private void pressed(MouseEvent event) {
        x = event.getSceneX();
        y = event.getSceneY();
    }

}
