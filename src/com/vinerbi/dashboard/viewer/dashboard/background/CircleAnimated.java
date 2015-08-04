/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vinerbi.dashboard.viewer.dashboard.background;

import java.util.Random;
import javafx.scene.shape.Circle;

/**
 *
 * @author avinerbi
 */
public class CircleAnimated
{
    private double stepX;
    private double stepY;
    
    private Circle circle;

    public CircleAnimated()
    {
        Random random = new Random();
        stepX = random.nextDouble()/2;
        stepY = random.nextDouble()/2;
        
        circle = new Circle();
    }
    
    public void update(double width, double heihgt)
    {
        if (circle.getLayoutX() <= 0 || circle.getLayoutX() >= width)
            stepX *= -1;
        
        if (circle.getLayoutY() <= 0 || circle.getLayoutY() >= heihgt)
            stepY *= -1;
        
        circle.setLayoutX(circle.getLayoutX()+stepX);
        circle.setLayoutY(circle.getLayoutY()+stepY);
    }
    
    /**
     * Get the value of circle
     *
     * @return the value of circle
     */
    public Circle getCircle()
    {
        return circle;
    }

    

}
