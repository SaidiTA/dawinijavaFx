/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class CardDashPatientController implements Initializable {

    @FXML
    private Label id_consultation;
    @FXML
    private Label date_consultation;
    @FXML
    private Label debut_consultation;
    @FXML
    private Button aff_ord1;
    @FXML
    private Button aff_ord;
    @FXML
    private Button qrcode;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AfficherOrdonnance(ActionEvent event) {
    }

    @FXML
    private void qrcode(ActionEvent event) {
    }
    
}
