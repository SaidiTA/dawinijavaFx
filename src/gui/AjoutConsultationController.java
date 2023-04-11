/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Consulation;
import java.awt.Desktop;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import static java.util.Optional.empty;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import services.ConsulationService;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class AjoutConsultationController implements Initializable {

    @FXML
    private Button suivant;
    @FXML
    private Button commencer;

    private String MEET_URL = "https://meet.google.com/new";
    @FXML
    private TextField tURL;
    @FXML
    private Button retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    private static void openUrlInBrowser(String url) {
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.browse(new URI(url));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Desktop is not supported, cannot open URL in browser");
        }
    }

    @FXML
    public void commencerConsultation(ActionEvent event) {
        openUrlInBrowser(MEET_URL);

    }

    @FXML
    public void newConsultation() throws SQLException {
        String url = tURL.getText();
        Calendar calendar = Calendar.getInstance();
        java.util.Date currentDate = calendar.getTime();
        Date dt = new Date(currentDate.getTime());
        Timestamp heure = new Timestamp(System.currentTimeMillis());

        Consulation c = new Consulation(dt, heure, heure, url, "0");
        ConsulationService cons = new ConsulationService();
        if (tURL.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "il faut remplir le champ pour recuperer l'url  !");
                } else {
                    
                
        try {
            cons.ajouter(c);
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/AjouterOrdonnance.fxml"));
        Parent root;

        try {
            root = loader.load();

            AjouterOrdonnanceController ordonnanceController = loader.getController();
            //b3atht el id cons mel controllerajoutcons lel  w 3malt el set mta3 id cons 
            ordonnanceController.setConsulation_id(cons.recupererByURL(url));
            
            suivant.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
}
    }
     @FXML
    public void Retour() throws IOException, SQLException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/DashMedecin.fxml"));
        Parent root;
        root = loader.load();

        retour.getScene().setRoot(root);

    }
     @FXML
    private void retour2(MouseEvent event) throws IOException {
          Parent root = FXMLLoader.load(getClass().getResource("/gui/DashMedecin.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
    }

}
