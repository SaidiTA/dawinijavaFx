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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jlidi
 */
public class DeleteSpecialitesController implements Initializable {

    @FXML
    private Button btnSupprimer;
    @FXML
    private Button btnAnnuler;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

     @FXML
    private void handleButtonAction(ActionEvent event) {
        if(event.getSource() == btnAnnuler) {
        // Get the Stage that contains the button
        Stage stage = (Stage) btnAnnuler.getScene().getWindow();
        // Close the stage
        stage.close();
      
    }
    }
}
