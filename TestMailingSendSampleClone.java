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

public class TestMailingSendSampleClone {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		WebDriver driver = new FirefoxDriver();
		String baseUrl = "http://chaptdemo.bluestatedigital.com/ctl/Core/AdminLogin?m=success";
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
	}

	@Test
	public void testMailingSendSampleClone() throws Exception {
		selenium.open("http://chaptdemo.bluestatedigital.com/ctl/Core/AdminLogin?m=success");
		verifyTrue(selenium.isTextPresent("Email"));
		assertTrue(selenium.isElementPresent("id=email"));
		selenium.type("id=email", "jwhitfield@bluestatedigital.com");
		verifyTrue(selenium.isTextPresent("Password"));
		selenium.type("id=password", "password");
		assertTrue(selenium.isElementPresent("id=formSubmit"));
		selenium.click("id=formSubmit");
		selenium.waitForPageToLoad("60000");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isTextPresent("Welcome to the Control Panel")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		verifyTrue(selenium.isElementPresent("link=Email"));
		selenium.click("link=Email");
		verifyTrue(selenium.isElementPresent("id=email-all"));
		selenium.click("id=email-all");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isTextPresent("1-GP_Production_Cloned_Mailing_Sample_New")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		assertTrue(selenium.isElementPresent("css=span.send-mailing"));
		selenium.click("css=span.send-mailing");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isTextPresent("Mailing Checklist")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		assertTrue(selenium.isElementPresent("css=div.bd"));
		assertTrue(selenium.isElementPresent("id=test_message_btn"));
		selenium.click("id=test_message_btn");
		assertTrue(selenium.isElementPresent("id=dlg_send_sample_h"));
		verifyTrue(selenium.isTextPresent("Send Sample Message"));
		verifyTrue(selenium.isTextPresent("jwhitfield@bluestatedigital.com"));
		verifyEquals("on", selenium.getValue("id=sample_recipients1"));
		verifyEquals("on", selenium.getValue("id=separate_plain_email"));
		assertTrue(selenium.isElementPresent("id=sample_recipients_text"));
		selenium.type("id=sample_recipients_text", "amy@bluestatedigital.com,jyuska@bluestatedigital.com,lhaiduck@bluestatedigital.com");
		verifyTrue(selenium.isTextPresent("exact:Send a separate plain email?"));
		verifyEquals("on", selenium.getValue("id=separate_plain_email"));
		assertTrue(selenium.isElementPresent("//input[@value='Send Sample']"));
		selenium.click("//input[@value='Send Sample']");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isElementPresent("css=input[type=\"button\"]")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		selenium.click("css=input[type=\"button\"]");
		// Check email for mailing sample...
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
