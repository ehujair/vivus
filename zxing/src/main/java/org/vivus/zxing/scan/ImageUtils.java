package org.vivus.zxing.scan;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageUtils {

	public static BufferedImage zoomImage(BufferedImage originalImage, double times) {
		int width = (int) (originalImage.getWidth() * times);
		int height = (int) (originalImage.getHeight() * times);
		BufferedImage newImage = new BufferedImage(width, height, originalImage.getType());
		Graphics g = newImage.getGraphics();
		g.drawImage(originalImage, 0, 0, width, height, null);
		g.dispose();
		return newImage;
	}

	public static void zoomImage(String srcPath, String newPath, double times) {
		BufferedImage bufferedImage = null;
		try {
			File of = new File(srcPath);
			if (of.canRead()) {
				bufferedImage = ImageIO.read(of);
			}
		} catch (IOException e) {
			throw new IllegalArgumentException("无法读取文件： " + srcPath, e);
		}
		if (bufferedImage != null) {
			bufferedImage = zoomImage(bufferedImage, times);
			try {
				ImageIO.write(bufferedImage, parseNewFileExt(srcPath, newPath), new File(newPath));
			} catch (IOException e) {
				throw new IllegalArgumentException("无法保存文件： " + newPath, e);
			}
		}
	}

	/**
	 * 对图片进行放大
	 * 
	 * @param originalImage
	 *            原始图片
	 * @param times
	 *            放大倍数
	 * @return
	 */
	public static BufferedImage zoomInImage(BufferedImage originalImage, double times) {
		return zoomImage(originalImage, times);
	}

	/**
	 * 对图片进行放大
	 * 
	 * @param srcPath
	 *            原始图片路径(绝对路径)
	 * @param newPath
	 *            放大后图片路径（绝对路径）
	 * @param times
	 *            放大倍数
	 * @return 是否放大成功
	 */
	public static void zoomInImage(String srcPath, String newPath, double times) {
		zoomImage(srcPath, newPath, times);
	}

	private static String parseNewFileExt(String srcPath, String newPath) {
		String ext = null;
		String fileExtSeperator = ".";
		int lastIndexOf = newPath.lastIndexOf(fileExtSeperator);
		if (lastIndexOf != -1) {
			ext = newPath.substring(lastIndexOf + 1);
		}
		if (ext == null || "".equals(ext)) {
			int lastIndexOfSrc = srcPath.lastIndexOf(fileExtSeperator);
			if (lastIndexOfSrc != -1) {
				ext = srcPath.substring(lastIndexOfSrc);
			}
		}
		ext = ext == null || "".equals(ext) ? "JPG" : ext;
		return ext;
	}

	/**
	 * 对图片进行缩小
	 * 
	 * @param originalImage
	 *            原始图片
	 * @param times
	 *            缩小倍数
	 * @return 缩小后的Image
	 */
	public static BufferedImage zoomOutImage(BufferedImage originalImage, double times) {
		return zoomImage(originalImage, 1.0D / times);
	}

	/**
	 * 对图片进行缩小
	 * 
	 * @param srcPath
	 *            源图片路径（绝对路径）
	 * @param newPath
	 *            新图片路径（绝对路径）
	 * @param times
	 *            缩小倍数
	 * @return 保存是否成功
	 */
	public static void zoomOutImage(String srcPath, String newPath, double times) {
		zoomImage(srcPath, newPath, 1.0D / times);
	}
}
