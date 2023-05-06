/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.ReplaySujet;
import entities.Sujet;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import services.ReplaySujetService;
import services.sujetService;

/**
 * FXML Controller class
 *
 * @author jlidi
 */
public class ReponseController implements Initializable {

    @FXML
    private VBox pnitems;
    @FXML
    private Button btnRef111;
    @FXML
    private ImageView btnRef11;
    @FXML
    private Button btnAjouter;
private double x, y;
    @FXML
    private Hyperlink btnnom;
    @FXML
    private Label btnid;

   
    
    private Sujet sujet;
    @FXML
    private Button btnback;

    public Sujet getSujet() {
        return sujet;
    }

    public void setSujet(Sujet sujet) {
        this.sujet = sujet;
    }


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        // Parcourez chaque spécialité et créez un élément FXML pour chaque spécialité
       
       
         }   
 @FXML
    private void refreshTable() {
    pnitems.getChildren().clear();
     ReplaySujetService Service = new ReplaySujetService();
    List<ReplaySujet> reponse = null;

    reponse = Service.listerReponse();

    for (ReplaySujet rep : reponse) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/ItemReponse.fxml"));
            Node node = loader.load();
            ItemReponseController ItemReponseController = loader.getController();
            ItemReponseController.setData(rep);
            pnitems.getChildren().add(node);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }

    @FXML
    private void handleButtonClick(ActionEvent event) {
         if(event.getSource()== btnAjouter){
      showAsDialog("AddReponse");
      }
     
    }

    private void showAsDialog(String fxml) {
        try {
           Parent parent = FXMLLoader.load(getClass().getResource("/gui/AddReponse.fxml"));
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
public void setReplay(Sujet sujet) {
    
        btnid.setText(String.valueOf(sujet.getId()));
        
        this.sujet = sujet;
        btnnom.setText(sujet.getTitle());
        ReplaySujetService sujetService = new ReplaySujetService();
        List<ReplaySujet> replay=null;
        System.out.println("replay "+replay);
        replay =sujetService.listreponse(sujet);
        System.out.println("replay "+replay);
 
          for (ReplaySujet Suje : replay) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/ItemReponse.fxml"));
                Node node = loader.load();
                ItemReponseController ItemsujetController = loader.getController();
                ItemsujetController.setData(Suje);
                pnitems.getChildren().add(node);
            } catch (IOException e) {
                e.printStackTrace();
            }
         }    
    }

    @FXML
    private void retour(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/sujet.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}    
    

