/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Consulation;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.stage.Stage;
import services.ConsulationService;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class ModifierConsultationController implements Initializable {

    private int patient;
    private Consulation cons;

    @FXML
    private DatePicker heureD;
    @FXML
    private DatePicker heureF;
    @FXML
    private Button Enregistrer;
    @FXML
    private Button retour;

    public Consulation getCons() {
        return cons;
    }

    public void setCons(Consulation cons) {
        this.cons = cons;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void modifierconsultation() throws IOException {
        try {
            // On crée une instance de ConsulationService pour pouvoir accéder à la méthode "modifier"

            ConsulationService consulationService = new ConsulationService();
            // On met à jour les attributs de l'instance de Consulation avec les nouvelles valeurs

            cons.setHeuredebut(Timestamp.valueOf(heureD.getValue().atTime(LocalTime.MIN)));
            cons.setHeurefin(Timestamp.valueOf(heureF.getValue().atTime(LocalTime.MIN)));
            // On appelle la méthode "modifier" pour mettre à jour la consultation dans la base de données

            consulationService.modifier(cons);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Dash.fxml"));

            Parent root;
            root = loader.load();

            // Récupération du contrôleur de la page de modification de consultation
            DashController dash = loader.getController();

            // Initialisation des champs de la page de modification de consultation avec les données de la consultation actuelle
            dash.initialize(cons.getPatients_id());
            // Changement de la scène pour afficher la page de modification de consultation

            Enregistrer.getScene().setRoot(root);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    //initialiser les champs de date et heure de début et de fin avec les valeurs de l'instance de Consulation à modifier

    void initialize(Consulation cons) throws SQLException {
        heureD.setValue(cons.getHeuredebut().toLocalDateTime().toLocalDate());
        heureF.setValue(cons.getHeurefin().toLocalDateTime().toLocalDate());
        this.cons = cons;
    }

    public void Retour() throws IOException, SQLException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Dash.fxml"));
        Parent root;
        root = loader.load();

    }

    @FXML
    private void retour2(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Dash.fxml"));
        Parent root;
        // Affichage de la page du tableau de bord du médecin

        root = loader.load();
        // Récupération du contrôleur de la page de modification de consultation
        DashController dash = loader.getController();

        // Initialisation des champs de la page de modification de consultation avec les données de la consultation actuelle
        dash.initialize(cons.getPatients_id());
        retour.getScene().setRoot(root);
    }

}
