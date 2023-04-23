/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
 * @author Islem
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
                int user_id = rs.getInt("utilisateur_id");
                System.out.println("comment " + user_id);

                Date date = rs.getDate("date");
                String msg = rs.getString("message");
                //commentaire.setArticle(article);
                User user = new UserService().recupererById(user_id);
                System.out.println("comment " + user);

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
                commentaire.setMessage(rs.getString("message"));
                commentaire.setDate(rs.getDate("date"));

                int articleId = rs.getInt("article_id");
                Article article = new ArticleService().recupererById(articleId);
                commentaire.setArticle(article);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return commentaire;
    }

}
