/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Autre_;

/**
 *
 * @author Akim
 */
public class Cls_charge_Ficher {

    private String code, designation, categorie, qteE, puE, PtE, qteS, puS, PtS, qteD, puD, PtD, date;

    /**
     */
    public Cls_charge_Ficher() {
    }

    public Cls_charge_Ficher(
            String code, String designation,
            String categorie, String qteE, String puE,
            String PtE, String qteS, String puS, String PtS, String qteD,
            String puD, String PtD, String date
    ) {
        this.code = code;
        this.designation = designation;
        this.categorie = categorie;
        this.qteE = qteE;
        this.puE = puE;
        this.PtE = PtE;
        this.qteS = qteS;
        this.puS = puS;
        this.PtS = PtS;
        this.qteD = qteD;
        this.puD = puD;
        this.PtD = PtD;
        this.date = date;

    }

    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
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
     * @return the qteE
     */
    public String getQteE() {
        return qteE;
    }

    /**
     * @param qteE the qteE to set
     */
    public void setQteE(String qteE) {
        this.qteE = qteE;
    }

    /**
     * @return the puE
     */
    public String getPuE() {
        return puE;
    }

    /**
     * @param puE the puE to set
     */
    public void setPuE(String puE) {
        this.puE = puE;
    }

    /**
     * @return the PtE
     */
    public String getPtE() {
        return PtE;
    }

    /**
     * @param PtE the PtE to set
     */
    public void setPtE(String PtE) {
        this.PtE = PtE;
    }

    /**
     * @return the qteS
     */
    public String getQteS() {
        return qteS;
    }

    /**
     * @param qteS the qteS to set
     */
    public void setQteS(String qteS) {
        this.qteS = qteS;
    }

    /**
     * @return the puS
     */
    public String getPuS() {
        return puS;
    }

    /**
     * @param puS the puS to set
     */
    public void setPuS(String puS) {
        this.puS = puS;
    }

    /**
     * @return the PtS
     */
    public String getPtS() {
        return PtS;
    }

    /**
     * @param PtS the PtS to set
     */
    public void setPtS(String PtS) {
        this.PtS = PtS;
    }

    /**
     * @return the qteD
     */
    public String getQteD() {
        return qteD;
    }

    /**
     * @param qteD the qteD to set
     */
    public void setQteD(String qteD) {
        this.qteD = qteD;
    }

    /**
     * @return the puD
     */
    public String getPuD() {
        return puD;
    }

    /**
     * @param puD the puD to set
     */
    public void setPuD(String puD) {
        this.puD = puD;
    }

    /**
     * @return the PtD
     */
    public String getPtD() {
        return PtD;
    }

    /**
     * @param PtD the PtD to set
     */
    public void setPtD(String PtD) {
        this.PtD = PtD;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

}
