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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import services.ConsulationService;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class ModifierConsultationController implements Initializable {

    private Consulation cons;

    @FXML
    private DatePicker heureD;
    @FXML
    private DatePicker heureF;
    @FXML
    private Button Enregistrer;

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

            cons.setHeuredebut(Timestamp.valueOf(heureD.getValue().atStartOfDay()));
            cons.setHeurefin(Timestamp.valueOf(heureF.getValue().atStartOfDay()));
                        // On appelle la méthode "modifier" pour mettre à jour la consultation dans la base de données

            consulationService.modifier(cons);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Dash.fxml"));
            Parent root;

            root = loader.load();

            Enregistrer.getScene().setRoot(root);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
        //initialiser les champs de date et heure de début et de fin avec les valeurs de l'instance de Consulation à modifier

    void initialize(Consulation cons) throws SQLException {
        heureD.setValue(cons.getHeuredebut().toLocalDateTime().toLocalDate());
        heureF.setValue(cons.getHeurefin().toLocalDateTime().toLocalDate());
        this.cons=cons;
    }
    public void Retour() throws IOException, SQLException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Dash.fxml"));
        Parent root;
        root = loader.load();

    }

}
