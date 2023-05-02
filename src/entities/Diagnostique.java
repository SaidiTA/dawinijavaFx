/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author msi
 */
public class Diagnostique {
    private int id;
    private int patient_id, dossiers_id;
    private Date date;
    private String symptome,diag_final,resultat_test;

    public Diagnostique() {
    }

    public Diagnostique(int id, int patient_id, int dossiers_id, Date date, String symptome, String diag_final, String resultat_test) {
        this.id = id;
        this.patient_id = patient_id;
        this.dossiers_id = dossiers_id;
        this.date = date;
        this.symptome = symptome;
        this.diag_final = diag_final;
        this.resultat_test = resultat_test;
    }

    public Diagnostique(int id, Date date, String symptome, String diag_final, String resultat_test) {
        this.id = id;
        this.date = date;
        this.symptome = symptome;
        this.diag_final = diag_final;
        this.resultat_test = resultat_test;
    }

    public Diagnostique(Date date, String symptome, String diag_final, String resultat_test) {
        this.date = date;
        this.symptome = symptome;
        this.diag_final = diag_final;
        this.resultat_test = resultat_test;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public int getDossiers_id() {
        return dossiers_id;
    }

    public void setDossiers_id(int dossiers_id) {
        this.dossiers_id = dossiers_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSymptome() {
        return symptome;
    }

    public void setSymptome(String symptome) {
        this.symptome = symptome;
    }

    public String getDiag_final() {
        return diag_final;
    }

    public void setDiag_final(String diag_final) {
        this.diag_final = diag_final;
    }

    public String getResultat_test() {
        return resultat_test;
    }

    public void setResultat_test(String resultat_test) {
        this.resultat_test = resultat_test;
    }

    @Override
    public String toString() {
        return "Diagnostique{" + "id=" + id + ", patient_id=" + patient_id + ", dossiers_id=" + dossiers_id + ", date=" + date + ", symptome=" + symptome + ", diag_final=" + diag_final + ", resultat_test=" + resultat_test + '}';
    }
    
    
}
