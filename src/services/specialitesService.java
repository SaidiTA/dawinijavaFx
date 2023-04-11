/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Specialites;
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
public class specialitesService {
    
    Connection cnx;

    public void ajouter(Specialites d) {
        try {
            String requete1 = "INSERT INTO Specialites(nom,description) VALUES(?,?)";

            PreparedStatement pst = MyDB.getInstance().getCnx().prepareStatement(requete1);
            pst.setString(1, d.getNom());
            pst.setString(2, d.getDescription());
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
            String requete3 = "DELETE FROM Specialites WHERE id=" + s.getId();
            Statement st = MyDB.getInstance().getCnx().createStatement();

            st.executeUpdate(requete3);
            System.out.println("Specialité supprimer !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }
}

