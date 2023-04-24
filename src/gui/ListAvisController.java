/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package gui;

import entities.Avis;
import entities.Medecin;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import services.AvisService;
import services.MedecinService;




/**
 *
 * @author soumayaab
 */
public class ListAvisController implements Initializable {

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
    @FXML
    private Label Action;
    private double x, y;
    @FXML
    private VBox pnitems = null;
    @FXML
    private Label ListeMedecin;
    @FXML
    private Button btnRef1;
    @FXML
    private ImageView btnRef;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
            AvisService aviService = new AvisService();
         List<Avis> avi = null;

    try {
        avi = aviService.recuperer();
    } catch (SQLException ex) {
        System.out.println(ex);
    }
          
      



    for (Avis avii : avi) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/itemAvis.fxml"));
            Node node = loader.load();
            ItemAvisController itemController = loader.getController();
            itemController.setData(avii);
            pnitems.getChildren().add(node);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    }

  

    @FXML
    private void refreshTable(ActionEvent event) {
        pnitems.getChildren().clear();
        AvisService aviService = new AvisService();
         List<Avis> avi = null;

    try {
        avi = aviService.recuperer();
    } catch (SQLException ex) {
        System.out.println(ex);
    }
          
      



    for (Avis avii : avi) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/itemAvis.fxml"));
            Node node = loader.load();
            ItemAvisController itemController = loader.getController();
            itemController.setData(avii);
            pnitems.getChildren().add(node);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }

    @FXML
    private void MED(ActionEvent event) {

          try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListMedecin.fxml"));
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
    private void Assistants(ActionEvent event) {
          try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListAssistant.fxml"));
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
    private void Patients(ActionEvent event) {
         try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListPatients.fxml"));
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
    private void Articles(ActionEvent event) {
    }

    @FXML
    private void AVIS(ActionEvent event) {
         try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListAvis.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
       
        
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    }

    

           
      
    
    

    

    

    
    
}
