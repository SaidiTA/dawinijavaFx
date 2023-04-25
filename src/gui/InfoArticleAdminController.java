/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Article;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Islem
 */
public class InfoArticleAdminController implements Initializable {

    @FXML
    private Button btncrosse;
    @FXML
    private ImageView btncross;
    @FXML
    private Text ID;
    @FXML
    private Text med;
    @FXML
    private Text spec;
    @FXML
    private Text nom;
    @FXML
    private Text description;
   private Article article;

   
    @FXML
    private Text date;
  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
 public Article getArticle() {
        return article;
    }
    void setArticle(Article article) {
       ID.setText(String.valueOf(article.getId()));
     this.article = article;
    med.setText(article.getMedecin().getNom() + " " + article.getMedecin().getPrenom());
    nom.setText(article.getNom());
    description.setText(article.getDescription());
    date.setText(article.getDate().toString()); // assuming date is a java.util.Date object
  
  
    }

  
    @FXML
    private void handleButtonAction(ActionEvent event) {
        
         if(event.getSource() == btncrosse) {
        // Get the Stage that contains the button
        Stage stage = (Stage) btncrosse.getScene().getWindow();
        // Close the stage
        stage.close();
      
    }
    }
    }
    

