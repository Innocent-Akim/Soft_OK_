/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.traitement;

import java.util.LinkedList;
import javax.swing.JOptionPane;
import org.smslib.CIncomingMessage;
import org.smslib.CMessage;
import org.smslib.COutgoingMessage;
import org.smslib.CService;
import javafx.concurrent.Service;

/**
 *
 * @author Akim
 */
public class Cls_sms {

    private CService cs;
    private final String port;
    private final String nomModem;
    private final int code;
    private Service service;
    public int x = 0, y = 0;

    public Cls_sms(String port, int code, String nomModem) {
        this.port = port;
        this.nomModem = nomModem;
        this.code = code;
        this.cs = null;

    }

    public void envoyer(String msg, String dest) throws Exception {
        try {
            cs = new CService(port, code, nomModem, "");
            cs.setSimPin("0000");
            cs.setSmscNumber("+243996100449");
            cs.connect();
            if (cs.getConnected() == true) {
                new GUI.Interface_principale_02Controller().x1 = 0;
            } else {
                new GUI.Interface_principale_02Controller().x1 = 1;
            }
            COutgoingMessage mySms = new COutgoingMessage(dest, msg);
            mySms.setMessageEncoding(CMessage.MessageEncoding.Enc7Bit);
            mySms.setStatusReport(false);
            mySms.setFlashSms(false);
            mySms.setValidityPeriod(1);
            cs.sendMessage(mySms);
            if (mySms.getStatusReport() == false) {
                System.out.println("Message envoyé !");
                cs.disconnect();
            } else {
                System.out.println("Echec d'envoi !");
                cs.disconnect();
            }
        } catch (Exception e) {
            new GUI.Interface_principale_02Controller().x1 = 1;
            JOptionPane.showMessageDialog(null, e.getMessage());

        } finally {
            cs.disconnect();
        }
    }

    public String recevoir() throws Exception {
        String retour = null;
        try {
            cs = new CService(port, code, nomModem, "");
            cs.setSimPin("0000");
            cs.setSmscNumber("+243996100449");
            cs.connect();
            LinkedList maListe = new LinkedList();
            cs.readMessages(maListe, CIncomingMessage.MessageClass.All, 1);
            System.out.println("Nouveau message... " + maListe.size());
            if (maListe.size() > 0) {
                for (int i = 0; i < maListe.size(); i++) {
                    CIncomingMessage msg = (CIncomingMessage) maListe.get(i);
                    retour = (msg.getText() + "^" + msg.getOriginator());
                    System.out.println(retour);
                    cs.deleteMessages(CIncomingMessage.MessageClass.All);
                }
            }
        } catch (Exception ex) {
            new GUI.Interface_principale_02Controller().y1 = 2;
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } finally {
            cs.disconnect();
        }
        return retour;
    }

}
