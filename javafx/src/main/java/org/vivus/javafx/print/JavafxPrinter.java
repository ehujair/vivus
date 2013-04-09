package org.vivus.javafx.print;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.print.PrinterJob;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class JavafxPrinter extends Application {
	public static void main(String[] args) {
		// print();
		// print1();
		// System.out.println("----------- showBootstapClass -----------");
		// showBootstapClass();
		// System.out.println("----------- showClassLoader -----------");
		// showClassLoader();
		// System.out.println("----------- showClassLoaderRecursively -----------");
		// showClassLoaderRecursively(JavafxPrinter.class.getClassLoader());
		// showClassPath();
		// showClassPathJar();
		// test();
		launch(args);
		// print2();
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

	public static void test() {
		com.sun.javafx.Logging.getCSSLogger();
		sun.util.logging.PlatformLogger logger = com.sun.javafx.tk.Toolkit.getToolkit().getLogger(
				"css");
		if (logger != null) {
			System.out.println(logger);
		}
	}

	public static void print() {
		Circle circ = new Circle(40, 40, 30);
		Group root = new Group(circ);
		PrinterJob printerJob = PrinterJob.createPrinterJob();
		if (printerJob != null) {
			if (printerJob.printPage(root)) {
				printerJob.endJob();
			}
		}
	}

	/*-------------------------------------------------------------------------
	 *                                                                        *
	 * Methods and state for managing the dirty bits of a Node. The dirty     *
	 * bits are flags used to keep track of what things are dirty on the      *
	 * node and therefore need processing on the next pulse. Since the pulse  *
	 * happens asynchronously to the change that made the node dirty (for     *
	 * performance reasons), we need to keep track of what things have        *
	 * changed.                                                               *
	 *                                                                        *
	 ------------------------------------------------------------------------*/
	public static void print1() {
		Circle node = new Circle(100, 200, 200);
		PrinterJob job = PrinterJob.createPrinterJob();
		if (job != null) {
			if (job.showPrintDialog(null)) {
				job.printPage(node);
				job.endJob();
			}
		}
	}

	public static void print2() {
		Node node = new Circle(100, 200, 200);
		PrinterJob job = PrinterJob.createPrinterJob();
		if (job != null) {
			boolean success = job.printPage(node);
			if (success) {
				job.endJob();
			}
		}
	}

	public static void showClassPathJar() {
		System.out.println("----------- showBootstapClass -----------");
		showBootstapClass();
		System.out.println("----------- showExtention -----------");
		System.out.println(System.getProperty("java.ext.dirs"));
		System.out.println("----------- showApplication -----------");
		Properties p = System.getProperties();
		String value = p.getProperty("java.class.path");
		String[] jar = value.split(";");
		for (int i = 0; i < jar.length; i++) {
			System.out.println(jar[i]);
		}
	}

	public static void showClassPath() {
		System.out.println(Thread.currentThread().getContextClassLoader().getResource(""));
		Enumeration<URL> resources = null;
		try {
			resources = Thread.currentThread().getContextClassLoader().getResources("");
		} catch (IOException e) {
			e.printStackTrace();
		}
		while (resources.hasMoreElements()) {
			URL url = resources.nextElement();
			System.out.println(url);
		}
	}

	public static void showBootstapClass() {
		URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
		for (URL url : urls) {
			System.out.println(url.toExternalForm());
		}
	}

	public static void showClassLoader() {
		ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
		System.out.println("contextClassLoader: " + contextClassLoader);
		ClassLoader currentThreadClassLoader = Thread.currentThread().getClass().getClassLoader();
		System.out.println("currentThreadClassLoader: " + currentThreadClassLoader);
		ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
		System.out.println("systemClassLoader: " + systemClassLoader);
		System.out.println("extensionClassloader: " + systemClassLoader.getParent());
	}

	public static void showClassLoaderRecursively(ClassLoader classLoader) {
		if (classLoader.getParent() != null) {
			showClassLoaderRecursively(classLoader.getParent());
		}
		System.out.println(classLoader);
	}

}
