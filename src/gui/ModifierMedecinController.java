/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package gui;

import entities.Medecin;
import entities.User;
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
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.MedecinService;

/**
 *
 * @author soumayaab
 */
public class ModifierMedecinController implements Initializable {
    
   @FXML
    private Button btncrosse;

    @FXML
    private ImageView btncross;
    @FXML
    private MenuButton lblGenre;
    @FXML
    private TextArea cabinet;
    @FXML
    private MenuButton lblCnam;
    @FXML
    private MenuButton lbltitre;
    @FXML
    private MenuButton spes;
    @FXML
    private TextArea adress;
    @FXML
    private TextArea diplome;
    @FXML
    private MenuButton gouv;
    @FXML
    private Button btnModifier;
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
    private TextArea cinn;
   
    @FXML
    private TextArea tariff;
    private Medecin medecin;
    @FXML
    private MenuButton Status;
    @FXML
    private Button btnUploadImage;

    public Medecin getMedecin() {
        return medecin;
    }

    public void setMedecin(Medecin medecin) {
        this.medecin = medecin;
    }
    
    public void setData(Medecin medecin) {
        this.medecin = medecin;
        Nom.setText(medecin.getNom());
        Prenom.setText(medecin.getPrenom());
        gouv.setText(medecin.getGouvernorat());
        Email.setText(medecin.getEmail());
        cabinet.setText(medecin.getAdresse_cabinet());
        Telephone.setText(medecin.getTelephone());
        lbltitre.setText(medecin.getTitre());
        lblGenre.setText(medecin.getSexe());
        adress.setText(medecin.getAdresse());
        Fixe.setText(medecin.getFixe());
       cinn.setText(String.valueOf(medecin.getCin()));
       lblCnam.setText(String.valueOf(lblCnam.getText()));
      tariff.setText(String.valueOf(medecin.getTarif()));
        
        diplome.setText(medecin.getDiplome_formation());
       btnUploadImage.setText(medecin.getImage());
       
       int Stat=medecin.getEnabled();
      if(Stat==1){
        Status.setText("Activé");
    
    }else{
      Status.setText("Désactivé");
    
    }
      

    
      
        
       
        
       
}
   @FXML
    private void handleButtonAction(ActionEvent event) {
       if (event.getSource() == btncrosse) {
            // Get the Stage that contains the button
            Stage stage = (Stage) btncrosse.getScene().getWindow();

            // Close the stage
            stage.close();

        }
        if (event.getSource() == btncross) {
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
        lblGenre.setText(maleItem.getText());
    });

    MenuItem femaleItem = new MenuItem("Femme");
    femaleItem.setOnAction(e -> {
        lblGenre.setText(femaleItem.getText());
    });

    lblGenre.getItems().addAll(maleItem, femaleItem);
    
    MenuItem ouiItem = new MenuItem("OUI");
    ouiItem.setOnAction(e -> {
        lblCnam.setText(ouiItem.getText());
    });

    MenuItem NonItem = new MenuItem("Non");
    NonItem.setOnAction(e -> {
        lblCnam.setText(NonItem.getText());
    });

    lblCnam.getItems().addAll(ouiItem, NonItem);


    // Add items to Gouvernorat MenuButton
   
    MenuItem g1 = new MenuItem("Ariana");
    g1.setOnAction(e -> {
        gouv.setText(g1.getText());
    });
    MenuItem g2 = new MenuItem("Zaghouen");
    g2.setOnAction(e -> {
        gouv.setText(g2.getText());
    });
     MenuItem g3 = new MenuItem("Nabeul");
    g3.setOnAction(e -> {
        gouv.setText(g3.getText());
    });
     MenuItem g4 = new MenuItem("Sousse");
    g4.setOnAction(e -> {
        gouv.setText(g4.getText());
    });
     MenuItem g5 = new MenuItem("Mahdia");
    g5.setOnAction(e -> {
        gouv.setText(g5.getText());
    });
    gouv.getItems().addAll(g1, g2,g3,g4,g5);

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

    MenuItem AciverItem = new MenuItem("Activé");
    AciverItem.setOnAction(e -> {
        Status.setText(AciverItem.getText());
    });

    MenuItem DesactiveItem = new MenuItem("Desactivé");
    DesactiveItem.setOnAction(e -> {
        Status.setText(DesactiveItem.getText());
    });

    Status.getItems().addAll(AciverItem, DesactiveItem);
    
    }    

    @FXML
   private void modifyMed(ActionEvent event) {
    MedecinService sc = new MedecinService();
    
   // id du médecin à modifier
    medecin.setEmail(Email.getText());
    medecin.setNom(Nom.getText());
    medecin.setPrenom(Prenom.getText());
    medecin.setCin(Integer.parseInt(cinn.getText()));
    medecin.setSexe(lblGenre.getText());
    medecin.setTelephone(Telephone.getText());
    medecin.setGouvernorat(gouv.getText());
    medecin.setAdresse(adress.getText());
   // Ajouter ce champ si nécessaire
    medecin.setImage(btnUploadImage.getText());
    medecin.setTitre(lbltitre.getText());
    medecin.setAdresse_cabinet(cabinet.getText());
    medecin.setFixe(Fixe.getText());
    medecin.setDiplome_formation(diplome.getText());
    medecin.setTarif(Float.parseFloat(tariff.getText()));
    medecin.setCnam(Boolean.parseBoolean(lblCnam.getText()));
    String Stat=Status.getText();
        
    if(Stat.equals("Activé")){
        
    medecin.setEnabled(1);
    
    }else{
     medecin.setEnabled(0);
    
    }
    
     
  
       
        
 


        
   

    try {
        sc.modifier(medecin);
        System.out.println("Médecin modifié avec succès !");
    } catch (SQLException e) {
        System.err.println("Erreur lors de la modification du médecin : " + e.getMessage());
    }
       Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Succès");
    alert.setHeaderText("Le compte a été créé avec succès !");
    alert.showAndWait();

    // Fermer la fenêtre d'ajout de médecin
    Stage stage = (Stage) btnModifier.getScene().getWindow();
    stage.close();
}

     
    
    // Modifier le dossier correspondant dans la base de données

   

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
       
       
        
    // Close the window


  
 
  


