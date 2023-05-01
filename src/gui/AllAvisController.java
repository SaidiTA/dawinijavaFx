/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import entities.Avis;
import entities.Patient;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import test.Dawini;

/**
 * FXML Controller class
 *
 * @author soumayaab
 */
public class AllAvisController implements Initializable {

    private Patient patient;

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @FXML
    private Text nompat;

    @FXML
    private Text DATE;
    @FXML
    private Text MESSAGE;
    @FXML
    private VBox allAvis;
    @FXML
    private HBox noteBox;

    @FXML
    private ImageView imgprofil;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    void setData(Avis avis) {
        try {
           Image image = new Image(Dawini.class.getClass().getResource("/images/" + avis.getPatient().getImage()).toString());
       

// Créer un objet Circle pour le cercle
            Circle clip = new Circle();
            clip.setRadius(imgprofil.getFitWidth() /3 ); // Le rayon est la moitié de la largeur de l'image
           clip.setCenterX(imgprofil.getFitWidth() /2); // Centrer le cercle horizontalement
            clip.setCenterY(imgprofil.getFitHeight()/2 ); // Centrer le cercle verticalement

// Appliquer le cercle comme clip pour l'ImageView
            imgprofil.setClip(clip);

// Ajouter un contour arrondi au cercle
            imgprofil.setStyle("-fx-border-radius: " + clip.getRadius() + "px; "
                    + "-fx-border-color: black; "
                    + "-fx-border-width: 2px;");

// Ajouter l'ImageView dans un conteneur comme un Pane ou un Group
            imgprofil.setImage(image);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        nompat.setText(avis.getPatient().getNom() + " " + avis.getPatient().getPrenom());
        DATE.setText(avis.getDate().toString());
        MESSAGE.setText(avis.getText());

        int note = (int) avis.getNote();
        for (int i = 0; i < 5; i++) {
            ImageView starImage;
            if (i < note) {
                starImage = new ImageView(new Image("/images/Star.png"));
            } else {
                starImage = new ImageView(new Image("/images/star-empty.png"));
            }
            starImage.setFitHeight(20);
            starImage.setFitWidth(20);
            noteBox.getChildren().add(starImage);
        }
    }
}
