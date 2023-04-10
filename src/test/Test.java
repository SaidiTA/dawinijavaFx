/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entities.Consulation;
import entities.Personne;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import services.ConsulationService;
import services.PersonneService;
import util.MyDB;

/**
 *
 * @author Skand
 */
/*public class Test {

    public static void main(String[] args) {
        List<Consulation> consultations=new ArrayList<>();
        Timestamp date=new Timestamp(System.currentTimeMillis()); ;
        Timestamp datedebut=new Timestamp(System.currentTimeMillis()); ;
        Timestamp datefin=new Timestamp(System.currentTimeMillis()); ;
        
        Consulation c = new Consulation(date,datedebut ,datefin,"lien de test","0");
        
        ConsulationService cs = new ConsulationService();
        
        try {
            //c.setId(62);
            //c.setEst_termine("1");
            //cs.modifier(c);
            consultations.addAll(cs.recuperer());
            for(Consulation i: consultations){
                System.out.println(i);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

}*/
public class Test extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/gui/DashMedecin.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
    
     /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args)  {
        
          launch(args);
        
    }

   
    
}
