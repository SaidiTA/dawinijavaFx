/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;
import java.util.List;

/**
 *
 * @author jlidi
 */
public class Specialites {
   private  int id;
   private String nom ,description,image;
   private List<Sujet> sujets;

    public Specialites(int id, String nom, String description, String image) {
         this.id = id;

        this.nom = nom;
        this.description = description;
        this.image = image;    }

  

    public List<Sujet> getSujets() {
        return sujets;
    }

    public void setSujets(List<Sujet> sujets) {
        this.sujets = sujets;
    }

    public Specialites(String nom, String description, String image) {
        this.nom = nom;
        this.description = description;
        this.image = image;
    }

   

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Specialites(int id, String nom, String description, List<Sujet> sujets) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.sujets = sujets;
    }


    public Specialites() {
    }
    public Specialites(String nom, String description) {
        this.nom = nom;
        this.description = description;
    }

   

    public Specialites(int id, String nom, String description) {
        this.id = id;
        this.nom = nom;
        this.description = description;
    }
   
   
       public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Specialites{" + "id=" + id + ", nom=" + nom + ", description=" + description + '}';
    }
    
}
