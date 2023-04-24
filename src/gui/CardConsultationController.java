/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Consulation;
import entities.ordonnance;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import services.ConsulationService;
import services.ordonnanceService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.image.BufferedImage;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class CardConsultationController implements Initializable {

    private int id;
    private Consulation cons;
    private ordonnance ord;
    @FXML
    private Label id_consultation;
    @FXML
    private Label date_consultation;
    @FXML
    private Label debut_consultation;
    @FXML
    private Label fin_consultation;
    @FXML
    private Button modif_cons;
    @FXML
    private Button supp_cons;
    @FXML
    private Button modif_ord;
    @FXML
    private Button aff_ord;
    @FXML
    private Button supp_ord;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Consulation getCons() {
        return cons;
    }

    public void setCons(Consulation cons) {
        this.cons = cons;
    }

    public ordonnance getOrd() {
        return ord;
    }

    public void setOrd(ordonnance ord) {
        this.ord = ord;
    }
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        

    }

    

  

    

    public void setModif_ord() {
       
         modif_ord.setDisable(true);
    }

    

    public void setSupp_ord() {
        
        supp_ord.setDisable(true);
    }
    
    
    public void setaff_ord() {
        this.aff_ord.setDisable(true);
    }
    // initialiser les champs de la vue en fonction des informations d'une consultation

    public void setData(Consulation cons) {
        
        this.cons = cons;
        this.id = cons.getId();
        id_consultation.setText(String.valueOf(cons.getId()));
        date_consultation.setText(String.valueOf(cons.getDate()));
        debut_consultation.setText(String.valueOf(cons.getHeuredebut()));
        fin_consultation.setText(String.valueOf(cons.getHeurefin()));
    }

    
    

    @FXML
    public void RedirectModifierConsultation() throws IOException, SQLException {
         
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/ModifierConsultation.fxml"));
        Parent root;
        root = loader.load();
            // Récupération du contrôleur de la page de modification de consultation

        ModifierConsultationController modif = loader.getController();
    // Initialisation des champs de la page de modification de consultation avec les données de la consultation actuelle

        modif.initialize(getCons());
            // Changement de la scène pour afficher la page de modification de consultation

        modif_cons.getScene().setRoot(root);

    }
    @FXML
    public void RedirectModifierOrdonnance() throws IOException, SQLException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/ModifierOrdonnance.fxml"));
        Parent root;
        root = loader.load();
            // Récupération du contrôleur de la page de modification d'ordonnance

        ModifierOrdonnanceController modiford = loader.getController();
            // Création d'un service pour la gestion des ordonnances

         ordonnanceService ordService = new ordonnanceService();
         
         
    // Initialisation des champs de la page de modification d'ordonnance avec les données de l'ordonnance associée à la consultation actuelle

        modiford.initialize(ordService.recupererbycons(this.cons.getId()));
            // Changement de la scène pour afficher la page de modification d'ordonnance

        modif_ord.getScene().setRoot(root);

    }
    @FXML
    public void SupprimerConsultation() throws IOException {
        try {
// Création d'un service pour la gestion des consultations
            ConsulationService consulationService = new ConsulationService();
                    // Suppression de la consultation actuelle

            consulationService.supprimer(cons);
                    // Récupération du fichier FXML de la page du tableau de bord du médecin

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Dash.fxml"));
            Parent root;
        // Affichage de la page du tableau de bord du médecin

            root = loader.load();

            supp_cons.getScene().setRoot(root);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    @FXML
    public void SupprimerOrdonnance() throws IOException {
        
        try {
                    // Création d'un service pour la gestion des ordonnances

            ordonnanceService ordService = new ordonnanceService();
                    // Récupération de l'ordonnance associée à la consultation actuelle et suppression de celle-ci

            ordService.supprimer(ordService.recupererbycons(this.cons.getId()));
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Dash.fxml"));
            Parent root;
            // Affichage de la page
            root = loader.load();
            supp_ord.getScene().setRoot(root);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    @FXML
        public void AfficherOrdonnance() throws IOException, SQLException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/afficheOrdonnance.fxml"));
        Parent root;
        root = loader.load();
        AfficheOrdonnanceController afford = loader.getController();
         ordonnanceService ordService = new ordonnanceService();
         
         
    // Appel de la méthode recupererbycons pour récupérer l'ordonnance correspondant à la consultation en cours

        afford.setOrd(ordService.recupererbycons(this.cons.getId()));
            // Changement de la scène aff_ord pour afficher l'interface graphique afficheOrdonnance.fxml

        aff_ord.getScene().setRoot(root);

    }
        
        @FXML
        public void qrcode() throws SQLException {
        ordonnanceService or = new ordonnanceService();
       
      ordonnance ord;
        ord = or.recupererbycons(this.cons.getId());

        // Create a button
        // Set an event handler for the button
        // Convert the data to a string
        StringBuilder stringBuilder = new StringBuilder();
       
            stringBuilder.append(ord);
            stringBuilder.append("\n");
        
        String dataString = stringBuilder.toString().trim();

        // Generate the QR code image
        Image qrCodeImage = generateQRCode(dataString);

        // Display the QR code image
        ImageView imageView = new ImageView(qrCodeImage);
        VBox vbox = new VBox(imageView);
        Scene scene = new Scene(vbox);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

    }
        
    private Image generateQRCode(String data) {
        try {
            // Create a QR code writer
            QRCodeWriter writer = new QRCodeWriter();

            // Create a BitMatrix from the data and set the size
            BitMatrix bitMatrix = writer.encode(data, BarcodeFormat.QR_CODE, 200, 200);

            // Create a BufferedImage from the BitMatrix
            BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);

            // Create a JavaFX Image from the BufferedImage
            return SwingFXUtils.toFXImage(image, null);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
