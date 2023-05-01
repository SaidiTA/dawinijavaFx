/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import entities.Commentaire;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author soumayaab
 */
public class AllcommentsController implements Initializable {

    private Commentaire commentaire;

    @FXML
    private VBox allComments;
    @FXML
    private Text ID;
    @FXML
    private Text nompat;
    @FXML
    private Text DATE;
    @FXML
    private Text MESSAGE;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    void setData(Commentaire commentaire) {
        int id = commentaire.getId();
        ID.setText(Integer.toString(id));
        nompat.setText(commentaire.getUser().getNom() + " " + commentaire.getUser().getPrenom());
        DATE.setText(commentaire.getDate().toString());

        MESSAGE.setText(commentaire.getMessage());

    }

    public Commentaire getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(Commentaire commentaire) {
        this.commentaire = commentaire;
    }
}