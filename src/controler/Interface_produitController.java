/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import Autre_.Cls_autre_;
import static Autre_.Cls_autre_.chargerTable;
import static champs.champs_vide.isFieldsempty;
import static Autre_.Cls_autre_.*;
import static Autre_.Cls_connexion.conne;
import Autre_.Cls_enregister;
import static Autre_.Cls_print.createBarecode;
import static Autre_.Cls_rapport.printRapport;
import Autre_.Cls_alerte;
import DAO.interface_produit;
import GUI.Interface_principale_02Controller;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Akm
 */
public class Interface_produitController implements Initializable {

    @FXML
    private JFXTextField disgna_tfd;
    @FXML
    private JFXTextField punitaireTfd;
    @FXML
    private JFXTextField quantiteTfd;
    @FXML
    private JFXTextField formeTfd;
    @FXML
    private JFXTextField nom_fab;
    @FXML
    private ComboBox<String> categorieCbx;
    private JFXTextField date_Tfd;
    @FXML
    private TextField rechercherTfd;
    @FXML
    private JFXButton enregistreBtn;
    @FXML
    private JFXTextField codebarTfd;
    @FXML
    private JFXButton updateBtn;
    public int code_produit;
    @FXML
    private JFXButton delete_;
    private java.sql.PreparedStatement ps;
    Autre_.Cls_enregister cn = new Cls_enregister();
    String code_categorie;
    @FXML
    private TableView<String> tab_prod;
    @FXML
    private JFXTextField unite_Tfd;
    @FXML
    private ComboBox<String> devise_cbx;
    @FXML
    private AnchorPane produit_interface;
    @FXML
    private JFXButton categorieBtn;
    @FXML
    private JFXButton imprimerProduitBtn;
    DAO.interface_produit pro;
    public static String categorie;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        construicteur();
    }

    private void construicteur() {
        try {
            Cls_autre_.charger_ComboBox(categorieCbx, "SELECT designation_cate FROM Tcategorie");
            chargerTable("charge_prod_t", tab_prod, 0);
            // back();
            devise_cbx.getItems().addAll("FC", "USD");
        } catch (SQLException ex) {
            Cls_alerte.alerteErreur("Attention", ex.getMessage());
        } catch (Exception ex) {
            Cls_alerte.alerteErreur("Attention", ex.getMessage());
        }
        ChargememtCompression(disgna_tfd, "TProduit", "Designation_pro");
        ChargememtCompression(punitaireTfd, "TProduit", "Prix_pro");
    }

    @FXML
    private void enre_update(ActionEvent event) throws SQLException, Exception {
        pro = new interface_produit(disgna_tfd.getText(), Double.parseDouble(quantiteTfd.getText()),
                Double.parseDouble(punitaireTfd.getText()), formeTfd.getText(),
                nom_fab.getText(), codebarTfd.getText(), unite_Tfd.getText(), devise_cbx.getValue(), code_produit, categorieCbx.getValue());
        if (event.getSource() == enregistreBtn) {
            if (isFieldsempty(codebarTfd, disgna_tfd, quantiteTfd, formeTfd, nom_fab, punitaireTfd, unite_Tfd, devise_cbx)) {
                Cls_alerte.alerteErreur("Attention", "completer le champs vide svp !!!");
            } else {
                if (Float.parseFloat(quantiteTfd.getText()) < 0 || Float.parseFloat(punitaireTfd.getText()) <= 0) {
                    Cls_alerte.alerteAvertissement("Attention", "La quantité et le prix unitaire doive être positive !!!");
                } else {
                    if (cn.enregistre(pro, "", "") == true) {
                        if (createBarecode(disgna_tfd.getText() + ".png", codebarTfd.getText()) == true) {
                            Cls_alerte.alerteInformation("Information Enregistrement", "Enregistrement reussi");
                            actualiser();
                        }
                    }
                }
            }
        } else if (event.getSource() == updateBtn) {
            if (isFieldsempty(codebarTfd, disgna_tfd, quantiteTfd, formeTfd, nom_fab, punitaireTfd)) {
                Cls_alerte.alerteAvertissement("Attention", "completer le champs vide svp !!!");
            } else {

                if (cn.update(pro) == true) {
                    Cls_alerte.alerteInformation("Information update", "Modifier avec succes");
                    actualiser();
                }
            }
        } else if (event.getSource() == delete_) {
            if (isFieldsempty(codebarTfd, disgna_tfd, quantiteTfd, formeTfd, nom_fab, punitaireTfd)) {
                Cls_alerte.alerteErreur("Attention", "selectionner d'abord le donnee a supprimer !!!");
            } else {

                call = conne().prepareCall("{call delete_(?)}");
                call.setInt(1, pro.getCode());
                call.executeUpdate();
                Cls_alerte.alerteInformation("Information Delete", "Supprimer avec succès");
                actualiser();
            }
        } else if (event.getSource() == imprimerProduitBtn) {
            printRapport(4);
        }
    }

    @FXML
    private void selection_code(ActionEvent event) throws SQLException {
        code_categorie = Cls_autre_.returnOK(""
                + "SELECT code_cate ,designation_cate "
                + "FROM Tcategorie  "
                + "WHERE designation_cate='" + categorieCbx.getValue() + "'");
    }

    private void actualiser() throws Exception {
        codebarTfd.setText("");
        disgna_tfd.setText("");
        formeTfd.setText("");
        quantiteTfd.setText("");
        punitaireTfd.setText("");
        nom_fab.setText("");
        codebarTfd.requestFocus();
        categorieCbx.getSelectionModel().select(null);
        chargerTable("charge_prod_t", tab_prod, 0);
        devise_cbx.setValue(null);
        unite_Tfd.setText("");
        categorieCbx.setValue(null);
    }

    @FXML
    private void recherech_produit(KeyEvent event) throws Exception {
        chargerTable("RECHERCHE'" + rechercherTfd.getText() + "'", tab_prod, 0);
    }

    @FXML
    private void clic_table(MouseEvent event) {

        try {
            code_produit = Integer.parseInt(onTableClick(tab_prod).get(0));
            disgna_tfd.setText(onTableClick(tab_prod).get(1));
            unite_Tfd.setText(onTableClick(tab_prod).get(2));
            quantiteTfd.setText(onTableClick(tab_prod).get(3));
            punitaireTfd.setText(onTableClick(tab_prod).get(4));
            devise_cbx.getSelectionModel().select(onTableClick(tab_prod).get(5));
            formeTfd.setText(onTableClick(tab_prod).get(7));
            codebarTfd.setText(onTableClick(tab_prod).get(8));
            nom_fab.setText(onTableClick(tab_prod).get(9));
            categorieCbx.getSelectionModel().select(onTableClick(tab_prod).get(6));

        } catch (NumberFormatException e) {
            Cls_alerte.alerteErreur(null, e.getMessage());
        }
    }

    @FXML
    private void categorie_btn_clic(ActionEvent event) throws SQLException {
        showDialog();
    }

    @FXML
    private void priUnitaire_isNumerique(KeyEvent event) {
        Cls_autre_.isNumeric(punitaireTfd);
    }

    @FXML
    private void quantite_disponible(KeyEvent event) {
        Cls_autre_.isNumeric(quantiteTfd);
    }

    @FXML
    private void key_released_codebar(KeyEvent event) {
        Cls_autre_.isNumeric(codebarTfd);
        if (codebarTfd.getText().length() <= 6) {
            System.out.println("OK");
            Cls_autre_.isNumeric(codebarTfd);
        } else {
            codebarTfd.setText("");
        }
    }

    private void showDialog() {
        JFXDialogLayout layout = new JFXDialogLayout();
        JFXTextField txt_categorie = new JFXTextField();
        txt_categorie.setLabelFloat(true);
        txt_categorie.setPromptText("Categorie");
        VBox box = new VBox();
        box.getChildren().add(txt_categorie);
        layout.setBody(box);
        JFXDialog dlg = new JFXDialog(Interface_principale_02Controller.container, layout, JFXDialog.DialogTransition.CENTER);
        JFXButton btn_add = new JFXButton("Ajouter");
        btn_add.setOnAction((e) -> {
            if (isFieldsempty(txt_categorie)) {
                Cls_alerte.alerteAvertissement("Attention", "Champ()s vide");
            } else {
                try {
                    call = conne().prepareCall("{call categorie_(?)}");
                    call.setString(1, txt_categorie.getText().toUpperCase());
                    call.executeUpdate();
                    Cls_alerte.alerteInformation("Information", "Categorie ajouter");
                    txt_categorie.setText("");
                    try {
                        Cls_autre_.charger_ComboBox(categorieCbx, "SELECT designation_cate FROM Tcategorie");
                    } catch (Exception ex) {
                        Logger.getLogger(Interface_produitController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getErrorCode());
                }
            }

        });

        JFXButton btn_close = new JFXButton("Fermer");
        btn_close.setOnAction((e) -> {
            dlg.close();
            try {
                Cls_autre_.charger_ComboBox(categorieCbx, "SELECT designation_cate FROM Tcategorie");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            } catch (Exception ex) {
                Logger.getLogger(Interface_produitController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        layout.setActions(btn_add, btn_close);
        dlg.show();
    }

}
