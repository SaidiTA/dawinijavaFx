/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import entities.Assistant;
import entities.Medecin;
import entities.Patient;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import services.MedecinService;
import services.PatientService;

/**
 * FXML Controller class
 *
 * @author soumayaab
 */
public class ItemPatController implements Initializable {

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
    private HBox lblAction;
    @FXML
    private Button btnshow;
    @FXML
    private Button btnDelete;
    private Patient patient;

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

      
            // Récupération des données du dossier sélectionné
            String idStr = btnid.getText();
            if (!idStr.isEmpty()) {
                try {
                    int id = Integer.parseInt(idStr);
                    PatientService dc = new PatientService();
                    List<Patient> spec = dc.recuperer();
                    Patient patient = null;
                    for (Patient d : spec) {
                        if (d.getId() == id) {
                            patient = d;
                            break;
                        }
                    }
                    setPatient(patient);
                } catch (NumberFormatException ex) {
                    // handle the exception, e.g. show an error message
                } catch (SQLException ex) {
                    Logger.getLogger(ItemPatController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

         void setData(Patient patient) {
       int id = patient.getId();
        btnid.setText(Integer.toString(id));
        lblNom.setText(patient.getNom());
        lblPrenom.setText(patient.getPrenom());
        lblEmail.setText(patient.getEmail());
        lblTelephone.setText(patient.getTelephone());
        lblAdresse.setText(patient.getAdresse());
         lblgouv.setText(patient.getGouvernorat());
         lblgenre.setText(patient.getSexe());
  
        }

    @FXML
    private void Affiche(ActionEvent event) {
        int id = Integer.parseInt(btnid.getText());
        PatientService ms = new PatientService();
        try {
            Patient patient = ms.recupererById(id);

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/infoPatient.fxml"));
            Parent parent = fxmlLoader.load();
            InfoPatController controller = fxmlLoader.getController();
            controller.setPatient(patient);

            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();

        } catch (IOException ex) {
            System.out.println("Erreur lors du chargement de la fenêtre d'affichage des informations du médecin: " + ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la récupération des informations du médecin: " + ex.getMessage());
        }
    }
    }

    
