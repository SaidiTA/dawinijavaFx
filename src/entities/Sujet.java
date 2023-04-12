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
    private int id ;
    private Date date;
    private String title,message,description;
    private Specialites specialite;
    private List<ReplaySujet> replaySujets;

    public Specialites getSpecialite() {
        return specialite;
    }

    public void setSpecialite(Specialites specialite) {
        this.specialite = specialite;
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
        this.specialite = specialite;
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

    @Override
    public String toString() {
        return "Sujet{" + "id=" + id + ", date=" + date + ", title=" + title + ", message=" + message + ", description=" + description + '}';
    }
    
}
