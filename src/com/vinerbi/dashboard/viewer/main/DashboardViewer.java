/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vinerbi.dashboard.viewer.main;

import com.vinerbi.dashboard.viewer.dashboard.Dashboard;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author avinerbi
 */
public class DashboardViewer extends Application
{

    @Override
    public void start(Stage primaryStage) throws Exception
    {
       Dashboard.createScene(primaryStage);
    }
    
    
    public static void main(String[] args)
    {
        launch(args);
    }
}
