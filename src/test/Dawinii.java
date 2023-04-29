/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Islem
 */
public class Dawinii extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
     // Parent root = FXMLLoader.load(getClass().getResource("/gui/article.fxml"));
      Parent root = FXMLLoader.load(getClass().getResource("/gui/ListMedecinArticle.fxml"));
     //Parent root = FXMLLoader.load(getClass().getResource("/gui/ListAdminArticle.fxml"));
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
