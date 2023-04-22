/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import entities.Assistant;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import services.AssistantService;


/**
 * FXML Controller class
 *
 * @author soumayaab
 */
public class ListAssistantController implements Initializable {
private double x, y;
    @FXML
    private Label btnid;
    @FXML
    private Label Nom;
    @FXML
    private Label Prenom;
    @FXML
    private Label Email;
    @FXML
    private Label Telephone;
    @FXML
    private Label genre;
    @FXML
    private Label gouvernorat;
    @FXML
    private Label Adresse;
    @FXML
    private Label Action;
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
    @FXML
    private Label ListeMedecin;
    @FXML
    private Label MEDECIN;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       AssistantService assistantService = new AssistantService();
         List<Assistant> assistants = null;

    try {
        assistants = assistantService.recuperer();
    } catch (SQLException ex) {
        System.out.println(ex);
    }
          
      



    for (Assistant assistant : assistants) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/itemAssis.fxml"));
            Node node = loader.load();
            ItemAssController itemController = loader.getController();
            itemController.setData(assistant);
            pnitems.getChildren().add(node);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    }
@FXML
    void handleButtonClick(ActionEvent event) {
      if(event.getSource()== btnAdd){
      showAsDialog("AddAssistant");
      }
      if(event.getSource()== btnAddplus1){
      showAsDialog("AddAssistant");
      }
     
    }
      private void showAsDialog(String fxml)
    {
       try {
           Parent parent = FXMLLoader.load(getClass().getResource("/gui/AddAssistant.fxml"));
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
    



   
    

  @FXML
    private void refreshTable() {
    pnitems.getChildren().clear();
    AssistantService assistantService = new AssistantService();
    List<Assistant> assistants = null;

    try {
        assistants = assistantService.recuperer();
    } catch (SQLException ex) {
        System.out.println(ex);
    }

    for (Assistant assistant : assistants) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/itemAssis.fxml"));
            Node node = loader.load();
            ItemAssController itemAssController = loader.getController();
            itemAssController.setData(assistant);
            pnitems.getChildren().add(node);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    }

    
}
