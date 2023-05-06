/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Specialites;
import entities.Sujet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.MyDB;

/**
 *
 * @author jlidi
 */
public class specialitesService {

   
    Connection cnx;

    public void ajouter(Specialites d) {
        try {
            String requete1 = "INSERT INTO specialites(nom,description,image) VALUES(?,?,?)";

            PreparedStatement pst = MyDB.getInstance().getCnx().prepareStatement(requete1);
            pst.setString(1, d.getNom());
            pst.setString(2, d.getDescription());
            pst.setString(3, d.getImage());

            pst.executeUpdate();
            System.out.println("Specialite ajouté !");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }  

    public ObservableList<Specialites> listerSpecialites() {
        ObservableList<Specialites> myList = FXCollections.observableArrayList();
        try {

            String requete2 = "Select * FROM specialites";
            Statement st = MyDB.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete2);
            while (rs.next()) {
                Specialites rec = new Specialites();
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
    
    public ObservableList<Specialites> listerSpecialite() {
        ObservableList<Specialites> myList = FXCollections.observableArrayList();
        try {

            String requete2 = "Select * FROM specialites";
            Statement st = MyDB.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete2);
            while (rs.next()) {
                Specialites rec = new Specialites();
                rec.setId(rs.getInt("id"));

                rec.setNom(rs.getString("nom"));
                rec.setImage(rs.getString("image"));

                myList.add(rec);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return myList;
    }
    
    public specialitesService() {
        cnx = MyDB.getInstance().getCnx();
    }

    public void modifier_spec(Specialites specialite ,String nom, String description) {
        try {
            String requete4 = " UPDATE Specialites SET " + "  nom= ?, description = ? WHERE id= " + specialite.getId();
            PreparedStatement pst = MyDB.getInstance().getCnx().prepareStatement(requete4);
            pst.setString(1, nom);
            pst.setString(2, description);
            pst.executeUpdate();
            System.out.println("specialite modifié !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

   

    }    

public void supprimer(Specialites s) {

        try {
            String requete3 = "DELETE FROM specialites WHERE id=" + s.getId();
            Statement st = MyDB.getInstance().getCnx().createStatement();

            st.executeUpdate(requete3);
            System.out.println("Specialité supprimer !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }
public Specialites recupererBynom(String nom) throws SQLException {
        String req = "select * from specialites where nom = ?";
        PreparedStatement st = cnx.prepareStatement(req);

        st.setString(1, nom);
        ResultSet rs = st.executeQuery();
        Specialites p = new Specialites();
        rs.next();
        p.setId(rs.getInt("id"));
        p.setNom(rs.getString("nom"));
        p.setDescription(rs.getString("description"));

        return p;
    }
public ObservableList<Sujet> listSujet(Specialites specialite) {
        ObservableList<Sujet> myList = FXCollections.observableArrayList();

        try {
            String requete = "SELECT * FROM sujet WHERE specialites_id = ?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, specialite.getId());
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                //Commentaire commentaire = new Commentaire();
                int id = rs.getInt("id");
                Date date = rs.getDate("date");
                String title = rs.getString("title");
                 

                Sujet sujet = new Sujet(id, title, date, specialite );
                myList.add(sujet);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return myList;

    }
public Specialites recupererById(int id) {
        try {
            String requete = "SELECT id, nom, description,image FROM specialites WHERE id=?";
            PreparedStatement pst = MyDB.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                String nom = rs.getString("nom");
                String description = rs.getString("description");
                String image = rs.getString("image");

                //rs.setImages(images);

                Specialites specialite = new Specialites(id, nom, description, image);
                return specialite;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
}

