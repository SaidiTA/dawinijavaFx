/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package gui;

import entities.Avis;
import entities.Medecin;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import services.AvisService;
import services.MedecinService;

/**
 *
 * @author soumayaab
 */
public class ListAvisController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
/*private double x, y;
    @FXML
    private VBox pnitems = null;
    private Button btnAdd;
    private ImageView btnAddplus1;   
    @FXML
    private Label Action;
    @FXML
    private Label ListeMedecin;
    @FXML
    private Label btnid;
    @FXML
    private Label lblnote;
    @FXML
    private Label lblstatut;
    @FXML
    private Label lbldate;
    @FXML
    private Label lblmed;
    @FXML
    private Label lblpati;
   

    

      public void initialize(URL location, ResourceBundle resources) {
           AvisService avisService = new AvisService();
         List<Medecin> medecins = null;

    try {
        medecins = avisService.recuperer();
    } catch (SQLException ex) {
        System.out.println(ex);
    }
          
      



    for (Avis avi : avis) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/itemAvis.fxml"));
            Node node = loader.load();
            ItemAvisController itemController = loader.getController();
            itemController.setData(avi);
            pnitems.getChildren().add(node);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    }

    
   
      */ 
           
           
           
           
      
    
    

    

    

    
    
}
