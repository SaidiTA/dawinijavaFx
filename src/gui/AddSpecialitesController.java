/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
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

/**
 * FXML Controller class
 *
 * @author jlidi
 */
public class AddSpecialitesController implements Initializable {

    @FXML
    private TextArea nom;
    @FXML
    private Button btnAjout;
    @FXML
    private Button btncrosse;
    @FXML
    private ImageView btncross;
    @FXML
    private HTMLEditor description;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
   
    @FXML
    private void buttonOnAction(ActionEvent event) {
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
    
