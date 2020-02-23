/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import Autre_.Cls_charge_Ficher;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import Autre_.*;
import static Autre_.Cls_autre_.datebut;
import static Autre_.Cls_print.rs;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;

/**
 * FXML Controller class
 *
 * @author Akim
 */
public class Interface_ficherStockController implements Initializable {

    @FXML
    private JFXButton printBtn;
    @FXML
    private TextField SearchTfd;
    @FXML
    private DatePicker date_Debut;
    @FXML
    private DatePicker Date_Fin;
    private TableView<String> FicheDestock;
    public static String code, designation, categorie, qteE, puE, PtE, qteS, puS, PtS, qteD, puD, PtD, date;
    private VBox table_list;
    @FXML
    private Pagination pagination;
    ObservableList<Cls_charge_Ficher> listAgent;
    Node nodel[] = new Node[1000];
    @FXML
    private Label codeLbl;
    @FXML
    private Label desinationLbl;
    @FXML
    private Label categorieLbl;
    @FXML
    private Label QteE;
    @FXML
    private Label PE;
    @FXML
    private Label PTE;
    @FXML
    private Label QtS;
    @FXML
    private Label PS;
    @FXML
    private HBox PTS;
    @FXML
    private Label PTS1;
    @FXML
    private Label QteDispo;
    @FXML
    private Label PDispo;
    @FXML
    private Label PTDis;
    @FXML
    private Label Date_Lbl;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            init("AfficheFicherStock");
            // Autre_.Cls_autre_.chargerTable("AfficheFicherStock", FicheDestock, 0);
            date_Debut.setValue(LocalDate.now());
            Date_Fin.setValue(LocalDate.now());
        } catch (Exception ex) {

        }
    }

    @FXML
    private void _PrintFicherStock(ActionEvent event) throws SQLException {
        try {
            if (!datebut(date_Debut).equals("") || !datebut(Date_Fin).equals("")) {
                Cls_rapport.PrintFicherStock(datebut(date_Debut), datebut(Date_Fin));
            } else {
                Cls_alerte.alerteAvertissement(" Attention", "Choisir la Date d'abord...");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @FXML
    private void _RechercherProduit(KeyEvent event) {
        try {
//            Autre_.Cls_autre_.chargerTable("RecheFicherStock '" + SearchTfd.getText() + "'", FicheDestock, 0);
            init("SELECT * FROM TficherStock "
                    + "WHERE ID LIKE '%" + SearchTfd.getText() + "%'"
                    + "OR DESIGNATION_PRO LIKE '%" + SearchTfd.getText() + "%' "
                    + "OR DESIGNATION_CATE LIKE '%" + SearchTfd.getText() + "%' "
                    + "AND DATE BETWEEN CONVERT(date,GETDATE()) AND CONVERT(date,GETDATE())");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ObservableList<Cls_charge_Ficher> getAgentList(String SQL) throws Exception {
        ObservableList<Cls_charge_Ficher> list = FXCollections.observableArrayList();
        list.removeAll();
        rs = Cls_connexion.conne().createStatement().executeQuery(SQL);
        while (rs.next()) {

            Cls_charge_Ficher ficher = new Cls_charge_Ficher();
            ficher.setCode(rs.getString(1));
            ficher.setDesignation(rs.getString(2));
            ficher.setCategorie(rs.getString(3));
            ficher.setQteE(rs.getString(4));
            ficher.setPuE(rs.getString(5));
            ficher.setPtE(rs.getString(6));
            ficher.setQteS(rs.getString(7));
            ficher.setPuS(rs.getString(8));
            ficher.setPtS(rs.getString(9));
            ficher.setQteD(rs.getString(10));
            ficher.setPuD(rs.getString(11));
            ficher.setPtD(rs.getString(12));
            ficher.setDate(rs.getString(13));
            list.add(ficher);

        }
        return list;
    }

    private void setFieldsData(Object... data) {
        try {
            code = data[0].toString();
            designation = data[1].toString();
            categorie = data[2].toString();
            qteE = data[3].toString();
            puE = data[4].toString();
            PtE = data[5].toString();
            qteS = data[6].toString();
            puS = data[7].toString();
            PtS = data[8].toString();
            qteD = data[9].toString();
            puD = data[10].toString();
            PtD = data[11].toString();
            date = data[12].toString();
            Node node = FXMLLoader.load(getClass().getResource("/GUI/interface_pageTable.fxml"));
            table_list.getChildren().add(node);
        } catch (IOException ex) {
            Logger.getLogger(Interface_ficherStockController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int itemsPerPage() {
        return 1;
    }

    public int rowsPerPage() {
        return 10;
    }

    private void init(String rqt) {
        try {
            listAgent = getAgentList(rqt);
//            for (ModeleAgent modeleAgent : listAgent) {
//               // System.out.println("id: " + modeleAgent.getId() + " nom: " + modeleAgent.getNom() + " " + listAgent.size());
//            }
            table_list = new VBox(5);
            table_list.setAlignment(Pos.CENTER);
            pagination.setPageCount((listAgent.size() / rowsPerPage() + 1));
            pagination.setCurrentPageIndex(0);
//            pagination = new Pagination((listAgent.size() / rowsPerPage() + 1), 0);
            pagination.setStyle("-fx-border-color:red;");
            pagination.setPageFactory((Integer pageIndex) -> {
                if (pageIndex > listAgent.size() / rowsPerPage() + 1) { //+1
                    return null;
                } else {
                    return createPage(pageIndex);
                }
            });
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            // Logger.getLogger(AgentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ScrollPane createPage(int pageIndex) {
        table_list.getChildren().clear();
        int lastIndex = 0;
        int displace = listAgent.size() % rowsPerPage();
        if (displace > 0) {
            lastIndex = listAgent.size() / rowsPerPage();
        } else {
            lastIndex = listAgent.size() / rowsPerPage() - 1;
        }

        int page = pageIndex * itemsPerPage();
        ScrollPane spane = new ScrollPane();
        table_list.setPrefWidth(spane.getPrefWidth());
        for (int i = page; i < page + itemsPerPage(); i++) {
            if (lastIndex == pageIndex) {
                for (int j = pageIndex * rowsPerPage(); j < pageIndex * rowsPerPage() + displace; j++) {
                    setFieldsData(
                            listAgent.get(j).getCode(),
                            listAgent.get(j).getDesignation(),
                            listAgent.get(j).getCategorie(),
                            listAgent.get(j).getQteE(),
                            listAgent.get(j).getPuE(),
                            listAgent.get(j).getPtE(),
                            listAgent.get(j).getQteS(),
                            listAgent.get(j).getPuS(),
                            listAgent.get(j).getPtS(),
                            listAgent.get(j).getQteD(),
                            listAgent.get(j).getPuD(),
                            listAgent.get(j).getPtD(),
                            listAgent.get(j).getDate()
                    );
                }
            } else {

                for (int j = pageIndex * rowsPerPage(); j < pageIndex * rowsPerPage() + rowsPerPage(); j++) {
                    setFieldsData(
                            listAgent.get(j).getCode(),
                            listAgent.get(j).getDesignation(),
                            listAgent.get(j).getCategorie(),
                            listAgent.get(j).getQteE(),
                            listAgent.get(j).getPuE(),
                            listAgent.get(j).getPtE(),
                            listAgent.get(j).getQteS(),
                            listAgent.get(j).getPuS(),
                            listAgent.get(j).getPtS(),
                            listAgent.get(j).getQteD(),
                            listAgent.get(j).getPuD(),
                            listAgent.get(j).getPtD(),
                            listAgent.get(j).getDate()
                    );

                }
            }
            spane = new ScrollPane(table_list);
        }
        return spane;
    }
}
