package org.vivus.javafx.print;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import org.vivus.javafx.util.PrintUtil;

public class PrinterTextTest extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Text text = new Text(10, 10, "如何区别接瓶、拔针的扫描？");
		Circle circ = new Circle(50, 50, 30);
		Group root = new Group(text, circ);
		PrintUtil.print(root, "PrinterTextTest");
		Platform.exit();
	}
}
