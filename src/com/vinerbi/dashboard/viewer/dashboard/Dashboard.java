/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vinerbi.dashboard.viewer.dashboard;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author avinerbi
 */
public class Dashboard
{
    
    private static Scene scene = null;

    private static PerspectiveCamera camera;
    
    private static DashboardUIController uiController;
    
    private static Stage stage;

    public static DashboardUIController getUiController()
    {
        return uiController;
    }
  
    /**
     * Get the value of scene
     *
     * @return the value of scene
     */
    public static Scene getScene()
    {
        return scene;
    }
    
    public static void createScene(Stage primaryStage)
    {
        stage = primaryStage;
        
        FXMLLoader loader = new FXMLLoader(Dashboard.class.getResource("DashboardUI.fxml"));
        
        try
        {
            loader.load();
            uiController = (DashboardUIController)loader.getController();
            
            scene = new Scene(uiController.getRoot(),800,600);
             
            
            stage.setScene(scene);
            stage.show();
            
            
        } catch (IOException ex)
        {

        }
        
    }

}
