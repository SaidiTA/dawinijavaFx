/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Diagnostique;
import entities.Dossier;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterAttributes;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import services.DiagnostiqueCrud;
import services.DossierCrud;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import util.MyDB;

/**
 * FXML Controller class
 *
 * @author msi
 */
public class ListDossierController implements Initializable {

    @FXML
    private ImageView search;
    @FXML
    private VBox dos;
    @FXML
    private TextField num;
    @FXML
    private TextField code;
    @FXML
    private TextField descp;
    
   // @FXML
   // private ComboBox<String> ExporterListe;
    @FXML
    private TextField Recherche;
    @FXML
    private HBox HB;
    @FXML
    private ComboBox<String> patientComboBox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Dossier> dossiers = dossiers();
        for(Dossier dossier:dossiers){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Dossier_item.fxml"));
     
            try{
                HBox hBox =fxmlLoader.load();
                Dossier_itemController dic= fxmlLoader.getController();
                dic.setData(dossier);
                dos.getChildren().add(hBox);
            } catch (IOException ex) {
               ex.printStackTrace(); 
            }
        }
        
       initializePatientComboBox();
        //    ObservableList<String> list1 = FXCollections.observableArrayList("PDF", "Excel", "Imprimer");
        //  ExporterListe.setItems(list1);
        // TODO
    }  
    /*       private void initializePatientComboBox() {
    try {
   String query = "SELECT DISTINCT CONCAT(u.nom, ' ', u.prenom) AS fullname "
+ "FROM user u "
+ "JOIN patient p ON u.id = p.id "
+ "WHERE u.roles LIKE '%ROLE_PATIENT%'";

        PreparedStatement preparedStatement = MyDB.getInstance().getCnx().prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            String fullname = resultSet.getString("fullname");
            patientComboBox.getItems().add(fullname);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
}*/
    int selectedPatientId = 0;
    
    private void initializePatientComboBox() {
    try {
        String query = "SELECT DISTINCT CONCAT(u.nom, ' ', u.prenom) AS fullname, p.id "
            + "FROM user u "
            + "JOIN patient p ON u.id = p.id "
            + "WHERE u.roles LIKE '%ROLE_PATIENT%'";

        PreparedStatement preparedStatement = MyDB.getInstance().getCnx().prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            String fullname = resultSet.getString("fullname");
            int patientId = resultSet.getInt("id");
            patientComboBox.getItems().add(fullname);
            // Stocker l'ID du patient sélectionné
            patientComboBox.setOnAction(e -> {
                selectedPatientId = patientId;
            });
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
}

   
               private boolean num_resvalide(){
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(num.getText());
        if(m.find() && m.group().equals(num.getText())){
            return true;
        }else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("numero invalide !");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez entrer un numero valide !");
                alert.showAndWait();
           
            return false;            
        }
     }
               private boolean code_resvalide(){
      Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(code.getText());
        if(m.find() && m.group().equals(code.getText())){
            return true;
        }else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("code apci invalide !");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez entrer un code apci valide !");
                alert.showAndWait();
           
            return false;            
        }
     }
                private boolean descp_resvalide(){
      Pattern p = Pattern.compile("[a-zA-Z ]+");
        Matcher m = p.matcher(descp.getText());
        if(m.find() && m.group().equals(descp.getText())){
            return true;
        }else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("description valide !");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez entrer un description valide !");
                alert.showAndWait();
           
            return false;            
        }
     }
                
    private List<Dossier> dossiers(){
DossierCrud ps = new DossierCrud();
List<Dossier> dossiers = ps.listerDossiers();
return dossiers;
    }
    
    
    private void updateDossiers() {
    dos.getChildren().clear();
    dos.getChildren().add(HB);
    List<Dossier> dossiers = dossiers();
    for(Dossier d : dossiers) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("Dossier_item.fxml"));
        try{
            HBox hBox = fxmlLoader.load();
            Dossier_itemController dic = fxmlLoader.getController();
            dic.setData(d);
            
            dos.getChildren().add(hBox);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
       

  

   /* @FXML
    private void btn_aj_dos(ActionEvent event) {
       
        if(num_resvalide()&&code_resvalide()&&descp_resvalide()){
        int numero = Integer.parseInt(num.getText());
        String code_apci = code.getText();
        String description  = descp.getText();
        
       
           // Récupération de l'ID du patient sélectionné dans la combobox
        String fullname = patientComboBox.getValue();
        int patient_id = 0;
        try {
            PreparedStatement preparedStatement = MyDB.getInstance().getCnx()
                    .prepareStatement("SELECT u.id FROM user u JOIN patient p ON u.id = p.id WHERE CONCAT(u.nom, ' ', u.prenom) = ?");
            preparedStatement.setString(1, fullname);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                patient_id = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Dossier d = new Dossier(patient_id,numero, code_apci, description);
        
        DossierCrud dc = new DossierCrud();
        dc.ajouter_dossier(d);
       
       DossierCrud sv = new DossierCrud();
        Dossier v = new Dossier();
        String Code = code.getText();
     //   int y = sv.calculnb((code.getText()));
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        tray.setAnimationType(type);
        tray.setTitle("Felicitation");
        tray.setMessage("dossier ajouté avec succées");
        tray.setNotificationType(NotificationType.INFORMATION);
        tray.showAndDismiss(Duration.millis(2000));
        
        num.clear();
        code.clear();
        descp.clear();
         updateDossiers();
        
    }
    
    }
    
    

*/
    
    @FXML
private void btn_aj_dos(ActionEvent event) {

    if(num_resvalide()&&code_resvalide()&&descp_resvalide()){
        int numero = Integer.parseInt(num.getText());
        String code_apci = code.getText();
        String description  = descp.getText();
        
        // Utiliser l'ID du patient sélectionné
        Dossier d = new Dossier(selectedPatientId, numero, code_apci, description);

        DossierCrud dc = new DossierCrud();
        dc.ajouter_dossier(d);
}
        DossierCrud sv = new DossierCrud();
        Dossier v = new Dossier();
        String Code = code.getText();
        //int y = sv.calculnb((code.getText()));
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        tray.setAnimationType(type);
        tray.setTitle("Felicitation");
        tray.setMessage("dossier ajouté avec succées");
        tray.setNotificationType(NotificationType.INFORMATION);
        tray.showAndDismiss(Duration.millis(2000));

        num.clear();
        code.clear();
        descp.clear();
        updateDossiers();

    }



    @FXML
    private void Recherche(KeyEvent event) {
          DossierCrud se = new DossierCrud();
        String chaine = Recherche.getText();
        populateTable(se.chercherDossier(chaine));
    }

  /*  private void populateTable(ObservableList<Dossier> chercherDossier) {
     dos.getChildren().clear();
        for(Dossier dossier: chercherDossier){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Dossier_item.fxml"));
            try{
                HBox hBox =fxmlLoader.load();
                Dossier_itemController dic= fxmlLoader.getController();
                dic.setData(dossier);
                dos.getChildren().add(hBox);
            } catch (IOException ex) {
               ex.printStackTrace(); 
            }
        }   
    }
 */
    private void populateTable(ObservableList<Dossier> chercherDossier) {
     dos.getChildren().clear();
     dos.getChildren().add(HB);
     if (chercherDossier.isEmpty()) {
         // Créer le label avec le message "Aucun dossier trouvé"
         Label aucunDossierLabel = new Label("Aucun dossier trouvé");
         aucunDossierLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
         aucunDossierLabel.setAlignment(Pos.CENTER);

         // Ajouter le label à la VBox dos
         dos.getChildren().add(aucunDossierLabel);
     } else {
         // Afficher les dossiers dans la VBox dos
         for(Dossier dossier: chercherDossier){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Dossier_item.fxml"));
            try{
                HBox hBox =fxmlLoader.load();
                Dossier_itemController dic= fxmlLoader.getController();
                dic.setData(dossier);
                dos.getChildren().add(hBox);
            } catch (IOException ex) {
               ex.printStackTrace(); 
            }
        }   
     }
} 

   /* private void PDF(MouseEvent event) {
        Dossier d = TableVoyage.getSelectionModel().getSelectedItem();

        Pdf pd=new Pdf();
        try{
                    pd.GeneratePdf("MesInformations",d,d.getId());

            System.out.println("impression done");
        } catch (Exception ex) {
            Logger.getLogger(DossierCrud.class.getName()).log(Level.SEVERE, null, ex);
            }
    }*/
    
    public static void printNode(final Node node) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Printer printer = Printer.getDefaultPrinter();
        PageLayout pageLayout = printer.createPageLayout(Paper.A4, PageOrientation.LANDSCAPE, Printer.MarginType.DEFAULT);
        PrinterAttributes attr = printer.getPrinterAttributes();
        PrinterJob job = PrinterJob.createPrinterJob();
        double scaleX = pageLayout.getPrintableWidth() / node.getBoundsInParent().getWidth();
        double scaleY = pageLayout.getPrintableHeight() / node.getBoundsInParent().getHeight();
        Scale scale = new Scale(scaleX, scaleY);
        node.getTransforms().add(scale);
        
        if (job != null && job.showPrintDialog(node.getScene().getWindow())) {
            boolean success = job.printPage(pageLayout, node);
            if (success) {
                job.endJob();
                
            }
        }
        node.getTransforms().remove(scale);
        
    }
    private void ImprimerAction(ActionEvent event) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        //        printNode(TableVoyage);
    }

    @FXML
    private void retour(ActionEvent event) {
                     try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListMedecinArticle.fxml"));
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
