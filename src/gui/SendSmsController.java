/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import com.twilio.Twilio;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import entities.User;
import static gui.SendSMS.ACCOUNT_SID;
import static gui.SendSMS.AUTH_TOKEN;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author soumayaab
 */
public class SendSmsController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private TextField TEL;
    @FXML
    private Button btnEnvoi;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void sendSms(ActionEvent event) throws SQLException {

        UserService userService = new UserService();
        String telephone = TEL.getText();
        User user = userService.VerifyPhone(telephone);

       
        if (user.getId() != 0) {
            String code = generateCode();
            String body = "Bonjour! Le code de verification pour changement de mot de passe: " + code;

            // Send the code via SMS
            SendSMS.sendMessage("+216" + telephone, body);
            //user.setVerifCode(code);
            userService.setVerifCode(user, code);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Verification code sent successfully.");
            alert.showAndWait();
  try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("VerifyCode.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
       
        
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
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

    private String generateCode() {
        // Generate a random 6-digit verification code
        int code = (int) (Math.random() * 900000) + 100000;
        return String.valueOf(code);
    }
}
