/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package gui;

import entities.Medecin;
import entities.UserSession;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import services.MedecinService;

/**
 *
 * @author soumayaab
 */
public class HomeController implements Initializable {
    
    private Label label;
    @FXML
    private GridPane postGrid;
    @FXML
    private Label ListeMedecin;
    @FXML
    private Button btnArt;
    
    private void handleButtonAction(ActionEvent event) {
       
       
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         MedecinService medecinService = new MedecinService();
        List<Medecin> medecins = null;

        try {
            medecins = medecinService.recuperer();
        } catch (SQLException ex) {
           
        }

        int row = 1;
        int column = 0;
        for (Medecin medecin : medecins) {
            try {
               
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/cardMed.fxml"));
                VBox node = loader.load();
                CardMedController itemController = loader.getController();
                itemController.setData(medecin);
                // add the node to the grid pane
                if (column == 3) {
                    column = 0;
                    ++row;
                }

                postGrid.add(node, column++, row);

                GridPane.setMargin(node, new Insets(5));

                // increment the row and column counters
            } // TODO
            catch (IOException ex) {
                
            }
        }

    
    }    

    @FXML
    private void MED(ActionEvent event) {
    }




   @FXML
    private void handleLogoutButtonAction(ActionEvent event) throws IOException {
        // Supprimez la session utilisateur en cours
        UserSession.getInstance().setCurrentUser(null);

        // Redirigez l'utilisateur vers l'écran de connexion
        Parent root = FXMLLoader.load(getClass().getResource("SignInUser.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    private void ARTICLES(ActionEvent event) {
           try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("article.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
       
        
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    }
    
}
