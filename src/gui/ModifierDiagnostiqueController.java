/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Diagnostique;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import services.DiagnostiqueCrud;

/**
 * FXML Controller class
 *
 * @author msi
 */
public class ModifierDiagnostiqueController implements Initializable {

    
    private Diagnostique diagnostique;
    @FXML
    private TextArea symCol;
    @FXML
    private DatePicker dateCol;
    @FXML
    private TextArea diagCol;
    @FXML
    private TextArea resCol;
    @FXML
    private Label idCol;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
     public void setData(Diagnostique diagnostique){
        /*
        descpCol.setText(dossier.getDescription());
        codeCol.setText(dossier.getCode_apci());
        int numm = dossier.getNumero();
        numCol.setText(Integer.toString(numm));    
       */
        
        this.diagnostique = diagnostique;
        
        Date date = Date.valueOf(dateCol.getValue());
        //dateCol.setText(diagnostique.getDate());
        symCol.setText(diagnostique.getSymptome());
        resCol.setText(diagnostique.getResultat_test());
        diagCol.setText(diagnostique.getDiag_final());
    }

    @FXML
    private void Mod_dos(ActionEvent event) {
            // Récupérer les données saisies par l'utilisateur
    Date date = Date.valueOf(dateCol.getValue());
    String symptome = symCol.getText();
    String resultat_test = resCol.getText();
    String diag_final = diagCol.getText();
    
    // Modifier le dossier correspondant dans la base de données
    DiagnostiqueCrud diagnostiqueCrud = new DiagnostiqueCrud();
    diagnostiqueCrud.modifier_diag(diagnostique, date, symptome, resultat_test, diag_final);
    
    // Fermer la fenêtre de modification
    Stage stage = (Stage) idCol.getScene().getWindow();
    stage.close();
    
    }

    @FXML
    private void cancel(ActionEvent event) {
    }
    
}
