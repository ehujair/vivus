/*
 * Copyright (c) 2008, 2012 Oracle and/or its affiliates.
 * All rights reserved. Use is subject to license terms.
 *
 * This file is available and licensed under the following license:
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  - Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *  - Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the distribution.
 *  - Neither the name of Oracle Corporation nor the names of its
 *    contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package stopwatch;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import stopwatch.watch.Watch;

public class MainScreen extends Application {
    private Stage stage;
    private Scene scene;

    private Watch watch;

    private static final double SCENE_WIDTH = 310;
    private static final double SCENE_HEIGHT = 315;

    private double initX;
    private double initY;

    @Override public void start(Stage stage) {
        this.stage = stage;
        watch = new Watch(this);
        configureScene();
        configureStage();
        addDrag();
        myLayout();
    }

    private void configureStage() {
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setTitle("Stop Watch");
        stage.setResizable(false);
        stage.setHeight(SCENE_HEIGHT);
        stage.setWidth(SCENE_WIDTH);
        stage.centerOnScreen();
        stage.setScene(scene);
        stage.show();
    }
    private void configureScene() {
        scene = new Scene(new Group(watch));
        scene.setFill(Color.TRANSPARENT);
    }

    private void myLayout() {
        watch.setLayoutX(15);
        watch.setLayoutY(20);
    }

    private void addDrag() {
        watch.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                initX = me.getScreenX() - stage.getX();
                initY = me.getScreenY() - stage.getY();
                me.consume();
            }
        });
        watch.setOnMouseDragged(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                stage.setX(me.getScreenX() - initX);
                stage.setY(me.getScreenY() - initY);
                me.consume();
            }
        });
    }

    public Scene getScene() {
        return scene;
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
