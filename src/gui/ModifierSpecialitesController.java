/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Specialites;
import java.net.URL;
import java.util.List;
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
public class ModifierSpecialitesController implements Initializable {

    @FXML
    private TextArea btnnom;
    @FXML
    private Button btnAjout;
    @FXML
    private Button btncrosse;
    @FXML
    private ImageView btncross;
    @FXML
    private HTMLEditor btndescription;
    private Specialites specialite;

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
public void setData(Specialites specialite){
        
        this.specialite = specialite;
        btnnom.setText(specialite.getNom());
        btndescription.setHtmlText(specialite.getDescription());
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
