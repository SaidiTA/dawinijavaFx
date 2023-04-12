/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.util.Date;

/**
 *
 * @author soumayaab
 */
public class Avis {
    private int id;
    private Medecin medecin;
    private Patient patient;
    private String text;
    private double note;
    private Date date;
    
   
    
    // Getters and setters for all fields

    public Avis() {
    }

    public Avis(int id, Medecin medecin, Patient patient, String text, double note, Date date) {
        this.id = id;
        this.medecin = medecin;
        this.patient = patient;
        this.text = text;
        this.note = note;
        this.date = date;
    }
    

    public Avis(Medecin medecin, Patient patient, String text, double note, Date date) {
        this.medecin = medecin;
        this.patient = patient;
        this.text = text;
        this.note = note;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Medecin getMedecin() {
        return medecin;
    }

    public void setMedecin(Medecin medecin) {
        this.medecin = medecin;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Avis{" + "id=" + id + ", medecin=" + medecin + ", patient=" + patient + ", text=" + text + ", note=" + note + ", date=" + date + '}';
    }
    
   
    

}
    

