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
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import services.DossierCrud;

/**
 * FXML Controller class
 *
 * @author msi
 */
public class AfficherDossierController implements Initializable {

    @FXML
    private TableView<Dossier> tableview;
    @FXML
    private TableColumn<Dossier, Integer> numCol;
    @FXML
    private TableColumn<Dossier, String> codeCol;
    @FXML
    private TableColumn<Dossier, String> descpCol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DossierCrud ps = new DossierCrud();
        List<Dossier> dossiers = ps.listerDossiers();
        ObservableList<Dossier> obs = FXCollections.observableArrayList(dossiers);
        tableview.setItems(obs);
        numCol.setCellValueFactory(new PropertyValueFactory<>("numero"));
        codeCol.setCellValueFactory(new PropertyValueFactory<>("code_apci"));
        descpCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        
        // obs.remove()
        //obs.add();
    }
    
     @FXML
    private void naviguer(ActionEvent event)  throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterDossier.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }
    
}
