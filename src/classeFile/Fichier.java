package classeFile;

import java.awt.HeadlessException;
import java.io.*;
import javafx.stage.FileChooser;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author VULEMBERE
 */
public class Fichier {

    public static void Start(File f, String Destination) {
        if (copier(f, Destination) == true) {
            JOptionPane.showMessageDialog(null, "Transpert  Reussie!!!!");
        } else {
            JOptionPane.showMessageDialog(null, "Transpert Echouer !!!!");
        }
    }

    public static boolean copier(File source, String Destination) {
        boolean resultat = false;
        long y = source.length();
// Declaration des flux 
        java.io.FileInputStream sourceFile = null;
        java.io.FileOutputStream destinationFile = null;
        try {

// Ouverture des flux 
            sourceFile = new java.io.FileInputStream(source);
            destinationFile = new java.io.FileOutputStream(Destination);

// Lecture par segment de 0.5Mo 
            byte buffer[] = new byte[512 * 1024];
            int nbLecture;
//JOptionPane.showMessageDialog(null, "La taille max a copier est de "+(y/1048576)+" Mo");
            while ((nbLecture = sourceFile.read(buffer)) != -1) {
//                Frame.TIMER.setText("Reste :"+((y/1048576)-(1048576/1024))+" Mo Sur "+(y/1048576));
                destinationFile.write(buffer, 0, nbLecture);

            }

// Copie réussie 
            resultat = true;
        } catch (java.io.FileNotFoundException f) {

        } catch (java.io.IOException e) {

        } finally {
// Quoi qu'il arrive, on ferme les flux 
            try {
                sourceFile.close();
            } catch (IOException e) {
            }
            try {
                destinationFile.close();
            } catch (IOException e) {
            }
        }
        return (resultat);
    }

    public static File chouserFichier() {
        File fichier = null;
        try {
            FileChooser file = new FileChooser();
            file.setInitialDirectory(new File(System.getProperty("user.home")));
            fichier = file.showOpenDialog(null);
            if (1 == JFileChooser.APPROVE_OPTION) {
                fichier = file.showOpenDialog(null);
            } else if (0 == JFileChooser.CANCEL_OPTION) {
                JOptionPane.showMessageDialog(null, "Aucun file");
            }
        } catch (HeadlessException e) {
            System.out.println(e.getMessage());
        }

        return fichier;
    }
}
