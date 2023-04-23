/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Article;
import entities.Medecin;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.MyDB;


public class ArticleService {

  
    
    Connection cnx;

    public void ajouter(Article d) {
        try {
            String requete1 = "INSERT INTO article(nom,description,date) VALUES(?,?,?)";

            PreparedStatement pst = MyDB.getInstance().getCnx().prepareStatement(requete1);
            pst.setString(1, d.getNom());
            pst.setString(2, d.getDescription());
           
            pst.executeUpdate();
            System.out.println("Article ajouté !");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public ObservableList<Article> listArticle() {
        ObservableList<Article> myList = FXCollections.observableArrayList();
        try {

            String requete2 = "Select * FROM article";
            Statement st = MyDB.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete2);
            while (rs.next()) {
                Article rec = new Article();
                rec.setId(rs.getInt(1));
                rec.setNom(rs.getString("nom"));
                rec.setDescription(rs.getString("Description"));
                myList.add(rec);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return myList;
    }
    public ArticleService() {
        cnx = MyDB.getInstance().getCnx();
    }

    public void modifier_article(Article article ,String nom, String description) {
        try {
            String requete4 = " UPDATE article SET " + "  nom= ?, description = ? WHERE id= " + article.getId();
            PreparedStatement pst = MyDB.getInstance().getCnx().prepareStatement(requete4);
            pst.setString(1, nom);
            pst.setString(2, description);
            pst.executeUpdate();
            System.out.println("article modifié !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

   

    }    

public void supprimer(Article s) {

        try {
            String requete3 = "DELETE FROM article WHERE id=" + s.getId();
            Statement st = MyDB.getInstance().getCnx().createStatement();

            st.executeUpdate(requete3);
            System.out.println("Article supprimer !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }
  public static List<Article> recuperer() throws SQLException {
     
    List<Article> articles = new ArrayList<>();
   
        String requete = "SELECT id, medecin_id,nom, description,date FROM article";
        Statement st = MyDB.getInstance().getCnx().createStatement();
        ResultSet rs = st.executeQuery(requete);
        while (rs.next()) {
            
           
              int id = rs.getInt("id");
            int medecinId = rs.getInt("medecin_id");
              String nom = rs.getString("nom");
            String description = rs.getString("description");
            Date date = rs.getDate("date");
           
            Medecin medecin = new MedecinService().recupererById(medecinId);
            
            Article article = new Article(id, nom,description, date, medecin);
            articles.add(article);
        }
           return articles;       
    }
   public Article recupererById(int id) {
    try {
        String requete = "SELECT id, medecin_id, nom, description, date FROM article WHERE id=?";
        PreparedStatement pst = MyDB.getInstance().getCnx().prepareStatement(requete);
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            int medecinId = rs.getInt("medecin_id");
            String nom = rs.getString("nom");
            String description = rs.getString("description");
            Date date = rs.getDate("date");
            Medecin medecin = new MedecinService().recupererById(medecinId);
            Article article = new Article(id, nom, description, date, medecin);
            return article;
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return null;
}

}

    
  




