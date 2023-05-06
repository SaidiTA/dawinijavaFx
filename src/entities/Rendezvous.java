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
public class Rendezvous {
    private int id;
    private String titre, description,etat;
  //  private Date date;
    //zyda
   // public Date getDate;
   //LocalDate date;

   /* public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }*/
    private Date date;

    public Rendezvous(String titre, String description, LocalDate date, String etat) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    

    
    
    public Rendezvous() {}

    public Rendezvous(int id, String titre, String description, Date date, String etat) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.date = date;
        this.etat = etat;
        //this.date = date;
    }
    public Rendezvous( String titre, String description, Date date, String etat) {
       
        this.titre = titre;
        this.description = description;
        this.date = date;
        this.etat = etat;
        //this.date = date;
    }

    public Rendezvous( String titre, String description,  String etat) {
       
        this.titre = titre;
        this.description = description;
        //this.date = date;
        this.etat = etat;
        //this.date = date;
    }
    
    //public Rendezvous(String tayssir, String tayssirjava, String valide) {
    //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    //}
    
    

    public int getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }

    public String getEtat() {
        return etat;
    }

   // public Date getDate() {
   //     return date;
   // }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

  //  public void setDate(Date date) {
  //      this.date = date;
  //  }

    @Override
    public String toString() {
        return "Rendezvous{" + "id=" + id + ", titre=" + titre + ", description=" + description + ", etat=" + etat + ", date=" + date + '}';
    }
    /*@Override
    public String toString() {
        return "Rendezvous{" + "id=" + id + ", titre=" + titre + ", description=" + description + ", etat=" + etat +  '}';
    }*/
    
    
    
}
