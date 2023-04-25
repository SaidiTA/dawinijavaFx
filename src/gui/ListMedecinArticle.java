/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Article;
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
import services.ArticleService;

/**
 * FXML Controller class
 *
 * @author Islem
 */
public class ListMedecinArticle implements Initializable {
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
    private Label Action;
    @FXML
    private VBox pnitems=null;
    @FXML
    private Button btnAdd;
    @FXML
    private ImageView btnAddplus1;
    @FXML
    private Button btnRef1;
    @FXML
    private ImageView btnRef;
    @FXML
    private Label ListeArticle;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          ArticleService articleService = new ArticleService();
         List<Article> articles = null;

    try {
        articles = articleService.recuperer();
    } catch (SQLException ex) {
        System.out.println(ex);
    }
   
    for (Article article : articles) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/itemArticleMedecin.fxml"));
        
            Node node = loader.load();
            ItemArticleMedecinController itemController = loader.getController();
            itemController.setData(article);
            pnitems.getChildren().add(node);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }   

   
    @FXML
    private void handleButtonClick(ActionEvent event) {
   if(event.getSource()== btnAdd){
      showAsDialog("addArticle");
      }
      if(event.getSource()== btnAddplus1){
      showAsDialog("addArticle");
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
           Parent parent = FXMLLoader.load(getClass().getResource("/gui/addArticle.fxml"));
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
    private void refreshTable(ActionEvent event) {
           pnitems.getChildren().clear();
        ArticleService articleService = new ArticleService();
         List<Article> arts = null;

    try {
        arts = articleService.recuperer();
    } catch (SQLException ex) {
        System.out.println(ex);
    }
          
      



    for (Article article : arts) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/itemArticleMedecin.fxml"));
            Node node = loader.load();
            ItemArticleMedecinController itemController = loader.getController();
            itemController.setData(article);
            pnitems.getChildren().add(node);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }

  
}
