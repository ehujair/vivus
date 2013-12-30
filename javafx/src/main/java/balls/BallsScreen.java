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
package balls;

import static balls.Constants.*;
import balls.component.InfoPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class BallsScreen {

    private final Stage stage;

    public BallsScreen(Stage stage) {
        this.stage = stage;
    }

    public void run() {
        stage.setTitle(TITLE);
        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT + HEIGHT_CORRECTION);
        stage.setScene(createScene());
        stage.setX((Screen.getPrimary().getVisualBounds().getWidth() - stage.getWidth()) / 2);
        stage.setY((Screen.getPrimary().getVisualBounds().getHeight() - stage.getHeight()) / 2);

        stage.setResizable(false);

        stage.show();
    }

    private Scene createScene() {
        final Scene scene = new Scene(new Group());
        scene.setFill(Color.ALICEBLUE);
        final Line line = createLine();
        final InfoPanel infoPanel = createInfoPanel();
        final BallsPane ballsPane = createBallsPane();

        infoPanel.setBallsPane(ballsPane);

        ((Group) scene.getRoot()).getChildren().add(line);
        ((Group) scene.getRoot()).getChildren().add(infoPanel);
        ((Group) scene.getRoot()).getChildren().add(ballsPane);
        return scene;
    }

    private Line createLine() {
        final Line line = new Line();
        line.setEndX(WIDTH);
        line.setTranslateY(HEIGHT / 2 + INFOPANEL_HEIGHT + BALL_RADIUS);
        line.setStrokeWidth(5f);
        line.setStroke(Color.BLACK);
        return line;
    }

    private InfoPanel createInfoPanel() {
        final InfoPanel infoPanel = new InfoPanel();
        infoPanel.setTranslateY(HEIGHT_CORRECTION);
        return infoPanel;
    }

    private BallsPane createBallsPane() {
        final BallsPane ballsPane = new BallsPane();
        return ballsPane;
    }
}
