/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Specialites;
import entities.Sujet;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.HTMLEditor;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import services.specialitesService;

/**
 * FXML Controller class
 *
 * @author jlidi
 */
public class AddSpecialitesController implements Initializable {

    @FXML
    private Button btnAjout;
    @FXML
    private Button btncrosse;
    @FXML
    private ImageView btncross;
    @FXML
    private TextArea btnnom;
    @FXML
    private HTMLEditor btndescription;
    private File file;
    private String lien = "";
    @FXML
    private Button choirimage;
    @FXML
    private ImageView simage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void buttonOnAction(ActionEvent event) {
        String nom = btnnom.getText().trim();
        String description = btndescription.getHtmlText().trim();
        if (nom.isEmpty() || description.isEmpty()) {
            // Show an error message to the user
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs avant de continuer.");
            alert.showAndWait();
        } else if (countWords(description) > 200) {
            // Show an error message to the user
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("La description ne doit pas dépasser 200 mots.");
            alert.showAndWait();
        } else if (lien.isEmpty()) {
            JOptionPane.showMessageDialog(null, "il faut importer l'image d'ordonnance!");
        } else {
            // Create the new Article object and add it to the database
            Specialites specialite = new Specialites(nom, description,lien+".png");
            specialitesService service = new specialitesService();
            service.ajouter(specialite);

            // Close the stage
            Stage stage = (Stage) btnnom.getScene().getWindow();
            stage.close();
        }
    }

    private int countWords(String text) {
        // Split the text into words using whitespace as the delimiter
        String[] words = text.split("\\s+");

        // Return the number of words
        return words.length;
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btncrosse) {
            // Get the Stage that contains the button
            Stage stage = (Stage) btncrosse.getScene().getWindow();

            // Close the stage
            stage.close();

        }
        if (event.getSource() == btncross) {
            // Get the Stage that contains the button
            Stage stage = (Stage) btncross.getScene().getWindow();

            // Close the stage
            stage.close();

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
            BufferedImage image = ImageIO.read(file);
            WritableImage imagee = SwingFXUtils.toFXImage(image, null);

            try {
                // save image to PNG file
                this.lien = UUID.randomUUID().toString();
                File f = new File("src\\images\\" + this.lien + ".png");
                System.out.println(f.toURI().toString());
                ImageIO.write(image, "PNG", f);

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
