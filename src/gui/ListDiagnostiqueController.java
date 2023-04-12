/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Diagnostique;
import entities.Dossier;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import services.DiagnostiqueCrud;

import java.time.ZoneId;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.control.Alert;


/**
 * FXML Controller class
 *
 * @author msi
 */
public class ListDiagnostiqueController implements Initializable {

    @FXML
    private DatePicker dateCol;
    @FXML
    private TextField sympCol;
    @FXML
    private TextField resCol;
    @FXML
    private TextField diagCol;
    @FXML
    private ImageView search;
    @FXML
    private VBox dos;

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

         
            private boolean symptome_resvalide(){
        Pattern p = Pattern.compile("[a-zA-Z ]+");
        Matcher m = p.matcher(sympCol.getText());
        if(m.find() && m.group().equals(sympCol.getText())){
            return true;
        }else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("symptome invalide !");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez entrer un symptome valide !");
                alert.showAndWait();
           
            return false;            
        }
     }
               private boolean resultat_resvalide(){
      Pattern p = Pattern.compile("[a-zA-Z ]+");
        Matcher m = p.matcher(resCol.getText());
        if(m.find() && m.group().equals(resCol.getText())){
            return true;
        }else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("resultat test invalide !");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez entrer un resultat de test valide !");
                alert.showAndWait();
           
            return false;            
        }
     }
                private boolean diag_resvalide(){
      Pattern p = Pattern.compile("[a-zA-Z ]+");
        Matcher m = p.matcher(diagCol.getText());
        if(m.find() && m.group().equals(diagCol.getText())){
            return true;
        }else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("diagnostique final invalide !");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez entrer un diagnostique valide !");
                alert.showAndWait();
           
            return false;            
        }
     }
  
    
    @FXML
    private void btn_aj_diag(ActionEvent event) {
       
         if(symptome_resvalide()&&resultat_resvalide()&&diag_resvalide()){
        Date date = Date.valueOf(dateCol.getValue());
        //Date date = Date.from(dateCol.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        String symptome = sympCol.getText();
        String resultat_test  = resCol.getText();
        String diag_final = diagCol.getText();
        Diagnostique d = new Diagnostique(date,symptome, resultat_test, diag_final);
        DiagnostiqueCrud dc = new DiagnostiqueCrud();
        dc.ajouter_diagnostique(d);
        updateDiagnostiques();
        
        dateCol.setValue(null);
        sympCol.clear();
        resCol.clear();
        diagCol.clear();
    }
    }
    
      private void updateDiagnostiques() {
    dos.getChildren().clear();
    List<Diagnostique> diagnostiques = diagnostiques();
    for(Diagnostique d : diagnostiques) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("Diagnostique_item.fxml"));
        try{
            HBox hBox = fxmlLoader.load();
            Diagnostique_itemController dic = fxmlLoader.getController();
            dic.setData(d);
            dos.getChildren().add(hBox);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
}
