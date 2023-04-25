/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entities.Rendezvous;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import services.RendezvousService;

/**
 *
 * @author HP
 */
public class NewFXMain extends Application {
//public class NewFXMain  {
/*    
//bch njarb l ajout
    public static void main(String[] args) {
        //List<Consulation> consultations=new ArrayList<>();
        //Timestamp date=new Timestamp(System.currentTimeMillis()); ;
      //  Timestamp datedebut=new Timestamp(System.currentTimeMillis()); ;
       // Timestamp datefin=new Timestamp(System.currentTimeMillis()); ;
        
        Rendezvous t= new Rendezvous("tayssir","tayssirjava" ,"valide");
        
        RendezvousService cs = new RendezvousService();
        try {
            //c.setId(62);
            //c.setEst_termine("1");
            cs.ajouter(t);
           // consultations.addAll(cs.recuperer());
           // for(Consulation i: consultations){
            //    System.out.println(i);
           // }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }*/
    
    /*@Override
    public void start(Stage primaryStage) {
        
        try {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/AjouterrendezvousFXML.fxml"));
        Parent root = loader.load();
           Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        
    }*/
    
    /*@Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/gui/Afficherrendezvous.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }*/
    
    @Override
    public void start(Stage primaryStage) {
        Parent root;
        try {
           root = FXMLLoader.load(getClass().getResource("../gui/afficherRFXML.fxml"));
        //   root = FXMLLoader.load(getClass().getResource("../gui/afficherpaiementFXML.fxml"));

        //root = FXMLLoader.load(getClass().getResource("../gui/AjouterrendezvousFXML.fxml"));
            // root = FXMLLoader.load(getClass().getResource("../gui/AfficherrendezvousFXML.fxml"));
            //root = FXMLLoader.load(getClass().getResource("../gui/TransactionFXML.fxml"));

            Scene scene = new Scene(root);
            primaryStage.setTitle("DAWINI DESKTOP");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex);
        }

    }
    
    

    /**
     * @param args the command line arguments
     */
  public static void main(String[] args) {
       launch(args);
  }
    
}
