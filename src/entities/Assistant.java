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
public class Assistant extends User  {
    private Medecin medecin;

    public Medecin getMedecin() {
        return medecin;
    }

   public void setMedecin(Medecin medecin) {
        this.medecin = medecin;
   }

    public Assistant() {
    }
    
      @Override
    public String toString() {
        return super.toString(); 
    }

    @Override
    public void setImage(String image) {
        super.setImage(image); 
    }

    @Override
    public String getImage() {
        return super.getImage(); 
    }

    @Override
    public void setConfirm_password(String confirm_password) {
        super.setConfirm_password(confirm_password); 
    }

    @Override
    public String getConfirm_password() {
        return super.getConfirm_password(); 
    }

    @Override
    public void setAdresse(String adresse) {
        super.setAdresse(adresse); 
    }

    @Override
    public String getAdresse() {
        return super.getAdresse(); 
    }

    @Override
    public void setGouvernorat(String gouvernorat) {
        super.setGouvernorat(gouvernorat); 
    }

    @Override
    public String getGouvernorat() {
        return super.getGouvernorat(); 
    }

    @Override
    public void setTelephone(String telephone) {
        super.setTelephone(telephone); 
    }

    @Override
    public String getTelephone() {
        return super.getTelephone(); 
    }

    @Override
    public void setSexe(String sexe) {
        super.setSexe(sexe); 
    }

    @Override
    public String getSexe() {
        return super.getSexe(); 
    }
    @Override
    public void setCin(int cin) {
        super.setCin(cin); 
    }
    @Override
    public int getCin() {
        return super.getCin(); 
    }

    @Override
    public void setPrenom(String prenom) {
        super.setPrenom(prenom); 
    }

    @Override
    public String getPrenom() {
        return super.getPrenom(); 
}
    @Override
    public void setNom(String nom) {
        super.setNom(nom);
    }

    @Override
    public String getNom() {
        return super.getNom(); 
    }

    @Override
    public void setPassword(String password) {
        super.setPassword(password); 
    }

    @Override
    public String getPassword() {
        return super.getPassword(); 
    }

    @Override
    public void setRoles(ArrayList<String> roles) {
        super.setRoles(roles); 
    }

    @Override
    public ArrayList<String> getRoles() {
        return super.getRoles(); 
    }

    @Override
    public void setEmail(String email) {
        super.setEmail(email); 
    }

    @Override
    public String getEmail() {
        return super.getEmail(); 
    }

    @Override
    public void setId(int id) {
        super.setId(id); 
    }

    @Override
    public int getId() {
        return super.getId(); 

    }
    public Assistant(int id, String email, ArrayList<String> roles, String password, String nom, String prenom, int cin, String sexe, String telephone, String gouvernorat, String adresse, String confirm_password, String image,Medecin medecin) {
        super(id, email, roles, password, nom, prenom, cin, sexe, telephone, gouvernorat, adresse, confirm_password, image);
       this.medecin=medecin;
    }

    public Assistant(String email, ArrayList<String> roles, String password, String nom, String prenom, int cin, String sexe, String telephone, String gouvernorat, String adresse, String confirm_password, String image,Medecin medecin) {
        super(email, roles, password, nom, prenom, cin, sexe, telephone, gouvernorat, adresse, confirm_password, image);
         this.medecin=medecin;
         
    }
}
