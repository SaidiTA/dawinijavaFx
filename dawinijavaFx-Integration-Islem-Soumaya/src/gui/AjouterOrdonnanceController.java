/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.ordonnance;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Calendar;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import services.ConsulationService;
import services.ordonnanceService;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class AjouterOrdonnanceController implements Initializable {

    private int consulation_id;
    private int patient;
    @FXML
    private Button add_ordonnance;
    
    @FXML
    private Button choirimage;
    @FXML
    private TextArea desc;
    private File file; 
    private String lien="";
    @FXML
    private ImageView affimage;

    /**
     * Initializes the controller class.
     */
    
    public int getConsulation_id() {
        return consulation_id;
        // TODO
    }

    public void setConsulation_id(int consulation_id,int id) {
        this.consulation_id = consulation_id;
        patient=id;
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void add_ordonnance(ActionEvent event) throws IOException, SQLException {
        System.out.println(this.consulation_id);
        // Récupération de la description de l'ordonnance à partir de la zone de texte
        String description = desc.getText();
            // création d'une instance d'ordonnance avec la description récupérée

        ordonnance or = new ordonnance(description);
            // création d'une instance de service pour l'ajout d'une ordonnance en base de données

        ordonnanceService os = new ordonnanceService();
            // création d'une instance de service pour la mise à jour de la consultation en base de données

        ConsulationService cons = new ConsulationService();
            // affectation de l'identifiant de la consultation à l'ordonnance

        or.setConsulation_id(this.consulation_id);
            // récupération de la date courante

        Calendar calendar = Calendar.getInstance();
        java.util.Date currentDate = calendar.getTime();
        Date dt = new Date(currentDate.getTime());
        or.setDate(dt);
        or.setImage(lien);
        if (desc.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "remplir la description!");
                } 
        else if (lien.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "il faut importer l'image d'ordonnance!");
                } 
        else {
        try {
            os.ajouter(or);
            cons.consultationTerminer(this.consulation_id);
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Dash.fxml"));
            
               
        Parent root;
        root = loader.load();
          
            // Récupération du contrôleur de la page de modification de consultation
DashController dash = loader.getController();
         
    // Initialisation des champs de la page de modification de consultation avec les données de la consultation actuelle
     
      dash.initialize(patient);
            // Changement de la scène pour afficher la page de modification de consultation
        
        add_ordonnance.getScene().setRoot(root);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        }
        
    }

    @FXML
    private void UploadImageActionPerformed(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG
                = new FileChooser.ExtensionFilter("JPG files (*.JPG)", "*.JPG");
        FileChooser.ExtensionFilter extFilterjpg
                = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter extFilterPNG
                = new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG");
        FileChooser.ExtensionFilter extFilterpng
                = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
        fileChooser.getExtensionFilters()
                .addAll(extFilterJPG, extFilterjpg, extFilterPNG, extFilterpng);
        //Show open file dialog
        file = fileChooser.showOpenDialog(null);

        try {
            // Lit l'image sélectionnée par l'utilisateur
            BufferedImage image = ImageIO.read(file);
                // Convertit l'image de BufferedImage en WritableImage pour pouvoir l'afficher dans l'interface utilisateur
            WritableImage imagee = SwingFXUtils.toFXImage(image, null);
                // Affiche l'image dans l'interface utilisateur

            affimage.setImage(imagee);
            affimage.setFitWidth(200);
            affimage.setFitHeight(200);
            affimage.scaleXProperty();
            affimage.scaleYProperty();
            affimage.setSmooth(true);
            affimage.setCache(true);                           

        try {
            // save image to PNG file
                    // Enregistre l'image dans un fichier PNG avec un nom de fichier généré aléatoirement

            this.lien=UUID.randomUUID().toString();
            File f=new File("src\\uploads\\" + this.lien + ".png");
            System.out.println(f.toURI().toString());
            ImageIO.write(image, "PNG",f);
                       
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }


}
