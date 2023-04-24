/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Categorie;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.text.Text;
import util.MyDB;

/**
 *
 * @author hadjn
 */
public class CategorieService  implements IService<Categorie> {
 Connection cnx;

    public CategorieService() {
        cnx = MyDB.getInstance().getCnx();
    }
    @Override
    public void ajouter(Categorie t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifier(Categorie t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean supprimer(Categorie c) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Categorie> recuperer() throws SQLException {
        List<Categorie> activitees = new ArrayList<>();
        String req = "select * from categorie_activite";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            Categorie p = new Categorie();
            p.setId(rs.getInt("id"));
            p.setNom(rs.getString("nom"));
            p.setDescription(rs.getString("description"));
       

            activitees.add(p);
        }
        return activitees;
    }
    
    
    public Categorie recupererBynom(String nom) throws SQLException {
        String req = "select * from categorie_activite where nom = ?";
        PreparedStatement st = cnx.prepareStatement(req);
       
        st.setString(1, nom);
        ResultSet rs = st.executeQuery();
        Categorie p = new Categorie();
        rs.next();
        p.setId(rs.getInt("id"));
        p.setNom(rs.getString("nom"));
        p.setDescription(rs.getString("description"));
       
      
        

        return p;
    }
    
    
}
