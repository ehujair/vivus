package org.vivus.nda.tools.impl;

import org.vivus.nda.tools.IMacAddressService;
import org.vivus.nda.tools.cmd.GetMacCmd;
import org.vivus.nda.tools.cmd.SaveMacAddressCmd;
import org.vivus.nda.tools.cmd.WriteMacCmd;
import org.vivus.nda.tools.ddmlib.AdbHelper;
import org.vivus.nda.tools.entity.MacAddress;

public class MacAddressService extends AService implements IMacAddressService {
	@Override
	public int getMac() {
		return getCommandExecutor().execute(new GetMacCmd());
	}

	public int saveMacAddress(MacAddress macAddress) {
		return getCommandExecutor().execute(new SaveMacAddressCmd(macAddress));
	}

	@Override
	public int writeMac(AdbHelper adbHelper, MacAddress macAddress) {
		try {
			Integer id = getCommandExecutor().execute(new WriteMacCmd(adbHelper, macAddress));
			adbHelper.printNewMac();
			adbHelper.print("写MAC地址成功");
			return id;
		} catch (Exception e) {
			adbHelper.print("写MAC地址出错，请重试");
//			adbHelper.print(ExceptionUtils.getStackTrace(e));
			throw new RuntimeException("", e);
		}
	}
}
