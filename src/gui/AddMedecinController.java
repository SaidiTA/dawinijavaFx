/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package gui;

import entities.Medecin;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.MedecinService;

/**
 *
 * @author soumayaab
 */
public class AddMedecinController implements Initializable {
    
     @FXML
    private Button btncrosse;

    @FXML
    private ImageView btncross;
    @FXML
    private MenuButton Genre;
    @FXML
    private TextArea Adresse_Cabinet;
    @FXML
    private MenuButton Cnam;
    @FXML
    private MenuButton Titre;
    //@FXML
    //private MenuButton Specialite;
    @FXML
    private TextArea Adresse;
    @FXML
    private TextArea Diplome_Formation;
    @FXML
    private MenuButton Gouvernorat;
    @FXML
    private TextArea Nom;
    @FXML
    private TextArea Prenom;
    @FXML
    private TextArea Telephone;
    @FXML
    private TextArea Email;
    @FXML
    private TextArea Fixe;
    @FXML
    private TextArea Tarif;
    @FXML
    private TextArea Cin;
    @FXML
    private Button btnAdd;
    @FXML
    private PasswordField Password;
    @FXML
    private PasswordField ConfirmPassword;
    @FXML
    private Text upimage;
    @FXML
    private MenuButton Specialite;
    
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
      if(event.getSource() == btncrosse) {
        // Get the Stage that contains the button
        Stage stage = (Stage) btncrosse.getScene().getWindow();
        
        // Close the stage
        stage.close();
      
    }
       if(event.getSource() == btncross) {
        // Get the Stage that contains the button
        Stage stage = (Stage) btncross.getScene().getWindow();
        
        // Close the stage
        stage.close();
      
    }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     MenuItem maleItem = new MenuItem("Homme");
    maleItem.setOnAction(e -> {
        Genre.setText(maleItem.getText());
    });

    MenuItem femaleItem = new MenuItem("Femme");
    femaleItem.setOnAction(e -> {
        Genre.setText(femaleItem.getText());
    });

    Genre.getItems().addAll(maleItem, femaleItem);
    
    MenuItem ouiItem = new MenuItem("OUI");
    ouiItem.setOnAction(e -> {
        Cnam.setText(ouiItem.getText());
    });

    MenuItem NonItem = new MenuItem("Non");
    NonItem.setOnAction(e -> {
        Cnam.setText(NonItem.getText());
    });

    Cnam.getItems().addAll(ouiItem, NonItem);


    // Add items to Gouvernorat MenuButton
   
    MenuItem g1 = new MenuItem("Ariana");
    g1.setOnAction(e -> {
        Gouvernorat.setText(g1.getText());
    });
    MenuItem g2 = new MenuItem("Zaghouen");
    g2.setOnAction(e -> {
        Gouvernorat.setText(g2.getText());
    });
     MenuItem g3 = new MenuItem("Nabeul");
    g3.setOnAction(e -> {
        Gouvernorat.setText(g3.getText());
    });
     MenuItem g4 = new MenuItem("Sousse");
    g4.setOnAction(e -> {
        Gouvernorat.setText(g4.getText());
    });
     MenuItem g5 = new MenuItem("Mahdia");
    g5.setOnAction(e -> {
        Gouvernorat.setText(g5.getText());
    });
    Gouvernorat.getItems().addAll(g1, g2,g3,g4,g5);

    // Add items to Titre MenuButton
   
    MenuItem t1 = new MenuItem("Medecin");
    t1.setOnAction(e -> {
        Titre.setText(t1.getText());
    });
    MenuItem t2 = new MenuItem("Professeur");
    t2.setOnAction(e -> {
        Titre.setText(t2.getText());
    });
    Titre.getItems().addAll(t1, t2);

    
}

    @FXML
    private void addMedecin(ActionEvent event) {
    // Vérifier que tous les champs sont remplis
    if (Nom.getText().isEmpty() || Prenom.getText().isEmpty() || Cin.getText().isEmpty() || Genre.getText().isEmpty()
            || Telephone.getText().isEmpty() || Gouvernorat.getText().isEmpty() || Adresse.getText().isEmpty()
            || Password.getText().isEmpty() || ConfirmPassword.getText().isEmpty() || Titre.getText().isEmpty()
            || Adresse_Cabinet.getText().isEmpty() || Fixe.getText().isEmpty() || Diplome_Formation.getText().isEmpty()
            || Tarif.getText().isEmpty() || Cnam.getText().isEmpty() || Email.getText().isEmpty()) {
        // Afficher un message d'erreur si un champ est vide
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Veuillez remplir tous les champs");
        alert.showAndWait();
        return;
    }

    // Ajouter le médecin s'il n'y a pas d'erreur
    String email = Email.getText();
    String password = Password.getText();
    String nom = Nom.getText();
    String prenom = Prenom.getText();
    int cin = Integer.parseInt(Cin.getText());
    String sexe = Genre.getText();
    String telephone = Telephone.getText();
    String gouvernorat = Gouvernorat.getText();
    String adresse = Adresse.getText();
    String confirm_password = ConfirmPassword.getText();
    String image = upimage.getText();
    String titre = Titre.getText();
    String adresse_cabinet = Adresse_Cabinet.getText();
    String fixe = Fixe.getText();
    String diplome_formation = Diplome_Formation.getText();
    float tarif = Float.parseFloat(Tarif.getText());
    boolean cnam = Boolean.parseBoolean(Cnam.getText());

    if (!password.equals(confirm_password)) {
        // Afficher un message d'erreur si les mots de passe ne correspondent pas
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Les mots de passe ne correspondent pas");
        alert.showAndWait();
        return;
    }

    // Ajouter le médecin à la base de données
    Medecin medecin = new Medecin(email, prenom, nom, password, cin, sexe, telephone, gouvernorat, adresse,
            confirm_password, image, titre, adresse_cabinet, fixe, diplome_formation, tarif, cnam);
    MedecinService medecinService = new MedecinService();
    try {
        medecinService.ajouter(medecin);
        System.out.println("Médecin ajouté avec succès !");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Succès");
    alert.setHeaderText("Le compte a été créé avec succès !");
    alert.showAndWait();

    // Fermer la fenêtre d'ajout de médecin
    Stage stage = (Stage) btnAdd.getScene().getWindow();
    stage.close();
}

    
}
    

  //

  
    

