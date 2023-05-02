/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author HP
 */
public class paiement {
    
    private int id,tarif,num_carte;
    private String etat, nom_carte ,email;
    private Date expiration_carte;
    private int rendez_vous_id;

    public void setRendez_vous_id(int rendez_vous_id) {
        this.rendez_vous_id = rendez_vous_id;
    }

    public int getRendez_vous_id() {
        return rendez_vous_id;
    }

    public void setNum_carte(int num_carte) {
        this.num_carte = num_carte;
    }

    public int getNum_carte() {
        return num_carte;
    }

    public paiement(int id, int tarif, String etat, String nom_carte, String email, Date expiration_carte) {
        this.id = id;
        this.tarif = tarif;
        this.etat = etat;
        this.nom_carte = nom_carte;
        this.email = email;
        this.expiration_carte = expiration_carte;
    }
    public paiement(int id, String etat,int tarif,int num_carte,Date expiration_carte,  String nom_carte, String email) {
        this.id = id;
        this.etat = etat;
        this.tarif = tarif;
        this.num_carte = num_carte;
         this.expiration_carte = expiration_carte;
        this.nom_carte = nom_carte;
        this.email = email;
        //this.expiration_carte = expiration_carte;
    }
    
    public paiement(int id,int rendez_vous_id, String etat,int tarif,int num_carte,Date expiration_carte,  String nom_carte, String email) {
        this.id = id;
         this.rendez_vous_id = rendez_vous_id;
        this.etat = etat;
        this.tarif = tarif;
        this.num_carte = num_carte;
         this.expiration_carte = expiration_carte;
        this.nom_carte = nom_carte;
        this.email = email;
        //this.expiration_carte = expiration_carte;
    }

    /*@Override
    public String toString() {
        return "paiement{" + "id=" + id + ", tarif=" + tarif + ", num_carte=" + num_carte + ", etat=" + etat + ", nom_carte=" + nom_carte + ", email=" + email + ", expiration_carte=" + expiration_carte + '}';
    }*/
     public paiement() {
     }

    public void setId(int id) {
        this.id = id;
    }

    public void setTarif(int tarif) {
        this.tarif = tarif;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public void setNom_carte(String nom_carte) {
        this.nom_carte = nom_carte;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setExpiration_carte(Date expiration_carte) {
        this.expiration_carte = expiration_carte;
    }

    public int getId() {
        return id;
    }

    public int getTarif() {
        return tarif;
    }

    public String getEtat() {
        return etat;
    }

    public String getNom_carte() {
        return nom_carte;
    }

    public String getEmail() {
        return email;
    }

    public Date getExpiration_carte() {
        return expiration_carte;
    }

    @Override
    public String toString() {
        return "paiement{" + "id=" + id + ", tarif=" + tarif + ", num_carte=" + num_carte + ", etat=" + etat + ", nom_carte=" + nom_carte + ", email=" + email + ", expiration_carte=" + expiration_carte + ", rendez_vous_id=" + rendez_vous_id + '}';
    }
    
    
    
}
