/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Diagnostique;
import entities.UserSession;
import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import services.DiagnostiqueCrud;

/**
 * FXML Controller class
 *
 * @author msi
 */
public class ListDiagnostiquePatientController implements Initializable {

    @FXML
    private TextField Recherche;
    @FXML
    private ImageView search;
    @FXML
    private VBox dos;
    @FXML
    private HBox HB;
    @FXML
    private Button btnArt;
    @FXML
    private Button dossier;
    @FXML
    private Button btnRdv;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
          List<Diagnostique> diagnostiques = diagnostiques();
        for(Diagnostique diagnostique:diagnostiques){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Diagnostique_item.fxml"));
     
            try{
                HBox hBox =fxmlLoader.load();
                Diagnostique_itemController dic= fxmlLoader.getController();
                dic.setData(diagnostique);
                           
                dos.getChildren().add(hBox);
            } catch (IOException ex) {
               ex.printStackTrace(); 
            }
        }
        // TODO
    }   
    
    private List<Diagnostique> diagnostiques(){
         DiagnostiqueCrud ps = new DiagnostiqueCrud();
        //int idDossier = 0;
         List<Diagnostique> diagnostiques = ps.listerDiagnostiques();
         
  

         return diagnostiques;
    }


    @FXML
    private void Recherche(KeyEvent event) {
    }

    @FXML
    private void retour(ActionEvent event) {
                 try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DossierPatient.fxml"));
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
    private void MED(ActionEvent event) {
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

    @FXML
    private void dossier(ActionEvent event) {
              try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DossierPatient.fxml"));
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
    private void Rdv(ActionEvent event) {
          try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("afficherRFXML.fxml"));
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
    

    
}
