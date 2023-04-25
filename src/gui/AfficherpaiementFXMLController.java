/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.paiement;
import entities.Rendezvous;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.StageStyle;
import javax.management.Query;
import javax.swing.JOptionPane;
import services.PaiementService;
import services.RendezvousService;
//import services.RendezvousService;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class AfficherpaiementFXMLController implements Initializable {

    /*@FXML
    private ChoiceBox<Integer> rendezvousCB;*/
    @FXML
    private TextField etatptf;
    @FXML
    private TextField tarifptf;
    @FXML
    private TextField numptf;
    @FXML
    private DatePicker expirationp;
    @FXML
    private TextField nomptf;
    @FXML
    private TextField emailptf;
    @FXML
    private TableView<paiement> tvpaiement;
    @FXML
    private TableColumn<paiement, Integer> colidr;
    @FXML
    private TableColumn<paiement, String> coletatp;
    @FXML
    private TableColumn<paiement, Integer> coltarifp;
    @FXML
    private TableColumn<paiement, Integer> colnump;
    @FXML
    private TableColumn<paiement, Date> colexpirationp;
    @FXML
    private TableColumn<paiement, String> colnomp;
    @FXML
    private TableColumn<paiement, String> colemailp;
    @FXML
    private TableColumn<paiement, Integer> colidpaiement;
    @FXML
    private Button btnajouterpaiement;
    @FXML
    private Button btnsupprimerpaiement;
    @FXML
    private TextField rechercherpaiement;
    @FXML
    private Button btnrechercherpaiement;
    
     PaiementService ts = new PaiementService();
    @FXML
    private Button generatePDF;
    @FXML
    private ComboBox<Integer> combo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     //  List<Rendezvous> ls = ts.getAllRendezvous();
      /* RendezvousService ts = new RendezvousService();
       ObservableList<Rendezvous> rendezvousList = ts.getAllRendezvous();
       ObservableList<Integer> fournisseurs = FXCollections.observableArrayList();
       List<Integer> li = rendezvousList.stream().map(f -> f.getId()).collect(Collectors.toList());
       fournisseurs = FXCollections.observableArrayList(li);
       combo.setItems(fournisseurs);
       combo.setValue(li.get(0));
       // TODO*/
        

// TODO
        showpaiement();
    }  
    
    public Connection getConnection() {
        Connection conn;
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dawini","root","");
            return conn;
            //System.out.println("Connexion établie");
        } catch (Exception ex) {
            System.out.println("Error:" + ex.getMessage());
            return null;
        }
    }
    
    public ObservableList<paiement> getpaiementList(){ 
         
        ObservableList<paiement> paiementList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM paiement";
        Statement st;
        ResultSet rs;
        
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            paiement paiement;
            while(rs.next()){
                paiement = new paiement(rs.getInt("id"),rs.getInt("rendez_vous_id"),rs.getString("etat"),rs.getInt("tarif"),rs.getInt("num_carte"),rs.getDate("expiration_carte"),rs.getString("nom_carte"),rs.getString("email"));
                paiementList.add(paiement);
            }
        
        }catch(Exception ex){
            ex.printStackTrace();
        
        }
        return paiementList;
      
    
    }
    
    public void showpaiement(){
        ObservableList<paiement> List = getpaiementList();
        colidpaiement.setCellValueFactory(new PropertyValueFactory<paiement, Integer>("id"));
        colidr.setCellValueFactory(new PropertyValueFactory<paiement, Integer>("rendez_vous_id"));
        coletatp.setCellValueFactory(new PropertyValueFactory<paiement, String>("etat"));
        coltarifp.setCellValueFactory(new PropertyValueFactory<paiement, Integer>("tarif"));
        colnump.setCellValueFactory(new PropertyValueFactory<paiement, Integer>("num_carte"));
         colexpirationp.setCellValueFactory(new PropertyValueFactory<paiement, Date>("expiration_carte"));
        colnomp.setCellValueFactory(new PropertyValueFactory<paiement, String>("nom_carte"));
       colemailp.setCellValueFactory(new PropertyValueFactory<paiement, String>("email"));
        
        tvpaiement.setItems(List);
    }
    
    @FXML
    private void afficherrecu(ActionEvent event) {
        paiement t = tvpaiement.getSelectionModel().getSelectedItem();
       
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("Numero de tel");
        alert.setHeaderText(null);
        alert.setContentText("Le numero de " + t.getId() +  "\n est de tarif " + t.getTarif() +  "\n la  date d'expiration carte est " + t.getExpiration_carte()+  "\n l'email de client est " + t.getEmail());
        alert.showAndWait();
    }
    
    @FXML
    private void DeleteOneP(ActionEvent event) throws SQLException {
        paiement t = tvpaiement.getSelectionModel().getSelectedItem();
        if (t != null) {
            if (ts.supprimerPP(t.getId())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initStyle(StageStyle.UTILITY);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("paiement a été supprimée :) ");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) // alert is exited, no button has been pressed.
                {
                    showpaiement();
                }

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initStyle(StageStyle.UTILITY);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("paiement n'a pas été supprimée");
                alert.showAndWait(); //si la transaction n'est pas supprimer
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(" Veuillez selectionnez paiement :) ");
            alert.show(); //en cas il ne selectionne pas aucun transaction
        }
    }
    
    
    
    
    
   @FXML
    //private void generatePDF(ActionEvent event) throws SQLException {
        private void generatePDF(ActionEvent event) throws SQLException {
     paiement p = tvpaiement.getSelectionModel().getSelectedItem();
        if ( p!= null) {
          
         // paiement p =   ts.getOneByIdP(Integer.parseInt(tvpaiement.getSelectionModel().getSelectedItem()));

            ts.exporterPDF(p);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
           alert.setHeaderText(" Veuillez selectionnez un paiement facture :) ");
           alert.show(); //en cas il ne selectionne pas aucun transaction
        }
        
        
        
        
   }
    
    
    
    
    
    
    @FXML
    private void AjouterP(ActionEvent event) {
       // ChoiceBox<Integer> choixBox = new ChoiceBox<>();
        //choixBox.getItems().addAll(1, 2, 3, 4, 5); 
       //
       Integer rendez_vous_id = combo.getValue();
        String etat = etatptf.getText();
        Integer tarif = Integer.parseInt(tarifptf.getText()); // Convertit une chaîne de caractères en Integer
        Integer num_carte = Integer.parseInt(numptf.getText()); // Convertit une chaîne de caractères en Integer
        //Integer tarif = tarifptf.getText();
        //Integer num_carte = numptf.getText();
        Calendar calendar = Calendar.getInstance();
        java.util.Date currentDate = calendar.getTime();
        Date expiration_carte = new Date(currentDate.getTime());
        //Date date = datePD.getDate();
        String nom_carte = nomptf.getText();
        String email = emailptf.getText();
        
        
        //int rendez_vous_id = 0;
       
        paiement t = new paiement(rendez_vous_id,etat,tarif,num_carte,expiration_carte,nom_carte,email);
        PaiementService rs = new PaiementService();
        if (etatptf.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "il faut remplir le champ d etat  !");
                } else if (nomptf.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "il faut remplir le champ de nom de votre bank !");
                } else if (emailptf.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "il faut remplir le champ de votre email  !");
                } 
        
        
        else {
        try {
            rs.ajouter(t);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        showpaiement();
    }
    }
    
}
