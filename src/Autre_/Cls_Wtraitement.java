package Autre_;

import static Autre_.Cls_connexion.conne;
import java.io.IOException;
import java.sql.*;
import java.sql.SQLException;

/**
 *
 * @author Akim
 */
public class Cls_Wtraitement {

    private static ResultSet rs;
    private static Statement stm;
    private static PreparedStatement pst;

    public float setValeur(String rqt, String colone) {
        float val = 0;
        try {
            pst = conne().prepareStatement(rqt);
            rs = pst.executeQuery();
            if (rs.next()) {
                val = rs.getFloat(colone);
            } else {
                val = 0;
            }
        } catch (SQLException ex) {
            Cls_alerte.alerteErreur("ERROR", ex.getMessage());
        }
        return val;
    }

    public static boolean ActiveReseau(String Action, String NameWiffi, String key) {
        boolean bool = false;
        if (null != Action) {
            switch (Action) {
                case "Start":
                    Action = "netsh wlan Start hostednetwork";
                    break;
                case "Stop":
                    Action = "netsh wlan Stop hostednetwork";
                    break;
                case "Set":
                    if (key.length() < 9) {
                        Cls_alerte.alerteErreur("Alerte", "Le Mot de passe doit etre supperieur a 8");
                    } else {
                        Action = "netsh wlan set  hostednetwork mode=allow ssid='" + NameWiffi + "' key ='" + key + "'";
                    }
                    break;
                default:
                    break;
            }
        }
        try {
            String[] commande = {"cmd.exe", "/C", Action, "Start"};
            Runtime r = Runtime.getRuntime();
            Process p = r.exec(commande);
            try {
                p.waitFor();
                bool = true;
            } catch (InterruptedException ex) {
                bool = false;
                Cls_alerte.alerteErreur("ERROR", ex.getMessage());
            }
        } catch (IOException ex) {
            Cls_alerte.alerteErreur("ERROR", ex.getMessage());
        }
        return bool;
    }

    public static void LancerApplication(String chemain) {
        try {
            Runtime r = Runtime.getRuntime();
            r.exec(new String[]{chemain});
        } catch (IOException ex) {
            Cls_alerte.alerteErreur("ERROR", ex.getMessage());
        }
    }

    public void SartCalculator() {
        LancerApplication("C:\\Windows\\System32\\calc.exe");
    }
}
