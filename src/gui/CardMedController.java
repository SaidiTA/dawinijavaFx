/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import entities.Medecin;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import services.MedecinService;
import test.Dawini;

/**
 * FXML Controller class
 *
 * @author soumayaab
 */
public class CardMedController implements Initializable {

    @FXML
    private VBox post;
    @FXML
    private TextField ID;
    @FXML
    private Label MED;
    @FXML
    private Label Location;
    @FXML
    private Label Tarif;
    @FXML
    private Button VoirPlus;
    private Medecin medecin;
    @FXML
    private ImageView postImage;

    public Medecin getMedecin() {
        return medecin;
    }

    public void setMedecin(Medecin medecin) {
        this.medecin = medecin;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void VoirPlus(ActionEvent event) throws SQLException {
         int id = Integer.parseInt(ID.getText());
        MedecinService a = new MedecinService();
        try {
            Medecin medecin = a.recupererById(id);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/ProfileMedecin.fxml"));
        Parent root = loader.load();
        ProfileMedecinController controller = loader.getController();
        controller.setMedecin(medecin);
        Scene scene = new Scene(root);
       
        
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    }

    void setData(Medecin medecin) {
        int id=medecin.getId();
        
        ID.setText(Integer.toString(id));
        
        MED.setText(medecin.getNom() + " " + medecin.getPrenom());
                Location.setText(medecin.getGouvernorat());

               
 Tarif.setText(String.valueOf(medecin.getTarif())+" "+"DT");
  
       

//if (imageUrl != null) {
        try {
            //Image image = new Image(imageUrl.getUrl());
            // Image image = new Image(getClass().getResourceAsStream("../images/account.png"));
            Image image = new Image(Dawini.class.getClass().getResource("/images/" + medecin.getImage()).toString());
            System.out.println("imageUrl: " + image);
            postImage.setImage(image);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
