/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.ReplaySujet;
import entities.Specialites;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;
import services.ReplaySujetService;
import services.specialitesService;

/**
 * FXML Controller class
 *
 * @author jlidi
 */
public class AddReponseController implements Initializable {

    @FXML
    private Button btnAjout;
    @FXML
    private Button btncrosse;
    @FXML
    private ImageView btncross;
    @FXML
    private HTMLEditor btnMessage;

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
       if(event.getSource() == btncross) {
        // Get the Stage that contains the button
        Stage stage = (Stage) btncross.getScene().getWindow();
        
        // Close the stage
        stage.close();
      
    }
    }

    @FXML
    private void buttonOnAction(ActionEvent event) {
    String message = btnMessage.getHtmlText().trim();

    if (message.isEmpty()) {
        // Show an error message to the user
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir tous les champs avant de continuer.");
        alert.showAndWait();
    } else if (countWords(message) > 200) {
        // Show an error message to the user
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Le message ne doit pas dépasser 200 mots.");
        alert.showAndWait();
    } else {
        // Create the new Article object and add it to the database
        ReplaySujet reponse = new ReplaySujet( message);
        ReplaySujetService service = new ReplaySujetService();
        service.ajouter(reponse);

        // Close the stage
        Stage stage = (Stage) btnMessage.getScene().getWindow();
        stage.close();
    }
}

private int countWords(String text) {
    // Split the text into words using whitespace as the delimiter
    String[] words = text.split("\\s+");

    // Return the number of words
    return words.length;
}    
    }

  
    
    

