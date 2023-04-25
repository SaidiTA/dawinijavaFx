/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import entities.User;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
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
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author soumayaab
 */
public class NewPasswordController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private Button btnModPass;
    @FXML
    private PasswordField pass;

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void UpdatePass(ActionEvent event) throws SQLException {

        try {
            String hashedPassword = BCrypt.hashpw(pass.getText(), BCrypt.gensalt());
            UserService service = new UserService();
            service.updatePSWD(this.user, hashedPassword);
            
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Mot de passe modifié avec succé!");
            alert.showAndWait();
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SignInUser.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void setData(User user) {

        this.user = user;

    }

}
