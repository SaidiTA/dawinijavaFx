/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import entities.Avis;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.AvisService;

/**
 * FXML Controller class
 *
 * @author soumayaab
 */
public class ModifierAvisController implements Initializable {

    @FXML
    private Button btnModifier;
    @FXML
    private Button btncrosse;
    @FXML
    private ImageView btncross;
    @FXML
    private MenuButton Stat;
    private Avis avis;
    public Avis getAvis() {
        return avis;
    }

    public void setAvis(Avis avis) {
        this.avis = avis;
    }
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           MenuItem AciverItem = new MenuItem("Activer");
    AciverItem.setOnAction(e -> {
        Stat.setText(AciverItem.getText());
    });

    MenuItem DesactiveItem = new MenuItem("Desactiver");
    DesactiveItem.setOnAction(e -> {
        Stat.setText(DesactiveItem.getText());
    });

    Stat.getItems().addAll(AciverItem, DesactiveItem);
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

   @FXML
private void modifier(ActionEvent event) throws SQLException {   
    AvisService avi = new AvisService();
    String status=Stat.getText();
    avis.setStatut(status);
      int id = avis.getId();
  try {
        avi.modifierStatut(id,status);
        System.out.println("Statut modifié avec succès !");
    } catch (SQLException e) {
        System.err.println("Erreur lors de la modification du Statut : " + e.getMessage());
    }
   
       Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Succès");
    alert.setHeaderText("Le compte a été créé avec succès !");
    alert.showAndWait();

    // Fermer la fenêtre d'ajout de médecin
    Stage stage = (Stage) btnModifier.getScene().getWindow();
    stage.close();
}

    void setData(Avis avis) {
         this.avis = avis;
        Stat.setText(avis.getStatut());
    }

}

    
