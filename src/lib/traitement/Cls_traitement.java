/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.traitement;

import Autre_.Cls_alerte;
import com.sun.comm.Win32Driver;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Formatter;
import javax.comm.CommPortIdentifier;

/**
 *
 * @author Akim
 */
public class Cls_traitement {

    static String destinateur;
    static String notification;
    public static String text;
    private static Cls_sms g;
    private static String port;
    private static String nomModem;
    private static int code;
    private static Cls_query rquete = new Cls_query();

    private static Connection cnx;
    private static ResultSet rs;
    public static CallableStatement call;
    private static Statement stm;

    static int bauds[] = {9600, 14400, 19200, 28800, 33600, 38400, 56000,
        57600, 115200};

    private static String[] jrs = {"Jours", "jrs", "journalier", "journee", "joune"};
    private static String[] mois = {"mois", "mwezi", "mwesi", "moins", "mwa"};
    private static String[] annee = {"annee", "anne", "mwaka", "12mois", "annuelle"};
    private static String[] semaine = {"semaine", "semene", "posho", "7jour", "7S"};
    private static String[] autre = {"montant", "stock", "Expire", "pre-expire", "invalide"};

    public Cls_traitement(String port, String nomModem, int code) {
        Cls_traitement.port = port;
        Cls_traitement.nomModem = nomModem;
        Cls_traitement.code = code;
        text = null;
    }

    public static void traitementNotification() throws SQLException, Exception {
        if (text != null) {
            separerStr(text);
            System.out.println(smsPrevis());
            gsmOn().envoyer(smsPrevis(), destinateur);
        }
    }

    static void separerStr(String str) {
        for (int i = 1; i < str.length(); i++) {
            if (str.substring(i - 1, i).equals("^")) {
                destinateur = str.substring(i);
                notification = str.substring(0, i - 1);
                System.out.println("Destinateur : " + destinateur + " notification : " + notification);
                i = str.length();
                System.out.println(i);
                text = null;
            }
        }
    }

