/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Specialites;
import entities.Sujet;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;
import services.specialitesService;
import services.sujetService;

/**
 * FXML Controller class
 *
 * @author jlidi
 */
public class ModifierSujetController implements Initializable {

    @FXML
    private MenuButton Specialite;
    @FXML
    private Button btncrosse;
    @FXML
    private ImageView btncross;
    @FXML
    private TextArea btntitle;
    @FXML
    private TextArea btnmessage;
         private Sujet sujet;

    @FXML
    private HTMLEditor btndescription;
    @FXML
    private Button ModifierSujet;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
public void setData(Sujet sujet){
        /*
        descpCol.setText(dossier.getDescription());
        codeCol.setText(dossier.getCode_apci());
        int numm = dossier.getNumero();
        numCol.setText(Integer.toString(numm));    
       */
        this.sujet = sujet;
        btntitle.setText(sujet.getTitle());
                btnmessage.setText(sujet.getMessage());

        btndescription.setHtmlText(sujet.getDescription());
    }   
    private void AjouterSujet(ActionEvent event) {
         String title = btntitle.getText();
         String message = btnmessage.getText();

    String description = btndescription.getHtmlText();
    
    // Modifier le dossier correspondant dans la base de données
    sujetService sujetCrud = new sujetService();
    sujetCrud.modifier_sujet(sujet ,message,title, description);
    
    // Fermer la fenêtre de modification
    Stage stage = (Stage) btntitle.getScene().getWindow();
    stage.close();
    
    }

  

    @FXML
    private void handleButtonAction(ActionEvent event) {
         if(event.getSource() == btncrosse) {
        // Get the Stage that contains the button
        Stage stage = (Stage) btncrosse.getScene().getWindow();
        
        // Close the stage
        stage.close();
      
    }
       if(event.getSource() == btncross) {
        // Get the Stage that contains the button
        Stage stage = (Stage) btncross.getScene().getWindow();
        
        // Close the stage
        stage.close();
      
    }
    }

    @FXML
    private void buttonOnAction(ActionEvent event) {
         String title = btntitle.getText();
         String message = btnmessage.getText();

    String description = btndescription.getHtmlText();
    
    // Modifier le dossier correspondant dans la base de données
    sujetService sujetCrud = new sujetService();
    sujetCrud.modifier_sujet(sujet ,message,title, description);
    
    // Fermer la fenêtre de modification
    Stage stage = (Stage) btntitle.getScene().getWindow();
    stage.close();
    
    }

    }


    

    

