/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class VideoFXMLController implements Initializable {

    @FXML
    private MediaView mediaview;
    @FXML
    private Slider volume;
    @FXML
    private Button stop;
    @FXML
    private Button play;
    @FXML
    private Button pause;
    @FXML
    private Button Go_Back;
private MediaPlayer mediaplayer;
    private Media media;
    public static String videoName;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         try{
           String videoname = null;
           String path = new File("src/video/" + videoname + ".mp4").getAbsolutePath();
           //File mediaFile = new File("C:\\Users\\ASUS\\Downloads\\Ciel et Nuages Zoom Virtuel Arrière-plan.mp4");
           File mediaFile = new File("C:\\Users\\HP\\Downloads\\dawini.mp4");
           
           Media media = new Media(mediaFile.toURI().toURL().toString());
            mediaplayer = new MediaPlayer(media);
            mediaview.setMediaPlayer(mediaplayer);
            volume.setValue(mediaplayer.getVolume() * 50);
            
             volume.valueProperty().addListener(new InvalidationListener() {
            

               @Override
               public void invalidated(Observable observable) {
                 mediaplayer.setVolume(volume.getValue() / 100);

               }

        });
      } catch (MalformedURLException ex) {
           System.out.println(ex.getMessage());
      }
    } 
    
    
    
    
    
    
    @FXML
    private void play(ActionEvent event) {
        mediaplayer.play();
    }
    
    @FXML
    private void pause(ActionEvent event) {
        mediaplayer.pause();
    }

    @FXML
    private void stop(ActionEvent event) {
        mediaplayer.seek(mediaplayer.getStartTime());
        mediaplayer.stop();
    }
    
}
