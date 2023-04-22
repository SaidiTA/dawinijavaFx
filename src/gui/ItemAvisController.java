/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package gui;

import entities.Avis;
import entities.Medecin;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import services.AvisService;
import services.MedecinService;

/**
 *
 * @author soumayaab
 */
public class ItemAvisController implements Initializable {

    @FXML
    private Label lblid;
    @FXML
    private Label lblnote;
    @FXML
    private Label lblcomment;
    @FXML
    private Label lbldate;
    @FXML
    private Label lblmedecin;
    @FXML
    private Label lblpat;
    @FXML
    private HBox lblAction;
    @FXML
    private Button btnshow;
    @FXML
    private Button btnModify;
    @FXML
    private Button btnDelete;

    private AvisService avisService;
    private Avis avis;

    public void setAvisService(AvisService avisService) {
        this.avisService = avisService;
    }

    public void setAvis(Avis avis) {
        this.avis = avis;
        setData(avis);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Ne rien faire pour l'instant
    }

    @FXML
    private void Affiche(ActionEvent event) {
         int id = Integer.parseInt(lblid.getText());
        AvisService ms = new AvisService();
        try {
            Avis avis = ms.recupererById(id);

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/infoAvis.fxml"));
            Parent parent = fxmlLoader.load();
            InfoAvisController controller = fxmlLoader.getController();
            controller.setAvis(avis);

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
        
        
        
        
        
        
        
        
      
    

    @FXML
    private void handleButtonClick(ActionEvent event) throws SQLException {
        try {
            // Récupération des données du dossier sélectionné

            int id = Integer.parseInt(lblid.getText());
            AvisService dc = new AvisService();
            List<Avis> spec = dc.recuperer();
            Avis avis = null;
            for (Avis d : spec) {
                if (d.getId() == id) {
                    avis = d;
                    break;
                }
            }

            // Chargement de la fenêtre de modification
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/ModifierAvis.fxml"));
            Parent parent = fxmlLoader.load();
            ModifierAvisController controller = fxmlLoader.getController();

            // Transmission des données du dossier à la fenêtre de modification
            controller.setData(avis);

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
         int id = Integer.parseInt(lblid.getText());
        AvisService dc = new AvisService();
        List<Avis> avi = dc.recuperer();
        for (Avis d : avi) {
            if (d.getId() == id) {
                dc.supprimer(d);

                break;

            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Suppression réussie");
            alert.setHeaderText(null);
            alert.setContentText("L'avis  a été supprimé avec succès !");
            alert.showAndWait();
        }
    }

    void setData(Avis avis) {
          int id = avis.getId();
        lblid.setText(String.valueOf(avis.getId()));
        lblnote.setText(String.valueOf(avis.getNote()));
        lblcomment.setText(avis.getText());
        lbldate.setText(avis.getDate().toString());
        lblmedecin.setText(avis.getMedecin().getNom() + " " + avis.getMedecin().getPrenom());
        lblpat.setText(avis.getPatient().getNom() + " " + avis.getPatient().getPrenom());
    }
}