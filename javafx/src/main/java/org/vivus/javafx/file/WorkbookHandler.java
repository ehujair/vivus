package org.vivus.javafx.file;

import org.apache.poi.ss.usermodel.Workbook;

public interface WorkbookHandler {
	boolean accept(String fileExtension);

	void handle(Workbook workbook);
}
