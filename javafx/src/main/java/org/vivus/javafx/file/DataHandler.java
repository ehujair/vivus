package org.vivus.javafx.file;

import org.apache.poi.ss.usermodel.Workbook;

public interface DataHandler<T> {
	void handle(Workbook workbook, T data);
}
