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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import services.ArticleService;

/**
 * FXML Controller class
 *
 * @author Islem
 */
public class ArticleController implements Initializable {

    @FXML
    private Label ListeMedecin;
    @FXML
    private GridPane postGrid;

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

}
