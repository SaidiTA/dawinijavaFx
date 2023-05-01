/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import entities.Medecin;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import test.Dawini;

/**
 * FXML Controller class
 *
 * @author soumayaab
 */
public class ProfileMedecinController implements Initializable {

    @FXML
    private ImageView btnUploadImage;
    @FXML
    private TextField ID;
    
    @FXML
    private TextField NOM;
    
    @FXML
    private TextField PRENOM;
    
    @FXML
    private TextField EMAIL;
    
   
    @FXML
    private TextField TEL;
    
    @FXML
    private TextField FIXE;
    
   
    @FXML
    private TextField TITRE;
    
    
private Medecin medecin;
    private Button btncrosse;
    private ImageView btncross;
    @FXML
    private TextField TARIF;
    
    @FXML
    private TextField GOUV;
    
    @FXML
    private TextField SPEC;
    
    @FXML
    private TextField ADRESSE;
    @FXML
    private TextField CABINET;
    
    @FXML
    private TextField Genre;
    @FXML
    private Button btnBack;

    

    public Medecin getMedecin() {
        return medecin;
    }

    public void setMedecin(Medecin medecin) {
        ID.setText(String.valueOf(medecin.getId()));
        this.medecin = medecin;
        NOM.setText(medecin.getNom());
        PRENOM.setText(medecin.getPrenom());
        GOUV.setText(medecin.getGouvernorat());
        EMAIL.setText(medecin.getEmail());
        CABINET.setText(medecin.getAdresse_cabinet());
        TEL.setText(medecin.getTelephone());
        TITRE.setText(medecin.getTitre());
        Genre.setText(medecin.getSexe());
        ADRESSE.setText(medecin.getAdresse());
        FIXE.setText(medecin.getFixe());
        TARIF.setText(String.valueOf(medecin.getTarif()));
        SPEC.setText("Diabète");
        try {
            //Image image = new Image(imageUrl.getUrl());
            // Image image = new Image(getClass().getResourceAsStream("../images/account.png"));
            Image image = new Image(Dawini.class.getClass().getResource("/images/" + medecin.getImage()).toString());
            System.out.println("imageUrl: " + image);
            btnUploadImage.setImage(image);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }


private void handleButtonAction(ActionEvent event) throws IOException {
   

}

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
}
