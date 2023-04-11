/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.ordonnance;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class AfficheOrdonnanceController implements Initializable {
    private ordonnance ord;
    @FXML
    private ImageView aff_img;
    @FXML
    private Label id1;
    @FXML
    private Label desc1;
    @FXML
    private Label date1;

    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    

    void setOrd(ordonnance ord) {
        this.ord=ord;
        id1.setText(String.valueOf(ord.getId()));
       desc1.setText(ord.getDescription());
       date1.setText(ord.getDate().toString());
    }
    
}
