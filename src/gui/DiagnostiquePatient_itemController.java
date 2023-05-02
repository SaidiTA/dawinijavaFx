/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Diagnostique;
import entities.Dossier;
import entities.Pdf;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import services.DiagnostiqueCrud;
import services.DossierCrud;

/**
 * FXML Controller class
 *
 * @author msi
 */
public class DiagnostiquePatient_itemController implements Initializable {

    @FXML
    private HBox HBox;
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
    private void pdf(ActionEvent event) {
      
    }

    @FXML
    private void onSelection(MouseEvent event) {
    }
    
}
