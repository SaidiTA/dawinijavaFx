/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.util.ArrayList;

/**
 *
 * @author soumayaab
 */
public class Medecin extends  User{

    
    private String titre;
    private String adresse_cabinet;
    private String fixe;
    private String diplome_formation;
    private float tarif;
    private boolean cnam;

    public Medecin(int id, String email, ArrayList<String> roles, String password, String nom, String prenom, int cin, String sexe, String telephone, String gouvernorat, String adresse, String confirm_password, String image, String titre, String adresse_cabinet, String fixe, String diplome_formation, float tarif, boolean cnam) {
        super(id, email, roles, password, nom, prenom, cin, sexe, telephone, gouvernorat, adresse, confirm_password, image);
        this.titre = titre;
        this.adresse_cabinet = adresse_cabinet;
        this.fixe = fixe;
        this.diplome_formation = diplome_formation;
        this.tarif = tarif;
        this.cnam = cnam;
    }

    public Medecin(String email, ArrayList<String> roles, String password, String nom, String prenom, int cin, String sexe, String telephone, String gouvernorat, String adresse, String confirm_password, String image, String titre, String adresse_cabinet, String fixe, String diplome_formation, float tarif, boolean cnam) {
        super(email, roles, password, nom, prenom, cin, sexe, telephone, gouvernorat, adresse, confirm_password, image);
        this.titre = titre;
        this.adresse_cabinet = adresse_cabinet;
        this.fixe = fixe;
        this.diplome_formation = diplome_formation;
        this.tarif = tarif;
        this.cnam = cnam;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAdresse_cabinet() {
        return adresse_cabinet;
    }

    public void setAdresse_cabinet(String adresse_cabinet) {
        this.adresse_cabinet = adresse_cabinet;
    }

    public String getFixe() {
        return fixe;
    }

    public void setFixe(String fixe) {
        this.fixe = fixe;
    }

    public String getDiplome_formation() {
        return diplome_formation;
    }

    public void setDiplome_formation(String diplome_formation) {
        this.diplome_formation = diplome_formation;
    }

    public float getTarif() {
        return tarif;
    }

    public void setTarif(float tarif) {
        this.tarif = tarif;
    }

    public boolean isCnam() {
        return cnam;
    }

    public void setCnam(boolean cnam) {
        this.cnam = cnam;
    }

    @Override
    public String toString() {
        return super.toString() + " Medecin{" + "titre=" + titre + ", adresse_cabinet=" + adresse_cabinet + ", fixe=" + fixe + ", diplome_formation=" + diplome_formation + ", tarif=" + tarif + ", cnam=" + cnam + '}';
    }

    public Medecin() {
    }
    
}
