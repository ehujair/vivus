package org.vivus.zxing.scan;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class BarcodeUtils {
	static final Logger LOGGER = LoggerFactory.getLogger(BarcodeUtils.class);

	public static final int DEFAULT_SIZE = 60;
	public static final double DEFAULT_SCALE = 25.0;

	public static BufferedImage encode(String contents) {
		return encode(contents, DEFAULT_SIZE, DEFAULT_SIZE);
	}

	public static BufferedImage encode(String contents, int width, int height) {
		return encode(contents, BarcodeFormat.QR_CODE, width, height);
	}

	public static BufferedImage encode(String contents, BarcodeFormat barcodeFormat, int width,
			int height) {
		return encode(contents, barcodeFormat, width, height, ErrorCorrectionLevel.H);
	}

	private static BufferedImage encode(String contents, BarcodeFormat barcodeFormat, int width,
			int height, ErrorCorrectionLevel ecl) {
		Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
		hints.put(EncodeHintType.ERROR_CORRECTION, ecl);
		return encode(contents, barcodeFormat, width, height, hints);
	}

	public static BufferedImage encode(String contents, BarcodeFormat barcodeFormat, int width,
			int height, Map<EncodeHintType, Object> hints) {
		if (contents == null || "".equals(contents.trim())) {
			return null;
		}
		try {
			return MatrixToImageWriter.toBufferedImage(
					new MultiFormatWriter().encode(contents, barcodeFormat, width, height, hints),
					new MatrixToImageConfig(0xFF000001, MatrixToImageConfig.WHITE));
		} catch (WriterException e) {
			throw new RuntimeException("生成二维码出错", e);
		}
	}

	public static BufferedImage embed(BufferedImage sourceImage, BufferedImage embededImage) {
		return embed(sourceImage, embededImage, DEFAULT_SCALE);
	}

	/**
	 * 在QR二维码中嵌入图片，程序会自动根据指定的scale值对嵌入图片进行缩放；
	 * scale值越大，嵌入的图片越小，但对QR码读取影响越小；
	 * scale值太小，嵌入的图片过大，有可能令QR码无法读取。
	 * 
	 * @param sourceImage 源QR码图片
	 * @param embededImage 嵌入图片
	 * @param scale sourceImage面积:embededImage面积的值
	 * @return
	 */
	public static BufferedImage embed(BufferedImage sourceImage, BufferedImage embededImage,
			double scale) {
		if (scale < 1) {
			throw new IllegalArgumentException("scale should large than 1");
		}
		double allowArea = sourceImage.getWidth() * sourceImage.getHeight() / scale;
		double embededArea = embededImage.getWidth() * embededImage.getHeight();
		int width = embededImage.getWidth();
		int height = embededImage.getHeight();
		if (embededArea > allowArea) {
			double zoomScale = Math.sqrt(embededArea / allowArea);
			width = (int) (width / zoomScale);
			height = (int) (height / zoomScale);
		}
		int left = (sourceImage.getWidth() - width) / 2;
		int top = (sourceImage.getHeight() - height) / 2;
		Graphics graphics = sourceImage.getGraphics();
		graphics.drawImage(embededImage, left, top, width, height, null);
		graphics.dispose();
		return sourceImage;
	}
}
