/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Article;
import entities.Images;
import entities.Medecin;
import entities.User;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.HTMLEditor;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.ArticleService;
import services.ImageService;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author Islem
 */
public class AddArticleController implements Initializable {

    @FXML
    private Button btnAjout;
    @FXML
    private Button btncrosse;
    @FXML
    private ImageView btncross;
    @FXML
    private TextArea Nom;
    @FXML
    private TextArea Description;
    @FXML
    private Button btnUpload;
    
    private Images img;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

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
    private void addArticle(ActionEvent event) throws SQLException {

        String nom = Nom.getText().trim();
        String description = Description.getText().trim();
        String image = btnUpload.getText();

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
        } else {
            // Create the new Article object and add it to the database
            Article article = new Article(nom, description);
            ArticleService service = new ArticleService();
            int article_id = service.createTask(article);
            Article article_saved= service.recupererById(article_id);
            System.out.println("article_id"+ article_id);
            
            ImageService imgservice = new ImageService();
            this.img.setArticleId(article_saved);
            imgservice.ajouter(this.img);
            UserService userService = new UserService();
            List<User> users = userService.recupererUserForEmail();
            //MailSender sender = new MailSender();
            for (User user : users) {
                MailSender.sendMail(user.getEmail());
            }
            
            //start image upload here
            
            
            
            //image upload ends here
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setHeaderText("L'article a été créé avec succès et un mail est envoyé aux utilisateurs !");
            alert.showAndWait();

            // Close the stage
            Stage stage = (Stage) btnAjout.getScene().getWindow();
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
    private void handleButtonAction(MouseEvent event) {
    }

    @FXML
    private void uploadImage(ActionEvent event) {
            FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Choisir une image");

    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.jpeg", "*.png");
    fileChooser.getExtensionFilters().add(extFilter);
    File file = fileChooser.showOpenDialog(btnUpload.getScene().getWindow());

    if (file != null) {
        // Display the selected file name
        btnUpload.setText(file.getName());

        // Save the selected file to the "images" directory in the project folder
        Path imagePath = Paths.get("images", file.getName());
        Images img = new Images(file.getName(),file.getName());
        this.img = img;
        try {
            Files.copy(file.toPath(), imagePath, StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    }

}
