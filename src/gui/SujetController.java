/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Sujet;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import services.specialitesService;
import services.sujetService;

/**
 * FXML Controller class
 *
 * @author jlidi
 */
public class SujetController implements Initializable {
        sujetService Sp = new sujetService();
         List<Sujet> lt = Sp.listerSujet();

    @FXML
    private VBox pnitems;
    @FXML
    private TextField search;
    @FXML
    private ImageView btnsearch;

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

   


    @FXML
    private void buttonSearch(ActionEvent event) {
    String recherche = search.getText();

    // Create an instance of the CommandeService
    sujetService tt = new sujetService();
    // Perform the search using the entered text
    lt = tt.RechercherSujet(recherche);
    System.out.println("Recherche");
    System.out.println(recherche);

    // Clear the previous items in the VBox
    pnitems.getChildren().clear();

    // Iterate through the search results and add an FXML item for each item
    for (Sujet sujet : lt) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/itemsujet.fxml"));
            Node node = loader.load();
            ItemsujetController ItemsujetController = loader.getController();
            ItemsujetController.setData(sujet);
            pnitems.getChildren().add(node);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

    }
