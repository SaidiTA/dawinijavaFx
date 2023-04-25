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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import services.ArticleService;

/**
 * FXML Controller class
 *
 * @author Islem
 */
public class ItemArticleMedecinController implements Initializable {

    private double x, y;
 private Article articles;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnModify;
    
    @FXML
    private Button btnshow;
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
    
    
 public void setData(Article articles){
        int id=articles.getId();
        btnid.setText(Integer.toString(id));
                lblName.setText(articles.getNom());

                        lbldescription.setText(articles.getDescription());

        
        
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

 

    private void sup_spec(ActionEvent event) {
         /*      try {
            Parent parent = FXMLLoader.load(getClass().getResource("SupprimerDossier.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AjouterDossierController.class.getName()).log(Level.SEVERE, null, ex);
        } */
         
        int id = Integer.parseInt(btnid.getText());
          ArticleService dc = new ArticleService();
            List<Article> articles = dc.listArticle();
                 for (Article d : articles) {
                     if (d.getId() == id) {
                     dc.supprimer(d);
                     
                 break;
                 
        }
    }
    
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

    @FXML
    private void Modifier(ActionEvent event) {
    try {
        // Récupération des données du dossier sélectionné
        int id = Integer.parseInt(btnid.getText());
        ArticleService dc = new ArticleService();
        List<Article> spec = dc.listArticle();
        Article articles = null;
        for (Article d : spec) {
            if (d.getId() == id) {
                articles = d;
                break;
            }
        }
        
        // Chargement de la fenêtre de modification
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/ModifierArticle.fxml"));
        Parent parent = fxmlLoader.load();
        ModifierArticleController controller = fxmlLoader.getController();

        // Transmission des données du dossier à la fenêtre de modification
        controller.setData(articles);

        // Affichage de la fenêtre de modification
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.show();
       
    } catch (IOException ex) {
        Logger.getLogger(ItemArticleMedecinController.class.getName()).log(Level.SEVERE, null, ex);
    } 
}

    @FXML
    private void supprimer(ActionEvent event) throws SQLException {  
        int id = Integer.parseInt(btnid.getText());
    
        ArticleService dc = new ArticleService();
        List<Article> articles = dc.recuperer();
        for (Article d : articles) {
            if (d.getId() == id) {
                dc.supprimer(d);

                break;

            }
            
        } Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Suppression réussie");
            alert.setHeaderText(null);
            alert.setContentText("L'article a été supprimé avec succès !");
            alert.showAndWait();
    }}