/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import Autre_.Cls_autre_;
import static controler.Interface_venteController.idClient;
import static controler.Interface_venteController.nomClient;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Akim
 */
public class Interface_clientController implements Initializable {

    @FXML
    private TextField Recher_Tfd;
    @FXML
    private TableView<String> tableClient;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Cls_autre_.chargerTable("SELECT code_client As Numero,nom_client AS Nom FROM Tclient ", tableClient, 0);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void rechercher_(KeyEvent event) throws Exception {
        Cls_autre_.chargerTable("SELECT code_client As Numero,nom_client AS Nom "
                + "FROM Tclient WHERE code_client "
                + "LIKE'%" + Recher_Tfd.getText() + "%'OR nom_client "
                + "LIKE'%" + Recher_Tfd.getText() + "%' OR postnom_client "
                + "LIKE '%" + Recher_Tfd.getText() + "%'OR prenom_client "
                + "LIKE '%" + Recher_Tfd.getText() + "%'", tableClient, 0);
    }

    @FXML
    private void ClicClient_(MouseEvent event) {
        try {
            idClient.setText(Cls_autre_.onTableClick(tableClient).get(0));
            nomClient.setValue(Cls_autre_.onTableClick(tableClient).get(1));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
