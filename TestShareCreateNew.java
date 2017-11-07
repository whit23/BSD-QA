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

public class TestShareCreateNew {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		WebDriver driver = new FirefoxDriver();
		String baseUrl = "demo.cp.bsd.net/ctl/Core/AdminLogin?m=success";
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
	}

	@Test
	public void testShareCreateNew() throws Exception {
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
		selenium.click("id=website-pages-share");
		selenium.waitForPageToLoad("60000");
		assertEquals("BSD Demo Control Panel | Share Pages", selenium.getTitle());
		selenium.click("name=new_invite_page");
		selenium.waitForPageToLoad("60000");
		assertEquals("Create Share Page - BSD Demo Control Panel", selenium.getTitle());
		selenium.click("link=Create a new share page");
		selenium.waitForPageToLoad("60000");
		assertEquals("BSD Demo Control Panel", selenium.getTitle());
		selenium.type("id=name", selenium.getEval("'Production_Share_Page_Name'+Math.floor(Math.random()*10000)"));
		selenium.type("id=slugInput-textBox-value", selenium.getEval("'production_share_page_name'+Math.floor(Math.random()*10000)"));
		verifyTrue(selenium.isTextPresent("Default Wrapper"));
		selenium.type("id=html_header", "Above Form Content (HTML Allowed)");
		selenium.type("id=html_footer", "Below Form Content (HTML Allowed)");
		selenium.type("id=bsd-submit-button-label", "Send Share Message");
		selenium.type("id=html_thankyou", "Thank You Options - Thank You Message: Share Thanks!!!");
		assertTrue(selenium.isElementPresent("name=default_subject"));
		selenium.type("name=default_subject", "Email Sharing Subject - Production Test");
		assertTrue(selenium.isElementPresent("name=default_body"));
		selenium.type("name=default_body", "Email Sharing Body (text only - no HTML)");
		selenium.click("name=submit_button");
		selenium.waitForPageToLoad("60000");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if ("BSD Demo Control Panel | Share Pages".equals(selenium.getTitle())) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
