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

public class TestMatchingCreateCampaign {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		WebDriver driver = new FirefoxDriver();
		String baseUrl = "demo.cp.bsd.net/ctl/Core/AdminLogin?m=success";
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
	}

	@Test
	public void testMatchingCreateCampaign() throws Exception {
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
		selenium.click("id=website-pages-matching");
		selenium.waitForPageToLoad("60000");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if ("BSD Demo Control Panel".equals(selenium.getTitle())) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		selenium.click("name=new");
		selenium.waitForPageToLoad("60000");
		assertEquals("BSD Demo Control Panel", selenium.getTitle());
		selenium.click("id=campaign-5");
		selenium.click("css=input[type=\"submit\"]");
		selenium.waitForPageToLoad("60000");
		assertEquals("BSD Demo Control Panel", selenium.getTitle());
		selenium.type("name=name", selenium.getEval("' 1-Clone_Matching_Campaign_Production'+Math.floor(Math.random()*100000)"));
		selenium.type("id=editslug", "clone_match");
		selenium.click("link=Matching Strategies & Emails");
		selenium.type("id=fallback_first_names", "James M. Whitfield");
		selenium.type("id=fallback_zip_codes", "02111");
		selenium.click("id=fallback_next");
		selenium.click("id=submit-button");
		selenium.waitForPageToLoad("60000");
		assertEquals("BSD Demo Control Panel", selenium.getTitle());
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
