/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Dossier;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import services.DossierCrud;
import util.MyDB;

/**
 * FXML Controller class
 *
 * @author msi
 */
public class Dossier_itemController implements Initializable {    
    @FXML
    private Label numCol;
    @FXML
    private Label codeCol;
    @FXML
    private Label descpCol;
    @FXML
    private HBox Hbox;
    @FXML
    private Label idCol;
    @FXML
    private Label nomCol;

    public void setData(Dossier dossier){
  
        int id = dossier.getId();
        idCol.setText(Integer.toString(id));
        descpCol.setText(dossier.getDescription());
        codeCol.setText(dossier.getCode_apci());
        int numm = dossier.getNumero();
        numCol.setText(Integer.toString(numm));  
                  
          // Récupération du nom et prénom du patient correspondant à l'ID
    String nomPrenom = "";
    int patient_id = dossier.getPatient_id();
    try {
        PreparedStatement preparedStatement = MyDB.getInstance().getCnx()
                .prepareStatement("SELECT CONCAT(u.nom, ' ', u.prenom) AS nom_prenom FROM user u JOIN patient p ON u.id = p.id WHERE p.id = ?");
        preparedStatement.setInt(1, patient_id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            nomPrenom = resultSet.getString("nom_prenom");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    nomCol.setText(nomPrenom);
      
      
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
        int numero = Integer.parseInt(numCol.getText());
        DossierCrud dc = new DossierCrud();
        List<Dossier> dossiers = dc.listerDossiers();
        Dossier dossier = null;
        for (Dossier d : dossiers) {
            if (d.getNumero() == numero) {
                dossier = d;
                break;
            }
        }
        
        // Chargement de la fenêtre de modification
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ModifierDossier.fxml"));
        Parent parent = fxmlLoader.load();
        ModifierDossierController controller = fxmlLoader.getController();

        // Transmission des données du dossier à la fenêtre de modification
        controller.setData(dossier);

        // Affichage de la fenêtre de modification
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
     
       updateDossier();

        stage.show();
       
      
    } catch (IOException ex) {
        Logger.getLogger(Dossier_itemController.class.getName()).log(Level.SEVERE, null, ex);
    } 
    }
    
    

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
         
           int numero = Integer.parseInt(numCol.getText());
           DossierCrud dc = new DossierCrud();
            List<Dossier> dossiers = dc.listerDossiers();
                 for (Dossier d : dossiers) {
                     if (d.getNumero() == numero) {
                     dc.supprimer_doss(d);
                     
                 break;
                 
        }
    }
    updateDossiers(); 
    }
    
   private void updateDossiers() {
    Hbox.getChildren().clear();
    DossierCrud dc = new DossierCrud();
    List<Dossier> dossiers = dc.listerDossiers();
    for (Dossier d : dossiers) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("Dossier_item.fxml"));
        try {
            HBox hBox = fxmlLoader.load();
            Dossier_itemController dic = fxmlLoader.getController();
            dic.setData(d);
            //Hbox.getChildren().add(hBox);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
    /*private void updateDossier() {
    Hbox.getChildren().clear();
    DossierCrud dc = new DossierCrud();
    List<Dossier> dossiers = dc.listerDossiers();
    for (Dossier d : dossiers) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("Dossier_item.fxml"));
        try {
            HBox hBox = fxmlLoader.load();
            Dossier_itemController dic = fxmlLoader.getController();
            dic.setData(d);
          //  Hbox.getChildren().add(hBox);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    VBox vbox = new VBox();
    vbox.getChildren().addAll(Hbox.getChildren());
    Hbox.getChildren().setAll(vbox);
}
*/
   private void updateDossier() {
      Hbox.getChildren().clear();
    DossierCrud dc = new DossierCrud();
    List<Dossier> dossiers = dc.listerDossiers();
    for (Dossier d : dossiers) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("Dossier_item.fxml"));
        try {
            
            HBox hBox = fxmlLoader.load();
            
            Dossier_itemController dic = fxmlLoader.getController();
            dic.setData(d);
            Hbox.getChildren().add(hBox);
            Hbox.getChildren().clear();
            Hbox.getChildren().add(hBox);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }}




    @FXML
    private void aj_diag(ActionEvent event) {
            try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListDiagnostique.fxml"));
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
    private void mod_dos(MouseEvent event) {
    }



   

   
    
}
