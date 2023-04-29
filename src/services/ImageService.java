/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Images;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import util.MyDB;

/**
 *
 * @author Islem
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
