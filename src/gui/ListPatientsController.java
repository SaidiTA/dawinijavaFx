/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import entities.Assistant;
import entities.Patient;
import entities.UserSession;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
    @FXML
    private ImageView btnUploadImage;

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

    @FXML
    private void refreshTable(MouseEvent event) {
    }

    @FXML
    private void handleLogoutButtonAction(ActionEvent event) throws IOException {
        // Supprimez la session utilisateur en cours
        UserSession.getInstance().setCurrentUser(null);

        // Redirigez l'utilisateur vers l'écran de connexion
        Parent root = FXMLLoader.load(getClass().getResource("SignInUser.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void MED(ActionEvent event) {
          try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListMedecin.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
       
        
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    }

    @FXML
    private void ASSISTANT(ActionEvent event) {
         try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListAssistant.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
       
        
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    }

    @FXML
    private void PAT(ActionEvent event) {
         try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListPatients.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
       
        
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    }

    @FXML
    private void AVIS(ActionEvent event) {
          try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListAvis.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
       
        
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    }

}
