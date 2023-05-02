/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import entities.Images;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import util.MyDB;

/**
 *
 * @author soumayaab
 */
public class ImageService {
     Connection cnx;

    public ImageService() {
        cnx = MyDB.getInstance().getCnx();
    }
   

    public void ajouter(Images img) throws SQLException {
        String req = "INSERT INTO images(article_id,name,url) VALUES (?, ?, ?)";
        PreparedStatement statement = cnx.prepareStatement(req);
        statement.setInt(1, img.getArticleId().getId());
        statement.setString(2, img.getName());
        statement.setString(3, img.getUrl());
        int rowsInserted = statement.executeUpdate();
        System.out.println("rowsInserted "+rowsInserted);
        //ResultSet rs = pst.getGeneratedKeys();
    }   
}
