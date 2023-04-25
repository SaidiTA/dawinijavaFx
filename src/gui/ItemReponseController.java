/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.ReplaySujet;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import services.ReplaySujetService;

/**
 * FXML Controller class
 *
 * @author jlidi
 */
public class ItemReponseController implements Initializable {

    @FXML
    private Label btndate;
    @FXML
    private Label btnmessage;
    @FXML
    private Button btnModify;
    @FXML
    private ImageView btnmodifier;
                 private ReplaySujet ReplaySujet;

    @FXML
    private Button btnDelete;
    @FXML
    private Label btnid;

        
public void setData(ReplaySujet replaysujet){
        int id=replaysujet.getId();
        btnid.setText(Integer.toString(id));

        btnmessage.setText(replaysujet.getMessage()); 

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
        ReplaySujetService dc = new ReplaySujetService();
        List<ReplaySujet> spec = dc.listerReponse();
        ReplaySujet reponse = null;
        for (ReplaySujet d : spec) {
            if (d.getId() == id) {
                reponse = d;
                break;
            }
        }
        
        // Chargement de la fenêtre de modification
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/ModfierReponse.fxml"));
        Parent parent = fxmlLoader.load();
        ModfierReponseController controller = fxmlLoader.getController();

        // Transmission des données du dossier à la fenêtre de modification
        controller.setData(reponse);

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
          ReplaySujetService dc = new ReplaySujetService();
            List<ReplaySujet> reponse = dc.listerReponse();
                 for (ReplaySujet d : reponse) {
                     if (d.getId() == id) {
                     dc.supprimer(d);
                     
                 break;
                 
        }
    }
    
    }
    
}
