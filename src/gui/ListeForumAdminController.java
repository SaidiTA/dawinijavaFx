/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author jlidi
 */
public class ListeForumAdminController implements Initializable {
@FXML
    private VBox pnitems = null;
    @FXML
    private Button btnforum;

    /**
     * Initializes the controller class.
     */
    @Override
   public void initialize(URL location, ResourceBundle resources) {
        Node[] nodes = new Node[5];
        for (int i = 0; i < nodes.length; i++) {
            try {

                final int j = i;
                nodes[i] = FXMLLoader.load(getClass().getResource("/gui/itemforum.fxml"));

                //give the items some effect

                nodes[i].setOnMouseEntered(event -> {
                    nodes[j].setStyle("-fx-background-color : #F1F7FD");
                });
                nodes[i].setOnMouseExited(event -> {
                    nodes[j].setStyle("-fx-background-color : #6C9FC1");
                });
                pnitems.getChildren().add(nodes[i]);
            } catch (IOException e) {
                e.printStackTrace();
            
            }
        }

    }  

    @FXML
    private void forum(ActionEvent event) {
    }
    
}
