/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author msi
 */
public class Dossier {
   private int id;
   private int patient_id;
   private int medecin_id;
   private int numero ;
   private String code_apci, description;

    public Dossier() {
    }
    
    public Dossier(int id, int patient_id, int medecin_id, int numero, String code_apci, String description) {
        this.id = id;
        this.patient_id = patient_id;
        this.medecin_id = medecin_id;
        this.numero = numero;
        this.code_apci = code_apci;
        this.description = description;
    }
    public Dossier(int id, int patient_id, int numero, String code_apci, String description) {
        this.id = id;
        this.patient_id = patient_id;
        this.numero = numero;
        this.code_apci = code_apci;
        this.description = description;
    }

    public Dossier(int id, int numero, String code_apci, String description) {
        this.id = id;
        this.numero = numero;
        this.code_apci = code_apci;
        this.description = description;
    }

    public Dossier(int numero, String code_apci, String description) {
        this.numero = numero;
        this.code_apci = code_apci;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getCode_apci() {
        return code_apci;
    }

    public void setCode_apci(String code_apci) {
        this.code_apci = code_apci;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public int getMedecin_id() {
        return medecin_id;
    }

    public void setMedecin_id(int medecin_id) {
        this.medecin_id = medecin_id;
    }

    
    @Override
    public String toString() {
        return "Dossier{" + "id=" + id + ", numero=" + numero + ", code_apci=" + code_apci + ", description=" + description + '}';
    } 
    
}
