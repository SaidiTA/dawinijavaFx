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
    @FXML
    private Button btncrosse;
    @FXML
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

    @FXML
    private void handleButtonAction(MouseEvent event) {
    }

    @FXML
private void handleButtonAction(ActionEvent event) throws IOException {
    if (event.getSource() == btncrosse || event.getSource() == btncross) {
        // Fermer la fenêtre actuelle
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
        
        // Ouvrir une nouvelle scène pour la page home
        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }


}}
