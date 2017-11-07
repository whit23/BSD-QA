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

public class TestAdminDatabaseUpdatesCompleted {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		WebDriver driver = new FirefoxDriver();
		String baseUrl = "demo.cp.bsd.net/ctl/Core/AdminLogin?m=success";
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
	}

	@Test
	public void testAdminDatabaseUpdatesCompleted() throws Exception {
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
		verifyTrue(selenium.isElementPresent("//li[@id='nav-account']/a/span[2]"));
		selenium.click("//li[@id='nav-account']/a/span[2]");
		selenium.click("id=account-bsd-admin");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if ("BSD Demo Control Panel".equals(selenium.getTitle())) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isTextPresent("Site Configuration")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		assertTrue(selenium.isElementPresent("link=Database updates"));
		selenium.click("link=Database updates");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isTextPresent("Analyze Database")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		assertTrue(selenium.isTextPresent("Table structure is up-to-date for all tables."));
		selenium.click("css=#nav-home > span");
		selenium.waitForPageToLoad("60000");
		assertEquals("BSD Demo Control Panel", selenium.getTitle());
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
