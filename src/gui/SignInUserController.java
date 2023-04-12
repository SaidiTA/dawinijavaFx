/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.UserService;

/**
 *
 * @author soumayaab
 */
public class SignInUserController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private Button btnlogin;
    @FXML
    private Text labelPass;
    @FXML
    private Text labelEmail;
    @FXML
    private TextField loginCol;
    @FXML
    private PasswordField mdpCol;
    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void login(ActionEvent event) {
         if(loginCol.getText().isEmpty() == false && mdpCol.getText().isEmpty() == false){
        UserService serv = new UserService();
        int res = serv.login(loginCol.getText(), mdpCol.getText());
      
           Parent root ;
                try{
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/ListMedecin.fxml"));
                    root = loader.load();
                    ListMedecinController listUsers = loader.getController();

                    Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    stage.setTitle("List Utilisateur");
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }catch(IOException e){
                    System.out.println(e.getMessage());
                }

      
}
    }
       }
