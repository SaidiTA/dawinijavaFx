/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 *
 * @author USER
 */
public class Consulation {
    private int id;
    private int patients_id;
    private int medecin_id;
    private Date date;
    private Timestamp heuredebut;        
    private Timestamp heurefin;        
    private String url_consultation;
    private String est_termine;

    public Consulation() {
    }

    public Consulation(Date date, Timestamp heuredebut, Timestamp heurefin, String url_consultation, String est_termine) {
        this.date = date;
        this.heuredebut = heuredebut;
        this.heurefin = heurefin;
        this.url_consultation = url_consultation;
        this.est_termine = est_termine;
    }

    public Consulation(Date date, Timestamp heuredebut, Timestamp heurefin) {
        this.date = date;
        this.heuredebut = heuredebut;
        this.heurefin = heurefin;
    }
    

    public Consulation(int patients_id, int medecin_id, Date date, Timestamp heuredebut, Timestamp heurefin, String url_consultation, String est_termine) {
        this.patients_id = patients_id;
        this.medecin_id = medecin_id;
        this.date = date;
        this.heuredebut = heuredebut;
        this.heurefin = heurefin;
        this.url_consultation = url_consultation;
        this.est_termine = est_termine;
    }

    public Consulation(int id, int patients_id, int medecin_id, Date date, Timestamp heuredebut, Timestamp heurefin, String url_consultation, String est_termine) {
        this.id = id;
        this.patients_id = patients_id;
        this.medecin_id = medecin_id;
        this.date = date;
        this.heuredebut = heuredebut;
        this.heurefin = heurefin;
        this.url_consultation = url_consultation;
        this.est_termine = est_termine;
    }

    public Consulation(int id, Date date, Timestamp heuredebut, Timestamp heurefin, String url_consultation, String est_termine) {
        this.id = id;
        this.date = date;
        this.heuredebut = heuredebut;
        this.heurefin = heurefin;
        this.url_consultation = url_consultation;
        this.est_termine = est_termine;
    }

    
    public int getId() {
        return id;
    }

    public int getPatients_id() {
        return patients_id;
    }

    public int getMedecin_id() {
        return medecin_id;
    }

    public Date getDate() {
        return date;
    }

    public Timestamp getHeuredebut() {
        return heuredebut;
    }

    public Timestamp getHeurefin() {
        return heurefin;
    }

    public String getUrl_consultation() {
        return url_consultation;
    }

    public String getEst_termine() {
        return est_termine;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPatients_id(int patients_id) {
        this.patients_id = patients_id;
    }

    public void setMedecin_id(int medecin_id) {
        this.medecin_id = medecin_id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setHeuredebut(Timestamp heuredebut) {
        this.heuredebut = heuredebut;
    }

    public void setHeurefin(Timestamp heurefin) {
        this.heurefin = heurefin;
    }

    public void setUrl_consultation(String url_consultation) {
        this.url_consultation = url_consultation;
    }

    public void setEst_termine(String est_termine) {
        this.est_termine = est_termine;
    }

    @Override
    public String toString() {
        return "Consultation{" + "id=" + id + ", patients_id=" + patients_id + ", medecin_id=" + medecin_id + ", date=" + date + ", heuredebut=" + heuredebut + ", heurefin=" + heurefin + ", url_consultation=" + url_consultation + ", est_termine=" + est_termine + '}';
    }
    
    
}
