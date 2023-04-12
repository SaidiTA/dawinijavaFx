/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Specialites;
import entities.Sujet;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import services.specialitesService;
import services.sujetService;

/**
 * FXML Controller class
 *
 * @author jlidi
 */
public class SujetController implements Initializable {

    @FXML
    private VBox pnitems;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         List<Sujet> sujet = getSujetList();

        // Parcourez chaque spécialité et créez un élément FXML pour chaque spécialité
        for (Sujet suje : sujet) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/itemsujet.fxml"));
                Node node = loader.load();
                ItemsujetController sujetController = loader.getController();
                sujetController.setData(suje);
                pnitems.getChildren().add(node);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }    
     private List<Sujet> getSujetList() {
        sujetService ps=new sujetService();
        List<Sujet> sujet = ps.listerSujet();
        return sujet;
    }
}
