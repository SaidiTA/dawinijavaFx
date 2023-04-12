/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.MyDB;

import entities.Rendezvous;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author HP
 */
public class RendezvousService implements IService<Rendezvous> {
    
    Connection cnx;

    public RendezvousService() {
        cnx = MyDB.getInstance().getCnx();
    }
    
    /*@Override
    public void ajouter(Rendezvous t) throws SQLException {
        String req = "INSERT INTO rendez_vous(titre,description,date,etat) values('" + t.getTitre() + "','" + t.getDescription() + "'," + t.getDate() + "'," + t.getEtat() + ")";
       // String req = "INSERT INTO rendez_vous(titre,description,date,etat) values('" + t.getTitre() + "','" + t.getDescription() + "',"  + t.getEtat() + ")";
        
       

    }*/
    
     @Override
    public void ajouter(Rendezvous t) throws SQLException {
        String req = "insert into rendez_vous(titre,description,date,etat) values('"   + t.getTitre() + "','" + t.getDescription() + "','" + t.getDate() + "','" + t.getEtat() +      " ')";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);

    }
   
   @Override
    public ObservableList<Rendezvous> getAllRendezvous() {
       ObservableList<Rendezvous> list = FXCollections.observableArrayList();
        try {
            String req = "Select * from rendez_vous";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Rendezvous t;
                t = new Rendezvous(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getString(5));
                //Rendezvous t = new Rendezvous(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
                list.add(t);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
        
    }
    
    //@Override
    
    public Rendezvous getOneByIdT(int id) {
         Rendezvous t = null;
        try {
            String req = "Select * from rendez_vous WHERE id = "+id;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                  if (rs.getInt(1)==id){
                      
                t = new Rendezvous(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getString(5));}
               
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return t;
    }
     
    
   // @Override
   // public void modifier(Rendezvous t) throws SQLException {
     //   String req = "update rendez_vous set titre = ?, description = ?, date = ?,etat = ? where id = ?";
     //   PreparedStatement ps = cnx.prepareStatement(req);

     //   ps.setString(1, t.getTitre());
      //  ps.setString(2, t.getDescription());
      //  ps.setDate(3, t.getDate());
      //  ps.setString(4, t.getEtat());
      //  ps.setInt(5, t.getId());
 //ps.executeUpdate();
    
    
    
    //@Override
    //public void supprimer(Rendezvous t)throws SQLException {
     //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    //}
    
    
    
    //@Override
    //public List<Personne> recuperer() throws SQLException {
      //  List<Personne> personnes = new ArrayList<>();
      //  String req = "select * from personne";
      //  Statement st = cnx.createStatement();
      //  ResultSet rs = st.executeQuery(req);
      //  while (rs.next()) {
        //    Personne p = new Personne();
       //     p.setId(rs.getInt("id"));
       //     p.setAge(rs.getInt("age"));
        //    p.setNom(rs.getString("nom"));
       //     p.setPrenom(rs.getString("prenom"));

       //     personnes.add(p);
       // }
       // return personnes;
    //}

    //public Personne recupererById(int id) throws SQLException {
      //  String req = "select count(*) as nbr from personne where id = ?";
      //  PreparedStatement st = cnx.prepareStatement(req);
       // st.setInt(1, id);
        //ResultSet rs = st.executeQuery();
       // Personne p = new Personne();
       // rs.next();
       // p.setId(rs.getInt("id"));
       // p.setAge(rs.getInt("age"));
       // p.setNom(rs.getString("nom"));
       // p.setPrenom(rs.getString("prenom"));
       // rs.getInt("nbr");

       // return p;
   // }
    
    /*public ObservableList<Rendezvous> getAllRendezvous() {
       ObservableList<Rendezvous> list = FXCollections.observableArrayList();
        try {
            String req = "Select * from rendez_vous";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            //while (rs.next()) {
               // Rendezvous t = new Rendezvous(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getString(5));
             //   list.add(t);
           // }
            while (rs.next()) {
            Rendezvous p = new Rendezvous();
           p.setId(rs.getInt("id"));
           p.setTitre(rs.getString("titre"));
            p.setDescription(rs.getString("description"));
           p.setDate(rs.getDate("date"));
           p.setEtat(rs.getString("etat"));

            list.add(p);
        }
            
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
        
    }*/
    
    //@Override
    public boolean supprimerRR(int id) throws SQLException {
        boolean res = false;
        try {
            String req = "DELETE FROM `rendez_vous` WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("rendez vous Deleted !");
            res=true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return res;
    }
    
    
}
