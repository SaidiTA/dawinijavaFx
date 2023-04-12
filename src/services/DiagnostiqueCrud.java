/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Diagnostique;
import entities.Dossier;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.MyDB;

/**
 *
 * @author msi
 */
public class DiagnostiqueCrud {
    
      public void ajouter_diagnostique(Diagnostique d) {
        try {
            String requete1 = "INSERT INTO diagnostique(date,symptome,resultat_test,diag_final) VALUES(?,?,?,?)";
          // String requete1 = "INSERT INTO diagnostique (patient_id,dossiers_id,date,symptome,resultat_test,diag_final) VALUES(?,?,?,?,?,?)";

            PreparedStatement pst = MyDB.getInstance().getCnx().prepareStatement(requete1);
            pst.setString(2, d.getSymptome());
          //  pst.setInt(1, d.getDossiers_id());
            pst.setDate(1, d.getDate());
            pst.setString(3,d.getResultat_test());
            pst.setString(4,d.getDiag_final());
            pst.executeUpdate();
            System.out.println("diagnostique ajouté !");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
       public ObservableList<Diagnostique> listerDiagnostiques() {
        ObservableList<Diagnostique> myList = FXCollections.observableArrayList();
        try {

            String requete2 = "Select * FROM diagnostique";
            Statement st = MyDB.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete2);
            while (rs.next()) {
                Diagnostique rec = new Diagnostique();
                rec.setId(rs.getInt(1));
               //rec.setPatient_id(rs.getInt("patient_id));
              //  rec.setDossier_id(rs.getInt("dossier_id"));
                rec.setDate(rs.getDate("date"));
                rec.setSymptome(rs.getString("symptome"));
                rec.setResultat_test(rs.getString("resultat_test"));
                rec.setDiag_final(rs.getString("diag_final"));
                
                myList.add(rec);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return myList;
    }
       
     /*  public ObservableList<Diagnostique> listerDiagnostique(int dossiers_id) {
    ObservableList<Diagnostique> myList = FXCollections.observableArrayList();
    try {
        String requete2 = "SELECT * FROM diagnostique WHERE dossiers_id = ?";
        PreparedStatement st = MyDB.getInstance().getCnx().prepareStatement(requete2);
        st.setInt(1, dossiers_id);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            Diagnostique rec = new Diagnostique();
            rec.setId(rs.getInt(1));
            //rec.setDossier_id(rs.getInt(2));
            rec.setSymptome(rs.getString("symptome"));
            rec.setResultat_test(rs.getString("resultat_test"));
            rec.setDiag_final(rs.getString("diag_final"));
            myList.add(rec);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return myList;
}*/

       public ObservableList<Diagnostique> listerDiagnostique(int dossierId) { // renommer le paramètre en dossierId pour suivre la convention de nommage
    ObservableList<Diagnostique> myList = FXCollections.observableArrayList();
    try {
            String requete2 = "SELECT * FROM diagnostique d,dossier do WHERE d.dossiers_id = do.id";
        PreparedStatement st = MyDB.getInstance().getCnx().prepareStatement(requete2);
        st.setInt(1, dossierId);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            Diagnostique rec = new Diagnostique();
            rec.setId(rs.getInt(1));
            //rec.setDossier_id(rs.getInt(2));
            rec.setSymptome(rs.getString("symptome"));
            rec.setResultat_test(rs.getString("resultat_test"));
            rec.setDiag_final(rs.getString("diag_final"));
            myList.add(rec);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return myList;
}

       
              public List<Diagnostique> lister() {
        ObservableList<Diagnostique> myList = FXCollections.observableArrayList();
        try {

           // String requete2 = "Select * FROM diagnostique WHERE dossier_id=?";
            //Statement st = MyDB.getInstance().getCnx().createStatement();
            //ResultSet rs = st.executeQuery(requete2);
            String requete2 = "SELECT * FROM diagnostique d,dossier do WHERE d.dossiers_id = do.id";
            PreparedStatement ps = MyDB.getInstance().getCnx().prepareStatement(requete2);
            int dossiers_id = 0;
            ps.setInt(1, dossiers_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Diagnostique rec = new Diagnostique();
               //rec.setId(rs.getInt(1));
               //rec.setPatient_id(rs.getInt("patient_id));
                //rec.setDossier_id(rs.getInt("dossier_id"));
                rec.setDate(rs.getDate("date"));
                rec.setSymptome(rs.getString("symptome"));
                rec.setDiag_final("diag_final");
                rec.setResultat_test("resultat_test");
                myList.add(rec);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return myList;
    }
           public void supprimer_diagnostique(Diagnostique d) {

        try {
            String requete3 = "DELETE FROM diagnostique WHERE id=" + d.getId();
            Statement st = MyDB.getInstance().getCnx().createStatement();

            st.executeUpdate(requete3);
            System.out.println("diagnostique supprimé !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }
    public void supprimer_diag(Diagnostique d) {
        try {
            String requete3 = "DELETE FROM dossier WHERE dossiers_id  = "+d.getDossiers_id();
            Statement st = MyDB.getInstance().getCnx().createStatement();

            st.executeUpdate(requete3);
            System.out.println("Diagnostique supprimé !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public void modifier_diagnostique(Diagnostique d,Date date,String symptome ,String resultat_test, String diag_final) {
        try {
            String requete4 = " UPDATE diagnostique SET " + "  date= ?, symptome = ?, resultat_test = ?, diag_final = ? WHERE id= " + d.getId();
            PreparedStatement pst = MyDB.getInstance().getCnx().prepareStatement(requete4);
            pst.setDate(1, date);
            pst.setString(2, symptome);
            pst.setString(3, resultat_test);
            pst.setString(4, diag_final);
            pst.executeUpdate();
            System.out.println("diagnostique modifié !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

   

    }
public void modifier_diag(Diagnostique d,Date date,String symptome ,String resultat_test, String diag_final) {
        try {
            String requete4 = " UPDATE dossier SET " + "  date= ?, symptome = ?, resultat_test = ?, diag_final= ? WHERE id= " + d.getId();
            PreparedStatement pst = MyDB.getInstance().getCnx().prepareStatement(requete4);
            pst.setDate(1, date);
            pst.setString(2, symptome);
            pst.setString(3, resultat_test);
            pst.setString(4, diag_final);
            pst.executeUpdate();
            System.out.println("Diagnostique modifié !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }}
}
