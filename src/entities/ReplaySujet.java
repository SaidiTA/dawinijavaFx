/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author jlidi
 */
public class ReplaySujet {
    private int id ,sujet_id;
    private String message;
    private Date date;

    public ReplaySujet() {
    }

    public ReplaySujet(int id, String message, Date date) {
        this.id = id;
        this.message = message;
        this.date = date;
    }

    public ReplaySujet(String message, Date date) {
        this.message = message;
        this.date = date;
    }

    public ReplaySujet(String message) {
        this.message = message;
    }

    public ReplaySujet(int id, int sujet_id, String message) {
        this.id = id;
        this.sujet_id = sujet_id;
        this.message = message;
    }

    public ReplaySujet(int id, String message, Sujet sujet) {
         this.id = id;
        this.sujet_id = sujet_id;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "ReplaySujet{" + "id=" + id + ", message=" + message + ", date=" + date + '}';
    }
    
}
