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

public class TestCanvassCreateScript {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		WebDriver driver = new FirefoxDriver();
		String baseUrl = "demo.cp.bsd.net/ctl/Core/AdminLogin?m=success";
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
	}

	@Test
	public void testCanvassCreateScript() throws Exception {
		selenium.open("https://demo.cp.bsd.net/ctl/Core/AdminLogin?m=success");
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
		selenium.click("link=Your Website");
		selenium.click("id=website-pages-canvass");
		selenium.waitForPageToLoad("60000");
		assertEquals("BSD Demo Control Panel", selenium.getTitle());
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isTextPresent("Manage Canvass Campaigns")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		assertTrue(selenium.isElementPresent("link=Manage scripts"));
		selenium.click("link=Manage scripts");
		selenium.waitForPageToLoad("60000");
		assertEquals("BSD Demo Control Panel", selenium.getTitle());
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isTextPresent("Manage Scripts")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		assertTrue(selenium.isElementPresent("link=Create new script"));
		selenium.click("link=Create new script");
		selenium.waitForPageToLoad("60000");
		assertEquals("BSD Demo Control Panel", selenium.getTitle());
		assertTrue(selenium.isTextPresent("Create a Script Step 1 of 2"));
		selenium.type("id=scriptName", "Production_Test_Script_Name");
		selenium.type("id=scriptDesc", "Production Test Script Description");
		selenium.click("id=blueScript-submit");
		assertEquals("BSD Demo Control Panel", selenium.getTitle());
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isTextPresent("Edit Script Elements Step 2 of 2")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		selenium.click("css=span");
		selenium.waitForPageToLoad("60000");
		assertEquals("BSD Demo Control Panel", selenium.getTitle());
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
