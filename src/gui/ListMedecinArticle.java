/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;


import entities.Article;
import entities.User;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import static javafx.collections.FXCollections.observableList;
import static javafx.collections.FXCollections.observableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import services.ArticleService;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author soumayaab
 */
public class ListMedecinArticle implements Initializable {

    private double x, y;
    @FXML
    private Label btnid;
    @FXML
    private Label Nom;
    @FXML
    private Label Prenom;
    @FXML
    private Label Action;
    @FXML
    private VBox pnitems = null;
    @FXML
    private Button btnAdd;
    @FXML
    private ImageView btnAddplus1;
    @FXML
    private Button btnRef1;
    @FXML
    private ImageView btnRef;
    @FXML
    private Label ListeArticle;
    @FXML
    private Label date;
    @FXML
    private Button exportButton;
    @FXML
    private Button listpat;//Malek
    @FXML
    private Button dos;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArticleService articleService = new ArticleService();
        List<Article> articles = null;

        try {
            articles = articleService.recuperer();
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        for (Article article : articles) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/itemArticleMedecin.fxml"));

                Node node = loader.load();
                ItemArticleMedecinController itemController = loader.getController();
                itemController.setData(article);
                pnitems.getChildren().add(node);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void handleButtonClick(ActionEvent event) throws SQLException {

        if (event.getSource() == btnAdd) {
            showAsDialog("addArticle");
            // Envoyer un e-mail après l'ajout d'un nouvel article

        }
        if (event.getSource() == btnAddplus1) {
            showAsDialog("addArticle");
            // Envoyer un e-mail après l'ajout d'un nouvel article
           // MailSender sender = new MailSender();
            //sender.sendMail("abderahmen.asma@gmail.com");
        }
    }

    private void showAsDialog(String fxml) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/gui/addArticle.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.initStyle(StageStyle.UNDECORATED);

            //drag it here
            parent.setOnMousePressed(event -> {
                x = event.getSceneX();
                y = event.getSceneY();
            });
            parent.setOnMouseDragged(event -> {

                stage.setX(event.getScreenX() - x);
                stage.setY(event.getScreenY() - y);

            });
            stage.show();

            //primaryStage.setTitle("Sign In User");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @FXML
    private void refreshTable(ActionEvent event) {
        pnitems.getChildren().clear();
        ArticleService articleService = new ArticleService();
        List<Article> arts = null;

        try {
            arts = articleService.recuperer();
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        for (Article article : arts) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/itemArticleMedecin.fxml"));
                Node node = loader.load();
                ItemArticleMedecinController itemController = loader.getController();
                itemController.setData(article);
                pnitems.getChildren().add(node);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

 
    @FXML
    private void exportToExcel(ActionEvent event) throws SQLException {
        List<Article> articlee = ArticleService.recuperer();
       //  List<Article> articlee = null ;
         FileChooser fileChooser = new FileChooser();

    // Set extension filter for Excel files
    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Excel files (.xlsx)", ".xlsx");
    fileChooser.getExtensionFilters().add(extFilter);

    // Show save file dialog
    File file = fileChooser.showSaveDialog(exportButton.getScene().getWindow());

    if (file != null) {
        try {
            // Create new Excel workbook and sheet
           Workbook workbook = new XSSFWorkbook();

            Sheet sheet = workbook.createSheet("articles");

            // Create header row
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("ID");
            headerRow.createCell(1).setCellValue("Nom");
            headerRow.createCell(2).setCellValue("Description");
            headerRow.createCell(3).setCellValue("Date");

            // Add data rows
            for (int i = 0; i < articlee.size(); i++) {
                Row row = sheet.createRow(i+1);
                row.createCell(0).setCellValue(articlee.get(i).getId());
                row.createCell(1).setCellValue(articlee.get(i).getNom());
                row.createCell(2).setCellValue(articlee.get(i).getDescription());
                row.createCell(3).setCellValue(articlee.get(i).getDate());
            }

            // Write to file
            FileOutputStream fileOut = new FileOutputStream(file);
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();

            // Show success message
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Export Successful");
            alert.setHeaderText(null);
            alert.setContentText("articles exported to Excel file.");
            alert.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Export Failed");
            alert.setHeaderText(null);
            alert.setContentText("An error occurred while exporting articles to Excel file.");
            alert.showAndWait();
        }
    
        
     
        
    }
    

       

}

       @FXML
    private void handleButtonClick(MouseEvent event) {
    }

    @FXML
    private void refreshTable(MouseEvent event) {
    }

    
    //Malek
    @FXML
    private void gotolistpat(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/affichePatient.fxml"));
        Parent root;
        // Affichage de la page du tableau de bord du médecin

        root = loader.load();
        // Récupération du contrôleur de la page de modification de consultation
        AffichePatientController affichpat = loader.getController();

        // Initialisation des champs de la page de modification de consultation avec les données de la consultation actuelle
       
        listpat.getScene().setRoot(root);
        
    }

    @FXML
    private void dossier(ActionEvent event){
                  try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListDossier.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
       
        
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    }





}
