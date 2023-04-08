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
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author soumayaab
 */
public class AddMedecinController implements Initializable {
    
     @FXML
    private Button btncrosse;

    @FXML
    private ImageView btncross;
    @FXML
    private MenuButton Genre;
    @FXML
    private TextArea Adresse_Cabinet;
    @FXML
    private MenuButton Cnam;
    @FXML
    private MenuButton Titre;
    @FXML
    private MenuButton Specialite;
    @FXML
    private TextArea Adresse;
    @FXML
    private TextArea Diplome_Formation;
    @FXML
    private MenuButton Gouvernorat;
    @FXML
    private TextArea Nom;
    @FXML
    private TextArea Prenom;
    @FXML
    private TextArea Telephone;
    @FXML
    private TextArea Email;
    @FXML
    private TextArea Fixe;
    @FXML
    private TextArea Tarif;
    @FXML
    private TextArea Cin;
    
    
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

  
    
}
