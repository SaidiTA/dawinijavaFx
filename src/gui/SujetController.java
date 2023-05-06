/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Specialites;
import entities.Sujet;
import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.NodeVisitor;
import services.specialitesService;
import services.sujetService;
import test.Dawini;

/**
 * FXML Controller class
 *
 * @author jlidi
 */
public class SujetController implements Initializable {
        sujetService Sp = new sujetService();
         List<Sujet> lt = Sp.listerSujet();

    @FXML
    private VBox pnitems;
    @FXML
    private TextField search;
    @FXML
    private ImageView btnsearch;
    private ComboBox<String> Trie;
    private Button exportButton;
     private Node selectedVBox; 
    private Specialites spec;
    @FXML
    private Label btnom;
    @FXML
    private Label btndescription;
    @FXML
    private Label btnid;
    @FXML
    private ImageView images;
    @FXML
    private Button btnback;

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
 
    }    

   


    @FXML
    private void buttonSearch(ActionEvent event) {
    String recherche = search.getText();

    // Create an instance of the CommandeService
    sujetService tt = new sujetService();
    // Perform the search using the entered text
    lt = tt.RechercherSujet(recherche);
    System.out.println("Recherche");
    System.out.println(recherche);

    // Clear the previous items in the VBox
    pnitems.getChildren().clear();

    // Iterate through the search results and add an FXML item for each item
    for (Sujet sujet : lt) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/itemsujet.fxml"));
            Node node = loader.load();
            ItemsujetController ItemsujetController = loader.getController();
            ItemsujetController.setDataa(sujet);
            pnitems.getChildren().add(node);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

    @FXML
    private void buttonSearch(MouseEvent event) {
    }

    private void Trie(ActionEvent event) {
    
    }

    private List<Sujet> getSujetList() {
sujetService ps=new sujetService();
        List<Sujet> sujet = ps.listerSujet();
                return sujet;
   
    }
public void setSujet(Specialites specialite) {
        btnid.setText(String.valueOf(specialite.getId()));
        
        this.spec = specialite;
        btnom.setText(specialite.getNom());
        btndescription.setText(specialite.getDescription());
        String htmlContent = specialite.getDescription();
    String plainText = convertHtmlToPlainText(htmlContent);
    btndescription.setText(plainText);
      String cheminImage = specialite.getImage();
    System.out.println("lien: " + cheminImage);
          
        // Load the image from the specified path
        Image image = new Image(Dawinii.class.getClass().getResource("/images/"+cheminImage).toString());
        images.setImage(image);
       //imgarticle.setImages(article.getImages());
        specialitesService sujetService = new specialitesService();
        List<Sujet> sujet=null;
        System.out.println("sujet "+sujet);
       sujet =sujetService.listSujet(specialite);
        System.out.println("sujet "+sujet);

         for (Sujet Suje : sujet) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/itemsujet.fxml"));
                Node node = loader.load();
                ItemsujetController ItemsujetController = loader.getController();
                ItemsujetController.setDataa(Suje);
                pnitems.getChildren().add(node);
            } catch (IOException e) {
                e.printStackTrace();
            }
         }
    }
private String convertHtmlToPlainText(String html) {
    Document doc = Jsoup.parse(html);
    PlainTextExtractor textExtractor = new PlainTextExtractor();
    textExtractor.traverse(doc.body());
    return textExtractor.getText();
}

    @FXML
    private void retour(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/SpecialitesFront.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
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
    public void head(org.jsoup.nodes.Node node, int depth) {
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
    public void tail(org.jsoup.nodes.Node node, int depth) {
        // Do nothing
    }

    public void traverse(Element element) {
        for (org.jsoup.nodes.Node child : element.childNodes()) {
            child.traverse(this);
        }
    }
}
    }
