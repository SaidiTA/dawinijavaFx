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
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import services.ConsulationService;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class CardConsultationController implements Initializable {

    private int id;
    private Consulation cons;
    @FXML
    private Label id_consultation;
    @FXML
    private Label date_consultation;
    @FXML
    private Label debut_consultation;
    @FXML
    private Label fin_consultation;
    @FXML
    private Button modif_cons;
    @FXML
    private Button supp_cons;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Consulation getCons() {
        return cons;
    }

    public void setCons(Consulation cons) {
        this.cons = cons;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void setData(Consulation cons) {
        this.cons = cons;
        this.id = cons.getId();
        id_consultation.setText(String.valueOf(cons.getId()));
        date_consultation.setText(String.valueOf(cons.getDate()));
        debut_consultation.setText(String.valueOf(cons.getHeuredebut()));
        fin_consultation.setText(String.valueOf(cons.getHeurefin()));
    }

    @FXML
    public void SupprimerConsultation() throws IOException {
        try {

            ConsulationService consulationService = new ConsulationService();
            consulationService.supprimer(cons);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/DashMedecin.fxml"));
            Parent root;
            
                root = loader.load();
                
                supp_cons.getScene().setRoot(root);


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    @FXML
    public void RedirectModifierConsultation() throws IOException {
        try {

            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/ModifierConsultation.fxml"));
            Parent root;
            
                root = loader.load();
                
                modif_cons.getScene().setRoot(root);
                ConsulationService consulationService = new ConsulationService();
            consulationService.modifier(cons);


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    

}
