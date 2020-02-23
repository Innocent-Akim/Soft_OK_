/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Akim
 */
public class Interface_beneficeController implements Initializable {

    @FXML
    private Label beneficeV;
    @FXML
    private DatePicker date1;
    @FXML
    private DatePicker date2;
    @FXML
    private JFXButton ActualiserBtn;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        date1.setValue(LocalDate.now());
        date2.setValue(LocalDate.now());
    }

    @FXML
    private void actualise_(ActionEvent event) {
        try {
            beneficeV.setText(Autre_.Cls_autre_.returnOK("Benefice_vente '"
                    + Autre_.Cls_autre_.datebut(date1)
                    + "','" + Autre_.Cls_autre_.datebut(date2) + "'"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
