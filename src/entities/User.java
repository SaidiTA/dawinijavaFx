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
public class User {

    public User(int id) {
        this.id = id;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(int id, String email, String prenom, String nom, int cin, String sexe, String telephone, String gouvernorat, String adresse, String image) {
        this.id = id;
        this.email = email;
        this.prenom = prenom;
        this.nom = nom;
        this.cin = cin;
        this.sexe = sexe;
        this.telephone = telephone;
        this.gouvernorat = gouvernorat;
        this.adresse = adresse;
        this.image = image;
    }

    public User(String email, String prenom, String nom, String password, int cin, String sexe, String telephone, String gouvernorat, String adresse, String confirm_password, String image) {
        this.email = email;
        this.prenom = prenom;
        this.nom = nom;
        this.password = password;
        this.cin = cin;
        this.sexe = sexe;
        this.telephone = telephone;
        this.gouvernorat = gouvernorat;
        this.adresse = adresse;
        this.confirm_password = confirm_password;
        this.image = image;
    }
    
    public User(String email, String prenom, String nom, int cin, String sexe, String telephone, String gouvernorat, String adresse, String image) {
        this.email = email;
        this.prenom = prenom;
        this.nom = nom;
        
        this.cin = cin;
        this.sexe = sexe;
        this.telephone = telephone;
        this.gouvernorat = gouvernorat;
        this.adresse = adresse;
        
        this.image = image;
    }
    
    private int id;
    private String email;
    private String prenom;
    private String nom;
    
    private ArrayList<String> roles;
    private String password;
    int cin;
    String sexe;
    private String telephone;
    private String gouvernorat;
    private String adresse;
    private String confirm_password;
    private String image;

    public User() {
    }

    public User(int id, String email, ArrayList<String> roles, String password, String nom, String prenom, int cin, String sexe, String telephone, String gouvernorat, String adresse, String confirm_password, String image) {
        this.id = id;
        this.email = email;
        this.roles = roles;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.sexe = sexe;
        this.telephone = telephone;
        this.gouvernorat = gouvernorat;
        this.adresse = adresse;
        this.confirm_password = confirm_password;
        this.image = image;
    }
    
    public User(int id, String email, String password, String nom, String prenom, int cin, String sexe, String telephone, String gouvernorat, String adresse, String confirm_password, String image) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.sexe = sexe;
        this.telephone = telephone;
        this.gouvernorat = gouvernorat;
        this.adresse = adresse;
        this.confirm_password = confirm_password;
        this.image = image;
    }

    public User(String email, ArrayList<String> roles, String password, String nom, String prenom, int cin, String sexe, String telephone, String gouvernorat, String adresse, String confirm_password, String image) {
        this.email = email;
        this.roles = roles;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.sexe = sexe;
        this.telephone = telephone;
        this.gouvernorat = gouvernorat;
        this.adresse = adresse;
        this.confirm_password = confirm_password;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<String> getRoles() {
        return roles;
    }

    public void setRoles(ArrayList<String> roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getGouvernorat() {
        return gouvernorat;
    }

    public void setGouvernorat(String gouvernorat) {
        this.gouvernorat = gouvernorat;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getConfirm_password() {
        return confirm_password;
    }

    public void setConfirm_password(String confirm_password) {
        this.confirm_password = confirm_password;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", email=" + email + ", roles=" + roles + ", password=" + password + ", nom=" + nom + ", prenom=" + prenom + ", cin=" + cin + ", sexe=" + sexe + ", telephone=" + telephone + ", gouvernorat=" + gouvernorat + ", adresse=" + adresse + ", confirm_password=" + confirm_password + '}';
    }
    
    
    
    
}
