/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vinerbi.dashboard.viewer.thumbs;

import com.vinerbi.dashboard.viewer.interfaces.RootContainer;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.CacheHint;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author avinerbi
 */
public class ThumbController implements RootContainer
{

    @FXML
    ImageView imageView;
    
    @FXML
    Pane root;
    
    private Image image;
    
    private GaussianBlur blur;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        Random r = new Random();
        
        imageView.setLayoutX(0);
        imageView.setLayoutY(0);
        
        imageView.fitWidthProperty().bind(root.prefWidthProperty());
        imageView.fitHeightProperty().bind(root.prefHeightProperty());
        
        loadImage(); 
       
        
        Random random = new Random();
        root.setLayoutX(random.nextInt(800));
        root.setLayoutY(random.nextInt(600));
        
        root.setTranslateZ(-r.nextInt(50)+50);
        
        blur = new GaussianBlur(0);
        
        root.setEffect(blur);
        
         
                
    }    
    
    private void loadImage()
    {
        image = new Image("http://lorempixel.com/400/200/");
        imageView.setImage(image);
    }

    @Override
    public Pane getRoot()
    {
        return root;
    }
    
}
