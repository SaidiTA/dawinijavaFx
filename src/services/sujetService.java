/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Sujet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.MyDB;

/**
 *
 * @author jlidi
 */
public class sujetService {
    Connection cnx;
 public sujetService(){

        cnx = MyDB.getInstance().getCnx();
    
    }

    public void ajouter(Sujet d) {
        try {
            String requete1 = "INSERT INTO Sujet(date,message,title,description,specialites_id) VALUES(NOW(),?,?,?,?)";

            PreparedStatement pst = MyDB.getInstance().getCnx().prepareStatement(requete1);
            pst.setString(1, d.getTitle());
                        pst.setString(2, d.getMessage());

            pst.setString(3, d.getDescription());
                        pst.setInt(4, d.getSpecialites_id());

            pst.executeUpdate();
            System.out.println("Sujet ajouté !");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
      public ObservableList<Sujet> listerSujet() {
        ObservableList<Sujet> myList = FXCollections.observableArrayList();
        try {

            String requete2 = "Select * FROM sujet";
            Statement st = MyDB.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete2);
            while (rs.next()) {
                Sujet rec = new Sujet();
                rec.setId(rs.getInt(1));
                rec.setTitle(rs.getString("title"));
                rec.setDate(rs.getDate("date"));


                myList.add(rec);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return myList;
    }

      

    public void modifier_sujet(Sujet sujet ,String message,String title, String description) {
        try {
            String requete4 = " UPDATE Sujet SET " + "  message= ?, title = ?, description = ? WHERE id= " + sujet.getId();
            PreparedStatement pst = MyDB.getInstance().getCnx().prepareStatement(requete4);
            pst.setString(1,sujet.getMessage());

            pst.setString(2, sujet.getTitle());

            pst.setString(3, sujet.getDescription());
            pst.executeUpdate();
            System.out.println("sujet modifié !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

   

    }    

public void supprimer(Sujet s) {

        try {
            String requete3 = "DELETE FROM Sujet WHERE id=" + s.getId();
            Statement st = MyDB.getInstance().getCnx().createStatement();

            st.executeUpdate(requete3);
            System.out.println("Sujet supprimer !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }
  public List<Sujet> RechercherSujet(String btntitle) {
         List<Sujet> list = new ArrayList<>();
        try{
            String req = "SELECT * FROM sujet where title LIKE '"+btntitle+"%'";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                Sujet r = new Sujet();
              r.setId(rs.getInt("id"));
                r.setTitle(rs.getString("title"));
                r.setMessage(rs.getString("message"));
               r.setDescription(rs.getString("description"));
             


                
                list.add(r);
            }
    }
        catch(SQLException c){
            
        }
        return list ; 
    }
}