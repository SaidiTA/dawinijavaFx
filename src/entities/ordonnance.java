/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author USER
 */
public class ordonnance {
    
    private int id;
    private String description;
    private Date date;
    private int consulation_id;
    
    private String image;

    public ordonnance() {
    }

    public ordonnance(String description) {
        this.description = description;
    }
    
    
    public ordonnance(int id, String description, Date date, int consulation_id, String image) {
        this.id = id;
        this.description = description;
        this.date = date;
        this.consulation_id = consulation_id;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }

    public int getConsulation_id() {
        return consulation_id;
    }

    public String getImage() {
        return image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setConsulation_id(int consulation_id) {
        this.consulation_id = consulation_id;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "ordonnance{" + "id=" + id + ", description=" + description + ", date=" + date + ", consulation_id=" + consulation_id + ", image=" + image + '}';
    }
    
    
    
}
