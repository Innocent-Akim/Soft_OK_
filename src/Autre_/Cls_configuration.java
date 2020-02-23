package Autre_;

import static Autre_.Cls_alerte.alerteAvertissement;
import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javafx.scene.control.TextField;
import static champs.champs_vide.isFieldsempty;

public class Cls_configuration {

    public static String ecrireEntreprise(String Entrep, String ficher) {
        String NomFichier = "" + ficher + ".txt";
        try {
            FileWriter fluxwrite = new FileWriter(NomFichier);
            try (BufferedWriter out = new BufferedWriter(fluxwrite)) {
                out.write(Entrep);
                out.newLine();
            }
        } catch (HeadlessException | IOException e) {
            Cls_alerte.alerteErreur("ERROR", e.getMessage());
        }
        return null;
    }
    public static String[] extaitIdentite(String ligne) {
        return ligne.split("");
    }

    public static String lireFicher(String NomFichier) {
        String Ligne = null;
        try {
            FileReader fluxRead = new FileReader(NomFichier);
            try (BufferedReader in = new BufferedReader(fluxRead)) {
                if ((Ligne = in.readLine()) != null) {
                    String[] info = extaitIdentite(Ligne);
                }
            }
        } catch (IOException ex) {
            alerteAvertissement("ERROR", ex.getMessage());
        }
        return Ligne;
    }
    public static boolean ecrire(TextField server, TextField db, TextField login, TextField pwd) {
        boolean retour = false;
        if (isFieldsempty(server, db, login, pwd)) {
            alerteAvertissement("Erreur", "completer le champs vide svp");
        } else {
            ecrireEntreprise("jdbc:sqlserver://" + server.getText() + ";databaseName=" + db.getText() + "", "serverdb");
            ecrireEntreprise(login.getText(), "login");
            ecrireEntreprise(pwd.getText(), "pwd");
            retour = true;
        }
        return retour;
    }
}
