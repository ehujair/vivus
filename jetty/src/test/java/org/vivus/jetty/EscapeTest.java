package org.vivus.jetty;

import org.apache.commons.lang3.StringEscapeUtils;
import org.junit.Test;

public class EscapeTest {
	@Test
	public void testEscape() {
		System.out.println("-------");
		System.out.println(System.lineSeparator());
		System.out.println("-------");
		System.out.println(StringEscapeUtils.escapeJava(System.lineSeparator()));
		System.out.println("-------");
		System.out.println(StringEscapeUtils.unescapeJava(System.lineSeparator()));
		System.out.println("-------");
	}
}
