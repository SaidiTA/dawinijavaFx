/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author soumayaab
 */
public class MyDB {
    
   private String url ="jdbc:mysql://localhost:3306/dawini2";
   //mysql://root:@127.0.0.1:3306/dawinijava?
   private String username="root";
   private String password="";
   private Connection cnx;
   private static MyDB instance;

    public MyDB() {
       try {
           cnx = DriverManager.getConnection(url, username, password);
           System.out.println("Connexion etablie");
       } catch (SQLException ex) {
           System.err.println(ex.getMessage());
       }
    }
    
    public static MyDB getInstance(){
    if(instance==null)
        instance = new MyDB();
       return instance;
        }

    public Connection getCnx() {
        return cnx;
    }

  
   
}
