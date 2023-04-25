/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Article;
import entities.Medecin;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;
import services.ArticleService;

/**
 * FXML Controller class
 *
 * @author Islem
 */
public class AddArticleController implements Initializable {

  
    @FXML
    private Button btnAjout;
    @FXML
    private Button btncrosse;
    @FXML
    private ImageView btncross;
    @FXML
    private TextArea Nom;
    @FXML
    private TextArea Description;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
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

    @FXML
    private void addArticle(ActionEvent event) {
        
      String nom = Nom.getText().trim();
    String description = Description.getText().trim();

    if (nom.isEmpty() || description.isEmpty()) {
        // Show an error message to the user
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir tous les champs avant de continuer.");
        alert.showAndWait();
    } else if (countWords(description) > 200) {
        // Show an error message to the user
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("La description ne doit pas dépasser 200 mots.");
        alert.showAndWait();
    } else {
        // Create the new Article object and add it to the database
        Article article = new Article(nom, description);
        ArticleService service = new ArticleService();
        service.ajouter(article);
   Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Succès");
    alert.setHeaderText("Le compte a été créé avec succès !");
    alert.showAndWait();

        // Close the stage
        Stage stage = (Stage) btnAjout.getScene().getWindow();
        stage.close();
    }
}

private int countWords(String text) {
    // Split the text into words using whitespace as the delimiter
    String[] words = text.split("\\s+");

    // Return the number of words
    return words.length;
}

    @FXML
    private void handleButtonAction(MouseEvent event) {
    }

    

    }
