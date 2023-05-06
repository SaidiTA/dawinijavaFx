/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import entities.Assistant;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import services.AssistantService;

/**
 * FXML Controller class
 *
 * @author soumayaab
 */
public class ListAssistForMed implements Initializable {

    @FXML
    private Button ASSIST;
    @FXML
    private Button listpat;
    @FXML
    private Button ART;
    @FXML
    private Button dos;
    @FXML
    private Label btnid;
    @FXML
    private Label Nom;
    @FXML
    private Label Prenom;
    @FXML
    private Label date;
    @FXML
    private Label Action;
    @FXML
    private VBox pnitems;
    @FXML
    private Button btnAdd;
    @FXML
    private ImageView btnAddplus1;
    @FXML
    private Button btnRef;
    @FXML
    private ImageView btnRef1;
    @FXML
    private Label ListeArticle;
    public void initialize(int medecin) {
         this.medecin_id=medecin;
    }
     private Medecin medecin;

    public Medecin getMedecin() {
        return medecin;
    }

    public void setMedecin(Medecin medecin) {
        this.medecin = medecin;
    }
public int medecin_id;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         AssistantService assistantService = new AssistantService();
         List<Assistant> assistants = null;

    try {
        assistants = assistantService.getAssistantsForMedecin(medecin_id);
    } catch (SQLException ex) {
        System.out.println(ex);
    }
          
      



    for (Assistant assistant : assistants) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/itemAssisForMed.fxml"));
            Node node = loader.load();
            itemAssisForMedController itemController = loader.getController();
            itemController.setData(assistant);
            pnitems.getChildren().add(node);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    }    

   @FXML
    private void gotolistpat(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/affichePatient.fxml"));
        Parent root;
        // Affichage de la page du tableau de bord du médecin

        root = loader.load();
        // Récupération du contrôleur de la page de modification de consultation
        AffichePatientController affichpat = loader.getController();

        // Initialisation des champs de la page de modification de consultation avec les données de la consultation actuelle
       
        listpat.getScene().setRoot(root);
        
    }

    @FXML
    private void dossier(ActionEvent event){
                  try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListDossier.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
       
        
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    }

    @FXML
    private void ASSISTANT(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/ListAssistForMed.fxml"));
        Parent root;
        // Affichage de la page du tableau de bord du médecin

        root = loader.load();
        // Récupération du contrôleur de la page de modification de consultation
        ListAssistForMed affichpat = loader.getController();

        // Initialisation des champs de la page de modification de consultation avec les données de la consultation actuelle
       
        ASSIST.getScene().setRoot(root);
        
    
    }

    @FXML
    private void ARTICLES(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/ListMedecinArticle.fxml"));
        Parent root;
        // Affichage de la page du tableau de bord du médecin

        root = loader.load();
        // Récupération du contrôleur de la page de modification de consultation
        ListMedecinArticle affichpat = loader.getController();

        // Initialisation des champs de la page de modification de consultation avec les données de la consultation actuelle
       
        listpat.getScene().setRoot(root);
        
    
    }

    @FXML
    private void handleLogoutButtonAction(ActionEvent event) throws IOException {
         UserSession.getInstance().setCurrentUser(null);

        // Redirigez l'utilisateur vers l'écran de connexion
        Parent root = FXMLLoader.load(getClass().getResource("SignInUser.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

  
  


    @FXML
    private void handleButtonClick(ActionEvent event) {
    }

  
    @FXML
    private void refreshTable(ActionEvent event) {
    }
    
}
