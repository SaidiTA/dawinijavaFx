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
public class DossierPatient_itemController implements Initializable {

    @FXML
    private HBox Hbox;
    @FXML
    private Label idCol;
    @FXML
    private Label numCol;
    private Label nomCol;
    @FXML
    private Label codeCol;
    @FXML
    private Label descpCol;

    
   public void setData(Dossier dossier){
  
        int id = dossier.getId();
        idCol.setText(Integer.toString(id));
        descpCol.setText(dossier.getDescription());
        codeCol.setText(dossier.getCode_apci());
        int numm = dossier.getNumero();
        numCol.setText(Integer.toString(numm));  
                  
     
      
    }
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

}
