package org.vivus.zxing;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.Writer;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class ZxingTest {
	public static final String BASE_DIR = "target/test-classes";
	static Reader reader = new MultiFormatReader();
	static Writer writer = new MultiFormatWriter();
	static String CONTENT = "I am a superman.";
	static final int WIDTH = 60;
	static final int HEIGHT = 60;
	static final String CHARSET = "UTF-8";
	static final BarcodeFormat BARCODE_FORMAT = BarcodeFormat.QR_CODE;

	@Test
	public void testEncode() throws WriterException, NotFoundException, ChecksumException,
			FormatException {
		BufferedImage image = MatrixToImageWriter.toBufferedImage(encode());
		Map<DecodeHintType, Object> dHints = new HashMap<DecodeHintType, Object>();
		dHints.put(DecodeHintType.CHARACTER_SET, CHARSET);
		Result decode = reader.decode(new BinaryBitmap(new HybridBinarizer(
				new BufferedImageLuminanceSource(image))), dHints);
		Assert.assertEquals(CONTENT, decode.getText());
	}

	@Test
	public void testToFile() throws WriterException, IOException {
		MatrixToImageWriter.writeToFile(encode(), "png", getFile());
	}

	@Test
	public void testPrint() throws WriterException, PrinterException {
		PrinterJob printerJob = PrinterJob.getPrinterJob();
		printerJob.setPrintable(new BarcodePrinter());
		if (printerJob.printDialog()) {
			printerJob.print();
		}
	}

	static class BarcodePrinter implements Printable {
		@Override
		public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
				throws PrinterException {
			if (pageIndex > 0)
				return NO_SUCH_PAGE;
			try {
				graphics.drawImage(MatrixToImageWriter.toBufferedImage(encode()), 72, 72, null);
			} catch (WriterException e) {
				e.printStackTrace();
			}
			return PAGE_EXISTS;
		}
	}

	private static BitMatrix encode() throws WriterException {
		Map<EncodeHintType, Object> eHints = new HashMap<EncodeHintType, Object>();
		eHints.put(EncodeHintType.CHARACTER_SET, CHARSET);
		eHints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
		BitMatrix bitMatrix = writer.encode(CONTENT, BarcodeFormat.QR_CODE, WIDTH, HEIGHT, eHints);
		return bitMatrix;
	}

	private File getFile() throws IOException {
		File file = new File(getFileName(".png"));
		if (!file.exists()) {
			File parent = file.getParentFile();
			if (!parent.exists()) {
				parent.mkdirs();
			}
			file.createNewFile();
		}
		return file;
	}

	private static String getFileName(String ext) {
		return BASE_DIR + ZxingTest.class.getName().replace('.', '/') + ext;
	}
}
