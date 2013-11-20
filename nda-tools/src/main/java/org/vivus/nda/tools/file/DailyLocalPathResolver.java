package org.vivus.nda.tools.file;

import java.util.Calendar;

import org.apache.commons.lang3.StringUtils;
import org.vivus.nda.tools.util.PathUtil;

public class DailyLocalPathResolver implements IPathResolver {
	String basePath = ClassLoader.getSystemResource("").toString() + "FileRepository/";

	public DailyLocalPathResolver() {
	}

	public DailyLocalPathResolver(String basePath) {
		setBasePath(basePath);
	}

	@Override
	public String getBasePath() {
		return basePath;
	}

	protected void setBasePath(String basePath) {
		checkBasePath(basePath);
		this.basePath = basePath;
	}

	private void checkBasePath(String basePath) {
		// TODO 检验是否是系统根目录
		if (StringUtils.isBlank(basePath)) {
			throw new FileException("the basePath can't be blank");
		}

	}

	@Override
	public String getPath() {
		Calendar today = Calendar.getInstance();
		return today.get(Calendar.YEAR) + PathUtil.FILE_SEPARATOR + today.get(Calendar.MONTH)
				+ PathUtil.FILE_SEPARATOR + today.get(Calendar.DATE) + PathUtil.FILE_SEPARATOR;
	}

}
