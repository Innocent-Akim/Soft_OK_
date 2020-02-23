package controler;

import Autre_.Cls_autre_;
import static Autre_.Cls_autre_.chargerTable;
import static Autre_.Cls_autre_.isNumeric;
import static Autre_.Cls_autre_.onTableClick;
import static Autre_.Cls_autre_.returnOK;
import static Autre_.Cls_connexion.conne;
import static Autre_.Cls_rapport.printPameretre1;
import Autre_.Cls_alerte;
import static Autre_.Cls_alerte.alertQuestion;
import static Autre_.Cls_alerte.alerteAvertissement;
import Autre_.Cls_Wtraitement;
import GUI.Interface_principale_02Controller;
import static GUI.Interface_principale_02Controller.codeAgent;
import static GUI.Interface_principale_02Controller.container;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import static controler.Parametre_interfaceController.niveauAcces;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import static champs.champs_vide.isFieldsempty;
import com.jfoenix.controls.JFXDialog;
import java.io.IOException;
import javax.swing.plaf.ComboBoxUI;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author Akm
 */
public class Interface_venteController implements Initializable {

    static Cls_Wtraitement cls = new Cls_Wtraitement();
    @FXML
    private TableView<String> table_recher_;
    @FXML
    private JFXTextField recher_vente;
    @FXML
    private JFXTextField codebarTfd;
    ResultSet rs;
    @FXML
    private JFXTextField punitaire;
    @FXML
    private JFXTextField qte;
    @FXML
    private JFXTextField pT;
    @FXML
    private Label client_nom;
    @FXML
    private ComboBox<String> code_client;
    @FXML
    private Label numerF;
    //private int code_pro = 0;
    private PreparedStatement ps;
    private Statement s;
    @FXML
    private JFXButton factureBtn;
    @FXML
    private Label montant_payer;
    @FXML
    private JFXButton print1;
    @FXML
    private JFXButton enregitrebtn;
    //private float quteDisponible = 0;
    @FXML
    private TableView<String> controle_facture_du_jour;
    @FXML
    private TextField recher_facture;
    @FXML
    private Label code_produit_;
    @FXML
    private Label quantite_;
    @FXML
    private JFXButton annuler_facture;
    @FXML
    private AnchorPane interfaceVente;

    public static Cls_autre_ popu = new Cls_autre_();
    GUI.Interface_principale_02Controller princi = new Interface_principale_02Controller();

