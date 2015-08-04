/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vinerbi.dashboard.viewer.dashboard;

import com.vinerbi.dashboard.viewer.dashboard.background.DynamicBackgroundPanel;
import com.vinerbi.dashboard.viewer.interfaces.RootContainer;
import com.vinerbi.dashboard.viewer.thumbs.ThumbController;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.RotateTransition;

import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point3D;
import javafx.scene.PerspectiveCamera;
import javafx.scene.SubScene;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author avinerbi
 */
public class DashboardUIController implements RootContainer
{

    @FXML
    Pane rootPanel;

    private DynamicBackgroundPanel backgroundPanel;

    private List<ThumbController> imageItems = new ArrayList<>();

    private Pane rootSubScene;

    private SubScene subScene;

    private Text text;

    private PerspectiveCamera cameraSubScene;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        backgroundPanel = new DynamicBackgroundPanel();

        backgroundPanel.prefWidthProperty().bind(rootPanel.widthProperty());
        backgroundPanel.prefHeightProperty().bind(rootPanel.heightProperty());

        backgroundPanel.setLayoutX(0);
        backgroundPanel.setLayoutY(0);

        rootPanel.getChildren().add(backgroundPanel);

        backgroundPanel.createCircles();

        cameraSubScene = new PerspectiveCamera();

        rootSubScene = new Pane();
        subScene = new SubScene(rootSubScene, rootPanel.getPrefWidth(), rootPanel.getPrefHeight());
        rootPanel.getChildren().add(subScene);

        subScene.widthProperty().bind(rootPanel.widthProperty());
        subScene.heightProperty().bind(rootPanel.heightProperty());

        subScene.setLayoutX(0);
        subScene.setLayoutY(0);
        
        cameraSubScene.setFieldOfView(50);

        subScene.setCamera(cameraSubScene);

        createImages();
    }

    public void createImages()
    {
        text = new Text("Load images...");

        rootPanel.getChildren().add(text);
        text.setFill(Color.WHITE);
        text.setLayoutX(50);
        text.setLayoutY(50);

        Thread sleeper = new Thread(() ->
        {

            for (int i = 0; i < 30; i++)
            {
                FXMLLoader loader = new FXMLLoader(ThumbController.class.getResource("Thumb.fxml"));
                try
                {
                    loader.load();
                    ThumbController controller = (ThumbController) loader.getController();
                    imageItems.add(controller);

                } catch (IOException ex)
                {
                    Logger.getLogger(DashboardUIController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            Platform.runLater(() -> showImages());
        });

        sleeper.start();

    }

    public void hideText()
    {
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(4), text);
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);

        fadeOut.setOnFinished(e -> rootPanel.getChildren().remove(text));

        fadeOut.play();

    }

    public void showImages()
    {
        hideText();

        Random delayRandom = new Random();
        int index = 0;
        for (ThumbController item : imageItems)
        {
            rootSubScene.getChildren().add(item.getRoot());

            item.getRoot().setOpacity(0);
            item.getRoot().setRotationAxis(Rotate.Y_AXIS);
            item.getRoot().setRotate(delayRandom.nextInt(180));

            item.getRoot().setScaleX(1.5);
            item.getRoot().setScaleY(1.5);

            item.getRoot().setEffect(new GaussianBlur(imageItems.size() - index));

            FadeTransition fadeIn = new FadeTransition(Duration.seconds(4), item.getRoot());
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.setDelay(Duration.seconds(delayRandom.nextInt(3)));
            fadeIn.play();

            ScaleTransition scale = new ScaleTransition(Duration.seconds(4), item.getRoot());
            scale.setFromX(item.getRoot().getScaleX());
            scale.setFromY(item.getRoot().getScaleY());
            scale.setToX(1);
            scale.setToY(1);
            scale.setDelay(fadeIn.getDelay());
            scale.play();

            RotateTransition rotation = new RotateTransition(Duration.seconds(3), item.getRoot());
            rotation.setFromAngle(0);
            rotation.setToAngle(360);
            rotation.setAxis(Rotate.Y_AXIS);
            rotation.setInterpolator(Interpolator.LINEAR);
            rotation.setDelay(fadeIn.getDelay());
            rotation.setCycleCount(Animation.INDEFINITE);
            rotation.play();
            index++;
        }

         
        // imposto il pivot al centro dello schermo
        Rotate rotate = new Rotate(0);
        rotate.setPivotX(400);
        rotate.setPivotY(300);
        rotate.setPivotZ(75);
        
        cameraSubScene.setTranslateZ(-100);
        
        // e gli dico di ruotare su tutti gli assi
        rotate.setAxis(new Point3D(0,1,0));
        
        cameraSubScene.getTransforms().clear();
        
        
        
        cameraSubScene.getTransforms().add(rotate);
        
        Timeline animation = new Timeline();
        animation.getKeyFrames().add(new KeyFrame(Duration.seconds(5), new KeyValue(rotate.angleProperty(), 360)));
        
        animation.setCycleCount(Animation.INDEFINITE);
        animation.play();
       
    }

    @Override
    public Pane getRoot()
    {
        return rootPanel;
    }

}
