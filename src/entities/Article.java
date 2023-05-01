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
public class Article {

    private int id;
    private String nom;
    private String description;
    private Date date;

    public Article(int id, String nom, String description, Date date, Medecin medecin, Images images, int likeCount, int dislikeCount) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.date = date;
        this.medecin = medecin;
        this.images = images;
        this.likeCount = likeCount;
        this.dislikeCount = dislikeCount;
    }

    public Article(String nom, String description, Date date, Medecin medecin, Images images, int likeCount, int dislikeCount) {
        this.nom = nom;
        this.description = description;
        this.date = date;
        this.medecin = medecin;
        this.images = images;
        this.likeCount = likeCount;
        this.dislikeCount = dislikeCount;
    }
    private Medecin medecin;
    private Images images;
    private int likeCount;
    private int dislikeCount;

    public Article(String nom, String description) {
        this.nom = nom;
        this.description = description;
    }

    public Article(String nom, String description, Images image) {
        this.nom = nom;
        this.description = description;
        this.images = image;
    }

    public Medecin getMedecin() {
        return medecin;
    }

    public void setMedecin(Medecin medecin) {
        this.medecin = medecin;
    }

    public Article() {

    }

    @Override
    public String toString() {
        return "Article{" + "id=" + id + ", nom=" + nom + ", description=" + description + ", date=" + date + '}';
    }

    // Constructor with all parameters
    public Article(int id, String nom, String description, Date date, Medecin medecin) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.date = date;
        this.medecin = medecin;
    }

    public Article(String nom, String description, Date date, Medecin medecin) {
        this.nom = nom;
        this.description = description;
        this.date = date;
        this.medecin = medecin;
    }

    public Article(int id, String nom, String description, Date date, Medecin medecin, Images images) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.date = date;
        this.medecin = medecin;
        this.images = images;
    }

    public Article(String nom, String description, Date date, Medecin medecin, Images images) {
        this.nom = nom;
        this.description = description;
        this.date = date;
        this.medecin = medecin;
        this.images = images;
    }

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }

    // Getters and setters
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getDislikeCount() {
        return dislikeCount;
    }

    public void setDislikeCount(int dislikeCount) {
        this.dislikeCount = dislikeCount;
    }

    public void incrementLikeCount() {
        likeCount++;
    }

    public void incrementDislikeCount() {
        dislikeCount++;
    }

    public void decrementLikeCount() {
        likeCount--;
    }

    public void decrementDislikeCount() {
        dislikeCount--;

    }
}
