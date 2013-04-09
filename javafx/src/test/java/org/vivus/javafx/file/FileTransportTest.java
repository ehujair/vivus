package org.vivus.javafx.file;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;

public class FileTransportTest extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		uploadFile(primaryStage);
		downloadFile(primaryStage);
		Platform.exit();
	}

	private void uploadFile(Stage primaryStage) throws InvalidFormatException, IOException {
		WorkbookHandler workbookHandler = new WorkbookHandler() {
			Set<String> acceptExtensions = new HashSet<String>();
			{
				acceptExtensions.add(".xls");
				acceptExtensions.add(".xlsx");
			}

			@Override
			public boolean accept(String fileExtension) {
				return acceptExtensions.contains(fileExtension);
			}

			@Override
			public void handle(Workbook workbook) {
				System.out.println("handling " + workbook.getClass().getName());
			}
		};
		ExcelUtil.upload(workbookHandler, primaryStage, "title", "excel file", "*.xls", "*.xlsx");
	}

	private void downloadFile(Stage primaryStage) throws InvalidFormatException, IOException {
		List<String> data = new ArrayList<String>();
		DataHandler<List<String>> dataHandler = new DataHandler<List<String>>() {
			@Override
			public void handle(Workbook workbook, List<String> data) {
				workbook.createSheet("downloadFile");
				System.out.println("handling data to " + workbook.getClass().getName());
			}
		};

		String xls = "*.xls";
		String xlsx = "*.xlsx";
		ExcelUtil
				.download(
						dataHandler,
						data,
						primaryStage,
						"save file",
						"save",
						new ExtensionFilter(ExcelUtil.spliceDescription(ExcelUtil.EXCEL_97TO2003,
								xls), xls),
						new ExtensionFilter(ExcelUtil.spliceDescription(ExcelUtil.EXCEL, xlsx),
								xlsx));
	}
}
