/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Autre_;
import static Autre_.Cls_connexion.conne;
import GUI.Interface_principale_02Controller;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static Autre_.Cls_autre_.call;

public class Cls_enregister {

    private PreparedStatement ps;
    private ResultSet rs;

    public boolean enregistre(Object ob, String code, String F) throws SQLException {

        if (ob instanceof DAO.interface_produit) {
            DAO.interface_produit prod = (DAO.interface_produit) ob;
            call = conne().prepareCall("{call produit_(?,?,?,?,?,?,?,?,?)}");
            call.setString(1, prod.getDesignation());
            call.setDouble(2, prod.getQte());
            call.setDouble(3, prod.getPunitaire());
            call.setString(4, prod.getCategorie());
            call.setString(5, prod.getAmballage());
            call.setString(6, prod.getNomfabri());
            call.setString(7, prod.getBarcode());
            call.setString(8, prod.getUnite());
            call.setString(9, prod.getDevise());
            call.executeUpdate();
        } else if (ob instanceof DAO.interface_client) {
            DAO.interface_client prod = (DAO.interface_client) ob;
            call = conne().prepareCall("{call client_(?,?,?,?,?,?)}");
            call.setString(1, prod.getNom());
            call.setString(2, prod.getPostnom());
            call.setString(3, prod.getPrenom());
            call.setString(5, prod.getAdresse());
            call.setString(4, prod.getContact());
            call.setString(6, prod.getEmail());
            call.executeUpdate();
        } else if (ob instanceof DAO.interface_agent) {
            DAO.interface_agent prod = (DAO.interface_agent) ob;
            call = conne().prepareCall("{call Agent_(?,?,?,?,?,?)}");
            call.setString(1, prod.getNom());
            call.setString(2, prod.getPostnom());
            call.setString(3, prod.getPrenom());
            call.setString(5, prod.getAdresse());
            call.setString(4, prod.getContact());
            call.setString(6, prod.getEmail());
            call.executeUpdate();
        } else if (ob instanceof DAO.interface_fournisseur) {
            DAO.interface_fournisseur prod = (DAO.interface_fournisseur) ob;
            call = conne().prepareCall("{call fournisseur_(?,?,?,?,?)}");
            call.setString(1, prod.getNom());
            call.setString(2, prod.getPostnom());
            call.setString(3, prod.getPrenom());
            call.setString(4, prod.getContact());
            call.setString(5, prod.getAdresse());
            call.executeUpdate();
        } else if (ob instanceof DAO.interface_user) {
            DAO.interface_user prod = (DAO.interface_user) ob;
            call = conne().prepareCall("{call USENAME(?,?,?,?)}");
            call.setString(1, prod.getUsername());
            call.setString(2, prod.getPassword());
            call.setString(3, F);
            call.setString(4, code);
            call.executeUpdate();
        }
        return true;
    }

    public boolean update(Object ob) throws SQLException {
        if (ob instanceof DAO.interface_produit) {

            DAO.interface_produit prod = (DAO.interface_produit) ob;
            call = conne().prepareCall("{call produit_updete(?,?,?,?,?,?,?,?,?,?)}");
            call.setString(1, prod.getDesignation());
            call.setDouble(2, prod.getQte());
            call.setDouble(3, prod.getPunitaire());
            call.setString(4, prod.getAmballage());
            call.setString(5, prod.getNomfabri());
            call.setString(6, prod.getBarcode());
            call.setInt(7, prod.getCode());
            call.setString(8, prod.getUnite());
            call.setString(9, prod.getDevise());
            call.setString(10, prod.getCategorie());
            call.executeUpdate();

        } else if (ob instanceof DAO.interface_client) {

            DAO.interface_client v = (DAO.interface_client) ob;
            call = conne().prepareCall("{call client_update(?,?,?,?,?,?,?)}");
            call.setString(1, v.getNom());
            call.setString(2, v.getPostnom());
            call.setString(3, v.getPrenom());
            call.setString(5, v.getAdresse());
            call.setString(4, v.getContact());
            call.setString(6, v.getEmail());
            call.setInt(7, v.getCode());
            call.executeUpdate();
        } else if (ob instanceof DAO.interface_agent) {
            DAO.interface_agent prod = (DAO.interface_agent) ob;
            call = Autre_.Cls_connexion.conne().prepareCall("{call AgentUpdate(?,?,?,?,?,?,?)}");
            call.setInt(1, prod.getCode());
            call.setString(2, prod.getNom());
            call.setString(3, prod.getPostnom());
            call.setString(4, prod.getPrenom());
            call.setString(6, prod.getAdresse());
            call.setString(5, prod.getContact());
            call.setString(7, prod.getEmail());
            call.executeUpdate();
        } else if (ob instanceof DAO.interface_fournisseur) {
            DAO.interface_fournisseur prod = (DAO.interface_fournisseur) ob;
            call = Autre_.Cls_connexion.conne().prepareCall("{call fournisseurUpdate_(?,?,?,?,?,?)}");
            call.setInt(1, prod.getCode());
            call.setString(2, prod.getNom());
            call.setString(3, prod.getPostnom());
            call.setString(4, prod.getPrenom());
            call.setString(5, prod.getContact());
            call.setString(6, prod.getAdresse());
            call.executeUpdate();
        }
        return true;
    }
    public boolean Enregistre1(Object ob) {
        boolean retu = false;
        if (ob instanceof DAO.interface_sorti) {
            DAO.interface_sorti sort = (DAO.interface_sorti) ob;
            try {
                call = conne().prepareCall("{call TsortiProc (?,?,?)}");
                call.setDouble(1, sort.getMontant());
                call.setString(2, sort.getMotif());
                call.setInt(3, Interface_principale_02Controller.codeAgent);
                call.executeUpdate();
                retu = true;

            } catch (SQLException ex) {
                retu = false;
            }

        }
        return retu;
    }

}
