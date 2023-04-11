/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Consulation;
import entities.ordonnance;
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
import services.ordonnanceService;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class CardConsultationController implements Initializable {

    private int id;
    private Consulation cons;
    private ordonnance ord;
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
    @FXML
    private Button modif_ord;
    @FXML
    private Button aff_ord;
    @FXML
    private Button supp_ord;

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

    public ordonnance getOrd() {
        return ord;
    }

    public void setOrd(ordonnance ord) {
        this.ord = ord;
    }
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        

    }

    

  

    

    public void setModif_ord() {
       
         modif_ord.setDisable(true);
    }

    

    public void setSupp_ord() {
        
        supp_ord.setDisable(true);
    }
    
    
    public void setaff_ord() {
        this.aff_ord.setDisable(true);
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
    public void RedirectModifierConsultation() throws IOException, SQLException {
         
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/ModifierConsultation.fxml"));
        Parent root;
        root = loader.load();
        ModifierConsultationController modif = loader.getController();

        modif.initialize(getCons());
        modif_cons.getScene().setRoot(root);

    }
    @FXML
    public void RedirectModifierOrdonnance() throws IOException, SQLException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/ModifierOrdonnance.fxml"));
        Parent root;
        root = loader.load();
        ModifierOrdonnanceController modiford = loader.getController();
         ordonnanceService ordService = new ordonnanceService();
         
         

        modiford.initialize(ordService.recupererbycons(this.cons.getId()));
        modif_ord.getScene().setRoot(root);

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
    public void SupprimerOrdonnance() throws IOException {
        
        try {
            ordonnanceService ordService = new ordonnanceService();
            ordService.supprimer(ordService.recupererbycons(this.cons.getId()));
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/DashMedecin.fxml"));
            Parent root;
            root = loader.load();
            supp_ord.getScene().setRoot(root);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    @FXML
        public void AfficherOrdonnance() throws IOException {
        
        try {
            ordonnanceService ordService = new ordonnanceService();
            ordService.recupererbyID(this.cons.getId());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/DashMedecin.fxml"));
            Parent root;
            root = loader.load();
            supp_ord.getScene().setRoot(root);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

}
