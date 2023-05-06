/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author HP
 */
public interface ppService<P> {
    void ajouter(P t) throws SQLException ;

    //void modifier(P t) throws SQLException ;

    //void supprimer(P t) throws SQLException ;

    //List<P> recuperer() throws SQLException ;
    public List<P> getAllRendezvous();
    public P getOneByIdP(int id);
    
}
