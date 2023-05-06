/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.ReplaySujet;
import entities.Specialites;
import entities.Sujet;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import services.ReplaySujetService;
import services.specialitesService;
import services.sujetService;

/**
 * FXML Controller class
 *
 * @author jlidi
 */
public class ItemsujetController implements Initializable {

    @FXML
    private Label btnid;
     private Sujet sujet;

    @FXML
    private Label btntitle;
    @FXML
    private Label btnmessage;
    @FXML
    private Label btndescription;
    @FXML
    private Label btndate;
    @FXML
    private Button btnModify;
    @FXML
    private ImageView btnmodifier;
    @FXML
    private Button btnDelete;
    
public void setDataa(Sujet sujet){
        int id=sujet.getId();
        btnid.setText(Integer.toString(id));
        btndate.setText(sujet.getDate().toString());
                      btntitle.setText(sujet.getTitle());
                btnmessage.setText(sujet.getMessage());

        btndescription.setText(sujet.getDescription());

        
        
    }

public void setData(Sujet sujet){
        int id=sujet.getId();
        btnid.setText(Integer.toString(id));
                      btntitle.setText(sujet.getTitle());
                btnmessage.setText(sujet.getMessage());

        btndescription.setText(sujet.getDescription());

        
        
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleButtonClick(ActionEvent event) {
        try {
        // Récupération des données du dossier sélectionné
        int id = Integer.parseInt(btnid.getText());
        sujetService dc = new sujetService();
        List<Sujet> spec = dc.listerSujet();
        Sujet sujet = null;
        for (Sujet d : spec) {
            if (d.getId() == id) {
                sujet = d;
                break;
            }
        }
        
        // Chargement de la fenêtre de modification
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/ModifierSujet.fxml"));
        Parent parent = fxmlLoader.load();
        ModifierSujetController controller = fxmlLoader.getController();

        // Transmission des données du dossier à la fenêtre de modification
        controller.setData(sujet);

        // Affichage de la fenêtre de modification
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.show();
       
    } catch (IOException ex) {
        Logger.getLogger(ItemsujetController.class.getName()).log(Level.SEVERE, null, ex);
    } 
}

    

    @FXML
    private void sup_spec(ActionEvent event) {
         int id = Integer.parseInt(btnid.getText());
           sujetService dc = new sujetService();
            List<Sujet> sujet = dc.listerSujet();
                 for (Sujet d : sujet) {
                     if (d.getId() == id) {
                     dc.supprimer(d);
                     
                 break;
                 
        }
    }
    
    }

    @FXML
    private void Voir(ActionEvent event) {
        int id = Integer.parseInt(btnid.getText());
        
        ReplaySujetService a = new ReplaySujetService();
        try {
            Sujet sujet = a.recupererById(id);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Reponse.fxml"));
            Parent root = loader.load();
            ReponseController controller = loader.getController();
            controller.setReplay(sujet);
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    }
    

