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

public class TestOutreachCreatePersonalCampaign {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		WebDriver driver = new FirefoxDriver();
		String baseUrl = "demo.cp.bsd.net/ctl/Core/AdminLogin?m=success";
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
	}

	@Test
	public void testOutreachCreatePersonalCampaign() throws Exception {
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
		selenium.selectWindow("null");
		selenium.click("id=website-comm-pers-fund");
		selenium.waitForPageToLoad("60000");
		assertEquals("BSD Demo Control Panel", selenium.getTitle());
		selenium.click("name=new");
		selenium.waitForPageToLoad("60000");
		assertEquals("Create Outreach Campaign - BSD Demo Control Panel", selenium.getTitle());
		selenium.click("link=Clone and modify an existing outreach campaign");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isElementPresent("css=tr.even > td.actionColumn > a")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		selenium.click("css=tr.even > td.actionColumn > a");
		selenium.waitForPageToLoad("60000");
		assertEquals("BSD Demo Control Panel", selenium.getTitle());
		selenium.type("id=name", "1-Production_Test_IORC");
		selenium.type("id=slugInput-textBox-value", "1-production-test-iorc");
		selenium.click("name=submit_button");
		selenium.waitForPageToLoad("60000");
		assertEquals("BSD Demo Control Panel", selenium.getTitle());
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
