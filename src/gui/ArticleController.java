/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
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
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import services.ArticleService;

/**
 * FXML Controller class
 *
 * @author soumayaab
 */
public class ArticleController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Label ListeMedecin;
    @FXML
    private GridPane postGrid;
    @FXML
    private Button btnArt;
    @FXML
    private Button btnRef1;
    @FXML
    private ImageView btnRef;

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
            Logger.getLogger(ArticleController.class.getName()).log(Level.SEVERE, null, ex);
        }

        int row = 1;
        int column = 0;
        for (Article article : articles) {
            try {
                System.out.println("article "+ article);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/card.fxml"));
                VBox node = loader.load();
                CardController itemController = loader.getController();
                itemController.setData(article);
                // add the node to the grid pane
                if (column == 3) {
                    column = 0;
                    ++row;
                }

                postGrid.add(node, column++, row);

                GridPane.setMargin(node, new Insets(5));

                // increment the row and column counters
            } // TODO
            catch (IOException ex) {
                Logger.getLogger(ArticleController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @FXML
    private void MED(ActionEvent event) {
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
       
        
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    }

    @FXML
    private void ARTICLES(ActionEvent event) {
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

    @FXML
    private void handleLogoutButtonAction(ActionEvent event) {
    }

}
