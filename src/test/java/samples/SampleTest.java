package samples;

import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.gilbert.kdt.AbstractWebDriver;

public class SampleTest extends AbstractWebDriver {

	@BeforeClass
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		baseUrl = "https://github.com";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testA() throws Exception {
		// ERROR: Caught exception [Error: locator strategy either id or name
		// must be specified explicitly.]
	}

	@AfterClass
	public void tearDown() throws Exception {
		driver.quit();
	}
}
