/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.github.plushaze.traynotification.notification.TrayNotification;
import com.github.plushaze.traynotification.notification.NotificationType;
//import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import entities.Rendezvous;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
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
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import services.RendezvousService;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
//import tray.notification.NotificationType;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

//import tray.animations.AnimationType;
import util.MyDB;

/////////////////////////
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
//import javafx.scene.input.KeyEvent;
//import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import static jdk.nashorn.internal.objects.NativeJava.type;
//import tray.animations.AnimationType;
//import tray.notification.NotificationType;
//import tray.notification.TrayNotification;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
//////////////////////

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
    @FXML
    private Button btnpayer;
    
    private MediaPlayer mediaplayer;
    private Media media;
    
    
    @FXML
    private TextField SearchM;
    
RendezvousService ts = new RendezvousService();
    ObservableList<Rendezvous> rendezvousList = ts.getAllRendezvous();
    @FXML
    private Label lb_totalTransac;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Rendezvous p = null;
        try {
            //searchRendezvous();
            // TODO
            showRendezvous();
        } catch (SQLException ex) {
            Logger.getLogger(AfficherRFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
         /*setLb_totalTransac(ts.getAllRendezvous().stream()
                                  .mapToDouble((Rendezvous t) -> ts.getOneByIdT(t.getId()))
                                  .sum());*/
         /*setLb_totalTransac(ts.getAllRendezvous().stream()
                                  .mapToDouble((Rendezvous t) -> t.getId())
                                  .sum());*/
        //int count = countRendezvousById(42);
       // this.lb_totalTransac.setText("Nombre de rendezvous pour l'ID  : " + count);
       //List<Rendezvous> rendezvousList = rendezvousService.getAllRendezvous();
      
       //***************************************************afficher nombre*/////////////
       /*RendezvousService ts = new RendezvousService();
       ObservableList<Rendezvous> rendezvousList = ts.getAllRendezvous();
        //int rendezvousCount = rendezvousList.size();
        //System.out.println("Taille de la liste de rendezvous : " + rendezvousList.size());
      //  long rendezvousCount = rendezvousList.stream().count();
        
        int rendezvousCount = 0;
for (Rendezvous r : rendezvousList) {
    rendezvousCount++;
}
        
                this.lb_totalTransac.setText("Nombre de rendezvous pour  : " + rendezvousCount);*/
      RendezvousService ts = new RendezvousService();
       ObservableList<Rendezvous> rendezvousList = ts.getAllRendezvous();
        String titre = "some_id_value"; // Replace with the actual id value you want to search for
      int count = calculnb(titre);  
        System.out.println("Taille de la liste de rendezvous : " + count);
        this.lb_totalTransac.setText("Nombres de rendezvous   : " + count);
      //  long rendezvousCount = rendezvousList.stream().count();
       
        /*******************************sout
         * 
         */
        
    } 
    
    /* public void setLb_totalTransac(int id) {
        this.lb_totalTransac.setText("Nombre de rendezvous pour l'ID 42 : " + count);
    }*/
     
      /*public int countRendezvousById(int id) {
         ObservableList<Rendezvous> rendezvousList = ts.getAllRendezvous();
        int count = 0;
        for (Rendezvous rendezvous : rendezvousList) {
            if (rendezvous.getId() == id) {
                count++;
            }
        }
        return count;
        
    }*/
    
    public int calculnb(String titre) {

        PreparedStatement pre;
        int count = 19;
        try {
            Connection conn = getConnection();

            String query = "SELECT COUNT(*) FROM rendez_vous WHERE id='"+titre+"'";
        Statement st;
        ResultSet rs;
            st = conn.createStatement();
            rs = st.executeQuery(query);

            rs.next();
            count = rs.getInt(1);
            return count;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return 0;

    }
     
    
    @FXML
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
    
    public void showRendezvous() throws SQLException{
        
        
//
           /* RendezvousService ts = new RendezvousService();
    ObservableList<Rendezvous> rendezvousList = ts.getAllRendezvous();
            
            System.out.println(rendezvousList);*/
            //
        ObservableList<Rendezvous> List = getRendezvousList();
        colid.setCellValueFactory(new PropertyValueFactory<Rendezvous, Integer>("id"));
        coltitre.setCellValueFactory(new PropertyValueFactory<Rendezvous, String>("titre"));
        coldescription.setCellValueFactory(new PropertyValueFactory<Rendezvous, String>("description"));
        coldate.setCellValueFactory(new PropertyValueFactory<Rendezvous, Date>("date"));
        coletat.setCellValueFactory(new PropertyValueFactory<Rendezvous, String>("etat"));
        //searchRendezvous();
        tvrendezvous.setItems(List);
        //searchRendezvous();
       /* setLb_totalTransac(ts.getAllRendezvous().stream()
                                  .mapToDouble((Rendezvous t) -> t.getId())
                                  .sum());*/
        
        /*rendezvousList = FXCollections.observableArrayList(rendezvousList);
        tvrendezvous.setItems(rendezvousList);
        searchRendezvous();
        */
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
    private void supprimerrendezvous(ActionEvent event){
        String query = "DELETE FROM rendez_vous WHERE titre =" + titretf.getText() + "";
        executeQuery(query);
        //showRendezvous();
        
    }
    
   
    private void modifierRendezvous() throws SQLException{
         Connection conn = getConnection();
   // String query = "INSERT INTO rendez_vous(titre, description, etat) VALUES ('" + titretf.getText() + "', '" +  descriptionta.getText() + "', '"  + etattf.getText() + "')";
   //String query = "INSERT INTO rendez_vous VALUES (" + titretf.getText() + "," +  descriptionta.getText() + "," + datePD.getDate().getTime()+ "," + etattf.getText() + ")";
    String query = "UPDATE rendez_vous SET titre = '"+ titretf.getText() + "', description = '" + descriptionta.getText() + "', etat ='" + etattf.getText() + "WHERE titre =" +titretf.getText() + "";
        executeQuery(query);
        
   //searchRendezvous();
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
    private void AjouterR(ActionEvent event) throws SQLException {
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
            notiff();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        showRendezvous();
    }
    }

    
    private void notiff()
    {
        //Service sv = new ServiceVoyage();
        //ReserverVoyage RV = new ReserverVoyage();
        RendezvousService ts = new RendezvousService();
         String Destinationv = titretf.getText();
        //int y=sv.calculnb((Destination.getText()));
        TrayNotification tray = new TrayNotification();
        //AnimationType type = AnimationType.POPUP;
        //tray.setAnimationType(type);
        tray.setTitle("Bienvenu a dawini");
        tray.setMessage("Le rendezvous  Du titre"+ titretf+ " a ete effectuer avec Success");
        //tray.setNotificationType(NotificationType.INFORMATION);
        tray.showAndDismiss(Duration.millis(2000));
    }
    
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
    
    
    
     
    private void searchRendezvous(KeyEvent event) throws SQLException {
    FilteredList<Rendezvous> filteredData = new FilteredList<>(FXCollections.observableArrayList(ts.getAllRendezvous()), p -> true);
    SearchM.textProperty().addListener((observable, oldValue, newValue) -> {
        filteredData.setPredicate(rendezvous -> {
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }
            String lowerCaseFilter = newValue.toLowerCase();
            if (rendezvous.getTitre().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            } else if (rendezvous.getDescription().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            } else if (rendezvous.getEtat().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            
            } else {
                return false;
            }
        });
    });
    SortedList<Rendezvous> sortedData = new SortedList<>(filteredData);
    sortedData.comparatorProperty().bind(tvrendezvous.comparatorProperty());
    tvrendezvous.setItems(sortedData);
}
    private void Gopaiement(ActionEvent event) {
        
        
        Parent root;
        try {
           root = FXMLLoader.load(getClass().getResource("../gui/AfficherpaiementFXML.fxml"));
         //  root = FXMLLoader.load(getClass().getResource("../gui/afficherpaiementFXML.fxml"));

        //root = FXMLLoader.load(getClass().getResource("../gui/AjouterrendezvousFXML.fxml"));
            // root = FXMLLoader.load(getClass().getResource("../gui/AfficherrendezvousFXML.fxml"));
            //root = FXMLLoader.load(getClass().getResource("../gui/TransactionFXML.fxml"));

            Scene scene = new Scene(root);
           // primaryStage.setTitle("DAWINI DESKTOP");
            //primaryStage.setScene(scene);
           // primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex);
        }
        
        /*FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherpaiementFXML.fxml"));
        try {
            Parent root = loader.load();
           // listViewRecepteur.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }*/

    }
    @FXML
    private void Govideoexplicatif(ActionEvent event) throws IOException {
        
        
        Parent root = FXMLLoader.load(getClass().getResource("../gui/videoFXML.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void retour(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../gui/AfficherpaiementFXML.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
     @FXML
    private void GetOneByTitre(ActionEvent event) {
        String nom = recherchertf.getText();
        if (nom.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Veuillez remplir Le Champ :) ");
            alert.show(); //champ vide alerte
        } else if (!nom.matches("^[a-zA-Z]*$")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Veuillez entré le titre du rendezvous :) ");
            alert.show(); //entré n'est pas string
        } else {
            Optional<Rendezvous> matchingRendezvous = rendezvousList.stream() //maching rendezvous t3abi valeur li 3malt 3lih recherche
                    .filter(rendezvous -> rendezvous.getTitre().toLowerCase().equals(nom.toLowerCase()))
                    .findFirst();
            //to lower case bech yefhem maj w miniscule 

            if (matchingRendezvous.isPresent()) {
                rendezvousList.clear();
                Rendezvous rendezvous = matchingRendezvous.get();
                rendezvousList.add(rendezvous);
               //  colid.setItems(rendezvousList);
                //coltitre.setItems(rendezvousList);
                //coldescription.setItems(rendezvousList);
               // coldate.setItems(rendezvousList);
               // coletat.setItems(rendezvousList);
                
                rendezvousList = ts.getAllRendezvous();
                

            }
        
            //
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Y'a pas de rendez vous avec ce titre :( ");
                alert.show();
            }//transaction introuvable nest pas une transaction avec ce nom
            //
        }
        
    }
    private void sendEmail(ActionEvent event) {
        Properties props = new Properties();
props.put("mail.smtp.auth", "true");
props.put("mail.smtp.starttls.enable", "true");
props.put("mail.smtp.host", "smtp.gmail.com");
props.put("mail.smtp.port", "587");

Session session = Session.getInstance(props,
  new javax.mail.Authenticator() {
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication("saidi.tayssir@esprit.tn", "TYSUTYSUTYSUTYSU456");
    }
  });
    }

    @FXML
    private void searchRendezvous(InputMethodEvent event) {
    }
    }
  
    
