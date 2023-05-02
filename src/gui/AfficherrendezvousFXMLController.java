/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Rendezvous;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import services.RendezvousService;
//ObservableList<Transaction> RendezvousList = ts.getAllRendezvous();

/**
 * FXML Controller class
 *
 * @author HP
 */
public class AfficherrendezvousFXMLController implements Initializable {
    
    @FXML
    private ListView listViewId;
    @FXML
    private ListView listViewTitre;
    @FXML
    private ListView listViewDescription;
    @FXML
    private ListView listViewDate;
    @FXML
    private ListView listViewEtat;
    @FXML
    private TextField rechercher;
    
    RendezvousService ts = new RendezvousService();
    ObservableList<Rendezvous> rendezvousList = ts.getAllRendezvous();
    
    
    
     @FXML
    private Button gotoajout;
     @FXML
    private Button supprimerR;
     @FXML
    private Button afficherR;
     @FXML
    private Button modifierR;
     @FXML
    private Button rechercherR;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    
    
    private void initList() {
        /* listViewId.setCellFactory(param -> new ListCell<Rendezvous>() {
        protected void updateItem(Rendezvous trans, boolean empty) {
            super.updateItem(trans, empty);

            if (empty || trans == null) {
                setText(null);
            } else {
                setText(trans.getId());
            }
        }
    });*/

    listViewTitre.setCellFactory(param -> new ListCell<Rendezvous>() {
        protected void updateItem(Rendezvous trans, boolean empty) {
            super.updateItem(trans, empty);

            if (empty || trans == null) {
                setText(null);
            } else {
                setText(trans.getTitre());
            }
        }
    });

    listViewDescription.setCellFactory(param -> new ListCell<Rendezvous>() {
        protected void updateItem(Rendezvous trans, boolean empty) {
            super.updateItem(trans, empty);

            if (empty || trans == null) {
                setText(null);
            } else {
                setText(trans.getDescription());
            }
        }
    });

    /*listViewDate.setCellFactory(param -> new ListCell<Rendezvous>() {
        protected void updateItem(Rendezvous trans, boolean empty) {
            super.updateItem(trans, empty);

            if (empty || trans == null) {
                setText(null);
            } else {
                setText(trans.getDate);
            }
        }
    });*/

    listViewEtat.setCellFactory(param -> new ListCell<Rendezvous>() {
        protected void updateItem(Rendezvous trans, boolean empty) {
            super.updateItem(trans, empty);

            if (empty || trans == null) {
                setText(null);
            } else {
                setText(trans.getEtat());
            }
        }
    });
    }
     @FXML
    private void getAll(ActionEvent event) {
        setListItemsFromDatabase();
        
    }

   
    
     private void setListItemsFromDatabase() {
        rendezvousList = ts.getAllRendezvous();
        listViewId.setItems(rendezvousList);
        listViewTitre.setItems(rendezvousList);
        listViewDescription.setItems(rendezvousList);
        listViewDate.setItems(rendezvousList);
         listViewEtat.setItems(rendezvousList);
        
    }

    
    
}
