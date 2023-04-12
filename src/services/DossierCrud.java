/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Dossier;
import util.MyDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author msi
 */
public class DossierCrud {
     public void ajouter_dossier(Dossier d) {
        try {
            String requete1 = "INSERT INTO dossier(numero,code_apci,description) VALUES(?,?,?)";
          // String requete1 = "INSERT INTO dossier(patient_id,medecin_id,numero,code_apci,description) VALUES(?,?,?,?,?)";

            PreparedStatement pst = MyDB.getInstance().getCnx().prepareStatement(requete1);
            pst.setString(2, d.getCode_apci());
            pst.setInt(1, d.getNumero());
            pst.setString(3, d.getDescription());
            pst.executeUpdate();
            System.out.println("dossier ajouté !");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
       public ObservableList<Dossier> listerDossiers() {
        ObservableList<Dossier> myList = FXCollections.observableArrayList();
        try {

            String requete2 = "Select * FROM dossier";
            Statement st = MyDB.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete2);
            while (rs.next()) {
                Dossier rec = new Dossier();
                rec.setId(rs.getInt(1));
               //rec.setPatient_id(rs.getInt("patient_id));
               //rec.setMedecin_id(rs.getInt("medecin_id"));
                
                rec.setNumero(rs.getInt("numero"));
                rec.setCode_apci(rs.getString("code_apci"));
                rec.setDescription(rs.getString("Description"));
                myList.add(rec);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return myList;
    }
       
              public List<Dossier> lister() {
        ObservableList<Dossier> myList = FXCollections.observableArrayList();
        try {

            String requete2 = "Select * FROM dossier";
            Statement st = MyDB.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete2);
            while (rs.next()) {
                Dossier rec = new Dossier();
               //rec.setId(rs.getInt(1));
               //rec.setPatient_id(rs.getInt("patient_id));
               //rec.setMedecin_id(rs.getInt("medecin_id"));
                
                rec.setNumero(rs.getInt("numero"));
                rec.setCode_apci(rs.getString("code_apci"));
                rec.setDescription(rs.getString("Description"));
                myList.add(rec);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return myList;
    }
           public void supprimer_dossier(Dossier d) {

        try {
            String requete3 = "DELETE FROM dossier WHERE id=" + d.getId();
            Statement st = MyDB.getInstance().getCnx().createStatement();

            st.executeUpdate(requete3);
            System.out.println("Dossier supprimé !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }
    public void supprimer_doss(Dossier d) {
        try {
            String requete3 = "DELETE FROM dossier WHERE numero  = "+d.getNumero();
            Statement st = MyDB.getInstance().getCnx().createStatement();

            st.executeUpdate(requete3);
            System.out.println("Dossier supprimé !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public void modifier_dossier(Dossier r, int numero, String code_apci ,String description) {
        try {
            String requete4 = " UPDATE dossier SET " + "  numero= ?, code_apci = ?, description = ? WHERE id= " + r.getId();
            PreparedStatement pst = MyDB.getInstance().getCnx().prepareStatement(requete4);
            pst.setInt(1, numero);
            pst.setString(2, code_apci);
            pst.setString(3, description);
            pst.executeUpdate();
            System.out.println("Dossier modifié !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

   

    }
public void modifier_dos(Dossier r, int numero, String code_apci ,String description) {
        try {
            String requete4 = " UPDATE dossier SET " + "  numero= ?, code_apci = ?, description = ? WHERE numero= " + r.getNumero();
            PreparedStatement pst = MyDB.getInstance().getCnx().prepareStatement(requete4);
            pst.setInt(1, numero);
            pst.setString(2, code_apci);
            pst.setString(3, description);
            pst.executeUpdate();
            System.out.println("Dossier modifié !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }}

    
    /* public String bad_words(String badWord) {

        List<String> badListW = Arrays.asList("mot","besh tmout", "fuck");
        String badNew = "";
        List<String> newList = new ArrayList<>();
        for (String str : badListW) {
            if (badWord.contains(str)) {
                badNew += "" + str;
                if (str.length() >= 1) {
                    StringBuilder result = new StringBuilder();
                    result.append(str.charAt(0));
                    for (int i = 0; i < str.length() - 2; ++i) {
                        result.append("*");
                    }
                    result.append(str.charAt(str.length() - 1));
                    str = result.toString();
                    if (!str.isEmpty()) {
                        System.out.println("ATTENTION !! Vous avez écrit un gros mot  : " + result + " .C'est un avertissement ! Priére d'avoir un peu de respect ! Votre description sera envoyée comme suit :");
                        System.out.println(badWord.replace(badNew, "") + " ");
                    }
                }
            }
        }
        return (badWord.replace(badNew, "") + " ");
    }
    */
}
