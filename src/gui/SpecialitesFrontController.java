/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Specialites;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import services.specialitesService;

/**
 * FXML Controller class
 *
 * @author jlidi
 */
public class SpecialitesFrontController implements Initializable {
private double x, y;

    @FXML
    private Label nomspecialites;
    @FXML
    private Button btnAdd;
    @FXML
    private ImageView btnAddplus1;
    @FXML
    private Label nomspecialites1;
private Specialites spec;
    @FXML
    private HBox pnitems;
    @FXML
    private Hyperlink home;
    @FXML
    private Hyperlink btnRdv;
    @FXML
    private Hyperlink BtnArt;
    @FXML
    private Hyperlink BtnForum;

    public Specialites getSpec() {
        return spec;
    }

    public void setSpec(Specialites spec) {
        this.spec = spec;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        List<Specialites> specialites = getSpecialitesList();
    Pagination pagination = new Pagination();
    pagination.setPageCount((int) Math.ceil(specialites.size() / 3.0)); // 5 sujets par page
    pagination.setPageFactory((Integer pageIndex) -> {
        HBox pageContent = new HBox();
        int pageStart = pageIndex * 3;
        int pageEnd = Math.min(pageStart + 3, specialites.size());
        for (int i = pageStart; i < pageEnd; i++) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/ItemSpecialitesFront.fxml"));
            try {
                Node node = fxmlLoader.load();
                ItemSpecialitesFrontController sujetController = fxmlLoader.getController();

                // Initialiser les données du sujet avec les données de la liste
                sujetController.setData(specialites.get(i));

                
                
                // Ajouter le sujet à la page actuelle
                pageContent.getChildren().add(node);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return pageContent;
    });

    // Ajouter la pagination à la VB
    pnitems.getChildren().add(pagination);
    }
    
  private List<Specialites> getSpecialitesList() {
        specialitesService ps=new specialitesService();
        List<Specialites> specialites = ps.listerSpecialite();
        return specialites;
    }
        
  
       

       
    

    @FXML
    private void handleButtonClick(ActionEvent event) {
        if(event.getSource()== btnAdd){
      showAsDialog("AddSujet");
      }
      if(event.getSource()== btnAddplus1){
      showAsDialog("AddSujet");
      }
    }

   private void showAsDialog(String fxml)
    {
       try {
           Parent parent = FXMLLoader.load(getClass().getResource("/gui/AddSujet.fxml"));
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
    private void HOME(ActionEvent event) {
    }

    @FXML
    private void RDV(ActionEvent event) {
    }

    @FXML
    private void ARTICLE(ActionEvent event) {
    }

    @FXML
    private void FORUM(ActionEvent event) {
    }

    @FXML
    private void handleButtonClick(MouseEvent event) {
    }

    
    

    

    
}
