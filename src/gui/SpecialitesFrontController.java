/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Specialites;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import services.specialitesService;

/**
 * FXML Controller class
 *
 * @author jlidi
 */
public class SpecialitesFrontController implements Initializable {
private double x, y;

    @FXML
    private Label nomspecialites;
    @FXML
    private Button btnAdd;
    @FXML
    private ImageView btnAddplus1;
    @FXML
    private Label nomspecialites1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

   
    @FXML
    private void handleButtonClick(ActionEvent event) {
        if(event.getSource()== btnAdd){
      showAsDialog("AddSpecialites");
      }
      if(event.getSource()== btnAddplus1){
      showAsDialog("AddSpecialites");
      }
    }

   private void showAsDialog(String fxml)
    {
       try {
           Parent parent = FXMLLoader.load(getClass().getResource("/gui/AddSujet.fxml"));
           Stage stage = new Stage();
           Scene scene = new Scene(parent);
           stage.setScene(scene);
           stage.initStyle(StageStyle.UNDECORATED);

        //drag it here
        parent.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });
        parent.setOnMouseDragged(event -> {

            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);

        });
           stage.show();
        
        //primaryStage.setTitle("Sign In User");
       
       
           
           
           
           
       } catch (IOException ex) {
           ex.printStackTrace();
       }
    
    }

   
    

    

    
}