    @FXML
    private MenuItem retireFac;
    @FXML
    private JFXButton btn_calculatrice;
    @FXML
    private Label pourcentage_statique;
    @FXML
    private Label pourcentage_statique1;
    @FXML
    private Label taux_;
    @FXML
    private Label pourcentage_statique2;
    @FXML
    private RadioButton dollarsam;
    @FXML
    private ToggleGroup grouper_taux;
    @FXML
    private RadioButton fccongolais;
    @FXML
    private TextField montatconvertir;
    @FXML
    private TextField reponseTfd;
    @FXML
    private Label taux_1;
    @FXML
    private Label pourcentage_statique21;
    public static Label idClient;
    public static ComboBox<String> nomClient;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        construicteur();
        idClient = client_nom;
        nomClient = code_client;

    }

    void construicteur() {
        TooltiPc();
        try {
            back();
            Cls_autre_.charger_ComboBox(code_client, "Select nom_client from Tclient");
        } catch (SQLException ex) {
            Cls_alerte.alerteErreur("ERROR", ex.getMessage());
        } catch (Exception ex) {
            Cls_alerte.alerteErreur("ERROR", ex.getMessage());
        }

        if (niveauAcces(2) || niveauAcces(4)) {
            print1.setVisible(true);
            enregitrebtn.setVisible(true);
            factureBtn.setVisible(true);
            annuler_facture.setVisible(true);
        } else {
            print1.setVisible(false);
            enregitrebtn.setVisible(false);
            factureBtn.setVisible(false);
            annuler_facture.setVisible(false);
        }
    }

    @FXML
    private void recher_produit(KeyEvent event) {

        table_recher_.setVisible(true);
        try {
            chargerTable("charge_produitSupe'" + recher_vente.getText() + "'", table_recher_, 0);
        } catch (Exception ex) {
            Cls_alerte.alerteErreur("Error", ex.getMessage());
        }
    }

    @FXML
    private void clic_table(MouseEvent event) {
        try {
            codebarTfd.setText(onTableClick(table_recher_).get(0));
            recher_vente.clear();
            codebarTfd.requestFocus();
            if (!codebarTfd.getText().equals("")) {
                table_recher_.setVisible(false);
            } else {
                table_recher_.setVisible(true);
            }
        } catch (Exception e) {

        }
    }

    private void actualiser() {
        codebarTfd.setText("");
        punitaire.setText("");
        qte.setText("");
        pT.setText("");
        codebarTfd.requestFocus();
    }

    private void back() throws Exception {
        punitaire.setEditable(false);
        pT.setEditable(false);
        table_recher_.setVisible(false);
        punitaire.setEditable(false);
        chargerTable("AfficherVente", controle_facture_du_jour, 0);
        annimation();
    }

    public void ente_facture(String d) throws SQLException {
        ps = conne().prepareCall("{call num_facture_(?,?,?,?)}");
        ps.setString(1, LocalDateTime.now().toString());
        ps.setString(2, "0");
        ps.setString(3, d);
        ps.setInt(4, codeAgent);
        ps.executeUpdate();
        Cls_alerte.alerteInformation("Nouvelle Facture", "Enregistrement reussi avec succès !!!");
        System.out.println(LocalDateTime.now().toString());
    }

    public int numero_facture() throws SQLException {
        int x = 0;
        s = conne().createStatement();
        rs = s.executeQuery("SELECT MAX(num_fact) FROM Tvente");
        if (rs.next()) {
            x = Integer.parseInt(rs.getString(1));
        }
        return x;
    }

    void actualise() throws Exception {
        // client_nom.setText("2");
        pT.setText("0.0");
        punitaire.setText("0.0");
        qte.setText("0");
        codebarTfd.setText("");
        code_produit_.setText("");
        quantite_.setText("0.0");
        chargerTable("AfficherVente", controle_facture_du_jour, 0);
    }

    @FXML
    private void nouveaulle_facture(ActionEvent event) throws SQLException {
        if (!client_nom.getText().equals("")) {
            ente_facture(client_nom.getText());
            numerF.setText(Integer.toString(numero_facture()));
            factureBtn.setVisible(false);
        } else {
            alerteAvertissement("selectionner d'abord le client", "Veuillez compléter le champs vide svp");
        }
    }

    @FXML
    private void select_code_client(ActionEvent event) throws SQLException {
        client_nom.setText(Cls_autre_.returnOK("Select code_client,nom_client FROM Tclient  where nom_client='" + code_client.getValue() + "'"));
    }

    @FXML
    private void imprimer_(ActionEvent event) throws SQLException, Exception {
        if (event.getSource() == print1) {
            factureBtn.setVisible(true);
            printPameretre1(numerF.getText());
            ps = conne().prepareCall("{Call StatutVente(?)}");
            ps.setString(1, numerF.getText());
            ps.executeUpdate();
            numerF.setText("00");
            montant_payer.setText("0.0");
            quantite_.setText("0.0");
            taux_1.setText("0.0");
            code_client.setValue(null);
            client_nom.setText("2");
        } else if (event.getSource() == enregitrebtn) {
//            if (!returnOK("SELECT Statut FROM Tvente Where num_fact='" + numerF.getText() + "'").equals("1")) {
//            } else {
//            }
            if (!isFieldsempty(qte, punitaire)) {
                if (!(qte.getText().equals("0") || punitaire.getText().equals("0.0") || codebarTfd.getText().equals(""))) {
                    if (Float.parseFloat(quantite_.getText()) < Float.parseFloat(qte.getText())) {
                        Cls_alerte.alerteAvertissement("Quantite disponiple", "quantite disponible!!!" + "  " + quantite_.getText());
                        qte.requestFocus();
                    } else {
                        if (Integer.parseInt(numerF.getText()) == 00) {
                            Cls_alerte.alerteAvertissement("complet", "Veuillez compléter le champs vide svp!!!");
                            factureBtn.setVisible(true);
                        } else {
                            if (Float.parseFloat(punitaire.getText()) <= 0 || Float.parseFloat(qte.getText()) <= 0) {
                                Cls_alerte.alerteAvertissement("complet", "la vente doit être possitive!!!");
                            } else {
                                if (returnOK("SELECT Statut FROM Tvente WHERE num_fact='" + numerF.getText() + "'").equals("1")) {
                                    alerteAvertissement("ERROR", "Ajout Invalider !!!");
                                } else {
                                    ps = conne().prepareCall("{call vente_(?,?,?,?)}");
                                    ps.setString(1, punitaire.getText());
                                    ps.setString(2, qte.getText());
                                    ps.setString(3, code_produit_.getText());
                                    ps.setString(4, numerF.getText());
                                    ps.executeUpdate();
                                    actualise();
                                    s = conne().createStatement();
                                    rs = s.executeQuery("select sum(pt_v) from TventeD where num_fact='" + numerF.getText() + "'");
                                    if (rs.next()) {
                                        montant_payer.setText(rs.getString(1));
                                        taux_1.setText(
                                                Double.toString(
                                                        Double.parseDouble(rs.getString(1))
                                                        * Double.parseDouble(taux_.getText())
                                                )
                                        );

                                    }
                                }
                            }
                        }
                    }
                } else {
                    Cls_alerte.alerteAvertissement("complet", "Veuillez compléter le champs vide svp!!!");
                }
            } else {
                Cls_alerte.alerteAvertissement("complet", "Veuillez compléter le champs vide svp!!!");
            }
        } else if (event.getSource() == annuler_facture) {
            if (cls.setValeur("select MontatDisponible from Tcaisse", "MontatDisponible") >= cls.setValeur("select montat from Tvente where num_fact='" + numerF.getText() + "'", "montat")) {
                if (alertQuestion(numerF.getText(), "Voulez vous annulez la facture !!!") == true) {
                    if (!delai_facture(numerF.getText()).equals("")) {
                        annuler(Integer.parseInt(numerF.getText()));
                        Cls_alerte.alerteInformation(null, "La facture est bien annuler !!!");
                        chargerTable("AfficherVente", controle_facture_du_jour, 0);
                        numerF.setText("00");
                    } else {
                        Cls_alerte.alerteAvertissement("Delai", "Delai deja passer!!!");
                    }
                }
            } else {
                Cls_alerte.alerteAvertissement("complet", "Il est impossible de supprimer cette facture pour L'instant !!! ");
            }

        } else if (event.getSource() == btn_calculatrice) {
            cls.SartCalculator();

        }
    }

    private String delai_facture(String a) throws SQLException {
        String x = "";
        rs = conne().createStatement().executeQuery(" EXEC DELAI_VENTE'" + Integer.parseInt(a) + "'");
        if (rs.next()) {
            x = rs.getString("X");
        }
        return x;
    }

    @FXML
    private void punitaire_prix(KeyEvent event) {
        try {
            if (isFieldsempty(qte)) {
                pT.setText("0.0");
            } else {
                if (Cls_autre_.isNumeric(qte) == true) {
                    pT.setText(
                            Double.toString(
                                    new Cls_autre_().totat(Double.parseDouble(
                                            qte.getText()), Double.parseDouble(
                                            punitaire.getText()))));
                }
            }
        } catch (NumberFormatException e) {
            Cls_alerte.alerteErreur("ERROR", e.getMessage());
        }
    }

    @FXML
    private void search_facture(KeyEvent event) throws Exception {
        chargerTable("RechercherVente '" + recher_facture.getText() + "'", controle_facture_du_jour, 0);
    }

    public void selection_la_vente() {
        try {
            ps = conne().prepareStatement("SELECT code_pro,prix_pro,qte_pro FROM Tproduit where Codebar like '%" + codebarTfd.getText() + "%'");
            rs = ps.executeQuery();
            code_produit_.setText("");
            quantite_.setText("");
            punitaire.clear();
            if (rs.next()) {
                code_produit_.setText(rs.getString(1));
                punitaire.setText(rs.getString(2));
                quantite_.setText(rs.getString(3));
            }
            Autre_.Cls_connexion.isDeconnection();
        } catch (SQLException ex) {
            Cls_alerte.alerteErreur("ERROR", ex.getMessage());
        }
    }

    @FXML
    private void vente_de_produit_(KeyEvent event) {
        selection_la_vente();
        if (event.getCode() == KeyCode.RIGHT) {
            qte.requestFocus();
        }
    }

    void annuler(int a) {

        try {
            ps = conne().prepareCall("PrAnnuleF " + a + "");
            ps.execute();
            ps = conne().prepareCall("DELETE FROM Tvente WHERE num_fact =" + a + "");
            ps.execute();
        } catch (SQLException ex) {
            Cls_alerte.alerteErreur(null, ex.getMessage());
        }
    }
    int c1 = 0;

    @FXML
    private void action_annuler_facture(MouseEvent event) {
        try {
            numerF.setText(onTableClick(controle_facture_du_jour).get(0));
            c1 = 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void mouse_exit_false(MouseEvent event) {
        table_recher_.setVisible(false);
    }

    @FXML
    private void retireProduitFac(ActionEvent event) throws SQLException, Exception {
        if (cls.setValeur("select MontatDisponible from Tcaisse", "MontatDisponible") >= cls.setValeur("select montat from Tvente where num_fact='" + numerF.getText() + "'", "montat")) {

            if (c1 == 1) {
                if (returnOK("SELECT statut_v FROM Tvente WHERE num_fact='" + numerF.getText() + "'").equals("1")) {
                    alerteAvertissement("ERROR", "Modification Invalide !!!");
                } else {
                    ps = conne().prepareCall("{call PrDeleteVente(?,?,?,?)}");
                    ps.setInt(1, Integer.parseInt(onTableClick(controle_facture_du_jour).get(0)));
                    ps.setString(2, onTableClick(controle_facture_du_jour).get(1));
                    ps.setFloat(3, Float.parseFloat(onTableClick(controle_facture_du_jour).get(4)));
                    ps.setFloat(4, Float.parseFloat(onTableClick(controle_facture_du_jour).get(5)));
                    ps.execute();
                    chargerTable("AfficherVente", controle_facture_du_jour, 0);
                    Cls_alerte.alerteInformation("", "Bien modifier !!!");
                }
            } else {
                alerteAvertissement("ERROR", "Clic sur le produit que t'as besoin de modifier");
            }
        } else {
            Cls_alerte.alerteAvertissement("Alert", "Il est impossible de supprimer cet Article pour L'instant !!! ");
        }
    }

    private String choix() {
        String bool = "";
        if (dollarsam.isSelected()) {
            bool = "USD";
        } else if (fccongolais.isSelected()) {
            bool = "FC";
        }
        return bool;
    }

    public double resultat1(double a, double b) {
        double c2 = 0;
        if (choix().equals("USD")) {
            c2 = b / a;
        } else if (choix().equals("FC")) {
            c2 = b * a;
        }
        return c2;

    }

    @FXML
    private void EvenementEntrermontat(KeyEvent event) {
        isNumeric(montatconvertir);

        try {
            if (montatconvertir.getText().equals("")) {
                reponseTfd.setText("0.0");
            } else {
                reponseTfd.setText(
                        Double.toString(
                                resultat1(Double.parseDouble(taux_.getText()),
                                        Double.parseDouble(montatconvertir.getText())))
                );
            }

        } catch (NumberFormatException e) {
            Cls_alerte.alerteErreur("ERROR", e.getMessage());
        }
    }

    @FXML
    private void dollard(ActionEvent event) {
        reponseTfd.setText("0.0");
        montatconvertir.setText("");
    }

    @FXML
    private void franc(ActionEvent event) {
        reponseTfd.setText("0.0");
        montatconvertir.setText("");
    }

    private void annimation() {
        AnimationTimer time = new AnimationTimer() {
            @Override
            public void handle(long now) {
                taux_.setText(returnOK("SELECT valeur FROM Ttaux"));
            }
        };
        time.start();
    }

    void TooltiPc() {

        code_client.setTooltip(princi.Titre("veuillez choisir le client"));
        punitaire.setTooltip(princi.Titre("veuillez completer le p.unitaire"));
        codebarTfd.setTooltip(princi.Titre("veuillez completer le barcode"));
        qte.setTooltip(princi.Titre("veuillez completer la quantité"));
        recher_vente.setTooltip(princi.Titre("veuillez completer le produit"));
        pT.setTooltip(princi.Titre("veuillez completer le p.total"));

    }

    @FXML
    private void keyPressed_(KeyEvent event) throws IOException {

        Cls_alerte.showFormDialog("Chercher le Client", container, getClass().getResource("/GUI/interface_client.fxml"), JFXDialog.DialogTransition.CENTER);
    }

}
