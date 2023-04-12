/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Diagnostique;
import entities.Dossier;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
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
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import services.DiagnostiqueCrud;
import services.DossierCrud;

/**
 * FXML Controller class
 *
 * @author msi
 */
public class Diagnostique_itemController implements Initializable {

    @FXML
    private Label idCol;
    @FXML
    private Label dateCol;
    @FXML
    private Label symCol;
    @FXML
    private Label resCol;
    @FXML
    private Label diagCol;
    @FXML
    private HBox HBox;
    
     public void setData(Diagnostique diagnostique){
        int id = diagnostique.getId();
        idCol.setText(Integer.toString(id));
        //Date date = diagnostique.getDate();
        //dateCol.setText(diagnostique.toString());
        Date date = diagnostique.getDate();
        dateCol.setText(date.toString());
        //dateCol.setDate(diagnostique.getDate());
        symCol.setText(diagnostique.getSymptome());
        resCol.setText(diagnostique.getResultat_test());
        diagCol.setText(diagnostique.getDiag_final());
        
    }

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void mod_dos(ActionEvent event) {
         /*          try {
            Parent parent = FXMLLoader.load(getClass().getResource("ModifierDossier.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AjouterDossierController.class.getName()).log(Level.SEVERE, null, ex);
        } */
      
        try {
        // Récupération des données du dossier sélectionné
        int id = Integer.parseInt(idCol.getText());
        DiagnostiqueCrud dc = new DiagnostiqueCrud();
        List<Diagnostique> diagnostiques = dc.listerDiagnostiques();
        Diagnostique diagnostique = null;
        for (Diagnostique d : diagnostiques) {
            if (d.getId() == id) {
                diagnostique = d;
                break;
            }
        }
        
        // Chargement de la fenêtre de modification
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ModifierDiagnostique.fxml"));
        Parent parent = fxmlLoader.load();
        ModifierDiagnostiqueController controller = fxmlLoader.getController();

        // Transmission des données du dossier à la fenêtre de modification
        controller.setData(diagnostique);

        // Affichage de la fenêtre de modification
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
     
       updateDiagnostiques();

        stage.show();
       
      
    } catch (IOException ex) {
        Logger.getLogger(Dossier_itemController.class.getName()).log(Level.SEVERE, null, ex);
    } 
    }
    
     private void updateDiagnostiques() {
    HBox.getChildren().clear();
    DiagnostiqueCrud dc = new DiagnostiqueCrud();
    List<Diagnostique> diagnostiques = dc.listerDiagnostiques();
    for (Diagnostique d : diagnostiques) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("Diagnostique_item.fxml"));
        try {
            HBox hBox = fxmlLoader.load();
            Diagnostique_itemController dic = fxmlLoader.getController();
            dic.setData(d);
            //Hbox.getChildren().add(hBox);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
     
      private void updateDiagnostique() {
      HBox.getChildren().clear();
    DiagnostiqueCrud dc = new DiagnostiqueCrud();
    List<Diagnostique> diagnostiques = dc.listerDiagnostiques();
    for (Diagnostique d : diagnostiques) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("Diagnostique_item.fxml"));
        try {
            
            HBox hBox = fxmlLoader.load();
            
            Diagnostique_itemController dic = fxmlLoader.getController();
            dic.setData(d);
            HBox.getChildren().add(hBox);
            HBox.getChildren().clear();
            HBox.getChildren().add(hBox);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }}

    
  

    @FXML
    private void sup_dos(ActionEvent event) {
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
         
           int id = Integer.parseInt(idCol.getText());
           DiagnostiqueCrud dc = new DiagnostiqueCrud();
            List<Diagnostique> diagnostiques = dc.listerDiagnostiques();
                 for (Diagnostique d : diagnostiques) {
                     if (d.getId() == id) {
                     dc.supprimer_diagnostique(d);
                     
                 break;
                 
        }
    }
    updateDiagnostiques();
    }

   

    
}
