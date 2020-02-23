package controler;

import Autre_.Cls_autre_;
import static Autre_.Cls_autre_.*;
import static Autre_.Cls_autre_.chargerTable;
import static Autre_.Cls_autre_.charger_ComboBox;
import static Autre_.Cls_autre_.enrestre;
import static champs.champs_vide.isFieldsempty;
import static Autre_.Cls_autre_.isRestoring;
import static Autre_.Cls_autre_.onTableClick;
import static Autre_.Cls_autre_.pwd_crypte_md5;
import static Autre_.Cls_autre_.call;
import static Autre_.Cls_connexion.conne;
import Autre_.Cls_enregister;
import static Autre_.Cls_preference.RedKeyExterne;
import static Autre_.Cls_preference.TimeBackup;
import static Autre_.Cls_preference.url_print;
import static Autre_.Cls_rapport.printRapport;
import Autre_.Cls_alerte;
import static Autre_.Cls_print.rs;
import DAO.interface_agent;
import DAO.interface_client;
import DAO.interface_fournisseur;
import DAO.interface_sorti;
import DAO.interface_user;
import GUI.Interface_principale_02Controller;
import static GUI.Interface_principale_02Controller.fonction;
import static GUI.Interface_principale_02Controller.niveau;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

/**
 * FXML Controller class
 *
 * @author Akm
 */
public class Parametre_interfaceController implements Initializable {

    @FXML
    private JFXTextField nomTfd;
    @FXML
    private JFXTextField postnomTfd;
    @FXML
    private JFXTextField prenomTfd;
    @FXML
    private JFXTextField contactTfd;
    @FXML
    private JFXTextField email_Tfd;
    @FXML
    private JFXTextField adressTfd;
    @FXML
    private JFXButton btnmodifier;
    @FXML
    private JFXButton btnenregistre;
    @FXML
    private JFXButton btnsupprimer;
    private int code_client;
    @FXML
    private TableView<String> table_client;
    @FXML
    private JFXButton backup_base_donne;
    @FXML
    private JFXButton btn_parcour_backup;
    @FXML
    private JFXButton btn_parcour_restaure;
    @FXML
    private JFXTextField chemin_backup_Tfd;
    @FXML
    private JFXTextField chemin_backup_Tfd1;
    @FXML
    private JFXButton restaure_base_donne;
    private Circle cercleImage;
    private JFXTextField chemin_File;
    @FXML
    private JFXTextField rechercherAgent;
    @FXML
    private RadioButton agentBtnR;
    @FXML
    private RadioButton FssBtnR;
    @FXML
    private RadioButton ClientBtnR;
    @FXML
    private Label partirConnecter;
    @FXML
    private ToggleGroup groupeBtn;
    private ComboBox<String> nomBnx;
    @FXML
    private JFXTextField Username;
    @FXML
    private JFXTextField idAgent;
    @FXML
    private JFXPasswordField password;
    @FXML
    private ComboBox<String> permissiom;
    @FXML
    private JFXButton validerPermission;
    private ComboBox<String> niveaut;
    @FXML
    private TableView<String> Tbpermission;
    @FXML
    private TableView<String> tableAgentLogin;
    @FXML
    private JFXTextField niveaut1;
    @FXML
    private VBox contener_agent;
    @FXML
    private MenuItem retireMnItm;
    private int codeAgent;
    Cls_autre_ a = new Cls_autre_();
    @FXML
    private AnchorPane identification;
    @FXML
    private AnchorPane gestionUtilisateur;
    @FXML
    private Tab backup;
    @FXML
    private Label perimer1;
    @FXML
    private JFXTextArea MotifText;
    @FXML
    private Label montatDisponible;
    public static Principale_acceuilController princi = new Principale_acceuilController();
    @FXML
    private JFXTextField montatSorti;
    @FXML
    private JFXButton retirerMontat;
    @FXML
    private ListView<String> montatSorti1;
    @FXML
    private ComboBox<String> comboxDate;
    @FXML
    private JFXButton restaure_base_donne1;
    @FXML
    private JFXButton btn_parcour_restaure1;
    @FXML
    private JFXTextField chemin_backup_Tfd11;
    @FXML
    private JFXButton modifiercaise;
    @FXML
    private JFXTextField tauxTfd;
    @FXML
    private JFXTextField chemin_backup_Tfd112;
    @FXML
    private JFXTextField backupheure;
    @FXML
    private FontAwesomeIconView actionModifierheure;
    PreparedStatement ps;
    @FXML
    private JFXButton btnprintIdentification;
    @FXML
    private JFXButton confugBtn;
    DAO.interface_client cn;
    DAO.interface_agent ag;
    Autre_.Cls_enregister enr;
    DAO.interface_fournisseur fss;
    DAO.interface_user us;
    Autre_.Cls_autre_ atr;
    @FXML
    private JFXButton btnVoir;

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

