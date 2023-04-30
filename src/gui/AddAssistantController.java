/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import entities.Assistant;
import entities.Medecin;
import java.io.File;
import java.io.IOException;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.AssistantService;
import services.MedecinService;

/**
 * FXML Controller class
 *
 * @author soumayaab
 */
public class AddAssistantController implements Initializable {

    @FXML
    private MenuButton Genre;
   
    @FXML
    private TextArea Adresse;
    @FXML
    private MenuButton Gouvernorat;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btncrosse;
    @FXML
    private ImageView btncross;
    @FXML
    private TextArea Nom;
    @FXML
    private TextArea Prenom;
    @FXML
    private TextArea Telephone;
    @FXML
    private TextArea Email;
    @FXML
    private TextArea Cin;
    @FXML
    private PasswordField Password;
    @FXML
    private PasswordField ConfirmPassword;
    @FXML
    private MenuButton MEDECIN;
    private MedecinService medecinService;

    private Medecin selectedMedecin;
    @FXML
    private Button btnUploadImage;

    public MedecinService getMedecinService() {
        return medecinService;
    }

    public void setMedecinService(MedecinService medecinService) {
        this.medecinService = medecinService;
    }

    public Medecin getSelectedMedecin() {
        return selectedMedecin;
    }

    public void setSelectedMedecin(Medecin selectedMedecin) {
        this.selectedMedecin = selectedMedecin;
    }

    public AssistantService getAssistantService() {
        return assistantService;
    }

    public void setAssistantService(AssistantService assistantService) {
        this.assistantService = assistantService;
    }

    /**
     * Initializes the controller class.
     */
    private AssistantService assistantService;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        medecinService = new MedecinService();
        assistantService = new AssistantService();

        MenuItem maleItem = new MenuItem("Homme");
        maleItem.setOnAction(e -> {
            Genre.setText(maleItem.getText());
        });

        MenuItem femaleItem = new MenuItem("Femme");
        femaleItem.setOnAction(e -> {
            Genre.setText(femaleItem.getText());
        });

        Genre.getItems().addAll(maleItem, femaleItem);

        // Add items to Gouvernorat MenuButton
        MenuItem g1 = new MenuItem("Ariana");
        g1.setOnAction(e -> {
            Gouvernorat.setText(g1.getText());
        });
        MenuItem g2 = new MenuItem("Zaghouen");
        g2.setOnAction(e -> {
            Gouvernorat.setText(g2.getText());
        });
        MenuItem g3 = new MenuItem("Nabeul");
        g3.setOnAction(e -> {
            Gouvernorat.setText(g3.getText());
        });
        MenuItem g4 = new MenuItem("Sousse");
        g4.setOnAction(e -> {
            Gouvernorat.setText(g4.getText());
        });
        MenuItem g5 = new MenuItem("Mahdia");
        g5.setOnAction(e -> {
            Gouvernorat.setText(g5.getText());
        });
        Gouvernorat.getItems().addAll(g1, g2, g3, g4, g5);

        // Add items to Titre MenuButton
        List<Medecin> medecins = null;
        try {
            medecins = medecinService.recuperer(); // Récupération de la liste des médecins
        } catch (SQLException ex) {
        }

// Création de la liste de MenuItem pour chaque médecin
        List<MenuItem> medecinItems = new ArrayList<>();
        for (Medecin medecin : medecins) {
            MenuItem medecinItem = new MenuItem(medecin.getNom() + " " + medecin.getPrenom());
            medecinItem.setUserData(medecin.getId());
            medecinItem.setOnAction(e -> {
                String medecinName = medecin.getNom() + " " + medecin.getPrenom();
                int medecinId = (int) ((MenuItem) e.getSource()).getUserData();
               
                // Utilisez l'ID du médecin sélectionné pour effectuer une action
                // ...
                MEDECIN.setText(medecinName);
            });
            medecinItems.add(medecinItem);
        }

// Ajout de chaque MenuItem au MenuButton
        MEDECIN.getItems().addAll(medecinItems);
    }

    @FXML
    private void addAssistant(ActionEvent event) throws SQLException {

        String email = Email.getText();
        String password = Password.getText();
        String nom = Nom.getText();
        String prenom = Prenom.getText();
        int cin = Integer.parseInt(Cin.getText());
        String genre = Genre.getText();
        String telephone = Telephone.getText();
        String gouvernorat = Gouvernorat.getText();
        String adresse = Adresse.getText();
        String image = btnUploadImage.getText();
        String confirmPassword = ConfirmPassword.getText();
        String medecinName = MEDECIN.getText();
if (medecinName.isEmpty()) {
    System.out.println("Aucun médecin !");
    return;
}
     List<Medecin> medecinList = medecinService.recuperer();
Medecin selectedMedecin = medecinList.get(1); // Initialize to first object in list
for (Medecin m : medecinList) {
    if (m.getNom().equalsIgnoreCase(medecinName)) {
        selectedMedecin = m;
        break;
    }
}

int medecinId = selectedMedecin.getId();
     

// Make sure a Medecin was selected
        // Validate the input fields
        if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || telephone.isEmpty()
                || adresse.isEmpty() || gouvernorat.isEmpty() || genre.isEmpty() || Cin.getText().isEmpty()
                || password.isEmpty() || confirmPassword.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Veuillez remplir tous les champs");
            alert.showAndWait();
            return;

        }
        if (!password.equals(confirmPassword)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Les mots de passe ne correspondent pas");
            alert.showAndWait();
            return;

        }

        Assistant assistant = new Assistant(medecinId, email, prenom, nom, password, cin, genre, telephone, gouvernorat, adresse, confirmPassword, image);
        AssistantService assistantService = new AssistantService();
        try {
            assistantService.ajouter(assistant);
            System.out.println("Assistant ajouté avec succès !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succès");
        alert.setHeaderText("Le compte a été créé avec succès !");
        alert.showAndWait();

        // Fermer la fenêtre d'ajout de médecin
        Stage stage = (Stage) btnAdd.getScene().getWindow();
        stage.close();
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
    private void handleUploadImage(ActionEvent event) {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Choose Image File");

    // Set the extension filter
    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.jpeg", "*.png");
    fileChooser.getExtensionFilters().add(extFilter);

    // Show the file chooser dialog
    File file = fileChooser.showOpenDialog(btnUploadImage.getScene().getWindow());

    if (file != null) {
        // Display the selected file name
        btnUploadImage.setText(file.getName());

        // Save the selected file to the "images" directory in the project folder
        Path imagePath = Paths.get("images", file.getName());
        try {
            Files.copy(file.toPath(), imagePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

}
