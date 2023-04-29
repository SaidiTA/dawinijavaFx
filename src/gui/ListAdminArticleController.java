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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javax.mail.internet.ParseException;
import services.ArticleService;

/**
 * FXML Controller class
 *
 * @author Islem
 */
public class ListAdminArticleController implements Initializable {

    private double x, y;
    @FXML
    private Label btnid;
    @FXML
    private Label Nom;
    @FXML
    private Label Action;
    @FXML
    private VBox pnitems = null;
    @FXML
    private Button btnRef1;
    @FXML
    private ImageView btnRef;
    @FXML
    private Label ListeArticle;
    @FXML
    private Label Description;
    @FXML
    private Label Date;
    @FXML
    private Button btnSearch;
    @FXML
    private TextField searchValue;

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
        for (Article art : articles) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/itemArticleAdmin.fxml"));
                Node node = loader.load();
                ItemArticleAdminController itemController = loader.getController();
                itemController.setData(art);
                pnitems.getChildren().add(node);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @FXML
    private void refreshTable(ActionEvent event) {
    }

    @FXML
    private void refreshTable(MouseEvent event) {
    }

   @FXML
    private void search(ActionEvent event) {
        pnitems.getChildren().clear();
        
        ArticleService articleService = new ArticleService();
        List<Article> articles = null;
        
        try{
            
        articles = articleService.search(searchValue.getText());
        }catch(SQLException e){
        
            System.out.println("exception "+ e);
        }
        for(Article article:articles){
                    try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/itemArticleAdmin.fxml"));
                Node node = loader.load();
                ItemArticleAdminController itemController = loader.getController();
                itemController.setData(article);
                pnitems.getChildren().add(node);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
   }

}
