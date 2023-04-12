/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Rendezvous;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import services.RendezvousService;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class AjouterrendezvousFXMLController implements Initializable {

    @FXML
    private TextField titretf;
    @FXML
    private TextField descriptiontf;
    @FXML
    //private TextField datetf;
    //@FXML
    private TextField etattf;
    @FXML
    private Button btnajouterrendezvous;
    
    //@FXML
    //private DatePicker dpd;
    
    @FXML
    private DatePicker datetf;
  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    @FXML
    public void Ajouterrendezvous(ActionEvent event) {
        // TODO
        String titre = titretf.getText();
        String description = descriptiontf.getText();
        String etat = etattf.getText();
        //Calendar calendar = Calendar.getInstance();
        //java.util.Date currentDate = calendar.getTime();
        //Date date = new Date(currentDate.getTime());
        //Timestamp heure = new Timestamp(System.currentTimeMillis());
      // String etat = etattf.getText();
     
        LocalDate date = datetf.getValue();
         //   java.sql.Date date = java.sql.Date.valueOf(localDate);
         //   statement.setDate(2, date);

        Rendezvous t = new Rendezvous(titre, description, date, etat);
        RendezvousService cons = new RendezvousService();

        try {
            cons.ajouter(t);
        } catch (SQLException ex) {
            System.out.println("ajouter avec succes");
            //System.out.println(ex.getMessage());
        }
        /*FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/AjouterOrdonnance.fxml"));
        Parent root;

        try {
            root = loader.load();

            AjouterOrdonnanceController ordonnanceController = loader.getController();
            
            ordonnanceController.setConsulation_id(cons.recupererByURL(url));
            
            suivant.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }*/
       // System.out.println("ajouter avec succes");
        
    }
    
   
    
}
