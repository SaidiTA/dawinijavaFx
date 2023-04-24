/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import entities.Activiter;
import entities.Categorie;
import javafx.scene.control.Button;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Popup;
import javafx.stage.PopupWindow;
import services.ActiviterService;
import services.CategorieService;



/**
 * FXML Controller class
 *
 * @author hadjn
 */
public class ActiviteController implements Initializable {

   


@FXML
    private TextField titre;
@FXML
    private DatePicker date_deb;
@FXML
    private DatePicker date_fin;
 @FXML
    private Button btn_ajouter;
ActiviterService as = new ActiviterService();
CategorieService cs = new CategorieService();

    @FXML
    private ComboBox<String> categ;
    @FXML
    private Button btnRetour;
        
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       Button btn = new Button();
       
         try {
        for (Categorie cat : cs.recuperer()) {
            categ.getItems().add(cat.getNom() );
        }   } catch (SQLException ex) {
        Logger.getLogger(ActiviteController.class.getName()).log(Level.SEVERE, null, ex);
    }
       

       
    }

    


 @FXML
    private void ajouteract(ActionEvent event) throws IOException, SQLException { 
        
            Activiter ac=new Activiter();
            ac.setId_user(5);
            Timestamp timestamp = Timestamp.valueOf(date_deb.getValue().atStartOfDay());
            System.out.println(timestamp);
            ac.setDate_debut(timestamp);
            timestamp = Timestamp.valueOf(date_fin.getValue().atStartOfDay());
            ac.setDate_fin(timestamp);
            ac.setTitre(titre.getText());
            Categorie id;
            try {
                id = cs.recupererBynom(categ.getValue());
                 ac.setRef_categ(id.getId());
            } catch (SQLException ex) {
                Logger.getLogger(ActiviteController.class.getName()).log(Level.SEVERE, null, ex);
            }
           
          
            try {
                
                as.ajouter(ac);
               Alert alert = new Alert(AlertType.CONFIRMATION);
alert.setTitle("Success");
alert.setHeaderText(null);
alert.setContentText("Operation completed successfully!");
alert.showAndWait();

            } catch (SQLException ex) {
                Logger.getLogger(ActiviteController.class.getName()).log(Level.SEVERE, null, ex);
            }

             
         
       
    }
    
     @FXML
    private void returndisplay(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/displayactivite.fxml"));
        ActiviteController aec = loader.getController();
        Parent root = loader.load();
        btnRetour.getScene().setRoot(root);
    
    
}
    
}
