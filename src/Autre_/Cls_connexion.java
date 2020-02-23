package Autre_;

import static Autre_.Cls_preference.localhost;
import static Autre_.Cls_preference.login1;
import static Autre_.Cls_preference.pwd1;
import java.sql.*;

/**
 *
 * @author Akim
 */
public class Cls_connexion {

    private static Connection con = null;

    private Cls_connexion() {
        try {
            con = DriverManager.getConnection(localhost(), login1(), pwd1());
        } catch (SQLException ex) {
            Cls_alerte.alerteErreur("Erreur Connexion", ex.getMessage());
        }
    }

    public static Connection conne() {
        if (con == null) {
            new Cls_connexion();
            System.out.println("Connexion etablir avec succès");
        }
        System.out.println("Connexion exister déjà !!!");
        return con;
    }

    public static boolean isDeconnection() throws SQLException {
        if (!conne().isClosed()) {
            conne().close();
        }
        return false;
    }

    public static boolean testConnection() {
        if (conne() != null) {
            return true;
        }
        return false;
    }

}
