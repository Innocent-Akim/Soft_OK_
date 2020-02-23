/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import static Autre_.Cls_autre_.chargerTable;
import static Autre_.Cls_rapport.printRapport;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author Akim
 */
public class Interface_produitPerimeController implements Initializable {

    @FXML
    private RadioButton dans_;
    @FXML
    private ToggleGroup Group;
    @FXML
    private RadioButton dans_7;
    @FXML
    private TableView<String> tabl_requi;
    @FXML
    private RadioButton requisition;
    @FXML
    private Label etiquete;
    @FXML
    private JFXButton imprimer_;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (dans_.isSelected()) {
            try {
                chargerTable(" SELECT designation article,quantite as quantite FROM List_produitExpire", tabl_requi, 0);
            } catch (Exception ex) {
                Logger.getLogger(Interface_produitPerimeController.class.getName()).log(Level.SEVERE, null, ex);
            }
            etiquete.setText("PEREMPTION");
        }
    }

    @FXML
    private void _perimes(ActionEvent event) {
        if (dans_.isSelected()) {
            try {
                chargerTable(" SELECT designation article,quantite as quantite FROM List_produitExpire", tabl_requi, 0);
            } catch (Exception ex) {
                Logger.getLogger(Interface_produitPerimeController.class.getName()).log(Level.SEVERE, null, ex);
            }
            etiquete.setText("PEREMPTION");

        }
    }

    @FXML
    private void dans_7(ActionEvent event) {
        if (dans_7.isSelected()) {
            try {
                chargerTable(" DANS_7", tabl_requi, 0);
                etiquete.setText("PEREMPTION");

                //   tabl_requi.backgroundProperty().getValue().getFills(Color.RED);
            } catch (SQLException ex) {
                Logger.getLogger(Interface_produitPerimeController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(Interface_produitPerimeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void aide_requ(ActionEvent event) {
        if (requisition.isSelected()) {
            try {
                chargerTable("SELECT designation_pro as Article ,qte_pro as Quantite FROM requisition_", tabl_requi, 0);
                etiquete.setText("AIDE REQUISITION");
            } catch (SQLException ex) {
                Logger.getLogger(Interface_produitPerimeController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(Interface_produitPerimeController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    @FXML
    private void imprimer_produi(ActionEvent event) throws SQLException {
        if (event.getSource() == imprimer_) {
            if (dans_.isSelected()) {
                printRapport(1);
            } else if (dans_7.isSelected()) {
                printRapport(2);
            } else if (requisition.isSelected()) {
                printRapport(3);
            }
        }
    }

}
