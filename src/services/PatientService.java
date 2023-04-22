/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import entities.Patient;
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
public class PatientService implements IService<Patient>{
Connection cnx;

    public PatientService() {
        cnx = MyDB.getInstance().getCnx();
    }
    @Override
    public void ajouter(Patient t) throws SQLException{
        
       String req = "INSERT INTO user(email,roles,password,nom,prenom, cin, sexe, telephone, gouvernorat,adresse,  confirm_password,Type,image) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pst = cnx.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
        pst.setString(1, t.getEmail());
        pst.setString(2, t.getRoles() != null ? String.join(",", t.getRoles()) + ",ROLE_PATIENT" : "[\"ROLE_PATIENT\"]");
        String hashedPassword = BCrypt.hashpw(t.getPassword(), BCrypt.gensalt(10));
        pst.setString(3, hashedPassword);
 
        pst.setString(4, t.getNom());
        pst.setString(5, t.getPrenom());
        pst.setInt(6, t.getCin());
        pst.setString(7, t.getSexe());
        pst.setString(8, t.getTelephone());
        pst.setString(9, t.getGouvernorat());
        pst.setString(10, t.getAdresse());
        pst.setString(11, t.getConfirm_password());
        pst.setString(12,"patient");
        pst.setString(13, t.getImage());
        pst.executeUpdate();
        ResultSet rs = pst.getGeneratedKeys();
        if (rs.next()) {
            int id = rs.getInt(1);
            String req2 = "INSERT INTO patient(id) " + "VALUES(?)";
            PreparedStatement p = cnx.prepareStatement(req2);
            p.setInt(1, id);
            p.executeUpdate();
             boolean passwordMatch = BCrypt.checkpw(t.getPassword(), hashedPassword);
       
    } else {
        throw new SQLException("Record insertion failed");
    }
    }

    @Override
    public void modifier(Patient t) throws SQLException{
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
    public void supprimer(Patient t) throws SQLException{
    String req = "DELETE FROM user WHERE id=?";
    PreparedStatement pst = cnx.prepareStatement(req);
    pst.setInt(1, t.getId());
    pst.executeUpdate();    
    }

    @Override
    public List<Patient> recuperer() throws SQLException{
         String req = "SELECT * FROM user u JOIN patient m ON u.id = m.id";
    PreparedStatement pst = cnx.prepareStatement(req);
    ResultSet rs = pst.executeQuery();

    List<Patient> patients = new ArrayList<>();

    while (rs.next()) {
        Patient patient = new Patient();
        patient.setId(rs.getInt("id"));
        patient.setEmail(rs.getString("email"));
        //List<Medecin> patients = new ArrayList<Medecin>(Arrays.asList(tab));
        patient.setPassword(rs.getString("password"));
        patient.setNom(rs.getString("nom"));
        patient.setPrenom(rs.getString("prenom"));
        patient.setCin(rs.getInt("cin"));
        patient.setSexe(rs.getString("sexe"));
        patient.setTelephone(rs.getString("telephone"));
        patient.setGouvernorat(rs.getString("gouvernorat"));
        patient.setAdresse(rs.getString("adresse"));
        patient.setConfirm_password(rs.getString("confirm_password"));
        patient.setImage(rs.getString("image"));
     
 String rolesString = rs.getString("roles");
        ArrayList<String> roles = new ArrayList<>(Arrays.asList(rolesString.substring(1, rolesString.length() - 1).split(", ")));
        patient.setRoles(roles);
        patients.add(patient);
    }

    return patients;
}

    public Patient recupererById(int id) throws SQLException {
      String req = "SELECT * FROM user u JOIN patient p ON u.id = p.id WHERE u.id = ?";
    PreparedStatement st = cnx.prepareStatement(req);
    st.setInt(1, id);
    ResultSet rs = st.executeQuery();
    Patient patient = new Patient();
    if (rs.next()) {
        patient.setId(rs.getInt("id"));
        patient.setEmail(rs.getString("email"));
        patient.setPassword(rs.getString("password"));
        String rolesString = rs.getString("roles");
        ArrayList<String> roles = new ArrayList<>(Arrays.asList(rolesString.substring(1, rolesString.length() - 1).split(", ")));
        patient.setRoles(roles);
        patient.setNom(rs.getString("nom"));
        patient.setPrenom(rs.getString("prenom"));
        patient.setCin(rs.getInt("cin"));
        patient.setSexe(rs.getString("sexe"));
        patient.setTelephone(rs.getString("telephone"));
        patient.setGouvernorat(rs.getString("gouvernorat"));
        patient.setAdresse(rs.getString("adresse"));
        patient.setConfirm_password(rs.getString("confirm_password"));
        patient.setImage(rs.getString("image"));
        
    }
    return patient;
    }}
    
    

