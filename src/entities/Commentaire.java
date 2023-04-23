/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Islem
 */
import java.util.Date;

public class Commentaire {

    @Override
    public String toString() {
        return "Commentaire{" + "id=" + id + ", message=" + message + ", date=" + date + ", article=" + article + ", user=" + user + '}';
    }

    public Commentaire() {
    }
    private int id;
    private String message;
    private Date date;
    private Article article;
    private User user;

    public Commentaire(int id, String message, Date date, Article article, User user) {
        this.id = id;
        this.message = message;
        this.date = date;
        this.article = article;
        this.user = user;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Commentaire(String message, Date date, Article article, User user) {
        this.message = message;
        this.date = date;
        this.article = article;
        this.user = user;

    }

    // Getters and setters
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
