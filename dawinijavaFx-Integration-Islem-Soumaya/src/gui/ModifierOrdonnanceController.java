/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Consulation;
import entities.ordonnance;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import java.util.UUID;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
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
public class ModifierOrdonnanceController implements Initializable {
    private int  patient; 
    private ordonnance ord;
    @FXML
    private Button modif_ord;
    @FXML
    private Button choirimage;
    @FXML
    private ImageView affimage;
    private File file;
    private String lien = "";
    @FXML
    private TextArea desc;
    @FXML
    private Button retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void retour2(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Dash.fxml"));
            Parent root;
        // Affichage de la page du tableau de bord du médecin

            root = loader.load();
            // Récupération du contrôleur de la page de modification de consultation
DashController dash = loader.getController();
         
    // Initialisation des champs de la page de modification de consultation avec les données de la consultation actuelle
     
      dash.initialize(patient);

                retour.getScene().setRoot(root);
    }

    @FXML
    private void UploadImageActionPerformed(ActionEvent event) throws IOException {

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

        BufferedImage image = ImageIO.read(file);
        WritableImage imagee = SwingFXUtils.toFXImage(image, null);
        affimage.setImage(imagee);
        affimage.setFitWidth(200);
        affimage.setFitHeight(200);
        affimage.scaleXProperty();
        affimage.scaleYProperty();
        affimage.setSmooth(true);
        affimage.setCache(true);
        try {
            // save image to PNG file
            this.lien = UUID.randomUUID().toString();
            File f = new File("src\\uploads\\" + this.lien + ".png");
            System.out.println(f.toURI().toString());
            ImageIO.write(image, "PNG", f);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    public void modifierordonnance() throws IOException {
        try {

            ordonnanceService ordService = new ordonnanceService();
            ord.setDescription(desc.getText());
            System.out.println("test");
            if (desc.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "remplir la description!");
            } else if (lien.isEmpty()) {
                JOptionPane.showMessageDialog(null, "il faut importer l'image d'ordonnance!");
            } else {
                ordService.modifier(ord);
                 FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Dash.fxml"));
            Parent root;
        // Affichage de la page du tableau de bord du médecin

            root = loader.load();
            // Récupération du contrôleur de la page de modification de consultation
DashController dash = loader.getController();
         
    // Initialisation des champs de la page de modification de consultation avec les données de la consultation actuelle
     
      dash.initialize(patient);

                modif_ord.getScene().setRoot(root);

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    void initialize(ordonnance ord,int id) throws SQLException, IOException {

        desc.setText(ord.getDescription());
       /* String imagePath = "src\\uploads\\" + this.lien + ".png";
        File imageFile = new File(imagePath);
       // Image image = ImageIO.read(imageFile);
Image image = new Image(imagePath);

        affimage.setImage(image);*/
        this.ord = ord;
        patient=id;
    }


}
