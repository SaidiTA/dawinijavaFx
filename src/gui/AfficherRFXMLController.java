/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import entities.Rendezvous;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import services.RendezvousService;
import util.MyDB;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class AfficherRFXMLController implements Initializable {

    @FXML
    private TextField titretf;
    @FXML
    private TextArea descriptionta;
    @FXML
    private TextField etattf;
    @FXML
    private DatePicker datePD;
    @FXML
    private TableView<Rendezvous> tvrendezvous;
    @FXML
    private TableColumn<Rendezvous, Integer> colid;
    @FXML
    private TableColumn<Rendezvous, String> coltitre;
    @FXML
    private TableColumn<Rendezvous, String> coldescription;
    @FXML
    private TableColumn<Rendezvous, Date> coldate;
    @FXML
    private TableColumn<Rendezvous, String> coletat;
    @FXML
    private Button btnajouter;
    @FXML
    private Button btnmodifier;
    @FXML
    private Button btnsupprimer;
    @FXML
    private TextField recherchertf;
    @FXML
    private Button btnrechercher;
    
    RendezvousService ts = new RendezvousService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showRendezvous();
    } 
    
    public void handleButtonAction(ActionEvent event) throws SQLException{
    
        //if(event.getSource() == btnajouter){
          //  ajouterRendezvous();
        //}
        //else if(event.getSource() == btnmodifier){
        //    modifierRendezvous();
        //}
        //else if(event.getSource() == btnsupprimer){
        //    supprimerRendezvous();
        //}
    //to do
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
    
    public ObservableList<Rendezvous> getRendezvousList(){ 
         
        ObservableList<Rendezvous> RendezvousList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM rendez_vous";
        Statement st;
        ResultSet rs;
        
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Rendezvous rendez_vous;
            while(rs.next()){
                rendez_vous = new Rendezvous(rs.getInt("id"),rs.getString("titre"),rs.getString("description"),rs.getDate("date"),rs.getString("etat"));
                RendezvousList.add(rendez_vous);
            }
        
        }catch(Exception ex){
            ex.printStackTrace();
        
        }
        return RendezvousList;
      
    
    }
    
    public void showRendezvous(){
        ObservableList<Rendezvous> List = getRendezvousList();
        colid.setCellValueFactory(new PropertyValueFactory<Rendezvous, Integer>("id"));
        coltitre.setCellValueFactory(new PropertyValueFactory<Rendezvous, String>("titre"));
        coldescription.setCellValueFactory(new PropertyValueFactory<Rendezvous, String>("description"));
        coldate.setCellValueFactory(new PropertyValueFactory<Rendezvous, Date>("date"));
        coletat.setCellValueFactory(new PropertyValueFactory<Rendezvous, String>("etat"));
        
        tvrendezvous.setItems(List);
    }
    
    
    private void ajouterRendezvous() throws SQLException{
         Connection conn = getConnection();
   // String query = "INSERT INTO rendez_vous(titre, description, etat) VALUES ('" + titretf.getText() + "', '" +  descriptionta.getText() + "', '"  + etattf.getText() + "')";
   //String query = "INSERT INTO rendez_vous VALUES (" + titretf.getText() + "," +  descriptionta.getText() + "," + datePD.getDate().getTime()+ "," + etattf.getText() + ")";
    String query = "INSERT INTO rendez_vous VALUES (" + titretf.getText() + "," +  descriptionta.getText() + "," + etattf.getText() + ")";
//('" + t.getDate() + "', '" + t.getHeuredebut() + "', '" + t.getHeurefin() + "', '" + t.getUrl_consultation() +"', '" + t.getEst_termine()+"')";
    executeQuery(query);
     //String query = "INSERT INTO rendez_vous ( titre, description,etat) VALUES (titretf.getText(),descriptionta.getText(), etattf.getText())";
   
    showRendezvous();
   
    }
    @FXML
    private void supprimerrendezvous(ActionEvent event){
        String query = "DELETE FROM rendez_vous WHERE titre =" + titretf.getText() + "";
        executeQuery(query);
        showRendezvous();
        
    }
    
   
    private void modifierRendezvous(){
         Connection conn = getConnection();
   // String query = "INSERT INTO rendez_vous(titre, description, etat) VALUES ('" + titretf.getText() + "', '" +  descriptionta.getText() + "', '"  + etattf.getText() + "')";
   //String query = "INSERT INTO rendez_vous VALUES (" + titretf.getText() + "," +  descriptionta.getText() + "," + datePD.getDate().getTime()+ "," + etattf.getText() + ")";
    String query = "UPDATE rendez_vous SET titre = '"+ titretf.getText() + "', description = '" + descriptionta.getText() + "', etat ='" + etattf.getText() + "WHERE titre =" +titretf.getText() + "";
        executeQuery(query);
        
   
         showRendezvous();
   
    }

    private void executeQuery(String query) {
        Connection conn = getConnection();
        Statement st;
        try{
            st = conn.createStatement();
            st.executeUpdate(query);
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    @FXML
    private void AjouterR(ActionEvent event) {
        String titre = titretf.getText();
        String description = descriptionta.getText();
        Calendar calendar = Calendar.getInstance();
        java.util.Date currentDate = calendar.getTime();
        Date date = new Date(currentDate.getTime());
        //Date date = datePD.getDate();
        String etat = etattf.getText();
       
        Rendezvous t = new Rendezvous(titre, description,date, etat);
        RendezvousService rs = new RendezvousService();
        if (titretf.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "il faut remplir le champ de titre  !");
                } else if (descriptionta.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "il faut remplir le champ de description  !");
                } else if (etattf.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "il faut remplir le champ d'etat  !");
                } 
        
        
        else {
        try {
            rs.ajouter(t);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        showRendezvous();
    }
    }

     @FXML
     private void handleMouseAction(MouseEvent event){
        Rendezvous rendezvous = tvrendezvous.getSelectionModel().getSelectedItem();
        System.out.println("titre" + rendezvous.getTitre() );
     }
     
     @FXML
    private void DeleteOne(ActionEvent event) throws SQLException {
        Rendezvous t = tvrendezvous.getSelectionModel().getSelectedItem();
        if (t != null) {
            if (ts.supprimerRR(t.getId())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initStyle(StageStyle.UTILITY);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("La transaction a été supprimée :) ");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) // alert is exited, no button has been pressed.
                {
                    showRendezvous();
                }

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initStyle(StageStyle.UTILITY);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Le rendezvous n'a pas été supprimée");
                alert.showAndWait(); //si la transaction n'est pas supprimer
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(" Veuillez selectionnez une transaction :) ");
            alert.show(); //en cas il ne selectionne pas aucun transaction
        }
    }
    
}
