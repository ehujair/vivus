package org.vivus.nda.tools.impl;

import org.vivus.nda.tools.IEngine;
import org.vivus.nda.tools.IMacAddressService;
import org.vivus.nda.tools.config.AConfiguration;

public class Engine implements IEngine {
	AConfiguration configuration;
	IMacAddressService macAddressService;

	public Engine(AConfiguration configuration) {
		this.configuration = configuration;
		macAddressService = configuration.getMacAddressService();
	}

	public AConfiguration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(AConfiguration configuration) {
		this.configuration = configuration;
	}

	public IMacAddressService getMacAddressService() {
		return macAddressService;
	}

	public void setMacAddressService(IMacAddressService macAddressService) {
		this.macAddressService = macAddressService;
	}

}
