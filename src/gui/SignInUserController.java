/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package gui;

import entities.User;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;
import services.UserService;
import util.MyDB;
import entities.UserSession;
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
    private UserSession session; //Malek
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnlogin.setOnAction((ActionEvent event) -> {
            try {
                login(event);
            } catch (SQLException ex) {
                System.err.println(ex);
            } catch (IOException ex) {
                System.err.println(ex);
            }

        });
    }

    @FXML
    private void login(ActionEvent event) throws SQLException, IOException {
        PreparedStatement st = null;

        Connection cnx = MyDB.getInstance().getCnx();
        
        PreparedStatement pst = cnx.prepareStatement("SELECT * FROM user WHERE email=?");
        pst.setString(1, loginCol.getText());
        ResultSet rs = pst.executeQuery();
       
        if (rs.next()) {
            if(rs.getInt("enabled")==1){
            String hashedPassword = rs.getString("password");
            System.out.println("*hash " + hashedPassword);
            System.out.println("*loginCol.getText() " + mdpCol.getText());

            boolean passwordMatch = BCrypt.checkpw(mdpCol.getText(), hashedPassword);
            System.out.println("passwordMatch " + passwordMatch);

            if (BCrypt.checkpw(mdpCol.getText(), hashedPassword)) {
               //Malek
        User us=new User();
        us.setId(rs.getInt("id"));
         us.setNom(rs.getString("nom"));
          us.setPrenom(rs.getString("prenom"));
           us.setEmail(rs.getString("email"));
           us.setImage(rs.getString("image"));
     UserSession.getInstance().setCurrentUser(us);
            
                
                
                
                // Passwords match
                
                String roles = rs.getString("roles");
                if (roles.equals("[\"ROLE_ADMIN\"]")) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/ListMedecin.fxml"));
                    Parent root = loader.load();
                    // get the controller for the new scene and set any necessary parameters
                    ListMedecinController controller = loader.getController();
                    // controller.setLoggedInUser(new User(rs.getInt("id"), rs.getString("name"), rs.getString("email")));
                    // show the new scene
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(new Scene(root));
                    stage.show();
                } else if (roles.equals("[\"ROLE_MEDECIN\"]")) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/ListMedecinArticle.fxml"));
                    Parent root = loader.load();
                    // get the controller for the new scene and set any necessary parameters
                    ListMedecinArticle controller = loader.getController();
                    // controller.setLoggedInUser(new User(rs.getInt("id"), rs.getString("name"), rs.getString("email")));
                    // show the new scene
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(new Scene(root));
                    stage.show();
                } else if (roles.equals("[\"ROLE_PATIENT\"]")) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Home.fxml"));
                    Parent root = loader.load();
                    // get the controller for the new scene and set any necessary parameters
                    HomeController controller = loader.getController();
                    // controller.setLoggedInUser(new User(rs.getInt("id"), rs.getString("name"), rs.getString("email")));
                    // show the new scene
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(new Scene(root));
                    stage.show();
                } else {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/ListAssistant.fxml"));
                    Parent root = loader.load();
                    // get the controller for the new scene and set any necessary parameters
                    ListAssistantController controller = loader.getController();
                    // controller.setLoggedInUser(new User(rs.getInt("id"), rs.getString("name"), rs.getString("email")));
                    // show the new scene
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(new Scene(root));
                    stage.show();
                }
            } else {
                // Passwords don't match
                Alert alert = new Alert(Alert.AlertType.WARNING, "Invalid email or password", ButtonType.OK);
                alert.showAndWait();

            }
        }else{
             Alert alert = new Alert(Alert.AlertType.WARNING, "Compte désactivé", ButtonType.OK);
                alert.showAndWait();

        }
        }
    }

    @FXML
    private void ForgotPassword(ActionEvent event) {
          try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SendSms.fxml"));
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
    private void register(ActionEvent event) {
          try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SignUpMedecin.fxml"));
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
