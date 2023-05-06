/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.ReplaySujet;
import entities.Sujet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.MyDB;

/**
 *
 * @author jlidi
 */
public class ReplaySujetService {
    
        Connection cnx;

    public ObservableList<ReplaySujet> listerReponse() {
        ObservableList<ReplaySujet> myList = FXCollections.observableArrayList();
        try {

            String requete2 = "Select * FROM replay_sujet";
            Statement st = MyDB.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete2);
            while (rs.next()) {
                ReplaySujet rec = new ReplaySujet();
                rec.setId(rs.getInt(1));
                rec.setMessage(rs.getString("message"));
                rec.setDate(rs.getDate("date"));


                myList.add(rec);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return myList;
    }
     public ReplaySujetService() {
        cnx = MyDB.getInstance().getCnx();
    }

 public void modifier_reponse(ReplaySujet reponse ,String message) {
        try {
            String requete4 = " UPDATE replay_sujet SET " + "  message= ? WHERE id= " + reponse.getId();
            PreparedStatement pst = MyDB.getInstance().getCnx().prepareStatement(requete4);
            pst.setString(1,reponse.getMessage());
            pst.executeUpdate();
            System.out.println("reponse modifié !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

   

    }   
 public void ajouter(ReplaySujet d) {
        try {
            String requete1 = "INSERT INTO replay_sujet(message,date) VALUES(?,NOW())";

            PreparedStatement pst = MyDB.getInstance().getCnx().prepareStatement(requete1);
            pst.setString(1, d.getMessage());
            pst.executeUpdate();
            System.out.println("reponse ajouté !");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
     
public void supprimer(ReplaySujet d) {

        try {
            String requete3 = "DELETE FROM replay_sujet WHERE id=" + d.getId();
            Statement st = MyDB.getInstance().getCnx().createStatement();

            st.executeUpdate(requete3);
            System.out.println("reponse supprimer !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }
public ObservableList<ReplaySujet> listreponse(Sujet sujet) {
        ObservableList<ReplaySujet> myList = FXCollections.observableArrayList();

        try {
            String requete = "SELECT * FROM replay_sujet WHERE sujet_id = ?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, sujet.getId());
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                //Commentaire commentaire = new Commentaire();
                int id = rs.getInt("id");
                String message = rs.getString("message");
                 

                ReplaySujet reponse = new ReplaySujet(id, message, sujet );
                myList.add(reponse);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return myList;

    }
public Sujet recupererById(int id) {
        try {
            String requete = "SELECT id, title FROM sujet WHERE id=?";
            PreparedStatement pst = MyDB.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                String title = rs.getString("title");
                

                //rs.setImages(images);

                Sujet sujet = new Sujet(id, title);
                return sujet;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
}
