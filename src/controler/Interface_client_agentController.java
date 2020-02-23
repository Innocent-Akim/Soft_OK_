///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package controler;
//
//import static Autre_.Cls_autre_.chargerTable;
//import static Autre_.Cls_autre_.onTableClick;
//import static Autre_.Cls_connexion.conne;
//import Autre_.Cls_enregister;
//import Autre_.alerte;
//import static Autre_.alerte.alerteAvertissement;
//import DAO.Tclient;
//import com.jfoenix.controls.JFXButton;
//import com.jfoenix.controls.JFXTextField;
//import java.net.URL;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//import java.util.ResourceBundle;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.control.RadioButton;
//import javafx.scene.control.TableView;
//import javafx.scene.input.MouseEvent;
//import javafx.scene.layout.AnchorPane;
//import static champs.champs_vide.isFieldsempty;
//
///**
// * FXML Controller class
// *
// * @author Akm
// */
//public class Interface_client_agentController implements Initializable {
//
//    @FXML
//    private AnchorPane parametre_;
//    @FXML
//    private TableView<?> table_client;
//    @FXML
//    private RadioButton ClientBtn;
//    @FXML
//    private RadioButton agentBtn;
//    @FXML
//    private JFXTextField nomTfd;
//    @FXML
//    private JFXTextField postnomTfd;
//    @FXML
//    private JFXTextField adressTfd;
//    @FXML
//    private JFXTextField contactTfd;
//    @FXML
//    private JFXTextField prenomTfd;
//    @FXML
//    private JFXTextField email_Tfd;
//    @FXML
//    private JFXButton btn_save;
//    @FXML
//    private JFXButton btn_update;
//    @FXML
//    private JFXButton btndelete;
//    int code_client;
//
//    /**
//     * Initializes the controller class.
//     *
//     * @param url
//     * @param rb
//     */
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        try {
//            chargerTable("select * from charge_client", table_client, 0);
//        } catch (Exception ex) {
//
//        }
//    }
//
//    @FXML
//    private void traitement_parametre(ActionEvent event) throws SQLException {
//        DAO.Tclient cn = new Tclient();
//        Autre_.Cls_enregister enr = new Cls_enregister();
//        if (event.getSource() == btn_save) {
//            if (isFieldsempty(nomTfd, prenomTfd, postnomTfd, contactTfd, email_Tfd, adressTfd)) {
//                alerteAvertissement("Erreur", "completer le champs vide svp");
//            } else {
//                cn.setNomClient(nomTfd.getText());
//                cn.setPostnomClient(postnomTfd.getText());
//                cn.setPrenomClient(prenomTfd.getText());
//                cn.setContactClient(contactTfd.getText());
//                cn.setEmailClient(email_Tfd.getText());
//                cn.setAdresseClient(adressTfd.getText());
//                if (enr.enregistre(cn, "", "", "", "")) {
//                    alerte.alerteInformation("Information Enregistrement", "Enregistrement reussi !!!");
//                    actualise();
//                }
//            }
//        } else if (event.getSource() == btn_update) {
//            if (isFieldsempty(nomTfd, prenomTfd, postnomTfd, contactTfd, email_Tfd, adressTfd)) {
//                alerteAvertissement("Erreur", "completer le champs vide svp");
//            } else {
//                cn.setNomClient(nomTfd.getText());
//                cn.setPostnomClient(postnomTfd.getText());
//                cn.setPrenomClient(prenomTfd.getText());
//                cn.setContactClient(contactTfd.getText());
//                cn.setEmailClient(email_Tfd.getText());
//                cn.setAdresseClient(adressTfd.getText());
//                cn.setCodeClient(code_client);
//                if (enr.update(cn, "", "")) {
//                    alerte.alerteInformation("Information Modifier", "Modifier reussi avec succes !!!");
//                    actualise();
//                }
//            }
//        } else if (event.getSource() == btndelete) {
//            cn.setCodeClient(code_client);
//            PreparedStatement ps = conne().prepareStatement("DELETE FROM Tclient where code_client='" + code_client + "'");
//            ps.executeUpdate();
//            alerte.alerteInformation("Information Delete", "Supprimer reussi avec succes !!!");
//            actualise();
//        }
//    }
//
//    private void actualise() {
//        nomTfd.setText("");
//        postnomTfd.setText("");
//        prenomTfd.setText("");
//        contactTfd.setText("");
//        adressTfd.setText("");
//        email_Tfd.setText("");
//        nomTfd.requestFocus();
//        try {
//            chargerTable("select * from charge_client", table_client, 0);
//        } catch (Exception ex) {
//            alerte.alerteErreur("Error", ex.getMessage());
//        }
//    }
//
//    @FXML
//    private void clic_table(MouseEvent event) {
//        try {
//            code_client = Integer.parseInt(onTableClick(table_client).get(0));
//            nomTfd.setText(onTableClick(table_client).get(1));
//            postnomTfd.setText(onTableClick(table_client).get(2));
//            prenomTfd.setText(onTableClick(table_client).get(3));
//            contactTfd.setText(onTableClick(table_client).get(4));
//            adressTfd.setText(onTableClick(table_client).get(5));
//            email_Tfd.setText(onTableClick(table_client).get(6));
//        } catch (NumberFormatException e) {
//            alerte.alerteErreur("Error", e.getMessage());
//        }
//    }
//
//}
