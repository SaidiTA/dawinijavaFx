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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import services.ArticleService;

/**
 * FXML Controller class
 *
 * @author Islem
 */
public class ItemArticleAdminController implements Initializable {

    @FXML
    private Label btnid;
    @FXML
    private Label lblName;
    @FXML
    private Label lbldescription;
    @FXML
    private Label lblDate;
    @FXML
    private HBox lblAction;
    @FXML
    private Button btnshow;
private Article article;

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
         setData(article);
    }

    public ArticleService getArticleService() {
        return articleService;
    }

    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }
private ArticleService articleService;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Affiche(ActionEvent event) {
        
          int id = Integer.parseInt(btnid.getText());
        ArticleService ms = new ArticleService();
        try {
            Article article = ms.recupererById(id);

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/infoArticleAdmin.fxml"));
            Parent parent = fxmlLoader.load();
           InfoArticleAdminController controller = fxmlLoader.getController();
            controller.setArticle(article);

            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();

        } catch (IOException ex) {
            System.out.println("Erreur lors du chargement de la fenêtre d'affichage des informations d'article: " + ex.getMessage());
        }
    }
       

    void setData(Article article) {
       int id = article.getId();
        btnid.setText(String.valueOf(article.getId()));
        lblName.setText(String.valueOf(article.getNom()));
        lbldescription.setText(article.getDescription());
        lblDate.setText(article.getDate().toString());
        
    }
    
}
