/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;


import entities.Medecin;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;
import util.MyDB;

/**
 *
 * @author soumayaab
 */
public class MedecinService implements IService<Medecin>{
Connection cnx;

    public MedecinService() {
        cnx = MyDB.getInstance().getCnx();
    }
   @Override
public void ajouter(Medecin t) throws SQLException {
    String req = "INSERT INTO user(email,roles,password,nom,prenom, cin, sexe, telephone, gouvernorat,adresse,  confirm_password,Type,image) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    PreparedStatement pst = cnx.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
    pst.setString(1, t.getEmail());
    pst.setString(2, t.getRoles() != null ? String.join(",", t.getRoles()) + ",ROLE_MEDECIN" : "[\"ROLE_MEDECIN\"]");
    String hashedPassword = BCrypt.hashpw(t.getPassword(), BCrypt.gensalt(10));
    pst.setString(3, hashedPassword);
 
    pst.setString(4, t.getNom());
    pst.setString(5, t.getPrenom());
    pst.setInt(6, t.getCin());
    pst.setString(7, t.getSexe());
    pst.setString(8, t.getTelephone());
    pst.setString(9, t.getGouvernorat());
    pst.setString(10, t.getAdresse());
   // String hashedConfirmPassword = BCrypt.hashpw(t.getConfirm_password(), BCrypt.gensalt(10));
    pst.setString(11, t.getConfirm_password());
    pst.setString(12,"medecin");
    pst.setString(13, t.getImage());
    pst.executeUpdate();

    ResultSet rs = pst.getGeneratedKeys();
    if (rs.next()) {
        int id = rs.getInt(1);
        String req2 = "INSERT INTO medecin(id, titre, adresse_cabinet, fixe, diplome_formation, tarif, cnam) " + "VALUES(?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement p = cnx.prepareStatement(req2);
        p.setInt(1, id);
        p.setString(2, t.getTitre());
        p.setString(3, t.getAdresse_cabinet());
        p.setString(4, t.getFixe());
        p.setString(5, t.getDiplome_formation());
        p.setFloat(6, t.getTarif());
        p.setBoolean(7, t.isCnam());
        p.executeUpdate();

        boolean passwordMatch = BCrypt.checkpw(t.getPassword(), hashedPassword);
       
    } else {
        throw new SQLException("Record insertion failed");
    }
}

        @Override
    public void modifier(Medecin t) throws SQLException {
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
        String req2 = "UPDATE medecin SET titre=?, adresse_cabinet=?, fixe=?, diplome_formation=?, tarif=?, cnam=? WHERE id="+t.getId();
        PreparedStatement p = cnx.prepareStatement(req2);
        p.setString(1, t.getTitre());
        p.setString(2, t.getAdresse_cabinet());
        p.setString(3, t.getFixe());
        p.setString(4, t.getDiplome_formation());
        p.setFloat(5, t.getTarif());
        p.setBoolean(6, t.isCnam());
       
         p.executeUpdate();
           
        }
    

    @Override
    public void supprimer(Medecin medecin) throws SQLException {
    String req = "DELETE FROM user WHERE id=?";
    PreparedStatement pst = cnx.prepareStatement(req);
    pst.setInt(1, medecin.getId());
    pst.executeUpdate();
      System.out.println("Médecin supprimer avec succès !");
      
}

    @Override
   
public List<Medecin> recuperer() throws SQLException {
    String req = "SELECT * FROM user u JOIN medecin m ON u.id = m.id";
    PreparedStatement pst = cnx.prepareStatement(req);
    ResultSet rs = pst.executeQuery();

    List<Medecin> medecins = new ArrayList<>();

    while (rs.next()) {
        Medecin medecin = new Medecin();
        medecin.setId(rs.getInt("id"));
        medecin.setEmail(rs.getString("email"));
        //List<Medecin> medecins = new ArrayList<Medecin>(Arrays.asList(tab));
        medecin.setPassword(rs.getString("password"));
        medecin.setNom(rs.getString("nom"));
        medecin.setPrenom(rs.getString("prenom"));
        medecin.setCin(rs.getInt("cin"));
        medecin.setSexe(rs.getString("sexe"));
        medecin.setTelephone(rs.getString("telephone"));
        medecin.setGouvernorat(rs.getString("gouvernorat"));
        medecin.setAdresse(rs.getString("adresse"));
        medecin.setConfirm_password(rs.getString("confirm_password"));
        medecin.setImage(rs.getString("image"));
        medecin.setTitre(rs.getString("titre"));
        medecin.setAdresse_cabinet(rs.getString("adresse_cabinet"));
        medecin.setFixe(rs.getString("fixe"));
        medecin.setDiplome_formation(rs.getString("diplome_formation"));
        medecin.setTarif(rs.getFloat("tarif"));
        medecin.setCnam(rs.getBoolean("cnam"));
 String rolesString = rs.getString("roles");
        ArrayList<String> roles;
if (rolesString.length() > 2) {
    roles = new ArrayList<>(Arrays.asList(rolesString.substring(1, rolesString.length() - 1).split(", ")));
} else {
    roles = new ArrayList<>();
}
        medecin.setRoles(roles);
        medecins.add(medecin);
    }

    return medecins;
}
    
    public Medecin recupererById(int id) throws SQLException {
      String req = "SELECT * FROM user u JOIN medecin m ON u.id = m.id WHERE u.id = ?";
    PreparedStatement st = cnx.prepareStatement(req);
    st.setInt(1, id);
    ResultSet rs = st.executeQuery();
    Medecin medecin = new Medecin();
    if (rs.next()) {
        medecin.setId(rs.getInt("id"));
        medecin.setEmail(rs.getString("email"));
        medecin.setPassword(rs.getString("password"));
        String rolesString = rs.getString("roles");
        ArrayList<String> roles = new ArrayList<>(Arrays.asList(rolesString.substring(1, rolesString.length() - 1).split(", ")));
        medecin.setRoles(roles);
        medecin.setNom(rs.getString("nom"));
        medecin.setPrenom(rs.getString("prenom"));
        medecin.setCin(rs.getInt("cin"));
        medecin.setSexe(rs.getString("sexe"));
        medecin.setTelephone(rs.getString("telephone"));
        medecin.setGouvernorat(rs.getString("gouvernorat"));
        medecin.setAdresse(rs.getString("adresse"));
        medecin.setConfirm_password(rs.getString("confirm_password"));
        medecin.setImage(rs.getString("image"));
        medecin.setTitre(rs.getString("titre"));
        medecin.setAdresse_cabinet(rs.getString("adresse_cabinet"));
        medecin.setFixe(rs.getString("fixe"));
        medecin.setDiplome_formation(rs.getString("diplome_formation"));
        medecin.setTarif(rs.getFloat("tarif"));
        medecin.setCnam(rs.getBoolean("cnam"));
    }
    return medecin;
    }

   
}
    

