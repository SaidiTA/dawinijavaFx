/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import entities.User;
import java.sql.*;
import java.util.ArrayList;


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

    public String getUserRole(String email, String password) {
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    String role = null;
    
    try {
        connection = MyDB.getInstance().getCnx();
        statement = connection.prepareStatement("SELECT roles FROM user WHERE email = ?");
        statement.setString(1, email);
        resultSet = statement.executeQuery();
        
        if (resultSet.next()) {
            role = resultSet.getString("roles");
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    } finally {
        try {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    return role;
}

   public boolean login(String email, String password) throws SQLException {
    PreparedStatement pst = null;
    ResultSet rs = null;
    Connection cnx = MyDB.getInstance().getCnx();
    try {
        pst = cnx.prepareStatement("SELECT * FROM user WHERE email=? AND password=?");
        pst.setString(1, email);
        pst.setString(2, password);
        rs = pst.executeQuery();
        if (rs.next()) {
            // login successful
            return true;
        } else {
            // login failed
            return false;
        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    } finally {
        // close the resources
        if (pst != null) {
            pst.close();
        }
        if (rs != null) {
            rs.close();
        }
    }
   }
}