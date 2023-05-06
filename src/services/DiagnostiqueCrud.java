/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Diagnostique;
import entities.Dossier;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.MyDB;

/**
 *
 * @author msi
 */
public class DiagnostiqueCrud {
    
    Connection cnx;

    public DiagnostiqueCrud() {
        cnx = MyDB.getInstance().getCnx();
    }

    
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
      
      public void ajouter(Diagnostique t) {
        try {
            String req = "insert into diagnostique(dossiers_id,date,symptome,resultat_test,diag_final) values( " +t.getDossiers_id()+",' " + t.getDate()+ "','" + t.getSymptome()+ "','"
                    + t.getResultat_test()+",' " + t.getDiag_final()+ "');";
          
            Statement st = MyDB .getInstance().getCnx().createStatement();
            st.executeUpdate(req);
            System.out.println("Diagnostique ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
      
      
       public ObservableList<Diagnostique> listerDiagnostiques() {
        ObservableList<Diagnostique> myList = FXCollections.observableArrayList();
        try {

            String requete2 = "Select * FROM diagnostique ";
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
       
         public List<Diagnostique> recupererByPatient(int idPatient) throws SQLException {
        List<Diagnostique> diags = new ArrayList<>();
        String req = "select * from diagnostique where dossiers_id = ?";
        PreparedStatement st = cnx.prepareStatement(req);

        st.setInt(1, idPatient);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            
            Diagnostique c = new Diagnostique();
            c.setId(rs.getInt("id"));
            c.setDate(rs.getDate("date"));
            c.setSymptome(rs.getString("symptome"));
            c.setResultat_test(rs.getString("resultat_test"));
            c.setDiag_final(rs.getString("diag_final"));
            diags.add(c);
        }
         System.out.println("hani");
        return diags;
    }
     /*public ObservableList<Diagnostique> listerDiagnostique(int dossiers_id) {
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

       public ObservableList<Diagnostique> listerDiag(int dossierId) { // renommer le paramètre en dossierId pour suivre la convention de nommage
    ObservableList<Diagnostique> myList = FXCollections.observableArrayList();
    try {
            String requete2 = "SELECT * FROM diagnostique WHERE dossiers_id ='"+dossierId+"';";
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


public Diagnostique recupererById(int id) throws SQLException {
        String req = "select count(*) as nbr from diagnostique where dossiers_id = ?";
        PreparedStatement st = cnx.prepareStatement(req);
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        Diagnostique p = new Diagnostique();
        rs.next();
        p.setId(rs.getInt("id"));
        p.setSymptome(rs.getString("symptome"));
        p.setDate(rs.getDate("date"));
        p.setDiag_final(rs.getString("diag_fianl"));
        //p.setDossiers_id(id);
        p.setResultat_test("resultat_test");
        rs.getInt("nbr");

        return p;
    }

 public ObservableList<Diagnostique> chercherDiagnostique(String chaine){
          String sql="SELECT * FROM diagnostique WHERE (id LIKE ? or date LIKE ? or symptome LIKE ? or resultat_test LIKE ? or diag_final LIKE ? )";
            
             Connection cnx= MyDB.getInstance().getCnx();
            String ch=""+chaine+"%";
         System.out.println(sql);
            ObservableList<Diagnostique> myList= FXCollections.observableArrayList();
        try {
           
            Statement ste= cnx.createStatement();
           // PreparedStatement pst = myCNX.getCnx().prepareStatement(requete6);
            PreparedStatement stee =cnx.prepareStatement(sql);  
            stee.setString(1, ch);
            stee.setString(2, ch);
            stee.setString(3, ch);
            stee.setString(4, ch);
            stee.setString(5, ch);
         System.out.println(stee);

            ResultSet rs = stee.executeQuery();
            while (rs.next()){
                Diagnostique d = new Diagnostique ();
                d.setId(rs.getInt(3));
                d.setDate(rs.getDate(4));
                d.setSymptome(rs.getString(5));
                d.setResultat_test(rs.getString(6));
                d.setDiag_final(rs.getString(7));
                

                myList.add(d);
                System.out.println("diagnostique trouvé! ");
            }
            if (myList.isEmpty()) {
            System.out.println("Aucun diagnostique trouvé !");
        }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
      }

   //--------------------------- NB Voyage ---------------------------------------------//
     public int calculnb(Date datef) {

        PreparedStatement pre;
        int count = 19;
        try {
            Statement stmt = cnx.createStatement();

            String query = "SELECT COUNT(*) FROM diagnostique WHERE date='"+datef+"'";

            ResultSet rs = stmt.executeQuery(query);

            rs.next();
            count = rs.getInt(1);
            return count;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return 0;

    }
}
