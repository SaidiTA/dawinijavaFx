/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package gui;

import entities.Medecin;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
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
    private Text upimage;
    @FXML
    private TextArea tariff;
   
    
    public void setData(Medecin medecin) {
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
      tariff.setText(String.valueOf(medecin.getTarif()));
        
        diplome.setText(medecin.getDiplome_formation());
       upimage.setText(medecin.getImage());
       
       boolean cnam = Boolean.parseBoolean(lblCnam.getText());
       if (!tariff.getText().isEmpty()) {
   
    
}

    
      
        if (!cinn.getText().isEmpty()) {
    int cin = Integer.parseInt(cinn.getText());
}
        
       
        
       
}
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

    
    }    

    @FXML
   private void modifyMed(ActionEvent event) {
    Medecin t = new Medecin();
    t.setEmail(Email.getText());
    t.setNom(Nom.getText());
    t.setPrenom(Prenom.getText());
    t.setCin(Integer.parseInt(cinn.getText()));
    t.setSexe(lblGenre.getText());
    t.setTelephone(Telephone.getText());
    t.setGouvernorat(gouv.getText());
    t.setAdresse(adress.getText());
    t.setImage(upimage.getText());
    t.setTitre(lbltitre.getText());
    t.setAdresse_cabinet(cabinet.getText());
    t.setFixe(Fixe.getText());
    t.setDiplome_formation(diplome.getText());
    t.setTarif(Float.parseFloat(tariff.getText()));
    t.setCnam(Boolean.parseBoolean(lblCnam.getText()));
         
    
    boolean cnam = Boolean.parseBoolean(lblCnam.getText());

    MedecinService medecinService = new MedecinService();

    try {
        medecinService.modifier(t);
        System.out.println("Médecin modifié avec succès !");
    } catch (SQLException e) {
        System.err.println("Erreur lors de la modification du médecin : " + e.getMessage());
    }
}

     
    
    // Modifier le dossier correspondant dans la base de données
       

    
       
       
        
    // Close the window
    
}

  
 
  


