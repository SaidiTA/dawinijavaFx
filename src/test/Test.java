/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package test;

import entities.Assistant;
import entities.Admin;
import entities.Medecin;
import entities.Patient;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import services.AdminService;
import services.AssistantService;
import services.MedecinService;
import services.PatientService;

/**
 *
 * @author soumayaab
 */
public class Test {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException {
        // Ajouter les rôles
        ArrayList<String> role=new ArrayList();
         //role.add("[\"ROLE_MEDECIN\"]");
         //role.add("[\"ROLE_PATIENT\"]");
         //role.add("[\"ROLE_ASSISTANT\"]");
         role.add("[\"ROLE_ADMIN\"]");
         
          // Création de l'assistant
    Admin admin = new Admin();
     
  admin.setId(211);
    admin.setEmail("admin3@test.com");
    admin.setPassword("password");
    admin.setRoles(role);
    admin.setNom("mmm");
    admin.setPrenom("tttt");
    admin.setCin(12345678);
    admin.setSexe("M");
    admin.setTelephone("12345678");
    admin.setGouvernorat("Tunis");
    admin.setAdresse("Test A");
    admin.setConfirm_password("password");
    admin.setImage("doctor-img1-64070568b93ad.png");
   

    
    /*
    
     
    // Ajout de l'assistant
    
    try {
        adminService.ajouter(admin);
        System.out.println("Admin ajouté avec succès !");
    } catch (SQLException ex) {
        System.out.println("Erreur lors de l'ajout de l'admin : " + ex.getMessage());
    }
         
     
         
         
         
         
         
         // modifier de l'admin
         try {
             AdminService adminService = new AdminService();
        adminService.modifier(admin);
        System.out.println("admin modifié avec succès !");
    } catch (SQLException e) {
        System.out.println("Erreur lors de la modification du admin : " + e.getMessage());
    }
        
         
         
           // Récupération de tous les admins
   try {
       AdminService adminService = new AdminService();
       List<Admin> admins = adminService.recuperer();
        System.out.println("Liste de tous les Admins : ");
        for (Admin a : admins) {
           System.out.println(a);
       }
    } catch (SQLException e) {
        System.out.println("Erreur lors de la récupération des admins : " + e.getMessage());
         
         
    }
         
        
         
         // Récupération d'un admin par ID

try {
    AdminService adminService = new AdminService();
    System.out.println("Admin récupéré par ID : " + adminService.recupererById(211));
} catch (SQLException e) {
   System.out.println("Erreur lors de la récupération du admin par ID : " + e.getMessage());
}
         
        
         
          // Suppression d'un admin existant
   try {
        AdminService adminService = new AdminService();
        admin.setId(211);
         adminService.supprimer(admin);
         System.out.println("Admin supprimé avec succès !");
     } catch (SQLException e) {
         System.out.println("Erreur lors de la suppression d' Admin : " + e.getMessage());
    }
         
           */ 
         
         
         ////////////////////////////partie Assistant///////////////////////
       /*          
         
         Medecin medecin = new Medecin();
    medecin.setId(67); // Remplacer 1 par l'id du médecin existant dans la base de données
    
    // Création de l'assistant
    Assistant assistant = new Assistant();
     
  assistant.setId(208);
    assistant.setEmail("anna@test.com");
    assistant.setPassword("password");
    assistant.setRoles(role);
    assistant.setNom("mmm");
    assistant.setPrenom("tttt");
    assistant.setCin(12345678);
    assistant.setSexe("M");
    assistant.setTelephone("12345678");
    assistant.setGouvernorat("Tunis");
    assistant.setAdresse("Test Adresse");
    assistant.setConfirm_password("password");
    assistant.setImage("doctor-img1-64070568b93ad.png");
    assistant.setMedecin(medecin);

    
    
    
     
    // Ajout de l'assistant
    AssistantService assistantService = new AssistantService();
    try {
        assistantService.ajouter(assistant);
        System.out.println("Assistant ajouté avec succès !");
    } catch (SQLException ex) {
        System.out.println("Erreur lors de l'ajout de l'assistant : " + ex.getMessage());
    }
       
   // modifier de l'assistant
         try {
        assistantService.modifier(assistant);
        System.out.println("assistant modifié avec succès !");
    } catch (SQLException e) {
        System.out.println("Erreur lors de la modification du assistant : " + e.getMessage());
    }
       
         
          // Récupération de tous les assistants
   try {
       List<Assistant> assistants = assistantService.recuperer();
        System.out.println("Liste de tous les assistants : ");
        for (Assistant a : assistants) {
           System.out.println(a);
       }
    } catch (SQLException e) {
        System.out.println("Erreur lors de la récupération des assistants : " + e.getMessage());
         
         
    }
         
         
   
    // Récupération d'un assistant par ID

try {
    System.out.println("Assistant récupéré par ID : " + assistantService.recupererById(208));
} catch (SQLException e) {
   System.out.println("Erreur lors de la récupération du assistant par ID : " + e.getMessage());
}
     
    
   
   
   // Suppression d'un assistant existant
   try {
        assistant.setId(203);
         assistantService.supprimer(assistant);
         System.out.println("Assistant supprimé avec succès !");
     } catch (SQLException e) {
         System.out.println("Erreur lors de la suppression d' assistant : " + e.getMessage());
    }
      
   */ 
   
   
   
   
         
