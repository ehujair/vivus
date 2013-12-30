package org.vivus.awt.print;

import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.print.DocFlavor;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.ServiceUI;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;

public class PrinterJobTest {
	public static void main(String[] args) throws PrinterException, PrintException {
		test1();
		test2();
	}

	private static void test1() throws PrinterException {
		PrinterJob pj = PrinterJob.getPrinterJob();
		pj.setPrintable(new Print());
		if (pj.printDialog()) {
			pj.print();
		}
	}

	private static void test2() throws PrintException {
		print(new Print(), DocFlavor.SERVICE_FORMATTED.PRINTABLE);
	}

	private static void print(Object printData, DocFlavor flavor) throws PrintException {
		PrintService[] lookupPrintServices = PrinterJob.lookupPrintServices();
		if (lookupPrintServices == null || lookupPrintServices.length < 1) {
			throw new RuntimeException("can not find PrintService");
		}
		System.out.println(lookupPrintServices.length);
		for (PrintService service : lookupPrintServices) {
			System.out.println(service);
		}
		HashPrintRequestAttributeSet attributes = new HashPrintRequestAttributeSet();
		PrintService printService = ServiceUI.printDialog(null, 72, 72, lookupPrintServices,
				PrintServiceLookup.lookupDefaultPrintService(), flavor, attributes);
		System.out.println("javax.print.PrintService: " + printService);
		if (printService != null) {
			printService.createPrintJob().print(new SimpleDoc(printData, flavor, null), attributes);
		}
	}

	static class Print implements Printable {

		public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
				throws PrinterException {
			if (pageIndex > 0) {// only print one page
				return NO_SUCH_PAGE;
			}
			// this will run twice
			System.out.println(graphics.getFont().getSize());
			System.out.println(graphics.getFont().getSize2D());
			graphics.drawString("org.vivus.awt.print.PrinterJobTest: 测试; Font height: "
					+ graphics.getFont().getSize(), 72, 72 + graphics.getFont().getSize());
			return PAGE_EXISTS;
		}

	}
}
