/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Specialites;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;
import services.specialitesService;

/**
 * FXML Controller class
 *
 * @author jlidi
 */
public class AddSpecialitesController implements Initializable {

    @FXML
    private Button btnAjout;
    @FXML
    private Button btncrosse;
    @FXML
    private ImageView btncross;
    @FXML
    private TextArea btnnom;
    @FXML
    private HTMLEditor btndescription;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
   
    @FXML
    private void buttonOnAction(ActionEvent event) {
        
    String nom = btnnom.getText();
    String description = btndescription.getHtmlText();

    //String specialite = Specialite.getText();
   
      
    
    
    
    Specialites specialite = new Specialites(nom, description);
    specialitesService Service = new specialitesService();
        Service.ajouter(specialite);
         Stage stage = (Stage) btnnom.getScene().getWindow();
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

    @FXML
    private void handleButtonAction(MouseEvent event) {
    }
    
    }
    
