/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.ReplaySujet;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.NodeTraversor;
import org.jsoup.select.NodeVisitor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.jsoup.nodes.Node;
import services.ReplaySujetService;

/**
 * FXML Controller class
 *
 * @author jlidi
 */
public class ItemReponseController implements Initializable {

    @FXML
    private Label btndate;
    @FXML
    private Label btnmessage;
    @FXML
    private Button btnModify;
    @FXML
    private ImageView btnmodifier;
                 private ReplaySujet ReplaySujet;

    @FXML
    private Button btnDelete;
    @FXML
    private Label btnid;

        
public void setData(ReplaySujet replaysujet) {
    int id = replaysujet.getId();
    btnid.setText(Integer.toString(id));
   // btndate.setText(replaysujet.getDate().toString());

    String htmlContent = replaysujet.getMessage();
    String plainText = convertHtmlToPlainText(htmlContent);
    btnmessage.setText(plainText);
}

private String convertHtmlToPlainText(String html) {
    Document doc = Jsoup.parse(html);
    PlainTextExtractor textExtractor = new PlainTextExtractor();
    textExtractor.traverse(doc.body());
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
    private void handleButtonClick(ActionEvent event) {
   try {
        // Récupération des données du dossier sélectionné
        int id = Integer.parseInt(btnid.getText());
        ReplaySujetService dc = new ReplaySujetService();
        List<ReplaySujet> spec = dc.listerReponse();
        ReplaySujet reponse = null;
        for (ReplaySujet d : spec) {
            if (d.getId() == id) {
                reponse = d;
                break;
            }
        }
        
        // Chargement de la fenêtre de modification
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/ModfierReponse.fxml"));
        Parent parent = fxmlLoader.load();
        ModfierReponseController controller = fxmlLoader.getController();

        // Transmission des données du dossier à la fenêtre de modification
        controller.setData(reponse);

        // Affichage de la fenêtre de modification
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.show();
       
    } catch (IOException ex) {
        Logger.getLogger(ItemsujetController.class.getName()).log(Level.SEVERE, null, ex);
    } 
}

    @FXML
    private void sup_spec(ActionEvent event) {
   int id = Integer.parseInt(btnid.getText());
          ReplaySujetService dc = new ReplaySujetService();
            List<ReplaySujet> reponse = dc.listerReponse();
                 for (ReplaySujet d : reponse) {
                     if (d.getId() == id) {
                     dc.supprimer(d);
                     
                 break;
                 
        }
    }
    
    }
    
}
