/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.User;
import entities.UserSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javax.imageio.ImageIO;
import services.PatientService;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class AffichePatientController implements Initializable {

    @FXML
    private VBox contenu;
    @FXML
    private Label id_consultation;
    @FXML
    private Label date;
    @FXML
    private Label heureD;
    @FXML
    private Label action;
    private PatientService ps=new PatientService();
    @FXML
    private ImageView image;
  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            
             
            String filePath = "src\\uploads\\" + UserSession.getInstance().getCurrentUser().getImage() ; // Replace with the actual file path
        File file = new File(filePath);
        Path path = Paths.get(file.getAbsolutePath());
         byte[] bytes = Files.readAllBytes(path);
            InputStream inputStream = new ByteArrayInputStream(bytes);
            Image imageset = new Image(inputStream);
            image.setImage(imageset); System.out.println(filePath);
            
        // Récupérer la liste des consultations pour l'utilisateur connecté
        List<User> patients = new ArrayList<>(ps.recupererbymedecin(UserSession.getInstance().getCurrentUser().getId()));

        // Initialiser la pagination
        Pagination pagination = new Pagination();
        pagination.setPageCount((int) Math.ceil(patients.size() / 5.0)); // 5 consultations par page
        pagination.setPageFactory((Integer pageIndex) -> {
            VBox pageContent = new VBox();
            int pageStart = pageIndex * 5;
            int pageEnd = Math.min(pageStart + 5, patients.size());
            for (int i = pageStart; i < pageEnd; i++) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CardaffichePatient.fxml"));
                try {
                    HBox hBox = fxmlLoader.load();
                    CardaffichePatientController listconsultation = fxmlLoader.getController();

                    // Vérifier s'il y a une ordonnance pour cette consultation
                   

                    // Initialiser les données de la carte avec les données de la consultation
                    listconsultation.setData(patients.get(i));

                    // Ajouter la carte à la page actuelle
                    pageContent.getChildren().add(hBox);
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
            return pageContent;
        });

        // Ajouter la pagination à la VBox principale
        contenu.getChildren().add(pagination);
        
        // Ajouter un écouteur d'événements pour la recherche
        

    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }   catch (IOException ex) {
            Logger.getLogger(AffichePatientController.class.getName()).log(Level.SEVERE, null, ex);
        }
}

    
    
    
 
    
   
    
}
