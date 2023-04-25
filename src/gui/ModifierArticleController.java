/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Article;
import java.net.URL;
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
    }}