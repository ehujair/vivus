package org.vivus.awt.print;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.IndexColorModel;
import java.awt.image.WritableRaster;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.util.Hashtable;

public class PrintableBufferedImage extends BufferedImage implements Printable {
	public PrintableBufferedImage(ColorModel cm, WritableRaster raster,
			boolean isRasterPremultiplied, Hashtable properties) {
		super(cm, raster, isRasterPremultiplied, properties);
	}

	public PrintableBufferedImage(int width, int height, int imageType) {
		super(width, height, imageType);
	}

	public PrintableBufferedImage(int width, int height, int imageType, IndexColorModel cm) {
		super(width, height, imageType, cm);
	}

	public int print(Graphics g, PageFormat pf, int page) throws PrinterException {

		Graphics2D g2d = (Graphics2D) g;

		if (page > 0) { /* We have only one page, and 'page' is zero-based */
			return NO_SUCH_PAGE;
		}

		/*
		 * User (0,0) is typically outside the imageable area, so we must
		 * translate by the X and Y values in the PageFormat to avoid clipping
		 */
		try {
			g2d.setClip(0, 0, 2100, 3300);
			g2d.drawImage(this, 225, 0, null);
			g2d.drawImage(this, 225, 1550, null);
		} catch (Exception exc) {
		}
		/* tell the caller that this page is part of the printed document */
		// return NO_SUCH_PAGE;
		return PAGE_EXISTS;
	}

	public BufferedImage resizeOurImageFromImage(BufferedImage OurImage, int targetWidth,
			int targetHeight) throws Exception {
		double tempWidth, tempHeight;
		int w, h, type;
		Boolean OurImageHasAlpha;
		OurImageHasAlpha = OurImage.getColorModel().hasAlpha();
		if (OurImageHasAlpha) {
			type = BufferedImage.TYPE_INT_ARGB;
		} else {
			type = BufferedImage.TYPE_INT_RGB;
		}

		w = OurImage.getWidth();
		h = OurImage.getHeight();

		if ((targetWidth == 0) && (targetHeight != 0)) {
			targetWidth = (targetHeight * w) / h;
		} else {
			if ((targetHeight == 0) && (targetWidth != 0)) {
				targetHeight = (targetWidth * h) / w;
			}
		}

		if ((targetHeight == 0) || (targetWidth == 0)) {
			throw (new Exception(
					"In the Resize Image module with one dimension still zero after trying proportion"));
		}
		do {
			if (w > targetWidth) {
				tempWidth = ((double) w) / 1.2;
				if (tempWidth < (double) targetWidth) {
					w = targetWidth;
				} else {
					w = (int) java.lang.Math.round(tempWidth + 0.49);
				}
			} else {
				w = targetWidth;
			}
			if (h > targetHeight) {
				tempHeight = ((double) h) / 1.2;
				if (tempHeight < (double) targetHeight) {
					h = targetHeight;
				} else {
					h = (int) java.lang.Math.round(tempHeight + 0.49);
				}
			} else {
				h = targetHeight;
			}
			BufferedImage tmp = new BufferedImage(w, h, type);
			Graphics2D g2 = tmp.createGraphics();
			g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
					RenderingHints.VALUE_INTERPOLATION_BICUBIC);
			g2.drawImage(OurImage, 0, 0, w, h, null);
			g2.dispose();
			OurImage = tmp;
		} while ((targetHeight != h) || (targetWidth != w));

		return OurImage;
	}
}
