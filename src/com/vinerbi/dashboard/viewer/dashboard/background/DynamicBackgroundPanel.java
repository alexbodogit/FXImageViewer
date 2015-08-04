/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vinerbi.dashboard.viewer.dashboard.background;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.scene.CacheHint;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 *
 * @author avinerbi
 */
public class DynamicBackgroundPanel extends Pane
{
    List<CircleAnimated> circles = new ArrayList<>();
    
    public DynamicBackgroundPanel()
    {
        setStyle("-fx-background-color:rgb(50,50,50);");
    }
    
    public void createCircles()
    {
        
        Random random = new Random();
        
        int panelWidth = 800;
        int panelHeight = 600;
        
        for (int i = 0; i < 550; i++)
        {
            CircleAnimated circleAnimated = new CircleAnimated();
            
            circleAnimated.getCircle().setRadius(random.nextInt(50));
            circleAnimated.getCircle().setLayoutX(random.nextInt(panelWidth));
            circleAnimated.getCircle().setLayoutY(random.nextInt(panelHeight));
             
            circleAnimated.getCircle().setOpacity(random.nextDouble()/5);
            
            circleAnimated.getCircle().setCache(true);
            circleAnimated.getCircle().setCacheHint(CacheHint.SPEED);
             
            circleAnimated.getCircle().setFill(Color.WHITESMOKE);
            
            circleAnimated.getCircle().setEffect(new GaussianBlur((550-i)/5));
             
            circles.add(circleAnimated);
            getChildren().add(circleAnimated.getCircle());
        }
        
        AnimationTimer timer = new AnimationTimer()
        {

            @Override
            public void handle(long now)
            {
                circles.forEach(c->c.update(getWidth(),getHeight()));
            }
        };
        
        timer.start();
    }
}
