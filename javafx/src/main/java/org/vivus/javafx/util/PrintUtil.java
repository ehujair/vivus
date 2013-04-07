package org.vivus.javafx.util;

import javafx.print.JobSettings;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.stage.Window;

public class PrintUtil {
	public static void print(Node root) {
		print(root, null, null);
	}

	public static void print(Node root, Window owner) {
		print(root, owner, null);
	}

	public static void print(Node root, String name) {
		print(root, null, name);
	}

	public static void print(Node root, Window owner, String name) {
		PrinterJob job = PrinterJob.createPrinterJob();
		if (job != null) {
			JobSettings jobSettings = job.getJobSettings();
			if (jobSettings != null && name != null) {
				jobSettings.setJobName(name);
			}
			if (job.showPrintDialog(owner)) {
				job.printPage(root);
				job.endJob();
			}
		}
	}
}
