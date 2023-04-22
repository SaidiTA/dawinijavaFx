/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import entities.Medecin;
import entities.Patient;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.MedecinService;
import services.PatientService;

/**
 * FXML Controller class
 *
 * @author soumayaab
 */
public class SignUpController implements Initializable {

    @FXML
    private TextField NOM;
    @FXML
    private TextField PRENOM;
    @FXML
    private TextField EMAIL;
    @FXML
    private TextField TELEPHONE;
    @FXML
    private TextField CIN;
    @FXML
    private PasswordField PASSWORD;
    @FXML
    private PasswordField CONFIRME;
    @FXML
    private MenuButton GOUV;
    @FXML
    private MenuButton GENRE;
    @FXML
    private TextArea ADRESSE;
    @FXML
    private Button btn;
    @FXML
    private Text IMAGE;

    /**
     * Initializes the controller class.
     */
  @Override
    public void initialize(URL url, ResourceBundle rb) {
            MenuItem maleItem = new MenuItem("Homme");
    maleItem.setOnAction(e -> {
        GENRE.setText(maleItem.getText());
    });

    MenuItem femaleItem = new MenuItem("Femme");
    femaleItem.setOnAction(e -> {
        GENRE.setText(femaleItem.getText());
    });
 GENRE.getItems().addAll(maleItem, femaleItem);
    // Add items to Gouvernorat MenuButton
   
    MenuItem g1 = new MenuItem("Ariana");
    g1.setOnAction(e -> {
        GOUV.setText(g1.getText());
    });
    MenuItem g2 = new MenuItem("Zaghouen");
    g2.setOnAction(e -> {
        GOUV.setText(g2.getText());
    });
     MenuItem g3 = new MenuItem("Nabeul");
    g3.setOnAction(e -> {
        GOUV.setText(g3.getText());
    });
     MenuItem g4 = new MenuItem("Sousse");
    g4.setOnAction(e -> {
        GOUV.setText(g4.getText());
    });
     MenuItem g5 = new MenuItem("Mahdia");
    g5.setOnAction(e -> {
        GOUV.setText(g5.getText());
    });
    GOUV.getItems().addAll(g1, g2,g3,g4,g5);

    // Add items to Titre MenuButton
   
   
    
    }    
 

    @FXML
    private void createAccount(ActionEvent event) throws SQLException, IOException {
    if (NOM.getText().isEmpty() || PRENOM.getText().isEmpty() || CIN.getText().isEmpty() || GENRE.getText().isEmpty()
            || TELEPHONE.getText().isEmpty() || GOUV.getText().isEmpty() || ADRESSE.getText().isEmpty()
            || PASSWORD.getText().isEmpty() || CONFIRME.getText().isEmpty() 
            || EMAIL.getText().isEmpty()) {
        // Afficher un message d'erreur si un champ est vide
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Veuillez remplir tous les champs");
        alert.showAndWait();
        return;
    }
    String email = EMAIL.getText();
    String password = PASSWORD.getText();
    String nom = NOM.getText();
    String prenom = PRENOM.getText();
    int cin = Integer.parseInt(CIN.getText());
    String sexe = GENRE.getText();
    String telephone = TELEPHONE.getText();
        String gouvernorat = GOUV.getText();
         String adresse = ADRESSE.getText();
    String confirm_password = CONFIRME.getText();
  String image = IMAGE.getText();
   
  
    
    //String specialite = Specialite.getText();
   
     if (!password.equals(confirm_password)) {
        // Afficher un message d'erreur si les mots de passe ne correspondent pas
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Les mots de passe ne correspondent pas");
        alert.showAndWait();
        return;
    
    }
    
    
    Patient patient = new Patient(email, prenom, nom, password, cin, sexe, telephone, gouvernorat, adresse, confirm_password, image);
    PatientService patientService = new PatientService();
    
   
    
    
    patientService.ajouter(patient);
    
    // Afficher un message de succès et vider les champs de saisie
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Succès");
    alert.setHeaderText("Le compte a été créé avec succès !");
    alert.showAndWait();
    
   
   
  
  
    PASSWORD.setText(patient.getPassword());
    CONFIRME.setText(patient.getConfirm_password());
   
   
    
    
    
   
  
   
        
         NOM.setText(patient.getNom());
        PRENOM.setText(patient.getPrenom());
        GOUV.setText(patient.getGouvernorat());
        EMAIL.setText(patient.getEmail());
      
        TELEPHONE.setText(patient.getTelephone());
      
        GENRE.setText(patient.getSexe());
        ADRESSE.setText(patient.getAdresse());
     
       CIN.setText(String.valueOf(patient.getCin()));
     
        
       IMAGE.setText(patient.getImage());
       
      
    Parent root = FXMLLoader.load(getClass().getResource("/gui/SignInUser.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
   
    
}

    
    
}
