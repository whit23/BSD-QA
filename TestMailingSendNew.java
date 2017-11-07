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

public class TestMailingSendNew {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		WebDriver driver = new FirefoxDriver();
		String baseUrl = "https://chaptdemo.bluestatedigital.com/ctl/Core/AdminLogin?m=success";
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
	}

	@Test
	public void testMailingSendNew() throws Exception {
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

		verifyTrue(selenium.isTextPresent("Welcome to the Control Panel"));
		verifyTrue(selenium.isElementPresent("link=Email"));
		selenium.click("link=Email");
		verifyTrue(selenium.isElementPresent("id=email-all"));
		selenium.click("id=email-all");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isTextPresent("1-Production_GP_Mailing_New")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		assertTrue(selenium.isElementPresent("css=span.send-mailing"));
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

		verifyTrue(selenium.isTextPresent("Members of 1-Recipients_Production_Mailing_QA"));
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isTextPresent("4 recipients")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		assertTrue(selenium.isElementPresent("//input[@value='Continue to next step']"));
		selenium.click("//input[@value='Continue to next step']");
		assertEquals("This is a restricted client. Emails will only be sent to approved domains and email addresses.", selenium.getText("id=restricted-client-warning"));
		assertTrue(selenium.isElementPresent("id=send_mailing_button"));
		selenium.click("id=send_mailing_button");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if ("queueing to prepare".equals(selenium.getText("id=sending_status"))) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		verifyEquals("Your mailing is in the process of being sent. The process will continue without further interaction from you, and you may now safely close this page or browse to another. You can monitor the mailing's progress from the Manage Mailings page.", selenium.getText("css=#sending_message_sent > p"));
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if ("Sending! - preparing".equals(selenium.getText("css=#sending_message_sent > h2"))) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if ("Sending!".equals(selenium.getText("css=span.step_description"))) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

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
