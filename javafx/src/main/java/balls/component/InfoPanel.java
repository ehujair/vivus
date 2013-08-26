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
package balls.component;

import balls.BallsPane;
import static balls.Constants.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class InfoPanel extends Parent {

    private static final int BUTTON_WIDTH = 60;
    private static final int BUTTON_HEIGHT = 30;
    private static final int BUTTON_X = (int) WIDTH - 2 * BUTTON_WIDTH;
    private BallsPane ballsPane;

    public InfoPanel() {
        getChildren().add(createInfoText());

        Node resetButton = createResetButton();
        resetButton.setTranslateX(BUTTON_X);
        resetButton.setTranslateY((-BUTTON_HEIGHT - text.getBoundsInLocal().getHeight()) / 2);
        getChildren().add(resetButton);
    }
    private Text text;

    private Text createInfoText() {
        text = new Text();
        text.setText("Press a ball to start/pause the animation.");
        text.setFill(Color.BLACK);
        text.setTranslateX((WIDTH - text.getBoundsInLocal().getWidth()) / 2);
        return text;
    }

    private Node createResetButton() {
        final Button btn = new Button("Reset");
        btn.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        btn.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                ballsPane.resetBalls();
            }
        });
        return btn;
    }

    public void setBallsPane(BallsPane ballsPane) {
        this.ballsPane = ballsPane;
    }
}
