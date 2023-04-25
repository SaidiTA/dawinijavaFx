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
public class Sujet  {
    private int id ,specialites_id;
    private Date date;
    
    private String title,message,description;
    private List<ReplaySujet> replaySujets;

    public Sujet(int id, int specialites_id, String title, String message, String description) {
        this.id = id;
        this.specialites_id = specialites_id;
        this.title = title;
        this.message = message;
        this.description = description;
    }

    public Sujet(String message, String title, String description, int specialites_id) {
        this.specialites_id = specialites_id;
        this.title = title;
        this.message = message;
        this.description = description;
    }

   
   

    public List<ReplaySujet> getReplaySujets() {
        return replaySujets;
    }

    public void setReplaySujets(List<ReplaySujet> replaySujets) {
        this.replaySujets = replaySujets;
    }

    public Sujet() {
    }

    public Sujet(int id, Date date, String title, String message, String description, Specialites specialite, List<ReplaySujet> replaySujets) {
        this.id = id;
        this.date = date;
        this.title = title;
        this.message = message;
        this.description = description;
        this.replaySujets = replaySujets;
    }

    public Sujet(String title, String message, String description) {
        this.title = title;
        this.message = message;
        this.description = description;
    }

    public Sujet(Date date, String title, String message, String description) {
        this.date = date;
        this.title = title;
        this.message = message;
        this.description = description;
    }

    public Sujet(int id, Date date, String title, String message, String description) {
        this.id = id;
        this.date = date;
        this.title = title;
        this.message = message;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSpecialites_id() {
        return specialites_id;
    }

    public void setSpecialites_id(int specialites_id) {
        this.specialites_id = specialites_id;
    }

    @Override
    public String toString() {
        return "Sujet{" + "id=" + id + ", date=" + date + ", title=" + title + ", message=" + message + ", description=" + description + '}';
    }
    
}
