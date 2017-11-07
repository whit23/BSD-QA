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

public class TestMailingCreateSampleViaCloning {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		WebDriver driver = new FirefoxDriver();
		String baseUrl = "http://demo.bluestatedigital.com/ctl/Core/AdminHome";
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
	}

	@Test
	public void testMailingCreateSampleViaCloning() throws Exception {
		selenium.open("http://chaptdemo.bluestatedigital.com/ctl/Core/AdminLogin?m=success");
		verifyTrue(selenium.isTextPresent("Email"));
		assertTrue(selenium.isElementPresent("id=email"));
		selenium.type("id=email", "jwhitfield@bluestatedigital.com");
		verifyTrue(selenium.isTextPresent("Password"));
		selenium.type("id=password", "password");
		assertTrue(selenium.isElementPresent("id=formSubmit"));
		selenium.click("id=formSubmit");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isTextPresent("Welcome to the Control Panel")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		selenium.click("link=Email");
		selenium.click("id=email-create-new");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isTextPresent("Clone and modify an existing mailing")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		selenium.click("link=Clone and modify an existing mailing");
		assertTrue(selenium.isElementPresent("id=ui-dialog-title-cloneAction_dialog"));
		assertTrue(selenium.isElementPresent("css=input[type=\"text\"]"));
		selenium.type("css=input[type=\"text\"]", "70149");
		selenium.typeKeys("css=input[type=\"text\"]", "70149");
		assertTrue(selenium.isElementPresent("link=Choose"));
		selenium.click("link=Choose");
		selenium.waitForPageToLoad("60000");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if ("Maillist Edit BSD Control Panel".equals(selenium.getTitle())) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		selenium.type("name=mailing_name", selenium.getEval("'1-GP_Production_Cloned_Mailing_Sample_New'+Math.floor(Math.random()*100000)"));
		selenium.type("id=description", "Production Guinea Pig Cloned Mailing Test...");
		assertTrue(selenium.isElementPresent("//input[@value='Save and NEXT »']"));
		selenium.click("//input[@value='Save and NEXT »']");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isTextPresent("Mailing Checklist")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		assertTrue(selenium.isElementPresent("id=count_update_link"));
		selenium.click("id=count_update_link");
		verifyTrue(selenium.isTextPresent("Recipients: Members of 1-Recipients_Production_Mailing_QA"));
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isTextPresent("4 recipients")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		selenium.click("link=Email");
		selenium.click("id=email-all");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isTextPresent("1-GP_Production_Cloned_Mailing_Sample_New")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
