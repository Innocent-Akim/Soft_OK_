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
public class interface_agent {

    private int code;
    private String nom;
    private String postnom;
    private String prenom;
    private String adresse;
    private String contact;
    private String email;

    public interface_agent(String nom, String postnom, String prenom, String adresse, String contact, String email, int code) {
        this.nom = nom;
        this.postnom = postnom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.contact = contact;
        this.email = email;
        this.code = code;
    }

    public interface_agent(String nom, String postnom, String prenom, String adresse, String contact, int code) {
        this.nom = nom;
        this.postnom = postnom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.contact = contact;
        this.code = code;
    }

    public interface_agent() {
    }

    /**
     * @return the nom
     */
    public String getNom() {

        return nom == null ? "" : nom;
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
        return postnom == null ? "" : postnom;
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
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
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
