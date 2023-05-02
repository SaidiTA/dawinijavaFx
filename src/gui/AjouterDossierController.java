/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Dossier;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.DossierCrud;

/**
 * FXML Controller class
 *
 * @author msi
 */
public class AjouterDossierController implements Initializable {

    @FXML
    private TextField numCol;
    @FXML
    private TextField codeCol;
    @FXML
    private TextField descpCol;
  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    @FXML
    private void AjouterDossier(ActionEvent event) {
        int numero = Integer.parseInt(numCol.getText());
        String code_apci = codeCol.getText();
        String description  = descpCol.getText();
        Dossier d = new Dossier(numero, code_apci, description);
        DossierCrud dc = new DossierCrud();
        dc.ajouter_dossier(d);
    }

    @FXML
    private void AfficherDossier(ActionEvent event) {
          try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherDossier.fxml"));
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
