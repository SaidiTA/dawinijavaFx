/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import entities.Assistant;
import entities.Medecin;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.MedecinService;

/**
 * FXML Controller class
 *
 * @author soumayaab
 */
public class InfoAssisController implements Initializable {

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
    private Text GOUV;
    @FXML
    private Text CIN;
    @FXML
    private Text MED;
    @FXML
    private Text ADRESSE;
    @FXML
    private Text FORMATION;
 private Assistant assistant;

    public Assistant getAssistant() {
        return assistant;
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

    void setAssistant(Assistant assistant) throws SQLException {
         ID.setText(String.valueOf(assistant.getId()));
        this.assistant = assistant;
         NOM.setText(assistant.getNom());
        PRENOM.setText(assistant.getPrenom());
        GOUV.setText(assistant.getGouvernorat());
        EMAIL.setText(assistant.getEmail());
       
        TEL.setText(assistant.getTelephone());
      CIN.setText(String.valueOf(assistant.getCin()));
        GENRE.setText(assistant.getSexe());
        ADRESSE.setText(assistant.getAdresse());
         Medecin medecin = assistant.getMedecin();
    if (medecin != null) {
        MED.setText(medecin.getNom() + " " + medecin.getPrenom());
    } else {
        MED.setText("Aucun médecin associé.");
    }}
    }

    
    
    

