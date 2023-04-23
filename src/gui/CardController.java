/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Article;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import services.ArticleService;

/**
 * FXML Controller class
 *
 * @author Islem
 */
public class CardController implements Initializable {

    @FXML
    private VBox post;
    @FXML
    private ImageView img;
    @FXML
    private Label MED;
    @FXML
    private Label DATE;
    @FXML
    private TextField Nom;
    @FXML
    private TextArea desc;
    @FXML
    private TextField ID;

    private Article article;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
public void setData(Article article){
        int id=article.getId();
        
        ID.setText(Integer.toString(id));
        MED.setText(article.getMedecin().getNom() + " " + article.getMedecin().getPrenom());
                Nom.setText(article.getNom());

                        desc.setText(article.getDescription());

        
        
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    @FXML
    private void comment(ActionEvent event) {
        int id = Integer.parseInt(ID.getText());
        ArticleService a = new ArticleService();
        try {
            Article article = a.recupererById(id);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/detailleArticle.fxml"));
        Parent root = loader.load();
        DetailleArticleController controller = loader.getController();
        controller.setArticle(article);
        Scene scene = new Scene(root);
       
        
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    }


  
   


    
   
}
