package org.vivus.javafx.util;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javafx.scene.Node;

import com.sun.javafx.sg.BaseNode;
import com.sun.javafx.sg.PGNode;
import com.sun.prism.j2d.PrismPrintGraphics;

public class AwtPrintUtil {
	public static void print(Node root) throws PrinterException {
		PrinterJob printerJob = PrinterJob.getPrinterJob();
		printerJob.setPrintable(new PrintNode(root), createPageFormat());
		if (printerJob.printDialog()) {
			printerJob.print();
		}
	}

	static PageFormat createPageFormat() {
		PageFormat pageFormat = new PageFormat();
		Paper paper = new Paper();
		paper.setImageableArea(0, 0, 225, 130);
		pageFormat.setPaper(paper);
		return pageFormat;
	}

	static class PrintNode implements Printable {
		Node node;

		PrintNode(Node node) {
			this.node = node;
		}

		public int print(Graphics g, PageFormat pf, int pageIndex) {
			if (pageIndex > 0) {
				return Printable.NO_SUCH_PAGE;
			}
			int x = (int) pf.getImageableX();
			int y = (int) pf.getImageableY();
			int w = (int) pf.getImageableWidth();
			int h = (int) pf.getImageableHeight();
			g.translate(x, y);
			printNode(node, g, w, h);
			return Printable.PAGE_EXISTS;
		}

		@SuppressWarnings({ "unchecked", "deprecation" })
		private void printNode(Node node, Graphics g, int w, int h) {
			PrismPrintGraphics ppg = new PrismPrintGraphics((Graphics2D) g, w, h);
			PGNode pgNode = node.impl_getPGNode();
			((BaseNode<PrismPrintGraphics>) pgNode).render(ppg);
		}
	}
}
