/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package gui;

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
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
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
    private Button btnRef1;
    @FXML
    private ImageView btnRef;
    @FXML
    private Label ListeMedecin;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
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
                System.out.println("article "+ medecin);
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
    private void Articles(ActionEvent event) {
    }


    @FXML
    private void refreshTable(ActionEvent event) {
    }
    
}
