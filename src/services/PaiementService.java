/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
//import static com.itextpdf.text.Image.getInstance;
//import com.itextpdf.text.Paragraph;
//import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Statement;
import java.util.List;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.MyDB;

//import images.logodawini.PNG;
import entities.Rendezvous;
import entities.paiement;
import java.io.File;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
//import javafx.scene.text.Font;
import javax.swing.filechooser.FileSystemView;

/**
 *
 * @author HP
 */
public class PaiementService {
    
    Connection cnx;

    public PaiementService() {
        cnx = MyDB.getInstance().getCnx();
    }
    
    
    //@Override
    public void ajouter(paiement t) throws SQLException {
        String req = "insert into paiement(id,rendez_vous_id,etat,tarif,num_carte,expiration_carte,nom_carte,email) values('"   + t.getId() + "','" + t.getRendez_vous_id() + "','" + t.getEtat() + "','" + t.getTarif()  + "','" + t.getNum_carte() + "','" + t.getExpiration_carte() + "','" + t.getNom_carte() + "','" + t.getEmail() +           " ')";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);

    }
    
    
    //@Override
    public ObservableList<paiement> getAllPaiement() {
       ObservableList<paiement> list = FXCollections.observableArrayList();
        try {
            String req = "Select * from paiement";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                paiement p;
                p = new paiement(rs.getInt(1),rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getDate(6),rs.getString(7),rs.getString(8));
                //Rendezvous t = new Rendezvous(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
                list.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
        
    }
    public boolean supprimerPP(int id) throws SQLException {
        boolean res = false;
        try {
            String req = "DELETE FROM `paiement` WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("paiement Deleted !");
            res=true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return res;
    }
    
    public paiement  getOneByIdP(int id) {
      paiement p = null;
        try {
            String req = "Select * from paiement where id=" + id;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                if (rs.getInt(1) == id) {
                  
                p = new paiement(rs.getInt(1),rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getDate(6),rs.getString(7),rs.getString(8));
                
                }

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return p;
 
    }
    
    
   /* @Override
    public void create(paiement t) throws SQLException {

        String sql = "INSERT INTO paiement (id,rendez_vous_id,etat,tarif,num_carte,expiration_carte,nom_carte,email) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (
                PreparedStatement pstmt = cnx.prepareStatement(sql)) {
            pstmt.setInt(1, t.getId());
             pstmt.setInt(1, t.getRendez_vous_id());
            pstmt.setString(2, t.getEtat());
            
           //pstmt.setString(4,t.getDate());
            pstmt.setInt(5, t.getTarif());
             pstmt.setInt(1, t.getNum_carte());
             pstmt.setString(4,t.getExpiration_carte()());
            pstmt.executeUpdate();
            System.out.println("stock ajouté");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }*/
    
    
    
    
    public void exporterPDF(paiement paiement) {
       try {
           //  this lines to get desktop path
           FileSystemView view = FileSystemView.getFileSystemView();
            File homeDirectory = view.getHomeDirectory();
           String directoryPath = homeDirectory.getPath() + "/DawiniPaiement";

 //create the directory if it doesn't exist
            File directory = new File(directoryPath);
           if (!directory.exists()) {
                directory.mkdirs();
            }

          //   this line to prepare the generated file path
            String file_name = directoryPath + "/paiement" + paiement.getId() + ".pdf";

          //   creating the file 
            Document document = new Document();
           //  generating the file
            PdfWriter.getInstance(document, new FileOutputStream(file_name));
         //    start editing file
            document.open();

            PdfPTable titleTable = new PdfPTable(new float[]{2, 4});

 //create a cell for the title
 PdfPCell titleCell = new PdfPCell(new Phrase("Recu N° " + paiement.getId() , new Font(Font.FontFamily.TIMES_ROMAN, 30, Font.BOLD)));
            //PdfPCell titleCell = new PdfPCell(new Phrase("Recu N° " + paiement.getId(), new Font(Font.FontFamily.TIMES_ROMAN, 30, Font.BOLD)));
            titleCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            titleCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            titleCell.setBorder(Rectangle.NO_BORDER);
            titleCell.setPadding(0); // set padding to 0

            titleTable.addCell(titleCell);

 //create a cell for the image
           
          // Image m = Image.getInstance("C:\Users\HP\Downloads\PIDEV3A32\PIDEV3A32\src\images\logodawini.PNG");
          Image m = Image.getInstance("C:/Users/HP/Downloads/PIDEV3A32/PIDEV3A32/src/images/logodawini.PNG");

            m.scaleAbsolute(100f, 50f);
            PdfPCell imageCell = new PdfPCell(m);
            imageCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            imageCell.setBorder(Rectangle.NO_BORDER);
            titleTable.addCell(imageCell);
//
            document.add(titleTable);

           //  create space between table and title
           Paragraph para2 = new Paragraph("\n");
            document.add(para2);

            Paragraph para3 = new Paragraph("Platforme : Dawini ");
             //Paragraph para3 = new Paragraph("Platforme :  "+paiement.getEtat());
            document.add(para3);
            Paragraph para4 = new Paragraph("Adresse postale :  1, 2 rue André Ampère - 2083 - Pôle Technologique - El Ghazala");
            document.add(para4);

            Paragraph para5 = new Paragraph("Adresse mail :  Dawiniappesprit@gmail.com");
            document.add(para5);

            Paragraph para6 = new Paragraph("Téléphone :  +216 00 000 000");
            document.add(para6);
            Paragraph paraa = new Paragraph("\n id de paiement est    "+ paiement.getId() +"\n le rendezvous  est    "+ paiement.getRendez_vous_id()+"\n Tarif est    "+ paiement.getTarif()+"\n email est    "+ paiement.getEmail()+ "\n date d'expiraxion carte est    "+ paiement.getExpiration_carte());
            document.add(paraa);

             //get the list of elements to display
              ObservableList<paiement> list = getAllPaiement();
            // create table of 4 columns
           PdfPTable table = new PdfPTable(4);
             //column 1
            PdfPCell c0 = new PdfPCell(new Phrase("Id paiement"));
            table.addCell(c0);
            PdfPCell cl = new PdfPCell(new Phrase("rendezvous id"));
            table.addCell(cl);
            // column 2
           PdfPCell cl1 = new PdfPCell(new Phrase("Etat"));
            table.addCell(cl1);
           //  column 3
            PdfPCell cl11 = new PdfPCell(new Phrase("tarif"));
            table.addCell(cl11);

            // set titles of the columns
            table.setHeaderRows(1);

            /*    // filling the table 
            for (int i = 0; i < reclamationsList.size(); i++) {
                

            }*/
            
             
         //   table.addCell(new ServiceUser().getUserFullNameById(facture.getId_prop()));
          //  table.addCell(new ServiceUser().getUserFullNameById(facture.getId_client()));
         //   table.addCell(new ServiceProduit().getNameById(facture.getId_produit()));
          //  table.addCell(facture.getMontant() + " DT");
          //   add the table to the file
            document.add(table);
          //  Paragraph para9 = new Paragraph("Total : " + facture.getMontant() + " DT");
          //  para9.setAlignment(Element.ALIGN_RIGHT);
          //  document.add(para9);
            Paragraph para2z = new Paragraph("\n");
            document.add(para2z);
           Paragraph para10 = new Paragraph("Signature");
            para10.setAlignment(Element.ALIGN_RIGHT);
//
            document.add(para10);
          //   save the file
            document.close();
           //  open the file
            Desktop.getDesktop().open(new File(file_name));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
}
