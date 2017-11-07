package com.example.tests;

import com.thoughtworks.selenium.Selenium;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebDriver;
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.regex.Pattern;
import static org.apache.commons.lang3.StringUtils.join;

public class TestGatewayDeleteAuthnetCVV {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		WebDriver driver = new FirefoxDriver();
		String baseUrl = "http://change-this-to-the-site-you-are-testing/";
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
	}

	@Test
	public void testGatewayDeleteAuthnetCVV() throws Exception {
		selenium.open("http://demo.cp.bsd.net/ctl/Core/AdminLogin?m=success");
		assertEquals("BSD Demo Control Panel : Sign In", selenium.getTitle());
		verifyTrue(selenium.isTextPresent("Email"));
		selenium.type("id=email", "jwhitfield@bluestatedigital.com");
		verifyTrue(selenium.isTextPresent("Password"));
		selenium.type("id=password", "password");
		assertTrue(selenium.isElementPresent("id=formSubmit"));
		selenium.click("id=formSubmit");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isTextPresent("BSD Tools")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		verifyTrue(selenium.isTextPresent("Welcome to the Control Panel"));
		selenium.click("link=BSD Demo");
		selenium.click("id=account-bsd-admin");
		selenium.waitForPageToLoad("60000");
		selenium.click("link=Manage gateways");
		selenium.waitForPageToLoad("60000");
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
