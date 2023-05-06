/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Specialites;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import services.specialitesService;
import test.Dawini;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.NodeTraversor;
import org.jsoup.select.NodeVisitor;

/**
 * FXML Controller class
 *
 * @author jlidi
 */
public class ItemSpecialitesFrontController implements Initializable {

    @FXML
    private ImageView images;
    @FXML
    private Label btnnom;
    private Specialites specialite;
    @FXML
    private Label btnid;
 public Specialites getSpecialite() {
        return specialite;
    }

    public void setSpecialite(Specialites specialite) {
        this.specialite = specialite;
    }
    /**
     * Initializes the controller class.
     */
    public void setData(Specialites specialite){
        int id=specialite.getId();
            btnid.setText(Integer.toString(id));

                btnnom.setText(specialite.getNom());
                String cheminImage = specialite.getImage();
    System.out.println("lien: " + cheminImage);
          try {
        // Load the image from the specified path
        Image image = new Image(Dawinii.class.getClass().getResource("/images/"+cheminImage).toString());
        images.setImage(image);
    } catch (Exception e) {
        e.printStackTrace();
    }
         
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    

    @FXML
    private void voir(ActionEvent event) {
        int id = Integer.parseInt(btnid.getText());
        
        specialitesService a = new specialitesService();
        try {
            Specialites specialite = a.recupererById(id);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/sujet.fxml"));
            Parent root = loader.load();
            SujetController controller = loader.getController();
            controller.setSujet(specialite);
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
       
    }


}
