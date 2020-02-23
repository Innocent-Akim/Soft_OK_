/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import static Autre_.Cls_autre_.charger_ComboBox;
import static Autre_.Cls_connexion.conne;
import static Autre_.Cls_connexion.isDeconnection;
import static Autre_.Cls_rapport.printParametre;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedAreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Akm
 */
public class Interface_statistiqueController implements Initializable {

    private PieChart pane_view1;

    XYChart.Series<String, Integer> series = new XYChart.Series<>();

    private PreparedStatement ps;
    private ResultSet rs;
    private JFXDatePicker Date_vente;
    private StackedAreaChart<String, Double> graph_en_batton;
    @FXML
    private AreaChart<String, Integer> StackedAreaChart;
    @FXML
    private DatePicker dateDebut;
    @FXML
    private DatePicker dateFin;
    @FXML
    private JFXButton avalidationBtn;
    @FXML
    private Label MontantConcerner;
    @FXML
    private JFXButton printOk;
    private JFXButton ValideStatistiqueBtn;
    @FXML
    private Label MontantConcerner1;
    @FXML
    private Label pourcentage_statique;
    @FXML
    private Label pourcentage_rapport;
    @FXML
    private PieChart graphe_statique;
    ObservableList<PieChart.Data> staticView;
    ArrayList<Integer> cout = new ArrayList<>();
    ArrayList<String> produit = new ArrayList<>();
    @FXML
    private JFXComboBox<String> comboxCategorie;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        StackedAreaChart.getData().add(series);
        try {
            charger_ComboBox(comboxCategorie, "SELECT designation_cate FROM VraiStatistique");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
    private void statistique() throws SQLException {
        double val1 = 0, val2 = 0, pource;

        ps = conne().prepareStatement("SELECT * FROM STATISTUQUE WHERE "
                + "jour BETWEEN '" + Autre_.Cls_autre_.datebut(dateDebut) + "'AND'" + Autre_.Cls_autre_.datebut(dateFin) + "'");
        rs = ps.executeQuery();
        while (rs.next()) {
            series.getData().add(new XYChart.Data<>(rs.getString(2).substring(0, 10).toUpperCase(), rs.getInt(1)));
        }
        ps = conne().prepareStatement("SELECT SUM(Nbre),SUM(MONTANT) FROM STATISTUQUE WHERE "
                + "jour BETWEEN '" + Autre_.Cls_autre_.datebut(dateDebut) + "'AND'" + Autre_.Cls_autre_.datebut(dateFin) + "'");
        rs = ps.executeQuery();
        if (rs.next()) {
            MontantConcerner.setText(rs.getString(2));
            val1 = Double.parseDouble(rs.getString(2));
            val2 = Double.parseDouble(rs.getString(1));
            pource = (val2 * 100) / val1;
            pourcentage_rapport.setText(Double.toString(pource) + " %");
        }
        isDeconnection();
    }

    private void Vraistatistique() {
        double val1 = 0, val2 = 0, pource = 0;
        try {
            staticView = FXCollections.observableArrayList();
            ps = conne().prepareStatement("SELECT * FROM VraiStatistique");
            rs = ps.executeQuery();
            while (rs.next()) {
                staticView.add(new PieChart.Data(rs.getString("Code") + " " + rs.getString("designation_cate"), rs.getInt("CODE")));
                produit.add(rs.getString("designation_cate") + rs.getString("Code"));
                cout.add(Integer.parseInt(rs.getString("Code")));

            }
            ps = conne().prepareStatement("SELECT SUM(MONTANT),SUM(Code) FROM VraiStatistique");
            rs = ps.executeQuery();
            if (rs.next()) {
                MontantConcerner1.setText(rs.getString(1));
                val1 = Double.parseDouble(rs.getString(2));
                val2 = Double.parseDouble(rs.getString(1));
                pource = (val1 * 100) / val2;
                pourcentage_statique.setText(Double.toString(pource) + " %");
            }
            isDeconnection();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void valider(ActionEvent event) throws SQLException {
        if (event.getSource() == avalidationBtn) {
            statistique();
            Vraistatistique();
            graphe_statique.setData(staticView);
        } else if (event.getSource() == printOk) {
            printParametre(Autre_.Cls_autre_.datebut(dateDebut), Autre_.Cls_autre_.datebut(dateFin));
        } else if (event.getSource() == ValideStatistiqueBtn) {
            graphe_statique.setData(staticView);
        }

    }
}
