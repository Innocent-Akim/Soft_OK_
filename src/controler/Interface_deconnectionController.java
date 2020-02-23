/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import static Autre_.Cls_alerte.alertQuestion;
import static GUI.Interface_principale_02Controller.container;
import static GUI.Interface_principale_02Controller.email;
import static GUI.Interface_principale_02Controller.niveau;
import static GUI.Interface_principale_02Controller.nomUtilisateur;
import static GUI.Interface_principale_02Controller.telephone;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Akim
 */
public class Interface_deconnectionController implements Initializable {

    @FXML
    private JFXButton deconnectBnt;
    @FXML
    private AnchorPane deconnectionPrincipal;
    public static boolean c12 = false;
    @FXML
    private Label utilisateurUser;
    @FXML
    private Label nom_concter_;
    @FXML
    private Label contactTel;
    @FXML
    private Label EmailUtilisateur;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        utilisateurUser.setText(nomUtilisateur);
        nom_concter_.setText(Integer.toString(niveau));
        EmailUtilisateur.setText(email);
        contactTel.setText(telephone);
    }

    @FXML
    private void deconnecter_vous(ActionEvent event) throws IOException {
        isDeconnecter("Deconnecter-vous");
    }

    private void deconnexion() throws IOException {
        Parent d = FXMLLoader.load(getClass().getResource("/GUI/interface_login.fxml"));
        Scene scene = new Scene(d);
        Stage st = new Stage();
        st.setScene(scene);
        st.initStyle(StageStyle.UNDECORATED);
        soft_ok.Soft_OK.stage = st;
        ((Stage) deconnectionPrincipal.getScene().getWindow()).close();
        st.show();
    }

    private void isDeconnecter(String c) {
        JFXButton b1 = new JFXButton("OK");
        JFXButton b2 = new JFXButton("Annuler");

        JFXDialogLayout Layout = new JFXDialogLayout();

        Layout.setHeading(new Label(c));

        JFXDialog dialog = new JFXDialog(container, Layout, JFXDialog.DialogTransition.CENTER);

        b1.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {

            try {
                deconnexion();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        });

        b2.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            dialog.close();
        });
        Layout.setActions(b1, b2);
        dialog.show();
    }

}
