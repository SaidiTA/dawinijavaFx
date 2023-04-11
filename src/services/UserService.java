/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import entities.User;
import java.sql.*;


import java.util.List;
import util.MyDB;

/**
 *
 * @author soumayaab
 */
public class UserService implements IService<User> {
    
Connection cnx;

    public UserService() {
        cnx = MyDB.getInstance().getCnx();
    }
    @Override
    public void ajouter(User t) throws SQLException{
 String req = "insert into user(nom,prenom) values('" + t.getNom() + "','" + t.getPrenom() + "',"  + ")";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
    }

    @Override
    public void modifier(User t) throws SQLException{
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void supprimer(User t) throws SQLException{
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

  

    @Override
    public List<User> recuperer() throws SQLException{
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
     public User recupererById(int id) throws SQLException {
        String req = "select count(*) as nbr from User where id = ?";
        PreparedStatement st = cnx.prepareStatement(req);
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        User u = new User();
        rs.next();
        u.setId(rs.getInt("id"));
        //u.setAge(rs.getInt("age"));
        u.setNom(rs.getString("nom"));
        u.setPrenom(rs.getString("prenom"));
        rs.getInt("nbr");

        return u;
    
    }
}
