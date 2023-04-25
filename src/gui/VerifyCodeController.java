/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import entities.User;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author soumayaab
 */
public class VerifyCodeController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private Button btnVerif;
    @FXML
    private TextField TEL;
    @FXML
    private TextField CODE;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void Verifier(ActionEvent event) {

        String telephone = TEL.getText();
        String code = CODE.getText();
        UserService service = new UserService();
        User user = service.CheckVerifCode(telephone, code);
        if (user.getId() != 0) {
            user.setVerifCode(null);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Verification code sent successfully.");
            alert.showAndWait();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("NewPassword.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                
            NewPasswordController newPaswdController = loader.getController();
            newPaswdController.setData(user);
                
                
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("telephone n'exist pas ");
            alert.showAndWait();
            return;

        }

    }
}
