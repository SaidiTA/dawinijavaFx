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
     
     public int login(String email, String password) {
    try {
        String sql = "SELECT COUNT(1), id, role FROM utilisateur WHERE email=? AND password=?";
        PreparedStatement ps = cnx.prepareStatement(sql);
        ps.setString(1, email);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            if (rs.getInt(1) == 1) {
                int id = rs.getInt("id");
                ArrayList<String> roles = new ArrayList<String>();
                int role = rs.getInt("role");
                if (role == 1) {
                    roles.add("[\"ROLE_ADMIN\"]");
                } else if (role == 2) {
                    roles.add("[\"ROLE_MEDECIN\"]");
                } else if (role == 3) {
                    roles.add("[\"ROLE_PATIENT\"]");
                }
                  else if (role == 4) {
                    roles.add("[\"ROLE_ASSISTANT\"]");
                }
                // Return a User object with the retrieved data
                
                return id;
            } else {
                return 0;
            }
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return 0;
}
}
