/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import entities.Patient;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author soumayaab
 */
public class InfoPatController implements Initializable {

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
   
    private Text CIN;
    
    @FXML
    private Text GOUV;
  
    @FXML
    private Text ADRESSE;

    private Patient patient;

    public Patient getPatient() {
        return patient;
    }
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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

    void setPatient(Patient patient) {
         ID.setText(String.valueOf(patient.getId()));
        this.patient = patient;
         NOM.setText(patient.getNom());
        PRENOM.setText(patient.getPrenom());
        GOUV.setText(patient.getGouvernorat());
        EMAIL.setText(patient.getEmail());
       
        TEL.setText(patient.getTelephone());
     
        GENRE.setText(patient.getSexe());
        ADRESSE.setText(patient.getAdresse());
       
       CIN.setText(String.valueOf(patient.getCin()));
     
     
      
    }
    
}
