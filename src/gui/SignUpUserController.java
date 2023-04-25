/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package gui;

import entities.Medecin;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;
import services.MedecinService;

/**
 *
 * @author soumayaab
 */
public class SignUpUserController implements Initializable {
    
    private Label label;
    @FXML
    private TextField lblNom;
    @FXML
    private TextField lblPrenom;
    @FXML
    private TextField lblEmail;
    @FXML
    private TextField lblTelephone;
    @FXML
    private TextField lblcin;
    @FXML
    private PasswordField lblpassword;
    @FXML
    private PasswordField lblconfirm;
    @FXML
    private MenuButton lblgouv;
    @FXML
    private MenuButton lblgenre;
    @FXML
    private TextArea lblcabinet;
    @FXML
    private Button btn;
   
    @FXML
    private MenuButton lblcnam;
    @FXML
    private MenuButton lbltitre;
    @FXML
    private MenuButton lblspec;
    @FXML
    private TextField lbltarif;
    @FXML
    private TextField lblFixe;
    @FXML
    private TextArea lbladress;
    @FXML
    private TextArea lbldiplome;
    @FXML
    private Button btnUploadImage;
 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            MenuItem maleItem = new MenuItem("Homme");
    maleItem.setOnAction(e -> {
        lblgenre.setText(maleItem.getText());
    });

    MenuItem femaleItem = new MenuItem("Femme");
    femaleItem.setOnAction(e -> {
        lblgenre.setText(femaleItem.getText());
    });

    lblgenre.getItems().addAll(maleItem, femaleItem);
    
    MenuItem ouiItem = new MenuItem("OUI");
    ouiItem.setOnAction(e -> {
        lblcnam.setText(ouiItem.getText());
    });

    MenuItem NonItem = new MenuItem("Non");
    NonItem.setOnAction(e -> {
        lblcnam.setText(NonItem.getText());
    });

    lblcnam.getItems().addAll(ouiItem, NonItem);


    // Add items to Gouvernorat MenuButton
   
    MenuItem g1 = new MenuItem("Ariana");
    g1.setOnAction(e -> {
        lblgouv.setText(g1.getText());
    });
    MenuItem g2 = new MenuItem("Zaghouen");
    g2.setOnAction(e -> {
        lblgouv.setText(g2.getText());
    });
     MenuItem g3 = new MenuItem("Nabeul");
    g3.setOnAction(e -> {
        lblgouv.setText(g3.getText());
    });
     MenuItem g4 = new MenuItem("Sousse");
    g4.setOnAction(e -> {
        lblgouv.setText(g4.getText());
    });
     MenuItem g5 = new MenuItem("Mahdia");
    g5.setOnAction(e -> {
        lblgouv.setText(g5.getText());
    });
    lblgouv.getItems().addAll(g1, g2,g3,g4,g5);

    // Add items to Titre MenuButton
   
    MenuItem t1 = new MenuItem("Medecin");
    t1.setOnAction(e -> {
        lbltitre.setText(t1.getText());
    });
    MenuItem t2 = new MenuItem("Professeur");
    t2.setOnAction(e -> {
        lbltitre.setText(t2.getText());
    });
    lbltitre.getItems().addAll(t1, t2);

   
    
    }    

  @FXML
private void createAccount(ActionEvent event) throws SQLException, IOException {
      // Vérifier que tous les champs sont remplis
    if (lblNom.getText().isEmpty() || lblPrenom.getText().isEmpty() || lblcin.getText().isEmpty() || lblgenre.getText().isEmpty()
            || lblTelephone.getText().isEmpty() || lblgouv.getText().isEmpty() || lbladress.getText().isEmpty()
            || lblpassword.getText().isEmpty() || lblconfirm.getText().isEmpty() || lbltitre.getText().isEmpty()
            || lblcabinet.getText().isEmpty() || lblFixe.getText().isEmpty() || lbldiplome.getText().isEmpty()
            || lbltarif.getText().isEmpty() || lblcnam.getText().isEmpty() || lblEmail.getText().isEmpty()) {
        // Afficher un message d'erreur si un champ est vide
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Veuillez remplir tous les champs");
        alert.showAndWait();
        return;
    }
    String email = lblEmail.getText();
    String password = lblpassword.getText();
    String nom = lblNom.getText();
    String prenom = lblPrenom.getText();
    int cin = Integer.parseInt(lblcin.getText());
    String sexe = lblgenre.getText();
    String telephone = lblTelephone.getText();
        String gouvernorat = lblgouv.getText();
         String adresse = lbladress.getText();
    String confirm_password = lblconfirm.getText();
  String image = btnUploadImage.getText();
    String titre = lbltitre.getText();
    String adresse_cabinet = lblcabinet.getText();
    String fixe = lblFixe.getText();
    String diplome_formation = lbldiplome.getText();
     float tarif = Float.parseFloat(lbltarif.getText());
    boolean cnam = Boolean.parseBoolean(lblcnam.getText());
    //String specialite = Specialite.getText();

     if (!password.equals(confirm_password)) {
        // Afficher un message d'erreur si les mots de passe ne correspondent pas
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Les mots de passe ne correspondent pas");
        alert.showAndWait();
        return;
    
    }
    
    
   
   
    Medecin medecin = new Medecin(email, prenom, nom, password, cin, sexe, telephone, gouvernorat, adresse, confirm_password, image, titre,adresse_cabinet,fixe,diplome_formation, tarif, cnam);
    MedecinService medecinService = new MedecinService();
    medecinService.ajouter(medecin);
    
  
    
    // Afficher un message de succès et vider les champs de saisie
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Succès");
    alert.setHeaderText("Le compte a été créé avec succès !");
    alert.showAndWait();
    
   
   
  
  
    lblpassword.setText(medecin.getPassword());
    lblconfirm.setText(medecin.getConfirm_password());
   
   
    
    lblcnam.setText("CNAM");
    lbltitre.setText(medecin.getTitre());
    lblspec.setText("Spécialité");
    
   
  
   
         lbltarif.setText(String.valueOf(medecin.getTarif()));
         lblNom.setText(medecin.getNom());
        lblPrenom.setText(medecin.getPrenom());
        lblgouv.setText(medecin.getGouvernorat());
        lblEmail.setText(medecin.getEmail());
        lblcabinet.setText(medecin.getAdresse_cabinet());
        lblTelephone.setText(medecin.getTelephone());
        lbltitre.setText(medecin.getTitre());
        lblgenre.setText(medecin.getSexe());
        lbladress.setText(medecin.getAdresse());
        lblFixe.setText(medecin.getFixe());
       lblcin.setText(String.valueOf(medecin.getCin()));
     
        lbldiplome.setText(medecin.getDiplome_formation());
       btnUploadImage.setText(medecin.getImage());
       
      
        Parent root = FXMLLoader.load(getClass().getResource("/gui/SignInUser.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
   
    
}

    @FXML
    private void handleUploadImage(ActionEvent event) {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Choose Image File");

    // Set the extension filter
    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.jpeg", "*.png");
    fileChooser.getExtensionFilters().add(extFilter);

    // Show the file chooser dialog
    File file = fileChooser.showOpenDialog(btnUploadImage.getScene().getWindow());

    if (file != null) {
        // Display the selected file name
        btnUploadImage.setText(file.getName());

        // Save the selected file to the "images" directory in the project folder
        Path imagePath = Paths.get("images", file.getName());
        try {
            Files.copy(file.toPath(), imagePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

    
      
      
        
       

}
