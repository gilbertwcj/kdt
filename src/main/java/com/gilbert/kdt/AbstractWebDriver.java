package com.gilbert.kdt;

import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.UnexpectedTagNameException;

public abstract class AbstractWebDriver implements GuiDriver {

	protected WebDriver driver;
	protected String baseUrl;
	protected boolean acceptNextAlert = true;
	protected StringBuffer verificationErrors = new StringBuffer();

	// driver action

	public void open(String url) {
		driver.get(url);
	}

	public void quit() {
		driver.quit();
	}

	public void setText(String path, String text) {
		WebElement e = findElement(path);
		if (e != null) {
			e.sendKeys(text);
		}
	}

	public void select(String path, int index) {

		try {
			Select s = new Select(findElement(path));
			s.selectByIndex(index);
		} catch (UnexpectedTagNameException e) {
			// ToDo log4j
			e.printStackTrace();
		}

	}

	public void select(String path, String text) {
		try {
			Select s = new Select(findElement(path));
			s.selectByVisibleText(text);
		} catch (UnexpectedTagNameException e) {
			// ToDo log4j
			e.printStackTrace();
		}

	}

	public void click(String path) {
		WebElement e = findElement(path);
		if (e != null) {
			e.click();
		}
	}

	public String getSelectedLabel(String path) {
		String rtn = null;
		WebElement e = findElement(path);
		if (e != null) {
			try {
				Select s = new Select(e);
				rtn = s.getFirstSelectedOption().getText();
			} catch (UnexpectedTagNameException ex) {
				// ToDo log4j
				ex.printStackTrace();
			}
		}
		return rtn;
	}

	public String getSelectedValue(String path) {
		String rtn = null;
		WebElement e = findElement(path);
		if (e != null) {
			try {
				Select s = new Select(e);
				rtn = s.getFirstSelectedOption().getAttribute("value");
			} catch (UnexpectedTagNameException ex) {
				// ToDo log4j
				ex.printStackTrace();
			}
		}
		return rtn;
	}

	public String getValue(String path) {
		return getAttribute("value", path);
	}

	public String getText(String path) {
		String rtn = null;
		WebElement e = findElement(path);
		if (e != null)
			rtn = e.getText();
		return rtn;
	}

	public boolean isVisible(String path) {
		boolean rtn = false;
		WebElement e = findElement(path);
		if (e != null)
			rtn = e.isDisplayed();
		return rtn;
	}

	public boolean isChecked(String path) {
		return getAttribute("checked", path) != null;
	}

	public boolean isEditable(String path) {
		return getAttribute("readonly", path) == null;
	}

	private String getAttribute(String attribute, String path) {
		String rtn = null;
		WebElement element = findElement(path);
		if (element != null) {
			rtn = element.getAttribute(attribute);
		}
		return rtn;
	}

	private WebElement findElement(String path) {
		try {
			if (path.startsWith("id="))
				return driver.findElement(By.id(path.substring(3)));
			else if (path.startsWith("name="))
				return driver.findElement(By.name(path.substring(5)));
			else
				return driver.findElement(By.xpath(path));
		} catch (NoSuchElementException e) {
			return null;
		}
	}

	protected boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	protected boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	protected String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
}
