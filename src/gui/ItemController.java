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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import services.MedecinService;

/**
 *
 * @author soumayaab
 */
public class ItemController implements Initializable {
     private double x, y;
   @FXML
    private Button btnshow;
  @FXML
    private Button btnDelete;
@FXML
    private Button btnModify;
    @FXML
    private Label lblNom;
    @FXML
    private Label lblPrenom;
    @FXML
    private Label lblEmail;
    @FXML
    private Label lblTelephone;
    @FXML
    private Label lblMedecin;
    @FXML
    private Label lblTARIF;
    @FXML
    private Label lblAdresse;
    @FXML
    private HBox lblAction;
    @FXML
    private Label btnid;

   
    @FXML
    void handleButtonClick(ActionEvent event) throws SQLException {
       try{
        // Récupération des données du dossier sélectionné
         
       int id = Integer.parseInt(btnid.getText());
       MedecinService dc = new MedecinService();
        List<Medecin> spec = dc.recuperer();
        Medecin medecin = null;
        for (Medecin d : spec) {
            if (d.getId() == id) {
                medecin = d;
                break;
            }
        }
        
        // Chargement de la fenêtre de modification
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/ModifierMedecin.fxml"));
        Parent parent = fxmlLoader.load();
        ModifierMedecinController controller = fxmlLoader.getController();

        // Transmission des données du dossier à la fenêtre de modification
        controller.setData(medecin);

        // Affichage de la fenêtre de modification
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.show();
       
    } catch (IOException ex) {
        
    }
        
        
     if(event.getSource()== btnshow){
      showAsDialog("infoMedecin");
      }
     if(event.getSource()== btnDelete){
      showDialog("delete");
      }
     }
     
  private void showAsDialog(String fxml)
    {
       try {
           Parent parent = FXMLLoader.load(getClass().getResource("/gui/infoMedecin.fxml"));
           Stage stage = new Stage();
           Scene scene = new Scene(parent);
           stage.setScene(scene);
              stage.initStyle(StageStyle.UNDECORATED);

        //drag it here
        parent.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });
        parent.setOnMouseDragged(event -> {

            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);

        });
           stage.show();
        
        //primaryStage.setTitle("Sign In User");
       
       
           
           
           
           
       } catch (IOException ex) {
           ex.printStackTrace();
       }
    
    }
     private void showDialog(String fxml)
    {
       try {
           Parent parent = FXMLLoader.load(getClass().getResource("/gui/delete.fxml"));
           Stage stage = new Stage();
           Scene scene = new Scene(parent);
           stage.setScene(scene);
              stage.initStyle(StageStyle.UNDECORATED);

        //drag it here
        parent.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });
        parent.setOnMouseDragged(event -> {

            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);

        });
           stage.show();
        
        //primaryStage.setTitle("Sign In User");
       
       
           
           
           
           
       } catch (IOException ex) {
           ex.printStackTrace();
       }
    
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
      public void setData(Medecin medecin) {
       
       int id=medecin.getId();
        btnid.setText(Integer.toString(id));

       
        lblNom.setText(medecin.getNom());
        lblPrenom.setText(medecin.getPrenom());
        lblEmail.setText(medecin.getEmail());
        lblTelephone.setText(medecin.getTelephone());
        lblMedecin.setText(medecin.getTitre());
        //lblspecialites.setText(medecin.getSpecialite());
        lblTARIF.setText(medecin.getTarif() + " DT");
        lblAdresse.setText(medecin.getAdresse());
       
    
    
}

    @FXML
    private void supprimer(ActionEvent event) throws SQLException {
                int id = Integer.parseInt(btnid.getText());
           MedecinService dc = new MedecinService();
            List<Medecin> medecins = dc.recuperer();
                 for (Medecin d : medecins) {
                     if (d.getId() == id) {
                     dc.supprimer(d);
                     
                 break;
                 
        }
    }
    }
}
