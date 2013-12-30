package org.vivus.jetty;

import java.io.File;
import java.io.IOException;

public class PathTest {
	public static void main(String[] args) {
		System.out.println("System.getProperty(\"user.dir\"): " + System.getProperty("user.dir"));
		System.out.println("java.net.URL.getPath(): "
				+ Thread.currentThread().getContextClassLoader().getResource("").getPath());
		System.out.println("PathTest.class.getResource(\"\").getPath(): "
				+ PathTest.class.getResource("").getPath());
		System.out.println("PathTest.class.getResource(\"/\").getPath(): "
				+ PathTest.class.getResource("/").getPath());
		System.out.println(PathTest.class.getProtectionDomain().getCodeSource().getLocation()
				.getPath());
		System.out.println("ClassLoader.getSystemResource(\"\").getPath(): "
				+ ClassLoader.getSystemResource("").getPath());
		try {
			System.out.println("new File(\"\").getCanonicalPath()"
					+ new File("").getCanonicalPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("new File(\"\").getAbsolutePath(): " + new File("").getAbsolutePath());
		System.out.println("System.getProperty(\"java.class.path\"): "
				+ System.getProperty("java.class.path"));
	}
}
