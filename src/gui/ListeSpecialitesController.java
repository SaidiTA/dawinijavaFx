/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Specialites;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import services.specialitesService;

/**
 * FXML Controller class
 *
 * @author jlidi
 */
public class ListeSpecialitesController implements Initializable {
private double x, y;

    @FXML
    private Button btnforum;
    @FXML
    private Button btnspecialites;
    @FXML
    private VBox pnitems;
    @FXML
    private Button btnAdd;
    @FXML
    private ImageView btnAddplus1;
  @FXML
    private Button btnRef1;
    @FXML
    private ImageView btnRef;
   
    /**
     * Initializes the controller class.
     */
    @Override
   public void initialize(URL location, ResourceBundle resources) {
     List<Specialites> specialites = getSpecialitesList();

        // Parcourez chaque spécialité et créez un élément FXML pour chaque spécialité
        for (Specialites specialite : specialites) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/itemspecialite.fxml"));
                Node node = loader.load();
                itemspecialiteController specialiteController = loader.getController();
                specialiteController.setData(specialite);
                pnitems.getChildren().add(node);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
              
    @FXML
    private void handleButtonClick(ActionEvent event) {
        if(event.getSource()== btnAdd){
      showAsDialog("AddSpecialites");
      }
      if(event.getSource()== btnAddplus1){
      showAsDialog("AddSpecialites");
      }
      //if(event.getSource() == btncross) {
        // Get the Stage that contains the button
      //  Stage stage = (Stage) btncross.getScene().getWindow();
        // Close the stage
       // stage.close();
    //}
    
    }
 private void showAsDialog(String fxml)
    {
       try {
           Parent parent = FXMLLoader.load(getClass().getResource("/gui/AddSpecialites.fxml"));
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

    private List<Specialites> getSpecialitesList() {
        specialitesService ps=new specialitesService();
        List<Specialites> specialites = ps.listerSpecialites();
        return specialites;
    }

   

    @FXML
    private void refreshTable() {
    pnitems.getChildren().clear();
     specialitesService Service = new specialitesService();
    List<Specialites> specialites = null;

    specialites = Service.listerSpecialites();

    for (Specialites specialite : specialites) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/itemspecialite.fxml"));
            Node node = loader.load();
            itemspecialiteController itemController = loader.getController();
            itemController.setData(specialite);
            pnitems.getChildren().add(node);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }
}

    
    

    

    

   
    
    

