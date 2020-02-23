package controler;

import Autre_.Cls_autre_;
import Autre_.Cls_alerte;
import com.jfoenix.controls.JFXPopup;
import static controler.Parametre_interfaceController.niveauAcces;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Akm
 */
public class Interface_acceuilController implements Initializable {

    @FXML
    private AnchorPane acceuill;
    @FXML
    private Pane contenairePn1;
    @FXML
    private ListView<String> list;
    @FXML
    private ListView<String> list1;
    @FXML
    private JFXPopup popupemperation;
    @FXML
    private Label clicIci;
    @FXML
    private Pane contenairePn11;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cont("/GUI/principale_acceuil.fxml", contenairePn1);
        cont("/GUI/principal_acceuil1.fxml", contenairePn11);
        Cls_autre_.Afficher_Produit(list);
        Cls_autre_.Afficher_qte(list1);
        final Tooltip tooltipUsername = new Tooltip();
        tooltipUsername.setText("Clic ici pour voir plus !!!");
        clicIci.setTooltip(tooltipUsername);
        if (niveauAcces(4) == true) {
            clicIci.setVisible(true);
        } else {
            clicIci.setVisible(false);
        }
    }

    void cont(String ecran, Pane a1) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(ecran));
            a1.getChildren().removeAll();
            a1.getChildren().setAll(root);
        } catch (IOException ex) {
            Cls_alerte.alerteErreur("error", ex.getMessage());

        }
    }

    void popupX() {
        try {
            AnchorPane anc = FXMLLoader.load(getClass().getResource("/GUI/interface_produitPerime.fxml"));
            popupemperation.setContent(anc);
            popupemperation.setSource(popupemperation);
            popupemperation.show(JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.LEFT);
        } catch (IOException ex) {
            Cls_alerte.alerteErreur("ERROR", ex.getMessage());
        }
    }

    @FXML
    private void ActionExpiration(MouseEvent event) {
        if (niveauAcces(4) == true) {
            popupX();
        } else {
            clicIci.setVisible(false);
        }
    }
}
