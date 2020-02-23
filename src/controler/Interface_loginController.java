/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import static Autre_.Cls_autre_.Execute;
import static Autre_.Cls_autre_.Login;
import static Autre_.Cls_autre_.dateMaint1;
import static Autre_.Cls_connexion.testConnection;
import static Autre_.Cls_preference.localhost;
import static Autre_.Cls_preference.login1;
import static Autre_.Cls_preference.pwd1;
import Autre_.Cls_alerte;
import static Autre_.Cls_connexion.conne;
import GUI.Interface_principale_02Controller;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author Akim
 */
public class Interface_loginController implements Initializable {

    @FXML
    private FontAwesomeIconView exit;
    @FXML
    private AnchorPane loginPrincipale;
    @FXML
    private JFXPasswordField passwordTfd;
    @FXML
    private JFXTextField usernameTfd;
    @FXML
    private JFXButton btnConnecter;
    private static double xOffset = 0;
    private static double yOffset = 0;
    @FXML
    private Label date_;
    @FXML
    private Pane contenairePn;
    @FXML
    private JFXButton testConnection;
    public static String sever;
    public static String pwd;
    public static String login;
    private int essaie = 5;
    @FXML
    private Label rester_essaie;
    Interface_principale_02Controller princi = new Interface_principale_02Controller();
    public static AnchorPane Principale;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        constructeur();
        Principale = loginPrincipale;
    }

    void TooltiProc() {
        usernameTfd.setTooltip(princi.Titre("veuillez completer votre nom d'utilisateur"));
        passwordTfd.setTooltip(princi.Titre("veuillez completer votre mot de passe "));
//        passwordTfd=TextFields.createClearablePasswordField();
//        AnchorPane.setBottomAnchor(passwordTfd, 20d);
//        AnchorPane.setLeftAnchor(passwordTfd, 20d);
    }

    void constructeur() {
        TooltiProc();
        usernameTfd.requestFocus();
        date_.setText(dateMaint1());
        makeStage(loginPrincipale);
        test();
        if (conne()==null) {
            testConnection.setVisible(true);
            btnConnecter.setVisible(false);
        } else {
            testConnection.setVisible(false);
            btnConnecter.setVisible(true);
            if (Execute("{Call PsFichesStock}") == true) {
                Execute("EXEC PrUserIsnotExist '" + Autre_.Cls_autre_.pwd_crypte_md5("ADMIN") + "'");
                System.out.println("Enregitrement réussir !!!");
            } else {
                System.out.println("Enregitrement non réussir !!!");
            }

        }

    }

    public void test() {
        sever = lire(1);
        pwd = lire(2);
        login = lire(3);
    }

    public static void makeStage(AnchorPane Ancho) {
        Ancho.setOnMousePressed((MouseEvent event) -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        Ancho.setOnMouseDragged((MouseEvent event) -> {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });
    }

    @FXML
    private void closeAPK(MouseEvent event) {
        System.exit(0);

    }

    @FXML
    private void ConnecterF(ActionEvent event) throws IOException, SQLException {
        if (event.getSource() == btnConnecter) {
            login();
        } else if (event.getSource() == testConnection) {
            ecran_remove("/GUI/interface_configuration.fxml");
        }
    }

    private void ecran_remove(String ecran) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(ecran));
            contenairePn.getChildren().removeAll();
            contenairePn.getChildren().setAll(root);
        } catch (IOException ex) {
            Cls_alerte.alerteErreur("error", ex.getMessage());

        }
    }

    private String lire(int x) {
        String a = null;
        switch (x) {
            case 1:
                a = localhost();
                break;
            case 2:
                a = pwd1();
                break;
            case 3:
                a = login1();
                break;
        }
        return a;
    }

    @FXML
    private void key_clic(KeyEvent event) throws SQLException, IOException {
        if (event.getCode() == KeyCode.ENTER) {
            if (!usernameTfd.getText().equals("")) {
                login();
            } else {
                usernameTfd.requestFocus();
            }
        }
    }

    void login() throws SQLException, IOException {
        if (essaie == 0) {
            btnConnecter.setVisible(false);
        } else {

            if (Login(usernameTfd.getText(), passwordTfd.getText()) == true) {
                Parent d = FXMLLoader.load(getClass().getResource("/GUI/interface_principale_02.fxml"));
                Scene scene = new Scene(d);
                Stage st = new Stage();
                st.setTitle("UNE SOLUTION DE GESTION ADAPTEE A VOTRE METIER ".trim());
                st.setScene(scene);
                ((Stage) loginPrincipale.getScene().getWindow()).close();
                st.getIcons().add(new javafx.scene.image.Image("/image/Pill_52px.png"));
                st.show();
            } else {
                Cls_alerte.alerteErreur("ERROR", "Pas autorisé !!!");
                essaie = essaie - 1;
                rester_essaie.setText("Reste encore: " + Integer.toString(essaie));
            }
        }
    }

    @FXML
    private void clic_attend(KeyEvent event) throws SQLException, IOException {
        if (event.getCode() == KeyCode.ENTER) {
            if (!passwordTfd.getText().equals("")) {
                login();
            } else {
                passwordTfd.requestFocus();
            }
        }
    }
}
