package com.gilbert.kdt;

import org.openqa.selenium.firefox.FirefoxDriver;

public class FireFoxWebDriver extends AbstractWebDriver {

	public FireFoxWebDriver() {
		this.driver = new FirefoxDriver();
	}
}
