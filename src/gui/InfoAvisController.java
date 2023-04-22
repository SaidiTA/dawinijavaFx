/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import entities.Avis;
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
public class InfoAvisController implements Initializable {

    @FXML
    private Button btncrosse;
    @FXML
    private ImageView btncross;
    @FXML
    private Text ID;
    @FXML
    private Text med;
    @FXML
    private Text Note;
    @FXML
    private Text DATE;
    @FXML
    private Text pat;
    @FXML
    private Text FORMATION;
    @FXML
    private Text comment;
    private Avis avis;
    @FXML
    private Text statut;
    public Avis getAvis() {
        return avis;
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

    
        void setAvis(Avis avis) {
    ID.setText(String.valueOf(avis.getId()));
     this.avis = avis;
    med.setText(avis.getMedecin().getNom() + " " + avis.getMedecin().getPrenom());
    Note.setText(String.valueOf(avis.getNote()));
    DATE.setText(avis.getDate().toString()); // assuming date is a java.util.Date object
    pat.setText(avis.getPatient().getNom() + " " + avis.getPatient().getPrenom());
    FORMATION.setText(avis.getMedecin().getDiplome_formation());
    comment.setText(avis.getText());
    statut.setText(avis.getStatut());
}

    @FXML
    private void handleButtonAction(MouseEvent event) {
    }
    }
    

