/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

/**
 *
 * @author Akim
 */
public class interface_produit {

    private String designation;
    private double qte;
    private double punitaire;
    private String amballage;
    private String nomfabri;
    private String barcode;
    private String unite;
    private String devise;
    private String categorie;
    private int code;

    public interface_produit(String designation, double qte, double pu, String ambal, String nomfabr, String barcode, String unite, String devise, int code, String categorie) {
        this.designation = designation;
        this.qte = qte;
        this.punitaire = pu;
        this.amballage = ambal;
        this.nomfabri = nomfabr;
        this.barcode = barcode;
        this.unite = unite;
        this.devise = devise;
        this.code = code;
        this.categorie = categorie;
    }

    public interface_produit() {
    }

    /**
     * @return the categorie
     */
    public String getCategorie() {
        return categorie;
    }

    /**
     * @param categorie the categorie to set
     */
    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    /**
     * @return the designation
     */
    public String getDesignation() {
        return designation;
    }

    /**
     * @param designation the designation to set
     */
    public void setDesignation(String designation) {
        this.designation = designation;
    }

    /**
     * @return the qte
     */
    public double getQte() {
        return qte;
    }

    /**
     * @param qte the qte to set
     */
    public void setQte(double qte) {
        this.qte = qte;
    }

    /**
     * @return the punitaire
     */
    public double getPunitaire() {
        return punitaire;
    }

    /**
     * @param punitaire the punitaire to set
     */
    public void setPunitaire(double punitaire) {
        this.punitaire = punitaire;
    }

    /**
     * @return the amballage
     */
    public String getAmballage() {
        return amballage;
    }

    /**
     * @param amballage the amballage to set
     */
    public void setAmballage(String amballage) {
        this.amballage = amballage;
    }

    /**
     * @return the nomfabri
     */
    public String getNomfabri() {
        return nomfabri;
    }

    /**
     * @param nomfabri the nomfabri to set
     */
    public void setNomfabri(String nomfabri) {
        this.nomfabri = nomfabri;
    }

    /**
     * @return the barcode
     */
    public String getBarcode() {
        return barcode;
    }

    /**
     * @param barcode the barcode to set
     */
    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    /**
     * @return the unite
     */
    public String getUnite() {
        return unite;
    }

    /**
     * @param unite the unite to set
     */
    public void setUnite(String unite) {
        this.unite = unite;
    }

    /**
     * @return the devise
     */
    public String getDevise() {
        return devise;
    }

    /**
     * @param devise the devise to set
     */
    public void setDevise(String devise) {
        this.devise = devise;
    }

    /**
     * @return the code
     */
    public int getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(int code) {
        this.code = code;
    }

}
