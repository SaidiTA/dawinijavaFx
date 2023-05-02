/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package gui;

import entities.Medecin;
import entities.User;
import entities.UserSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import services.MedecinService;
import test.Dawini;

/**
 *
 * @author soumayaab
 */
public class ListMedecinController implements Initializable {
private double x, y;
    @FXML
    private VBox pnitems = null;
    @FXML
    private Button btnAdd;
    @FXML
    private ImageView btnAddplus1;   
    @FXML
    private Label Nom;
    @FXML
    private Label Prenom;
    @FXML
    private Label Email;
    @FXML
    private Label Telephone;
    @FXML
    private Label Titre;
    @FXML
    private Label Tarif;
    @FXML
    private Label Adresse;
    @FXML
    private Label Action;
    @FXML
    private Label ListeMedecin;
    @FXML
    private Label btnid;
    @FXML
    private Button btnRef1;
    @FXML
    private ImageView btnRef;
    @FXML
    private Button btnSearch;
    @FXML
    private TextField txtSearch;
    @FXML
    private Button btnlogout;
    @FXML
    private ImageView imgprofile;
   private Medecin medecin;
    @FXML
    private Button btnArticle;

    public Medecin getMedecin() {
        return medecin;
    }

    public void setMedecin(Medecin medecin) {
        this.medecin = medecin;
    }

    

      public void initialize(URL location, ResourceBundle resources) {
          User currentUser = UserSession.getInstance().getCurrentUser();
if (currentUser != null) {
    String imagePath = currentUser.getImagePath();
    try {
            //Image image = new Image(imageUrl.getUrl());
            // Image image = new Image(getClass().getResourceAsStream("../images/account.png"));
            Image image = new Image(Dawini.class.getClass().getResource("/images/" + medecin.getImage()).toString());
            System.out.println("imageUrl: " + image);
            imgprofile.setImage(image);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    

           MedecinService medecinService = new MedecinService();
         List<Medecin> medecins = null;

    try {
        medecins = medecinService.recuperer();
    } catch (SQLException ex) {
        System.out.println(ex);
    }
          
      


for (Medecin medecin : medecins) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/item.fxml"));
        Node node = loader.load();
        ItemController itemController = loader.getController();
        itemController.setData(medecin);

        node.setOnMouseEntered(event -> {
            node.setStyle("-fx-background-color : #c8d7db");
        });
        node.setOnMouseExited(event -> {
            node.setStyle("-fx-background-color :  #F1F7FD");
        });

        pnitems.getChildren().add(node);
    } catch (IOException e) {
        e.printStackTrace();
    }
}


    }
@FXML
    void handleButtonClick(ActionEvent event) {
      if(event.getSource()== btnAdd){
      showAsDialog("AddMedecin");
      }
      if(event.getSource()== btnAddplus1){
      showAsDialog("AddMedecin");
      }
      //if(event.getSource() == btncross) {
        // Get the Stage that contains the button
      //  Stage stage = (Stage) btncross.getScene().getWindow();
        // Close the stage
       // stage.close();
    //}
    }
    
    
    private void showAsDialog(String fxml)
    {
       try {
           Parent parent = FXMLLoader.load(getClass().getResource("/gui/AddMedecin.fxml"));
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
    private void refreshTable() {
    pnitems.getChildren().clear();
    MedecinService medecinService = new MedecinService();
    List<Medecin> medecins = null;

    try {
        medecins = medecinService.recuperer();
    } catch (SQLException ex) {
        System.out.println(ex);
    }

    for (Medecin medecin : medecins) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/item.fxml"));
            Node node = loader.load();
            ItemController itemController = loader.getController();
            itemController.setData(medecin);
            pnitems.getChildren().add(node);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }

   

    @FXML
private void search(ActionEvent event) {
    pnitems.getChildren().clear();
    MedecinService medecinService = new MedecinService();
    List<Medecin> medecins = null;

    try {
        medecins =  medecinService.rechercher(txtSearch.getText());
    } catch (SQLException ex) {
        System.out.println(ex);
    }

   for (Medecin medecin : medecins) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/item.fxml"));
        Node node = loader.load();
        ItemController itemController = loader.getController();
        itemController.setData(medecin);
        pnitems.getChildren().add(node);
    } catch (IOException e) {
        e.printStackTrace();
    }
}
}

   @FXML
private void handleLogoutButtonAction(ActionEvent event) throws IOException {
    // Supprimez la session utilisateur en cours
    UserSession.getInstance().setCurrentUser(null);

    // Redirigez l'utilisateur vers l'écran de connexion
    Parent root = FXMLLoader.load(getClass().getResource("SignInUser.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
}

    @FXML
    private void Med(ActionEvent event) {
           try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListMedecin.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
       
        
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    

    
    
    }

    @FXML
    private void ASSISTANT(ActionEvent event) {
           try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListAssistant.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
       
        
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    }

    
    
    

    @FXML
    private void PAT(ActionEvent event) {
           try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListPatients.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
       
        
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    }

    
    
    

    @FXML
    private void AVIS(ActionEvent event) {
       try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListAvis.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
       
        
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    }

    @FXML
    private void ARTICLE(ActionEvent event) {
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListAdminArticle.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
       
        
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    }

    @FXML
    private void handleButtonClick(MouseEvent event) {
    }

    
    

 
   

  


    
    
}
