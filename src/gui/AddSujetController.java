/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Specialites;
import entities.Sujet;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;
import services.specialitesService;
import services.sujetService;
import util.MyDB;

/**
 * FXML Controller class
 *
 * @author jlidi
 */
public class AddSujetController implements Initializable {

    @FXML
    private MenuButton Specialite;
    @FXML
    private Button btncrosse;
    @FXML
    private ImageView btncross;
    @FXML
    private TextArea btnmessage;
    @FXML
    private HTMLEditor btndescription;
    @FXML
    private Button AjouterSujet;
    @FXML
    private TextArea btntitle;
        @FXML

        private ComboBox<String> tfCat;


    /**
     * Initializes the controller class.
     */
    @Override
   public void initialize(URL url, ResourceBundle rb) {
        try {
            Connection cnx = MyDB.getInstance().getCnx();
            String query = "SELECT nom FROM Specialites";
            PreparedStatement pst = cnx.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String categoryName = rs.getString("nom");
                tfCat.getItems().add(categoryName);
            }
            pst.close();
            rs.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

   

    @FXML
    private void handleButtonAction(ActionEvent event) {
    }

    
        
   
    @FXML
    private void AjouterSujet(ActionEvent event) throws SQLException {
        
        specialitesService cs=new specialitesService();
          String title = btntitle.getText().trim();
                    String message = btnmessage.getText().trim();

    String description = btndescription.getHtmlText().trim();
int specialites_id = cs.recupererBynom(tfCat.getValue()).getId();

    if (title.isEmpty() || description.isEmpty()) {
        // Show an error message to the user
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir tous les champs avant de continuer.");
        alert.showAndWait();
    } else if (countWords(message) > 50) {
        // Show an error message to the user
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Le Message  ne doit pas dépasser 200 mots.");
        alert.showAndWait();
    } else if (countWords(description) > 100) {
        // Show an error message to the user
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("La description ne doit pas dépasser 200 mots.");
        alert.showAndWait();
    }else {
        // Create the new Article object and add it to the database
        Sujet sujet = new Sujet(message,title, description,specialites_id);
        sujetService service = new sujetService();
        service.ajouter(sujet);

        // Close the stage
        Stage stage = (Stage) btntitle.getScene().getWindow();
        stage.close();
    }
}

private int countWords(String text) {
    // Split the text into words using whitespace as the delimiter
    String[] words = text.split("\\s+");

    // Return the number of words
    return words.length;
}    
    
    

   
    

    @FXML
    private void handleButtonAction(MouseEvent event) {
    }
    
}
