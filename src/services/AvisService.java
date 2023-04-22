/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import entities.Avis;
import entities.Medecin;
import entities.Patient;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import util.MyDB;

public class AvisService implements IService<Avis> {

    Connection cnx;

    public AvisService() {
         cnx = MyDB.getInstance().getCnx();
    }
    
    @Override
    public void ajouter(Avis avis) throws SQLException {
        String sql = "INSERT INTO avis (medecin_id, patient_id, text, note, date) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement = cnx.prepareStatement(sql);
        statement.setInt(1, avis.getMedecin().getId());
        statement.setInt(2, avis.getPatient().getId());
        statement.setString(3, avis.getText());
        statement.setDouble(4, avis.getNote());
        statement.setDate(5, new java.sql.Date(avis.getDate().getTime()));
        statement.executeUpdate();
    }

    @Override
    public void modifier(Avis avis) throws SQLException {
        String sql = "UPDATE avis SET medecin_id = ?, patient_id = ?, text = ?, note = ?, date = ? , statut = ? WHERE id = ?";
        PreparedStatement statement = cnx.prepareStatement(sql);
        statement.setInt(1, avis.getMedecin().getId());
        statement.setInt(2, avis.getPatient().getId());
        statement.setString(3, avis.getText());
        statement.setDouble(4, avis.getNote());
        statement.setDate(5, new java.sql.Date(avis.getDate().getTime()));
        statement.setString(6, avis.getStatut());
        statement.setInt(7, avis.getId());
        statement.executeUpdate();
    }

  public void modifierStatut(int id, String statut) throws SQLException {
    String sql = "UPDATE avis SET statut = ? WHERE id = ?";
    PreparedStatement statement = cnx.prepareStatement(sql);
    statement.setString(1, statut);
    statement.setInt(2, id);
    statement.executeUpdate();
}
    @Override
    public void supprimer(Avis avis) throws SQLException {
        String sql = "DELETE FROM avis WHERE id = ?";
        PreparedStatement statement = cnx.prepareStatement(sql);
        statement.setInt(1, avis.getId());
        statement.executeUpdate();
    }

    @Override
    public List<Avis> recuperer() throws SQLException {
        String sql = "SELECT id, medecin_id, patient_id, text, note, date,statut FROM avis";
        PreparedStatement statement = cnx.prepareStatement(sql);
        ResultSet result = statement.executeQuery();
        List<Avis> avisList = new ArrayList<>();
        while (result.next()) {
            int id = result.getInt("id");
            int medecinId = result.getInt("medecin_id");
            int patientId = result.getInt("patient_id");
            String text = result.getString("text");
            double note = result.getDouble("note");
            Date date = result.getDate("date");
            String statut = result.getString("statut");
            Medecin medecin = new MedecinService().recupererById(medecinId);
            Patient patient = new PatientService().recupererById(patientId);
            Avis avis = new Avis(id, medecin, patient, text, note, date,statut);
            avisList.add(avis);
        }
        return avisList;
    }

public Avis recupererById(int id) throws SQLException {
    String sql = "SELECT medecin_id, patient_id, text, note,date,statut FROM avis WHERE id = ?";
    PreparedStatement statement = cnx.prepareStatement(sql);
    statement.setInt(1, id);
    ResultSet result = statement.executeQuery();
    if (result.next()) {
        int medecinId = result.getInt("medecin_id");
        int patientId = result.getInt("patient_id");
        String text = result.getString("text");
        double note = result.getDouble("note");
        Date date = result.getDate("date");
        String statut = result.getString("statut");
        Medecin medecin = new MedecinService().recupererById(medecinId);
        Patient patient = new PatientService().recupererById(patientId);
        Avis avis = new Avis(id, medecin, patient, text, note, date,statut);
        return avis;
    }
    return null;
}

   

   

}




