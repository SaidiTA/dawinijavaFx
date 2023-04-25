/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.ReplaySujet;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;
import services.ReplaySujetService;

/**
 * FXML Controller class
 *
 * @author jlidi
 */
public class ModfierReponseController implements Initializable {

    @FXML
    private Button btnModifier;
    @FXML
    private Button btncrosse;
    @FXML
    private ImageView btncross;
    @FXML
    private HTMLEditor btnMessage;
             private ReplaySujet ReplaySujet;

public void setData(ReplaySujet replaysujet){
       this.ReplaySujet = replaysujet;

        btnMessage.setHtmlText(replaysujet.getMessage()); 

    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void buttonOnAction(ActionEvent event) {
         String message = btnMessage.getHtmlText();

    
    // Modifier le dossier correspondant dans la base de données
    ReplaySujetService reponseCrud = new ReplaySujetService();
    reponseCrud.modifier_reponse(ReplaySujet ,message);
    
    // Fermer la fenêtre de modification
    Stage stage = (Stage) btnMessage.getScene().getWindow();
    stage.close();
    
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
    }
    

