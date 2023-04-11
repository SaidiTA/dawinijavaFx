/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
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
private void handleButtonAction(ActionEvent event) {
    if(event.getSource() == btncrosse) {
        // Get the Stage that contains the button
        Stage stage = (Stage) btncrosse.getScene().getWindow();
        // Close the stage
        stage.close();
      
    }
}

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
