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
public class DashMedecinController implements Initializable {

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
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {

            Consultations = new ArrayList<>(consService.recupererByIdPatient(2));
            for (Consulation cons : Consultations) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("cardConsultation.fxml"));

                try {
                    HBox hBox = fxmlLoader.load();
                    CardConsultationController listconsultation = fxmlLoader.getController();
                     ordonnanceService ordService = new ordonnanceService(); 
                         if(ordService.recupererbycons(cons.getId())==null)
        {
            listconsultation.setModif_ord();
                    listconsultation.setSupp_ord();
                    listconsultation.setaff_ord();
        }

                                        
                        
                    listconsultation.setData(cons);
                   

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
            AjoutConsultationController ajC = loader.getController();
            ConsultationAddButton.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
