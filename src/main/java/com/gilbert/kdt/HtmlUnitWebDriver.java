package com.gilbert.kdt;

import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class HtmlUnitWebDriver extends AbstractWebDriver {

	public HtmlUnitWebDriver() {
		this.driver = new HtmlUnitDriver();
	}
}
