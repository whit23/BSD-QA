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

public class TestMailingSendClone {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		WebDriver driver = new FirefoxDriver();
		String baseUrl = "https://chaptdemo.bluestatedigital.com/ctl/Core/AdminLogin?m=success";
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
	}

	@Test
	public void testMailingSendClone() throws Exception {
		selenium.open("https://demo.cp.bsd.net/ctl/Core/AdminLogin?m=success");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isTextPresent("Welcome to the Control Panel")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		selenium.click("link=Email");
		selenium.click("id=email-all");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isElementPresent("css=span.send-mailing")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		selenium.click("css=span.send-mailing");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isTextPresent("Mailing Checklist")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		assertTrue(selenium.isElementPresent("id=count_update_link"));
		selenium.click("id=count_update_link");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isTextPresent("exact:Recipients:")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isTextPresent("2 recipients")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		assertTrue(selenium.isElementPresent("//input[@value='Continue to next step']"));
		assertEquals("This is a restricted client. Emails will only be sent to approved domains and email addresses.", selenium.getText("id=restricted-client-warning"));
		assertTrue(selenium.isElementPresent("id=send_mailing_button"));
		selenium.click("id=send_mailing_button");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if ("Done".equals(selenium.getText("id=sending_status"))) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
