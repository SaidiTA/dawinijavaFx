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
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import services.specialitesService;

/**
 * FXML Controller class
 *
 * @author jlidi
 */
public class itemspecialiteController implements Initializable {
     private double x, y;
 private Specialites specialite;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnModify;
    @FXML
    private Label btnnom;
    @FXML
    private Label btndescription;
    @FXML
    private ImageView btnmodifier;
    @FXML
    private Label btnid;
    
 public void setData(Specialites specialite){
        int id=specialite.getId();
        btnid.setText(Integer.toString(id));
                btnnom.setText(specialite.getNom());

                        btndescription.setText(specialite.getDescription());

        
        
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

     @FXML
    void handleButtonClick(ActionEvent event) {
        if(event.getSource()== btnModify){

      showModifierDialog("MofdifierMedecin");
      int id = specialite.getId();
        btnid.setText(Integer.toString(id));
        
        // Récupération des données saisies
        String nom = btnnom.getText();
        String description = btndescription.getText();

        // Modification de la spécialité correspondante dans la base de données
        specialitesService Crud = new specialitesService();
        Crud.modifier_spec(specialite, id, nom, description);
        
        // Fermeture de la fenêtre
      
        }
        // Récupération des données du dossier sélectionné
       
        
     if(event.getSource()== btnDelete){
      showDialog("delete");
      }
      
    }
     private void showModifierDialog(String fxml)
    {
       try {
           Parent parent = FXMLLoader.load(getClass().getResource("/gui/ModifierSpecialites.fxml"));
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
  
     private void showDialog(String fxml)
    {
       try {
           Parent parent = FXMLLoader.load(getClass().getResource("/gui/deleteSpecialites.fxml"));
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
