/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Dossier;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import services.DossierCrud;

/**
 * FXML Controller class
 *
 * @author msi
 */
public class ModifierDossierController implements Initializable {

    @FXML
    private TextArea numCol;
    @FXML
    private TextArea descpCol;
    @FXML
    private TextArea codeCol;
    
    
    private Dossier dossier;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    
      public void setData(Dossier dossier){
        /*
        descpCol.setText(dossier.getDescription());
        codeCol.setText(dossier.getCode_apci());
        int numm = dossier.getNumero();
        numCol.setText(Integer.toString(numm));    
       */
        this.dossier = dossier;
        numCol.setText(Integer.toString(dossier.getNumero()));
        codeCol.setText(dossier.getCode_apci());
        descpCol.setText(dossier.getDescription());
    }

    @FXML
    private void Mod_dos(ActionEvent event) {
         // Récupérer les données saisies par l'utilisateur
    int numero = Integer.parseInt(numCol.getText());
    String codeApci = codeCol.getText();
    String description = descpCol.getText();
    
    // Modifier le dossier correspondant dans la base de données
    DossierCrud dossierCrud = new DossierCrud();
    dossierCrud.modifier_dos(dossier, numero, codeApci, description);
    
    // Fermer la fenêtre de modification
    Stage stage = (Stage) numCol.getScene().getWindow();
    stage.close();
    
    }

    @FXML
    private void cancel(ActionEvent event) {
    }
    
    
}
