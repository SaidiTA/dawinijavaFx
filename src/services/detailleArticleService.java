/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import entities.Article;
import entities.Commentaire;
import entities.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.MyDB;

/**
 *
 * @author soumayaab
 */
public class detailleArticleService {

    Connection cnx;

    public detailleArticleService() {
        cnx = MyDB.getInstance().getCnx();
    }
//bsh tbadel yrecuperi l patient connecte 

    public void ajouter(Commentaire commentaire) {
        try {
            String requete = "INSERT INTO commentaire(message, date, article_id,utilisateur_id) VALUES(?, ?, ?,?)";

            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, commentaire.getMessage());
            pst.setDate(2, new java.sql.Date(commentaire.getDate().getTime()));
            pst.setInt(3, commentaire.getArticle().getId());
            pst.setInt(4, commentaire.getUser().getId());
            pst.executeUpdate();
            System.out.println("Commentaire ajouté !");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ObservableList<Commentaire> listCommentaire(Article article) {
        ObservableList<Commentaire> myList = FXCollections.observableArrayList();

        try {
            String requete = "SELECT * FROM commentaire WHERE article_id = ?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, article.getId());
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                //Commentaire commentaire = new Commentaire();
                int id = rs.getInt("id");
                Date date = rs.getDate("date");
                String msg = rs.getString("message");
                int user_id = rs.getInt("utilisateur_id");
                User user = new UserService().recupererById(user_id);
                Commentaire comment = new Commentaire(id, msg, date, article, user);
                myList.add(comment);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return myList;

    }

    public void modifier(Commentaire commentaire, String message) {
        try {
            String requete = "UPDATE commentaire SET message = ? WHERE id = ?";

            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, message);
            pst.setInt(2, commentaire.getId());

            pst.executeUpdate();
            System.out.println("Commentaire modifié !");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void supprimer(Commentaire commentaire) {
        try {
            String requete = "DELETE FROM commentaire WHERE id = ?";

            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, commentaire.getId());

            pst.executeUpdate();
            System.out.println("Commentaire supprimé !");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Commentaire recupererById(int id) {
        Commentaire commentaire = null;
        try {
            String requete = "SELECT * FROM commentaire WHERE id=?";
            PreparedStatement pst = MyDB.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                commentaire = new Commentaire();
                
                commentaire.setId(rs.getInt("id"));
                  int articleId = rs.getInt("article_id");
                Article article = new ArticleService().recupererById(articleId);
                commentaire.setArticle(article);
                commentaire.setMessage(rs.getString("message"));
                commentaire.setDate(rs.getDate("date"));

              
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return commentaire;
    }

    public void ajouterLike(User user, Article article) {
        try {
            String requete = "INSERT INTO article_like(user_id, article_id, value) VALUES (?, ?, ?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, user.getId());
            pst.setInt(2, article.getId());
            pst.setInt(3, 1); // 1 pour un like
            pst.executeUpdate();
            System.out.println("Like ajouté !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void ajouterDislike(User user, Article article) {
        try {
            String requete = "INSERT INTO article_like(user_id, article_id, value) VALUES (?, ?, ?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, user.getId());
            pst.setInt(2, article.getId());
            pst.setInt(3, -1); // -1 pour un dislike
            pst.executeUpdate();
            System.out.println("Dislike ajouté !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public int estDejaLikeOuDislike(User user, Article article) {
        int value = 0;
        try {
            String requete = "SELECT value FROM article_like WHERE user_id=? AND article_id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, user.getId());
            pst.setInt(2, article.getId());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                value = rs.getInt("value");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return value;
    }

}
