/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Article;
import entities.Images;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.ArticleService;

/**
 * FXML Controller class
 *
 * @author Islem
 */
public class ModifierArticleController implements Initializable {

    @FXML
    private TextArea Nom;
    @FXML
    private TextArea Description;
    @FXML
    private Button btnModify;
    @FXML
    private Button btncrosse;
    @FXML
    private ImageView btncross;
private Article article ; 
 private Images image;
    @FXML
    private Button btnupload;

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    void setData(Article article) {
        this.article = article;
        Nom.setText(article.getNom());
        Description.setText(article.getDescription());
    }

    @FXML
    private void updateArticle(ActionEvent event) {
          ArticleService sc = new ArticleService();
    article.setNom(Nom.getText());
    article.setDescription(Description.getText());

    sc.modifier_article(article, article.getNom(), article.getDescription());
    System.out.println("Article modifié avec succès !");

    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Succès");
    alert.setHeaderText("L'article a été modifié avec succès !");
    alert.showAndWait();

    // Fermer la fenêtre de modification d'article
    Stage stage = (Stage) btnModify.getScene().getWindow();
    stage.close();
}


    @FXML
    private void handleButtonAction(MouseEvent event) {
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        
        
     if (event.getSource() == btncrosse) {
            // Get the Stage that contains the button
            Stage stage = (Stage) btncrosse.getScene().getWindow();

            // Close the stage
            stage.close();

        }
        if (event.getSource() == btncross) {
            // Get the Stage that contains the button
            Stage stage = (Stage) btncross.getScene().getWindow();

            // Close the stage
            stage.close();

        
    }
    }

    @FXML
    private void uploadimage(ActionEvent event) {
             FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Choisir une image");

    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.jpeg", "*.png");
    fileChooser.getExtensionFilters().add(extFilter);
    File file = fileChooser.showOpenDialog(btnupload.getScene().getWindow());

    if (file != null) {
        // Display the selected file name
        btnupload.setText(file.getName());

        // Save the selected file to the "images" directory in the project folder
        Path imagePath = Paths.get("images", file.getName());
        Images img = new Images(file.getName(),file.getName());
        this.image = img;
        try {
            Files.copy(file.toPath(), imagePath, StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    }
}