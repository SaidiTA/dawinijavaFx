/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.MyDB;

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
    String req = "update user set nom=?, prenom=? where id=?";
    PreparedStatement st = cnx.prepareStatement(req);
    st.setString(1, t.getNom());
    st.setString(2, t.getPrenom());
    st.setInt(3, t.getId());
    st.executeUpdate();
}

@Override
public void supprimer(User t) throws SQLException{
    String req = "delete from user where id=?";
    PreparedStatement st = cnx.prepareStatement(req);
    st.setInt(1, t.getId());
    st.executeUpdate();
}

@Override
public List<User> recuperer() throws SQLException{
    List<User> users = new ArrayList<>();
    String req = "select * from user";
    Statement st = cnx.createStatement();
    ResultSet rs = st.executeQuery(req);
    while (rs.next()) {
        User u = new User();
        u.setId(rs.getInt("id"));
        u.setNom(rs.getString("nom"));
        u.setPrenom(rs.getString("prenom"));
        users.add(u);
    }
    return users;
}

     public User recupererById(int id) throws SQLException {
         System.out.println("idididid "+id);
        String req = "select * from User where id = ?";
        PreparedStatement st = cnx.prepareStatement(req);
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        User u = new User();
        rs.next();
        u.setId(rs.getInt("id"));
        //u.setAge(rs.getInt("age"));
        u.setNom(rs.getString("nom"));
        u.setPrenom(rs.getString("prenom"));
        //rs.getInt("nbr");

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