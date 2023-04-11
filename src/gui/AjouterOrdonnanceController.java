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
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
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

    public void setConsulation_id(int consulation_id) {
        this.consulation_id = consulation_id;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void add_ordonnance(ActionEvent event) throws IOException, SQLException {
        System.out.println(this.consulation_id);
        String description = desc.getText();
        ordonnance or = new ordonnance(description);
        ordonnanceService os = new ordonnanceService();
        ConsulationService cons = new ConsulationService();
        or.setConsulation_id(this.consulation_id);
        
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
