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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import services.ConsulationService;
import services.ordonnanceService;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class DashController implements Initializable {
private List<Consulation> Consultations;
    private ConsulationService consService = new ConsulationService();
    @FXML
    private VBox contenu;
    @FXML
    private Label id_consultation;
    @FXML
    private Label date;
    @FXML
    private Label heureD;
    @FXML
    private Label heureF;
    @FXML
    private Button ConsultationAddButton;
    @FXML
    private Label action;
    @FXML
    private Label ord;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     try {
            // Récupérer la liste des consultations pour l'utilisateur connecté

            Consultations = new ArrayList<>(consService.recupererByIdPatient(2));
            // Pour chaque consultation, on crée une carte (CardConsultationController) 

            for (Consulation cons : Consultations) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("cardConsultation.fxml"));

                try {
                    // On charge le fxml de la carte

                    HBox hBox = fxmlLoader.load();
                    CardConsultationController listconsultation = fxmlLoader.getController();
                                        // On vérifie s'il y a une ordonnance pour cette consultation

                    ordonnanceService ordService = new ordonnanceService();
                    if (ordService.recupererbycons(cons.getId()) == null) {
                                                // S'il n'y a pas d'ordonnance, on permet à l'utilisateur de modifier/supprimer/l'afficher

                        listconsultation.setModif_ord();
                        listconsultation.setSupp_ord();
                        listconsultation.setaff_ord();
                    }
                    // On initialise les données de la carte avec les données de la consultation

                    listconsultation.setData(cons);
                    // On ajoute la carte à la VBox

                    contenu.getChildren().add(hBox);
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
    }

    @FXML
    public void AjouterConsultation(ActionEvent event) throws SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/AjoutConsultation.fxml"));
        Parent root;
        try {
            root = loader.load();
                    // Obtenir le contrôleur de la vue AjoutConsultation

            AjoutConsultationController ajC = loader.getController(); 
            // Remplacer la vue actuelle par la vue AjoutConsultation

            ConsultationAddButton.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
