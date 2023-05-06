/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author soumayaab
 */
import java.util.Date;

public class Commentaire {

    public Commentaire(String message, Date date, Article article, int user_id) {
   this.message = message;
        this.date = date;
        this.article = article;
        this.user_id = user_id;
    }

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
    private int user_id;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
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
