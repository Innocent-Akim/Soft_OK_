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
public class interface_sorti {

    private String motif;
    private double montant;

    /**
     * @param motif
     * @param montant
     */
    public interface_sorti(String motif, double montant) {
        this.motif = motif;
        this.montant = montant;
    }

    public interface_sorti() {
    }

    public String getMotif() {
        return motif;
    }

    /**
     *
     * @param motif the motif to set
     */
    public void setMotif(String motif) {
        this.motif = motif;
    }

    /**
     * @return the montant
     */
    public double getMontant() {
        return montant;
    }

    /**
     * @param montant the montant to set
     */
    public void setMontant(double montant) {
        this.montant = montant;
    }

}
