package org.vivus.nda.tools.util;

import org.junit.Assert;
import org.junit.Test;

public class ByteUtilTest {
	@Test
	public void testToShort() {
		Assert.assertEquals("123B", ByteUtil.toShort(123L));
		Assert.assertEquals("1k", ByteUtil.toShort(1024L));
		Assert.assertEquals("2k", ByteUtil.toShort(1124L));
		Assert.assertEquals("1023k", ByteUtil.toShort(1047552L));
		// 为简化起见，暂不处理按精度截取进位后等于进率的情况
		// Assert.assertEquals("1M", ByteUtil.toShort(1047553L));
		// Assert.assertEquals("1M", ByteUtil.toShort(1048575L));
		Assert.assertEquals("1024k", ByteUtil.toShort(1047553L));
		Assert.assertEquals("1024k", ByteUtil.toShort(1048575L));
		Assert.assertEquals("1M", ByteUtil.toShort(1048576L));
		Assert.assertEquals("2M", ByteUtil.toShort(1048577L));
		Assert.assertEquals("2M", ByteUtil.toShort(2097152L));
		Assert.assertEquals("1G", ByteUtil.toShort(1073741824L));
		Assert.assertEquals("1T", ByteUtil.toShort(1099511627776L));
		Assert.assertEquals("1P", ByteUtil.toShort(1125899906842624L));
		//
		Assert.assertEquals("1.01M", ByteUtil.toShort(1048577L, 2));
		Assert.assertEquals("2M", ByteUtil.toShort(2097151L, 2));
		Assert.assertEquals("1.12M", ByteUtil.toShort(1165072L, 2));
	}
	
	@Test
	public void testCutZeroAndDot() {
		Assert.assertEquals("0", ByteUtil.cutZeroAndDot("0"));
		Assert.assertEquals("0", ByteUtil.cutZeroAndDot("0."));
		Assert.assertEquals("0", ByteUtil.cutZeroAndDot("0.0"));
		Assert.assertEquals("0", ByteUtil.cutZeroAndDot("0.0000"));
		Assert.assertEquals("0.001", ByteUtil.cutZeroAndDot("0.001"));
		Assert.assertEquals("0.001", ByteUtil.cutZeroAndDot("0.00100"));
		Assert.assertEquals("12", ByteUtil.cutZeroAndDot("12"));
		Assert.assertEquals("12", ByteUtil.cutZeroAndDot("12."));
		Assert.assertEquals("12", ByteUtil.cutZeroAndDot("12.00"));
		Assert.assertEquals("12.01", ByteUtil.cutZeroAndDot("12.01"));
		Assert.assertEquals("12.01", ByteUtil.cutZeroAndDot("12.0100"));
	}
}
