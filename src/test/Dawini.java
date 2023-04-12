/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML.java to edit this template
 */
package test;

import java.io.IOException;
import java.sql.Connection;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import util.MyDB;


/**
 *
 * @author soumayaab
 */


public class Dawini extends Application {

    @Override
   public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../gui/SpecialitesFront.fxml"));
        
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


    
   
   
     MyDB myDB = new MyDB();
    Connection cnx = myDB.getCnx();

    
}
 

//public class Dawini extends Application {
    
   
    //public void start(Stage primaryStage) throws Exception {
       //  Parent root = FXMLLoader.load(getClass().getResource("/gui/SignInUser.fxml"));
       // Scene scene = new Scene(root);
        
        //primaryStage.setTitle("Sign In User");
        //primaryStage.setScene(scene);
        //primaryStage.show();
    //}
  //  