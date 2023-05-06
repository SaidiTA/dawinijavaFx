/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import entities.Assistant;
import entities.Medecin;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import services.AssistantService;

/**
 * FXML Controller class
 *
 * @author soumayaab
 */
public class itemAssisForMedController implements Initializable {
    private Medecin medecin;

    public Medecin getMedecin() {
        return medecin;
    }

    public void setMedecin(Medecin medecin) {
        this.medecin = medecin;
    }
public int medecin_id;
    @FXML
    private Label btnid;
    @FXML
    private Label lblNom;
    @FXML
    private Label lblPrenom;
    @FXML
    private Label lblEmail;
    @FXML
    private Label lblTelephone;
    @FXML
    private Label lblgenre;
    @FXML
    private Label lblgouv;
    @FXML
    private Label lblAdresse;
    @FXML
    private Label lblMedecin;
    @FXML
    private HBox lblAction;
    @FXML
    private Button btnshow;
    @FXML
    private Button btnDelete;
public void initialize(int medecin) {
         this.medecin_id=medecin;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Affiche(ActionEvent event) {
    }

    @FXML
    private void supprimer(ActionEvent event) {
    }
    void handleButtonClick(ActionEvent event) throws SQLException {
        try {
            // Récupération des données du dossier sélectionné

              int id = Integer.parseInt(btnid.getText());
            AssistantService dc = new AssistantService();
            List<Assistant> spec = dc.getAssistantsForMedecin(medecin_id);
            Assistant assistant = null;
            for (Assistant d : spec) {
                if (d.getId() == id) {
                    assistant = d;
                    break;
                }
            }
           


            // Chargement de la fenêtre de modification
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/ModifierAssistant.fxml"));
            Parent parent = fxmlLoader.load();
            ModifierMedecinController controller = fxmlLoader.getController();

            // Transmission des données du dossier à la fenêtre de modification
           // controller.setData(assistant);

            // Affichage de la fenêtre de modification
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();

        } catch (IOException ex) {

        }

    }
    
    void setData(Assistant assistant) {
        int id = assistant.getId();
        btnid.setText(Integer.toString(id));

        lblNom.setText(assistant.getNom());
        lblPrenom.setText(assistant.getPrenom());
        lblEmail.setText(assistant.getEmail());
        lblTelephone.setText(assistant.getTelephone());
        lblgouv.setText(assistant.getGouvernorat());
        //lblspecialites.setText(medecin.getSpecialite());
        lblgenre.setText(assistant.getSexe());
        lblAdresse.setText(assistant.getAdresse());
 Medecin medecin = assistant.getMedecin();
        
            lblMedecin.setText(medecin.getNom() + " " + medecin.getPrenom());
       
        }

    }
