/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Consulation;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import util.MyDB;

/**
 *
 * @author USER
 */
public class ConsulationService implements IService<Consulation> {

    Connection cnx;

    public ConsulationService() {
        cnx = MyDB.getInstance().getCnx();
    }

    @Override
public void ajouter(Consulation t) throws SQLException {
       
    String req = "INSERT INTO consulation (patients_id,date, heuredebut, heurefin,url_consultation,est_termine) VALUES ('" + t.getPatients_id() + "','" + t.getDate() + "', '" + t.getHeuredebut() + "', '" + t.getHeurefin() + "', '" + t.getUrl_consultation() +"', '" + t.getEst_termine()+"')";
    Statement st = cnx.createStatement();
    st.executeUpdate(req);
}


    @Override
    public void modifier(Consulation t) throws SQLException {
        String req = "update consulation set  heuredebut = ?, heurefin = ? where id = ?";
        PreparedStatement cs = cnx.prepareStatement(req);
        
        cs.setTimestamp(1, t.getHeuredebut());
        cs.setTimestamp(2, t.getHeurefin());
        cs.setInt(3, t.getId());
        cs.executeUpdate();
    }
    public void consultationTerminer(int id) throws SQLException {
        String req = "update consulation set heurefin = ?, est_termine = ? where id = ?";
        PreparedStatement cs = cnx.prepareStatement(req);
               Timestamp heure = new Timestamp(System.currentTimeMillis());


        cs.setTimestamp(1,heure);
        
        cs.setString(2, "1");
        cs.setInt(3,id);

        cs.executeUpdate();
    }

    @Override
    public void supprimer(Consulation t) throws SQLException {
        String req = "delete from consulation where consulation.id='"+t.getId()+"';";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
    }

    @Override
    
    public List<Consulation> recuperer() throws SQLException {
        List<Consulation> consultations = new ArrayList<>();
        String req = "select * from consulation";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            
            Consulation c = new Consulation();
            c.setId(rs.getInt("id"));
            c.setPatients_id(rs.getInt("patients_id"));
            c.setMedecin_id(rs.getInt("medecin_id"));
            c.setDate(rs.getDate("date"));
            c.setHeuredebut(rs.getTimestamp("heuredebut"));
            c.setHeurefin(rs.getTimestamp("heurefin"));
            c.setUrl_consultation(rs.getString("url_consultation"));
            c.setEst_termine(rs.getString("est_termine"));
            consultations.add(c);
        }
        return consultations;
    }
    public int recupererByURL(String url) throws SQLException {
        String req = "select id from consulation where url_consultation = ?";
        PreparedStatement st = cnx.prepareStatement(req);

        st.setString(1, url);
        ResultSet rs = st.executeQuery();
        rs.next();
        int i;
        i=rs.getInt("id");
        return i;
    }
     public List<Consulation> recupererByIdPatient(int idPatient) throws SQLException {
        List<Consulation> consultations = new ArrayList<>();
        String req = "select * from consulation where patients_id =?";
        PreparedStatement st = cnx.prepareStatement(req);

        st.setInt(1, idPatient);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            
            Consulation c = new Consulation();
            c.setId(rs.getInt("id"));
            c.setDate(rs.getDate("date"));
            c.setHeuredebut(rs.getTimestamp("heuredebut"));
            c.setHeurefin(rs.getTimestamp("heurefin"));
            consultations.add(c);
        }
        return consultations;
    }
     public List<Consulation> recupererByDate(Date date) throws SQLException {
    List<Consulation> consultations = new ArrayList<>();
    String req = "select * from consulation where date = ?";
    PreparedStatement st = cnx.prepareStatement(req);

    st.setDate(1, date);
    ResultSet rs = st.executeQuery();

    while (rs.next()) {
        Consulation c = new Consulation();
        c.setId(rs.getInt("id"));
        c.setDate(rs.getDate("date"));
        c.setHeuredebut(rs.getTimestamp("heuredebut"));
        c.setHeurefin(rs.getTimestamp("heurefin"));
        consultations.add(c);
    }
    return consultations;
}

     
     
     public List<Consulation> getCalendarActivitiesMonth(ZonedDateTime mth) throws SQLException {
        String req = "select * from Consulation where Month(Heuredebut) = ? and year(Heuredebut)=?";
         
        PreparedStatement st = cnx.prepareStatement(req);
        st.setObject(1, mth.getMonthValue());
        st.setObject(2, mth.getYear());
        ResultSet rs = st.executeQuery();
         List<Consulation> Consulations = new ArrayList<>();
          while (rs.next()) {
            Consulation p = new Consulation();
            p.setId(rs.getInt("id"));
            p.setMedecin_id(rs.getInt("medecin_id"));
             p.setPatients_id(rs.getInt("patients_id"));
             p.setEst_termine(rs.getString("est_termine"));
            p.setDate(rs.getDate("Date"));
            p.setHeuredebut(rs.getTimestamp("heuredebut"));
            p.setHeurefin(rs.getTimestamp("heurefin"));
            

            Consulations.add(p);
        }
        return Consulations;
    }
     

}
