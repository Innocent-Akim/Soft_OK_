package Autre_;

import static Autre_.Cls_connexion.conne;
import static Autre_.Cls_preference.cheminDynamyc;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import static GUI.Interface_principale_02Controller.*;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXPopup;
import java.sql.CallableStatement;
import java.sql.Timestamp;
import java.util.Date;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
import org.controlsfx.control.textfield.TextFields;
import soft_ok.Soft_OK;

/**
 *
 * @author Akm
 */
public class Cls_autre_ {

    private static ResultSet rs;
    private static Statement stm;
    private static PreparedStatement pst;
    public static Image img = null;
    public static CallableStatement call;

    public static boolean isNumeric(TextField l) {
        float a;
        boolean bool = true;
        try {
            a = Float.parseFloat(l.getText());
        } catch (NumberFormatException e) {
            bool = false;
            l.setText("");
        }

        return bool;
    }

    @SuppressWarnings("unchecked")
    public static void chargerTable(String SQL, TableView v, int taille) throws Exception {

        ObservableList<ObservableList> oblist = FXCollections.observableArrayList();
        v.getColumns().clear();

        rs = conne().createStatement().executeQuery(SQL);

        for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {

            final int j = i;
            TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1).toUpperCase());
            col.setPrefWidth((v.getPrefWidth() / rs.getMetaData().getColumnCount()) + taille);
            col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, Object>, ObservableValue<Object>>() {
                @Override
                public ObservableValue<Object> call(TableColumn.CellDataFeatures<ObservableList, Object> param) {
                    return new SimpleObjectProperty(param.getValue().get(j)); //To change body of generated methods, choose Tools | Templates.
                }
            });
            v.getColumns().addAll(col);
        }
        while (rs.next()) {

            ObservableList<String> row = FXCollections.observableArrayList();
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {

                row.add(rs.getString(i));
            }
            oblist.add(row);
        }
        v.setItems(oblist);
    }

    /**
     *
     * @param maTable
     * @return
     */
    @SuppressWarnings("unchecked")
    public static ObservableList<String> onTableClick(TableView maTable) {
        ObservableList<String> o = null;
        try {
            o = (ObservableList) maTable.getItems().get(maTable.getSelectionModel().getSelectedIndex());

        } catch (Exception e) {
        }
        return o;

    }

    public double totat(double q, double p) {
        double tota = 0;
        return tota = q * p;
    }

    public static String pwd_crypte_md5(String password) {
        byte[] uniquekey = password.getBytes();
        byte[] hash = null;
        try {
            hash = MessageDigest.getInstance("MD5").digest(uniquekey);
        } catch (NoSuchAlgorithmException e) {
        }
        StringBuilder hashString = new StringBuilder();
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(hash[i]);
            if (hex.length() == 1) {
                hashString.append('0');
                hashString.append(hex.charAt(hex.length() - 1));
            } else {
                hashString.append(hex.charAt(hex.length() - 2));
            }
        }
        return hashString.toString();

    }

    public static boolean BackUp12() throws Exception {
        try {

            Calendar cal = new GregorianCalendar();
            int heure = cal.get(Calendar.HOUR_OF_DAY);
            int minute = cal.get(Calendar.MINUTE);
            int seconde = cal.get(Calendar.SECOND);
            int pAm = cal.get(Calendar.AM_PM);
            File fichier = new File(Autre_.Cls_preference.url_backup() + "/Sauvegarde_Du_" + LocalDate.now().toString() + "_A_" + heure + "h" + minute + "m" + seconde + "s");
            if (!fichier.exists()) {
                fichier.mkdir();
                String sql = "BACKUP DATABASE " + Autre_.Cls_preference.nomBase() + " TO DISK ='" + fichier.getAbsolutePath() + "\\" + Autre_.Cls_preference.nomBase() + ".bak'";
                stm = conne().createStatement();
                stm.execute(sql);
                return true;
            }
        } catch (SQLException e) {
            throw new Exception("Erreur lors de la sauvegarde" + e.getMessage());
        }
        return false;
    }

    public static boolean isRestoring(String chemin) throws Exception {
        try {
            String mydatabase = Autre_.Cls_preference.nomBase();
            String alter = "ALTER DATABASE  " + mydatabase + " SET SINGLE_USER WITH ROLLBACK IMMEDIATE";
            String user = "USE MASTER RESTORE DATABASE " + mydatabase + " FROM DISK ='" + chemin + "' WITH REPLACE";
            String db = "ALTER DATABASE   " + mydatabase + " SET MULTI_USER";

            stm = conne().createStatement();
            stm.execute(alter);
            System.out.println("ALTER DATABASE...");
            stm.execute(user);
            System.out.println("USE MASTER RESTORE...");
            stm.execute(db);
            System.out.println("ALTER DATABASE...");
            Cls_connexion.isDeconnection();
            Cls_alerte.alerteInformation("Information", "Restauration  terminer avec succes !!!");
            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (!stm.isClosed() | !conne().isClosed()) {

            }
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    public static void charger_ComboBox(ComboBox<String> comboBox, String Requette) throws ClassNotFoundException, SQLException, Exception {
        try {
            pst = conne().prepareStatement(Requette);
            rs = pst.executeQuery();
            while (rs.next()) {
                comboBox.getItems().add(rs.getObject(1).toString().toUpperCase());
            }
        } catch (SQLException e) {
            throw new Exception(e.getMessage());
        } 
    }
    // private static File chemin;
    private static final FileChooser file = new FileChooser();
    // private static final ObservableList liste = FXCollections.observableArrayList("*.Image", "*.jpg");

    public static File Chemin_ba(String cm, String a) {
        File chemin = null;
        String url = cheminDynamyc();

        try {
            //    ObservableList<String> liste1 = FXCollections.observableArrayList(cm, a);
            ObservableList liste = FXCollections.observableArrayList(cm, a);
            if (url.equals("root")) {
                file.setInitialDirectory(new File("C:\\"));
            } else {
                file.setInitialDirectory(new File(url));
            }

            boolean addAll = file.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Fichier", liste));
            chemin = file.showOpenDialog(Soft_OK.stage).getAbsoluteFile();
            if (null != chemin) {
                System.out.println(chemin);
            } else {
                System.out.println(1);

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return chemin;
    }

    public static void enrestre(String chen) throws SQLException {
        pst = conne().prepareCall("{call chemin_back(?)}");
        pst.setString(1, chen);
        pst.execute();
        Cls_alerte.alerteInformation("Information", "Chemin Enregistrer avec succes");
    }

    static String chemin_() throws SQLException {
        stm = conne().createStatement();
        rs = stm.executeQuery("Select chemin From back");
        if (rs.next()) {
            return rs.getString(1);
        }
        return null;
    }

    public static String datebut(DatePicker c) {
        String j, a, m;
        String date1;
        j = c.getEditor().getText().substring(0, 2);
        m = c.getEditor().getText().substring(3, 5);
        a = c.getEditor().getText().substring(6, 10);
        date1 = a + "-" + m + "-" + j;
        return date1;
    }

    public static float setSupprimer(String codeP) {
        float qte = 0;
        try {
            pst = conne().prepareStatement("SELECT qte_pro FROM Tproduit where designation_pro='" + codeP + "'");
            rs = pst.executeQuery();
            if (rs.next()) {
                qte = rs.getFloat("qte_pro");
            }
            rs.close();
            pst.close();
        } catch (SQLException ex) {
            Cls_alerte.alerteErreur("Erreur de Recuperation", ex.getMessage());
        }
        return qte;
    }

    public static boolean AjoutLink(String index, String chemain) {
        boolean val;
        try {
            pst = conne().prepareStatement("AjoutLink '" + index + "','" + chemain + "'");
            pst.execute();
            val = true;
            pst.close();
        } catch (SQLException ex) {
            val = false;
            Cls_alerte.alerteErreur("Erreur de Recuperation", ex.getMessage());
        }
        return val;
    }

    public static String[] extaitIdentite(String ligne) {
        return ligne.split("");
    }

    public static String lireEnregi(String NomFichier) {
        String Ligne = null;
        try {
            FileReader fluxRead = new FileReader(NomFichier);
            try (BufferedReader in = new BufferedReader(fluxRead)) {
                if ((Ligne = in.readLine()) != null) {
                    String[] info = extaitIdentite(Ligne);
                }
            }
        } catch (IOException e) {

        }
        return Ligne;

    }

    public static double SetMontantMAx(String date1, String date2) {
        double f = 0;
        try {
            pst = conne().prepareStatement("Somme_date '" + date1 + "','" + date2 + "'");
            rs = pst.executeQuery();
            if (rs.next()) {
                if (rs.getString("Toltal") == null) {
                    f = 0;
                } else {
                    f = Double.valueOf(rs.getString("Toltal"));
                }

            }
        } catch (SQLException ex) {
            Cls_alerte.alerteErreur("Erreur de Recuperation", ex.getMessage());
        }
        return f;
    }

    public boolean retire(int a) {
        try {
            if (Cls_alerte.alertQuestion("voulais retire la fonction d'un agent !!!", "Retirer !!!") == true) {
                call = conne().prepareCall("{call RETIRE_(?)}");
                call.setInt(1, a);
                call.execute();
                Cls_alerte.alerteInformation("", "vous etes retire de vos fonctions");
            }

        } catch (SQLException ex) {
            Cls_alerte.alerteErreur("Erreur de Recuperation", ex.getMessage());
        }
        return true;
    }

    public static boolean Login(String use, String pwd) throws SQLException {
        boolean r = false;
        pst = conne().prepareCall("{CALL LOGIN1('" + use + "','" + pwd + "')}");
        rs = pst.executeQuery();
        if (rs.next()) {
            nomUtilisateur = rs.getString("NOM");
            niveau = Integer.parseInt(rs.getString("niveau"));
            fonction = rs.getString("permission");
            codeAgent = Integer.parseInt(rs.getString("CodeAgent"));
            email = rs.getString("email_agent");
            telephone = rs.getString("contact_agent");
            r = true;
        }
        return r;
    }

    public static String dateNow(String choix) {
        String retour = null;
        switch (choix) {
            case "annee":
                retour = new Timestamp(new Date().getTime()).toString().substring(0, 4);
                break;
            case "mois":
                retour = new Timestamp(new Date().getTime()).toString().substring(5, 7);
                break;
            case "jour":
                retour = new Timestamp(new Date().getTime()).toString().substring(8, 10);
                break;
        }
        return retour;
    }

    public static String dateMaint1() {
        return (dateNow("jour") + "-" + dateNow("mois") + "-" + dateNow("annee"));
    }

    public static void Afficher_Produit(ListView<String> list) {
        try {
            rs = conne().createStatement().executeQuery("SELECT designation,quantite FROM List_produitExpire");
            while (rs.next()) {
                list.getItems().add(rs.getString(1) + "             " + rs.getString(2));
            }
            rs.close();
        } catch (SQLException ex) {

        }
    }

    public static void Afficher_qte(ListView<String> list) {
        try {
            rs = conne().createStatement().executeQuery("SELECT designation_pro ,qte_pro FROM requisition_");
            while (rs.next()) {
                list.getItems().add(rs.getString(1) + "          " + rs.getString(2));
            }
            rs.close();
        } catch (SQLException ex) {

        }
    }

    public void popupX2(JFXPopup a, String b) {
        try {
            AnchorPane anc = FXMLLoader.load(getClass().getResource(b));
            a.setContent(anc);
            a.setSource(a);
            a.show(JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT);
        } catch (IOException ex) {
            Cls_alerte.alerteErreur("ERROR", ex.getMessage());
        }
    }

    public static String returnOK(String Requete) {

        try {
            rs = conne().createStatement().executeQuery(Requete);
            while (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public static boolean Execute(String rqt) {
        try {
            call = conne().prepareCall(rqt);
            call.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public void Dialog(StackPane pan, String a) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(a));
            JFXDialogLayout d1 = new JFXDialogLayout();
            d1.setBody(root);
            JFXDialog dialo = new JFXDialog(pan, d1, JFXDialog.DialogTransition.CENTER);
            dialo.show(pan);
        } catch (IOException e) {
            System.out.append(e.getMessage());
        }

    }

    public static ObservableList AutoCompression(String Table, String Colone) {
        ObservableList list = FXCollections.observableArrayList();
        try {
            pst = conne().prepareStatement("SELECT " + Colone + " FROM " + Table);
            rs = pst.executeQuery();
            while (rs.next()) {
                list.addAll(rs.getString(Colone));
            }
        } catch (SQLException ex) {
            Cls_alerte.alerteErreur("ERROR", ex.getMessage());
        }
        return list;
    }

    //  Methode Auto Complete
    public static void Autocomplete(TextField txt, AnchorPane pan) {
        txt.getStyleClass().addAll("textfiled", "Search");
        txt.setPrefWidth(186);
        pan.getChildren().add(txt);
    }

    // FOnction D'affichage
    public static void ChargememtCompression(TextField textFied, String Table, String Colonne) {
        textFied.setOnMouseClicked((e) -> {
            TextFields.bindAutoCompletion(textFied, AutoCompression(Table, Colonne));
        });
    }

    //   public static AutoCompress AutoCompressExt = new AutoCompress();
}
