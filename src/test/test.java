/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entities.Rendezvous;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import services.RendezvousService;

/**
 *
 * @author HP
 */
public class test  {
    
   public static void main(String[] args) {
        
        Rendezvous t = new Rendezvous("titre","description","etat");
        RendezvousService ps = new RendezvousService();
        //try {
            //ps.modifier(p);
           // System.out.println(ps.recupererById(3));
       // } catch (SQLException ex) {
           // System.out.println(ex.getMessage());
           System.out.println("ajout valide");
       // }
        
    }
    
}
