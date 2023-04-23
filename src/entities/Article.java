package entities;

/**
 *
 * @author Islem
 */
import java.time.LocalDateTime;
import java.util.Date;

public class Article {
private int id;
    private String nom;
    private String description;
    private Date date;
    private Medecin medecin;

    public Medecin getMedecin() {
        return medecin;
    }

    public void setMedecin(Medecin medecin) {
        this.medecin = medecin;
    }
    
    
    public Article(String nom, String description,Date date,Medecin medecin ) {
        this.nom = nom;
        this.description = description;
        this.date=date;
           this.medecin=medecin;
    }

    public Article() {
    }

  
    @Override
    public String toString() {
        return "Article{" + "id=" + id + ", nom=" + nom + ", description=" + description + ", date=" + date + '}';
    }
    

    // Constructor with all parameters
    public Article(int id, String nom, String description, Date date,Medecin medecin) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.date = date;
        this.medecin=medecin;
    }

   

   
    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
   
}
