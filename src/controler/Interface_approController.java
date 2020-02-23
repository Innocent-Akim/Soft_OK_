package controler;

import static Autre_.Cls_autre_.chargerTable;
import static Autre_.Cls_autre_.isNumeric;
import static Autre_.Cls_autre_.onTableClick;
import static Autre_.Cls_autre_.returnOK;
import Autre_.Cls_connexion;
import static Autre_.Cls_connexion.conne;
import static Autre_.Cls_rapport.print;
import Autre_.Cls_alerte;
import static Autre_.Cls_alerte.alerteErreur;
import static GUI.Interface_principale_02Controller.container;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import static champs.champs_vide.isFieldsempty;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Akm
 */
public class Interface_approController implements Initializable {

    @FXML
    private ComboBox<String> nomfss;
    @FXML
    private JFXTextField code_fss;
    @FXML
    private JFXTextField idProduit;
    @FXML
    private JFXTextField qte_fourni;
    private PreparedStatement ps;
    private Statement s;
    private static ResultSet rs;
    @FXML
    private JFXButton btn_valide;
    @FXML
    private JFXDatePicker date_e;
    @FXML
    private TableView<String> charge_appro;
    private Label stockdisponible;
    @FXML
    private TextField rechercher_v;
    @FXML
    private JFXTextField p_initiale;
    @FXML
    private TableView<String> approvisiMedicament;
    @FXML
    private JFXTextField medicament_appro;
    @FXML
    private JFXDatePicker dateEntere;
    @FXML
    private Label IdCommande;
    @FXML
    private JFXButton nveaucommande;
    @FXML
    private JFXButton imprimerBtn;

    /**
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            Autre_.Cls_autre_.chargerTable("CHARGE", charge_appro, 0);
            idProduit.setEditable(false);
            approvisiMedicament.setVisible(false);
            dateEntere.setValue(LocalDate.now());
            IdCommande.setText(returnOK("SELECT dbo.Fscommande()"));
            nomFss();
        } catch (SQLException ex) {
            alerteErreur("Attention", ex.getMessage());
        } catch (Exception ex) {
            alerteErreur("Attention", ex.getMessage());
        }
    }

    private void nomFss() throws SQLException {
        rs = conne().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY).executeQuery("Fournisseur");
        while (rs.next()) {
            nomfss.getItems().addAll(rs.getString(1));
        }
        Cls_connexion.isDeconnection();
    }

    private int code_founi(String nom) throws SQLException {
        int x = 0;
        rs = conne().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY).executeQuery("nomFournisseur'" + nom + "'");
        if (rs.next()) {
            x = Integer.parseInt(rs.getString(1));
        }
        Cls_connexion.isDeconnection();
        return x;
    }

    private int code_produit(String nom) throws SQLException {
        int x = 0;
        rs = conne().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY).executeQuery("designationProduit'" + nom + "'");
        if (rs.next()) {
            x = Integer.parseInt(rs.getString(1));
        }
        Cls_connexion.isDeconnection();
        return x;
    }

    @FXML
    private void charge_code_fss(ActionEvent event) throws SQLException {
        code_fss.setText(Integer.toString(code_founi(nomfss.getValue())));
    }

    @FXML
    private void enregistre_(ActionEvent event) throws SQLException, Exception {
//        LocalDate dat = date_e.getValue();
//        String date_ = String.valueOf(dat);
        if (event.getSource() == btn_valide) {
            if (!isFieldsempty(nomfss, idProduit, qte_fourni, p_initiale)) {
                ps = conne().prepareCall("{call fournir_(?,?,?,?,?,?,?)}");
                ps.setString(1, code_fss.getText());
                ps.setString(2, idProduit.getText());
                ps.setString(3, date_c(date_e));
                ps.setString(4, date_c(dateEntere));
                ps.setString(5, qte_fourni.getText());
                ps.setString(6, p_initiale.getText());
                ps.setString(7, IdCommande.getText());
                ps.executeUpdate();
                actualise();
                Cls_alerte.alerteInformation("Information", "Vous avez bien approvissionner!!!");
            } else {
                alerteErreur("ERROR", "completer le champs vide svp");
            }
        }
    }

    private String date_c(JFXDatePicker date_) {
        LocalDate dat = date_.getValue();
        String retourn = dat.format(DateTimeFormatter.ISO_DATE);
        return retourn;
    }

    void actualise() throws Exception {
        nomfss.setValue(null);
        code_fss.setText("");
        idProduit.setText("");
        date_e.setValue(null);
        qte_fourni.setText("");
        medicament_appro.setText("");
        p_initiale.setText("");
        nomfss.requestFocus();
        Autre_.Cls_autre_.chargerTable("CHARGE", charge_appro, 0);
    }

    @FXML
    private void rechercher_produit(KeyEvent event) {
        try {
            Autre_.Cls_autre_.chargerTable("rechercher'" + rechercher_v.getText() + "'", charge_appro, 0);
        } catch (Exception e) {
        }
    }

    @FXML
    private void isNumerique_pro(KeyEvent event) {
        isNumeric(p_initiale);
    }

    @FXML
    private void isNumerique_quantite(KeyEvent event) {
        isNumeric(qte_fourni);
    }

    @FXML
    private void rechercher_produit_(KeyEvent event) throws Exception {
        approvisiMedicament.setVisible(true);
        chargerTable("PrRecherche'" + medicament_appro.getText() + "'", approvisiMedicament, 0);
    }

    @FXML
    private void selection_produit(MouseEvent event) {
        try {
            idProduit.setText(onTableClick(approvisiMedicament).get(0));
            medicament_appro.setText(onTableClick(approvisiMedicament).get(1));
            approvisiMedicament.setVisible(false);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @FXML
    private void mousiExit_valeur(MouseEvent event) {
        approvisiMedicament.setVisible(false);
    }

    @FXML
    private void nouveauCommende(ActionEvent event) throws SQLException {
        if (event.getSource() == nveaucommande) {
            JFXButton b1 = new JFXButton("OK");
            JFXButton b2 = new JFXButton("Annuler");
            JFXDialogLayout Layout = new JFXDialogLayout();
            Layout.setHeading(new Label("Voulez-vous éffectuer la nouvelle commande..."));
            JFXDialog dialog = new JFXDialog(container, Layout, JFXDialog.DialogTransition.CENTER);
            b1.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
                IdCommande.setText(returnOK("SELECT dbo.Fscommande()"));
                dialog.close();
            });
            b2.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
                dialog.close();
            });
            Layout.setActions(b1, b2);
            dialog.show();
        } else if (event.getSource() == imprimerBtn) {
            print(IdCommande.getText());
        }
    }
}
