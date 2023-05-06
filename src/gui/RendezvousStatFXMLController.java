/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class RendezvousStatFXMLController implements Initializable {

    @FXML
    private PieChart voy_stat;
     ObservableList<PieChart.Data>data=FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         stat();
    }   
    public Connection getConnection() {
        Connection conn;
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dawini2","root","");
            return conn;
            //System.out.println("Connexion établie");
        } catch (Exception ex) {
            System.out.println("Error:" + ex.getMessage());
            return null;
        }
    }
     private void stat() {
          try{
           // String query ="select COUNT(*),reservation_voyage.travel_class from voyage INNER JOIN reservation_voyage on reservation_voyage.voyage_id =voyage.id GROUP BY travel_class;";
           //String query ="select COUNT(*),`prix`  from voyage GROUP BY `destination`;";
           Connection conn = getConnection();
           String query ="select COUNT(*),`titre` from rendez_vous GROUP BY `titre`;";
Statement st;
        ResultSet rs;
           //PreparedStatement PreparedStatement = cnx.prepareStatement(query);
            // rs = PreparedStatement.executeQuery();
             st = conn.createStatement();
            rs = st.executeQuery(query);
             while (rs.next()){               
               data.add(new PieChart.Data(rs.getString("titre"),rs.getInt("COUNT(*)")));
            }  
             
        } catch (SQLException ex) {
              System.out.println(ex.getMessage());
        }        
         voy_stat.setTitle("**Statistiques Des titres courant **");
        voy_stat.setLegendSide(Side.LEFT);
        voy_stat.setData(data);
    }
    
}
