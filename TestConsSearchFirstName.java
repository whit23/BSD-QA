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

public class TestConsSearchFirstName {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		WebDriver driver = new FirefoxDriver();
		String baseUrl = "demo.cp.bsd.net/ctl/Core/AdminLogin?m=success";
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
	}

	@Test
	public void testConsSearchFirstName() throws Exception {
		selenium.open("https://demo.cp.bsd.net/ctl/Core/AdminLogin?m=success");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isTextPresent("BSD Tools")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		selenium.click("link=People & Lists");
		selenium.click("id=cons-search");
		selenium.waitForPageToLoad("60000");
		verifyTrue(selenium.isElementPresent("id=clear_search"));
		selenium.click("id=clear_search");
		selenium.click("css=div.ui-dialog-buttonset > button.btn.btn-primary");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isElementPresent("link=Add criterion")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		selenium.click("link=Add criterion");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isTextPresent("Personal & contact information")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		assertTrue(selenium.isElementPresent("link=First name"));
		selenium.click("link=First name");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if ("Adding criterion".equals(selenium.getText("id=ui-dialog-title-criterion-dialog"))) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isTextPresent("First Name")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		selenium.select("name=type", "label=is exactly");
		assertTrue(selenium.isElementPresent("id=search"));
		selenium.type("id=search", "Lester");
		verifyEquals("Ok", selenium.getText("css=div.ui-dialog-buttonset > button.btn.btn-primary"));
		selenium.click("css=div.ui-dialog-buttonset > button.btn.btn-primary");
		verifyEquals("View Count", selenium.getText("css=#view_count"));
		verifyTrue(selenium.isElementPresent("id=view_count"));
		selenium.clickAt("id=view_count", "");
		selenium.click("css=#view_records");
		verifyTrue(selenium.getText("css=div.query-header-top").matches("^[\\s\\S]*count[\\s\\S]*$"));
		verifyTrue(selenium.isTextPresent("first name"));
		verifyTrue(selenium.isTextPresent("Lester"));
		selenium.click("id=clear_search");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isElementPresent("//div[5]/div")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		verifyTrue(selenium.isTextPresent("Confirm"));
		verifyTrue(selenium.isTextPresent("exact:Are you sure you want to clear this search?"));
		assertTrue(selenium.isElementPresent("css=div.ui-dialog-buttonset > button.btn.btn-primary"));
		selenium.click("css=div.ui-dialog-buttonset > button.btn.btn-primary");
		selenium.click("css=span");
		selenium.waitForPageToLoad("60000");
		verifyEquals("BSD Control Panel", selenium.getTitle());
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
