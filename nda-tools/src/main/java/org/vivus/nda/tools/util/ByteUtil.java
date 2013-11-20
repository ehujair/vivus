package org.vivus.nda.tools.util;

public class ByteUtil {
	public static final int STEP = 1024;

	public static String toShort(long size) {
		return toShort(size, 0);
	}

	/**
	 * 将字节大小转换为“数字+单位”的简短形式以进行显示，截取方式为取最小整数(Math.ceil(double))
	 * 
	 * @param size
	 *            字节大小，单位为byte
	 * @param precision
	 *            精度，小数点后保留的位数
	 * @return
	 */
	public static String toShort(long size, int precision) {
		if (precision < 0) {
			throw new IllegalArgumentException("the parameter precision can not < 0");
		}
		ByteSize byteSize = toShort(new ByteSize((double) size, ByteUnit.B));
		return toString(byteSize, precision);
	}

	private static ByteSize toShort(ByteSize byteSize) {
		if (byteSize.getValue() < STEP) {
			return byteSize;
		}
		ByteUnit unit = ByteUnit.valueOf(byteSize.getUnit().ordinal() + 1);
		if (unit == null) {
			return byteSize;
		}
		return toShort(new ByteSize(byteSize.getValue() / STEP, unit));
	}

	private static String toString(ByteSize byteSize, int precision) {
		if (precision < 0) {
			throw new IllegalArgumentException("the parameter precision can not < 0");
		}
		return cutZeroAndDot(ceil(byteSize.getValue(), precision) + "") + byteSize.getUnit().getPrefix();
	}

	public static double ceil(double value, int precision) {
		double multiple = Math.pow(10, precision);
		return Math.ceil(value * multiple) / multiple;
	}

	/**
	 * 使用java正则表达式去掉多余的.与0
	 */
	public static String cutZeroAndDot(String s) {
		if (s.indexOf(".") > 0) {
			// 去掉多余的0
			s = s.replaceAll("0+?$", "");
			// 如最后一位是.则去掉
			s = s.replaceAll("[.]$", "");
		}
		return s;
	}

	private ByteUtil() {
	}

	public static enum ByteUnit {
		B("B"), K("k"), M("M"), G("G"), T("T"), P("P"), E("E"), Z("Z"), Y("Y");

		private String prefix;

		// http://en.wikipedia.org/wiki/Metric_prefix
		// http://www.bipm.org/en/si/si_brochure/chapter3/prefixes.html
		// http://www.bipm.org/en/si/prefixes.html
		ByteUnit(String prefix) {
			this.prefix = prefix;
		}

		public String getPrefix() {
			return prefix;
		}

		public static ByteUnit valueOf(int ordinal) {
			ByteUnit[] values = values();
			if (ordinal < values.length) {
				return values[ordinal];
			}
			return null;
		}

		@Override
		public String toString() {
			return prefix;
		}
	}

	static class ByteSize {
		private double value;
		private ByteUnit unit;

		public ByteSize(double value, ByteUnit unit) {
			this.value = value;
			this.unit = unit;
		}

		public double getValue() {
			return value;
		}

		public ByteUnit getUnit() {
			return unit;
		}

		@Override
		public String toString() {
			return value + unit.getPrefix();
		}
	}
}