    void construicteur() {
        backupheure.setText(TimeBackup());
        try {
            tableAgentLogin.setVisible(false);
            chargerTable("SELECT * FROM PERMISSION", Tbpermission, 0);
            charger_ComboBox(permissiom, "SELECT Fonction FROM TFONCTION");
            if (agentBtnR.isSelected()) {
                chargerTable("charge_agentProc", table_client, 0);
                partirConnecter.setText("Agent");

                ChargememtCompression(nomTfd, "TAgent", "nom_agent");
                ChargememtCompression(postnomTfd, "TAgent", "postnom_agent");
                ChargememtCompression(prenomTfd, "TAgent", "prenom_agent");
            }
            if (niveauAcces(4) == true) {
                btnenregistre.setVisible(true);
                btnmodifier.setVisible(true);
                btnsupprimer.setVisible(true);
            } else {
                btnenregistre.setVisible(false);
                btnmodifier.setVisible(false);
                btnsupprimer.setVisible(false);
            }
            caisse();
            atr = new Autre_.Cls_autre_();

        } catch (Exception ex) {
            Logger.getLogger(Parametre_interfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void traitement_parametrer(ActionEvent event) throws SQLException, Exception {
        ag = new interface_agent(
                nomTfd.getText(),
                postnomTfd.getText(),
                prenomTfd.getText(),
                adressTfd.getText(),
                contactTfd.getText(),
                email_Tfd.getText(),
                code_client
        );
        cn = new interface_client(
                nomTfd.getText(),
                postnomTfd.getText(),
                prenomTfd.getText(),
                adressTfd.getText(),
                contactTfd.getText(),
                email_Tfd.getText(),
                code_client);
        enr = new Cls_enregister();

        fss = new interface_fournisseur(
                nomTfd.getText(),
                postnomTfd.getText(),
                prenomTfd.getText(),
                adressTfd.getText(),
                contactTfd.getText(),
                code_client);

        us = new interface_user(
                Username.getText(),
                password.getText());

        if (event.getSource() == btnenregistre) {
            if (isFieldsempty(nomTfd, prenomTfd, postnomTfd, contactTfd, email_Tfd, adressTfd)) {
                Cls_alerte.alerteAvertissement("ERROR", "Pas autorisé sur le vide !!!");
            } else {
                if (ClientBtnR.isSelected()) {
                    if (enr.enregistre(cn, "", "")) {
                        Cls_alerte.alerteInformation("Information Enregistrement", "Enregistrement reussi !!!");
                        actualise();
                    }
                } else if (agentBtnR.isSelected()) {
                    if (isFieldsempty(nomTfd, postnomTfd, prenomTfd, contactTfd, adressTfd, email_Tfd)) {
                        Cls_alerte.alerteAvertissement("ERROR", "Pas autorisé sur le vide !!!");
                    } else {
                        if (enr.enregistre(ag, "", "")) {
                            Cls_alerte.alerteInformation("Information Enregistrement", "Enregistrement reussi !!!");
                            actualise();
                        }
                    }
                } else if (FssBtnR.isSelected()) {
                    if (isFieldsempty(nomTfd, postnomTfd, prenomTfd, contactTfd, adressTfd)) {
                        Cls_alerte.alerteAvertissement("ERROR", "Pas autorisé sur le vide !!!");
                    } else {
                        if (enr.enregistre(fss, "", "")) {
                            Cls_alerte.alerteInformation("Information Enregistrement", "Enregistrement reussi !!!");
                            actualise();
                        }
                    }
                }
            }
        } else if (event.getSource() == btnmodifier) {
            if (isFieldsempty(nomTfd, prenomTfd, postnomTfd, contactTfd, email_Tfd, adressTfd)) {
                Cls_alerte.alerteAvertissement("ERROR", "Pas autorisé sur le vide !!!");
            } else {
                if (ClientBtnR.isSelected()) {

                    if (enr.update(cn)) {
                        Cls_alerte.alerteInformation("Information Modifier", "Modifier reussi avec succes !!!");
                        actualise();
                    }
                } else if (agentBtnR.isSelected()) {
                    if (isFieldsempty(nomTfd, prenomTfd, postnomTfd, contactTfd, email_Tfd, adressTfd)) {
                        Cls_alerte.alerteAvertissement("ERROR", "Pas autorisé sur le vide !!!");
                    } else {

                        if (enr.update(ag)) {
                            Cls_alerte.alerteInformation("Information Modifier", "Modifier reussi avec succes !!!");
                            actualise();
                        }
                    }
                } else if (FssBtnR.isSelected()) {
                    if (isFieldsempty(nomTfd, prenomTfd, postnomTfd, contactTfd, adressTfd)) {
                        Cls_alerte.alerteAvertissement("ERROR", "Pas autorisé sur le vide !!!");
                    } else {
                        if (code_client <= 0) {
                            Cls_alerte.alerteAvertissement("ERROR", "Pas autorisé sur le vide !!!");
                        } else {
                            if (enr.update(fss)) {
                                Cls_alerte.alerteInformation("Information Modifier", "Modifier reussi avec succes !!!");
                                actualise();
                            }
                        }
                    }
                }
            }
        } else if (event.getSource() == btnsupprimer) {
            if (ClientBtnR.isSelected()) {
                cn.setCode(code_client);
                if (code_client <= 0) {
                    Cls_alerte.alerteAvertissement("ERROR", "Pas autorisé sur le vide !!!");
                } else {
                    ps = conne().prepareStatement("DELETE FROM Tclient where code_client='" + code_client + "'");
                    ps.executeUpdate();
                    Cls_alerte.alerteInformation("Information Delete", "Supprimer reussi avec succes !!!");
                    actualise();
                }
            } else if (FssBtnR.isSelected()) {
                if (code_client <= 0) {
                    Cls_alerte.alerteAvertissement("ERROR", "Pas autorisé sur le vide !!!");
                } else {
                    deletePersonnel("{Call DeleteFss(?)}", Integer.toString(code_client));
                }
                actualise();
            } else {
                if (code_client <= 0) {
                    Cls_alerte.alerteAvertissement("ERROR", "Pas autorisé sur le vide !!!");
                } else {
                    deletePersonnel("{Call Deleteagent(?)}", Integer.toString(code_client));
                    actualise();
                }
            }
        } else if (event.getSource() == validerPermission) {

            if (isFieldsempty(password, permissiom)) {
                Cls_alerte.alerteAvertissement("ERROR", "Pas autorisé sur le vide !!!");
            } else {
                us.setUsername(Username.getText());
                us.setPassword(pwd_crypte_md5(password.getText()));
                if (enr.enregistre(us, idAgent.getText(), permissiom.getValue())) {
                    chargerTable("SELECT * FROM PERMISSION", Tbpermission, 0);
                    Cls_alerte.alerteInformation("Information Enregistrement", "Enregistrement reussi !!!");
                    vide();
                }
            }
        } else if (event.getSource() == retirerMontat) {
            if (isFieldsempty(montatSorti, MotifText)) {
                Cls_alerte.alerteAvertissement("ERROR", "Pas autorisé sur le vide !!!");
            } else {
                DAO.interface_sorti sor = new interface_sorti(MotifText.getText(), Double.parseDouble(montatSorti.getText()));
                sor.setMontant(Double.parseDouble(montatSorti.getText()));
                sor.setMotif((MotifText.getText()));
                if (enr.Enregistre1(sor) == true) {
                    Cls_alerte.alerteInformation("Information Retrer", "Retrer reussi avec succe !!!");
                    comboxDate.getItems().clear();
                    caisse();
                }
            }
        } else if (event.getSource() == btnprintIdentification) {
            if (agentBtnR.isSelected()) {
                printRapport(5);
            } else if (FssBtnR.isSelected()) {
                Cls_alerte.alerteInformation("Information", fonction);
            } else if (ClientBtnR.isSelected()) {
            }
        }
    }

    void caisse() throws SQLException, Exception {
        montatDisponible.setText(princi.recuper("SELECT SUM(MONTATDISPONIBLE) FROM Tcaisse"));
        charger_ComboBox(comboxDate, "SELECT Tdate FROM Tsorti");
    }

    private void actualise() throws Exception {
        nomTfd.setText("");
        postnomTfd.setText("");
        prenomTfd.setText("");
        contactTfd.setText("");
        adressTfd.setText("");
        nomTfd.requestFocus();
        if (ClientBtnR.isSelected()) {
            chargerTable("select * from charge_client", table_client, 0);
            email_Tfd.setText("");
        } else if (agentBtnR.isSelected()) {
            chargerTable("charge_agentProc", table_client, 0);
            email_Tfd.setText("");
        } else {
            chargerTable("Fss", table_client, 0);
        }
    }

    @FXML
    private void mouse_clic_table(MouseEvent event) {
        try {
            if (ClientBtnR.isSelected()) {
                code_client = Integer.parseInt(onTableClick(table_client).get(0));
                nomTfd.setText(onTableClick(table_client).get(1));
                postnomTfd.setText(onTableClick(table_client).get(2));
                prenomTfd.setText(onTableClick(table_client).get(3));
                contactTfd.setText(onTableClick(table_client).get(4));
                adressTfd.setText(onTableClick(table_client).get(5));
                email_Tfd.setText(onTableClick(table_client).get(6));
            } else if (agentBtnR.isSelected()) {
                try {
                    code_client = Integer.parseInt(onTableClick(table_client).get(0));
                    nomTfd.setText(onTableClick(table_client).get(1));
                    postnomTfd.setText(onTableClick(table_client).get(2));
                    prenomTfd.setText(onTableClick(table_client).get(3));
                    contactTfd.setText(onTableClick(table_client).get(4));
                    adressTfd.setText(onTableClick(table_client).get(5));
                    email_Tfd.setText(onTableClick(table_client).get(6));
                } catch (NumberFormatException e) {
                    System.out.println(e.getMessage());
                }

            } else if (FssBtnR.isSelected()) {
                code_client = Integer.parseInt(onTableClick(table_client).get(0));
                nomTfd.setText(onTableClick(table_client).get(1));
                postnomTfd.setText(onTableClick(table_client).get(2));
                prenomTfd.setText(onTableClick(table_client).get(3));
                contactTfd.setText(onTableClick(table_client).get(4));
                adressTfd.setText(onTableClick(table_client).get(5));
            }
        } catch (NumberFormatException e) {
            Cls_alerte.alerteErreur("Error", e.getMessage());
        }
    }

    @FXML
    private void requete_base(ActionEvent event) throws Exception {

        if (event.getSource() == backup_base_donne) {
            if (BackUp12() == true) {
                Cls_alerte.alerteInformation("Information", "Backup terminer avec succes !!!");
            } else {
                Cls_alerte.alerteErreur("ERROR", "Mistake of the backup");
            }
        } else if (event.getSource() == restaure_base_donne) {
            if (isFieldsempty(chemin_backup_Tfd112)) {
                Cls_alerte.alerteErreur("ERROR", "Champ()s vide !!!");
            } else {
                if (isRestoring(chemin_backup_Tfd112.getText()) == true) {
                    RedKeyExterne("accespoint", Strg(chemin_backup_Tfd112.getText()));
                    enrestre(Strg(chemin_backup_Tfd112.getText()));
                }

            }
        } else if (event.getSource() == btn_parcour_restaure1) {
            try {
                chemin_backup_Tfd11.setText(classeFile.Fichier.chouserFichier().getPath());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        } else if (event.getSource() == restaure_base_donne1) {
            if (RedKeyExterne(chemin_backup_Tfd1.getText(), chemin_backup_Tfd11.getText()) == true) {
                if (chemin_backup_Tfd1.getText().equalsIgnoreCase("imprimer")) {
                    Save_link();
                }
                Cls_alerte.alerteInformation("Information Retrer", "Chemin Enregistrer avec succes !!!");
            } else {
                Cls_alerte.alerteErreur("ERROR", "Chemin non Enregistrer");
            }
        } else if (event.getSource() == modifiercaise) {
            if (isFieldsempty(tauxTfd)) {
                Cls_alerte.alerteErreur("ERROR", "Champ()s vide !!!");
            } else {
                call = conne().prepareCall("{Call PrTaux(?)}");
                call.setString(1, tauxTfd.getText());
                call.executeUpdate();
                Cls_alerte.alerteInformation("Information", "Taux modifier avec succes !!!");
                tauxTfd.setText("");
            }
        } else if (event.getSource() == confugBtn) {
            atr.Dialog(Interface_principale_02Controller.container, "/GUI/interface_configuration.fxml");
        }
    }

    @FXML
    private void clic_parcourir_chemin(ActionEvent event) throws SQLException {
        if ((event.getSource() == btn_parcour_backup)) {
            try {
                chemin_backup_Tfd.setText(Cls_autre_.Chemin_ba("*.txt", ".txt").getAbsolutePath());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        } else if (event.getSource() == btn_parcour_restaure) {
            if (btn_parcour_restaure.getText().equals("Browser")) {
                try {
                    chemin_backup_Tfd112.setText(Cls_autre_.Chemin_ba("*.bak", ".bak").getAbsolutePath());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

            }

        }
    }

    private void clic_parcourir_chemin_File_rapport(ActionEvent event) {
        try {
            chemin_File.setText(classeFile.Fichier.chouserFichier().getPath());
            chemin_File.setVisible(true);
        } catch (Exception e) {
        }

    }

    private void Save_link() {
        if (Cls_autre_.AjoutLink("Rapport", url_print()) == true) {
            Cls_alerte.alerteInformation("Information", "Chemin d'acces enregistrer avec succes !!!");
        } else {
            Cls_alerte.alerteAvertissement("Echec!!!!", "Le lient n'a pas ete Sauvé  !!!");
        }
    }

    @FXML
    private void inserserEnter(ActionEvent event) throws Exception {
        if (agentBtnR.isSelected()) {
            chargerTable("charge_agentProc", table_client, 0);
            partirConnecter.setText("Agent");
            actualise();
            email_Tfd.setEditable(true);
        }
    }

    @FXML
    private void InsertClient(ActionEvent event) throws Exception {
        if (ClientBtnR.isSelected()) {
            chargerTable("select * from charge_client", table_client, 0);
            partirConnecter.setText("Client");
            actualise();
            email_Tfd.setEditable(true);

        }
    }

    @FXML
    private void InsertFournisseur(ActionEvent event) throws Exception {
        if (FssBtnR.isSelected()) {
            chargerTable("Fss", table_client, 0);
            email_Tfd.setText("E-mail");
            email_Tfd.setEditable(false);
            partirConnecter.setText("Fournisseur");
            actualise();
        }
    }

    private void recuperId(ActionEvent event) throws SQLException {
        Statement s = conne().createStatement();
        ResultSet rs = s.executeQuery("SELECT code_agent FROM TAGENT WHERE NOM_AGENT='" + nomBnx.getValue() + "'");
        if (rs.next()) {
            idAgent.setText(rs.getString(1));
        }
    }

    @FXML
    private void rechrecherAgent_(KeyEvent event) throws Exception {
        tableAgentLogin.setVisible(true);
        idAgent.setVisible(false);
        contener_agent.setVisible(false);
        chargerTable("LOGIN_RECHER'" + niveaut1.getText() + "'", tableAgentLogin, 0);
    }

    @FXML
    private void recupereId(MouseEvent event) {
        idAgent.setVisible(true);
        contener_agent.setVisible(true);
        idAgent.setText(onTableClick(tableAgentLogin).get(0));
        niveaut1.setText(onTableClick(tableAgentLogin).get(1));
        tableAgentLogin.setVisible(false);
    }

    private void exitVisibleTable(MouseEvent event) {
        tableAgentLogin.setVisible(false);
        contener_agent.setVisible(true);
        idAgent.setVisible(true);

    }

    @FXML
    private void rechrecher_(KeyEvent event) throws Exception {
        if (agentBtnR.isSelected()) {
            chargerTable("AGENT_RECHER'" + rechercherAgent.getText() + "'", table_client, 0);
        }
    }

    @FXML
    private void retire_fonction(ActionEvent event) throws Exception {
        if (Interface_principale_02Controller.codeAgent == codeAgent) {
            Cls_alerte.alerteErreur("ERROR", "Il est connecter pour l'instant");
        } else {
            if (a.retire(codeAgent) == true) {
                chargerTable("SELECT * FROM PERMISSION", Tbpermission, 0);
                System.out.println("vous etes retire de vos fonction");
            }
        }
    }

    void vide() {
        niveaut1.setText("");
        idAgent.setText("");
        password.setText("");
        Username.setText("");
        permissiom.setValue(null);
    }

    @FXML
    private void clicTable_utilisateur(MouseEvent event) {
        codeAgent = Integer.parseInt(onTableClick(Tbpermission).get(0));
    }

    public static boolean niveauAcces(int x) {
        boolean a = false;
        switch (x) {
            case 1:
                if (fonction.equals("RECEPTION") && Integer.toString(niveau).equals("1")) {
                    a = true;
                }
                break;
            case 2:
                if (fonction.equals("VENDEUR") && Integer.toString(niveau).equals("2")) {
                    a = true;
                }
                break;
            case 3:
                if (fonction.equals("COMPTABLE") && Integer.toString(niveau).equals("3")) {
                    a = true;
                }
                break;
            case 4:
                if (fonction.equals("RESPONSABLE") && Integer.toString(niveau).equals("4")) {
                    a = true;
                }
                break;

        }

        return a;
    }

    public void Afficher_qte1(ListView<String> list) {
        try {
            rs = conne().createStatement().executeQuery(" PrAfficheSorti '" + comboxDate.getValue() + "'");
            list.getItems().clear();
            while (rs.next()) {
                list.getItems().add(rs.getString(1) + "    " + rs.getString(2) + "    " + rs.getString(3) + "    " + rs.getString(4));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getNextException());

        }
    }

    @FXML
    private void chargerTableCaisse(ActionEvent event) {

        Afficher_qte1(montatSorti1);
        perimer1.setText(Cls_autre_.
                returnOK("SELECT SUM(MontatSorti) FROM Tsorti WHERE Tdate='" + comboxDate.getValue() + "'"));
    }

    @FXML
    private void refus_number(KeyEvent event) {
        Cls_autre_.isNumeric(montatSorti);
    }

    @FXML
    private void modifier_le_heure(MouseEvent event) {
        if (isFieldsempty(backupheure)) {
            Cls_alerte.alerteAvertissement("Attention", "Champ()s vide");
        } else {
            if (RedKeyExterne("heure", backupheure.getText()) == true) {
                Cls_alerte.alerteInformation("Information", "Changement de l'heure !!!");
                backupheure.setText(TimeBackup());
            }
        }

    }

    boolean deletePersonnel(String proc, String i) {
        boolean bool = false;
        try {
            call = conne().prepareCall(proc);
            call.setString(1, i);
            call.executeUpdate();
            Cls_alerte.alerteInformation("Information Delete", "Supprimer reussi avec succes !!!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return bool;
    }

    public static String Strg(String str) {
        int x = str.length();
        String nstrg = "";
        while (x > 0) {
            nstrg = str.substring(x - 1, x);
            if (nstrg.equals("\\")) {
                return str.substring(0, x);
            }
            x -= 1;
        }
        return null;
    }

    @FXML
    private void Action_voirplus(ActionEvent event) {
        atr.Dialog(Interface_principale_02Controller.container, "/GUI/interface_benefice.fxml");
    }

}
