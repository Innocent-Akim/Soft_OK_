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
public class interface_fournisseur {

    private String nom;
    private String postnom;
    private String prenom;
    private String adresse;
    private String contact;
    private int code;

    public interface_fournisseur() {

    }

    public interface_fournisseur(String nom, String postnom, String prenom, String adresse, String contact, int code) {
        this.nom = nom;
        this.postnom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.contact = contact;
        this.code = code;
    }

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return the postnom
     */
    public String getPostnom() {
        return postnom;
    }

    /**
     * @param postnom the postnom to set
     */
    public void setPostnom(String postnom) {
        this.postnom = postnom;
    }

    /**
     * @return the prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * @param prenom the prenom to set
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * @return the adresse
     */
    public String getAdresse() {
        return adresse;
    }

    /**
     * @param adresse the adresse to set
     */
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    /**
     * @return the contact
     */
    public String getContact() {
        return contact;
    }

    /**
     * @param contact the contact to set
     */
    public void setContact(String contact) {
        this.contact = contact;
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
