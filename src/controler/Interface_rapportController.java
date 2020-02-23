/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import Autre_.Cls_autre_;
import static Autre_.Cls_autre_.SetMontantMAx;
import static Autre_.Cls_autre_.*;
import static Autre_.Cls_autre_.datebut;
import static Autre_.Cls_autre_.onTableClick;
import static Autre_.Cls_autre_.setSupprimer;
import static Autre_.Cls_connexion.conne;
import static Autre_.Cls_imprimer._impresion;
import Autre_.Cls_rapport;
import static Autre_.Cls_rapport.requeteChemin;
import Autre_.Cls_alerte;
import static Autre_.Cls_alerte.alertQuestion;
import static GUI.Interface_principale_02Controller.container;
import static champs.champs_vide.isFieldsempty;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Akim
 */
public class Interface_rapportController implements Initializable {

    @FXML
    private RadioButton vente_produit;
    @FXML
    private ToggleGroup GroupeButton;
    @FXML
    private RadioButton produit_disponible;
    @FXML
    private RadioButton approvi;
    @FXML
    private RadioButton facture_annule;
    private RadioButton prdExpire;
    private RadioButton requisi;
    @FXML
    private TableView<String> tableRapport;
    @FXML
    private MenuItem Click_Supprimer;
    @FXML
    private ContextMenu Menu_Contexte;
    @FXML
    private DatePicker Date_Fin;
    @FXML
    private Label Montant_Total;
    @FXML
    private DatePicker date_Debut;
    @FXML
    private JFXButton printT;
    @FXML
    private Pane produitExpire;
    @FXML
    private JFXComboBox<String> choixCombox;
    Cls_autre_ aut;
    @FXML
    private Hyperlink HyperClic;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Montant_Total.setVisible(false);
            date_Debut.setValue(LocalDate.now());
            Date_Fin.setValue(LocalDate.now());
            Cls_autre_.charger_ComboBox(choixCombox, "Select * From Tcommande");
        } catch (SQLException ex) {
            Logger.getLogger(Interface_rapportController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Interface_rapportController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void repportProduit(MouseEvent event) throws Exception {

    }

    @FXML
    private void chargerAprov(ActionEvent event) {
        Montant_Total.setVisible(false);
        try {
            chargerTable("SELECT * FROM ViewRapportAppro ORDER BY COMMANDE DESC", tableRapport, 0);
        } catch (Exception ex) {
            Cls_alerte.alerteErreur("Probleme de Chargement !!!!", ex.getMessage());
        }
    }

    @FXML
    private void supprimer(ActionEvent event) throws Exception {
        if (approvi.isSelected()) {
            SupprimerApprov();
        } else if (produit_disponible.isSelected()) {
            Cls_alerte.alerteInformation("Ev !!!!", "Evenement non Defini!!");
        } else if (vente_produit.isSelected()) {
            Cls_alerte.alerteInformation("Ev !!!!", "Evenement non Defini!!");
        } else if (facture_annule.isSelected()) {
            Cls_alerte.alerteInformation("Ev !!!!", "Evenement non Defini!!");
        } else if (prdExpire.isSelected()) {
            Cls_alerte.alerteInformation("Ev !!!!", "Evenement non Defini!!");
        } else if (requisi.isSelected()) {
            Cls_alerte.alerteInformation("Ev !!!!", "Evenement non Defini!!");
        }
    }

    private void SupprimerApprov() throws Exception {
        if (Float.parseFloat(onTableClick(tableRapport).get(5)) > setSupprimer(onTableClick(tableRapport).get(1))) {
            Cls_alerte.alerteInformation("Information", "Il est Impossible de suprimer cet Approvisionnement car une partie du stock a deja ete vendu !!!");
        } else {
            if ((alertQuestion("La quantité Disponible dans le Stock est de " + setSupprimer(onTableClick(tableRapport).get(1)) + " !!!", "Ete vous sur de Vouloir Supprimer !!!!")) == true) {
                try {
                    call = conne().prepareCall("{Call delete_approv '" + onTableClick(tableRapport).get(0) + "'}");
                    call.execute();
                    chargerTable("Aprovision", tableRapport, 0);
                    Cls_alerte.alerteInformation("Suppression !!!!", "La suppresseion a reussie !!");
                } catch (SQLException ex) {
                    Cls_alerte.alerteErreur("Erreur  !!!! ", ex.getMessage());
                }
            }

        }
    }

    @FXML
    private void Afficher_Produit(ActionEvent event) throws Exception {
        Montant_Total.setVisible(false);
        chargerTable("rapport_produit", tableRapport, 0);
    }

    @FXML
    private void Afficher_Vente(ActionEvent event) {
        try {
            Montant_Total.setVisible(true);
            Montant_Total.setText("Montant Total est : " +SetMontantMAx(datebut(date_Debut), datebut(Date_Fin)));
            chargerTable("select * from facture_bien_", tableRapport, 0);
        } catch (Exception ex) {
            Cls_alerte.alerteErreur("Probleme de Chargement !!!!", ex.getMessage());
        }
    }

    @FXML
    private void AfficherFact_supprimerrrrrrrrr(ActionEvent event) throws Exception {
        Montant_Total.setVisible(false);
        chargerTable("select codeH as CODE ,"
                + "designation_pro AS Designation "
                + ",Qte ,prix_pro As Punitaire,"
                + "(Qte * prix_pro) as Ptotal ,"
                + "Statut  from Historique_Vente inner"
                + " join Tproduit on Historique_Vente.codeProd="
                + "Tproduit.code_pro where statut='Annulé' "
                + "order by codeH desc", tableRapport, 0);
    }

    @FXML
    private void FonctionPrint_(ActionEvent event) throws SQLException {
        if (produit_disponible.isSelected()) {
            _impresion("listProduitSupe", Cls_autre_.returnOK(requeteChemin) + "report2.jrxml");
        } else if (approvi.isSelected()) {
            if (isFieldsempty(choixCombox)) {
                Cls_alerte.alerteAvertissement("Attention", "Choisir le numero de livraison");
            } else {
                Cls_rapport.print(choixCombox.getValue());
            }

        }
    }

    @FXML
    private void AppelFicher(ActionEvent event) throws SQLException, IOException {
        if (event.getSource() == HyperClic) {
                   new Cls_autre_().Dialog(container, "/GUI/interface_ficherStock.fxml");
                   Cls_alerte.showFormDialog("Ficher de Stock", container, getClass().getResource("/GUI/interface_ficherStock.fxml"), JFXDialog.DialogTransition.CENTER);
        }
    }

}
