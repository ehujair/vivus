package org.vivus.javafx.print;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.print.PrinterJob;
import javafx.scene.Group;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class PrinterTest extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Circle circ = new Circle(40, 40, 30);
		Group root = new Group(circ);
		PrinterJob printerJob = PrinterJob.createPrinterJob();
		if (printerJob != null) {
			if (printerJob.printPage(root)) {
				printerJob.endJob();
			}
		}
		Platform.exit();
	}
}
