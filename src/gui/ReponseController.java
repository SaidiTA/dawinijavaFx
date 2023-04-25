/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.ReplaySujet;
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
import javafx.scene.image.ImageView;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       List<ReplaySujet> reponse = getReponseList();

        // Parcourez chaque spécialité et créez un élément FXML pour chaque spécialité
        for (ReplaySujet rep : reponse) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/ItemReponse.fxml"));
                Node node = loader.load();
                ItemReponseController reponseController = loader.getController();
                reponseController.setData(rep);
                pnitems.getChildren().add(node);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }    
     private List<ReplaySujet> getReponseList() {
        ReplaySujetService ps=new ReplaySujetService();
        List<ReplaySujet> reponse = ps.listerReponse();
        return reponse;
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

    
}    
    