       ////////////////////////////partie patient///////////////////////
       /*   PatientService patientService = new PatientService();
   Patient patient = new Patient();
  patient.setId(203);
    patient.setEmail("patient2@test.com");
    patient.setPassword("password");
    patient.setRoles(role);
    patient.setNom("ttttt");
    patient.setPrenom("tttt");
    patient.setCin(12345678);
    patient.setSexe("M");
    patient.setTelephone("12345678");
    patient.setGouvernorat("Tunis");
    patient.setAdresse("Test Adresse");
    patient.setConfirm_password("password");
    patient.setImage("doctor-img1-64070568b93ad.png");

         try {
        patientService.ajouter(patient);
        System.out.println("Patient ajouté avec succès !");
    } catch (SQLException e) {
        System.out.println("Erreur lors de l'ajout de patient : " + e.getMessage());
    }
    ///modifier
          try {
        patientService.modifier(patient);
        System.out.println("Patient modifié avec succès !");
    } catch (SQLException e) {
        System.out.println("Erreur lors de la modification du patient : " + e.getMessage());
    }
    
         // Récupération de tous les patients
   try {
       List<Patient> patients = patientService.recuperer();
        System.out.println("Liste de tous les patients : ");
        for (Patient p : patients) {
           System.out.println(p);
       }
    } catch (SQLException e) {
        System.out.println("Erreur lors de la récupération des Patients : " + e.getMessage());
    
     // Récupération d'un patient par ID

try {
     
    System.out.println("Patient récupéré par ID : " + patientService.recupererById(203));
} catch (SQLException e) {
   System.out.println("Erreur lors de la récupération du patient par ID : " + e.getMessage());
}
     
     
     
     
     // Suppression d'un patient existant
   try {
        patient.setId(203);
         patientService.supprimer(patient);
         System.out.println("Patient supprimé avec succès !");
     } catch (SQLException e) {
         System.out.println("Erreur lors de la suppression du patient : " + e.getMessage());
    }
      }*/
     
     ////////////////////////////////////////////////////////////////////////////
     
     
     
     
     
     
     
     
          MedecinService medecinService = new MedecinService();
   Medecin medecin = new Medecin();
  // medecin.setId(199);
    medecin.setEmail("tet@test.com");
    medecin.setPassword("password");
    medecin.setRoles(role);
    medecin.setNom("abdou");
    medecin.setPrenom("Test");
    medecin.setCin(12345678);
    medecin.setSexe("M");
    medecin.setTelephone("12345678");
    medecin.setGouvernorat("Tunis");
    medecin.setAdresse("Test Adresse");
    medecin.setConfirm_password("password");
    medecin.setImage("test.png");
    medecin.setTitre("Dr.");
    medecin.setAdresse_cabinet("Test Cabinet");
    medecin.setFixe("12345678");
    medecin.setDiplome_formation("Test Formation");
    medecin.setTarif(50.0f);
    medecin.setCnam(true);
        }} 
    /*
    try {
       medecinService.ajouter(medecin);
        System.out.println("Médecin ajouté avec succès !");
    } catch (SQLException e) {
        System.out.println("Erreur lors de l'ajout de médecin : " + e.getMessage());
    
   }*/

    // Récupération de tous les médecins
  // try {
      //  List<Medecin> medecins = medecinService.recuperer();
      //  System.out.println("Liste de tous les médecins : ");
       // for (Medecin m : medecins) {
         //  System.out.println(m);
      // }
    //} catch (SQLException e) {
      //  System.out.println("Erreur lors de la récupération des médecins : " + e.getMessage());
   // }}}

    // Récupération d'un médecin par ID

//try {
     
   // System.out.println("Médecin récupéré par ID : " + medecinService.recupererById(67));
//} catch (SQLException e) {
   // System.out.println("Erreur lors de la récupération du médecin par ID : " + e.getMessage());
//}}}
/*Scanner scanner = new Scanner(System.in);
System.out.print("Entrez l'ID du médecin à récupérer : ");
int id = scanner.nextInt();
try {
    Medecin medecinRecupere = medecinService.recupererById(id);
    System.out.println("Médecin récupéré par ID : " + medecinRecupere);
} catch (SQLException e) {
    System.out.println("Erreur lors de la récupération du médecin par ID : " + e.getMessage());
}}}*/
    // Modification d'un médecin existant
    
  //  try {
        //medecinService.modifier(medecin);
        //System.out.println("Médecin modifié avec succès !");
    //} catch (SQLException e) {
       // System.out.println("Erreur lors de la modification du médecin : " + e.getMessage());
    //}}}

    // Suppression d'un médecin existant
    //try {
        //medecin.setId(199);
       //  medecinService.supprimer(medecin);
        // System.out.println("Médecin supprimé avec succès !");
    // } catch (SQLException e) {
        // System.out.println("Erreur lors de la suppression du médecin : " + e.getMessage());
     //}}}

    

