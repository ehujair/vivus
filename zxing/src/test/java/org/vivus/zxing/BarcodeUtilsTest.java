package org.vivus.zxing;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Test;
import org.vivus.zxing.scan.BarcodeUtils;

public class BarcodeUtilsTest {
	@Test
	public void testEmbed() throws IOException {
		int size = 500;
		BufferedImage barcode = BarcodeUtils.encode("http://www.baidu.com", size, size);
		ImageIO.write(barcode, "jpg", new File("C:/Users/Administrator/Desktop/SourceBarcode.jpg"));
		BufferedImage embedImage = ImageIO.read(new File(
				"C:/Users/Administrator/Desktop/ul45211631-3.jpg"));
		BufferedImage embed = BarcodeUtils.embed(barcode, embedImage);
		ImageIO.write(embed, "jpg", new File("C:/Users/Administrator/Desktop/EmbedBarcode.jpg"));
	}

	@Test
	public void testEmbed2Image() throws IOException {
		BufferedImage a = ImageIO.read(new File("C:/Users/Administrator/Desktop/wikipedia.jpg"));
		BufferedImage b = ImageIO.read(new File("C:/Users/Administrator/Desktop/ul45211631-3.jpg"));
		BufferedImage embed = BarcodeUtils.embed(a, b);
		ImageIO.write(embed, "jpg", new File("C:/Users/Administrator/Desktop/testEmbed2Image.jpg"));
	}

	@Test
	public void testEmbed2Image2() throws IOException {
		BufferedImage a = ImageIO
				.read(new File("C:/Users/Administrator/Desktop/SourceBarcode.jpg"));
		System.out.println(a);
		BufferedImage b = ImageIO.read(new File("C:/Users/Administrator/Desktop/ul45211631-3.jpg"));
		System.out.println(b);
		BufferedImage embed = BarcodeUtils.embed(a, b);
		ImageIO.write(embed, "jpg", new File("C:/Users/Administrator/Desktop/testEmbed2Image2.jpg"));
	}

	@Test
	public void testEmbed2() throws IOException {
		int size = 500;
		BufferedImage barcode = BarcodeUtils.encode("http://www.baidu.com", size, size);
		ImageIO.write(barcode, "PNG", new File("C:/Users/Administrator/Desktop/SourceBarcode2.png"));
		BufferedImage embed = BarcodeUtils.embed(barcode,
				ImageIO.read(new File("C:/Users/Administrator/Desktop/wikipedia.jpg")));
		ImageIO.write(embed, "PNG", new File("C:/Users/Administrator/Desktop/EmbedBarcode2.png"));
	}

	@Test
	public void testEmbed3() throws IOException {
		int size = 500;
		BufferedImage barcode = BarcodeUtils.encode("http://www.baidu.com", size, size);
		ImageIO.write(barcode, "PNG", new File("C:/Users/Administrator/Desktop/SourceBarcode3.png"));
		BufferedImage embed = BarcodeUtils.embed(barcode,
				ImageIO.read(new File("C:/Users/Administrator/Desktop/wikipedia.jpg")), 30);
		ImageIO.write(embed, "PNG", new File("C:/Users/Administrator/Desktop/EmbedBarcode3.png"));
	}

	@Test
	public void testEmbed4() throws IOException {
		int size = 500;
		BufferedImage barcode = BarcodeUtils.encode("http://www.baidu.com", size, size);
		ImageIO.write(barcode, "PNG", new File("C:/Users/Administrator/Desktop/SourceBarcode4.png"));
		BufferedImage embed = BarcodeUtils.embed(barcode,
				ImageIO.read(new File("C:/Users/Administrator/Desktop/wikipedia.jpg")), 5);
		ImageIO.write(embed, "PNG", new File("C:/Users/Administrator/Desktop/EmbedBarcode4.png"));
	}
}
