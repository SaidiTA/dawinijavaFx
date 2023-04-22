/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;


import entities.Assistant;
import entities.Patient;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import services.PatientService;

/**
 * FXML Controller class
 *
 * @author soumayaab
 */
public class ListPatientsController implements Initializable {

    @FXML
    private Label btnid;
    @FXML
    private Label Nom;
    @FXML
    private Label Prenom;
    @FXML
    private Label Email;
    @FXML
    private Label Telephone;
    @FXML
    private Label genre;
    @FXML
    private Label gouvernorat;
    @FXML
    private Label Adresse;
    @FXML
    private Label Action;
    @FXML
    private VBox pnitems;
    @FXML
    private Button btnRef1;
    @FXML
    private ImageView btnRef;
    @FXML
    private Label ListeMedecin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        PatientService patientService = new PatientService();
         List<Patient> patients = null;

    try {
        patients = patientService.recuperer();
    } catch (SQLException ex) {
        System.out.println(ex);
    }
          for (Patient patient : patients) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/itemPat.fxml"));
            Node node = loader.load();
            ItemPatController itemController = loader.getController();
            itemController.setData(patient);
            pnitems.getChildren().add(node);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }    

   

    @FXML
    private void refreshTable(ActionEvent event) {
         pnitems.getChildren().clear();
    PatientService patientService = new PatientService();
         List<Patient> patients = null;

    try {
        patients = patientService.recuperer();
    } catch (SQLException ex) {
        System.out.println(ex);
    }
          for (Patient patient : patients) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/itemPat.fxml"));
            Node node = loader.load();
            ItemPatController itemController = loader.getController();
            itemController.setData(patient);
            pnitems.getChildren().add(node);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }
    
}
