/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package gui;

import entities.Assistant;
import entities.Medecin;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author soumayaab
 */
public class InfoMedecinController implements Initializable {
    
     @FXML
    private Button btncrosse;

    @FXML
    private ImageView btncross;
    @FXML
    private Text ID;
    @FXML
    private Text NOM;
    @FXML
    private Text PRENOM;
    @FXML
    private Text EMAIL;
    @FXML
    private Text GENRE;
    @FXML
    private Text TEL;
    @FXML
    private Text FIXE;
    @FXML
    private Text CIN;
    @FXML
    private Text TARIF;
    @FXML
    private Text GOUV;
    @FXML
    private Text TITRE;
    @FXML
    private Text SPEC;
    @FXML
    private Text CNAM;
    @FXML
    private Text ADRESSE;
    @FXML
    private Text CABINET;
    @FXML
    private Text FORMATION;
    private Medecin medecin;

    public Medecin getMedecin() {
        return medecin;
    }

    public void setMedecin(Medecin medecin) {
         ID.setText(String.valueOf(medecin.getId()));
        this.medecin = medecin;
         NOM.setText(medecin.getNom());
        PRENOM.setText(medecin.getPrenom());
        GOUV.setText(medecin.getGouvernorat());
        EMAIL.setText(medecin.getEmail());
        CABINET.setText(medecin.getAdresse_cabinet());
        TEL.setText(medecin.getTelephone());
        TITRE.setText(medecin.getTitre());
        GENRE.setText(medecin.getSexe());
        ADRESSE.setText(medecin.getAdresse());
        FIXE.setText(medecin.getFixe());
       CIN.setText(String.valueOf(medecin.getCin()));
     
       CNAM.setText(String.valueOf(medecin.isCnam()));
      TARIF.setText(String.valueOf(medecin.getTarif()));
        
        FORMATION.setText(medecin.getDiplome_formation());
      
       
    }
    @FXML
private void handleButtonAction(ActionEvent event) {
    if(event.getSource() == btncrosse) {
        // Get the Stage that contains the button
        Stage stage = (Stage) btncrosse.getScene().getWindow();
        // Close the stage
        stage.close();
      
    }
}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

   
    

   
    
    

    
}
