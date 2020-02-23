/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import Autre_.Cls_alerte;
import Autre_.Cls_Wtraitement;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import static champs.champs_vide.isFieldsempty;

/**
 * FXML Controller class
 *
 * @author Akim
 */
public class Interface_resseauController implements Initializable {

    @FXML
    private JFXTextField tfdNomresseau;
    @FXML
    private JFXTextField tfdPwd;
    @FXML
    private RadioButton rdbCree;
    @FXML
    private ToggleGroup ResseauBtn;
    @FXML
    private RadioButton rbtnActive;
    @FXML
    private RadioButton rbtnArret;
    @FXML
    private JFXButton btnConnexion;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        if (rdbCree.isSelected()) {
            btnConnexion.setText("Cree");
        }
    }

    @FXML
    private void ConnectionBtn(ActionEvent event) {
        if (rdbCree.isSelected()) {
            if (isFieldsempty(tfdNomresseau, tfdPwd)) {
                Cls_alerte.alerteErreur("ERROR", "Les Vides ne sonts Autorisé !!!");
            } else {
                if (Cls_Wtraitement.ActiveReseau("Set", tfdNomresseau.getText(), tfdPwd.getText()) == true) {
                    Cls_alerte.alerteInformation("Succe ", "La creation du reseau as reussie !!!");
                } else {
                    Cls_alerte.alerteErreur("ERROR", "La creation n'as pas reussie !!!");
                }
            }
        } else if (rbtnArret.isSelected()) {
            if ((Cls_Wtraitement.ActiveReseau("Stop", null, null)) == true) {
                Cls_alerte.alerteInformation("Succe ", "Le reseau a ete Arreté");
            } else {
                Cls_alerte.alerteErreur("ERROR", "La creation n'as pas reussie !!!");
            }
        } else if (rbtnActive.isSelected()) {
            if (Cls_Wtraitement.ActiveReseau("Start", null, null) == true) {
                Cls_alerte.alerteInformation("Succe ", "Le reseau a bien ete Activé");
            } else {
                Cls_alerte.alerteErreur("ERROR", "La creation n'as pas reussie !!!");
            }
        }
    }

    @FXML
    private void CreeAction(ActionEvent event) {
        if (rdbCree.isSelected()) {
            btnConnexion.setText("Cree");
            tfdNomresseau.setVisible(true);
            tfdPwd.setVisible(true);
        }
    }

    @FXML
    private void ActiveAction(ActionEvent event) {
        if (rbtnActive.isSelected()) {
            btnConnexion.setText("Active");
            tfdNomresseau.setVisible(false);
            tfdPwd.setVisible(false);
        }
    }

    @FXML
    private void ArretAction(ActionEvent event) {
        if (rbtnArret.isSelected()) {
            btnConnexion.setText("Arret");
            tfdNomresseau.setVisible(false);
            tfdPwd.setVisible(false);
        }
    }

}
