/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Consulation;
import entities.ordonnance;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.MyDB;

/**
 *
 * @author USER
 */
public class ordonnanceService implements IService<ordonnance> {

    Connection cnx;

    public ordonnanceService() {
        cnx = MyDB.getInstance().getCnx();
    }

    @Override
public void ajouter(ordonnance t) throws SQLException {
    String req = "INSERT INTO ordonnance (description,date,consulation_id,image) VALUES ('" + t.getDescription() + "', '" + t.getDate() + "', " + t.getConsulation_id() + ", '" + t.getImage() +"')";
    Statement st = cnx.createStatement();
    st.executeUpdate(req);
}


    @Override
    public void modifier(ordonnance t) throws SQLException {
        String req = "update ordonnance set description = ?, date = ?,image=? where id=?";
        PreparedStatement cs = cnx.prepareStatement(req);
        
        cs.setString(1, t.getDescription());
        cs.setDate(2, t.getDate());
        cs.setString(3, t.getImage());
        cs.setInt(4, t.getId());
        cs.executeUpdate();
    }

    @Override
    public void supprimer(ordonnance t) throws SQLException {
        String req = "delete from ordonnance where id='"+t.getId()+"';";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
    }

    @Override
    
    public List<ordonnance> recuperer() throws SQLException {
        List<ordonnance> ordonnances = new ArrayList<>();
        String req = "select * from ordonnance";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            
            ordonnance o = new ordonnance();
            o.setId(rs.getInt("id"));
            o.setDescription(rs.getString("description"));
            o.setDate(rs.getDate("date"));
            o.setImage(rs.getString("image"));
            ordonnances.add(o);
        }
        return ordonnances;
    }

    
    public ordonnance recupererbycons(int id) throws SQLException {
        
        String req = "select * from ordonnance where consulation_id='"+id+"';";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        if(rs.next())
        {
            
            ordonnance o = new ordonnance();
            o.setId(rs.getInt("id"));
            o.setDescription(rs.getString("description"));
            o.setDate(rs.getDate("date"));
            o.setImage(rs.getString("image"));
          return o; 
        }
        return null;
    }
    public ordonnance recupererbyID(int id) throws SQLException {
        
        String req = "select * from ordonnance where id='"+id+"';";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        if(rs.next())
        {
            
            ordonnance o = new ordonnance();
            o.setId(rs.getInt("id"));
            o.setDescription(rs.getString("description"));
            o.setDate(rs.getDate("date"));
            o.setImage(rs.getString("image"));
          return o; 
        }
        return null;
    }
    
    
}

