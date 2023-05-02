/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Consulation;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import services.ConsulationService;
import services.ordonnanceService;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class DashController implements Initializable {

    private List<Consulation> Consultations;
    private int patient;
    @FXML
    private Button listpatient;

    public int getPatient() {
        return patient;
    }

    public void setPatient(int patient) {

        this.patient = patient;
    }
    private ConsulationService consService = new ConsulationService();
    @FXML
    private VBox contenu;
    @FXML
    private Label id_consultation;
    @FXML
    private Label date;
    @FXML
    private Label heureD;
    @FXML
    private Label heureF;
    @FXML
    private Button ConsultationAddButton;
    @FXML
    private Label action;
    @FXML
    private Label ord;
    @FXML
    private TextField chercher;
    @FXML
    private Button calendar;
    @FXML
    private Button excel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    private void search(String query) {
        // Perform the search based on the query string
        List<Consulation> results = Consultations.stream()
                .filter(c -> String.valueOf(c.getHeuredebut()).contains(query))
                .collect(Collectors.toList());

        // Update the pagination with the search results
        Pagination pagination = (Pagination) contenu.getChildren().get(0);
        pagination.setPageCount((int) Math.ceil(results.size() / 5.0));
        pagination.setCurrentPageIndex(0);
        pagination.setPageFactory((Integer pageIndex) -> {
            VBox pageContent = new VBox();
            int pageStart = pageIndex * 5;
            int pageEnd = Math.min(pageStart + 5, results.size());
            pageContent.getChildren().clear(); // Clear the VBox before adding new search results
            for (int i = pageStart; i < pageEnd; i++) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("cardConsultation.fxml"));
                try {
                    HBox hBox = fxmlLoader.load();
                    CardConsultationController listconsultation = fxmlLoader.getController();

                    // Vérifier s'il y a une ordonnance pour cette consultation
                    ordonnanceService ordService = new ordonnanceService();
                    if (ordService.recupererbycons(results.get(i).getId()) == null) {
                        // S'il n'y a pas d'ordonnance, on permet à l'utilisateur de modifier/supprimer/l'afficher
                        listconsultation.setModif_ord();
                        listconsultation.setSupp_ord();
                        listconsultation.setaff_ord();
                    }

                    // Initialiser les données de la carte avec les données de la consultation
                    listconsultation.setData(results.get(i));

                    // Ajouter la carte à la page actuelle
                    pageContent.getChildren().add(hBox);
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                } catch (SQLException ex) {
                    Logger.getLogger(DashController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return pageContent;
        });
    }

    public void initialize(int id) {
        patient = id;
        try {
           
            // Récupérer la liste des consultations pour l'utilisateur connecté 
            
            Consultations = new ArrayList<>(consService.recupererByIdPatient(id));

            // Initialiser la pagination
            Pagination pagination = new Pagination();
            pagination.setPageCount((int) Math.ceil(Consultations.size() / 5.0)); // 5 consultations par page
            pagination.setPageFactory((Integer pageIndex) -> {
                VBox pageContent = new VBox();
                int pageStart = pageIndex * 5;
                int pageEnd = Math.min(pageStart + 5, Consultations.size());
                for (int i = pageStart; i < pageEnd; i++) {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("cardConsultation.fxml"));
                    try {
                        HBox hBox = fxmlLoader.load();
                        CardConsultationController listconsultation = fxmlLoader.getController();

                        // Vérifier s'il y a une ordonnance pour cette consultation
                        ordonnanceService ordService = new ordonnanceService();
                        if (ordService.recupererbycons(Consultations.get(i).getId()) == null) {
                            // S'il n'y a pas d'ordonnance, on permet à l'utilisateur de modifier/supprimer/l'afficher
                            listconsultation.setModif_ord();
                            listconsultation.setSupp_ord();
                            listconsultation.setaff_ord();
                        }

                        // Initialiser les données de la carte avec les données de la consultation
                        listconsultation.setData(Consultations.get(i));
                        
                        // Ajouter la carte à la page actuelle
                        pageContent.getChildren().add(hBox);
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    } catch (SQLException ex) {
                        Logger.getLogger(DashController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                return pageContent;
            });

            // Ajouter la pagination à la VBox principale
            contenu.getChildren().add(pagination);

            // Ajouter un écouteur d'événements pour la recherche
            chercher.textProperty().addListener((observable, oldValue, newValue) -> {
                search(newValue);
            });

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    public void AjouterConsultation(ActionEvent event) throws SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/AjoutConsultation.fxml"));
        Parent root;
        try {
            root = loader.load();
            // Obtenir le contrôleur de la vue AjoutConsultation

            AjoutConsultationController ajC = loader.getController();
            // Remplacer la vue actuelle par la vue AjoutConsultation
            ajC.initialize(patient);
            ConsultationAddButton.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void gotocalendar(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Calendar.fxml"));
        Parent root;
        // Affichage de la page du tableau de bord du médecin

        root = loader.load();
        // Récupération du contrôleur de la page de modification de consultation
        CalendarController dash = loader.getController();

        // Initialisation des champs de la page de modification de consultation avec les données de la consultation actuelle
        dash.initialize(patient);
        calendar.getScene().setRoot(root);
    }

    @FXML
    private void exportToExcel(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();

        // Set extension filter for Excel files
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Excel files (.xlsx)", ".xlsx");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File file = fileChooser.showSaveDialog(excel.getScene().getWindow());

        if (file != null) {
            try {
                // Create new Excel workbook and sheet
                Workbook workbook = new XSSFWorkbook();

                org.apache.poi.ss.usermodel.Sheet sheet = workbook.createSheet("Consultations");

                // Create header row
                Row headerRow = sheet.createRow(0);
                headerRow.createCell(0).setCellValue("ID");
                headerRow.createCell(1).setCellValue("Date");
                headerRow.createCell(2).setCellValue("Heure_debut");
                headerRow.createCell(3).setCellValue("Heure_fin");

                // Add data rows
                for (int i = 0; i < Consultations.size(); i++) {
                    Row row = sheet.createRow(i + 1);
                    row.createCell(0).setCellValue(Consultations.get(i).getId());
                    row.createCell(1).setCellValue(Consultations.get(i).getDate());
                    row.createCell(2).setCellValue(Consultations.get(i).getHeuredebut());
                    row.createCell(3).setCellValue(Consultations.get(i).getHeurefin());
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
                alert.setContentText("Consultations exported to Excel file.");
                alert.showAndWait();

            } catch (Exception e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Export Failed");
                alert.setHeaderText(null);
                alert.setContentText("An error occurred while exporting consultations to Excel file.");
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void redirectToListPatient(ActionEvent event) {
    }

}