/*
    @FXML
    private void GetOneByTitre(ActionEvent event) {
        String nom = recherchertf.getText();
        if (nom.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Veuillez remplir Le Champ :) ");
            alert.show(); //champ vide alerte
        } else if (!nom.matches("^[a-zA-Z]*$")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Veuillez entré le titre du rendezvous :) ");
            alert.show(); //entré n'est pas string
        } else {
            Optional<Rendezvous> matchingRendezvous = rendezvousList.stream() //maching rendezvous t3abi valeur li 3malt 3lih recherche
                    .filter(rendezvous -> rendezvous.getTitre().toLowerCase().equals(nom.toLowerCase()))
                    .findFirst();
            //to lower case bech yefhem maj w miniscule 

            if (matchingRendezvous.isPresent()) {
                rendezvousList.clear();
                Rendezvous rendezvous = matchingRendezvous.get();
                rendezvousList.add(rendezvous);
                // colid.setItems(rendezvousList);
                //coltitre.setItems(rendezvousList);
                //coldescription.setItems(rendezvousList);
                //coldate.setItems(rendezvousList);
                //coletat.setItems(rendezvousList);
                
                rendezvousList = ts.getAllRendezvous();
                

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Y'a pas de rendez vous avec ce titre :( ");
                alert.show();
            }//transaction introuvable nest pas une transaction avec ce nom 
        }
    }
*/