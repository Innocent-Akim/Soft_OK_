package GUI;

import Autre_.Cls_autre_;
import static Autre_.Cls_autre_.dateMaint1;
import static Autre_.Cls_connexion.conne;
import Autre_.Cls_alerte;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPopup;
import static controler.Interface_loginController.makeStage;
import static controler.Parametre_interfaceController.niveauAcces;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import lib.traitement.Cls_sms;
import lib.traitement.Cls_traitement;

/**
 * FXML Controller class
 *
 * @author Akm
 */
public class Interface_principale_02Controller implements Initializable {

    public static StackPane container;
    public static StackPane stp;
    @FXML
    private StackPane ecran_principal;
    @FXML
    private JFXButton acceuilBtn;
    @FXML
    private JFXButton approviBtn;
    @FXML
    private JFXButton produitBtn;
    @FXML
    private JFXButton venteBtn;
    @FXML
    private JFXButton statistiBtn;
    @FXML
    private JFXButton document_;
    @FXML
    private JFXButton parameBtn;
    @FXML
    private Label nom_concter_;
    public static Label heure_du_jours;
    @FXML
    private JFXPopup popup;
    @FXML
    private AnchorPane containere;
    @FXML
    private AnchorPane unte;
    @FXML
    private VBox vboxDeconexion;
    public static StackPane stac;
    public static String nomUtilisateur;
    public static int niveau;
    public static String fonction;
    public static int codeAgent;
    public static String telephone;
    public static String email;
    @FXML
    private Label username;
    @FXML
    private Circle alerter;
    @FXML
    private Label date_actuelle;
    @FXML
    private Circle alerte_2;
    @FXML
    private Circle alerter_3;
    int s = 0;
    @FXML
    private Circle alerter_4;
    @FXML
    private Label niveauConnexion;
    @FXML
    private JFXPopup popapConnexion;
    @FXML
    private FontAwesomeIconView btnConnection_1;
    @FXML
    private JFXButton parameBtn1;
    @FXML
    private Label timer;
    @FXML
    private JFXButton deconnectionBtn;
    public int x1 = 0, y1 = 0;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        container = ecran_principal;
        construicteur();
        stac = ecran_principal;
        makeStage(containere);

    }

    void construicteur() {
        username.setText(nomUtilisateur);
        nom_concter_.setText(Integer.toString(niveau));
        ecran_remove("interface_acceuil.fxml");
        date_actuelle.setText(dateMaint1());
        Auto_backup();
        niveauConnexion.setText("<-- Accueil -->");
        if (acces() != true) {
            s = 1;
        } else {
            s = 0;
        }
        try {
            AnimationTimer tim = new AnimationTimer() {
                @Override
                public void handle(long now) {
                    try {
                        timer.setText(" " + LocalTime.now().toString().substring(0, 8));
                    } catch (Exception e) {
                        System.out.println(Arrays.toString(e.getSuppressed()));
                    }
                }
            };
            tim.start();
        } catch (Exception e) {
        }
        TooltiPftc();
        stp = container;
        Cls_traitement a = new Cls_traitement("COM23", "ZTE", 9600);
        a.lectureNotification();
    }

    public void TooltiPftc() {
        acceuilBtn.setTooltip(Titre("Accueil"));
        parameBtn.setTooltip(Titre("Parametrer"));
        produitBtn.setTooltip(Titre("Mise en stock"));
        approviBtn.setTooltip(Titre("Approvisionnement"));
        venteBtn.setTooltip(Titre("Venter"));
        parameBtn1.setTooltip(Titre("Aides"));
        statistiBtn.setTooltip(Titre("Statistique"));
        document_.setTooltip(Titre("Rapport"));
        deconnectionBtn.setTooltip(Titre("Déconnexion"));
    }

    public Tooltip Titre(String titre) {
        Tooltip a = new Tooltip();
        a.setText(titre);
        return a;
    }

    @FXML
    private void principale_appel(ActionEvent event) {
        if (event.getSource() == approviBtn) {

            ecran_remove("interface_appro.fxml");

            niveauConnexion.setText("<-- Approvisionnement -->");
        } else if (event.getSource() == produitBtn) {

            ecran_remove("interface_produit.fxml");

            niveauConnexion.setText("<-- Produit -->");

        } else if (event.getSource() == parameBtn) {

            ecran_remove("parametre_interface.fxml");

            niveauConnexion.setText("<-- Parametres -->");

        } else if (event.getSource() == venteBtn) {

            ecran_remove("interface_vente.fxml");

            niveauConnexion.setText("<-- Ventes -->");

        } else if (event.getSource() == statistiBtn) {

            ecran_remove("interface_statistique.fxml");

            niveauConnexion.setText("<-- Statistique -->");

        } else if (event.getSource() == acceuilBtn) {
            ecran_remove("interface_acceuil.fxml");
            niveauConnexion.setText("<-- Accueil -->");
        } else if (event.getSource() == document_) {
            ecran_remove("interface_rapport.fxml");

            niveauConnexion.setText("<-- Rapport -->");
        } else if (event.getSource() == parameBtn1) {
            niveauConnexion.setText("<-- Aides -->");

            ecran_remove("interface_help.fxml");
        }

    }

    public void ecran_remove(String ecran) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(ecran));
            ecran_principal.getChildren().removeAll();
            ecran_principal.getChildren().setAll(root);
        } catch (IOException ex) {
            Cls_alerte.alerteErreur("error", ex.getMessage());
        }
    }
    double x = 0;
    double y = 0;

    @FXML
    private void dragged1(MouseEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setX(event.getScreenX() - x);
        stage.setY(event.getScreenY() - y);
    }

    @FXML
    private void pressed(MouseEvent event) {
        x = event.getSceneX();
        y = event.getSceneY();
    }

    private void backup(String a, String b) throws SQLException {
        Statement s1 = conne().createStatement();
        s1.execute("Backup_'" + a + "','" + b + "'");
    }

    public void Auto_backup() {
        Thread clock;
        clock = new Thread() {
            @Override
            public void run() {
                for (;;) {
                    try {
                        if (LocalTime.now().toString().substring(0, 8).equals(Autre_.Cls_preference.TimeBackup())) {
                            Calendar cal = new GregorianCalendar();
                            int heure = cal.get(Calendar.HOUR_OF_DAY);
                            int minute = cal.get(Calendar.MINUTE);
                            int seconde = cal.get(Calendar.SECOND);
                            File fichier = new File(Autre_.Cls_preference.url_backup() + "/Sauvegarde_Du_" + LocalDate.now().toString() + "_A_" + heure + "h" + minute + "m" + seconde + "s");
                            backup(fichier.getAbsolutePath(), Autre_.Cls_preference.nomBase());
                            try {
                                Autre_.Cls_autre_.BackUp12();
                            } catch (Exception ex) {
                                System.out.println(ex.getMessage());
                            }
                        }
                    } catch (SQLException ex) {
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Cls_alerte.alerteErreur("", ex.getMessage());
                    }
                }
            }
        };
        clock.start();
    }

    @FXML
    private void deconnection(ActionEvent event) throws IOException {

        AnchorPane anc = FXMLLoader.load(getClass().getResource("/GUI/interface_deconnection.fxml"));
        popup.setContent(anc);
        popup.setSource(vboxDeconexion);
        popup.show(JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT);

    }

    boolean acces() {
        if (niveauAcces(1) == true) {
            approviBtn.setVisible(false);
            produitBtn.setVisible(false);
            parameBtn.setVisible(true);
            venteBtn.setVisible(false);
            statistiBtn.setVisible(false);
            document_.setVisible(false);
            alerter.setFill(Color.BLUE);
            alerte_2.setFill(Color.RED);
            alerter_3.setFill(Color.BLACK);
            alerter_4.setFill(Color.SKYBLUE);
            btnConnection_1.setVisible(false);

        } else if (niveauAcces(2) == true) {
            approviBtn.setVisible(false);
            produitBtn.setVisible(false);
            parameBtn.setVisible(false);
            venteBtn.setVisible(true);
            statistiBtn.setVisible(false);
            document_.setVisible(false);
            alerter.setFill(Color.RED);
            alerte_2.setFill(Color.BLUE);
            alerter_3.setFill(Color.BLACK);
            alerter_4.setFill(Color.SKYBLUE);
            btnConnection_1.setVisible(false);
        }
        if (niveauAcces(3) == true) {
            approviBtn.setVisible(false);
            produitBtn.setVisible(false);
            parameBtn.setVisible(true);
            venteBtn.setVisible(false);
            statistiBtn.setVisible(true);
            document_.setVisible(false);
            alerter.setFill(Color.BLACK);
            alerte_2.setFill(Color.RED);
            alerter_3.setFill(Color.BLUE);
            alerter_4.setFill(Color.SKYBLUE);
            btnConnection_1.setVisible(false);

        } else if (niveauAcces(4) == true) {
            approviBtn.setVisible(true);
            produitBtn.setVisible(true);
            parameBtn.setVisible(true);
            venteBtn.setVisible(true);
            statistiBtn.setVisible(true);
            document_.setVisible(true);
            alerter.setFill(Color.SKYBLUE);
            alerte_2.setFill(Color.RED);
            alerter_3.setFill(Color.BLUE);
            alerter_4.setFill(Color.BLACK);
            btnConnection_1.setVisible(true);

        }
        return true;
    }

    @FXML
    private void Popup_actionConfiguration(MouseEvent event) {
        new Cls_autre_().popupX2(popapConnexion, "/GUI/interface_resseau.fxml");
    }

    @FXML
    private void key_Help(KeyEvent event) {
        if (event.getCode() == KeyCode.F1) {
            niveauConnexion.setText("<-- Aides -->");
            ecran_remove("interface_help.fxml");
        }
    }

}
