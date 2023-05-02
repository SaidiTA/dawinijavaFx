/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;
import entities.Dossier;
import entities.Diagnostique;
import com.itextpdf.text.Document;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import services.DiagnostiqueCrud;

/**
 *
 * @author msi
 */
public class Pdf {
    
     public void GeneratePdf(String filename, Diagnostique p, int id) throws FileNotFoundException, DocumentException, BadElementException, IOException, InterruptedException, SQLException {

        Document document = new Document() {
        };
        PdfWriter.getInstance(document, new FileOutputStream(filename + ".pdf"));
        document.open();

        DiagnostiqueCrud us = new DiagnostiqueCrud();
        document.add(new Paragraph("            Date  :"+LocalDateTime.now()));
       // document.add(new Paragraph("            le diagnostique de dossier numero :"+d.getNumero()));
        document.add(new Paragraph("                      "));
        document.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------"));

        //document.add(new Paragraph("dossier numero:" + p.getDossiers_id()));
        //document.add(new Paragraph("                      "));
        document.add(new Paragraph("date du diagnostique :" + p.getDate()));
        document.add(new Paragraph("                      "));
        document.add(new Paragraph("symptome :" + p.getSymptome()));
        document.add(new Paragraph("                      "));
        document.add(new Paragraph("resultat du test :" + p.getResultat_test()));
        document.add(new Paragraph("                      "));
        document.add(new Paragraph("diagnostique final:" + p.getDiag_final()));
        document.add(new Paragraph("                      "));
       

        document.add(new Paragraph("---------------------------------------------------------------------------------------------------------------------------------- "));
        document.add(new Paragraph("                                                        Dawini                                                                     "));
        document.close();
        Process pro = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + filename + ".pdf");
    }

}