    public void lectureNotification() {
        gsmOn();
        Thread a = new Thread() {
            public void run() {
                while (true) {
                    try {

                        System.out.println("Attente...");
                        text = g.recevoir();
                        traitementNotification();
                        try {
                            this.sleep(2000);
                        } catch (InterruptedException ex) {
                            System.out.println(ex.getMessage());
                        }

                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            }
        };
        a.start();
    }

    public static Cls_sms gsmOn() {
        if (g == null) {
            g = new Cls_sms(port, code, nomModem);
        }
        return g;
    }

    public Cls_traitement() {
    }

    public static String smsPrevis() {

        if (notification.toLowerCase().contains("Jour".toLowerCase())) {
            return "Bonjour Mr,Mm le montant dispoble est de :" + NotificationDB(rquete.valJours) + " USD";
        } else if (notification.toLowerCase().contains("siku".toLowerCase())) {
            return "Bonjour Mr,Mm le montant dispoble est de :" + NotificationDB(rquete.valJours) + " USD";
        } else if (notification.toLowerCase().contains("Leo".toLowerCase())) {
            return "Bonjour Mr,Mm le montant dispoble est de :" + NotificationDB(rquete.valJours) + " USD";
        } else if (notification.toLowerCase().contains("Aujourd'hui".toLowerCase())) {
            return "Bonjour Mr,Mm le montant dispoble est de :" + NotificationDB(rquete.valJours) + " USD";
        } else if (notification.toLowerCase().contains("maintenant".toLowerCase())) {
            return "Bonjour Mr,Mm le montant dispoble est de :" + NotificationDB(rquete.valJours) + " USD";
            //==========================================================================================//
        } else if (notification.toLowerCase().contains("Mois".toLowerCase())) {
            return "Bonjour Mr,Mm le montant dispoble est de :" + NotificationDB(rquete.valMois) + " USD";
        } else if (notification.toLowerCase().contains("mwa".toLowerCase())) {
            return "Bonjour Mr,Mm le montant dispoble est de :" + NotificationDB(rquete.valMois) + " USD";
        } else if (notification.toLowerCase().contains("1mois".toLowerCase())) {
            return "Bonjour Mr,Mm le montant dispoble est de :" + NotificationDB(rquete.valMois) + " USD";
        } else if (notification.toLowerCase().contains("moth".toLowerCase())) {
            return "Bonjour Mr,Mm le montant dispoble est de :" + NotificationDB(rquete.valMois) + " USD";
        } else if (notification.toLowerCase().contains("mwezi".toLowerCase())) {
            return "Bonjour Mr,Mm le montant dispoble est de :" + NotificationDB(rquete.valMois) + " USD";
            //==========================================================================================//
        } else if (notification.toLowerCase().contains("Anuelle".toLowerCase())) {
            return "Bonjour Mr,Mm le montant dispoble est de :" + NotificationDB(rquete.valAnuel) + " USD";
        } else if (notification.toLowerCase().contains("année".toLowerCase())) {
            return "Bonjour Mr,Mm le montant dispoble est de :" + NotificationDB(rquete.valAnuel) + " USD";
        } else if (notification.toLowerCase().contains("anuel".toLowerCase())) {
            return "Bonjour Mr,Mm le montant dispoble est de :" + NotificationDB(rquete.valAnuel) + " USD";
        } else if (notification.toLowerCase().contains("mwaka".toLowerCase())) {
            return "Bonjour Mr,Mm le montant dispoble est de :" + NotificationDB(rquete.valAnuel) + " USD";
        } else if (notification.toLowerCase().contains("annuelle".toLowerCase())) {
            return "Bonjour Mr,Mm le montant dispoble est de :" + NotificationDB(rquete.valAnuel) + " USD";
        } else {
            return "Desole pour que l'application soit a mesure de vous repondrez correctement il faut envoie \n"
                    + " - Jour \n"
                    + " - Mois \n"
                    + " - Anuelle ";

        }
    }
    private final static Formatter _formatter = new Formatter(System.out);

    private ArrayList<String> port() {
        ArrayList<String> ports = new ArrayList();
        Win32Driver win = new Win32Driver();
        win.initialize();
        Enumeration port1 = CommPortIdentifier.getPortIdentifiers();
        CommPortIdentifier portId;
        do {
            portId = (CommPortIdentifier) port1.nextElement();
            if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                ports.add(String.format("%-5s%n", portId.getName()));
            }
        } while (port1.hasMoreElements());
        return ports;
    }

    public String TestPort() {
        int x = 0;
        String z = null;

        while (x < port().size()) {

            if (new GUI.Interface_principale_02Controller().x1 != 1) {
                Cls_traitement a = new Cls_traitement(port().get(x), "HUAWEI", 9600);
                a.lectureNotification();
                break;
            } else {
                x++;
            }

        }
        return z;
    }

    public static Connection cnx() {
        try {
            cnx = DriverManager.getConnection("jdbc:sqlserver://Akim;databaseName=gestion_vente", "SA", "AA1");
        } catch (SQLException ex) {

        }
        return cnx;
    }

    public static Connection con() {
        Connection cnx = null;
        try {
            cnx = DriverManager.getConnection("jdbc:sqlite:smsSettings.db");
            System.out.println("Connection OK");
            return cnx;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public static String NotificationDB(String query) {
        try {
            stm = cnx().createStatement();
            rs = stm.executeQuery(query);
            if (rs.next()) {
                if (rs.getString("x") != null) {
                    return rs.getString("x");
                } else {
                    return "0.0";
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public static boolean ExecuteUpate(String query) {
        try {
            stm = con().createStatement();
            stm.executeQuery(query);
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public void SaveUpdaete(String port, String modem, String centre) {
        String Str = "Insert Into TconfigSMS Values('" + port + "','" + modem + "','" + centre + "')";
        if (ExecuteUpate(Str) == true) {
            Cls_alerte.alerteInformation("Information", "Information enregistrer !!!");
        }
    }
}
