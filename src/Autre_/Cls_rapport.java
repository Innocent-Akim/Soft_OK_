package Autre_;

import static Autre_.Cls_autre_.returnOK;
import static Autre_.Cls_imprimer._impresion;
import java.sql.SQLException;

/**
 *
 * @author Akim
 */
public class Cls_rapport {

    public static String requeteChemin = "SELECT CHAIMAIN FROM Link_File";

    public static void printRapport(int x) throws SQLException {
        switch (x) {
            case 1:
                _impresion("SELECT * FROM List_produitExpire", returnOK(requeteChemin) + "perimes.jrxml");
                break;
            case 2:
                _impresion(" SELECT* FROM DANS_710", returnOK(requeteChemin) + "dans7_jours.jrxml");
                break;
            case 3:
                _impresion("SELECT * FROM requisition_ ORDER BY NOM_FSS", returnOK(requeteChemin) + "requisition_r.jrxml");
                break;
            case 4:
                _impresion("listProduit", returnOK(requeteChemin) + "liste_de_produit.jrxml");
                break;
            case 5:
                _impresion("SELECT * FROM Tagent  ORDER BY nom_agent", returnOK(requeteChemin) + "liste_agent.jrxml");
                break;
        }
    }

    public static void printParametre(String a, String b) throws SQLException {
        _impresion("SELECT * FROM  INVENTAIRE WHERE DATE BETWEEN '" + a + "'AND'" + b + "' ORDER BY designation_cate ", returnOK(requeteChemin) + "rapportVente_.jrxml");
    }

    public static boolean PrintFicherStock(String a, String b) throws SQLException {
        _impresion("SELECT * FROM TficherStock WHERE DATE BETWEEN '" + a + "'AND'" + b + "'", returnOK(requeteChemin) + "Ficher_Stock.jrxml");
        return true;
    }

    public static void printPameretre1(String x) throws SQLException {
        _impresion("SELECT * FROM facture_bien_ WHERE FACTURE='" + x + "'", returnOK(requeteChemin) + "facture_2.jrxml");
    }

    public static boolean print(String a) throws SQLException {
        _impresion("SELECT *  FROM  ViewRapportAppro WHERE COMMANDE='" + a + "' ORDER BY COMMANDE", returnOK(requeteChemin) + "rapportAppro.jrxml");
        return true;
    }

}
