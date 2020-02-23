/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Akim
 */
public class Principal_acceuil1Controller implements Initializable {
    
    @FXML
    private Label perimer;
    @FXML
    private Label presExpire;
    @FXML
    private Label produitNondispo;
    @FXML
    private Label clicIci;
    Principale_acceuilController acc = new Principale_acceuilController();

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Backup();
    }
    
    @FXML
    private void ActionExpiration(MouseEvent event) {
    }
    
    void afficher() {
        perimer.setText(acc.recuper("SELECT COUNT(Id) FROM USysUsr"));
        presExpire.setText(acc.recuper("SELECT COUNT(nom_fss) FROM Tfournisseur"));
        produitNondispo.setText(acc.recuper("SELECT COUNT(nom_client) FROM Tclient"));
    }
    
    public void Backup() {
        Timeline to = new Timeline(new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                afficher();
            }
            
        }));
        to.playFrom(Duration.millis(500));
    }
    
}
