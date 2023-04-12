/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Dossier;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import services.DossierCrud;

/**
 * FXML Controller class
 *
 * @author msi
 */
public class TesttController implements Initializable {

    @FXML
    private ImageView search;
    @FXML
    private VBox dos;
    @FXML
    private TextField num;
    @FXML
    private TextField code;
    @FXML
    private TextField descp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Dossier> dossiers = dossiers();
        for(Dossier dossier:dossiers){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Dossier_item.fxml"));
     
            try{
                HBox hBox =fxmlLoader.load();
                Dossier_itemController dic= fxmlLoader.getController();
                dic.setData(dossier);
                dos.getChildren().add(hBox);
            } catch (IOException ex) {
               ex.printStackTrace(); 
            }
        }
        // TODO
    }    
   
   
    private List<Dossier> dossiers(){
DossierCrud ps = new DossierCrud();
List<Dossier> dossiers = ps.listerDossiers();
return dossiers;
    }
    
    private void updateDossiers() {
    dos.getChildren().clear();
    List<Dossier> dossiers = dossiers();
    for(Dossier d : dossiers) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("Dossier_item.fxml"));
        try{
            HBox hBox = fxmlLoader.load();
            Dossier_itemController dic = fxmlLoader.getController();
            dic.setData(d);
            dos.getChildren().add(hBox);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

       
            private boolean num_resvalide(){
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(num.getText());
        if(m.find() && m.group().equals(num.getText())){
            return true;
        }else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("numero invalide !");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez entrer un numero valide !");
                alert.showAndWait();
           
            return false;            
        }
     }
               private boolean code_resvalide(){
      Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(code.getText());
        if(m.find() && m.group().equals(code.getText())){
            return true;
        }else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("code apci invalide !");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez entrer un code apci valide !");
                alert.showAndWait();
           
            return false;            
        }
     }
                private boolean descp_resvalide(){
      Pattern p = Pattern.compile("[a-zA-Z ]+");
        Matcher m = p.matcher(descp.getText());
        if(m.find() && m.group().equals(descp.getText())){
            return true;
        }else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("description valide !");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez entrer un description valide !");
                alert.showAndWait();
           
            return false;            
        }
     }
  

    @FXML
    private void btn_aj_dos(ActionEvent event) {
        if(num_resvalide()&&code_resvalide()&&descp_resvalide()){
        int numero = Integer.parseInt(num.getText());
        String code_apci = code.getText();
        String description  = descp.getText();
        Dossier d = new Dossier(numero, code_apci, description);
        DossierCrud dc = new DossierCrud();
        dc.ajouter_dossier(d);
        updateDossiers();
        
        num.clear();
        code.clear();
        descp.clear();
    }
    
    }
}
