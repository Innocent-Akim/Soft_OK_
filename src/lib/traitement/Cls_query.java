/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.traitement;

/**
 *
 * @author Akim
 */
public class Cls_query {

    public String valJours = "SELECT SUM(pt_v) AS x FROM Tvente INNER JOIN TventeD ON Tvente.num_fact=TventeD.num_fact WHERE CONVERT(date,date_vente)=GETDATE()";
    public String valMois = "SELECT SUM(pt_v) AS x FROM Tvente INNER JOIN TventeD ON Tvente.num_fact=TventeD.num_fact WHERE CONVERT(date,date_vente) BETWEEN DATEADD(DAY,-30,GETDATE()) AND CONVERT(date,GETDATE())";
    public String valAnuel = "SELECT SUM(pt_v) AS x FROM Tvente INNER JOIN TventeD ON Tvente.num_fact=TventeD.num_fact WHERE CONVERT(date,date_vente) BETWEEN DATEADD(MONTH,-12,GETDATE()) AND CONVERT(date,GETDATE())";

}
