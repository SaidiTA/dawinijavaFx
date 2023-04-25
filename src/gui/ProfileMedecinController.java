/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import entities.Medecin;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import test.Dawini;

/**
 * FXML Controller class
 *
 * @author soumayaab
 */
public class ProfileMedecinController implements Initializable {

    @FXML
    private TextArea commentTextArea;
    @FXML
    private Button submitButton;
    @FXML
    private VBox allComments;
    @FXML
    private ImageView btnUploadImage;
    @FXML
    private Text ID;
    @FXML
    private Text NOM;
    @FXML
    private Text PRENOM;
    @FXML
    private Text EMAIL;
   
    @FXML
    private Text TEL;
    @FXML
    private Text FIXE;
   
    @FXML
    private Text TITRE;
    
    @FXML
    private Text diplome;
private Medecin medecin;
    @FXML
    private Button btncrosse;
    @FXML
    private ImageView btncross;
    @FXML
    private Text TARIF;
    @FXML
    private Text GOUV;
    @FXML
    private Text SPEC;
    @FXML
    private Text ADRESSE;
    @FXML
    private Text CABINET;

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
        
        ADRESSE.setText(medecin.getAdresse());
        FIXE.setText(medecin.getFixe());
     
     FIXE.setText(medecin.getFixe());
      
      TARIF.setText(String.valueOf(medecin.getTarif()));
        
        SPEC.setText("");
      
       
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
    private void submitComment(ActionEvent event) {
       
    }

    @FXML
    private void handleButtonAction(MouseEvent event) {
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
    }
    
}
