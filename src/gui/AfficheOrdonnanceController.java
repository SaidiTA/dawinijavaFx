/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.ordonnance;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

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
    @FXML
    private Button retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    void setOrd(ordonnance ord) {
        // Stocker l'objet 'ordonnance' à afficher dans la variable 'ord'
        this.ord = ord;
        // Mettre à jour les labels avec les informations de l'objet 'ordonnance'

        id1.setText(String.valueOf(ord.getId()));
        desc1.setText(ord.getDescription());
        date1.setText(ord.getDate().toString());
        System.out.println(ord.getImage());
        aff_img.setImage(new Image("file:src/uploads/"+ord.getImage()+".png"));
    }

    @FXML
    private void retour2(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("/gui/Dash.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}
