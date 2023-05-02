/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Dossier;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import util.MyDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.usermodel.Cell;

/**
 *
 * @author msi
 */
public class DossierCrud {
    Connection cnx;
     public void ajouter_dossier(Dossier d) {
      try {
            String requete1 = "INSERT INTO dossier(patient_id,numero,code_apci,description) VALUES(?,?,?,?)";
          // String requete1 = "INSERT INTO dossier(patient_id,medecin_id,numero,code_apci,description) VALUES(?,?,?,?,?)";
          /*
            PreparedStatement pst = MyDB.getInstance().getCnx().prepareStatement(requete1); 
            pst.setInt(1, d.getNumero());
            pst.setString(2, d.getCode_apci());
            pst.setString(3, d.getDescription());
            
            pst.setInt(4, d.getPatient_id());
            pst.executeUpdate();
            System.out.println("dossier ajouté !");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }*/
          PreparedStatement pst = MyDB.getInstance().getCnx().prepareStatement(requete1); 
    pst.setInt(1, d.getPatient_id());
    pst.setInt(2, d.getNumero());
    pst.setString(3, d.getCode_apci());
    pst.setString(4, d.getDescription());
    pst.executeUpdate();
    System.out.println("Dossier ajouté !");

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
                rec.setPatient_id(rs.getInt(2));
               //rec.setPatient_id(rs.getInt("patient_id"));
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

   public List<Dossier> RechercheDossier(String numero) {
 List<Dossier> dossier = new ArrayList<>();
        try {
            String req ="select * from dossier WHERE numero = '"+numero+"'";
           // Statement st = cnx.createStatement();
           // ResultSet rs = st.executeQuery(req);
             Statement st = MyDB.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next())
            {
               Dossier d = new Dossier();
               d.setId(rs.getInt("id"));
               d.setNumero(rs.getInt("numero"));
               d.setCode_apci(rs.getString("code_apci"));
               d.setDescription(rs.getString("Description"));
               
               dossier.add(d);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());        }
            
return dossier;    
   }
   
     public ObservableList<Dossier> chercherDossier(String chaine){
          String sql="SELECT * FROM dossier WHERE (numero LIKE ? or id LIKE ? or description LIKE ? or code_apci LIKE ? )";
            
             Connection cnx= MyDB.getInstance().getCnx();
            String ch=""+chaine+"%";
         System.out.println(sql);
            ObservableList<Dossier> myList= FXCollections.observableArrayList();
        try {
           
            Statement ste= cnx.createStatement();
           // PreparedStatement pst = myCNX.getCnx().prepareStatement(requete6);
            PreparedStatement stee =cnx.prepareStatement(sql);  
            stee.setString(1, ch);
            stee.setString(2, ch);
            stee.setString(3, ch);
            stee.setString(4, ch);
         System.out.println(stee);

            ResultSet rs = stee.executeQuery();
            while (rs.next()){
                Dossier d = new Dossier ();
                d.setId(rs.getInt(3));
                d.setCode_apci(rs.getString(4));
                d.setNumero(rs.getInt(5));
                d.setDescription(rs.getString(6));
                

                myList.add(d);
                System.out.println("dossier trouvé! ");
            }
            if (myList.isEmpty()) {
            System.out.println("Aucun dossier trouvé !");
        }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
      }
      //---------------------------------------- Excel -----------------------------------------------------------//
     
     public void getDefendants( String db) throws Exception  { 
        
        @SuppressWarnings("unused")
        Workbook rbook = WorkbookFactory.create(new FileInputStream("C:\\Users\\msi\\Documents\\pidev\\test2.xls") );
        @SuppressWarnings("resource")
        Workbook writeWorkbook = (Workbook) new HSSFWorkbook();
        Sheet desSheet = writeWorkbook.createSheet("new sheet");

        Statement stmt = null;
        ResultSet rs = null;
        try{
            String query ="SELECT * FROM dossier"+db;

            stmt = cnx.createStatement();
            rs = stmt.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();

            Row desRow1 = desSheet.createRow(0);
            for(int col=0 ;col < columnsNumber;col++) {
                Cell newpath = desRow1.createCell(col);
                newpath.setCellValue(rsmd.getColumnLabel(col+1));
            }
            while(rs.next()) {
                System.out.println("Row number" + rs.getRow() );
                Row desRow = desSheet.createRow(rs.getRow());
                for(int col=0 ;col < columnsNumber;col++) {
                    Cell newpath = desRow.createCell(col);
                    newpath.setCellValue(rs.getString(col+1));  
                }
                FileOutputStream fileOut = new FileOutputStream("C:\\Users\\msi\\Documents\\pidev\\test2.xls");
                writeWorkbook.write(fileOut);
                fileOut.close();
            }
        }
        catch (SQLException e) {
            System.out.println("Failed to get data from database");
        }
    }

   
     
}
