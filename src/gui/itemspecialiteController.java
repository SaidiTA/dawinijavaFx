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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.jsoup.nodes.Node;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.NodeTraversor;
import org.jsoup.select.NodeVisitor;
import javafx.stage.StageStyle;
import services.specialitesService;

/**
 * FXML Controller class
 *
 * @author jlidi
 */
public class itemspecialiteController implements Initializable {
     private double x, y;
 private Specialites specialite;

    @FXML
    private Button btnDelete;
    @FXML
    private Button btnModify;
    @FXML
    private Label btnnom;
    private Label btndescription;
    @FXML
    private ImageView btnmodifier;
    @FXML
    private Label btnid;
    @FXML
    private Label btndescrip;
    
public void setData(Specialites specialite) {
    int id = specialite.getId();
    btnid.setText(Integer.toString(id));
    btnnom.setText(specialite.getNom());

    String htmlContent = specialite.getDescription();
    String plainText = convertHtmlToPlainText(htmlContent);
    btndescrip.setText(specialite.getDescription());
    System.out.println(plainText);
}

private String convertHtmlToPlainText(String html) {
    Document doc = Jsoup.parse(html);
    PlainTextExtractor textExtractor = new PlainTextExtractor();
    Element body = doc.body();
    if (body != null) {
        textExtractor.traverse(body);
    }
    return textExtractor.getText();
}

private static class PlainTextExtractor implements NodeVisitor {
    private StringBuilder plainText;

    public PlainTextExtractor() {
        plainText = new StringBuilder();
    }

    public String getText() {
        return plainText.toString();
    }

    @Override
    public void head(Node node, int depth) {
        if (node instanceof TextNode) {
            TextNode textNode = (TextNode) node;
            plainText.append(textNode.text());
        } else if (node instanceof Element) {
            Element element = (Element) node;
            if (element.isBlock() || element.tagName().equals("br")) {
                plainText.append("\n");
            }
        }
    }

    @Override
    public void tail(Node node, int depth) {
        // Do nothing
    }

    public void traverse(Element element) {
        for (Node child : element.childNodes()) {
            child.traverse(this);
        }
    }
}
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
void handleButtonClick(ActionEvent event) {
    try {
        // Récupération des données du dossier sélectionné
        int id = Integer.parseInt(btnid.getText());
        specialitesService dc = new specialitesService();
        List<Specialites> spec = dc.listerSpecialites();
        Specialites specialite = null;
        for (Specialites d : spec) {
            if (d.getId() == id) {
                specialite = d;
                break;
            }
        }
        
        // Chargement de la fenêtre de modification
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/ModifierSpecialites.fxml"));
        Parent parent = fxmlLoader.load();
        ModifierSpecialitesController controller = fxmlLoader.getController();

        // Transmission des données du dossier à la fenêtre de modification
        controller.setData(specialite);

        // Affichage de la fenêtre de modification
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.show();
       
    } catch (IOException ex) {
        Logger.getLogger(itemspecialiteController.class.getName()).log(Level.SEVERE, null, ex);
    } 
}

@FXML
    private void sup_spec(ActionEvent event) {
         
         
        int id = Integer.parseInt(btnid.getText());
           specialitesService dc = new specialitesService();
            List<Specialites> specialite = dc.listerSpecialites();
                 for (Specialites d : specialite) {
                     if (d.getId() == id) {
                     dc.supprimer(d);
                     
                 break;
                 
        }
    }
    
    }
   
}
