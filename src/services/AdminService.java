/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import entities.Admin;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import util.MyDB;

/**
 *
 * @author soumayaab
 */
public class AdminService implements IService<Admin>{
Connection cnx;

    public AdminService() {
        cnx = MyDB.getInstance().getCnx();
    }
    @Override
    public void ajouter(Admin t) throws SQLException{
   
       String req = "INSERT INTO user(email,roles,password,nom,prenom, cin, sexe, telephone, gouvernorat,adresse,  confirm_password,Type,image) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pst = cnx.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
        pst.setString(1, t.getEmail());
        pst.setString(2, String.join(",", t.getRoles()));
        pst.setString(3, t.getPassword());
        pst.setString(4, t.getNom());
        pst.setString(5, t.getPrenom());
        pst.setInt(6, t.getCin());
        pst.setString(7, t.getSexe());
        pst.setString(8, t.getTelephone());
        pst.setString(9, t.getGouvernorat());
        pst.setString(10, t.getAdresse());
        pst.setString(11, t.getConfirm_password());
        pst.setString(12,"admin");
        pst.setString(13, t.getImage());
        pst.executeUpdate();
        ResultSet rs = pst.getGeneratedKeys();
        if (rs.next()) {
            int id = rs.getInt(1);
            String req2 = "INSERT INTO admin(id) " + "VALUES(?)";
            PreparedStatement p = cnx.prepareStatement(req2);
            p.setInt(1, id);
            p.executeUpdate();
    }}


    @Override
    public void modifier(Admin t) throws SQLException{
        String req = "UPDATE user SET email=?, password=?, nom=?, prenom=?, cin=?, sexe=?, telephone=?, gouvernorat=?, adresse=?, confirm_password=?, image=? WHERE id="+t.getId();
        PreparedStatement pst = cnx.prepareStatement(req);
        pst.setString(1, t.getEmail());
        pst.setString(2, t.getPassword());
        pst.setString(3, t.getNom());
        pst.setString(4, t.getPrenom());
        pst.setInt(5, t.getCin());
        pst.setString(6, t.getSexe());
        pst.setString(7, t.getTelephone());
        pst.setString(8, t.getGouvernorat());
        pst.setString(9, t.getAdresse());
        pst.setString(10, t.getConfirm_password());
        pst.setString(11, t.getImage());
        
       
        pst.executeUpdate();
        
            
    }

    @Override
    public void supprimer(Admin t) throws SQLException{
         String req = "DELETE FROM user WHERE id=?";
    PreparedStatement pst = cnx.prepareStatement(req);
    pst.setInt(1, t.getId());
    pst.executeUpdate();
    }

    @Override
    public List<Admin> recuperer() throws SQLException{
         String req = "SELECT * FROM user u JOIN admin ad ON u.id = ad.id";
    PreparedStatement pst = cnx.prepareStatement(req);
    ResultSet rs = pst.executeQuery();

    List<Admin> admins = new ArrayList<>();

    while (rs.next()) {
        Admin admin = new Admin();
        admin.setId(rs.getInt("id"));
        admin.setEmail(rs.getString("email"));
        //List<Medecin> admins = new ArrayList<Medecin>(Arrays.asList(tab));
        admin.setPassword(rs.getString("password"));
        admin.setNom(rs.getString("nom"));
        admin.setPrenom(rs.getString("prenom"));
        admin.setCin(rs.getInt("cin"));
        admin.setSexe(rs.getString("sexe"));
        admin.setTelephone(rs.getString("telephone"));
        admin.setGouvernorat(rs.getString("gouvernorat"));
        admin.setAdresse(rs.getString("adresse"));
        admin.setConfirm_password(rs.getString("confirm_password"));
        admin.setImage(rs.getString("image"));
     
 String rolesString = rs.getString("roles");
        ArrayList<String> roles = new ArrayList<>(Arrays.asList(rolesString.substring(1, rolesString.length() - 1).split(", ")));
        admin.setRoles(roles);
        admins.add(admin);
    }

    return admins;
}
     public Admin recupererById(int id) throws SQLException {
      String req = "SELECT * FROM user u JOIN admin ad ON u.id = ad.id WHERE u.id = ?";
    PreparedStatement st = cnx.prepareStatement(req);
    st.setInt(1, id);
    ResultSet rs = st.executeQuery();
    Admin admin = new Admin();
    if (rs.next()) {
        admin.setId(rs.getInt("id"));
        admin.setEmail(rs.getString("email"));
        admin.setPassword(rs.getString("password"));
        String rolesString = rs.getString("roles");
        ArrayList<String> roles = new ArrayList<>(Arrays.asList(rolesString.substring(1, rolesString.length() - 1).split(", ")));
        admin.setRoles(roles);
        admin.setNom(rs.getString("nom"));
        admin.setPrenom(rs.getString("prenom"));
        admin.setCin(rs.getInt("cin"));
        admin.setSexe(rs.getString("sexe"));
        admin.setTelephone(rs.getString("telephone"));
        admin.setGouvernorat(rs.getString("gouvernorat"));
        admin.setAdresse(rs.getString("adresse"));
        admin.setConfirm_password(rs.getString("confirm_password"));
        admin.setImage(rs.getString("image"));
        
    }
    return admin;
    }
    }
    

