/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import entities.Avis;
import entities.Medecin;
import entities.Patient;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import services.AvisService;
import services.MedecinService;
import services.PatientService;

/**
 * FXML Controller class
 *
 * @author soumayaab
 */
public class FrontAvisController implements Initializable {

    private Medecin medecin;
    private Patient patient;
    @FXML
    private Label ID;

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    @FXML
    private Button btnBack;

    public Medecin getMedecin() {
        return medecin;
    }

    public void setMedecin(Medecin medecin) {
        ID.setText(String.valueOf(medecin.getId()));
        this.medecin = medecin;
    }
    @FXML
    private TextArea commentTextArea;
    @FXML
    private Button submitButton;
    @FXML
    private VBox allAVIS;
    @FXML
    private MenuButton NOTE;
    private Avis avis;

    public Avis getAvis() {
        return avis;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MenuItem g1 = new MenuItem("1");
        g1.setOnAction(e -> {
            NOTE.setText(g1.getText());
        });
        MenuItem g2 = new MenuItem("2");
        g2.setOnAction(e -> {
            NOTE.setText(g2.getText());
        });
        MenuItem g3 = new MenuItem("3");
        g3.setOnAction(e -> {
            NOTE.setText(g3.getText());
        });
        MenuItem g4 = new MenuItem("4");
        g4.setOnAction(e -> {
            NOTE.setText(g4.getText());
        });
        MenuItem g5 = new MenuItem("5");
        g5.setOnAction(e -> {
            NOTE.setText(g5.getText());
        });
        NOTE.getItems().addAll(g1, g2, g3, g4, g5);
    }
@FXML
private void submitComment(ActionEvent event) throws SQLException {
    String comment = commentTextArea.getText();
    int rating = Integer.parseInt(NOTE.getText());
    Date date =new Date();
   Avis avis = new Avis();
    avis.setMedecin(medecin);
    
   Patient patient = new Patient();
patient.setId(334); // set the ID to a valid value
avis.setPatient(patient);
    avis.setText(comment);
    avis.setDate(date);
    avis.setNote(rating);

    AvisService avisService = new AvisService();
    avisService.ajouter(avis);

    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Avis ajouté");
    alert.setHeaderText(null);
    alert.setContentText("Votre avis a été ajouté avec succès !");
    alert.showAndWait();
   // refreshAvis();
}





    public void setAvis(List<Avis> avisList) {
        for (Avis avis : avisList) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("allAvis.fxml"));
                Node node = loader.load();
                AllAvisController aviController = loader.getController();
                aviController.setData(avis);
                allAVIS.getChildren().add(node);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    
    

    // Close the stage
    @FXML
    private void retour(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void refreshAvis() {
       // allAVIS.getChildren().clear();

    
    }
}
