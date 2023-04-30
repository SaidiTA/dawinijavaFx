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
public class article_like {
    private int id ;
    private int value;
     private Article article;
    private User user;
   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public article_like() {
    }

    public article_like(int id, int value, Article article, User user) {
        this.id = id;
        this.value = value;
        this.article = article;
        this.user = user;
       
    }

    public article_like(int value, Article article, User user) {
        this.value = value;
        this.article = article;
        this.user = user;
        
    }

  
 
  
}
