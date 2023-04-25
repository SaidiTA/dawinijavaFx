/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Article;
import entities.Commentaire;
import entities.Images;
import entities.User;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
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
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.detailleArticleService;
import test.Dawinii;

/**
 * FXML Controller class
 *
 * @author Islem
 */
public class DetailleArticleController implements Initializable {

    @FXML
    private TextArea commentTextArea;
    @FXML
    private Button submitButton;
    @FXML
    private ImageView imgarticle;
    @FXML
    private Text ID;
    @FXML
    private Label narticle;
    @FXML
    private Label descarticle;
    @FXML
    private Button unlike;
    @FXML
    private Button like;
    @FXML
    private Button retour;
    
    @FXML
    private VBox allComments;

     private Article article;
     private User user;

    /**
     * Initializes the controller class.
     */
     
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public void setArticle(Article article) {
        ID.setText(String.valueOf(article.getId()));
        this.article = article;
        narticle.setText(article.getNom());
        descarticle.setText(article.getDescription());
        Images imageUrl = article.getImages();
      Image image = new Image(Dawinii.class.getClass().getResource("/images/" + imageUrl.getUrl()).toString());

        imgarticle.setImage(image);
       //imgarticle.setImages(article.getImages());
        detailleArticleService commetaireService = new detailleArticleService();
        List<Commentaire> commentaires=null;
        System.out.println("article "+article);
        commentaires =commetaireService.listCommentaire(article);
        System.out.println("commentaires "+commentaires);

         for (Commentaire comment : commentaires) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("allcomments.fxml"));
                Node node = loader.load();
                AllcommentsController commentaireController = loader.getController();
                commentaireController.setData(comment);
                allComments.getChildren().add(node);
            } catch (IOException e) {
                e.printStackTrace();
            }
         }
    }

    @FXML
   private void submitComment(ActionEvent event) throws SQLException {
        String message = commentTextArea.getText().trim();
        
        if (!message.isEmpty()) {
            detailleArticleService commentaireService = new detailleArticleService();
            User user = new User();
        //bsh tbadel yrecuperi l patient connecte 
           user.setId(1);

            Commentaire commentaire = new Commentaire(message, new Date(), article, user);
            commentaireService.ajouter(commentaire);
            // Clear the text area after submitting the comment
            commentTextArea.clear();
            refreshComments();
        }
   }

    @FXML
    private void btndislike(ActionEvent event) {
    }

    @FXML
    private void btnlike(ActionEvent event) {
    }

    @FXML
    private void retour(ActionEvent event) {
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("article.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
       
        
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    }
    private void refreshComments() {
    // Nettoyer tous les commentaires existants
    allComments.getChildren().clear();

    // Charger et afficher les nouveaux commentaires
    detailleArticleService commetaireService = new detailleArticleService();
    List<Commentaire> commentaires = commetaireService.listCommentaire(article);

    for (Commentaire comment : commentaires) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("allcomments.fxml"));
            Node node = loader.load();
            AllcommentsController commentaireController = loader.getController();
            commentaireController.setData(comment);
            allComments.getChildren().add(node);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
    
}
