/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Consulation;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import services.ConsulationService;
import services.ordonnanceService;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class DashPatientController implements Initializable {
    @FXML
    private VBox contenu;

    private List<Consulation> Consultations;
    private ConsulationService consService = new ConsulationService();
    @FXML
    private Button ConsultationAddButton;
    @FXML
    private Label id_consultation;
    @FXML
    private Label date;
    @FXML
    private Label heureD;
    @FXML
    private Label heureF;
    @FXML
    private Label action;
    @FXML
    private Label ord;

    /**
     * Initializes the controller class.
     */
      public void initialize(int id) {
        
        try {
            // Récupérer la liste des consultations pour l'utilisateur connecté 

            Consultations = new ArrayList<>(consService.recupererByIdPatient(id));

            // Initialiser la pagination
            Pagination pagination = new Pagination();
            pagination.setPageCount((int) Math.ceil(Consultations.size() / 5.0)); // 5 consultations par page
            pagination.setPageFactory((Integer pageIndex) -> {
                VBox pageContent = new VBox();
                int pageStart = pageIndex * 5;
                int pageEnd = Math.min(pageStart + 5, Consultations.size());
                for (int i = pageStart; i < pageEnd; i++) {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("cardDashPatient.fxml"));
                    try {
                        HBox hBox = fxmlLoader.load();
                        CardConsultationController listconsultation = fxmlLoader.getController();

                        // Vérifier s'il y a une ordonnance pour cette consultation
                        ordonnanceService ordService = new ordonnanceService();
                        if (ordService.recupererbycons(Consultations.get(i).getId()) == null) {
                            // S'il n'y a pas d'ordonnance, on permet à l'utilisateur de modifier/supprimer/l'afficher
                            listconsultation.setModif_ord();
                            listconsultation.setSupp_ord();
                            listconsultation.setaff_ord();
                        }

                        // Initialiser les données de la carte avec les données de la consultation
                        listconsultation.setData(Consultations.get(i));

                        // Ajouter la carte à la page actuelle
                        pageContent.getChildren().add(hBox);
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    } catch (SQLException ex) {
                        Logger.getLogger(DashController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                return pageContent;
            });

            // Ajouter la pagination à la VBox principale
            contenu.getChildren().add(pagination);

            // Ajouter un écouteur d'événements pour la recherche
            //chercher.textProperty().addListener((observable, oldValue, newValue) -> {
             //   search(newValue);
           // });

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }


}
