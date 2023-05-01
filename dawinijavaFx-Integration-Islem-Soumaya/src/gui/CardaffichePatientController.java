/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class CardaffichePatientController implements Initializable {
     private int id;
    private User patient;
   

    @FXML
    private Label nom_patient;
    @FXML
    private Label prenom_patient;
    @FXML
    private Label email_patient;
    @FXML
    private Button afficherPatient;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return patient;
    }

    public void setUser(User user) {
        this.patient = user;
    }

    public Label getNom_patient() {
        return nom_patient;
    }

    public void setNom_patient(Label nom_patient) {
        this.nom_patient = nom_patient;
    }

    public Label getPrenom_patient() {
        return prenom_patient;
    }

    public void setPrenom_patient(Label prenom_patient) {
        this.prenom_patient = prenom_patient;
    }

    public Label getEmail_patient() {
        return email_patient;
    }

    public void setEmail_patient(Label email_patient) {
        this.email_patient = email_patient;
    }

    public Button getAfficherPatient() {
        return afficherPatient;
    }

    public void setAfficherPatient(Button afficherPatient) {
        this.afficherPatient = afficherPatient;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    
    
    
     public void setData(User patient) {
        
        this.patient = patient;
        
        this.id =patient.getId();
         
        nom_patient.setText(String.valueOf(patient.getNom()));
        prenom_patient.setText(String.valueOf(patient.getPrenom()));
        email_patient.setText(String.valueOf(patient.getEmail()));
       
    }

    @FXML
    private void gotoconsult(ActionEvent event) throws IOException {
          
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Dash.fxml"));
            
               
        Parent root;
        root = loader.load();
          
            // Récupération du contrôleur de la page de modification de consultation
DashController dash = loader.getController();
         
    // Initialisation des champs de la page de modification de consultation avec les données de la consultation actuelle
       
      dash.initialize(id);
            // Changement de la scène pour afficher la page de modification de consultation
        
        afficherPatient.getScene().setRoot(root);
    }
    
}
