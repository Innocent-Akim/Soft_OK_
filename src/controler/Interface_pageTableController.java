/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import controler.Interface_ficherStockController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author Akim
 */
public class Interface_pageTableController implements Initializable {

    @FXML
    private Label codeLbl;
    @FXML
    private Label desinationLbl;
    @FXML
    private Label categorieLbl;
    @FXML
    private Label QteE;
    @FXML
    private Label PE;
    @FXML
    private Label PTE;
    @FXML
    private Label QtS;
    @FXML
    private Label PS;
    @FXML
    private HBox PTS;
    @FXML
    private Label QteDispo;
    @FXML
    private Label PDispo;
    @FXML
    private Label PTDis;
    @FXML
    private Label Date_Lbl;
    @FXML
    private Label PTS1;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        codeLbl.setText(Interface_ficherStockController.code);
        desinationLbl.setText(Interface_ficherStockController.designation);
        categorieLbl.setText(Interface_ficherStockController.categorie);
        QteE.setText(Interface_ficherStockController.qteE);
        PE.setText(Interface_ficherStockController.puE);
        PTE.setText(Interface_ficherStockController.PtE);
        QtS.setText(Interface_ficherStockController.qteS);
        PS.setText(Interface_ficherStockController.puS);
        PTS1.setText(Interface_ficherStockController.PtS);
        QteDispo.setText(Interface_ficherStockController.qteD);
        PDispo.setText(Interface_ficherStockController.puD);
        PTDis.setText(Interface_ficherStockController.PtD);
        Date_Lbl.setText(Interface_ficherStockController.date);

    }

}
