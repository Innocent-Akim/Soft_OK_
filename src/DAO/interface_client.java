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
public class interface_client extends interface_agent {
    
    public interface_client() {
        super();
    }
    
    public interface_client(String nom, String postnom, String prenom, String adresse, String contact, String email, int code) {
        super(nom, postnom, prenom, adresse, contact, email, code);
    }
}
