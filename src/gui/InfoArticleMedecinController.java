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

/**
 * FXML Controller class
 *
 * @author Islem
 */
public class InfoArticleMedecinController implements Initializable {

    @FXML
    private Button btncrosse;
    @FXML
    private ImageView btncross;
    @FXML
    private Text ID;
    @FXML
    private Text MED;
    @FXML
    private Text SPEC;
    @FXML
    private Text nomArt;
    @FXML
    private Text DATE;
    @FXML
    private Text DESC;

    private Article article;

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
         ID.setText(String.valueOf(article.getId()));
     this.article = article;
    MED.setText(article.getMedecin().getNom() + " " + article.getMedecin().getPrenom());
    nomArt.setText(article.getNom());
    DESC.setText(article.getDescription());
    DATE.setText(article.getDate().toString()); // assuming date is a java.util.Date object
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleButtonAction(MouseEvent event) {
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
    }
    
}
