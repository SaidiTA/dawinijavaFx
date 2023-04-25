/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import entities.Assistant;
import entities.Medecin;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import services.AssistantService;
import services.MedecinService;

/**
 * FXML Controller class
 *
 * @author soumayaab
 */
public class ItemAssController implements Initializable {

    @FXML
    private Label btnid;
    @FXML
    private Label lblNom;
    @FXML
    private Label lblPrenom;
    @FXML
    private Label lblEmail;
    @FXML
    private Label lblTelephone;
    @FXML
    private Label lblgenre;
    @FXML
    private Label lblgouv;
    @FXML
    private Label lblAdresse;
    @FXML
    private HBox lblAction;
    @FXML
    private Button btnshow;
    @FXML
    private Button btnDelete;
private Assistant assistant;
    @FXML
    private Label lblMedecin;

    public Assistant getAssistant() {
        return assistant;
    }

    public void setAssistant(Assistant assistant) {
        this.assistant = assistant;
    }

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Affiche(ActionEvent event) throws IOException, SQLException {
        int id = Integer.parseInt(btnid.getText());
        AssistantService ms = new AssistantService();
        try {
            Assistant assistant = ms.recupererById(id);

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/infoAssistant.fxml"));
            Parent parent = fxmlLoader.load();
            InfoAssisController controller = fxmlLoader.getController();
            
            
            controller.setAssistant(assistant);

            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();

        } catch (IOException ex) {
            System.out.println("Erreur lors du chargement de la fenêtre d'affichage des informations du médecin: " + ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la récupération des informations du médecin: " + ex.getMessage());
        }
    }

   void handleButtonClick(ActionEvent event) throws SQLException {
        try {
            // Récupération des données du dossier sélectionné

              int id = Integer.parseInt(btnid.getText());
            AssistantService dc = new AssistantService();
            List<Assistant> spec = dc.recuperer();
            Assistant assistant = null;
            for (Assistant d : spec) {
                if (d.getId() == id) {
                    assistant = d;
                    break;
                }
            }
           


            // Chargement de la fenêtre de modification
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/ModifierAssistant.fxml"));
            Parent parent = fxmlLoader.load();
            ModifierMedecinController controller = fxmlLoader.getController();

            // Transmission des données du dossier à la fenêtre de modification
           // controller.setData(assistant);

            // Affichage de la fenêtre de modification
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();

        } catch (IOException ex) {

        }

    }
    @FXML
    private void supprimer(ActionEvent event) throws SQLException {
          int id = Integer.parseInt(btnid.getText());
        AssistantService dc = new AssistantService();
        List<Assistant> assistants = dc.recuperer();
        for (Assistant d : assistants) {
            if (d.getId() == id) {
                dc.supprimer(d);

                break;

            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Suppression réussie");
            alert.setHeaderText(null);
            alert.setContentText("Le médecin a été supprimé avec succès !");
            alert.showAndWait();
        }
    }

    void setData(Assistant assistant) {
        int id = assistant.getId();
        btnid.setText(Integer.toString(id));

        lblNom.setText(assistant.getNom());
        lblPrenom.setText(assistant.getPrenom());
        lblEmail.setText(assistant.getEmail());
        lblTelephone.setText(assistant.getTelephone());
        lblgouv.setText(assistant.getGouvernorat());
        //lblspecialites.setText(medecin.getSpecialite());
        lblgenre.setText(assistant.getSexe());
        lblAdresse.setText(assistant.getAdresse());
 Medecin medecin = assistant.getMedecin();
        
            lblMedecin.setText(medecin.getNom() + " " + medecin.getPrenom());
       
        }

    }
    

